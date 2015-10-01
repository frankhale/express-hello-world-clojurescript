(ns hello-express-clojurescript.core
  (:require [cljs.nodejs :as node]
            [hello-express-clojurescript.views :as views]
            [hello-express-clojurescript.routes :as routes]))

(node/enable-util-print!)

(def express (node/require "express"))
(def path (node/require "path"))
(def favicon (node/require "serve-favicon"))
(def logger (node/require "morgan"))
(def cookieParser (node/require "cookie-parser"))
(def bodyParser (node/require "body-parser"))
(def app (express))
(def http (node/require "http"))
(def server (.createServer http app))
(def debug ((node/require "debug") "hello-express:server"))

(defn normalizePort [val]
  (let [port (js/parseInt val 10)]
    (if (js/isNaN port)
      val
      (if (>= port 0)
        port
        false))))

(def port (normalizePort
  (if-not (nil? (.-env.PORT node/process)) (.-env.PORT node/process) "3000")))

(defn setup-express-config []
  (-> app
    (.set "port" port)
    (.use (logger "dev"))
    (.use (.json bodyParser))
    (.use (.urlencoded bodyParser #js { :extended false }))
    (.use (.static express (.join path js/__dirname "public")))
    (.use "/" routes/router)
    (.use (fn [req res next]
      (let [err (js/Error. "Not Found")]
        (set! (.-status err) 404)
        (next err))))))

(defn setup-error-handler []
  (.use app (fn [err req res next]
    (let [dev? (if (= (.get app "env") "development") true false)
          err-status (.-status err)
          err-message (.-message err)]
      (.status res (if-not (nil? err-status) err-status 500))
      (.send res (views/error err-message (when dev? err)))))))

(defn server-error-handler [error]
  (if-not (= (.-syscall error) "listen")
    (throw error)
    (let [bind (if (= (type port) "string") (str "Pipe" port) (str "Port" port))]
      (case (.-code error)
        "EACCES" (fn []
          (js/console.error (str bind " requires elevated privileges")
          (node/process.exit 1)))
        "EADDRINUSE" (fn []
          (js/console.error (str bind " is already in use")))))))

(defn server-listening-handler []
   (let [addr (.address server)
         bind (if (= (type addr) "string") (str "pipe" addr) (str "port" (.-port addr)))]
     (debug (str "Listening on " bind))))

(defn -main [& args]
  (println (str "Starting up Express on http://localhost:" port))
  (setup-express-config)
  (setup-error-handler)
  (-> server
    (.listen port)
    (.on "error" server-error-handler)
    (.on "listening" server-listening-handler)))

(set! *main-cli-fn* -main)

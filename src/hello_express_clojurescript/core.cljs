(ns hello-express-clojurescript.core
  (:require [cljs.nodejs :as node]
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
  (.set app "port" port)
  (.set app "views" (.join path js/__dirname "views"))
  (.set app "view engine" "hbs")
  (.use app (logger "dev"))
  (.use app (.json bodyParser))
  (.use app (.urlencoded bodyParser #js { :extended false }))
  (.use app (.static express (.join path js/__dirname "public")))
  (.use app "/" routes/router)
  (.use app (fn [req res next]
    (let [err (js/Error. "Not Found")]
      (set! (.-status err) 404)
      (next err)))))

(defn setup-error-handler []
  (.use app (fn [err req res next]
    (let [dev? (if (= (.get app "env") "development") true false)
          err-status (.-status err)
          err-message (.-message err)]
      (.status res (if-not (nil? err-status) err-status 500))
      (.render res "error" #js {
        :message err-message
        :error (if dev? err #js {})})))))

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
  (println "Starting up Express...")
  (setup-express-config)
  (setup-error-handler)
  (.listen server port)
  (.on server "error" server-error-handler)
  (.on server "listening" server-listening-handler))

(set! *main-cli-fn* -main)

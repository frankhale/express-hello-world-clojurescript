(ns hello-express-clojurescript.routes
  (:require [cljs.nodejs :as node]
            [hello-express-clojurescript.views :as views]))

(def express (node/require "express"))
(def router (.Router express))

(defn- get-route [loc callback]
  (.get router loc (fn [req res next]
    (let [view-result (callback req res next)]
      (.send res view-result)))))

(get-route "/" #(views/index "Express from Clojurescript"))
(get-route "/test" #(views/index "Just another test route"))
(get-route "/foo" #(views/index "Foobar was here..."))

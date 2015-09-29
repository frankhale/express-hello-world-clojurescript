(ns hello-express-clojurescript.routes
  (:require [cljs.nodejs :as node]))

(def express (node/require "express"))
(def router (.Router express))

(.get router "/" (fn [req res next]
  (.render res "index" #js { :title "Express from Clojurescript" })))

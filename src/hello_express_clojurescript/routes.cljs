(ns hello-express-clojurescript.routes
  (:require [cljs.nodejs :as node]
            [hello-express-clojurescript.util :as util :refer [get-route]]
            [hello-express-clojurescript.views :as views]))

(def router (.Router (node/require "express")))

(get-route router "/"
  #(views/index "Express from Clojurescript"))

(get-route router "/test"
  #(views/index "Just another test route"))

(get-route router "/foo"
  #(views/index "Foobar was here..."))

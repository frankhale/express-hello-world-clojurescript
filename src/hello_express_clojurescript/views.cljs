(ns hello-express-clojurescript.views
  (:require-macros [hiccups.core :as hiccups :refer [html html5]])
  (:require [cljs.nodejs :as node]
            [hiccups.runtime :as hiccupsrt]))

;(node/enable-util-print!)

(hiccups/defhtml layout [title view]
  (html5
    [:head
      [:title title]
      [:link {:rel "stylesheet", :href "/stylesheets/style.css"}]]
      [:body view]))

(defn index [title]
  (layout title (html
                  [:div
                    [:h1 title]
                      [:p (str "Welcome to " title)]])))

(defn error [message error]
  (layout "Error"
    (html
      [:div
        [:h1 message]
          (if-not (nil? error)
            [:h2 (.-status error)]
            [:pre (.-stack error)])])))

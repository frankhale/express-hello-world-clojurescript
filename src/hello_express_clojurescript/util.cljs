(ns hello-express-clojurescript.util)

(defn get-route [router loc callback]
  (.get router loc (fn [req res next]
    (.send res (callback req res next)))))

(defn post-route [router loc callback]
  (.post router loc (fn [req res next]
    (.send res (callback req res next)))))

(defproject hello-express-clojurescript "0.1.0-SNAPSHOT"
  :description "FIXME: A loose port of an express-generator generated hello,world to Clojurescript"
  :url "https://github.com/frankhale/express-hello-world-clojurescript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"project" "file:repo"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [io.nervous/cljs-nodejs-externs "0.2.0"]
                 [local/hiccups "0.4.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-npm "0.6.1"]]
  :source-paths ["src"]
  :main "hello-express.js"
  :npm { :dependencies [[body-parser "~1.13.2"]
                        [cookie-parser "~1.3.5"]
                        [debug "~2.2.0"]
                        [express "~4.13.1"]
                        [hbs "~3.1.0"]
                        [morgan "~1.6.1"]
                        [serve-favicon "~2.3.0"]]}
  :cljsbuild
    {:builds [{:id "dev"
              :source-paths ["src/hello_express_clojurescript"]
              :compiler {:language-in :ecmascript5-strict
                         :language-out :ecmascript5-strict
                         :output-to "hello-express.js"
                         :output-dir "out/server-dev"
                         :source-map "hello-express.js.map"
                         :target :nodejs
                         :optimizations :simple
                         :pretty-print true}}
              {:id "prod"
               :source-paths ["src/hello_express_clojurescript"]
               :compiler {:language-in :ecmascript5-strict
                          :language-out :ecmascript5-strict
                          :output-to "hello-express.min.js"
                          :output-dir "out/server-prod"
                          :source-map "hello-express.min.js.map"
                          :target :nodejs
                          :closure-warnings {:externs-validation :off
                                             :non-standard-jsdoc :off}
                          :externs
                            ["node_modules/body-parser/index.js"
                             "node_modules/express/lib/middleware/init.js"
                             "node_modules/express/lib/middleware/query.js"
                             "node_modules/express/lib/router/index.js"
                             "node_modules/express/lib/router/layer.js"
                             "node_modules/express/lib/router/route.js"
                             "node_modules/express/lib/application.js"
                             "node_modules/express/lib/express.js"
                             "node_modules/express/lib/request.js"
                             "node_modules/express/lib/response.js"
                             "node_modules/express/lib/utils.js"
                             "node_modules/express/lib/view.js"
                             "externs/externs.js"]
                          :optimizations :advanced
                          :pretty-print true}}]})

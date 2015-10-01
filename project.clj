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
            [lein-npm "0.6.1"]
            [lein-figwheel "0.4.0"]]
  :clean-targets ^{:protect false} ["target"]
  :source-paths ["src"]
  :npm { :dependencies [[body-parser "~1.13.2"]
                        [cookie-parser "~1.3.5"]
                        [debug "~2.2.0"]
                        [express "~4.13.1"]
                        [hbs "~3.1.0"]
                        [morgan "~1.6.1"]
                        [serve-favicon "~2.3.0"]
                        [ws "~0.8.0"]]}
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/hello_express_clojurescript"]
              :figwheel true
              :compiler {:language-in :ecmascript5-strict
                         :language-out :ecmascript5-strict
                         :main hello-express-clojurescript.core
                         :output-to "target/server-dev/hello-express.js"
                         :output-dir "target/server-dev"
                         :target :nodejs
                         :optimizations :none
                         :pretty-print true}}]})

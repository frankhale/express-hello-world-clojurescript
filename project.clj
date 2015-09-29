(defproject hello-express-clojurescript "0.1.0-SNAPSHOT"
  :description "FIXME: A loose port of an express-generator generated hello,world to Clojurescript"
  :url "https://github.com/frankhale/express-hello-world-clojurescript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]]
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
  :cljsbuild {
            :builds [{:id "server"
                      :source-paths ["src/hello_express_clojurescript"]
                      :compiler {:language-in :ecmascript5
                                 :language-out :ecmascript5
                                 :output-to "hello-express.js"
                                 :output-dir "out/server"
                                 :target :nodejs
                                 :optimizations :simple
                                 :pretty-print true}}]})

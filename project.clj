(defproject clojure-web-app "1.0.0"
  :description "Projeto Clojure que controla usuarios, carteiras e operações de investimento"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.layerware/hugsql "0.5.1"]
                 [com.layerware/hugsql-adapter-next-jdbc "0.5.1"]
                 [org.postgresql/postgresql "42.2.2"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [org.clojure/tools.logging "0.2.3"]
                 [log4j/log4j "1.2.16"]
                 [funcool/clojure.jdbc "0.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clojure.java-time "0.3.2"]]
  :main clojure-web-app.core
  :aot [clojure-web-app.core]
  :uberjar-name "clojure-web-app"
  :target-path "target/%s")
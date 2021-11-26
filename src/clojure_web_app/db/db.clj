(ns clojure-web-app.db.db
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter])
  (:gen-class))

;; (def db
;;   {:subname "//localhost:5432/postgres"
;;    :host "localhost"
;;    :port "5432"
;;    :dbname "postgres"
;;    :subprotocol "postgres"
;;    :dbtype "postgres"
;;    :user "postgres"
;;    :password "twitter"})
(def db
  {:subname "//db:5432/postgres"
   :host "db"
   :port "5432"
   :dbname "postgres"
   :subprotocol "postgres"
   :dbtype "postgres"
   :user "postgres"
   :password "mydummypass123"})

(hugsql/def-db-fns
  "clojure_web_app/db/sql/queries.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

(hugsql/def-sqlvec-fns
  "clojure_web_app/db/sql/queries.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})


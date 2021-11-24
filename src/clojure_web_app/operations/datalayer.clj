(ns clojure-web-app.operations.datalayer
  (:require [clojure-web-app.db.db :refer :all]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure-web-app.operations.validation :as v]
            [clojure.pprint :as pp]
            [java-time :as t])
  (:import java.util.UUID)
  (:gen-class))



  ;; * DATA ACCESS LAYER - CREATE


(defn post-operation
  "Create a operation"
  [operation]
  (let [is-valid (v/validate-operation operation)]
    (when is-valid
      (pp/pprint operation)
      (sql-insert-operation db 
        (assoc operation :idWallet (java.util.UUID/fromString (:idWallet operation)))))))
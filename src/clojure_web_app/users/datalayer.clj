(ns clojure-web-app.users.datalayer
  (:require [clojure-web-app.db.db  :refer :all]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure-web-app.users.validation :as v])
  (:import java.util.UUID)
  (:gen-class))

;; * DATA ACCESS LAYER - CREATE
(defn post-user
  "Create a user"
  [user]
  (let [is-valid (v/validate-user user)]
    (when is-valid
      (sql-insert-user db {:name (:name user)}))))

;; * DATA ACCESS LAYER - GET ALL
(defn fetch-users
  "returns all users"
  []
  (let [result (sql-select-users db)] result))


;; * DATA ACCESS LAYER - GET BY ID


(defn fetch-user-by-id
  "returns one user"
  [id]
  (let [result
        (sql-select-user-by-id db {:id (num (read-string id))})] (if (nil? result) [] result)))
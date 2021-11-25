(ns clojure-web-app.wallets.datalayer
  (:require [clojure-web-app.db.db  :refer :all]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure-web-app.wallets.validation :as v])
  (:import java.util.UUID)
  (:gen-class))

(defn fetch-wallet-by-id
  "returns wallet and it's operations"
  [id]
  (let [result
        (sql-select-wallet-by-id db {:id (java.util.UUID/fromString id)})]
    (assoc {}
           :wallet (name (first (keys (group-by :name result))))
           :operations (map #(dissoc % :name) result))))

;; * DATA ACCESS LAYER - GET BY USER ID
(defn fetch-wallet-by-user-id
  "returns one wallet"
  [id]
  (let [result
        (sql-select-wallet-by-user-id db {:id (num (read-string id))})]
    (assoc {}
           :name (name (first (keys (group-by :user result))))
           :wallets (map #(dissoc % :user) result))))

;; * DATA ACCESS LAYER - CREATE
(defn post-wallet
  "Create a wallet"
  [wallet]
  (let [is-valid (v/validate-wallet wallet)]
    (when is-valid
      (sql-insert-wallet db (assoc wallet :id (java.util.UUID/randomUUID))))))

;; * DATA ACCESS LAYER - CREATE
(defn update-wallet
  "updates a wallet"
  [wallet]
  (let [is-valid (v/validate-wallet wallet)]
    (when is-valid
      (sql-update-wallet db
                         (assoc wallet :id (java.util.UUID/fromString (:id wallet)))))))

;; * DATA ACCESS LAYER - DELETE
(defn delete-wallet
  "deletes one user"
  [id]
  (sql-delete-wallet-by-id db
                         {:id (java.util.UUID/fromString id)}))
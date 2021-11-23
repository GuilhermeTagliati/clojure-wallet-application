(ns clojure-web-app.wallets.datalayer
    (:require [clojure-web-app.db.db  :refer :all]
              [cheshire.core :as json]
              [clojure.string :as str]
              [clojure-web-app.wallets.validation :as v])
    (:import java.util.UUID)
    (:gen-class))
  

  
  ;; * DATA ACCESS LAYER - GET ALL
  ;; (defn fetch-wallets
  ;;   "returns all wallets"
  ;;   [idUser]
  ;;   (let [result (sql-select-wallets db {:idUser (num (read-string idUser))})] result))
  
  
  ;; * DATA ACCESS LAYER - GET BY USER ID
   
  (defn fetch-wallet-by-user-id
    "returns one wallet"
    [id]
    (let [result
          (sql-select-wallet-by-user-id db {:id (num (read-string id))})] (group-by :user result)))

;; * DATA ACCESS LAYER - CREATE
(defn post-wallet
  "Create a wallet"
  [wallet]
  (let [is-valid (v/validate-wallet wallet)]
    (when is-valid
      (sql-insert-wallet db (assoc wallet :id (java.util.UUID/randomUUID))))))
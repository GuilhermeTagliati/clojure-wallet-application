(ns clojure-web-app.wallets.datalayer
    (:require [clojure-web-app.db.db  :refer :all]
              [cheshire.core :as json]
              [clojure.string :as str]
              [clojure-web-app.wallets.validation :as v])
    (:import java.util.UUID)
    (:gen-class))
  
  ;; * DATA ACCESS LAYER - CREATE
  (defn post-wallet
    "Create a wallet"
    [wallet]
    (let [is-valid (v/validate-wallet wallet)]
      (when is-valid
        (sql-insert-wallet db {:name (:name wallet)}))))
  
  ;; * DATA ACCESS LAYER - GET ALL
  (defn fetch-wallets
    "returns all wallets"
    []
    (let [result (sql-select-wallets db)] result))
  
  
  ;; * DATA ACCESS LAYER - GET BY ID
  
  
  (defn fetch-wallet-by-id
    "returns one wallet"
    [id]
    (let [result
          (sql-select-wallet-by-id db {:id (num (read-string id))})] result))
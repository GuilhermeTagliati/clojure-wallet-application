(ns clojure-web-app.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clojure-web-app.users.datalayer :as user_dal]
            [clojure-web-app.wallets.datalayer :as wallet_dal]
            [clojure-web-app.operations.datalayer :as operation_dal])
  (:gen-class))

;; * USER HANDLER CREATE
(defn post-user-handler
  [req]
  (let [user-json (:body req)
        saved (try
                (user_dal/post-user user-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info user-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error on creating user")}))

;; * USER HANDLER GET ALL
(defn get-user-handler
  [req]
  (log/info req)
  (let
   [userlist (user_dal/fetch-users)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userlist)}))

;; * USER HANDLER GET BY ID
(defn get-user-byid-handler
  [req]
  (log/info req)
  (let [num (-> req :params :id)
        userObj (user_dal/fetch-user-by-id num)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userObj)}))

;; * USER HANDLER DELETE
(defn delete-user-handler
  [req]
  (let [num (-> req :params :id)
        deleted (try
                  (user_dal/delete-user num)
                  (catch Exception e
                    (do
                      (log/error e)
                      false)))]
    {:status  (if deleted 204 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not deleted)
                "error on deleting user")}))

;; * USER HANDLER UPDATE
(defn update-user-handler
  [req]
  (let [user-json (:body req)
        updated (try
                  (user_dal/update-user user-json)
                  (catch Exception e
                    (do
                      (log/error e)
                      false)))]
    {:status  (if updated 204 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not updated)
                "error on updating user")}))


;; * WALLET HANDLER GET BY USER ID


(defn get-wallets-by-user-handler
  [req]
  (log/info req)
  (let [num (-> req :params :id)
        wallet (wallet_dal/fetch-wallet-by-user-id num)]
    (println num)
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str wallet)}))

(defn get-wallet-by-id-handler
  [req]
  (let [num (-> req :params :id)
        wallet (wallet_dal/fetch-wallet-by-id num)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str wallet)}))

;; * WALLET HANDLER CREATE
(defn post-wallet-handler
  [req]
  (let [wallet-json (:body req)
        saved (try
                (wallet_dal/post-wallet wallet-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info wallet-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error on creating wallet")}))
;; * WALLET HANDLER UPDATE
(defn update-wallet-handler
  [req]
  (let [wallet-json (:body req)
        updated (try
                  (wallet_dal/update-wallet wallet-json)
                  (catch Exception e
                    (do
                      (log/error e)
                      false)))]
    (log/info wallet-json)
    {:status  (if updated 204 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not updated)
                "error on updating wallet")}))
;; * WALLET HANDLER DELETE
(defn delete-wallet-handler
  [req]
  (let [identifier (-> req :params :id)
        deleted (try
                  (wallet_dal/delete-wallet identifier)
                  (catch Exception e
                    (do
                      (log/error e)
                      false)))]
    {:status  (if deleted 204 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not deleted)
                "error on deleting user")}))



;; * OPERATION HANDLER CREATE


(defn post-operation-handler
  [req]
  (let [operation-json (:body req)
        saved (try
                (operation_dal/post-operation operation-json)
                (catch Exception e
                  (do
                    (log/error e)
                    (println e)
                    false)))]
    (log/info operation-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error on creating operation")}))
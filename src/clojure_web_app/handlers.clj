(ns clojure-web-app.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clojure-web-app.users.datalayer :as duser]
            [clojure-web-app.wallets.datalayer :as dwallet])
  (:gen-class))

;; * USER HANDLER CREATE USER
(defn post-user-handler
  [req]
  (let [user-json (:body req)
        saved (try
                (duser/post-user user-json)
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
   [userlist (duser/fetch-users)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userlist)}))

;; * USER HANDLER GET BY ID
(defn get-user-byid-handler
  [req]
  (log/info req)
  (let [num ( -> req :params :id)
        userObj (duser/fetch-user-by-id num)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userObj)}))



;; * WALLET HANDLER GET BY ID
(defn get-wallets-by-user-handler
  [req]
  (log/info req)
  (let [num ( -> req :params :id)
    wallet (dwallet/fetch-wallet-by-user-id num)]
    (println num)
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str wallet)}))    


;; * WALLET HANDLER CREATE USER
(defn post-wallet-handler
  [req]
  (let [wallet-json (:body req)
        saved (try
                (dwallet/post-wallet wallet-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info wallet-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error on creating wallet")}))
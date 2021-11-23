(ns clojure-web-app.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clojure-web-app.users.datalayer :as d])
  (:gen-class))

;; * HANDLER CREATE USER
(defn post-user-handler
  [req]
  (let [user-json (:body req)
        saved (try
                (d/post-user user-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info user-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error on creating user")}))

;; * HANDLER GET ALL
(defn get-user-handler
  [req]
  (log/info req)
  (let
   [userlist (d/fetch-users)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userlist)}))

;; * HANDLER GET BY ID
(defn get-user-byid-handler
  [req]
  (log/info req)
  (let [num ( -> req :params :id)
        userObj (d/fetch-user-by-id num)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str userObj)}))
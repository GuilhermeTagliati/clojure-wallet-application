(ns clojure-web-app.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clojure-web-app.handlers :refer :all]
            [clojure-web-app.users.datalayer :as d])
  (:gen-class))

(defroutes app-routes
  ;; * CREATE OPERATION
  (POST "/operation" [] (mj/wrap-json-body post-operation-handler {:keywords? true}))

  ;; ** WALLET METHODS   
  (GET ["/wallet/GetByUserId/:id", :id #"[0-9]+"] [id] get-wallets-by-user-handler)
  (GET "/wallet" [] get-wallet-by-id-handler)
  (POST "/wallet" [] (mj/wrap-json-body post-wallet-handler {:keywords? true}))
  (PUT "/wallet" [] (mj/wrap-json-body update-wallet-handler {:keywords? true}))
  (DELETE "/wallet" [] delete-wallet-handler)

  ;; * USER METHODS
  (GET "/user" [] get-user-handler)
  (GET ["/user/:id", :id #"[0-9]+"] [id] get-user-byid-handler)
  (POST "/user" [] (mj/wrap-json-body post-user-handler {:keywords? true}))
  (PUT "/user" [] (mj/wrap-json-body update-user-handler {:keywords? true}))
  (DELETE "/user" [] delete-user-handler))

(defn -main [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Running service on port " port))))

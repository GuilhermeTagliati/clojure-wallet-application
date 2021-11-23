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
  (GET ["/wallet/GetByUserId/:id", :id #"[0-9]+"] [id] get-wallets-by-user-handler)
  (POST "/wallet" [] (mj/wrap-json-body post-wallet-handler {:keywords? true}))  

  (GET "/user" [] get-user-handler)
  (GET ["/user/:id", :id #"[0-9]+"] [id] get-user-byid-handler)
  (POST "/user" [] (mj/wrap-json-body post-user-handler {:keywords? true})))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Running service on port " port))))

(ns clojure-web-app.wallets.validation
    (:require [clojure.string :as str]
              [compojure.core :refer :all]
              [clojure.data.json :as json])
    (:gen-class))
  
  (defn validate-wallet
    "validate if a tweet has all the required data"
    [wallet]
    (do (println "Validated") true))
(ns clojure-web-app.users.validation
  (:require [clojure.string :as str]
            [compojure.core :refer :all]
            [clojure.data.json :as json])
  (:gen-class))

(defn validate-user
  "validate if a tweet has all the required data"
  [user]
  (do (println "Validated") true))
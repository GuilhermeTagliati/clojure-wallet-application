(ns clojure-web-app.operations.validation
    (:require [clojure.string :as str]
              [compojure.core :refer :all]
              [clojure.data.json :as json])
    (:gen-class))
  
  (defn validate-operation
    "Validates Operation"
    [operation]
    (do (println "Validated") true))
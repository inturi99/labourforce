(ns labourforce.views.labours
  (:require [labourforce.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn db-form []
  [:div {:id "db-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "db" "What do you want to LABOUR?")
                 (form/text-area "db")
                 (form/submit-button "db!"))])

(defn display-labours [labours]
  [:div {:class "labource sixteen columns alpha omega"}
   (map
    (fn [db] [:h2 {:class "db"} (h (:body db))])
    labours)])

(defn index [labours]
  (layout/common "LABOUR"
                 (db-form)
                 [:div {:class "clear"}]
                 (display-labours labours)))

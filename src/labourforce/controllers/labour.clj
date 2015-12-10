(ns labourforce.controllers.labour
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [labourforce.views.labours :as view]
            [labourforce.models.db :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [db]
  (when-not (str/blank? db)
    (model/create db))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [db] (create db)))


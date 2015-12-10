(ns labourforce.models.db
  (:require [clojure.java.jdbc :as sql]))
(let [db-host "localhost"
      db-port 5432
      db-name "labourforce"]
  (def spec {:classname "org.postgresql.Driver" ; must be in classpath
             :subprotocol "postgresql"
             :subname (str "//" db-host ":" db-port "/" db-name)
                                        ; Any additional keys are passed to the driver
                                        ; as driver-specific properties.
             :user "postgres"
             :password "Design_20"}))

(defn all []
  (into [] (sql/query spec ["select * from labourtest order by id desc"])))

(defn create [db]
  (sql/insert! spec :labourtest [:body] [db]))



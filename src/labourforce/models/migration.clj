(ns labourforce.models.migration
  (:require [clojure.java.jdbc :as sql]
            [labourforce.models.db :as db]))

(defn migrated? []
  (-> (sql/query db/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='labourtest'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands db/spec
                        (sql/create-table-ddl
                         :labourtest
                         [:id :serial "PRIMARY KEY"]
                         [:body :varchar "NOT NULL"]
                         [:created_at :timestamp
                          "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))


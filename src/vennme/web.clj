(ns vennme.web
  (:use noir.core)
  (:require [noir.server :as server])
  (:require com.googlecode.charts4j [GCharts :as GCharts])
  )

(defpage "/" []
         "This is totes my index page. Totes")

(defpage "/chart" []
         )


(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))

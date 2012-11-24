(ns vennme.web
  (:use noir.core)
  ; (:use com.googlecode.charts4j)
  (:require [noir.server :as server])
  (:import [com.googlecode.charts4j GCharts Plots Data])
  )

(defpage "/" []
         "This is totes my index page. Totes")

(defn venn-diagram []
  ; (. GCharts newVennDiagram 100 80 60 30 30 30 10))
  (. GCharts newVennDiagram
     100 ; Size of first circle
     100 ; Size of second circle
     0   ; Size of third circle, 0 for no circle
     25  ; Size of overlapping region
     0 ; Overlaps between 3 circled charts, all zero for us
     0
     0
     ))

(defpage "/up" []
         (let [chart (venn-diagram)]
           (. chart toURLString)
           )
         )


(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))

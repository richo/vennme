(ns vennme.web
  (:use noir.core)
  ; (:use com.googlecode.charts4j)
  (:require [noir.server :as server])
  (:require [vennme.views :as views])
  (:import [com.googlecode.charts4j GCharts Plots Data])
  )

(defpage "/" []
         "This is totes my index page. Totes")

(defn toInt [i]
  (Integer/parseInt i))

(defn venn-diagram [a b ab]
  ; (. GCharts newVennDiagram 100 80 60 30 30 30 10))
  (. GCharts newVennDiagram
     (toInt a)    ; Size of first circle
     (toInt b)    ; Size of second circle
     0            ; Size of third circle, 0 for no circle
     (toInt ab)   ; Size of overlapping region
     0            ; Overlaps between 3 circled charts, all zero for us
     0
     0
     ))

(defpage "/up" {:keys [a b ab]}
         (let [chart (venn-diagram a b ab)]
           (views/up (. chart toURLString))
           )
         )


(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))

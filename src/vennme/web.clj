(ns vennme.web
  (:use noir.core)
  ; (:use com.googlecode.charts4j)
  (:require [noir.server :as server])
  (:require [vennme.views :as views])
  (:import [com.googlecode.charts4j GCharts Plots Data])
  )

(defpage "/" []
         (views/index))

(defn toInt [i]
  (Integer/parseInt i))

(def normalize
  (fn [a b c]
    (let [largest (max a b c)]
      (let [to (/ 100 largest)]
        (map (fn [x] (* x to)) [a b c])
        )
      )
    )
  )

(defn venn-diagram [a b ab]
  ; (. GCharts newVennDiagram 100 80 60 30 30 30 10))
    (let [[na nb nab] (normalize (toInt a) (toInt b) (toInt ab))]
      (. GCharts newVennDiagram
         na    ; Size of first circle
         nb    ; Size of second circle
         0     ; Size of third circle, 0 for no circle
         nab   ; Size of overlapping region
         0     ; Overlaps between 3 circled charts, all zero for us
         0
         0
         )
      )
    )

(defpage "/up" {:keys [a b ab alabel blabel ablabel]}
         (let [chart (venn-diagram a b ab)]
           (. chart setSize 500 500)
           (. chart setCircleLegends alabel blabel "")
           (views/up (. chart toURLString))
           )
         )

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))

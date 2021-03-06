(ns vennme.web
  (:use noir.core)
  ; (:use com.googlecode.charts4j)
  (:require [noir.server :as server])
  (:require [noir.response :as resp])
  (:require [vennme.views :as views])
  (:require [vennme.google-charts :as google-charts])
  (:require [vennme.native-charts :as native-charts])
  )


; (def engine-name 'google-charts)
(def engine-name 'native)

(defpage "/" []
         (views/index))

(def engine
  (cond
     (= engine-name 'google-charts) google-charts/google-venn-diagram
     (= engine-name 'native) (fn [a b ab alabel blabel ablabel]
                               (resp/content-type "image/png" (native-charts/native-venn-diagram a b ab alabel blabel ablabel)))
     ))

(defpage "/up" {:keys [a b ab alabel blabel ablabel]}
         (apply engine [a b ab alabel blabel ablabel])
         )

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))

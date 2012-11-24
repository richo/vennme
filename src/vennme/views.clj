(ns vennme.views
  (:use hiccup.core)
  )

(defn up [url]
  (html [:img {:src url :width "100%" :height "100%"}])
  )

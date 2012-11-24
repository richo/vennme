(ns vennme.views
  (:use hiccup.core)
  )

(defn up [url]
  (html [:img {:src url :width "100%" :height "100%"}])
  )

(defn index []
  (html [:h1 "Venn Diagrams! woo!"]
        [:form {:action "/up" :method "get"}
          [:label "Size of left circle"
            [:input {:type "text" :name "a"}]
           ]
          [:label "Size of right circle"
            [:input {:type "text" :name "b"}]
           ]
          [:label "Size of overlap"
            [:input {:type "text" :name "ab"}]
           ]
            [:input {:type "submit" :name "submit"}]
        ]
        )
  )

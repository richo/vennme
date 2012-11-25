(ns vennme.views
  (:use hiccup.core)
  )

(defn up [url]
  (html [:img {:src url :width "500" :height "500"}])
  )

(defn index []
  (html [:h1 "Venn Diagrams! woo!"]
        [:form {:action "/up" :method "get"}
          [:label "Size of left circle"
            [:input {:type "text" :name "a"}]
           ]
          [:label "Label for left circle"
            [:input {:type "text" :name "alabel"}]
           ]
          [:label "Size of right circle"
            [:input {:type "text" :name "b"}]
           ]
          [:label "Label for right circle"
            [:input {:type "text" :name "blabel"}]
           ]
          [:label "Size of overlap"
            [:input {:type "text" :name "ab"}]
           ]
            [:input {:type "submit" :name "submit"}]
        ]
        )
  )

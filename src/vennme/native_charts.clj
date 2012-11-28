(ns vennme.native-charts
  (:import [java.awt.image BufferedImage])
  (:import [java.awt.geom Ellipse2D])
  (:import [javax.imageio ImageIO])
  (:import [java.io ByteArrayOutputStream])
  (:import [java.io ByteArrayInputStream])
  )

(def img-width 1024)
(def img-height 768)

(defn new-buffered-image []
  (new BufferedImage img-width img-height (. BufferedImage TYPE_INT_ARGB)))

(defn new-ellipse [x y width height]
  (new java.awt.geom.Ellipse2D$Double x y width height))

(defn native-venn-diagram [a b ab alabel blabel ablabel]
  (let [img (new-buffered-image)
        g   (. img createGraphics)
        output (new ByteArrayOutputStream)]
    (. g draw (new-ellipse 50 50 25 25))
    (javax.imageio.ImageIO/write img "png" output)
    (new ByteArrayInputStream (.toByteArray output))
    )
  )

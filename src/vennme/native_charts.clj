(ns vennme.native-charts
  (:import [java.awt.image BufferedImage])
  (:import [java.awt.geom Ellipse2D])
  (:import [java.awt Color])
  (:import [javax.imageio ImageIO])
  (:import [java.io ByteArrayOutputStream])
  (:import [java.io ByteArrayInputStream])
  )

(def img-width 1024)
(def img-height 768)

(def color1
  (new Color 0.7 0.0 0.0 0.4)
  )

(def color2
  (new Color 0.0 0.7 0.0 0.4)
  )

(defn new-buffered-image []
  (new BufferedImage img-width img-height (. BufferedImage TYPE_INT_ARGB)))

(defn toInt [i]
  (Integer/parseInt i))

(defn new-ellipse [x y width height]
  (new java.awt.geom.Ellipse2D$Double x y width height))

(defn native-venn-diagram [a b ab alabel blabel ablabel]
  (let [img (new-buffered-image)
        g   (. img createGraphics)
        output (new ByteArrayOutputStream)
        ia  (toInt a)
        ib  (toInt b)
        iab (toInt ab)]

    (let [
          left-circle (new-ellipse (- (+ (/ img-width 2) iab) (* ia 2)) (- (/ img-height 2) ia)  (* ia 2) (* ia 2))
          right-circle (new-ellipse (- (/ img-width 2) iab) (- (/ img-height 2) ib) (* ib 2) (* ib 2))
         ]
      (. g draw left-circle)
      (. g setColor color1)
      (. g fill left-circle)
      (. g draw right-circle)
      (. g setColor color2)
      (. g fill right-circle)

      (. g setColor Color/BLACK)
      (if (not (nil? alabel))
        (. g drawString alabel (- (+ (/ img-width 2) iab) (* ia 2)) (/ img-height 2)))
      (if (not (nil? blabel))
        (. g drawString blabel (+ (/ img-width 2) iab) (/ img-height 2)))
      (if (not (nil? ablabel))
        (. g drawString ablabel (- (/ img-width 2) iab) (/ img-height 2)))
      )

    (ImageIO/write img "png" output)
    (new ByteArrayInputStream (.toByteArray output))
    )
  )

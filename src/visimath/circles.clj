(ns visimath.circles
  (:import [java.awt.event KeyEvent])
  (:require [quil.core :as qc]))

(defn deg->pos [radius deg cos-k sin-k]
  (let [rads (qc/radians deg)
        posx (* radius (Math/cos (* cos-k rads)))
        posy (* radius (Math/sin (* sin-k rads)))]
    [posx posy]))

(def psize 3)
(def radius 30)

(defn draw []
  (let [frame (qc/frame-count)
        ts (rem frame 360)
        color (rem frame 256)]

    (qc/no-stroke)
    (doseq [x (range 1 9) y (range 1 9)]
      (let [[posx posy] (deg->pos radius ts x y)]
        (qc/with-translation [(* x 3 radius) (* y 3 radius)]
          (qc/with-fill [100 100 255 25]
            (qc/ellipse posx posy psize psize))))))
    ))

(defn setup []
  (qc/smooth)
  (qc/no-stroke)
  (qc/background 0)
  (qc/frame-rate 60))

(defn key-pressed []
  (qc/background 0))

(qc/defsketch circles
  :title "Circles"
  :size [800 800]
  :draw draw
  :setup setup
  :key-pressed key-pressed
  :renderer :opengl)

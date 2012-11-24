(ns demo.web
  (:use ring.adaptor.jetty))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Test string\n"})

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))


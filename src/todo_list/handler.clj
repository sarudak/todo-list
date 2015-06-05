(ns todo-list.handler
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [clojure.string :as s]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))
(def todo-list
  ["goto store" "stop using goto" "learn clojure"]
  )
(defn listify [item]
  [:li item]
  )

(defn main-page [param-list]
  (let [content (slurp (io/resource "main.html"))
        data (html [:ul (map listify param-list)])
        ]
    (s/replace content "My-List-Here" data)
    )
  )

(defroutes app-routes
  (GET "/" [] (main-page todo-list))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))



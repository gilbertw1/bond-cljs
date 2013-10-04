(ns bond-cljs.corse
	(:require [cljs.nodejs :as node]
            [bond-cljs.config :as config]
            [bond-cljs.view.templates :as templates]
            [bond-cljs.view.functions :as vfun]
            [bond-cljs.chat :as chat]
            [bond-cljs.accounts :as accounts]
            [bond-cljs.global :as g]))

;; Initialize Configuration
(config/initialize-configuration!)

;; Render Initial Page
(vfun/render-page! (templates/main-page (or (:display-name @g/config) "User")))

;; Bind Page Events
(vfun/bind-events)

;; Connect Accounts
(doseq [account (:accounts @g/config)]
  (accounts/add-new-account! account))

;; Bind Event Stream to UI
(vfun/bind-event-stream g/in-stream)
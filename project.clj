(defproject antizer "0.3.1-1"
  :description "Antizer"
  :url "https://github.com/priornix/antizer"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [#_[org.clojure/clojure "1.9.0"]
                 #_[org.clojure/clojurescript "1.9.946"]
                 [cljsjs/antd "3.8.0-0"]
                 [cljsjs/moment "2.17.1-1"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox {:language :clojurescript
          :metadata {:doc/format :markdown}
          :output-path "doc/dist/latest/api"
          :namespaces [antizer.core antizer.reagent antizer.rum]})

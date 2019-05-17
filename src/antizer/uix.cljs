(ns antizer.uix
  (:refer-clojure :exclude [list])
  (:require [antizer.core :as ant]
            [goog.object :refer [getValueByKeys]]
            [uix.core.alpha :as uix])
  (:require-macros [antizer.macros :refer [export-funcs export-props export-form-funcs
                                           export-uix-components]]))

(defn adapt-class [react-class args]
  (let [[opts children] (if (map? (first args))
                          [(first args) (rest args)]
                          [nil args])

        opts (->> opts
                  (map (fn [[k v]]
                         (if (vector? v)
                           [k (uix/as-react v)]
                           [k v])))
                  (into {}))
        els (if (sequential? (first children))
              (map uix/as-element children)
              children)]
    (into
      [:> react-class (ant/map-keys->camel-case opts :html-props true)]
      els)))

#_(defn create-form
  "Calls Form.create() decorator with the form to be created. form can be
   any hiccup form. Accepts the following options:

   * :options - map of Form.create() options. Refer to:
                https://ant.design/components/form/#Form.create(options) for
                details
   * :props - the properties hashmap to be passed to the component. Note that
              the received properties will be in the form of a JavaScript associative map"
  [form & {:keys [options props] :or {options {} props {}}}]
  (r/create-element
    (((getValueByKeys js/antd "Form" "create") (clj->js (ant/map-keys->camel-case options)))
     (r/reactify-component form))
    (clj->js props)))

#_(defn get-form
  "Returns the `form` created by Form.create(). This function must be called
   from within the `form` component"
  []
  (-> (r/current-component)
      (r/props)
      (js->clj :keywordize-keys true)
      (:form)))

#_(defn decorate-field
  "Decorate a form field, corresponds to antd's getFieldDecorator() function
   Arguments:

   * form - the `form` object, obtained from `(get-form)`
   * id - field identifier, supports nested fields format in string format
   * options - the validation options for the field
   * field - the input field element that the validation will be applied to

   For more information about the validation options, please refer to:
   https://ant.design/components/form/#getFieldDecorator(id,-options)-parameters"
  ([form id field] (decorate-field form id {} field))
  ([form id options field]
   (let [field-decorator (:getFieldDecorator form)
         params (clj->js (ant/map-keys->camel-case options))]
     ((field-decorator id params) (r/as-element field)))))

(export-form-funcs)
(export-funcs)
(export-props)
(export-uix-components)

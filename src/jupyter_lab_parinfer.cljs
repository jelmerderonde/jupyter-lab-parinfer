(ns jupyter-lab-parinfer
  (:require ["@jupyterlab/notebook" :refer [INotebookTracker]]
            ["@jupyterlab/cells" :refer [CodeCell]]
            ["parinfer-codemirror" :as parinferCodeMirror]))

(defn activate-parinfer
  "Activates Parinfer for a Notebook cell,
  if it is not already activated."
  [^js cell]
  (let [codemirror (.. cell -editor -editor)]
    (if-not (.-__parinfer__ codemirror)
      (.init parinferCodeMirror codemirror)
      nil)))

(defn handle-model-change
  "Called each time the Notebook model changes.
  calls activate-parinfer for all cells if the
  default language is \"clojure\"."
  [notebook]
  (when-let [model (.-model notebook)]
    (let [language (.-defaultKernelLanguage model)]
      (when (= language "clojure")
        (let [cells (.-widgets notebook)]
          (doseq [cell cells]
            (activate-parinfer cell)))))))

(defn handle-widget-change
  "Called each time the user switches to a new
  widget (notebook tab)."
  [_ ^js notebook-panel]
  (if notebook-panel
    (.connect (.. notebook-panel -content -modelContentChanged) handle-model-change)))

(defn handle-active-cell-change
  "Called each time the active cell changes,
  and calls activate-parinfer if it is a CodeCell."
  [_ ^js cell]
  (when (and cell (= (type cell) CodeCell))
    (activate-parinfer cell)))

(defn ^:export activate
  "The activation function required for a
  Jupyter Lab extension."
  [app ^js notebook-tracker]
  (.connect (.-currentChanged notebook-tracker) handle-widget-change)
  (.connect (.-activeCellChanged notebook-tracker) handle-active-cell-change))

(def ^:export extension #js
  {:id "jupyter-lab-parinfer"
   :autoStart true
   :requires #js [INotebookTracker]
   :activate activate})

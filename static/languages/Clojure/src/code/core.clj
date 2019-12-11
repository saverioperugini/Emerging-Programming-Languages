;*******************************************************************************
;
;      filename:  core.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(ns clojure-demo.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  )
(load "core_recursion")
(load "core_functions")
(load "core_objects")
(load "core_macros")
(load "core_data_structures")
(load "core_lazy")
(load "core_control")
(load "core_thread_macros")
(load "core_closures")
(load "core_concurrency")
(load "core_stm")

/* Copyright (c) 2020-2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.util

import so.madprogrammer.PatternFilter

object Filters {
    val FILE = PatternFilter("""[^\\./:*?\"<>|]*""")
    val PATH_WINDOWS = PatternFilter("""[A-Za-z]:[^:*?"<>|]*""")
    // https://stackoverflow.com/a/28989719
    val PATH_UNIX = PatternFilter("""^(/[^/\n]*)+/?$""")

    val INTEGER = PatternFilter("[0-9]*")
}

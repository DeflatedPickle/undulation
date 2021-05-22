/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.extensions

import com.deflatedpickle.marvin.util.OSUtil
import com.deflatedpickle.undulation.util.Filters
import so.madprogrammer.PatternFilter

fun OSUtil.OS.toPatternFilter(): PatternFilter = if (this == OSUtil.OS.WINDOWS) {
    Filters.PATH_WINDOWS
} else {
    Filters.PATH_UNIX
}

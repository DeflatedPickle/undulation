/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.extensions

import javax.swing.JTree

fun JTree.expandAll() {
    var j = this.rowCount
    var i = 0
    while (i < j) {
        this.expandRow(i)
        i += 1
        // We keep expanding rows, increasing the row count
        // So we need to keep updating it's value
        j = this.rowCount
    }
}

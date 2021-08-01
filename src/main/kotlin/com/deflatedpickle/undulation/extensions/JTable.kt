/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.extensions

import javax.swing.JTable

fun JTable.hasColumn(value: Any): Boolean {
    for (i in 0..columnModel.columnCount) {
        if (columnModel.getColumn(i).identifier == value) {
            return true
        }
    }
    return false
}

fun JTable.hasRow(value: Any): Boolean {
    for (r in 0 until model.rowCount) {
        for (c in 0 until model.columnCount) {
            if (model.getValueAt(r, c) == value) {
                return true
            }
        }
    }
    return false
}

fun JTable.findRow(value: Any): Int {
    for (r in 0 until model.rowCount) {
        for (c in 0 until model.columnCount) {
            if (model.getValueAt(r, c) == value) {
                return r
            }
        }
    }
    return -1
}

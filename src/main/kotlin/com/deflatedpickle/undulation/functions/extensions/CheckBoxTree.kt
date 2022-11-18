package com.deflatedpickle.undulation.functions.extensions

import com.jidesoft.swing.CheckBoxTree

fun CheckBoxTree.isSelected(path: String) = this.checkBoxTreeSelectionModel.selectionPaths.filter {
    // println(it.path.toList())
    this.checkBoxTreeSelectionModel.isPathSelected(it, true)
}.map {
    // println(it.path.joinToString("/").replace("root/", ""))
    it.path.joinToString("/").replace("root/", "")
}.contains(path)
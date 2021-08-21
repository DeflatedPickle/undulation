/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.extensions

import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeNode
import javax.swing.tree.TreePath

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

// https://stackoverflow.com/a/8210759
fun JTree.find(path: List<Any>): DefaultMutableTreeNode? {
    val e = (model.root as DefaultMutableTreeNode).depthFirstEnumeration()
    while (e.hasMoreElements()) {
        val node: DefaultMutableTreeNode = e.nextElement() as DefaultMutableTreeNode
        if (node.childCount == 0) continue

        if (path == (node.path.last() as DefaultMutableTreeNode).path.map { (it as DefaultMutableTreeNode).userObject }.drop(1)) {
            return node
        }
    }

    return null
}

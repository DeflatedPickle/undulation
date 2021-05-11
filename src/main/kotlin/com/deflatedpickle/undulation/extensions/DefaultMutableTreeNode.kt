@file:Suppress("UNCHECKED_CAST")

package com.deflatedpickle.undulation.extensions

import java.util.Enumeration
import javax.swing.tree.DefaultMutableTreeNode

// https://stackoverflow.com/a/8210759
fun DefaultMutableTreeNode.findNode(obj: Any): DefaultMutableTreeNode? {
    val e: Enumeration<DefaultMutableTreeNode> = this.depthFirstEnumeration() as Enumeration<DefaultMutableTreeNode>
    while (e.hasMoreElements()) {
        val node: DefaultMutableTreeNode = e.nextElement()
        if (node.userObject == obj) {
            return node
        }
    }
    return null
}
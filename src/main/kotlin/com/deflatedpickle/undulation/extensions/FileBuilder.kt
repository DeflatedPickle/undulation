package com.deflatedpickle.undulation.extensions

import com.deflatedpickle.marvin.builder.Builder
import com.deflatedpickle.marvin.builder.FileBuilder
import com.deflatedpickle.marvin.dsl.DSLFileNode
import java.io.File
import javax.swing.tree.DefaultMutableTreeNode


fun loopNodes(list: List<Builder.Node<File>>, lastNode: DefaultMutableTreeNode) {
    for (i in list) {
        val nextNode = DefaultMutableTreeNode(i.get().name)
        lastNode.add(nextNode)
    }
}

fun loopBuilders(list: List<FileBuilder>, lastNode: DefaultMutableTreeNode) {
    for (i in list) {
        val nextNode = DefaultMutableTreeNode(i.firstNode.get().name)
        loopNodes(i.nodeList.filterIsInstance<DSLFileNode>(), nextNode)
        lastNode.add(nextNode)
        loopBuilders(i.builder.childBuilderList, nextNode)
    }
}

fun FileBuilder.toDefaultMutableTreeNode(): DefaultMutableTreeNode {
    val root = DefaultMutableTreeNode("root")
    // loopNodes(emptyPack.nodeList.filterIsInstance<DSLFileNode>(), this)
    loopBuilders(this.childBuilderList, root)

    return root
}
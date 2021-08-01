/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.predicate.highlight

import java.awt.Component
import javax.swing.JLabel
import org.jdesktop.swingx.JXTable
import org.jdesktop.swingx.decorator.ComponentAdapter
import org.jdesktop.swingx.decorator.HighlightPredicate

class EmptyPredicate(
    val table: JXTable
) : HighlightPredicate {
    override fun isHighlighted(
        renderer: Component,
        adapter: ComponentAdapter
    ): Boolean = (renderer as JLabel).text == ""
}

/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.predicate.highlight

import com.deflatedpickle.marvin.api.Appliable
import java.awt.Component
import javax.swing.JLabel
import org.jdesktop.swingx.JXTable
import org.jdesktop.swingx.decorator.ColorHighlighter
import org.jdesktop.swingx.decorator.ComponentAdapter
import org.jdesktop.swingx.decorator.HighlightPredicate
import java.awt.Color

class EmptyPredicate(
    val table: JXTable
) : HighlightPredicate, Appliable {
    override fun isHighlighted(
        renderer: Component,
        adapter: ComponentAdapter
    ): Boolean = (renderer as JLabel).text == ""

    override fun apply() {
        table.addHighlighter(
            ColorHighlighter(this).apply {
                background = Color.RED
                selectedBackground = Color.RED
            }
        )
    }
}

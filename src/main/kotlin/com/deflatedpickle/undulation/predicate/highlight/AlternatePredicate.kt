/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.predicate.highlight

import com.deflatedpickle.marvin.api.Appliable
import java.awt.Component
import org.jdesktop.swingx.JXTable
import org.jdesktop.swingx.decorator.ColorHighlighter
import org.jdesktop.swingx.decorator.ComponentAdapter
import org.jdesktop.swingx.decorator.HighlightPredicate

class AlternatePredicate(
    val table: JXTable
) : HighlightPredicate, Appliable {
    override fun isHighlighted(
        renderer: Component,
        adapter: ComponentAdapter
    ): Boolean = adapter.row % 2 == 0

    override fun apply() {
        table.addHighlighter(
            ColorHighlighter(this).apply {
                background = table.background.darker()
                // selectedBackground = table.background.darker().darker()
            }
        )
    }
}

package com.deflatedpickle.undulation.functions

import javax.swing.Icon

fun JButton(text: String? = null, icon: Icon? = null, tooltip: String? = null, function: () -> Unit) =
    javax.swing.JButton(text, icon).apply {
        toolTipText = tooltip
        addActionListener {
            function()
        }
    }
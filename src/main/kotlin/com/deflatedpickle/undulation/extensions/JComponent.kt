package com.deflatedpickle.undulation.extensions

import javax.swing.JComponent

fun JComponent.updateUIRecursively() {
    updateUI()

    for (i in components.filterIsInstance<JComponent>()) {
        i.updateUIRecursively()
    }
}
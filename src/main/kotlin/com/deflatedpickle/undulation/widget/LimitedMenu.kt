package com.deflatedpickle.undulation.widget

import javax.swing.JMenu
import javax.swing.JMenuItem

class LimitedMenu(
    text: String,
    var limit: Int,
) : JMenu(text) {
    private fun check() {
        if (menuComponentCount >= limit) {
            for (i in limit..menuComponentCount) {
                remove(0)
            }
        }
    }

    override fun insert(mi: JMenuItem, pos: Int): JMenuItem {
        check()
        return super.insert(mi, pos)
    }

    override fun add(s: String): JMenuItem {
        check()
        return super.add(s)
    }
}
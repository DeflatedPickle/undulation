package com.deflatedpickle.undulation.widget.taginput

import com.deflatedpickle.undulation.constraints.FillBothFinishLine
import com.deflatedpickle.undulation.constraints.FillHorizontalFinishLine
import org.jdesktop.swingx.JXPanel
import org.jdesktop.swingx.JXTextField
import uk.co.timwise.wraplayout.WrapLayout
import java.awt.GridBagLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener


class TagInput : JXPanel() {
    private val field = JXTextField()
    internal val tagArea = JXPanel().apply {
        layout = WrapLayout()
    }

    val tags = mutableListOf<String>()

    init {
        this.layout = GridBagLayout()

        add(field, FillHorizontalFinishLine)
        add(tagArea, FillBothFinishLine)

        field.addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent) {
                if (e.keyChar == ' ') {
                    tags.add(field.text.dropLast(1))
                    val tagComponent = TagComponent(
                        field.text.dropLast(1),
                        this@TagInput
                    )
                    tagArea.add(tagComponent)
                    fireChangedUpdate(ChangeEvent(this))

                    field.text = ""

                    repaint()
                    revalidate()
                }
            }
        })
    }

    fun addChangeListener(listener: ChangeListener) {
        listenerList.add(ChangeListener::class.java, listener)
    }

    internal fun fireChangedUpdate(e: ChangeEvent) {
        val listeners = listenerList.listenerList
        var i = listeners.size - 2
        while (i >= 0) {
            if (listeners[i] == ChangeListener::class.java) {
                (listeners[i + 1] as ChangeListener).stateChanged(e)
            }
            i -= 2
        }
    }
}
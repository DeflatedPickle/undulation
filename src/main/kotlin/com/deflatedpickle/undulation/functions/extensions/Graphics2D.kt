package com.deflatedpickle.undulation.functions.extensions

import java.awt.BasicStroke
import java.awt.Graphics2D
import java.awt.font.TextLayout
import java.awt.geom.AffineTransform

fun Graphics2D.drawOutlinedString(string: String, x: Int, y: Int) {
    val c = color
    val t = transform
    val s = stroke

    transform = AffineTransform.getTranslateInstance(x.toDouble(), y.toDouble())

    val tl = TextLayout(
        string,
        font,
        fontRenderContext
    )

    val shape = tl.getOutline(null)

    color = c.getContrast()
    stroke = BasicStroke(4f)
    draw(shape)
    color = c
    fill(shape)

    transform = t
    stroke = s
}

fun Graphics2D.drawOutlinedRect(x: Int, y: Int, width: Int, height: Int) {
    val c = color
    val s = stroke

    color = c.getContrast()
    stroke = BasicStroke(4f)
    drawRect(x, y, width, height)
    color = c
    stroke = s
    drawRect(x, y, width, height)
}
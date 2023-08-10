@file:Suppress("CovariantEquals")

package com.deflatedpickle.undulation.impl

import java.awt.Font
import java.awt.FontMetrics
import java.awt.Shape
import java.awt.font.FontRenderContext
import java.awt.font.GlyphJustificationInfo
import java.awt.font.GlyphMetrics
import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D

data class ImplGlyphVector(
    private val font: Font,
    private val fontRenderContext: FontRenderContext,
    private val numGlyphs: Int,
    private val glyphCode: Int,
    private val logicalBounds: Rectangle2D,
    private val visualBounds: Rectangle2D,
    private val outline: Shape,
    private val glyphOutline: Shape,
    private var glyphPosition: Point2D,
    private var transform: AffineTransform,
    private val metrics: GlyphMetrics,
    private val justificationInfo: GlyphJustificationInfo?,
) : GlyphVector() {
    override fun equals(set: GlyphVector) = set == this

    override fun getFont() = font

    override fun getFontRenderContext() = fontRenderContext

    override fun performDefaultLayout() {
    }

    override fun getNumGlyphs() = numGlyphs

    override fun getGlyphCode(glyphIndex: Int) = glyphCode

    override fun getGlyphCodes(beginGlyphIndex: Int, numEntries: Int, codeReturn: IntArray?) = intArrayOf(glyphCode)

    override fun getLogicalBounds() = logicalBounds

    override fun getVisualBounds() = visualBounds

    override fun getOutline() = outline

    override fun getOutline(x: Float, y: Float) = outline

    override fun getGlyphOutline(glyphIndex: Int) = glyphOutline

    override fun getGlyphPosition(glyphIndex: Int) = glyphPosition

    override fun setGlyphPosition(glyphIndex: Int, newPos: Point2D) {
        glyphPosition = newPos
    }

    override fun getGlyphTransform(glyphIndex: Int) = transform

    override fun setGlyphTransform(glyphIndex: Int, newTX: AffineTransform) {
        transform = newTX
    }

    override fun getGlyphPositions(beginGlyphIndex: Int, numEntries: Int, positionReturn: FloatArray) = floatArrayOf()

    override fun getGlyphLogicalBounds(glyphIndex: Int) = logicalBounds

    override fun getGlyphVisualBounds(glyphIndex: Int) = visualBounds

    override fun getGlyphMetrics(glyphIndex: Int): GlyphMetrics = metrics

    override fun getGlyphJustificationInfo(glyphIndex: Int) = justificationInfo
}
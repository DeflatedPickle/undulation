@file:Suppress("CovariantEquals")

package com.deflatedpickle.undulation.impl

import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.geom.Point2D

// this exists to initialize the grid with as we dont have the information
// when exported, instances of a non-null implementation will be used
class NullGlyphVector : GlyphVector() {
    override fun equals(set: GlyphVector) = set == this

    override fun getFont() = null

    override fun getFontRenderContext() = null

    override fun performDefaultLayout() {
    }

    override fun getNumGlyphs() = 0

    override fun getGlyphCode(glyphIndex: Int) = -1

    override fun getGlyphCodes(beginGlyphIndex: Int, numEntries: Int, codeReturn: IntArray?) = null

    override fun getLogicalBounds() = null

    override fun getVisualBounds() = null

    override fun getOutline() = null

    override fun getOutline(x: Float, y: Float) = null

    override fun getGlyphOutline(glyphIndex: Int) = null

    override fun getGlyphPosition(glyphIndex: Int) = null

    override fun setGlyphPosition(glyphIndex: Int, newPos: Point2D?) {}

    override fun getGlyphTransform(glyphIndex: Int) = null

    override fun setGlyphTransform(glyphIndex: Int, newTX: AffineTransform?) {}

    override fun getGlyphPositions(beginGlyphIndex: Int, numEntries: Int, positionReturn: FloatArray?) = null

    override fun getGlyphLogicalBounds(glyphIndex: Int) = null

    override fun getGlyphVisualBounds(glyphIndex: Int) = null

    override fun getGlyphMetrics(glyphIndex: Int) = null

    override fun getGlyphJustificationInfo(glyphIndex: Int) = null
}
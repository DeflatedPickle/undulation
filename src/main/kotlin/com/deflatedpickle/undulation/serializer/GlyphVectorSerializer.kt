package com.deflatedpickle.undulation.serializer

import com.deflatedpickle.undulation.impl.ImplGlyphVector
import com.deflatedpickle.undulation.impl.NullGlyphVector
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.geom.GeneralPath
import com.deflatedpickle.undulation.api.mimic.GlyphVector as GlyphVectorMimic

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GlyphVector::class)
object GlyphVectorSerializer : KSerializer<GlyphVector> {
    override fun serialize(encoder: Encoder, value: GlyphVector) {
        encoder.encodeNullableSerializableValue(
            GlyphVectorMimic.serializer(),
            if (value is NullGlyphVector) null
            else GlyphVectorMimic(
                value.font,
                value.fontRenderContext,
                value.numGlyphs,
                value.getGlyphCode(0),
                value.logicalBounds,
                value.visualBounds,
                // TODO: encode the outline and glyphOutline
                // https://stackoverflow.com/questions/26579729/how-to-serialize-java-2d-shape-objects-as-xml
                GeneralPath(),
                GeneralPath(),
                value.getGlyphPosition(0),
                AffineTransform(),
                value.getGlyphMetrics(0),
                null,
            )
        )
    }

    override fun deserialize(decoder: Decoder): GlyphVector {
        val mimic = decoder.decodeNullableSerializableValue(GlyphVectorMimic.serializer().nullable)
            ?: return NullGlyphVector()

        return ImplGlyphVector(
            mimic.font,
            mimic.fontRenderContext,
            mimic.numGlyphs,
            mimic.glyphCode,
            mimic.logicalBounds,
            mimic.visualBounds,
            GeneralPath(),
            GeneralPath(),
            mimic.glyphPosition,
            AffineTransform(),
            mimic.metrics,
            mimic.justificationInfo,
        )
    }
}
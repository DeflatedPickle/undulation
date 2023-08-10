package com.deflatedpickle.undulation.serializer

import com.deflatedpickle.undulation.api.mimic.GlyphMetrics as GlyphMetricsMimic
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.font.GlyphMetrics

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GlyphMetrics::class)
object GlyphMetricsSerializer : KSerializer<GlyphMetrics> {
    override fun serialize(encoder: Encoder, value: GlyphMetrics) {
        // FIXME: use the other constructor with values gotten with reflection
        encoder.encodeSerializableValue(
            GlyphMetricsMimic.serializer(),
            GlyphMetricsMimic(
                value.advance,
                value.bounds2D,
                value.type.toByte(),
            )
        )
    }

    override fun deserialize(decoder: Decoder): GlyphMetrics {
        val mimic = decoder.decodeSerializableValue(GlyphMetricsMimic.serializer())

        return GlyphMetrics(
            mimic.advance,
            mimic.bounds,
            mimic.glyphType,
        )
    }
}
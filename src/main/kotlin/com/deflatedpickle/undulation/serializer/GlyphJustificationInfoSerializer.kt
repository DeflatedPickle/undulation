package com.deflatedpickle.undulation.serializer

import com.deflatedpickle.undulation.api.mimic.GlyphJustificationInfo as GlyphJustificationInfoMimic
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.font.GlyphJustificationInfo
import java.awt.font.GlyphMetrics

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GlyphJustificationInfo::class)
object GlyphJustificationInfoSerializer : KSerializer<GlyphJustificationInfo> {
    override fun serialize(encoder: Encoder, value: GlyphJustificationInfo) {
        encoder.encodeSerializableValue(
            GlyphJustificationInfoMimic.serializer(),
            GlyphJustificationInfoMimic(
                value.weight,
                value.growAbsorb,
                value.growPriority,
                value.growLeftLimit,
                value.growRightLimit,
                value.shrinkAbsorb,
                value.shrinkPriority,
                value.shrinkLeftLimit,
                value.shrinkRightLimit,
            )
        )
    }

    override fun deserialize(decoder: Decoder): GlyphJustificationInfo {
        val mimic = decoder.decodeSerializableValue(GlyphJustificationInfoMimic.serializer())

        return GlyphJustificationInfo(
            mimic.weight,
            mimic.growAbsorb,
            mimic.growPriority,
            mimic.growLeftLimit,
            mimic.growRightLimit,
            mimic.shrinkAbsorb,
            mimic.shrinkPriority,
            mimic.shrinkLeftLimit,
            mimic.shrinkRightLimit
        )
    }
}
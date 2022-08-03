package com.deflatedpickle.undulation.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Color


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Color::class)
object ColorSerializer : KSerializer<Color> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Color",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Color) =
        encoder.encodeString(
            String.format(
                "#%08x",
                (value.alpha and 0xFF shl 24) or (value.red and 0xFF shl 16) or
                        (value.green and 0xFF shl 8) or (value.blue and 0xFF shl 0)
            )
        )

    override fun deserialize(decoder: Decoder): Color {
        val intval = java.lang.Long.decode(decoder.decodeString())
        val i = intval.toInt()
        return Color(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF, i shr 24 and 0xFF)
    }
}
package com.deflatedpickle.undulation.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Color
import java.awt.Point

@ExperimentalSerializationApi
@Serializer(forClass = Color::class)
object ColorSerializer : KSerializer<Color> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Color",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Color) =
        encoder.encodeString(String.format("#%06x", value.rgb and 0xFFFFFF))

    override fun deserialize(decoder: Decoder): Color = Color.decode(decoder.decodeString())
}
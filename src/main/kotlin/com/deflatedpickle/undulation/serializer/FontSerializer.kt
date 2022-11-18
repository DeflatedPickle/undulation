package com.deflatedpickle.undulation.serializer

import com.deflatedpickle.undulation.api.FontStyle
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Font
import java.awt.Point

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Font::class)
object FontSerializer : KSerializer<Font> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Point",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Font) =
        encoder.encodeSerializableValue(
            MapSerializer(String.serializer(), String.serializer()),
            mapOf(
                "name" to value.name,
                "style" to FontStyle.values()[value.style].toString(),
                "size" to value.size.toString()
            )
        )

    override fun deserialize(decoder: Decoder): Font {
        val decode = decoder.decodeSerializableValue(
            MapSerializer(String.serializer(), String.serializer()),
        )

        return Font(
            decode["name"] ?: Font.DIALOG,
            FontStyle.values().indexOf(decode["style"] ?: FontStyle.PLAIN),
            (decode["size"] ?: 12).toString().toInt(),
        )
    }
}
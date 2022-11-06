package com.emphasoft;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CowSerializer extends StdSerializer<Cow> {

    public CowSerializer() {
        this(null);
    }

    public CowSerializer(Class<Cow> t) {
        super(t);
    }

    @Override
    public void serialize(
            Cow value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("cowId", value.getCowId());
        jgen.writeStringField("nickname", value.getNickName());
        jgen.writeBooleanField("isAlive", value.isAlive());
        if (value instanceof CowQuestion1 && !((CowQuestion1) value).getChildren().isEmpty()) {
            jgen.writeObjectField("children", ((CowQuestion1) value).getChildren());
        }
        if (value instanceof CowQuestion2) {
            jgen.writeObjectField("childCow", ((CowQuestion2) value).getChildCow());
            jgen.writeObjectField("sibling", ((CowQuestion2) value).getSibling());
        }
        jgen.writeEndObject();
    }
}
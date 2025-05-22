package com.fasterxml.jackson.databind.jsonschema;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface SchemaAware {
    JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException;

    JsonNode getSchema(SerializerProvider serializerProvider, Type type, boolean z) throws JsonMappingException;
}

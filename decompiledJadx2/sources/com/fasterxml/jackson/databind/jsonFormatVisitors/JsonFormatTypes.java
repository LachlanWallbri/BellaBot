package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public enum JsonFormatTypes {
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    OBJECT,
    ARRAY,
    NULL,
    ANY;

    private static final Map<String, JsonFormatTypes> _byLCName = new HashMap();

    static {
        for (JsonFormatTypes jsonFormatTypes : values()) {
            _byLCName.put(jsonFormatTypes.name().toLowerCase(), jsonFormatTypes);
        }
    }

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static JsonFormatTypes forValue(String str) {
        return _byLCName.get(str);
    }
}

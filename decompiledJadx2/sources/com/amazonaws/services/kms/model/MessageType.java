package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum MessageType {
    RAW("RAW"),
    DIGEST("DIGEST");

    private static final Map<String, MessageType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("RAW", RAW);
        enumMap.put("DIGEST", DIGEST);
    }

    MessageType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static MessageType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

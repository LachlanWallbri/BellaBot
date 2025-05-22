package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ConnectionStateType {
    CONNECTED("CONNECTED"),
    CONNECTING("CONNECTING"),
    FAILED("FAILED"),
    DISCONNECTED("DISCONNECTED"),
    DISCONNECTING("DISCONNECTING");

    private static final Map<String, ConnectionStateType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("CONNECTED", CONNECTED);
        enumMap.put("CONNECTING", CONNECTING);
        enumMap.put("FAILED", FAILED);
        enumMap.put("DISCONNECTED", DISCONNECTED);
        enumMap.put("DISCONNECTING", DISCONNECTING);
    }

    ConnectionStateType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ConnectionStateType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

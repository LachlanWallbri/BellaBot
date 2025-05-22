package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum DataKeySpec {
    AES_256("AES_256"),
    AES_128("AES_128");

    private static final Map<String, DataKeySpec> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("AES_256", AES_256);
        enumMap.put("AES_128", AES_128);
    }

    DataKeySpec(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DataKeySpec fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

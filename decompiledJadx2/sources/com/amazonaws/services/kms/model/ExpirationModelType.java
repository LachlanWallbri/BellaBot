package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ExpirationModelType {
    KEY_MATERIAL_EXPIRES("KEY_MATERIAL_EXPIRES"),
    KEY_MATERIAL_DOES_NOT_EXPIRE("KEY_MATERIAL_DOES_NOT_EXPIRE");

    private static final Map<String, ExpirationModelType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("KEY_MATERIAL_EXPIRES", KEY_MATERIAL_EXPIRES);
        enumMap.put("KEY_MATERIAL_DOES_NOT_EXPIRE", KEY_MATERIAL_DOES_NOT_EXPIRE);
    }

    ExpirationModelType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ExpirationModelType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

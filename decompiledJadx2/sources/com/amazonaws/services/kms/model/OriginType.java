package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum OriginType {
    AWS_KMS("AWS_KMS"),
    EXTERNAL("EXTERNAL"),
    AWS_CLOUDHSM("AWS_CLOUDHSM");

    private static final Map<String, OriginType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("AWS_KMS", AWS_KMS);
        enumMap.put("EXTERNAL", EXTERNAL);
        enumMap.put("AWS_CLOUDHSM", AWS_CLOUDHSM);
    }

    OriginType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static OriginType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

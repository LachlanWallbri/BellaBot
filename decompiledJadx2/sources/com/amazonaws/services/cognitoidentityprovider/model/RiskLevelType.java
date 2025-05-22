package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum RiskLevelType {
    Low("Low"),
    Medium("Medium"),
    High("High");

    private static final Map<String, RiskLevelType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Low", Low);
        enumMap.put("Medium", Medium);
        enumMap.put("High", High);
    }

    RiskLevelType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RiskLevelType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

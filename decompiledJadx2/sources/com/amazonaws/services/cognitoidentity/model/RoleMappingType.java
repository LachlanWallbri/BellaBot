package com.amazonaws.services.cognitoidentity.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum RoleMappingType {
    Token("Token"),
    Rules("Rules");

    private static final Map<String, RoleMappingType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Token", Token);
        enumMap.put("Rules", Rules);
    }

    RoleMappingType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RoleMappingType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

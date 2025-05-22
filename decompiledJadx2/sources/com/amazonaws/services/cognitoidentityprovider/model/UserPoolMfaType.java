package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum UserPoolMfaType {
    OFF("OFF"),
    ON("ON"),
    OPTIONAL("OPTIONAL");

    private static final Map<String, UserPoolMfaType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("OFF", OFF);
        enumMap.put("ON", ON);
        enumMap.put("OPTIONAL", OPTIONAL);
    }

    UserPoolMfaType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static UserPoolMfaType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

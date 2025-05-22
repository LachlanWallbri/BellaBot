package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum EventFilterType {
    SIGN_IN("SIGN_IN"),
    PASSWORD_CHANGE("PASSWORD_CHANGE"),
    SIGN_UP("SIGN_UP");

    private static final Map<String, EventFilterType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("SIGN_IN", SIGN_IN);
        enumMap.put("PASSWORD_CHANGE", PASSWORD_CHANGE);
        enumMap.put("SIGN_UP", SIGN_UP);
    }

    EventFilterType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static EventFilterType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

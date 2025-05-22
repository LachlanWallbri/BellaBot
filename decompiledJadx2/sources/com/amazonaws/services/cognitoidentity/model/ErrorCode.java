package com.amazonaws.services.cognitoidentity.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ErrorCode {
    AccessDenied("AccessDenied"),
    InternalServerError("InternalServerError");

    private static final Map<String, ErrorCode> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("AccessDenied", AccessDenied);
        enumMap.put("InternalServerError", InternalServerError);
    }

    ErrorCode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ErrorCode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum FeedbackValueType {
    Valid("Valid"),
    Invalid("Invalid");

    private static final Map<String, FeedbackValueType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Valid", Valid);
        enumMap.put("Invalid", Invalid);
    }

    FeedbackValueType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static FeedbackValueType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

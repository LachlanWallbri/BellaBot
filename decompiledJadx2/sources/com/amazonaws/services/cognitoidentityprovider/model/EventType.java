package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum EventType {
    SignIn("SignIn"),
    SignUp("SignUp"),
    ForgotPassword("ForgotPassword");

    private static final Map<String, EventType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("SignIn", SignIn);
        enumMap.put("SignUp", SignUp);
        enumMap.put("ForgotPassword", ForgotPassword);
    }

    EventType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static EventType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

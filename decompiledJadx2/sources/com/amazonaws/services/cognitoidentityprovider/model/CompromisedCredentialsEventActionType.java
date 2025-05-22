package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum CompromisedCredentialsEventActionType {
    BLOCK("BLOCK"),
    NO_ACTION("NO_ACTION");

    private static final Map<String, CompromisedCredentialsEventActionType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("BLOCK", BLOCK);
        enumMap.put("NO_ACTION", NO_ACTION);
    }

    CompromisedCredentialsEventActionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CompromisedCredentialsEventActionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

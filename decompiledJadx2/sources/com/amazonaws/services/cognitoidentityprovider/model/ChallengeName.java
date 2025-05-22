package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ChallengeName {
    Password("Password"),
    Mfa("Mfa");

    private static final Map<String, ChallengeName> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Password", Password);
        enumMap.put("Mfa", Mfa);
    }

    ChallengeName(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ChallengeName fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

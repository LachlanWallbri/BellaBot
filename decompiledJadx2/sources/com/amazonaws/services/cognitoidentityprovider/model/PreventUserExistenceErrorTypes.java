package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum PreventUserExistenceErrorTypes {
    LEGACY("LEGACY"),
    ENABLED("ENABLED");

    private static final Map<String, PreventUserExistenceErrorTypes> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("LEGACY", LEGACY);
        enumMap.put("ENABLED", ENABLED);
    }

    PreventUserExistenceErrorTypes(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static PreventUserExistenceErrorTypes fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

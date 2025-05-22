package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum UserStatusType {
    UNCONFIRMED("UNCONFIRMED"),
    CONFIRMED("CONFIRMED"),
    ARCHIVED("ARCHIVED"),
    COMPROMISED("COMPROMISED"),
    RESET_REQUIRED("RESET_REQUIRED"),
    FORCE_CHANGE_PASSWORD("FORCE_CHANGE_PASSWORD");

    private static final Map<String, UserStatusType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("UNCONFIRMED", UNCONFIRMED);
        enumMap.put("CONFIRMED", CONFIRMED);
        enumMap.put("ARCHIVED", ARCHIVED);
        enumMap.put("COMPROMISED", COMPROMISED);
        enumMap.put("RESET_REQUIRED", RESET_REQUIRED);
        enumMap.put("FORCE_CHANGE_PASSWORD", FORCE_CHANGE_PASSWORD);
    }

    UserStatusType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static UserStatusType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

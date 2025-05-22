package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum RecoveryOptionNameType {
    Verified_email("verified_email"),
    Verified_phone_number("verified_phone_number"),
    Admin_only("admin_only");

    private static final Map<String, RecoveryOptionNameType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("verified_email", Verified_email);
        enumMap.put("verified_phone_number", Verified_phone_number);
        enumMap.put("admin_only", Admin_only);
    }

    RecoveryOptionNameType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RecoveryOptionNameType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AccountTakeoverEventActionType {
    BLOCK("BLOCK"),
    MFA_IF_CONFIGURED("MFA_IF_CONFIGURED"),
    MFA_REQUIRED("MFA_REQUIRED"),
    NO_ACTION("NO_ACTION");

    private static final Map<String, AccountTakeoverEventActionType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("BLOCK", BLOCK);
        enumMap.put("MFA_IF_CONFIGURED", MFA_IF_CONFIGURED);
        enumMap.put("MFA_REQUIRED", MFA_REQUIRED);
        enumMap.put("NO_ACTION", NO_ACTION);
    }

    AccountTakeoverEventActionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AccountTakeoverEventActionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum DomainStatusType {
    CREATING("CREATING"),
    DELETING("DELETING"),
    UPDATING("UPDATING"),
    ACTIVE("ACTIVE"),
    FAILED("FAILED");

    private static final Map<String, DomainStatusType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("CREATING", CREATING);
        enumMap.put("DELETING", DELETING);
        enumMap.put("UPDATING", UPDATING);
        enumMap.put("ACTIVE", ACTIVE);
        enumMap.put("FAILED", FAILED);
    }

    DomainStatusType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DomainStatusType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

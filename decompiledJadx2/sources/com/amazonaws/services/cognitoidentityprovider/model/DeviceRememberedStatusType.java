package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum DeviceRememberedStatusType {
    Remembered("remembered"),
    Not_remembered("not_remembered");

    private static final Map<String, DeviceRememberedStatusType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("remembered", Remembered);
        enumMap.put("not_remembered", Not_remembered);
    }

    DeviceRememberedStatusType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DeviceRememberedStatusType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

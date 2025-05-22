package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum TimeUnitsType {
    Seconds("seconds"),
    Minutes("minutes"),
    Hours("hours"),
    Days("days");

    private static final Map<String, TimeUnitsType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("seconds", Seconds);
        enumMap.put("minutes", Minutes);
        enumMap.put("hours", Hours);
        enumMap.put("days", Days);
    }

    TimeUnitsType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static TimeUnitsType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

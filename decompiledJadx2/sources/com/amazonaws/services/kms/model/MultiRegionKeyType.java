package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum MultiRegionKeyType {
    PRIMARY("PRIMARY"),
    REPLICA("REPLICA");

    private static final Map<String, MultiRegionKeyType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("PRIMARY", PRIMARY);
        enumMap.put("REPLICA", REPLICA);
    }

    MultiRegionKeyType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static MultiRegionKeyType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

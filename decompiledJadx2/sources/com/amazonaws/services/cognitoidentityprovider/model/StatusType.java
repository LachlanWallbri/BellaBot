package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.services.p048s3.model.BucketLifecycleConfiguration;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum StatusType {
    Enabled("Enabled"),
    Disabled(BucketLifecycleConfiguration.DISABLED);

    private static final Map<String, StatusType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Enabled", Enabled);
        enumMap.put(BucketLifecycleConfiguration.DISABLED, Disabled);
    }

    StatusType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static StatusType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

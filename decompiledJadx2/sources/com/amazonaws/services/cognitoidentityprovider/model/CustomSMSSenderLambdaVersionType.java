package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum CustomSMSSenderLambdaVersionType {
    V1_0("V1_0");

    private static final Map<String, CustomSMSSenderLambdaVersionType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("V1_0", V1_0);
    }

    CustomSMSSenderLambdaVersionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CustomSMSSenderLambdaVersionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

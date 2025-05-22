package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum EmailSendingAccountType {
    COGNITO_DEFAULT("COGNITO_DEFAULT"),
    DEVELOPER("DEVELOPER");

    private static final Map<String, EmailSendingAccountType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("COGNITO_DEFAULT", COGNITO_DEFAULT);
        enumMap.put("DEVELOPER", DEVELOPER);
    }

    EmailSendingAccountType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static EmailSendingAccountType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

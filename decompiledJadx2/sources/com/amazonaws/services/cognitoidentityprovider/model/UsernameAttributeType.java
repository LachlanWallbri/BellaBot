package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum UsernameAttributeType {
    Phone_number(CognitoUserPoolsSignInProvider.AttributeKeys.PHONE_NUMBER),
    Email("email");

    private static final Map<String, UsernameAttributeType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(CognitoUserPoolsSignInProvider.AttributeKeys.PHONE_NUMBER, Phone_number);
        enumMap.put("email", Email);
    }

    UsernameAttributeType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static UsernameAttributeType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

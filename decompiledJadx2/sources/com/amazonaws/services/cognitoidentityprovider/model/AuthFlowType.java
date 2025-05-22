package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AuthFlowType {
    USER_SRP_AUTH(CognitoServiceConstants.AUTH_TYPE_INIT_USER_SRP),
    REFRESH_TOKEN_AUTH(CognitoServiceConstants.AUTH_TYPE_REFRESH_TOKEN),
    REFRESH_TOKEN(CognitoServiceConstants.AUTH_PARAM_REFRESH_TOKEN),
    CUSTOM_AUTH(CognitoServiceConstants.AUTH_TYPE_INIT_CUSTOM_AUTH),
    ADMIN_NO_SRP_AUTH("ADMIN_NO_SRP_AUTH"),
    USER_PASSWORD_AUTH(CognitoServiceConstants.AUTH_TYPE_INIT_USER_PASSWORD),
    ADMIN_USER_PASSWORD_AUTH("ADMIN_USER_PASSWORD_AUTH");

    private static final Map<String, AuthFlowType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(CognitoServiceConstants.AUTH_TYPE_INIT_USER_SRP, USER_SRP_AUTH);
        enumMap.put(CognitoServiceConstants.AUTH_TYPE_REFRESH_TOKEN, REFRESH_TOKEN_AUTH);
        enumMap.put(CognitoServiceConstants.AUTH_PARAM_REFRESH_TOKEN, REFRESH_TOKEN);
        enumMap.put(CognitoServiceConstants.AUTH_TYPE_INIT_CUSTOM_AUTH, CUSTOM_AUTH);
        enumMap.put("ADMIN_NO_SRP_AUTH", ADMIN_NO_SRP_AUTH);
        enumMap.put(CognitoServiceConstants.AUTH_TYPE_INIT_USER_PASSWORD, USER_PASSWORD_AUTH);
        enumMap.put("ADMIN_USER_PASSWORD_AUTH", ADMIN_USER_PASSWORD_AUTH);
    }

    AuthFlowType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuthFlowType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

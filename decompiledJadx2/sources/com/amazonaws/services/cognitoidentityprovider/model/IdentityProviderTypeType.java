package com.amazonaws.services.cognitoidentityprovider.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum IdentityProviderTypeType {
    SAML("SAML"),
    Facebook("Facebook"),
    Google("Google"),
    LoginWithAmazon("LoginWithAmazon"),
    SignInWithApple("SignInWithApple"),
    OIDC("OIDC");

    private static final Map<String, IdentityProviderTypeType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("SAML", SAML);
        enumMap.put("Facebook", Facebook);
        enumMap.put("Google", Google);
        enumMap.put("LoginWithAmazon", LoginWithAmazon);
        enumMap.put("SignInWithApple", SignInWithApple);
        enumMap.put("OIDC", OIDC);
    }

    IdentityProviderTypeType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static IdentityProviderTypeType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

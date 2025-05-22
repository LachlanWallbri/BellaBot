package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ChallengeNameType {
    SMS_MFA("SMS_MFA"),
    SOFTWARE_TOKEN_MFA(CognitoServiceConstants.CHLG_TYPE_SOFTWARE_TOKEN_MFA),
    SELECT_MFA_TYPE(CognitoServiceConstants.CHLG_TYPE_SELECT_MFA_TYPE),
    MFA_SETUP(CognitoServiceConstants.CHLG_TYPE_MFA_SETUP),
    PASSWORD_VERIFIER(CognitoServiceConstants.CHLG_TYPE_USER_PASSWORD_VERIFIER),
    CUSTOM_CHALLENGE(CognitoServiceConstants.CHLG_TYPE_CUSTOM_CHALLENGE),
    DEVICE_SRP_AUTH(CognitoServiceConstants.CHLG_TYPE_DEVICE_SRP_AUTH),
    DEVICE_PASSWORD_VERIFIER(CognitoServiceConstants.CHLG_TYPE_DEVICE_PASSWORD_VERIFIER),
    ADMIN_NO_SRP_AUTH("ADMIN_NO_SRP_AUTH"),
    NEW_PASSWORD_REQUIRED(CognitoServiceConstants.CHLG_TYPE_NEW_PASSWORD_REQUIRED);

    private static final Map<String, ChallengeNameType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("SMS_MFA", SMS_MFA);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_SOFTWARE_TOKEN_MFA, SOFTWARE_TOKEN_MFA);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_SELECT_MFA_TYPE, SELECT_MFA_TYPE);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_MFA_SETUP, MFA_SETUP);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_USER_PASSWORD_VERIFIER, PASSWORD_VERIFIER);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_CUSTOM_CHALLENGE, CUSTOM_CHALLENGE);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_DEVICE_SRP_AUTH, DEVICE_SRP_AUTH);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_DEVICE_PASSWORD_VERIFIER, DEVICE_PASSWORD_VERIFIER);
        enumMap.put("ADMIN_NO_SRP_AUTH", ADMIN_NO_SRP_AUTH);
        enumMap.put(CognitoServiceConstants.CHLG_TYPE_NEW_PASSWORD_REQUIRED, NEW_PASSWORD_REQUIRED);
    }

    ChallengeNameType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ChallengeNameType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}

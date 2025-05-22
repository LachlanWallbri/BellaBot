package com.amazonaws.mobileconnectors.cognitoidentityprovider.util;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.exceptions.CognitoParameterInvalidException;

/* loaded from: classes.dex */
public final class CognitoIdentityProviderClientConfig {
    private static final long REFRESH_THRESHOLD_DEFAULT = 120000;
    private static final long REFRESH_THRESHOLD_MAX = 1800000;
    private static final long REFRESH_THRESHOLD_MIN = 0;
    private static long refreshThreshold = 120000;

    public static void setRefreshThreshold(long j) {
        if (j > REFRESH_THRESHOLD_MAX || j < 0) {
            throw new CognitoParameterInvalidException(String.format("The value of refreshThreshold must between %d and %d milliseconds", 0L, Long.valueOf(REFRESH_THRESHOLD_MAX)));
        }
        refreshThreshold = j;
    }

    public static long getRefreshThreshold() {
        return refreshThreshold;
    }
}

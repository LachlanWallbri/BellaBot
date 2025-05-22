package com.amazonaws.mobileconnectors.cognitoidentityprovider.tokens;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.exceptions.CognitoInternalErrorException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoJWTParser;
import com.iflytek.aiui.constant.InternalConstant;
import java.util.Date;

/* loaded from: classes.dex */
public class CognitoIdToken extends CognitoUserToken {
    private static final int SECS = 1000;

    public CognitoIdToken(String str) {
        super(str);
    }

    public String getJWTToken() {
        return super.getToken();
    }

    public Date getExpiration() {
        try {
            String claim = CognitoJWTParser.getClaim(super.getToken(), "exp");
            if (claim == null) {
                return null;
            }
            return new Date(Long.parseLong(claim) * 1000);
        } catch (Exception e) {
            throw new CognitoInternalErrorException(e.getMessage(), e);
        }
    }

    public Date getNotBefore() {
        try {
            String claim = CognitoJWTParser.getClaim(super.getToken(), "nbf");
            if (claim == null) {
                return null;
            }
            return new Date(Long.parseLong(claim) * 1000);
        } catch (Exception e) {
            throw new CognitoInternalErrorException(e.getMessage(), e);
        }
    }

    public Date getIssuedAt() {
        try {
            String claim = CognitoJWTParser.getClaim(super.getToken(), InternalConstant.SUB_IAT);
            if (claim == null) {
                return null;
            }
            return new Date(Long.parseLong(claim) * 1000);
        } catch (Exception e) {
            throw new CognitoInternalErrorException(e.getMessage(), e);
        }
    }
}

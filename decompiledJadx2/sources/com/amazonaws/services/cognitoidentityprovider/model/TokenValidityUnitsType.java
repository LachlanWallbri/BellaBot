package com.amazonaws.services.cognitoidentityprovider.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TokenValidityUnitsType implements Serializable {
    private String accessToken;
    private String idToken;
    private String refreshToken;

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public TokenValidityUnitsType withAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public void setAccessToken(TimeUnitsType timeUnitsType) {
        this.accessToken = timeUnitsType.toString();
    }

    public TokenValidityUnitsType withAccessToken(TimeUnitsType timeUnitsType) {
        this.accessToken = timeUnitsType.toString();
        return this;
    }

    public String getIdToken() {
        return this.idToken;
    }

    public void setIdToken(String str) {
        this.idToken = str;
    }

    public TokenValidityUnitsType withIdToken(String str) {
        this.idToken = str;
        return this;
    }

    public void setIdToken(TimeUnitsType timeUnitsType) {
        this.idToken = timeUnitsType.toString();
    }

    public TokenValidityUnitsType withIdToken(TimeUnitsType timeUnitsType) {
        this.idToken = timeUnitsType.toString();
        return this;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public TokenValidityUnitsType withRefreshToken(String str) {
        this.refreshToken = str;
        return this;
    }

    public void setRefreshToken(TimeUnitsType timeUnitsType) {
        this.refreshToken = timeUnitsType.toString();
    }

    public TokenValidityUnitsType withRefreshToken(TimeUnitsType timeUnitsType) {
        this.refreshToken = timeUnitsType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccessToken() != null) {
            sb.append("AccessToken: " + getAccessToken() + ",");
        }
        if (getIdToken() != null) {
            sb.append("IdToken: " + getIdToken() + ",");
        }
        if (getRefreshToken() != null) {
            sb.append("RefreshToken: " + getRefreshToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getAccessToken() == null ? 0 : getAccessToken().hashCode()) + 31) * 31) + (getIdToken() == null ? 0 : getIdToken().hashCode())) * 31) + (getRefreshToken() != null ? getRefreshToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TokenValidityUnitsType)) {
            return false;
        }
        TokenValidityUnitsType tokenValidityUnitsType = (TokenValidityUnitsType) obj;
        if ((tokenValidityUnitsType.getAccessToken() == null) ^ (getAccessToken() == null)) {
            return false;
        }
        if (tokenValidityUnitsType.getAccessToken() != null && !tokenValidityUnitsType.getAccessToken().equals(getAccessToken())) {
            return false;
        }
        if ((tokenValidityUnitsType.getIdToken() == null) ^ (getIdToken() == null)) {
            return false;
        }
        if (tokenValidityUnitsType.getIdToken() != null && !tokenValidityUnitsType.getIdToken().equals(getIdToken())) {
            return false;
        }
        if ((tokenValidityUnitsType.getRefreshToken() == null) ^ (getRefreshToken() == null)) {
            return false;
        }
        return tokenValidityUnitsType.getRefreshToken() == null || tokenValidityUnitsType.getRefreshToken().equals(getRefreshToken());
    }
}

package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class RevokeTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private String clientId;
    private String clientSecret;
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public RevokeTokenRequest withToken(String str) {
        this.token = str;
        return this;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public RevokeTokenRequest withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String str) {
        this.clientSecret = str;
    }

    public RevokeTokenRequest withClientSecret(String str) {
        this.clientSecret = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getToken() != null) {
            sb.append("Token: " + getToken() + ",");
        }
        if (getClientId() != null) {
            sb.append("ClientId: " + getClientId() + ",");
        }
        if (getClientSecret() != null) {
            sb.append("ClientSecret: " + getClientSecret());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getToken() == null ? 0 : getToken().hashCode()) + 31) * 31) + (getClientId() == null ? 0 : getClientId().hashCode())) * 31) + (getClientSecret() != null ? getClientSecret().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RevokeTokenRequest)) {
            return false;
        }
        RevokeTokenRequest revokeTokenRequest = (RevokeTokenRequest) obj;
        if ((revokeTokenRequest.getToken() == null) ^ (getToken() == null)) {
            return false;
        }
        if (revokeTokenRequest.getToken() != null && !revokeTokenRequest.getToken().equals(getToken())) {
            return false;
        }
        if ((revokeTokenRequest.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        if (revokeTokenRequest.getClientId() != null && !revokeTokenRequest.getClientId().equals(getClientId())) {
            return false;
        }
        if ((revokeTokenRequest.getClientSecret() == null) ^ (getClientSecret() == null)) {
            return false;
        }
        return revokeTokenRequest.getClientSecret() == null || revokeTokenRequest.getClientSecret().equals(getClientSecret());
    }
}

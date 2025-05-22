package com.amazonaws.services.cognitoidentityprovider.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AdminDeleteUserRequest extends AmazonWebServiceRequest implements Serializable {
    private String userPoolId;
    private String username;

    public String getUserPoolId() {
        return this.userPoolId;
    }

    public void setUserPoolId(String str) {
        this.userPoolId = str;
    }

    public AdminDeleteUserRequest withUserPoolId(String str) {
        this.userPoolId = str;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public AdminDeleteUserRequest withUsername(String str) {
        this.username = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUserPoolId() != null) {
            sb.append("UserPoolId: " + getUserPoolId() + ",");
        }
        if (getUsername() != null) {
            sb.append("Username: " + getUsername());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getUserPoolId() == null ? 0 : getUserPoolId().hashCode()) + 31) * 31) + (getUsername() != null ? getUsername().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AdminDeleteUserRequest)) {
            return false;
        }
        AdminDeleteUserRequest adminDeleteUserRequest = (AdminDeleteUserRequest) obj;
        if ((adminDeleteUserRequest.getUserPoolId() == null) ^ (getUserPoolId() == null)) {
            return false;
        }
        if (adminDeleteUserRequest.getUserPoolId() != null && !adminDeleteUserRequest.getUserPoolId().equals(getUserPoolId())) {
            return false;
        }
        if ((adminDeleteUserRequest.getUsername() == null) ^ (getUsername() == null)) {
            return false;
        }
        return adminDeleteUserRequest.getUsername() == null || adminDeleteUserRequest.getUsername().equals(getUsername());
    }
}

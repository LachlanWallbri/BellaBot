package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AssumeRoleResult implements Serializable {
    private AssumedRoleUser assumedRoleUser;
    private Credentials credentials;
    private Integer packedPolicySize;
    private String sourceIdentity;

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public AssumeRoleResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public AssumedRoleUser getAssumedRoleUser() {
        return this.assumedRoleUser;
    }

    public void setAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public AssumeRoleResult withAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
        return this;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public void setPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
    }

    public AssumeRoleResult withPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
        return this;
    }

    public String getSourceIdentity() {
        return this.sourceIdentity;
    }

    public void setSourceIdentity(String str) {
        this.sourceIdentity = str;
    }

    public AssumeRoleResult withSourceIdentity(String str) {
        this.sourceIdentity = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCredentials() != null) {
            sb.append("Credentials: " + getCredentials() + ",");
        }
        if (getAssumedRoleUser() != null) {
            sb.append("AssumedRoleUser: " + getAssumedRoleUser() + ",");
        }
        if (getPackedPolicySize() != null) {
            sb.append("PackedPolicySize: " + getPackedPolicySize() + ",");
        }
        if (getSourceIdentity() != null) {
            sb.append("SourceIdentity: " + getSourceIdentity());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31) + (getAssumedRoleUser() == null ? 0 : getAssumedRoleUser().hashCode())) * 31) + (getPackedPolicySize() == null ? 0 : getPackedPolicySize().hashCode())) * 31) + (getSourceIdentity() != null ? getSourceIdentity().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleResult)) {
            return false;
        }
        AssumeRoleResult assumeRoleResult = (AssumeRoleResult) obj;
        if ((assumeRoleResult.getCredentials() == null) ^ (getCredentials() == null)) {
            return false;
        }
        if (assumeRoleResult.getCredentials() != null && !assumeRoleResult.getCredentials().equals(getCredentials())) {
            return false;
        }
        if ((assumeRoleResult.getAssumedRoleUser() == null) ^ (getAssumedRoleUser() == null)) {
            return false;
        }
        if (assumeRoleResult.getAssumedRoleUser() != null && !assumeRoleResult.getAssumedRoleUser().equals(getAssumedRoleUser())) {
            return false;
        }
        if ((assumeRoleResult.getPackedPolicySize() == null) ^ (getPackedPolicySize() == null)) {
            return false;
        }
        if (assumeRoleResult.getPackedPolicySize() != null && !assumeRoleResult.getPackedPolicySize().equals(getPackedPolicySize())) {
            return false;
        }
        if ((assumeRoleResult.getSourceIdentity() == null) ^ (getSourceIdentity() == null)) {
            return false;
        }
        return assumeRoleResult.getSourceIdentity() == null || assumeRoleResult.getSourceIdentity().equals(getSourceIdentity());
    }
}

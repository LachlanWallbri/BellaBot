package com.amazonaws.services.cognitoidentityprovider.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class CustomSMSLambdaVersionConfigType implements Serializable {
    private String lambdaArn;
    private String lambdaVersion;

    public String getLambdaVersion() {
        return this.lambdaVersion;
    }

    public void setLambdaVersion(String str) {
        this.lambdaVersion = str;
    }

    public CustomSMSLambdaVersionConfigType withLambdaVersion(String str) {
        this.lambdaVersion = str;
        return this;
    }

    public void setLambdaVersion(CustomSMSSenderLambdaVersionType customSMSSenderLambdaVersionType) {
        this.lambdaVersion = customSMSSenderLambdaVersionType.toString();
    }

    public CustomSMSLambdaVersionConfigType withLambdaVersion(CustomSMSSenderLambdaVersionType customSMSSenderLambdaVersionType) {
        this.lambdaVersion = customSMSSenderLambdaVersionType.toString();
        return this;
    }

    public String getLambdaArn() {
        return this.lambdaArn;
    }

    public void setLambdaArn(String str) {
        this.lambdaArn = str;
    }

    public CustomSMSLambdaVersionConfigType withLambdaArn(String str) {
        this.lambdaArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getLambdaVersion() != null) {
            sb.append("LambdaVersion: " + getLambdaVersion() + ",");
        }
        if (getLambdaArn() != null) {
            sb.append("LambdaArn: " + getLambdaArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getLambdaVersion() == null ? 0 : getLambdaVersion().hashCode()) + 31) * 31) + (getLambdaArn() != null ? getLambdaArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CustomSMSLambdaVersionConfigType)) {
            return false;
        }
        CustomSMSLambdaVersionConfigType customSMSLambdaVersionConfigType = (CustomSMSLambdaVersionConfigType) obj;
        if ((customSMSLambdaVersionConfigType.getLambdaVersion() == null) ^ (getLambdaVersion() == null)) {
            return false;
        }
        if (customSMSLambdaVersionConfigType.getLambdaVersion() != null && !customSMSLambdaVersionConfigType.getLambdaVersion().equals(getLambdaVersion())) {
            return false;
        }
        if ((customSMSLambdaVersionConfigType.getLambdaArn() == null) ^ (getLambdaArn() == null)) {
            return false;
        }
        return customSMSLambdaVersionConfigType.getLambdaArn() == null || customSMSLambdaVersionConfigType.getLambdaArn().equals(getLambdaArn());
    }
}

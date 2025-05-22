package com.amazonaws.services.cognitoidentityprovider.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LambdaConfigType implements Serializable {
    private String createAuthChallenge;
    private CustomEmailLambdaVersionConfigType customEmailSender;
    private String customMessage;
    private CustomSMSLambdaVersionConfigType customSMSSender;
    private String defineAuthChallenge;
    private String kMSKeyID;
    private String postAuthentication;
    private String postConfirmation;
    private String preAuthentication;
    private String preSignUp;
    private String preTokenGeneration;
    private String userMigration;
    private String verifyAuthChallengeResponse;

    public String getPreSignUp() {
        return this.preSignUp;
    }

    public void setPreSignUp(String str) {
        this.preSignUp = str;
    }

    public LambdaConfigType withPreSignUp(String str) {
        this.preSignUp = str;
        return this;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }

    public void setCustomMessage(String str) {
        this.customMessage = str;
    }

    public LambdaConfigType withCustomMessage(String str) {
        this.customMessage = str;
        return this;
    }

    public String getPostConfirmation() {
        return this.postConfirmation;
    }

    public void setPostConfirmation(String str) {
        this.postConfirmation = str;
    }

    public LambdaConfigType withPostConfirmation(String str) {
        this.postConfirmation = str;
        return this;
    }

    public String getPreAuthentication() {
        return this.preAuthentication;
    }

    public void setPreAuthentication(String str) {
        this.preAuthentication = str;
    }

    public LambdaConfigType withPreAuthentication(String str) {
        this.preAuthentication = str;
        return this;
    }

    public String getPostAuthentication() {
        return this.postAuthentication;
    }

    public void setPostAuthentication(String str) {
        this.postAuthentication = str;
    }

    public LambdaConfigType withPostAuthentication(String str) {
        this.postAuthentication = str;
        return this;
    }

    public String getDefineAuthChallenge() {
        return this.defineAuthChallenge;
    }

    public void setDefineAuthChallenge(String str) {
        this.defineAuthChallenge = str;
    }

    public LambdaConfigType withDefineAuthChallenge(String str) {
        this.defineAuthChallenge = str;
        return this;
    }

    public String getCreateAuthChallenge() {
        return this.createAuthChallenge;
    }

    public void setCreateAuthChallenge(String str) {
        this.createAuthChallenge = str;
    }

    public LambdaConfigType withCreateAuthChallenge(String str) {
        this.createAuthChallenge = str;
        return this;
    }

    public String getVerifyAuthChallengeResponse() {
        return this.verifyAuthChallengeResponse;
    }

    public void setVerifyAuthChallengeResponse(String str) {
        this.verifyAuthChallengeResponse = str;
    }

    public LambdaConfigType withVerifyAuthChallengeResponse(String str) {
        this.verifyAuthChallengeResponse = str;
        return this;
    }

    public String getPreTokenGeneration() {
        return this.preTokenGeneration;
    }

    public void setPreTokenGeneration(String str) {
        this.preTokenGeneration = str;
    }

    public LambdaConfigType withPreTokenGeneration(String str) {
        this.preTokenGeneration = str;
        return this;
    }

    public String getUserMigration() {
        return this.userMigration;
    }

    public void setUserMigration(String str) {
        this.userMigration = str;
    }

    public LambdaConfigType withUserMigration(String str) {
        this.userMigration = str;
        return this;
    }

    public CustomSMSLambdaVersionConfigType getCustomSMSSender() {
        return this.customSMSSender;
    }

    public void setCustomSMSSender(CustomSMSLambdaVersionConfigType customSMSLambdaVersionConfigType) {
        this.customSMSSender = customSMSLambdaVersionConfigType;
    }

    public LambdaConfigType withCustomSMSSender(CustomSMSLambdaVersionConfigType customSMSLambdaVersionConfigType) {
        this.customSMSSender = customSMSLambdaVersionConfigType;
        return this;
    }

    public CustomEmailLambdaVersionConfigType getCustomEmailSender() {
        return this.customEmailSender;
    }

    public void setCustomEmailSender(CustomEmailLambdaVersionConfigType customEmailLambdaVersionConfigType) {
        this.customEmailSender = customEmailLambdaVersionConfigType;
    }

    public LambdaConfigType withCustomEmailSender(CustomEmailLambdaVersionConfigType customEmailLambdaVersionConfigType) {
        this.customEmailSender = customEmailLambdaVersionConfigType;
        return this;
    }

    public String getKMSKeyID() {
        return this.kMSKeyID;
    }

    public void setKMSKeyID(String str) {
        this.kMSKeyID = str;
    }

    public LambdaConfigType withKMSKeyID(String str) {
        this.kMSKeyID = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPreSignUp() != null) {
            sb.append("PreSignUp: " + getPreSignUp() + ",");
        }
        if (getCustomMessage() != null) {
            sb.append("CustomMessage: " + getCustomMessage() + ",");
        }
        if (getPostConfirmation() != null) {
            sb.append("PostConfirmation: " + getPostConfirmation() + ",");
        }
        if (getPreAuthentication() != null) {
            sb.append("PreAuthentication: " + getPreAuthentication() + ",");
        }
        if (getPostAuthentication() != null) {
            sb.append("PostAuthentication: " + getPostAuthentication() + ",");
        }
        if (getDefineAuthChallenge() != null) {
            sb.append("DefineAuthChallenge: " + getDefineAuthChallenge() + ",");
        }
        if (getCreateAuthChallenge() != null) {
            sb.append("CreateAuthChallenge: " + getCreateAuthChallenge() + ",");
        }
        if (getVerifyAuthChallengeResponse() != null) {
            sb.append("VerifyAuthChallengeResponse: " + getVerifyAuthChallengeResponse() + ",");
        }
        if (getPreTokenGeneration() != null) {
            sb.append("PreTokenGeneration: " + getPreTokenGeneration() + ",");
        }
        if (getUserMigration() != null) {
            sb.append("UserMigration: " + getUserMigration() + ",");
        }
        if (getCustomSMSSender() != null) {
            sb.append("CustomSMSSender: " + getCustomSMSSender() + ",");
        }
        if (getCustomEmailSender() != null) {
            sb.append("CustomEmailSender: " + getCustomEmailSender() + ",");
        }
        if (getKMSKeyID() != null) {
            sb.append("KMSKeyID: " + getKMSKeyID());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((getPreSignUp() == null ? 0 : getPreSignUp().hashCode()) + 31) * 31) + (getCustomMessage() == null ? 0 : getCustomMessage().hashCode())) * 31) + (getPostConfirmation() == null ? 0 : getPostConfirmation().hashCode())) * 31) + (getPreAuthentication() == null ? 0 : getPreAuthentication().hashCode())) * 31) + (getPostAuthentication() == null ? 0 : getPostAuthentication().hashCode())) * 31) + (getDefineAuthChallenge() == null ? 0 : getDefineAuthChallenge().hashCode())) * 31) + (getCreateAuthChallenge() == null ? 0 : getCreateAuthChallenge().hashCode())) * 31) + (getVerifyAuthChallengeResponse() == null ? 0 : getVerifyAuthChallengeResponse().hashCode())) * 31) + (getPreTokenGeneration() == null ? 0 : getPreTokenGeneration().hashCode())) * 31) + (getUserMigration() == null ? 0 : getUserMigration().hashCode())) * 31) + (getCustomSMSSender() == null ? 0 : getCustomSMSSender().hashCode())) * 31) + (getCustomEmailSender() == null ? 0 : getCustomEmailSender().hashCode())) * 31) + (getKMSKeyID() != null ? getKMSKeyID().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LambdaConfigType)) {
            return false;
        }
        LambdaConfigType lambdaConfigType = (LambdaConfigType) obj;
        if ((lambdaConfigType.getPreSignUp() == null) ^ (getPreSignUp() == null)) {
            return false;
        }
        if (lambdaConfigType.getPreSignUp() != null && !lambdaConfigType.getPreSignUp().equals(getPreSignUp())) {
            return false;
        }
        if ((lambdaConfigType.getCustomMessage() == null) ^ (getCustomMessage() == null)) {
            return false;
        }
        if (lambdaConfigType.getCustomMessage() != null && !lambdaConfigType.getCustomMessage().equals(getCustomMessage())) {
            return false;
        }
        if ((lambdaConfigType.getPostConfirmation() == null) ^ (getPostConfirmation() == null)) {
            return false;
        }
        if (lambdaConfigType.getPostConfirmation() != null && !lambdaConfigType.getPostConfirmation().equals(getPostConfirmation())) {
            return false;
        }
        if ((lambdaConfigType.getPreAuthentication() == null) ^ (getPreAuthentication() == null)) {
            return false;
        }
        if (lambdaConfigType.getPreAuthentication() != null && !lambdaConfigType.getPreAuthentication().equals(getPreAuthentication())) {
            return false;
        }
        if ((lambdaConfigType.getPostAuthentication() == null) ^ (getPostAuthentication() == null)) {
            return false;
        }
        if (lambdaConfigType.getPostAuthentication() != null && !lambdaConfigType.getPostAuthentication().equals(getPostAuthentication())) {
            return false;
        }
        if ((lambdaConfigType.getDefineAuthChallenge() == null) ^ (getDefineAuthChallenge() == null)) {
            return false;
        }
        if (lambdaConfigType.getDefineAuthChallenge() != null && !lambdaConfigType.getDefineAuthChallenge().equals(getDefineAuthChallenge())) {
            return false;
        }
        if ((lambdaConfigType.getCreateAuthChallenge() == null) ^ (getCreateAuthChallenge() == null)) {
            return false;
        }
        if (lambdaConfigType.getCreateAuthChallenge() != null && !lambdaConfigType.getCreateAuthChallenge().equals(getCreateAuthChallenge())) {
            return false;
        }
        if ((lambdaConfigType.getVerifyAuthChallengeResponse() == null) ^ (getVerifyAuthChallengeResponse() == null)) {
            return false;
        }
        if (lambdaConfigType.getVerifyAuthChallengeResponse() != null && !lambdaConfigType.getVerifyAuthChallengeResponse().equals(getVerifyAuthChallengeResponse())) {
            return false;
        }
        if ((lambdaConfigType.getPreTokenGeneration() == null) ^ (getPreTokenGeneration() == null)) {
            return false;
        }
        if (lambdaConfigType.getPreTokenGeneration() != null && !lambdaConfigType.getPreTokenGeneration().equals(getPreTokenGeneration())) {
            return false;
        }
        if ((lambdaConfigType.getUserMigration() == null) ^ (getUserMigration() == null)) {
            return false;
        }
        if (lambdaConfigType.getUserMigration() != null && !lambdaConfigType.getUserMigration().equals(getUserMigration())) {
            return false;
        }
        if ((lambdaConfigType.getCustomSMSSender() == null) ^ (getCustomSMSSender() == null)) {
            return false;
        }
        if (lambdaConfigType.getCustomSMSSender() != null && !lambdaConfigType.getCustomSMSSender().equals(getCustomSMSSender())) {
            return false;
        }
        if ((lambdaConfigType.getCustomEmailSender() == null) ^ (getCustomEmailSender() == null)) {
            return false;
        }
        if (lambdaConfigType.getCustomEmailSender() != null && !lambdaConfigType.getCustomEmailSender().equals(getCustomEmailSender())) {
            return false;
        }
        if ((lambdaConfigType.getKMSKeyID() == null) ^ (getKMSKeyID() == null)) {
            return false;
        }
        return lambdaConfigType.getKMSKeyID() == null || lambdaConfigType.getKMSKeyID().equals(getKMSKeyID());
    }
}

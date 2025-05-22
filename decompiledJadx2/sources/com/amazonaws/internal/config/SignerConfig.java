package com.amazonaws.internal.config;

/* loaded from: classes.dex */
public class SignerConfig {
    private final String signerType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignerConfig(String str) {
        this.signerType = str;
    }

    SignerConfig(SignerConfig signerConfig) {
        this.signerType = signerConfig.getSignerType();
    }

    public String getSignerType() {
        return this.signerType;
    }

    public String toString() {
        return this.signerType;
    }
}

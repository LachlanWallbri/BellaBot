package com.google.auth.oauth2;

import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AwsSecurityCredentials {
    private final String accessKeyId;
    private final String secretAccessKey;

    @Nullable
    private final String token;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwsSecurityCredentials(String str, String str2, @Nullable String str3) {
        this.accessKeyId = str;
        this.secretAccessKey = str2;
        this.token = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSecretAccessKey() {
        return this.secretAccessKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getToken() {
        return this.token;
    }
}

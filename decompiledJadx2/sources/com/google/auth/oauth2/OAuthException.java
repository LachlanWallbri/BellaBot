package com.google.auth.oauth2;

import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class OAuthException extends IOException {
    private final String errorCode;

    @Nullable
    private final String errorDescription;

    @Nullable
    private final String errorUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuthException(String str, @Nullable String str2, @Nullable String str3) {
        this.errorCode = (String) Preconditions.checkNotNull(str);
        this.errorDescription = str2;
        this.errorUri = str3;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder("Error code " + this.errorCode);
        if (this.errorDescription != null) {
            sb.append(": ");
            sb.append(this.errorDescription);
        }
        if (this.errorUri != null) {
            sb.append(" - ");
            sb.append(this.errorUri);
        }
        return sb.toString();
    }

    String getErrorCode() {
        return this.errorCode;
    }

    @Nullable
    String getErrorDescription() {
        return this.errorDescription;
    }

    @Nullable
    String getErrorUri() {
        return this.errorUri;
    }
}

package com.amazonaws.mobile.client.internal.oauth2;

import android.net.Uri;

/* loaded from: classes.dex */
public class AuthorizeResponse {
    String code;
    Uri responseUri;

    public Uri getResponseUri() {
        return this.responseUri;
    }

    public String getCode() {
        return this.code;
    }
}

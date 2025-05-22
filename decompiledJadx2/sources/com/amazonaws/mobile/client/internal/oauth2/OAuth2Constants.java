package com.amazonaws.mobile.client.internal.oauth2;

import com.pudutech.robot.module.report.track2.TrackKey;

/* compiled from: OAuth2Client.java */
/* loaded from: classes.dex */
class OAuth2Constants {
    public static final String GRANT_TYPE = "grant_type";
    public static final String SCOPES = "scopes";

    OAuth2Constants() {
    }

    /* compiled from: OAuth2Client.java */
    /* loaded from: classes.dex */
    enum TokenResponseFields {
        ACCESS_TOKEN("access_token"),
        ID_TOKEN("id_token"),
        REFRESH_TOKEN("refresh_token"),
        TOKEN_TYPE("token_type"),
        EXPIRES_IN("expires_in"),
        SCOPES(OAuth2Constants.SCOPES),
        ERROR("error"),
        ERROR_DESCRIPTION(TrackKey.ERROR_DESCRIPTION),
        ERROR_URI("error_uri");

        private final String value;

        TokenResponseFields(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* compiled from: OAuth2Client.java */
    /* loaded from: classes.dex */
    enum TokenRequestFields {
        ACCESS_TOKEN("access_token"),
        ID_TOKEN("id_token");

        private final String value;

        TokenRequestFields(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* compiled from: OAuth2Client.java */
    /* loaded from: classes.dex */
    enum GrantTypes {
        AUTHORIZATION_CODE("authorization_code"),
        REFRESH_TOKEN("refresh_token");

        private final String value;

        GrantTypes(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}

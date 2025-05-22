package io.grpc.auth;

import com.google.auth.Credentials;
import io.grpc.CallCredentials;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class MoreCallCredentials {
    public static CallCredentials from(Credentials credentials) {
        return new GoogleAuthLibraryCallCredentials(credentials);
    }

    private MoreCallCredentials() {
    }
}

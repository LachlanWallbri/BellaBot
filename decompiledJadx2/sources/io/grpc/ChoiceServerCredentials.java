package io.grpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ChoiceServerCredentials extends ServerCredentials {
    private final List<ServerCredentials> creds;

    public static ServerCredentials create(ServerCredentials... serverCredentialsArr) {
        if (serverCredentialsArr.length == 0) {
            throw new IllegalArgumentException("At least one credential is required");
        }
        return new ChoiceServerCredentials(serverCredentialsArr);
    }

    private ChoiceServerCredentials(ServerCredentials... serverCredentialsArr) {
        for (ServerCredentials serverCredentials : serverCredentialsArr) {
            if (serverCredentials == null) {
                throw new NullPointerException();
            }
        }
        this.creds = Collections.unmodifiableList(new ArrayList(Arrays.asList(serverCredentialsArr)));
    }

    public List<ServerCredentials> getCredentialsList() {
        return this.creds;
    }
}

package com.google.auth.oauth2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class CloudShellCredentials extends GoogleCredentials {
    private static final int ACCESS_TOKEN_INDEX = 2;
    protected static final String GET_AUTH_TOKEN_REQUEST = "2\n[]";
    protected static final byte[] GET_AUTH_TOKEN_REQUEST_BYTES = "2\n[]\n".getBytes(Charsets.UTF_8);
    private static final int READ_TIMEOUT_MS = 5000;
    private static final long serialVersionUID = -2133257318957488451L;
    private final int authPort;

    public static CloudShellCredentials create(int i) {
        return newBuilder().setAuthPort(i).build();
    }

    private CloudShellCredentials(int i) {
        this.authPort = i;
    }

    protected int getAuthPort() {
        return this.authPort;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        Socket socket = new Socket("localhost", getAuthPort());
        socket.setSoTimeout(5000);
        try {
            socket.getOutputStream().write(GET_AUTH_TOKEN_REQUEST_BYTES);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.readLine();
            return new AccessToken(((List) OAuth2Utils.JSON_FACTORY.createJsonParser(bufferedReader).parseArray(ArrayList.class, Object.class)).get(2).toString(), null);
        } finally {
            socket.close();
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.authPort));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add("authPort", this.authPort).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        return (obj instanceof CloudShellCredentials) && this.authPort == ((CloudShellCredentials) obj).authPort;
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends GoogleCredentials.Builder {
        private int authPort;

        protected Builder() {
        }

        protected Builder(CloudShellCredentials cloudShellCredentials) {
            this.authPort = cloudShellCredentials.authPort;
        }

        public Builder setAuthPort(int i) {
            this.authPort = i;
            return this;
        }

        public int getAuthPort() {
            return this.authPort;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public CloudShellCredentials build() {
            return new CloudShellCredentials(this.authPort);
        }
    }
}

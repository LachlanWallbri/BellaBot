package io.grpc;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class TlsServerCredentials extends ServerCredentials {
    private final byte[] certificateChain;
    private final ClientAuth clientAuth;
    private final boolean fakeFeature;
    private final List<KeyManager> keyManagers;
    private final byte[] privateKey;
    private final String privateKeyPassword;
    private final byte[] rootCertificates;
    private final List<TrustManager> trustManagers;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ClientAuth {
        NONE,
        OPTIONAL,
        REQUIRE
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Feature {
        FAKE,
        MTLS,
        CUSTOM_MANAGERS
    }

    public static ServerCredentials create(File file, File file2) throws IOException {
        return newBuilder().keyManager(file, file2).build();
    }

    public static ServerCredentials create(InputStream inputStream, InputStream inputStream2) throws IOException {
        return newBuilder().keyManager(inputStream, inputStream2).build();
    }

    TlsServerCredentials(Builder builder) {
        this.fakeFeature = builder.fakeFeature;
        this.certificateChain = builder.certificateChain;
        this.privateKey = builder.privateKey;
        this.privateKeyPassword = builder.privateKeyPassword;
        this.keyManagers = builder.keyManagers;
        this.clientAuth = builder.clientAuth;
        this.rootCertificates = builder.rootCertificates;
        this.trustManagers = builder.trustManagers;
    }

    public byte[] getCertificateChain() {
        byte[] bArr = this.certificateChain;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getPrivateKey() {
        byte[] bArr = this.privateKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public List<KeyManager> getKeyManagers() {
        return this.keyManagers;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public byte[] getRootCertificates() {
        byte[] bArr = this.rootCertificates;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public List<TrustManager> getTrustManagers() {
        return this.trustManagers;
    }

    public Set<Feature> incomprehensible(Set<Feature> set) {
        EnumSet noneOf = EnumSet.noneOf(Feature.class);
        if (this.fakeFeature) {
            requiredFeature(set, noneOf, Feature.FAKE);
        }
        if (this.clientAuth != ClientAuth.NONE) {
            requiredFeature(set, noneOf, Feature.MTLS);
        }
        if (this.keyManagers != null || this.trustManagers != null) {
            requiredFeature(set, noneOf, Feature.CUSTOM_MANAGERS);
        }
        return Collections.unmodifiableSet(noneOf);
    }

    private static void requiredFeature(Set<Feature> set, Set<Feature> set2, Feature feature) {
        if (set.contains(feature)) {
            return;
        }
        set2.add(feature);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private byte[] certificateChain;
        private ClientAuth clientAuth;
        private boolean fakeFeature;
        private List<KeyManager> keyManagers;
        private byte[] privateKey;
        private String privateKeyPassword;
        private byte[] rootCertificates;
        private List<TrustManager> trustManagers;

        private Builder() {
            this.clientAuth = ClientAuth.NONE;
        }

        public Builder requireFakeFeature() {
            this.fakeFeature = true;
            return this;
        }

        public Builder keyManager(File file, File file2) throws IOException {
            return keyManager(file, file2, (String) null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x001a, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x001e, code lost:
        
            throw r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder keyManager(File file, File file2, String str) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                fileInputStream = new FileInputStream(file2);
                Builder keyManager = keyManager(fileInputStream, fileInputStream, str);
                fileInputStream.close();
                return keyManager;
            } finally {
            }
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2) throws IOException {
            return keyManager(inputStream, inputStream2, (String) null);
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2, String str) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            byte[] byteArray2 = ByteStreams.toByteArray(inputStream2);
            clearKeyManagers();
            this.certificateChain = byteArray;
            this.privateKey = byteArray2;
            this.privateKeyPassword = str;
            return this;
        }

        public Builder keyManager(KeyManager... keyManagerArr) {
            List<KeyManager> unmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(keyManagerArr)));
            clearKeyManagers();
            this.keyManagers = unmodifiableList;
            return this;
        }

        private void clearKeyManagers() {
            this.certificateChain = null;
            this.privateKey = null;
            this.privateKeyPassword = null;
            this.keyManagers = null;
        }

        public Builder clientAuth(ClientAuth clientAuth) {
            Preconditions.checkNotNull(clientAuth, "clientAuth");
            this.clientAuth = clientAuth;
            return this;
        }

        public Builder trustManager(File file) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return trustManager(fileInputStream);
            } finally {
                fileInputStream.close();
            }
        }

        public Builder trustManager(InputStream inputStream) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            clearTrustManagers();
            this.rootCertificates = byteArray;
            return this;
        }

        public Builder trustManager(TrustManager... trustManagerArr) {
            List<TrustManager> unmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(trustManagerArr)));
            clearTrustManagers();
            this.trustManagers = unmodifiableList;
            return this;
        }

        private void clearTrustManagers() {
            this.rootCertificates = null;
            this.trustManagers = null;
        }

        public ServerCredentials build() {
            if (this.certificateChain == null && this.keyManagers == null) {
                throw new IllegalStateException("A key manager is required");
            }
            return new TlsServerCredentials(this);
        }
    }
}

package org.conscrypt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
abstract class NativeRef {
    final long address;

    abstract void doFree(long j);

    NativeRef(long j) {
        if (j == 0) {
            throw new NullPointerException("address == 0");
        }
        this.address = j;
    }

    public boolean equals(Object obj) {
        return (obj instanceof NativeRef) && ((NativeRef) obj).address == this.address;
    }

    public int hashCode() {
        long j = this.address;
        return (int) (j ^ (j >>> 32));
    }

    protected void finalize() throws Throwable {
        try {
            if (this.address != 0) {
                doFree(this.address);
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class EC_GROUP extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EC_GROUP(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EC_GROUP_clear_free(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class EC_POINT extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EC_POINT(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EC_POINT_clear_free(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class EVP_CIPHER_CTX extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EVP_CIPHER_CTX(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EVP_CIPHER_CTX_free(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class EVP_MD_CTX extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EVP_MD_CTX(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EVP_MD_CTX_destroy(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static final class EVP_PKEY extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EVP_PKEY(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EVP_PKEY_free(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class EVP_PKEY_CTX extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EVP_PKEY_CTX(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.EVP_PKEY_CTX_free(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class HMAC_CTX extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public HMAC_CTX(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.HMAC_CTX_free(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static final class SSL_SESSION extends NativeRef {
        /* JADX INFO: Access modifiers changed from: package-private */
        public SSL_SESSION(long j) {
            super(j);
        }

        @Override // org.conscrypt.NativeRef
        void doFree(long j) {
            NativeCrypto.SSL_SESSION_free(j);
        }
    }
}

package com.amazonaws.services.p048s3.internal.crypto;

import com.amazonaws.services.p048s3.model.CryptoMode;
import java.security.SecureRandom;

@Deprecated
/* loaded from: classes.dex */
final class S3CryptoScheme {
    static final String AES = "AES";
    static final String RSA = "RSA";
    private static final SecureRandom SRAND = new SecureRandom();
    private final ContentCryptoScheme contentCryptoScheme;
    private final S3KeyWrapScheme kwScheme;

    S3CryptoScheme(ContentCryptoScheme contentCryptoScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = new S3KeyWrapScheme();
    }

    private S3CryptoScheme(ContentCryptoScheme contentCryptoScheme, S3KeyWrapScheme s3KeyWrapScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = s3KeyWrapScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureRandom getSecureRandom() {
        return SRAND;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentCryptoScheme getContentCryptoScheme() {
        return this.contentCryptoScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public S3KeyWrapScheme getKeyWrapScheme() {
        return this.kwScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAesGcm(String str) {
        return ContentCryptoScheme.AES_GCM.getCipherAlgorithm().equals(str);
    }

    /* renamed from: com.amazonaws.services.s3.internal.crypto.S3CryptoScheme$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C13101 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$services$s3$model$CryptoMode = new int[CryptoMode.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.EncryptionOnly.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.AuthenticatedEncryption.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.StrictAuthenticatedEncryption.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static S3CryptoScheme from(CryptoMode cryptoMode) {
        int i = C13101.$SwitchMap$com$amazonaws$services$s3$model$CryptoMode[cryptoMode.ordinal()];
        if (i == 1) {
            return new S3CryptoScheme(ContentCryptoScheme.AES_CBC, S3KeyWrapScheme.NONE);
        }
        if (i == 2 || i == 3) {
            return new S3CryptoScheme(ContentCryptoScheme.AES_GCM, new S3KeyWrapScheme());
        }
        throw new IllegalStateException();
    }
}

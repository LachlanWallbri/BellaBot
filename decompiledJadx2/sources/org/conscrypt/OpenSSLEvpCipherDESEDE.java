package org.conscrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.crypto.NoSuchPaddingException;
import org.conscrypt.OpenSSLCipher;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class OpenSSLEvpCipherDESEDE extends OpenSSLEvpCipher {
    private static final int DES_BLOCK_SIZE = 8;

    @Override // org.conscrypt.OpenSSLCipher
    String getBaseCipherName() {
        return "DESede";
    }

    @Override // org.conscrypt.OpenSSLCipher
    int getCipherBlockSize() {
        return 8;
    }

    OpenSSLEvpCipherDESEDE(OpenSSLCipher.Mode mode, OpenSSLCipher.Padding padding) {
        super(mode, padding);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class CBC extends OpenSSLEvpCipherDESEDE {
        CBC(OpenSSLCipher.Padding padding) {
            super(OpenSSLCipher.Mode.CBC, padding);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class NoPadding extends CBC {
            public NoPadding() {
                super(OpenSSLCipher.Padding.NOPADDING);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class PKCS5Padding extends CBC {
            public PKCS5Padding() {
                super(OpenSSLCipher.Padding.PKCS5PADDING);
            }
        }
    }

    @Override // org.conscrypt.OpenSSLEvpCipher
    String getCipherName(int i, OpenSSLCipher.Mode mode) {
        return (i == 16 ? "des-ede" : "des-ede3") + "-" + mode.toString().toLowerCase(Locale.US);
    }

    @Override // org.conscrypt.OpenSSLCipher
    void checkSupportedKeySize(int i) throws InvalidKeyException {
        if (i != 16 && i != 24) {
            throw new InvalidKeyException("key size must be 128 or 192 bits");
        }
    }

    @Override // org.conscrypt.OpenSSLCipher
    void checkSupportedMode(OpenSSLCipher.Mode mode) throws NoSuchAlgorithmException {
        if (mode == OpenSSLCipher.Mode.CBC) {
            return;
        }
        throw new NoSuchAlgorithmException("Unsupported mode " + mode.toString());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: org.conscrypt.OpenSSLEvpCipherDESEDE$1 */
    /* loaded from: classes9.dex */
    static /* synthetic */ class C85721 {
        static final /* synthetic */ int[] $SwitchMap$org$conscrypt$OpenSSLCipher$Padding = new int[OpenSSLCipher.Padding.values().length];

        static {
            try {
                $SwitchMap$org$conscrypt$OpenSSLCipher$Padding[OpenSSLCipher.Padding.NOPADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$conscrypt$OpenSSLCipher$Padding[OpenSSLCipher.Padding.PKCS5PADDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // org.conscrypt.OpenSSLCipher
    void checkSupportedPadding(OpenSSLCipher.Padding padding) throws NoSuchPaddingException {
        int i = C85721.$SwitchMap$org$conscrypt$OpenSSLCipher$Padding[padding.ordinal()];
        if (i == 1 || i == 2) {
            return;
        }
        throw new NoSuchPaddingException("Unsupported padding " + padding.toString());
    }
}

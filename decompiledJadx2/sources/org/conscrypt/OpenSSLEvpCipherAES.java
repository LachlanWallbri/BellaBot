package org.conscrypt;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.crypto.NoSuchPaddingException;
import org.conscrypt.OpenSSLCipher;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class OpenSSLEvpCipherAES extends OpenSSLEvpCipher {
    private static final int AES_BLOCK_SIZE = 16;

    @Override // org.conscrypt.OpenSSLCipher
    String getBaseCipherName() {
        return JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
    }

    @Override // org.conscrypt.OpenSSLCipher
    int getCipherBlockSize() {
        return 16;
    }

    OpenSSLEvpCipherAES(OpenSSLCipher.Mode mode, OpenSSLCipher.Padding padding) {
        super(mode, padding);
    }

    @Override // org.conscrypt.OpenSSLCipher
    void checkSupportedMode(OpenSSLCipher.Mode mode) throws NoSuchAlgorithmException {
        int i = C85711.$SwitchMap$org$conscrypt$OpenSSLCipher$Mode[mode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        throw new NoSuchAlgorithmException("Unsupported mode " + mode.toString());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: org.conscrypt.OpenSSLEvpCipherAES$1 */
    /* loaded from: classes9.dex */
    static /* synthetic */ class C85711 {
        static final /* synthetic */ int[] $SwitchMap$org$conscrypt$OpenSSLCipher$Mode;
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
            $SwitchMap$org$conscrypt$OpenSSLCipher$Mode = new int[OpenSSLCipher.Mode.values().length];
            try {
                $SwitchMap$org$conscrypt$OpenSSLCipher$Mode[OpenSSLCipher.Mode.CBC.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$conscrypt$OpenSSLCipher$Mode[OpenSSLCipher.Mode.CTR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$conscrypt$OpenSSLCipher$Mode[OpenSSLCipher.Mode.ECB.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // org.conscrypt.OpenSSLCipher
    void checkSupportedPadding(OpenSSLCipher.Padding padding) throws NoSuchPaddingException {
        int i = C85711.$SwitchMap$org$conscrypt$OpenSSLCipher$Padding[padding.ordinal()];
        if (i == 1 || i == 2) {
            return;
        }
        throw new NoSuchPaddingException("Unsupported padding " + padding.toString());
    }

    @Override // org.conscrypt.OpenSSLEvpCipher
    String getCipherName(int i, OpenSSLCipher.Mode mode) {
        return "aes-" + (i * 8) + "-" + mode.toString().toLowerCase(Locale.US);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class AES extends OpenSSLEvpCipherAES {
        AES(OpenSSLCipher.Mode mode, OpenSSLCipher.Padding padding) {
            super(mode, padding);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CBC extends AES {
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

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CTR extends AES {
            public CTR() {
                super(OpenSSLCipher.Mode.CTR, OpenSSLCipher.Padding.NOPADDING);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class ECB extends AES {
            ECB(OpenSSLCipher.Padding padding) {
                super(OpenSSLCipher.Mode.ECB, padding);
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class NoPadding extends ECB {
                public NoPadding() {
                    super(OpenSSLCipher.Padding.NOPADDING);
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class PKCS5Padding extends ECB {
                public PKCS5Padding() {
                    super(OpenSSLCipher.Padding.PKCS5PADDING);
                }
            }
        }

        @Override // org.conscrypt.OpenSSLCipher
        void checkSupportedKeySize(int i) throws InvalidKeyException {
            if (i == 16 || i == 24 || i == 32) {
                return;
            }
            throw new InvalidKeyException("Unsupported key size: " + i + " bytes");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class AES_128 extends OpenSSLEvpCipherAES {
        AES_128(OpenSSLCipher.Mode mode, OpenSSLCipher.Padding padding) {
            super(mode, padding);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CBC extends AES_128 {
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

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CTR extends AES_128 {
            public CTR() {
                super(OpenSSLCipher.Mode.CTR, OpenSSLCipher.Padding.NOPADDING);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class ECB extends AES_128 {
            ECB(OpenSSLCipher.Padding padding) {
                super(OpenSSLCipher.Mode.ECB, padding);
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class NoPadding extends ECB {
                public NoPadding() {
                    super(OpenSSLCipher.Padding.NOPADDING);
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class PKCS5Padding extends ECB {
                public PKCS5Padding() {
                    super(OpenSSLCipher.Padding.PKCS5PADDING);
                }
            }
        }

        @Override // org.conscrypt.OpenSSLCipher
        void checkSupportedKeySize(int i) throws InvalidKeyException {
            if (i == 16) {
                return;
            }
            throw new InvalidKeyException("Unsupported key size: " + i + " bytes");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class AES_256 extends OpenSSLEvpCipherAES {
        AES_256(OpenSSLCipher.Mode mode, OpenSSLCipher.Padding padding) {
            super(mode, padding);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CBC extends AES_256 {
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

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class CTR extends AES_256 {
            public CTR() {
                super(OpenSSLCipher.Mode.CTR, OpenSSLCipher.Padding.NOPADDING);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
         */
        /* loaded from: classes9.dex */
        public static class ECB extends AES_256 {
            ECB(OpenSSLCipher.Padding padding) {
                super(OpenSSLCipher.Mode.ECB, padding);
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class NoPadding extends ECB {
                public NoPadding() {
                    super(OpenSSLCipher.Padding.NOPADDING);
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* loaded from: classes9.dex */
            public static class PKCS5Padding extends ECB {
                public PKCS5Padding() {
                    super(OpenSSLCipher.Padding.PKCS5PADDING);
                }
            }
        }

        @Override // org.conscrypt.OpenSSLCipher
        void checkSupportedKeySize(int i) throws InvalidKeyException {
            if (i == 32) {
                return;
            }
            throw new InvalidKeyException("Unsupported key size: " + i + " bytes");
        }
    }
}

package com.loc;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: Encrypt.java */
/* renamed from: com.loc.o */
/* loaded from: classes4.dex */
public final class C3889o {

    /* renamed from: a */
    private static final char[] f4293a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: b */
    private static final byte[] f4294b = new byte[128];

    static {
        for (int i = 0; i < 128; i++) {
            f4294b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f4294b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            f4294b[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            f4294b[i4] = (byte) ((i4 - 48) + 52);
        }
        byte[] bArr = f4294b;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    /* renamed from: a */
    public static String m3177a(String str) {
        return C3894t.m3226a(m3182b(str));
    }

    /* renamed from: a */
    public static String m3178a(byte[] bArr) {
        try {
            return m3184c(bArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "Encrypt", "encodeBase64");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m3179a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m3180a(byte[] bArr, byte[] bArr2) {
        try {
            return m3183b(bArr, bArr2);
        } catch (Throwable th) {
            C3897w.m3249a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    /* renamed from: b */
    public static String m3181b(byte[] bArr) {
        try {
            return m3184c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
    
        if (r5 == (-1)) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0059, code lost:
    
        r2.write(((r4 & 15) << 4) | ((r5 & 60) >>> 2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
    
        r4 = r0 + 1;
        r0 = r8[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
    
        if (r0 != 61) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0070, code lost:
    
        r0 = com.loc.C3889o.f4294b[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
    
        if (r4 >= r1) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0076, code lost:
    
        if (r0 == (-1)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0079, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:
    
        if (r0 == (-1)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007d, code lost:
    
        r2.write(r0 | ((r5 & 3) << 6));
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006f, code lost:
    
        return r2.toByteArray();
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] m3182b(String str) {
        int i;
        byte b;
        int i2;
        byte b2;
        int i3 = 0;
        if (str == null) {
            return new byte[0];
        }
        byte[] m3232a = C3894t.m3232a(str);
        int length = m3232a.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        while (i3 < length) {
            while (true) {
                i = i3 + 1;
                b = f4294b[m3232a[i3]];
                if (i >= length || b != -1) {
                    break;
                }
                i3 = i;
            }
            if (b == -1) {
                break;
            }
            while (true) {
                i2 = i + 1;
                b2 = f4294b[m3232a[i]];
                if (i2 >= length || b2 != -1) {
                    break;
                }
                i = i2;
            }
            if (b2 == -1) {
                break;
            }
            byteArrayOutputStream.write((b << 2) | ((b2 & TarConstants.LF_NORMAL) >>> 4));
            while (true) {
                int i4 = i2 + 1;
                byte b3 = m3232a[i2];
                if (b3 == 61) {
                    return byteArrayOutputStream.toByteArray();
                }
                byte b4 = f4294b[b3];
                if (i4 >= length || b4 != -1) {
                    break;
                }
                i2 = i4;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    private static byte[] m3183b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(C3894t.m3231a());
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        try {
            cipher.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return cipher.doFinal(bArr2);
    }

    /* renamed from: c */
    private static String m3184c(byte[] bArr) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(f4293a[i3 >>> 2]);
                stringBuffer.append(f4293a[(i3 & 3) << 4]);
                str = "==";
            } else {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (i4 == length) {
                    stringBuffer.append(f4293a[i3 >>> 2]);
                    stringBuffer.append(f4293a[((i3 & 3) << 4) | ((i5 & DimensionsKt.HDPI) >>> 4)]);
                    stringBuffer.append(f4293a[(i5 & 15) << 2]);
                    str = "=";
                } else {
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & 255;
                    stringBuffer.append(f4293a[i3 >>> 2]);
                    stringBuffer.append(f4293a[((i3 & 3) << 4) | ((i5 & DimensionsKt.HDPI) >>> 4)]);
                    stringBuffer.append(f4293a[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
                    stringBuffer.append(f4293a[i7 & 63]);
                    i = i6;
                }
            }
            stringBuffer.append(str);
            break;
        }
        return stringBuffer.toString();
    }
}

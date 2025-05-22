package com.iflytek.aiui.pro;

import android.text.TextUtils;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.l */
/* loaded from: classes.dex */
public class C3624l {

    /* renamed from: a */
    private static final char[] f2573a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: b */
    private static final byte[] f2574b = new byte[128];

    static {
        for (int i = 0; i < 128; i++) {
            f2574b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f2574b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            f2574b[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            f2574b[i4] = (byte) ((i4 - 48) + 52);
        }
        byte[] bArr = f2574b;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    /* renamed from: a */
    public static String m1392a(String str) {
        FileInputStream fileInputStream;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[2048];
                    MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                    fileInputStream = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                String m1423d = C3628n.m1423d(messageDigest.digest());
                                try {
                                    fileInputStream.close();
                                    return m1423d;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    return m1423d;
                                }
                            }
                            messageDigest.update(bArr, 0, read);
                        } catch (Throwable th) {
                            th = th;
                            try {
                                th.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        return null;
                                    } catch (IOException e2) {
                                    }
                                }
                                return null;
                            } finally {
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m1393a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m1394a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m1395a(byte[] bArr, byte[] bArr2) {
        try {
            return m1398b(bArr, bArr2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String m1396b(String str) {
        if (str == null) {
            return null;
        }
        return C3628n.m1423d(m1400c(str));
    }

    /* renamed from: b */
    public static String m1397b(byte[] bArr) {
        try {
            return m1399c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m1398b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(C3628n.m1414a());
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
    private static String m1399c(byte[] bArr) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(f2573a[i3 >>> 2]);
                stringBuffer.append(f2573a[(i3 & 3) << 4]);
                str = "==";
            } else {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (i4 == length) {
                    stringBuffer.append(f2573a[i3 >>> 2]);
                    stringBuffer.append(f2573a[((i3 & 3) << 4) | ((i5 & DimensionsKt.HDPI) >>> 4)]);
                    stringBuffer.append(f2573a[(i5 & 15) << 2]);
                    str = "=";
                } else {
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & 255;
                    stringBuffer.append(f2573a[i3 >>> 2]);
                    stringBuffer.append(f2573a[((i3 & 3) << 4) | ((i5 & DimensionsKt.HDPI) >>> 4)]);
                    stringBuffer.append(f2573a[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
                    stringBuffer.append(f2573a[i7 & 63]);
                    i = i6;
                }
            }
            stringBuffer.append(str);
            break;
        }
        return stringBuffer.toString();
    }

    /* renamed from: c */
    public static byte[] m1400c(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(C3628n.m1415a(str));
            return messageDigest.digest();
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
    
        if (r5 == (-1)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005b, code lost:
    
        r2.write(((r4 & 15) << 4) | ((r5 & 60) >>> 2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0067, code lost:
    
        r4 = r0 + 1;
        r0 = r8[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006b, code lost:
    
        if (r0 != 61) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0072, code lost:
    
        r0 = com.iflytek.aiui.pro.C3624l.f2574b[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0076, code lost:
    
        if (r4 >= r1) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0078, code lost:
    
        if (r0 == (-1)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007b, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007d, code lost:
    
        if (r0 == (-1)) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
    
        r2.write(r0 | ((r5 & 3) << 6));
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0071, code lost:
    
        return r2.toByteArray();
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] m1401d(String str) {
        int i;
        byte b;
        int i2;
        byte b2;
        int i3 = 0;
        if (str == null) {
            return new byte[0];
        }
        byte[] m1415a = C3628n.m1415a(str);
        int length = m1415a.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        while (i3 < length) {
            while (true) {
                i = i3 + 1;
                b = f2574b[m1415a[i3]];
                if (i >= length || b != -1) {
                    break;
                }
                i3 = i;
            }
            if (b != -1) {
                while (true) {
                    i2 = i + 1;
                    b2 = f2574b[m1415a[i]];
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
                    byte b3 = m1415a[i2];
                    if (b3 == 61) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byte b4 = f2574b[b3];
                    if (i4 >= length || b4 != -1) {
                        break;
                    }
                    i2 = i4;
                }
            } else {
                break;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}

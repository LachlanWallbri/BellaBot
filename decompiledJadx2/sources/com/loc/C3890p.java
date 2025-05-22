package com.loc;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: MD5.java */
/* renamed from: com.loc.p */
/* loaded from: classes4.dex */
public final class C3890p {
    /* renamed from: a */
    public static String m3185a(String str) {
        FileInputStream fileInputStream;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                byte[] bArr = new byte[2048];
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            C3897w.m3249a(th, MessageDigestAlgorithms.MD5, "getMd5FromFile");
                            return null;
                        } finally {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    C3897w.m3249a(e, MessageDigestAlgorithms.MD5, "getMd5FromFile");
                                }
                            }
                        }
                    }
                }
                String m3240d = C3894t.m3240d(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    C3897w.m3249a(e2, MessageDigestAlgorithms.MD5, "getMd5FromFile");
                }
                return m3240d;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    /* renamed from: a */
    public static String m3186a(byte[] bArr) {
        return C3894t.m3240d(m3187a(bArr, MessageDigestAlgorithms.MD5));
    }

    /* renamed from: a */
    public static byte[] m3187a(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            C3897w.m3249a(th, MessageDigestAlgorithms.MD5, "getMd5Bytes1");
            return null;
        }
    }

    /* renamed from: b */
    public static String m3188b(String str) {
        if (str == null) {
            return null;
        }
        return C3894t.m3240d(m3190d(str));
    }

    /* renamed from: c */
    public static String m3189c(String str) {
        return C3894t.m3241e(m3191e(str));
    }

    /* renamed from: d */
    private static byte[] m3190d(String str) {
        try {
            return m3192f(str);
        } catch (Throwable th) {
            C3897w.m3249a(th, MessageDigestAlgorithms.MD5, "getMd5Bytes");
            return new byte[0];
        }
    }

    /* renamed from: e */
    private static byte[] m3191e(String str) {
        try {
            return m3192f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    /* renamed from: f */
    private static byte[] m3192f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        messageDigest.update(C3894t.m3232a(str));
        return messageDigest.digest();
    }
}

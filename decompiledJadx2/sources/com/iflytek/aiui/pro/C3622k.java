package com.iflytek.aiui.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.k */
/* loaded from: classes.dex */
public class C3622k {
    /* renamed from: a */
    public static String m1385a() {
        Throwable th;
        String str;
        try {
            str = String.valueOf(System.currentTimeMillis());
            try {
                int length = str.length();
                return str.substring(0, length - 2) + "1" + str.substring(length - 1);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th3) {
            th = th3;
            str = null;
        }
    }

    /* renamed from: a */
    public static String m1386a(Context context, String str, String str2) {
        try {
            return C3624l.m1396b(C3618i.m1360d(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m1387a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                C3628n.m1411a(byteArrayOutputStream, (byte) 0, new byte[0]);
            } else {
                C3628n.m1411a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, C3628n.m1415a(str));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static byte[] m1388a(Context context) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            m1387a(byteArrayOutputStream, C3618i.m1368l(context));
            m1387a(byteArrayOutputStream, "");
            String m1362f = C3618i.m1362f(context);
            if (m1362f == null) {
                m1362f = "";
            }
            m1387a(byteArrayOutputStream, m1362f);
            m1387a(byteArrayOutputStream, C3618i.m1357b(context));
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, C3618i.m1352a(context));
            m1387a(byteArrayOutputStream, C3618i.m1359c(context));
            m1387a(byteArrayOutputStream, String.valueOf(Build.VERSION.SDK_INT));
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, C3618i.m1365i(context) + "");
            m1387a(byteArrayOutputStream, C3618i.m1364h(context) + "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, C3618i.m1363g(context));
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            m1387a(byteArrayOutputStream, "");
            byte[] m1422c = C3628n.m1422c(byteArrayOutputStream.toByteArray());
            PublicKey m1410a = C3628n.m1410a(context);
            if (m1422c.length <= 117) {
                return C3624l.m1394a(m1422c, m1410a);
            }
            byte[] bArr = new byte[117];
            System.arraycopy(m1422c, 0, bArr, 0, 117);
            byte[] m1394a = C3624l.m1394a(bArr, m1410a);
            byte[] bArr2 = new byte[(m1422c.length + 128) - 117];
            System.arraycopy(m1394a, 0, bArr2, 0, 128);
            System.arraycopy(m1422c, 117, bArr2, 128, m1422c.length - 117);
            return bArr2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m1389a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        if (keyGenerator == null) {
            return null;
        }
        keyGenerator.init(256);
        byte[] encoded = keyGenerator.generateKey().getEncoded();
        PublicKey m1410a = C3628n.m1410a(context);
        if (m1410a == null) {
            return null;
        }
        byte[] m1394a = C3624l.m1394a(encoded, m1410a);
        byte[] m1395a = C3624l.m1395a(encoded, bArr);
        byte[] bArr2 = new byte[m1394a.length + m1395a.length];
        System.arraycopy(m1394a, 0, bArr2, 0, m1394a.length);
        System.arraycopy(m1395a, 0, bArr2, m1394a.length, m1395a.length);
        return bArr2;
    }

    /* renamed from: b */
    public static String m1390b(Context context, byte[] bArr) {
        try {
            byte[] m1422c = C3628n.m1422c(m1389a(context, bArr));
            return m1422c != null ? C3624l.m1397b(m1422c) : "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}

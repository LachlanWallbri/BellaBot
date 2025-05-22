package com.loc;

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

/* compiled from: ClientInfo.java */
/* renamed from: com.loc.m */
/* loaded from: classes4.dex */
public final class C3887m {

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ClientInfo.java */
    /* renamed from: com.loc.m$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        String f4267a;

        /* renamed from: b */
        String f4268b;

        /* renamed from: c */
        String f4269c;

        /* renamed from: d */
        String f4270d;

        /* renamed from: e */
        String f4271e;

        /* renamed from: f */
        String f4272f;

        /* renamed from: g */
        String f4273g;

        /* renamed from: h */
        String f4274h;

        /* renamed from: i */
        String f4275i;

        /* renamed from: j */
        String f4276j;

        /* renamed from: k */
        String f4277k;

        /* renamed from: l */
        String f4278l;

        /* renamed from: m */
        String f4279m;

        /* renamed from: n */
        String f4280n;

        /* renamed from: o */
        String f4281o;

        /* renamed from: p */
        String f4282p;

        /* renamed from: q */
        String f4283q;

        /* renamed from: r */
        String f4284r;

        /* renamed from: s */
        String f4285s;

        /* renamed from: t */
        String f4286t;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    /* renamed from: a */
    public static String m3136a() {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            int length = valueOf.length();
            return valueOf.substring(0, length - 2) + "1" + valueOf.substring(length - 1);
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "getTS");
            return null;
        }
    }

    /* renamed from: a */
    public static String m3137a(Context context, String str, String str2) {
        try {
            return C3890p.m3188b(C3885k.m3126e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "Scode");
            return null;
        }
    }

    /* renamed from: a */
    private static void m3138a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            C3894t.m3228a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            C3894t.m3228a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, C3894t.m3232a(str));
        }
    }

    /* renamed from: a */
    public static byte[] m3139a(Context context) {
        try {
            a aVar = new a((byte) 0);
            aVar.f4267a = C3888n.m3168q(context);
            aVar.f4268b = C3888n.m3160i(context);
            String m3157f = C3888n.m3157f(context);
            if (m3157f == null) {
                m3157f = "";
            }
            aVar.f4269c = m3157f;
            aVar.f4270d = C3885k.m3124c(context);
            aVar.f4271e = Build.MODEL;
            aVar.f4272f = Build.MANUFACTURER;
            aVar.f4273g = Build.DEVICE;
            aVar.f4274h = C3885k.m3122b(context);
            aVar.f4275i = C3885k.m3125d(context);
            aVar.f4276j = String.valueOf(Build.VERSION.SDK_INT);
            aVar.f4277k = C3888n.m3169r(context);
            aVar.f4278l = C3888n.m3167p(context);
            StringBuilder sb = new StringBuilder();
            sb.append(C3888n.m3164m(context));
            aVar.f4279m = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(C3888n.m3163l(context));
            aVar.f4280n = sb2.toString();
            aVar.f4281o = C3888n.m3170s(context);
            aVar.f4282p = C3888n.m3162k(context);
            aVar.f4283q = C3888n.m3159h(context);
            aVar.f4284r = C3888n.m3158g(context);
            String[] m3161j = C3888n.m3161j(context);
            aVar.f4285s = m3161j[0];
            aVar.f4286t = m3161j[1];
            return m3140a(aVar);
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m3140a(a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] m3179a;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            m3138a(byteArrayOutputStream, aVar.f4267a);
            m3138a(byteArrayOutputStream, aVar.f4268b);
            m3138a(byteArrayOutputStream, aVar.f4269c);
            m3138a(byteArrayOutputStream, aVar.f4270d);
            m3138a(byteArrayOutputStream, aVar.f4271e);
            m3138a(byteArrayOutputStream, aVar.f4272f);
            m3138a(byteArrayOutputStream, aVar.f4273g);
            m3138a(byteArrayOutputStream, aVar.f4274h);
            m3138a(byteArrayOutputStream, aVar.f4275i);
            m3138a(byteArrayOutputStream, aVar.f4276j);
            m3138a(byteArrayOutputStream, aVar.f4277k);
            m3138a(byteArrayOutputStream, aVar.f4278l);
            m3138a(byteArrayOutputStream, aVar.f4279m);
            m3138a(byteArrayOutputStream, aVar.f4280n);
            m3138a(byteArrayOutputStream, aVar.f4281o);
            m3138a(byteArrayOutputStream, aVar.f4282p);
            m3138a(byteArrayOutputStream, aVar.f4283q);
            m3138a(byteArrayOutputStream, aVar.f4284r);
            m3138a(byteArrayOutputStream, aVar.f4285s);
            m3138a(byteArrayOutputStream, aVar.f4286t);
            byte[] m3236b = C3894t.m3236b(byteArrayOutputStream.toByteArray());
            PublicKey m3235b = C3894t.m3235b();
            if (m3236b.length > 117) {
                byte[] bArr = new byte[117];
                System.arraycopy(m3236b, 0, bArr, 0, 117);
                byte[] m3179a2 = C3889o.m3179a(bArr, m3235b);
                m3179a = new byte[(m3236b.length + 128) - 117];
                System.arraycopy(m3179a2, 0, m3179a, 0, 128);
                System.arraycopy(m3236b, 117, m3179a, 128, m3236b.length - 117);
            } else {
                m3179a = C3889o.m3179a(m3236b, m3235b);
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return m3179a;
        } catch (Throwable th3) {
            th = th3;
            try {
                C3897w.m3249a(th, "CInfo", "InitXInfo");
                return null;
            } finally {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static byte[] m3141a(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        if (keyGenerator == null) {
            return null;
        }
        keyGenerator.init(256);
        byte[] encoded = keyGenerator.generateKey().getEncoded();
        PublicKey m3235b = C3894t.m3235b();
        if (m3235b == null) {
            return null;
        }
        byte[] m3179a = C3889o.m3179a(encoded, m3235b);
        byte[] m3180a = C3889o.m3180a(encoded, bArr);
        byte[] bArr2 = new byte[m3179a.length + m3180a.length];
        System.arraycopy(m3179a, 0, bArr2, 0, m3179a.length);
        System.arraycopy(m3180a, 0, bArr2, m3179a.length, m3180a.length);
        return bArr2;
    }

    /* renamed from: b */
    public static String m3142b(byte[] bArr) {
        try {
            return m3144d(bArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "AESData");
            return "";
        }
    }

    /* renamed from: c */
    public static String m3143c(byte[] bArr) {
        try {
            return m3144d(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    private static String m3144d(byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] m3236b = C3894t.m3236b(m3141a(bArr));
        return m3236b != null ? C3889o.m3178a(m3236b) : "";
    }
}

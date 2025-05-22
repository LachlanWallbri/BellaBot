package com.aliyun.alink.dm.p021o;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: StringGeneratorUtils.java */
/* renamed from: com.aliyun.alink.dm.o.e */
/* loaded from: classes.dex */
public class C0870e {

    /* renamed from: a */
    protected static final char[] f479a = "0123456789ABCDEF".toCharArray();

    /* renamed from: a */
    public static String m189a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(f479a[random.nextInt(16)]);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m192a(byte[] bArr, String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(bArr);
        return m191a(messageDigest.digest());
    }

    /* renamed from: a */
    public static String m190a(String str) {
        String str2 = str + m189a(32);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(str2.getBytes());
            return m191a(messageDigest.digest());
        } catch (NoSuchAlgorithmException | Exception unused) {
            return m189a(32);
        }
    }

    /* renamed from: a */
    public static String m191a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f479a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }
}

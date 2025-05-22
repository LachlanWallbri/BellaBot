package com.iflytek.aiui.pro;

import android.text.TextUtils;
import java.util.Arrays;

/* renamed from: com.iflytek.aiui.pro.m0 */
/* loaded from: classes4.dex */
public class C3627m0 {

    /* renamed from: a */
    private static byte[] f2581a;

    /* renamed from: a */
    private static byte[] m1404a(byte[] bArr) {
        byte[] bArr2 = f2581a;
        if (bArr2 == null || bArr2.length != bArr.length * 4) {
            f2581a = new byte[bArr.length * 4];
        } else {
            Arrays.fill(bArr2, (byte) 0);
        }
        for (int i = 0; i < bArr.length / 2; i++) {
            byte[] bArr3 = f2581a;
            int i2 = i * 8;
            byte b = (byte) 0;
            bArr3[i2] = b;
            bArr3[i2 + 1] = b;
            int i3 = i * 2;
            bArr3[i2 + 2] = bArr[i3];
            bArr3[i2 + 3] = bArr[i3 + 1];
        }
        return f2581a;
    }

    /* renamed from: b */
    public static byte[] m1405b(String str, byte[] bArr) {
        return TextUtils.equals(str, "C1B16_2_C2B32") ? m1404a(bArr) : bArr;
    }
}

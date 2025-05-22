package com.iflytek.aiui.pro;

import java.nio.ByteBuffer;

/* renamed from: com.iflytek.aiui.pro.s0 */
/* loaded from: classes4.dex */
public class C3639s0 {
    /* renamed from: a */
    public static int m1535a(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[4]);
        for (int i2 = i; i2 < i + 4; i2++) {
            wrap.put(bArr[i2]);
        }
        wrap.flip();
        return wrap.getInt();
    }
}

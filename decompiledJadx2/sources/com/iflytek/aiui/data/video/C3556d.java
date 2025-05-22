package com.iflytek.aiui.data.video;

import android.media.Image;
import java.nio.ByteBuffer;

/* renamed from: com.iflytek.aiui.data.video.d */
/* loaded from: classes4.dex */
class C3556d {

    /* renamed from: a */
    static byte[] f2191a;

    /* renamed from: a */
    public static void m828a(Image image, byte[] bArr) {
        int width = image.getWidth();
        int height = image.getHeight();
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        ByteBuffer buffer2 = planes[1].getBuffer();
        ByteBuffer buffer3 = planes[2].getBuffer();
        int i = height * width;
        int min = Math.min(i, buffer.remaining());
        int min2 = Math.min(i / 2, buffer2.remaining());
        buffer.get(bArr, 0, min);
        buffer3.get(bArr, min, min2);
        byte[] bArr2 = f2191a;
        if (bArr2 == null || bArr2.length < min2) {
            f2191a = new byte[min2];
        }
        buffer2.get(f2191a);
        int i2 = min + 1;
        for (int i3 = 0; i3 < min2; i3 += 2) {
            bArr[i2] = f2191a[i3];
            i2 += 2;
        }
    }
}

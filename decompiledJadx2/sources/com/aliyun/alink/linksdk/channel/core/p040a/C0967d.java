package com.aliyun.alink.linksdk.channel.core.p040a;

import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.id2.Id2Itls;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ITLSOutputStream.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.a.d */
/* loaded from: classes.dex */
public class C0967d extends OutputStream {

    /* renamed from: a */
    private Id2Itls f881a;

    /* renamed from: b */
    private long f882b;

    /* renamed from: c */
    private byte[] f883c;

    public C0967d(Id2Itls id2Itls, long j) {
        this.f881a = null;
        this.f883c = null;
        this.f881a = id2Itls;
        this.f882b = j;
        this.f883c = new byte[1024];
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null) {
            return;
        }
        if (i >= 0) {
            try {
                try {
                    try {
                    } catch (IOException e) {
                        throw e;
                    } catch (Exception e2) {
                        C0969a.m396d("ITLSOutputStream", "itls write exception " + e2);
                    }
                } catch (IndexOutOfBoundsException e3) {
                    e3.printStackTrace();
                }
            } catch (NullPointerException e4) {
                e4.printStackTrace();
            }
            if (i <= bArr.length && i2 >= 0 && (i3 = i + i2) <= bArr.length && i3 >= 0) {
                if (i2 == 0) {
                    return;
                }
                C0969a.m393a("ITLSOutputStream", "b.len=" + bArr.length + ", off=" + i + ", len=" + i2);
                for (int i4 = 0; i4 < i2; i4 += 1024) {
                    int min = Math.min(1024, i2 - i4);
                    System.arraycopy(bArr, i4, this.f883c, 0, min);
                    int itlsWrite = this.f881a.itlsWrite(this.f882b, this.f883c, min, MqttConfigure.itlsWriteTimeout);
                    C0969a.m393a("ITLSOutputStream", "result=" + itlsWrite + ", length=" + min);
                    if (itlsWrite < min) {
                        throw new IOException(String.valueOf(32109), new Throwable("itlsWriteErrorDataLen=" + itlsWrite));
                    }
                }
                super.write(bArr, i, i2);
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }
}

package com.aliyun.alink.linksdk.channel.core.p040a;

import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.id2.Id2Itls;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ITLSInputStream.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.a.b */
/* loaded from: classes.dex */
public class C0965b extends InputStream {

    /* renamed from: a */
    private Id2Itls f870a;

    /* renamed from: b */
    private long f871b;

    /* renamed from: d */
    private byte[] f873d;

    /* renamed from: c */
    private final Object f872c = new Object();

    /* renamed from: e */
    private int f874e = -1;

    /* renamed from: f */
    private int f875f = 0;

    public C0965b(Id2Itls id2Itls, long j) {
        this.f870a = null;
        this.f873d = null;
        this.f870a = id2Itls;
        this.f871b = j;
        this.f873d = new byte[1024];
    }

    @Override // java.io.InputStream
    public int available() {
        return super.available();
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        int i;
        synchronized (this.f872c) {
            if (this.f874e < 0 || this.f874e >= this.f875f - 1) {
                this.f874e = -1;
                this.f875f = 0;
                m390a();
            }
            if (this.f875f > 0) {
                int i2 = this.f874e + 1;
                this.f874e = i2;
                i = this.f873d[i2] & 255;
            } else {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new SocketTimeoutException("NoData");
            }
        }
        return i;
    }

    /* renamed from: a */
    private void m390a() {
        try {
            C0969a.m393a("ITLSInputStream", "read dataLen=" + this.f875f + ", byteIndex=" + this.f874e + ",handleId=" + this.f871b);
            this.f874e = -1;
            this.f875f = 0;
            if (this.f870a != null) {
                this.f875f = this.f870a.itlsRead(this.f871b, this.f873d, 1024, MqttConfigure.itlsReadTimeout);
            }
            C0969a.m393a("ITLSInputStream", "read dataLen=" + this.f875f + ", byteIndex=" + this.f874e + ",handleId=" + this.f871b);
        } catch (Exception e) {
            this.f875f = 0;
            e.printStackTrace();
        }
        if (this.f875f >= 0) {
            return;
        }
        throw new IOException(String.valueOf(32109), new Throwable("itlsReadErrorDataLen=" + this.f875f));
    }
}

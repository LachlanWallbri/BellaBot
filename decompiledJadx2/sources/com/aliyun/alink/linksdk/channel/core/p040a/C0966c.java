package com.aliyun.alink.linksdk.channel.core.p040a;

import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.id2.Id2Itls;
import com.aliyun.alink.linksdk.tools.ALog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ITLSNetworkModule.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.a.c */
/* loaded from: classes.dex */
public class C0966c implements NetworkModule {

    /* renamed from: a */
    private C0964a f876a;

    /* renamed from: b */
    private Id2Itls f877b;

    /* renamed from: c */
    private long f878c = 0;

    /* renamed from: d */
    private boolean f879d = true;

    /* renamed from: e */
    private AtomicBoolean f880e = new AtomicBoolean(false);

    public C0966c(C0964a c0964a) {
        this.f876a = null;
        this.f877b = null;
        this.f876a = c0964a;
        this.f877b = new Id2Itls();
        int i = MqttConfigure.itlsLogLevel;
        int level = (ALog.getLevel() & 255) + 2;
        C0969a.m393a("ITLSNetworkModule", "itlsDebugLevel = " + i + "， jniLevel=" + level);
        this.f877b.setItlsDebugLevel(i);
        this.f877b.setJniDebugLevel(level);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() {
        C0969a.m394b("ITLSNetworkModule", "start");
        this.f879d = false;
        try {
            this.f878c = this.f877b.establishItls(this.f876a.f866a, this.f876a.f867b, this.f876a.f868c, this.f876a.f869d);
        } catch (Exception e) {
            e.printStackTrace();
            this.f879d = true;
        }
        this.f880e.set(true);
        C0969a.m393a("ITLSNetworkModule", "handleId=" + this.f878c);
        if (this.f878c != 0) {
            return;
        }
        C0969a.m396d("ITLSNetworkModule", "establishItls failed.");
        this.f879d = true;
        throw new MqttException(this.f877b.getAlertType());
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() {
        if (this.f879d) {
            throw new IOException("ITLS Channel Closed.");
        }
        return new C0965b(this.f877b, this.f878c);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() {
        if (this.f879d) {
            throw new IOException("ITLS Channel Closed.");
        }
        return new C0967d(this.f877b, this.f878c);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() {
        C0969a.m394b("ITLSNetworkModule", "stop");
        try {
            this.f879d = true;
            if (this.f880e.compareAndSet(true, false)) {
                C0969a.m393a("ITLSNetworkModule", "stop itls destroy.");
                this.f877b.destroyItls(this.f878c);
                this.f880e.set(false);
            } else {
                C0969a.m393a("ITLSNetworkModule", "stop itls already destroyed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "ssl://" + this.f876a.f866a + ":" + this.f876a.f867b;
    }
}

package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send;

import com.aliyun.alink.linksdk.channel.core.base.AError;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttSendResponseRunnable.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.d */
/* loaded from: classes.dex */
public class RunnableC0982d implements Runnable {

    /* renamed from: a */
    private C0980b f940a;

    /* renamed from: b */
    private byte f941b;

    /* renamed from: c */
    private String f942c;

    public RunnableC0982d(C0980b c0980b, byte b, String str) {
        this.f940a = null;
        this.f941b = (byte) 0;
        this.f942c = null;
        this.f940a = c0980b;
        this.f941b = b;
        this.f942c = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0980b c0980b = this.f940a;
        if (c0980b == null) {
            return;
        }
        switch (this.f941b) {
            case 1:
                if (c0980b.getListener() == null) {
                    return;
                }
                this.f940a.getListener().onSuccess(this.f940a.getRequest(), this.f940a.getResponse());
                return;
            case 2:
            case 3:
                if (c0980b.getListener() == null) {
                    return;
                }
                AError aError = new AError();
                if (this.f941b == 3) {
                    aError.setCode(4101);
                } else {
                    aError.setCode(4201);
                }
                aError.setMsg(this.f942c);
                this.f940a.getListener().onFailed(this.f940a.getRequest(), aError);
                return;
            case 4:
                if (c0980b.m441b() == null) {
                    return;
                }
                this.f940a.m441b().onSuccess(((MqttSubscribeRequest) this.f940a.getRequest()).topic);
                return;
            case 5:
            case 6:
                if (c0980b.m441b() == null) {
                    return;
                }
                AError aError2 = new AError();
                if (this.f941b == 3) {
                    aError2.setCode(4101);
                } else {
                    aError2.setCode(4201);
                }
                aError2.setMsg(this.f942c);
                this.f940a.m441b().onFailed(((MqttSubscribeRequest) this.f940a.getRequest()).topic, aError2);
                return;
            default:
                return;
        }
    }
}

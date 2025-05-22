package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a;

import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import java.util.ResourceBundle;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttLogger.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.b */
/* loaded from: classes.dex */
public class C0975b implements Logger {

    /* renamed from: a */
    public static boolean f912a = false;

    /* renamed from: b */
    private String f913b;

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public String formatMessage(String str, Object[] objArr) {
        return str;
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void initialise(ResourceBundle resourceBundle, String str, String str2) {
        C0969a.m393a("MqttPaho", "initialise，loggerId = " + str + " , name = " + str2);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void setResourceName(String str) {
        C0969a.m393a("MqttPaho", "setResourceName(), " + str);
        this.f913b = str;
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public boolean isLoggable(int i) {
        return f912a;
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void severe(String str, String str2, String str3) {
        m412a("severe", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void severe(String str, String str2, String str3, Object[] objArr) {
        m412a("severe", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void severe(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("severe", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void warning(String str, String str2, String str3) {
        m412a("warning", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void warning(String str, String str2, String str3, Object[] objArr) {
        m412a("warning", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void warning(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("warning", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void info(String str, String str2, String str3) {
        m412a("info", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void info(String str, String str2, String str3, Object[] objArr) {
        m412a("info", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void info(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("info", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void config(String str, String str2, String str3) {
        m412a("config", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void config(String str, String str2, String str3, Object[] objArr) {
        m412a("config", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void config(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("config", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void fine(String str, String str2, String str3) {
        m412a("fine", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void fine(String str, String str2, String str3, Object[] objArr) {
        m412a("fine", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void fine(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("fine", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finer(String str, String str2, String str3) {
        m412a("finer", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finer(String str, String str2, String str3, Object[] objArr) {
        m412a("finer", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finer(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("finer", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finest(String str, String str2, String str3) {
        m412a("finest", str, str2, str3, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finest(String str, String str2, String str3, Object[] objArr) {
        m412a("finest", str, str2, str3, objArr, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void finest(String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("finest", str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void log(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a("log " + i, str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void trace(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        m412a(MqttServiceConstants.TRACE_ACTION, str, str2, str3, objArr, th);
    }

    @Override // org.eclipse.paho.client.mqttv3.logging.Logger
    public void dumpTrace() {
        C0969a.m393a("MqttPaho", "dumpTrace()");
    }

    /* renamed from: a */
    private void m412a(String str, String str2, String str3, String str4, Object[] objArr, Throwable th) {
        if (f912a) {
            if ("warning".equals(str)) {
                C0969a.m395c("MqttPaho", str + ", c= " + str2 + " , method = " + str3 + " , msg = " + str4 + ", inserts = " + m411a(objArr) + ", throwable = " + th);
                return;
            }
            if ("info".equals(str) || "config".equals(str)) {
                C0969a.m394b("MqttPaho", str + ", c= " + str2 + " , method = " + str3 + " , msg = " + str4 + ", inserts = " + m411a(objArr) + ", throwable = " + th);
                return;
            }
            C0969a.m393a("MqttPaho", str + ", c= " + str2 + " , method = " + str3 + " , msg = " + str4 + ", inserts = " + m411a(objArr) + ", throwable = " + th);
        }
    }

    /* renamed from: a */
    private String m411a(Object[] objArr) {
        if (objArr == null || objArr.length < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < objArr.length; i++) {
            stringBuffer.append(String.valueOf(objArr[i]));
            if (i != objArr.length - 1 && stringBuffer.length() > 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }
}

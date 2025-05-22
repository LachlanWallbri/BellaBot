package com.aliyun.alink.linksdk.channel.gateway.p043a;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: GatewayRequest.java */
/* renamed from: com.aliyun.alink.linksdk.channel.gateway.a.b */
/* loaded from: classes.dex */
public class C0985b extends MqttPublishRequest {

    /* renamed from: a */
    private static AtomicLong f987a = new AtomicLong();

    /* renamed from: b */
    private String f988b;

    /* renamed from: c */
    private String f989c = "1.0";

    /* renamed from: d */
    private String f990d;

    /* renamed from: e */
    private Object f991e;

    public C0985b(boolean z, PersistentConnectConfig persistentConnectConfig, String str, String str2, Map<String, Object> map, Object obj) {
        if (!TextUtils.isEmpty(str) && !str.startsWith("/sys/") && !str.startsWith("/ota/") && persistentConnectConfig != null) {
            this.topic = "/sys/" + persistentConnectConfig.productKey + "/" + persistentConnectConfig.deviceName + "/" + str;
            this.topic = this.topic.replace("//", "/");
        } else {
            this.topic = str;
        }
        this.f990d = str2;
        this.isRPC = z;
        this.replyTopic = this.topic + TmpConstant.URI_TOPIC_REPLY_POST;
        if (obj == null) {
            this.f991e = new HashMap();
        } else {
            this.f991e = obj;
        }
        if (map != null && map.containsKey(MqttServiceConstants.QOS)) {
            try {
                this.qos = Integer.parseInt(String.valueOf(map.get(MqttServiceConstants.QOS)));
            } catch (Exception e) {
                e.printStackTrace();
                this.qos = 0;
            }
        }
        String str3 = f987a.incrementAndGet() + "";
        this.f988b = str3;
        this.msgId = str3;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", (Object) this.f988b);
        jSONObject.put("version", (Object) this.f989c);
        jSONObject.put("params", obj);
        jSONObject.put("method", (Object) str2);
        this.payloadObj = jSONObject.toJSONString();
    }
}

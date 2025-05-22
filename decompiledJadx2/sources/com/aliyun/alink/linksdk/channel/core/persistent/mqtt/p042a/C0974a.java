package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttAlinkProtocolHelper.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.a */
/* loaded from: classes.dex */
public class C0974a {
    /* renamed from: a */
    public static String m410a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSONObject.parseObject(str).getString("id");
    }
}

package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a;

import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: TrackUtils.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.d */
/* loaded from: classes.dex */
public class C0977d {
    /* renamed from: a */
    public static void m419a(String str, Map<String, String> map) {
        ALog.m479d("TrackUtils", "sendEvent() called with: eventName = [" + str + "], trackDataMap = [" + map + "]");
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    HashMap<String, String> m418a = m418a(map);
                    map.clear();
                    AUserTrack.record(str, m418a);
                    return;
                }
            } catch (Exception unused) {
                ALog.m484w("TrackUtils", "sendEvent AUserTrack.record failed.");
                return;
            }
        }
        ALog.m479d("TrackUtils", "sendEvent ignore, data empty.");
    }

    /* renamed from: a */
    private static HashMap<String, String> m418a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.putAll(map);
        return hashMap;
    }
}

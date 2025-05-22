package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p033b;

import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ConnectMgr.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.b.a */
/* loaded from: classes.dex */
public class C0923a {

    /* renamed from: a */
    private Map<String, IPalConnect> f723a = new ConcurrentHashMap();

    /* renamed from: a */
    public Map<String, IPalConnect> m345a() {
        return this.f723a;
    }

    /* renamed from: a */
    public void m347a(String str, IPalConnect iPalConnect) {
        this.f723a.put(str, iPalConnect);
    }

    /* renamed from: a */
    public void m346a(String str) {
        this.f723a.remove(str);
    }

    /* renamed from: b */
    public IPalConnect m348b(String str) {
        return this.f723a.get(str);
    }
}

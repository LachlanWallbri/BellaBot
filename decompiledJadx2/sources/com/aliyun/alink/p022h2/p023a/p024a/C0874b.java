package com.aliyun.alink.p022h2.p023a.p024a;

import com.aliyun.alink.p022h2.api.IAuthCallback;
import com.aliyun.alink.p022h2.p023a.InterfaceC0872a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CommonAuthHandler.java */
/* renamed from: com.aliyun.alink.h2.a.a.b */
/* loaded from: classes.dex */
public class C0874b implements InterfaceC0872a {

    /* renamed from: a */
    private Map<String, String> f483a = new HashMap();

    /* renamed from: b */
    private IAuthCallback f484b;

    public C0874b(Map<String, String> map, IAuthCallback iAuthCallback) {
        if (map != null && map.size() > 0) {
            this.f483a.putAll(map);
        }
        this.f484b = iAuthCallback;
    }

    @Override // com.aliyun.alink.p022h2.p023a.InterfaceC0872a
    /* renamed from: a */
    public Map<String, String> mo193a() {
        try {
            if (this.f484b != null) {
                this.f483a.putAll(this.f484b.updateAuthData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.f483a;
    }
}

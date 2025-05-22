package com.aliyun.alink.dm.coap;

import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAP;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.resources.AlcsCoAPResource;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: AlcsCoAPService.java */
/* renamed from: com.aliyun.alink.dm.coap.a */
/* loaded from: classes.dex */
public class C0848a {

    /* renamed from: b */
    private AlcsCoAP f362b;

    /* renamed from: a */
    private long f361a = -1;

    /* renamed from: c */
    private AlcsCoAPServiceStatus f363c = AlcsCoAPServiceStatus.IDLE;

    public C0848a() {
        this.f362b = null;
        this.f362b = new AlcsCoAP();
        this.f362b.setLogLevel((ALog.getLevel() & 65535) + 2);
    }

    /* renamed from: a */
    public AlcsCoAPServiceStatus m102a() {
        return this.f363c;
    }

    /* renamed from: a */
    public void m103a(AlcsCoAPContext alcsCoAPContext, AlcsCoAPResource alcsCoAPResource) {
        try {
            this.f361a = this.f362b.createCoAPContext(alcsCoAPContext, alcsCoAPResource);
            this.f363c = AlcsCoAPServiceStatus.INITED;
            C0859a.m131a("AlcsCoAPService", "initCoAPService contexId=" + this.f361a);
        } catch (Exception e) {
            C0859a.m134c("AlcsCoAPService", "initCoAPService createCoAPContext exception." + e);
        }
    }

    /* renamed from: b */
    public void m105b() {
        try {
            this.f362b.alcsStart(this.f361a);
            this.f363c = AlcsCoAPServiceStatus.STARTED;
        } catch (Exception e) {
            C0859a.m134c("AlcsCoAPService", "startCoAPService alcsStart exception." + e);
        }
    }

    /* renamed from: a */
    public boolean m104a(AlcsCoAPResponse alcsCoAPResponse) {
        try {
            return this.f362b.sendResponse(this.f361a, alcsCoAPResponse);
        } catch (Exception e) {
            C0859a.m134c("AlcsCoAPService", "sendResponse sendAlcsResponse exception." + e);
            return false;
        }
    }
}

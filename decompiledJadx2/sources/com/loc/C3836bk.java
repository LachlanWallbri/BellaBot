package com.loc;

/* compiled from: DownloadManager.java */
/* renamed from: com.loc.bk */
/* loaded from: classes4.dex */
public final class C3836bk {

    /* renamed from: a */
    private C3837bl f3681a;

    /* renamed from: b */
    private AbstractC3839bn f3682b;

    /* compiled from: DownloadManager.java */
    /* renamed from: com.loc.bk$a */
    /* loaded from: classes4.dex */
    public interface a {
        /* renamed from: a */
        void mo2470a(byte[] bArr, long j);

        /* renamed from: b */
        void mo2471b();

        /* renamed from: c */
        void mo2472c();

        /* renamed from: d */
        void mo2473d();
    }

    public C3836bk(AbstractC3839bn abstractC3839bn) {
        this(abstractC3839bn, (byte) 0);
    }

    private C3836bk(AbstractC3839bn abstractC3839bn, byte b) {
        this.f3682b = abstractC3839bn;
        this.f3681a = new C3837bl(this.f3682b.f3696c, this.f3682b.f3697d, abstractC3839bn.f3698e == null ? null : abstractC3839bn.f3698e);
        this.f3681a.m2624b();
        this.f3681a.m2622a();
    }

    /* renamed from: a */
    public final void m2615a(a aVar) {
        this.f3681a.m2623a(this.f3682b.mo2488b(), this.f3682b.mo2487a(), this.f3682b.mo2489c(), aVar);
    }
}

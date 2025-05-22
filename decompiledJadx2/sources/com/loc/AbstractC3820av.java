package com.loc;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseClassLoader.java */
/* renamed from: com.loc.av */
/* loaded from: classes4.dex */
public abstract class AbstractC3820av extends ClassLoader {

    /* renamed from: a */
    protected final Context f3597a;

    /* renamed from: b */
    protected final Map<String, Class<?>> f3598b;

    /* renamed from: c */
    protected DexFile f3599c;

    /* renamed from: d */
    volatile boolean f3600d;

    /* renamed from: e */
    protected C3893s f3601e;

    /* renamed from: f */
    protected String f3602f;

    public AbstractC3820av(Context context, C3893s c3893s) {
        super(context.getClassLoader());
        this.f3598b = new HashMap();
        this.f3599c = null;
        this.f3600d = true;
        this.f3597a = context;
        this.f3601e = c3893s;
    }

    /* renamed from: a */
    public final boolean m2483a() {
        return this.f3599c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m2484b() {
        try {
            synchronized (this.f3598b) {
                this.f3598b.clear();
            }
            if (this.f3599c != null) {
                this.f3599c.close();
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "BaseClassLoader", "releaseDexFile()");
        }
    }
}

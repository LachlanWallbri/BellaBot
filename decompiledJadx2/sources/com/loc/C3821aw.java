package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ClassLoaderFactory.java */
/* renamed from: com.loc.aw */
/* loaded from: classes4.dex */
public final class C3821aw {

    /* renamed from: a */
    private static final C3821aw f3603a = new C3821aw();

    /* renamed from: b */
    private final Map<String, AbstractC3820av> f3604b = new HashMap();

    private C3821aw() {
    }

    /* renamed from: a */
    public static C3821aw m2485a() {
        return f3603a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized AbstractC3820av m2486a(Context context, C3893s c3893s) throws Exception {
        boolean z;
        AbstractC3820av abstractC3820av;
        if (c3893s != null) {
            try {
                if (!TextUtils.isEmpty(c3893s.m3208b()) && !TextUtils.isEmpty(c3893s.m3206a())) {
                    z = true;
                    if (z || context == null) {
                        throw new Exception("sdkInfo or context referance is null");
                    }
                    String m3206a = c3893s.m3206a();
                    abstractC3820av = this.f3604b.get(m3206a);
                    if (abstractC3820av == null) {
                        try {
                            C3824az c3824az = new C3824az(context.getApplicationContext(), c3893s);
                            try {
                                this.f3604b.put(m3206a, c3824az);
                                C3826ba.m2515a(context, c3893s);
                            } catch (Throwable unused) {
                            }
                            abstractC3820av = c3824az;
                        } catch (Throwable unused2) {
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        z = false;
        if (z) {
        }
        throw new Exception("sdkInfo or context referance is null");
        return abstractC3820av;
    }
}

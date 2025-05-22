package com.loc;

import android.text.TextUtils;
import java.net.URLConnection;
import java.util.Map;

/* compiled from: BaseNetManager.java */
/* renamed from: com.loc.bi */
/* loaded from: classes4.dex */
public final class C3834bi {

    /* renamed from: a */
    private static C3834bi f3678a;

    /* compiled from: BaseNetManager.java */
    /* renamed from: com.loc.bi$a */
    /* loaded from: classes4.dex */
    public interface a {
        /* renamed from: a */
        URLConnection m2603a();
    }

    /* renamed from: a */
    public static C3834bi m2600a() {
        if (f3678a == null) {
            f3678a = new C3834bi();
        }
        return f3678a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0071 A[Catch: all -> 0x0082, j -> 0x0084, TryCatch #2 {j -> 0x0084, all -> 0x0082, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0014, B:10:0x001c, B:12:0x002b, B:15:0x002f, B:17:0x003a, B:18:0x0056, B:20:0x0060, B:22:0x0075, B:25:0x0063, B:27:0x0071, B:28:0x0035, B:29:0x001a, B:30:0x007a, B:31:0x0081, B:32:0x0086, B:33:0x008d), top: B:1:0x0000 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C3840bo m2601a(AbstractC3839bn abstractC3839bn, boolean z) throws C3884j {
        String mo2488b;
        byte[] mo2611d;
        String m2617a;
        Map<String, String> mo2489c;
        try {
            if (abstractC3839bn == null) {
                throw new C3884j("requeust is null");
            }
            if (abstractC3839bn.mo2488b() == null || "".equals(abstractC3839bn.mo2488b())) {
                throw new C3884j("request url is empty");
            }
            C3837bl c3837bl = new C3837bl(abstractC3839bn.f3696c, abstractC3839bn.f3697d, abstractC3839bn.f3698e == null ? null : abstractC3839bn.f3698e, z);
            byte[] mo2611d2 = abstractC3839bn.mo2611d();
            if (mo2611d2 != null && mo2611d2.length != 0 && (mo2489c = abstractC3839bn.mo2489c()) != null) {
                String m2617a2 = C3837bl.m2617a(mo2489c);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(abstractC3839bn.mo2488b());
                stringBuffer.append("?");
                stringBuffer.append(m2617a2);
                mo2488b = stringBuffer.toString();
                Map<String, String> mo2487a = abstractC3839bn.mo2487a();
                mo2611d = abstractC3839bn.mo2611d();
                if (mo2611d != null || mo2611d.length == 0) {
                    m2617a = C3837bl.m2617a(abstractC3839bn.mo2489c());
                    if (!TextUtils.isEmpty(m2617a)) {
                        mo2611d = C3894t.m3232a(m2617a);
                    }
                }
                return c3837bl.m2621a(mo2488b, mo2487a, mo2611d);
            }
            mo2488b = abstractC3839bn.mo2488b();
            Map<String, String> mo2487a2 = abstractC3839bn.mo2487a();
            mo2611d = abstractC3839bn.mo2611d();
            if (mo2611d != null) {
            }
            m2617a = C3837bl.m2617a(abstractC3839bn.mo2489c());
            if (!TextUtils.isEmpty(m2617a)) {
            }
            return c3837bl.m2621a(mo2488b, mo2487a2, mo2611d);
        } catch (C3884j e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new C3884j("未知的错误");
        }
    }

    /* renamed from: a */
    public static byte[] m2602a(AbstractC3839bn abstractC3839bn) throws C3884j {
        try {
            C3840bo m2601a = m2601a(abstractC3839bn, false);
            if (m2601a != null) {
                return m2601a.f3699a;
            }
            return null;
        } catch (C3884j e) {
            throw e;
        } catch (Throwable th) {
            C3897w.m3249a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new C3884j("未知的错误");
        }
    }
}

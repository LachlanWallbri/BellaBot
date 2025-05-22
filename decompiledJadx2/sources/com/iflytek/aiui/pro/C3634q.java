package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.q */
/* loaded from: classes.dex */
public class C3634q {
    /* renamed from: a */
    public static <T> T m1484a(Context context, C3626m c3626m, String str, Class[] clsArr, Object[] objArr) throws Exception {
        C3638s c3638s;
        Class<?> loadClass;
        try {
            c3638s = C3638s.m1522a(context, c3626m, C3636r.m1504a(context, c3626m.f2579a, c3626m.f2580b), C3636r.m1515d(context), null, context.getClassLoader());
        } catch (Throwable th) {
            th.printStackTrace();
            c3638s = null;
        }
        if (c3638s != null) {
            try {
                if (c3638s.m1534a() && c3638s.f2640a && (loadClass = c3638s.loadClass(str)) != null) {
                    return (T) loadClass.getConstructor(clsArr).newInstance(objArr);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        throw new Exception("获取对象错误");
    }

    /* renamed from: a */
    public static void m1485a(Context context, C3632p c3632p, C3626m c3626m) {
        if (c3632p == null || TextUtils.isEmpty(c3632p.f2597a) || TextUtils.isEmpty(c3632p.f2598b) || TextUtils.isEmpty(c3632p.f2599c)) {
            return;
        }
        new C3630o(context, c3632p, c3626m).m1457a();
    }
}

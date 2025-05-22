package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.C5888d;
import com.tencent.bugly.proguard.C5940x;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class InnerApi {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C5940x.m3825e("post u3d fail args null", new Object[0]);
        }
        C5940x.m3818a("post u3d crash %s %s", str, str2);
        C5888d.m3572a(Thread.currentThread(), 4, str, str2, str3, null);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C5940x.m3825e("post cocos2d-x fail args null", new Object[0]);
        } else if (i != 5 && i != 6) {
            C5940x.m3825e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        } else {
            C5940x.m3818a("post cocos2d-x crash %s %s", str, str2);
            C5888d.m3572a(Thread.currentThread(), i, str, str2, str3, null);
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            C5940x.m3825e("post h5 fail args null", new Object[0]);
        } else {
            C5940x.m3818a("post h5 crash %s %s", str, str2);
            C5888d.m3572a(thread, 8, str, str2, str3, map);
        }
    }
}

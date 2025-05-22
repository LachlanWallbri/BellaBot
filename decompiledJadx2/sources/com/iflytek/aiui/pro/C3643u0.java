package com.iflytek.aiui.pro;

import android.content.Context;
import java.io.File;

/* renamed from: com.iflytek.aiui.pro.u0 */
/* loaded from: classes4.dex */
public class C3643u0 {
    /* renamed from: a */
    public static String m1550a(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str = absolutePath + "msclib/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }
}

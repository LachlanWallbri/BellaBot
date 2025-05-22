package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.au */
/* loaded from: classes.dex */
public class C3591au {

    /* renamed from: com.iflytek.aiui.pro.au$a */
    /* loaded from: classes.dex */
    public enum a {
        assets,
        res,
        path
    }

    /* renamed from: a */
    public static String m1071a(Context context, a aVar, String str) {
        return aVar == a.path ? m1072a(str) : m1073b(context, aVar, str);
    }

    /* renamed from: a */
    private static String m1072a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return "fo|" + str + "|0|" + file.length();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v11, types: [android.content.res.AssetFileDescriptor] */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m1073b(Context context, a aVar, String str) {
        Exception e;
        long startOffset;
        String str2 = null;
        str2 = null;
        AssetFileDescriptor assetFileDescriptor = null;
        str2 = null;
        if (!TextUtils.isEmpty(str) && context != 0) {
            String packageResourcePath = context.getPackageResourcePath();
            try {
                try {
                } catch (Exception e2) {
                    e = e2;
                    context = 0;
                } catch (Throwable th) {
                    th = th;
                    if (assetFileDescriptor != null) {
                    }
                    throw th;
                }
                try {
                    if (aVar == a.assets) {
                        AssetFileDescriptor openFd = context.getAssets().openFd(str);
                        startOffset = openFd.getStartOffset();
                        context = openFd;
                    } else {
                        AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(Integer.valueOf(str).intValue());
                        startOffset = openRawResourceFd.getStartOffset();
                        context = openRawResourceFd;
                    }
                    str2 = "fo|" + packageResourcePath + "|" + startOffset + "|" + context.getLength();
                    if (context != 0) {
                        try {
                            context.close();
                            return str2;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return str2;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    if (context != 0) {
                        try {
                            context.close();
                            return null;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return null;
                        }
                    }
                    return str2;
                }
            } catch (Throwable th2) {
                assetFileDescriptor = context;
                th = th2;
                if (assetFileDescriptor != null) {
                    try {
                        assetFileDescriptor.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return str2;
    }
}

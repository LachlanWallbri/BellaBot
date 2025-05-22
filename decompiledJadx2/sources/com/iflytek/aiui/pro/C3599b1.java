package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import java.io.File;

/* renamed from: com.iflytek.aiui.pro.b1 */
/* loaded from: classes4.dex */
public class C3599b1 {

    /* renamed from: com.iflytek.aiui.pro.b1$a */
    /* loaded from: classes4.dex */
    public enum a {
        assets,
        res,
        path
    }

    /* renamed from: a */
    public static String m1120a(Context context, a aVar, String str) {
        return aVar == a.path ? m1121b(str) : m1122c(context, aVar, str);
    }

    /* renamed from: b */
    private static String m1121b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return "fo|" + str + "|0|" + file.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0056, code lost:
    
        if (r7 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0063, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0067, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        r7.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0061, code lost:
    
        if (r7 == null) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v8, types: [android.content.res.AssetFileDescriptor] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m1122c(Context context, a aVar, String str) {
        Exception e;
        AssetFileDescriptor assetFileDescriptor;
        AssetFileDescriptor assetFileDescriptor2 = null;
        r2 = null;
        String str2 = null;
        if (TextUtils.isEmpty(str) || context == 0) {
            return null;
        }
        String packageResourcePath = context.getPackageResourcePath();
        try {
            try {
                if (aVar == a.assets) {
                    assetFileDescriptor = context.getAssets().openFd(str);
                } else {
                    assetFileDescriptor = context.getResources().openRawResourceFd(Integer.valueOf(str).intValue());
                }
            } catch (Exception e2) {
                e = e2;
                assetFileDescriptor = null;
            } catch (Throwable th) {
                th = th;
                if (assetFileDescriptor2 != null) {
                }
                throw th;
            }
            try {
                long startOffset = assetFileDescriptor.getStartOffset();
                str2 = "fo|" + packageResourcePath + "|" + startOffset + "|" + assetFileDescriptor.getLength();
                context = assetFileDescriptor;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                context = assetFileDescriptor;
            }
        } catch (Throwable th2) {
            assetFileDescriptor2 = context;
            th = th2;
            if (assetFileDescriptor2 != null) {
                try {
                    assetFileDescriptor2.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return str2;
    }
}

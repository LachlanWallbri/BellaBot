package com.iflytek.cloud.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.File;

/* loaded from: classes3.dex */
public class ResourceUtil {
    public static final String ASR_RES_PATH = "asr_res_path";
    public static final String ENGINE_DESTROY = "engine_destroy";
    public static final String ENGINE_START = "engine_start";
    public static final String GRM_BUILD_PATH = "grm_build_path";
    public static final String IVW_RES_PATH = "ivw_res_path";
    public static final String TTS_RES_PATH = "tts_res_path";

    /* loaded from: classes3.dex */
    public enum RESOURCE_TYPE {
        assets,
        res,
        path
    }

    public static String generateResourcePath(Context context, RESOURCE_TYPE resource_type, String str) {
        if (resource_type == RESOURCE_TYPE.path) {
            return m2302a(str);
        }
        return m2301a(context, resource_type, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v10, types: [android.content.res.AssetFileDescriptor] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m2301a(Context context, RESOURCE_TYPE resource_type, String str) {
        long startOffset;
        long length;
        String str2 = null;
        if (TextUtils.isEmpty(str) || context == 0) {
            return null;
        }
        String packageResourcePath = context.getPackageResourcePath();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
            } catch (Exception e2) {
                e = e2;
                context = 0;
            } catch (Throwable th) {
                th = th;
                context = 0;
                if (context != 0) {
                }
                throw th;
            }
            try {
                if (resource_type == RESOURCE_TYPE.assets) {
                    AssetFileDescriptor openFd = context.getAssets().openFd(str);
                    startOffset = openFd.getStartOffset();
                    length = openFd.getLength();
                    context = openFd;
                } else {
                    AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(Integer.valueOf(str).intValue());
                    startOffset = openRawResourceFd.getStartOffset();
                    length = openRawResourceFd.getLength();
                    context = openRawResourceFd;
                }
                str2 = "fo|" + packageResourcePath + "|" + startOffset + "|" + length;
            } catch (Exception e3) {
                e = e3;
                DebugLog.LogE(e);
                if (context != 0) {
                    context.close();
                }
                return str2;
            }
            if (context != 0) {
                context.close();
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (context != 0) {
                try {
                    context.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static String m2302a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return "fo|" + str + "|0|" + file.length();
    }
}

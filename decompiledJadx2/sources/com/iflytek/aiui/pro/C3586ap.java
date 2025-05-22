package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.utils.log.DebugLog;
import com.iflytek.location.LocationListener;
import com.iflytek.location.result.LocResult;
import com.iflytek.location.result.NetLocResult;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ap */
/* loaded from: classes.dex */
public class C3586ap {

    /* renamed from: com.iflytek.aiui.pro.ap$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements LocationListener {
        AnonymousClass1() {
        }

        @Override // com.iflytek.location.LocationListener
        public void onResult(LocResult locResult) {
            if (locResult != null) {
                NetLocResult netLocResult = (NetLocResult) locResult;
                C3586ap.this.a((float) netLocResult.getLat(), (float) netLocResult.getLon());
                DebugLog.LogD("use PosLocator get NetLocation description: " + netLocResult.getAoiname() + " lat: " + netLocResult.getLat() + ", lng: " + netLocResult.getLon());
            }
        }
    }

    /* renamed from: a */
    public static String m1053a(Context context) {
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

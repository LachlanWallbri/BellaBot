package com.iflytek.idata.extension;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.thirdparty.C3722bg;
import com.iflytek.cloud.thirdparty.C3723bh;
import com.iflytek.idata.extension.C3752b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* renamed from: com.iflytek.idata.extension.a */
/* loaded from: classes3.dex */
public class RunnableC3751a implements Runnable {

    /* renamed from: a */
    protected Context f3498a;

    /* renamed from: b */
    private C3752b.a f3499b = new C3752b.a() { // from class: com.iflytek.idata.extension.a.1
        @Override // com.iflytek.idata.extension.C3752b.a
        /* renamed from: a */
        public void mo2328a(C3752b c3752b, byte[] bArr) {
            if (bArr != null) {
                try {
                    RunnableC3751a.this.m2325a(new JSONObject(new String(bArr, "utf-8")));
                } catch (Exception e) {
                    mo2329a(e);
                }
            }
        }

        @Override // com.iflytek.idata.extension.C3752b.a
        /* renamed from: a */
        public void mo2329a(Exception exc) {
            C3723bh.m2047b("Collector", "update error:" + exc);
        }
    };

    /* renamed from: c */
    private boolean f3500c;

    public RunnableC3751a(Context context, boolean z) {
        this.f3498a = context;
        this.f3500c = z;
    }

    /* renamed from: a */
    private void m2322a() {
        try {
            SystemClock.sleep(10000L);
            if (m2327b() && C3722bg.m2043a(this.f3498a)) {
                C3752b c3752b = new C3752b();
                c3752b.m2342b(20000);
                c3752b.m2338a(1);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appid", IFlyCollectorExt.m2309a(this.f3498a).m2319d());
                jSONObject.put("ver", IFlyCollectorExt.m2309a(this.f3498a).m2314a());
                jSONObject.put("impl", 2);
                jSONObject.put("pkg", this.f3498a.getPackageName());
                c3752b.m2340a("https://logconf.iflytek.com/hotupdate", null, jSONObject.toString().getBytes("utf-8"));
                c3752b.m2343b(this.f3499b);
            }
        } catch (Throwable unused) {
            C3723bh.m2048c("Collector", "exception occur while update");
        }
    }

    /* renamed from: a */
    private static void m2324a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2325a(JSONObject jSONObject) {
        if ("yes".equalsIgnoreCase(jSONObject.optString("update"))) {
            String optString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
            if (!TextUtils.isEmpty(optString)) {
                m2326a(Base64.decode(optString, 0));
            }
        } else {
            C3723bh.m2046a("Collector", "no need to update");
        }
        SharedPreferences.Editor edit = C3722bg.m2045b(this.f3498a).edit();
        edit.putLong("last_check_time", System.currentTimeMillis());
        edit.apply();
    }

    /* renamed from: a */
    private void m2326a(byte[] bArr) {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        IFlyCollectorExt m2309a = IFlyCollectorExt.m2309a(this.f3498a);
        m2309a.m2318c();
        File file = new File(m2309a.m2316b(), C3722bg.m2042a());
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            bufferedOutputStream.write(bArr);
            m2324a(bufferedOutputStream);
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                th.printStackTrace();
                m2324a(bufferedOutputStream2);
                m2324a(fileOutputStream);
            } catch (Throwable th4) {
                m2324a(bufferedOutputStream2);
                m2324a(fileOutputStream);
                throw th4;
            }
        }
        m2324a(fileOutputStream);
    }

    /* renamed from: b */
    private boolean m2327b() {
        SharedPreferences m2045b;
        if (this.f3500c || (m2045b = C3722bg.m2045b(this.f3498a)) == null) {
            return true;
        }
        return System.currentTimeMillis() - m2045b.getLong("last_check_time", 0L) > 604800000;
    }

    @Override // java.lang.Runnable
    public void run() {
        m2322a();
    }
}

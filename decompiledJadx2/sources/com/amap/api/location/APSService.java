package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.loc.C3819au;
import com.loc.C3874cv;
import com.loc.C3879e;
import com.loc.C3880f;

/* loaded from: classes.dex */
public class APSService extends Service {

    /* renamed from: a */
    APSServiceBase f1122a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            return this.f1122a.onBind(intent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSService", "onBind");
            return null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        onCreate(this);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:(2:1|2)|(1:4)(8:18|(1:20)|7|8|(1:10)|11|12|13)|5|7|8|(0)|11|12|13) */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
    
        com.loc.C3880f.m3097a(r8, "APSService", "onCreate");
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0035 A[Catch: all -> 0x0042, TryCatch #1 {all -> 0x0042, blocks: (B:8:0x0031, B:10:0x0035, B:11:0x003c), top: B:7:0x0031 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Context context) {
        APSServiceBase c3879e;
        if (!C3874cv.m2950d(context)) {
            if (this.f1122a == null) {
                c3879e = new C3879e(this);
            }
            if (this.f1122a == null) {
                this.f1122a = new C3879e(this);
            }
            this.f1122a.onCreate();
            super.onCreate();
        }
        c3879e = (APSServiceBase) C3819au.m2476a(context, C3880f.m3091a("loc"), "com.amap.api.location.APSServiceWrapper", C3879e.class, new Class[]{Context.class}, new Object[]{context});
        this.f1122a = c3879e;
        if (this.f1122a == null) {
        }
        this.f1122a.onCreate();
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            this.f1122a.onDestroy();
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.f1122a.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSService", "onStartCommand");
            return super.onStartCommand(intent, i, i2);
        }
    }
}

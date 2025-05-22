package com.slamtec.slamware.bridge;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class BridgeIntentService extends IntentService {
    private static final String TAG = "BridgeIntentService";

    public BridgeIntentService() {
        super(TAG);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        BridgeUtil.startTcpBridge("fileName");
        while (true) {
            Log.d(TAG, "onHandleIntent: id " + Thread.currentThread().getId());
            SystemClock.sleep(2000L);
        }
    }
}

package com.slamtec.slamware.bridge;

import android.content.Context;
import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class BridgeUtil {
    public static String CONFIG_FILE_NAME = "CONFIG_FILE_NAME";
    private static Context mContext;

    public static native void setMovementEstimation(MovementEstimation movementEstimation);

    public static native void startTcpBridge(String str);

    static {
        System.loadLibrary("slamware_bridge");
    }

    public static void init(Context context) {
        mContext = context;
        mContext.startService(new Intent(mContext, (Class<?>) BridgeIntentService.class));
    }
}

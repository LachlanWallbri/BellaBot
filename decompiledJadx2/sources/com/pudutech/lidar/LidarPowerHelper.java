package com.pudutech.lidar;

import com.pudutech.base.Pdlog;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class LidarPowerHelper {
    private static String TAG = "LidarPowerHelper";
    private static boolean isPowerOn = false;
    private static boolean isSupportPowerControl = false;

    public static void setVersionForSupportPowerControl(int i, int i2) {
        if (i == 1 && i2 > 2) {
            isSupportPowerControl = true;
        } else if (i == 2 && i2 == 0) {
            isSupportPowerControl = false;
        } else {
            isSupportPowerControl = i > 1;
        }
        Pdlog.m3273d(TAG, "set lidar powerControl=" + isSupportPowerControl + " by major=" + i + " min=" + i2);
    }

    public static boolean checkSupportPowerOn() {
        return isSupportPowerControl;
    }

    public static void markPower(boolean z) {
        Pdlog.m3273d(TAG, "markerPower " + z);
        isPowerOn = z;
    }

    public static boolean checkPowerOn() {
        return isPowerOn;
    }
}

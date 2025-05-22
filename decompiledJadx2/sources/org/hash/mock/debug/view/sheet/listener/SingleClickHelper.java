package org.hash.mock.debug.view.sheet.listener;

import android.os.SystemClock;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SingleClickHelper {
    private static long L_CLICK_INTERVAL = 400;
    private long preClickTime;

    public boolean clickEnable() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.preClickTime <= L_CLICK_INTERVAL) {
            return false;
        }
        this.preClickTime = elapsedRealtime;
        return true;
    }
}

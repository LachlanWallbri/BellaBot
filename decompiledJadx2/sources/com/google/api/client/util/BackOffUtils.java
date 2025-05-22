package com.google.api.client.util;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class BackOffUtils {
    public static boolean next(Sleeper sleeper, BackOff backOff) throws InterruptedException, IOException {
        long nextBackOffMillis = backOff.nextBackOffMillis();
        if (nextBackOffMillis == -1) {
            return false;
        }
        sleeper.sleep(nextBackOffMillis);
        return true;
    }

    private BackOffUtils() {
    }
}

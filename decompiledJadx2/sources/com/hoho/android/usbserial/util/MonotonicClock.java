package com.hoho.android.usbserial.util;

/* loaded from: classes4.dex */
public final class MonotonicClock {
    private static final long NS_PER_MS = 1000000;

    private MonotonicClock() {
    }

    public static long millis() {
        return System.nanoTime() / NS_PER_MS;
    }
}

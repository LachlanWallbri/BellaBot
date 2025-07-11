package com.airbnb.lottie;

import androidx.core.os.TraceCompat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* renamed from: com.airbnb.lottie.L */
/* loaded from: classes.dex */
public class C0750L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static int depthPastMaxDepth = 0;
    private static String[] sections = null;
    private static long[] startTimeNs = null;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    public static void setTraceEnabled(boolean z) {
        if (traceEnabled == z) {
            return;
        }
        traceEnabled = z;
        if (traceEnabled) {
            sections = new String[20];
            startTimeNs = new long[20];
        }
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i = traceDepth;
            if (i == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i] = str;
            startTimeNs[i] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return 0.0f;
        }
        if (!traceEnabled) {
            return 0.0f;
        }
        traceDepth--;
        int i2 = traceDepth;
        if (i2 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (!str.equals(sections[i2])) {
            throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + sections[traceDepth] + ".");
        }
        TraceCompat.endSection();
        return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
    }
}

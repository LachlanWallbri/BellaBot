package com.aliyun.alink.linksdk.alcs.lpbs.data;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalProbeResult {
    public static final int PAL_PROBE_ERROR_RSPERROR = 2;
    public static final int PAL_PROBE_ERROR_TIMEOUT = 1;
    public static final int PAL_PROBE_OK = 0;
    public int probeResult;

    public PalProbeResult(int i) {
        this.probeResult = i;
    }
}

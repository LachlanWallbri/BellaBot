package com.pudutech.event_tracking;

import kotlin.Metadata;

/* compiled from: PuduEventTrackingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000e¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/event_tracking/WorkRuler;", "", "()V", "dataSaveTime", "", "getDataSaveTime", "()I", "setDataSaveTime", "(I)V", "initialDelay", "", "getInitialDelay", "()J", "setInitialDelay", "(J)V", "maxNum", "getMaxNum", "setMaxNum", "maxUnUploadCount", "getMaxUnUploadCount", "setMaxUnUploadCount", "minAvailableSpace", "", "getMinAvailableSpace", "()D", "setMinAvailableSpace", "(D)V", "repeatInterval", "getRepeatInterval", "setRepeatInterval", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WorkRuler {
    private long repeatInterval = 15000;
    private long initialDelay = 10000;
    private int maxUnUploadCount = 30;
    private long maxNum = 250000;
    private int dataSaveTime = 604800000;
    private double minAvailableSpace = 0.1d;

    public final long getRepeatInterval() {
        return this.repeatInterval;
    }

    public final void setRepeatInterval(long j) {
        this.repeatInterval = j;
    }

    public final long getInitialDelay() {
        return this.initialDelay;
    }

    public final void setInitialDelay(long j) {
        this.initialDelay = j;
    }

    public final int getMaxUnUploadCount() {
        return this.maxUnUploadCount;
    }

    public final void setMaxUnUploadCount(int i) {
        this.maxUnUploadCount = i;
    }

    public final long getMaxNum() {
        return this.maxNum;
    }

    public final void setMaxNum(long j) {
        this.maxNum = j;
    }

    public final int getDataSaveTime() {
        return this.dataSaveTime;
    }

    public final void setDataSaveTime(int i) {
        this.dataSaveTime = i;
    }

    public final double getMinAvailableSpace() {
        return this.minAvailableSpace;
    }

    public final void setMinAvailableSpace(double d) {
        this.minAvailableSpace = d;
    }
}

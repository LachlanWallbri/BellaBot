package com.pudutech.bumblebee.robot_ui.bean;

import kotlin.Metadata;

/* compiled from: CallHistoryData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/CallHistoryData;", "", "()V", "completeTime", "", "getCompleteTime", "()J", "setCompleteTime", "(J)V", "destination", "", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", "mid", "getMid", "setMid", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CallHistoryData {
    private long completeTime;
    private String destination;
    private long mid;

    public final long getMid() {
        return this.mid;
    }

    public final void setMid(long j) {
        this.mid = j;
    }

    public final long getCompleteTime() {
        return this.completeTime;
    }

    public final void setCompleteTime(long j) {
        this.completeTime = j;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        this.destination = str;
    }

    public String toString() {
        return "CallHistoryData(mid=" + this.mid + ", completeTime=" + this.completeTime + ", destination=" + this.destination + ')';
    }
}

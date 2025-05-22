package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ReqRobotPoster.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ReqRobotPoster;", "", "switch", "", "dance_time", "", "(IJ)V", "getDance_time", "()J", "setDance_time", "(J)V", "getSwitch", "()I", "setSwitch", "(I)V", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.network.req.ReqRobotPoster, reason: from toString */
/* loaded from: classes4.dex */
public final class ReqRobotDance {
    private long dance_time;
    private int switch;

    public ReqRobotDance(int i, long j) {
        this.switch = i;
        this.dance_time = j;
    }

    public final long getDance_time() {
        return this.dance_time;
    }

    public final int getSwitch() {
        return this.switch;
    }

    public final void setDance_time(long j) {
        this.dance_time = j;
    }

    public final void setSwitch(int i) {
        this.switch = i;
    }

    public String toString() {
        return "ReqRobotDance(switch='" + this.switch + "')";
    }
}

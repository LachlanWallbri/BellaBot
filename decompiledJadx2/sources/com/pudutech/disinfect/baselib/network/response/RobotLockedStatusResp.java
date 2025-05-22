package com.pudutech.disinfect.baselib.network.response;

import com.pudutech.robot.module.report.track2.TrackKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotLockedStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RobotLockedStatusResp;", "", "lock_state", "", TrackKey.CURRENT_TIME, "", "(Ljava/lang/String;J)V", "getCurrent_time", "()J", "getLock_state", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RobotLockedStatusResp {
    private final long current_time;
    private final String lock_state;

    public static /* synthetic */ RobotLockedStatusResp copy$default(RobotLockedStatusResp robotLockedStatusResp, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = robotLockedStatusResp.lock_state;
        }
        if ((i & 2) != 0) {
            j = robotLockedStatusResp.current_time;
        }
        return robotLockedStatusResp.copy(str, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLock_state() {
        return this.lock_state;
    }

    /* renamed from: component2, reason: from getter */
    public final long getCurrent_time() {
        return this.current_time;
    }

    public final RobotLockedStatusResp copy(String lock_state, long current_time) {
        Intrinsics.checkParameterIsNotNull(lock_state, "lock_state");
        return new RobotLockedStatusResp(lock_state, current_time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotLockedStatusResp)) {
            return false;
        }
        RobotLockedStatusResp robotLockedStatusResp = (RobotLockedStatusResp) other;
        return Intrinsics.areEqual(this.lock_state, robotLockedStatusResp.lock_state) && this.current_time == robotLockedStatusResp.current_time;
    }

    public int hashCode() {
        String str = this.lock_state;
        return ((str != null ? str.hashCode() : 0) * 31) + Long.hashCode(this.current_time);
    }

    public String toString() {
        return "RobotLockedStatusResp(lock_state=" + this.lock_state + ", current_time=" + this.current_time + ")";
    }

    public RobotLockedStatusResp(String lock_state, long j) {
        Intrinsics.checkParameterIsNotNull(lock_state, "lock_state");
        this.lock_state = lock_state;
        this.current_time = j;
    }

    public final String getLock_state() {
        return this.lock_state;
    }

    public final long getCurrent_time() {
        return this.current_time;
    }
}

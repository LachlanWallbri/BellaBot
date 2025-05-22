package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.robot.module.report.track2.TrackKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "Landroid/os/Parcelable;", "robot_type", "", "over_time", "", TrackKey.CURRENT_TIME, "", "(Ljava/lang/String;IJ)V", "getCurrent_time", "()J", "getOver_time", "()I", "getRobot_type", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RobotActiveStatusResp implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final long current_time;
    private final int over_time;
    private final String robot_type;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new RobotActiveStatusResp(in.readString(), in.readInt(), in.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new RobotActiveStatusResp[i];
        }
    }

    public static /* synthetic */ RobotActiveStatusResp copy$default(RobotActiveStatusResp robotActiveStatusResp, String str, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = robotActiveStatusResp.robot_type;
        }
        if ((i2 & 2) != 0) {
            i = robotActiveStatusResp.over_time;
        }
        if ((i2 & 4) != 0) {
            j = robotActiveStatusResp.current_time;
        }
        return robotActiveStatusResp.copy(str, i, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRobot_type() {
        return this.robot_type;
    }

    /* renamed from: component2, reason: from getter */
    public final int getOver_time() {
        return this.over_time;
    }

    /* renamed from: component3, reason: from getter */
    public final long getCurrent_time() {
        return this.current_time;
    }

    public final RobotActiveStatusResp copy(String robot_type, int over_time, long current_time) {
        Intrinsics.checkParameterIsNotNull(robot_type, "robot_type");
        return new RobotActiveStatusResp(robot_type, over_time, current_time);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotActiveStatusResp)) {
            return false;
        }
        RobotActiveStatusResp robotActiveStatusResp = (RobotActiveStatusResp) other;
        return Intrinsics.areEqual(this.robot_type, robotActiveStatusResp.robot_type) && this.over_time == robotActiveStatusResp.over_time && this.current_time == robotActiveStatusResp.current_time;
    }

    public int hashCode() {
        String str = this.robot_type;
        return ((((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.over_time)) * 31) + Long.hashCode(this.current_time);
    }

    public String toString() {
        return "RobotActiveStatusResp(robot_type=" + this.robot_type + ", over_time=" + this.over_time + ", current_time=" + this.current_time + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.robot_type);
        parcel.writeInt(this.over_time);
        parcel.writeLong(this.current_time);
    }

    public RobotActiveStatusResp(String robot_type, int i, long j) {
        Intrinsics.checkParameterIsNotNull(robot_type, "robot_type");
        this.robot_type = robot_type;
        this.over_time = i;
        this.current_time = j;
    }

    public final String getRobot_type() {
        return this.robot_type;
    }

    public final int getOver_time() {
        return this.over_time;
    }

    public final long getCurrent_time() {
        return this.current_time;
    }
}

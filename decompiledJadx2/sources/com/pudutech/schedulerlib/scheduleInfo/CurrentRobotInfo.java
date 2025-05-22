package com.pudutech.schedulerlib.scheduleInfo;

import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: CurrentRobotInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/schedulerlib/scheduleInfo/CurrentRobotInfo;", "", "()V", "data", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "getData", "()Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "setData", "(Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;)V", "type", "", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "toString", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CurrentRobotInfo {
    private String type = "robot";
    private RobotScheduleInfo data = new RobotScheduleInfo();

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public final RobotScheduleInfo getData() {
        return this.data;
    }

    public final void setData(RobotScheduleInfo robotScheduleInfo) {
        Intrinsics.checkParameterIsNotNull(robotScheduleInfo, "<set-?>");
        this.data = robotScheduleInfo;
    }

    public String toString() {
        return "CurrentRobotInfo(type='" + this.type + "', data=" + this.data + ')';
    }
}

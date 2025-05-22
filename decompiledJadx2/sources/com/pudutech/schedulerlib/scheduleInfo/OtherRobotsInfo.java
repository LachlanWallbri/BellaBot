package com.pudutech.schedulerlib.scheduleInfo;

import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: OtherRobotsInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/schedulerlib/scheduleInfo/OtherRobotsInfo;", "", "()V", "data", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "type", "", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class OtherRobotsInfo {
    private String type = "robots";
    private ArrayList<RobotScheduleInfo> data = new ArrayList<>();

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public final ArrayList<RobotScheduleInfo> getData() {
        return this.data;
    }

    public final void setData(ArrayList<RobotScheduleInfo> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.data = arrayList;
    }
}

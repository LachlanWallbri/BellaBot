package com.pudutech.mirsdk.base;

import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKRobotState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, m3961d2 = {"peerConversion", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDKRobotStateKt {
    public static final RobotState peerConversion(SDKRobotState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        switch (state) {
            case Warn:
            case Error:
                return RobotState.Error;
            case Moving:
                return RobotState.Moving;
            case Stuck:
                return RobotState.Stuck;
            case Approaching:
                return RobotState.Approaching;
            case Arrive:
                return RobotState.Arrive;
            case Pause:
                return RobotState.Pause;
            case Resume:
                return RobotState.Resume;
            case Avoid:
                return RobotState.Avoid;
            default:
                return RobotState.Idle;
        }
    }
}

package com.pudutech.bumblebee.business.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActiveModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "", "()V", "TAG", "", "checkActive", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ActiveModel {
    private final String TAG = "ActiveModel";

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000f. Please report as an issue. */
    public final boolean checkActive(RobotState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        switch (state) {
            case Idle:
                return false;
            case Moving:
            case Stuck:
            case Approaching:
                return true;
            case Arrive:
            case Pause:
                return false;
            case Resume:
            case Avoid:
                return true;
            default:
                Pdlog.m3274e(this.TAG, "missing case " + state);
            case Error:
                return false;
        }
    }
}

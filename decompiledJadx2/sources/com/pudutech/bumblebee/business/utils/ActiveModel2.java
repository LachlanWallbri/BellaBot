package com.pudutech.bumblebee.business.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActiveModel2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\nH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0011\"\u00020\n¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u000fJ\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\r\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/utils/ActiveModel2;", "", "()V", "TAG", "", "last", "", "Ljava/lang/Boolean;", "noPauseList", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/collections/ArrayList;", "checkActive", "state", "resetLast", "", "setNoNeedPauseShowScene", "", "([Lcom/pudutech/mirsdk/aidl/serialize/RobotState;)V", "setTaskArrival", "updateState", "Lkotlin/Pair;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ActiveModel2 {
    private Boolean last;
    private final String TAG = "ActiveModel2";
    private ArrayList<RobotState> noPauseList = new ArrayList<>();

    public final void resetLast() {
        this.last = (Boolean) null;
    }

    public final void setNoNeedPauseShowScene(RobotState... state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(this.TAG, "setNoPauseScene state=" + state);
        this.noPauseList.clear();
        CollectionsKt.addAll(this.noPauseList, state);
    }

    public final void setTaskArrival() {
        this.last = false;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    private final boolean checkActive(RobotState state) {
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

    public final Pair<Boolean, Boolean> updateState(RobotState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        boolean checkActive = checkActive(state);
        boolean z = !Intrinsics.areEqual(Boolean.valueOf(checkActive), this.last);
        this.last = Boolean.valueOf(checkActive);
        if (this.noPauseList.contains(state) && !checkActive) {
            z = false;
        }
        Pdlog.m3273d(this.TAG, "updateState : state = " + state + "; " + this.noPauseList + " , last = " + this.last + " , needShow = " + z);
        return new Pair<>(Boolean.valueOf(z), Boolean.valueOf(checkActive));
    }
}

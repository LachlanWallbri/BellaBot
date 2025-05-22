package com.pudutech.bumblebee.business.movementTask;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementCallback.HangOutCallback;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HangoutTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\tH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0010\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\u0019\u001a\u00020\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementTask/HangoutTask;", "Lcom/pudutech/bumblebee/business/movementInterface/HangOutInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "()V", "TAG", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/HangOutCallback;", "isActive", "", "checkActive", "onAccessDoorChange", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "destination", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "pause", "keepTime_ms", "", "setActive", "boolean", "setCallback", "setRandom", "range", "", "Lcom/pudutech/bumblebee/business/movementInterface/HangOutInterface$Point;", "radius_m", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class HangoutTask implements HangOutInterface, MoveStateListener {
    private final String TAG = "HangoutTask";
    private HangOutCallback callback;
    private boolean isActive;

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public MoveActionInterface getMoveAction() {
        return HangOutInterface.DefaultImpls.getMoveAction(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void pauseTask() {
        HangOutInterface.DefaultImpls.pauseTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void resumeTask() {
        HangOutInterface.DefaultImpls.resumeTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean z, boolean z2) {
        HangOutInterface.DefaultImpls.setActive(this, z, z2);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.HangOutInterface
    public void setCallback(HangOutCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onStateChange(RobotState state, String description) {
        HangOutCallback hangOutCallback = this.callback;
        if (hangOutCallback != null) {
            hangOutCallback.onMovementChanged(state, description);
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onAccessDoorChange(AccessDoorControlState state, String destination) {
        Pdlog.m3273d(this.TAG, "onAccessDoorChange state: " + state + ",destination:" + destination);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.HangOutInterface
    public void setRandom(List<HangOutInterface.Point> range, double radius_m) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        HangOutCallback hangOutCallback = this.callback;
        if (hangOutCallback != null) {
            hangOutCallback.onTaskSetup(true, "range=[" + range.toString() + "]  radius=" + radius_m);
        }
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean r3) {
        this.isActive = r3;
        if (r3) {
            HangOutCallback hangOutCallback = this.callback;
            if (hangOutCallback != null) {
                hangOutCallback.onMovementChanged(RobotState.Moving, "");
                return;
            }
            return;
        }
        HangOutCallback hangOutCallback2 = this.callback;
        if (hangOutCallback2 != null) {
            hangOutCallback2.onMovementChanged(RobotState.Pause, "");
        }
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    /* renamed from: checkActive, reason: from getter */
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void pause(long keepTime_ms) {
        setActive(false);
    }
}

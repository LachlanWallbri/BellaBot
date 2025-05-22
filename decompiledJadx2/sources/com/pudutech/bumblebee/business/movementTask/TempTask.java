package com.pudutech.bumblebee.business.movementTask;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementCallback.HangOutCallback;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;

/* compiled from: TempTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\tH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0010\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementTask/TempTask;", "Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "Lcom/pudutech/bumblebee/business/movementInterface/TempMoveInterface;", "()V", "TAG", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/HangOutCallback;", "isActive", "", "checkActive", "onAccessDoorChange", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "destination", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "pause", "keepTime_ms", "", "setActive", "boolean", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TempTask implements MoveStateListener, TempMoveInterface {
    private final String TAG = "TempTask";
    private HangOutCallback callback;
    private boolean isActive;

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public MoveActionInterface getMoveAction() {
        return TempMoveInterface.DefaultImpls.getMoveAction(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void pauseTask() {
        TempMoveInterface.DefaultImpls.pauseTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void resumeTask() {
        TempMoveInterface.DefaultImpls.resumeTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean z, boolean z2) {
        TempMoveInterface.DefaultImpls.setActive(this, z, z2);
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

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean r1) {
        setActive(r1);
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

package com.pudutech.bumblebee.business.movementTask;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.CruiseCallback;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000bH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000bH\u0016J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0016J&\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementTask/CruiseTask;", "Lcom/pudutech/bumblebee/business/movementInterface/CruiseInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "()V", "TAG", "", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/CruiseCallback;", "isActive", "", "taskSet", "checkActive", "onAccessDoorChange", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "destination", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "pause", "keepTime_ms", "", "setActive", "boolean", "setCallback", "setPath", "pathID", "", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "stops", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseTask implements CruiseInterface, MoveStateListener {
    private final String TAG = "CruiseTask";
    private ActiveModel activeModel = new ActiveModel();
    private CruiseCallback callback;
    private boolean isActive;
    private boolean taskSet;

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public MoveActionInterface getMoveAction() {
        return CruiseInterface.DefaultImpls.getMoveAction(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void pauseTask() {
        CruiseInterface.DefaultImpls.pauseTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void resumeTask() {
        CruiseInterface.DefaultImpls.resumeTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean z, boolean z2) {
        CruiseInterface.DefaultImpls.setActive(this, z, z2);
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onStateChange(RobotState state, String description) {
        CruiseCallback cruiseCallback;
        if (state == RobotState.Moving) {
            if (!this.taskSet && (cruiseCallback = this.callback) != null) {
                cruiseCallback.onTaskSetup();
            }
            this.taskSet = true;
        }
        if (state != null) {
            this.isActive = this.activeModel.checkActive(state);
        }
        CruiseCallback cruiseCallback2 = this.callback;
        if (cruiseCallback2 != null) {
            cruiseCallback2.onMovementChanged(state, description);
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onAccessDoorChange(AccessDoorControlState state, String destination) {
        Pdlog.m3273d(this.TAG, "onAccessDoorChange state: " + state + ",destination:" + destination);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.CruiseInterface
    public void setCallback(CruiseCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.CruiseInterface
    public void setPath(int pathID, MoveTaskMode performance, List<String> stops) {
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Intrinsics.checkParameterIsNotNull(stops, "stops");
        this.taskSet = false;
        getMoveAction().goCruisePath(pathID, performance, stops);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean r5) {
        if (!this.taskSet) {
            Pdlog.m3274e(this.TAG, "taskSet=" + this.taskSet);
            return;
        }
        if (r5) {
            getMoveAction().resume();
        } else {
            getMoveAction().pause();
        }
        DelayResumeActive.INSTANCE.cancelTask();
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    /* renamed from: checkActive, reason: from getter */
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void pause(long keepTime_ms) {
        getMoveAction().pause();
        DelayResumeActive.INSTANCE.post$module_bumblebee_business_robotRelease(this, keepTime_ms);
    }
}

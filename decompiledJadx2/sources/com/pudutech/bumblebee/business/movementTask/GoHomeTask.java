package com.pudutech.bumblebee.business.movementTask;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.GoHomeCallback;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GoHomeTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\fH\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0016\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\fH\u0016J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010 \u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementTask/GoHomeTask;", "Lcom/pudutech/bumblebee/business/movementInterface/GoHomeInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "()V", "TAG", "", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/GoHomeCallback;", "destination", "isActive", "", "moveTaskMode", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "checkActive", "onAccessDoorChange", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "pause", "keepTime_ms", "", "setActive", "boolean", "isFillIn", "setCallback", "setHome", "performance", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GoHomeTask implements GoHomeInterface, MoveStateListener {
    private GoHomeCallback callback;
    private String destination;
    private boolean isActive;
    private final String TAG = "GoHomeTask";
    private TaskStatus status = TaskStatus.AWAIT;
    private ActiveModel activeModel = new ActiveModel();
    private MoveTaskMode moveTaskMode = MoveTaskMode.Normal;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RobotState.values().length];

        static {
            $EnumSwitchMapping$0[RobotState.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[RobotState.Arrive.ordinal()] = 2;
            $EnumSwitchMapping$0[RobotState.Idle.ordinal()] = 3;
            $EnumSwitchMapping$0[RobotState.Pause.ordinal()] = 4;
            $EnumSwitchMapping$0[RobotState.Error.ordinal()] = 5;
        }
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public MoveActionInterface getMoveAction() {
        return GoHomeInterface.DefaultImpls.getMoveAction(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void pauseTask() {
        GoHomeInterface.DefaultImpls.pauseTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void resumeTask() {
        GoHomeInterface.DefaultImpls.resumeTask(this);
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onStateChange(RobotState state, String description) {
        int i;
        if (state != null) {
            this.isActive = this.activeModel.checkActive(state);
        }
        if (state != null && (i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) != 1) {
            if (i == 2) {
                this.status = TaskStatus.ARRIVAL;
                GoHomeCallback goHomeCallback = this.callback;
                if (goHomeCallback != null) {
                    goHomeCallback.onStatusChanged(this.status);
                }
            } else if (i == 3 || i != 4) {
            }
        }
        Pdlog.m3273d(this.TAG, "onStateChange robotState=" + state + " taskStatus=" + this.status);
        GoHomeCallback goHomeCallback2 = this.callback;
        if (goHomeCallback2 != null) {
            goHomeCallback2.onMovementChanged(state, description);
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onAccessDoorChange(AccessDoorControlState state, String destination) {
        Pdlog.m3273d(this.TAG, "onAccessDoorChange state: " + state + ",destination:" + destination);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.GoHomeInterface
    public void setCallback(GoHomeCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3275i(this.TAG, "set callback=" + callback);
        this.callback = callback;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.GoHomeInterface
    public void setHome(String destination, MoveTaskMode performance) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3273d(this.TAG, "setHome destination=" + destination + "  performance=" + performance);
        this.moveTaskMode = performance;
        this.status = TaskStatus.AWAIT;
        this.destination = destination;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean isActive, boolean isFillIn) {
        GoHomeInterface.DefaultImpls.setActive(this, isActive, isFillIn);
        String str = this.destination;
        if (str == null || str.length() == 0) {
            Pdlog.m3274e(this.TAG, "setActive : destination null ?");
            return;
        }
        Pdlog.m3273d(this.TAG, "set active=" + isActive + " when status=" + this.status + "  isFillIn=" + isFillIn);
        DelayResumeActive.INSTANCE.cancelTask();
        if (!isActive) {
            getMoveAction().pause();
            return;
        }
        if (this.status == TaskStatus.AWAIT) {
            this.status = TaskStatus.ON_THE_WAY;
            GoHomeCallback goHomeCallback = this.callback;
            if (goHomeCallback != null) {
                goHomeCallback.onStatusChanged(this.status);
            }
        }
        getMoveAction().goHomeFill(this.destination, this.moveTaskMode, isFillIn);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean r2) {
        setActive(r2, false);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    /* renamed from: checkActive, reason: from getter */
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void pause(long keepTime_ms) {
        Pdlog.m3273d(this.TAG, "pause " + keepTime_ms);
        setActive(false);
        DelayResumeActive.INSTANCE.post$module_bumblebee_business_robotRelease(this, keepTime_ms);
    }
}

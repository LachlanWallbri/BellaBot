package com.pudutech.peanut.robot_ui.viewmodel;

import android.os.Handler;
import android.os.Message;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseMoveViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000 32\u00020\u0001:\u00013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0006\u0010\u001b\u001a\u00020\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0004J\b\u0010\u001f\u001a\u00020\u0019H\u0004J\b\u0010 \u001a\u00020\u0019H\u0004J\b\u0010!\u001a\u00020\u0019H\u0014J\b\u0010\"\u001a\u00020\u0019H\u0004J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u000fH\u0004J\b\u0010%\u001a\u00020\u0019H\u0004J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(H\u0004J\b\u0010)\u001a\u00020\u0019H\u0004J\b\u0010*\u001a\u00020\u0019H\u0004J\u0018\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u0012H\u0002J\b\u0010.\u001a\u00020\u0019H\u0016J\u0010\u0010/\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\tH\u0002J\b\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0002J\b\u00102\u001a\u00020\u0019H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000b¨\u00064"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "faceLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoAnimation;", "getFaceLiveData", "()Landroidx/lifecycle/MutableLiveData;", "handler", "Landroid/os/Handler;", "moveErrorHelperLiveData", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "getMoveErrorHelperLiveData", "moveState", "", "onFillState", "", "getOnFillState", "onSchedulingLiveData", "getOnSchedulingLiveData", "active", "", "cancel", "isNotErrorMove", "isStuckState", "normalMoveState", "onArrive", "onAvoid", "onCancel", "onCleared", "onDone", "onError", "eh", "onFillIn", "onMoving", "event", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotMoveEvent;", "onPause", "onStart", "onStuckEvent", "face", "int", "pause", "playEventFace", "playNormalFace", "stopStuckEvent", "stuckMoveState", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class BaseMoveViewModel extends BaseViewModel {
    private static final int HANDLER_ONE_EVENT = 1001;
    private static final int HANDLER_THREE_EVENT = 1003;
    private static final int HANDLER_TWO_EVENT = 1002;
    private int moveState;
    private final String TAG = "BaseMoveViewModel";
    private final MutableLiveData<FaceVideoAnimation> faceLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> onSchedulingLiveData = new MutableLiveData<>();
    private final MutableLiveData<MoveErrorHelper> moveErrorHelperLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> onFillState = new MutableLiveData<>();
    private final Handler handler = new Handler(new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel$handler$1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0031, code lost:
        
            return true;
         */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    BaseMoveViewModel.this.onStuckEvent(SceneAnimationResources.INSTANCE.getImpatient(), 1);
                    break;
                case 1002:
                    BaseMoveViewModel.this.onStuckEvent(SceneAnimationResources.INSTANCE.getImpatient(), 2);
                    break;
                case 1003:
                    LightPlayManager.INSTANCE.playAngryAvoid();
                    BaseMoveViewModel.this.onStuckEvent(SceneAnimationResources.INSTANCE.getAngry(), 3);
                    break;
            }
        }
    });

    public void cancel() {
    }

    public void pause() {
    }

    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<FaceVideoAnimation> getFaceLiveData() {
        return this.faceLiveData;
    }

    public final MutableLiveData<Boolean> getOnSchedulingLiveData() {
        return this.onSchedulingLiveData;
    }

    public final MutableLiveData<MoveErrorHelper> getMoveErrorHelperLiveData() {
        return this.moveErrorHelperLiveData;
    }

    public final MutableLiveData<Boolean> getOnFillState() {
        return this.onFillState;
    }

    private final void normalMoveState() {
        this.moveState = 0;
    }

    private final void stuckMoveState() {
        this.moveState = 1;
    }

    private final boolean isStuckState() {
        return this.moveState == 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPause() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onCancel() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onStart() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onDone() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onArrive() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onError(MoveErrorHelper eh) {
        Intrinsics.checkParameterIsNotNull(eh, "eh");
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
        if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
            this.moveErrorHelperLiveData.postValue(eh);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onFillIn() {
        stopStuckEvent();
        this.onSchedulingLiveData.postValue(false);
        this.onFillState.postValue(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onAvoid() {
        VoicePlayTasks.INSTANCE.playMovingSchedule();
        this.onSchedulingLiveData.postValue(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onMoving(RobotMoveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.onSchedulingLiveData.postValue(false);
        if (event == RobotMoveEvent.APPROACHING) {
            stopStuckEvent();
        } else if (event != RobotMoveEvent.STUCK) {
            stopStuckEvent();
            if (isStuckState()) {
                playNormalFace();
            }
            normalMoveState();
        } else if (!isStuckState()) {
            stuckMoveState();
            this.handler.sendEmptyMessageDelayed(1001, 10000L);
            VoicePlayTasks.INSTANCE.playMovingStruck();
        }
        this.onFillState.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onStuckEvent(FaceVideoAnimation face, int r7) {
        Pdlog.m3273d(getTAG(), "onStuckEvent : face = " + face + "; int = " + r7 + "; ");
        stopStuckEvent();
        if (r7 == 1) {
            this.handler.sendEmptyMessageDelayed(1002, 10000L);
        } else if (r7 == 2) {
            this.handler.sendEmptyMessageDelayed(1003, 10000L);
        } else if (r7 == 3) {
            this.handler.sendEmptyMessageDelayed(1003, 10000L);
        }
        playEventFace(face);
    }

    private final void stopStuckEvent() {
        this.handler.removeCallbacksAndMessages(null);
    }

    private final void playEventFace(FaceVideoAnimation face) {
        this.faceLiveData.setValue(face);
    }

    private final void playNormalFace() {
        stopStuckEvent();
        this.faceLiveData.setValue(SceneAnimationResources.INSTANCE.getDeliver());
    }

    public void active() {
        this.moveErrorHelperLiveData.postValue(null);
    }

    public final boolean isNotErrorMove() {
        return this.moveErrorHelperLiveData.getValue() == null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        stopStuckEvent();
    }
}

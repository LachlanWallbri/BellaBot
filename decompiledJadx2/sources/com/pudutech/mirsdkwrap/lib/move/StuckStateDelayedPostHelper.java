package com.pudutech.mirsdkwrap.lib.move;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: StuckStateDelayedPostHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0006\u0010\u0015\u001a\u00020\u0011JF\u0010\u0016\u001a\u00020\u00112>\u0010\u000b\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fj\u0002`\u0012J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nJ\b\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010\u000b\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fj\u0002`\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/StuckStateDelayedPostHelper;", "", "()V", "DELAYED_POST_STATE", "", "TAG", "", "handler", "Landroid/os/Handler;", "lastState", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "moveStateListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "needPostState", "delayedPostState", "destroy", "init", "needDelayedPost", "", "state", "resetPostTask", "stopDelayedPostState", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StuckStateDelayedPostHelper {
    private Function2<? super RobotState, ? super String, Unit> moveStateListener;
    private RobotState needPostState;
    private final String TAG = "StuckStateDelayedPostHelper";
    private RobotState lastState = RobotState.Idle;
    private final int DELAYED_POST_STATE = 1001;
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.mirsdkwrap.lib.move.StuckStateDelayedPostHelper$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            RobotState robotState;
            String str;
            RobotState robotState2;
            RobotState robotState3;
            RobotState robotState4;
            int i2 = message.what;
            i = StuckStateDelayedPostHelper.this.DELAYED_POST_STATE;
            if (i2 == i) {
                robotState = StuckStateDelayedPostHelper.this.needPostState;
                if (robotState != null) {
                    str = StuckStateDelayedPostHelper.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("post delayed state = ");
                    robotState2 = StuckStateDelayedPostHelper.this.needPostState;
                    sb.append(robotState2);
                    Pdlog.m3273d(str, sb.toString());
                    StuckStateDelayedPostHelper stuckStateDelayedPostHelper = StuckStateDelayedPostHelper.this;
                    robotState3 = stuckStateDelayedPostHelper.needPostState;
                    if (robotState3 == null) {
                        Intrinsics.throwNpe();
                    }
                    stuckStateDelayedPostHelper.lastState = robotState3;
                    Function2 access$getMoveStateListener$p = StuckStateDelayedPostHelper.access$getMoveStateListener$p(StuckStateDelayedPostHelper.this);
                    robotState4 = StuckStateDelayedPostHelper.this.needPostState;
                    access$getMoveStateListener$p.invoke(robotState4, "");
                    StuckStateDelayedPostHelper.this.needPostState = (RobotState) null;
                }
            }
            return true;
        }
    });

    public static final /* synthetic */ Function2 access$getMoveStateListener$p(StuckStateDelayedPostHelper stuckStateDelayedPostHelper) {
        Function2<? super RobotState, ? super String, Unit> function2 = stuckStateDelayedPostHelper.moveStateListener;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveStateListener");
        }
        return function2;
    }

    public final void init(Function2<? super RobotState, ? super String, Unit> moveStateListener) {
        Intrinsics.checkParameterIsNotNull(moveStateListener, "moveStateListener");
        this.moveStateListener = moveStateListener;
    }

    private final void delayedPostState() {
        stopDelayedPostState();
        this.handler.sendEmptyMessageDelayed(this.DELAYED_POST_STATE, 2000L);
    }

    private final void stopDelayedPostState() {
        this.handler.removeMessages(this.DELAYED_POST_STATE);
    }

    public final boolean needDelayedPost(RobotState state) {
        RobotState robotState;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(this.TAG, "needDelayedPost : state = " + state + "; ");
        if (state == RobotState.Moving && this.lastState == RobotState.Stuck && (robotState = this.needPostState) == null) {
            if (robotState == null) {
                this.needPostState = RobotState.Moving;
                delayedPostState();
                Pdlog.m3273d(this.TAG, "needDelayedPost : delayed post state = " + state + "; ");
            } else {
                Pdlog.m3273d(this.TAG, "not handle state = " + state + "; lastState = " + this.lastState + " ; needPostState = " + this.needPostState);
            }
            return true;
        }
        if (state == RobotState.Stuck && this.lastState != RobotState.Stuck) {
            if (this.needPostState == null) {
                this.needPostState = RobotState.Stuck;
                delayedPostState();
                Pdlog.m3273d(this.TAG, "needDelayedPost : delayed post state = " + state + "; ");
            } else {
                Pdlog.m3273d(this.TAG, "not handle state = " + state + "; lastState = " + this.lastState + " ; needPostState = " + this.needPostState);
            }
            return true;
        }
        Pdlog.m3273d(this.TAG, "needDelayedPost : state = " + state + "; reset tesk");
        resetPostTask();
        this.lastState = state;
        return false;
    }

    private final void resetPostTask() {
        this.needPostState = (RobotState) null;
        stopDelayedPostState();
    }

    public final void destroy() {
        Pdlog.m3273d(this.TAG, "destroy ");
        resetPostTask();
    }
}

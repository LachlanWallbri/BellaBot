package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.MoveBySuspendChargeListener;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoveBySuspendCharge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012B\u0010\u0007\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\tj\u0002`\u0010\u0018\u00010\b¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0018\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fJ\b\u0010\u001a\u001a\u00020\u000fH\u0016J\u001b\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0013H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\u000fH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u000fJ\u0006\u0010!\u001a\u00020\u000fR\u001a\u0010\u0012\u001a\u00020\u0013X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveBySuspendCharge;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveBySuspendChargeListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "speedListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "active", "cancel", "destroy", "onArrive", "description", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pause", "resume", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveBySuspendCharge extends BaseMove<MoveBySuspendChargeListener> {
    private String TAG;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveBySuspendCharge(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper, ListenerList<Function2<Double, Double, Unit>> listenerList) {
        super(moveInterfaceDecorator, devicesControlHelper, listenerList);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.TAG = "MoveBySuspendCharge";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    protected void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    public final void active() {
        Pdlog.m3273d(getTAG(), "脱离充电桩任务:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveBySuspendCharge$active$1(null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        runOnUi(new MoveBySuspendCharge$onArrive$2(this, str, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "pause ");
        if (getIsDestroy()) {
            return Unit.INSTANCE;
        }
        runAsyn(new MoveBySuspendCharge$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    public final void resume() {
        Pdlog.m3273d(getTAG(), "resume ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveBySuspendCharge$resume$1(this, null));
    }

    public final void pause() {
        Pdlog.m3273d(getTAG(), "pause ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveBySuspendCharge$pause$1(this, null));
    }

    public final void cancel() {
        Pdlog.m3273d(getTAG(), "cancelOne ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveBySuspendCharge$cancel$1(this, null));
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    /* renamed from: destroy, reason: merged with bridge method [inline-methods] */
    public void destroy$module_robot_mirsdk_wrapper_release() {
        super.destroy$module_robot_mirsdk_wrapper_release();
    }
}

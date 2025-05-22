package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.MoveByChargeListener;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByChargeTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByCharge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012B\u0010\t\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000bj\u0002`\u0012\u0018\u00010\n¢\u0006\u0002\u0010\u0013J\u0006\u0010\u001c\u001a\u00020\u0011J\u0006\u0010\u001d\u001a\u00020\u0011J\u0011\u0010\u001e\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u001bJ\u001b\u0010!\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010#J\u0011\u0010$\u001a\u00020\u0011H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0006\u0010%\u001a\u00020\u0011R\u001a\u0010\u0014\u001a\u00020\u0015X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000RJ\u0010\t\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000bj\u0002`\u0012\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveByCharge;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByChargeListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "task", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByChargeTask;", "speedListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByChargeTask;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "moveByGroupReport", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupReport;", "active", "cancel", "genMoveReport", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMoveReport", "onArrive", "description", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "pause", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveByCharge extends BaseMove<MoveByChargeListener> {
    private String TAG;
    private final DevicesControlHelper devicesControlHelper;
    private MoveByGroupReport moveByGroupReport;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private final ListenerList<Function2<Double, Double, Unit>> speedListeners;
    private final MoveByChargeTask task;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByCharge(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper, MoveByChargeTask task, ListenerList<Function2<Double, Double, Unit>> listenerList) {
        super(moveInterfaceDecorator, devicesControlHelper, listenerList);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
        this.task = task;
        this.speedListeners = listenerList;
        this.moveByGroupReport = new MoveByGroupReport(null, 1, null);
        this.TAG = "MoveByCharge";
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
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByCharge$active$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        MoveByCharge$onArrive$1 moveByCharge$onArrive$1;
        int i;
        MoveByCharge moveByCharge;
        if (continuation instanceof MoveByCharge$onArrive$1) {
            moveByCharge$onArrive$1 = (MoveByCharge$onArrive$1) continuation;
            if ((moveByCharge$onArrive$1.label & Integer.MIN_VALUE) != 0) {
                moveByCharge$onArrive$1.label -= Integer.MIN_VALUE;
                Object obj = moveByCharge$onArrive$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByCharge$onArrive$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    moveByCharge$onArrive$1.L$0 = this;
                    moveByCharge$onArrive$1.L$1 = str;
                    moveByCharge$onArrive$1.label = 1;
                    if (genMoveReport(moveByCharge$onArrive$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveByCharge = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    str = (String) moveByCharge$onArrive$1.L$1;
                    moveByCharge = (MoveByCharge) moveByCharge$onArrive$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                moveByCharge.runOnUi(new MoveByCharge$onArrive$2(moveByCharge, str, null));
                return Unit.INSTANCE;
            }
        }
        moveByCharge$onArrive$1 = new MoveByCharge$onArrive$1(this, continuation);
        Object obj2 = moveByCharge$onArrive$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByCharge$onArrive$1.label;
        if (i != 0) {
        }
        moveByCharge.runOnUi(new MoveByCharge$onArrive$2(moveByCharge, str, null));
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    protected Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "pause:" + getIsDestroy());
        if (getIsDestroy()) {
            return Unit.INSTANCE;
        }
        runAsyn(new MoveByCharge$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    public final void pause() {
        Pdlog.m3273d(getTAG(), "pause:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByCharge$pause$1(this, null));
    }

    public final void cancel() {
        Pdlog.m3273d(getTAG(), "cancelOne ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByCharge$cancel$1(this, null));
    }

    /* renamed from: getMoveReport, reason: from getter */
    public final MoveByGroupReport getMoveByGroupReport() {
        return this.moveByGroupReport;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object genMoveReport(Continuation<? super Unit> continuation) {
        MoveByCharge$genMoveReport$1 moveByCharge$genMoveReport$1;
        int i;
        if (continuation instanceof MoveByCharge$genMoveReport$1) {
            moveByCharge$genMoveReport$1 = (MoveByCharge$genMoveReport$1) continuation;
            if ((moveByCharge$genMoveReport$1.label & Integer.MIN_VALUE) != 0) {
                moveByCharge$genMoveReport$1.label -= Integer.MIN_VALUE;
                Object obj = moveByCharge$genMoveReport$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByCharge$genMoveReport$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = endRecodeReport();
                    if (((MoveReportData) objectRef.element) == null) {
                        Pdlog.m3274e(getTAG(), "genMoveReport : report is null");
                    } else {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        MoveByCharge$genMoveReport$2 moveByCharge$genMoveReport$2 = new MoveByCharge$genMoveReport$2(this, objectRef, null);
                        moveByCharge$genMoveReport$1.L$0 = this;
                        moveByCharge$genMoveReport$1.L$1 = objectRef;
                        moveByCharge$genMoveReport$1.label = 1;
                        if (BuildersKt.withContext(main, moveByCharge$genMoveReport$2, moveByCharge$genMoveReport$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        moveByCharge$genMoveReport$1 = new MoveByCharge$genMoveReport$1(this, continuation);
        Object obj2 = moveByCharge$genMoveReport$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByCharge$genMoveReport$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}

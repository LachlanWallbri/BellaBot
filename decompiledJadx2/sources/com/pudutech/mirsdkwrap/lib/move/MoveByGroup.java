package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
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
/* compiled from: MoveByGroup.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u001d\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012B\u0010\t\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000bj\u0002`\u0012\u0018\u00010\n¢\u0006\u0002\u0010\u0013J\u0006\u0010%\u001a\u00020\u0011J\u0006\u0010&\u001a\u00020\u0011J\r\u0010'\u001a\u00020\u0011H\u0010¢\u0006\u0002\b(J\u0011\u0010)\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0006\u0010+\u001a\u00020$J\u001b\u0010,\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0011\u0010/\u001a\u00020\u0011H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0006\u00100\u001a\u00020\u0011R\u001a\u0010\u0014\u001a\u00020\u0015X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u001e\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000RJ\u0010\t\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000bj\u0002`\u0012\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByGroupStateListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "task", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupTask;", "speedListener", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupTask;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "fillInDestination", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "fillInStateListener", "com/pudutech/mirsdkwrap/lib/move/MoveByGroup$fillInStateListener$1", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup$fillInStateListener$1;", "<set-?>", "", "isFillIn", "()Z", "moveByGroupReport", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupReport;", "active", "cancel", "destroy", "destroy$module_robot_mirsdk_wrapper_release", "genMoveReport", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMoveReport", "onArrive", "s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "pause", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveByGroup extends BaseMove<MoveByGroupStateListener> {
    private String TAG;
    private final DevicesControlHelper devicesControlHelper;
    private Destination fillInDestination;
    private final MoveByGroup$fillInStateListener$1 fillInStateListener;
    private boolean isFillIn;
    private MoveByGroupReport moveByGroupReport;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private final ListenerList<Function2<Double, Double, Unit>> speedListener;
    private final MoveByGroupTask task;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByGroup(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper, MoveByGroupTask task, ListenerList<Function2<Double, Double, Unit>> listenerList) {
        super(moveInterfaceDecorator, devicesControlHelper, listenerList);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
        this.task = task;
        this.speedListener = listenerList;
        this.TAG = "MoveByGroup";
        this.moveByGroupReport = new MoveByGroupReport(null, 1, null);
        this.fillInStateListener = new MoveByGroup$fillInStateListener$1(this);
        this.moveInterfaceDecorator.addFillInStateListener(getTAG(), this.fillInStateListener);
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

    /* renamed from: isFillIn, reason: from getter */
    public final boolean getIsFillIn() {
        return this.isFillIn;
    }

    /* renamed from: getMoveReport, reason: from getter */
    public final MoveByGroupReport getMoveByGroupReport() {
        return this.moveByGroupReport;
    }

    public final void active() {
        Pdlog.m3273d(getTAG(), "active " + this.task + " , currentMoveState = " + getCurrentMoveState());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByGroup$active$1(this, null));
    }

    public final void pause() {
        Pdlog.m3273d(getTAG(), "pause ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByGroup$pause$1(this, null));
    }

    public final void cancel() {
        Pdlog.m3273d(getTAG(), "cancelOne ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByGroup$cancel$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        MoveByGroup$onArrive$1 moveByGroup$onArrive$1;
        int i;
        MoveByGroup moveByGroup;
        if (continuation instanceof MoveByGroup$onArrive$1) {
            moveByGroup$onArrive$1 = (MoveByGroup$onArrive$1) continuation;
            if ((moveByGroup$onArrive$1.label & Integer.MIN_VALUE) != 0) {
                moveByGroup$onArrive$1.label -= Integer.MIN_VALUE;
                Object obj = moveByGroup$onArrive$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByGroup$onArrive$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(getTAG(), "onArrive " + this.task + " , isFillIn = " + this.isFillIn);
                    moveByGroup$onArrive$1.L$0 = this;
                    moveByGroup$onArrive$1.L$1 = str;
                    moveByGroup$onArrive$1.label = 1;
                    if (genMoveReport(moveByGroup$onArrive$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveByGroup = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveByGroup = (MoveByGroup) moveByGroup$onArrive$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (!moveByGroup.isFillIn) {
                    moveByGroup.runOnUi(new MoveByGroup$onArrive$2(moveByGroup, null));
                } else {
                    moveByGroup.runOnUi(new MoveByGroup$onArrive$3(moveByGroup, null));
                }
                return Unit.INSTANCE;
            }
        }
        moveByGroup$onArrive$1 = new MoveByGroup$onArrive$1(this, continuation);
        Object obj2 = moveByGroup$onArrive$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByGroup$onArrive$1.label;
        if (i != 0) {
        }
        if (!moveByGroup.isFillIn) {
        }
        return Unit.INSTANCE;
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
        MoveByGroup$genMoveReport$1 moveByGroup$genMoveReport$1;
        int i;
        if (continuation instanceof MoveByGroup$genMoveReport$1) {
            moveByGroup$genMoveReport$1 = (MoveByGroup$genMoveReport$1) continuation;
            if ((moveByGroup$genMoveReport$1.label & Integer.MIN_VALUE) != 0) {
                moveByGroup$genMoveReport$1.label -= Integer.MIN_VALUE;
                Object obj = moveByGroup$genMoveReport$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByGroup$genMoveReport$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = endRecodeReport();
                    if (((MoveReportData) objectRef.element) == null) {
                        Pdlog.m3274e(getTAG(), "genMoveReport : report is null");
                    } else {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        MoveByGroup$genMoveReport$2 moveByGroup$genMoveReport$2 = new MoveByGroup$genMoveReport$2(this, objectRef, null);
                        moveByGroup$genMoveReport$1.L$0 = this;
                        moveByGroup$genMoveReport$1.L$1 = objectRef;
                        moveByGroup$genMoveReport$1.label = 1;
                        if (BuildersKt.withContext(main, moveByGroup$genMoveReport$2, moveByGroup$genMoveReport$1) == coroutine_suspended) {
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
        moveByGroup$genMoveReport$1 = new MoveByGroup$genMoveReport$1(this, continuation);
        Object obj2 = moveByGroup$genMoveReport$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByGroup$genMoveReport$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "onPause " + this.task);
        runOnUi(new MoveByGroup$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public void destroy$module_robot_mirsdk_wrapper_release() {
        this.moveInterfaceDecorator.removeFillInStateListener(getTAG());
        super.destroy$module_robot_mirsdk_wrapper_release();
    }
}

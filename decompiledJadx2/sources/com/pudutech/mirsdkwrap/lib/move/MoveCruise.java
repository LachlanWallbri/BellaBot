package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveCruiseReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveCruiseTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveCruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B{\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0018\u0010\t\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\n\u0012B\u0010\u000e\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u0015\u0018\u00010\n¢\u0006\u0002\u0010\u0016J\u0006\u0010 \u001a\u00020\fJ\u0006\u0010!\u001a\u00020\fJ\r\u0010\"\u001a\u00020\fH\u0010¢\u0006\u0002\b#J\u0011\u0010$\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0006\u0010&\u001a\u00020\u001eJ\u001b\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u0018H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010)J\u0011\u0010*\u001a\u00020\fH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0006\u0010+\u001a\u00020\fR\u001a\u0010\u0017\u001a\u00020\u0018X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000RJ\u0010\u000e\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u0015\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveCruise;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveCruiseStateListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "task", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveCruiseTask;", "goalCruiseListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function0;", "", "Lcom/pudutech/mirsdkwrap/lib/interf/GoalCruiseListener;", "speedListener", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveCruiseTask;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "moveCruiseReport", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveCruiseReport;", "onGoalCruiseListener", "active", "cancel", "destroy", "destroy$module_robot_mirsdk_wrapper_release", "genMoveReport", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMoveReport", "onArrive", "s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "pause", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveCruise extends BaseMove<MoveCruiseStateListener> {
    private String TAG;
    private final DevicesControlHelper devicesControlHelper;
    private final ListenerList<Function0<Unit>> goalCruiseListeners;
    private final MoveCruiseReport moveCruiseReport;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private final Function0<Unit> onGoalCruiseListener;
    private final ListenerList<Function2<Double, Double, Unit>> speedListener;
    private final MoveCruiseTask task;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveCruise(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper, MoveCruiseTask task, ListenerList<Function0<Unit>> listenerList, ListenerList<Function2<Double, Double, Unit>> listenerList2) {
        super(moveInterfaceDecorator, devicesControlHelper, listenerList2);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
        this.task = task;
        this.goalCruiseListeners = listenerList;
        this.speedListener = listenerList2;
        this.TAG = "MoveCruise";
        this.moveCruiseReport = new MoveCruiseReport(null, 1, null);
        this.onGoalCruiseListener = new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.MoveCruise$onGoalCruiseListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MoveCruise.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveCruise$onGoalCruiseListener$1$1", m3970f = "MoveCruise.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveCruise$onGoalCruiseListener$1$1 */
            /* loaded from: classes6.dex */
            public static final class C53491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6603p$;

                C53491(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53491 c53491 = new C53491(completion);
                    c53491.f6603p$ = (CoroutineScope) obj;
                    return c53491;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6603p$;
                    MoveCruiseStateListener onMoveStateListener = MoveCruise.this.getOnMoveStateListener();
                    if (onMoveStateListener != null) {
                        onMoveStateListener.onGoalCruise();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Pdlog.m3273d(MoveCruise.this.getTAG(), "onGoalCruise ");
                MoveCruise.this.runOnUi(new C53491(null));
            }
        };
        ListenerList<Function0<Unit>> listenerList3 = this.goalCruiseListeners;
        if (listenerList3 != null) {
            listenerList3.addNotSame$module_robot_mirsdk_wrapper_release(this.onGoalCruiseListener);
        }
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
        Pdlog.m3273d(getTAG(), "active " + this.task + " , currentMoveState = " + getCurrentMoveState());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveCruise$active$1(this, null));
    }

    public final void pause() {
        Pdlog.m3273d(getTAG(), "pause ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveCruise$pause$1(this, null));
    }

    public final void cancel() {
        Pdlog.m3273d(getTAG(), "cancelOne ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveCruise$cancel$1(this, null));
    }

    public final MoveCruiseReport getMoveReport() {
        MoveReportData recodeReport = recodeReport();
        if (recodeReport != null) {
            this.moveCruiseReport.setMoveReportData(recodeReport);
        }
        return this.moveCruiseReport;
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
        MoveCruise$genMoveReport$1 moveCruise$genMoveReport$1;
        int i;
        if (continuation instanceof MoveCruise$genMoveReport$1) {
            moveCruise$genMoveReport$1 = (MoveCruise$genMoveReport$1) continuation;
            if ((moveCruise$genMoveReport$1.label & Integer.MIN_VALUE) != 0) {
                moveCruise$genMoveReport$1.label -= Integer.MIN_VALUE;
                Object obj = moveCruise$genMoveReport$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveCruise$genMoveReport$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = endRecodeReport();
                    if (((MoveReportData) objectRef.element) == null) {
                        Pdlog.m3274e(getTAG(), "genMoveReport : report is null");
                    } else {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        MoveCruise$genMoveReport$2 moveCruise$genMoveReport$2 = new MoveCruise$genMoveReport$2(this, objectRef, null);
                        moveCruise$genMoveReport$1.L$0 = this;
                        moveCruise$genMoveReport$1.L$1 = objectRef;
                        moveCruise$genMoveReport$1.label = 1;
                        if (BuildersKt.withContext(main, moveCruise$genMoveReport$2, moveCruise$genMoveReport$1) == coroutine_suspended) {
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
        moveCruise$genMoveReport$1 = new MoveCruise$genMoveReport$1(this, continuation);
        Object obj2 = moveCruise$genMoveReport$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveCruise$genMoveReport$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        MoveCruise$onArrive$1 moveCruise$onArrive$1;
        int i;
        MoveCruise moveCruise;
        if (continuation instanceof MoveCruise$onArrive$1) {
            moveCruise$onArrive$1 = (MoveCruise$onArrive$1) continuation;
            if ((moveCruise$onArrive$1.label & Integer.MIN_VALUE) != 0) {
                moveCruise$onArrive$1.label -= Integer.MIN_VALUE;
                Object obj = moveCruise$onArrive$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveCruise$onArrive$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(getTAG(), "onArrive : s = " + str + "; ");
                    moveCruise$onArrive$1.L$0 = this;
                    moveCruise$onArrive$1.L$1 = str;
                    moveCruise$onArrive$1.label = 1;
                    if (genMoveReport(moveCruise$onArrive$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveCruise = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    str = (String) moveCruise$onArrive$1.L$1;
                    moveCruise = (MoveCruise) moveCruise$onArrive$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (str != null) {
                    moveCruise.runOnUi(new MoveCruise$onArrive$$inlined$let$lambda$1(str, null, moveCruise));
                }
                return Unit.INSTANCE;
            }
        }
        moveCruise$onArrive$1 = new MoveCruise$onArrive$1(this, continuation);
        Object obj2 = moveCruise$onArrive$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveCruise$onArrive$1.label;
        if (i != 0) {
        }
        if (str != null) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "onPause " + this.task);
        runOnUi(new MoveCruise$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public void destroy$module_robot_mirsdk_wrapper_release() {
        super.destroy$module_robot_mirsdk_wrapper_release();
        ListenerList<Function0<Unit>> listenerList = this.goalCruiseListeners;
        if (listenerList != null) {
            listenerList.remove$module_robot_mirsdk_wrapper_release(this.onGoalCruiseListener);
        }
    }
}

package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoveBySolicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0011\u0010\r\u001a\u00020\tH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveBySolicit;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveCruiseStateListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;)V", "onArrive", "", "s", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "test", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MoveBySolicit extends BaseSolicitMove<MoveCruiseStateListener> {
    private final DevicesControlHelper devicesControlHelper;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveBySolicit(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper) {
        super(moveInterfaceDecorator, devicesControlHelper);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "onArrive : s = " + str + "; ");
        Unit unit = null;
        if (str != null) {
            runOnUi(new MoveBySolicit$onArrive$$inlined$let$lambda$1(str, null, this));
            unit = Unit.INSTANCE;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove
    public Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "onPause ");
        runOnUi(new MoveBySolicit$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    public final void test() {
        runOnUi(new MoveBySolicit$test$1(this, null));
    }
}

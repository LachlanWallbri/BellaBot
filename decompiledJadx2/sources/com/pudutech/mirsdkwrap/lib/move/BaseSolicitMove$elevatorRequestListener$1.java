package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import kotlin.Metadata;

/* compiled from: BaseSolicitMove.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$elevatorRequestListener$1", "Lcom/pudutech/mirsdk/aidl/ElevatorRequestListener$Stub;", "informElevatorUtilizeState", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "p1", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BaseSolicitMove$elevatorRequestListener$1 extends ElevatorRequestListener.Stub {
    final /* synthetic */ BaseSolicitMove this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseSolicitMove$elevatorRequestListener$1(BaseSolicitMove baseSolicitMove) {
        this.this$0 = baseSolicitMove;
    }

    @Override // com.pudutech.mirsdk.aidl.ElevatorRequestListener
    public void informElevatorUtilizeState(ElevatorUtilizeState p0, String p1) {
        boolean isElevatorSame;
        ElevatorEventParam parserElevatorJson;
        Pdlog.m3273d(this.this$0.getTAG(), "informElevatorUtilizeState : p0 = " + p0 + "; p1 = " + p1 + "; ");
        isElevatorSame = this.this$0.isElevatorSame(p0, p1);
        if (isElevatorSame) {
            Pdlog.m3277w(this.this$0.getTAG(), "informElevatorUtilizeState : same state !!!");
            return;
        }
        if (p0 == null) {
            return;
        }
        parserElevatorJson = this.this$0.parserElevatorJson(p1);
        if (parserElevatorJson != null) {
            this.this$0.updateElevatorData(p0, p1);
            this.this$0.runOnUi(new C5334x227998b4(this, p0, parserElevatorJson, null));
        } else {
            Pdlog.m3274e(this.this$0.getTAG(), "informElevatorUtilizeState : parser gson is null");
        }
    }
}

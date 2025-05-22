package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByGroup.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdkwrap/lib/move/MoveByGroup$fillInStateListener$1", "Lcom/pudutech/mirsdk/aidl/FillInStateListener$Stub;", "onFillIn", "", "p0", "", "p1", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveByGroup$fillInStateListener$1 extends FillInStateListener.Stub {
    final /* synthetic */ MoveByGroup this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MoveByGroup$fillInStateListener$1(MoveByGroup moveByGroup) {
        this.this$0 = moveByGroup;
    }

    @Override // com.pudutech.mirsdk.aidl.FillInStateListener
    public void onFillIn(boolean p0, Destination p1) {
        Destination destination;
        Destination destination2;
        Pdlog.m3273d(this.this$0.getTAG(), "onFillIn : p0 = " + p0 + "; p1 = " + p1 + "; ");
        if (p0) {
            destination = this.this$0.fillInDestination;
            if (destination != null || p1 == null) {
                destination2 = this.this$0.fillInDestination;
                if (destination2 != null && p1 == null) {
                    this.this$0.fillInDestination = (Destination) null;
                }
            } else {
                this.this$0.fillInDestination = p1;
                this.this$0.active();
            }
        } else if (this.this$0.getIsFillIn() && !p0 && this.this$0.getCurrentMoveState() == RobotState.Arrive) {
            this.this$0.runOnUi(new MoveByGroup$fillInStateListener$1$onFillIn$1(this, null));
        }
        this.this$0.isFillIn = p0;
    }
}

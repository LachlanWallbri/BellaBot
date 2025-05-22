package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.mircore.DockerEstimateTransformListener;
import com.pudutech.mirsdk.movetask.GeneralTask;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/movetask/GeneralTask$dockListener$1", "Lcom/pudutech/mirsdk/mircore/DockerEstimateTransformListener$Stub;", "callDockerEstimateTransform", "", "p0", "", "p1", "", "p2", "p3", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GeneralTask$dockListener$1 extends DockerEstimateTransformListener.Stub {
    final /* synthetic */ GeneralTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneralTask$dockListener$1(GeneralTask generalTask) {
        this.this$0 = generalTask;
    }

    @Override // com.pudutech.mirsdk.mircore.DockerEstimateTransformListener
    public void callDockerEstimateTransform(boolean p0, double p1, double p2, double p3) {
        if ((GeneralTask.access$getMoveState$p(this.this$0) instanceof GeneralTask.GoBluetoothChargingState) && p0) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$dockListener$1$callDockerEstimateTransform$1(this, p1, p2, null), 3, null);
        }
    }
}

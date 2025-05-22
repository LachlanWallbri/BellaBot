package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$onResume$3$informElevatorUtilizeState$1 implements Runnable {
    final /* synthetic */ ElevatorUtilizeState $p0;
    final /* synthetic */ String $p1;
    final /* synthetic */ MirSDKActivity$onResume$3 this$0;

    MirSDKActivity$onResume$3$informElevatorUtilizeState$1(MirSDKActivity$onResume$3 mirSDKActivity$onResume$3, ElevatorUtilizeState elevatorUtilizeState, String str) {
        this.this$0 = mirSDKActivity$onResume$3;
        this.$p0 = elevatorUtilizeState;
        this.$p1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView elev_state = (TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.elev_state);
        Intrinsics.checkExpressionValueIsNotNull(elev_state, "elev_state");
        elev_state.setText("elv: " + this.$p0 + ' ' + this.$p1);
    }
}

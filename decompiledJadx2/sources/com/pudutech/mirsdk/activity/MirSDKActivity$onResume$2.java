package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onResume$2", "Lcom/pudutech/mirsdk/aidl/ElevatorRequestListener$Stub;", "informElevatorUtilizeState", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "p1", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onResume$2 extends ElevatorRequestListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onResume$2(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.ElevatorRequestListener
    public void informElevatorUtilizeState(final ElevatorUtilizeState p0, final String p1) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$2$informElevatorUtilizeState$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView elev_state = (TextView) MirSDKActivity$onResume$2.this.this$0._$_findCachedViewById(C4946R.id.elev_state);
                Intrinsics.checkExpressionValueIsNotNull(elev_state, "elev_state");
                elev_state.setText("elv: " + p0 + ' ' + p1);
            }
        });
    }
}

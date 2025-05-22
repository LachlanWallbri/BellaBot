package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.activity.MirSDKActivity;
import com.pudutech.mirsdk.aidl.dance.DanceCallback;
import com.pudutech.mirsdk.aidl.serialize.DanceStatus;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$mDanceCallback$1", "Lcom/pudutech/mirsdk/aidl/dance/DanceCallback$Stub;", "onCallback", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/DanceStatus;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$mDanceCallback$1 extends DanceCallback.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$mDanceCallback$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.dance.DanceCallback
    public void onCallback(final DanceStatus p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$mDanceCallback$1$onCallback$1
            @Override // java.lang.Runnable
            public final void run() {
                String string;
                TextView tx_dance_status = (TextView) MirSDKActivity$mDanceCallback$1.this.this$0._$_findCachedViewById(C4946R.id.tx_dance_status);
                Intrinsics.checkExpressionValueIsNotNull(tx_dance_status, "tx_dance_status");
                int i = MirSDKActivity.WhenMappings.$EnumSwitchMapping$0[p0.ordinal()];
                if (i == 1) {
                    string = MirSDKActivity$mDanceCallback$1.this.this$0.getString(C4946R.string.dancing);
                } else if (i == 2) {
                    string = MirSDKActivity$mDanceCallback$1.this.this$0.getString(C4946R.string.stop_dancing);
                } else {
                    string = MirSDKActivity$mDanceCallback$1.this.this$0.getString(C4946R.string.dance_status);
                }
                tx_dance_status.setText(string);
            }
        });
    }
}

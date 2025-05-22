package com.pudutech.bumblebee.robot.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/PeripheralsActivity$deliveryListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener$Stub;", "onPalletState", "", "pallets", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "onQRScanMsg", "mark", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity$deliveryListener$1 extends IDeliveryRobotListener.Stub {
    final /* synthetic */ PeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$deliveryListener$1(PeripheralsActivity peripheralsActivity) {
        this.this$0 = peripheralsActivity;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
    public void onQRScanMsg(final String mark) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "scanMsg mark=" + mark);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$deliveryListener$1$onQRScanMsg$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView tvQRScanner = (TextView) PeripheralsActivity$deliveryListener$1.this.this$0._$_findCachedViewById(C4144R.id.tvQRScanner);
                Intrinsics.checkExpressionValueIsNotNull(tvQRScanner, "tvQRScanner");
                tvQRScanner.setText(mark);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
    public void onPalletState(List<Pallet> pallets) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPalletState pallets=" + pallets);
        this.this$0.runOnUiThread(new PeripheralsActivity$deliveryListener$1$onPalletState$1(this, pallets));
    }
}

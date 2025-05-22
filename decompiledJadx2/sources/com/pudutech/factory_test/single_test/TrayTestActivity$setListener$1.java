package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TrayTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/factory_test/single_test/TrayTestActivity$setListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener$Stub;", "onPalletState", "", "p0", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "onQRScanMsg", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TrayTestActivity$setListener$1 extends IDeliveryRobotListener.Stub {
    final /* synthetic */ TrayTestActivity this$0;

    @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
    public void onQRScanMsg(String p0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrayTestActivity$setListener$1(TrayTestActivity trayTestActivity) {
        this.this$0 = trayTestActivity;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
    public void onPalletState(final List<Pallet> p0) {
        String str;
        if (p0 != null) {
            for (Pallet pallet : p0) {
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "onPalletState palletId=" + pallet.getPalletId() + " isPowerOn=" + pallet.getIsPowerOn() + " isInstalled=" + pallet.getIsInstalled() + " isPlaced=" + pallet.getIsPlaced());
            }
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$setListener$1$onPalletState$2
            @Override // java.lang.Runnable
            public final void run() {
                List<Pallet> list = p0;
                if (list != null) {
                    TrayTestActivity$setListener$1.this.this$0.setLastEvent(list);
                    TrayTestActivity$setListener$1.this.this$0.getEvents().add(list);
                }
            }
        });
    }
}

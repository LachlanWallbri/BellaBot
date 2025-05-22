package com.pudutech.bumblebee.robot.activity;

import android.widget.CompoundButton;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.pallet.PalletProtocol;
import kotlin.Metadata;
import kotlin.UByte;

/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"<anonymous>", "", "v", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "checked", "", "onCheckedChanged", "com/pudutech/bumblebee/robot/activity/PeripheralsActivity$deliveryListener$1$onPalletState$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$deliveryListener$1$onPalletState$1$$special$$inlined$forEach$lambda$1 */
/* loaded from: classes2.dex */
final class C4162xd172edd2 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ Pallet $it;
    final /* synthetic */ PeripheralsActivity$deliveryListener$1$onPalletState$1 this$0;

    C4162xd172edd2(Pallet pallet, PeripheralsActivity$deliveryListener$1$onPalletState$1 peripheralsActivity$deliveryListener$1$onPalletState$1) {
        this.$it = pallet;
        this.this$0 = peripheralsActivity$deliveryListener$1$onPalletState$1;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String str;
        RobotInterface robotInterface;
        PeripheralDevice m4329getDevice7apg3OU = PalletProtocol.INSTANCE.m4329getDevice7apg3OU(UByte.m4528constructorimpl((byte) this.$it.getPalletId()));
        if (m4329getDevice7apg3OU != null) {
            str = this.this$0.this$0.this$0.TAG;
            Pdlog.m3275i(str, "pallet switch change when isChecked=" + z + " isPowerOn=" + this.$it.getIsPowerOn());
            if (this.$it.getIsPowerOn() == z || (robotInterface = this.this$0.this$0.this$0.getRobotInterface()) == null) {
                return;
            }
            robotInterface.setPeripheralDevicePower(m4329getDevice7apg3OU, z);
        }
    }
}

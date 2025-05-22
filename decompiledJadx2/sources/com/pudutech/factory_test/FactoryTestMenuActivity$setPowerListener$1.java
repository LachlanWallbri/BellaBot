package com.pudutech.factory_test;

import android.widget.TextView;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: FactoryTestMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/factory_test/FactoryTestMenuActivity$setPowerListener$1", "Lcom/pudutech/mirsdk/hardware/ICANData$Stub;", "onData", "", "p0", "", "p1", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class FactoryTestMenuActivity$setPowerListener$1 extends ICANData.Stub {
    final /* synthetic */ Ref.IntRef $lastPowerPercent;
    final /* synthetic */ Ref.ObjectRef $lastState;
    final /* synthetic */ FactoryTestMenuActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FactoryTestMenuActivity$setPowerListener$1(FactoryTestMenuActivity factoryTestMenuActivity, Ref.IntRef intRef, Ref.ObjectRef objectRef) {
        this.this$0 = factoryTestMenuActivity;
        this.$lastPowerPercent = intRef;
        this.$lastState = objectRef;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [T, com.pudutech.mirsdk.hardware.serialize.ChargeState] */
    @Override // com.pudutech.mirsdk.hardware.ICANData
    public void onData(int p0, byte[] p1) {
        if (p1 == null) {
            Intrinsics.throwNpe();
        }
        byte[] copyOf = Arrays.copyOf(p1, p1.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
        if (p0 == 115) {
            this.$lastPowerPercent.element = UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255;
            ?? m4433fromValue7apg3OU = ChargeState.INSTANCE.m4433fromValue7apg3OU(UByteArray.m4577getimpl(m4572constructorimpl, 1));
            if (((ChargeState) this.$lastState.element) != m4433fromValue7apg3OU) {
                this.$lastState.element = m4433fromValue7apg3OU;
            }
        }
        if (p0 == 9) {
            this.$lastPowerPercent.element = UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255;
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$setPowerListener$1$onData$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView tvPower = (TextView) FactoryTestMenuActivity$setPowerListener$1.this.this$0._$_findCachedViewById(C4491R.id.tvPower);
                Intrinsics.checkExpressionValueIsNotNull(tvPower, "tvPower");
                tvPower.setText("电量:" + FactoryTestMenuActivity$setPowerListener$1.this.$lastPowerPercent.element + "% " + ((ChargeState) FactoryTestMenuActivity$setPowerListener$1.this.$lastState.element));
            }
        });
    }
}

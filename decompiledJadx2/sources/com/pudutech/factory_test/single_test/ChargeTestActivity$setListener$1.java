package com.pudutech.factory_test.single_test;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.utils.SystemTool;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

/* compiled from: ChargeTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/factory_test/single_test/ChargeTestActivity$setListener$1", "Lcom/pudutech/mirsdk/hardware/ICANData$Stub;", "onData", "", "p0", "", "p1", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ChargeTestActivity$setListener$1 extends ICANData.Stub {
    final /* synthetic */ ChargeTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChargeTestActivity$setListener$1(ChargeTestActivity chargeTestActivity) {
        this.this$0 = chargeTestActivity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0192, code lost:
    
        if (r3 != (r4.doubleValue() > 20.0d)) goto L48;
     */
    @Override // com.pudutech.mirsdk.hardware.ICANData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onData(int p0, byte[] p1) {
        String str;
        String str2;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        sb.append(Util.toHexString(p0));
        sb.append(",p1:");
        SystemTool systemTool = SystemTool.INSTANCE;
        if (p1 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(systemTool.toHexString(p1));
        objArr[0] = sb.toString();
        Pdlog.m3276v(str, objArr);
        byte[] copyOf = Arrays.copyOf(p1, p1.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (p0 == 115) {
            ChargeState m4433fromValue7apg3OU = ChargeState.INSTANCE.m4433fromValue7apg3OU(UByteArray.m4577getimpl(m4572constructorimpl, 1));
            this.this$0.setLastPowerPercent(Integer.valueOf(UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255));
            if (this.this$0.getLastState() != m4433fromValue7apg3OU) {
                this.this$0.setLastState(m4433fromValue7apg3OU);
            }
        }
        if (p0 == 116) {
            double m4577getimpl = ((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255))) / 100.0d;
            double m4577getimpl2 = ((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255))) / 100.0d;
            this.this$0.getKeyInfo().put("充电电压", String.valueOf(m4577getimpl));
            this.this$0.getKeyInfo().put("充电电流", String.valueOf(m4577getimpl2));
            if (this.this$0.getIsChecking()) {
                ChargeTestActivity chargeTestActivity = this.this$0;
                chargeTestActivity.setSumCurrent(chargeTestActivity.getSumCurrent() + m4577getimpl2);
                ChargeTestActivity chargeTestActivity2 = this.this$0;
                chargeTestActivity2.setCnt(chargeTestActivity2.getCnt() + 1);
            }
            if (this.this$0.getLastVoltage() != null) {
                boolean z = m4577getimpl > 20.0d;
                Double lastVoltage = this.this$0.getLastVoltage();
                if (lastVoltage == null) {
                    Intrinsics.throwNpe();
                }
                if (z == (lastVoltage.doubleValue() > 20.0d)) {
                    str2 = this.this$0.TAG;
                    Pdlog.m3276v(str2, "duration:" + (elapsedRealtime - this.this$0.getLastTmpTimestamps()) + " voltage:" + m4577getimpl + ", lastVoltage:" + this.this$0.getLastVoltage());
                    if (elapsedRealtime - this.this$0.getLastTmpTimestamps() > 3000) {
                        this.this$0.setLastCurrent(m4577getimpl2);
                        if (this.this$0.getLastInvokeVoltage() != null) {
                            Double lastInvokeVoltage = this.this$0.getLastInvokeVoltage();
                            if (lastInvokeVoltage == null) {
                                Intrinsics.throwNpe();
                            }
                            boolean z2 = lastInvokeVoltage.doubleValue() > 20.0d;
                            Double lastVoltage2 = this.this$0.getLastVoltage();
                            if (lastVoltage2 == null) {
                                Intrinsics.throwNpe();
                            }
                        }
                        ChargeTestActivity chargeTestActivity3 = this.this$0;
                        chargeTestActivity3.setLastInvokeVoltage(chargeTestActivity3.getLastVoltage());
                        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$setListener$1$onData$1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Function0<Unit> onCharge = ChargeTestActivity$setListener$1.this.this$0.getOnCharge();
                                if (onCharge != null) {
                                    onCharge.invoke();
                                }
                            }
                        });
                    }
                }
            }
            this.this$0.setLastVoltage(Double.valueOf(m4577getimpl));
            this.this$0.setLastTmpTimestamps(elapsedRealtime);
        }
        if (p0 == 9) {
            this.this$0.setLastPowerPercent(Integer.valueOf(UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255));
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$setListener$1$onData$2
            @Override // java.lang.Runnable
            public final void run() {
                ChargeTestActivity$setListener$1.this.this$0.getKeyInfo().put("电量", String.valueOf(ChargeTestActivity$setListener$1.this.this$0.getLastPowerPercent()));
                ChargeTestActivity$setListener$1.this.this$0.getKeyInfo().put("充电器状态", String.valueOf(ChargeTestActivity$setListener$1.this.this$0.getLastState()));
                ChargeTestActivity$setListener$1.this.this$0.showKeyInfo(ChargeTestActivity$setListener$1.this.this$0.toStr(ChargeTestActivity$setListener$1.this.this$0.getKeyInfo()));
            }
        });
    }
}

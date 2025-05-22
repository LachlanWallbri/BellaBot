package com.pudutech.mirsdk.mirhardware;

import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BatteryState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u00020\f8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/BatteryState;", "", "()V", "LOW_POWER", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getChargeState", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setChargeState", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "isLowPower", "", "()Z", "setLowPower", "(Z)V", "percent", "", "getPercent", "()I", "setPercent", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BatteryState {
    private boolean isLowPower;
    private final double LOW_POWER = 2.0d;
    private int percent = 100;
    private ChargeState chargeState = ChargeState.Idle;

    public final int getPercent() {
        return this.percent;
    }

    public final void setPercent(int i) {
        this.percent = i;
    }

    public final void setLowPower(boolean z) {
        this.isLowPower = z;
    }

    public final boolean isLowPower() {
        return ((double) this.percent) < this.LOW_POWER;
    }

    public final ChargeState getChargeState() {
        return this.chargeState;
    }

    public final void setChargeState(ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "<set-?>");
        this.chargeState = chargeState;
    }
}

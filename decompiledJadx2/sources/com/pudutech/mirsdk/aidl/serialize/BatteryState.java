package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0013\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0014H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u00020\u000f8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\""}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/BatteryState;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "LOW_POWER", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getChargeState", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setChargeState", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "isLowPower", "", "()Z", "setLowPower", "(Z)V", "percent", "", "getPercent", "()I", "setPercent", "(I)V", "describeContents", "equals", "other", "", "hashCode", "writeToParcel", "", "flags", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BatteryState implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final double LOW_POWER;
    private ChargeState chargeState;
    private boolean isLowPower;
    private int percent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BatteryState() {
        this.LOW_POWER = 2.0d;
        this.percent = 100;
        this.chargeState = ChargeState.Idle;
    }

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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BatteryState(Parcel parcel) {
        this();
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this.percent = parcel.readInt();
        Parcelable readParcelable = parcel.readParcelable(ChargeState.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable, "parcel.readParcelable(Ch…::class.java.classLoader)");
        this.chargeState = (ChargeState) readParcelable;
    }

    public int hashCode() {
        return Integer.valueOf(this.percent).hashCode() + UByte.m4536hashCodeimpl(this.chargeState.getValue());
    }

    public boolean equals(Object other) {
        if (!(other instanceof BatteryState)) {
            return false;
        }
        BatteryState batteryState = (BatteryState) other;
        return this.percent == batteryState.percent && this.chargeState.getValue() == batteryState.chargeState.getValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.percent);
        parcel.writeParcelable(this.chargeState, flags);
    }

    /* compiled from: BatteryState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/BatteryState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/BatteryState;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/BatteryState;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.BatteryState$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes4.dex */
    public static final class Companion implements Parcelable.Creator<BatteryState> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryState createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new BatteryState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryState[] newArray(int size) {
            return new BatteryState[size];
        }
    }
}

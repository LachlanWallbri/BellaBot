package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: ChargeState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001fB\u0012\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Idle", "Charging", "ChargeFull", "ChargeErrorContact", "ChargeErrorElectric", "ErrorBatteryPackComm", "ErrorOverVolt", "ErrorOverElectric", "ErrorOverTemperature", "ErrorLowTemperature", "Retain", "CharingUsePile", "ChargeFullUsePile", "StopChargeUsePile", "ErrorOverTime", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum ChargeState implements Parcelable {
    Idle((byte) 0),
    Charging((byte) 1),
    ChargeFull((byte) 2),
    ChargeErrorContact((byte) 3),
    ChargeErrorElectric((byte) 4),
    ErrorBatteryPackComm((byte) 5),
    ErrorOverVolt((byte) 6),
    ErrorOverElectric((byte) 7),
    ErrorOverTemperature((byte) 8),
    ErrorLowTemperature((byte) 9),
    Retain((byte) 10),
    CharingUsePile((byte) 11),
    ChargeFullUsePile((byte) 12),
    StopChargeUsePile((byte) 13),
    ErrorOverTime((byte) -1);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    ChargeState(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: ChargeState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/ChargeState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "fromValue", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "fromValue-7apg3OU", "(B)Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.ChargeState$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<ChargeState> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargeState createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
            return ChargeState.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargeState[] newArray(int size) {
            return new ChargeState[size];
        }

        /* renamed from: fromValue-7apg3OU, reason: not valid java name */
        public final ChargeState m4433fromValue7apg3OU(byte value) {
            for (ChargeState chargeState : ChargeState.values()) {
                if (value == chargeState.getValue()) {
                    return chargeState;
                }
            }
            return ChargeState.Idle;
        }
    }
}

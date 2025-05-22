package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Dance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003J1\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\b\u0010 \u001a\u00020!H\u0016J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020!HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001J\u0018\u0010)\u001a\u00020*2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010+\u001a\u00020!H\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", ES6Iterator.VALUE_PROPERTY, "", "speed", "direction", "Lcom/pudutech/mirsdk/aidl/serialize/DanceDirection;", "mode", "Lcom/pudutech/mirsdk/aidl/serialize/DanceMode;", "(DDLcom/pudutech/mirsdk/aidl/serialize/DanceDirection;Lcom/pudutech/mirsdk/aidl/serialize/DanceMode;)V", "getDirection", "()Lcom/pudutech/mirsdk/aidl/serialize/DanceDirection;", "setDirection", "(Lcom/pudutech/mirsdk/aidl/serialize/DanceDirection;)V", "getMode", "()Lcom/pudutech/mirsdk/aidl/serialize/DanceMode;", "setMode", "(Lcom/pudutech/mirsdk/aidl/serialize/DanceMode;)V", "getSpeed", "()D", "setSpeed", "(D)V", "getValue", "setValue", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "flags", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Dance implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DanceDirection direction;
    private DanceMode mode;
    private double speed;
    private double value;

    public static /* synthetic */ Dance copy$default(Dance dance, double d, double d2, DanceDirection danceDirection, DanceMode danceMode, int i, Object obj) {
        if ((i & 1) != 0) {
            d = dance.value;
        }
        double d3 = d;
        if ((i & 2) != 0) {
            d2 = dance.speed;
        }
        double d4 = d2;
        if ((i & 4) != 0) {
            danceDirection = dance.direction;
        }
        DanceDirection danceDirection2 = danceDirection;
        if ((i & 8) != 0) {
            danceMode = dance.mode;
        }
        return dance.copy(d3, d4, danceDirection2, danceMode);
    }

    /* renamed from: component1, reason: from getter */
    public final double getValue() {
        return this.value;
    }

    /* renamed from: component2, reason: from getter */
    public final double getSpeed() {
        return this.speed;
    }

    /* renamed from: component3, reason: from getter */
    public final DanceDirection getDirection() {
        return this.direction;
    }

    /* renamed from: component4, reason: from getter */
    public final DanceMode getMode() {
        return this.mode;
    }

    public final Dance copy(double value, double speed, DanceDirection direction, DanceMode mode) {
        Intrinsics.checkParameterIsNotNull(direction, "direction");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        return new Dance(value, speed, direction, mode);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Dance)) {
            return false;
        }
        Dance dance = (Dance) other;
        return Double.compare(this.value, dance.value) == 0 && Double.compare(this.speed, dance.speed) == 0 && Intrinsics.areEqual(this.direction, dance.direction) && Intrinsics.areEqual(this.mode, dance.mode);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        long doubleToLongBits2 = Double.doubleToLongBits(this.speed);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2))) * 31;
        DanceDirection danceDirection = this.direction;
        int hashCode = (i + (danceDirection != null ? danceDirection.hashCode() : 0)) * 31;
        DanceMode danceMode = this.mode;
        return hashCode + (danceMode != null ? danceMode.hashCode() : 0);
    }

    public String toString() {
        return "Dance(value=" + this.value + ", speed=" + this.speed + ", direction=" + this.direction + ", mode=" + this.mode + ")";
    }

    public Dance(double d, double d2, DanceDirection direction, DanceMode mode) {
        Intrinsics.checkParameterIsNotNull(direction, "direction");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        this.value = d;
        this.speed = d2;
        this.direction = direction;
        this.mode = mode;
    }

    public final double getValue() {
        return this.value;
    }

    public final void setValue(double d) {
        this.value = d;
    }

    public final double getSpeed() {
        return this.speed;
    }

    public final void setSpeed(double d) {
        this.speed = d;
    }

    public final DanceDirection getDirection() {
        return this.direction;
    }

    public final void setDirection(DanceDirection danceDirection) {
        Intrinsics.checkParameterIsNotNull(danceDirection, "<set-?>");
        this.direction = danceDirection;
    }

    public final DanceMode getMode() {
        return this.mode;
    }

    public final void setMode(DanceMode danceMode) {
        Intrinsics.checkParameterIsNotNull(danceMode, "<set-?>");
        this.mode = danceMode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Dance(Parcel parcel) {
        this(r2, r4, (DanceDirection) r0, (DanceMode) r9);
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        double readDouble = parcel.readDouble();
        double readDouble2 = parcel.readDouble();
        Parcelable readParcelable = parcel.readParcelable(DanceDirection.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable, "parcel.readParcelable(Da…::class.java.classLoader)");
        Parcelable readParcelable2 = parcel.readParcelable(DanceMode.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable2, "parcel.readParcelable(Da…::class.java.classLoader)");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeDouble(this.value);
        parcel.writeDouble(this.speed);
        parcel.writeParcelable(this.direction, flags);
        parcel.writeParcelable(this.mode, flags);
    }

    /* compiled from: Dance.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/Dance$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.Dance$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes4.dex */
    public static final class Companion implements Parcelable.Creator<Dance> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Dance createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new Dance(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Dance[] newArray(int size) {
            return new Dance[size];
        }
    }
}

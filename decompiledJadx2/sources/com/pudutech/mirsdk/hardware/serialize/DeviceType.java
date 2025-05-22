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
/* compiled from: DeviceType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0016B\u0012\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/DeviceType;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "ESP", "RGBD", "Disinfection", "CameraIRDLED", "Lidar", "Camera", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum DeviceType implements Parcelable {
    ESP((byte) 0),
    RGBD((byte) 1),
    Disinfection((byte) 2),
    CameraIRDLED((byte) 3),
    Lidar((byte) 4),
    Camera((byte) 5);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    DeviceType(byte b) {
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
    /* compiled from: DeviceType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/DeviceType$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/DeviceType;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/DeviceType;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.DeviceType$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<DeviceType> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceType createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
            return DeviceType.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceType[] newArray(int size) {
            return new DeviceType[size];
        }
    }
}

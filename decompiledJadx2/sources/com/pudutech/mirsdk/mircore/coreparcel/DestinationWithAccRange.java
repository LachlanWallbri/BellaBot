package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DestinationWithAccRange.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "Landroid/os/Parcelable;", "floor", "", "id", "range", "", "(Ljava/lang/String;Ljava/lang/String;D)V", "getFloor", "()Ljava/lang/String;", "getId", "getRange", "()D", "describeContents", "", "writeToParcel", "", "p0", "Landroid/os/Parcel;", "p1", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DestinationWithAccRange implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String floor;
    private final String id;
    private final double range;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DestinationWithAccRange(String floor, String id, double d) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.floor = floor;
        this.id = id;
        this.range = d;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final String getId() {
        return this.id;
    }

    public final double getRange() {
        return this.range;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(this.floor);
        p0.writeString(this.id);
        p0.writeDouble(this.range);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: DestinationWithAccRange.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<DestinationWithAccRange> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DestinationWithAccRange createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            String readString = p0.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString, "p0.readString()");
            String readString2 = p0.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString2, "p0.readString()");
            return new DestinationWithAccRange(readString, readString2, p0.readDouble());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DestinationWithAccRange[] newArray(int p0) {
            return new DestinationWithAccRange[p0];
        }
    }
}

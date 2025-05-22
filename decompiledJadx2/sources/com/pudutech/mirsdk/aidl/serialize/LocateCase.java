package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LocateCase.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "", "Landroid/os/Parcelable;", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "describeContents", "writeToParcel", "", "p0", "Landroid/os/Parcel;", "p1", "Marker", "Laser", "LaserMark", "Slamware", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public enum LocateCase implements Parcelable {
    Marker(1),
    Laser(2),
    LaserMark(3),
    Slamware(4);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int id;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    LocateCase(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LocateCase.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/LocateCase$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.LocateCase$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes5.dex */
    public static final class Companion implements Parcelable.Creator<LocateCase> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocateCase createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            String readString = p0.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString, "p0.readString()");
            return LocateCase.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocateCase[] newArray(int p0) {
            return new LocateCase[p0];
        }
    }
}

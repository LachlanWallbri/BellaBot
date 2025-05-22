package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TakingType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/TakingType;", "", "Landroid/os/Parcelable;", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "ParkingTake", "QueuingTake", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum TakingType implements Parcelable {
    ParkingTake(0),
    QueuingTake(1);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int id;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    TakingType(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.id);
    }

    /* compiled from: TakingType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/TakingType$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/TakingType;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/TakingType;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.TakingType$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<TakingType> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TakingType createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return TakingType.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TakingType[] newArray(int size) {
            TakingType[] takingTypeArr = new TakingType[size];
            for (int i = 0; i < size; i++) {
                takingTypeArr[i] = TakingType.ParkingTake;
            }
            return takingTypeArr;
        }
    }
}

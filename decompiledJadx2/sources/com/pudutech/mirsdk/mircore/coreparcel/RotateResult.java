package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RotateResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0015H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult;", "Landroid/os/Parcelable;", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "angularspeed", "", "getAngularspeed", "()D", "setAngularspeed", "(D)V", "linespeed", "getLinespeed", "setLinespeed", "status", "Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;", "getStatus", "()Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;", "setStatus", "(Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;)V", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "RotateStatus", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RotateResult implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private double angularspeed;
    private double linespeed;
    private RotateStatus status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RotateResult.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0014B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;", "", "Landroid/os/Parcelable;", "id", "", "(Ljava/lang/String;IB)V", "getId", "()B", "setId", "(B)V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "Rotating", "Stuck", "Finish", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum RotateStatus implements Parcelable {
        Rotating((byte) 0),
        Stuck((byte) 1),
        Finish((byte) 2);


        /* renamed from: CREATOR, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private byte id;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        RotateStatus(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        public final void setId(byte b) {
            this.id = b;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            if (dest != null) {
                dest.writeString(name());
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: RotateResult.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$RotateStatus;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.RotateResult$RotateStatus$CREATOR, reason: from kotlin metadata */
        /* loaded from: classes6.dex */
        public static final class Companion implements Parcelable.Creator<RotateStatus> {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RotateStatus createFromParcel(Parcel source) {
                Intrinsics.checkParameterIsNotNull(source, "source");
                String readString = source.readString();
                if (readString == null) {
                    Intrinsics.throwNpe();
                }
                return RotateStatus.valueOf(readString);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RotateStatus[] newArray(int size) {
                RotateStatus[] rotateStatusArr = new RotateStatus[size];
                for (int i = 0; i < size; i++) {
                    rotateStatusArr[i] = RotateStatus.Rotating;
                }
                return rotateStatusArr;
            }
        }
    }

    public final RotateStatus getStatus() {
        return this.status;
    }

    public final void setStatus(RotateStatus rotateStatus) {
        Intrinsics.checkParameterIsNotNull(rotateStatus, "<set-?>");
        this.status = rotateStatus;
    }

    public final double getLinespeed() {
        return this.linespeed;
    }

    public final void setLinespeed(double d) {
        this.linespeed = d;
    }

    public final double getAngularspeed() {
        return this.angularspeed;
    }

    public final void setAngularspeed(double d) {
        this.angularspeed = d;
    }

    public RotateResult(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.status = RotateStatus.Rotating;
        ClassLoader classLoader = RotateStatus.class.getClassLoader();
        if (classLoader == null) {
            Intrinsics.throwNpe();
        }
        Parcelable readParcelable = source.readParcelable(classLoader);
        Intrinsics.checkExpressionValueIsNotNull(readParcelable, "source.readParcelable(Ro…class.java.classLoader!!)");
        this.status = (RotateStatus) readParcelable;
        this.linespeed = source.readDouble();
        this.angularspeed = source.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeParcelable(this.status, flags);
        dest.writeDouble(this.linespeed);
        dest.writeDouble(this.angularspeed);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RotateResult.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.RotateResult$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<RotateResult> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RotateResult createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new RotateResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RotateResult[] newArray(int size) {
            return new RotateResult[size];
        }
    }
}

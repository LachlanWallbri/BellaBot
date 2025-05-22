package com.pudutech.bumblebee.robot.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: Pallet.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\rH\u0016J\u0013\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\rH\u0016J\u0014\u0010\u001e\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0005\"\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0005\"\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "Landroid/os/Parcelable;", "()V", "isInstalled", "", "()Z", "setInstalled", "(Z)V", "isPlaced", "setPlaced", "isPowerOn", "setPowerOn", "palletId", "", "getPalletId", "()I", "setPalletId", "(I)V", "describeContents", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "writeBoolean", "b", "CREATOR", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Pallet implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean isInstalled;
    private boolean isPlaced;
    private boolean isPowerOn;
    private int palletId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getPalletId() {
        return this.palletId;
    }

    public final void setPalletId(int i) {
        this.palletId = i;
    }

    /* renamed from: isInstalled, reason: from getter */
    public final boolean getIsInstalled() {
        return this.isInstalled;
    }

    public final void setInstalled(boolean z) {
        this.isInstalled = z;
    }

    /* renamed from: isPlaced, reason: from getter */
    public final boolean getIsPlaced() {
        return this.isPlaced;
    }

    public final void setPlaced(boolean z) {
        this.isPlaced = z;
    }

    /* renamed from: isPowerOn, reason: from getter */
    public final boolean getIsPowerOn() {
        return this.isPowerOn;
    }

    public final void setPowerOn(boolean z) {
        this.isPowerOn = z;
    }

    private final void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeByte((byte) this.palletId);
            writeBoolean(dest, this.isInstalled);
            writeBoolean(dest, this.isPlaced);
            writeBoolean(dest, this.isPowerOn);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: Pallet.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot.aidl.serialize.Pallet$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion implements Parcelable.Creator<Pallet> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Pallet createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            Pallet pallet = new Pallet();
            pallet.setPalletId(parcel.readInt());
            byte b = (byte) 0;
            pallet.setInstalled(parcel.readByte() != b);
            pallet.setPlaced(parcel.readByte() != b);
            pallet.setPowerOn(parcel.readByte() != b);
            return pallet;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Pallet[] newArray(int size) {
            return new Pallet[size];
        }
    }

    public String toString() {
        return "Pallet(palletId=" + this.palletId + " isInstalled=" + this.isInstalled + " isPlaced=" + this.isPlaced + " isPowerOn=" + this.isPowerOn + ')';
    }

    public boolean equals(Object other) {
        if (other != null && (other instanceof Pallet)) {
            try {
                return this.palletId == ((Pallet) other).palletId;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        try {
            return Integer.valueOf(this.palletId).hashCode();
        } catch (Exception e) {
            e.printStackTrace();
            return super.hashCode();
        }
    }
}

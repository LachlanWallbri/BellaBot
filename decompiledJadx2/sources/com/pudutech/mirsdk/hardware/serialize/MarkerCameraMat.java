package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: MarkerCameraMat.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0007H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0007H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MarkerCameraMat;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "cols", "", "getCols", "()I", "setCols", "(I)V", "data", "", "getData", "()[B", "setData", "([B)V", "elementSize", "getElementSize", "setElementSize", "rows", "getRows", "setRows", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MarkerCameraMat implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int cols;
    private byte[] data;
    private int elementSize;
    private int rows;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getRows() {
        return this.rows;
    }

    public final void setRows(int i) {
        this.rows = i;
    }

    public final int getCols() {
        return this.cols;
    }

    public final void setCols(int i) {
        this.cols = i;
    }

    public final int getElementSize() {
        return this.elementSize;
    }

    public final void setElementSize(int i) {
        this.elementSize = i;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final void setData(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.data = bArr;
    }

    public MarkerCameraMat() {
        this.data = new byte[0];
        this.rows = 0;
        this.cols = 0;
        this.elementSize = 0;
        this.data = new byte[0];
    }

    public MarkerCameraMat(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.data = new byte[0];
        this.rows = source.readInt();
        this.cols = source.readInt();
        this.elementSize = source.readInt();
        byte[] createByteArray = source.createByteArray();
        if (createByteArray == null) {
            Intrinsics.throwNpe();
        }
        this.data = createByteArray;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeInt(this.rows);
        dest.writeInt(this.cols);
        dest.writeInt(this.elementSize);
        dest.writeByteArray(this.data);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MarkerCameraMat.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MarkerCameraMat$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/MarkerCameraMat;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/MarkerCameraMat;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.MarkerCameraMat$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<MarkerCameraMat> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarkerCameraMat createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new MarkerCameraMat(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarkerCameraMat[] newArray(int size) {
            MarkerCameraMat[] markerCameraMatArr = new MarkerCameraMat[size];
            for (int i = 0; i < size; i++) {
                markerCameraMatArr[i] = new MarkerCameraMat();
            }
            return markerCameraMatArr;
        }
    }
}

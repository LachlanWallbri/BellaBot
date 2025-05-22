package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruisePath.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u0016H\u0016R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "nodes", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Node;", "getNodes", "()[Lcom/pudutech/mirsdk/mircore/coreparcel/Node;", "setNodes", "([Lcom/pudutech/mirsdk/mircore/coreparcel/Node;)V", "[Lcom/pudutech/mirsdk/mircore/coreparcel/Node;", "tracks", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getTracks", "()[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setTracks", "([Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruisePath implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Node[] nodes;
    private Vector3d[] tracks;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Vector3d[] getTracks() {
        return this.tracks;
    }

    public final void setTracks(Vector3d[] vector3dArr) {
        Intrinsics.checkParameterIsNotNull(vector3dArr, "<set-?>");
        this.tracks = vector3dArr;
    }

    public final Node[] getNodes() {
        return this.nodes;
    }

    public final void setNodes(Node[] nodeArr) {
        Intrinsics.checkParameterIsNotNull(nodeArr, "<set-?>");
        this.nodes = nodeArr;
    }

    public CruisePath() {
        this.tracks = new Vector3d[0];
        this.nodes = new Node[0];
        this.tracks = new Vector3d[0];
        this.nodes = new Node[0];
    }

    public CruisePath(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.tracks = new Vector3d[0];
        this.nodes = new Node[0];
        Object[] createTypedArray = source.createTypedArray(Vector3d.INSTANCE);
        if (createTypedArray == null) {
            Intrinsics.throwNpe();
        }
        this.tracks = (Vector3d[]) createTypedArray;
        Object[] createTypedArray2 = source.createTypedArray(Node.INSTANCE);
        if (createTypedArray2 == null) {
            Intrinsics.throwNpe();
        }
        this.nodes = (Node[]) createTypedArray2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeTypedArray(this.tracks, flags);
        }
        if (dest != null) {
            dest.writeTypedArray(this.nodes, flags);
        }
    }

    /* compiled from: CruisePath.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.CruisePath$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes4.dex */
    public static final class Companion implements Parcelable.Creator<CruisePath> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CruisePath[] newArray(int size) {
            return new CruisePath[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CruisePath createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new CruisePath(source);
        }
    }
}

package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PathSegment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B)\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u001cH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegment;", "Landroid/os/Parcelable;", "p0", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "curGoal", "", "deviceId", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "navMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;)V", "getCurGoal", "()Ljava/lang/String;", "setCurGoal", "(Ljava/lang/String;)V", "getDeviceId", "setDeviceId", "getNavMode", "()Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "setNavMode", "(Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;)V", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "describeContents", "", "writeToParcel", "", "p1", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PathSegment implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String curGoal;
    private String deviceId;
    private NavigationMode navMode;
    private Vector3d pose;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PathSegment(String str, String str2, Vector3d pose, NavigationMode navMode) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(navMode, "navMode");
        this.curGoal = str;
        this.deviceId = str2;
        this.pose = pose;
        this.navMode = navMode;
    }

    public final String getCurGoal() {
        return this.curGoal;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final NavigationMode getNavMode() {
        return this.navMode;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setCurGoal(String str) {
        this.curGoal = str;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final void setNavMode(NavigationMode navigationMode) {
        Intrinsics.checkParameterIsNotNull(navigationMode, "<set-?>");
        this.navMode = navigationMode;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(this.curGoal);
        p0.writeString(this.deviceId);
        p0.writeParcelable(this.pose, p1);
        p0.writeParcelable(this.navMode, p1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PathSegment(Parcel p0) {
        this(r0, r1, (Vector3d) r2, (NavigationMode) r5);
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        String readString = p0.readString();
        String readString2 = p0.readString();
        Parcelable readParcelable = p0.readParcelable(Vector3d.INSTANCE.getClass().getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable, "p0.readParcelable(Vector3d.javaClass.classLoader)");
        Parcelable readParcelable2 = p0.readParcelable(NavigationMode.INSTANCE.getClass().getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable2, "p0.readParcelable(Naviga…de.javaClass.classLoader)");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: PathSegment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegment$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegment;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegment;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.PathSegment$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<PathSegment> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PathSegment createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            return new PathSegment(p0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PathSegment[] newArray(int p0) {
            return new PathSegment[p0];
        }
    }
}

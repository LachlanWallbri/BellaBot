package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PathSegments.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B5\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020 H\u0016R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006%"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "Landroid/os/Parcelable;", "p0", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "dstFloor", "", "curFloor", "finalGoal", "range", "", "segments", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegment;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/util/List;)V", "getCurFloor", "()Ljava/lang/String;", "setCurFloor", "(Ljava/lang/String;)V", "getDstFloor", "setDstFloor", "getFinalGoal", "setFinalGoal", "getRange", "()D", "setRange", "(D)V", "getSegments", "()Ljava/util/List;", "setSegments", "(Ljava/util/List;)V", "describeContents", "", "writeToParcel", "", "p1", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PathSegments implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String curFloor;
    private String dstFloor;
    private String finalGoal;
    private double range;
    private List<PathSegment> segments;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PathSegments(String dstFloor, String curFloor, String finalGoal, double d, List<PathSegment> list) {
        Intrinsics.checkParameterIsNotNull(dstFloor, "dstFloor");
        Intrinsics.checkParameterIsNotNull(curFloor, "curFloor");
        Intrinsics.checkParameterIsNotNull(finalGoal, "finalGoal");
        this.dstFloor = dstFloor;
        this.curFloor = curFloor;
        this.finalGoal = finalGoal;
        this.range = d;
        this.segments = list;
    }

    public final String getCurFloor() {
        return this.curFloor;
    }

    public final String getDstFloor() {
        return this.dstFloor;
    }

    public final String getFinalGoal() {
        return this.finalGoal;
    }

    public final double getRange() {
        return this.range;
    }

    public final List<PathSegment> getSegments() {
        return this.segments;
    }

    public final void setCurFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.curFloor = str;
    }

    public final void setDstFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.dstFloor = str;
    }

    public final void setFinalGoal(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.finalGoal = str;
    }

    public final void setRange(double d) {
        this.range = d;
    }

    public final void setSegments(List<PathSegment> list) {
        this.segments = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(this.dstFloor);
        p0.writeString(this.curFloor);
        p0.writeString(this.finalGoal);
        p0.writeDouble(this.range);
        p0.writeList(this.segments);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PathSegments(Parcel p0) {
        this(r2, r3, r4, p0.readDouble(), null);
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        String readString = p0.readString();
        if (readString == null) {
            Intrinsics.throwNpe();
        }
        String readString2 = p0.readString();
        if (readString2 == null) {
            Intrinsics.throwNpe();
        }
        String readString3 = p0.readString();
        if (readString3 == null) {
            Intrinsics.throwNpe();
        }
        p0.readList(this.segments, PathSegment.INSTANCE.getClass().getClassLoader());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: PathSegments.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.PathSegments$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<PathSegments> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PathSegments createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            return new PathSegments(p0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PathSegments[] newArray(int p0) {
            return new PathSegments[p0];
        }
    }
}

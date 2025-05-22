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
/* compiled from: NavigationResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B-\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0019H\u0016R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "Landroid/os/Parcelable;", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "linear_vel", "", "angular_vel", "cost", "navigation_status", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;", "(DDDLcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;)V", "getAngular_vel", "()D", "setAngular_vel", "(D)V", "getCost", "setCost", "getLinear_vel", "setLinear_vel", "getNavigation_status", "()Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;", "setNavigation_status", "(Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;)V", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NavigationResult implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private double angular_vel;
    private double cost;
    private double linear_vel;
    private NavigationStatus navigation_status;

    public NavigationResult() {
        this(0.0d, 0.0d, 0.0d, null, 15, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NavigationResult(double d, double d2, double d3, NavigationStatus navigation_status) {
        Intrinsics.checkParameterIsNotNull(navigation_status, "navigation_status");
        this.linear_vel = d;
        this.angular_vel = d2;
        this.cost = d3;
        this.navigation_status = navigation_status;
    }

    public final double getLinear_vel() {
        return this.linear_vel;
    }

    public final void setLinear_vel(double d) {
        this.linear_vel = d;
    }

    public final double getAngular_vel() {
        return this.angular_vel;
    }

    public final void setAngular_vel(double d) {
        this.angular_vel = d;
    }

    public final double getCost() {
        return this.cost;
    }

    public final void setCost(double d) {
        this.cost = d;
    }

    public /* synthetic */ NavigationResult(double d, double d2, double d3, NavigationStatus navigationStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2, (i & 4) == 0 ? d3 : 0.0d, (i & 8) != 0 ? NavigationStatus.Navigating : navigationStatus);
    }

    public final NavigationStatus getNavigation_status() {
        return this.navigation_status;
    }

    public final void setNavigation_status(NavigationStatus navigationStatus) {
        Intrinsics.checkParameterIsNotNull(navigationStatus, "<set-?>");
        this.navigation_status = navigationStatus;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public NavigationResult(Parcel source) {
        this(r2, r4, r6, (NavigationStatus) r10);
        Intrinsics.checkParameterIsNotNull(source, "source");
        double readDouble = source.readDouble();
        double readDouble2 = source.readDouble();
        double readDouble3 = source.readDouble();
        Parcelable readParcelable = source.readParcelable(NavigationStatus.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeDouble(this.linear_vel);
        dest.writeDouble(this.angular_vel);
        dest.writeDouble(this.cost);
        dest.writeParcelable(this.navigation_status, flags);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: NavigationResult.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.NavigationResult$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<NavigationResult> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationResult createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new NavigationResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationResult[] newArray(int size) {
            return new NavigationResult[size];
        }
    }
}

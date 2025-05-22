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
/* compiled from: LocalizationStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B%\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0019H\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "Landroid/os/Parcelable;", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "status_level", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusLevel;", "status_info", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusInfo;", "status_description", "", "(Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusLevel;Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusInfo;Ljava/lang/String;)V", "getStatus_description", "()Ljava/lang/String;", "setStatus_description", "(Ljava/lang/String;)V", "getStatus_info", "()Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusInfo;", "setStatus_info", "(Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusInfo;)V", "getStatus_level", "()Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusLevel;", "setStatus_level", "(Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatusLevel;)V", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LocalizationStatus implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String status_description;
    private LocalizationStatusInfo status_info;
    private LocalizationStatusLevel status_level;

    public LocalizationStatus() {
        this(null, null, null, 7, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LocalizationStatus(LocalizationStatusLevel status_level, LocalizationStatusInfo status_info, String str) {
        Intrinsics.checkParameterIsNotNull(status_level, "status_level");
        Intrinsics.checkParameterIsNotNull(status_info, "status_info");
        this.status_level = status_level;
        this.status_info = status_info;
        this.status_description = str;
    }

    public /* synthetic */ LocalizationStatus(LocalizationStatusLevel localizationStatusLevel, LocalizationStatusInfo localizationStatusInfo, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? LocalizationStatusLevel.Normal : localizationStatusLevel, (i & 2) != 0 ? LocalizationStatusInfo.Normal : localizationStatusInfo, (i & 4) != 0 ? "" : str);
    }

    public final LocalizationStatusLevel getStatus_level() {
        return this.status_level;
    }

    public final void setStatus_level(LocalizationStatusLevel localizationStatusLevel) {
        Intrinsics.checkParameterIsNotNull(localizationStatusLevel, "<set-?>");
        this.status_level = localizationStatusLevel;
    }

    public final LocalizationStatusInfo getStatus_info() {
        return this.status_info;
    }

    public final void setStatus_info(LocalizationStatusInfo localizationStatusInfo) {
        Intrinsics.checkParameterIsNotNull(localizationStatusInfo, "<set-?>");
        this.status_info = localizationStatusInfo;
    }

    public final String getStatus_description() {
        return this.status_description;
    }

    public final void setStatus_description(String str) {
        this.status_description = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public LocalizationStatus(Parcel source) {
        this(r2, (LocalizationStatusInfo) r8, null, 4, null);
        Intrinsics.checkParameterIsNotNull(source, "source");
        Parcelable readParcelable = source.readParcelable(LocalizationStatusLevel.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        LocalizationStatusLevel localizationStatusLevel = (LocalizationStatusLevel) readParcelable;
        Parcelable readParcelable2 = source.readParcelable(LocalizationStatusInfo.class.getClassLoader());
        if (readParcelable2 == null) {
            Intrinsics.throwNpe();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeParcelable(this.status_level, flags);
        dest.writeParcelable(this.status_info, flags);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LocalizationStatus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<LocalizationStatus> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalizationStatus createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new LocalizationStatus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalizationStatus[] newArray(int size) {
            return new LocalizationStatus[size];
        }
    }
}

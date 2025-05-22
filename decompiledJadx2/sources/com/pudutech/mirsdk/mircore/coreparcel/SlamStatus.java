package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SlamStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0086\u0001\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001(B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'¨\u0006)"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;IS)V", "getValue", "()S", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "Success", "NoImage", "NoMarker", "NoOdom", "NoInit", "ErrorMap", "NoParam", "DuplicateData", "OnlySaveData", "ErrorMove", "ParamError", "NoMarkerVersion", "NoMarkerInMap", "CalibError", "CalibCompleted", "LaserSuccess", "LaserNoLaser", "LaserNoOdom", "LaserNoInit", "LaserErrorMap", "LaserNoParam", "LaserLocateLose", "LaserParamError", "LaserOnlySaveData", "LaserLocateLostRecovering", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum SlamStatus implements Parcelable {
    Success(0),
    NoImage(1),
    NoMarker(2),
    NoOdom(3),
    NoInit(4),
    ErrorMap(5),
    NoParam(6),
    DuplicateData(7),
    OnlySaveData(8),
    ErrorMove(9),
    ParamError(10),
    NoMarkerVersion(11),
    NoMarkerInMap(12),
    CalibError(13),
    CalibCompleted(14),
    LaserSuccess(256),
    LaserNoLaser(257),
    LaserNoOdom(258),
    LaserNoInit(259),
    LaserErrorMap(260),
    LaserNoParam(261),
    LaserLocateLose(262),
    LaserParamError(263),
    LaserOnlySaveData(264),
    LaserLocateLostRecovering(265);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final short value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    SlamStatus(short s) {
        this.value = s;
    }

    public final short getValue() {
        return this.value;
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
    /* compiled from: SlamStatus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.SlamStatus$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<SlamStatus> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SlamStatus createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            String readString = source.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return SlamStatus.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SlamStatus[] newArray(int size) {
            return new SlamStatus[size];
        }
    }
}

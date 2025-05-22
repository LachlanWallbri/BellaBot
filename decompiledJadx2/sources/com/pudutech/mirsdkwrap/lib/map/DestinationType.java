package com.pudutech.mirsdkwrap.lib.map;

import androidx.core.os.EnvironmentCompat;
import com.pudutech.freeinstall_ui.utils.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DestinationType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "", "typeName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTypeName", "()Ljava/lang/String;", "UNKNOWN", "TABLE", "DINING_OUTLET", "TRANSLIST", "DISHWASHING", "USHER", "CHARGE_PILE", "Companion", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum DestinationType {
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    TABLE(Constants.POINT_TYPE_TABLE),
    DINING_OUTLET("dining_outlet"),
    TRANSLIST(Constants.POINT_TYPE_BACK_INVENTORY),
    DISHWASHING("dishwashing"),
    USHER(Constants.POINT_TYPE_DOOR),
    CHARGE_PILE("chargePile");


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String typeName;

    DestinationType(String str) {
        this.typeName = str;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: DestinationType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/DestinationType$Companion;", "", "()V", "getTypeByName", "Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "t", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DestinationType getTypeByName(String t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            if (Intrinsics.areEqual(t, DestinationType.UNKNOWN.getTypeName())) {
                return DestinationType.UNKNOWN;
            }
            if (Intrinsics.areEqual(t, DestinationType.TABLE.getTypeName())) {
                return DestinationType.TABLE;
            }
            if (Intrinsics.areEqual(t, DestinationType.DINING_OUTLET.getTypeName())) {
                return DestinationType.DINING_OUTLET;
            }
            if (Intrinsics.areEqual(t, DestinationType.TRANSLIST.getTypeName())) {
                return DestinationType.TRANSLIST;
            }
            if (Intrinsics.areEqual(t, DestinationType.DISHWASHING.getTypeName())) {
                return DestinationType.DISHWASHING;
            }
            if (Intrinsics.areEqual(t, DestinationType.USHER.getTypeName())) {
                return DestinationType.USHER;
            }
            if (Intrinsics.areEqual(t, DestinationType.CHARGE_PILE.getTypeName())) {
                return DestinationType.CHARGE_PILE;
            }
            return null;
        }
    }
}

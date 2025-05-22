package com.pudutech.freeinstall_ui.bean;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/bean/CruiseDestination;", "", "id", "", "vector3s", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "(Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getVector3s", "()Ljava/util/List;", "setVector3s", "(Ljava/util/List;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class CruiseDestination {
    private String id;
    private List<Vector3d> vector3s;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CruiseDestination copy$default(CruiseDestination cruiseDestination, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cruiseDestination.id;
        }
        if ((i & 2) != 0) {
            list = cruiseDestination.vector3s;
        }
        return cruiseDestination.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<Vector3d> component2() {
        return this.vector3s;
    }

    public final CruiseDestination copy(String id, List<Vector3d> vector3s) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(vector3s, "vector3s");
        return new CruiseDestination(id, vector3s);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CruiseDestination)) {
            return false;
        }
        CruiseDestination cruiseDestination = (CruiseDestination) other;
        return Intrinsics.areEqual(this.id, cruiseDestination.id) && Intrinsics.areEqual(this.vector3s, cruiseDestination.vector3s);
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<Vector3d> list = this.vector3s;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "CruiseDestination(id=" + this.id + ", vector3s=" + this.vector3s + ")";
    }

    public CruiseDestination(String id, List<Vector3d> vector3s) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(vector3s, "vector3s");
        this.id = id;
        this.vector3s = vector3s;
    }

    public final String getId() {
        return this.id;
    }

    public final List<Vector3d> getVector3s() {
        return this.vector3s;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setVector3s(List<Vector3d> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.vector3s = list;
    }
}

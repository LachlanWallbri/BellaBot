package com.pudutech.mirsdkwrap.lib.map;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapCruiseLine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u0007HÆ\u0003JU\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\u001c\b\u0002\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR.\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "", "name", "", "lines", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "Lkotlin/collections/ArrayList;", "id", "stayPoint", "Lcom/pudutech/mirsdkwrap/lib/map/StayPoint;", "(Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;)V", "getId", "()Ljava/lang/String;", "getLines", "()Ljava/util/ArrayList;", "getName", "getStayPoint", "setStayPoint", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MapCruiseLine {
    private final String id;
    private final ArrayList<MapLine> lines;
    private final String name;
    private ArrayList<StayPoint> stayPoint;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MapCruiseLine copy$default(MapCruiseLine mapCruiseLine, String str, ArrayList arrayList, String str2, ArrayList arrayList2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mapCruiseLine.name;
        }
        if ((i & 2) != 0) {
            arrayList = mapCruiseLine.lines;
        }
        if ((i & 4) != 0) {
            str2 = mapCruiseLine.id;
        }
        if ((i & 8) != 0) {
            arrayList2 = mapCruiseLine.stayPoint;
        }
        return mapCruiseLine.copy(str, arrayList, str2, arrayList2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final ArrayList<MapLine> component2() {
        return this.lines;
    }

    /* renamed from: component3, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final ArrayList<StayPoint> component4() {
        return this.stayPoint;
    }

    public final MapCruiseLine copy(String name, ArrayList<MapLine> lines, String id, ArrayList<StayPoint> stayPoint) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lines, "lines");
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new MapCruiseLine(name, lines, id, stayPoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapCruiseLine)) {
            return false;
        }
        MapCruiseLine mapCruiseLine = (MapCruiseLine) other;
        return Intrinsics.areEqual(this.name, mapCruiseLine.name) && Intrinsics.areEqual(this.lines, mapCruiseLine.lines) && Intrinsics.areEqual(this.id, mapCruiseLine.id) && Intrinsics.areEqual(this.stayPoint, mapCruiseLine.stayPoint);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<MapLine> arrayList = this.lines;
        int hashCode2 = (hashCode + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        String str2 = this.id;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        ArrayList<StayPoint> arrayList2 = this.stayPoint;
        return hashCode3 + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public String toString() {
        return "MapCruiseLine(name=" + this.name + ", lines=" + this.lines + ", id=" + this.id + ", stayPoint=" + this.stayPoint + ")";
    }

    public MapCruiseLine(String name, ArrayList<MapLine> lines, String id, ArrayList<StayPoint> arrayList) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lines, "lines");
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.name = name;
        this.lines = lines;
        this.id = id;
        this.stayPoint = arrayList;
    }

    public /* synthetic */ MapCruiseLine(String str, ArrayList arrayList, String str2, ArrayList arrayList2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, arrayList, str2, (i & 8) != 0 ? (ArrayList) null : arrayList2);
    }

    public final String getId() {
        return this.id;
    }

    public final ArrayList<MapLine> getLines() {
        return this.lines;
    }

    public final String getName() {
        return this.name;
    }

    public final ArrayList<StayPoint> getStayPoint() {
        return this.stayPoint;
    }

    public final void setStayPoint(ArrayList<StayPoint> arrayList) {
        this.stayPoint = arrayList;
    }
}

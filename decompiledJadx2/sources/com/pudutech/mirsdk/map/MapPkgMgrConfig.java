package com.pudutech.mirsdk.map;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapPkgMgrConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapPkgMgrConfig;", "", "def_floor", "", "defmap", "list", "", "Lcom/pudutech/mirsdk/map/MapListConfig;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDef_floor", "()Ljava/lang/String;", "setDef_floor", "(Ljava/lang/String;)V", "getDefmap", "setDefmap", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapPkgMgrConfig {
    private String def_floor;
    private String defmap;
    private List<MapListConfig> list;

    public MapPkgMgrConfig(String str, String str2, List<MapListConfig> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.def_floor = str;
        this.defmap = str2;
        this.list = list;
    }

    public /* synthetic */ MapPkgMgrConfig(String str, String str2, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? new ArrayList() : arrayList);
    }

    public final String getDef_floor() {
        return this.def_floor;
    }

    public final String getDefmap() {
        return this.defmap;
    }

    public final List<MapListConfig> getList() {
        return this.list;
    }

    public final void setDef_floor(String str) {
        this.def_floor = str;
    }

    public final void setDefmap(String str) {
        this.defmap = str;
    }

    public final void setList(List<MapListConfig> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }
}

package com.pudutech.mirsdk.map;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapPackageConfig.kt */
@Deprecated(message = "conflict with aidl define, use MapPkgMgrConfig")
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapPackageConfig;", "", "def_floor", "", "list", "", "Lcom/pudutech/mirsdk/map/MapListConfig;", "(Ljava/lang/String;Ljava/util/List;)V", "getDef_floor", "()Ljava/lang/String;", "setDef_floor", "(Ljava/lang/String;)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapPackageConfig {
    private String def_floor;
    private List<MapListConfig> list;

    public MapPackageConfig(String str, List<MapListConfig> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.def_floor = str;
        this.list = list;
    }

    public /* synthetic */ MapPackageConfig(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final String getDef_floor() {
        return this.def_floor;
    }

    public final List<MapListConfig> getList() {
        return this.list;
    }

    public final void setDef_floor(String str) {
        this.def_floor = str;
    }

    public final void setList(List<MapListConfig> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }
}

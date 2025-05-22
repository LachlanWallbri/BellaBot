package com.pudutech.mirsdk.mapify.mapdata;

import com.google.gson.annotations.SerializedName;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapModules.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/MapModules;", "", "()V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", MapElement.Key.MAP, "", "Lcom/pudutech/mirsdk/mapify/mapdata/Map;", "getMap", "()Ljava/util/List;", "setMap", "(Ljava/util/List;)V", "name", "setName", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapModules {
    private String id = "0";

    @SerializedName("Map")
    private List<Map> map;
    private String name;

    public MapModules() {
        this.map = new ArrayList();
        this.map = new ArrayList();
    }

    public final List<Map> getMap() {
        return this.map;
    }

    public final void setMap(List<Map> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.map = list;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setName(String name) {
        this.name = name;
    }
}

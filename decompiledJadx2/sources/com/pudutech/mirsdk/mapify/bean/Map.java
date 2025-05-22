package com.pudutech.mirsdk.mapify.bean;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Map.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/bean/Map;", "", "()V", MapElement.Key.ELEMENTS, "", "Lcom/pudutech/mirsdk/mapify/bean/Elements;", "getElements", "()Ljava/util/List;", "setElements", "(Ljava/util/List;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Map {
    private List<Elements> elements = new ArrayList();

    public final List<Elements> getElements() {
        return this.elements;
    }

    public final void setElements(List<Elements> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.elements = list;
    }
}

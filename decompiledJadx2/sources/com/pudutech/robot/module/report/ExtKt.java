package com.pudutech.robot.module.report;

import com.pudutech.pd_network.report.utils.GsonUtils;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"toPairArray", "", "Lkotlin/Pair;", "", "", "(Ljava/lang/Object;)[Lkotlin/Pair;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ExtKt {
    public static final Pair<String, Object>[] toPairArray(Object toPairArray) {
        Intrinsics.checkParameterIsNotNull(toPairArray, "$this$toPairArray");
        Map<String, Object> gsonToMaps = GsonUtils.gsonToMaps(GsonUtils.toJson(toPairArray));
        Intrinsics.checkExpressionValueIsNotNull(gsonToMaps, "GsonUtils.gsonToMaps(GsonUtils.toJson(this))");
        ArrayList arrayList = new ArrayList(gsonToMaps.size());
        for (Map.Entry<String, Object> entry : gsonToMaps.entrySet()) {
            arrayList.add(TuplesKt.m3968to(entry.getKey(), entry.getValue()));
        }
        Object[] array = arrayList.toArray(new Pair[0]);
        if (array != null) {
            return (Pair[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}

package com.pudutech.lidar.eai_tg30;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TG30_Filter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001\u001a\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¨\u0006\f"}, m3961d2 = {"calculateTargetOffset", "", "last", "Lcom/pudutech/lidar/LidarNode;", "current", "isRangeValid", "", "rang", "tg30Filter", "", "input", "", "LidarLib_mirRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TG30_FilterKt {
    public static final boolean isRangeValid(double d) {
        return d > 0.05d && d < 32.0d;
    }

    public static final void tg30Filter(List<? extends LidarNode> input) {
        double d;
        Intrinsics.checkParameterIsNotNull(input, "input");
        Pdlog.m3273d("tg30Filter", "tg30Filter");
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        SystemClock.elapsedRealtime();
        ArrayList arrayList = new ArrayList(input.size());
        int size = input.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Info());
        }
        LidarNode lidarNode = null;
        int size2 = input.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                i2 = -1;
                break;
            }
            ((Info) arrayList.get(i2)).setValid(isRangeValid(input.get(i2).distance_m));
            if (((Info) arrayList.get(i2)).getIsValid()) {
                lidarNode = input.get(i2);
                break;
            }
            i2++;
        }
        if (i2 < 0 || CollectionsKt.getLastIndex(input) - i2 < 2) {
            return;
        }
        int lastIndex = CollectionsKt.getLastIndex(input);
        int i3 = i2;
        while (true) {
            d = 0.2d;
            if (i3 >= lastIndex) {
                break;
            }
            long j = currentThreadTimeMillis;
            ((Info) arrayList.get(i3)).setValid(isRangeValid(input.get(i3).distance_m));
            if (((Info) arrayList.get(i3)).getIsValid()) {
                Info info = (Info) arrayList.get(i3);
                if (lidarNode == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lastValidPoint");
                }
                info.setOffset(calculateTargetOffset(lidarNode, input.get(i3)));
                ((Info) arrayList.get(i3)).setDiff(Math.abs(lidarNode.distance_m - input.get(i3).distance_m));
                ((Info) arrayList.get(i3)).setMaxOffsetDistance(input.get(i3).distance_m * 0.2d);
                lidarNode = input.get(i3);
            }
            i3++;
            currentThreadTimeMillis = j;
        }
        long j2 = currentThreadTimeMillis;
        ArrayList<FilterRange> arrayList2 = new ArrayList();
        Object obj = arrayList.get(i2);
        Intrinsics.checkExpressionValueIsNotNull(obj, "scanInfo[firstValidIndex]");
        Info info2 = (Info) obj;
        int lastIndex2 = CollectionsKt.getLastIndex(input);
        double d2 = 0.0d;
        int i4 = -1;
        while (i2 < lastIndex2) {
            if (((Info) arrayList.get(i2)).getIsValid()) {
                if (i4 < 0) {
                    if (((Info) arrayList.get(i2)).getDiff() > info2.getMaxOffsetDistance() && ((Info) arrayList.get(i2)).getOffset() < d) {
                        d2 = ((Info) arrayList.get(i2)).getOffset() + 0.05d;
                        i4 = i2;
                    }
                    Object obj2 = arrayList.get(i2);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "scanInfo[i]");
                    info2 = (Info) obj2;
                } else {
                    Object obj3 = arrayList.get(i2);
                    Intrinsics.checkExpressionValueIsNotNull(obj3, "scanInfo[i]");
                    info2 = (Info) obj3;
                    if (((Info) arrayList.get(i2)).getOffset() > d2) {
                        FilterRange filterRange = new FilterRange();
                        filterRange.setStartIndex(i4 - 4);
                        filterRange.setEndIndex(i2 + 4);
                        if (filterRange.getStartIndex() < 0) {
                            filterRange.setStartIndex(0);
                        }
                        if (filterRange.getEndIndex() > CollectionsKt.getLastIndex(input)) {
                            filterRange.setEndIndex(CollectionsKt.getLastIndex(input));
                        }
                        filterRange.setFilterOffset(d2);
                        arrayList2.add(filterRange);
                        i4 = -1;
                    }
                }
            }
            i2++;
            d = 0.2d;
        }
        for (FilterRange filterRange2 : arrayList2) {
            int endIndex = filterRange2.getEndIndex();
            for (int startIndex = filterRange2.getStartIndex(); startIndex < endIndex; startIndex++) {
                if (((Info) arrayList.get(startIndex)).getOffset() < filterRange2.getFilterOffset()) {
                    input.get(startIndex).distance_m = 0.0d;
                }
            }
        }
        Pdlog.m3273d("tg30Filter", "filter end. spend=" + (SystemClock.currentThreadTimeMillis() - j2) + "ms, filterRanges=" + arrayList2.size());
    }

    public static final double calculateTargetOffset(LidarNode last, LidarNode current) {
        Intrinsics.checkParameterIsNotNull(last, "last");
        Intrinsics.checkParameterIsNotNull(current, "current");
        double abs = Math.abs(last.angleInRad - current.angleInRad);
        if (abs == 0.0d) {
            return last.distance_m;
        }
        double cos = last.distance_m - (current.distance_m * Math.cos(abs));
        double sin = current.distance_m * Math.sin(abs);
        return (sin * last.distance_m) / Math.sqrt((cos * cos) + (sin * sin));
    }
}

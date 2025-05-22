package com.pudutech.lidar.eaitg30;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Tg30Filter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0016\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005\u001a,\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u001aF\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\f2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\u0011\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0002\u001aF\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\f2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0002\u001a\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0001\u001a\u0014\u0010\u0018\u001a\u00020\u00132\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"JITTER_OFFSET_DISTANCE", "", "MAX_OFFSET_DISTANCE", "calculateTargetOffset", "last", "Lcom/pudutech/lidar/LidarNode;", "current", "findFirstValidIndex", "", "scanInfo", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/eaitg30/Info;", "Lkotlin/collections/ArrayList;", "input", "", "findTrailing", "Lcom/pudutech/lidar/eaitg30/FilterRange;", "firstValidIndex", "handleTrailing", "", "filterRanges", "isRangeValid", "", "rang", "tg30Filter", "LidarLib_mirRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Tg30FilterKt {
    public static final double JITTER_OFFSET_DISTANCE = 0.05d;
    public static final double MAX_OFFSET_DISTANCE = 0.2d;

    public static final boolean isRangeValid(double d) {
        return d > 0.05d && d < 32.0d;
    }

    public static final void tg30Filter(List<? extends LidarNode> input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Pdlog.m3273d("tg30Filter", "tg30Filter");
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        ArrayList arrayList = new ArrayList(input.size());
        int size = input.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Info());
        }
        int findFirstValidIndex = findFirstValidIndex(arrayList, input);
        if (findFirstValidIndex == -1) {
            return;
        }
        ArrayList<FilterRange> findTrailing = findTrailing(arrayList, findFirstValidIndex, input);
        handleTrailing(findTrailing, arrayList, input);
        Pdlog.m3273d("tg30Filter", "filter end. spend=" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis) + "ms, filterRanges=" + findTrailing.size());
    }

    public static final int findFirstValidIndex(ArrayList<Info> scanInfo, List<? extends LidarNode> input) {
        LidarNode lidarNode;
        Intrinsics.checkParameterIsNotNull(scanInfo, "scanInfo");
        Intrinsics.checkParameterIsNotNull(input, "input");
        int size = input.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                lidarNode = null;
                i = -1;
                break;
            }
            scanInfo.get(i).setValid(isRangeValid(input.get(i).distanceM));
            if (scanInfo.get(i).getIsValid()) {
                lidarNode = input.get(i);
                break;
            }
            i++;
        }
        if (i < 0 || CollectionsKt.getLastIndex(input) - i < 2) {
            return -1;
        }
        int lastIndex = CollectionsKt.getLastIndex(input);
        LidarNode lidarNode2 = lidarNode;
        for (int i2 = i; i2 < lastIndex; i2++) {
            scanInfo.get(i2).setValid(isRangeValid(input.get(i2).distanceM));
            if (scanInfo.get(i2).getIsValid()) {
                Info info = scanInfo.get(i2);
                if (lidarNode2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lastValidPoint");
                }
                info.setOffset(calculateTargetOffset(lidarNode2, input.get(i2)));
                scanInfo.get(i2).setDiff(Math.abs(lidarNode2.distanceM - input.get(i2).distanceM));
                scanInfo.get(i2).setMaxOffsetDistance(input.get(i2).distanceM * 0.2d);
                lidarNode2 = input.get(i2);
            }
        }
        return i;
    }

    private static final ArrayList<FilterRange> findTrailing(ArrayList<Info> arrayList, int i, List<? extends LidarNode> list) {
        ArrayList<FilterRange> arrayList2 = new ArrayList<>();
        Info info = arrayList.get(i);
        Intrinsics.checkExpressionValueIsNotNull(info, "scanInfo[firstValidIndex]");
        int lastIndex = CollectionsKt.getLastIndex(list);
        double d = 0.0d;
        Info info2 = info;
        int i2 = -1;
        while (i < lastIndex) {
            if (arrayList.get(i).getIsValid()) {
                if (i2 < 0) {
                    if (arrayList.get(i).getDiff() > info2.getMaxOffsetDistance() && arrayList.get(i).getOffset() < 0.2d) {
                        d = arrayList.get(i).getOffset() + 0.05d;
                        i2 = i;
                    }
                    Info info3 = arrayList.get(i);
                    Intrinsics.checkExpressionValueIsNotNull(info3, "scanInfo[i]");
                    info2 = info3;
                } else {
                    Info info4 = arrayList.get(i);
                    Intrinsics.checkExpressionValueIsNotNull(info4, "scanInfo[i]");
                    info2 = info4;
                    if (arrayList.get(i).getOffset() > d) {
                        FilterRange filterRange = new FilterRange();
                        filterRange.setStartIndex(i2 - 4);
                        filterRange.setEndIndex(i + 4);
                        if (filterRange.getStartIndex() < 0) {
                            filterRange.setStartIndex(0);
                        }
                        if (filterRange.getEndIndex() > CollectionsKt.getLastIndex(list)) {
                            filterRange.setEndIndex(CollectionsKt.getLastIndex(list));
                        }
                        filterRange.setFilterOffset(d);
                        arrayList2.add(filterRange);
                        i2 = -1;
                    }
                }
            }
            i++;
        }
        return arrayList2;
    }

    private static final void handleTrailing(ArrayList<FilterRange> arrayList, ArrayList<Info> arrayList2, List<? extends LidarNode> list) {
        for (FilterRange filterRange : arrayList) {
            int endIndex = filterRange.getEndIndex();
            for (int startIndex = filterRange.getStartIndex(); startIndex < endIndex; startIndex++) {
                if (arrayList2.get(startIndex).getOffset() < filterRange.getFilterOffset()) {
                    list.get(startIndex).distanceM = 0.0d;
                }
            }
        }
    }

    public static final double calculateTargetOffset(LidarNode last, LidarNode current) {
        Intrinsics.checkParameterIsNotNull(last, "last");
        Intrinsics.checkParameterIsNotNull(current, "current");
        double abs = Math.abs(last.angleInRad - current.angleInRad);
        if (abs == 0.0d) {
            return last.distanceM;
        }
        double cos = last.distanceM - (current.distanceM * Math.cos(abs));
        double sin = current.distanceM * Math.sin(abs);
        return (sin * last.distanceM) / Math.sqrt((cos * cos) + (sin * sin));
    }
}

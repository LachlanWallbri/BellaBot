package com.pudutech.bumblebee.business.map_task;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SketchMapHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\b\u0002\u001a\u000e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"SCALE", "", "sketchMapScale", "a", "", "", "doubleArray", "module_bumblebee_business_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SketchMapHelperKt {
    private static final int SCALE = 50;

    public static final int sketchMapScale(double d) {
        return (int) (((d * 100) / SCALE) + 0.5d);
    }

    public static final double[] sketchMapScale(double[] doubleArray) {
        Intrinsics.checkParameterIsNotNull(doubleArray, "doubleArray");
        int length = doubleArray.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = (doubleArray[i] * 100) / SCALE;
        }
        return dArr;
    }
}

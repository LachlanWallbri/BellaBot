package com.pudutech.mirsdk.mircore.tools;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AnalysisUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\bj\b\u0012\u0004\u0012\u00020\f`\nJ\u001e\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\f0\bj\b\u0012\u0004\u0012\u00020\f`\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/tools/AnalysisUtils;", "", "()V", "TAG", "", "analyzeRecognization", "", "recognizeArray", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "dockPosArray", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "calStdr", "", "data", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AnalysisUtils {
    public static final AnalysisUtils INSTANCE = new AnalysisUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private AnalysisUtils() {
    }

    public final void analyzeRecognization(ArrayList<Boolean> recognizeArray, ArrayList<Vector2d> dockPosArray) {
        Intrinsics.checkParameterIsNotNull(recognizeArray, "recognizeArray");
        Intrinsics.checkParameterIsNotNull(dockPosArray, "dockPosArray");
        Iterator<Boolean> it = recognizeArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            Boolean okor = it.next();
            Intrinsics.checkExpressionValueIsNotNull(okor, "okor");
            if (okor.booleanValue()) {
                i++;
            }
        }
        double size = recognizeArray.size() > 0 ? ((i * 1.0d) / recognizeArray.size()) * 100 : 0.0d;
        double calStdr = calStdr(dockPosArray);
        Pdlog.m3275i(TAG, "recognize rate " + size + " stdr " + calStdr);
    }

    public final double calStdr(ArrayList<Vector2d> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        double d = 0.0d;
        if (data.size() <= 1) {
            return 0.0d;
        }
        Iterator<Vector2d> it = data.iterator();
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (it.hasNext()) {
            Vector2d next = it.next();
            d2 += next.getX();
            d3 += next.getY();
        }
        double size = d2 / data.size();
        double size2 = d3 / data.size();
        Iterator<Vector2d> it2 = data.iterator();
        while (it2.hasNext()) {
            Vector2d next2 = it2.next();
            d += Math.pow(next2.getX() - size, 2.0d) + Math.pow(next2.getY() - size2, 2.0d);
        }
        return Math.sqrt(d / (data.size() - 1));
    }
}

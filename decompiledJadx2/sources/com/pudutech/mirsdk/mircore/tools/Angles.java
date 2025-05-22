package com.pudutech.mirsdk.mircore.tools;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Angles.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/tools/Angles;", "", "()V", "normalizeRad", "", "theta", "radDist", "from", TypedValues.Transition.S_TO, "vector2Angle", "dir_y", "dir_x", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Angles {
    public static final Angles INSTANCE = new Angles();

    public final double normalizeRad(double theta) {
        while (theta > 3.141592653589793d) {
            theta -= 6.283185307179586d;
        }
        while (theta < -3.141592653589793d) {
            theta += 6.283185307179586d;
        }
        return theta;
    }

    private Angles() {
    }

    public final double radDist(double from, double to) {
        return normalizeRad(to - from);
    }

    public final double vector2Angle(double dir_y, double dir_x) {
        return Math.atan2(dir_y, dir_x);
    }
}

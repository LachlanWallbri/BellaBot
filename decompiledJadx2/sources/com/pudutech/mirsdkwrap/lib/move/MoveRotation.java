package com.pudutech.mirsdkwrap.lib.move;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveRotation.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\f\u0010\u000e\u001a\u00020\u0005*\u00020\u0005H\u0002J\f\u0010\u000f\u001a\u00020\u0005*\u00020\u0005H\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveRotation;", "", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "radians", "", "startAngle", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;DD)V", "M_2PI", "TAG", "", TypedValues.Attributes.S_TARGET, "start", "", "toAbsWithinPI", "toWithin2PI", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveRotation {
    private final double M_2PI;
    private final String TAG;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private final double radians;
    private final double startAngle;
    private double target;

    public MoveRotation(RobotMoveInterfaceDecorator moveInterfaceDecorator, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.radians = d;
        this.startAngle = d2;
        this.TAG = "MoveRotation";
        this.M_2PI = 6.283185307179586d;
    }

    public final void start() {
        this.target = toAbsWithinPI(this.radians + this.startAngle);
        Pdlog.m3273d(this.TAG, "rotate. now=" + Math.toDegrees(this.startAngle) + " target=" + Math.toDegrees(this.target));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MoveRotation$start$1(this, toAbsWithinPI(this.radians), null), 3, null);
    }

    private final double toAbsWithinPI(double d) {
        while (d > 3.141592653589793d) {
            d -= this.M_2PI;
        }
        while (d < -3.141592653589793d) {
            d += this.M_2PI;
        }
        return d;
    }

    private final double toWithin2PI(double d) {
        while (true) {
            double d2 = this.M_2PI;
            if (d <= d2) {
                break;
            }
            d -= d2;
        }
        while (d < 0) {
            d += this.M_2PI;
        }
        return d;
    }
}

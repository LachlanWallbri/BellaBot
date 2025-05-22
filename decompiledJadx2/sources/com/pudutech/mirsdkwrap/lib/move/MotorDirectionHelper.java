package com.pudutech.mirsdkwrap.lib.move;

import android.os.SystemClock;
import com.pudutech.mirsdkwrap.lib.interf.MotorDirection;
import com.pudutech.mirsdkwrap.lib.interf.MotorDirectionListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MotorDirectionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MotorDirectionHelper;", "", "()V", "BRAKE_DIFF", "", "FORWARD_DIFF", "SPEED_PERIOD_MS", "", "TURN_DIFF", ES6Iterator.VALUE_PROPERTY, "", "isMoving", "()Z", "setMoving", "(Z)V", "lastCenterSpeed_mps", "lastSpeedTimestampMs", "", "onMotorDirectionListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;", "getOnMotorDirectionListener", "()Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;", "setOnMotorDirectionListener", "(Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;)V", "state", "Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirection;", "destroy", "", "notifyLis", "onSpeedChange", "center", "rotate", "updateFSM", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MotorDirectionHelper {
    private boolean isMoving;
    private double lastCenterSpeed_mps;
    private long lastSpeedTimestampMs;
    private MotorDirectionListener onMotorDirectionListener;
    private final int SPEED_PERIOD_MS = 80;
    private final double TURN_DIFF = 0.5d;
    private final double FORWARD_DIFF = 0.3d;
    private final double BRAKE_DIFF = 0.08d;
    private volatile MotorDirection state = MotorDirection.STOP;

    public final MotorDirectionListener getOnMotorDirectionListener() {
        return this.onMotorDirectionListener;
    }

    public final void setOnMotorDirectionListener(MotorDirectionListener motorDirectionListener) {
        this.onMotorDirectionListener = motorDirectionListener;
    }

    /* renamed from: isMoving, reason: from getter */
    public final boolean getIsMoving() {
        return this.isMoving;
    }

    public final void setMoving(boolean z) {
        this.isMoving = z;
        if (z || this.state == MotorDirection.STOP) {
            return;
        }
        this.state = MotorDirection.STOP;
        notifyLis(this.state);
    }

    public final void onSpeedChange(double center, double rotate) {
        if (this.onMotorDirectionListener != null && SystemClock.elapsedRealtime() - this.lastSpeedTimestampMs >= this.SPEED_PERIOD_MS) {
            this.lastSpeedTimestampMs = SystemClock.elapsedRealtime();
            MotorDirection updateFSM = updateFSM(center, rotate);
            if (updateFSM != this.state && this.isMoving) {
                notifyLis(updateFSM);
            }
        }
    }

    private final void notifyLis(MotorDirection state) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MotorDirectionHelper$notifyLis$1(this, state, null), 2, null);
    }

    private final MotorDirection updateFSM(double center, double rotate) {
        MotorDirection motorDirection;
        MotorDirection motorDirection2 = this.state;
        double d = this.TURN_DIFF;
        if (rotate >= d) {
            motorDirection = MotorDirection.LEFT;
        } else if (rotate <= (-d)) {
            motorDirection = MotorDirection.RIGHT;
        } else if (center > 0.01d && this.lastCenterSpeed_mps - center > this.BRAKE_DIFF) {
            motorDirection = MotorDirection.BRAKE;
        } else {
            if (motorDirection2 == MotorDirection.BRAKE) {
                motorDirection2 = MotorDirection.FORWARD;
            }
            motorDirection = Math.abs(rotate) < this.FORWARD_DIFF ? MotorDirection.FORWARD : motorDirection2;
        }
        this.lastCenterSpeed_mps = center;
        return motorDirection;
    }

    public final void destroy() {
        this.onMotorDirectionListener = (MotorDirectionListener) null;
    }
}

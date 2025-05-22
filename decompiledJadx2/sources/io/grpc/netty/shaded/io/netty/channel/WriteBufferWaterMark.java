package io.grpc.netty.shaded.io.netty.channel;

import com.slamtec.slamware.robot.SystemParameters;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WriteBufferWaterMark {
    public static final WriteBufferWaterMark DEFAULT = new WriteBufferWaterMark(32768, 65536, false);
    private static final int DEFAULT_HIGH_WATER_MARK = 65536;
    private static final int DEFAULT_LOW_WATER_MARK = 32768;
    private final int high;
    private final int low;

    public WriteBufferWaterMark(int i, int i2) {
        this(i, i2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteBufferWaterMark(int i, int i2, boolean z) {
        if (z) {
            ObjectUtil.checkPositiveOrZero(i, SystemParameters.SYSVAL_ROBOT_SPEED_LOW);
            if (i2 < i) {
                throw new IllegalArgumentException("write buffer's high water mark cannot be less than  low water mark (" + i + "): " + i2);
            }
        }
        this.low = i;
        this.high = i2;
    }

    public int low() {
        return this.low;
    }

    public int high() {
        return this.high;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(55);
        sb.append("WriteBufferWaterMark(low: ");
        sb.append(this.low);
        sb.append(", high: ");
        sb.append(this.high);
        sb.append(")");
        return sb.toString();
    }
}

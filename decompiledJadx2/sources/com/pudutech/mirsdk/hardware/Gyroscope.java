package com.pudutech.mirsdk.hardware;

import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: Gyroscope.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/Gyroscope;", "", "()V", "CONVERT_TO_METRIC", "", "PERIOD", "_accumulate", "Lcom/pudutech/mirsdk/hardware/Gyroscope$Data;", "_last", "accumulate", "getAccumulate", "()Lcom/pudutech/mirsdk/hardware/Gyroscope$Data;", "last", "getLast", "update", "", "x", "", "y", CompressorStreamFactory.f8930Z, "Data", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Gyroscope {
    private double CONVERT_TO_METRIC = 2.6631610446506074E-4d;
    private final double PERIOD = 0.025d;
    private final Data _last = new Data();
    private final Data _accumulate = new Data();

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: Gyroscope.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/Gyroscope$Data;", "", "()V", "x", "", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", CompressorStreamFactory.f8930Z, "getZ", "setZ", "toString", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Data {
        private double x;
        private double y;
        private double z;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public String toString() {
            return "x:" + CommonKt.format(this.x, 4) + " y:" + CommonKt.format(this.y, 4) + " z:" + CommonKt.format(this.z, 4);
        }
    }

    /* renamed from: getLast, reason: from getter */
    public final Data get_last() {
        return this._last;
    }

    /* renamed from: getAccumulate, reason: from getter */
    public final Data get_accumulate() {
        return this._accumulate;
    }

    public final void update(short x, short y, short z) {
        this._last.setX(x * this.CONVERT_TO_METRIC);
        this._last.setY(y * this.CONVERT_TO_METRIC);
        this._last.setZ(z * this.CONVERT_TO_METRIC);
        Data data = this._accumulate;
        data.setX(data.getX() + (this._last.getX() * this.PERIOD));
        Data data2 = this._accumulate;
        data2.setY(data2.getY() + (this._last.getY() * this.PERIOD));
        Data data3 = this._accumulate;
        data3.setZ(data3.getZ() + (this._last.getZ() * this.PERIOD));
        Pdlog.m3273d("Hardware", "Gyroscope current:" + get_last() + " accumulate:" + get_accumulate());
    }
}

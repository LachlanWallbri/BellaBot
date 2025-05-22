package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.movementCallback.HangOutCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import java.util.List;
import kotlin.Metadata;

/* compiled from: HangOutInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001e\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/HangOutInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "setCallback", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/HangOutCallback;", "setRandom", "range", "", "Lcom/pudutech/bumblebee/business/movementInterface/HangOutInterface$Point;", "radius_m", "", "Point", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface HangOutInterface extends BaseTaskInterface {

    /* compiled from: HangOutInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(HangOutInterface hangOutInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(hangOutInterface);
        }

        public static void pauseTask(HangOutInterface hangOutInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(hangOutInterface);
        }

        public static void resumeTask(HangOutInterface hangOutInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(hangOutInterface);
        }

        public static void setActive(HangOutInterface hangOutInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(hangOutInterface, z, z2);
        }
    }

    void setCallback(HangOutCallback callback);

    void setRandom(List<Point> range, double radius_m);

    /* compiled from: HangOutInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/HangOutInterface$Point;", "", "x", "", "y", "(DD)V", "getX", "()D", "getY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Point {
        private final double x;
        private final double y;

        public static /* synthetic */ Point copy$default(Point point, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = point.x;
            }
            if ((i & 2) != 0) {
                d2 = point.y;
            }
            return point.copy(d, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final double getX() {
            return this.x;
        }

        /* renamed from: component2, reason: from getter */
        public final double getY() {
            return this.y;
        }

        public final Point copy(double x, double y) {
            return new Point(x, y);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Point)) {
                return false;
            }
            Point point = (Point) other;
            return Double.compare(this.x, point.x) == 0 && Double.compare(this.y, point.y) == 0;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.x);
            int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
            long doubleToLongBits2 = Double.doubleToLongBits(this.y);
            return i + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
        }

        public Point(double d, double d2) {
            this.x = d;
            this.y = d2;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public String toString() {
            return "x=" + this.x + "  y=" + this.y;
        }
    }
}

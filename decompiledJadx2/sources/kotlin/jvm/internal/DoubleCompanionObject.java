package kotlin.jvm.internal;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: PrimitiveCompanionObjects.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lkotlin/jvm/internal/DoubleCompanionObject;", "", "()V", "MAX_VALUE", "", "getMAX_VALUE", "()D", "MIN_VALUE", "getMIN_VALUE", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY", "NaN", "getNaN", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DoubleCompanionObject {
    public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
    private static final double MIN_VALUE = MIN_VALUE;
    private static final double MIN_VALUE = MIN_VALUE;
    private static final double MAX_VALUE = MAX_VALUE;
    private static final double MAX_VALUE = MAX_VALUE;
    private static final double POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final double POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final double NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final double NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final double NaN = NaN;
    private static final double NaN = NaN;

    private DoubleCompanionObject() {
    }

    public final double getMIN_VALUE() {
        return MIN_VALUE;
    }

    public final double getMAX_VALUE() {
        return MAX_VALUE;
    }

    public final double getPOSITIVE_INFINITY() {
        return POSITIVE_INFINITY;
    }

    public final double getNEGATIVE_INFINITY() {
        return NEGATIVE_INFINITY;
    }

    public final double getNaN() {
        return NaN;
    }
}

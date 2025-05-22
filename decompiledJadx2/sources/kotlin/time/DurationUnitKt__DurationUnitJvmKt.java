package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: DurationUnitJvm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001*\u001e\b\u0007\u0010\u0007\"\u00020\u00042\u00020\u0004B\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nB\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"convertDurationUnit", "", ES6Iterator.VALUE_PROPERTY, "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "DurationUnit", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/time/ExperimentalTime;", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes2.dex */
class DurationUnitKt__DurationUnitJvmKt {
    public static /* synthetic */ void DurationUnit$annotations() {
    }

    public static final double convertDurationUnit(double d, TimeUnit sourceUnit, TimeUnit targetUnit) {
        Intrinsics.checkParameterIsNotNull(sourceUnit, "sourceUnit");
        Intrinsics.checkParameterIsNotNull(targetUnit, "targetUnit");
        long convert = targetUnit.convert(1L, sourceUnit);
        return convert > 0 ? d * convert : d / sourceUnit.convert(1L, targetUnit);
    }
}

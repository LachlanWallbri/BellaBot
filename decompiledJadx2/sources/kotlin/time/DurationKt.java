package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Duration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\b2\u0006\u0010&\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\r2\u0006\u0010&\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a \u0010+\u001a\u00020\u0007*\u00020\b2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u0010.\u001a \u0010+\u001a\u00020\u0007*\u00020\r2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u0010/\u001a \u0010+\u001a\u00020\u0007*\u00020\u00102\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u00100\"\u001b\u0010\u0000\u001a\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000e\u001a\u0004\b\u000b\u0010\u000f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u0011\u001a\u0004\b\u000b\u0010\u0012\"!\u0010\u0013\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u000f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0012\"!\u0010\u0016\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u000f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0012\"!\u0010\u0019\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\n\u001a\u0004\b\u001b\u0010\f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u000f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0012\"!\u0010\u001c\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\n\u001a\u0004\b\u001e\u0010\f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u000f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0012\"!\u0010\u001f\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\n\u001a\u0004\b!\u0010\f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u000f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\u0011\u001a\u0004\b!\u0010\u0012\"!\u0010\"\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\n\u001a\u0004\b$\u0010\f\"!\u0010\"\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\u000e\u001a\u0004\b$\u0010\u000f\"!\u0010\"\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\u0011\u001a\u0004\b$\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, m3961d2 = {"storageUnit", "Ljava/util/concurrent/TimeUnit;", "storageUnit$annotations", "()V", "getStorageUnit", "()Ljava/util/concurrent/TimeUnit;", "days", "Lkotlin/time/Duration;", "", "days$annotations", "(D)V", "getDays", "(D)D", "", "(I)V", "(I)D", "", "(J)V", "(J)D", "hours", "hours$annotations", "getHours", "microseconds", "microseconds$annotations", "getMicroseconds", "milliseconds", "milliseconds$annotations", "getMilliseconds", "minutes", "minutes$annotations", "getMinutes", "nanoseconds", "nanoseconds$annotations", "getNanoseconds", "seconds", "seconds$annotations", "getSeconds", "times", TypedValues.Transition.S_DURATION, "times-kIfJnKk", "(DD)D", "times-mvk6XK0", "(ID)D", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLjava/util/concurrent/TimeUnit;)D", "(ILjava/util/concurrent/TimeUnit;)D", "(JLjava/util/concurrent/TimeUnit;)D", "kotlin-stdlib"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DurationKt {
    public static /* synthetic */ void days$annotations(double d) {
    }

    public static /* synthetic */ void days$annotations(int i) {
    }

    public static /* synthetic */ void days$annotations(long j) {
    }

    public static /* synthetic */ void hours$annotations(double d) {
    }

    public static /* synthetic */ void hours$annotations(int i) {
    }

    public static /* synthetic */ void hours$annotations(long j) {
    }

    public static /* synthetic */ void microseconds$annotations(double d) {
    }

    public static /* synthetic */ void microseconds$annotations(int i) {
    }

    public static /* synthetic */ void microseconds$annotations(long j) {
    }

    public static /* synthetic */ void milliseconds$annotations(double d) {
    }

    public static /* synthetic */ void milliseconds$annotations(int i) {
    }

    public static /* synthetic */ void milliseconds$annotations(long j) {
    }

    public static /* synthetic */ void minutes$annotations(double d) {
    }

    public static /* synthetic */ void minutes$annotations(int i) {
    }

    public static /* synthetic */ void minutes$annotations(long j) {
    }

    public static /* synthetic */ void nanoseconds$annotations(double d) {
    }

    public static /* synthetic */ void nanoseconds$annotations(int i) {
    }

    public static /* synthetic */ void nanoseconds$annotations(long j) {
    }

    public static /* synthetic */ void seconds$annotations(double d) {
    }

    public static /* synthetic */ void seconds$annotations(int i) {
    }

    public static /* synthetic */ void seconds$annotations(long j) {
    }

    private static /* synthetic */ void storageUnit$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TimeUnit getStorageUnit() {
        return TimeUnit.NANOSECONDS;
    }

    public static final double toDuration(int i, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return toDuration(i, unit);
    }

    public static final double toDuration(long j, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return toDuration(j, unit);
    }

    public static final double getNanoseconds(int i) {
        return toDuration(i, TimeUnit.NANOSECONDS);
    }

    public static final double getNanoseconds(long j) {
        return toDuration(j, TimeUnit.NANOSECONDS);
    }

    public static final double getNanoseconds(double d) {
        return toDuration(d, TimeUnit.NANOSECONDS);
    }

    public static final double getMicroseconds(int i) {
        return toDuration(i, TimeUnit.MICROSECONDS);
    }

    public static final double getMicroseconds(long j) {
        return toDuration(j, TimeUnit.MICROSECONDS);
    }

    public static final double getMicroseconds(double d) {
        return toDuration(d, TimeUnit.MICROSECONDS);
    }

    public static final double getMilliseconds(int i) {
        return toDuration(i, TimeUnit.MILLISECONDS);
    }

    public static final double getMilliseconds(long j) {
        return toDuration(j, TimeUnit.MILLISECONDS);
    }

    public static final double getMilliseconds(double d) {
        return toDuration(d, TimeUnit.MILLISECONDS);
    }

    public static final double getSeconds(int i) {
        return toDuration(i, TimeUnit.SECONDS);
    }

    public static final double getSeconds(long j) {
        return toDuration(j, TimeUnit.SECONDS);
    }

    public static final double getSeconds(double d) {
        return toDuration(d, TimeUnit.SECONDS);
    }

    public static final double getMinutes(int i) {
        return toDuration(i, TimeUnit.MINUTES);
    }

    public static final double getMinutes(long j) {
        return toDuration(j, TimeUnit.MINUTES);
    }

    public static final double getMinutes(double d) {
        return toDuration(d, TimeUnit.MINUTES);
    }

    public static final double getHours(int i) {
        return toDuration(i, TimeUnit.HOURS);
    }

    public static final double getHours(long j) {
        return toDuration(j, TimeUnit.HOURS);
    }

    public static final double getHours(double d) {
        return toDuration(d, TimeUnit.HOURS);
    }

    public static final double getDays(int i) {
        return toDuration(i, TimeUnit.DAYS);
    }

    public static final double getDays(long j) {
        return toDuration(j, TimeUnit.DAYS);
    }

    public static final double getDays(double d) {
        return toDuration(d, TimeUnit.DAYS);
    }

    /* renamed from: times-mvk6XK0, reason: not valid java name */
    private static final double m5522timesmvk6XK0(int i, double d) {
        return Duration.m5504timesimpl(d, i);
    }

    /* renamed from: times-kIfJnKk, reason: not valid java name */
    private static final double m5521timeskIfJnKk(double d, double d2) {
        return Duration.m5503timesimpl(d2, d);
    }

    public static final double toDuration(double d, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return Duration.m5477constructorimpl(DurationUnitKt.convertDurationUnit(d, unit, TimeUnit.NANOSECONDS));
    }
}

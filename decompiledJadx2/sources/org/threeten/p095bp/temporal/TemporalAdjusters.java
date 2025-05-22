package org.threeten.p095bp.temporal;

import org.threeten.p095bp.DayOfWeek;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TemporalAdjusters {
    private TemporalAdjusters() {
    }

    public static TemporalAdjuster firstDayOfMonth() {
        return Impl.FIRST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster lastDayOfMonth() {
        return Impl.LAST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster firstDayOfNextMonth() {
        return Impl.FIRST_DAY_OF_NEXT_MONTH;
    }

    public static TemporalAdjuster firstDayOfYear() {
        return Impl.FIRST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster lastDayOfYear() {
        return Impl.LAST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster firstDayOfNextYear() {
        return Impl.FIRST_DAY_OF_NEXT_YEAR;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class Impl implements TemporalAdjuster {
        private final int ordinal;
        private static final Impl FIRST_DAY_OF_MONTH = new Impl(0);
        private static final Impl LAST_DAY_OF_MONTH = new Impl(1);
        private static final Impl FIRST_DAY_OF_NEXT_MONTH = new Impl(2);
        private static final Impl FIRST_DAY_OF_YEAR = new Impl(3);
        private static final Impl LAST_DAY_OF_YEAR = new Impl(4);
        private static final Impl FIRST_DAY_OF_NEXT_YEAR = new Impl(5);

        private Impl(int i) {
            this.ordinal = i;
        }

        @Override // org.threeten.p095bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            int i = this.ordinal;
            if (i == 0) {
                return temporal.with(ChronoField.DAY_OF_MONTH, 1L);
            }
            if (i == 1) {
                return temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
            }
            if (i == 2) {
                return temporal.with(ChronoField.DAY_OF_MONTH, 1L).plus(1L, ChronoUnit.MONTHS);
            }
            if (i == 3) {
                return temporal.with(ChronoField.DAY_OF_YEAR, 1L);
            }
            if (i == 4) {
                return temporal.with(ChronoField.DAY_OF_YEAR, temporal.range(ChronoField.DAY_OF_YEAR).getMaximum());
            }
            if (i == 5) {
                return temporal.with(ChronoField.DAY_OF_YEAR, 1L).plus(1L, ChronoUnit.YEARS);
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    public static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(1, dayOfWeek);
    }

    public static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(-1, dayOfWeek);
    }

    public static TemporalAdjuster dayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(i, dayOfWeek);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static final class DayOfWeekInMonth implements TemporalAdjuster {
        private final int dowValue;
        private final int ordinal;

        private DayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
            this.ordinal = i;
            this.dowValue = dayOfWeek.getValue();
        }

        @Override // org.threeten.p095bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            if (this.ordinal >= 0) {
                return temporal.with(ChronoField.DAY_OF_MONTH, 1L).plus((int) ((((this.dowValue - r10.get(ChronoField.DAY_OF_WEEK)) + 7) % 7) + ((this.ordinal - 1) * 7)), ChronoUnit.DAYS);
            }
            Temporal with = temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
            int i = this.dowValue - with.get(ChronoField.DAY_OF_WEEK);
            if (i == 0) {
                i = 0;
            } else if (i > 0) {
                i -= 7;
            }
            return with.plus((int) (i - (((-this.ordinal) - 1) * 7)), ChronoUnit.DAYS);
        }
    }

    public static TemporalAdjuster next(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(2, dayOfWeek);
    }

    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(0, dayOfWeek);
    }

    public static TemporalAdjuster previous(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(3, dayOfWeek);
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(1, dayOfWeek);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static final class RelativeDayOfWeek implements TemporalAdjuster {
        private final int dowValue;
        private final int relative;

        private RelativeDayOfWeek(int i, DayOfWeek dayOfWeek) {
            Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
            this.relative = i;
            this.dowValue = dayOfWeek.getValue();
        }

        @Override // org.threeten.p095bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            int i = temporal.get(ChronoField.DAY_OF_WEEK);
            if (this.relative < 2 && i == this.dowValue) {
                return temporal;
            }
            if ((this.relative & 1) == 0) {
                return temporal.plus(i - this.dowValue >= 0 ? 7 - r0 : -r0, ChronoUnit.DAYS);
            }
            return temporal.minus(this.dowValue - i >= 0 ? 7 - r1 : -r1, ChronoUnit.DAYS);
        }
    }
}

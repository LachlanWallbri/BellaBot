package org.threeten.p095bp.temporal;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.Locale;
import java.util.Map;
import org.threeten.p095bp.DayOfWeek;
import org.threeten.p095bp.Duration;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.chrono.IsoChronology;
import org.threeten.p095bp.format.ResolverStyle;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class IsoFields {
    public static final TemporalField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    public static final TemporalUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    public static final TemporalUnit QUARTER_YEARS = Unit.QUARTER_YEARS;

    private IsoFields() {
        throw new AssertionError("Not instantiable");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum Field implements TemporalField {
        DAY_OF_QUARTER { // from class: org.threeten.bp.temporal.IsoFields.Field.1
            @Override // java.lang.Enum
            public String toString() {
                return "DayOfQuarter";
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.DAYS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.m4245of(1L, 90L, 92L);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR) && temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && temporalAccessor.isSupported(ChronoField.YEAR) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                long j = temporalAccessor.getLong(QUARTER_OF_YEAR);
                if (j == 1) {
                    return IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? ValueRange.m4244of(1L, 91L) : ValueRange.m4244of(1L, 90L);
                }
                if (j == 2) {
                    return ValueRange.m4244of(1L, 91L);
                }
                if (j == 3 || j == 4) {
                    return ValueRange.m4244of(1L, 92L);
                }
                return range();
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                return temporalAccessor.get(ChronoField.DAY_OF_YEAR) - Field.QUARTER_DAYS[((temporalAccessor.get(ChronoField.MONTH_OF_YEAR) - 1) / 3) + (IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? 4 : 0)];
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                return (R) r.with(ChronoField.DAY_OF_YEAR, r.getLong(ChronoField.DAY_OF_YEAR) + (j - from));
            }

            /* JADX WARN: Code restructure failed: missing block: B:21:0x0079, code lost:
            
                if (r0 == 2) goto L19;
             */
            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.p095bp.temporal.TemporalField
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                LocalDate plusDays;
                Long l = map.get(ChronoField.YEAR);
                Long l2 = map.get(QUARTER_OF_YEAR);
                if (l == null || l2 == null) {
                    return null;
                }
                int checkValidIntValue = ChronoField.YEAR.checkValidIntValue(l.longValue());
                long longValue = map.get(DAY_OF_QUARTER).longValue();
                if (resolverStyle == ResolverStyle.LENIENT) {
                    plusDays = LocalDate.m4199of(checkValidIntValue, 1, 1).plusMonths(Jdk8Methods.safeMultiply(Jdk8Methods.safeSubtract(l2.longValue(), 1L), 3)).plusDays(Jdk8Methods.safeSubtract(longValue, 1L));
                } else {
                    int checkValidIntValue2 = QUARTER_OF_YEAR.range().checkValidIntValue(l2.longValue(), QUARTER_OF_YEAR);
                    if (resolverStyle == ResolverStyle.STRICT) {
                        int i = 92;
                        if (checkValidIntValue2 == 1) {
                            if (!IsoChronology.INSTANCE.isLeapYear(checkValidIntValue)) {
                                i = 90;
                                ValueRange.m4244of(1L, i).checkValidValue(longValue, this);
                            }
                            i = 91;
                            ValueRange.m4244of(1L, i).checkValidValue(longValue, this);
                        }
                    } else {
                        range().checkValidValue(longValue, this);
                    }
                    plusDays = LocalDate.m4199of(checkValidIntValue, ((checkValidIntValue2 - 1) * 3) + 1, 1).plusDays(longValue - 1);
                }
                map.remove(this);
                map.remove(ChronoField.YEAR);
                map.remove(QUARTER_OF_YEAR);
                return plusDays;
            }
        },
        QUARTER_OF_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.2
            @Override // java.lang.Enum
            public String toString() {
                return "QuarterOfYear";
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.YEARS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.m4244of(1L, 4L);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return range();
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                }
                return (temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) + 2) / 3;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                return (R) r.with(ChronoField.MONTH_OF_YEAR, r.getLong(ChronoField.MONTH_OF_YEAR) + ((j - from) * 3));
            }
        },
        WEEK_OF_WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.3
            @Override // java.lang.Enum
            public String toString() {
                return "WeekOfWeekBasedYear";
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.WEEKS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.p095bp.temporal.TemporalField
            public String getDisplayName(Locale locale) {
                Jdk8Methods.requireNonNull(locale, "locale");
                return "Week";
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.m4245of(1L, 52L, 53L);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeekRange(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeek(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                range().checkValidValue(j, this);
                return (R) r.plus(Jdk8Methods.safeSubtract(j, getFrom(r)), ChronoUnit.WEEKS);
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.p095bp.temporal.TemporalField
            public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                LocalDate with;
                Long l = map.get(WEEK_BASED_YEAR);
                Long l2 = map.get(ChronoField.DAY_OF_WEEK);
                if (l == null || l2 == null) {
                    return null;
                }
                int checkValidIntValue = WEEK_BASED_YEAR.range().checkValidIntValue(l.longValue(), WEEK_BASED_YEAR);
                long longValue = map.get(WEEK_OF_WEEK_BASED_YEAR).longValue();
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long longValue2 = l2.longValue();
                    long j = 0;
                    if (longValue2 > 7) {
                        long j2 = longValue2 - 1;
                        j = j2 / 7;
                        longValue2 = (j2 % 7) + 1;
                    } else if (longValue2 < 1) {
                        j = (longValue2 / 7) - 1;
                        longValue2 = (longValue2 % 7) + 7;
                    }
                    with = LocalDate.m4199of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).plusWeeks(j).with((TemporalField) ChronoField.DAY_OF_WEEK, longValue2);
                } else {
                    int checkValidIntValue2 = ChronoField.DAY_OF_WEEK.checkValidIntValue(l2.longValue());
                    if (resolverStyle == ResolverStyle.STRICT) {
                        Field.getWeekRange(LocalDate.m4199of(checkValidIntValue, 1, 4)).checkValidValue(longValue, this);
                    } else {
                        range().checkValidValue(longValue, this);
                    }
                    with = LocalDate.m4199of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).with((TemporalField) ChronoField.DAY_OF_WEEK, checkValidIntValue2);
                }
                map.remove(this);
                map.remove(WEEK_BASED_YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return with;
            }
        },
        WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.4
            @Override // java.lang.Enum
            public String toString() {
                return "WeekBasedYear";
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.FOREVER;
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange range() {
                return ChronoField.YEAR.range();
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return ChronoField.YEAR.range();
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeekBasedYear(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // org.threeten.p095bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                if (!isSupportedBy(r)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                int checkValidIntValue = range().checkValidIntValue(j, WEEK_BASED_YEAR);
                LocalDate from = LocalDate.from((TemporalAccessor) r);
                int i = from.get(ChronoField.DAY_OF_WEEK);
                int week = Field.getWeek(from);
                if (week == 53 && Field.getWeekRange(checkValidIntValue) == 52) {
                    week = 52;
                }
                return (R) r.with(LocalDate.m4199of(checkValidIntValue, 1, 4).plusDays((i - r5.get(ChronoField.DAY_OF_WEEK)) + ((week - 1) * 7)));
            }
        };

        private static final int[] QUARTER_DAYS = {0, 90, 181, BaseQuickAdapter.HEADER_VIEW, 0, 91, 182, 274};

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            return null;
        }

        /* synthetic */ Field(C89611 c89611) {
            this();
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isIso(TemporalAccessor temporalAccessor) {
            return Chronology.from(temporalAccessor).equals(IsoChronology.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ValueRange getWeekRange(LocalDate localDate) {
            return ValueRange.m4244of(1L, getWeekRange(getWeekBasedYear(localDate)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeekRange(int i) {
            LocalDate m4199of = LocalDate.m4199of(i, 1, 1);
            if (m4199of.getDayOfWeek() != DayOfWeek.THURSDAY) {
                return (m4199of.getDayOfWeek() == DayOfWeek.WEDNESDAY && m4199of.isLeapYear()) ? 53 : 52;
            }
            return 53;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeek(LocalDate localDate) {
            int ordinal = localDate.getDayOfWeek().ordinal();
            int dayOfYear = localDate.getDayOfYear() - 1;
            int i = (3 - ordinal) + dayOfYear;
            int i2 = (i - ((i / 7) * 7)) - 3;
            if (i2 < -3) {
                i2 += 7;
            }
            if (dayOfYear < i2) {
                return (int) getWeekRange(localDate.withDayOfYear(180).minusYears(1L)).getMaximum();
            }
            int i3 = ((dayOfYear - i2) / 7) + 1;
            if (i3 != 53) {
                return i3;
            }
            if (i2 == -3 || (i2 == -2 && localDate.isLeapYear())) {
                return i3;
            }
            return 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeekBasedYear(LocalDate localDate) {
            int year = localDate.getYear();
            int dayOfYear = localDate.getDayOfYear();
            if (dayOfYear <= 3) {
                return dayOfYear - localDate.getDayOfWeek().ordinal() < -2 ? year - 1 : year;
            }
            if (dayOfYear >= 363) {
                return ((dayOfYear - 363) - (localDate.isLeapYear() ? 1 : 0)) - localDate.getDayOfWeek().ordinal() >= 0 ? year + 1 : year;
            }
            return year;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952)),
        QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238));

        private final Duration duration;
        private final String name;

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public boolean isDurationEstimated() {
            return true;
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public boolean isTimeBased() {
            return false;
        }

        Unit(String str, Duration duration) {
            this.name = str;
            this.duration = duration;
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public Duration getDuration() {
            return this.duration;
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public boolean isSupportedBy(Temporal temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public <R extends Temporal> R addTo(R r, long j) {
            int i = C89611.$SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return (R) r.with(IsoFields.WEEK_BASED_YEAR, Jdk8Methods.safeAdd(r.get(IsoFields.WEEK_BASED_YEAR), j));
            }
            if (i == 2) {
                return (R) r.plus(j / 256, ChronoUnit.YEARS).plus((j % 256) * 3, ChronoUnit.MONTHS);
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override // org.threeten.p095bp.temporal.TemporalUnit
        public long between(Temporal temporal, Temporal temporal2) {
            int i = C89611.$SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return Jdk8Methods.safeSubtract(temporal2.getLong(IsoFields.WEEK_BASED_YEAR), temporal.getLong(IsoFields.WEEK_BASED_YEAR));
            }
            if (i == 2) {
                return temporal.until(temporal2, ChronoUnit.MONTHS) / 3;
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override // java.lang.Enum, org.threeten.p095bp.temporal.TemporalUnit
        public String toString() {
            return this.name;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.threeten.bp.temporal.IsoFields$1 */
    /* loaded from: classes9.dex */
    static /* synthetic */ class C89611 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit = new int[Unit.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[Unit.WEEK_BASED_YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[Unit.QUARTER_YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}

package org.threeten.p095bp.temporal;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.DayOfWeek;
import org.threeten.p095bp.Year;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.format.ResolverStyle;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class WeekFields implements Serializable {
    private static final ConcurrentMap<String, WeekFields> CACHE = new ConcurrentHashMap(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = m4248of(DayOfWeek.SUNDAY, 1);
    private static final long serialVersionUID = -1177360819670808121L;
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    private final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);
    private final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);

    /* renamed from: of */
    public static WeekFields m4247of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        return m4248of(DayOfWeek.SUNDAY.plus(r4.getFirstDayOfWeek() - 1), new GregorianCalendar(new Locale(locale.getLanguage(), locale.getCountry())).getMinimalDaysInFirstWeek());
    }

    /* renamed from: of */
    public static WeekFields m4248of(DayOfWeek dayOfWeek, int i) {
        String str = dayOfWeek.toString() + i;
        WeekFields weekFields = CACHE.get(str);
        if (weekFields != null) {
            return weekFields;
        }
        CACHE.putIfAbsent(str, new WeekFields(dayOfWeek, i));
        return CACHE.get(str);
    }

    private WeekFields(DayOfWeek dayOfWeek, int i) {
        Jdk8Methods.requireNonNull(dayOfWeek, "firstDayOfWeek");
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.firstDayOfWeek = dayOfWeek;
        this.minimalDays = i;
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return m4248of(this.firstDayOfWeek, this.minimalDays);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectException("Invalid WeekFields" + e.getMessage());
        }
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfYear() {
        return this.weekOfYear;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WeekFields) && hashCode() == obj.hashCode();
    }

    public int hashCode() {
        return (this.firstDayOfWeek.ordinal() * 7) + this.minimalDays;
    }

    public String toString() {
        return "WeekFields[" + this.firstDayOfWeek + ',' + this.minimalDays + ']';
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    static class ComputedDayOfField implements TemporalField {
        private final TemporalUnit baseUnit;
        private final String name;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;
        private final WeekFields weekDef;
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.m4244of(1, 7);
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.m4246of(0, 1, 4, 6);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.m4246of(0, 1, 52, 54);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.m4245of(1, 52, 53);
        private static final ValueRange WEEK_BASED_YEAR_RANGE = ChronoField.YEAR.range();

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekFields) {
            return new ComputedDayOfField("DayOfWeek", weekFields, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfMonth", weekFields, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfYear", weekFields, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekFields, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekBasedYear", weekFields, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, WEEK_BASED_YEAR_RANGE);
        }

        private ComputedDayOfField(String str, WeekFields weekFields, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
            this.name = str;
            this.weekDef = weekFields;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = valueRange;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public long getFrom(TemporalAccessor temporalAccessor) {
            int localizedWBY;
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return floorMod;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                int i = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
                localizedWBY = computeWeek(startOfWeekOffset(i, floorMod), i);
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                localizedWBY = computeWeek(startOfWeekOffset(i2, floorMod), i2);
            } else if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                localizedWBY = localizedWOWBY(temporalAccessor);
            } else if (this.rangeUnit == ChronoUnit.FOREVER) {
                localizedWBY = localizedWBY(temporalAccessor);
            } else {
                throw new IllegalStateException("unreachable");
            }
            return localizedWBY;
        }

        private int localizedDayOfWeek(TemporalAccessor temporalAccessor, int i) {
            return Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - i, 7) + 1;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private long localizedWeekOfYear(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private int localizedWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return ((int) localizedWeekOfYear(Chronology.from(temporalAccessor).date(temporalAccessor).minus(1L, (TemporalUnit) ChronoUnit.WEEKS), floorMod)) + 1;
            }
            if (localizedWeekOfYear >= 53) {
                if (localizedWeekOfYear >= computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) {
                    return (int) (localizedWeekOfYear - (r6 - 1));
                }
            }
            return (int) localizedWeekOfYear;
        }

        private int localizedWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            int i = temporalAccessor.get(ChronoField.YEAR);
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return i - 1;
            }
            if (localizedWeekOfYear < 53) {
                return i;
            }
            return localizedWeekOfYear >= ((long) computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) i) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) ? i + 1 : i;
        }

        private int startOfWeekOffset(int i, int i2) {
            int floorMod = Jdk8Methods.floorMod(i - i2, 7);
            return floorMod + 1 > this.weekDef.getMinimalDaysInFirstWeek() ? 7 - floorMod : -floorMod;
        }

        private int computeWeek(int i, int i2) {
            return ((i + 7) + (i2 - 1)) / 7;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public <R extends Temporal> R adjustInto(R r, long j) {
            int checkValidIntValue = this.range.checkValidIntValue(j, this);
            if (checkValidIntValue == r.get(this)) {
                return r;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                int i = r.get(this.weekDef.weekOfWeekBasedYear);
                Temporal plus = r.plus((long) ((j - r1) * 52.1775d), ChronoUnit.WEEKS);
                if (plus.get(this) > checkValidIntValue) {
                    return (R) plus.minus(plus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                }
                if (plus.get(this) < checkValidIntValue) {
                    plus = plus.plus(2L, ChronoUnit.WEEKS);
                }
                R r2 = (R) plus.plus(i - plus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                return r2.get(this) > checkValidIntValue ? (R) r2.minus(1L, ChronoUnit.WEEKS) : r2;
            }
            return (R) r.plus(checkValidIntValue - r1, this.baseUnit);
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            long checkValidIntValue;
            ChronoLocalDate date;
            long checkValidIntValue2;
            ChronoLocalDate date2;
            long checkValidIntValue3;
            int localizedDayOfWeek;
            long localizedWeekOfYear;
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                map.put(ChronoField.DAY_OF_WEEK, Long.valueOf(Jdk8Methods.floorMod((value - 1) + (this.range.checkValidIntValue(map.remove(this).longValue(), this) - 1), 7) + 1));
                return null;
            }
            if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                if (!map.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                    return null;
                }
                Chronology from = Chronology.from(temporalAccessor);
                int floorMod = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
                int checkValidIntValue4 = range().checkValidIntValue(map.get(this).longValue(), this);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    date2 = from.date(checkValidIntValue4, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    checkValidIntValue3 = map.get(this.weekDef.weekOfWeekBasedYear).longValue();
                    localizedDayOfWeek = localizedDayOfWeek(date2, value);
                    localizedWeekOfYear = localizedWeekOfYear(date2, localizedDayOfWeek);
                } else {
                    date2 = from.date(checkValidIntValue4, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    checkValidIntValue3 = this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(map.get(this.weekDef.weekOfWeekBasedYear).longValue(), this.weekDef.weekOfWeekBasedYear);
                    localizedDayOfWeek = localizedDayOfWeek(date2, value);
                    localizedWeekOfYear = localizedWeekOfYear(date2, localizedDayOfWeek);
                }
                ChronoLocalDate plus = date2.plus(((checkValidIntValue3 - localizedWeekOfYear) * 7) + (floorMod - localizedDayOfWeek), (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && plus.getLong(this) != map.get(this).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                map.remove(this);
                map.remove(this.weekDef.weekOfWeekBasedYear);
                map.remove(ChronoField.DAY_OF_WEEK);
                return plus;
            }
            if (!map.containsKey(ChronoField.YEAR)) {
                return null;
            }
            int floorMod2 = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
            int checkValidIntValue5 = ChronoField.YEAR.checkValidIntValue(map.get(ChronoField.YEAR).longValue());
            Chronology from2 = Chronology.from(temporalAccessor);
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                if (!map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                    return null;
                }
                long longValue = map.remove(this).longValue();
                if (resolverStyle == ResolverStyle.LENIENT) {
                    date = from2.date(checkValidIntValue5, 1, 1).plus(map.get(ChronoField.MONTH_OF_YEAR).longValue() - 1, (TemporalUnit) ChronoUnit.MONTHS);
                    checkValidIntValue2 = ((longValue - localizedWeekOfMonth(date, localizedDayOfWeek(date, value))) * 7) + (floorMod2 - r0);
                } else {
                    date = from2.date(checkValidIntValue5, ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.get(ChronoField.MONTH_OF_YEAR).longValue()), 8);
                    checkValidIntValue2 = ((this.range.checkValidIntValue(longValue, this) - localizedWeekOfMonth(date, localizedDayOfWeek(date, value))) * 7) + (floorMod2 - r0);
                }
                ChronoLocalDate plus2 = date.plus(checkValidIntValue2, (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && plus2.getLong(ChronoField.MONTH_OF_YEAR) != map.get(ChronoField.MONTH_OF_YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                map.remove(this);
                map.remove(ChronoField.YEAR);
                map.remove(ChronoField.MONTH_OF_YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return plus2;
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                long longValue2 = map.remove(this).longValue();
                ChronoLocalDate date3 = from2.date(checkValidIntValue5, 1, 1);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    checkValidIntValue = ((longValue2 - localizedWeekOfYear(date3, localizedDayOfWeek(date3, value))) * 7) + (floorMod2 - r0);
                } else {
                    checkValidIntValue = ((this.range.checkValidIntValue(longValue2, this) - localizedWeekOfYear(date3, localizedDayOfWeek(date3, value))) * 7) + (floorMod2 - r0);
                }
                ChronoLocalDate plus3 = date3.plus(checkValidIntValue, (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && plus3.getLong(ChronoField.YEAR) != map.get(ChronoField.YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                map.remove(this);
                map.remove(ChronoField.YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return plus3;
            }
            throw new IllegalStateException("unreachable");
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public ValueRange range() {
            return this.range;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            if (!temporalAccessor.isSupported(ChronoField.DAY_OF_WEEK)) {
                return false;
            }
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return true;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_MONTH);
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR);
            }
            if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            return false;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            ChronoField chronoField;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                chronoField = ChronoField.DAY_OF_MONTH;
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                chronoField = ChronoField.DAY_OF_YEAR;
            } else {
                if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                    return rangeWOWBY(temporalAccessor);
                }
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    return temporalAccessor.range(ChronoField.YEAR);
                }
                throw new IllegalStateException("unreachable");
            }
            int startOfWeekOffset = startOfWeekOffset(temporalAccessor.get(chronoField), Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1);
            ValueRange range = temporalAccessor.range(chronoField);
            return ValueRange.m4244of(computeWeek(startOfWeekOffset, (int) range.getMinimum()), computeWeek(startOfWeekOffset, (int) range.getMaximum()));
        }

        private ValueRange rangeWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).minus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            if (localizedWeekOfYear >= computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).plus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            return ValueRange.m4244of(1L, r0 - 1);
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return this.rangeUnit == ChronoUnit.YEARS ? "Week" : toString();
        }

        public String toString() {
            return this.name + "[" + this.weekDef.toString() + "]";
        }
    }
}

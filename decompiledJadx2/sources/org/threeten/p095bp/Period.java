package org.threeten.p095bp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.chrono.ChronoPeriod;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.chrono.IsoChronology;
import org.threeten.p095bp.format.DateTimeParseException;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.UnsupportedTemporalTypeException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Period extends ChronoPeriod implements Serializable {
    private static final long serialVersionUID = -8290556941213247973L;
    private final int days;
    private final int months;
    private final int years;
    public static final Period ZERO = new Period(0, 0, 0);
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);

    public static Period ofYears(int i) {
        return create(i, 0, 0);
    }

    public static Period ofMonths(int i) {
        return create(0, i, 0);
    }

    public static Period ofWeeks(int i) {
        return create(0, 0, Jdk8Methods.safeMultiply(i, 7));
    }

    public static Period ofDays(int i) {
        return create(0, 0, i);
    }

    /* renamed from: of */
    public static Period m4219of(int i, int i2, int i3) {
        return create(i, i2, i3);
    }

    public static Period from(TemporalAmount temporalAmount) {
        if (temporalAmount instanceof Period) {
            return (Period) temporalAmount;
        }
        if ((temporalAmount instanceof ChronoPeriod) && !IsoChronology.INSTANCE.equals(((ChronoPeriod) temporalAmount).getChronology())) {
            throw new DateTimeException("Period requires ISO chronology: " + temporalAmount);
        }
        Jdk8Methods.requireNonNull(temporalAmount, "amount");
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (TemporalUnit temporalUnit : temporalAmount.getUnits()) {
            long j = temporalAmount.get(temporalUnit);
            if (temporalUnit == ChronoUnit.YEARS) {
                i = Jdk8Methods.safeToInt(j);
            } else if (temporalUnit == ChronoUnit.MONTHS) {
                i2 = Jdk8Methods.safeToInt(j);
            } else if (temporalUnit == ChronoUnit.DAYS) {
                i3 = Jdk8Methods.safeToInt(j);
            } else {
                throw new DateTimeException("Unit must be Years, Months or Days, but was " + temporalUnit);
            }
        }
        return create(i, i2, i3);
    }

    public static Period between(LocalDate localDate, LocalDate localDate2) {
        return localDate.until((ChronoLocalDate) localDate2);
    }

    public static Period parse(CharSequence charSequence) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Matcher matcher = PATTERN.matcher(charSequence);
        if (matcher.matches()) {
            int i = "-".equals(matcher.group(1)) ? -1 : 1;
            String group = matcher.group(2);
            String group2 = matcher.group(3);
            String group3 = matcher.group(4);
            String group4 = matcher.group(5);
            if (group != null || group2 != null || group3 != null || group4 != null) {
                try {
                    return create(parseNumber(charSequence, group, i), parseNumber(charSequence, group2, i), Jdk8Methods.safeAdd(parseNumber(charSequence, group4, i), Jdk8Methods.safeMultiply(parseNumber(charSequence, group3, i), 7)));
                } catch (NumberFormatException e) {
                    throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0).initCause(e));
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0);
    }

    private static int parseNumber(CharSequence charSequence, String str, int i) {
        if (str == null) {
            return 0;
        }
        try {
            return Jdk8Methods.safeMultiply(Integer.parseInt(str), i);
        } catch (ArithmeticException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0).initCause(e));
        }
    }

    private static Period create(int i, int i2, int i3) {
        if ((i | i2 | i3) == 0) {
            return ZERO;
        }
        return new Period(i, i2, i3);
    }

    private Period(int i, int i2, int i3) {
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    private Object readResolve() {
        return ((this.years | this.months) | this.days) == 0 ? ZERO : this;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Chronology getChronology() {
        return IsoChronology.INSTANCE;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public long get(TemporalUnit temporalUnit) {
        int i;
        if (temporalUnit == ChronoUnit.YEARS) {
            i = this.years;
        } else if (temporalUnit == ChronoUnit.MONTHS) {
            i = this.months;
        } else if (temporalUnit == ChronoUnit.DAYS) {
            i = this.days;
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
        return i;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public boolean isZero() {
        return this == ZERO;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public boolean isNegative() {
        return this.years < 0 || this.months < 0 || this.days < 0;
    }

    public int getYears() {
        return this.years;
    }

    public int getMonths() {
        return this.months;
    }

    public int getDays() {
        return this.days;
    }

    public Period withYears(int i) {
        return i == this.years ? this : create(i, this.months, this.days);
    }

    public Period withMonths(int i) {
        return i == this.months ? this : create(this.years, i, this.days);
    }

    public Period withDays(int i) {
        return i == this.days ? this : create(this.years, this.months, i);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Period plus(TemporalAmount temporalAmount) {
        Period from = from(temporalAmount);
        return create(Jdk8Methods.safeAdd(this.years, from.years), Jdk8Methods.safeAdd(this.months, from.months), Jdk8Methods.safeAdd(this.days, from.days));
    }

    public Period plusYears(long j) {
        return j == 0 ? this : create(Jdk8Methods.safeToInt(Jdk8Methods.safeAdd(this.years, j)), this.months, this.days);
    }

    public Period plusMonths(long j) {
        return j == 0 ? this : create(this.years, Jdk8Methods.safeToInt(Jdk8Methods.safeAdd(this.months, j)), this.days);
    }

    public Period plusDays(long j) {
        return j == 0 ? this : create(this.years, this.months, Jdk8Methods.safeToInt(Jdk8Methods.safeAdd(this.days, j)));
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Period minus(TemporalAmount temporalAmount) {
        Period from = from(temporalAmount);
        return create(Jdk8Methods.safeSubtract(this.years, from.years), Jdk8Methods.safeSubtract(this.months, from.months), Jdk8Methods.safeSubtract(this.days, from.days));
    }

    public Period minusYears(long j) {
        return j == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1L) : plusYears(-j);
    }

    public Period minusMonths(long j) {
        return j == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j);
    }

    public Period minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Period multipliedBy(int i) {
        return (this == ZERO || i == 1) ? this : create(Jdk8Methods.safeMultiply(this.years, i), Jdk8Methods.safeMultiply(this.months, i), Jdk8Methods.safeMultiply(this.days, i));
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Period negated() {
        return multipliedBy(-1);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Period normalized() {
        long totalMonths = toTotalMonths();
        long j = totalMonths / 12;
        int i = (int) (totalMonths % 12);
        return (j == ((long) this.years) && i == this.months) ? this : create(Jdk8Methods.safeToInt(j), i, this.days);
    }

    public long toTotalMonths() {
        return (this.years * 12) + this.months;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public Temporal addTo(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        int i = this.years;
        if (i != 0) {
            if (this.months != 0) {
                temporal = temporal.plus(toTotalMonths(), ChronoUnit.MONTHS);
            } else {
                temporal = temporal.plus(i, ChronoUnit.YEARS);
            }
        } else {
            int i2 = this.months;
            if (i2 != 0) {
                temporal = temporal.plus(i2, ChronoUnit.MONTHS);
            }
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.plus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public Temporal subtractFrom(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        int i = this.years;
        if (i != 0) {
            if (this.months != 0) {
                temporal = temporal.minus(toTotalMonths(), ChronoUnit.MONTHS);
            } else {
                temporal = temporal.minus(i, ChronoUnit.YEARS);
            }
        } else {
            int i2 = this.months;
            if (i2 != 0) {
                temporal = temporal.minus(i2, ChronoUnit.MONTHS);
            }
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.minus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        return this.years == period.years && this.months == period.months && this.days == period.days;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public int hashCode() {
        return this.years + Integer.rotateLeft(this.months, 8) + Integer.rotateLeft(this.days, 16);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public String toString() {
        if (this == ZERO) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('P');
        int i = this.years;
        if (i != 0) {
            sb.append(i);
            sb.append('Y');
        }
        int i2 = this.months;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        int i3 = this.days;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }
}

package org.threeten.p095bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import org.threeten.p095bp.Clock;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.Period;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalAdjuster;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.p095bp.temporal.ValueRange;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class JapaneseDate extends ChronoDateImpl<JapaneseDate> implements Serializable {
    static final LocalDate MIN_DATE = LocalDate.m4199of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private transient JapaneseEra era;
    private final LocalDate isoDate;
    private transient int yearOfEra;

    @Override // org.threeten.p095bp.chrono.ChronoDateImpl, org.threeten.p095bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    public static JapaneseDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static JapaneseDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static JapaneseDate now(Clock clock) {
        return new JapaneseDate(LocalDate.now(clock));
    }

    /* renamed from: of */
    public static JapaneseDate m4237of(JapaneseEra japaneseEra, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(japaneseEra, "era");
        if (i < 1) {
            throw new DateTimeException("Invalid YearOfEra: " + i);
        }
        LocalDate startDate = japaneseEra.startDate();
        LocalDate endDate = japaneseEra.endDate();
        LocalDate m4199of = LocalDate.m4199of((startDate.getYear() - 1) + i, i2, i3);
        if (m4199of.isBefore(startDate) || m4199of.isAfter(endDate)) {
            throw new DateTimeException("Requested date is outside bounds of era " + japaneseEra);
        }
        return new JapaneseDate(japaneseEra, i, m4199of);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseDate ofYearDay(JapaneseEra japaneseEra, int i, int i2) {
        Jdk8Methods.requireNonNull(japaneseEra, "era");
        if (i < 1) {
            throw new DateTimeException("Invalid YearOfEra: " + i);
        }
        LocalDate startDate = japaneseEra.startDate();
        LocalDate endDate = japaneseEra.endDate();
        if (i == 1 && (i2 = i2 + (startDate.getDayOfYear() - 1)) > startDate.lengthOfYear()) {
            throw new DateTimeException("DayOfYear exceeds maximum allowed in the first year of era " + japaneseEra);
        }
        LocalDate ofYearDay = LocalDate.ofYearDay((startDate.getYear() - 1) + i, i2);
        if (ofYearDay.isBefore(startDate) || ofYearDay.isAfter(endDate)) {
            throw new DateTimeException("Requested date is outside bounds of era " + japaneseEra);
        }
        return new JapaneseDate(japaneseEra, i, ofYearDay);
    }

    /* renamed from: of */
    public static JapaneseDate m4236of(int i, int i2, int i3) {
        return new JapaneseDate(LocalDate.m4199of(i, i2, i3));
    }

    public static JapaneseDate from(TemporalAccessor temporalAccessor) {
        return JapaneseChronology.INSTANCE.date(temporalAccessor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JapaneseDate(LocalDate localDate) {
        if (localDate.isBefore(MIN_DATE)) {
            throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
        }
        this.era = JapaneseEra.from(localDate);
        this.yearOfEra = localDate.getYear() - (this.era.startDate().getYear() - 1);
        this.isoDate = localDate;
    }

    JapaneseDate(JapaneseEra japaneseEra, int i, LocalDate localDate) {
        if (localDate.isBefore(MIN_DATE)) {
            throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
        }
        this.era = japaneseEra;
        this.yearOfEra = i;
        this.isoDate = localDate;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.era = JapaneseEra.from(this.isoDate);
        this.yearOfEra = this.isoDate.getYear() - (this.era.startDate().getYear() - 1);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public JapaneseChronology getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public JapaneseEra getEra() {
        return this.era;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public int lengthOfYear() {
        Calendar calendar = Calendar.getInstance(JapaneseChronology.LOCALE);
        calendar.set(0, this.era.getValue() + 2);
        calendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return calendar.getActualMaximum(6);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || temporalField == ChronoField.ALIGNED_WEEK_OF_MONTH || temporalField == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return super.isSupported(temporalField);
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i = C89461.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
                if (i == 1) {
                    return actualRange(6);
                }
                if (i == 2) {
                    return actualRange(1);
                }
                return getChronology().range(chronoField);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    private ValueRange actualRange(int i) {
        Calendar calendar = Calendar.getInstance(JapaneseChronology.LOCALE);
        calendar.set(0, this.era.getValue() + 2);
        calendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return ValueRange.m4244of(calendar.getActualMinimum(i), calendar.getActualMaximum(i));
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            switch ((ChronoField) temporalField) {
                case DAY_OF_YEAR:
                    return getDayOfYear();
                case YEAR_OF_ERA:
                    return this.yearOfEra;
                case ALIGNED_DAY_OF_WEEK_IN_MONTH:
                case ALIGNED_DAY_OF_WEEK_IN_YEAR:
                case ALIGNED_WEEK_OF_MONTH:
                case ALIGNED_WEEK_OF_YEAR:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                case ERA:
                    return this.era.getValue();
                default:
                    return this.isoDate.getLong(temporalField);
            }
        }
        return temporalField.getFrom(this);
    }

    private long getDayOfYear() {
        int dayOfYear;
        if (this.yearOfEra == 1) {
            dayOfYear = (this.isoDate.getDayOfYear() - this.era.startDate().getDayOfYear()) + 1;
        } else {
            dayOfYear = this.isoDate.getDayOfYear();
        }
        return dayOfYear;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate with(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.with(temporalAdjuster);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (getLong(chronoField) == j) {
                return this;
            }
            int i = C89461.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
            if (i == 1 || i == 2 || i == 7) {
                int checkValidIntValue = getChronology().range(chronoField).checkValidIntValue(j, chronoField);
                int i2 = C89461.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
                if (i2 == 1) {
                    return with(this.isoDate.plusDays(checkValidIntValue - getDayOfYear()));
                }
                if (i2 == 2) {
                    return withYear(checkValidIntValue);
                }
                if (i2 == 7) {
                    return withYear(JapaneseEra.m4238of(checkValidIntValue), this.yearOfEra);
                }
            }
            return with(this.isoDate.with(temporalField, j));
        }
        return (JapaneseDate) temporalField.adjustInto(this, j);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate plus(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.plus(temporalAmount);
    }

    @Override // org.threeten.p095bp.chrono.ChronoDateImpl, org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.plus(j, temporalUnit);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate minus(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.minus(temporalAmount);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public JapaneseDate minus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.minus(j, temporalUnit);
    }

    private JapaneseDate withYear(JapaneseEra japaneseEra, int i) {
        return with(this.isoDate.withYear(JapaneseChronology.INSTANCE.prolepticYear(japaneseEra, i)));
    }

    private JapaneseDate withYear(int i) {
        return withYear(getEra(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.p095bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.p095bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.p095bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    private JapaneseDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new JapaneseDate(localDate);
    }

    @Override // org.threeten.p095bp.chrono.ChronoDateImpl, org.threeten.p095bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<JapaneseDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override // org.threeten.p095bp.chrono.ChronoDateImpl, org.threeten.p095bp.chrono.ChronoLocalDate
    public ChronoPeriod until(ChronoLocalDate chronoLocalDate) {
        Period until = this.isoDate.until(chronoLocalDate);
        return getChronology().period(until.getYears(), until.getMonths(), until.getDays());
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JapaneseDate) {
            return this.isoDate.equals(((JapaneseDate) obj).isoDate);
        }
        return false;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public int hashCode() {
        return getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return JapaneseChronology.INSTANCE.date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }
}

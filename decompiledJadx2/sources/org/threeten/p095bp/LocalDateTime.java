package org.threeten.p095bp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.threeten.p095bp.chrono.ChronoLocalDateTime;
import org.threeten.p095bp.chrono.ChronoZonedDateTime;
import org.threeten.p095bp.format.DateTimeFormatter;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalAdjuster;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.TemporalQueries;
import org.threeten.p095bp.temporal.TemporalQuery;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.p095bp.temporal.ValueRange;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class LocalDateTime extends ChronoLocalDateTime<LocalDate> implements Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6207766400415563566L;
    private final LocalDate date;
    private final LocalTime time;
    public static final LocalDateTime MIN = m4207of(LocalDate.MIN, LocalTime.MIN);
    public static final LocalDateTime MAX = m4207of(LocalDate.MAX, LocalTime.MAX);
    public static final TemporalQuery<LocalDateTime> FROM = new TemporalQuery<LocalDateTime>() { // from class: org.threeten.bp.LocalDateTime.1
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public LocalDateTime queryFrom(TemporalAccessor temporalAccessor) {
            return LocalDateTime.from(temporalAccessor);
        }
    };

    public static LocalDateTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static LocalDateTime now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static LocalDateTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant instant = clock.instant();
        return ofEpochSecond(instant.getEpochSecond(), instant.getNano(), clock.getZone().getRules().getOffset(instant));
    }

    /* renamed from: of */
    public static LocalDateTime m4204of(int i, Month month, int i2, int i3, int i4) {
        return new LocalDateTime(LocalDate.m4200of(i, month, i2), LocalTime.m4208of(i3, i4));
    }

    /* renamed from: of */
    public static LocalDateTime m4205of(int i, Month month, int i2, int i3, int i4, int i5) {
        return new LocalDateTime(LocalDate.m4200of(i, month, i2), LocalTime.m4209of(i3, i4, i5));
    }

    /* renamed from: of */
    public static LocalDateTime m4206of(int i, Month month, int i2, int i3, int i4, int i5, int i6) {
        return new LocalDateTime(LocalDate.m4200of(i, month, i2), LocalTime.m4210of(i3, i4, i5, i6));
    }

    /* renamed from: of */
    public static LocalDateTime m4201of(int i, int i2, int i3, int i4, int i5) {
        return new LocalDateTime(LocalDate.m4199of(i, i2, i3), LocalTime.m4208of(i4, i5));
    }

    /* renamed from: of */
    public static LocalDateTime m4202of(int i, int i2, int i3, int i4, int i5, int i6) {
        return new LocalDateTime(LocalDate.m4199of(i, i2, i3), LocalTime.m4209of(i4, i5, i6));
    }

    /* renamed from: of */
    public static LocalDateTime m4203of(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return new LocalDateTime(LocalDate.m4199of(i, i2, i3), LocalTime.m4210of(i4, i5, i6, i7));
    }

    /* renamed from: of */
    public static LocalDateTime m4207of(LocalDate localDate, LocalTime localTime) {
        Jdk8Methods.requireNonNull(localDate, TmpConstant.TYPE_VALUE_DATE);
        Jdk8Methods.requireNonNull(localTime, "time");
        return new LocalDateTime(localDate, localTime);
    }

    public static LocalDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zoneId, "zone");
        return ofEpochSecond(instant.getEpochSecond(), instant.getNano(), zoneId.getRules().getOffset(instant));
    }

    public static LocalDateTime ofEpochSecond(long j, int i, ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        return new LocalDateTime(LocalDate.ofEpochDay(Jdk8Methods.floorDiv(j + zoneOffset.getTotalSeconds(), 86400L)), LocalTime.ofSecondOfDay(Jdk8Methods.floorMod(r2, 86400), i));
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [org.threeten.bp.LocalDateTime] */
    public static LocalDateTime from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        }
        if (temporalAccessor instanceof ZonedDateTime) {
            return ((ZonedDateTime) temporalAccessor).toLocalDateTime2();
        }
        try {
            return new LocalDateTime(LocalDate.from(temporalAccessor), LocalTime.from(temporalAccessor));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static LocalDateTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return (LocalDateTime) dateTimeFormatter.parse(charSequence, FROM);
    }

    private LocalDateTime(LocalDate localDate, LocalTime localTime) {
        this.date = localDate;
        this.time = localTime;
    }

    private LocalDateTime with(LocalDate localDate, LocalTime localTime) {
        return (this.date == localDate && this.time == localTime) ? this : new LocalDateTime(localDate, localTime);
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField.isDateBased() || temporalField.isTimeBased() : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // org.threeten.p095bp.temporal.Temporal
    public boolean isSupported(TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? temporalUnit.isDateBased() || temporalUnit.isTimeBased() : temporalUnit != null && temporalUnit.isSupportedBy(this);
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.range(temporalField) : this.date.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField);
        }
        return super.get(temporalField);
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    public int getYear() {
        return this.date.getYear();
    }

    public int getMonthValue() {
        return this.date.getMonthValue();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }

    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    public int getDayOfYear() {
        return this.date.getDayOfYear();
    }

    public DayOfWeek getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    public int getHour() {
        return this.time.getHour();
    }

    public int getMinute() {
        return this.time.getMinute();
    }

    public int getSecond() {
        return this.time.getSecond();
    }

    public int getNano() {
        return this.time.getNano();
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return with((LocalDate) temporalAdjuster, this.time);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return with(this.date, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof LocalDateTime) {
            return (LocalDateTime) temporalAdjuster;
        }
        return (LocalDateTime) temporalAdjuster.adjustInto(this);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return with(this.date, this.time.with(temporalField, j));
            }
            return with(this.date.with(temporalField, j), this.time);
        }
        return (LocalDateTime) temporalField.adjustInto(this, j);
    }

    public LocalDateTime withYear(int i) {
        return with(this.date.withYear(i), this.time);
    }

    public LocalDateTime withMonth(int i) {
        return with(this.date.withMonth(i), this.time);
    }

    public LocalDateTime withDayOfMonth(int i) {
        return with(this.date.withDayOfMonth(i), this.time);
    }

    public LocalDateTime withDayOfYear(int i) {
        return with(this.date.withDayOfYear(i), this.time);
    }

    public LocalDateTime withHour(int i) {
        return with(this.date, this.time.withHour(i));
    }

    public LocalDateTime withMinute(int i) {
        return with(this.date, this.time.withMinute(i));
    }

    public LocalDateTime withSecond(int i) {
        return with(this.date, this.time.withSecond(i));
    }

    public LocalDateTime withNano(int i) {
        return with(this.date, this.time.withNano(i));
    }

    public LocalDateTime truncatedTo(TemporalUnit temporalUnit) {
        return with(this.date, this.time.truncatedTo(temporalUnit));
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime plus(TemporalAmount temporalAmount) {
        return (LocalDateTime) temporalAmount.addTo(this);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch ((ChronoUnit) temporalUnit) {
                case NANOS:
                    return plusNanos(j);
                case MICROS:
                    return plusDays(j / 86400000000L).plusNanos((j % 86400000000L) * 1000);
                case MILLIS:
                    return plusDays(j / 86400000).plusNanos((j % 86400000) * 1000000);
                case SECONDS:
                    return plusSeconds(j);
                case MINUTES:
                    return plusMinutes(j);
                case HOURS:
                    return plusHours(j);
                case HALF_DAYS:
                    return plusDays(j / 256).plusHours((j % 256) * 12);
                default:
                    return with(this.date.plus(j, temporalUnit), this.time);
            }
        }
        return (LocalDateTime) temporalUnit.addTo(this, j);
    }

    public LocalDateTime plusYears(long j) {
        return with(this.date.plusYears(j), this.time);
    }

    public LocalDateTime plusMonths(long j) {
        return with(this.date.plusMonths(j), this.time);
    }

    public LocalDateTime plusWeeks(long j) {
        return with(this.date.plusWeeks(j), this.time);
    }

    public LocalDateTime plusDays(long j) {
        return with(this.date.plusDays(j), this.time);
    }

    public LocalDateTime plusHours(long j) {
        return plusWithOverflow(this.date, j, 0L, 0L, 0L, 1);
    }

    public LocalDateTime plusMinutes(long j) {
        return plusWithOverflow(this.date, 0L, j, 0L, 0L, 1);
    }

    public LocalDateTime plusSeconds(long j) {
        return plusWithOverflow(this.date, 0L, 0L, j, 0L, 1);
    }

    public LocalDateTime plusNanos(long j) {
        return plusWithOverflow(this.date, 0L, 0L, 0L, j, 1);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime minus(TemporalAmount temporalAmount) {
        return (LocalDateTime) temporalAmount.subtractFrom(this);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public LocalDateTime minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, temporalUnit).plus(1L, temporalUnit) : plus(-j, temporalUnit);
    }

    public LocalDateTime minusYears(long j) {
        return j == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1L) : plusYears(-j);
    }

    public LocalDateTime minusMonths(long j) {
        return j == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j);
    }

    public LocalDateTime minusWeeks(long j) {
        return j == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : plusWeeks(-j);
    }

    public LocalDateTime minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    public LocalDateTime minusHours(long j) {
        return plusWithOverflow(this.date, j, 0L, 0L, 0L, -1);
    }

    public LocalDateTime minusMinutes(long j) {
        return plusWithOverflow(this.date, 0L, j, 0L, 0L, -1);
    }

    public LocalDateTime minusSeconds(long j) {
        return plusWithOverflow(this.date, 0L, 0L, j, 0L, -1);
    }

    public LocalDateTime minusNanos(long j) {
        return plusWithOverflow(this.date, 0L, 0L, 0L, j, -1);
    }

    private LocalDateTime plusWithOverflow(LocalDate localDate, long j, long j2, long j3, long j4, int i) {
        if ((j | j2 | j3 | j4) == 0) {
            return with(localDate, this.time);
        }
        long j5 = (j4 / 86400000000000L) + (j3 / 86400) + (j2 / AgentTestManager.TWENTY_FOUR_HOUR) + (j / 24);
        long j6 = i;
        long j7 = (j4 % 86400000000000L) + ((j3 % 86400) * 1000000000) + ((j2 % AgentTestManager.TWENTY_FOUR_HOUR) * 60000000000L) + ((j % 24) * 3600000000000L);
        long nanoOfDay = this.time.toNanoOfDay();
        long j8 = (j7 * j6) + nanoOfDay;
        long floorDiv = (j5 * j6) + Jdk8Methods.floorDiv(j8, 86400000000000L);
        long floorMod = Jdk8Methods.floorMod(j8, 86400000000000L);
        return with(localDate.plusDays(floorDiv), floorMod == nanoOfDay ? this.time : LocalTime.ofNanoOfDay(floorMod));
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.localDate()) {
            return (R) toLocalDate();
        }
        return (R) super.query(temporalQuery);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return super.adjustInto(temporal);
    }

    @Override // org.threeten.p095bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        LocalDateTime from = from((TemporalAccessor) temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            if (chronoUnit.isTimeBased()) {
                long daysUntil = this.date.daysUntil(from.date);
                long nanoOfDay = from.time.toNanoOfDay() - this.time.toNanoOfDay();
                if (daysUntil > 0 && nanoOfDay < 0) {
                    daysUntil--;
                    nanoOfDay += 86400000000000L;
                } else if (daysUntil < 0 && nanoOfDay > 0) {
                    daysUntil++;
                    nanoOfDay -= 86400000000000L;
                }
                switch (chronoUnit) {
                    case NANOS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 86400000000000L), nanoOfDay);
                    case MICROS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 86400000000L), nanoOfDay / 1000);
                    case MILLIS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 86400000L), nanoOfDay / 1000000);
                    case SECONDS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 86400), nanoOfDay / 1000000000);
                    case MINUTES:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 1440), nanoOfDay / 60000000000L);
                    case HOURS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 24), nanoOfDay / 3600000000000L);
                    case HALF_DAYS:
                        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(daysUntil, 2), nanoOfDay / 43200000000000L);
                    default:
                        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
                }
            }
            LocalDate localDate = from.date;
            if (localDate.isAfter(this.date) && from.time.isBefore(this.time)) {
                localDate = localDate.minusDays(1L);
            } else if (localDate.isBefore(this.date) && from.time.isAfter(this.time)) {
                localDate = localDate.plusDays(1L);
            }
            return this.date.until(localDate, temporalUnit);
        }
        return temporalUnit.between(this, from);
    }

    public OffsetDateTime atOffset(ZoneOffset zoneOffset) {
        return OffsetDateTime.m4216of(this, zoneOffset);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    /* renamed from: atZone, reason: merged with bridge method [inline-methods] */
    public ChronoZonedDateTime<LocalDate> atZone2(ZoneId zoneId) {
        return ZonedDateTime.m4228of(this, zoneId);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public LocalDate toLocalDate() {
        return this.date;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public LocalTime toLocalTime() {
        return this.time;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, java.lang.Comparable
    public int compareTo(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) chronoLocalDateTime);
        }
        return super.compareTo(chronoLocalDateTime);
    }

    private int compareTo0(LocalDateTime localDateTime) {
        int compareTo0 = this.date.compareTo0(localDateTime.toLocalDate());
        return compareTo0 == 0 ? this.time.compareTo(localDateTime.toLocalTime()) : compareTo0;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public boolean isAfter(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) chronoLocalDateTime) > 0;
        }
        return super.isAfter(chronoLocalDateTime);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public boolean isBefore(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) chronoLocalDateTime) < 0;
        }
        return super.isBefore(chronoLocalDateTime);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public boolean isEqual(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) chronoLocalDateTime) == 0;
        }
        return super.isEqual(chronoLocalDateTime);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalDateTime)) {
            return false;
        }
        LocalDateTime localDateTime = (LocalDateTime) obj;
        return this.date.equals(localDateTime.date) && this.time.equals(localDateTime.time);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public int hashCode() {
        return this.date.hashCode() ^ this.time.hashCode();
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public String toString() {
        return this.date.toString() + 'T' + this.time.toString();
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public String format(DateTimeFormatter dateTimeFormatter) {
        return super.format(dateTimeFormatter);
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        this.date.writeExternal(dataOutput);
        this.time.writeExternal(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalDateTime readExternal(DataInput dataInput) throws IOException {
        return m4207of(LocalDate.readExternal(dataInput), LocalTime.readExternal(dataInput));
    }
}

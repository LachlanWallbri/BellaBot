package org.threeten.p095bp.chrono;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAdjuster;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.ValueRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ChronoLocalDateTimeImpl<D extends ChronoLocalDate> extends ChronoLocalDateTime<D> implements Temporal, TemporalAdjuster, Serializable {
    private static final int HOURS_PER_DAY = 24;
    private static final long MICROS_PER_DAY = 86400000000L;
    private static final long MILLIS_PER_DAY = 86400000;
    private static final int MINUTES_PER_DAY = 1440;
    private static final int MINUTES_PER_HOUR = 60;
    private static final long NANOS_PER_DAY = 86400000000000L;
    private static final long NANOS_PER_HOUR = 3600000000000L;
    private static final long NANOS_PER_MINUTE = 60000000000L;
    private static final long NANOS_PER_SECOND = 1000000000;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final long serialVersionUID = 4556003607393004514L;
    private final D date;
    private final LocalTime time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: of */
    public static <R extends ChronoLocalDate> ChronoLocalDateTimeImpl<R> m4229of(R r, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl<>(r, localTime);
    }

    private ChronoLocalDateTimeImpl(D d, LocalTime localTime) {
        Jdk8Methods.requireNonNull(d, TmpConstant.TYPE_VALUE_DATE);
        Jdk8Methods.requireNonNull(localTime, "time");
        this.date = d;
        this.time = localTime;
    }

    private ChronoLocalDateTimeImpl<D> with(Temporal temporal, LocalTime localTime) {
        return (this.date == temporal && this.time == localTime) ? this : new ChronoLocalDateTimeImpl<>(this.date.getChronology().ensureChronoLocalDate(temporal), localTime);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public D toLocalDate() {
        return this.date;
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    public LocalTime toLocalTime() {
        return this.time;
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
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public ChronoLocalDateTimeImpl<D> with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof ChronoLocalDate) {
            return with((ChronoLocalDate) temporalAdjuster, this.time);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return with(this.date, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof ChronoLocalDateTimeImpl) {
            return this.date.getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl) temporalAdjuster);
        }
        return this.date.getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl) temporalAdjuster.adjustInto(this));
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.temporal.Temporal
    public ChronoLocalDateTimeImpl<D> with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return with(this.date, this.time.with(temporalField, j));
            }
            return with(this.date.with(temporalField, j), this.time);
        }
        return this.date.getChronology().ensureChronoLocalDateTime(temporalField.adjustInto(this, j));
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime, org.threeten.p095bp.temporal.Temporal
    public ChronoLocalDateTimeImpl<D> plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch ((ChronoUnit) temporalUnit) {
                case NANOS:
                    return plusNanos(j);
                case MICROS:
                    return plusDays(j / MICROS_PER_DAY).plusNanos((j % MICROS_PER_DAY) * 1000);
                case MILLIS:
                    return plusDays(j / MILLIS_PER_DAY).plusNanos((j % MILLIS_PER_DAY) * 1000000);
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
        return this.date.getChronology().ensureChronoLocalDateTime(temporalUnit.addTo(this, j));
    }

    private ChronoLocalDateTimeImpl<D> plusDays(long j) {
        return with(this.date.plus(j, ChronoUnit.DAYS), this.time);
    }

    private ChronoLocalDateTimeImpl<D> plusHours(long j) {
        return plusWithOverflow(this.date, j, 0L, 0L, 0L);
    }

    private ChronoLocalDateTimeImpl<D> plusMinutes(long j) {
        return plusWithOverflow(this.date, 0L, j, 0L, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChronoLocalDateTimeImpl<D> plusSeconds(long j) {
        return plusWithOverflow(this.date, 0L, 0L, j, 0L);
    }

    private ChronoLocalDateTimeImpl<D> plusNanos(long j) {
        return plusWithOverflow(this.date, 0L, 0L, 0L, j);
    }

    private ChronoLocalDateTimeImpl<D> plusWithOverflow(D d, long j, long j2, long j3, long j4) {
        if ((j | j2 | j3 | j4) == 0) {
            return with(d, this.time);
        }
        long j5 = (j4 / NANOS_PER_DAY) + (j3 / 86400) + (j2 / AgentTestManager.TWENTY_FOUR_HOUR) + (j / 24);
        long j6 = (j4 % NANOS_PER_DAY) + ((j3 % 86400) * NANOS_PER_SECOND) + ((j2 % AgentTestManager.TWENTY_FOUR_HOUR) * NANOS_PER_MINUTE) + ((j % 24) * NANOS_PER_HOUR);
        long nanoOfDay = this.time.toNanoOfDay();
        long j7 = j6 + nanoOfDay;
        long floorDiv = j5 + Jdk8Methods.floorDiv(j7, NANOS_PER_DAY);
        long floorMod = Jdk8Methods.floorMod(j7, NANOS_PER_DAY);
        return with(d.plus(floorDiv, ChronoUnit.DAYS), floorMod == nanoOfDay ? this.time : LocalTime.ofNanoOfDay(floorMod));
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDateTime
    /* renamed from: atZone */
    public ChronoZonedDateTime<D> atZone2(ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.ofBest(this, zoneId, null);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    @Override // org.threeten.p095bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDateTime<?> localDateTime = toLocalDate().getChronology().localDateTime(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            if (chronoUnit.isTimeBased()) {
                long j = localDateTime.getLong(ChronoField.EPOCH_DAY) - this.date.getLong(ChronoField.EPOCH_DAY);
                switch (chronoUnit) {
                    case NANOS:
                        j = Jdk8Methods.safeMultiply(j, NANOS_PER_DAY);
                        break;
                    case MICROS:
                        j = Jdk8Methods.safeMultiply(j, MICROS_PER_DAY);
                        break;
                    case MILLIS:
                        j = Jdk8Methods.safeMultiply(j, MILLIS_PER_DAY);
                        break;
                    case SECONDS:
                        j = Jdk8Methods.safeMultiply(j, 86400);
                        break;
                    case MINUTES:
                        j = Jdk8Methods.safeMultiply(j, MINUTES_PER_DAY);
                        break;
                    case HOURS:
                        j = Jdk8Methods.safeMultiply(j, 24);
                        break;
                    case HALF_DAYS:
                        j = Jdk8Methods.safeMultiply(j, 2);
                        break;
                }
                return Jdk8Methods.safeAdd(j, this.time.until(localDateTime.toLocalTime(), temporalUnit));
            }
            ?? localDate = localDateTime.toLocalDate();
            ChronoLocalDate chronoLocalDate = localDate;
            if (localDateTime.toLocalTime().isBefore(this.time)) {
                chronoLocalDate = localDate.minus(1L, ChronoUnit.DAYS);
            }
            return this.date.until(chronoLocalDate, temporalUnit);
        }
        return temporalUnit.between(this, localDateTime);
    }

    private Object writeReplace() {
        return new Ser((byte) 12, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.date);
        objectOutput.writeObject(this.time);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDateTime<?> readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return ((ChronoLocalDate) objectInput.readObject()).atTime((LocalTime) objectInput.readObject());
    }
}

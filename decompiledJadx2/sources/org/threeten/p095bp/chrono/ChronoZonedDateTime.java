package org.threeten.p095bp.chrono;

import java.util.Comparator;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.Instant;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.format.DateTimeFormatter;
import org.threeten.p095bp.jdk8.DefaultInterfaceTemporal;
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
public abstract class ChronoZonedDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements Temporal, Comparable<ChronoZonedDateTime<?>> {
    private static Comparator<ChronoZonedDateTime<?>> INSTANT_COMPARATOR = new Comparator<ChronoZonedDateTime<?>>() { // from class: org.threeten.bp.chrono.ChronoZonedDateTime.1
        @Override // java.util.Comparator
        public int compare(ChronoZonedDateTime<?> chronoZonedDateTime, ChronoZonedDateTime<?> chronoZonedDateTime2) {
            int compareLongs = Jdk8Methods.compareLongs(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
            return compareLongs == 0 ? Jdk8Methods.compareLongs(chronoZonedDateTime.toLocalTime().toNanoOfDay(), chronoZonedDateTime2.toLocalTime().toNanoOfDay()) : compareLongs;
        }
    };

    public abstract ZoneOffset getOffset();

    public abstract ZoneId getZone();

    @Override // org.threeten.p095bp.temporal.Temporal
    public abstract ChronoZonedDateTime<D> plus(long j, TemporalUnit temporalUnit);

    /* renamed from: toLocalDateTime */
    public abstract ChronoLocalDateTime<D> toLocalDateTime2();

    @Override // org.threeten.p095bp.temporal.Temporal
    public abstract ChronoZonedDateTime<D> with(TemporalField temporalField, long j);

    /* renamed from: withEarlierOffsetAtOverlap */
    public abstract ChronoZonedDateTime<D> withEarlierOffsetAtOverlap2();

    /* renamed from: withLaterOffsetAtOverlap */
    public abstract ChronoZonedDateTime<D> withLaterOffsetAtOverlap2();

    /* renamed from: withZoneSameInstant */
    public abstract ChronoZonedDateTime<D> withZoneSameInstant2(ZoneId zoneId);

    /* renamed from: withZoneSameLocal */
    public abstract ChronoZonedDateTime<D> withZoneSameLocal2(ZoneId zoneId);

    public static Comparator<ChronoZonedDateTime<?>> timeLineOrder() {
        return INSTANT_COMPARATOR;
    }

    public static ChronoZonedDateTime<?> from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoZonedDateTime) {
            return (ChronoZonedDateTime) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology == null) {
            throw new DateTimeException("No Chronology found to create ChronoZonedDateTime: " + temporalAccessor.getClass());
        }
        return chronology.zonedDateTime(temporalAccessor);
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) {
                return temporalField.range();
            }
            return toLocalDateTime2().range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.threeten.bp.chrono.ChronoZonedDateTime$2 */
    /* loaded from: classes9.dex */
    static /* synthetic */ class C89402 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = C89402.$SwitchMap$org$threeten$bp$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return getOffset().getTotalSeconds();
                }
                return toLocalDateTime2().get(temporalField);
            }
            throw new UnsupportedTemporalTypeException("Field too large for an int: " + temporalField);
        }
        return super.get(temporalField);
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = C89402.$SwitchMap$org$threeten$bp$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 1) {
                return toEpochSecond();
            }
            if (i == 2) {
                return getOffset().getTotalSeconds();
            }
            return toLocalDateTime2().getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    public D toLocalDate() {
        return toLocalDateTime2().toLocalDate();
    }

    public LocalTime toLocalTime() {
        return toLocalDateTime2().toLocalTime();
    }

    public Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public ChronoZonedDateTime<D> with(TemporalAdjuster temporalAdjuster) {
        return toLocalDate().getChronology().ensureChronoZonedDateTime(super.with(temporalAdjuster));
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public ChronoZonedDateTime<D> plus(TemporalAmount temporalAmount) {
        return toLocalDate().getChronology().ensureChronoZonedDateTime(super.plus(temporalAmount));
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public ChronoZonedDateTime<D> minus(TemporalAmount temporalAmount) {
        return toLocalDate().getChronology().ensureChronoZonedDateTime(super.minus(temporalAmount));
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporal, org.threeten.p095bp.temporal.Temporal
    public ChronoZonedDateTime<D> minus(long j, TemporalUnit temporalUnit) {
        return toLocalDate().getChronology().ensureChronoZonedDateTime(super.minus(j, temporalUnit));
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone()) {
            return (R) getZone();
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) toLocalDate().getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.offset()) {
            return (R) getOffset();
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            return (R) LocalDate.ofEpochDay(toLocalDate().toEpochDay());
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return (R) toLocalTime();
        }
        return (R) super.query(temporalQuery);
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    public Instant toInstant() {
        return Instant.ofEpochSecond(toEpochSecond(), toLocalTime().getNano());
    }

    public long toEpochSecond() {
        return ((toLocalDate().toEpochDay() * 86400) + toLocalTime().toSecondOfDay()) - getOffset().getTotalSeconds();
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    @Override // java.lang.Comparable
    public int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int compareLongs = Jdk8Methods.compareLongs(toEpochSecond(), chronoZonedDateTime.toEpochSecond());
        if (compareLongs != 0) {
            return compareLongs;
        }
        int nano = toLocalTime().getNano() - chronoZonedDateTime.toLocalTime().getNano();
        if (nano != 0) {
            return nano;
        }
        int compareTo = toLocalDateTime2().compareTo(chronoZonedDateTime.toLocalDateTime2());
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = getZone().getId().compareTo(chronoZonedDateTime.getZone().getId());
        return compareTo2 == 0 ? toLocalDate().getChronology().compareTo(chronoZonedDateTime.toLocalDate().getChronology()) : compareTo2;
    }

    public boolean isAfter(ChronoZonedDateTime<?> chronoZonedDateTime) {
        long epochSecond = toEpochSecond();
        long epochSecond2 = chronoZonedDateTime.toEpochSecond();
        return epochSecond > epochSecond2 || (epochSecond == epochSecond2 && toLocalTime().getNano() > chronoZonedDateTime.toLocalTime().getNano());
    }

    public boolean isBefore(ChronoZonedDateTime<?> chronoZonedDateTime) {
        long epochSecond = toEpochSecond();
        long epochSecond2 = chronoZonedDateTime.toEpochSecond();
        return epochSecond < epochSecond2 || (epochSecond == epochSecond2 && toLocalTime().getNano() < chronoZonedDateTime.toLocalTime().getNano());
    }

    public boolean isEqual(ChronoZonedDateTime<?> chronoZonedDateTime) {
        return toEpochSecond() == chronoZonedDateTime.toEpochSecond() && toLocalTime().getNano() == chronoZonedDateTime.toLocalTime().getNano();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime<?>) obj) == 0;
    }

    public int hashCode() {
        return (toLocalDateTime2().hashCode() ^ getOffset().hashCode()) ^ Integer.rotateLeft(getZone().hashCode(), 3);
    }

    public String toString() {
        String str = toLocalDateTime2().toString() + getOffset().toString();
        if (getOffset() == getZone()) {
            return str;
        }
        return str + '[' + getZone().toString() + ']';
    }
}

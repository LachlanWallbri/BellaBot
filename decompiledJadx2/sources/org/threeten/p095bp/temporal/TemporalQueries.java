package org.threeten.p095bp.temporal;

import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.chrono.Chronology;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TemporalQueries {
    static final TemporalQuery<ZoneId> ZONE_ID = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.temporal.TemporalQueries.1
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            return (ZoneId) temporalAccessor.query(this);
        }
    };
    static final TemporalQuery<Chronology> CHRONO = new TemporalQuery<Chronology>() { // from class: org.threeten.bp.temporal.TemporalQueries.2
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public Chronology queryFrom(TemporalAccessor temporalAccessor) {
            return (Chronology) temporalAccessor.query(this);
        }
    };
    static final TemporalQuery<TemporalUnit> PRECISION = new TemporalQuery<TemporalUnit>() { // from class: org.threeten.bp.temporal.TemporalQueries.3
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public TemporalUnit queryFrom(TemporalAccessor temporalAccessor) {
            return (TemporalUnit) temporalAccessor.query(this);
        }
    };
    static final TemporalQuery<ZoneId> ZONE = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.temporal.TemporalQueries.4
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.ZONE_ID);
            return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(TemporalQueries.OFFSET);
        }
    };
    static final TemporalQuery<ZoneOffset> OFFSET = new TemporalQuery<ZoneOffset>() { // from class: org.threeten.bp.temporal.TemporalQueries.5
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public ZoneOffset queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS)) {
                return ZoneOffset.ofTotalSeconds(temporalAccessor.get(ChronoField.OFFSET_SECONDS));
            }
            return null;
        }
    };
    static final TemporalQuery<LocalDate> LOCAL_DATE = new TemporalQuery<LocalDate>() { // from class: org.threeten.bp.temporal.TemporalQueries.6
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public LocalDate queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                return LocalDate.ofEpochDay(temporalAccessor.getLong(ChronoField.EPOCH_DAY));
            }
            return null;
        }
    };
    static final TemporalQuery<LocalTime> LOCAL_TIME = new TemporalQuery<LocalTime>() { // from class: org.threeten.bp.temporal.TemporalQueries.7
        @Override // org.threeten.p095bp.temporal.TemporalQuery
        public LocalTime queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.NANO_OF_DAY)) {
                return LocalTime.ofNanoOfDay(temporalAccessor.getLong(ChronoField.NANO_OF_DAY));
            }
            return null;
        }
    };

    private TemporalQueries() {
    }

    public static final TemporalQuery<ZoneId> zoneId() {
        return ZONE_ID;
    }

    public static final TemporalQuery<Chronology> chronology() {
        return CHRONO;
    }

    public static final TemporalQuery<TemporalUnit> precision() {
        return PRECISION;
    }

    public static final TemporalQuery<ZoneId> zone() {
        return ZONE;
    }

    public static final TemporalQuery<ZoneOffset> offset() {
        return OFFSET;
    }

    public static final TemporalQuery<LocalDate> localDate() {
        return LOCAL_DATE;
    }

    public static final TemporalQuery<LocalTime> localTime() {
        return LOCAL_TIME;
    }
}

package org.threeten.p095bp.format;

import java.util.Locale;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.Instant;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.chrono.IsoChronology;
import org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.TemporalQueries;
import org.threeten.p095bp.temporal.TemporalQuery;
import org.threeten.p095bp.temporal.ValueRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DateTimePrintContext {
    private Locale locale;
    private int optional;
    private DecimalStyle symbols;
    private TemporalAccessor temporal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        this.temporal = adjust(temporalAccessor, dateTimeFormatter);
        this.locale = dateTimeFormatter.getLocale();
        this.symbols = dateTimeFormatter.getDecimalStyle();
    }

    DateTimePrintContext(TemporalAccessor temporalAccessor, Locale locale, DecimalStyle decimalStyle) {
        this.temporal = temporalAccessor;
        this.locale = locale;
        this.symbols = decimalStyle;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        Chronology chronology = dateTimeFormatter.getChronology();
        ZoneId zone = dateTimeFormatter.getZone();
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        Chronology chronology2 = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        final ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
        final ChronoLocalDate chronoLocalDate = null;
        if (Jdk8Methods.equals(chronology2, chronology)) {
            chronology = null;
        }
        if (Jdk8Methods.equals(zoneId, zone)) {
            zone = null;
        }
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        final Chronology chronology3 = chronology != null ? chronology : chronology2;
        if (zone != null) {
            zoneId = zone;
        }
        if (zone != null) {
            if (temporalAccessor.isSupported(ChronoField.INSTANT_SECONDS)) {
                if (chronology3 == null) {
                    chronology3 = IsoChronology.INSTANCE;
                }
                return chronology3.zonedDateTime(Instant.from(temporalAccessor), zone);
            }
            ZoneId normalized = zone.normalized();
            ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.query(TemporalQueries.offset());
            if ((normalized instanceof ZoneOffset) && zoneOffset != null && !normalized.equals(zoneOffset)) {
                throw new DateTimeException("Invalid override zone for temporal: " + zone + " " + temporalAccessor);
            }
        }
        if (chronology != null) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                chronoLocalDate = chronology3.date(temporalAccessor);
            } else if (chronology != IsoChronology.INSTANCE || chronology2 != null) {
                for (ChronoField chronoField : ChronoField.values()) {
                    if (chronoField.isDateBased() && temporalAccessor.isSupported(chronoField)) {
                        throw new DateTimeException("Invalid override chronology for temporal: " + chronology + " " + temporalAccessor);
                    }
                }
            }
        }
        return new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.format.DateTimePrintContext.1
            @Override // org.threeten.p095bp.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.isSupported(temporalField);
                }
                return temporalAccessor.isSupported(temporalField);
            }

            @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
            public ValueRange range(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.range(temporalField);
                }
                return temporalAccessor.range(temporalField);
            }

            @Override // org.threeten.p095bp.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.getLong(temporalField);
                }
                return temporalAccessor.getLong(temporalField);
            }

            @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.chronology()) {
                    return (R) chronology3;
                }
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return (R) zoneId;
                }
                if (temporalQuery == TemporalQueries.precision()) {
                    return (R) temporalAccessor.query(temporalQuery);
                }
                return temporalQuery.queryFrom(this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TemporalAccessor getTemporal() {
        return this.temporal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Locale getLocale() {
        return this.locale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecimalStyle getSymbols() {
        return this.symbols;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startOptional() {
        this.optional++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endOptional() {
        this.optional--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <R> R getValue(TemporalQuery<R> temporalQuery) {
        R r = (R) this.temporal.query(temporalQuery);
        if (r != null || this.optional != 0) {
            return r;
        }
        throw new DateTimeException("Unable to extract value: " + this.temporal.getClass());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Long getValue(TemporalField temporalField) {
        try {
            return Long.valueOf(this.temporal.getLong(temporalField));
        } catch (DateTimeException e) {
            if (this.optional > 0) {
                return null;
            }
            throw e;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }

    void setDateTime(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        this.temporal = temporalAccessor;
    }

    void setLocale(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        this.locale = locale;
    }
}

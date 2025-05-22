package org.threeten.p095bp.temporal;

import java.util.Locale;
import java.util.Map;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.format.ResolverStyle;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class JulianFields {
    public static final TemporalField JULIAN_DAY = Field.JULIAN_DAY;
    public static final TemporalField MODIFIED_JULIAN_DAY = Field.MODIFIED_JULIAN_DAY;
    public static final TemporalField RATA_DIE = Field.RATA_DIE;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private enum Field implements TemporalField {
        JULIAN_DAY("JulianDay", ChronoUnit.DAYS, ChronoUnit.FOREVER, 2440588),
        MODIFIED_JULIAN_DAY("ModifiedJulianDay", ChronoUnit.DAYS, ChronoUnit.FOREVER, 40587),
        RATA_DIE("RataDie", ChronoUnit.DAYS, ChronoUnit.FOREVER, 719163);

        private final TemporalUnit baseUnit;
        private final String name;
        private final long offset;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        Field(String str, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, long j) {
            this.name = str;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = ValueRange.m4244of((-365243219162L) + j, 365241780471L + j);
            this.offset = j;
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
            return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            if (!isSupportedBy(temporalAccessor)) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + this);
            }
            return range();
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public long getFrom(TemporalAccessor temporalAccessor) {
            return temporalAccessor.getLong(ChronoField.EPOCH_DAY) + this.offset;
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public <R extends Temporal> R adjustInto(R r, long j) {
            if (!range().isValidValue(j)) {
                throw new DateTimeException("Invalid value: " + this.name + " " + j);
            }
            return (R) r.with(ChronoField.EPOCH_DAY, Jdk8Methods.safeSubtract(j, this.offset));
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return toString();
        }

        @Override // org.threeten.p095bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            return Chronology.from(temporalAccessor).dateEpochDay(Jdk8Methods.safeSubtract(map.remove(this).longValue(), this.offset));
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }
}

package org.threeten.p095bp.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.Instant;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.Period;
import org.threeten.p095bp.ZoneId;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.chrono.ChronoLocalDateTime;
import org.threeten.p095bp.chrono.ChronoZonedDateTime;
import org.threeten.p095bp.chrono.Chronology;
import org.threeten.p095bp.chrono.IsoChronology;
import org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.TemporalQueries;
import org.threeten.p095bp.temporal.TemporalQuery;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DateTimeBuilder extends DefaultInterfaceTemporalAccessor implements TemporalAccessor, Cloneable {
    Chronology chrono;
    ChronoLocalDate date;
    Period excessDays;
    final Map<TemporalField, Long> fieldValues = new HashMap();
    boolean leapSecond;
    LocalTime time;
    ZoneId zone;

    public DateTimeBuilder() {
    }

    public DateTimeBuilder(TemporalField temporalField, long j) {
        addFieldValue(temporalField, j);
    }

    private Long getFieldValue0(TemporalField temporalField) {
        return this.fieldValues.get(temporalField);
    }

    DateTimeBuilder addFieldValue(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 != null && fieldValue0.longValue() != j) {
            throw new DateTimeException("Conflict found: " + temporalField + " " + fieldValue0 + " differs from " + temporalField + " " + j + ": " + this);
        }
        return putFieldValue0(temporalField, j);
    }

    private DateTimeBuilder putFieldValue0(TemporalField temporalField, long j) {
        this.fieldValues.put(temporalField, Long.valueOf(j));
        return this;
    }

    void addObject(ChronoLocalDate chronoLocalDate) {
        this.date = chronoLocalDate;
    }

    void addObject(LocalTime localTime) {
        this.time = localTime;
    }

    public DateTimeBuilder resolve(ResolverStyle resolverStyle, Set<TemporalField> set) {
        ChronoLocalDate chronoLocalDate;
        if (set != null) {
            this.fieldValues.keySet().retainAll(set);
        }
        mergeInstantFields();
        mergeDate(resolverStyle);
        mergeTime(resolverStyle);
        if (resolveFields(resolverStyle)) {
            mergeInstantFields();
            mergeDate(resolverStyle);
            mergeTime(resolverStyle);
        }
        resolveTimeInferZeroes(resolverStyle);
        crossCheck();
        Period period = this.excessDays;
        if (period != null && !period.isZero() && (chronoLocalDate = this.date) != null && this.time != null) {
            this.date = chronoLocalDate.plus((TemporalAmount) this.excessDays);
            this.excessDays = Period.ZERO;
        }
        resolveFractional();
        resolveInstant();
        return this;
    }

    private boolean resolveFields(ResolverStyle resolverStyle) {
        int i = 0;
        loop0: while (i < 100) {
            Iterator<Map.Entry<TemporalField, Long>> it = this.fieldValues.entrySet().iterator();
            while (it.hasNext()) {
                TemporalField key = it.next().getKey();
                TemporalAccessor resolve = key.resolve(this.fieldValues, this, resolverStyle);
                if (resolve != null) {
                    if (resolve instanceof ChronoZonedDateTime) {
                        ChronoZonedDateTime chronoZonedDateTime = (ChronoZonedDateTime) resolve;
                        ZoneId zoneId = this.zone;
                        if (zoneId == null) {
                            this.zone = chronoZonedDateTime.getZone();
                        } else if (!zoneId.equals(chronoZonedDateTime.getZone())) {
                            throw new DateTimeException("ChronoZonedDateTime must use the effective parsed zone: " + this.zone);
                        }
                        resolve = chronoZonedDateTime.toLocalDateTime2();
                    }
                    if (resolve instanceof ChronoLocalDate) {
                        resolveMakeChanges(key, (ChronoLocalDate) resolve);
                    } else if (resolve instanceof LocalTime) {
                        resolveMakeChanges(key, (LocalTime) resolve);
                    } else if (resolve instanceof ChronoLocalDateTime) {
                        ChronoLocalDateTime chronoLocalDateTime = (ChronoLocalDateTime) resolve;
                        resolveMakeChanges(key, chronoLocalDateTime.toLocalDate());
                        resolveMakeChanges(key, chronoLocalDateTime.toLocalTime());
                    } else {
                        throw new DateTimeException("Unknown type: " + resolve.getClass().getName());
                    }
                } else if (!this.fieldValues.containsKey(key)) {
                    break;
                }
                i++;
            }
        }
        if (i != 100) {
            return i > 0;
        }
        throw new DateTimeException("Badly written field");
    }

    private void resolveMakeChanges(TemporalField temporalField, ChronoLocalDate chronoLocalDate) {
        if (!this.chrono.equals(chronoLocalDate.getChronology())) {
            throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + this.chrono);
        }
        long epochDay = chronoLocalDate.toEpochDay();
        Long put = this.fieldValues.put(ChronoField.EPOCH_DAY, Long.valueOf(epochDay));
        if (put == null || put.longValue() == epochDay) {
            return;
        }
        throw new DateTimeException("Conflict found: " + LocalDate.ofEpochDay(put.longValue()) + " differs from " + LocalDate.ofEpochDay(epochDay) + " while resolving  " + temporalField);
    }

    private void resolveMakeChanges(TemporalField temporalField, LocalTime localTime) {
        long nanoOfDay = localTime.toNanoOfDay();
        Long put = this.fieldValues.put(ChronoField.NANO_OF_DAY, Long.valueOf(nanoOfDay));
        if (put == null || put.longValue() == nanoOfDay) {
            return;
        }
        throw new DateTimeException("Conflict found: " + LocalTime.ofNanoOfDay(put.longValue()) + " differs from " + localTime + " while resolving  " + temporalField);
    }

    private void mergeDate(ResolverStyle resolverStyle) {
        if (this.chrono instanceof IsoChronology) {
            checkDate(IsoChronology.INSTANCE.resolveDate(this.fieldValues, resolverStyle));
        } else if (this.fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            checkDate(LocalDate.ofEpochDay(this.fieldValues.remove(ChronoField.EPOCH_DAY).longValue()));
        }
    }

    private void checkDate(LocalDate localDate) {
        if (localDate != null) {
            addObject(localDate);
            for (TemporalField temporalField : this.fieldValues.keySet()) {
                if ((temporalField instanceof ChronoField) && temporalField.isDateBased()) {
                    try {
                        long j = localDate.getLong(temporalField);
                        Long l = this.fieldValues.get(temporalField);
                        if (j != l.longValue()) {
                            throw new DateTimeException("Conflict found: Field " + temporalField + " " + j + " differs from " + temporalField + " " + l + " derived from " + localDate);
                        }
                    } catch (DateTimeException unused) {
                        continue;
                    }
                }
            }
        }
    }

    private void mergeTime(ResolverStyle resolverStyle) {
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_DAY)) {
            long longValue = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue != 0)) {
                ChronoField.CLOCK_HOUR_OF_DAY.checkValidValue(longValue);
            }
            ChronoField chronoField = ChronoField.HOUR_OF_DAY;
            if (longValue == 24) {
                longValue = 0;
            }
            addFieldValue(chronoField, longValue);
        }
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_AMPM)) {
            long longValue2 = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_AMPM).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue2 != 0)) {
                ChronoField.CLOCK_HOUR_OF_AMPM.checkValidValue(longValue2);
            }
            addFieldValue(ChronoField.HOUR_OF_AMPM, longValue2 != 12 ? longValue2 : 0L);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY)) {
                ChronoField.AMPM_OF_DAY.checkValidValue(this.fieldValues.get(ChronoField.AMPM_OF_DAY).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
                ChronoField.HOUR_OF_AMPM.checkValidValue(this.fieldValues.get(ChronoField.HOUR_OF_AMPM).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY) && this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
            addFieldValue(ChronoField.HOUR_OF_DAY, (this.fieldValues.remove(ChronoField.AMPM_OF_DAY).longValue() * 12) + this.fieldValues.remove(ChronoField.HOUR_OF_AMPM).longValue());
        }
        if (this.fieldValues.containsKey(ChronoField.NANO_OF_DAY)) {
            long longValue3 = this.fieldValues.remove(ChronoField.NANO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.NANO_OF_DAY.checkValidValue(longValue3);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue3 / 1000000000);
            addFieldValue(ChronoField.NANO_OF_SECOND, longValue3 % 1000000000);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_DAY)) {
            long longValue4 = this.fieldValues.remove(ChronoField.MICRO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MICRO_OF_DAY.checkValidValue(longValue4);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue4 / 1000000);
            addFieldValue(ChronoField.MICRO_OF_SECOND, longValue4 % 1000000);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_DAY)) {
            long longValue5 = this.fieldValues.remove(ChronoField.MILLI_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MILLI_OF_DAY.checkValidValue(longValue5);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue5 / 1000);
            addFieldValue(ChronoField.MILLI_OF_SECOND, longValue5 % 1000);
        }
        if (this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY)) {
            long longValue6 = this.fieldValues.remove(ChronoField.SECOND_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.SECOND_OF_DAY.checkValidValue(longValue6);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, longValue6 / 3600);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, (longValue6 / 60) % 60);
            addFieldValue(ChronoField.SECOND_OF_MINUTE, longValue6 % 60);
        }
        if (this.fieldValues.containsKey(ChronoField.MINUTE_OF_DAY)) {
            long longValue7 = this.fieldValues.remove(ChronoField.MINUTE_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MINUTE_OF_DAY.checkValidValue(longValue7);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, longValue7 / 60);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, longValue7 % 60);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
                ChronoField.MILLI_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MILLI_OF_SECOND).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
                ChronoField.MICRO_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MICRO_OF_SECOND).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, (this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000) + (this.fieldValues.get(ChronoField.MICRO_OF_SECOND).longValue() % 1000));
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000);
            this.fieldValues.remove(ChronoField.MICRO_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MILLI_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000000);
            this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MICRO_OF_SECOND).longValue() * 1000);
        } else if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000000);
        }
    }

    private void resolveTimeInferZeroes(ResolverStyle resolverStyle) {
        Long l = this.fieldValues.get(ChronoField.HOUR_OF_DAY);
        Long l2 = this.fieldValues.get(ChronoField.MINUTE_OF_HOUR);
        Long l3 = this.fieldValues.get(ChronoField.SECOND_OF_MINUTE);
        Long l4 = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
        if (l == null) {
            return;
        }
        if (l2 != null || (l3 == null && l4 == null)) {
            if (l2 == null || l3 != null || l4 == null) {
                if (resolverStyle != ResolverStyle.LENIENT) {
                    if (l != null) {
                        if (resolverStyle == ResolverStyle.SMART && l.longValue() == 24 && ((l2 == null || l2.longValue() == 0) && ((l3 == null || l3.longValue() == 0) && (l4 == null || l4.longValue() == 0)))) {
                            l = 0L;
                            this.excessDays = Period.ofDays(1);
                        }
                        int checkValidIntValue = ChronoField.HOUR_OF_DAY.checkValidIntValue(l.longValue());
                        if (l2 != null) {
                            int checkValidIntValue2 = ChronoField.MINUTE_OF_HOUR.checkValidIntValue(l2.longValue());
                            if (l3 != null) {
                                int checkValidIntValue3 = ChronoField.SECOND_OF_MINUTE.checkValidIntValue(l3.longValue());
                                if (l4 != null) {
                                    addObject(LocalTime.m4210of(checkValidIntValue, checkValidIntValue2, checkValidIntValue3, ChronoField.NANO_OF_SECOND.checkValidIntValue(l4.longValue())));
                                } else {
                                    addObject(LocalTime.m4209of(checkValidIntValue, checkValidIntValue2, checkValidIntValue3));
                                }
                            } else if (l4 == null) {
                                addObject(LocalTime.m4208of(checkValidIntValue, checkValidIntValue2));
                            }
                        } else if (l3 == null && l4 == null) {
                            addObject(LocalTime.m4208of(checkValidIntValue, 0));
                        }
                    }
                } else if (l != null) {
                    long longValue = l.longValue();
                    if (l2 == null) {
                        int safeToInt = Jdk8Methods.safeToInt(Jdk8Methods.floorDiv(longValue, 24L));
                        addObject(LocalTime.m4208of(Jdk8Methods.floorMod(longValue, 24), 0));
                        this.excessDays = Period.ofDays(safeToInt);
                    } else if (l3 != null) {
                        if (l4 == null) {
                            l4 = 0L;
                        }
                        long safeAdd = Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(longValue, 3600000000000L), Jdk8Methods.safeMultiply(l2.longValue(), 60000000000L)), Jdk8Methods.safeMultiply(l3.longValue(), 1000000000L)), l4.longValue());
                        int floorDiv = (int) Jdk8Methods.floorDiv(safeAdd, 86400000000000L);
                        addObject(LocalTime.ofNanoOfDay(Jdk8Methods.floorMod(safeAdd, 86400000000000L)));
                        this.excessDays = Period.ofDays(floorDiv);
                    } else {
                        long safeAdd2 = Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(longValue, 3600L), Jdk8Methods.safeMultiply(l2.longValue(), 60L));
                        int floorDiv2 = (int) Jdk8Methods.floorDiv(safeAdd2, 86400L);
                        addObject(LocalTime.ofSecondOfDay(Jdk8Methods.floorMod(safeAdd2, 86400L)));
                        this.excessDays = Period.ofDays(floorDiv2);
                    }
                }
                this.fieldValues.remove(ChronoField.HOUR_OF_DAY);
                this.fieldValues.remove(ChronoField.MINUTE_OF_HOUR);
                this.fieldValues.remove(ChronoField.SECOND_OF_MINUTE);
                this.fieldValues.remove(ChronoField.NANO_OF_SECOND);
            }
        }
    }

    private void mergeInstantFields() {
        if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS)) {
            ZoneId zoneId = this.zone;
            if (zoneId != null) {
                mergeInstantFields0(zoneId);
                return;
            }
            Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
            if (l != null) {
                mergeInstantFields0(ZoneOffset.ofTotalSeconds(l.intValue()));
            }
        }
    }

    private void mergeInstantFields0(ZoneId zoneId) {
        ChronoZonedDateTime<?> zonedDateTime = this.chrono.zonedDateTime(Instant.ofEpochSecond(this.fieldValues.remove(ChronoField.INSTANT_SECONDS).longValue()), zoneId);
        if (this.date == null) {
            addObject(zonedDateTime.toLocalDate());
        } else {
            resolveMakeChanges(ChronoField.INSTANT_SECONDS, zonedDateTime.toLocalDate());
        }
        addFieldValue(ChronoField.SECOND_OF_DAY, zonedDateTime.toLocalTime().toSecondOfDay());
    }

    private void crossCheck() {
        LocalTime localTime;
        if (this.fieldValues.size() > 0) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && (localTime = this.time) != null) {
                crossCheck(chronoLocalDate.atTime(localTime));
                return;
            }
            ChronoLocalDate chronoLocalDate2 = this.date;
            if (chronoLocalDate2 != null) {
                crossCheck(chronoLocalDate2);
                return;
            }
            LocalTime localTime2 = this.time;
            if (localTime2 != null) {
                crossCheck(localTime2);
            }
        }
    }

    private void crossCheck(TemporalAccessor temporalAccessor) {
        Iterator<Map.Entry<TemporalField, Long>> it = this.fieldValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<TemporalField, Long> next = it.next();
            TemporalField key = next.getKey();
            long longValue = next.getValue().longValue();
            if (temporalAccessor.isSupported(key)) {
                try {
                    long j = temporalAccessor.getLong(key);
                    if (j != longValue) {
                        throw new DateTimeException("Cross check failed: " + key + " " + j + " vs " + key + " " + longValue);
                    }
                    it.remove();
                } catch (RuntimeException unused) {
                    continue;
                }
            }
        }
    }

    private void resolveFractional() {
        if (this.time == null) {
            if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS) || this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY) || this.fieldValues.containsKey(ChronoField.SECOND_OF_MINUTE)) {
                if (this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
                    long longValue = this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue();
                    this.fieldValues.put(ChronoField.MICRO_OF_SECOND, Long.valueOf(longValue / 1000));
                    this.fieldValues.put(ChronoField.MILLI_OF_SECOND, Long.valueOf(longValue / 1000000));
                } else {
                    this.fieldValues.put(ChronoField.NANO_OF_SECOND, 0L);
                    this.fieldValues.put(ChronoField.MICRO_OF_SECOND, 0L);
                    this.fieldValues.put(ChronoField.MILLI_OF_SECOND, 0L);
                }
            }
        }
    }

    private void resolveInstant() {
        if (this.date == null || this.time == null) {
            return;
        }
        Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
        if (l != null) {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(this.date.atTime(this.time).atZone2(ZoneOffset.ofTotalSeconds(l.intValue())).getLong(ChronoField.INSTANT_SECONDS)));
        } else if (this.zone != null) {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(this.date.atTime(this.time).atZone2(this.zone).getLong(ChronoField.INSTANT_SECONDS)));
        }
    }

    public <R> R build(TemporalQuery<R> temporalQuery) {
        return temporalQuery.queryFrom(this);
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        ChronoLocalDate chronoLocalDate;
        LocalTime localTime;
        if (temporalField == null) {
            return false;
        }
        return this.fieldValues.containsKey(temporalField) || ((chronoLocalDate = this.date) != null && chronoLocalDate.isSupported(temporalField)) || ((localTime = this.time) != null && localTime.isSupported(temporalField));
    }

    @Override // org.threeten.p095bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 == null) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && chronoLocalDate.isSupported(temporalField)) {
                return this.date.getLong(temporalField);
            }
            LocalTime localTime = this.time;
            if (localTime != null && localTime.isSupported(temporalField)) {
                return this.time.getLong(temporalField);
            }
            throw new DateTimeException("Field not found: " + temporalField);
        }
        return fieldValue0.longValue();
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId()) {
            return (R) this.zone;
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) this.chrono;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null) {
                return (R) LocalDate.from((TemporalAccessor) chronoLocalDate);
            }
            return null;
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return (R) this.time;
        }
        if (temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset()) {
            return temporalQuery.queryFrom(this);
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("DateTimeBuilder[");
        if (this.fieldValues.size() > 0) {
            sb.append("fields=");
            sb.append(this.fieldValues);
        }
        sb.append(", ");
        sb.append(this.chrono);
        sb.append(", ");
        sb.append(this.zone);
        sb.append(", ");
        sb.append(this.date);
        sb.append(", ");
        sb.append(this.time);
        sb.append(']');
        return sb.toString();
    }
}

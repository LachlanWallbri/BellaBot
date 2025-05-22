package org.threeten.p095bp.chrono;

import java.io.Serializable;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalAdjuster;
import org.threeten.p095bp.temporal.TemporalUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class ChronoDateImpl<D extends ChronoLocalDate> extends ChronoLocalDate implements Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    abstract ChronoDateImpl<D> plusDays(long j);

    abstract ChronoDateImpl<D> plusMonths(long j);

    abstract ChronoDateImpl<D> plusYears(long j);

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate, org.threeten.p095bp.temporal.Temporal
    public ChronoDateImpl<D> plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch ((ChronoUnit) temporalUnit) {
                case DAYS:
                    return plusDays(j);
                case WEEKS:
                    return plusDays(Jdk8Methods.safeMultiply(j, 7));
                case MONTHS:
                    return plusMonths(j);
                case YEARS:
                    return plusYears(j);
                case DECADES:
                    return plusYears(Jdk8Methods.safeMultiply(j, 10));
                case CENTURIES:
                    return plusYears(Jdk8Methods.safeMultiply(j, 100));
                case MILLENNIA:
                    return plusYears(Jdk8Methods.safeMultiply(j, 1000));
                default:
                    throw new DateTimeException(temporalUnit + " not valid for chronology " + getChronology().getId());
            }
        }
        return (ChronoDateImpl) getChronology().ensureChronoLocalDate(temporalUnit.addTo(this, j));
    }

    ChronoDateImpl<D> plusWeeks(long j) {
        return plusDays(Jdk8Methods.safeMultiply(j, 7));
    }

    ChronoDateImpl<D> minusYears(long j) {
        return j == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1L) : plusYears(-j);
    }

    ChronoDateImpl<D> minusMonths(long j) {
        return j == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j);
    }

    ChronoDateImpl<D> minusWeeks(long j) {
        return j == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : plusWeeks(-j);
    }

    ChronoDateImpl<D> minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public ChronoLocalDateTime<?> atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.m4229of(this, localTime);
    }

    @Override // org.threeten.p095bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDate date = getChronology().date(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return LocalDate.from((TemporalAccessor) this).until(date, temporalUnit);
        }
        return temporalUnit.between(this, date);
    }

    @Override // org.threeten.p095bp.chrono.ChronoLocalDate
    public ChronoPeriod until(ChronoLocalDate chronoLocalDate) {
        throw new UnsupportedOperationException("Not supported in ThreeTen backport");
    }
}

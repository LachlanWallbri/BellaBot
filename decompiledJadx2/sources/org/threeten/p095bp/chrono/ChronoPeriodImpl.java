package org.threeten.p095bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalQueries;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.UnsupportedTemporalTypeException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ChronoPeriodImpl extends ChronoPeriod implements Serializable {
    private static final long serialVersionUID = 275618735781L;
    private final Chronology chronology;
    private final int days;
    private final int months;
    private final int years;

    public ChronoPeriodImpl(Chronology chronology, int i, int i2, int i3) {
        this.chronology = chronology;
        this.years = i;
        this.months = i2;
        this.days = i3;
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

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public Chronology getChronology() {
        return this.chronology;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public ChronoPeriod plus(TemporalAmount temporalAmount) {
        if (temporalAmount instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) temporalAmount;
            if (chronoPeriodImpl.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeAdd(this.years, chronoPeriodImpl.years), Jdk8Methods.safeAdd(this.months, chronoPeriodImpl.months), Jdk8Methods.safeAdd(this.days, chronoPeriodImpl.days));
            }
        }
        throw new DateTimeException("Unable to add amount: " + temporalAmount);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public ChronoPeriod minus(TemporalAmount temporalAmount) {
        if (temporalAmount instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) temporalAmount;
            if (chronoPeriodImpl.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeSubtract(this.years, chronoPeriodImpl.years), Jdk8Methods.safeSubtract(this.months, chronoPeriodImpl.months), Jdk8Methods.safeSubtract(this.days, chronoPeriodImpl.days));
            }
        }
        throw new DateTimeException("Unable to subtract amount: " + temporalAmount);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public ChronoPeriod multipliedBy(int i) {
        return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeMultiply(this.years, i), Jdk8Methods.safeMultiply(this.months, i), Jdk8Methods.safeMultiply(this.days, i));
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public ChronoPeriod normalized() {
        if (!this.chronology.range(ChronoField.MONTH_OF_YEAR).isFixed()) {
            return this;
        }
        long maximum = (this.chronology.range(ChronoField.MONTH_OF_YEAR).getMaximum() - this.chronology.range(ChronoField.MONTH_OF_YEAR).getMinimum()) + 1;
        long j = (this.years * maximum) + this.months;
        return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeToInt(j / maximum), Jdk8Methods.safeToInt(j % maximum), this.days);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public Temporal addTo(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chronology != null && !this.chronology.equals(chronology)) {
            throw new DateTimeException("Invalid chronology, required: " + this.chronology.getId() + ", but was: " + chronology.getId());
        }
        int i = this.years;
        if (i != 0) {
            temporal = temporal.plus(i, ChronoUnit.YEARS);
        }
        int i2 = this.months;
        if (i2 != 0) {
            temporal = temporal.plus(i2, ChronoUnit.MONTHS);
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.plus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod, org.threeten.p095bp.temporal.TemporalAmount
    public Temporal subtractFrom(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chronology != null && !this.chronology.equals(chronology)) {
            throw new DateTimeException("Invalid chronology, required: " + this.chronology.getId() + ", but was: " + chronology.getId());
        }
        int i = this.years;
        if (i != 0) {
            temporal = temporal.minus(i, ChronoUnit.YEARS);
        }
        int i2 = this.months;
        if (i2 != 0) {
            temporal = temporal.minus(i2, ChronoUnit.MONTHS);
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.minus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoPeriodImpl)) {
            return false;
        }
        ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) obj;
        return this.years == chronoPeriodImpl.years && this.months == chronoPeriodImpl.months && this.days == chronoPeriodImpl.days && this.chronology.equals(chronoPeriodImpl.chronology);
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public int hashCode() {
        return this.chronology.hashCode() + Integer.rotateLeft(this.years, 16) + Integer.rotateLeft(this.months, 8) + this.days;
    }

    @Override // org.threeten.p095bp.chrono.ChronoPeriod
    public String toString() {
        if (isZero()) {
            return this.chronology + " P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.chronology);
        sb.append(' ');
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

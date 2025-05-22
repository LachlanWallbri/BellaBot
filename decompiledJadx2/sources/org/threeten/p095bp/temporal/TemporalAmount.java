package org.threeten.p095bp.temporal;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface TemporalAmount {
    Temporal addTo(Temporal temporal);

    long get(TemporalUnit temporalUnit);

    List<TemporalUnit> getUnits();

    Temporal subtractFrom(Temporal temporal);
}

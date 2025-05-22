package org.threeten.p095bp.temporal;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface TemporalAccessor {
    int get(TemporalField temporalField);

    long getLong(TemporalField temporalField);

    boolean isSupported(TemporalField temporalField);

    <R> R query(TemporalQuery<R> temporalQuery);

    ValueRange range(TemporalField temporalField);
}

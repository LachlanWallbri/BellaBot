package org.threeten.p095bp.chrono;

import java.util.Locale;
import org.threeten.p095bp.format.TextStyle;
import org.threeten.p095bp.temporal.TemporalAccessor;
import org.threeten.p095bp.temporal.TemporalAdjuster;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface Era extends TemporalAccessor, TemporalAdjuster {
    String getDisplayName(TextStyle textStyle, Locale locale);

    int getValue();
}

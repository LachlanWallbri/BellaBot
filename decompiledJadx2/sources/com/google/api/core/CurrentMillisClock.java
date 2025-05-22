package com.google.api.core;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class CurrentMillisClock implements ApiClock, Serializable {
    private static final ApiClock DEFAULT_CLOCK = new CurrentMillisClock();
    private static final long serialVersionUID = -6019259882852183285L;

    public static ApiClock getDefaultClock() {
        return DEFAULT_CLOCK;
    }

    private CurrentMillisClock() {
    }

    @Override // com.google.api.core.ApiClock
    public long nanoTime() {
        return TimeUnit.NANOSECONDS.convert(millisTime(), TimeUnit.MILLISECONDS);
    }

    @Override // com.google.api.core.ApiClock
    public long millisTime() {
        return System.currentTimeMillis();
    }

    private Object readResolve() throws ObjectStreamException {
        return DEFAULT_CLOCK;
    }
}

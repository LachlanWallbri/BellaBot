package com.google.api.core;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class NanoClock implements ApiClock, Serializable {
    private static final ApiClock DEFAULT_CLOCK = new NanoClock();
    private static final long serialVersionUID = 5541462688633944865L;

    public static ApiClock getDefaultClock() {
        return DEFAULT_CLOCK;
    }

    private NanoClock() {
    }

    @Override // com.google.api.core.ApiClock
    public final long nanoTime() {
        return System.nanoTime();
    }

    @Override // com.google.api.core.ApiClock
    public final long millisTime() {
        return TimeUnit.MILLISECONDS.convert(nanoTime(), TimeUnit.NANOSECONDS);
    }

    private Object readResolve() throws ObjectStreamException {
        return DEFAULT_CLOCK;
    }
}

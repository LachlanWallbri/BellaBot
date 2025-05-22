package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
class AtomicIntegerTransform implements Transform<AtomicInteger> {
    @Override // org.simpleframework.xml.transform.Transform
    public AtomicInteger read(String str) {
        return new AtomicInteger(Integer.valueOf(str).intValue());
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(AtomicInteger atomicInteger) {
        return atomicInteger.toString();
    }
}

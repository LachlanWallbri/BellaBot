package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes9.dex */
class AtomicLongTransform implements Transform<AtomicLong> {
    @Override // org.simpleframework.xml.transform.Transform
    public AtomicLong read(String str) {
        return new AtomicLong(Long.valueOf(str).longValue());
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(AtomicLong atomicLong) {
        return atomicLong.toString();
    }
}

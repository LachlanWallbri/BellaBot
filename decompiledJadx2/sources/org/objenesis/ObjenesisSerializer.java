package org.objenesis;

import org.objenesis.strategy.SerializingInstantiatorStrategy;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ObjenesisSerializer extends ObjenesisBase {
    public ObjenesisSerializer() {
        super(new SerializingInstantiatorStrategy());
    }

    public ObjenesisSerializer(boolean z) {
        super(new SerializingInstantiatorStrategy(), z);
    }
}

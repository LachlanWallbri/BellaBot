package org.objenesis;

import org.objenesis.strategy.StdInstantiatorStrategy;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ObjenesisStd extends ObjenesisBase {
    public ObjenesisStd() {
        super(new StdInstantiatorStrategy());
    }

    public ObjenesisStd(boolean z) {
        super(new StdInstantiatorStrategy(), z);
    }
}

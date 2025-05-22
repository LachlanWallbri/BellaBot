package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
@Instantiator(Typology.NOT_COMPLIANT)
/* loaded from: classes9.dex */
public class NullInstantiator<T> implements ObjectInstantiator<T> {
    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        return null;
    }

    public NullInstantiator(Class<T> cls) {
    }
}

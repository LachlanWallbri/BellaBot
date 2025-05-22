package com.pudutech.peanut.presenter.utils.cloner.cloning;

import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes5.dex */
public interface IInstantiationStrategy {
    <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls);

    <T> T newInstance(Class<T> cls);
}

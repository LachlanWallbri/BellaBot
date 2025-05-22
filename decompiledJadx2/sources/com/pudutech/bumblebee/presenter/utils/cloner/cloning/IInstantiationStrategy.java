package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes4.dex */
public interface IInstantiationStrategy {
    <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls);

    <T> T newInstance(Class<T> cls);
}

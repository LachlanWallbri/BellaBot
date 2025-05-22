package com.pudutech.peanut.presenter.utils.cloner.cloning;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes5.dex */
public class ObjenesisInstantiationStrategy implements IInstantiationStrategy {
    private static ObjenesisInstantiationStrategy instance = new ObjenesisInstantiationStrategy();
    private final Objenesis objenesis = new ObjenesisStd();

    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IInstantiationStrategy
    public <T> T newInstance(Class<T> cls) {
        return (T) this.objenesis.newInstance(cls);
    }

    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IInstantiationStrategy
    public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls) {
        return this.objenesis.getInstantiatorOf(cls);
    }

    public static ObjenesisInstantiationStrategy getInstance() {
        return instance;
    }
}

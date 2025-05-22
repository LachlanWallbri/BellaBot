package com.pudutech.peanut.presenter.utils.cloner.cloning;

import com.pudutech.peanut.presenter.utils.cloner.cloning.ICloningStrategy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class CloningStrategyFactory {
    public static ICloningStrategy annotatedField(final Class<? extends Annotation> cls, final ICloningStrategy.Strategy strategy) {
        return new ICloningStrategy() { // from class: com.pudutech.peanut.presenter.utils.cloner.cloning.CloningStrategyFactory.1
            @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.ICloningStrategy
            public ICloningStrategy.Strategy strategyFor(Object obj, Field field) {
                if (obj == null) {
                    return ICloningStrategy.Strategy.IGNORE;
                }
                return field.getDeclaredAnnotation(cls) != null ? strategy : ICloningStrategy.Strategy.IGNORE;
            }
        };
    }
}

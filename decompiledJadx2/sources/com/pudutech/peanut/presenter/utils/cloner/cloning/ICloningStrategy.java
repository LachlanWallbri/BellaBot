package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public interface ICloningStrategy {

    /* loaded from: classes5.dex */
    public enum Strategy {
        NULL_INSTEAD_OF_CLONE,
        SAME_INSTANCE_INSTEAD_OF_CLONE,
        IGNORE
    }

    Strategy strategyFor(Object obj, Field field);
}

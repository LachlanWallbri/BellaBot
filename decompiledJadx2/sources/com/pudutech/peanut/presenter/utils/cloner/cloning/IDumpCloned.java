package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public interface IDumpCloned {
    void cloning(Field field, Class<?> cls);

    void startCloning(Class<?> cls);
}

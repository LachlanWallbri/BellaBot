package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public interface IDumpCloned {
    void cloning(Field field, Class<?> cls);

    void startCloning(Class<?> cls);
}

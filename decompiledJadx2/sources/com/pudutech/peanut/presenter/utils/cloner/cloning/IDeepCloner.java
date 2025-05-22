package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Map;

/* loaded from: classes5.dex */
public interface IDeepCloner {
    <T> T deepClone(T t, Map<Object, Object> map);
}

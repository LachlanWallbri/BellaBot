package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.Map;

/* loaded from: classes4.dex */
public interface IDeepCloner {
    <T> T deepClone(T t, Map<Object, Object> map);
}

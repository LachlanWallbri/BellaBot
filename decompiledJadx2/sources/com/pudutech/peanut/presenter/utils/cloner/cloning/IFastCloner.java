package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Map;

/* loaded from: classes5.dex */
public interface IFastCloner {
    Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map);
}

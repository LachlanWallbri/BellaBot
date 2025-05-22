package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.Map;

/* loaded from: classes4.dex */
public interface IFastCloner {
    Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map);
}

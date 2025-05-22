package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class FastClonerConcurrentHashMap implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry entry : ((ConcurrentHashMap) obj).entrySet()) {
            concurrentHashMap.put(iDeepCloner.deepClone(entry.getKey(), map), iDeepCloner.deepClone(entry.getValue(), map));
        }
        return concurrentHashMap;
    }
}

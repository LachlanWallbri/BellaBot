package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Map;

/* loaded from: classes5.dex */
public abstract class FastClonerCustomMap<T extends Map> implements IFastCloner {
    protected abstract T getInstance(T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        Map map2 = (Map) obj;
        Map fastClonerCustomMap = getInstance(map2);
        for (Map.Entry entry : map2.entrySet()) {
            fastClonerCustomMap.put(iDeepCloner.deepClone(entry.getKey(), map), iDeepCloner.deepClone(entry.getValue(), map));
        }
        return fastClonerCustomMap;
    }
}

package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class FastClonerLinkedHashMap implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((LinkedHashMap) obj).entrySet()) {
            linkedHashMap.put(iDeepCloner.deepClone(entry.getKey(), map), iDeepCloner.deepClone(entry.getValue(), map));
        }
        return linkedHashMap;
    }
}

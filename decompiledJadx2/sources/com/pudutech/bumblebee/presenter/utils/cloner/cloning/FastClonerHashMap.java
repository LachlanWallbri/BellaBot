package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class FastClonerHashMap implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : ((HashMap) obj).entrySet()) {
            hashMap.put(iDeepCloner.deepClone(entry.getKey(), map), iDeepCloner.deepClone(entry.getValue(), map));
        }
        return hashMap;
    }
}

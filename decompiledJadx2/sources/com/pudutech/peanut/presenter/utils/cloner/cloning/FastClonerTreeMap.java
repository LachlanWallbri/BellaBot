package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public class FastClonerTreeMap implements IFastCloner {
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        TreeMap treeMap = (TreeMap) obj;
        TreeMap treeMap2 = new TreeMap(treeMap.comparator());
        for (Map.Entry entry : treeMap.entrySet()) {
            treeMap2.put(iDeepCloner.deepClone(entry.getKey(), map), iDeepCloner.deepClone(entry.getValue(), map));
        }
        return treeMap2;
    }
}

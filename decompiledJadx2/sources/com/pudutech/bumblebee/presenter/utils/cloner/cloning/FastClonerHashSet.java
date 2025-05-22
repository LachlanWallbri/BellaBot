package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class FastClonerHashSet implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        HashSet hashSet = new HashSet();
        Iterator it = ((HashSet) obj).iterator();
        while (it.hasNext()) {
            hashSet.add(iDeepCloner.deepClone(it.next(), map));
        }
        return hashSet;
    }
}

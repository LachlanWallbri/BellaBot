package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes4.dex */
public class FastClonerTreeSet implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        TreeSet treeSet = (TreeSet) obj;
        TreeSet treeSet2 = new TreeSet(treeSet.comparator());
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            treeSet2.add(iDeepCloner.deepClone(it.next(), map));
        }
        return treeSet2;
    }
}

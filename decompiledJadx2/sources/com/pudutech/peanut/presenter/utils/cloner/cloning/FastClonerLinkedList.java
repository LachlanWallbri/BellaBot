package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes5.dex */
public class FastClonerLinkedList implements IFastCloner {
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        LinkedList linkedList = new LinkedList();
        Iterator it = ((LinkedList) obj).iterator();
        while (it.hasNext()) {
            linkedList.add(iDeepCloner.deepClone(it.next(), map));
        }
        return linkedList;
    }
}

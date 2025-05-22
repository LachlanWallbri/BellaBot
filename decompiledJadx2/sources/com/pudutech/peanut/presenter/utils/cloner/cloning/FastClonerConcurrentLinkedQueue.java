package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes5.dex */
public class FastClonerConcurrentLinkedQueue implements IFastCloner {
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        Iterator it = ((ConcurrentLinkedQueue) obj).iterator();
        while (it.hasNext()) {
            concurrentLinkedQueue.add(iDeepCloner.deepClone(it.next(), map));
        }
        return concurrentLinkedQueue;
    }
}

package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class FastClonerCustomCollection<T extends Collection> implements IFastCloner {
    public abstract T getInstance(T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        Collection collection = (Collection) obj;
        Collection fastClonerCustomCollection = getInstance(collection);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            fastClonerCustomCollection.add(iDeepCloner.deepClone(it.next(), map));
        }
        return fastClonerCustomCollection;
    }
}

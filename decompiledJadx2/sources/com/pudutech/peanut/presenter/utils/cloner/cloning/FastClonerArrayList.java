package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes5.dex */
public class FastClonerArrayList implements IFastCloner {
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList2.add(iDeepCloner.deepClone(arrayList.get(i), map));
        }
        return arrayList2;
    }
}

package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class FastClonerArrayListSubList implements IFastCloner {
    @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        List list = (List) obj;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(iDeepCloner.deepClone(list.get(i), map));
        }
        return arrayList;
    }
}

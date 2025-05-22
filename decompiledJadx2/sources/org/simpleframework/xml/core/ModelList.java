package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ModelList extends ArrayList<Model> {
    public ModelList build() {
        ModelList modelList = new ModelList();
        Iterator<Model> it = iterator();
        while (it.hasNext()) {
            modelList.register(it.next());
        }
        return modelList;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        Iterator<Model> it = iterator();
        while (it.hasNext()) {
            Model next = it.next();
            if (next != null && !next.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Model lookup(int i) {
        if (i <= size()) {
            return get(i - 1);
        }
        return null;
    }

    public void register(Model model) {
        int index = model.getIndex();
        int size = size();
        for (int i = 0; i < index; i++) {
            if (i >= size) {
                add(null);
            }
            int i2 = index - 1;
            if (i == i2) {
                set(i2, model);
            }
        }
    }

    public Model take() {
        while (!isEmpty()) {
            Model remove = remove(0);
            if (!remove.isEmpty()) {
                return remove;
            }
        }
        return null;
    }
}

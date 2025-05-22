package com.pudutech.bumblebee.presenter.utils.cloner.perspectives;

import com.pudutech.bumblebee.presenter.utils.cloner.cloning.Cloner;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class Perspectives {
    private final Cloner cloner;

    public Perspectives(Cloner cloner) {
        this.cloner = cloner;
    }

    public <T, E extends T> E viewAs(Class<E> cls, T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Collection) {
            throw new IllegalArgumentException("for collections please use viewCollectionAs() method. Invalid object " + t);
        }
        E e = (E) this.cloner.fastCloneOrNewInstance(cls);
        this.cloner.copyPropertiesOfInheritedClass(t, e);
        return e;
    }

    public <I, NI extends I, T extends Collection<I>, E extends Collection<NI>> E viewCollectionAs(E e, Class<NI> cls, T t) {
        if (t == null) {
            return null;
        }
        Iterator it = t.iterator();
        while (it.hasNext()) {
            e.add(viewAs(cls, it.next()));
        }
        return e;
    }
}

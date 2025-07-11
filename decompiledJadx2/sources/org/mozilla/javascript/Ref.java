package org.mozilla.javascript;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class Ref implements Serializable {
    static final long serialVersionUID = 4044540354730911424L;

    public boolean delete(Context context) {
        return false;
    }

    public abstract Object get(Context context);

    public boolean has(Context context) {
        return true;
    }

    @Deprecated
    public abstract Object set(Context context, Object obj);

    public Object set(Context context, Scriptable scriptable, Object obj) {
        return set(context, obj);
    }
}

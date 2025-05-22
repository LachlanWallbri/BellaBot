package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface Function extends Scriptable, Callable {
    @Override // org.mozilla.javascript.Callable
    Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    Scriptable construct(Context context, Scriptable scriptable, Object[] objArr);
}

package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface RefCallable extends Callable {
    Ref refCall(Context context, Scriptable scriptable, Object[] objArr);
}

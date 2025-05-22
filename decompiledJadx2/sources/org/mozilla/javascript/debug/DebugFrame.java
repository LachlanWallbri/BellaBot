package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface DebugFrame {
    void onDebuggerStatement(Context context);

    void onEnter(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    void onExceptionThrown(Context context, Throwable th);

    void onExit(Context context, boolean z, Object obj);

    void onLineChange(Context context, int i);
}

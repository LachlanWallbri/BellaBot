package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface Debugger {
    DebugFrame getFrame(Context context, DebuggableScript debuggableScript);

    void handleCompilationDone(Context context, DebuggableScript debuggableScript, String str);
}

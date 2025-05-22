package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class SecurityProxy extends SecurityController {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void callProcessFileSecure(Context context, Scriptable scriptable, String str);
}

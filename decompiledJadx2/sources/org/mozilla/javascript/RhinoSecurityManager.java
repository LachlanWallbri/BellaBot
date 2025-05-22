package org.mozilla.javascript;

import org.mozilla.javascript.PolicySecurityController;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class RhinoSecurityManager extends SecurityManager {
    /* JADX INFO: Access modifiers changed from: protected */
    public Class<?> getCurrentScriptClass() {
        for (Class<?> cls : getClassContext()) {
            if ((cls != InterpretedFunction.class && NativeFunction.class.isAssignableFrom(cls)) || PolicySecurityController.SecureCaller.class.isAssignableFrom(cls)) {
                return cls;
            }
        }
        return null;
    }
}

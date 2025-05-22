package org.mozilla.javascript.jdk18;

import java.lang.reflect.Method;
import org.mozilla.javascript.jdk15.VMBridge_jdk15;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class VMBridge_jdk18 extends VMBridge_jdk15 {
    @Override // org.mozilla.javascript.jdk15.VMBridge_jdk15, org.mozilla.javascript.VMBridge
    public boolean isDefaultMethod(Method method) {
        return method.isDefault();
    }
}

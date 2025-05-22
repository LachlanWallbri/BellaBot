package org.mozilla.javascript;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class VMBridge {
    static final VMBridge instance = makeInstance();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Context getContext(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object getInterfaceProxyHelper(ContextFactory contextFactory, Class<?>[] clsArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Iterator<?> getJavaIterator(Context context, Scriptable scriptable, Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object getThreadContextHelper();

    public abstract boolean isDefaultMethod(Method method);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object newInterfaceProxy(Object obj, ContextFactory contextFactory, InterfaceAdapter interfaceAdapter, Object obj2, Scriptable scriptable);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void setContext(Object obj, Context context);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean tryToMakeAccessible(AccessibleObject accessibleObject);

    private static VMBridge makeInstance() {
        VMBridge vMBridge;
        String[] strArr = {"org.mozilla.javascript.VMBridge_custom", "org.mozilla.javascript.jdk18.VMBridge_jdk18", "org.mozilla.javascript.jdk15.VMBridge_jdk15"};
        for (int i = 0; i != 3; i++) {
            Class<?> classOrNull = Kit.classOrNull(strArr[i]);
            if (classOrNull != null && (vMBridge = (VMBridge) Kit.newInstanceOrNull(classOrNull)) != null) {
                return vMBridge;
            }
        }
        throw new IllegalStateException("Failed to create VMBridge instance");
    }
}

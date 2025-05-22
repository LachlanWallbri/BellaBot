package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Undefined implements Serializable {
    static final long serialVersionUID = 9195680630202616767L;
    public static final Object instance = new Undefined();
    public static final Scriptable SCRIPTABLE_UNDEFINED = (Scriptable) Proxy.newProxyInstance(Undefined.class.getClassLoader(), new Class[]{Scriptable.class}, new InvocationHandler() { // from class: org.mozilla.javascript.Undefined.1
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.getName().equals("toString")) {
                return "undefined";
            }
            if (method.getName().equals("equals")) {
                boolean z = false;
                if (objArr.length > 0 && Undefined.isUndefined(objArr[0])) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            throw new UnsupportedOperationException("undefined doesn't support " + method.getName());
        }
    });

    public int hashCode() {
        return 0;
    }

    private Undefined() {
    }

    public Object readResolve() {
        return instance;
    }

    public boolean equals(Object obj) {
        return isUndefined(obj) || super.equals(obj);
    }

    public static boolean isUndefined(Object obj) {
        return instance == obj || SCRIPTABLE_UNDEFINED == obj;
    }
}

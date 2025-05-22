package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class WrapFactory {
    private boolean javaPrimitiveWrap = true;

    public Object wrap(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        if (obj == null || obj == Undefined.instance || (obj instanceof Scriptable)) {
            return obj;
        }
        if (cls != null && cls.isPrimitive()) {
            if (cls == Void.TYPE) {
                return Undefined.instance;
            }
            return cls == Character.TYPE ? Integer.valueOf(((Character) obj).charValue()) : obj;
        }
        if (!isJavaPrimitiveWrap()) {
            if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double)) {
                return obj;
            }
            if (obj instanceof Character) {
                return String.valueOf(((Character) obj).charValue());
            }
        }
        if (obj.getClass().isArray()) {
            return NativeJavaArray.wrap(scriptable, obj);
        }
        return wrapAsJavaObject(context, scriptable, obj, cls);
    }

    public Scriptable wrapNewObject(Context context, Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj.getClass().isArray()) {
            return NativeJavaArray.wrap(scriptable, obj);
        }
        return wrapAsJavaObject(context, scriptable, obj, null);
    }

    public Scriptable wrapAsJavaObject(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        return new NativeJavaObject(scriptable, obj, cls);
    }

    public Scriptable wrapJavaClass(Context context, Scriptable scriptable, Class<?> cls) {
        return new NativeJavaClass(scriptable, cls);
    }

    public final boolean isJavaPrimitiveWrap() {
        return this.javaPrimitiveWrap;
    }

    public final void setJavaPrimitiveWrap(boolean z) {
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null && currentContext.isSealed()) {
            Context.onSealedMutation();
        }
        this.javaPrimitiveWrap = z;
    }
}

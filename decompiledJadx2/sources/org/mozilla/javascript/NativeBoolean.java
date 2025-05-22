package org.mozilla.javascript;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeBoolean extends IdScriptableObject {
    private static final Object BOOLEAN_TAG = "Boolean";
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 4;
    static final long serialVersionUID = -3716996899943880933L;
    private boolean booleanValue;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Boolean";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeBoolean(false).exportAsJSClass(4, scriptable, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeBoolean(boolean z) {
        this.booleanValue = z;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        if (cls == ScriptRuntime.BooleanClass) {
            return ScriptRuntime.wrapBoolean(this.booleanValue);
        }
        return super.getDefaultValue(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        if (i == 1) {
            i2 = 1;
            str = "constructor";
        } else if (i == 2) {
            str = "toString";
        } else if (i == 3) {
            str = "toSource";
        } else {
            if (i != 4) {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            str = "valueOf";
        }
        initPrototypeMethod(BOOLEAN_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(BOOLEAN_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            boolean z = false;
            if (objArr.length != 0) {
                z = ((objArr[0] instanceof ScriptableObject) && ((ScriptableObject) objArr[0]).avoidObjectDetection()) ? true : ScriptRuntime.toBoolean(objArr[0]);
            }
            if (scriptable2 == null) {
                return new NativeBoolean(z);
            }
            return ScriptRuntime.wrapBoolean(z);
        }
        if (!(scriptable2 instanceof NativeBoolean)) {
            throw incompatibleCallError(idFunctionObject);
        }
        boolean z2 = ((NativeBoolean) scriptable2).booleanValue;
        if (methodId == 2) {
            return z2 ? "true" : "false";
        }
        if (methodId == 3) {
            return z2 ? "(new Boolean(true))" : "(new Boolean(false))";
        }
        if (methodId == 4) {
            return ScriptRuntime.wrapBoolean(z2);
        }
        throw new IllegalArgumentException(String.valueOf(methodId));
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 3;
        if (length == 7) {
            i = 4;
            str2 = "valueOf";
        } else if (length == 8) {
            char charAt = str.charAt(3);
            if (charAt == 'o') {
                str2 = "toSource";
            } else {
                if (charAt == 't') {
                    i = 2;
                    str2 = "toString";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 11) {
                i = 1;
                str2 = "constructor";
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeCallSite extends IdScriptableObject {
    private static final String CALLSITE_TAG = "CallSite";
    private static final int Id_constructor = 1;
    private static final int Id_getColumnNumber = 9;
    private static final int Id_getEvalOrigin = 10;
    private static final int Id_getFileName = 7;
    private static final int Id_getFunction = 4;
    private static final int Id_getFunctionName = 5;
    private static final int Id_getLineNumber = 8;
    private static final int Id_getMethodName = 6;
    private static final int Id_getThis = 2;
    private static final int Id_getTypeName = 3;
    private static final int Id_isConstructor = 14;
    private static final int Id_isEval = 12;
    private static final int Id_isNative = 13;
    private static final int Id_isToplevel = 11;
    private static final int Id_toString = 15;
    private static final int MAX_PROTOTYPE_ID = 15;
    private ScriptStackElement element;

    private Object getNull() {
        return null;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CALLSITE_TAG;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeCallSite().exportAsJSClass(15, scriptable, z);
    }

    static NativeCallSite make(Scriptable scriptable, Scriptable scriptable2) {
        NativeCallSite nativeCallSite = new NativeCallSite();
        Scriptable scriptable3 = (Scriptable) scriptable2.get("prototype", scriptable2);
        nativeCallSite.setParentScope(scriptable);
        nativeCallSite.setPrototype(scriptable3);
        return nativeCallSite;
    }

    private NativeCallSite() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setElement(ScriptStackElement scriptStackElement) {
        this.element = scriptStackElement;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "getThis";
                break;
            case 3:
                str = "getTypeName";
                break;
            case 4:
                str = "getFunction";
                break;
            case 5:
                str = "getFunctionName";
                break;
            case 6:
                str = "getMethodName";
                break;
            case 7:
                str = "getFileName";
                break;
            case 8:
                str = "getLineNumber";
                break;
            case 9:
                str = "getColumnNumber";
                break;
            case 10:
                str = "getEvalOrigin";
                break;
            case 11:
                str = "isToplevel";
                break;
            case 12:
                str = "isEval";
                break;
            case 13:
                str = "isNative";
                break;
            case 14:
                str = "isConstructor";
                break;
            case 15:
                str = "toString";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(CALLSITE_TAG, i, str, 0);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CALLSITE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return make(scriptable, idFunctionObject);
            case 2:
            case 3:
            case 4:
            case 9:
                return getUndefined();
            case 5:
                return getFunctionName(scriptable2);
            case 6:
                return getNull();
            case 7:
                return getFileName(scriptable2);
            case 8:
                return getLineNumber(scriptable2);
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return getFalse();
            case 15:
                return js_toString(scriptable2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public String toString() {
        ScriptStackElement scriptStackElement = this.element;
        return scriptStackElement == null ? "" : scriptStackElement.toString();
    }

    private Object js_toString(Scriptable scriptable) {
        while (scriptable != null && !(scriptable instanceof NativeCallSite)) {
            scriptable = scriptable.getPrototype();
        }
        if (scriptable == null) {
            return NOT_FOUND;
        }
        StringBuilder sb = new StringBuilder();
        ((NativeCallSite) scriptable).element.renderJavaStyle(sb);
        return sb.toString();
    }

    private Object getUndefined() {
        return Undefined.instance;
    }

    private Object getFalse() {
        return Boolean.FALSE;
    }

    private Object getFunctionName(Scriptable scriptable) {
        while (scriptable != null && !(scriptable instanceof NativeCallSite)) {
            scriptable = scriptable.getPrototype();
        }
        if (scriptable == null) {
            return NOT_FOUND;
        }
        ScriptStackElement scriptStackElement = ((NativeCallSite) scriptable).element;
        if (scriptStackElement == null) {
            return null;
        }
        return scriptStackElement.functionName;
    }

    private Object getFileName(Scriptable scriptable) {
        while (scriptable != null && !(scriptable instanceof NativeCallSite)) {
            scriptable = scriptable.getPrototype();
        }
        if (scriptable == null) {
            return NOT_FOUND;
        }
        ScriptStackElement scriptStackElement = ((NativeCallSite) scriptable).element;
        if (scriptStackElement == null) {
            return null;
        }
        return scriptStackElement.fileName;
    }

    private Object getLineNumber(Scriptable scriptable) {
        while (scriptable != null && !(scriptable instanceof NativeCallSite)) {
            scriptable = scriptable.getPrototype();
        }
        if (scriptable == null) {
            return NOT_FOUND;
        }
        NativeCallSite nativeCallSite = (NativeCallSite) scriptable;
        ScriptStackElement scriptStackElement = nativeCallSite.element;
        if (scriptStackElement == null || scriptStackElement.lineNumber < 0) {
            return Undefined.instance;
        }
        return Integer.valueOf(nativeCallSite.element.lineNumber);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int i = 4;
        switch (str.length()) {
            case 6:
                i = 12;
                str2 = "isEval";
                break;
            case 7:
                i = 2;
                str2 = "getThis";
                break;
            case 8:
                char charAt = str.charAt(0);
                if (charAt != 'i') {
                    if (charAt == 't') {
                        i = 15;
                        str2 = "toString";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 13;
                    str2 = "isNative";
                    break;
                }
            case 9:
            case 12:
            case 14:
            default:
                str2 = null;
                i = 0;
                break;
            case 10:
                i = 11;
                str2 = "isToplevel";
                break;
            case 11:
                char charAt2 = str.charAt(4);
                if (charAt2 == 'i') {
                    i = 7;
                    str2 = "getFileName";
                    break;
                } else if (charAt2 == 'y') {
                    str2 = "getTypeName";
                    i = 3;
                    break;
                } else if (charAt2 == 't') {
                    i = 1;
                    str2 = "constructor";
                    break;
                } else {
                    if (charAt2 == 'u') {
                        str2 = "getFunction";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                }
            case 13:
                char charAt3 = str.charAt(3);
                if (charAt3 == 'E') {
                    i = 10;
                    str2 = "getEvalOrigin";
                    break;
                } else if (charAt3 == 'o') {
                    i = 14;
                    str2 = "isConstructor";
                    break;
                } else if (charAt3 == 'L') {
                    i = 8;
                    str2 = "getLineNumber";
                    break;
                } else {
                    if (charAt3 == 'M') {
                        i = 6;
                        str2 = "getMethodName";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                }
            case 15:
                char charAt4 = str.charAt(3);
                if (charAt4 != 'C') {
                    if (charAt4 == 'F') {
                        i = 5;
                        str2 = "getFunctionName";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 9;
                    str2 = "getColumnNumber";
                    break;
                }
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

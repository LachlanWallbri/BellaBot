package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ScriptRuntimeES6 {
    public static Scriptable requireObjectCoercible(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable == null || Undefined.isUndefined(scriptable)) {
            throw ScriptRuntime.typeError2("msg.called.null.or.undefined", idFunctionObject.getTag(), idFunctionObject.getFunctionName());
        }
        return scriptable;
    }
}

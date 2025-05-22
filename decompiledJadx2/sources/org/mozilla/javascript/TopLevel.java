package org.mozilla.javascript;

import java.util.EnumMap;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class TopLevel extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long serialVersionUID = -4648046356662472260L;
    private EnumMap<Builtins, BaseFunction> ctors;
    private EnumMap<NativeErrors, BaseFunction> errors;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public enum Builtins {
        Object,
        Array,
        Function,
        String,
        Number,
        Boolean,
        RegExp,
        Error,
        Symbol
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public enum NativeErrors {
        Error,
        EvalError,
        RangeError,
        ReferenceError,
        SyntaxError,
        TypeError,
        URIError,
        InternalError,
        JavaException
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "global";
    }

    public void cacheBuiltins() {
        this.ctors = new EnumMap<>(Builtins.class);
        for (Builtins builtins : Builtins.values()) {
            Object property = ScriptableObject.getProperty(this, builtins.name());
            if (property instanceof BaseFunction) {
                this.ctors.put((EnumMap<Builtins, BaseFunction>) builtins, (Builtins) property);
            }
        }
        this.errors = new EnumMap<>(NativeErrors.class);
        for (NativeErrors nativeErrors : NativeErrors.values()) {
            Object property2 = ScriptableObject.getProperty(this, nativeErrors.name());
            if (property2 instanceof BaseFunction) {
                this.errors.put((EnumMap<NativeErrors, BaseFunction>) nativeErrors, (NativeErrors) property2);
            }
        }
    }

    public static Function getBuiltinCtor(Context context, Scriptable scriptable, Builtins builtins) {
        BaseFunction builtinCtor;
        return (!(scriptable instanceof TopLevel) || (builtinCtor = ((TopLevel) scriptable).getBuiltinCtor(builtins)) == null) ? ScriptRuntime.getExistingCtor(context, scriptable, builtins.name()) : builtinCtor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function getNativeErrorCtor(Context context, Scriptable scriptable, NativeErrors nativeErrors) {
        BaseFunction nativeErrorCtor;
        return (!(scriptable instanceof TopLevel) || (nativeErrorCtor = ((TopLevel) scriptable).getNativeErrorCtor(nativeErrors)) == null) ? ScriptRuntime.getExistingCtor(context, scriptable, nativeErrors.name()) : nativeErrorCtor;
    }

    public static Scriptable getBuiltinPrototype(Scriptable scriptable, Builtins builtins) {
        Scriptable builtinPrototype;
        return (!(scriptable instanceof TopLevel) || (builtinPrototype = ((TopLevel) scriptable).getBuiltinPrototype(builtins)) == null) ? ScriptableObject.getClassPrototype(scriptable, builtins.name()) : builtinPrototype;
    }

    public BaseFunction getBuiltinCtor(Builtins builtins) {
        EnumMap<Builtins, BaseFunction> enumMap = this.ctors;
        if (enumMap != null) {
            return enumMap.get(builtins);
        }
        return null;
    }

    BaseFunction getNativeErrorCtor(NativeErrors nativeErrors) {
        EnumMap<NativeErrors, BaseFunction> enumMap = this.errors;
        if (enumMap != null) {
            return enumMap.get(nativeErrors);
        }
        return null;
    }

    public Scriptable getBuiltinPrototype(Builtins builtins) {
        BaseFunction builtinCtor = getBuiltinCtor(builtins);
        Object prototypeProperty = builtinCtor != null ? builtinCtor.getPrototypeProperty() : null;
        if (prototypeProperty instanceof Scriptable) {
            return (Scriptable) prototypeProperty;
        }
        return null;
    }
}

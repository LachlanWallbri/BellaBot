package org.mozilla.javascript;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ArrowFunction extends BaseFunction {
    static final long serialVersionUID = -7377989503697220633L;
    private final Scriptable boundThis;
    private final Callable targetFunction;

    public ArrowFunction(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2) {
        this.targetFunction = callable;
        this.boundThis = scriptable2;
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        Object typeErrorThrower = ScriptRuntime.typeErrorThrower();
        NativeObject nativeObject = new NativeObject();
        nativeObject.put(TmpConstant.PROPERTY_IDENTIFIER_GET, nativeObject, typeErrorThrower);
        nativeObject.put(TmpConstant.PROPERTY_IDENTIFIER_SET, nativeObject, typeErrorThrower);
        nativeObject.put("enumerable", (Scriptable) nativeObject, (Object) false);
        nativeObject.put("configurable", (Scriptable) nativeObject, (Object) false);
        nativeObject.preventExtensions();
        defineOwnProperty(context, "caller", nativeObject, false);
        defineOwnProperty(context, "arguments", nativeObject, false);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable scriptable3 = this.boundThis;
        if (scriptable3 == null) {
            scriptable3 = ScriptRuntime.getTopCallScope(context);
        }
        return this.targetFunction.call(context, scriptable, scriptable3, objArr);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeError1("msg.not.ctor", decompile(0, 0));
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        Callable callable = this.targetFunction;
        if (callable instanceof Function) {
            return ((Function) callable).hasInstance(scriptable);
        }
        throw ScriptRuntime.typeError0("msg.not.ctor");
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        Callable callable = this.targetFunction;
        if (callable instanceof BaseFunction) {
            return ((BaseFunction) callable).getLength();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.mozilla.javascript.BaseFunction
    public String decompile(int i, int i2) {
        Callable callable = this.targetFunction;
        if (callable instanceof BaseFunction) {
            return ((BaseFunction) callable).decompile(i, i2);
        }
        return super.decompile(i, i2);
    }
}

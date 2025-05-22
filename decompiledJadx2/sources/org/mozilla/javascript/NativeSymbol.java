package org.mozilla.javascript;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeSymbol extends IdScriptableObject implements Symbol {
    public static final String CLASS_NAME = "Symbol";
    private static final int ConstructorId_for = -1;
    private static final int ConstructorId_keyFor = -2;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 5;
    private static final int SymbolId_toPrimitive = 5;
    private static final int SymbolId_toStringTag = 3;
    public static final String TYPE_NAME = "symbol";
    private static final long serialVersionUID = -589539749749830003L;
    private final SymbolKey key;
    private final NativeSymbol symbolData;
    private static final Object GLOBAL_TABLE_KEY = new Object();
    private static final Object CONSTRUCTOR_SLOT = new Object();

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject exportAsJSClass = new NativeSymbol("").exportAsJSClass(5, scriptable, false);
        context.putThreadLocal(CONSTRUCTOR_SLOT, Boolean.TRUE);
        try {
            createStandardSymbol(context, scriptable, exportAsJSClass, "iterator", SymbolKey.ITERATOR);
            createStandardSymbol(context, scriptable, exportAsJSClass, "species", SymbolKey.SPECIES);
            createStandardSymbol(context, scriptable, exportAsJSClass, "toStringTag", SymbolKey.TO_STRING_TAG);
            createStandardSymbol(context, scriptable, exportAsJSClass, "hasInstance", SymbolKey.HAS_INSTANCE);
            createStandardSymbol(context, scriptable, exportAsJSClass, "isConcatSpreadable", SymbolKey.IS_CONCAT_SPREADABLE);
            createStandardSymbol(context, scriptable, exportAsJSClass, "isRegExp", SymbolKey.IS_REGEXP);
            createStandardSymbol(context, scriptable, exportAsJSClass, "toPrimitive", SymbolKey.TO_PRIMITIVE);
            createStandardSymbol(context, scriptable, exportAsJSClass, "match", SymbolKey.MATCH);
            createStandardSymbol(context, scriptable, exportAsJSClass, "replace", SymbolKey.REPLACE);
            createStandardSymbol(context, scriptable, exportAsJSClass, "search", SymbolKey.SEARCH);
            createStandardSymbol(context, scriptable, exportAsJSClass, "split", SymbolKey.SPLIT);
            createStandardSymbol(context, scriptable, exportAsJSClass, "unscopables", SymbolKey.UNSCOPABLES);
            if (z) {
                exportAsJSClass.sealObject();
            }
        } finally {
            context.removeThreadLocal(CONSTRUCTOR_SLOT);
        }
    }

    private NativeSymbol(String str) {
        this.key = new SymbolKey(str);
        this.symbolData = this;
    }

    private NativeSymbol(SymbolKey symbolKey) {
        this.key = symbolKey;
        this.symbolData = this;
    }

    public NativeSymbol(NativeSymbol nativeSymbol) {
        this.key = nativeSymbol.key;
        this.symbolData = nativeSymbol.symbolData;
    }

    public static NativeSymbol construct(Context context, Scriptable scriptable, Object[] objArr) {
        context.putThreadLocal(CONSTRUCTOR_SLOT, Boolean.TRUE);
        try {
            return (NativeSymbol) context.newObject(scriptable, CLASS_NAME, objArr);
        } finally {
            context.removeThreadLocal(CONSTRUCTOR_SLOT);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        super.fillConstructorProperties(idFunctionObject);
        addIdFunctionProperty(idFunctionObject, CLASS_NAME, -1, "for", 1);
        addIdFunctionProperty(idFunctionObject, CLASS_NAME, -2, "keyFor", 1);
    }

    private static void createStandardSymbol(Context context, Scriptable scriptable, ScriptableObject scriptableObject, String str, SymbolKey symbolKey) {
        scriptableObject.defineProperty(str, context.newObject(scriptable, CLASS_NAME, new Object[]{str, symbolKey}), 7);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 7) {
            i = 4;
            str2 = "valueOf";
        } else if (length == 8) {
            i = 2;
            str2 = "toString";
        } else if (length == 11) {
            i = 1;
            str2 = "constructor";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        if (SymbolKey.TO_STRING_TAG.equals(symbol)) {
            return 3;
        }
        return SymbolKey.TO_PRIMITIVE.equals(symbol) ? 5 : 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        if (i == 1) {
            initPrototypeMethod(CLASS_NAME, i, "constructor", 1);
            return;
        }
        if (i == 2) {
            initPrototypeMethod(CLASS_NAME, i, "toString", 0);
            return;
        }
        if (i == 3) {
            initPrototypeValue(i, SymbolKey.TO_STRING_TAG, CLASS_NAME, 3);
            return;
        }
        if (i == 4) {
            initPrototypeMethod(CLASS_NAME, i, "valueOf", 0);
        } else if (i == 5) {
            initPrototypeMethod(CLASS_NAME, i, SymbolKey.TO_PRIMITIVE, "Symbol.toPrimitive", 1);
        } else {
            super.initPrototypeId(i);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CLASS_NAME)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == -2) {
            return js_keyFor(context, scriptable, objArr);
        }
        if (methodId == -1) {
            return js_for(context, scriptable, objArr);
        }
        if (methodId == 1) {
            if (scriptable2 == null) {
                if (context.getThreadLocal(CONSTRUCTOR_SLOT) == null) {
                    throw ScriptRuntime.typeError0("msg.no.symbol.new");
                }
                return js_constructor(objArr);
            }
            return construct(context, scriptable, objArr);
        }
        if (methodId == 2) {
            return getSelf(scriptable2).toString();
        }
        if (methodId == 4 || methodId == 5) {
            return getSelf(scriptable2).js_valueOf();
        }
        return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
    }

    private NativeSymbol getSelf(Object obj) {
        try {
            return (NativeSymbol) obj;
        } catch (ClassCastException unused) {
            throw ScriptRuntime.typeError1("msg.invalid.type", obj.getClass().getName());
        }
    }

    private static NativeSymbol js_constructor(Object[] objArr) {
        String str = "";
        if (objArr.length > 0 && !Undefined.instance.equals(objArr[0])) {
            str = ScriptRuntime.toString(objArr[0]);
        }
        if (objArr.length > 1) {
            return new NativeSymbol((SymbolKey) objArr[1]);
        }
        return new NativeSymbol(str);
    }

    private Object js_valueOf() {
        return this.symbolData;
    }

    private Object js_for(Context context, Scriptable scriptable, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(objArr.length > 0 ? objArr[0] : Undefined.instance);
        Map<String, NativeSymbol> globalMap = getGlobalMap();
        NativeSymbol nativeSymbol = globalMap.get(scriptRuntime);
        if (nativeSymbol != null) {
            return nativeSymbol;
        }
        NativeSymbol construct = construct(context, scriptable, new Object[]{scriptRuntime});
        globalMap.put(scriptRuntime, construct);
        return construct;
    }

    private Object js_keyFor(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (!(obj instanceof NativeSymbol)) {
            throw ScriptRuntime.throwCustomError(context, scriptable, "TypeError", "Not a Symbol");
        }
        NativeSymbol nativeSymbol = (NativeSymbol) obj;
        for (Map.Entry<String, NativeSymbol> entry : getGlobalMap().entrySet()) {
            if (entry.getValue().key == nativeSymbol.key) {
                return entry.getKey();
            }
        }
        return Undefined.instance;
    }

    public String toString() {
        return this.key.toString();
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(str, scriptable, obj);
        } else if (Context.getCurrentContext().isStrictMode()) {
            throw ScriptRuntime.typeError0("msg.no.assign.symbol.strict");
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(i, scriptable, obj);
        } else if (Context.getCurrentContext().isStrictMode()) {
            throw ScriptRuntime.typeError0("msg.no.assign.symbol.strict");
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(symbol, scriptable, obj);
        } else if (Context.getCurrentContext().isStrictMode()) {
            throw ScriptRuntime.typeError0("msg.no.assign.symbol.strict");
        }
    }

    public boolean isSymbol() {
        return this.symbolData == this;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return isSymbol() ? TYPE_NAME : super.getTypeOf();
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        return this.key.equals(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolKey getKey() {
        return this.key;
    }

    private Map<String, NativeSymbol> getGlobalMap() {
        ScriptableObject scriptableObject = (ScriptableObject) getTopLevelScope(this);
        Map<String, NativeSymbol> map = (Map) scriptableObject.getAssociatedValue(GLOBAL_TABLE_KEY);
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        scriptableObject.associateValue(GLOBAL_TABLE_KEY, hashMap);
        return hashMap;
    }
}

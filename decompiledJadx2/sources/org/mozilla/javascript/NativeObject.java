package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeObject extends IdScriptableObject implements Map {
    private static final int ConstructorId_assign = -15;
    private static final int ConstructorId_create = -9;
    private static final int ConstructorId_defineProperties = -8;
    private static final int ConstructorId_defineProperty = -5;
    private static final int ConstructorId_freeze = -13;
    private static final int ConstructorId_getOwnPropertyDescriptor = -4;
    private static final int ConstructorId_getOwnPropertyNames = -3;
    private static final int ConstructorId_getOwnPropertySymbols = -14;
    private static final int ConstructorId_getPrototypeOf = -1;
    private static final int ConstructorId_is = -16;
    private static final int ConstructorId_isExtensible = -6;
    private static final int ConstructorId_isFrozen = -11;
    private static final int ConstructorId_isSealed = -10;
    private static final int ConstructorId_keys = -2;
    private static final int ConstructorId_preventExtensions = -7;
    private static final int ConstructorId_seal = -12;
    private static final int Id___defineGetter__ = 9;
    private static final int Id___defineSetter__ = 10;
    private static final int Id___lookupGetter__ = 11;
    private static final int Id___lookupSetter__ = 12;
    private static final int Id_constructor = 1;
    private static final int Id_hasOwnProperty = 5;
    private static final int Id_isPrototypeOf = 7;
    private static final int Id_propertyIsEnumerable = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 8;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 12;
    private static final Object OBJECT_TAG = "Object";
    static final long serialVersionUID = -6345305608474346996L;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Object";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeObject().exportAsJSClass(12, scriptable, z);
    }

    public String toString() {
        return ScriptRuntime.defaultObjectToString(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -1, "getPrototypeOf", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -2, "keys", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -14, "getOwnPropertySymbols", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -4, "getOwnPropertyDescriptor", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -5, "defineProperty", 3);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -6, "isExtensible", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -7, "preventExtensions", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -8, "defineProperties", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -9, "create", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -10, "isSealed", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -11, "isFrozen", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -12, "seal", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -13, "freeze", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -15, "assign", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -16, "is", 2);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0003. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 2:
                str = "toString";
                i2 = 0;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 3:
                str = "toLocaleString";
                i2 = 0;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 4:
                str = "valueOf";
                i2 = 0;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 5:
                str = "hasOwnProperty";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 6:
                str = "propertyIsEnumerable";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 7:
                str = "isPrototypeOf";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 8:
                str = "toSource";
                i2 = 0;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 9:
                str2 = "__defineGetter__";
                i2 = 2;
                str = str2;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 10:
                str2 = "__defineSetter__";
                i2 = 2;
                str = str2;
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 11:
                str = "__lookupGetter__";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            case 12:
                str = "__lookupSetter__";
                initPrototypeMethod(OBJECT_TAG, i, str, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x011c, code lost:
    
        if ((((org.mozilla.javascript.ScriptableObject) r12).getAttributes(r9) & 2) == 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0130, code lost:
    
        if ((((org.mozilla.javascript.ScriptableObject) r12).getAttributes(r9) & 2) == 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00fc, code lost:
    
        if ((((org.mozilla.javascript.ScriptableObject) r12).getAttributes(r9) & 2) == 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00fe, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00ff, code lost:
    
        r10 = r4;
     */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int int32;
        Object obj;
        boolean has;
        boolean has2;
        Scriptable prototype;
        if (!idFunctionObject.hasTag(OBJECT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        r4 = false;
        r4 = false;
        boolean z = false;
        r4 = false;
        r4 = false;
        boolean z2 = false;
        switch (methodId) {
            case -16:
                return ScriptRuntime.wrapBoolean(ScriptRuntime.same(objArr.length < 1 ? Undefined.instance : objArr[0], objArr.length < 2 ? Undefined.instance : objArr[1]));
            case -15:
                if (objArr.length < 1) {
                    throw ScriptRuntime.typeError1("msg.incompat.call", "assign");
                }
                Scriptable object = ScriptRuntime.toObject(context, scriptable2, objArr[0]);
                for (int i = 1; i < objArr.length; i++) {
                    if (objArr[i] != null && !Undefined.instance.equals(objArr[i])) {
                        Scriptable object2 = ScriptRuntime.toObject(context, scriptable2, objArr[i]);
                        for (Object obj2 : object2.getIds()) {
                            if (obj2 instanceof String) {
                                String str = (String) obj2;
                                Object obj3 = object2.get(str, object);
                                if (obj3 != Scriptable.NOT_FOUND && obj3 != Undefined.instance) {
                                    object.put(str, object, obj3);
                                }
                            } else if ((obj2 instanceof Number) && (obj = object2.get((int32 = ScriptRuntime.toInt32(obj2)), object)) != Scriptable.NOT_FOUND && obj != Undefined.instance) {
                                object.put(int32, object, obj);
                            }
                        }
                    }
                }
                return object;
            case -14:
                Object[] ids = ensureScriptableObject(getCompatibleObject(context, scriptable, objArr.length < 1 ? Undefined.instance : objArr[0])).getIds(true, true);
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < ids.length; i2++) {
                    if (ids[i2] instanceof Symbol) {
                        arrayList.add(ids[i2]);
                    }
                }
                return context.newArray(scriptable, arrayList.toArray());
            case -13:
                ScriptableObject ensureScriptableObject = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                for (Object obj4 : ensureScriptableObject.getAllIds()) {
                    ScriptableObject ownPropertyDescriptor = ensureScriptableObject.getOwnPropertyDescriptor(context, obj4);
                    if (isDataDescriptor(ownPropertyDescriptor) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable"))) {
                        ownPropertyDescriptor.put("writable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                        ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    ensureScriptableObject.defineOwnProperty(context, obj4, ownPropertyDescriptor, false);
                }
                ensureScriptableObject.preventExtensions();
                return ensureScriptableObject;
            case -12:
                ScriptableObject ensureScriptableObject2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                for (Object obj5 : ensureScriptableObject2.getAllIds()) {
                    ScriptableObject ownPropertyDescriptor2 = ensureScriptableObject2.getOwnPropertyDescriptor(context, obj5);
                    if (Boolean.TRUE.equals(ownPropertyDescriptor2.get("configurable"))) {
                        ownPropertyDescriptor2.put("configurable", ownPropertyDescriptor2, Boolean.FALSE);
                        ensureScriptableObject2.defineOwnProperty(context, obj5, ownPropertyDescriptor2, false);
                    }
                }
                ensureScriptableObject2.preventExtensions();
                return ensureScriptableObject2;
            case -11:
                ScriptableObject ensureScriptableObject3 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (ensureScriptableObject3.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object obj6 : ensureScriptableObject3.getAllIds()) {
                    ScriptableObject ownPropertyDescriptor3 = ensureScriptableObject3.getOwnPropertyDescriptor(context, obj6);
                    if (Boolean.TRUE.equals(ownPropertyDescriptor3.get("configurable"))) {
                        return Boolean.FALSE;
                    }
                    if (isDataDescriptor(ownPropertyDescriptor3) && Boolean.TRUE.equals(ownPropertyDescriptor3.get("writable"))) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            case -10:
                ScriptableObject ensureScriptableObject4 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (ensureScriptableObject4.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object obj7 : ensureScriptableObject4.getAllIds()) {
                    if (Boolean.TRUE.equals(ensureScriptableObject4.getOwnPropertyDescriptor(context, obj7).get("configurable"))) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            case -9:
                Object obj8 = objArr.length < 1 ? Undefined.instance : objArr[0];
                Scriptable ensureScriptable = obj8 == null ? null : ensureScriptable(obj8);
                NativeObject nativeObject = new NativeObject();
                nativeObject.setParentScope(getParentScope());
                nativeObject.setPrototype(ensureScriptable);
                if (objArr.length > 1 && objArr[1] != Undefined.instance) {
                    nativeObject.defineOwnProperties(context, ensureScriptableObject(Context.toObject(objArr[1], getParentScope())));
                }
                return nativeObject;
            case -8:
                ScriptableObject ensureScriptableObject5 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                ensureScriptableObject5.defineOwnProperties(context, ensureScriptableObject(Context.toObject(objArr.length < 2 ? Undefined.instance : objArr[1], getParentScope())));
                return ensureScriptableObject5;
            case -7:
                ScriptableObject ensureScriptableObject6 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                ensureScriptableObject6.preventExtensions();
                return ensureScriptableObject6;
            case -6:
                return Boolean.valueOf(ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]).isExtensible());
            case -5:
                ScriptableObject ensureScriptableObject7 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                ensureScriptableObject7.defineOwnProperty(context, objArr.length < 2 ? Undefined.instance : objArr[1], ensureScriptableObject(objArr.length < 3 ? Undefined.instance : objArr[2]));
                return ensureScriptableObject7;
            case -4:
                ScriptableObject ownPropertyDescriptor4 = ensureScriptableObject(getCompatibleObject(context, scriptable, objArr.length < 1 ? Undefined.instance : objArr[0])).getOwnPropertyDescriptor(context, objArr.length < 2 ? Undefined.instance : objArr[1]);
                return ownPropertyDescriptor4 == null ? Undefined.instance : ownPropertyDescriptor4;
            case -3:
                Object[] ids2 = ensureScriptableObject(getCompatibleObject(context, scriptable, objArr.length < 1 ? Undefined.instance : objArr[0])).getIds(true, false);
                for (int i3 = 0; i3 < ids2.length; i3++) {
                    ids2[i3] = ScriptRuntime.toString(ids2[i3]);
                }
                return context.newArray(scriptable, ids2);
            case -2:
                Object[] ids3 = getCompatibleObject(context, scriptable, objArr.length < 1 ? Undefined.instance : objArr[0]).getIds();
                for (int i4 = 0; i4 < ids3.length; i4++) {
                    ids3[i4] = ScriptRuntime.toString(ids3[i4]);
                }
                return context.newArray(scriptable, ids3);
            case -1:
                return getCompatibleObject(context, scriptable, objArr.length < 1 ? Undefined.instance : objArr[0]).getPrototype();
            case 0:
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
            case 1:
                if (scriptable2 != null) {
                    return idFunctionObject.construct(context, scriptable, objArr);
                }
                if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
                    return new NativeObject();
                }
                return ScriptRuntime.toObject(context, scriptable, objArr[0]);
            case 2:
                if (context.hasFeature(4)) {
                    String defaultObjectToSource = ScriptRuntime.defaultObjectToSource(context, scriptable, scriptable2, objArr);
                    int length = defaultObjectToSource.length();
                    if (length == 0 || defaultObjectToSource.charAt(0) != '(') {
                        return defaultObjectToSource;
                    }
                    int i5 = length - 1;
                    return defaultObjectToSource.charAt(i5) == ')' ? defaultObjectToSource.substring(1, i5) : defaultObjectToSource;
                }
                return ScriptRuntime.defaultObjectToString(scriptable2);
            case 3:
                Object property = ScriptableObject.getProperty(scriptable2, "toString");
                if (!(property instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(property);
                }
                return ((Callable) property).call(context, scriptable, scriptable2, ScriptRuntime.emptyArgs);
            case 4:
                return scriptable2;
            case 5:
                Object obj9 = objArr.length < 1 ? Undefined.instance : objArr[0];
                if (obj9 instanceof Symbol) {
                    has = ensureSymbolScriptable(scriptable2).has((Symbol) obj9, scriptable2);
                } else {
                    String stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, obj9);
                    if (stringIdOrIndex == null) {
                        has = scriptable2.has(ScriptRuntime.lastIndexResult(context), scriptable2);
                    } else {
                        has = scriptable2.has(stringIdOrIndex, scriptable2);
                    }
                }
                return ScriptRuntime.wrapBoolean(has);
            case 6:
                Object obj10 = objArr.length < 1 ? Undefined.instance : objArr[0];
                if (obj10 instanceof Symbol) {
                    Symbol symbol = (Symbol) obj10;
                    has2 = ((SymbolScriptable) scriptable2).has(symbol, scriptable2);
                    if (has2 && (scriptable2 instanceof ScriptableObject)) {
                        break;
                    }
                    return ScriptRuntime.wrapBoolean(has2);
                }
                String stringIdOrIndex2 = ScriptRuntime.toStringIdOrIndex(context, obj10);
                if (stringIdOrIndex2 == null) {
                    int lastIndexResult = ScriptRuntime.lastIndexResult(context);
                    has2 = scriptable2.has(lastIndexResult, scriptable2);
                    if (has2 && (scriptable2 instanceof ScriptableObject)) {
                        break;
                    }
                    return ScriptRuntime.wrapBoolean(has2);
                }
                has2 = scriptable2.has(stringIdOrIndex2, scriptable2);
                if (has2 && (scriptable2 instanceof ScriptableObject)) {
                    break;
                }
                return ScriptRuntime.wrapBoolean(has2);
            case 7:
                if (objArr.length != 0 && (objArr[0] instanceof Scriptable)) {
                    Scriptable scriptable3 = (Scriptable) objArr[0];
                    while (true) {
                        scriptable3 = scriptable3.getPrototype();
                        if (scriptable3 == scriptable2) {
                            z = true;
                        } else if (scriptable3 == null) {
                        }
                    }
                }
                return ScriptRuntime.wrapBoolean(z);
            case 8:
                return ScriptRuntime.defaultObjectToSource(context, scriptable, scriptable2, objArr);
            case 9:
            case 10:
                if (objArr.length < 2 || !(objArr[1] instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(objArr.length >= 2 ? objArr[1] : Undefined.instance);
                }
                if (!(scriptable2 instanceof ScriptableObject)) {
                    throw Context.reportRuntimeError2("msg.extend.scriptable", scriptable2.getClass().getName(), String.valueOf(objArr[0]));
                }
                ScriptableObject scriptableObject = (ScriptableObject) scriptable2;
                String stringIdOrIndex3 = ScriptRuntime.toStringIdOrIndex(context, objArr[0]);
                scriptableObject.setGetterOrSetter(stringIdOrIndex3, stringIdOrIndex3 != null ? 0 : ScriptRuntime.lastIndexResult(context), (Callable) objArr[1], methodId == 10);
                if (scriptableObject instanceof NativeArray) {
                    ((NativeArray) scriptableObject).setDenseOnly(false);
                }
                return Undefined.instance;
            case 11:
            case 12:
                if (objArr.length < 1 || !(scriptable2 instanceof ScriptableObject)) {
                    return Undefined.instance;
                }
                ScriptableObject scriptableObject2 = (ScriptableObject) scriptable2;
                String stringIdOrIndex4 = ScriptRuntime.toStringIdOrIndex(context, objArr[0]);
                int lastIndexResult2 = stringIdOrIndex4 != null ? 0 : ScriptRuntime.lastIndexResult(context);
                boolean z3 = methodId == 12;
                while (true) {
                    Object getterOrSetter = scriptableObject2.getGetterOrSetter(stringIdOrIndex4, lastIndexResult2, z3);
                    if (getterOrSetter == null && (prototype = scriptableObject2.getPrototype()) != null && (prototype instanceof ScriptableObject)) {
                        scriptableObject2 = (ScriptableObject) prototype;
                    }
                }
                return Undefined.instance;
        }
    }

    private Scriptable getCompatibleObject(Context context, Scriptable scriptable, Object obj) {
        if (context.getLanguageVersion() >= 200) {
            return ensureScriptable(ScriptRuntime.toObject(context, scriptable, obj));
        }
        return ensureScriptable(obj);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return has((String) obj, this);
        }
        if (obj instanceof Number) {
            return has(((Number) obj).intValue(), this);
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        for (Object obj2 : values()) {
            if (obj == obj2) {
                return true;
            }
            if (obj != null && obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        Object obj2 = get(obj);
        if (obj instanceof String) {
            delete((String) obj);
        } else if (obj instanceof Number) {
            delete(((Number) obj).intValue());
        }
        return obj2;
    }

    @Override // java.util.Map
    public Set<Object> keySet() {
        return new KeySet();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return new ValueCollection();
    }

    @Override // java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public class EntrySet extends AbstractSet<Map.Entry<Object, Object>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Object, Object>> iterator() {
            return new Iterator<Map.Entry<Object, Object>>() { // from class: org.mozilla.javascript.NativeObject.EntrySet.1
                Object[] ids;
                Object key = null;
                int index = 0;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override // java.util.Iterator
                public Map.Entry<Object, Object> next() {
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    final Object obj = objArr[i];
                    this.key = obj;
                    final Object obj2 = NativeObject.this.get(this.key);
                    return new Map.Entry<Object, Object>() { // from class: org.mozilla.javascript.NativeObject.EntrySet.1.1
                        @Override // java.util.Map.Entry
                        public Object getKey() {
                            return obj;
                        }

                        @Override // java.util.Map.Entry
                        public Object getValue() {
                            return obj2;
                        }

                        @Override // java.util.Map.Entry
                        public Object setValue(Object obj3) {
                            throw new UnsupportedOperationException();
                        }

                        @Override // java.util.Map.Entry
                        public boolean equals(Object obj3) {
                            if (!(obj3 instanceof Map.Entry)) {
                                return false;
                            }
                            Map.Entry entry = (Map.Entry) obj3;
                            Object obj4 = obj;
                            if (obj4 == null) {
                                if (entry.getKey() != null) {
                                    return false;
                                }
                            } else if (!obj4.equals(entry.getKey())) {
                                return false;
                            }
                            Object obj5 = obj2;
                            if (obj5 == null) {
                                if (entry.getValue() != null) {
                                    return false;
                                }
                            } else if (!obj5.equals(entry.getValue())) {
                                return false;
                            }
                            return true;
                        }

                        @Override // java.util.Map.Entry
                        public int hashCode() {
                            Object obj3 = obj;
                            int hashCode = obj3 == null ? 0 : obj3.hashCode();
                            Object obj4 = obj2;
                            return hashCode ^ (obj4 != null ? obj4.hashCode() : 0);
                        }

                        public String toString() {
                            return obj + "=" + obj2;
                        }
                    };
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return NativeObject.this.size();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    class KeySet extends AbstractSet<Object> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return NativeObject.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Object> iterator() {
            return new Iterator<Object>() { // from class: org.mozilla.javascript.NativeObject.KeySet.1
                Object[] ids;
                int index = 0;
                Object key;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override // java.util.Iterator
                public Object next() {
                    try {
                        Object[] objArr = this.ids;
                        int i = this.index;
                        this.index = i + 1;
                        Object obj = objArr[i];
                        this.key = obj;
                        return obj;
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        this.key = null;
                        throw new NoSuchElementException();
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return NativeObject.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public class ValueCollection extends AbstractCollection<Object> {
        ValueCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return new Iterator<Object>() { // from class: org.mozilla.javascript.NativeObject.ValueCollection.1
                Object[] ids;
                int index = 0;
                Object key;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override // java.util.Iterator
                public Object next() {
                    NativeObject nativeObject = NativeObject.this;
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    Object obj = objArr[i];
                    this.key = obj;
                    return nativeObject.get(obj);
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return NativeObject.this.size();
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 2;
        if (length == 7) {
            i = 4;
            str2 = "valueOf";
        } else if (length == 8) {
            char charAt = str.charAt(3);
            if (charAt == 'o') {
                str2 = "toSource";
                i = 8;
            } else {
                if (charAt == 't') {
                    str2 = "toString";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 11) {
            i = 1;
            str2 = "constructor";
        } else if (length == 16) {
            char charAt2 = str.charAt(2);
            if (charAt2 == 'd') {
                char charAt3 = str.charAt(8);
                if (charAt3 == 'G') {
                    i = 9;
                    str2 = "__defineGetter__";
                } else {
                    if (charAt3 == 'S') {
                        i = 10;
                        str2 = "__defineSetter__";
                    }
                    str2 = null;
                    i = 0;
                }
            } else {
                if (charAt2 == 'l') {
                    char charAt4 = str.charAt(8);
                    if (charAt4 == 'G') {
                        str2 = "__lookupGetter__";
                        i = 11;
                    } else if (charAt4 == 'S') {
                        i = 12;
                        str2 = "__lookupSetter__";
                    }
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 20) {
            i = 6;
            str2 = "propertyIsEnumerable";
        } else if (length != 13) {
            if (length == 14) {
                char charAt5 = str.charAt(0);
                if (charAt5 == 'h') {
                    i = 5;
                    str2 = "hasOwnProperty";
                } else if (charAt5 == 't') {
                    str2 = "toLocaleString";
                    i = 3;
                }
            }
            str2 = null;
            i = 0;
        } else {
            str2 = "isPrototypeOf";
            i = 7;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

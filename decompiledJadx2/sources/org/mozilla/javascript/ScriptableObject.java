package org.mozilla.javascript;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.common.reflect.Invokable;
import com.loc.C3898x;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;
import org.mozilla.javascript.debug.DebuggableObject;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class ScriptableObject implements Scriptable, SymbolScriptable, Serializable, DebuggableObject, ConstProperties {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONST = 13;
    public static final int DONTENUM = 2;
    public static final int EMPTY = 0;
    private static final Method GET_ARRAY_LENGTH;
    private static final Comparator<Object> KEY_COMPARATOR;
    public static final int PERMANENT = 4;
    public static final int READONLY = 1;
    public static final int UNINITIALIZED_CONST = 8;
    static final long serialVersionUID = 2829861078851942586L;
    private volatile Map<Object, Object> associatedValues;
    private transient ExternalArrayData externalData;
    private boolean isExtensible;
    private boolean isSealed;
    private Scriptable parentScopeObject;
    private Scriptable prototypeObject;
    private transient SlotMapContainer slotMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public enum SlotAccess {
        QUERY,
        MODIFY,
        MODIFY_CONST,
        MODIFY_GETTER_SETTER,
        CONVERT_ACCESSOR_TO_DATA
    }

    public boolean avoidObjectDetection() {
        return false;
    }

    @Override // org.mozilla.javascript.Scriptable
    public abstract String getClassName();

    static {
        try {
            GET_ARRAY_LENGTH = ScriptableObject.class.getMethod("getExternalArrayLength", new Class[0]);
            KEY_COMPARATOR = new KeyComparator();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class Slot implements Serializable {
        private static final long serialVersionUID = -6090581677123995491L;
        private short attributes;
        int indexOrHash;
        Object name;
        transient Slot next;
        transient Slot orderedNext;
        Object value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Slot(Object obj, int i, int i2) {
            this.name = obj;
            this.indexOrHash = i;
            this.attributes = (short) i2;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object obj = this.name;
            if (obj != null) {
                this.indexOrHash = obj.hashCode();
            }
        }

        boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            if ((this.attributes & 1) != 0) {
                if (Context.getContext().isStrictMode()) {
                    throw ScriptRuntime.typeError1("msg.modify.readonly", this.name);
                }
                return true;
            }
            if (scriptable != scriptable2) {
                return false;
            }
            this.value = obj;
            return true;
        }

        Object getValue(Scriptable scriptable) {
            return this.value;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public synchronized void setAttributes(int i) {
            ScriptableObject.checkValidAttributes(i);
            this.attributes = (short) i;
        }

        ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
            return ScriptableObject.buildDataDescriptor(scriptable, this.value, this.attributes);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ScriptableObject buildDataDescriptor(Scriptable scriptable, Object obj, int i) {
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, TopLevel.Builtins.Object);
        nativeObject.defineProperty(ES6Iterator.VALUE_PROPERTY, obj, 0);
        nativeObject.defineProperty("writable", Boolean.valueOf((i & 1) == 0), 0);
        nativeObject.defineProperty("enumerable", Boolean.valueOf((i & 2) == 0), 0);
        nativeObject.defineProperty("configurable", Boolean.valueOf((i & 4) == 0), 0);
        return nativeObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class GetterSlot extends Slot {
        static final long serialVersionUID = -4900574849788797588L;
        Object getter;
        Object setter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public GetterSlot(Object obj, int i, int i2) {
            super(obj, i, i2);
        }

        @Override // org.mozilla.javascript.ScriptableObject.Slot
        ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
            int attributes = getAttributes();
            NativeObject nativeObject = new NativeObject();
            ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, TopLevel.Builtins.Object);
            nativeObject.defineProperty("enumerable", Boolean.valueOf((attributes & 2) == 0), 0);
            nativeObject.defineProperty("configurable", Boolean.valueOf((attributes & 4) == 0), 0);
            if (this.getter == null && this.setter == null) {
                nativeObject.defineProperty("writable", Boolean.valueOf((attributes & 1) == 0), 0);
            }
            Object obj = this.getter;
            if (obj != null) {
                if (obj instanceof MemberBox) {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_GET, new FunctionObject(C3898x.f4339h, ((MemberBox) this.getter).member(), scriptable), 0);
                } else if (obj instanceof Member) {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_GET, new FunctionObject(C3898x.f4339h, (Member) this.getter, scriptable), 0);
                } else {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_GET, obj, 0);
                }
            }
            Object obj2 = this.setter;
            if (obj2 != null) {
                if (obj2 instanceof MemberBox) {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_SET, new FunctionObject(C3898x.f4339h, ((MemberBox) this.setter).member(), scriptable), 0);
                } else if (obj2 instanceof Member) {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_SET, new FunctionObject(C3898x.f4339h, (Member) this.setter, scriptable), 0);
                } else {
                    nativeObject.defineProperty(TmpConstant.PROPERTY_IDENTIFIER_SET, obj2, 0);
                }
            }
            return nativeObject;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.mozilla.javascript.ScriptableObject.Slot
        boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            Object[] objArr;
            if (this.setter == null) {
                if (this.getter != null) {
                    Context context = Context.getContext();
                    if (context.isStrictMode() || context.hasFeature(11)) {
                        throw ScriptRuntime.typeError1("msg.set.prop.no.setter", this.name);
                    }
                    return true;
                }
                return super.setValue(obj, scriptable, scriptable2);
            }
            Context context2 = Context.getContext();
            Object obj2 = this.setter;
            if (obj2 instanceof MemberBox) {
                MemberBox memberBox = (MemberBox) obj2;
                Class<?>[] clsArr = memberBox.argTypes;
                Object convertArg = FunctionObject.convertArg(context2, scriptable2, obj, FunctionObject.getTypeTag(clsArr[clsArr.length - 1]));
                if (memberBox.delegateTo == null) {
                    objArr = new Object[]{convertArg};
                } else {
                    Object[] objArr2 = {scriptable2, convertArg};
                    scriptable2 = memberBox.delegateTo;
                    objArr = objArr2;
                }
                memberBox.invoke(scriptable2, objArr);
            } else if (obj2 instanceof Function) {
                Function function = (Function) obj2;
                function.call(context2, function.getParentScope(), scriptable2, new Object[]{obj});
            }
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.mozilla.javascript.ScriptableObject.Slot
        Object getValue(Scriptable scriptable) {
            Object[] objArr;
            Object obj = this.getter;
            if (obj != null) {
                if (obj instanceof MemberBox) {
                    MemberBox memberBox = (MemberBox) obj;
                    if (memberBox.delegateTo == null) {
                        objArr = ScriptRuntime.emptyArgs;
                    } else {
                        Object[] objArr2 = {scriptable};
                        scriptable = memberBox.delegateTo;
                        objArr = objArr2;
                    }
                    return memberBox.invoke(scriptable, objArr);
                }
                if (obj instanceof Function) {
                    Function function = (Function) obj;
                    return function.call(Context.getContext(), function.getParentScope(), scriptable, ScriptRuntime.emptyArgs);
                }
            }
            Object obj2 = this.value;
            if (!(obj2 instanceof LazilyLoadedCtor)) {
                return obj2;
            }
            LazilyLoadedCtor lazilyLoadedCtor = (LazilyLoadedCtor) obj2;
            try {
                lazilyLoadedCtor.init();
                return lazilyLoadedCtor.getValue();
            } finally {
                this.value = lazilyLoadedCtor.getValue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkValidAttributes(int i) {
        if ((i & (-16)) != 0) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    private SlotMapContainer createSlotMap(int i) {
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null && currentContext.hasFeature(17)) {
            return new ThreadSafeSlotMapContainer(i);
        }
        return new SlotMapContainer(i);
    }

    public ScriptableObject() {
        this.isExtensible = true;
        this.isSealed = false;
        this.slotMap = createSlotMap(0);
    }

    public ScriptableObject(Scriptable scriptable, Scriptable scriptable2) {
        this.isExtensible = true;
        this.isSealed = false;
        if (scriptable == null) {
            throw new IllegalArgumentException();
        }
        this.parentScopeObject = scriptable;
        this.prototypeObject = scriptable2;
        this.slotMap = createSlotMap(0);
    }

    public String getTypeOf() {
        return avoidObjectDetection() ? "undefined" : "object";
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return this.slotMap.query(str, 0) != null;
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        ExternalArrayData externalArrayData = this.externalData;
        return externalArrayData != null ? i < externalArrayData.getArrayLength() : this.slotMap.query(null, i) != null;
    }

    public boolean has(Symbol symbol, Scriptable scriptable) {
        return this.slotMap.query(symbol, 0) != null;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        Slot query = this.slotMap.query(str, 0);
        if (query == null) {
            return Scriptable.NOT_FOUND;
        }
        return query.getValue(scriptable);
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        ExternalArrayData externalArrayData = this.externalData;
        if (externalArrayData != null) {
            if (i < externalArrayData.getArrayLength()) {
                return this.externalData.getArrayElement(i);
            }
            return Scriptable.NOT_FOUND;
        }
        Slot query = this.slotMap.query(null, i);
        if (query == null) {
            return Scriptable.NOT_FOUND;
        }
        return query.getValue(scriptable);
    }

    public Object get(Symbol symbol, Scriptable scriptable) {
        Slot query = this.slotMap.query(symbol, 0);
        if (query == null) {
            return Scriptable.NOT_FOUND;
        }
        return query.getValue(scriptable);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        if (putImpl(str, 0, scriptable, obj)) {
            return;
        }
        if (scriptable == this) {
            throw Kit.codeBug();
        }
        scriptable.put(str, scriptable, obj);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        ExternalArrayData externalArrayData = this.externalData;
        if (externalArrayData != null) {
            if (i < externalArrayData.getArrayLength()) {
                this.externalData.setArrayElement(i, obj);
                return;
            }
            throw new JavaScriptException(ScriptRuntime.newNativeError(Context.getCurrentContext(), this, TopLevel.NativeErrors.RangeError, new Object[]{"External array index out of bounds "}), null, 0);
        }
        if (putImpl(null, i, scriptable, obj)) {
            return;
        }
        if (scriptable == this) {
            throw Kit.codeBug();
        }
        scriptable.put(i, scriptable, obj);
    }

    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        if (putImpl(symbol, 0, scriptable, obj)) {
            return;
        }
        if (scriptable == this) {
            throw Kit.codeBug();
        }
        ensureSymbolScriptable(scriptable).put(symbol, scriptable, obj);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(String str) {
        checkNotSealed(str, 0);
        this.slotMap.remove(str, 0);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(int i) {
        checkNotSealed(null, i);
        this.slotMap.remove(null, i);
    }

    public void delete(Symbol symbol) {
        checkNotSealed(symbol, 0);
        this.slotMap.remove(symbol, 0);
    }

    @Override // org.mozilla.javascript.ConstProperties
    public void putConst(String str, Scriptable scriptable, Object obj) {
        if (putConstImpl(str, 0, scriptable, obj, 1)) {
            return;
        }
        if (scriptable == this) {
            throw Kit.codeBug();
        }
        if (scriptable instanceof ConstProperties) {
            ((ConstProperties) scriptable).putConst(str, scriptable, obj);
        } else {
            scriptable.put(str, scriptable, obj);
        }
    }

    @Override // org.mozilla.javascript.ConstProperties
    public void defineConst(String str, Scriptable scriptable) {
        if (putConstImpl(str, 0, scriptable, Undefined.instance, 8)) {
            return;
        }
        if (scriptable == this) {
            throw Kit.codeBug();
        }
        if (scriptable instanceof ConstProperties) {
            ((ConstProperties) scriptable).defineConst(str, scriptable);
        }
    }

    @Override // org.mozilla.javascript.ConstProperties
    public boolean isConst(String str) {
        Slot query = this.slotMap.query(str, 0);
        return query != null && (query.getAttributes() & 5) == 5;
    }

    @Deprecated
    public final int getAttributes(String str, Scriptable scriptable) {
        return getAttributes(str);
    }

    @Deprecated
    public final int getAttributes(int i, Scriptable scriptable) {
        return getAttributes(i);
    }

    @Deprecated
    public final void setAttributes(String str, Scriptable scriptable, int i) {
        setAttributes(str, i);
    }

    @Deprecated
    public void setAttributes(int i, Scriptable scriptable, int i2) {
        setAttributes(i, i2);
    }

    public int getAttributes(String str) {
        return findAttributeSlot(str, 0, SlotAccess.QUERY).getAttributes();
    }

    public int getAttributes(int i) {
        return findAttributeSlot(null, i, SlotAccess.QUERY).getAttributes();
    }

    public int getAttributes(Symbol symbol) {
        return findAttributeSlot(symbol, SlotAccess.QUERY).getAttributes();
    }

    public void setAttributes(String str, int i) {
        checkNotSealed(str, 0);
        findAttributeSlot(str, 0, SlotAccess.MODIFY).setAttributes(i);
    }

    public void setAttributes(int i, int i2) {
        checkNotSealed(null, i);
        findAttributeSlot(null, i, SlotAccess.MODIFY).setAttributes(i2);
    }

    public void setAttributes(Symbol symbol, int i) {
        checkNotSealed(symbol, 0);
        findAttributeSlot(symbol, SlotAccess.MODIFY).setAttributes(i);
    }

    public void setGetterOrSetter(String str, int i, Callable callable, boolean z) {
        setGetterOrSetter(str, i, callable, z, false);
    }

    private void setGetterOrSetter(String str, int i, Callable callable, boolean z, boolean z2) {
        GetterSlot getterSlot;
        if (str != null && i != 0) {
            throw new IllegalArgumentException(str);
        }
        if (!z2) {
            checkNotSealed(str, i);
        }
        if (isExtensible()) {
            getterSlot = (GetterSlot) this.slotMap.get(str, i, SlotAccess.MODIFY_GETTER_SETTER);
        } else {
            Slot query = this.slotMap.query(str, i);
            if (!(query instanceof GetterSlot)) {
                return;
            } else {
                getterSlot = (GetterSlot) query;
            }
        }
        if (!z2 && (getterSlot.getAttributes() & 1) != 0) {
            throw Context.reportRuntimeError1("msg.modify.readonly", str);
        }
        if (z) {
            getterSlot.setter = callable;
        } else {
            getterSlot.getter = callable;
        }
        getterSlot.value = Undefined.instance;
    }

    public Object getGetterOrSetter(String str, int i, boolean z) {
        if (str != null && i != 0) {
            throw new IllegalArgumentException(str);
        }
        Slot query = this.slotMap.query(str, i);
        if (query == null) {
            return null;
        }
        if (query instanceof GetterSlot) {
            GetterSlot getterSlot = (GetterSlot) query;
            Object obj = z ? getterSlot.setter : getterSlot.getter;
            return obj != null ? obj : Undefined.instance;
        }
        return Undefined.instance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isGetterOrSetter(String str, int i, boolean z) {
        Slot query = this.slotMap.query(str, i);
        if (!(query instanceof GetterSlot)) {
            return false;
        }
        if (!z || ((GetterSlot) query).setter == null) {
            return (z || ((GetterSlot) query).getter == null) ? false : true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addLazilyInitializedValue(String str, int i, LazilyLoadedCtor lazilyLoadedCtor, int i2) {
        if (str != null && i != 0) {
            throw new IllegalArgumentException(str);
        }
        checkNotSealed(str, i);
        GetterSlot getterSlot = (GetterSlot) this.slotMap.get(str, i, SlotAccess.MODIFY_GETTER_SETTER);
        getterSlot.setAttributes(i2);
        getterSlot.getter = null;
        getterSlot.setter = null;
        getterSlot.value = lazilyLoadedCtor;
    }

    public void setExternalArrayData(ExternalArrayData externalArrayData) {
        this.externalData = externalArrayData;
        if (externalArrayData == null) {
            delete(Name.LENGTH);
        } else {
            defineProperty(Name.LENGTH, null, GET_ARRAY_LENGTH, null, 3);
        }
    }

    public ExternalArrayData getExternalArrayData() {
        return this.externalData;
    }

    public Object getExternalArrayLength() {
        ExternalArrayData externalArrayData = this.externalData;
        return Integer.valueOf(externalArrayData == null ? 0 : externalArrayData.getArrayLength());
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getPrototype() {
        return this.prototypeObject;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setPrototype(Scriptable scriptable) {
        this.prototypeObject = scriptable;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getParentScope() {
        return this.parentScopeObject;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setParentScope(Scriptable scriptable) {
        this.parentScopeObject = scriptable;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        return getIds(false, false);
    }

    @Override // org.mozilla.javascript.debug.DebuggableObject
    public Object[] getAllIds() {
        return getIds(true, false);
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        return getDefaultValue(this, cls);
    }

    public static Object getDefaultValue(Scriptable scriptable, Class<?> cls) {
        String str;
        String str2;
        Object[] objArr;
        Context context = null;
        int i = 0;
        while (true) {
            str = "undefined";
            if (i < 2) {
                boolean z = cls != ScriptRuntime.StringClass ? i == 1 : i == 0;
                if (z) {
                    objArr = ScriptRuntime.emptyArgs;
                    str2 = "toString";
                } else {
                    Object[] objArr2 = new Object[1];
                    if (cls != null) {
                        if (cls == ScriptRuntime.StringClass) {
                            str = "string";
                        } else if (cls == ScriptRuntime.ScriptableClass) {
                            str = "object";
                        } else if (cls == ScriptRuntime.FunctionClass) {
                            str = "function";
                        } else if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
                            str = "boolean";
                        } else {
                            if (cls != ScriptRuntime.NumberClass && cls != ScriptRuntime.ByteClass && cls != Byte.TYPE && cls != ScriptRuntime.ShortClass && cls != Short.TYPE && cls != ScriptRuntime.IntegerClass && cls != Integer.TYPE && cls != ScriptRuntime.FloatClass && cls != Float.TYPE && cls != ScriptRuntime.DoubleClass && cls != Double.TYPE) {
                                throw Context.reportRuntimeError1("msg.invalid.type", cls.toString());
                            }
                            str = "number";
                        }
                    }
                    objArr2[0] = str;
                    str2 = "valueOf";
                    objArr = objArr2;
                }
                Object property = getProperty(scriptable, str2);
                if (property instanceof Function) {
                    Function function = (Function) property;
                    if (context == null) {
                        context = Context.getContext();
                    }
                    Object call = function.call(context, function.getParentScope(), scriptable, objArr);
                    if (call == null) {
                        continue;
                    } else if ((call instanceof Scriptable) && cls != ScriptRuntime.ScriptableClass && cls != ScriptRuntime.FunctionClass) {
                        if (z && (call instanceof Wrapper)) {
                            Object unwrap = ((Wrapper) call).unwrap();
                            if (unwrap instanceof String) {
                                return unwrap;
                            }
                        }
                    }
                }
                i++;
            } else {
                throw ScriptRuntime.typeError1("msg.default.value", cls != null ? cls.getName() : "undefined");
            }
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        return ScriptRuntime.jsDelegatesTo(scriptable, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object equivalentValues(Object obj) {
        return this == obj ? Boolean.TRUE : Scriptable.NOT_FOUND;
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, false, false);
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls, boolean z) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, z, false);
    }

    public static <T extends Scriptable> String defineClass(Scriptable scriptable, Class<T> cls, boolean z, boolean z2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        BaseFunction buildClassCtor = buildClassCtor(scriptable, cls, z, z2);
        if (buildClassCtor == null) {
            return null;
        }
        String className = buildClassCtor.getClassPrototype().getClassName();
        defineProperty(scriptable, className, buildClassCtor, 2);
        return className;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0207, code lost:
    
        if (r4 == null) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T extends Scriptable> BaseFunction buildClassCtor(Scriptable scriptable, Class<T> cls, boolean z, boolean z2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?> constructor;
        Scriptable scriptable2;
        Member findAnnotatedMember;
        Member member;
        Member member2;
        FunctionObject functionObject;
        HashSet hashSet;
        Annotation annotation;
        String str;
        HashSet hashSet2;
        boolean z3;
        int i;
        HashSet hashSet3;
        String propertyName;
        String defineClass;
        BaseFunction baseFunction;
        Object prototypeProperty;
        Method[] methodList = FunctionObject.getMethodList(cls);
        int i2 = 0;
        while (true) {
            int i3 = 3;
            Method method = null;
            if (i2 < methodList.length) {
                Method method2 = methodList[i2];
                if (method2.getName().equals("init")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    if (parameterTypes.length == 3 && parameterTypes[0] == ScriptRuntime.ContextClass && parameterTypes[1] == ScriptRuntime.ScriptableClass && parameterTypes[2] == Boolean.TYPE && Modifier.isStatic(method2.getModifiers())) {
                        Object[] objArr = new Object[3];
                        objArr[0] = Context.getContext();
                        objArr[1] = scriptable;
                        objArr[2] = z ? Boolean.TRUE : Boolean.FALSE;
                        method2.invoke(null, objArr);
                        return null;
                    }
                    if (parameterTypes.length == 1 && parameterTypes[0] == ScriptRuntime.ScriptableClass && Modifier.isStatic(method2.getModifiers())) {
                        method2.invoke(null, scriptable);
                        return null;
                    }
                }
                i2++;
            } else {
                Constructor<?>[] constructors = cls.getConstructors();
                int i4 = 0;
                while (true) {
                    if (i4 >= constructors.length) {
                        constructor = null;
                        break;
                    }
                    if (constructors[i4].getParameterTypes().length == 0) {
                        constructor = constructors[i4];
                        break;
                    }
                    i4++;
                }
                if (constructor == null) {
                    throw Context.reportRuntimeError1("msg.zero.arg.ctor", cls.getName());
                }
                Scriptable scriptable3 = (Scriptable) constructor.newInstance(ScriptRuntime.emptyArgs);
                String className = scriptable3.getClassName();
                Object property = getProperty(getTopLevelScope(scriptable), className);
                if ((property instanceof BaseFunction) && (prototypeProperty = (baseFunction = (BaseFunction) property).getPrototypeProperty()) != null) {
                    if (cls.equals(prototypeProperty.getClass())) {
                        return baseFunction;
                    }
                }
                if (z2) {
                    Class<? super T> superclass = cls.getSuperclass();
                    if (ScriptRuntime.ScriptableClass.isAssignableFrom(superclass) && !Modifier.isAbstract(superclass.getModifiers()) && (defineClass = defineClass(scriptable, extendsScriptable(superclass), z, z2)) != null) {
                        scriptable2 = getClassPrototype(scriptable, defineClass);
                        if (scriptable2 == null) {
                            scriptable2 = getObjectPrototype(scriptable);
                        }
                        scriptable3.setPrototype(scriptable2);
                        findAnnotatedMember = findAnnotatedMember(methodList, JSConstructor.class);
                        member = findAnnotatedMember;
                        if (findAnnotatedMember == null) {
                            member = findAnnotatedMember(constructors, JSConstructor.class);
                        }
                        String str2 = "jsConstructor";
                        member2 = member;
                        if (member == null) {
                            member2 = FunctionObject.findSingleMethod(methodList, "jsConstructor");
                        }
                        if (member2 == null) {
                            if (constructors.length == 1) {
                                member2 = constructors[0];
                            } else {
                                member2 = member2;
                                if (constructors.length == 2) {
                                    if (constructors[0].getParameterTypes().length == 0) {
                                        member2 = constructors[1];
                                    } else {
                                        member2 = member2;
                                        if (constructors[1].getParameterTypes().length == 0) {
                                            member2 = constructors[0];
                                        }
                                    }
                                }
                            }
                            if (member2 == null) {
                                throw Context.reportRuntimeError1("msg.ctor.multiple.parms", cls.getName());
                            }
                        }
                        functionObject = new FunctionObject(className, member2, scriptable);
                        if (!functionObject.isVarArgsMethod()) {
                            throw Context.reportRuntimeError1("msg.varargs.ctor", member2.getName());
                        }
                        functionObject.initAsConstructor(scriptable, scriptable3);
                        HashSet hashSet4 = new HashSet();
                        HashSet hashSet5 = new HashSet();
                        int length = methodList.length;
                        int i5 = 0;
                        while (i5 < length) {
                            Method method3 = methodList[i5];
                            if (method3 == member2) {
                                hashSet = hashSet4;
                            } else {
                                String name = method3.getName();
                                if (name.equals("finishInit")) {
                                    Class<?>[] parameterTypes2 = method3.getParameterTypes();
                                    hashSet = hashSet4;
                                    if (parameterTypes2.length == i3 && parameterTypes2[0] == ScriptRuntime.ScriptableClass && parameterTypes2[1] == FunctionObject.class && parameterTypes2[2] == ScriptRuntime.ScriptableClass && Modifier.isStatic(method3.getModifiers())) {
                                        method = method3;
                                    }
                                } else {
                                    hashSet = hashSet4;
                                }
                                if (name.indexOf(36) == -1 && !name.equals(str2)) {
                                    if (method3.isAnnotationPresent(JSFunction.class)) {
                                        annotation = method3.getAnnotation(JSFunction.class);
                                    } else if (method3.isAnnotationPresent(JSStaticFunction.class)) {
                                        annotation = method3.getAnnotation(JSStaticFunction.class);
                                    } else if (method3.isAnnotationPresent(JSGetter.class)) {
                                        annotation = method3.getAnnotation(JSGetter.class);
                                    } else if (!method3.isAnnotationPresent(JSSetter.class)) {
                                        annotation = null;
                                    }
                                    String str3 = "jsFunction_";
                                    str = str2;
                                    if (annotation == null) {
                                        if (!name.startsWith("jsFunction_")) {
                                            if (name.startsWith("jsStaticFunction_")) {
                                                str3 = "jsStaticFunction_";
                                            } else if (name.startsWith("jsGet_")) {
                                                str3 = "jsGet_";
                                            }
                                        }
                                        hashSet2 = hashSet5;
                                        z3 = !(annotation instanceof JSStaticFunction) || str3 == "jsStaticFunction_";
                                        if (z3) {
                                            i = length;
                                            hashSet3 = hashSet2;
                                        } else {
                                            hashSet3 = hashSet;
                                            i = length;
                                        }
                                        propertyName = getPropertyName(name, str3, annotation);
                                        if (!hashSet3.contains(propertyName)) {
                                            throw Context.reportRuntimeError2("duplicate.defineClass.name", name, propertyName);
                                        }
                                        hashSet3.add(propertyName);
                                        if ((annotation instanceof JSGetter) || str3 == "jsGet_") {
                                            if (!(scriptable3 instanceof ScriptableObject)) {
                                                throw Context.reportRuntimeError2("msg.extend.scriptable", scriptable3.getClass().toString(), propertyName);
                                            }
                                            Method findSetterMethod = findSetterMethod(methodList, propertyName, "jsSet_");
                                            ((ScriptableObject) scriptable3).defineProperty(propertyName, null, method3, findSetterMethod, (findSetterMethod != null ? 0 : 1) | 6);
                                        } else {
                                            if (z3 && !Modifier.isStatic(method3.getModifiers())) {
                                                throw Context.reportRuntimeError("jsStaticFunction must be used with static method.");
                                            }
                                            FunctionObject functionObject2 = new FunctionObject(propertyName, method3, scriptable3);
                                            if (functionObject2.isVarArgsConstructor()) {
                                                throw Context.reportRuntimeError1("msg.varargs.fun", member2.getName());
                                            }
                                            defineProperty(z3 ? functionObject : scriptable3, propertyName, functionObject2, 2);
                                            if (z) {
                                                functionObject2.sealObject();
                                            }
                                        }
                                        i5++;
                                        hashSet4 = hashSet;
                                        str2 = str;
                                        hashSet5 = hashSet2;
                                        length = i;
                                        i3 = 3;
                                    }
                                    hashSet2 = hashSet5;
                                    str3 = null;
                                    if (annotation instanceof JSStaticFunction) {
                                    }
                                    if (z3) {
                                    }
                                    propertyName = getPropertyName(name, str3, annotation);
                                    if (!hashSet3.contains(propertyName)) {
                                    }
                                }
                            }
                            str = str2;
                            hashSet2 = hashSet5;
                            i = length;
                            i5++;
                            hashSet4 = hashSet;
                            str2 = str;
                            hashSet5 = hashSet2;
                            length = i;
                            i3 = 3;
                        }
                        if (method != null) {
                            method.invoke(null, scriptable, functionObject, scriptable3);
                        }
                        if (z) {
                            functionObject.sealObject();
                            if (scriptable3 instanceof ScriptableObject) {
                                ((ScriptableObject) scriptable3).sealObject();
                            }
                        }
                        return functionObject;
                    }
                }
                scriptable2 = null;
                if (scriptable2 == null) {
                }
                scriptable3.setPrototype(scriptable2);
                findAnnotatedMember = findAnnotatedMember(methodList, JSConstructor.class);
                member = findAnnotatedMember;
                if (findAnnotatedMember == null) {
                }
                String str22 = "jsConstructor";
                member2 = member;
                if (member == null) {
                }
                if (member2 == null) {
                }
                functionObject = new FunctionObject(className, member2, scriptable);
                if (!functionObject.isVarArgsMethod()) {
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Member findAnnotatedMember(AccessibleObject[] accessibleObjectArr, Class<? extends Annotation> cls) {
        for (Invokable.ConstructorInvokable constructorInvokable : accessibleObjectArr) {
            if (constructorInvokable.isAnnotationPresent(cls)) {
                return constructorInvokable;
            }
        }
        return null;
    }

    private static Method findSetterMethod(Method[] methodArr, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(TmpConstant.PROPERTY_IDENTIFIER_SET);
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        String sb2 = sb.toString();
        for (Method method : methodArr) {
            JSSetter jSSetter = (JSSetter) method.getAnnotation(JSSetter.class);
            if (jSSetter != null && (str.equals(jSSetter.value()) || ("".equals(jSSetter.value()) && sb2.equals(method.getName())))) {
                return method;
            }
        }
        String str3 = str2 + str;
        for (Method method2 : methodArr) {
            if (str3.equals(method2.getName())) {
                return method2;
            }
        }
        return null;
    }

    private static String getPropertyName(String str, String str2, Annotation annotation) {
        if (str2 != null) {
            return str.substring(str2.length());
        }
        String str3 = null;
        if (annotation instanceof JSGetter) {
            str3 = ((JSGetter) annotation).value();
            if ((str3 == null || str3.length() == 0) && str.length() > 3 && str.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET)) {
                str3 = str.substring(3);
                if (Character.isUpperCase(str3.charAt(0))) {
                    if (str3.length() == 1) {
                        str3 = str3.toLowerCase();
                    } else if (!Character.isUpperCase(str3.charAt(1))) {
                        str3 = Character.toLowerCase(str3.charAt(0)) + str3.substring(1);
                    }
                }
            }
        } else if (annotation instanceof JSFunction) {
            str3 = ((JSFunction) annotation).value();
        } else if (annotation instanceof JSStaticFunction) {
            str3 = ((JSStaticFunction) annotation).value();
        }
        return (str3 == null || str3.length() == 0) ? str : str3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> cls) {
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return cls;
        }
        return null;
    }

    public void defineProperty(String str, Object obj, int i) {
        checkNotSealed(str, 0);
        put(str, this, obj);
        setAttributes(str, i);
    }

    public void defineProperty(Symbol symbol, Object obj, int i) {
        checkNotSealed(symbol, 0);
        put(symbol, this, obj);
        setAttributes(symbol, i);
    }

    public static void defineProperty(Scriptable scriptable, String str, Object obj, int i) {
        if (!(scriptable instanceof ScriptableObject)) {
            scriptable.put(str, scriptable, obj);
        } else {
            ((ScriptableObject) scriptable).defineProperty(str, obj, i);
        }
    }

    public static void defineConstProperty(Scriptable scriptable, String str) {
        if (scriptable instanceof ConstProperties) {
            ((ConstProperties) scriptable).defineConst(str, scriptable);
        } else {
            defineProperty(scriptable, str, Undefined.instance, 13);
        }
    }

    public void defineProperty(String str, Class<?> cls, int i) {
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        char[] cArr = new char[length + 3];
        str.getChars(0, length, cArr, 3);
        cArr[3] = Character.toUpperCase(cArr[3]);
        cArr[0] = 'g';
        cArr[1] = 'e';
        cArr[2] = 't';
        String str2 = new String(cArr);
        cArr[0] = 's';
        String str3 = new String(cArr);
        Method[] methodList = FunctionObject.getMethodList(cls);
        Method findSingleMethod = FunctionObject.findSingleMethod(methodList, str2);
        Method findSingleMethod2 = FunctionObject.findSingleMethod(methodList, str3);
        if (findSingleMethod2 == null) {
            i |= 1;
        }
        int i2 = i;
        if (findSingleMethod2 == null) {
            findSingleMethod2 = null;
        }
        defineProperty(str, null, findSingleMethod, findSingleMethod2, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x003c, code lost:
    
        if (r5 == org.mozilla.javascript.ScriptRuntime.ScriptableObjectClass) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x003f, code lost:
    
        if (r4 != false) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void defineProperty(String str, Object obj, Method method, Method method2, int i) {
        MemberBox memberBox;
        boolean z;
        String str2;
        boolean z2;
        String str3 = null;
        if (method != null) {
            memberBox = new MemberBox(method);
            if (!Modifier.isStatic(method.getModifiers())) {
                z2 = obj != null;
                memberBox.delegateTo = obj;
            } else {
                memberBox.delegateTo = Void.TYPE;
                z2 = true;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            String str4 = "msg.bad.getter.parms";
            if (parameterTypes.length == 0) {
                if (z2) {
                    str4 = "msg.obj.getter.parms";
                    if (str4 != null) {
                        throw Context.reportRuntimeError1(str4, method.toString());
                    }
                }
                str4 = null;
                if (str4 != null) {
                }
            } else {
                if (parameterTypes.length == 1) {
                    Class<?> cls = parameterTypes[0];
                    if (cls != ScriptRuntime.ScriptableClass) {
                    }
                }
                if (str4 != null) {
                }
            }
        } else {
            memberBox = null;
        }
        if (method2 != null) {
            if (method2.getReturnType() != Void.TYPE) {
                throw Context.reportRuntimeError1("msg.setter.return", method2.toString());
            }
            MemberBox memberBox2 = new MemberBox(method2);
            if (!Modifier.isStatic(method2.getModifiers())) {
                z = obj != null;
                memberBox2.delegateTo = obj;
            } else {
                memberBox2.delegateTo = Void.TYPE;
                z = true;
            }
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            if (parameterTypes2.length == 1) {
                if (z) {
                    str3 = "msg.setter2.expected";
                }
            } else if (parameterTypes2.length == 2) {
                Class<?> cls2 = parameterTypes2[0];
                if (cls2 == ScriptRuntime.ScriptableClass || cls2 == ScriptRuntime.ScriptableObjectClass) {
                    str2 = z ? "msg.setter2.parms" : "msg.setter1.parms";
                }
                str3 = str2;
            } else {
                str3 = "msg.setter.parms";
            }
            if (str3 != null) {
                throw Context.reportRuntimeError1(str3, method2.toString());
            }
            str3 = memberBox2;
        }
        GetterSlot getterSlot = (GetterSlot) this.slotMap.get(str, 0, SlotAccess.MODIFY_GETTER_SETTER);
        getterSlot.setAttributes(i);
        getterSlot.getter = memberBox;
        getterSlot.setter = str3;
    }

    public void defineOwnProperties(Context context, ScriptableObject scriptableObject) {
        Object[] ids = scriptableObject.getIds(false, true);
        ScriptableObject[] scriptableObjectArr = new ScriptableObject[ids.length];
        int length = ids.length;
        for (int i = 0; i < length; i++) {
            ScriptableObject ensureScriptableObject = ensureScriptableObject(ScriptRuntime.getObjectElem((Scriptable) scriptableObject, ids[i], context));
            checkPropertyDefinition(ensureScriptableObject);
            scriptableObjectArr[i] = ensureScriptableObject;
        }
        int length2 = ids.length;
        for (int i2 = 0; i2 < length2; i2++) {
            defineOwnProperty(context, ids[i2], scriptableObjectArr[i2]);
        }
    }

    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject) {
        checkPropertyDefinition(scriptableObject);
        defineOwnProperty(context, obj, scriptableObject, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        int applyDescriptorToAttributeBitset;
        Slot slot = getSlot(context, obj, SlotAccess.QUERY);
        boolean z2 = slot == null;
        if (z) {
            checkPropertyChange(obj, slot == null ? null : slot.getPropertyDescriptor(context, this), scriptableObject);
        }
        boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject);
        if (slot == null) {
            slot = getSlot(context, obj, isAccessorDescriptor ? SlotAccess.MODIFY_GETTER_SETTER : SlotAccess.MODIFY);
            applyDescriptorToAttributeBitset = applyDescriptorToAttributeBitset(7, scriptableObject);
        } else {
            applyDescriptorToAttributeBitset = applyDescriptorToAttributeBitset(slot.getAttributes(), scriptableObject);
        }
        if (isAccessorDescriptor) {
            if (!(slot instanceof GetterSlot)) {
                slot = getSlot(context, obj, SlotAccess.MODIFY_GETTER_SETTER);
            }
            GetterSlot getterSlot = (GetterSlot) slot;
            Object property = getProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_GET);
            if (property != NOT_FOUND) {
                getterSlot.getter = property;
            }
            Object property2 = getProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_SET);
            if (property2 != NOT_FOUND) {
                getterSlot.setter = property2;
            }
            getterSlot.value = Undefined.instance;
            getterSlot.setAttributes(applyDescriptorToAttributeBitset);
            return;
        }
        if ((slot instanceof GetterSlot) && isDataDescriptor(scriptableObject)) {
            slot = getSlot(context, obj, SlotAccess.CONVERT_ACCESSOR_TO_DATA);
        }
        Object property3 = getProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY);
        if (property3 != NOT_FOUND) {
            slot.value = property3;
        } else if (z2) {
            slot.value = Undefined.instance;
        }
        slot.setAttributes(applyDescriptorToAttributeBitset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkPropertyDefinition(ScriptableObject scriptableObject) {
        Object property = getProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_GET);
        if (property != NOT_FOUND && property != Undefined.instance && !(property instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(property);
        }
        Object property2 = getProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_SET);
        if (property2 != NOT_FOUND && property2 != Undefined.instance && !(property2 instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(property2);
        }
        if (isDataDescriptor(scriptableObject) && isAccessorDescriptor(scriptableObject)) {
            throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkPropertyChange(Object obj, ScriptableObject scriptableObject, ScriptableObject scriptableObject2) {
        if (scriptableObject == null) {
            if (!isExtensible()) {
                throw ScriptRuntime.typeError0("msg.not.extensible");
            }
            return;
        }
        if (isFalse(scriptableObject.get("configurable", scriptableObject))) {
            if (isTrue(getProperty(scriptableObject2, "configurable"))) {
                throw ScriptRuntime.typeError1("msg.change.configurable.false.to.true", obj);
            }
            if (isTrue(scriptableObject.get("enumerable", scriptableObject)) != isTrue(getProperty(scriptableObject2, "enumerable"))) {
                throw ScriptRuntime.typeError1("msg.change.enumerable.with.configurable.false", obj);
            }
            boolean isDataDescriptor = isDataDescriptor(scriptableObject2);
            boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject2);
            if (isDataDescriptor || isAccessorDescriptor) {
                if (isDataDescriptor && isDataDescriptor(scriptableObject)) {
                    if (isFalse(scriptableObject.get("writable", scriptableObject))) {
                        if (isTrue(getProperty(scriptableObject2, "writable"))) {
                            throw ScriptRuntime.typeError1("msg.change.writable.false.to.true.with.configurable.false", obj);
                        }
                        if (!sameValue(getProperty(scriptableObject2, ES6Iterator.VALUE_PROPERTY), scriptableObject.get(ES6Iterator.VALUE_PROPERTY, scriptableObject))) {
                            throw ScriptRuntime.typeError1("msg.change.value.with.writable.false", obj);
                        }
                        return;
                    }
                    return;
                }
                if (isAccessorDescriptor && isAccessorDescriptor(scriptableObject)) {
                    if (!sameValue(getProperty(scriptableObject2, TmpConstant.PROPERTY_IDENTIFIER_SET), scriptableObject.get(TmpConstant.PROPERTY_IDENTIFIER_SET, scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.setter.with.configurable.false", obj);
                    }
                    if (!sameValue(getProperty(scriptableObject2, TmpConstant.PROPERTY_IDENTIFIER_GET), scriptableObject.get(TmpConstant.PROPERTY_IDENTIFIER_GET, scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.getter.with.configurable.false", obj);
                    }
                    return;
                }
                if (isDataDescriptor(scriptableObject)) {
                    throw ScriptRuntime.typeError1("msg.change.property.data.to.accessor.with.configurable.false", obj);
                }
                throw ScriptRuntime.typeError1("msg.change.property.accessor.to.data.with.configurable.false", obj);
            }
        }
    }

    protected static boolean isTrue(Object obj) {
        return obj != NOT_FOUND && ScriptRuntime.toBoolean(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isFalse(Object obj) {
        return !isTrue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean sameValue(Object obj, Object obj2) {
        if (obj == NOT_FOUND) {
            return true;
        }
        if (obj2 == NOT_FOUND) {
            obj2 = Undefined.instance;
        }
        if ((obj2 instanceof Number) && (obj instanceof Number)) {
            double doubleValue = ((Number) obj2).doubleValue();
            double doubleValue2 = ((Number) obj).doubleValue();
            if (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2)) {
                return true;
            }
            if (doubleValue == 0.0d && Double.doubleToLongBits(doubleValue) != Double.doubleToLongBits(doubleValue2)) {
                return false;
            }
        }
        return ScriptRuntime.shallowEq(obj2, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int applyDescriptorToAttributeBitset(int i, ScriptableObject scriptableObject) {
        Object property = getProperty(scriptableObject, "enumerable");
        if (property != NOT_FOUND) {
            i = ScriptRuntime.toBoolean(property) ? i & (-3) : i | 2;
        }
        Object property2 = getProperty(scriptableObject, "writable");
        if (property2 != NOT_FOUND) {
            i = ScriptRuntime.toBoolean(property2) ? i & (-2) : i | 1;
        }
        Object property3 = getProperty(scriptableObject, "configurable");
        return property3 != NOT_FOUND ? ScriptRuntime.toBoolean(property3) ? i & (-5) : i | 4 : i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDataDescriptor(ScriptableObject scriptableObject) {
        return hasProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY) || hasProperty(scriptableObject, "writable");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isAccessorDescriptor(ScriptableObject scriptableObject) {
        return hasProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_GET) || hasProperty(scriptableObject, TmpConstant.PROPERTY_IDENTIFIER_SET);
    }

    protected boolean isGenericDescriptor(ScriptableObject scriptableObject) {
        return (isDataDescriptor(scriptableObject) || isAccessorDescriptor(scriptableObject)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Scriptable ensureScriptable(Object obj) {
        if (!(obj instanceof Scriptable)) {
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
        }
        return (Scriptable) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static SymbolScriptable ensureSymbolScriptable(Object obj) {
        if (!(obj instanceof SymbolScriptable)) {
            throw ScriptRuntime.typeError1("msg.object.not.symbolscriptable", ScriptRuntime.typeof(obj));
        }
        return (SymbolScriptable) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ScriptableObject ensureScriptableObject(Object obj) {
        if (!(obj instanceof ScriptableObject)) {
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
        }
        return (ScriptableObject) obj;
    }

    public void defineFunctionProperties(String[] strArr, Class<?> cls, int i) {
        Method[] methodList = FunctionObject.getMethodList(cls);
        for (String str : strArr) {
            Method findSingleMethod = FunctionObject.findSingleMethod(methodList, str);
            if (findSingleMethod == null) {
                throw Context.reportRuntimeError2("msg.method.not.found", str, cls.getName());
            }
            defineProperty(str, new FunctionObject(str, findSingleMethod, this), i);
        }
    }

    public static Scriptable getObjectPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Object);
    }

    public static Scriptable getFunctionPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Function);
    }

    public static Scriptable getArrayPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Array);
    }

    public static Scriptable getClassPrototype(Scriptable scriptable, String str) {
        Object obj;
        Object property = getProperty(getTopLevelScope(scriptable), str);
        if (property instanceof BaseFunction) {
            obj = ((BaseFunction) property).getPrototypeProperty();
        } else {
            if (property instanceof Scriptable) {
                Scriptable scriptable2 = (Scriptable) property;
                obj = scriptable2.get("prototype", scriptable2);
            }
            return null;
        }
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return null;
    }

    public static Scriptable getTopLevelScope(Scriptable scriptable) {
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                return scriptable;
            }
            scriptable = parentScope;
        }
    }

    public boolean isExtensible() {
        return this.isExtensible;
    }

    public void preventExtensions() {
        this.isExtensible = false;
    }

    public void sealObject() {
        if (this.isSealed) {
            return;
        }
        long readLock = this.slotMap.readLock();
        try {
            Iterator<Slot> it = this.slotMap.iterator();
            while (it.hasNext()) {
                Slot next = it.next();
                Object obj = next.value;
                if (obj instanceof LazilyLoadedCtor) {
                    LazilyLoadedCtor lazilyLoadedCtor = (LazilyLoadedCtor) obj;
                    try {
                        lazilyLoadedCtor.init();
                        next.value = lazilyLoadedCtor.getValue();
                    } catch (Throwable th) {
                        next.value = lazilyLoadedCtor.getValue();
                        throw th;
                    }
                }
            }
            this.isSealed = true;
        } finally {
            this.slotMap.unlockRead(readLock);
        }
    }

    public final boolean isSealed() {
        return this.isSealed;
    }

    private void checkNotSealed(Object obj, int i) {
        if (isSealed()) {
            throw Context.reportRuntimeError1("msg.modify.sealed", obj != null ? obj.toString() : Integer.toString(i));
        }
    }

    public static Object getProperty(Scriptable scriptable, String str) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(str, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                break;
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        return obj;
    }

    public static Object getProperty(Scriptable scriptable, Symbol symbol) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = ensureSymbolScriptable(scriptable2).get(symbol, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                break;
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, int i, Class<T> cls) {
        Object property = getProperty(scriptable, i);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static Object getProperty(Scriptable scriptable, int i) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(i, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                break;
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, String str, Class<T> cls) {
        Object property = getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static boolean hasProperty(Scriptable scriptable, String str) {
        return getBase(scriptable, str) != null;
    }

    public static void redefineProperty(Scriptable scriptable, String str, boolean z) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            return;
        }
        if ((base instanceof ConstProperties) && ((ConstProperties) base).isConst(str)) {
            throw ScriptRuntime.typeError1("msg.const.redecl", str);
        }
        if (z) {
            throw ScriptRuntime.typeError1("msg.var.redecl", str);
        }
    }

    public static boolean hasProperty(Scriptable scriptable, int i) {
        return getBase(scriptable, i) != null;
    }

    public static boolean hasProperty(Scriptable scriptable, Symbol symbol) {
        return getBase(scriptable, symbol) != null;
    }

    public static void putProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        base.put(str, scriptable, obj);
    }

    public static void putProperty(Scriptable scriptable, Symbol symbol, Object obj) {
        Scriptable base = getBase(scriptable, symbol);
        if (base == null) {
            base = scriptable;
        }
        ensureSymbolScriptable(base).put(symbol, scriptable, obj);
    }

    public static void putConstProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        if (base instanceof ConstProperties) {
            ((ConstProperties) base).putConst(str, scriptable, obj);
        }
    }

    public static void putProperty(Scriptable scriptable, int i, Object obj) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            base = scriptable;
        }
        base.put(i, scriptable, obj);
    }

    public static boolean deleteProperty(Scriptable scriptable, String str) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            return true;
        }
        base.delete(str);
        return !base.has(str, scriptable);
    }

    public static boolean deleteProperty(Scriptable scriptable, int i) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            return true;
        }
        base.delete(i);
        return !base.has(i, scriptable);
    }

    public static Object[] getPropertyIds(Scriptable scriptable) {
        if (scriptable == null) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] ids = scriptable.getIds();
        ObjToIntMap objToIntMap = null;
        while (true) {
            scriptable = scriptable.getPrototype();
            if (scriptable == null) {
                break;
            }
            Object[] ids2 = scriptable.getIds();
            if (ids2.length != 0) {
                if (objToIntMap == null) {
                    if (ids.length == 0) {
                        ids = ids2;
                    } else {
                        objToIntMap = new ObjToIntMap(ids.length + ids2.length);
                        for (int i = 0; i != ids.length; i++) {
                            objToIntMap.intern(ids[i]);
                        }
                        ids = null;
                    }
                }
                for (int i2 = 0; i2 != ids2.length; i2++) {
                    objToIntMap.intern(ids2[i2]);
                }
            }
        }
        return objToIntMap != null ? objToIntMap.getKeys() : ids;
    }

    public static Object callMethod(Scriptable scriptable, String str, Object[] objArr) {
        return callMethod(null, scriptable, str, objArr);
    }

    public static Object callMethod(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Object property = getProperty(scriptable, str);
        if (!(property instanceof Function)) {
            throw ScriptRuntime.notFunctionError(scriptable, str);
        }
        Function function = (Function) property;
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        if (context != null) {
            return function.call(context, topLevelScope, scriptable, objArr);
        }
        return Context.call(null, function, topLevelScope, scriptable, objArr);
    }

    private static Scriptable getBase(Scriptable scriptable, String str) {
        while (!scriptable.has(str, scriptable) && (scriptable = scriptable.getPrototype()) != null) {
        }
        return scriptable;
    }

    private static Scriptable getBase(Scriptable scriptable, int i) {
        while (!scriptable.has(i, scriptable) && (scriptable = scriptable.getPrototype()) != null) {
        }
        return scriptable;
    }

    private static Scriptable getBase(Scriptable scriptable, Symbol symbol) {
        while (!ensureSymbolScriptable(scriptable).has(symbol, scriptable) && (scriptable = scriptable.getPrototype()) != null) {
        }
        return scriptable;
    }

    public final Object getAssociatedValue(Object obj) {
        Map<Object, Object> map = this.associatedValues;
        if (map == null) {
            return null;
        }
        return map.get(obj);
    }

    public static Object getTopScopeValue(Scriptable scriptable, Object obj) {
        Object associatedValue;
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        do {
            if ((topLevelScope instanceof ScriptableObject) && (associatedValue = ((ScriptableObject) topLevelScope).getAssociatedValue(obj)) != null) {
                return associatedValue;
            }
            topLevelScope = topLevelScope.getPrototype();
        } while (topLevelScope != null);
        return null;
    }

    public final synchronized Object associateValue(Object obj, Object obj2) {
        Map map;
        if (obj2 == null) {
            throw new IllegalArgumentException();
        }
        map = this.associatedValues;
        if (map == null) {
            map = new HashMap();
            this.associatedValues = map;
        }
        return Kit.initHash(map, obj, obj2);
    }

    private boolean putImpl(Object obj, int i, Scriptable scriptable, Object obj2) {
        Slot slot;
        if (!this.isExtensible && Context.getContext().isStrictMode()) {
            throw ScriptRuntime.typeError0("msg.not.extensible");
        }
        if (this != scriptable) {
            slot = this.slotMap.query(obj, i);
            if (slot == null) {
                return false;
            }
        } else if (!this.isExtensible) {
            slot = this.slotMap.query(obj, i);
            if (slot == null) {
                return true;
            }
        } else {
            if (this.isSealed) {
                checkNotSealed(obj, i);
            }
            slot = this.slotMap.get(obj, i, SlotAccess.MODIFY);
        }
        return slot.setValue(obj2, this, scriptable);
    }

    private boolean putConstImpl(String str, int i, Scriptable scriptable, Object obj, int i2) {
        Slot query;
        if (!this.isExtensible && Context.getContext().isStrictMode()) {
            throw ScriptRuntime.typeError0("msg.not.extensible");
        }
        if (this != scriptable) {
            query = this.slotMap.query(str, i);
            if (query == null) {
                return false;
            }
        } else if (!isExtensible()) {
            query = this.slotMap.query(str, i);
            if (query == null) {
                return true;
            }
        } else {
            checkNotSealed(str, i);
            Slot slot = this.slotMap.get(str, i, SlotAccess.MODIFY_CONST);
            int attributes = slot.getAttributes();
            if ((attributes & 1) == 0) {
                throw Context.reportRuntimeError1("msg.var.redecl", str);
            }
            if ((attributes & 8) != 0) {
                slot.value = obj;
                if (i2 != 8) {
                    slot.setAttributes(attributes & (-9));
                }
            }
            return true;
        }
        return query.setValue(obj, this, scriptable);
    }

    private Slot findAttributeSlot(String str, int i, SlotAccess slotAccess) {
        Slot slot = this.slotMap.get(str, i, slotAccess);
        if (slot != null) {
            return slot;
        }
        if (str == null) {
            str = Integer.toString(i);
        }
        throw Context.reportRuntimeError1("msg.prop.not.found", str);
    }

    private Slot findAttributeSlot(Symbol symbol, SlotAccess slotAccess) {
        Slot slot = this.slotMap.get(symbol, 0, slotAccess);
        if (slot != null) {
            return slot;
        }
        throw Context.reportRuntimeError1("msg.prop.not.found", symbol);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object[] getIds(boolean z, boolean z2) {
        Object[] objArr;
        ExternalArrayData externalArrayData = this.externalData;
        int arrayLength = externalArrayData == null ? 0 : externalArrayData.getArrayLength();
        if (arrayLength == 0) {
            objArr = ScriptRuntime.emptyArgs;
        } else {
            objArr = new Object[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                objArr[i] = Integer.valueOf(i);
            }
        }
        if (this.slotMap.isEmpty()) {
            return objArr;
        }
        long readLock = this.slotMap.readLock();
        try {
            Iterator<Slot> it = this.slotMap.iterator();
            int i2 = arrayLength;
            while (it.hasNext()) {
                Slot next = it.next();
                if (z || (next.getAttributes() & 2) == 0) {
                    if (z2 || !(next.name instanceof Symbol)) {
                        if (i2 == arrayLength) {
                            Object[] objArr2 = new Object[this.slotMap.dirtySize() + arrayLength];
                            if (objArr != null) {
                                System.arraycopy(objArr, 0, objArr2, 0, arrayLength);
                            }
                            objArr = objArr2;
                        }
                        int i3 = i2 + 1;
                        objArr[i2] = next.name != null ? next.name : Integer.valueOf(next.indexOrHash);
                        i2 = i3;
                    }
                }
            }
            this.slotMap.unlockRead(readLock);
            if (i2 != objArr.length + arrayLength) {
                Object[] objArr3 = new Object[i2];
                System.arraycopy(objArr, 0, objArr3, 0, i2);
                objArr = objArr3;
            }
            Context currentContext = Context.getCurrentContext();
            if (currentContext != null && currentContext.hasFeature(16)) {
                Arrays.sort(objArr, KEY_COMPARATOR);
            }
            return objArr;
        } catch (Throwable th) {
            this.slotMap.unlockRead(readLock);
            throw th;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        long readLock = this.slotMap.readLock();
        try {
            int dirtySize = this.slotMap.dirtySize();
            if (dirtySize == 0) {
                objectOutputStream.writeInt(0);
            } else {
                objectOutputStream.writeInt(dirtySize);
                Iterator<Slot> it = this.slotMap.iterator();
                while (it.hasNext()) {
                    objectOutputStream.writeObject(it.next());
                }
            }
        } finally {
            this.slotMap.unlockRead(readLock);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        this.slotMap = createSlotMap(readInt);
        for (int i = 0; i < readInt; i++) {
            this.slotMap.addSlot((Slot) objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        Slot slot = getSlot(context, obj, SlotAccess.QUERY);
        if (slot == null) {
            return null;
        }
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        return slot.getPropertyDescriptor(context, parentScope);
    }

    protected Slot getSlot(Context context, Object obj, SlotAccess slotAccess) {
        if (obj instanceof Symbol) {
            return this.slotMap.get(obj, 0, slotAccess);
        }
        String stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            return this.slotMap.get(null, ScriptRuntime.lastIndexResult(context), slotAccess);
        }
        return this.slotMap.get(stringIdOrIndex, 0, slotAccess);
    }

    public int size() {
        return this.slotMap.size();
    }

    public boolean isEmpty() {
        return this.slotMap.isEmpty();
    }

    public Object get(Object obj) {
        Object obj2;
        if (obj instanceof String) {
            obj2 = get((String) obj, this);
        } else if (obj instanceof Symbol) {
            obj2 = get((Symbol) obj, this);
        } else {
            obj2 = obj instanceof Number ? get(((Number) obj).intValue(), this) : null;
        }
        if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
            return null;
        }
        return obj2 instanceof Wrapper ? ((Wrapper) obj2).unwrap() : obj2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class KeyComparator implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            int intValue;
            int intValue2;
            if (!(obj instanceof Integer)) {
                return obj2 instanceof Integer ? 1 : 0;
            }
            if (!(obj2 instanceof Integer) || (intValue = ((Integer) obj).intValue()) < (intValue2 = ((Integer) obj2).intValue())) {
                return -1;
            }
            return intValue > intValue2 ? 1 : 0;
        }
    }
}

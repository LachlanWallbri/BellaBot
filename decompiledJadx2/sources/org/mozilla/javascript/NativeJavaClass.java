package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Map;
import kotlin.text.Typography;
import org.objenesis.strategy.PlatformDescription;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeJavaClass extends NativeJavaObject implements Function {
    static final String javaClassPropertyName = "__javaObject__";
    static final long serialVersionUID = -6460763940409461664L;
    private Map<String, FieldAndMethods> staticFieldAndMethods;

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "JavaClass";
    }

    public NativeJavaClass() {
    }

    public NativeJavaClass(Scriptable scriptable, Class<?> cls) {
        this(scriptable, cls, false);
    }

    public NativeJavaClass(Scriptable scriptable, Class<?> cls, boolean z) {
        super(scriptable, cls, null, z);
    }

    @Override // org.mozilla.javascript.NativeJavaObject
    protected void initMembers() {
        Class cls = (Class) this.javaObject;
        this.members = JavaMembers.lookupClass(this.parent, cls, cls, this.isAdapter);
        this.staticFieldAndMethods = this.members.getFieldAndMethodsObjects(this, cls, true);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return this.members.has(str, true) || javaClassPropertyName.equals(str);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        FieldAndMethods fieldAndMethods;
        if (str.equals("prototype")) {
            return null;
        }
        Map<String, FieldAndMethods> map = this.staticFieldAndMethods;
        if (map != null && (fieldAndMethods = map.get(str)) != null) {
            return fieldAndMethods;
        }
        if (this.members.has(str, true)) {
            return this.members.get(this, str, this.javaObject, true);
        }
        Context context = Context.getContext();
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        WrapFactory wrapFactory = context.getWrapFactory();
        if (javaClassPropertyName.equals(str)) {
            return wrapFactory.wrap(context, topLevelScope, this.javaObject, ScriptRuntime.ClassClass);
        }
        Class<?> findNestedClass = findNestedClass(getClassObject(), str);
        if (findNestedClass != null) {
            Scriptable wrapJavaClass = wrapFactory.wrapJavaClass(context, topLevelScope, findNestedClass);
            wrapJavaClass.setParentScope(this);
            return wrapJavaClass;
        }
        throw this.members.reportMemberNotFound(str);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        this.members.put(this, str, this.javaObject, obj, true);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        return this.members.getIds(true);
    }

    public Class<?> getClassObject() {
        return (Class) super.unwrap();
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        if (cls == null || cls == ScriptRuntime.StringClass) {
            return toString();
        }
        if (cls == ScriptRuntime.BooleanClass) {
            return Boolean.TRUE;
        }
        return cls == ScriptRuntime.NumberClass ? ScriptRuntime.NaNobj : this;
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length == 1 && (objArr[0] instanceof Scriptable)) {
            Class<?> classObject = getClassObject();
            Scriptable scriptable3 = (Scriptable) objArr[0];
            do {
                if ((scriptable3 instanceof Wrapper) && classObject.isInstance(((Wrapper) scriptable3).unwrap())) {
                    return scriptable3;
                }
                scriptable3 = scriptable3.getPrototype();
            } while (scriptable3 != null);
        }
        return construct(context, scriptable, objArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0086, code lost:
    
        if (r6 == null) goto L29;
     */
    @Override // org.mozilla.javascript.Function
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        String message;
        Class<?> classObject = getClassObject();
        int modifiers = classObject.getModifiers();
        if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers)) {
            NativeJavaMethod nativeJavaMethod = this.members.ctors;
            int findCachedFunction = nativeJavaMethod.findCachedFunction(context, objArr);
            if (findCachedFunction < 0) {
                throw Context.reportRuntimeError2("msg.no.java.ctor", classObject.getName(), NativeJavaMethod.scriptSignature(objArr));
            }
            return constructSpecific(context, scriptable, objArr, nativeJavaMethod.methods[findCachedFunction]);
        }
        if (objArr.length == 0) {
            throw Context.reportRuntimeError0("msg.adapter.zero.args");
        }
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(this);
        try {
        } catch (Exception e) {
            message = e.getMessage();
        }
        if (PlatformDescription.DALVIK.equals(System.getProperty("java.vm.name")) && classObject.isInterface()) {
            return context.getWrapFactory().wrapAsJavaObject(context, scriptable, createInterfaceAdapter(classObject, ScriptableObject.ensureScriptableObject(objArr[0])), null);
        }
        Object obj = topLevelScope.get("JavaAdapter", topLevelScope);
        if (obj != NOT_FOUND) {
            return ((Function) obj).construct(context, topLevelScope, new Object[]{this, objArr[0]});
        }
        message = "";
        throw Context.reportRuntimeError2("msg.cant.instantiate", message, classObject.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Scriptable constructSpecific(Context context, Scriptable scriptable, Object[] objArr, MemberBox memberBox) {
        Object constructInternal = constructInternal(objArr, memberBox);
        return context.getWrapFactory().wrapNewObject(context, ScriptableObject.getTopLevelScope(scriptable), constructInternal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object constructInternal(Object[] objArr, MemberBox memberBox) {
        Object[] objArr2;
        Object obj;
        Class<?>[] clsArr = memberBox.argTypes;
        int i = 0;
        if (memberBox.vararg) {
            objArr2 = new Object[clsArr.length];
            for (int i2 = 0; i2 < clsArr.length - 1; i2++) {
                objArr2[i2] = Context.jsToJava(objArr[i2], clsArr[i2]);
            }
            if (objArr.length == clsArr.length && (objArr[objArr.length - 1] == null || (objArr[objArr.length - 1] instanceof NativeArray) || (objArr[objArr.length - 1] instanceof NativeJavaArray))) {
                obj = Context.jsToJava(objArr[objArr.length - 1], clsArr[clsArr.length - 1]);
            } else {
                Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
                Object newInstance = Array.newInstance(componentType, (objArr.length - clsArr.length) + 1);
                while (i < Array.getLength(newInstance)) {
                    Array.set(newInstance, i, Context.jsToJava(objArr[(clsArr.length - 1) + i], componentType));
                    i++;
                }
                obj = newInstance;
            }
            objArr2[clsArr.length - 1] = obj;
        } else {
            objArr2 = objArr;
            while (i < objArr2.length) {
                Object obj2 = objArr2[i];
                Object jsToJava = Context.jsToJava(obj2, clsArr[i]);
                if (jsToJava != obj2) {
                    if (objArr2 == objArr) {
                        objArr2 = (Object[]) objArr.clone();
                    }
                    objArr2[i] = jsToJava;
                }
                i++;
            }
        }
        return memberBox.newInstance(objArr2);
    }

    public String toString() {
        return "[JavaClass " + getClassObject().getName() + "]";
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        if (!(scriptable instanceof Wrapper) || (scriptable instanceof NativeJavaClass)) {
            return false;
        }
        return getClassObject().isInstance(((Wrapper) scriptable).unwrap());
    }

    private static Class<?> findNestedClass(Class<?> cls, String str) {
        String str2 = cls.getName() + Typography.dollar + str;
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            return Kit.classOrNull(str2);
        }
        return Kit.classOrNull(classLoader, str2);
    }
}

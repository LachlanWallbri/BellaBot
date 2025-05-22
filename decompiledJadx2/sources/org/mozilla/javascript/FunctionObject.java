package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class FunctionObject extends BaseFunction {
    public static final int JAVA_BOOLEAN_TYPE = 3;
    public static final int JAVA_DOUBLE_TYPE = 4;
    public static final int JAVA_INT_TYPE = 2;
    public static final int JAVA_OBJECT_TYPE = 6;
    public static final int JAVA_SCRIPTABLE_TYPE = 5;
    public static final int JAVA_STRING_TYPE = 1;
    public static final int JAVA_UNSUPPORTED_TYPE = 0;
    private static final short VARARGS_CTOR = -2;
    private static final short VARARGS_METHOD = -1;
    private static boolean sawSecurityException = false;
    static final long serialVersionUID = -5332312783643935019L;
    private String functionName;
    private transient boolean hasVoidReturn;
    private boolean isStatic;
    MemberBox member;
    private int parmsLength;
    private transient int returnTypeTag;
    private transient byte[] typeTags;

    public FunctionObject(String str, Member member, Scriptable scriptable) {
        if (member instanceof Constructor) {
            this.member = new MemberBox((Constructor<?>) member);
            this.isStatic = true;
        } else {
            MemberBox memberBox = new MemberBox((Method) member);
            this.member = memberBox;
            this.isStatic = memberBox.isStatic();
        }
        String name = this.member.getName();
        this.functionName = str;
        Class<?>[] clsArr = this.member.argTypes;
        int length = clsArr.length;
        if (length == 4 && (clsArr[1].isArray() || clsArr[2].isArray())) {
            if (clsArr[1].isArray()) {
                if (!this.isStatic || clsArr[0] != ScriptRuntime.ContextClass || clsArr[1].getComponentType() != ScriptRuntime.ObjectClass || clsArr[2] != ScriptRuntime.FunctionClass || clsArr[3] != Boolean.TYPE) {
                    throw Context.reportRuntimeError1("msg.varargs.ctor", name);
                }
                this.parmsLength = -2;
            } else {
                if (!this.isStatic || clsArr[0] != ScriptRuntime.ContextClass || clsArr[1] != ScriptRuntime.ScriptableClass || clsArr[2].getComponentType() != ScriptRuntime.ObjectClass || clsArr[3] != ScriptRuntime.FunctionClass) {
                    throw Context.reportRuntimeError1("msg.varargs.fun", name);
                }
                this.parmsLength = -1;
            }
        } else {
            this.parmsLength = length;
            if (length > 0) {
                this.typeTags = new byte[length];
                for (int i = 0; i != length; i++) {
                    int typeTag = getTypeTag(clsArr[i]);
                    if (typeTag == 0) {
                        throw Context.reportRuntimeError2("msg.bad.parms", clsArr[i].getName(), name);
                    }
                    this.typeTags[i] = (byte) typeTag;
                }
            }
        }
        if (this.member.isMethod()) {
            Class<?> returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        } else {
            Class<?> declaringClass = this.member.getDeclaringClass();
            if (!ScriptRuntime.ScriptableClass.isAssignableFrom(declaringClass)) {
                throw Context.reportRuntimeError1("msg.bad.ctor.return", declaringClass.getName());
            }
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
    }

    public static int getTypeTag(Class<?> cls) {
        if (cls == ScriptRuntime.StringClass) {
            return 1;
        }
        if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
            return 2;
        }
        if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
            return 3;
        }
        if (cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return 4;
        }
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return 5;
        }
        return cls == ScriptRuntime.ObjectClass ? 6 : 0;
    }

    public static Object convertArg(Context context, Scriptable scriptable, Object obj, int i) {
        switch (i) {
            case 1:
                return obj instanceof String ? obj : ScriptRuntime.toString(obj);
            case 2:
                return obj instanceof Integer ? obj : Integer.valueOf(ScriptRuntime.toInt32(obj));
            case 3:
                return obj instanceof Boolean ? obj : ScriptRuntime.toBoolean(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return obj instanceof Double ? obj : new Double(ScriptRuntime.toNumber(obj));
            case 5:
                return ScriptRuntime.toObjectOrNull(context, obj, scriptable);
            case 6:
                return obj;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        int i = this.parmsLength;
        if (i < 0) {
            return 1;
        }
        return i;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        return getArity();
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        String str = this.functionName;
        return str == null ? "" : str;
    }

    public Member getMethodOrConstructor() {
        if (this.member.isMethod()) {
            return this.member.method();
        }
        return this.member.ctor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Method findSingleMethod(Method[] methodArr, String str) {
        int length = methodArr.length;
        Method method = null;
        for (int i = 0; i != length; i++) {
            Method method2 = methodArr[i];
            if (method2 != null && str.equals(method2.getName())) {
                if (method != null) {
                    throw Context.reportRuntimeError2("msg.no.overload", str, method2.getDeclaringClass().getName());
                }
                method = method2;
            }
        }
        return method;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Method[] getMethodList(Class<?> cls) {
        Method[] methodArr;
        int i;
        int i2;
        try {
        } catch (SecurityException unused) {
            sawSecurityException = true;
        }
        if (!sawSecurityException) {
            methodArr = cls.getDeclaredMethods();
            if (methodArr == null) {
                methodArr = cls.getMethods();
            }
            int i3 = 0;
            for (i2 = 0; i2 < methodArr.length; i2++) {
                if (!sawSecurityException ? Modifier.isPublic(methodArr[i2].getModifiers()) : methodArr[i2].getDeclaringClass() == cls) {
                    i3++;
                } else {
                    methodArr[i2] = null;
                }
            }
            Method[] methodArr2 = new Method[i3];
            int i4 = 0;
            for (i = 0; i < methodArr.length; i++) {
                if (methodArr[i] != null) {
                    methodArr2[i4] = methodArr[i];
                    i4++;
                }
            }
            return methodArr2;
        }
        methodArr = null;
        if (methodArr == null) {
        }
        int i32 = 0;
        while (i2 < methodArr.length) {
        }
        Method[] methodArr22 = new Method[i32];
        int i42 = 0;
        while (i < methodArr.length) {
        }
        return methodArr22;
    }

    public void addAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        initAsConstructor(scriptable, scriptable2);
        defineProperty(scriptable, scriptable2.getClassName(), this, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        setImmunePrototypeProperty(scriptable2);
        scriptable2.setParentScope(this);
        defineProperty(scriptable2, "constructor", this, 7);
        setParentScope(scriptable);
    }

    @Deprecated
    public static Object convertArg(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        int typeTag = getTypeTag(cls);
        if (typeTag == 0) {
            throw Context.reportRuntimeError1("msg.cant.convert", cls.getName());
        }
        return convertArg(context, scriptable, obj, typeTag);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object[] objArr2;
        Object newInstance;
        boolean z;
        Scriptable parentScope;
        int length = objArr.length;
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            if (objArr[i] instanceof ConsString) {
                objArr[i] = objArr[i].toString();
            }
        }
        int i2 = this.parmsLength;
        if (i2 < 0) {
            if (i2 == -1) {
                newInstance = this.member.invoke(null, new Object[]{context, scriptable2, objArr, this});
                z2 = true;
                if (z2) {
                    return newInstance;
                }
                if (this.hasVoidReturn) {
                    return Undefined.instance;
                }
                return this.returnTypeTag == 0 ? context.getWrapFactory().wrap(context, scriptable, newInstance, null) : newInstance;
            }
            Object[] objArr3 = {context, objArr, this, scriptable2 == null ? Boolean.TRUE : Boolean.FALSE};
            if (this.member.isCtor()) {
                newInstance = this.member.newInstance(objArr3);
            } else {
                newInstance = this.member.invoke(null, objArr3);
            }
            if (z2) {
            }
        } else {
            if (!this.isStatic) {
                Class<?> declaringClass = this.member.getDeclaringClass();
                if (!declaringClass.isInstance(scriptable2)) {
                    if (scriptable2 != scriptable || scriptable == (parentScope = getParentScope())) {
                        z = false;
                    } else {
                        z = declaringClass.isInstance(parentScope);
                        if (z) {
                            scriptable2 = parentScope;
                        }
                    }
                    if (!z) {
                        throw ScriptRuntime.typeError1("msg.incompat.call", this.functionName);
                    }
                }
            }
            int i3 = this.parmsLength;
            if (i3 == length) {
                objArr2 = objArr;
                for (int i4 = 0; i4 != this.parmsLength; i4++) {
                    Object obj = objArr[i4];
                    Object convertArg = convertArg(context, scriptable, obj, this.typeTags[i4]);
                    if (obj != convertArg) {
                        if (objArr2 == objArr) {
                            objArr2 = (Object[]) objArr.clone();
                        }
                        objArr2[i4] = convertArg;
                    }
                }
            } else if (i3 == 0) {
                objArr2 = ScriptRuntime.emptyArgs;
            } else {
                objArr2 = new Object[i3];
                int i5 = 0;
                while (i5 != this.parmsLength) {
                    objArr2[i5] = convertArg(context, scriptable, i5 < length ? objArr[i5] : Undefined.instance, this.typeTags[i5]);
                    i5++;
                }
            }
            if (this.member.isMethod()) {
                newInstance = this.member.invoke(scriptable2, objArr2);
                z2 = true;
                if (z2) {
                }
            } else {
                newInstance = this.member.newInstance(objArr2);
                if (z2) {
                }
            }
        }
    }

    @Override // org.mozilla.javascript.BaseFunction
    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.member.isCtor() || this.parmsLength == -2) {
            return null;
        }
        try {
            Scriptable scriptable2 = (Scriptable) this.member.getDeclaringClass().newInstance();
            scriptable2.setPrototype(getClassPrototype());
            scriptable2.setParentScope(getParentScope());
            return scriptable2;
        } catch (Exception e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVarArgsMethod() {
        return this.parmsLength == -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVarArgsConstructor() {
        return this.parmsLength == -2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.parmsLength > 0) {
            Class<?>[] clsArr = this.member.argTypes;
            this.typeTags = new byte[this.parmsLength];
            for (int i = 0; i != this.parmsLength; i++) {
                this.typeTags[i] = (byte) getTypeTag(clsArr[i]);
            }
        }
        if (this.member.isMethod()) {
            Class<?> returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        }
    }
}

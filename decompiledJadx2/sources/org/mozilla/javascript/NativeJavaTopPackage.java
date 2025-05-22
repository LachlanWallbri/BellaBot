package org.mozilla.javascript;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeJavaTopPackage extends NativeJavaPackage implements Function, IdFunctionCall {
    private static final int Id_getClass = 1;
    static final long serialVersionUID = -1455787259477709999L;
    private static final String[][] commonPackages = {new String[]{"java", "lang", "reflect"}, new String[]{"java", "io"}, new String[]{"java", "math"}, new String[]{"java", "net"}, new String[]{"java", "util", ArchiveStreamFactory.ZIP}, new String[]{"java", "text", "resources"}, new String[]{"java", "applet"}, new String[]{"javax", "swing"}};
    private static final Object FTAG = "JavaTopPackage";

    NativeJavaTopPackage(ClassLoader classLoader) {
        super(true, "", classLoader);
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return construct(context, scriptable, objArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    @Override // org.mozilla.javascript.Function
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        ClassLoader classLoader;
        if (objArr.length != 0) {
            Object obj = objArr[0];
            if (obj instanceof Wrapper) {
                obj = ((Wrapper) obj).unwrap();
            }
            if (obj instanceof ClassLoader) {
                classLoader = (ClassLoader) obj;
                if (classLoader != null) {
                    Context.reportRuntimeError0("msg.not.classloader");
                    return null;
                }
                NativeJavaPackage nativeJavaPackage = new NativeJavaPackage(true, "", classLoader);
                ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage, scriptable);
                return nativeJavaPackage;
            }
        }
        classLoader = null;
        if (classLoader != null) {
        }
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeJavaTopPackage nativeJavaTopPackage = new NativeJavaTopPackage(context.getApplicationClassLoader());
        nativeJavaTopPackage.setPrototype(getObjectPrototype(scriptable));
        nativeJavaTopPackage.setParentScope(scriptable);
        for (int i = 0; i != commonPackages.length; i++) {
            int i2 = 0;
            NativeJavaPackage nativeJavaPackage = nativeJavaTopPackage;
            while (true) {
                String[][] strArr = commonPackages;
                if (i2 != strArr[i].length) {
                    nativeJavaPackage = nativeJavaPackage.forcePackage(strArr[i][i2], scriptable);
                    i2++;
                }
            }
        }
        IdFunctionObject idFunctionObject = new IdFunctionObject(nativeJavaTopPackage, FTAG, 1, "getClass", 1, scriptable);
        String[] topPackageNames = ScriptRuntime.getTopPackageNames();
        Object[] objArr = new NativeJavaPackage[topPackageNames.length];
        for (int i3 = 0; i3 < topPackageNames.length; i3++) {
            objArr[i3] = (NativeJavaPackage) nativeJavaTopPackage.get(topPackageNames[i3], nativeJavaTopPackage);
        }
        ScriptableObject scriptableObject = (ScriptableObject) scriptable;
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
        scriptableObject.defineProperty("Packages", nativeJavaTopPackage, 2);
        for (int i4 = 0; i4 < topPackageNames.length; i4++) {
            scriptableObject.defineProperty(topPackageNames[i4], objArr[i4], 2);
        }
    }

    @Override // org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_getClass(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    private Scriptable js_getClass(Context context, Scriptable scriptable, Object[] objArr) {
        String substring;
        if (objArr.length > 0) {
            int i = 0;
            if (objArr[0] instanceof Wrapper) {
                String name = ((Wrapper) objArr[0]).unwrap().getClass().getName();
                Scriptable scriptable2 = this;
                while (true) {
                    int indexOf = name.indexOf(46, i);
                    if (indexOf == -1) {
                        substring = name.substring(i);
                    } else {
                        substring = name.substring(i, indexOf);
                    }
                    Object obj = scriptable2.get(substring, scriptable2);
                    if (!(obj instanceof Scriptable)) {
                        break;
                    }
                    scriptable2 = (Scriptable) obj;
                    if (indexOf == -1) {
                        return scriptable2;
                    }
                    i = indexOf + 1;
                }
            }
        }
        throw Context.reportRuntimeError0("msg.not.java.obj");
    }
}

package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeJavaPackage extends ScriptableObject {
    static final long serialVersionUID = 7445054382212031523L;
    private transient ClassLoader classLoader;
    private Set<String> negativeCache;
    private String packageName;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "JavaPackage";
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeJavaPackage(boolean z, String str, ClassLoader classLoader) {
        this.negativeCache = null;
        this.packageName = str;
        this.classLoader = classLoader;
    }

    @Deprecated
    public NativeJavaPackage(String str, ClassLoader classLoader) {
        this(false, str, classLoader);
    }

    @Deprecated
    public NativeJavaPackage(String str) {
        this(false, str, Context.getCurrentContext().getApplicationClassLoader());
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        throw Context.reportRuntimeError0("msg.pkg.int");
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        return getPkgProperty(str, scriptable, true);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        return NOT_FOUND;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeJavaPackage forcePackage(String str, Scriptable scriptable) {
        String str2;
        Object obj = super.get(str, this);
        if (obj != null && (obj instanceof NativeJavaPackage)) {
            return (NativeJavaPackage) obj;
        }
        if (this.packageName.length() == 0) {
            str2 = str;
        } else {
            str2 = this.packageName + "." + str;
        }
        NativeJavaPackage nativeJavaPackage = new NativeJavaPackage(true, str2, this.classLoader);
        ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage, scriptable);
        super.put(str, this, nativeJavaPackage);
        return nativeJavaPackage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [org.mozilla.javascript.Scriptable] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.NativeJavaPackage] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable, org.mozilla.javascript.NativeJavaPackage] */
    public synchronized Object getPkgProperty(String str, Scriptable scriptable, boolean z) {
        String str2;
        Class<?> classOrNull;
        Object obj = super.get(str, scriptable);
        if (obj != NOT_FOUND) {
            return obj;
        }
        ?? r1 = 0;
        r1 = 0;
        if (this.negativeCache != null && this.negativeCache.contains(str)) {
            return null;
        }
        if (this.packageName.length() == 0) {
            str2 = str;
        } else {
            str2 = this.packageName + FilenameUtils.EXTENSION_SEPARATOR + str;
        }
        Context context = Context.getContext();
        ClassShutter classShutter = context.getClassShutter();
        if (classShutter == null || classShutter.visibleToScripts(str2)) {
            if (this.classLoader != null) {
                classOrNull = Kit.classOrNull(this.classLoader, str2);
            } else {
                classOrNull = Kit.classOrNull(str2);
            }
            if (classOrNull != null) {
                r1 = context.getWrapFactory().wrapJavaClass(context, getTopLevelScope(this), classOrNull);
                r1.setPrototype(getPrototype());
            }
        }
        if (r1 == 0) {
            if (z) {
                r1 = new NativeJavaPackage(true, str2, this.classLoader);
                ScriptRuntime.setObjectProtoAndParent(r1, getParentScope());
            } else {
                if (this.negativeCache == null) {
                    this.negativeCache = new HashSet();
                }
                this.negativeCache.add(str);
            }
        }
        if (r1 != 0) {
            super.put(str, scriptable, r1);
        }
        return r1;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        return toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.classLoader = Context.getCurrentContext().getApplicationClassLoader();
    }

    public String toString() {
        return "[JavaPackage " + this.packageName + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeJavaPackage)) {
            return false;
        }
        NativeJavaPackage nativeJavaPackage = (NativeJavaPackage) obj;
        return this.packageName.equals(nativeJavaPackage.packageName) && this.classLoader == nativeJavaPackage.classLoader;
    }

    public int hashCode() {
        int hashCode = this.packageName.hashCode();
        ClassLoader classLoader = this.classLoader;
        return hashCode ^ (classLoader == null ? 0 : classLoader.hashCode());
    }
}

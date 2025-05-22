package org.mozilla.javascript;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.JavaAdapter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ClassCache implements Serializable {
    private static final Object AKEY = "ClassCache";
    private static final long serialVersionUID = -8866246036237312215L;
    private Scriptable associatedScope;
    private volatile boolean cachingIsEnabled = true;
    private transient Map<JavaAdapter.JavaAdapterSignature, Class<?>> classAdapterCache;
    private transient Map<Class<?>, JavaMembers> classTable;
    private int generatedClassSerial;
    private transient Map<Class<?>, Object> interfaceAdapterCache;

    @Deprecated
    public boolean isInvokerOptimizationEnabled() {
        return false;
    }

    public static ClassCache get(Scriptable scriptable) {
        ClassCache classCache = (ClassCache) ScriptableObject.getTopScopeValue(scriptable, AKEY);
        if (classCache != null) {
            return classCache;
        }
        throw new RuntimeException("Can't find top level scope for ClassCache.get");
    }

    public boolean associate(ScriptableObject scriptableObject) {
        if (scriptableObject.getParentScope() != null) {
            throw new IllegalArgumentException();
        }
        if (this != scriptableObject.associateValue(AKEY, this)) {
            return false;
        }
        this.associatedScope = scriptableObject;
        return true;
    }

    public synchronized void clearCaches() {
        this.classTable = null;
        this.classAdapterCache = null;
        this.interfaceAdapterCache = null;
    }

    public final boolean isCachingEnabled() {
        return this.cachingIsEnabled;
    }

    public synchronized void setCachingEnabled(boolean z) {
        if (z == this.cachingIsEnabled) {
            return;
        }
        if (!z) {
            clearCaches();
        }
        this.cachingIsEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<Class<?>, JavaMembers> getClassCacheMap() {
        if (this.classTable == null) {
            this.classTable = new ConcurrentHashMap(16, 0.75f, 1);
        }
        return this.classTable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<JavaAdapter.JavaAdapterSignature, Class<?>> getInterfaceAdapterCacheMap() {
        if (this.classAdapterCache == null) {
            this.classAdapterCache = new ConcurrentHashMap(16, 0.75f, 1);
        }
        return this.classAdapterCache;
    }

    @Deprecated
    public synchronized void setInvokerOptimizationEnabled(boolean z) {
    }

    public final synchronized int newClassSerialNumber() {
        int i;
        i = this.generatedClassSerial + 1;
        this.generatedClassSerial = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getInterfaceAdapter(Class<?> cls) {
        Map<Class<?>, Object> map = this.interfaceAdapterCache;
        if (map == null) {
            return null;
        }
        return map.get(cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void cacheInterfaceAdapter(Class<?> cls, Object obj) {
        if (this.cachingIsEnabled) {
            if (this.interfaceAdapterCache == null) {
                this.interfaceAdapterCache = new ConcurrentHashMap(16, 0.75f, 1);
            }
            this.interfaceAdapterCache.put(cls, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Scriptable getAssociatedScope() {
        return this.associatedScope;
    }
}

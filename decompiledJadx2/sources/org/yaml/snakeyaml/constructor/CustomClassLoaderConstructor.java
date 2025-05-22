package org.yaml.snakeyaml.constructor;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class CustomClassLoaderConstructor extends Constructor {
    private ClassLoader loader;

    public CustomClassLoaderConstructor(ClassLoader classLoader) {
        this(Object.class, classLoader);
    }

    public CustomClassLoaderConstructor(Class<? extends Object> cls, ClassLoader classLoader) {
        super(cls);
        this.loader = CustomClassLoaderConstructor.class.getClassLoader();
        if (classLoader == null) {
            throw new NullPointerException("Loader must be provided.");
        }
        this.loader = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.constructor.Constructor
    public Class<?> getClassForName(String str) throws ClassNotFoundException {
        return Class.forName(str, true, this.loader);
    }
}

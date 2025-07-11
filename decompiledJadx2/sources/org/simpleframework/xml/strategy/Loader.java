package org.simpleframework.xml.strategy;

/* loaded from: classes9.dex */
class Loader {
    public Class load(String str) throws Exception {
        ClassLoader classLoader = getClassLoader();
        if (classLoader == null) {
            classLoader = getCallerClassLoader();
        }
        return classLoader.loadClass(str);
    }

    private static ClassLoader getCallerClassLoader() throws Exception {
        return Loader.class.getClassLoader();
    }

    private static ClassLoader getClassLoader() throws Exception {
        return Thread.currentThread().getContextClassLoader();
    }
}

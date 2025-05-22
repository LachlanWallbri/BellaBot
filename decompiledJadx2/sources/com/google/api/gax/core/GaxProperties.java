package com.google.api.gax.core;

import com.google.api.core.InternalApi;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class GaxProperties {
    private static final String DEFAULT_VERSION = "";
    private static final String GAX_VERSION = getLibraryVersion(GaxProperties.class, "version.gax");
    private static final String JAVA_VERSION = getRuntimeVersion();

    private GaxProperties() {
    }

    public static String getLibraryVersion(Class<?> cls) {
        String implementationVersion = cls.getPackage().getImplementationVersion();
        return implementationVersion != null ? implementationVersion : "";
    }

    public static String getLibraryVersion(Class<?> cls, String str) {
        String str2;
        if (cls.equals(GaxProperties.class)) {
            str2 = null;
        } else {
            str2 = getLibraryVersion(cls);
            if (!"".equals(str2)) {
                return str2;
            }
        }
        try {
            InputStream resourceAsStream = cls.getResourceAsStream("/dependencies.properties");
            if (resourceAsStream != null) {
                try {
                    Properties properties = new Properties();
                    properties.load(resourceAsStream);
                    str2 = properties.getProperty(str);
                } finally {
                }
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (IOException unused) {
        }
        return str2 != null ? str2 : "";
    }

    public static String getJavaVersion() {
        return JAVA_VERSION;
    }

    public static String getGaxVersion() {
        return GAX_VERSION;
    }

    private static String getRuntimeVersion() {
        return System.getProperty("java.version");
    }
}

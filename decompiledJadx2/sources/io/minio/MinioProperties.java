package io.minio;

import androidx.core.os.EnvironmentCompat;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes7.dex */
public enum MinioProperties {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(MinioProperties.class.getName());
    private final AtomicReference<String> version = new AtomicReference<>(null);

    MinioProperties() {
    }

    public String getVersion() {
        String str = this.version.get();
        if (str != null) {
            return str;
        }
        setVersion();
        return this.version.get();
    }

    private synchronized void setVersion() {
        if (this.version.get() != null) {
            return;
        }
        this.version.set("dev");
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            return;
        }
        try {
            Enumeration<URL> resources = classLoader.getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                InputStream openStream = resources.nextElement().openStream();
                try {
                    Manifest manifest = new Manifest(openStream);
                    if ("minio".equals(manifest.getMainAttributes().getValue("Implementation-Title"))) {
                        this.version.set(manifest.getMainAttributes().getValue("Implementation-Version"));
                        if (openStream != null) {
                            openStream.close();
                        }
                        return;
                    } else if (openStream != null) {
                        openStream.close();
                    }
                } catch (Throwable th) {
                    if (openStream != null) {
                        try {
                            openStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", (Throwable) e);
            this.version.set(EnvironmentCompat.MEDIA_UNKNOWN);
        }
    }

    public String getDefaultUserAgent() {
        return "MinIO (" + System.getProperty("os.name") + "; " + System.getProperty("os.arch") + ") minio-java/" + getVersion();
    }
}

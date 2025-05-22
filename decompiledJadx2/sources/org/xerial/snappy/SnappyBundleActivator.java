package org.xerial.snappy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/* loaded from: classes9.dex */
public class SnappyBundleActivator implements BundleActivator {
    public static final String LIBRARY_NAME = "snappyjava";

    public void start(BundleContext bundleContext) throws Exception {
        String mapLibraryName = System.mapLibraryName(LIBRARY_NAME);
        if (mapLibraryName.toLowerCase().endsWith(".dylib")) {
            mapLibraryName = mapLibraryName.replace(".dylib", ".jnilib");
        }
        System.loadLibrary(mapLibraryName);
        SnappyLoader.setSnappyApi(new SnappyNative());
    }

    public void stop(BundleContext bundleContext) throws Exception {
        SnappyLoader.setSnappyApi(null);
        SnappyLoader.cleanUpExtractedNativeLib();
    }
}

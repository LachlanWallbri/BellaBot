package androidx.test.internal.platform.util;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public interface TestOutputHandler {
    boolean addOutputProperties(Map<String, Serializable> properties);

    boolean captureWindowHierarchy(String outputName);

    void dumpThreadStates(String outputName);

    boolean takeScreenshot(String outputName);
}

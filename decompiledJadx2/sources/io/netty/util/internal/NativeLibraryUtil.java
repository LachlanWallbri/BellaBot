package io.netty.util.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class NativeLibraryUtil {
    public static void loadLibrary(String str, boolean z) {
        if (z) {
            System.load(str);
        } else {
            System.loadLibrary(str);
        }
    }

    private NativeLibraryUtil() {
    }
}

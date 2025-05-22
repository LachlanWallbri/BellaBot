package org.conscrypt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
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

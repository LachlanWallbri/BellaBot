package androidx.core.os;

import android.os.Build;
import androidx.exifinterface.media.ExifInterface;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BuildCompat {
    private BuildCompat() {
    }

    @Deprecated
    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30 || Build.VERSION.CODENAME.equals("R");
    }

    public static boolean isAtLeastS() {
        return Build.VERSION.CODENAME.equals(ExifInterface.LATITUDE_SOUTH);
    }
}

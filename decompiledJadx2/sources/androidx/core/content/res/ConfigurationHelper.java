package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(Resources resources) {
        if (Build.VERSION.SDK_INT >= 17) {
            return resources.getConfiguration().densityDpi;
        }
        return resources.getDisplayMetrics().densityDpi;
    }
}

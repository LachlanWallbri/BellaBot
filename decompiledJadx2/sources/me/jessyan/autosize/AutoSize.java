package me.jessyan.autosize;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.unit.Subunits;
import me.jessyan.autosize.utils.LogUtils;
import me.jessyan.autosize.utils.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public final class AutoSize {
    private static Map<String, DisplayMetricsInfo> mCache = new ConcurrentHashMap();

    private AutoSize() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void autoConvertDensityOfGlobal(Activity activity) {
        if (AutoSizeConfig.getInstance().isBaseOnWidth()) {
            autoConvertDensityBaseOnWidth(activity, AutoSizeConfig.getInstance().getDesignWidthInDp());
        } else {
            autoConvertDensityBaseOnHeight(activity, AutoSizeConfig.getInstance().getDesignHeightInDp());
        }
    }

    public static void autoConvertDensityOfCustomAdapt(Activity activity, CustomAdapt customAdapt) {
        int designHeightInDp;
        Preconditions.checkNotNull(customAdapt, "customAdapt == null");
        float sizeInDp = customAdapt.getSizeInDp();
        if (sizeInDp <= 0.0f) {
            if (customAdapt.isBaseOnWidth()) {
                designHeightInDp = AutoSizeConfig.getInstance().getDesignWidthInDp();
            } else {
                designHeightInDp = AutoSizeConfig.getInstance().getDesignHeightInDp();
            }
            sizeInDp = designHeightInDp;
        }
        autoConvertDensity(activity, sizeInDp, customAdapt.isBaseOnWidth());
    }

    public static void autoConvertDensityOfExternalAdaptInfo(Activity activity, ExternalAdaptInfo externalAdaptInfo) {
        int designHeightInDp;
        Preconditions.checkNotNull(externalAdaptInfo, "externalAdaptInfo == null");
        float sizeInDp = externalAdaptInfo.getSizeInDp();
        if (sizeInDp <= 0.0f) {
            if (externalAdaptInfo.isBaseOnWidth()) {
                designHeightInDp = AutoSizeConfig.getInstance().getDesignWidthInDp();
            } else {
                designHeightInDp = AutoSizeConfig.getInstance().getDesignHeightInDp();
            }
            sizeInDp = designHeightInDp;
        }
        autoConvertDensity(activity, sizeInDp, externalAdaptInfo.isBaseOnWidth());
    }

    public static void autoConvertDensityBaseOnWidth(Activity activity, float f) {
        autoConvertDensity(activity, f, true);
    }

    public static void autoConvertDensityBaseOnHeight(Activity activity, float f) {
        autoConvertDensity(activity, f, false);
    }

    public static void autoConvertDensity(Activity activity, float f, boolean z) {
        float designHeight;
        int screenHeight;
        float density;
        int densityDpi;
        float scaledDensity;
        float xdpi;
        int screenHeight2;
        int screenHeight3;
        Preconditions.checkNotNull(activity, "activity == null");
        if (z) {
            designHeight = AutoSizeConfig.getInstance().getUnitsManager().getDesignWidth();
        } else {
            designHeight = AutoSizeConfig.getInstance().getUnitsManager().getDesignHeight();
        }
        if (designHeight <= 0.0f) {
            designHeight = f;
        }
        if (z) {
            screenHeight = AutoSizeConfig.getInstance().getScreenWidth();
        } else {
            screenHeight = AutoSizeConfig.getInstance().getScreenHeight();
        }
        String str = f + "|" + designHeight + "|" + z + "|" + AutoSizeConfig.getInstance().isUseDeviceSize() + "|" + AutoSizeConfig.getInstance().getInitScaledDensity() + "|" + screenHeight;
        DisplayMetricsInfo displayMetricsInfo = mCache.get(str);
        if (displayMetricsInfo == null) {
            if (z) {
                screenHeight2 = AutoSizeConfig.getInstance().getScreenWidth();
            } else {
                screenHeight2 = AutoSizeConfig.getInstance().getScreenHeight();
            }
            density = (screenHeight2 * 1.0f) / f;
            scaledDensity = (AutoSizeConfig.getInstance().isExcludeFontScale() ? 1.0f : (AutoSizeConfig.getInstance().getInitScaledDensity() * 1.0f) / AutoSizeConfig.getInstance().getInitDensity()) * density;
            densityDpi = (int) (160.0f * density);
            if (z) {
                screenHeight3 = AutoSizeConfig.getInstance().getScreenWidth();
            } else {
                screenHeight3 = AutoSizeConfig.getInstance().getScreenHeight();
            }
            xdpi = (screenHeight3 * 1.0f) / designHeight;
            mCache.put(str, new DisplayMetricsInfo(density, densityDpi, scaledDensity, xdpi));
        } else {
            density = displayMetricsInfo.getDensity();
            densityDpi = displayMetricsInfo.getDensityDpi();
            scaledDensity = displayMetricsInfo.getScaledDensity();
            xdpi = displayMetricsInfo.getXdpi();
        }
        setDensity(activity, density, densityDpi, scaledDensity, xdpi);
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[11];
        objArr[0] = activity.getClass().getName();
        objArr[1] = activity.getClass().getSimpleName();
        objArr[2] = Boolean.valueOf(z);
        objArr[3] = z ? "designWidthInDp" : "designHeightInDp";
        objArr[4] = Float.valueOf(f);
        objArr[5] = z ? "designWidthInSubunits" : "designHeightInSubunits";
        objArr[6] = Float.valueOf(designHeight);
        objArr[7] = Float.valueOf(density);
        objArr[8] = Float.valueOf(scaledDensity);
        objArr[9] = Integer.valueOf(densityDpi);
        objArr[10] = Float.valueOf(xdpi);
        LogUtils.m3984d(String.format(locale, "The %s has been adapted! \n%s Info: isBaseOnWidth = %s, %s = %f, %s = %f, targetDensity = %f, targetScaledDensity = %f, targetDensityDpi = %d, targetXdpi = %f", objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: me.jessyan.autosize.AutoSize$1 */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class C79891 {
        static final /* synthetic */ int[] $SwitchMap$me$jessyan$autosize$unit$Subunits = new int[Subunits.values().length];

        static {
            try {
                $SwitchMap$me$jessyan$autosize$unit$Subunits[Subunits.PT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$me$jessyan$autosize$unit$Subunits[Subunits.MM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$me$jessyan$autosize$unit$Subunits[Subunits.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$me$jessyan$autosize$unit$Subunits[Subunits.IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void cancelAdapt(Activity activity) {
        float f;
        float initXdpi = AutoSizeConfig.getInstance().getInitXdpi();
        int i = C79891.$SwitchMap$me$jessyan$autosize$unit$Subunits[AutoSizeConfig.getInstance().getUnitsManager().getSupportSubunits().ordinal()];
        if (i != 1) {
            f = i == 2 ? 25.4f : 72.0f;
            setDensity(activity, AutoSizeConfig.getInstance().getInitDensity(), AutoSizeConfig.getInstance().getInitDensityDpi(), AutoSizeConfig.getInstance().getInitScaledDensity(), initXdpi);
        }
        initXdpi /= f;
        setDensity(activity, AutoSizeConfig.getInstance().getInitDensity(), AutoSizeConfig.getInstance().getInitDensityDpi(), AutoSizeConfig.getInstance().getInitScaledDensity(), initXdpi);
    }

    public static void initCompatMultiProcess(Context context) {
        context.getContentResolver().query(Uri.parse("content://" + context.getPackageName() + ".autosize-init-provider"), null, null, null, null);
    }

    private static void setDensity(Activity activity, float f, int i, float f2, float f3) {
        DisplayMetrics metricsOnMiui = getMetricsOnMiui(activity.getResources());
        DisplayMetrics metricsOnMiui2 = getMetricsOnMiui(AutoSizeConfig.getInstance().getApplication().getResources());
        if (metricsOnMiui != null) {
            setDensity(metricsOnMiui, f, i, f2, f3);
        } else {
            setDensity(activity.getResources().getDisplayMetrics(), f, i, f2, f3);
        }
        if (metricsOnMiui2 != null) {
            setDensity(metricsOnMiui2, f, i, f2, f3);
        } else {
            setDensity(AutoSizeConfig.getInstance().getApplication().getResources().getDisplayMetrics(), f, i, f2, f3);
        }
    }

    private static void setDensity(DisplayMetrics displayMetrics, float f, int i, float f2, float f3) {
        if (AutoSizeConfig.getInstance().getUnitsManager().isSupportDP()) {
            displayMetrics.density = f;
            displayMetrics.densityDpi = i;
        }
        if (AutoSizeConfig.getInstance().getUnitsManager().isSupportSP()) {
            displayMetrics.scaledDensity = f2;
        }
        int i2 = C79891.$SwitchMap$me$jessyan$autosize$unit$Subunits[AutoSizeConfig.getInstance().getUnitsManager().getSupportSubunits().ordinal()];
        if (i2 == 1) {
            displayMetrics.xdpi = f3 * 72.0f;
            return;
        }
        if (i2 == 2) {
            displayMetrics.xdpi = f3 * 25.4f;
        } else {
            if (i2 == 3 || i2 != 4) {
                return;
            }
            displayMetrics.xdpi = f3;
        }
    }

    private static DisplayMetrics getMetricsOnMiui(Resources resources) {
        if (AutoSizeConfig.getInstance().isMiui() && AutoSizeConfig.getInstance().getTmpMetricsField() != null) {
            try {
                return (DisplayMetrics) AutoSizeConfig.getInstance().getTmpMetricsField().get(resources);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}

package me.jessyan.autosize;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.unit.Subunits;
import me.jessyan.autosize.utils.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public final class AutoSizeCompat {
    private static Map<String, DisplayMetricsInfo> mCache = new ConcurrentHashMap();

    private AutoSizeCompat() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void autoConvertDensityOfGlobal(Resources resources) {
        if (AutoSizeConfig.getInstance().isBaseOnWidth()) {
            autoConvertDensityBaseOnWidth(resources, AutoSizeConfig.getInstance().getDesignWidthInDp());
        } else {
            autoConvertDensityBaseOnHeight(resources, AutoSizeConfig.getInstance().getDesignHeightInDp());
        }
    }

    public static void autoConvertDensityOfCustomAdapt(Resources resources, CustomAdapt customAdapt) {
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
        autoConvertDensity(resources, sizeInDp, customAdapt.isBaseOnWidth());
    }

    public static void autoConvertDensityOfExternalAdaptInfo(Resources resources, ExternalAdaptInfo externalAdaptInfo) {
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
        autoConvertDensity(resources, sizeInDp, externalAdaptInfo.isBaseOnWidth());
    }

    public static void autoConvertDensityBaseOnWidth(Resources resources, float f) {
        autoConvertDensity(resources, f, true);
    }

    public static void autoConvertDensityBaseOnHeight(Resources resources, float f) {
        autoConvertDensity(resources, f, false);
    }

    public static void autoConvertDensity(Resources resources, float f, boolean z) {
        float designHeight;
        int screenHeight;
        float density;
        int densityDpi;
        float scaledDensity;
        float xdpi;
        int screenHeight2;
        int screenHeight3;
        Preconditions.checkNotNull(resources, "resources == null");
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
        setDensity(resources, density, densityDpi, scaledDensity, xdpi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: me.jessyan.autosize.AutoSizeCompat$1 */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class C79901 {
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

    public static void cancelAdapt(Resources resources) {
        float f;
        float initXdpi = AutoSizeConfig.getInstance().getInitXdpi();
        int i = C79901.$SwitchMap$me$jessyan$autosize$unit$Subunits[AutoSizeConfig.getInstance().getUnitsManager().getSupportSubunits().ordinal()];
        if (i != 1) {
            f = i == 2 ? 25.4f : 72.0f;
            setDensity(resources, AutoSizeConfig.getInstance().getInitDensity(), AutoSizeConfig.getInstance().getInitDensityDpi(), AutoSizeConfig.getInstance().getInitScaledDensity(), initXdpi);
        }
        initXdpi /= f;
        setDensity(resources, AutoSizeConfig.getInstance().getInitDensity(), AutoSizeConfig.getInstance().getInitDensityDpi(), AutoSizeConfig.getInstance().getInitScaledDensity(), initXdpi);
    }

    private static void setDensity(Resources resources, float f, int i, float f2, float f3) {
        DisplayMetrics metricsOnMiui = getMetricsOnMiui(resources);
        DisplayMetrics metricsOnMiui2 = getMetricsOnMiui(AutoSizeConfig.getInstance().getApplication().getResources());
        if (metricsOnMiui != null) {
            setDensity(metricsOnMiui, f, i, f2, f3);
        } else {
            setDensity(resources.getDisplayMetrics(), f, i, f2, f3);
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
        int i2 = C79901.$SwitchMap$me$jessyan$autosize$unit$Subunits[AutoSizeConfig.getInstance().getUnitsManager().getSupportSubunits().ordinal()];
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

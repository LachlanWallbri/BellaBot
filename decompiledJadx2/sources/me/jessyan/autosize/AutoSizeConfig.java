package me.jessyan.autosize;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Field;
import me.jessyan.autosize.external.ExternalAdaptManager;
import me.jessyan.autosize.unit.UnitsManager;
import me.jessyan.autosize.utils.LogUtils;
import me.jessyan.autosize.utils.Preconditions;
import me.jessyan.autosize.utils.ScreenUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public final class AutoSizeConfig {
    private static final String KEY_DESIGN_HEIGHT_IN_DP = "design_height_in_dp";
    private static final String KEY_DESIGN_WIDTH_IN_DP = "design_width_in_dp";
    private static volatile AutoSizeConfig sInstance;
    private boolean isCustomFragment;
    private boolean isExcludeFontScale;
    private boolean isMiui;
    private boolean isStop;
    private boolean isVertical;
    private ActivityLifecycleCallbacksImpl mActivityLifecycleCallbacks;
    private Application mApplication;
    private int mDesignHeightInDp;
    private int mDesignWidthInDp;
    private int mInitDensityDpi;
    private float mInitScaledDensity;
    private float mInitXdpi;
    private onAdaptListener mOnAdaptListener;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mStatusBarHeight;
    private Field mTmpMetricsField;
    private ExternalAdaptManager mExternalAdaptManager = new ExternalAdaptManager();
    private UnitsManager mUnitsManager = new UnitsManager();
    private float mInitDensity = -1.0f;
    private boolean isBaseOnWidth = true;
    private boolean isUseDeviceSize = true;

    public static AutoSizeConfig getInstance() {
        if (sInstance == null) {
            synchronized (AutoSizeConfig.class) {
                if (sInstance == null) {
                    sInstance = new AutoSizeConfig();
                }
            }
        }
        return sInstance;
    }

    private AutoSizeConfig() {
    }

    public Application getApplication() {
        Preconditions.checkNotNull(this.mApplication, "Please call the AutoSizeConfig#init() first");
        return this.mApplication;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoSizeConfig init(Application application) {
        return init(application, true, null);
    }

    AutoSizeConfig init(Application application, boolean z) {
        return init(application, z, null);
    }

    AutoSizeConfig init(final Application application, boolean z, AutoAdaptStrategy autoAdaptStrategy) {
        Preconditions.checkArgument(this.mInitDensity == -1.0f, "AutoSizeConfig#init() can only be called once");
        Preconditions.checkNotNull(application, "application == null");
        this.mApplication = application;
        this.isBaseOnWidth = z;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        getMetaData(application);
        this.isVertical = application.getResources().getConfiguration().orientation == 1;
        int[] screenSize = ScreenUtils.getScreenSize(application);
        this.mScreenWidth = screenSize[0];
        this.mScreenHeight = screenSize[1];
        this.mStatusBarHeight = ScreenUtils.getStatusBarHeight();
        LogUtils.m3984d("designWidthInDp = " + this.mDesignWidthInDp + ", designHeightInDp = " + this.mDesignHeightInDp + ", screenWidth = " + this.mScreenWidth + ", screenHeight = " + this.mScreenHeight);
        this.mInitDensity = displayMetrics.density;
        this.mInitDensityDpi = displayMetrics.densityDpi;
        this.mInitScaledDensity = displayMetrics.scaledDensity;
        this.mInitXdpi = displayMetrics.xdpi;
        application.registerComponentCallbacks(new ComponentCallbacks() { // from class: me.jessyan.autosize.AutoSizeConfig.1
            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
                if (configuration != null) {
                    if (configuration.fontScale > 0.0f) {
                        AutoSizeConfig.this.mInitScaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
                        LogUtils.m3984d("initScaledDensity = " + AutoSizeConfig.this.mInitScaledDensity + " on ConfigurationChanged");
                    }
                    AutoSizeConfig.this.isVertical = configuration.orientation == 1;
                    int[] screenSize2 = ScreenUtils.getScreenSize(application);
                    AutoSizeConfig.this.mScreenWidth = screenSize2[0];
                    AutoSizeConfig.this.mScreenHeight = screenSize2[1];
                }
            }
        });
        LogUtils.m3984d("initDensity = " + this.mInitDensity + ", initScaledDensity = " + this.mInitScaledDensity);
        if (autoAdaptStrategy == null) {
            autoAdaptStrategy = new WrapperAutoAdaptStrategy(new DefaultAutoAdaptStrategy());
        }
        this.mActivityLifecycleCallbacks = new ActivityLifecycleCallbacksImpl(autoAdaptStrategy);
        application.registerActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
        if ("MiuiResources".equals(application.getResources().getClass().getSimpleName()) || "XResources".equals(application.getResources().getClass().getSimpleName())) {
            this.isMiui = true;
            try {
                this.mTmpMetricsField = Resources.class.getDeclaredField("mTmpMetrics");
                this.mTmpMetricsField.setAccessible(true);
            } catch (Exception unused) {
                this.mTmpMetricsField = null;
            }
        }
        return this;
    }

    public void restart() {
        Preconditions.checkNotNull(this.mActivityLifecycleCallbacks, "Please call the AutoSizeConfig#init() first");
        synchronized (AutoSizeConfig.class) {
            if (this.isStop) {
                this.mApplication.registerActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
                this.isStop = false;
            }
        }
    }

    public void stop(Activity activity) {
        Preconditions.checkNotNull(this.mActivityLifecycleCallbacks, "Please call the AutoSizeConfig#init() first");
        synchronized (AutoSizeConfig.class) {
            if (!this.isStop) {
                this.mApplication.unregisterActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
                AutoSize.cancelAdapt(activity);
                this.isStop = true;
            }
        }
    }

    public AutoSizeConfig setAutoAdaptStrategy(AutoAdaptStrategy autoAdaptStrategy) {
        Preconditions.checkNotNull(autoAdaptStrategy, "autoAdaptStrategy == null");
        Preconditions.checkNotNull(this.mActivityLifecycleCallbacks, "Please call the AutoSizeConfig#init() first");
        this.mActivityLifecycleCallbacks.setAutoAdaptStrategy(new WrapperAutoAdaptStrategy(autoAdaptStrategy));
        return this;
    }

    public AutoSizeConfig setOnAdaptListener(onAdaptListener onadaptlistener) {
        Preconditions.checkNotNull(onadaptlistener, "onAdaptListener == null");
        this.mOnAdaptListener = onadaptlistener;
        return this;
    }

    public AutoSizeConfig setBaseOnWidth(boolean z) {
        this.isBaseOnWidth = z;
        return this;
    }

    public AutoSizeConfig setUseDeviceSize(boolean z) {
        this.isUseDeviceSize = z;
        return this;
    }

    public AutoSizeConfig setLog(boolean z) {
        LogUtils.setDebug(z);
        return this;
    }

    public AutoSizeConfig setCustomFragment(boolean z) {
        this.isCustomFragment = z;
        return this;
    }

    public boolean isCustomFragment() {
        return this.isCustomFragment;
    }

    public boolean isStop() {
        return this.isStop;
    }

    public ExternalAdaptManager getExternalAdaptManager() {
        return this.mExternalAdaptManager;
    }

    public UnitsManager getUnitsManager() {
        return this.mUnitsManager;
    }

    public onAdaptListener getOnAdaptListener() {
        return this.mOnAdaptListener;
    }

    public boolean isBaseOnWidth() {
        return this.isBaseOnWidth;
    }

    public boolean isUseDeviceSize() {
        return this.isUseDeviceSize;
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public int getScreenHeight() {
        return isUseDeviceSize() ? this.mScreenHeight : this.mScreenHeight - this.mStatusBarHeight;
    }

    public int getDesignWidthInDp() {
        Preconditions.checkArgument(this.mDesignWidthInDp > 0, "you must set design_width_in_dp  in your AndroidManifest file");
        return this.mDesignWidthInDp;
    }

    public int getDesignHeightInDp() {
        Preconditions.checkArgument(this.mDesignHeightInDp > 0, "you must set design_height_in_dp  in your AndroidManifest file");
        return this.mDesignHeightInDp;
    }

    public float getInitDensity() {
        return this.mInitDensity;
    }

    public int getInitDensityDpi() {
        return this.mInitDensityDpi;
    }

    public float getInitScaledDensity() {
        return this.mInitScaledDensity;
    }

    public float getInitXdpi() {
        return this.mInitXdpi;
    }

    public boolean isVertical() {
        return this.isVertical;
    }

    public boolean isMiui() {
        return this.isMiui;
    }

    public Field getTmpMetricsField() {
        return this.mTmpMetricsField;
    }

    public AutoSizeConfig setVertical(boolean z) {
        this.isVertical = z;
        return this;
    }

    public boolean isExcludeFontScale() {
        return this.isExcludeFontScale;
    }

    public AutoSizeConfig setExcludeFontScale(boolean z) {
        this.isExcludeFontScale = z;
        return this;
    }

    public AutoSizeConfig setScreenWidth(int i) {
        Preconditions.checkArgument(i > 0, "screenWidth must be > 0");
        this.mScreenWidth = i;
        return this;
    }

    public AutoSizeConfig setScreenHeight(int i) {
        Preconditions.checkArgument(i > 0, "screenHeight must be > 0");
        this.mScreenHeight = i;
        return this;
    }

    public AutoSizeConfig setDesignWidthInDp(int i) {
        Preconditions.checkArgument(i > 0, "designWidthInDp must be > 0");
        this.mDesignWidthInDp = i;
        return this;
    }

    public AutoSizeConfig setDesignHeightInDp(int i) {
        Preconditions.checkArgument(i > 0, "designHeightInDp must be > 0");
        this.mDesignHeightInDp = i;
        return this;
    }

    public AutoSizeConfig setStatusBarHeight(int i) {
        Preconditions.checkArgument(i > 0, "statusBarHeight must be > 0");
        this.mStatusBarHeight = i;
        return this;
    }

    private void getMetaData(final Context context) {
        new Thread(new Runnable() { // from class: me.jessyan.autosize.AutoSizeConfig.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    if (applicationInfo == null || applicationInfo.metaData == null) {
                        return;
                    }
                    if (applicationInfo.metaData.containsKey(AutoSizeConfig.KEY_DESIGN_WIDTH_IN_DP)) {
                        AutoSizeConfig.this.mDesignWidthInDp = ((Integer) applicationInfo.metaData.get(AutoSizeConfig.KEY_DESIGN_WIDTH_IN_DP)).intValue();
                    }
                    if (applicationInfo.metaData.containsKey(AutoSizeConfig.KEY_DESIGN_HEIGHT_IN_DP)) {
                        AutoSizeConfig.this.mDesignHeightInDp = ((Integer) applicationInfo.metaData.get(AutoSizeConfig.KEY_DESIGN_HEIGHT_IN_DP)).intValue();
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

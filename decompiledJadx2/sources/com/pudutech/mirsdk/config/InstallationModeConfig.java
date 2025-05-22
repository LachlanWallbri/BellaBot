package com.pudutech.mirsdk.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.pudutech.base.Pdlog;
import com.pudutech.installerserver.InstallerServer;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: InstallationModeConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010+\u001a\u00020\u000fH\u0002J\u0010\u0010,\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010-\u001a\u00020\u0011J\u000e\u0010.\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010/\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J*\u00100\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00101\u001a\u00020\u000b2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00063"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/InstallationModeConfig;", "", "()V", "DISABLE_ADB", "", "EACH_LOOP_DURATION", "", "EACH_SAVE_DURATION", "ENABLE_ADB", "InstallModeSwitchNotice", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "notice", "", "TAG", "", "WAIT_CLOSE_TIME", "context", "Landroid/content/Context;", "installModeOpenTime", "installerService", "Lcom/pudutech/installerserver/InstallerServer;", "getInstallerService", "()Lcom/pudutech/installerserver/InstallerServer;", "setInstallerService", "(Lcom/pudutech/installerserver/InstallerServer;)V", "isOpen", "()Z", "setOpen", "(Z)V", "lastSaveTime", "modeJob", "Lkotlinx/coroutines/Job;", "openInstallationModeTime", "productMachineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "getProductMachineType", "()Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "setProductMachineType", "(Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;)V", "apply", "checkOpenTime", "closeInstallationMode", "getGitHash", "init", "openInstallationMode", "setInstallMode", "switch", "listener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class InstallationModeConfig {
    private final int DISABLE_ADB;
    private Function1<? super Boolean, Unit> InstallModeSwitchNotice;
    private Context context;
    private long installModeOpenTime;
    private InstallerServer installerService;
    private boolean isOpen;
    private long lastSaveTime;
    private Job modeJob;
    private long openInstallationModeTime;
    private ProductMachineType productMachineType;
    private final String TAG = "InstallationModeConfig";
    private final long WAIT_CLOSE_TIME = 21600000;
    private final long EACH_SAVE_DURATION = 120000;
    private final long EACH_LOOP_DURATION = 60000;
    private final int ENABLE_ADB = 1;

    public static final /* synthetic */ Context access$getContext$p(InstallationModeConfig installationModeConfig) {
        Context context = installationModeConfig.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
    }

    /* renamed from: isOpen, reason: from getter */
    public final boolean getIsOpen() {
        return this.isOpen;
    }

    public final void setOpen(boolean z) {
        this.isOpen = z;
    }

    public final InstallerServer getInstallerService() {
        return this.installerService;
    }

    public final void setInstallerService(InstallerServer installerServer) {
        this.installerService = installerServer;
    }

    public final ProductMachineType getProductMachineType() {
        return this.productMachineType;
    }

    public final void setProductMachineType(ProductMachineType productMachineType) {
        this.productMachineType = productMachineType;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.isOpen = SDKConfig.INSTANCE.getPreferences().getBoolean("InstallMode", false);
        this.installModeOpenTime = SDKConfig.INSTANCE.getPreferences().getLong("InstallModeOpenTime", 0L);
        Pdlog.m3273d(this.TAG, "installation set mode time " + this.installModeOpenTime + " installation mode " + this.isOpen);
        if (this.isOpen) {
            long j = this.installModeOpenTime;
            if (j > this.WAIT_CLOSE_TIME) {
                j = 0;
            }
            this.openInstallationModeTime = j;
            this.lastSaveTime = 0L;
        }
        apply(context);
    }

    private final void checkOpenTime() {
        Job launch$default;
        if (this.modeJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new InstallationModeConfig$checkOpenTime$1(this, null), 3, null);
            this.modeJob = launch$default;
        }
    }

    public final void setInstallMode(Context context, boolean r8, Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (r8 == this.isOpen) {
            return;
        }
        this.InstallModeSwitchNotice = listener;
        if (r8) {
            this.openInstallationModeTime = 0L;
            this.lastSaveTime = 0L;
            this.isOpen = true;
        } else {
            this.isOpen = false;
            this.openInstallationModeTime = 0L;
            this.lastSaveTime = 0L;
            Job job = this.modeJob;
            if (job != null && job != null && job.isActive()) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new InstallationModeConfig$setInstallMode$1(this, null), 3, null);
            }
        }
        SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
        edit.putBoolean("InstallMode", this.isOpen);
        edit.putLong("InstallModeOpenTime", this.openInstallationModeTime);
        edit.apply();
        apply(context);
    }

    public final String getGitHash() {
        return "{\"install\":\"" + InstallerServer.getGitHash() + "\"}";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(Context context) {
        MachineModel model;
        MachineModel model2;
        String str = null;
        if (this.isOpen) {
            if (this.installerService == null) {
                InstallerServer installerServer = new InstallerServer();
                if (installerServer.init(this.productMachineType)) {
                    this.installerService = installerServer;
                    String str2 = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("InstallerService init success with machine type ");
                    ProductMachineType productMachineType = this.productMachineType;
                    if (productMachineType != null && (model2 = productMachineType.getModel()) != null) {
                        str = model2.name();
                    }
                    sb.append(str);
                    objArr[0] = sb.toString();
                    Pdlog.m3275i(str2, objArr);
                } else {
                    String str3 = this.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("InstallerService init fail with machine type ");
                    ProductMachineType productMachineType2 = this.productMachineType;
                    if (productMachineType2 != null && (model = productMachineType2.getModel()) != null) {
                        str = model.name();
                    }
                    sb2.append(str);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3277w(str3, objArr2);
                }
            } else {
                Pdlog.m3275i(this.TAG, "InstallerService is already opened");
            }
            openInstallationMode(context);
            checkOpenTime();
        } else {
            InstallerServer installerServer2 = this.installerService;
            if (installerServer2 != null) {
                if (installerServer2 != null) {
                    installerServer2.uninit();
                }
                this.installerService = (InstallerServer) null;
            }
            closeInstallationMode(context);
        }
        Function1<? super Boolean, Unit> function1 = this.InstallModeSwitchNotice;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(this.isOpen));
        }
    }

    private final void openInstallationMode(Context context) {
        try {
            String adbWifiEnabled = Settings.Global.getString(context.getContentResolver(), SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY());
            Pdlog.m3273d(this.TAG, "set adb open for " + SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY() + ", current is " + adbWifiEnabled);
            Intrinsics.checkExpressionValueIsNotNull(adbWifiEnabled, "adbWifiEnabled");
            if (Integer.parseInt(adbWifiEnabled) != this.ENABLE_ADB) {
                Settings.Global.putInt(context.getContentResolver(), SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY(), this.ENABLE_ADB);
                SystemProperty.INSTANCE.setProperty(SystemProperty.INSTANCE.getADB_WIFI_CONNECT_KEY(), "1", context);
            }
            boolean areEqual = Intrinsics.areEqual(SystemProperty.INSTANCE.getProperty(SystemProperty.INSTANCE.getADB_WIFI_CONNECT_KEY(), context), "1");
            Pdlog.m3273d(this.TAG, "open installation mode? " + areEqual);
        } catch (Throwable th) {
            Pdlog.m3274e(this.TAG, "adb open exception:", th, " stack:", th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeInstallationMode(Context context) {
        try {
            String string = Settings.Global.getString(context.getContentResolver(), SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY());
            Pdlog.m3273d(this.TAG, "set adb close for " + SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY() + ", current is " + string);
            Settings.Global.putInt(context.getContentResolver(), SystemProperty.INSTANCE.getADB_WIFI_ENABLE_KEY(), this.DISABLE_ADB);
            SystemProperty.INSTANCE.setProperty(SystemProperty.INSTANCE.getADB_WIFI_CONNECT_KEY(), "0", context);
            boolean areEqual = Intrinsics.areEqual(SystemProperty.INSTANCE.getProperty(SystemProperty.INSTANCE.getADB_WIFI_CONNECT_KEY(), context), "0");
            Pdlog.m3273d(this.TAG, "close installation mode? " + areEqual);
        } catch (Throwable th) {
            Pdlog.m3274e(this.TAG, "adb close exception:", th, " stack:", th.getMessage());
        }
    }
}

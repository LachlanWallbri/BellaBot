package com.pudutech.freeinstall_ui.manager;

import android.os.PowerManager;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.LostLocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.HardwareExceptListener;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.module.report.task.ReportError;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AbnormalManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0006\u0010\u0018\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\u0013J\u0006\u0010\u001a\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/manager/AbnormalManager;", "", "()V", "HARDWARE_EXCEPTION", "", "LOCATE_STATUS", "TAG", "TYPE_CLEARERROR", "TYPE_EMERGENCYBRAKE", "TYPE_WHEELERROR", "hasShowExceptionDialog", "", "hasShowLocateDialog", "hasShowStopExceptionDialog", "locateDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "sensorDialog", "shutStopDialog", "addHardWareListener", "", "addLocateStatusListener", "removeHardWareListener", "removeLocateStatusListener", "restartApp", "showExceptionDialog", "showLostDialog", "showStopDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AbnormalManager {
    private static final String HARDWARE_EXCEPTION = "hardware_exception";
    public static final AbnormalManager INSTANCE = new AbnormalManager();
    private static final String LOCATE_STATUS = "locate_status";
    public static final String TAG = "AbnormalManager";
    public static final String TYPE_CLEARERROR = "ClearError";
    public static final String TYPE_EMERGENCYBRAKE = "EmergencyBrake";
    public static final String TYPE_WHEELERROR = "WheelError";
    private static boolean hasShowExceptionDialog;
    private static boolean hasShowLocateDialog;
    private static boolean hasShowStopExceptionDialog;
    private static CommonDialog locateDialog;
    private static CommonDialog sensorDialog;
    private static CommonDialog shutStopDialog;

    private AbnormalManager() {
    }

    public final void addLocateStatusListener() {
        RobotMapManager.INSTANCE.addLocateStatusListener(LOCATE_STATUS, new LostLocalizationListener.Stub() { // from class: com.pudutech.freeinstall_ui.manager.AbnormalManager$addLocateStatusListener$1
            @Override // com.pudutech.mirsdk.aidl.LostLocalizationListener
            public void LostLocalization(LocalizationStatus p0) {
                CommonDialog commonDialog;
                CommonDialog commonDialog2;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("LostLocalization ");
                sb.append(p0 != null ? p0.getStatus_level() : null);
                sb.append("  ");
                sb.append(p0 != null ? p0.getStatus_info() : null);
                sb.append(' ');
                sb.append(p0 != null ? p0.getStatus_description() : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(AbnormalManager.TAG, objArr);
                if (p0 == null || p0.getStatus_level() != LocalizationStatusLevel.Error) {
                    AbnormalManager abnormalManager = AbnormalManager.INSTANCE;
                    commonDialog = AbnormalManager.locateDialog;
                    if (commonDialog == null || !commonDialog.isShowing()) {
                        return;
                    }
                    AbnormalManager abnormalManager2 = AbnormalManager.INSTANCE;
                    commonDialog2 = AbnormalManager.locateDialog;
                    if (commonDialog2 != null) {
                        commonDialog2.dismiss();
                    }
                    AbnormalManager abnormalManager3 = AbnormalManager.INSTANCE;
                    AbnormalManager.locateDialog = (CommonDialog) null;
                    AbnormalManager abnormalManager4 = AbnormalManager.INSTANCE;
                    AbnormalManager.hasShowLocateDialog = false;
                    return;
                }
                AbnormalManager.INSTANCE.showLostDialog();
            }
        });
    }

    public final void showLostDialog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AbnormalManager$showLostDialog$1(null), 2, null);
    }

    public final void removeLocateStatusListener() {
        RobotMapManager.INSTANCE.removeLocateStatusListener(LOCATE_STATUS);
    }

    public final void addHardWareListener() {
        LocateMappingManager.INSTANCE.addHardWareListener(HARDWARE_EXCEPTION, new HardwareExceptListener.Stub() { // from class: com.pudutech.freeinstall_ui.manager.AbnormalManager$addHardWareListener$1
            @Override // com.pudutech.mirsdk.aidl.mapify.HardwareExceptListener
            public void onReceiveHardWareInfo(String p0) {
                boolean z;
                boolean z2;
                CommonDialog commonDialog;
                boolean z3;
                String str = p0;
                if (TextUtils.equals(str, AbnormalManager.TYPE_EMERGENCYBRAKE)) {
                    Pdlog.m3273d(AbnormalManager.TAG, "onReceiveHardWareInfo----- " + p0);
                    AbnormalManager abnormalManager = AbnormalManager.INSTANCE;
                    z3 = AbnormalManager.hasShowStopExceptionDialog;
                    if (z3) {
                        return;
                    }
                    AbnormalManager.INSTANCE.showStopDialog();
                    ReportError reportError = new ReportError();
                    if (p0 == null) {
                        p0 = "";
                    }
                    reportError.setFreeInstallError(p0).report();
                    return;
                }
                if (TextUtils.equals(str, AbnormalManager.TYPE_CLEARERROR)) {
                    AbnormalManager abnormalManager2 = AbnormalManager.INSTANCE;
                    z2 = AbnormalManager.hasShowStopExceptionDialog;
                    if (z2) {
                        Pdlog.m3273d(AbnormalManager.TAG, "onReceiveHardWareInfo----- " + p0);
                        AbnormalManager abnormalManager3 = AbnormalManager.INSTANCE;
                        commonDialog = AbnormalManager.shutStopDialog;
                        if (commonDialog != null) {
                            commonDialog.dismiss();
                        }
                        AbnormalManager abnormalManager4 = AbnormalManager.INSTANCE;
                        AbnormalManager.shutStopDialog = (CommonDialog) null;
                        AbnormalManager abnormalManager5 = AbnormalManager.INSTANCE;
                        AbnormalManager.hasShowStopExceptionDialog = false;
                        return;
                    }
                    return;
                }
                if (TextUtils.equals(str, AbnormalManager.TYPE_WHEELERROR)) {
                    Pdlog.m3273d(AbnormalManager.TAG, "onReceiveHardWareInfo----- " + p0);
                    AbnormalManager abnormalManager6 = AbnormalManager.INSTANCE;
                    z = AbnormalManager.hasShowExceptionDialog;
                    if (z) {
                        return;
                    }
                    AbnormalManager.INSTANCE.showExceptionDialog();
                    ReportError reportError2 = new ReportError();
                    if (p0 == null) {
                        p0 = "";
                    }
                    reportError2.setFreeInstallError(p0).report();
                }
            }
        });
    }

    public final void removeHardWareListener() {
        LocateMappingManager.INSTANCE.removeHardWareListener(HARDWARE_EXCEPTION);
    }

    public final void showStopDialog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AbnormalManager$showStopDialog$1(null), 2, null);
    }

    public final void showExceptionDialog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AbnormalManager$showExceptionDialog$1(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartApp() {
        try {
            Object systemService = AppContext.INSTANCE.getContext().getSystemService("power");
            if (!(systemService instanceof PowerManager)) {
                systemService = null;
            }
            PowerManager powerManager = (PowerManager) systemService;
            if (powerManager != null) {
                powerManager.reboot("reason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

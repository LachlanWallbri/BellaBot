package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.ErrorAlertingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.ErrorUserTryDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.FixErrorDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.StutterStopMsgDialog;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* compiled from: RunningErrorHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jn\u0010\u001e\u001a\u00020\u001828\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00122\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001a2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001aJ \u0010\u001f\u001a\u00020\u00042\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\"0!j\b\u0012\u0004\u0012\u00020\"`#H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002J\u0006\u0010%\u001a\u00020\u0013J\u0010\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0004H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\u0018\u0010)\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0004H\u0002J\u0018\u0010+\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0004H\u0002J\u0018\u0010,\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0004H\u0002J\u0018\u0010-\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0004H\u0002J\u0018\u0010.\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u00102\u001a\u00020\u00182\u0006\u0010/\u001a\u000200J\u0006\u00103\u001a\u00020\u0018R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "activity", "Landroid/app/Activity;", "errorCautionDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/ErrorAlertingDialog;", "errorNotStartDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "errorNoteDialog", "errorUserTryDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/ErrorUserTryDialog;", "errorWaitingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/FixErrorDialog;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isShow", "isNeedFinish", "", "onLostLocationLostCallback", "Lkotlin/Function0;", "onTryRestart", "stutterStopMsgDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/StutterStopMsgDialog;", "bind", "getErrorString", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveError;", "Lkotlin/collections/ArrayList;", "hasErrorDialogShow", "isErrorShowing", "isPushError", "s", "showCollisionDialog", "showErrorCautionDialog", "tips", "showErrorNoteDialog", "showErrorTaskNotStartDialog", "showErrorUserTryDialog", "showErrorWaitingDialog", "errorHelper", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "showStutterStopErrorDialog", "showSuggestion", "unbind", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RunningErrorHelper {
    private final String TAG = getClass().getSimpleName();
    private Activity activity;
    private ErrorAlertingDialog errorCautionDialog;
    private ShowTipMsgDialog errorNotStartDialog;
    private ShowTipMsgDialog errorNoteDialog;
    private ErrorUserTryDialog errorUserTryDialog;
    private FixErrorDialog errorWaitingDialog;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private Function0<Unit> onLostLocationLostCallback;
    private Function0<Unit> onTryRestart;
    private StutterStopMsgDialog stutterStopMsgDialog;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MoveErrorProcess.values().length];

        static {
            $EnumSwitchMapping$0[MoveErrorProcess.NOTHING.ordinal()] = 1;
            $EnumSwitchMapping$0[MoveErrorProcess.TRY.ordinal()] = 2;
            $EnumSwitchMapping$0[MoveErrorProcess.AUTO_RESUME.ordinal()] = 3;
            $EnumSwitchMapping$0[MoveErrorProcess.RESTART_SOFTWARE.ordinal()] = 4;
            $EnumSwitchMapping$0[MoveErrorProcess.REBOOT_POWER.ordinal()] = 5;
            $EnumSwitchMapping$0[MoveErrorProcess.RE_BACK.ordinal()] = 6;
        }
    }

    public final void showSuggestion(MoveErrorHelper errorHelper) {
        LocateCase locateCase;
        Intrinsics.checkParameterIsNotNull(errorHelper, "errorHelper");
        boolean z = false;
        Pdlog.m3273d(this.TAG, "showSuggestion " + errorHelper.getCurrentErrorSuggestion() + " , error = $" + errorHelper.getCurrentErrors());
        ArrayList<MoveError> currentErrors = errorHelper.getCurrentErrors();
        if (currentErrors != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[errorHelper.getCurrentErrorSuggestion().ordinal()]) {
                case 2:
                    ArrayList<MoveError> arrayList = currentErrors;
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : arrayList) {
                        if (Intrinsics.areEqual(((MoveError) obj).getError_type(), "PoseNotInit")) {
                            arrayList2.add(obj);
                        }
                    }
                    if (!arrayList2.isEmpty() && ((locateCase = RobotMapManager.INSTANCE.getLocateCase()) == LocateCase.Laser || locateCase == LocateCase.Slamware)) {
                        Function0<Unit> function0 = this.onLostLocationLostCallback;
                        if (function0 != null) {
                            function0.invoke();
                            return;
                        }
                        return;
                    }
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        MoveError moveError = (MoveError) obj2;
                        if (Intrinsics.areEqual(moveError.getError_type(), "LostLocalization") && (Intrinsics.areEqual(moveError.getDetail(), "NoMarker") || Intrinsics.areEqual(moveError.getDetail(), "LaserLocateLose") || Intrinsics.areEqual(moveError.getDetail(), "MarkerError") || (RobotMapManager.INSTANCE.getLocateCase() != LocateCase.Marker && Intrinsics.areEqual(moveError.getDetail(), "NoInit")))) {
                            arrayList3.add(obj2);
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        Function0<Unit> function02 = this.onLostLocationLostCallback;
                        if (function02 != null) {
                            function02.invoke();
                        }
                        LightPlayManager.INSTANCE.playLostLocation();
                        return;
                    }
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj3 : arrayList) {
                        MoveError moveError2 = (MoveError) obj3;
                        if ((Intrinsics.areEqual(moveError2.getError_type(), "BusinessDefine") && Intrinsics.areEqual(moveError2.getDetail(), "CollisionSensorTrigger")) || Intrinsics.areEqual(moveError2.getDetail(), "BumpSwitchReset")) {
                            arrayList4.add(obj3);
                        }
                    }
                    if (!arrayList4.isEmpty()) {
                        showCollisionDialog();
                        LightPlayManager.INSTANCE.playError();
                        return;
                    }
                    ArrayList arrayList5 = new ArrayList();
                    for (Object obj4 : arrayList) {
                        MoveError moveError3 = (MoveError) obj4;
                        if ((Intrinsics.areEqual(moveError3.getError_type(), "WheelErrorRight") || Intrinsics.areEqual(moveError3.getError_type(), "WheelErrorLeft")) && Intrinsics.areEqual(moveError3.getDetail(), "EmergencyKeyPressed")) {
                            arrayList5.add(obj4);
                        }
                    }
                    if (!arrayList5.isEmpty()) {
                        Activity activity = this.activity;
                        if (activity != null) {
                            showStutterStopErrorDialog(activity);
                        }
                        LightPlayManager.INSTANCE.playEmergencyStop();
                        return;
                    }
                    Activity activity2 = this.activity;
                    if (activity2 != null) {
                        showErrorUserTryDialog(activity2, getErrorString(currentErrors));
                        LightPlayManager.INSTANCE.playError();
                        break;
                    }
                    break;
                case 3:
                    Activity activity3 = this.activity;
                    if (activity3 != null) {
                        showErrorWaitingDialog(activity3, errorHelper);
                        LightPlayManager.INSTANCE.playError();
                        return;
                    }
                    return;
                case 4:
                    Activity activity4 = this.activity;
                    if (activity4 != null) {
                        showErrorNoteDialog(activity4, getErrorString(currentErrors));
                        LightPlayManager.INSTANCE.playError();
                        return;
                    }
                    return;
                case 5:
                    Activity activity5 = this.activity;
                    if (activity5 != null) {
                        showErrorCautionDialog(activity5, getErrorString(currentErrors));
                        LightPlayManager.INSTANCE.playError();
                        return;
                    }
                    return;
                case 6:
                    Activity activity6 = this.activity;
                    if (activity6 != null) {
                        showErrorTaskNotStartDialog(activity6, getErrorString(currentErrors));
                        LightPlayManager.INSTANCE.playError();
                        return;
                    }
                    return;
            }
            for (MoveError moveError4 : currentErrors) {
                if (!z && (Intrinsics.areEqual(moveError4.getError_type(), "WheelErrorLeft") || Intrinsics.areEqual(moveError4.getError_type(), "WheelErrorRight"))) {
                    if (isPushError(moveError4.getDetail())) {
                        z = true;
                    }
                }
            }
            if (z) {
                VoicePlayTasks.INSTANCE.playMotorError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x032e, code lost:
    
        if (r7.equals("LostCamera") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0337, code lost:
    
        if (r7.equals("LostEncoder") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x036a, code lost:
    
        if (r7.equals("LostLidar") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0388, code lost:
    
        if (r7.equals("LostRGBD") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
    
        if (r7.equals("LostCAN") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
    
        if (r7.equals("ErrorMap") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0082, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0080, code lost:
    
        if (r7.equals("NoParam") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x015f, code lost:
    
        if (r7.equals("LostBattery") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0169, code lost:
    
        if (r7.equals("WheelErrorRight") != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01a2, code lost:
    
        if (kotlin.collections.CollectionsKt.arrayListOf("HardwareCurrentOver", "MotherCurrentOver", "MotherVoltageOver", "MotherVoltageLow", "TemperatureOver", "EncoderError", "ABZBreak", "HallError", "CurrentZeroDriftError", "EepromError", "MotorTempOver", "MosTempOver", "OutLosePhase", "TaskLoadOver", "MosSoftStartError").contains(r6.getDetail()) == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01a4, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_21);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01ba, code lost:
    
        if (isPushError(r6.getDetail()) == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01bc, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01d4, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.getDetail(), "EmergencyKeyError") == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01d6, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01ee, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.getDetail(), "CANBreak") != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01fa, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.getDetail(), "CANCmdLose") == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0038, code lost:
    
        if (r7.equals("LostIMU") != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0207, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.getDetail(), "TouchSwitchReset") == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0209, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_40);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0218, code lost:
    
        r7 = r6.getDetail();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x021e, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0172, code lost:
    
        if (r7.equals("WheelErrorLeft") != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x038a, code lost:
    
        r7 = com.pudutech.peanut.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.peanut.robot_ui.C5508R.string.pdStr11_20);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x0056. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x002d. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getErrorString(ArrayList<MoveError> list) {
        String string;
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (MoveError moveError : list) {
            String error_type = moveError.getError_type();
            switch (error_type.hashCode()) {
                case -1854713221:
                    break;
                case -1693386453:
                    if (error_type.equals("InternalError")) {
                        string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_25);
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case -1666029548:
                    break;
                case -1641363920:
                    if (error_type.equals("CanNotReach")) {
                        string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_33);
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case -1179015170:
                    if (error_type.equals("UnknownError")) {
                        string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_36);
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case -806523592:
                    break;
                case -372086903:
                    break;
                case -335940462:
                    if (error_type.equals("PoseNotInit")) {
                        string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_34);
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case 126458426:
                    if (error_type.equals("TaskCannotStart")) {
                        String detail = moveError.getDetail();
                        switch (detail.hashCode()) {
                            case 1507423:
                                if (detail.equals("1000")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.dingweichushihua);
                                    break;
                                }
                                break;
                            case 1507424:
                                if (detail.equals("1001")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.dangqianditumeiyouxh);
                                    break;
                                }
                                break;
                            case 1507425:
                                if (detail.equals("1002")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.error_cannotfind_criuse);
                                    break;
                                }
                                break;
                            case 1507426:
                                if (detail.equals("1003")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.cannot_arrive);
                                    break;
                                }
                                break;
                            case 1507427:
                                if (detail.equals("1004")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.weizhimubiao);
                                    break;
                                }
                                break;
                            case 1507428:
                                if (detail.equals("1005")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.weizhifanhuidian);
                                    break;
                                }
                                break;
                            case 1507429:
                                if (detail.equals("1006")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.no_charge_zhuang);
                                    break;
                                }
                                break;
                            case 1507430:
                                if (detail.equals("1007")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.map_cannotfind);
                                    break;
                                }
                                break;
                            case 1507431:
                                if (detail.equals("1008")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.chongdianzhuangbukeda);
                                    break;
                                }
                                break;
                        }
                        Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                        string = moveError.getDetail();
                        z = false;
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case 150563124:
                    break;
                case 378150543:
                    break;
                case 469603881:
                    break;
                case 563446203:
                    if (error_type.equals("BusinessDefine")) {
                        if (StringsKt.contains$default((CharSequence) moveError.getDetail(), (CharSequence) "CollisionSensorTrigger", false, 2, (Object) null)) {
                            string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_40);
                            break;
                        } else {
                            string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                            break;
                        }
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case 997221231:
                    if (error_type.equals("CoreNotReady")) {
                        string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_35);
                        break;
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case 1356238845:
                    if (error_type.equals("LostLocalization")) {
                        String detail2 = moveError.getDetail();
                        switch (detail2.hashCode()) {
                            case -1961665063:
                                if (detail2.equals("ErrorMove")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_31);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case -1957098863:
                                if (detail2.equals("NoInit")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_29);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case -1956929548:
                                if (detail2.equals("NoOdom")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_28);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case -1872366181:
                                if (detail2.equals("ParamError")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_32);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case -540560390:
                                if (detail2.equals("NoImage")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_26);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case -534437076:
                                break;
                            case 526441851:
                                if (detail2.equals("NoMarker")) {
                                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_27);
                                    break;
                                }
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                            case 1460740692:
                                break;
                            default:
                                Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                                string = moveError.getDetail();
                                break;
                        }
                    }
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
                case 2018365932:
                    break;
                case 2018372077:
                    break;
                default:
                    string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_17);
                    break;
            }
            if (z) {
                string = string + " " + moveError.getError_type();
            }
            if (!arrayList.contains(string)) {
                if (sb.length() > 0) {
                    sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                }
                arrayList.add(string);
                sb.append(string);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "s.toString()");
        return sb2;
    }

    private final boolean isPushError(String s) {
        return CollectionsKt.arrayListOf("SpeedOver", "MotorStuck", "MotorOver", "PhaseCurOver", "SpeedFlowDeviation").contains(s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorNoteDialog(Activity activity, String tips) {
        FixErrorDialog fixErrorDialog;
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showErrorNoteDialog failed , ac is finish");
            return;
        }
        FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
        if (fixErrorDialog2 != null && fixErrorDialog2.isShowing() && (fixErrorDialog = this.errorWaitingDialog) != null) {
            fixErrorDialog.dismiss();
        }
        Pdlog.m3273d(this.TAG, "showErrorNoteDialog " + tips);
        if (this.errorNoteDialog == null) {
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            this.errorNoteDialog = new ShowTipMsgDialog(activity);
        }
        ShowTipMsgDialog showTipMsgDialog = this.errorNoteDialog;
        if (showTipMsgDialog == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog2 = this.errorNoteDialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        String string = activity.getString(C5508R.string.pdStr5_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "activity!!.getString(R.string.pdStr5_1)");
        showTipMsgDialog2.setTitle(string);
        ShowTipMsgDialog showTipMsgDialog3 = this.errorNoteDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorNoteDialog$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Function2 function2;
                function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                if (function2 != null) {
                }
            }
        });
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, false);
        }
        ShowTipMsgDialog showTipMsgDialog4 = this.errorNoteDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    private final void showErrorTaskNotStartDialog(Activity activity, String tips) {
        ShowTipMsgDialog showTipMsgDialog;
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "errorNotStartDialog failed , ac is finish");
            return;
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.errorNotStartDialog;
        if (showTipMsgDialog2 != null && showTipMsgDialog2.isShowing() && (showTipMsgDialog = this.errorNotStartDialog) != null) {
            showTipMsgDialog.dismiss();
        }
        Pdlog.m3273d(this.TAG, "errorNotStartDialog " + tips);
        if (this.errorNotStartDialog == null) {
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            this.errorNotStartDialog = new ShowTipMsgDialog(activity);
        }
        ShowTipMsgDialog showTipMsgDialog3 = this.errorNotStartDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog4 = this.errorNotStartDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        String string = activity.getString(C5508R.string.pdStr5_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "activity!!.getString(R.string.pdStr5_1)");
        showTipMsgDialog4.setTitle(string);
        ShowTipMsgDialog showTipMsgDialog5 = this.errorNotStartDialog;
        if (showTipMsgDialog5 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog5.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorTaskNotStartDialog$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Function2 function2;
                function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                if (function2 != null) {
                }
            }
        });
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, true);
        }
        ShowTipMsgDialog showTipMsgDialog6 = this.errorNotStartDialog;
        if (showTipMsgDialog6 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog6.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    private final void showErrorUserTryDialog(Activity activity, String tips) {
        FixErrorDialog fixErrorDialog;
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showErrorUserTryDialog failed , ac is finish");
            return;
        }
        FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
        if (fixErrorDialog2 != null && fixErrorDialog2.isShowing() && (fixErrorDialog = this.errorWaitingDialog) != null) {
            fixErrorDialog.dismiss();
        }
        Pdlog.m3273d(this.TAG, "showErrorUserTryDialog " + tips);
        if (this.errorUserTryDialog == null) {
            this.errorUserTryDialog = new ErrorUserTryDialog(activity);
        }
        ErrorUserTryDialog errorUserTryDialog = this.errorUserTryDialog;
        if (errorUserTryDialog != null) {
            errorUserTryDialog.setContent(tips);
        }
        ErrorUserTryDialog errorUserTryDialog2 = this.errorUserTryDialog;
        if (errorUserTryDialog2 != null) {
            errorUserTryDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Function2 function2;
                    function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                    if (function2 != null) {
                    }
                }
            });
        }
        ErrorUserTryDialog errorUserTryDialog3 = this.errorUserTryDialog;
        if (errorUserTryDialog3 != null) {
            errorUserTryDialog3.setOnBtnCancelClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ErrorUserTryDialog errorUserTryDialog4;
                    errorUserTryDialog4 = RunningErrorHelper.this.errorUserTryDialog;
                    if (errorUserTryDialog4 != null) {
                        errorUserTryDialog4.dismiss();
                    }
                }
            });
        }
        ErrorUserTryDialog errorUserTryDialog4 = this.errorUserTryDialog;
        if (errorUserTryDialog4 != null) {
            errorUserTryDialog4.setOnBtnTryClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ErrorUserTryDialog errorUserTryDialog5;
                    Function0 function0;
                    errorUserTryDialog5 = RunningErrorHelper.this.errorUserTryDialog;
                    if (errorUserTryDialog5 != null) {
                        errorUserTryDialog5.dismiss();
                    }
                    function0 = RunningErrorHelper.this.onTryRestart;
                    if (function0 != null) {
                    }
                }
            });
        }
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, false);
        }
        ErrorUserTryDialog errorUserTryDialog5 = this.errorUserTryDialog;
        if (errorUserTryDialog5 != null) {
            errorUserTryDialog5.show();
        }
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    private final void showErrorCautionDialog(Activity activity, String tips) {
        FixErrorDialog fixErrorDialog;
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showErrorCautionDialog failed , ac is finish");
            return;
        }
        FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
        if (fixErrorDialog2 != null && fixErrorDialog2.isShowing() && (fixErrorDialog = this.errorWaitingDialog) != null) {
            fixErrorDialog.dismiss();
        }
        Pdlog.m3273d(this.TAG, "showErrorCautionDialog " + tips);
        if (this.errorCautionDialog == null) {
            this.errorCautionDialog = new ErrorAlertingDialog(activity);
        }
        ErrorAlertingDialog errorAlertingDialog = this.errorCautionDialog;
        if (errorAlertingDialog != null) {
            errorAlertingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorCautionDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Function2 function2;
                    function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                    if (function2 != null) {
                    }
                }
            });
        }
        ErrorAlertingDialog errorAlertingDialog2 = this.errorCautionDialog;
        if (errorAlertingDialog2 != null) {
            errorAlertingDialog2.setContent(tips);
        }
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, false);
        }
        ErrorAlertingDialog errorAlertingDialog3 = this.errorCautionDialog;
        if (errorAlertingDialog3 == null) {
            Intrinsics.throwNpe();
        }
        errorAlertingDialog3.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    private final void showErrorWaitingDialog(final Activity activity, final MoveErrorHelper errorHelper) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showErrorWaitingDialog failed , ac is finish");
            return;
        }
        Pdlog.m3273d(this.TAG, "showErrorWaitingDialog");
        if (this.errorWaitingDialog == null) {
            this.errorWaitingDialog = new FixErrorDialog(activity);
        }
        FixErrorDialog fixErrorDialog = this.errorWaitingDialog;
        if (fixErrorDialog != null) {
            fixErrorDialog.setOnCancelClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorWaitingDialog$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FixErrorDialog fixErrorDialog2;
                    errorHelper.cancelAutoFix();
                    fixErrorDialog2 = RunningErrorHelper.this.errorWaitingDialog;
                    if (fixErrorDialog2 != null) {
                        fixErrorDialog2.dismiss();
                    }
                }
            });
        }
        FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
        if (fixErrorDialog2 != null) {
            fixErrorDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorWaitingDialog$2
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                }
            });
        }
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, false);
        }
        FixErrorDialog fixErrorDialog3 = this.errorWaitingDialog;
        if (fixErrorDialog3 == null) {
            Intrinsics.throwNpe();
        }
        fixErrorDialog3.show();
        errorHelper.startAutoFix(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showErrorWaitingDialog$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String errorString;
                Function0 function0;
                if (z) {
                    ReportHelper.INSTANCE.reportErrorProcess(errorHelper, ReportHelper.INSTANCE.getROBOT_RESUME());
                    function0 = RunningErrorHelper.this.onTryRestart;
                    if (function0 != null) {
                        return;
                    }
                    return;
                }
                RunningErrorHelper runningErrorHelper = RunningErrorHelper.this;
                Activity activity2 = activity;
                ArrayList<MoveError> currentErrors = errorHelper.getCurrentErrors();
                if (currentErrors == null) {
                    Intrinsics.throwNpe();
                }
                errorString = runningErrorHelper.getErrorString(currentErrors);
                runningErrorHelper.showErrorNoteDialog(activity2, errorString);
                LightPlayManager.INSTANCE.playError();
            }
        });
    }

    private final void showStutterStopErrorDialog(Activity activity) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showStutterStopErrorDialog failed , ac is finish");
            return;
        }
        Pdlog.m3273d(this.TAG, "showStutterStopErrorDialog");
        if (this.stutterStopMsgDialog == null) {
            this.stutterStopMsgDialog = new StutterStopMsgDialog(activity);
        }
        StutterStopMsgDialog stutterStopMsgDialog = this.stutterStopMsgDialog;
        if (stutterStopMsgDialog != null) {
            stutterStopMsgDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.RunningErrorHelper$showStutterStopErrorDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Function2 function2;
                    function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                    if (function2 != null) {
                    }
                }
            });
        }
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, false);
        }
        StutterStopMsgDialog stutterStopMsgDialog2 = this.stutterStopMsgDialog;
        if (stutterStopMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        stutterStopMsgDialog2.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void bind$default(RunningErrorHelper runningErrorHelper, Function2 function2, Function0 function0, Activity activity, Function0 function02, int i, Object obj) {
        if ((i & 4) != 0) {
            activity = (Activity) null;
        }
        if ((i & 8) != 0) {
            function02 = (Function0) null;
        }
        runningErrorHelper.bind(function2, function0, activity, function02);
    }

    public final void bind(Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus, Function0<Unit> onLostLocationLostCallback, Activity activity, Function0<Unit> onTryRestart) {
        Pdlog.m3273d(this.TAG, "bind");
        this.onErrorDialogShowStatus = onErrorDialogShowStatus;
        this.onLostLocationLostCallback = onLostLocationLostCallback;
        this.onTryRestart = onTryRestart;
        this.activity = activity;
    }

    public final void unbind() {
        Pdlog.m3273d(this.TAG, "unbind");
        this.onErrorDialogShowStatus = (Function2) null;
        Function0<Unit> function0 = (Function0) null;
        this.onLostLocationLostCallback = function0;
        this.activity = (Activity) null;
        this.onTryRestart = function0;
    }

    private final void showCollisionDialog() {
        Activity activity;
        if (hasErrorDialogShow() || (activity = this.activity) == null) {
            return;
        }
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr11_40);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.get…ring(R.string.pdStr11_40)");
        showErrorNoteDialog(activity, string);
    }

    private final boolean hasErrorDialogShow() {
        ShowTipMsgDialog showTipMsgDialog = this.errorNoteDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            return true;
        }
        ErrorAlertingDialog errorAlertingDialog = this.errorCautionDialog;
        if (errorAlertingDialog != null && errorAlertingDialog.isShowing()) {
            return true;
        }
        FixErrorDialog fixErrorDialog = this.errorWaitingDialog;
        if (fixErrorDialog != null && fixErrorDialog.isShowing()) {
            return true;
        }
        ErrorUserTryDialog errorUserTryDialog = this.errorUserTryDialog;
        if (errorUserTryDialog != null && errorUserTryDialog.isShowing()) {
            return true;
        }
        StutterStopMsgDialog stutterStopMsgDialog = this.stutterStopMsgDialog;
        return stutterStopMsgDialog != null && stutterStopMsgDialog.isShowing();
    }

    public final boolean isErrorShowing() {
        return hasErrorDialogShow();
    }
}

package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.monitor_task.MonitorContract;
import com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter;
import com.pudutech.bumblebee.presenter.monitor_task.StrConstant;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.DropCheckActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ErrorAlertingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ErrorUserTryDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FixErrorDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.StutterStopMsgDialog;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.ErrorKt;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: RunningErrorHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u008e\u0001\u0010'\u001a\u00020\u001a28\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001c2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0016J \u0010,\u001a\u00020\u00042\u0016\u0010-\u001a\u0012\u0012\u0004\u0012\u00020/0.j\b\u0012\u0004\u0012\u00020/`0H\u0002J\b\u00101\u001a\u00020\u001dH\u0002J\u0006\u00102\u001a\u00020\u001dJ\u0010\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u0004H\u0002J\b\u00105\u001a\u00020\u001aH\u0002J\u0018\u00106\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0004H\u0002J\u0018\u00108\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0004H\u0002J\u0018\u00109\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J(\u0010<\u001a\u00020\u001a2\u0006\u00104\u001a\u00020=2\u0016\u0010-\u001a\u0012\u0012\u0004\u0012\u00020/0.j\b\u0012\u0004\u0012\u00020/`0H\u0016J\u0006\u0010>\u001a\u00020\u001aR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "activity", "Landroid/app/Activity;", "errorCautionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ErrorAlertingDialog;", "errorNoteDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "errorUserTryDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ErrorUserTryDialog;", "errorWaitingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FixErrorDialog;", "monitorPresenter", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter;", "getMonitorPresenter", "()Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter;", "monitorPresenter$delegate", "Lkotlin/Lazy;", "motorErrorVoice", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "onErrorClearCallback", "Lkotlin/Function0;", "", "onErrorDialogShowStatus", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isShow", "isEmergencyStop", "onFallDropCallback", "onLostLocationLostCallback", "onTryRestart", "stutterStopMsgDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/StutterStopMsgDialog;", "bind", "onFallDropCallBack", "collectError", "errors", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "getErrorString", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "Lkotlin/collections/ArrayList;", "hasErrorDialogShow", "isErrorShowing", "isPushError", "s", "showCollisionDialog", "showErrorCautionDialog", "tips", "showErrorNoteDialog", "showErrorUserTryDialog", "showErrorWaitingDialog", "showStutterStopErrorDialog", "showSuggestion", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$Suggestion;", "unbind", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RunningErrorHelper implements MonitorContract.ViewInterface {
    private Activity activity;
    private ErrorAlertingDialog errorCautionDialog;
    private ShowTipMsgDialog errorNoteDialog;
    private ErrorUserTryDialog errorUserTryDialog;
    private FixErrorDialog errorWaitingDialog;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private Function0<Unit> onFallDropCallback;
    private Function0<Unit> onLostLocationLostCallback;
    private Function0<Unit> onTryRestart;
    private StutterStopMsgDialog stutterStopMsgDialog;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: monitorPresenter$delegate, reason: from kotlin metadata */
    private final Lazy monitorPresenter = LazyKt.lazy(new Function0<MonitorPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$monitorPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MonitorPresenter invoke() {
            MonitorPresenter monitorPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MonitorPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MonitorPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                monitorPresenter = new MonitorPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(MonitorPresenter.class).toString(), monitorPresenter);
            } else {
                if (!(basePresenterInterface instanceof MonitorPresenter)) {
                    basePresenterInterface = null;
                }
                monitorPresenter = (MonitorPresenter) basePresenterInterface;
            }
            if (monitorPresenter != null) {
                return monitorPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter");
        }
    });
    private final VoiceTask motorErrorVoice = new VoiceTask(-1, VoiceItem.voice22_1);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MonitorContract.Suggestion.values().length];

        static {
            $EnumSwitchMapping$0[MonitorContract.Suggestion.USER_TRY.ordinal()] = 1;
            $EnumSwitchMapping$0[MonitorContract.Suggestion.WAIT.ordinal()] = 2;
            $EnumSwitchMapping$0[MonitorContract.Suggestion.REBOOT.ordinal()] = 3;
            $EnumSwitchMapping$0[MonitorContract.Suggestion.RESTART.ordinal()] = 4;
            $EnumSwitchMapping$0[MonitorContract.Suggestion.NO_ERROR_LEFT.ordinal()] = 5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MonitorPresenter getMonitorPresenter() {
        return (MonitorPresenter) this.monitorPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.ViewInterface
    public void showSuggestion(MonitorContract.Suggestion s, ArrayList<Error> list) {
        LocateCase locateCase;
        Intrinsics.checkParameterIsNotNull(s, "s");
        Intrinsics.checkParameterIsNotNull(list, "list");
        boolean z = false;
        Pdlog.m3273d(this.TAG, "showSuggestion " + s + " , error = " + list);
        if (s != MonitorContract.Suggestion.NO_ERROR_LEFT) {
            Peripherals.INSTANCE.getFunctionButton().setMute(true);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[s.ordinal()];
        if (i == 1) {
            ArrayList<Error> arrayList = list;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (Intrinsics.areEqual(((Error) obj).error_type, "PoseNotInit")) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty() && ((locateCase = CheckLocationHelper.INSTANCE.getLocateCase()) == LocateCase.Laser || locateCase == LocateCase.Slamware)) {
                Function0<Unit> function0 = this.onLostLocationLostCallback;
                if (function0 != null) {
                    function0.invoke();
                    return;
                }
                return;
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList) {
                Error error = (Error) obj2;
                if (Intrinsics.areEqual(error.error_type, "LostLocalization") && (Intrinsics.areEqual(error.detail, "NoMarker") || Intrinsics.areEqual(error.detail, "LaserLocateLose") || Intrinsics.areEqual(error.detail, "MarkerError") || Intrinsics.areEqual(error.detail, "NoInit"))) {
                    arrayList3.add(obj2);
                }
            }
            if (!arrayList3.isEmpty()) {
                Function0<Unit> function02 = this.onLostLocationLostCallback;
                if (function02 != null) {
                    function02.invoke();
                    return;
                }
                return;
            }
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : arrayList) {
                Error error2 = (Error) obj3;
                if ((Intrinsics.areEqual(error2.error_type, "BusinessDefine") && Intrinsics.areEqual(error2.detail, "CollisionSensorTrigger")) || Intrinsics.areEqual(error2.detail, "BumpSwitchReset")) {
                    arrayList4.add(obj3);
                }
            }
            if (!arrayList4.isEmpty()) {
                showCollisionDialog();
                return;
            }
            ArrayList arrayList5 = new ArrayList();
            for (Object obj4 : arrayList) {
                Error error3 = (Error) obj4;
                if ((Intrinsics.areEqual(error3.error_type, "WheelErrorRight") || Intrinsics.areEqual(error3.error_type, "WheelErrorLeft")) && Intrinsics.areEqual(error3.detail, "EmergencyKeyPressed")) {
                    arrayList5.add(obj4);
                }
            }
            if (!arrayList5.isEmpty()) {
                Activity activity = this.activity;
                if (activity != null) {
                    showStutterStopErrorDialog(activity);
                    return;
                }
                return;
            }
            ArrayList arrayList6 = new ArrayList();
            for (Object obj5 : arrayList) {
                if (Intrinsics.areEqual(((Error) obj5).error_type, StrConstant.FallDropOccur)) {
                    arrayList6.add(obj5);
                }
            }
            ArrayList arrayList7 = arrayList6;
            if (!arrayList7.isEmpty()) {
                Activity activity2 = this.activity;
                if (activity2 != null) {
                    AnkoInternals.internalStartActivity(activity2, DropCheckActivity.class, new Pair[0]);
                }
                Function0<Unit> function03 = this.onFallDropCallback;
                if (function03 != null) {
                    function03.invoke();
                }
                Pdlog.m3273d(this.TAG, "fallDrop--", arrayList7.toString());
                return;
            }
            Activity activity3 = this.activity;
            if (activity3 != null) {
                showErrorUserTryDialog(activity3, getErrorString(list));
            }
        } else {
            if (i == 2) {
                Activity activity4 = this.activity;
                if (activity4 != null) {
                    showErrorWaitingDialog(activity4);
                    return;
                }
                return;
            }
            if (i == 3) {
                Activity activity5 = this.activity;
                if (activity5 != null) {
                    showErrorCautionDialog(activity5, getErrorString(list));
                }
            } else if (i == 4) {
                Activity activity6 = this.activity;
                if (activity6 != null) {
                    showErrorNoteDialog(activity6, getErrorString(list));
                }
            } else if (i == 5) {
                Function0<Unit> function04 = this.onErrorClearCallback;
                if (function04 != null) {
                    function04.invoke();
                }
                FixErrorDialog fixErrorDialog = this.errorWaitingDialog;
                if (fixErrorDialog == null || !fixErrorDialog.isShowing()) {
                    return;
                }
                FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
                if (fixErrorDialog2 != null) {
                    fixErrorDialog2.dismiss();
                }
                Function0<Unit> function05 = this.onTryRestart;
                if (function05 != null) {
                    function05.invoke();
                }
                Pdlog.m3273d(this.TAG, "NO_ERROR_LEFT onTryRestart");
                return;
            }
        }
        for (Error error4 : list) {
            if (!z && (Intrinsics.areEqual(error4.error_type, "WheelErrorLeft") || Intrinsics.areEqual(error4.error_type, "WheelErrorRight"))) {
                String str = error4.detail;
                Intrinsics.checkExpressionValueIsNotNull(str, "it.detail");
                if (isPushError(str)) {
                    z = true;
                }
            }
        }
        if (z) {
            VoicePlayer.INSTANCE.play(this.motorErrorVoice);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.ViewInterface
    public void collectError(Errors errors) {
        Intrinsics.checkParameterIsNotNull(errors, "errors");
        ArrayList<Error> arrayList = errors.list;
        if (arrayList != null) {
            for (Error error : arrayList) {
                TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
                String str = error.error_type;
                Intrinsics.checkExpressionValueIsNotNull(str, "it.error_type");
                String str2 = error.detail;
                Intrinsics.checkExpressionValueIsNotNull(str2, "it.detail");
                String str3 = error.level;
                Intrinsics.checkExpressionValueIsNotNull(str3, "it.level");
                long currentTimeMillis = System.currentTimeMillis();
                String str4 = error.description;
                Intrinsics.checkExpressionValueIsNotNull(str4, "it.description");
                ErrorKt.onError(trackingReportManager, str, str2, str3, currentTimeMillis, str4);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0278, code lost:
    
        if (r8.equals("LostCamera") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0281, code lost:
    
        if (r8.equals("LostEncoder") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x02d4, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02b4, code lost:
    
        if (r8.equals("LostLidar") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02d2, code lost:
    
        if (r8.equals("LostRGBD") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
    
        if (r8.equals("LostCAN") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a7, code lost:
    
        if (r8.equals("MapError") != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0113, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b0, code lost:
    
        if (r8.equals("NoParam") != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e7, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e5, code lost:
    
        if (r8.equals("ParamError") != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0111, code lost:
    
        if (r8.equals("NoInit") != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0199, code lost:
    
        if (r8.equals("LostBattery") != false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a3, code lost:
    
        if (r8.equals("WheelErrorRight") != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01da, code lost:
    
        if (kotlin.collections.CollectionsKt.arrayListOf("HardwareCurrentOver", "MotherCurrentOver", "MotherVoltageOver", "MotherVoltageLow", "TemperatureOver", "EncoderError", "ABZBreak", "HallError", "CurrentZeroDriftError", "EepromError", "MotorTempOver", "MosTempOver", "OutLosePhase", "TaskLoadOver", "MosSoftStartError").contains(r6.detail) == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01dc, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_21);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01ea, code lost:
    
        r8 = r6.detail;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, "it.detail");
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01f3, code lost:
    
        if (isPushError(r8) == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01f5, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x020b, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.detail, "EmergencyKeyError") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x020d, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0223, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.detail, "CANBreak") != false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x022d, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.detail, "CANCmdLose") == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0238, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.detail, "TouchSwitchReset") == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x023a, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_40);
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x024a, code lost:
    
        r8 = r6.detail;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x024e, code lost:
    
        r8 = com.pudutech.bumblebee.robot_ui.RobotContext.INSTANCE.getContext().getString(com.pudutech.bumblebee.robot_ui.C4188R.string.pdStr11_24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01ac, code lost:
    
        if (r8.equals("WheelErrorLeft") != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003a, code lost:
    
        if (r8.equals("LostIMU") != false) goto L118;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x005a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x030b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0016 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getErrorString(ArrayList<Error> list) {
        String string;
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        boolean z = true;
        while (true) {
            if (!it.hasNext()) {
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "s.toString()");
                Pdlog.m3273d(this.TAG, "getErrorString = " + sb2);
                return sb2;
            }
            Error error = (Error) it.next();
            String str = error.error_type;
            if (str != null) {
                switch (str.hashCode()) {
                    case -1854713221:
                        break;
                    case -1693386453:
                        if (str.equals("InternalError")) {
                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_25);
                            break;
                        }
                        break;
                    case -1666029548:
                        break;
                    case -1641363920:
                        if (str.equals("CanNotReach")) {
                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_33);
                            break;
                        }
                        break;
                    case -1179015170:
                        if (str.equals("UnknownError")) {
                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_36);
                            break;
                        }
                        break;
                    case -806523592:
                        break;
                    case -372086903:
                        break;
                    case -335940462:
                        if (str.equals("PoseNotInit")) {
                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_34);
                            break;
                        }
                        break;
                    case 150563124:
                        break;
                    case 378150543:
                        break;
                    case 469603881:
                        break;
                    case 563446203:
                        if (str.equals("BusinessDefine")) {
                            String str2 = error.detail;
                            Intrinsics.checkExpressionValueIsNotNull(str2, "it.detail");
                            if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "CollisionSensorTrigger", false, 2, (Object) null)) {
                                string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_17);
                                break;
                            } else {
                                string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_40);
                                break;
                            }
                        }
                        break;
                    case 997221231:
                        if (str.equals("CoreNotReady")) {
                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_35);
                            break;
                        }
                        break;
                    case 1356238845:
                        if (str.equals("LostLocalization")) {
                            String str3 = error.detail;
                            if (str3 != null) {
                                switch (str3.hashCode()) {
                                    case -1961665063:
                                        if (str3.equals("ErrorMove")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_31);
                                            break;
                                        }
                                        break;
                                    case -1957098863:
                                        break;
                                    case -1956929548:
                                        if (str3.equals("NoOdom")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_28);
                                            break;
                                        }
                                        break;
                                    case -1872366181:
                                        break;
                                    case -1872305518:
                                        if (str3.equals("LaserLocateLose")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.lost_localization_laser_locate_lose);
                                            break;
                                        }
                                        break;
                                    case -540560390:
                                        if (str3.equals("NoImage")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_26);
                                            break;
                                        }
                                        break;
                                    case -534437076:
                                        break;
                                    case 219650604:
                                        break;
                                    case 526441851:
                                        if (str3.equals("NoMarker")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_27);
                                            break;
                                        }
                                        break;
                                    case 1460740692:
                                        if (str3.equals("ErrorMap")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_30);
                                            break;
                                        }
                                        break;
                                    case 1872005582:
                                        if (str3.equals("MarkerError")) {
                                            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.abnormal_signs);
                                            break;
                                        }
                                        break;
                                }
                            }
                            Pdlog.m3274e(this.TAG, "has undefined LostLocalization error");
                            string = error.detail;
                            break;
                        }
                        break;
                    case 2018365932:
                        break;
                    case 2018372077:
                        break;
                }
                if (z) {
                    string = string + " " + error.error_type;
                }
                if (!arrayList.contains(string)) {
                    if (sb.length() > 0) {
                        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                    }
                    arrayList.add(string);
                    sb.append(string);
                }
            }
            string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_17);
            if (z) {
            }
            if (!arrayList.contains(string)) {
            }
        }
    }

    private final boolean isPushError(String s) {
        return CollectionsKt.arrayListOf("SpeedOver", "MotorStuck", "MotorOver", "PhaseCurOver", "SpeedFlowDeviation").contains(s);
    }

    private final void showErrorNoteDialog(Activity activity, String tips) {
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
        String string = activity.getString(C4188R.string.pdStr5_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "activity!!.getString(R.string.pdStr5_1)");
        showTipMsgDialog2.setTitle(string);
        ShowTipMsgDialog showTipMsgDialog3 = this.errorNoteDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorNoteDialog$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Function2 function2;
                MonitorPresenter monitorPresenter;
                function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                if (function2 != null) {
                }
                monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                monitorPresenter.cancelThisTime();
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
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.error_tips_1);
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
            errorUserTryDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$1
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
            errorUserTryDialog3.setOnBtnCancelClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$2
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
                    MonitorPresenter monitorPresenter;
                    errorUserTryDialog4 = RunningErrorHelper.this.errorUserTryDialog;
                    if (errorUserTryDialog4 != null) {
                        errorUserTryDialog4.dismiss();
                    }
                    monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                    monitorPresenter.cancelThisTime();
                }
            });
        }
        ErrorUserTryDialog errorUserTryDialog4 = this.errorUserTryDialog;
        if (errorUserTryDialog4 != null) {
            errorUserTryDialog4.setOnBtnTryClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorUserTryDialog$3
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
                    MonitorPresenter monitorPresenter;
                    Function0 function0;
                    errorUserTryDialog5 = RunningErrorHelper.this.errorUserTryDialog;
                    if (errorUserTryDialog5 != null) {
                        errorUserTryDialog5.dismiss();
                    }
                    monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                    monitorPresenter.cancelThisTime();
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
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.error_tips_1);
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
            errorAlertingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorCautionDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Function2 function2;
                    MonitorPresenter monitorPresenter;
                    function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                    if (function2 != null) {
                    }
                    monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                    monitorPresenter.cancelThisTime();
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
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.error_tips_1);
    }

    private final void showErrorWaitingDialog(Activity activity) {
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
            fixErrorDialog.setOnCancelClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorWaitingDialog$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FixErrorDialog fixErrorDialog2;
                    MonitorPresenter monitorPresenter;
                    fixErrorDialog2 = RunningErrorHelper.this.errorWaitingDialog;
                    if (fixErrorDialog2 != null) {
                        fixErrorDialog2.dismiss();
                    }
                    monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                    monitorPresenter.cancelThisTime();
                }
            });
        }
        FixErrorDialog fixErrorDialog2 = this.errorWaitingDialog;
        if (fixErrorDialog2 != null) {
            fixErrorDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showErrorWaitingDialog$2
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
            stutterStopMsgDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.RunningErrorHelper$showStutterStopErrorDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Function2 function2;
                    MonitorPresenter monitorPresenter;
                    function2 = RunningErrorHelper.this.onErrorDialogShowStatus;
                    if (function2 != null) {
                    }
                    monitorPresenter = RunningErrorHelper.this.getMonitorPresenter();
                    monitorPresenter.cancelThisTime();
                }
            });
        }
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onErrorDialogShowStatus;
        if (function2 != null) {
            function2.invoke(true, true);
        }
        StutterStopMsgDialog stutterStopMsgDialog2 = this.stutterStopMsgDialog;
        if (stutterStopMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        stutterStopMsgDialog2.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.error_tips_1);
    }

    public final void bind(Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus, Function0<Unit> onLostLocationLostCallback, Function0<Unit> onErrorClearCallback, Function0<Unit> onFallDropCallBack, Activity activity, Function0<Unit> onTryRestart) {
        Pdlog.m3273d(this.TAG, "bind");
        this.onErrorDialogShowStatus = onErrorDialogShowStatus;
        this.onLostLocationLostCallback = onLostLocationLostCallback;
        this.onErrorClearCallback = onErrorClearCallback;
        this.onFallDropCallback = onFallDropCallBack;
        this.onTryRestart = onTryRestart;
        this.activity = activity;
        getMonitorPresenter().replaceView((MonitorContract.ViewInterface) this);
    }

    public final void unbind() {
        Pdlog.m3273d(this.TAG, "unbind");
        this.onErrorDialogShowStatus = (Function2) null;
        Function0<Unit> function0 = (Function0) null;
        this.onLostLocationLostCallback = function0;
        this.onErrorClearCallback = function0;
        this.activity = (Activity) null;
        this.onTryRestart = function0;
        getMonitorPresenter().cancelThisTime();
        getMonitorPresenter().removeView(this);
        VoicePlayer.INSTANCE.stop(this.motorErrorVoice);
    }

    private final void showCollisionDialog() {
        Activity activity;
        if (hasErrorDialogShow() || (activity = this.activity) == null) {
            return;
        }
        String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr11_40);
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

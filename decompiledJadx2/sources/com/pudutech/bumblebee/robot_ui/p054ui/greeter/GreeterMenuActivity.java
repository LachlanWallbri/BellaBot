package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SmartInputTableFragment;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.SelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.RobotGreeterTaskLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.track.task.PeripheralsTrack;
import com.pudutech.bumblebee.robot_ui.track.task.VoiceInteractionTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceLongClickListenerKt;
import com.pudutech.bumblebee.robot_ui.util.FragmentUtils;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.bumblebee.robot_ui.viewmodel.GreeterMenuVM;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.robot.module.report.track2.WakeUpType;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: GreeterMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ñ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b*\u0003\u0014'8\u0018\u0000 l2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001lB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010B\u001a\u000204H\u0002J\b\u0010C\u001a\u000204H\u0016J\u0012\u0010D\u001a\u00020\u001e2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\b\u0010G\u001a\u000204H\u0002J\b\u0010H\u001a\u000204H\u0002J\b\u0010I\u001a\u000204H\u0002J\b\u0010J\u001a\u000204H\u0002J\u0012\u0010K\u001a\u0002042\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u000204H\u0002J\b\u0010O\u001a\u000204H\u0002J\b\u0010P\u001a\u00020\u001eH\u0016J\u0010\u0010Q\u001a\u0002042\u0006\u0010R\u001a\u00020SH\u0016J\b\u0010T\u001a\u00020\u0006H\u0016J\u0010\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020\u0006H\u0002J\u0012\u0010W\u001a\u0002042\b\u0010R\u001a\u0004\u0018\u00010SH\u0014J\b\u0010X\u001a\u000204H\u0014J\b\u0010Y\u001a\u000204H\u0014J\u0010\u0010Z\u001a\u0002042\u0006\u0010[\u001a\u00020\u001eH\u0016J\b\u0010\\\u001a\u000204H\u0002J\u0010\u0010]\u001a\u0002042\u0006\u0010^\u001a\u00020\u001eH\u0002J\b\u0010_\u001a\u000204H\u0002J\u0018\u0010`\u001a\u0002042\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\u001eH\u0002J\u0010\u0010d\u001a\u0002042\u0006\u00103\u001a\u00020eH\u0016J\b\u0010f\u001a\u000204H\u0002J\b\u0010g\u001a\u000204H\u0002J\b\u0010h\u001a\u000204H\u0002J\b\u0010i\u001a\u000204H\u0002J\b\u0010j\u001a\u000204H\u0002J\b\u0010k\u001a\u000204H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0010\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u0010\u001a\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(R)\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u001e0*X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010.\u001a2\u0012\u0013\u0012\u001100¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(1\u0012\u0013\u0012\u001102¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(3\u0012\u0004\u0012\u0002040/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010@\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010A\u0012\u0004\u0012\u0002040*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006m"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/GreeterMenuVM;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "HANDLER_SHOW_FACE", "", "TAG", "", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "callSetVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallSetVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callSetVM$delegate", "Lkotlin/Lazy;", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$functionButton$1;", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "isFirstStart", "", "mAgentTestHelper", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "getMAgentTestHelper", "()Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "mAgentTestHelper$delegate", "mainHandler", "Landroid/os/Handler;", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$onHomeSettingDialogClickListener$1;", "onTaskGreeterListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "destination", "onTouchSensorPlaceListener", "Lkotlin/Function2;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "", "selectTableFragment", "Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterMenuActivity$singleBatteryListener$1;", "tipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "createObserver", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "initAiVoice", "initAllData", "initDragTips", "initTablesInput", "initView", "saveInstanceState", "Landroid/os/Bundle;", "initViews", "intoGreeterFace", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "layoutId", "onFeatureChange", "type", "onNewIntent", "onPause", "onResume", "onWindowFocusChanged", "hasFocus", "release", "setMusicBtnBg", "open", "setSelectTableFragment", "setViewVisibility", "view", "Landroid/view/View;", "show", "showIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showSettingDialog", "showTouchFace", "startGo", "startShowFaceTask", "stopShowFaceTask", "unbindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterMenuActivity extends MyBaseVmActivity<GreeterMenuVM> implements IdleMoveContract.ViewInterface {
    public static final int TYPE_FEATURE_AIVOICE = 2;
    public static final int TYPE_FEATURE_GREETER = 0;
    public static final int TYPE_TOUCH_FACE = 1;
    private HashMap _$_findViewCache;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private ISelectTableFragment selectTableFragment;
    private ShowTipMsgDialog tipDialog;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final int HANDLER_SHOW_FACE = 100;
    private final String TAG = "GreeterMenuActivity";
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    /* renamed from: mAgentTestHelper$delegate, reason: from kotlin metadata */
    private final Lazy mAgentTestHelper = LazyKt.lazy(new Function0<AgentTestHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$mAgentTestHelper$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AgentTestHelper invoke() {
            return new AgentTestHelper();
        }
    });

    /* renamed from: callSetVM$delegate, reason: from kotlin metadata */
    private final Lazy callSetVM = LazyKt.lazy(new Function0<CallSettingVM>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$callSetVM$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CallSettingVM invoke() {
            GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
            ViewModel viewModel = new ViewModelProvider(greeterMenuActivity, new ViewModelProvider.AndroidViewModelFactory(greeterMenuActivity.getApplication())).get(CallSettingVM.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
            return (CallSettingVM) ((BaseViewModel) viewModel);
        }
    });
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$onTouchSensorPlaceListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            invoke2(place, event);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(event, "event");
            PeripheralsTrack peripheralsTrack = PeripheralsTrack.INSTANCE;
            int ordinal = place.ordinal();
            int value = event.getValue();
            String simpleName = GreeterMenuActivity.this.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GreeterMenuActivity::class.java.simpleName");
            PeripheralsTrack.onTouchPeripherals$default(peripheralsTrack, ordinal, value, simpleName, null, 8, null);
        }
    };
    private final Function1<WakeupInfo, Unit> wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$wakeupListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(WakeupInfo wakeupInfo) {
            invoke2(wakeupInfo);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(WakeupInfo wakeupInfo) {
            VoiceInteractionDialog voiceInteractionDialog;
            VoiceInteractionDialog voiceInteractionDialog2;
            String str;
            VoiceInteractionDialog voiceInteractionDialog3;
            VoiceInteractionTrack.INSTANCE.onWakeup(WakeUpType.VOICE);
            voiceInteractionDialog = GreeterMenuActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog == null) {
                GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
                greeterMenuActivity.voiceInteractionDialog = new VoiceInteractionDialog(greeterMenuActivity);
                voiceInteractionDialog3 = GreeterMenuActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog3 != null) {
                    voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$wakeupListener$1.1
                        @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                        public void onDismiss() {
                            String str2;
                            str2 = GreeterMenuActivity.this.TAG;
                            Pdlog.m3273d(str2, "wakeupListener onDismiss ");
                            GreeterMenuActivity.this.onFeatureChange(0);
                        }
                    });
                }
            }
            voiceInteractionDialog2 = GreeterMenuActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                return;
            }
            GreeterMenuActivity.this.onFeatureChange(2);
            voiceInteractionDialog2.show();
            str = GreeterMenuActivity.this.TAG;
            Pdlog.m3273d(str, "wakeupListener show ");
        }
    };
    private final Function1<String, Boolean> onTaskGreeterListener = new Function1<String, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$onTaskGreeterListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(invoke2(str));
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(final String destination) {
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            GreeterMenuActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$onTaskGreeterListener$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceInteractionDialog voiceInteractionDialog;
                    voiceInteractionDialog = GreeterMenuActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
                        voiceInteractionDialog.dismiss();
                    }
                    ((RobotGreeterTaskLayout) GreeterMenuActivity.this._$_findCachedViewById(C4188R.id.greeter_task_lasyout)).setPalletData(destination);
                    GreeterMenuActivity.this.startGo();
                }
            });
            return true;
        }
    };

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$idleMovePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IdleMovePresenter invoke() {
            IdleMovePresenter idleMovePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(IdleMovePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                idleMovePresenter = new IdleMovePresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
                if (!(basePresenterInterface instanceof IdleMovePresenter)) {
                    basePresenterInterface = null;
                }
                idleMovePresenter = (IdleMovePresenter) basePresenterInterface;
            }
            if (idleMovePresenter == null) {
                Intrinsics.throwNpe();
            }
            return idleMovePresenter;
        }
    });
    private boolean isFirstStart = true;
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = GreeterMenuActivity.this.HANDLER_SHOW_FACE;
            if (i2 != i || GreeterMenuActivity.this.isDestroyed() || GreeterMenuActivity.this.isFinishing()) {
                return true;
            }
            GreeterMenuActivity.this.intoGreeterFace();
            return true;
        }
    });
    private final GreeterMenuActivity$functionButton$1 functionButton = new GreeterMenuActivity$functionButton$1(this);
    private final GreeterMenuActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new GreeterMenuActivity$onHomeSettingDialogClickListener$1(this);
    private final GreeterMenuActivity$singleBatteryListener$1 singleBatteryListener = new GreeterMenuActivity$singleBatteryListener$1(this);

    private final CallSettingVM getCallSetVM() {
        return (CallSettingVM) this.callSetVM.getValue();
    }

    private final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
    }

    private final AgentTestHelper getMAgentTestHelper() {
        return (AgentTestHelper) this.mAgentTestHelper.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity
    public boolean isBusyState() {
        Boolean value = getCallSetVM().getGreeterPointCanCallSwitchLD().getValue();
        if (value == null) {
            value = false;
        }
        return !value.booleanValue();
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_greeter_menu;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        if (getNeedReinitApp()) {
            Pdlog.m3277w(this.TAG, "need re_init app");
            return;
        }
        initViews();
        PeripheralsSceneUtil.INSTANCE.showGreeterWait();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        getMAgentTestHelper().bindLifecycle(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        ((GreeterMenuVM) getMViewModel()).getTableGroupLV().observe(this, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$createObserver$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean z = ((Number) t).intValue() > 0;
                GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
                View view_ghost_1 = greeterMenuActivity._$_findCachedViewById(C4188R.id.view_ghost_1);
                Intrinsics.checkExpressionValueIsNotNull(view_ghost_1, "view_ghost_1");
                greeterMenuActivity.setViewVisibility(view_ghost_1, !z);
            }
        });
        ((GreeterMenuVM) getMViewModel()).loadTableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewVisibility(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private final void initViews() {
        setSelectTableFragment();
        ((ImageView) _$_findCachedViewById(C4188R.id.setting_info)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                GreeterMenuActivity.this.showSettingDialog();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.btn_start)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterMenuActivity.this.startGo();
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.go_to_welcome_area_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                ShowTipMsgDialog showTipMsgDialog;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (BatteryInfoManager.INSTANCE.isCharging()) {
                    showTipMsgDialog = GreeterMenuActivity.this.tipDialog;
                    if (showTipMsgDialog != null) {
                        showTipMsgDialog.dismiss();
                    }
                    GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
                    String string = greeterMenuActivity.getString(C4188R.string.pdStr25_17);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
                    greeterMenuActivity.tipDialog = MyBaseVmActivity.showTipDialog$default(greeterMenuActivity, string, null, null, null, 14, null);
                    return;
                }
                GreeterMenuActivity.this.jumpAndFinish(new Intent(GreeterMenuActivity.this, (Class<?>) GotoWelcomeAreaActivity.class));
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.show_face_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterMenuActivity.this.intoGreeterFace();
            }
        }, 3, null));
        ((Button) _$_findCachedViewById(C4188R.id.setting_btn)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterMenuActivity.this.jumpAndFinish(new Intent(GreeterMenuActivity.this, (Class<?>) GreeterSettingActivity.class));
            }
        }, 3, null));
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        Button music_btn = (Button) _$_findCachedViewById(C4188R.id.music_btn);
        Intrinsics.checkExpressionValueIsNotNull(music_btn, "music_btn");
        VoiceLongClickListenerKt.onVoiceLongClickListener$default(music_btn, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initViews$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
                if (musicPlayerHelper.isOpenMusicSwitch()) {
                    GreeterMenuActivity.this.setMusicBtnBg(false);
                    MusicPlayerHelper musicPlayerHelper2 = MusicPlayerHelper.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper2, "MusicPlayerHelper.getInstance()");
                    musicPlayerHelper2.setOpenMusicSwitch(false);
                    return;
                }
                GreeterMenuActivity.this.setMusicBtnBg(true);
                MusicPlayerHelper musicPlayerHelper3 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper3, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper3.setOpenMusicSwitch(true);
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMusicBtnBg(boolean open) {
        Button music_btn = (Button) _$_findCachedViewById(C4188R.id.music_btn);
        Intrinsics.checkExpressionValueIsNotNull(music_btn, "music_btn");
        Sdk27PropertiesKt.setBackgroundResource(music_btn, open ? C4188R.drawable.music_btn_selector : C4188R.drawable.ic_music_close);
    }

    private final void setSelectTableFragment() {
        int tableInputType = Constans.INSTANCE.getTableInputType();
        if (tableInputType == 0 && !(this.selectTableFragment instanceof SelectTableFragment)) {
            this.selectTableFragment = new SelectTableFragment();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
            if (iSelectTableFragment == null) {
                Intrinsics.throwNpe();
            }
            FragmentUtils.replace(supportFragmentManager, iSelectTableFragment, C4188R.id.select_table_container);
            initTablesInput();
            initDragTips();
            return;
        }
        if (tableInputType != 1 || (this.selectTableFragment instanceof SmartInputTableFragment)) {
            return;
        }
        this.selectTableFragment = new SmartInputTableFragment();
        FragmentManager supportFragmentManager2 = getSupportFragmentManager();
        ISelectTableFragment iSelectTableFragment2 = this.selectTableFragment;
        if (iSelectTableFragment2 == null) {
            Intrinsics.throwNpe();
        }
        FragmentUtils.replace(supportFragmentManager2, iSelectTableFragment2, C4188R.id.select_table_container);
        initTablesInput();
    }

    private final void initTablesInput() {
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            iSelectTableFragment.setOnSelectTable(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initTablesInput$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    ((RobotGreeterTaskLayout) GreeterMenuActivity.this._$_findCachedViewById(C4188R.id.greeter_task_lasyout)).setPalletData(it);
                }
            });
        }
    }

    private final void initDragTips() {
        if (Constans.INSTANCE.getDragTip() && RobotMapManager.INSTANCE.getAllTableGroup().size() != 0) {
            View layout_drag = _$_findCachedViewById(C4188R.id.layout_drag);
            Intrinsics.checkExpressionValueIsNotNull(layout_drag, "layout_drag");
            layout_drag.setVisibility(0);
            ((TextView) _$_findCachedViewById(C4188R.id.btn_i_get)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$initDragTips$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick() {
                    CheckBox checkbox_tips = (CheckBox) GreeterMenuActivity.this._$_findCachedViewById(C4188R.id.checkbox_tips);
                    Intrinsics.checkExpressionValueIsNotNull(checkbox_tips, "checkbox_tips");
                    if (checkbox_tips.isChecked()) {
                        Constans.INSTANCE.setDragTip(false);
                    }
                    View layout_drag2 = GreeterMenuActivity.this._$_findCachedViewById(C4188R.id.layout_drag);
                    Intrinsics.checkExpressionValueIsNotNull(layout_drag2, "layout_drag");
                    layout_drag2.setVisibility(8);
                }
            });
            return;
        }
        View layout_drag2 = _$_findCachedViewById(C4188R.id.layout_drag);
        Intrinsics.checkExpressionValueIsNotNull(layout_drag2, "layout_drag");
        layout_drag2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void intoGreeterFace() {
        jumpAndFinish(new Intent(this, (Class<?>) GreeterFaceActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGo() {
        if (BatteryInfoManager.INSTANCE.isCharging()) {
            ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
            if (showTipMsgDialog != null) {
                showTipMsgDialog.dismiss();
            }
            String string = getString(C4188R.string.pdStr25_17);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
            this.tipDialog = MyBaseVmActivity.showTipDialog$default(this, string, null, null, null, 14, null);
            return;
        }
        ArrayList<String> taskList = ((RobotGreeterTaskLayout) _$_findCachedViewById(C4188R.id.greeter_task_lasyout)).getTaskList();
        if (taskList == null || taskList.size() == 0) {
            ShowTipMsgDialog showTipMsgDialog2 = this.tipDialog;
            if (showTipMsgDialog2 != null) {
                showTipMsgDialog2.dismiss();
            }
            String string2 = getString(C4188R.string.pdStr25_6);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr25_6)");
            this.tipDialog = MyBaseVmActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) GreeterActivity.class);
        intent.putStringArrayListExtra(GreeterActivity.INSTANCE.getTABLE_NAME(), taskList);
        jumpAndFinish(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
            HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
            if (homeSettingDialog2 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$showSettingDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    String str;
                    TouchSensorEventHelper touchSensorEventHelper;
                    str = GreeterMenuActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog OnDismissListener");
                    touchSensorEventHelper = GreeterMenuActivity.this.touchSensorEventHelper;
                    TouchSensorEventHelper.setCanHandle$default(touchSensorEventHelper, true, false, 2, null);
                }
            });
            HomeSettingDialog homeSettingDialog3 = this.homeSettingDialog;
            if (homeSettingDialog3 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$showSettingDialog$2
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    String str;
                    TouchSensorEventHelper touchSensorEventHelper;
                    str = GreeterMenuActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog setOnShowListener");
                    touchSensorEventHelper = GreeterMenuActivity.this.touchSensorEventHelper;
                    TouchSensorEventHelper.setCanHandle$default(touchSensorEventHelper, false, false, 2, null);
                }
            });
        }
        HomeSettingDialog homeSettingDialog4 = this.homeSettingDialog;
        if (homeSettingDialog4 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog4.show();
    }

    private final void initAllData() {
        Pdlog.m3273d(this.TAG, "initAllData ");
        initAiVoice();
        bindPresenter();
        getIdleMovePresenter().actionIDLE();
        getIdleMovePresenter().actionTimerCount(false);
        onFeatureChange(0);
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        setMusicBtnBg(musicPlayerHelper.isOpenMusicSwitch());
    }

    private final void release() {
        unbindPresenter();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initAllData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        stopShowFaceTask();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        startShowFaceTask();
        return super.dispatchTouchEvent(ev);
    }

    private final void startShowFaceTask() {
        stopShowFaceTask();
        this.mainHandler.sendEmptyMessageDelayed(this.HANDLER_SHOW_FACE, 30000L);
    }

    private final void stopShowFaceTask() {
        this.mainHandler.removeMessages(this.HANDLER_SHOW_FACE);
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter ");
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        VoiceInteractionTrack voiceInteractionTrack = VoiceInteractionTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GreeterMenuActivity::class.java.simpleName");
        VoiceInteractionTrack.onCreateTask$default(voiceInteractionTrack, simpleName, null, 2, null);
        getIdleMovePresenter().replaceView(this);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        this.singleBatteryListener.showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        Peripherals.INSTANCE.getFunctionButton().setMute(false);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        this.touchSensorEventHelper.setOnShowFaceStateListener(new Function1<TouchSensorEventHelper.State, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterMenuActivity$bindPresenter$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorEventHelper.State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorEventHelper.State it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterMenuActivity.this.onFeatureChange(1);
                if (it == TouchSensorEventHelper.State.FINISH_FACE) {
                    GreeterMenuActivity.this.onFeatureChange(0);
                }
            }
        });
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        AiVoiceTriggerHelper.INSTANCE.setOnTaskGreeterListener(this.onTaskGreeterListener);
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.setWorkMode(WorkMode.Guide);
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter ");
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(this);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
        this.touchSensorEventHelper.unBindPresent();
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        AiVoiceTriggerHelper.INSTANCE.setOnTaskGreeterListener((Function1) null);
    }

    private final void showTouchFace() {
        ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
        stopShowFaceTask();
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showIdleEvent event=" + event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type != 0) {
            if (type == 1) {
                AiVoiceManager.INSTANCE.stopAiRecording();
                showTouchFace();
                return;
            } else {
                if (type != 2) {
                    return;
                }
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                stopShowFaceTask();
                return;
            }
        }
        AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        Peripherals.INSTANCE.getFunctionButton().setMute(false);
        IdleMovePresenter idleMovePresenter = getIdleMovePresenter();
        if (idleMovePresenter == null) {
            Intrinsics.throwNpe();
        }
        idleMovePresenter.actionTimerCount(true);
        this.touchSensorEventHelper.stopCurrentAnimation();
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        startShowFaceTask();
    }
}

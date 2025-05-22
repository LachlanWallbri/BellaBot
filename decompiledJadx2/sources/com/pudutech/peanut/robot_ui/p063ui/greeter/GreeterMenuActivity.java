package com.pudutech.peanut.robot_ui.p063ui.greeter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.GreeterSettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.HomeActivity;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.WelComeDialogueActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment;
import com.pudutech.peanut.robot_ui.p063ui.fragment.SelectTableFragment;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.peanut.robot_ui.util.FragmentUtils;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.pudutech.peanut.robot_ui.viewmodel.HeyPuduWakeViewModel;
import com.pudutech.robot.module.report.task.ReportUsherTask;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: GreeterMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e*\u0001!\u0018\u0000 X2\u00020\u00012\u00020\u0002:\u0001XB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010'\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020+H\u0002J\b\u0010-\u001a\u00020+H\u0002J\b\u0010.\u001a\u00020+H\u0002J\u0012\u0010/\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\u0012\u00102\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0016J1\u00103\u001a\u00020+2\u0006\u0010%\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u00020\u00112\b\u00107\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u00108J\u0012\u00109\u001a\u00020+2\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u00020+H\u0014J\u0010\u0010=\u001a\u00020+2\u0006\u0010>\u001a\u00020\u0005H\u0002J\b\u0010?\u001a\u00020+H\u0014J\u0010\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020\u0005H\u0016J\b\u0010B\u001a\u00020+H\u0014J\b\u0010C\u001a\u00020+H\u0014J\b\u0010D\u001a\u00020+H\u0014J\u0010\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020\u0011H\u0016J\b\u0010G\u001a\u00020+H\u0002J\b\u0010H\u001a\u00020+H\u0002J\b\u0010I\u001a\u00020+H\u0002J\b\u0010J\u001a\u00020+H\u0002J\u001c\u0010K\u001a\u0004\u0018\u00010\u00172\u0006\u0010L\u001a\u00020\b2\b\b\u0002\u0010M\u001a\u00020\u0011H\u0002J\b\u0010N\u001a\u00020+H\u0002J\b\u0010O\u001a\u00020+H\u0002J\b\u0010P\u001a\u00020+H\u0002J\b\u0010Q\u001a\u00020+H\u0002J\b\u0010R\u001a\u00020+H\u0002J\b\u0010S\u001a\u00020+H\u0002J\b\u0010T\u001a\u00020+H\u0002J\b\u0010U\u001a\u00020+H\u0002J\b\u0010V\u001a\u00020+H\u0002J\b\u0010W\u001a\u00020+H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Y"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnWeComeApproachListener;", "()V", "HANDLER_SHOW_FACE", "", "TAB_SHOW", "TAG", "", "curStateType", "faceAnimationView", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoView;", "handler", "Landroid/os/Handler;", "homeSettingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "isFirstStart", "", "isNotShow", "isPlayLowPowerVoice", "isRelease", "isShowLowPowerDialog", "lowerPowerDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "mHeyPUduWakeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "getMHeyPUduWakeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "mHeyPUduWakeVm$delegate", "Lkotlin/Lazy;", "onGreeterAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onHomeSettingDialogClickListener", "com/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity$onHomeSettingDialogClickListener$1;", "selectTableFragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/ISelectTableFragment;", "state", "tipDialog", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "hideFace", "", "initAiVoice", "initAllData", "initViews", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onFeatureChange", "type", "onPause", "onPersonApproach", "p0", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "playFaceAnimation", "playGreeter", "release", "setGreeterFaceFragment", "showDialog", NotificationCompat.CATEGORY_MESSAGE, "needCloseSensorFace", "showGreeterFace", "showLowerNotify", "showSettingDialog", "showTouchFace", "startCountDownTimer", "startGo", "startShowFaceTask", "stopGreeter", "stopShowFaceTask", "unbindPresenter", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GreeterMenuActivity extends BatteryBaseActivity implements SolicitInfoManager.OnWeComeApproachListener {
    public static final int AVOID_WAKE_UP = 7;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int GREETER_FACE_SHOW = 8;
    public static final int TYPE_FEATURE_AIVOICE = 2;
    public static final int TYPE_FEATURE_DIALOG = 4;
    public static final int TYPE_FEATURE_NOMAL = 0;
    public static final int TYPE_GREETER_FACK_SHOW = 3;
    public static final int TYPE_TOUCH_FACE = 1;
    public static final int USHER_MODE = 5;
    public static final int WAKE_UP = 6;
    private static boolean wakeup;
    private HashMap _$_findViewCache;
    private int curStateType;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private boolean isPlayLowPowerVoice;
    private boolean isRelease;
    private boolean isShowLowPowerDialog;
    private ShowTipMsgDialog lowerPowerDialog;
    private ISelectTableFragment selectTableFragment;
    private int state;
    private ShowTipMsgDialog tipDialog;
    private final int HANDLER_SHOW_FACE = 100;
    private final int TAB_SHOW = 101;
    private final String TAG = "GreeterMenuActivity";
    private Handler handler = new Companion.WithoutLeakHandler(this);
    private boolean isNotShow = true;

    /* renamed from: mHeyPUduWakeVm$delegate, reason: from kotlin metadata */
    private final Lazy mHeyPUduWakeVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(HeyPuduWakeViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private boolean isFirstStart = true;
    private final GreeterMenuActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type != HomeSettingDialog.FunctionType.USHER_MODE) {
                if (intent != null) {
                    GreeterMenuActivity.this.jump(intent);
                }
            } else {
                GreeterMenuActivity.this.state = 0;
                TextView go_to_welcome_area_tv = (TextView) GreeterMenuActivity.this._$_findCachedViewById(C5508R.id.go_to_welcome_area_tv);
                Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area_tv, "go_to_welcome_area_tv");
                go_to_welcome_area_tv.setText(GreeterMenuActivity.this.getString(C5508R.string.go_linwei));
                ((ImageView) GreeterMenuActivity.this._$_findCachedViewById(C5508R.id.ivImg)).setImageResource(C5508R.drawable.ic_icon_guest_default);
            }
        }
    };
    private final SingleClickListener onGreeterAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$onGreeterAnimationViewClick$1
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
            GreeterMenuActivity.this.stopGreeter();
        }
    });

    private final HeyPuduWakeViewModel getMHeyPUduWakeVm() {
        return (HeyPuduWakeViewModel) this.mHeyPUduWakeVm.getValue();
    }

    private final void stopShowFaceTask() {
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX WARN: Type inference failed for: r0v6, types: [com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$onHomeSettingDialogClickListener$1] */
    public GreeterMenuActivity() {
    }

    private final void playFaceAnimation() {
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getGreeterFace());
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 1) {
            if (this.isShowLowPowerDialog) {
                return;
            }
            showLowerNotify();
        } else if (state == 2 && i != null) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
        }
    }

    private final void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        ShowTipMsgDialog showTipMsgDialog = this.lowerPowerDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            this.isShowLowPowerDialog = true;
            return;
        }
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        this.lowerPowerDialog = showDialog$default(this, string, false, 2, null);
    }

    static /* synthetic */ ShowTipMsgDialog showDialog$default(GreeterMenuActivity greeterMenuActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return greeterMenuActivity.showDialog(str, z);
    }

    private final ShowTipMsgDialog showDialog(String msg, final boolean needCloseSensorFace) {
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                GreeterMenuActivity.this.onFeatureChange(4);
                boolean z = needCloseSensorFace;
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$showDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                GreeterMenuActivity.this.onFeatureChange(0);
            }
        }, null, 8, null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_greeter_menu);
        Pdlog.m3273d(this.TAG, "onCreate");
        if (getNeedReinitApp()) {
            Pdlog.m3277w(this.TAG, "need re_init app");
            return;
        }
        this.state = getIntent().getIntExtra("state", 0);
        if (this.state == 0) {
            ((ImageView) _$_findCachedViewById(C5508R.id.ivImg)).setImageResource(C5508R.drawable.ic_icon_guest_default);
            TextView go_to_welcome_area_tv = (TextView) _$_findCachedViewById(C5508R.id.go_to_welcome_area_tv);
            Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area_tv, "go_to_welcome_area_tv");
            go_to_welcome_area_tv.setText(getString(C5508R.string.go_linwei));
        } else {
            ((ImageView) _$_findCachedViewById(C5508R.id.ivImg)).setImageResource(C5508R.drawable.ic_icon_backtoguest);
            TextView go_to_welcome_area_tv2 = (TextView) _$_findCachedViewById(C5508R.id.go_to_welcome_area_tv);
            Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area_tv2, "go_to_welcome_area_tv");
            go_to_welcome_area_tv2.setText(getString(C5508R.string.back_solicit));
        }
        if (RobotMapManager.INSTANCE.haveMapGroup()) {
            View _$_findCachedViewById = _$_findCachedViewById(C5508R.id.viewLine);
            if (_$_findCachedViewById != null) {
                _$_findCachedViewById.setVisibility(0);
            }
            TextView textView = (TextView) _$_findCachedViewById(C5508R.id.tvTable);
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.info_title_tv);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            View _$_findCachedViewById2 = _$_findCachedViewById(C5508R.id.line);
            if (_$_findCachedViewById2 != null) {
                _$_findCachedViewById2.setVisibility(8);
            }
        } else {
            View _$_findCachedViewById3 = _$_findCachedViewById(C5508R.id.viewLine);
            if (_$_findCachedViewById3 != null) {
                _$_findCachedViewById3.setVisibility(8);
            }
        }
        SolicitInfoManager.INSTANCE.addPersonApproachListener(this);
        SolicitInfoManager.INSTANCE.startWeCome();
        initViews();
        VoicePlayTasks.INSTANCE.playGreeter();
        LightPlayManager.INSTANCE.playNormalStatus();
        HeyPuduWakeViewModel mHeyPUduWakeVm = getMHeyPUduWakeVm();
        (mHeyPUduWakeVm != null ? mHeyPUduWakeVm.getWakeUpAngle() : null).observe(this, new Observer<Double>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$onCreate$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Double it) {
                if (!Intrinsics.areEqual(it, 0.0d)) {
                    RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    robotMoveManager.rotate(it.doubleValue());
                }
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        initAllData();
        setGreeterFaceFragment();
    }

    private final void initViews() {
        ImageView ivBack = (ImageView) _$_findCachedViewById(C5508R.id.ivBack);
        Intrinsics.checkExpressionValueIsNotNull(ivBack, "ivBack");
        ViewExtKt.onSingleClick(ivBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$1
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
                int i;
                Intent intent;
                Intrinsics.checkParameterIsNotNull(it, "it");
                i = GreeterMenuActivity.this.state;
                if (i == 0) {
                    intent = new Intent(GreeterMenuActivity.this, (Class<?>) HomeActivity.class);
                } else {
                    intent = new Intent(GreeterMenuActivity.this, (Class<?>) WelComeDialogueActivity.class);
                }
                GreeterMenuActivity.this.jumpAndFinish(intent);
            }
        });
        this.selectTableFragment = new SelectTableFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment == null) {
            Intrinsics.throwNpe();
        }
        FragmentUtils.replace(supportFragmentManager, iSelectTableFragment, C5508R.id.select_table_container);
        ISelectTableFragment iSelectTableFragment2 = this.selectTableFragment;
        if (iSelectTableFragment2 != null) {
            iSelectTableFragment2.setOnSelectTable(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$2
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
                    AppCompatTextView info_table_tv = (AppCompatTextView) GreeterMenuActivity.this._$_findCachedViewById(C5508R.id.info_table_tv);
                    Intrinsics.checkExpressionValueIsNotNull(info_table_tv, "info_table_tv");
                    info_table_tv.setTextSize(GreeterMenuActivity.this.getResources().getDimension(C5508R.dimen.greeter_info_table_tv_s));
                    AppCompatTextView info_table_tv2 = (AppCompatTextView) GreeterMenuActivity.this._$_findCachedViewById(C5508R.id.info_table_tv);
                    Intrinsics.checkExpressionValueIsNotNull(info_table_tv2, "info_table_tv");
                    info_table_tv2.setText(it);
                    UiUtils.adjustTvTextSize((AppCompatTextView) GreeterMenuActivity.this._$_findCachedViewById(C5508R.id.info_table_tv), (int) (GreeterMenuActivity.this.getResources().getDimension(C5508R.dimen.greeter_menu_info_w) - (GreeterMenuActivity.this.getResources().getDimension(C5508R.dimen.greeter_menu_table_info_p) * 2)), it, 44);
                }
            });
        }
        ((ImageView) _$_findCachedViewById(C5508R.id.setting_info)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$3
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                GreeterMenuActivity.this.showSettingDialog();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.btn_start)).setOnClickListener(new SingleVoiceClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$4
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
                GreeterMenuActivity.this.startGo();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.llDoor)).setOnClickListener(new SingleVoiceClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$5
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
                int i;
                ShowTipMsgDialog showTipMsgDialog;
                ShowTipMsgDialog showTipMsgDialog2;
                ShowTipMsgDialog showTipMsgDialog3;
                i = GreeterMenuActivity.this.state;
                if (i == 0) {
                    if (BatteryInfoManager.INSTANCE.isCharging()) {
                        showTipMsgDialog3 = GreeterMenuActivity.this.tipDialog;
                        if (showTipMsgDialog3 != null) {
                            showTipMsgDialog3.dismiss();
                        }
                        GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
                        String string = greeterMenuActivity.getString(C5508R.string.pdStr25_17);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
                        greeterMenuActivity.tipDialog = MyBaseActivity.showTipDialog$default(greeterMenuActivity, string, null, null, null, 14, null);
                        return;
                    }
                    Integer power = BatteryInfoManager.INSTANCE.getPower();
                    if ((power != null ? power.intValue() : 0) < 2) {
                        showTipMsgDialog2 = GreeterMenuActivity.this.tipDialog;
                        if (showTipMsgDialog2 != null) {
                            showTipMsgDialog2.dismiss();
                        }
                        GreeterMenuActivity greeterMenuActivity2 = GreeterMenuActivity.this;
                        String string2 = greeterMenuActivity2.getString(C5508R.string.pdStr2_19);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr2_19)");
                        greeterMenuActivity2.tipDialog = MyBaseActivity.showTipDialog$default(greeterMenuActivity2, string2, null, null, null, 14, null);
                        return;
                    }
                    Intent intent = new Intent(GreeterMenuActivity.this, (Class<?>) SolicitCustomerActivity.class);
                    intent.putExtra("state", 2);
                    GreeterMenuActivity.this.jumpAndFinish(intent);
                    return;
                }
                if (BatteryInfoManager.INSTANCE.isCharging()) {
                    showTipMsgDialog = GreeterMenuActivity.this.tipDialog;
                    if (showTipMsgDialog != null) {
                        showTipMsgDialog.dismiss();
                    }
                    GreeterMenuActivity greeterMenuActivity3 = GreeterMenuActivity.this;
                    String string3 = greeterMenuActivity3.getString(C5508R.string.pdStr25_17);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr25_17)");
                    greeterMenuActivity3.tipDialog = MyBaseActivity.showTipDialog$default(greeterMenuActivity3, string3, null, null, null, 14, null);
                    return;
                }
                GreeterMenuActivity.this.jumpAndFinish(new Intent(GreeterMenuActivity.this, (Class<?>) SolicitCustomerActivity.class));
            }
        }));
        ImageView settingIv = (ImageView) _$_findCachedViewById(C5508R.id.settingIv);
        Intrinsics.checkExpressionValueIsNotNull(settingIv, "settingIv");
        ViewExtKt.onSingleClick(settingIv, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$initViews$6
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
                GreeterMenuActivity greeterMenuActivity = GreeterMenuActivity.this;
                greeterMenuActivity.jump(new Intent(greeterMenuActivity, (Class<?>) GreeterSettingActivity.class));
            }
        });
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_greeter_animation_view)).addOnFaceClickListener(this.onGreeterAnimationViewClick);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGo() {
        if (BatteryInfoManager.INSTANCE.isCharging()) {
            ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
            if (showTipMsgDialog != null) {
                showTipMsgDialog.dismiss();
            }
            String string = getString(C5508R.string.pdStr25_17);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
            this.tipDialog = MyBaseActivity.showTipDialog$default(this, string, null, null, null, 14, null);
            return;
        }
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) <= 2) {
            ShowTipMsgDialog showTipMsgDialog2 = this.tipDialog;
            if (showTipMsgDialog2 != null) {
                showTipMsgDialog2.dismiss();
            }
            String string2 = getString(C5508R.string.pdStr2_19);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr2_19)");
            this.tipDialog = MyBaseActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
            return;
        }
        AppCompatTextView info_table_tv = (AppCompatTextView) _$_findCachedViewById(C5508R.id.info_table_tv);
        Intrinsics.checkExpressionValueIsNotNull(info_table_tv, "info_table_tv");
        CharSequence text = info_table_tv.getText();
        if (text == null || StringsKt.isBlank(text)) {
            ShowTipMsgDialog showTipMsgDialog3 = this.tipDialog;
            if (showTipMsgDialog3 != null) {
                showTipMsgDialog3.dismiss();
            }
            String string3 = getString(C5508R.string.pdStr25_6);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr25_6)");
            this.tipDialog = MyBaseActivity.showTipDialog$default(this, string3, null, null, null, 14, null);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) GreeterActivity.class);
        intent.putExtra(GreeterActivity.INSTANCE.getTABLE_NAME(), text);
        intent.putExtra("state", this.state);
        jumpAndFinish(intent);
        ReportUsherTask.INSTANCE.trackingUsherEvent();
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
            homeSettingDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$showSettingDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    Pdlog.m3273d(GreeterMenuActivity.this.TAG, "showSettingDialog OnDismissListener");
                }
            });
            HomeSettingDialog homeSettingDialog3 = this.homeSettingDialog;
            if (homeSettingDialog3 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$showSettingDialog$2
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    Pdlog.m3273d(GreeterMenuActivity.this.TAG, "showSettingDialog setOnShowListener");
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
        onFeatureChange(0);
    }

    private final void release() {
        this.isRelease = true;
        unbindPresenter();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        VoicePlayTasks.INSTANCE.finishStop();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        if (intent != null) {
            startActivity(intent);
        }
        overridePendingTransition(0, 0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        release();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isNotShow = true;
        onFeatureChange(0);
        initAllData();
        startCountDownTimer();
        getMHeyPUduWakeVm().startListening();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCountDownTimer() {
        this.handler.removeMessages(8);
        this.handler.sendEmptyMessageDelayed(8, 20000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playGreeter() {
        RelativeLayout rlFaceGreeterBg = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlFaceGreeterBg);
        Intrinsics.checkExpressionValueIsNotNull(rlFaceGreeterBg, "rlFaceGreeterBg");
        rlFaceGreeterBg.setVisibility(0);
        FaceVideoView face_greeter_animation_view = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_greeter_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_greeter_animation_view, "face_greeter_animation_view");
        face_greeter_animation_view.setVisibility(0);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_greeter_animation_view);
        if (faceVideoView != null) {
            faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getCruiseDeliver());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopGreeter() {
        RelativeLayout rlFaceGreeterBg = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlFaceGreeterBg);
        Intrinsics.checkExpressionValueIsNotNull(rlFaceGreeterBg, "rlFaceGreeterBg");
        rlFaceGreeterBg.setVisibility(8);
        FaceVideoView face_greeter_animation_view = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_greeter_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_greeter_animation_view, "face_greeter_animation_view");
        face_greeter_animation_view.setVisibility(8);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_greeter_animation_view);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        stopShowFaceTask();
        getMHeyPUduWakeVm().stopListening();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        startShowFaceTask();
        startCountDownTimer();
        return super.dispatchTouchEvent(ev);
    }

    private final void startShowFaceTask() {
        ConstraintLayout clt_face_show = (ConstraintLayout) _$_findCachedViewById(C5508R.id.clt_face_show);
        Intrinsics.checkExpressionValueIsNotNull(clt_face_show, "clt_face_show");
        if (clt_face_show.getVisibility() == 0) {
            return;
        }
        stopShowFaceTask();
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        }
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter ");
    }

    private final void setGreeterFaceFragment() {
        ((AppCompatTextView) _$_findCachedViewById(C5508R.id.back_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterMenuActivity$setGreeterFaceFragment$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GreeterMenuActivity.this.onFeatureChange(0);
            }
        });
    }

    private final void showTouchFace() {
        ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
        stopShowFaceTask();
        ConstraintLayout clt_face_show = (ConstraintLayout) _$_findCachedViewById(C5508R.id.clt_face_show);
        Intrinsics.checkExpressionValueIsNotNull(clt_face_show, "clt_face_show");
        clt_face_show.setVisibility(0);
        AppCompatTextView back_btn = (AppCompatTextView) _$_findCachedViewById(C5508R.id.back_btn);
        Intrinsics.checkExpressionValueIsNotNull(back_btn, "back_btn");
        back_btn.setVisibility(8);
        AppCompatTextView tv_greeter_say_bellabella = (AppCompatTextView) _$_findCachedViewById(C5508R.id.tv_greeter_say_bellabella);
        Intrinsics.checkExpressionValueIsNotNull(tv_greeter_say_bellabella, "tv_greeter_say_bellabella");
        tv_greeter_say_bellabella.setVisibility(8);
    }

    private final void showGreeterFace() {
        playFaceAnimation();
        ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
        stopShowFaceTask();
        ConstraintLayout clt_face_show = (ConstraintLayout) _$_findCachedViewById(C5508R.id.clt_face_show);
        Intrinsics.checkExpressionValueIsNotNull(clt_face_show, "clt_face_show");
        clt_face_show.setVisibility(0);
        AppCompatTextView back_btn = (AppCompatTextView) _$_findCachedViewById(C5508R.id.back_btn);
        Intrinsics.checkExpressionValueIsNotNull(back_btn, "back_btn");
        back_btn.setVisibility(0);
        AppCompatTextView tv_greeter_say_bellabella = (AppCompatTextView) _$_findCachedViewById(C5508R.id.tv_greeter_say_bellabella);
        Intrinsics.checkExpressionValueIsNotNull(tv_greeter_say_bellabella, "tv_greeter_say_bellabella");
        tv_greeter_say_bellabella.setVisibility(0);
    }

    private final void hideFace() {
        ConstraintLayout clt_face_show = (ConstraintLayout) _$_findCachedViewById(C5508R.id.clt_face_show);
        Intrinsics.checkExpressionValueIsNotNull(clt_face_show, "clt_face_show");
        clt_face_show.setVisibility(8);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isNotShow = false;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        wakeup = false;
        this.handler.removeCallbacksAndMessages(null);
        SolicitInfoManager.INSTANCE.stopWeCome();
        SolicitInfoManager.INSTANCE.removePersonApproachListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type == 0) {
            this.curStateType = 0;
            hideFace();
            startShowFaceTask();
        } else if (type == 1) {
            this.curStateType = 1;
            stopShowFaceTask();
            showTouchFace();
        } else if (type == 2) {
            stopShowFaceTask();
        } else {
            if (type != 3) {
                return;
            }
            this.curStateType = 3;
            stopShowFaceTask();
            showGreeterFace();
        }
    }

    /* compiled from: GreeterMenuActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity$Companion;", "", "()V", "AVOID_WAKE_UP", "", "GREETER_FACE_SHOW", "TYPE_FEATURE_AIVOICE", "TYPE_FEATURE_DIALOG", "TYPE_FEATURE_NOMAL", "TYPE_GREETER_FACK_SHOW", "TYPE_TOUCH_FACE", "USHER_MODE", "WAKE_UP", "wakeup", "", "getWakeup", "()Z", "setWakeup", "(Z)V", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getWakeup() {
            return GreeterMenuActivity.wakeup;
        }

        public final void setWakeup(boolean z) {
            GreeterMenuActivity.wakeup = z;
        }

        /* compiled from: GreeterMenuActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "activity", "Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity;", "(Lcom/pudutech/peanut/robot_ui/ui/greeter/GreeterMenuActivity;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<GreeterMenuActivity> mActivity;

            public WithoutLeakHandler(GreeterMenuActivity activity) {
                Intrinsics.checkParameterIsNotNull(activity, "activity");
                this.mActivity = new WeakReference<>(activity);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                GreeterMenuActivity greeterMenuActivity = this.mActivity.get();
                if (greeterMenuActivity != null) {
                    int i = msg.what;
                    if (i != 6) {
                        if (i == 7) {
                            Pdlog.m3274e(greeterMenuActivity.TAG, "is open  ALGORITHM_WAKE_UP");
                            GreeterMenuActivity.INSTANCE.setWakeup(false);
                            return;
                        } else {
                            if (i != 8) {
                                return;
                            }
                            greeterMenuActivity.playGreeter();
                            return;
                        }
                    }
                    Pdlog.m3273d(greeterMenuActivity.TAG, "wake_u:" + GreeterMenuActivity.INSTANCE.getWakeup() + ' ');
                    if (GreeterMenuActivity.INSTANCE.getWakeup()) {
                        return;
                    }
                    VoicePlayTasks.INSTANCE.playAttractUsher();
                    GreeterMenuActivity.INSTANCE.setWakeup(true);
                    greeterMenuActivity.stopGreeter();
                    greeterMenuActivity.startCountDownTimer();
                    greeterMenuActivity.handler.sendEmptyMessageDelayed(7, 5000L);
                }
            }
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager.OnWeComeApproachListener
    public void onPersonApproach(int p0) {
        Pdlog.m3274e(this.TAG, "onPersonApproach： " + p0);
        if (this.isNotShow) {
            this.handler.sendEmptyMessage(6);
            ReportUsherTask.INSTANCE.trackingUsherFlowEvent();
        }
    }
}

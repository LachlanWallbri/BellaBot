package com.pudutech.peanut.robot_ui.p063ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowDownloadingDialog;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.fragment.HomeFuncFragment;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.FramesSequenceAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import com.pudutech.peanut.robot_ui.util.FaceAnimationUtil;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.HeyPuduWakeViewModel;
import com.pudutech.peanut.robot_ui.viewmodel.TtsVoiceGenVm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: HomeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 G2\u00020\u0001:\u0002GHB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J\u0006\u0010#\u001a\u00020\u0007J\b\u0010$\u001a\u00020\"H\u0007J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\"H\u0002J\u0012\u0010*\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\u0012\u0010-\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J1\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u000f2\b\u00103\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u00104J\u0012\u00105\u001a\u00020\"2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020\"H\u0014J\u0012\u00109\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010:\u001a\u00020\"H\u0014J\b\u0010;\u001a\u00020\"H\u0014J\b\u0010<\u001a\u00020\"H\u0014J\b\u0010=\u001a\u00020\"H\u0014J\b\u0010>\u001a\u00020\"H\u0002J\b\u0010?\u001a\u00020\"H\u0002J\b\u0010@\u001a\u00020\"H\u0002J\u0012\u0010A\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020\u0004H\u0002J\b\u0010C\u001a\u00020\"H\u0002J\b\u0010D\u001a\u00020\"H\u0002J\u0010\u0010E\u001a\u00020\"2\u0006\u0010B\u001a\u00020\u0004H\u0002J\b\u0010F\u001a\u00020\"H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0018\u001a\u0004\b\u001e\u0010\u001f¨\u0006I"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/HomeActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/SleepBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "backType", "", "Ljava/lang/Integer;", "finishTip", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "framDelivery", "Lcom/pudutech/peanut/robot_ui/ui/view/FramesSequenceAnimation;", "framGuide", "isLoadingImage", "", "isPlayingAnimation", "isShowLowPowerDialog", "lowerPowerDialog", "mHeyPUduWakeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "getMHeyPUduWakeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "mHeyPUduWakeVm$delegate", "Lkotlin/Lazy;", "ttsDownInfoDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "ttsTipDialog", "ttsVoiceGenVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm;", "getTtsVoiceGenVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm;", "ttsVoiceGenVm$delegate", "checkTts", "", "getSleepStates", "getTableNumber", "initFuncFragment", "", "Landroidx/fragment/app/Fragment;", "initListener", "initView", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "onPause", "onResume", "onStart", "onStop", "reset", "showAnimation", "showDeliveryBackAnimation", "showDialog", NotificationCompat.CATEGORY_MESSAGE, "showGuideModeBackAnimation", "showLowerNotify", "showTip", "showTtsDownInfoDialog", "Companion", "HomeFuncPagerAdapter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HomeActivity extends SleepBaseActivity {
    public static final String BACK_TYPE_KEY = "BACK_TYPE_KEY";
    public static final int DELIVERY_BACK_TYPE = 2;
    public static final int GUIDE_BACK_TYPE = 3;
    private static int isStop;
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog finishTip;
    private FramesSequenceAnimation framDelivery;
    private FramesSequenceAnimation framGuide;
    private boolean isLoadingImage;
    private boolean isPlayingAnimation;
    private boolean isShowLowPowerDialog;
    private ShowTipMsgDialog lowerPowerDialog;
    private ShowDownloadingDialog ttsDownInfoDialog;
    private ShowTipMsgDialog ttsTipDialog;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int PAGE_NUM = 1;
    private String TAG = getClass().getSimpleName();

    /* renamed from: ttsVoiceGenVm$delegate, reason: from kotlin metadata */
    private final Lazy ttsVoiceGenVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TtsVoiceGenVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$$special$$inlined$viewModels$1
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

    /* renamed from: mHeyPUduWakeVm$delegate, reason: from kotlin metadata */
    private final Lazy mHeyPUduWakeVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(HeyPuduWakeViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$$special$$inlined$viewModels$4
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$$special$$inlined$viewModels$3
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
    private Integer backType = 0;

    private final HeyPuduWakeViewModel getMHeyPUduWakeVm() {
        return (HeyPuduWakeViewModel) this.mHeyPUduWakeVm.getValue();
    }

    private final TtsVoiceGenVm getTtsVoiceGenVm() {
        return (TtsVoiceGenVm) this.ttsVoiceGenVm.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    public HomeActivity() {
    }

    /* compiled from: HomeActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/HomeActivity$Companion;", "", "()V", HomeActivity.BACK_TYPE_KEY, "", "DELIVERY_BACK_TYPE", "", "GUIDE_BACK_TYPE", "PAGE_NUM", "isStop", "()I", "setStop", "(I)V", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int isStop() {
            return HomeActivity.isStop;
        }

        public final void setStop(int i) {
            HomeActivity.isStop = i;
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        NavigationBarUtil.hideNavigationBars(getWindow());
        setContentView(C5508R.layout.activity_home);
        getTableNumber();
        initView();
        reset();
        initListener();
        checkTts();
        isStop = 0;
    }

    private final void checkTts() {
        HomeActivity homeActivity = this;
        getTtsVoiceGenVm().getNeedDown().observe(homeActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$checkTts$1
            /* JADX WARN: Code restructure failed: missing block: B:24:0x00a7, code lost:
            
                r6 = r5.this$0.ttsTipDialog;
             */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onChanged(Boolean bool) {
                String str;
                ShowTipMsgDialog showTipMsgDialog;
                ShowTipMsgDialog showTipMsgDialog2;
                ShowTipMsgDialog showTipMsgDialog3;
                ShowTipMsgDialog showTipMsgDialog4;
                ShowTipMsgDialog showTipMsgDialog5;
                ShowTipMsgDialog showTipMsgDialog6;
                ShowTipMsgDialog showTipMsgDialog7;
                ShowTipMsgDialog showTipMsgDialog8;
                ShowTipMsgDialog showTipMsgDialog9;
                str = HomeActivity.this.TAG;
                Pdlog.m3273d(str, "checkTts " + bool);
                if (bool.booleanValue()) {
                    return;
                }
                showTipMsgDialog = HomeActivity.this.ttsTipDialog;
                if (showTipMsgDialog == null) {
                    HomeActivity homeActivity2 = HomeActivity.this;
                    homeActivity2.ttsTipDialog = new ShowTipMsgDialog(homeActivity2);
                    showTipMsgDialog6 = HomeActivity.this.ttsTipDialog;
                    if (showTipMsgDialog6 != null) {
                        String string = HomeActivity.this.getString(C5508R.string.dialog_title_have_update);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_title_have_update)");
                        showTipMsgDialog6.setTitle(string);
                    }
                    showTipMsgDialog7 = HomeActivity.this.ttsTipDialog;
                    if (showTipMsgDialog7 != null) {
                        String string2 = HomeActivity.this.getString(C5508R.string.dialog_tip_have_update);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.dialog_tip_have_update)");
                        showTipMsgDialog7.showTipMsg(string2);
                    }
                    showTipMsgDialog8 = HomeActivity.this.ttsTipDialog;
                    if (showTipMsgDialog8 != null) {
                        String string3 = HomeActivity.this.getString(C5508R.string.dialog_btn_download);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.dialog_btn_download)");
                        showTipMsgDialog8.changeBtnTv(string3);
                    }
                    showTipMsgDialog9 = HomeActivity.this.ttsTipDialog;
                    if (showTipMsgDialog9 != null) {
                        showTipMsgDialog9.setCanCancel(true);
                    }
                }
                showTipMsgDialog2 = HomeActivity.this.ttsTipDialog;
                if (showTipMsgDialog2 != null) {
                    showTipMsgDialog4 = HomeActivity.this.ttsTipDialog;
                    if (showTipMsgDialog4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!showTipMsgDialog4.isShowing() && showTipMsgDialog5 != null) {
                        showTipMsgDialog5.show();
                    }
                }
                showTipMsgDialog3 = HomeActivity.this.ttsTipDialog;
                if (showTipMsgDialog3 != null) {
                    showTipMsgDialog3.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$checkTts$1.1
                        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
                        public void onDissmiss(ShowTipMsgDialog dialog) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            HomeActivity.this.showTtsDownInfoDialog();
                        }
                    });
                }
            }
        });
        getMHeyPUduWakeVm().getWakeUpAngle().observe(homeActivity, new Observer<Double>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$checkTts$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Double it) {
                if (!Intrinsics.areEqual(it, 0.0d)) {
                    if (HomeActivity.this.getIsSleepState() != 0) {
                        HomeActivity.this.openBlackScreen();
                        return;
                    }
                    RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    robotMoveManager.rotate(it.doubleValue());
                }
            }
        });
        getTtsVoiceGenVm().check();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTtsDownInfoDialog() {
        ShowDownloadingDialog showDownloadingDialog;
        if (this.ttsDownInfoDialog == null) {
            this.ttsDownInfoDialog = new ShowDownloadingDialog(this);
            getTtsVoiceGenVm().getDownInfo().observe(this, new Observer<TtsDownInfo>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showTtsDownInfoDialog$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(TtsDownInfo ttsDownInfo) {
                    String str;
                    String valueOf;
                    ShowDownloadingDialog showDownloadingDialog2;
                    ShowDownloadingDialog showDownloadingDialog3;
                    ShowDownloadingDialog showDownloadingDialog4;
                    str = HomeActivity.this.TAG;
                    Pdlog.m3273d(str, "showTtsDownInfoDialog " + ttsDownInfo);
                    if (ttsDownInfo.getCode() != 0) {
                        showDownloadingDialog4 = HomeActivity.this.ttsDownInfoDialog;
                        if (showDownloadingDialog4 != null) {
                            showDownloadingDialog4.dismiss();
                        }
                        HomeActivity homeActivity = HomeActivity.this;
                        String string = homeActivity.getString(C5508R.string.dialog_tip_update_err);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_tip_update_err)");
                        homeActivity.showTip(string);
                    } else {
                        if (ttsDownInfo.getAll() == 0) {
                            valueOf = ttsDownInfo + ".all";
                        } else {
                            valueOf = String.valueOf((ttsDownInfo.getLeft() * 100) / ttsDownInfo.getAll());
                        }
                        showDownloadingDialog2 = HomeActivity.this.ttsDownInfoDialog;
                        if (showDownloadingDialog2 != null) {
                            showDownloadingDialog2.showProgress(valueOf);
                        }
                    }
                    if (ttsDownInfo.getLeft() == ttsDownInfo.getAll()) {
                        showDownloadingDialog3 = HomeActivity.this.ttsDownInfoDialog;
                        if (showDownloadingDialog3 != null) {
                            showDownloadingDialog3.dismiss();
                        }
                        HomeActivity homeActivity2 = HomeActivity.this;
                        String string2 = homeActivity2.getString(C5508R.string.dialog_tip_download_success);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.dialog_tip_download_success)");
                        homeActivity2.showTip(string2);
                    }
                }
            });
        }
        ShowDownloadingDialog showDownloadingDialog2 = this.ttsDownInfoDialog;
        if (showDownloadingDialog2 != null) {
            showDownloadingDialog2.showProgress("0");
        }
        ShowDownloadingDialog showDownloadingDialog3 = this.ttsDownInfoDialog;
        if (showDownloadingDialog3 != null) {
            if (showDownloadingDialog3 == null) {
                Intrinsics.throwNpe();
            }
            if (!showDownloadingDialog3.isShowing() && (showDownloadingDialog = this.ttsDownInfoDialog) != null) {
                showDownloadingDialog.show();
            }
        }
        getTtsVoiceGenVm().down();
    }

    public final void getTableNumber() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HomeActivity$getTableNumber$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        setIntent(intent);
        reset();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        showAnimation();
    }

    public final int getSleepStates() {
        return getIsSleepState();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
        isStop = 0;
        if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            MyStatusBarLayout myStatusBarLayout = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layoutMyStatusBar);
            if (myStatusBarLayout != null) {
                myStatusBarLayout.onNetStatus(true);
            }
        } else {
            MyStatusBarLayout myStatusBarLayout2 = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layoutMyStatusBar);
            if (myStatusBarLayout2 != null) {
                myStatusBarLayout2.onNetStatus(false);
            }
        }
        VoicePlayTasks.INSTANCE.stop();
        getMHeyPUduWakeVm().startListening();
    }

    private final void showAnimation() {
        this.backType = Integer.valueOf(getIntent().getIntExtra(BACK_TYPE_KEY, 0));
        Integer num = this.backType;
        if (num != null && num.intValue() == 2) {
            showDeliveryBackAnimation();
        } else if (num != null && num.intValue() == 3) {
            showGuideModeBackAnimation();
        }
    }

    private final void reset() {
        Pdlog.m3273d(this.TAG, "reset");
        this.isShowLowPowerDialog = false;
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initView() {
        TextView textView = (TextView) _$_findCachedViewById(C5508R.id.returnTv);
        if (textView != null) {
            ViewExtKt.onSingleClick(textView, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$initView$1
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
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = HomeActivity.this.TAG;
                    boolean z = true;
                    Pdlog.m3273d(str, "goHome click ");
                    String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
                    if (currentMapDiningOutLetChosen != null && currentMapDiningOutLetChosen.length() != 0) {
                        z = false;
                    }
                    if (z) {
                        HomeActivity homeActivity = HomeActivity.this;
                        ToastUtils.show(homeActivity, homeActivity.getString(C5508R.string.please_setting_back_part), new Object[0]);
                    } else {
                        HomeActivity homeActivity2 = HomeActivity.this;
                        homeActivity2.jumpAndFinish(new Intent(homeActivity2, (Class<?>) TurnBackActivity.class));
                    }
                }
            });
        }
        TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.chargeCallBackTv);
        if (textView2 != null) {
            ViewExtKt.onSingleClick(textView2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$initView$2
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
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = HomeActivity.this.TAG;
                    boolean z = true;
                    Pdlog.m3273d(str, "charge click ");
                    List<String> chargingPiles = RobotMapManager.INSTANCE.getChargingPiles();
                    if (chargingPiles != null && !chargingPiles.isEmpty()) {
                        z = false;
                    }
                    if (z) {
                        HomeActivity homeActivity = HomeActivity.this;
                        ToastUtils.show(homeActivity, homeActivity.getString(C5508R.string.toast_tip_not_find_piles), new Object[0]);
                    } else {
                        HomeActivity homeActivity2 = HomeActivity.this;
                        homeActivity2.jumpAndFinish(new Intent(homeActivity2, (Class<?>) OneKeyChargingActivity.class));
                    }
                }
            });
        }
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C5508R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        viewPager.setAdapter(new HomeFuncPagerAdapter(supportFragmentManager, initFuncFragment()));
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(C5508R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager2, "viewPager");
        viewPager2.setOffscreenPageLimit(PAGE_NUM);
        ViewPager viewPager3 = (ViewPager) _$_findCachedViewById(C5508R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager3, "viewPager");
        viewPager3.setCurrentItem(0);
    }

    private final void showDeliveryBackAnimation() {
        FramesSequenceAnimation framesSequenceAnimation = this.framDelivery;
        if (framesSequenceAnimation == null) {
            if (this.isLoadingImage) {
                return;
            }
            this.isLoadingImage = true;
            FaceAnimationUtil.INSTANCE.getCacheImage(new Function1<HashMap<Integer, Bitmap>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showDeliveryBackAnimation$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HashMap<Integer, Bitmap> hashMap) {
                    invoke2(hashMap);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HashMap<Integer, Bitmap> it) {
                    FramesSequenceAnimation framesSequenceAnimation2;
                    FramesSequenceAnimation framesSequenceAnimation3;
                    FramesSequenceAnimation framesSequenceAnimation4;
                    FramesSequenceAnimation framesSequenceAnimation5;
                    FramesSequenceAnimation framesSequenceAnimation6;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    HomeActivity homeActivity = HomeActivity.this;
                    homeActivity.framDelivery = new FramesSequenceAnimation((ImageView) homeActivity._$_findCachedViewById(C5508R.id.homeRobotIv), FaceAnimationUtil.INSTANCE.getDeliveryBackToHomeAnimation(), it);
                    framesSequenceAnimation2 = HomeActivity.this.framDelivery;
                    if (framesSequenceAnimation2 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation2.setOnAnimStopListener(new FramesSequenceAnimation.OnAnimationListener() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showDeliveryBackAnimation$1.1
                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationEnd(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = false;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStart(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = true;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStopOrCancel(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = false;
                        }
                    });
                    framesSequenceAnimation3 = HomeActivity.this.framDelivery;
                    if (framesSequenceAnimation3 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation3.setDuration(1000L);
                    framesSequenceAnimation4 = HomeActivity.this.framDelivery;
                    if (framesSequenceAnimation4 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation4.goBackStart();
                    framesSequenceAnimation5 = HomeActivity.this.framDelivery;
                    if (framesSequenceAnimation5 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation5.setGoBack(false);
                    framesSequenceAnimation6 = HomeActivity.this.framDelivery;
                    if (framesSequenceAnimation6 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation6.start();
                    HomeActivity.this.isLoadingImage = false;
                }
            });
            return;
        }
        if (framesSequenceAnimation == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation.setDuration(1000L);
        FramesSequenceAnimation framesSequenceAnimation2 = this.framDelivery;
        if (framesSequenceAnimation2 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation2.goBackStart();
        FramesSequenceAnimation framesSequenceAnimation3 = this.framDelivery;
        if (framesSequenceAnimation3 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation3.setGoBack(false);
        FramesSequenceAnimation framesSequenceAnimation4 = this.framDelivery;
        if (framesSequenceAnimation4 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation4.start();
    }

    private final void showGuideModeBackAnimation() {
        FramesSequenceAnimation framesSequenceAnimation = this.framGuide;
        if (framesSequenceAnimation == null) {
            if (this.isLoadingImage) {
                return;
            }
            this.isLoadingImage = true;
            FaceAnimationUtil.INSTANCE.getCacheImage(new Function1<HashMap<Integer, Bitmap>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showGuideModeBackAnimation$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HashMap<Integer, Bitmap> hashMap) {
                    invoke2(hashMap);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HashMap<Integer, Bitmap> it) {
                    FramesSequenceAnimation framesSequenceAnimation2;
                    FramesSequenceAnimation framesSequenceAnimation3;
                    FramesSequenceAnimation framesSequenceAnimation4;
                    FramesSequenceAnimation framesSequenceAnimation5;
                    FramesSequenceAnimation framesSequenceAnimation6;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    HomeActivity homeActivity = HomeActivity.this;
                    homeActivity.framGuide = new FramesSequenceAnimation((ImageView) homeActivity._$_findCachedViewById(C5508R.id.homeRobotIv), FaceAnimationUtil.INSTANCE.getGuideBackToHomeAnimation(), it);
                    framesSequenceAnimation2 = HomeActivity.this.framGuide;
                    if (framesSequenceAnimation2 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation2.setOnAnimStopListener(new FramesSequenceAnimation.OnAnimationListener() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showGuideModeBackAnimation$1.1
                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationEnd(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = false;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStart(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = true;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStopOrCancel(FramesSequenceAnimation animation) {
                            HomeActivity.this.isPlayingAnimation = false;
                        }
                    });
                    framesSequenceAnimation3 = HomeActivity.this.framGuide;
                    if (framesSequenceAnimation3 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation3.setDuration(1000L);
                    framesSequenceAnimation4 = HomeActivity.this.framGuide;
                    if (framesSequenceAnimation4 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation4.goBackStart();
                    framesSequenceAnimation5 = HomeActivity.this.framGuide;
                    if (framesSequenceAnimation5 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation5.setGoBack(false);
                    framesSequenceAnimation6 = HomeActivity.this.framGuide;
                    if (framesSequenceAnimation6 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation6.start();
                    HomeActivity.this.isLoadingImage = false;
                }
            });
            return;
        }
        if (framesSequenceAnimation == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation.setDuration(1000L);
        FramesSequenceAnimation framesSequenceAnimation2 = this.framGuide;
        if (framesSequenceAnimation2 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation2.goBackStart();
        FramesSequenceAnimation framesSequenceAnimation3 = this.framGuide;
        if (framesSequenceAnimation3 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation3.setGoBack(false);
        FramesSequenceAnimation framesSequenceAnimation4 = this.framGuide;
        if (framesSequenceAnimation4 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation4.start();
    }

    private final void initListener() {
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layoutMyStatusBar)).startChangeListener(this);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        jump(intent);
    }

    private final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    private final List<Fragment> initFuncFragment() {
        ArrayList arrayList = new ArrayList();
        int i = PAGE_NUM;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                HomeFuncFragment homeFuncFragment = new HomeFuncFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(HomeFuncFragment.INDEX_FLAG, i2);
                homeFuncFragment.setArguments(bundle);
                arrayList.add(homeFuncFragment);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    /* compiled from: HomeActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/HomeActivity$HomeFuncPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "funcFragments", "", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "getFuncFragments", "()Ljava/util/List;", "setFuncFragments", "(Ljava/util/List;)V", "getCount", "", "getItem", RequestParameters.POSITION, "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class HomeFuncPagerAdapter extends FragmentPagerAdapter {
        private List<? extends Fragment> funcFragments;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HomeFuncPagerAdapter(FragmentManager fm, List<? extends Fragment> funcFragments) {
            super(fm, 1);
            Intrinsics.checkParameterIsNotNull(fm, "fm");
            Intrinsics.checkParameterIsNotNull(funcFragments, "funcFragments");
            this.funcFragments = funcFragments;
        }

        public final List<Fragment> getFuncFragments() {
            return this.funcFragments;
        }

        public final void setFuncFragments(List<? extends Fragment> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.funcFragments = list;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return HomeActivity.PAGE_NUM;
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int position) {
            return this.funcFragments.get(position);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 1) {
            if (this.isShowLowPowerDialog) {
                return;
            }
            Pdlog.m3275i(this.TAG, "showLowerNotify power：" + i);
            showLowerNotify();
            if (BatteryInfoManager.INSTANCE.getPower() != null) {
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) > 5) {
                    VoicePlayTasks.INSTANCE.playLowPower5_10();
                    return;
                }
                Integer power2 = BatteryInfoManager.INSTANCE.getPower();
                if ((power2 != null ? power2.intValue() : 0) > 2) {
                    VoicePlayTasks.INSTANCE.playLowPower2_5();
                    return;
                }
                Integer power3 = BatteryInfoManager.INSTANCE.getPower();
                if ((power3 != null ? power3.intValue() : 0) < 2) {
                    VoicePlayTasks.INSTANCE.playLowPower0_2();
                    return;
                }
                return;
            }
            return;
        }
        if (state == 2) {
            if (i != null) {
                ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layoutMyStatusBar)).setBattery(i.intValue());
                return;
            }
            return;
        }
        if (state != 3) {
            if (state == 4 && model != null && isCharging) {
                PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
                return;
            }
            return;
        }
        if (BatteryInfoManager.INSTANCE.getPower() != null) {
            Integer power4 = BatteryInfoManager.INSTANCE.getPower();
            if ((power4 != null ? power4.intValue() : 0) < 2) {
                VoicePlayTasks.INSTANCE.playLowPower0_2();
                return;
            }
            Integer power5 = BatteryInfoManager.INSTANCE.getPower();
            if ((power5 != null ? power5.intValue() : 0) < 5) {
                VoicePlayTasks.INSTANCE.playLowPower2_5();
                return;
            }
            Integer power6 = BatteryInfoManager.INSTANCE.getPower();
            if ((power6 != null ? power6.intValue() : 0) <= 10) {
                VoicePlayTasks.INSTANCE.playLowPower5_10();
            }
        }
    }

    private final void showLowerNotify() {
        openBlackScreen();
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
        this.lowerPowerDialog = showDialog(string);
    }

    private final ShowTipMsgDialog showDialog(String msg) {
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.HomeActivity$showDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
            }
        }, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTip(String msg) {
        ShowTipMsgDialog showTipMsgDialog;
        if (this.finishTip == null) {
            this.finishTip = new ShowTipMsgDialog(this);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.finishTip;
        if (showTipMsgDialog2 != null) {
            showTipMsgDialog2.showTipMsg(msg);
        }
        ShowTipMsgDialog showTipMsgDialog3 = this.finishTip;
        if (showTipMsgDialog3 != null) {
            showTipMsgDialog3.setCanCancel(true);
        }
        ShowTipMsgDialog showTipMsgDialog4 = this.finishTip;
        if (showTipMsgDialog4 == null || showTipMsgDialog4.isShowing() || (showTipMsgDialog = this.finishTip) == null) {
            return;
        }
        showTipMsgDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
        getMHeyPUduWakeVm().stopListening();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        isStop = 1;
        Pdlog.m3273d(this.TAG, "onStop");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        isStop = 0;
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layoutMyStatusBar)).stopChangeListener();
    }
}

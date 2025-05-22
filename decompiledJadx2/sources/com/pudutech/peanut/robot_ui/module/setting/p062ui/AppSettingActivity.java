package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.AppUpdateManager;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.AppSettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.CheckPermissionDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.FaceAnimationDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.LeaseHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.util.FragmentUtils;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\b\u0010!\u001a\u00020\u001aH\u0002J\u0006\u0010\"\u001a\u00020\u001aJ\u0012\u0010#\u001a\u00020\u001a2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J1\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010,J\u0012\u0010-\u001a\u00020\u001a2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020\u001aH\u0014J\b\u00101\u001a\u00020\u001aH\u0002J\b\u00102\u001a\u00020\u001aH\u0014J\b\u00103\u001a\u00020\u001aH\u0014J\b\u00104\u001a\u00020\u001aH\u0002J\b\u00105\u001a\u00020\u001aH\u0014J\b\u00106\u001a\u00020\u001aH\u0014J\u0010\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u0010H\u0016J\b\u00109\u001a\u00020\u001aH\u0002J\u0010\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020<H\u0002J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020\u001aH\u0002J\b\u0010A\u001a\u00020\u001aH\u0002J\u000e\u0010B\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u0004J\b\u0010C\u001a\u00020\u001aH\u0002J\b\u0010D\u001a\u00020\u001aH\u0002J\b\u0010E\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "RESET_ADVANCED_CLICK_COUNT", "", "TAG", "", "advancedShowClickCount", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "currentSelectIndex", "faceAnimationDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/FaceAnimationDialog;", "handler", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity$Companion$WithoutLeakHandler;", "isBind", "", "()Z", "setBind", "(Z)V", "isRelease", "isUnBind", "setUnBind", "leaseHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/LeaseHelper;", "bindPresenter", "", "changeFragemnt", "index", "checkAppUpdate", "getItemBg", "item", "getItemTextColor", "hideFaceDialog", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onFragmentChange", "onPause", "onResume", "onSettingItemSelectChange", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "replaceFragment", "fragment", "Landroidx/fragment/app/Fragment;", "showFaceDialog", "animations", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showSleepAnimation", "showStandbyAnimation", "showUpdateText", "stopStandby", "unBindPresenter", "updateAboutSetting", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AppSettingActivity extends BatteryBaseActivity {
    private static final int SET_BASIC_SETUP = 0;
    private static int isStop;
    private HashMap _$_findViewCache;
    private int advancedShowClickCount;
    private FaceAnimationDialog faceAnimationDialog;
    private boolean isBind;
    private boolean isRelease;
    private boolean isUnBind;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int SET_WIFI = 1;
    private static final int SET_VOLUME = 2;
    private static final int SET_SPEED = 3;
    private static final int SET_UPDATE_VERSION = 4;
    private static final int SET_DEBUG = 5;
    private static final int SET_PALLET_SENSOR = 6;
    private static final int SET_LATTICE_TEXT = 7;
    private static final int SET_MULTI_MAP = 8;
    private static final int SET_PALLET_COUNT = 9;
    private static final int SET_VOICE_SETTING = 10;
    private static final int SET_ADVANCED = 11;
    private static final int SET_ITERACTIVE = 12;
    private static final int SET_ABOUT = 13;
    private static final int SET_AD = 14;
    private final String TAG = "SettingActivity";
    private int currentSelectIndex = -1;
    private final LeaseHelper leaseHelper = new LeaseHelper();
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final int RESET_ADVANCED_CLICK_COUNT = 101;
    private final Companion.WithoutLeakHandler handler = new Companion.WithoutLeakHandler(this);

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

    /* compiled from: AppSettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b#\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001&B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u001a\u0010#\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010%¨\u0006'"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity$Companion;", "", "()V", "SET_ABOUT", "", "getSET_ABOUT", "()I", "SET_AD", "getSET_AD", "SET_ADVANCED", "getSET_ADVANCED", "SET_BASIC_SETUP", "getSET_BASIC_SETUP", "SET_DEBUG", "getSET_DEBUG", "SET_ITERACTIVE", "getSET_ITERACTIVE", "SET_LATTICE_TEXT", "getSET_LATTICE_TEXT", "SET_MULTI_MAP", "getSET_MULTI_MAP", "SET_PALLET_COUNT", "getSET_PALLET_COUNT", "SET_PALLET_SENSOR", "getSET_PALLET_SENSOR", "SET_SPEED", "getSET_SPEED", "SET_UPDATE_VERSION", "getSET_UPDATE_VERSION", "SET_VOICE_SETTING", "getSET_VOICE_SETTING", "SET_VOLUME", "getSET_VOLUME", "SET_WIFI", "getSET_WIFI", "isStop", "setStop", "(I)V", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getSET_BASIC_SETUP() {
            return AppSettingActivity.SET_BASIC_SETUP;
        }

        public final int getSET_WIFI() {
            return AppSettingActivity.SET_WIFI;
        }

        public final int getSET_VOLUME() {
            return AppSettingActivity.SET_VOLUME;
        }

        public final int getSET_SPEED() {
            return AppSettingActivity.SET_SPEED;
        }

        public final int getSET_UPDATE_VERSION() {
            return AppSettingActivity.SET_UPDATE_VERSION;
        }

        public final int getSET_DEBUG() {
            return AppSettingActivity.SET_DEBUG;
        }

        public final int getSET_PALLET_SENSOR() {
            return AppSettingActivity.SET_PALLET_SENSOR;
        }

        public final int getSET_LATTICE_TEXT() {
            return AppSettingActivity.SET_LATTICE_TEXT;
        }

        public final int getSET_MULTI_MAP() {
            return AppSettingActivity.SET_MULTI_MAP;
        }

        public final int getSET_PALLET_COUNT() {
            return AppSettingActivity.SET_PALLET_COUNT;
        }

        public final int getSET_VOICE_SETTING() {
            return AppSettingActivity.SET_VOICE_SETTING;
        }

        public final int getSET_ADVANCED() {
            return AppSettingActivity.SET_ADVANCED;
        }

        public final int getSET_ITERACTIVE() {
            return AppSettingActivity.SET_ITERACTIVE;
        }

        public final int getSET_ABOUT() {
            return AppSettingActivity.SET_ABOUT;
        }

        public final int getSET_AD() {
            return AppSettingActivity.SET_AD;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: AppSettingActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "activity", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity;", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/AppSettingActivity;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class WithoutLeakHandler extends Handler {
            private WeakReference<AppSettingActivity> mActivity;

            public WithoutLeakHandler(AppSettingActivity activity) {
                Intrinsics.checkParameterIsNotNull(activity, "activity");
                this.mActivity = new WeakReference<>(activity);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                AppSettingActivity appSettingActivity = this.mActivity.get();
                if (appSettingActivity == null || msg.what != appSettingActivity.RESET_ADVANCED_CLICK_COUNT) {
                    return;
                }
                Pdlog.m3273d(appSettingActivity.TAG, "RESET_ADVANCED_CLICK_COUNT");
                appSettingActivity.advancedShowClickCount = 0;
            }
        }

        public final int isStop() {
            return AppSettingActivity.isStop;
        }

        public final void setStop(int i) {
            AppSettingActivity.isStop = i;
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_app_setting);
        initView();
        checkAppUpdate();
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    public final void initView() {
        changeFragemnt(SET_BASIC_SETUP);
        ((TextView) _$_findCachedViewById(C5508R.id.base_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_BASIC_SETUP());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.wifi_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_WIFI());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.volume_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$3
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_VOLUME());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.speed_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$4
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_SPEED());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.version_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$5
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.showUpdateText(0);
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_UPDATE_VERSION());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.debug_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$6
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_DEBUG());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.multi_map_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$7
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_MULTI_MAP());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.pallet_count_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$8
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_PALLET_COUNT());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.voice_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$9
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_VOICE_SETTING());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.interactive_mode_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$10
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_ITERACTIVE());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.advanced_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$11
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_ADVANCED());
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.about_info_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$12
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_ABOUT());
            }
        });
        LinearLayout llBack = (LinearLayout) _$_findCachedViewById(C5508R.id.llBack);
        Intrinsics.checkExpressionValueIsNotNull(llBack, "llBack");
        ViewExtKt.onSingleClick(llBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$13
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
                AppSettingActivity.this.jumpAndFinish(null);
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.ad_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$14
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_AD());
            }
        });
        _$_findCachedViewById(C5508R.id.llGoDebug).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                AppSettingActivity.Companion.WithoutLeakHandler withoutLeakHandler;
                int i2;
                int i3;
                AppSettingActivity.Companion.WithoutLeakHandler withoutLeakHandler2;
                TextView advanced_setting = (TextView) AppSettingActivity.this._$_findCachedViewById(C5508R.id.advanced_setting);
                Intrinsics.checkExpressionValueIsNotNull(advanced_setting, "advanced_setting");
                if (advanced_setting.getVisibility() == 0) {
                    return;
                }
                String str = AppSettingActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("bar_title_tv add advanced click count ");
                i = AppSettingActivity.this.advancedShowClickCount;
                sb.append(i);
                Pdlog.m3273d(str, sb.toString());
                withoutLeakHandler = AppSettingActivity.this.handler;
                withoutLeakHandler.removeMessages(AppSettingActivity.this.RESET_ADVANCED_CLICK_COUNT);
                AppSettingActivity appSettingActivity = AppSettingActivity.this;
                i2 = appSettingActivity.advancedShowClickCount;
                appSettingActivity.advancedShowClickCount = i2 + 1;
                i3 = AppSettingActivity.this.advancedShowClickCount;
                if (i3 == 5) {
                    final CheckPermissionDialog checkPermissionDialog = new CheckPermissionDialog(AppSettingActivity.this);
                    checkPermissionDialog.setPassword(CollectionsKt.arrayListOf("pudu888", "pudupw"));
                    checkPermissionDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$initView$15.1
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
                            if (z) {
                                ToastUtils.show(RobotContext.INSTANCE.getContext(), AppSettingActivity.this.getString(C5508R.string.pdStr7_95), new Object[0]);
                                TextView advanced_setting2 = (TextView) AppSettingActivity.this._$_findCachedViewById(C5508R.id.advanced_setting);
                                Intrinsics.checkExpressionValueIsNotNull(advanced_setting2, "advanced_setting");
                                advanced_setting2.setVisibility(0);
                                checkPermissionDialog.dismiss();
                                AppSettingActivity.this.changeFragemnt(AppSettingActivity.INSTANCE.getSET_ADVANCED());
                                ((ScrollView) AppSettingActivity.this._$_findCachedViewById(C5508R.id.tag_scroll_view)).fullScroll(130);
                            }
                        }
                    });
                    checkPermissionDialog.show();
                }
                withoutLeakHandler2 = AppSettingActivity.this.handler;
                withoutLeakHandler2.sendEmptyMessageDelayed(AppSettingActivity.this.RESET_ADVANCED_CLICK_COUNT, 1000L);
            }
        });
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        bindPresenter();
        updateAboutSetting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAboutSetting() {
        if (Constans.INSTANCE.getSettingAboutInfoSwitch()) {
            TextView about_info_setting = (TextView) _$_findCachedViewById(C5508R.id.about_info_setting);
            Intrinsics.checkExpressionValueIsNotNull(about_info_setting, "about_info_setting");
            about_info_setting.setVisibility(0);
        } else {
            TextView about_info_setting2 = (TextView) _$_findCachedViewById(C5508R.id.about_info_setting);
            Intrinsics.checkExpressionValueIsNotNull(about_info_setting2, "about_info_setting");
            about_info_setting2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeFragemnt(int index) {
        Pdlog.m3273d(this.TAG, "changeFragemnt " + index);
        if (this.currentSelectIndex != index) {
            this.currentSelectIndex = index;
            onSettingItemSelectChange();
            onFragmentChange();
        }
    }

    private final void onFragmentChange() {
        Pdlog.m3273d(this.TAG, "onFragmentChange " + this.currentSelectIndex);
        int i = this.currentSelectIndex;
        if (i == SET_BASIC_SETUP) {
            replaceFragment(new BasicSetupFragment());
            return;
        }
        if (i == SET_WIFI) {
            replaceFragment(new WifiFragment());
            return;
        }
        if (i == SET_VOLUME) {
            replaceFragment(new SoundFragment());
            return;
        }
        if (i == SET_SPEED) {
            replaceFragment(new SpeedFragment());
            return;
        }
        if (i == SET_UPDATE_VERSION) {
            replaceFragment(new VersionFragment());
            return;
        }
        if (i == SET_DEBUG) {
            replaceFragment(new DebugFragment());
            return;
        }
        if (i == SET_MULTI_MAP) {
            replaceFragment(new MapSettingFragment());
            return;
        }
        if (i == SET_PALLET_COUNT) {
            return;
        }
        if (i == SET_VOICE_SETTING) {
            replaceFragment(new TtsVoiceFragment());
            return;
        }
        if (i == SET_ITERACTIVE) {
            replaceFragment(new LotteryFragment());
            return;
        }
        if (i == SET_ADVANCED) {
            AdvancedSettingsFragment advancedSettingsFragment = new AdvancedSettingsFragment();
            advancedSettingsFragment.setOnAboutInfoSwitchChange(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$onFragmentChange$1
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
                    AppSettingActivity.this.updateAboutSetting();
                }
            });
            replaceFragment(advancedSettingsFragment);
        } else if (i == SET_ABOUT) {
            replaceFragment(new AboutInfoFragment());
        } else if (i == SET_AD) {
            replaceFragment(new AdSettingFragment());
        }
    }

    private final void replaceFragment(Fragment fragment) {
        FragmentUtils.replace(getSupportFragmentManager(), fragment, C5508R.id.fragment_container);
    }

    private final void onSettingItemSelectChange() {
        ((TextView) _$_findCachedViewById(C5508R.id.debug_setting)).setBackgroundResource(getItemBg(SET_DEBUG));
        ((TextView) _$_findCachedViewById(C5508R.id.base_setting)).setBackgroundResource(getItemBg(SET_BASIC_SETUP));
        ((TextView) _$_findCachedViewById(C5508R.id.wifi_setting)).setBackgroundResource(getItemBg(SET_WIFI));
        ((TextView) _$_findCachedViewById(C5508R.id.volume_setting)).setBackgroundResource(getItemBg(SET_VOLUME));
        ((TextView) _$_findCachedViewById(C5508R.id.speed_setting)).setBackgroundResource(getItemBg(SET_SPEED));
        ((TextView) _$_findCachedViewById(C5508R.id.version_setting)).setBackgroundResource(getItemBg(SET_UPDATE_VERSION));
        ((TextView) _$_findCachedViewById(C5508R.id.multi_map_setting)).setBackgroundResource(getItemBg(SET_MULTI_MAP));
        ((TextView) _$_findCachedViewById(C5508R.id.pallet_count_setting)).setBackgroundResource(getItemBg(SET_PALLET_COUNT));
        ((TextView) _$_findCachedViewById(C5508R.id.voice_setting)).setBackgroundResource(getItemBg(SET_VOICE_SETTING));
        ((TextView) _$_findCachedViewById(C5508R.id.interactive_mode_setting)).setBackgroundResource(getItemBg(SET_ITERACTIVE));
        ((TextView) _$_findCachedViewById(C5508R.id.advanced_setting)).setBackgroundResource(getItemBg(SET_ADVANCED));
        ((TextView) _$_findCachedViewById(C5508R.id.about_info_setting)).setBackgroundResource(getItemBg(SET_ABOUT));
        ((TextView) _$_findCachedViewById(C5508R.id.ad_setting)).setBackgroundResource(getItemBg(SET_AD));
        ((TextView) _$_findCachedViewById(C5508R.id.debug_setting)).setTextColor(getItemTextColor(SET_DEBUG));
        ((TextView) _$_findCachedViewById(C5508R.id.base_setting)).setTextColor(getItemTextColor(SET_BASIC_SETUP));
        ((TextView) _$_findCachedViewById(C5508R.id.wifi_setting)).setTextColor(getItemTextColor(SET_WIFI));
        ((TextView) _$_findCachedViewById(C5508R.id.volume_setting)).setTextColor(getItemTextColor(SET_VOLUME));
        ((TextView) _$_findCachedViewById(C5508R.id.speed_setting)).setTextColor(getItemTextColor(SET_SPEED));
        ((TextView) _$_findCachedViewById(C5508R.id.version_setting)).setTextColor(getItemTextColor(SET_UPDATE_VERSION));
        ((TextView) _$_findCachedViewById(C5508R.id.multi_map_setting)).setTextColor(getItemTextColor(SET_MULTI_MAP));
        ((TextView) _$_findCachedViewById(C5508R.id.pallet_count_setting)).setTextColor(getItemTextColor(SET_PALLET_COUNT));
        ((TextView) _$_findCachedViewById(C5508R.id.voice_setting)).setTextColor(getItemTextColor(SET_VOICE_SETTING));
        ((TextView) _$_findCachedViewById(C5508R.id.interactive_mode_setting)).setTextColor(getItemTextColor(SET_ITERACTIVE));
        ((TextView) _$_findCachedViewById(C5508R.id.advanced_setting)).setTextColor(getItemTextColor(SET_ADVANCED));
        ((TextView) _$_findCachedViewById(C5508R.id.about_info_setting)).setTextColor(getItemTextColor(SET_ABOUT));
        ((TextView) _$_findCachedViewById(C5508R.id.ad_setting)).setTextColor(getItemTextColor(SET_AD));
    }

    private final int getItemBg(int item) {
        if (this.currentSelectIndex == item) {
            return C5508R.drawable.rectangle_setting_item_corners;
        }
        return C5508R.drawable.rectangle_setting_item_unselect_corners;
    }

    private final int getItemTextColor(int item) {
        if (this.currentSelectIndex == item) {
            return -1;
        }
        return getColor(C5508R.color.font_color_1);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        if (intent != null) {
            startActivity(intent);
        }
        finish();
    }

    private final void release() {
        this.isRelease = true;
        unBindPresenter();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        isStop = 0;
        if (!this.isBind || this.isUnBind) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onResume start actionTimerCount");
        this.beeperCallHelper.receiverCallTask(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isUnBind) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onPause stop actionTimerCount");
        this.beeperCallHelper.stopReceiverCallTask();
    }

    /* renamed from: isUnBind, reason: from getter */
    public final boolean getIsUnBind() {
        return this.isUnBind;
    }

    public final void setUnBind(boolean z) {
        this.isUnBind = z;
    }

    /* renamed from: isBind, reason: from getter */
    public final boolean getIsBind() {
        return this.isBind;
    }

    public final void setBind(boolean z) {
        this.isBind = z;
    }

    private final void unBindPresenter() {
        this.isUnBind = true;
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
    }

    private final void bindPresenter() {
        this.leaseHelper.bind(null);
        LeaseHelper leaseHelper = this.leaseHelper;
        Application application = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application, "this.application");
        leaseHelper.startCheck(application);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        this.isBind = true;
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        showFaceDialog(SceneAnimationResources.INSTANCE.getSleep());
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        showFaceDialog(SceneAnimationResources.INSTANCE.getStandby());
        setScreenDark();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged : hasFocus = " + hasFocus + ';');
    }

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        if (this.faceAnimationDialog == null) {
            this.faceAnimationDialog = new FaceAnimationDialog();
        }
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog == null) {
            Intrinsics.throwNpe();
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        faceAnimationDialog.show(supportFragmentManager, "face_animation_dialog");
        FaceAnimationDialog faceAnimationDialog2 = this.faceAnimationDialog;
        if (faceAnimationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog2.playAnimation(animations);
        FaceAnimationDialog faceAnimationDialog3 = this.faceAnimationDialog;
        if (faceAnimationDialog3 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$showFaceDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3273d(AppSettingActivity.this.TAG, "faceAnimationDialog OnClick ");
            }
        });
    }

    private final void hideFaceDialog() {
        Pdlog.m3273d(this.TAG, "hideFaceDialog ");
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog != null) {
            faceAnimationDialog.dismissAllowingStateLoss();
        }
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        hideFaceDialog();
        resetScreenLight();
    }

    private final void checkAppUpdate() {
        if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            AppUpdateManager.INSTANCE.checkAppUpdate(new AppUpdateManager.OnCheckResult() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AppSettingActivity$checkAppUpdate$1
                @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
                public void onFailed() {
                }

                @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
                public void onSuccess(VerionResult vr) {
                    Intrinsics.checkParameterIsNotNull(vr, "vr");
                    if (vr.getAvailable() && vr.getVersion() != null && AppUpdateManager.INSTANCE.getUpdateLevel(vr) == AppUpdateManager.UpdateLevel.emergency) {
                        Integer power = BatteryInfoManager.INSTANCE.getPower();
                        if ((power != null ? power.intValue() : 0) > 20) {
                            AppSettingActivity.this.showUpdateText(1);
                        }
                    }
                }
            });
        }
    }

    public final void showUpdateText(int state) {
        if (state == 0) {
            TextView tvUpdateHint = (TextView) _$_findCachedViewById(C5508R.id.tvUpdateHint);
            Intrinsics.checkExpressionValueIsNotNull(tvUpdateHint, "tvUpdateHint");
            tvUpdateHint.setVisibility(8);
        } else {
            TextView tvUpdateHint2 = (TextView) _$_findCachedViewById(C5508R.id.tvUpdateHint);
            Intrinsics.checkExpressionValueIsNotNull(tvUpdateHint2, "tvUpdateHint");
            tvUpdateHint2.setVisibility(0);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        isStop = 1;
        Pdlog.m3273d(this.TAG, "onStop ");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart ");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (!this.isBind || this.isUnBind) {
            return;
        }
        release();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 2) {
            if (i != null) {
                i.intValue();
                ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
                return;
            }
            return;
        }
        if (state == 4 && model != null) {
            Pdlog.m3275i(this.TAG, "jump to Charging");
            if (isCharging) {
                PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            }
        }
    }
}

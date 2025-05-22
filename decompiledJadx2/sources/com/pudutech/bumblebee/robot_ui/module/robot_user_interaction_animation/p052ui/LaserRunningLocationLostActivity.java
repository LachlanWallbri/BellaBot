package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.resources.voice.VoiceItem;
import com.warkiz.widget.SizeUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.Sdk27PropertiesKt;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: LaserRunningLocationLostActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 22\u00020\u00012\u00020\u0002:\u00012B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\u0012\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\b\u0010\u001c\u001a\u00020\u0011H\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0002J\u0012\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u0011H\u0014J\b\u0010#\u001a\u00020\u0011H\u0014J\b\u0010$\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020\u0011H\u0002J\b\u0010&\u001a\u00020\u0011H\u0002J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0011H\u0016J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u0007H\u0016J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010(\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u0011H\u0002J\b\u00100\u001a\u00020\u0011H\u0002J\b\u00101\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/robot_user_interaction_animation/ui/LaserRunningLocationLostActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "TASK_CHEACK_LOCATION", "", "handler", "Landroid/os/Handler;", "isFirstTryRelocation", "", "onLocationInitListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "", "touchJob", "Lkotlinx/coroutines/Job;", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "bindPresenter", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initData", "initView", "jumpToCharging", "jumpToEditPage", "jumpToHomePage", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "parseIntent", "playSound", "setListeners", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "startCheckLocation", "stopCheckLocation", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LaserRunningLocationLostActivity extends MyBaseActivity implements BatteryContract2.ViewInterface {
    public static final int CURRENT_BIRTHDAY_MODIFY_MODE = 3;
    public static final int CURRENT_DIRECT_MODIFY_MODE = 5;
    public static final int CURRENT_INVALID_MODE = -1;
    public static final String CURRENT_MODE_TYPE = "CURRENT_MODE_TYPE";
    public static final int CURRENT_MODIFY_MODE = 1;
    public static final int CURRENT_NEW_MODE = 0;
    public static final int CURRENT_RECYCLING_PLATE_MODIFY_MODE = 7;
    public static int currentTaskModeType;
    private HashMap _$_findViewCache;
    private Job touchJob;
    private final String TAG = "LaserRunningLocationLostActivity";
    private final int TASK_CHEACK_LOCATION = 100;
    private boolean isFirstTryRelocation = true;
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserRunningLocationLostActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = LaserRunningLocationLostActivity.this.TASK_CHEACK_LOCATION;
            if (i2 != i) {
                return true;
            }
            LaserRunningLocationLostActivity.this.startCheckLocation();
            return true;
        }
    });
    private final Function1<Boolean, Unit> onLocationInitListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserRunningLocationLostActivity$onLocationInitListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            String str;
            boolean z2;
            String str2;
            Handler handler;
            int i;
            str = LaserRunningLocationLostActivity.this.TAG;
            Pdlog.m3273d(str, "showLocationInitDone is:" + z);
            if (z) {
                str2 = LaserRunningLocationLostActivity.this.TAG;
                Pdlog.m3273d(str2, "showLocationInitDone is:" + z + " go to HomeMenuActivity");
                handler = LaserRunningLocationLostActivity.this.handler;
                i = LaserRunningLocationLostActivity.this.TASK_CHEACK_LOCATION;
                handler.removeMessages(i);
                LaserRunningLocationLostActivity.this.jumpToEditPage();
                return;
            }
            RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_layout);
            Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
            slam_checking_location_layout.setVisibility(8);
            RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
            Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
            slam_checking_location_result_layout.setVisibility(0);
            z2 = LaserRunningLocationLostActivity.this.isFirstTryRelocation;
            if (z2) {
                TextView relocation_tip = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip, "relocation_tip");
                Sdk27PropertiesKt.setBackgroundResource(relocation_tip, C4188R.drawable.selector_check_location_result_try_tip_btn);
                TextView relocation_tip2 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip2, "relocation_tip");
                relocation_tip2.setText(LaserRunningLocationLostActivity.this.getString(C4188R.string.pdStr1_27));
                TextView relocation_descrip = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip, "relocation_descrip");
                relocation_descrip.setText(LaserRunningLocationLostActivity.this.getString(C4188R.string.pdStr1_26));
                TextView relocation_descrip2 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip2, "relocation_descrip");
                Sdk27PropertiesKt.setTextColor(relocation_descrip2, LaserRunningLocationLostActivity.this.getColor(C4188R.color.white));
                return;
            }
            TextView relocation_tip3 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_tip3, "relocation_tip");
            Sdk27PropertiesKt.setBackgroundResource(relocation_tip3, C4188R.drawable.selector_check_location_result_fail_btn);
            TextView relocation_tip4 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_tip4, "relocation_tip");
            relocation_tip4.setText(LaserRunningLocationLostActivity.this.getString(C4188R.string.pdStr1_29));
            TextView relocation_descrip3 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_descrip3, "relocation_descrip");
            relocation_descrip3.setText(LaserRunningLocationLostActivity.this.getString(C4188R.string.pdStr1_28));
            TextView relocation_descrip4 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_descrip4, "relocation_descrip");
            Sdk27PropertiesKt.setTextColor(relocation_descrip4, LaserRunningLocationLostActivity.this.getColor(C4188R.color.red_alert));
        }
    };
    private VoiceTask voiceTask = new VoiceTask(-1, VoiceItem.voice17_1);

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_laser_running_location_lost);
        parseIntent();
        bindPresenter();
        initView();
        setListeners();
        initData();
    }

    private final void parseIntent() {
        int intExtra;
        Intent intent = getIntent();
        if (intent == null || (intExtra = intent.getIntExtra(CURRENT_MODE_TYPE, 0)) == -1) {
            return;
        }
        currentTaskModeType = intExtra;
    }

    private final void bindPresenter() {
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        CheckLocationHelper.INSTANCE.addLocationInitDoneListener(this.onLocationInitListener);
    }

    private final void unBindPresenter() {
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        CheckLocationHelper.INSTANCE.removeLocationInitDoneListener(this.onLocationInitListener);
    }

    private final void initData() {
        playSound();
        PeripheralsSceneUtil.INSTANCE.lostLocation();
    }

    private final void initView() {
        RelativeLayout slam_checking_location_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.slam_checking_location_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
        slam_checking_location_layout.setVisibility(8);
        RelativeLayout slam_checking_location_result_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
        slam_checking_location_result_layout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LaserRunningLocationLostActivity laserRunningLocationLostActivity = this;
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.checking_tip), (int) ((SizeUtils.dp2px(laserRunningLocationLostActivity, getResources().getDimension(C4188R.dimen.checking_location_tip_width)) - 10) * 1.7d), getString(C4188R.string.pdStr1_25));
        String string = getString(C4188R.string.pdStr1_26);
        if (string.length() < getString(C4188R.string.pdStr1_28).length()) {
            string = getString(C4188R.string.pdStr1_28);
        }
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.relocation_descrip), (int) ((SizeUtils.dp2px(laserRunningLocationLostActivity, getResources().getDimension(C4188R.dimen.checking_location_tip_width)) - 10) * 1.9d), string);
    }

    private final void setListeners() {
        ((TextView) _$_findCachedViewById(C4188R.id.relocation_tip)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserRunningLocationLostActivity$setListeners$1
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
                Handler handler;
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = LaserRunningLocationLostActivity.this.TAG;
                Pdlog.m3273d(str, "click relocation btn");
                LaserRunningLocationLostActivity.this.stopCheckLocation();
                RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                slam_checking_location_layout.setVisibility(0);
                RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                slam_checking_location_result_layout.setVisibility(8);
                LaserRunningLocationLostActivity.this.isFirstTryRelocation = false;
                CheckLocationHelper.INSTANCE.reCheckLocation();
                handler = LaserRunningLocationLostActivity.this.handler;
                i = LaserRunningLocationLostActivity.this.TASK_CHEACK_LOCATION;
                handler.sendEmptyMessageDelayed(i, 5000L);
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C4188R.id.checking_location_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C4188R.id.location_checking_result_anim);
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            lottieAnimationView2.cancelAnimation();
        }
        VoicePlayer.INSTANCE.stop(this.voiceTask);
        stopCheckLocation();
        this.isFirstTryRelocation = true;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        Job launch$default;
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.touchJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserRunningLocationLostActivity$dispatchTouchEvent$1(this, null), 3, null);
                this.touchJob = launch$default;
            }
        } else if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserRunningLocationLostActivity$dispatchTouchEvent$2(this, null), 3, null);
        }
        return super.dispatchTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToEditPage() {
        Pdlog.m3273d(this.TAG, " go to HomeMenuActivity");
        unBindPresenter();
        stopCheckLocation();
        AnkoInternals.internalStartActivity(this, DeliverTaskEditActivity.class, new Pair[]{TuplesKt.m3968to("MODE_TYPE", Integer.valueOf(currentTaskModeType))});
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToHomePage() {
        Pdlog.m3273d(this.TAG, " go to HomeMenuActivity");
        unBindPresenter();
        stopCheckLocation();
        TableTaskManager.INSTANCE.clearAll();
        SceneRecord.INSTANCE.startLastSceneActivity(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        stopCheckLocation();
        CheckLocationHelper.INSTANCE.checkLocationInitResult();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCheckLocation() {
        Pdlog.m3273d(this.TAG, "stopCheckLocation");
        this.handler.removeMessages(this.TASK_CHEACK_LOCATION);
    }

    private final void playSound() {
        VoicePlayer.INSTANCE.play(this.voiceTask);
    }

    private final void jumpToCharging() {
        Pdlog.m3275i(this.TAG, "jump to Charging");
        stopCheckLocation();
        unBindPresenter();
        Intent intent = new Intent(this, (Class<?>) RobotChargingActivity.class);
        intent.putExtra("LOST_LOCATION_IN_LASER_CASE_RUNNING", true);
        startActivity(intent);
        finish();
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            jumpToCharging();
        }
    }
}

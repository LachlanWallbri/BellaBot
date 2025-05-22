package com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: LaserRunningLocationLostActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\fH\u0014J\b\u0010 \u001a\u00020\fH\u0014J\b\u0010!\u001a\u00020\fH\u0002J\b\u0010\"\u001a\u00020\fH\u0002J\b\u0010#\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/LaserRunningLocationLostActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "isFinish", "", "isFirstTryRelocation", "", "touchJob", "Lkotlinx/coroutines/Job;", "chargingFromIntent", "", "i", "Landroid/content/Intent;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initData", "initView", "jumpAndFinish", "intent", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "playSound", "setListeners", "startCheckLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LaserRunningLocationLostActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private int isFinish;
    private Job touchJob;
    private final String TAG = "LaserRunningLocationLostActivity";
    private boolean isFirstTryRelocation = true;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_laser_running_location_lost);
        initView();
        setListeners();
        initData();
    }

    private final void initData() {
        playSound();
        LightPlayManager.INSTANCE.playLostLocation();
    }

    private final void initView() {
        RelativeLayout slam_checking_location_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.slam_checking_location_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
        slam_checking_location_layout.setVisibility(8);
        RelativeLayout slam_checking_location_result_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
        slam_checking_location_result_layout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LaserRunningLocationLostActivity laserRunningLocationLostActivity = this;
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.checking_tip), (int) ((SizeUtils.dp2px(laserRunningLocationLostActivity, getResources().getDimension(C5508R.dimen.checking_location_tip_width)) - 10) * 1.7d), getString(C5508R.string.pdStr1_25));
        String string = getString(C5508R.string.pdStr1_26);
        if (string.length() < getString(C5508R.string.pdStr1_28).length()) {
            string = getString(C5508R.string.pdStr1_28);
        }
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.relocation_descrip), (int) ((SizeUtils.dp2px(laserRunningLocationLostActivity, getResources().getDimension(C5508R.dimen.checking_location_tip_width)) - 10) * 1.9d), string);
    }

    private final void setListeners() {
        ((TextView) _$_findCachedViewById(C5508R.id.relocation_tip)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserRunningLocationLostActivity$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = LaserRunningLocationLostActivity.this.TAG;
                Pdlog.m3273d(str, "click relocation btn");
                RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                slam_checking_location_layout.setVisibility(0);
                RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                slam_checking_location_result_layout.setVisibility(8);
                LaserRunningLocationLostActivity.this.isFirstTryRelocation = false;
                RobotMapManager.INSTANCE.reloadLocation();
                LaserRunningLocationLostActivity.this.startCheckLocation();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.checking_location_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.location_checking_result_anim);
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            lottieAnimationView2.cancelAnimation();
        }
        this.isFirstTryRelocation = true;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        if (this.isFinish == 1) {
            return;
        }
        this.isFinish = 1;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserRunningLocationLostActivity$jumpAndFinish$1(this, null), 3, null);
        Pdlog.m3273d(this.TAG, " go to HomeMenuActivity");
        TableTaskManager.INSTANCE.clearAll();
        startActivity(intent);
        VoicePlayTasks.INSTANCE.finishStop();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        RobotMapManager.checkLocationInit$default(RobotMapManager.INSTANCE, new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserRunningLocationLostActivity$startCheckLocation$1
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
                str = LaserRunningLocationLostActivity.this.TAG;
                Pdlog.m3273d(str, "showLocationInitDone is:" + z);
                if (LaserRunningLocationLostActivity.this.isFinishing() || LaserRunningLocationLostActivity.this.isDestroyed()) {
                    return;
                }
                if (z) {
                    str2 = LaserRunningLocationLostActivity.this.TAG;
                    Pdlog.m3273d(str2, "showLocationInitDone is:" + z + " go to HomeMenuActivity");
                    LaserRunningLocationLostActivity laserRunningLocationLostActivity = LaserRunningLocationLostActivity.this;
                    laserRunningLocationLostActivity.jumpAndFinish(new Intent(laserRunningLocationLostActivity, (Class<?>) SayHiAnimationActivity.class));
                    return;
                }
                RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                slam_checking_location_layout.setVisibility(8);
                RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                slam_checking_location_result_layout.setVisibility(0);
                z2 = LaserRunningLocationLostActivity.this.isFirstTryRelocation;
                if (z2) {
                    TextView relocation_tip = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip, "relocation_tip");
                    Sdk27PropertiesKt.setBackgroundResource(relocation_tip, C5508R.drawable.selector_check_location_result_try_tip_btn);
                    TextView relocation_tip2 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip2, "relocation_tip");
                    relocation_tip2.setText(LaserRunningLocationLostActivity.this.getString(C5508R.string.pdStr1_27));
                    TextView relocation_descrip = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip, "relocation_descrip");
                    relocation_descrip.setText(LaserRunningLocationLostActivity.this.getString(C5508R.string.pdStr1_26));
                    TextView relocation_descrip2 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip2, "relocation_descrip");
                    Sdk27PropertiesKt.setTextColor(relocation_descrip2, LaserRunningLocationLostActivity.this.getColor(C5508R.color.white));
                    return;
                }
                TextView relocation_tip3 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip3, "relocation_tip");
                Sdk27PropertiesKt.setBackgroundResource(relocation_tip3, C5508R.drawable.selector_check_location_result_fail_btn);
                TextView relocation_tip4 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip4, "relocation_tip");
                relocation_tip4.setText(LaserRunningLocationLostActivity.this.getString(C5508R.string.pdStr1_29));
                TextView relocation_descrip3 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip3, "relocation_descrip");
                relocation_descrip3.setText(LaserRunningLocationLostActivity.this.getString(C5508R.string.pdStr1_28));
                TextView relocation_descrip4 = (TextView) LaserRunningLocationLostActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip4, "relocation_descrip");
                Sdk27PropertiesKt.setTextColor(relocation_descrip4, LaserRunningLocationLostActivity.this.getColor(C5508R.color.red_alert));
            }
        }, 0L, 2, null);
    }

    private final void playSound() {
        VoicePlayTasks.INSTANCE.playMarkerLostLocation();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 4 && model != null) {
            Pdlog.m3275i(this.TAG, "jump to Charging");
            if (isCharging) {
                VoicePlayTasks.INSTANCE.stop();
            }
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void chargingFromIntent(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        i.putExtra("LOST_LOCATION_IN_LASER_CASE_RUNNING", true);
    }
}

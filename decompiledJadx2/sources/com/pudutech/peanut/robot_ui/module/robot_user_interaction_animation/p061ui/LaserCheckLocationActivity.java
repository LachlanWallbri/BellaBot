package com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui;

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
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: LaserCheckLocationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0016J1\u0010\u001b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010$\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010'\u001a\u00020\u0011H\u0014J\u0012\u0010(\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010)\u001a\u00020\u0011H\u0014J\b\u0010*\u001a\u00020\u0011H\u0002J\b\u0010+\u001a\u00020\u0011H\u0002J\b\u0010,\u001a\u00020\u0011H\u0002J\b\u0010-\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/LaserCheckLocationActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "REQUESTCODE_SELECT_MAP", "", "TAG", "", "TASK_CHEACK_LOCATION", "handler", "Landroid/os/Handler;", "isFinish", "isFirstTryRelocation", "", "state", "touchJob", "Lkotlinx/coroutines/Job;", "chargingFromIntent", "", "i", "Landroid/content/Intent;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initData", "initView", "jumpAndFinish", "intent", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onActivityResult", "requestCode", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "onResume", "playSound", "setListeners", "startCheckLocation", "stopCheckLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LaserCheckLocationActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private int isFinish;
    private int state;
    private Job touchJob;
    private final String TAG = "LaserCheckLocationActivity";
    private final int TASK_CHEACK_LOCATION = 100;
    private final int REQUESTCODE_SELECT_MAP = 1001;
    private boolean isFirstTryRelocation = true;
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
            if (i2 != i) {
                return true;
            }
            LaserCheckLocationActivity.this.startCheckLocation();
            return true;
        }
    });

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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_laser_check_location);
        this.state = getIntent().getIntExtra("state", 0);
        ((TextView) _$_findCachedViewById(C5508R.id.switch_map_tip)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                int i2;
                LaserCheckLocationActivity.this.stopCheckLocation();
                Intent intent = new Intent(LaserCheckLocationActivity.this, (Class<?>) MapSelectActivity.class);
                i = LaserCheckLocationActivity.this.state;
                intent.putExtra("state", i);
                LaserCheckLocationActivity laserCheckLocationActivity = LaserCheckLocationActivity.this;
                i2 = laserCheckLocationActivity.REQUESTCODE_SELECT_MAP;
                laserCheckLocationActivity.startActivityForResult(intent, i2);
            }
        });
        initView();
        setListeners();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        startCheckLocation();
    }

    private final void initData() {
        playSound();
        LightPlayManager.INSTANCE.playLostLocation();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 5000L);
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$initData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<String> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<String> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it.size() > 1) {
                    TextView switch_map_tip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.switch_map_tip);
                    Intrinsics.checkExpressionValueIsNotNull(switch_map_tip, "switch_map_tip");
                    switch_map_tip.setVisibility(0);
                }
            }
        });
    }

    private final void initView() {
        RelativeLayout slam_checking_location_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.slam_checking_location_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
        slam_checking_location_layout.setVisibility(0);
        RelativeLayout slam_checking_location_result_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
        slam_checking_location_result_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LaserCheckLocationActivity laserCheckLocationActivity = this;
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.checking_tip), (int) ((SizeUtils.dp2px(laserCheckLocationActivity, getResources().getDimension(C5508R.dimen.checking_location_tip_width)) - 10) * 1.7d), getString(C5508R.string.pdStr1_25));
        String string = getString(C5508R.string.pdStr1_26);
        if (string.length() < getString(C5508R.string.pdStr1_28).length()) {
            string = getString(C5508R.string.pdStr1_28);
        }
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.relocation_descrip), (int) ((SizeUtils.dp2px(laserCheckLocationActivity, getResources().getDimension(C5508R.dimen.checking_location_tip_width)) - 10) * 1.9d), string);
    }

    private final void setListeners() {
        ((TextView) _$_findCachedViewById(C5508R.id.relocation_tip)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                Handler handler;
                int i;
                str = LaserCheckLocationActivity.this.TAG;
                Pdlog.m3273d(str, "click relocation btn");
                LaserCheckLocationActivity.this.stopCheckLocation();
                RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                slam_checking_location_layout.setVisibility(0);
                RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                slam_checking_location_result_layout.setVisibility(8);
                LaserCheckLocationActivity.this.isFirstTryRelocation = false;
                RobotMapManager.INSTANCE.reloadLocation();
                handler = LaserCheckLocationActivity.this.handler;
                i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
                handler.sendEmptyMessageDelayed(i, 5000L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Pdlog.m3273d(this.TAG, "onActivityResult requestCode = " + requestCode + " , resultCode " + resultCode);
        if (requestCode == this.REQUESTCODE_SELECT_MAP) {
            if (resultCode == 1001) {
                finish();
            } else {
                startCheckLocation();
            }
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.checking_location_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.location_checking_result_anim);
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            lottieAnimationView2.cancelAnimation();
        }
        stopCheckLocation();
        this.isFirstTryRelocation = true;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        Job launch$default;
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.touchJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserCheckLocationActivity$dispatchTouchEvent$1(this, null), 3, null);
                this.touchJob = launch$default;
            }
        } else if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserCheckLocationActivity$dispatchTouchEvent$2(this, null), 3, null);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        if (this.isFinish == 1) {
            return;
        }
        this.isFinish = 1;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserCheckLocationActivity$jumpAndFinish$1(this, null), 3, null);
        Pdlog.m3273d(this.TAG, "jumpAndFinish : intent = " + intent + "; ");
        stopCheckLocation();
        startActivity(intent);
        VoicePlayTasks.INSTANCE.finishStop();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        stopCheckLocation();
        if (this.state == 0) {
            RobotMapManager.INSTANCE.checkLocationInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$startCheckLocation$1
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
                    Handler handler;
                    int i;
                    String str2;
                    str = LaserCheckLocationActivity.this.TAG;
                    Pdlog.m3273d(str, "onLocationInitListener is:" + z);
                    if (z) {
                        handler = LaserCheckLocationActivity.this.handler;
                        i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
                        handler.removeMessages(i);
                        str2 = LaserCheckLocationActivity.this.TAG;
                        Pdlog.m3273d(str2, "onLocationInitListener is:" + z + " go to HomeMenuActivity");
                        LaserCheckLocationActivity laserCheckLocationActivity = LaserCheckLocationActivity.this;
                        laserCheckLocationActivity.jumpAndFinish(new Intent(laserCheckLocationActivity, (Class<?>) SayHiAnimationActivity.class));
                        return;
                    }
                    RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_layout);
                    Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                    slam_checking_location_layout.setVisibility(8);
                    RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
                    Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                    slam_checking_location_result_layout.setVisibility(0);
                    z2 = LaserCheckLocationActivity.this.isFirstTryRelocation;
                    if (z2) {
                        TextView relocation_tip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_tip, "relocation_tip");
                        Sdk27PropertiesKt.setBackgroundResource(relocation_tip, C5508R.drawable.selector_check_location_result_try_tip_btn);
                        TextView relocation_tip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_tip2, "relocation_tip");
                        relocation_tip2.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_27));
                        TextView relocation_descrip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_descrip, "relocation_descrip");
                        relocation_descrip.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_26));
                        TextView relocation_descrip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_descrip2, "relocation_descrip");
                        Sdk27PropertiesKt.setTextColor(relocation_descrip2, LaserCheckLocationActivity.this.getColor(C5508R.color.white));
                        return;
                    }
                    TextView relocation_tip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip3, "relocation_tip");
                    Sdk27PropertiesKt.setBackgroundResource(relocation_tip3, C5508R.drawable.selector_check_location_result_fail_btn);
                    TextView relocation_tip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip4, "relocation_tip");
                    relocation_tip4.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_29));
                    TextView relocation_descrip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip3, "relocation_descrip");
                    relocation_descrip3.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_28));
                    TextView relocation_descrip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip4, "relocation_descrip");
                    Sdk27PropertiesKt.setTextColor(relocation_descrip4, LaserCheckLocationActivity.this.getColor(C5508R.color.red_alert));
                }
            }, 500L);
        } else {
            RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$startCheckLocation$2
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
                    Handler handler;
                    int i;
                    String str2;
                    str = LaserCheckLocationActivity.this.TAG;
                    Pdlog.m3273d(str, "onLocationInitListener is:" + z);
                    if (z) {
                        handler = LaserCheckLocationActivity.this.handler;
                        i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
                        handler.removeMessages(i);
                        str2 = LaserCheckLocationActivity.this.TAG;
                        Pdlog.m3273d(str2, "onLocationInitListener is:" + z + " go to HomeMenuActivity");
                        LaserCheckLocationActivity laserCheckLocationActivity = LaserCheckLocationActivity.this;
                        laserCheckLocationActivity.jumpAndFinish(new Intent(laserCheckLocationActivity, (Class<?>) SayHiAnimationActivity.class));
                        return;
                    }
                    RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_layout);
                    Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                    slam_checking_location_layout.setVisibility(8);
                    RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.slam_checking_location_result_layout);
                    Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                    slam_checking_location_result_layout.setVisibility(0);
                    z2 = LaserCheckLocationActivity.this.isFirstTryRelocation;
                    if (z2) {
                        TextView relocation_tip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_tip, "relocation_tip");
                        Sdk27PropertiesKt.setBackgroundResource(relocation_tip, C5508R.drawable.selector_check_location_result_try_tip_btn);
                        TextView relocation_tip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_tip2, "relocation_tip");
                        relocation_tip2.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_27));
                        TextView relocation_descrip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_descrip, "relocation_descrip");
                        relocation_descrip.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_26));
                        TextView relocation_descrip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                        Intrinsics.checkExpressionValueIsNotNull(relocation_descrip2, "relocation_descrip");
                        Sdk27PropertiesKt.setTextColor(relocation_descrip2, LaserCheckLocationActivity.this.getColor(C5508R.color.white));
                        return;
                    }
                    TextView relocation_tip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip3, "relocation_tip");
                    Sdk27PropertiesKt.setBackgroundResource(relocation_tip3, C5508R.drawable.selector_check_location_result_fail_btn);
                    TextView relocation_tip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_tip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_tip4, "relocation_tip");
                    relocation_tip4.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_29));
                    TextView relocation_descrip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip3, "relocation_descrip");
                    relocation_descrip3.setText(LaserCheckLocationActivity.this.getString(C5508R.string.pdStr1_28));
                    TextView relocation_descrip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C5508R.id.relocation_descrip);
                    Intrinsics.checkExpressionValueIsNotNull(relocation_descrip4, "relocation_descrip");
                    Sdk27PropertiesKt.setTextColor(relocation_descrip4, LaserCheckLocationActivity.this.getColor(C5508R.color.red_alert));
                }
            }, 500L);
        }
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCheckLocation() {
        Pdlog.m3273d(this.TAG, "stopCheckLocation");
        this.handler.removeMessages(this.TASK_CHEACK_LOCATION);
    }

    private final void playSound() {
        VoicePlayTasks.INSTANCE.playMarkerLostLocation();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 4 && model != null && isCharging) {
            VoicePlayTasks.INSTANCE.stop();
            Pdlog.m3275i(this.TAG, "jump to Charging");
            stopCheckLocation();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void chargingFromIntent(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        i.putExtra("LOST_LOCATION_IN_LASER_CASE", true);
    }
}

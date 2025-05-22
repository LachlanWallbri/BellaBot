package com.pudutech.peanut.robot_ui.p063ui.base;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.SystemBrightManager;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0010\b&\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\n \u0005*\u0004\u0018\u00010\u00190\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0006\u0010\u001d\u001a\u00020\tJ\b\u0010\u001e\u001a\u00020\u0015H\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\u0012\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u0015H\u0014J\b\u0010$\u001a\u00020\u0015H\u0014J\b\u0010%\u001a\u00020\u0015H\u0014J\u0012\u0010&\u001a\u00020\u00172\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0006\u0010)\u001a\u00020\u0015J\b\u0010*\u001a\u00020\u0015H\u0002J\b\u0010+\u001a\u00020\u0015H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/base/SleepBaseActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handle", "Landroid/os/Handler;", "isSleepState", "", "mAnimation", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoView;", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "otherActivity", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/base/SleepBaseActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/base/SleepBaseActivity$robotSpeedListener$1;", "sleepView", "Landroid/view/View;", "closeOrOpenDevice", "", "flag", "", "getContentView", "Landroid/view/ViewGroup;", "activity", "Landroid/app/Activity;", "getRootView", "getSleepState", "handleStandByMessage", "lightTheScreen", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onStop", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "openBlackScreen", "sendSleepMessage", "sendStandByMessage", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class SleepBaseActivity extends BatteryBaseActivity {
    public static final int MESSAGE_TO_SLEEP = 1001;
    public static final int MESSAGE_TO_STANDBY = 1002;
    private HashMap _$_findViewCache;
    private volatile int isSleepState;
    private FaceVideoView mAnimation;
    private int otherActivity;
    private View sleepView;
    private final String TAG = getClass().getName();
    private final SleepBaseActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.base.SleepBaseActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                SleepBaseActivity.this.openBlackScreen();
            }
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.base.SleepBaseActivity$onFaceAnimationViewClick$1
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
            SleepBaseActivity.this.openBlackScreen();
        }
    });
    private final Handler handle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.base.SleepBaseActivity$handle$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            String str;
            String str2;
            int i = message.what;
            if (i == 1001) {
                SleepBaseActivity.this.isSleepState = 1;
                SleepBaseActivity.this.sendStandByMessage();
                str = SleepBaseActivity.this.TAG;
                Pdlog.m3273d(str, "message to sleep");
                LightPlayManager.INSTANCE.playSleep();
            } else if (i == 1002) {
                str2 = SleepBaseActivity.this.TAG;
                Pdlog.m3273d(str2, "message to standby ");
                SleepBaseActivity.this.handleStandByMessage();
            }
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater from = LayoutInflater.from(this);
        this.sleepView = from != null ? from.inflate(C5508R.layout.layout_sleep_view, (ViewGroup) null, false) : null;
        View view = this.sleepView;
        this.mAnimation = view != null ? (FaceVideoView) view.findViewById(C5508R.id.animation_view) : null;
        View view2 = this.sleepView;
        if (view2 != null) {
            ViewExtKt.onSingleClick(view2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.base.SleepBaseActivity$onCreate$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view3) {
                    invoke2(view3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    SleepBaseActivity.this.openBlackScreen();
                }
            });
        }
        FaceVideoView faceVideoView = this.mAnimation;
        if (faceVideoView != null) {
            faceVideoView.addOnFaceClickListener(this.onFaceAnimationViewClick);
        }
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
    }

    private final ViewGroup getContentView(Activity activity) {
        ViewGroup rootView = getRootView(activity);
        Intrinsics.checkExpressionValueIsNotNull(rootView, "getRootView(activity)");
        return rootView;
    }

    private final ViewGroup getRootView(Activity activity) {
        return (ViewGroup) activity.findViewById(R.id.content);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        Integer valueOf = event != null ? Integer.valueOf(event.getAction()) : null;
        if (valueOf != null) {
            valueOf.intValue();
        }
        return super.onTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.otherActivity = 0;
        this.isSleepState = 0;
        sendSleepMessage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.otherActivity = 1;
        this.handle.removeMessages(1001);
        this.handle.removeMessages(1002);
        super.onStop();
    }

    public final void openBlackScreen() {
        if (this.isSleepState != 0) {
            SleepBaseActivity sleepBaseActivity = this;
            SystemBrightManager.setSystemBrightness(sleepBaseActivity, SystemBrightManager.getSystemBrightness(sleepBaseActivity));
            lightTheScreen();
        }
        this.isSleepState = 0;
        Pdlog.m3273d(this.TAG, "openBlackScreen ");
        FaceVideoView faceVideoView = this.mAnimation;
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        this.handle.removeCallbacksAndMessages(null);
        sendSleepMessage();
    }

    private final void lightTheScreen() {
        Pdlog.m3273d(this.TAG, "###Time is " + System.currentTimeMillis() + " light to the screen");
        if (this.sleepView != null) {
            SleepBaseActivity sleepBaseActivity = this;
            ViewGroup contentView = getContentView(sleepBaseActivity);
            View view = this.sleepView;
            if (view == null) {
                Intrinsics.throwNpe();
            }
            if (contentView.indexOfChild(view) != -1) {
                getContentView(sleepBaseActivity).removeView(this.sleepView);
                LightPlayManager.INSTANCE.playNormalStatus();
            }
        }
        closeOrOpenDevice(true);
    }

    /* renamed from: getSleepState, reason: from getter */
    public final int getIsSleepState() {
        return this.isSleepState;
    }

    private final void sendSleepMessage() {
        if (this.otherActivity == 0) {
            this.handle.sendEmptyMessageDelayed(1001, 120000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStandByMessage() {
        Boolean bool;
        this.isSleepState = 2;
        this.handle.removeCallbacksAndMessages(1002);
        View view = this.sleepView;
        if (view == null) {
            return;
        }
        if (view != null) {
            bool = Boolean.valueOf(getContentView(this).indexOfChild(view) != -1);
        } else {
            bool = null;
        }
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        if (bool.booleanValue()) {
            getContentView(this).removeView(this.sleepView);
        }
        SleepBaseActivity sleepBaseActivity = this;
        getContentView(sleepBaseActivity).addView(this.sleepView);
        FaceVideoView faceVideoView = this.mAnimation;
        if (faceVideoView != null) {
            faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        }
        closeOrOpenDevice(false);
        int systemBrightness = SystemBrightManager.getSystemBrightness(sleepBaseActivity);
        Pdlog.m3273d(this.TAG, "change current bright down to 20% of it self " + systemBrightness);
        SystemBrightManager.setSystemBrightness(sleepBaseActivity, 20);
    }

    private final void closeOrOpenDevice(boolean flag) {
        ArrayList<DevicesControlHelper.DeviceName> arrayList = new ArrayList<>();
        arrayList.add(DevicesControlHelper.DeviceName.RGBD);
        if (flag) {
            Pdlog.m3273d(this.TAG, "open device LIDAR and RGBD");
            DevicesControlHelper deviceControlHelper = MirSdkManager.INSTANCE.getDeviceControlHelper();
            if (deviceControlHelper != null) {
                deviceControlHelper.openDevice(arrayList);
                return;
            }
            return;
        }
        DevicesControlHelper deviceControlHelper2 = MirSdkManager.INSTANCE.getDeviceControlHelper();
        if (deviceControlHelper2 != null) {
            deviceControlHelper2.closeDevice(arrayList);
        }
        Pdlog.m3273d(this.TAG, "close device LIDAR and RGBD");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendStandByMessage() {
        this.handle.removeMessages(1001);
        this.handle.sendEmptyMessageDelayed(1002, HardwareConfig.RGBDFwUpdateTimeOut);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handle.removeCallbacksAndMessages(null);
        if (this.sleepView != null) {
            getContentView(this).removeView(this.sleepView);
            closeOrOpenDevice(true);
            this.sleepView = (View) null;
        }
    }
}

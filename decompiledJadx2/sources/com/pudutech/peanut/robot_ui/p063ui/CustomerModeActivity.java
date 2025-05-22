package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.SolicitSettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.view.FramesSequenceAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.FaceAnimationUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomerModeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u00020\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u000bH\u0014J\b\u0010\u001c\u001a\u00020\u000bH\u0014J\b\u0010\u001d\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/CustomerModeActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "framCustomerMode", "Lcom/pudutech/peanut/robot_ui/ui/view/FramesSequenceAnimation;", "isLoadingImage", "", "isPlayingAnimation", "tipDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "initView", "", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStop", "showCustomerModeAnimation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CustomerModeActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private FramesSequenceAnimation framCustomerMode;
    private boolean isLoadingImage;
    private boolean isPlayingAnimation;
    private ShowTipMsgDialog tipDialog;

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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_customer_mode);
        initView();
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initView() {
        ImageView ivBack = (ImageView) _$_findCachedViewById(C5508R.id.ivBack);
        Intrinsics.checkExpressionValueIsNotNull(ivBack, "ivBack");
        ViewExtKt.onSingleClick(ivBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.CustomerModeActivity$initView$1
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
                Intent intent = new Intent(CustomerModeActivity.this, (Class<?>) HomeActivity.class);
                intent.putExtra(HomeActivity.BACK_TYPE_KEY, 3);
                CustomerModeActivity.this.jumpAndFinish(intent);
            }
        });
        ImageView customerSettingIv = (ImageView) _$_findCachedViewById(C5508R.id.customerSettingIv);
        Intrinsics.checkExpressionValueIsNotNull(customerSettingIv, "customerSettingIv");
        ViewExtKt.onSingleClick(customerSettingIv, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.CustomerModeActivity$initView$2
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
                CustomerModeActivity customerModeActivity = CustomerModeActivity.this;
                customerModeActivity.jump(new Intent(customerModeActivity, (Class<?>) SolicitSettingActivity.class));
            }
        });
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceAnimationView)).setFirstFrameRes(C5508R.drawable.video_customermode_home);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceAnimationView)).addOnFaceClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.CustomerModeActivity$initView$3
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
                ShowTipMsgDialog showTipMsgDialog;
                ShowTipMsgDialog showTipMsgDialog2;
                if (BatteryInfoManager.INSTANCE.isCharging()) {
                    showTipMsgDialog2 = CustomerModeActivity.this.tipDialog;
                    if (showTipMsgDialog2 != null) {
                        showTipMsgDialog2.dismiss();
                    }
                    CustomerModeActivity customerModeActivity = CustomerModeActivity.this;
                    String string = customerModeActivity.getString(C5508R.string.pdStr25_17);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
                    customerModeActivity.tipDialog = MyBaseActivity.showTipDialog$default(customerModeActivity, string, null, null, null, 14, null);
                    return;
                }
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) < 2) {
                    showTipMsgDialog = CustomerModeActivity.this.tipDialog;
                    if (showTipMsgDialog != null) {
                        showTipMsgDialog.dismiss();
                    }
                    CustomerModeActivity customerModeActivity2 = CustomerModeActivity.this;
                    String string2 = customerModeActivity2.getString(C5508R.string.pdStr2_19);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr2_19)");
                    customerModeActivity2.tipDialog = MyBaseActivity.showTipDialog$default(customerModeActivity2, string2, null, null, null, 14, null);
                    return;
                }
                CustomerModeActivity customerModeActivity3 = CustomerModeActivity.this;
                customerModeActivity3.jump(new Intent(customerModeActivity3, (Class<?>) SolicitCustomerActivity.class));
            }
        }));
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceAnimationView);
        if (faceVideoView != null) {
            faceVideoView.setState(1);
        }
        showCustomerModeAnimation();
    }

    private final void showCustomerModeAnimation() {
        FramesSequenceAnimation framesSequenceAnimation = this.framCustomerMode;
        if (framesSequenceAnimation == null) {
            if (this.isLoadingImage) {
                return;
            }
            this.isLoadingImage = true;
            FaceAnimationUtil.INSTANCE.getCacheImage(new Function1<HashMap<Integer, Bitmap>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.CustomerModeActivity$showCustomerModeAnimation$1
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
                    CustomerModeActivity customerModeActivity = CustomerModeActivity.this;
                    customerModeActivity.framCustomerMode = new FramesSequenceAnimation((ImageView) customerModeActivity._$_findCachedViewById(C5508R.id.homeRobotIv), FaceAnimationUtil.INSTANCE.getCustomerModeAnimation(), it);
                    framesSequenceAnimation2 = CustomerModeActivity.this.framCustomerMode;
                    if (framesSequenceAnimation2 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation2.setOnAnimStopListener(new FramesSequenceAnimation.OnAnimationListener() { // from class: com.pudutech.peanut.robot_ui.ui.CustomerModeActivity$showCustomerModeAnimation$1.1
                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationEnd(FramesSequenceAnimation animation) {
                            CustomerModeActivity.this.isPlayingAnimation = false;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStart(FramesSequenceAnimation animation) {
                            CustomerModeActivity.this.isPlayingAnimation = true;
                        }

                        @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStopOrCancel(FramesSequenceAnimation animation) {
                            CustomerModeActivity.this.isPlayingAnimation = false;
                        }
                    });
                    framesSequenceAnimation3 = CustomerModeActivity.this.framCustomerMode;
                    if (framesSequenceAnimation3 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation3.setDuration(1000L);
                    framesSequenceAnimation4 = CustomerModeActivity.this.framCustomerMode;
                    if (framesSequenceAnimation4 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation4.goBackStart();
                    framesSequenceAnimation5 = CustomerModeActivity.this.framCustomerMode;
                    if (framesSequenceAnimation5 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation5.setGoBack(false);
                    framesSequenceAnimation6 = CustomerModeActivity.this.framCustomerMode;
                    if (framesSequenceAnimation6 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation6.start();
                    CustomerModeActivity.this.isLoadingImage = false;
                }
            });
            return;
        }
        if (framesSequenceAnimation == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation.setDuration(1000L);
        FramesSequenceAnimation framesSequenceAnimation2 = this.framCustomerMode;
        if (framesSequenceAnimation2 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation2.goBackStart();
        FramesSequenceAnimation framesSequenceAnimation3 = this.framCustomerMode;
        if (framesSequenceAnimation3 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation3.setGoBack(false);
        FramesSequenceAnimation framesSequenceAnimation4 = this.framCustomerMode;
        if (framesSequenceAnimation4 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation4.start();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        jump(intent);
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceAnimationView)).playAnimation(SceneAnimationResources.INSTANCE.getCustomerMode());
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceAnimationView)).stopPlay();
    }
}

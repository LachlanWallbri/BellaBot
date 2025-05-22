package com.pudutech.bumblebee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.pudutech.base.FilePath;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.RobotActiveActivity;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import com.pudutech.bumblebee.robot_ui.util.permission.PermissionManager;
import com.pudutech.bumblebee.robot_ui.util.permission.PermissionResultListener;
import com.pudutech.disinfect.baselib.imageLoader.ImageLoader;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import java.io.File;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: Welcome.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\bH\u0014J\u0012\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\bH\u0002J\b\u0010\u0018\u001a\u00020\bH\u0002J\b\u0010\u0019\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/Welcome;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "TAG", "", "animatorSet", "Landroid/animation/AnimatorSet;", "displayFromSDCard", "", "logoPath", "logoImage", "Landroid/widget/ImageView;", "finish", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "requestPermissions", "showAppLogo", "showLogoAnimator", "Bumblebee-6.12.0.10-1-2023-07-12_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Welcome extends MyBaseActivity {
    private final String TAG = "Welcome";
    private HashMap _$_findViewCache;
    private AnimatorSet animatorSet;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageUtil.INSTANCE.killMapOrFactory();
        setContentView(2131558456);
        requestPermissions();
        Pdlog.m3273d(this.TAG, "onCreate");
        Pdlog.m3273d(this.TAG, "init build time:2023-07-12 05-40");
        showAppLogo();
        showLogoAnimator();
        InitAppManager.INSTANCE.init();
        NetStatusUtil.setWifiAutoOpen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        NetStatusUtil.setWifiAutoOpen();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkParameterIsNotNull(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Pdlog.m3273d(this.TAG, "onConfigurationChanged");
    }

    private final void showAppLogo() {
        if (new File(FilePath.CUSTOM_WELCOME_LOGO).exists()) {
            String str = FilePath.CUSTOM_WELCOME_LOGO;
            ImageView logo = (ImageView) _$_findCachedViewById(C3999R.id.logo);
            Intrinsics.checkExpressionValueIsNotNull(logo, "logo");
            displayFromSDCard(str, logo);
            return;
        }
        if (new File(FilePath.WELCOME_LOGO).exists()) {
            String str2 = FilePath.WELCOME_LOGO;
            ImageView logo2 = (ImageView) _$_findCachedViewById(C3999R.id.logo);
            Intrinsics.checkExpressionValueIsNotNull(logo2, "logo");
            displayFromSDCard(str2, logo2);
            return;
        }
        ((ImageView) _$_findCachedViewById(C3999R.id.logo)).setImageResource(2131231198);
    }

    private final void displayFromSDCard(String logoPath, ImageView logoImage) {
        ImageLoader.getInstance().display((ImageLoader) logoImage, Uri.parse("file://" + logoPath), true);
    }

    public final void showLogoAnimator() {
        Pdlog.m3273d(this.TAG, "showLogoAnimator");
        this.animatorSet = new AnimatorSet();
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(C3999R.id.logo), "alpha", 0.0f, 1.0f);
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(C3999R.id.logo), "ScaleX", 0.0f, 1.0f);
        final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(C3999R.id.logo), "ScaleY", 0.0f, 1.0f);
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatorSet");
        }
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        animatorSet.setDuration(2000L);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.pudutech.bumblebee.Welcome$showLogoAnimator$$inlined$run$lambda$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                String str;
                Intrinsics.checkParameterIsNotNull(animation, "animation");
                str = Welcome.this.TAG;
                Pdlog.m3273d(str, "onAnimationEnd");
                if (InitAppManager.INSTANCE.isNeedSetLanguage()) {
                    AnkoInternals.internalStartActivity(Welcome.this, RobotActiveActivity.class, new Pair[0]);
                } else {
                    AnkoInternals.internalStartActivity(Welcome.this, SelfCheckActivity.class, new Pair[0]);
                }
                Welcome.this.finish();
            }
        });
    }

    private final void requestPermissions() {
        PermissionManager.requestGroupPermission(this, new String[]{"android.permission.WRITE_SETTINGS", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.INTERNET", "android.permission.RECORD_AUDIO"}, new PermissionResultListener() { // from class: com.pudutech.bumblebee.Welcome$requestPermissions$1
            @Override // com.pudutech.bumblebee.robot_ui.util.permission.PermissionResultListener
            public final void onPermissionResult(PermissionManager.PermissionType permissionType) {
                String str;
                str = Welcome.this.TAG;
                Pdlog.m3274e(str, "requestGroupPermission = " + permissionType);
                PermissionManager.PermissionType permissionType2 = PermissionManager.PermissionType.DENIED;
            }
        });
        Application companion = BaseApplication.INSTANCE.getInstance();
        if (!(companion instanceof App)) {
            companion = null;
        }
        App app = (App) companion;
        if (app != null) {
            app.setQuestPerssionStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatorSet");
        }
        animatorSet.removeAllListeners();
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatorSet");
        }
        animatorSet2.cancel();
        AnimatorSet animatorSet3 = this.animatorSet;
        if (animatorSet3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatorSet");
        }
        animatorSet3.end();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}

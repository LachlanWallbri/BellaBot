package com.pudutech.mpmodule.p060ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jaeger.library.StatusBarUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import com.pudutech.mpmodule.utils.CountTimer;
import com.pudutech.mpmodule.utils.NavigationBar;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    private CountTimer mCountTimer;
    private Unbinder unbinder;

    protected void initData() {
    }

    protected void initWidget() {
    }

    protected void onRefresh() {
    }

    protected void onRelease() {
    }

    protected void setListeners() {
    }

    protected abstract void setRootView();

    private void setSystemUiVisibilityChangeListener() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.mpmodule.ui.BaseActivity.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    BaseActivity.this.translucent();
                }
            });
        }
    }

    private void hideNavigationBar() {
        NavigationBar.statusBarDisable(62849024, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translucent() {
        getWindow().getDecorView().setSystemUiVisibility(3846);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupTransparentStatusBar();
        supportRequestWindowFeature(1);
        setSystemUiVisibilityChangeListener();
        hideNavigationBar();
        setRootView();
        PlayerModuleActivityManager.getInstance().addActivityToStack(this);
        this.unbinder = ButterKnife.bind(this);
        initData();
        initWidget();
        setListeners();
        Pdlog.m3273d(this.TAG, "onCreate");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
        onRefresh();
        translucent();
        CountTimer countTimer = this.mCountTimer;
        if (countTimer != null) {
            countTimer.cancel();
            this.mCountTimer = null;
        }
        this.mCountTimer = new CountTimer(HardwareConfig.RGBDFwUpdateTimeOut, 100000L);
        this.mCountTimer.start();
        Pdlog.m3273d(this.TAG, "start----return home time counter----resume page.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
        this.mCountTimer.cancel();
        this.mCountTimer = null;
        Pdlog.m3273d(this.TAG, "cancel----return home time counter----pause page.");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        translucent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        Pdlog.m3273d(this.TAG, "onRestart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.unbinder.unbind();
        onRelease();
        CountTimer countTimer = this.mCountTimer;
        if (countTimer != null) {
            countTimer.cancel();
        }
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        PlayerModuleActivityManager.getInstance().removeActivityFromStack(this);
    }

    protected void setupTransparentStatusBar() {
        StatusBarUtil.setColor(this, 0);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            CountTimer countTimer = this.mCountTimer;
            if (countTimer != null) {
                countTimer.start();
                Pdlog.m3273d(this.TAG, "start----return home time counter----touchevent.");
            }
        } else {
            CountTimer countTimer2 = this.mCountTimer;
            if (countTimer2 != null) {
                countTimer2.cancel();
                Pdlog.m3273d(this.TAG, "cancel----return home time counter----touchevent.");
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}

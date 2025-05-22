package com.pudutech.importmusic.p056ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jaeger.library.StatusBarUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.C4619R;
import com.pudutech.importmusic.NavigationBar;
import com.pudutech.importmusic.utils.CountTimer;
import com.pudutech.mirsdk.hardware.HardwareConfig;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
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
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.importmusic.ui.BaseActivity.1
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
        ((TextView) findViewById(C4619R.id.connect_tip)).setText(getString(C4619R.string.pdStr10_56) + "\n" + getString(C4619R.string.pdStr10_57));
        ImPModuleActivityManager.getInstance().addActivityToStack(this);
        this.unbinder = ButterKnife.bind(this);
        initData();
        initWidget();
        setListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        onRefresh();
        translucent();
        CountTimer countTimer = this.mCountTimer;
        if (countTimer != null) {
            countTimer.cancel();
            this.mCountTimer = null;
        }
        this.mCountTimer = new CountTimer(HardwareConfig.RGBDFwUpdateTimeOut, 100000L, getApplicationContext());
        this.mCountTimer.start();
        Pdlog.m3273d(this.TAG, "start----return home time counter----resume page.");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        translucent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mCountTimer.cancel();
        this.mCountTimer = null;
        Pdlog.m3273d(this.TAG, "cancel----return home time counter----pause page.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
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
        ImPModuleActivityManager.getInstance().removeActivityFromStack(this);
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

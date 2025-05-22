package com.pudutech.mirsdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;

/* loaded from: classes5.dex */
public class DeviceInfoActivity extends AppCompatActivity {
    private static final String TAG = "DeviceInfoActivity";
    private boolean isSystemWifiSettingPage = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(TAG, "onResume");
        translatePage(this);
    }

    private void translatePage(Activity activity) {
        if (this.isSystemWifiSettingPage) {
            Pdlog.m3273d(TAG, "back and finish");
            this.isSystemWifiSettingPage = false;
            activity.finish();
        } else {
            Pdlog.m3273d(TAG, "open system wifi setting");
            this.isSystemWifiSettingPage = true;
            startActivity(new Intent("android.settings.DEVICE_INFO_SETTINGS"));
        }
    }
}

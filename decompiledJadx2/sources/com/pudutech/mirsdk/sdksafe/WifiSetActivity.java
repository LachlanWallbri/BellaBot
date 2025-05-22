package com.pudutech.mirsdk.sdksafe;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import kotlin.Metadata;

/* compiled from: WifiSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/WifiSetActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "isWifiSettingPage", "", "onResume", "", "translatePage", "activity", "Landroid/app/Activity;", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class WifiSetActivity extends AppCompatActivity {
    private final String TAG = "DeviceInfoActivity";
    private HashMap _$_findViewCache;
    private boolean isWifiSettingPage;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
        translatePage(this);
    }

    private final void translatePage(Activity activity) {
        if (this.isWifiSettingPage) {
            Pdlog.m3273d(this.TAG, "back and finish");
            this.isWifiSettingPage = false;
            activity.finish();
        } else {
            Pdlog.m3273d(this.TAG, "open system wifi setting");
            this.isWifiSettingPage = true;
            startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        }
    }
}

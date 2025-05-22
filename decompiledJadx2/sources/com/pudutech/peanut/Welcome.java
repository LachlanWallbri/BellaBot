package com.pudutech.peanut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity;
import com.pudutech.freeinstall_ui.activity.MapSelectUserActivity;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.module_robot_selfcheck.p058ui.InitActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.util.permission.PermissionManager;
import com.pudutech.peanut.robot_ui.util.permission.PermissionResultListener;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* compiled from: Welcome.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0014J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/Welcome;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "requestCode", "", "jumpAndFinish", "", "i", "Landroid/content/Intent;", "onActivityResult", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestPermissions", "BellFree-9.1.2-1-2021-11-09_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Welcome extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private final String TAG = "Welcome";
    private final int requestCode = 100;

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean z = true;
        Pdlog.m3273d(this.TAG, "onActivityResult : requestCode = " + requestCode + " , resultCode = " + resultCode);
        if (requestCode == this.requestCode) {
            if (resultCode == 4090) {
                List<MapInfo> localMapList = MapUpdateManager.INSTANCE.getLocalMapList();
                if (localMapList != null && !localMapList.isEmpty()) {
                    z = false;
                }
                if (z) {
                    jumpAndFinish(new Intent(this, (Class<?>) CreateMapNoticeActivity.class));
                    return;
                } else {
                    jumpAndFinish(new Intent(this, (Class<?>) MapSelectUserActivity.class));
                    return;
                }
            }
            if (resultCode == 4091) {
                jumpAndFinish(new Intent(this, (Class<?>) MapSelectUserActivity.class));
            } else if (resultCode == 4094) {
                jumpAndFinish(new Intent(this, (Class<?>) MapSelectUserActivity.class));
            } else {
                if (resultCode != 4095) {
                    return;
                }
                jumpAndFinish(new Intent(this, (Class<?>) MapSelectUserActivity.class));
            }
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent i) {
        startActivityForResult(i, this.requestCode);
    }

    private final void requestPermissions() {
        Pdlog.m3274e(this.TAG, "requestGroupPermission into");
        PermissionManager.requestGroupPermission(this, new String[]{"android.permission.WRITE_SETTINGS", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.INTERNET", "android.permission.RECORD_AUDIO"}, new PermissionResultListener() { // from class: com.pudutech.peanut.Welcome$requestPermissions$1
            @Override // com.pudutech.peanut.robot_ui.util.permission.PermissionResultListener
            public final void onPermissionResult(PermissionManager.PermissionType permissionType) {
                String str;
                int i;
                str = Welcome.this.TAG;
                Pdlog.m3274e(str, "requestGroupPermission = " + permissionType);
                Welcome welcome = Welcome.this;
                Intent intent = new Intent(welcome, (Class<?>) InitActivity.class);
                i = Welcome.this.requestCode;
                welcome.startActivityForResult(intent, i);
            }
        });
        App.INSTANCE.getInstance().setQuestPerssionStatus();
    }
}

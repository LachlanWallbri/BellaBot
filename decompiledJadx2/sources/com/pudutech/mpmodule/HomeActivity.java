package com.pudutech.mpmodule;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import butterknife.BindView;
import butterknife.OnClick;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.ImportMusicActivity;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.p060ui.BaseActivity;
import com.pudutech.mpmodule.permission.PermissionManager;
import com.pudutech.mpmodule.utils.ApkHelper;
import com.pudutech.mpmodule.utils.AppCommonUtil;
import com.pudutech.mpmodule.utils.SystemTool;
import com.pudutech.mpmodule.utils.TransferFilesUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class HomeActivity extends BaseActivity {

    @BindView(2131427527)
    ViewGroup mMainLayout;

    @BindView(2131427591)
    Switch mMusicSwtichBtn;
    private Switch switchConnectionPlay;
    private RxPermissions rxPermissions = new RxPermissions(this);
    private boolean isNeedKillMusicProcess = false;

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void onRefresh() {
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setRootView() {
        setContentView(C5441R.layout.module_musicplayer_activity_home);
        this.switchConnectionPlay = (Switch) findViewById(C5441R.id.switch_connection_play);
        requestMustPermissions();
    }

    private void requestMustPermissions() {
        PermissionManager.getInstance().checkPermission(this.rxPermissions, new PermissionManager.OnPermissionResultListener() { // from class: com.pudutech.mpmodule.HomeActivity.1
            @Override // com.pudutech.mpmodule.permission.PermissionManager.OnPermissionResultListener
            public void onGranted() {
                Pdlog.m3273d(HomeActivity.this.TAG, "reset and recreate database in home page");
                DatabaseManagerFactory.getDatabaseManager().initDatabase();
                DatabaseManagerFactory.getDatabaseManager().initDefaultPlaylists(HomeActivity.this);
            }

            @Override // com.pudutech.mpmodule.permission.PermissionManager.OnPermissionResultListener
            public void onUnGranted() {
                HomeActivity.this.finish();
            }
        }, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initData() {
        TransferFilesUtil.doUpgrade(this);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initWidget() {
        this.mMusicSwtichBtn.setChecked(AppCommonUtil.isOpenMusicSwitch());
        this.switchConnectionPlay.setChecked(AppCommonUtil.isOpenConnectionPlay());
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setListeners() {
        this.mMusicSwtichBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mpmodule.-$$Lambda$HomeActivity$YPaVVVdLYb-8nC2EP5y8CcWkYI8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HomeActivity.this.lambda$setListeners$0$HomeActivity(compoundButton, z);
            }
        });
        this.switchConnectionPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mpmodule.-$$Lambda$HomeActivity$4tmhPDSuih8Z6IzllXpaX7ClKhA
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HomeActivity.lambda$setListeners$1(compoundButton, z);
            }
        });
    }

    public /* synthetic */ void lambda$setListeners$0$HomeActivity(CompoundButton compoundButton, boolean z) {
        AppCommonUtil.setOpenMusicSwitch(z);
        if (z) {
            return;
        }
        Pdlog.m3273d(this.TAG, "open music switch is closed, release player.");
        MusicPlayerHelper.getInstance().release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setListeners$1(CompoundButton compoundButton, boolean z) {
        AppCommonUtil.switchConnectionPlay(z);
        MusicPlayerHelper.getInstance().resetConnectionPlayMode(z);
    }

    private void killMusicProcess() {
        if (this.isNeedKillMusicProcess) {
            int intValue = SystemTool.getPidsByPackageName(ApkHelper.PACKAGE_NAME_QQ_MUSIC).intValue();
            if (intValue != -1) {
                Pdlog.m3273d("freddy", "kill qqmusic process.");
                SystemTool.killProcessByPid(intValue);
            }
            int intValue2 = SystemTool.getPidsByPackageName(ApkHelper.PACKAGE_NAME_QQ_MUSIC_WNS).intValue();
            if (intValue2 != -1) {
                Pdlog.m3273d("freddy", "kill qqmusic:wns process.");
                SystemTool.killProcessByPid(intValue2);
            }
            int intValue3 = SystemTool.getPidsByPackageName(ApkHelper.PACKAGE_NAME_QQ_MUSIC_SERVICE).intValue();
            if (intValue3 != -1) {
                Pdlog.m3273d("freddy", "kill qqmusic:QQPlayerService process.");
                SystemTool.killProcessByPid(intValue3);
            }
            this.isNeedKillMusicProcess = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({2131427452, 2131427453, 2131427450})
    public void onClickListener(View view) {
        int id = view.getId();
        if (id == C5441R.id.cv_list_edit) {
            MusicPlayerHelper.getInstance().release();
            startActivity(new Intent(this, (Class<?>) PlaylistEditActivity.class));
        } else if (id == C5441R.id.cv_music_import) {
            startActivity(new Intent(this, (Class<?>) ImportMusicActivity.class));
        } else if (id == C5441R.id.cv_delete_music) {
            startActivity(new Intent(this, (Class<?>) DeleteMusicActivity.class));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}

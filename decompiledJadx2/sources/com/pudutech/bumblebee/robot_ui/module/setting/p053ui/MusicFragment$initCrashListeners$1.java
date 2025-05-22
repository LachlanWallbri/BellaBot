package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.importmusic.ImportMusicActivity;
import com.pudutech.importmusic.MusicHttpServer;
import com.pudutech.importmusic.NotiDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MusicFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "restartServer"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MusicFragment$initCrashListeners$1 implements ImportMusicActivity.ServerCrashListener {
    final /* synthetic */ MusicFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MusicFragment$initCrashListeners$1(MusicFragment musicFragment) {
        this.this$0 = musicFragment;
    }

    @Override // com.pudutech.importmusic.ImportMusicActivity.ServerCrashListener
    public final void restartServer() {
        MusicHttpServer musicHttpServer;
        Handler handler;
        NotiDialog notiDialog;
        MusicHttpServer musicHttpServer2;
        musicHttpServer = this.this$0.mWebserver;
        NotiDialog notiDialog2 = null;
        if (musicHttpServer != null) {
            musicHttpServer2 = this.this$0.mWebserver;
            if (musicHttpServer2 == null) {
                Intrinsics.throwNpe();
            }
            musicHttpServer2.closeAllConnections();
            this.this$0.mWebserver = (MusicHttpServer) null;
        }
        handler = this.this$0.mHandler;
        handler.removeCallbacksAndMessages(null);
        notiDialog = this.this$0.mDialog;
        if (notiDialog != null && notiDialog.isShowing()) {
            notiDialog.setOnDismissListener(null);
            notiDialog.dismiss();
        }
        MusicFragment musicFragment = this.this$0;
        Context context = musicFragment.getContext();
        if (context != null) {
            notiDialog2 = new NotiDialog(context, NotiDialog.DialogType.NOTICRASH);
            notiDialog2.setContentView(C4188R.layout.import_music_dialog_server_crash_tip);
            notiDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initCrashListeners$1$$special$$inlined$apply$lambda$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    MusicFragment$initCrashListeners$1.this.this$0.mDialog = (NotiDialog) null;
                }
            });
            notiDialog2.show();
        }
        musicFragment.mDialog = notiDialog2;
    }
}

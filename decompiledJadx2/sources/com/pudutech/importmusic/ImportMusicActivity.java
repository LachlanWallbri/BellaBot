package com.pudutech.importmusic;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.NotiDialog;
import com.pudutech.importmusic.p056ui.BaseActivity;
import com.pudutech.importmusic.utils.Constant;
import java.io.IOException;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class ImportMusicActivity extends BaseActivity {
    private ServerCrashListener mCrashListener;
    private NotiDialog mDialog;
    private Handler mHandler = new Handler() { // from class: com.pudutech.importmusic.ImportMusicActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case Constant.IMPORT_MUSIC_FLAG /* 783 */:
                    int floatValue = (int) (((Float) message.obj).floatValue() * 100.0f);
                    if (ImportMusicActivity.this.mDialog == null || ImportMusicActivity.this.mDialog.getType() != NotiDialog.DialogType.NOTIPROGRESS) {
                        ImportMusicActivity importMusicActivity = ImportMusicActivity.this;
                        importMusicActivity.mDialog = new NotiDialog(importMusicActivity, NotiDialog.DialogType.NOTIPROGRESS) { // from class: com.pudutech.importmusic.ImportMusicActivity.1.1
                            @Override // com.pudutech.importmusic.NotiDialog
                            public void setContent(int i) {
                                TextView textView = (TextView) findViewById(C4619R.id.dialog_content);
                                if (textView != null) {
                                    textView.setText(String.valueOf(i));
                                }
                            }
                        };
                        ImportMusicActivity.this.mDialog.setContentView(C4619R.layout.import_music_process_dialog_layout);
                        ImportMusicActivity.this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.importmusic.ImportMusicActivity.1.2
                            @Override // android.content.DialogInterface.OnDismissListener
                            public void onDismiss(DialogInterface dialogInterface) {
                                ImportMusicActivity.this.showSuccessDialog();
                            }
                        });
                        ImportMusicActivity.this.mDialog.show();
                    }
                    if (ImportMusicActivity.this.mDialog == null || !ImportMusicActivity.this.mDialog.isShowing()) {
                        return;
                    }
                    if (floatValue >= 100) {
                        ImportMusicActivity.this.mHandler.removeMessages(Constant.IMPORT_MUSIC_FLAG);
                        ImportMusicActivity.this.mDialog.dismiss();
                        return;
                    } else {
                        ImportMusicActivity.this.mDialog.setContent(floatValue);
                        return;
                    }
                case Constant.IMPORT_MUSIC_NAME /* 784 */:
                    ImportMusicActivity.this.mReceivedSongName = (String) message.obj;
                    return;
                case Constant.MSG_IMPORT_MUSIC_OUTOF_LEGAL_SIZE /* 785 */:
                    ImportMusicActivity.this.showOutOfLegalSizeDialog();
                    return;
                default:
                    return;
            }
        }
    };
    private String mReceivedSongName;
    private MusicHttpServer mWebserver;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ServerCrashListener {
        void restartServer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOutOfLegalSizeDialog() {
        NotiDialog notiDialog = this.mDialog;
        if (notiDialog != null && notiDialog.isShowing()) {
            this.mDialog.setOnDismissListener(null);
            this.mDialog.dismiss();
        }
        this.mDialog = new NotiDialog(this, NotiDialog.DialogType.NOTIOUTOFLEGALSIZE);
        this.mDialog.setContentView(C4619R.layout.import_music_dialog_outof_legalsize_tip);
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.importmusic.ImportMusicActivity.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ImportMusicActivity.this.mDialog = null;
            }
        });
        this.mDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuccessDialog() {
        this.mDialog = new NotiDialog(this, NotiDialog.DialogType.NOTISUCCESS) { // from class: com.pudutech.importmusic.ImportMusicActivity.3
            @Override // com.pudutech.importmusic.NotiDialog
            public void setContent(int i) {
                TextView textView = (TextView) findViewById(C4619R.id.music_name);
                if (textView != null) {
                    if (!TextUtils.isEmpty(ImportMusicActivity.this.mReceivedSongName)) {
                        textView.setText(ImportMusicActivity.this.mReceivedSongName);
                    } else {
                        textView.setVisibility(8);
                    }
                }
            }
        };
        this.mDialog.setContentView(C4619R.layout.import_music_dialog_success_tip);
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.importmusic.ImportMusicActivity.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ImportMusicActivity.this.mDialog = null;
            }
        });
        this.mDialog.setContent(0);
        this.mDialog.show();
    }

    @Override // com.pudutech.importmusic.p056ui.BaseActivity
    protected void setRootView() {
        setContentView(C4619R.layout.import_music_activity_main);
    }

    private void initCrashListeners() {
        if (this.mCrashListener == null) {
            this.mCrashListener = new ServerCrashListener() { // from class: com.pudutech.importmusic.ImportMusicActivity.5
                @Override // com.pudutech.importmusic.ImportMusicActivity.ServerCrashListener
                public void restartServer() {
                    if (ImportMusicActivity.this.mWebserver != null) {
                        ImportMusicActivity.this.mWebserver.closeAllConnections();
                        ImportMusicActivity.this.mWebserver = null;
                    }
                    ImportMusicActivity.this.mHandler.removeCallbacksAndMessages(null);
                    if (ImportMusicActivity.this.mDialog != null && ImportMusicActivity.this.mDialog.isShowing()) {
                        ImportMusicActivity.this.mDialog.setOnDismissListener(null);
                        ImportMusicActivity.this.mDialog.dismiss();
                    }
                    ImportMusicActivity importMusicActivity = ImportMusicActivity.this;
                    importMusicActivity.mDialog = new NotiDialog(importMusicActivity, NotiDialog.DialogType.NOTICRASH);
                    ImportMusicActivity.this.mDialog.setContentView(C4619R.layout.import_music_dialog_server_crash_tip);
                    ImportMusicActivity.this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.importmusic.ImportMusicActivity.5.1
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            ImportMusicActivity.this.mDialog = null;
                            ImportMusicActivity.this.finish();
                        }
                    });
                    ImportMusicActivity.this.mDialog.show();
                }
            };
        }
    }

    @Override // com.pudutech.importmusic.p056ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initCrashListeners();
        MusicHttpServer.verifyStoragePermissions(this);
        try {
            this.mWebserver = new MusicHttpServer(getApplicationContext(), this.mHandler);
        } catch (IOException e) {
            e.printStackTrace();
            Pdlog.m3273d(this.TAG, "connect music importserver fail, exception: " + e);
            notifyServerCrash();
        }
        String localIpStr = MusicHttpServer.getLocalIpStr(getApplicationContext());
        ImageView imageView = (ImageView) findViewById(C4619R.id.qr_code_view);
        Bitmap createQRCodeBitmap = QRCodeUtils.createQRCodeBitmap("http://" + localIpStr + "/pdd", DimensionsKt.XXHDPI, DimensionsKt.XXHDPI);
        if (createQRCodeBitmap == null) {
            Pdlog.m3273d(this.TAG, "get QRcode failed.");
            notifyServerCrash();
        }
        Pdlog.m3273d(this.TAG, "httpserver address: " + localIpStr);
        imageView.setImageBitmap(createQRCodeBitmap);
    }

    @Override // com.pudutech.importmusic.p056ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause...");
    }

    @Override // com.pudutech.importmusic.p056ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MusicHttpServer musicHttpServer = this.mWebserver;
        if (musicHttpServer != null) {
            musicHttpServer.closeAllConnections();
            this.mWebserver = null;
            Pdlog.m3273d(this.TAG, "app stop, so web server close");
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        NotiDialog notiDialog = this.mDialog;
        if (notiDialog == null || !notiDialog.isShowing()) {
            return;
        }
        this.mDialog.setOnDismissListener(null);
        this.mDialog.dismiss();
        this.mDialog = null;
    }

    public void notifyServerCrash() {
        ServerCrashListener serverCrashListener = this.mCrashListener;
        if (serverCrashListener != null) {
            serverCrashListener.restartServer();
        }
    }
}

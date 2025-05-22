package com.example.alsarecorder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.iflytek.alsa.AlsaRecorder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MainActivity extends Activity {
    private static final int pcm_card = 0;
    private static final int pcm_channel = 2;
    private static final int pcm_device = 0;
    private static final int pcm_format = 1;
    private static final int pcm_periodCount = 8;
    private static final int pcm_periodSize = 1536;
    private static final int pcm_sampleRate = 32000;
    Button mLog;
    PcmFileUtil mMicRawFileUtil;
    PcmFileUtil mRawFileUtil;
    Button mRecord;
    AlsaRecorder mRecorder;
    Button mStop;
    private String TAG = MainActivity.class.getSimpleName();
    boolean mShowLog = false;
    String mRawAudioDir = "/sdcard/alsaaudio/";
    String mMicRawAudioDir = "/sdcard/micrawaudio/";
    AlsaRecorder.PcmListener mPcmListener = new AlsaRecorder.PcmListener() { // from class: com.example.alsarecorder.MainActivity.1
        @Override // com.iflytek.alsa.AlsaRecorder.PcmListener
        public void onPcmData(byte[] bArr, int i) {
            Log.e(MainActivity.this.TAG, "datalen" + String.valueOf(i));
            MainActivity.this.mRawFileUtil.write(bArr, 0, i);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903040);
        Log.e(this.TAG, "初始化录音机");
        this.mRecorder = AlsaRecorder.createInstance(0, 0, 2, pcm_sampleRate, pcm_periodSize, 8, 1);
        this.mRawFileUtil = new PcmFileUtil(this.mRawAudioDir);
        this.mMicRawFileUtil = new PcmFileUtil(this.mMicRawAudioDir);
        initUI();
    }

    private void initUI() {
        this.mRecord = (Button) findViewById(2131099648);
        this.mStop = (Button) findViewById(2131099649);
        this.mLog = (Button) findViewById(2131099650);
        this.mRecord.setOnClickListener(new View.OnClickListener() { // from class: com.example.alsarecorder.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.e(MainActivity.this.TAG, "创建保存录音文件");
                MainActivity.this.mRawFileUtil.createPcmFile();
                MainActivity.this.mMicRawFileUtil.createPcmFile();
                Log.e(MainActivity.this.TAG, "开始录音");
                MainActivity.this.mRecorder.startRecording(MainActivity.this.mPcmListener);
            }
        });
        this.mStop.setOnClickListener(new View.OnClickListener() { // from class: com.example.alsarecorder.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.mRecorder.stopRecording();
                MainActivity.this.mRawFileUtil.closeWriteFile();
                MainActivity.this.mMicRawFileUtil.closeWriteFile();
                Log.e(MainActivity.this.TAG, "初始化录音机");
            }
        });
        this.mLog.setOnClickListener(new View.OnClickListener() { // from class: com.example.alsarecorder.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.mShowLog = !r2.mShowLog;
                MainActivity.this.mRecorder.setLogShow(MainActivity.this.mShowLog);
            }
        });
    }

    private byte[] addmicnumber(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length * 2];
        int i = 0;
        while (i < length / 2) {
            for (int i2 = 1; i2 < 9; i2++) {
                int i3 = i * 4;
                bArr2[i3] = 0;
                bArr2[i3 + 1] = (byte) i2;
                int i4 = i * 2;
                bArr2[i3 + 2] = bArr[i4];
                bArr2[i3 + 3] = bArr[i4 + 1];
                i++;
            }
        }
        return bArr2;
    }
}

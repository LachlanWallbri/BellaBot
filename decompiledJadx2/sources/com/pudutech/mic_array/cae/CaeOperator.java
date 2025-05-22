package com.pudutech.mic_array.cae;

import android.content.Context;
import com.iflytek.iflyos.cae.CAE;
import com.iflytek.iflyos.cae.ICAEListener;
import com.pudutech.mic_array.util.FileUtil;
import com.pudutech.mic_array.util.LogUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CaeOperator {
    public static boolean isSaveAudio = false;
    private static String mAlsaRawAudioDir = "/sdcard/vtn/cae/alsaRawAudio/";
    public static FileUtil mAlsaRawFileUtil = null;
    private static String mAlsaRecAudioDir = "/sdcard/vtn/cae/alsaRecAudio/";
    public static FileUtil mAlsaRecFileUtil = null;
    public static FileUtil mCaeOutPutFileUtil = null;
    private static String mCaeWriteAudioDir = "/sdcard/vtn/cae/caeOutPutAudio/";
    private static String mWorkDir = "/sdcard/vtn/cae";
    private OnCaeOperatorlistener caeOperatorlistener;
    private boolean mStop = true;
    private boolean mStart = true;
    ICAEListener mCAEListener = new ICAEListener() { // from class: com.pudutech.mic_array.cae.CaeOperator.1
        @Override // com.iflytek.iflyos.cae.ICAEListener
        public void onIvwAudioCallback(byte[] bArr, int i) {
        }

        @Override // com.iflytek.iflyos.cae.ICAEListener
        public void onAudioCallback(byte[] bArr, int i) {
            if (bArr == null) {
                return;
            }
            if (!CaeOperator.this.mStop || CaeOperator.this.caeOperatorlistener == null) {
                CaeOperator.this.mStop = true;
                return;
            }
            CaeOperator.this.caeOperatorlistener.onAudio(bArr, i);
            if (CaeOperator.isSaveAudio) {
                CaeOperator.mCaeOutPutFileUtil.write(bArr);
            }
        }

        public void onWakeup(String str) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("ivw");
                int optInt = optJSONObject.optInt("beam");
                int optInt2 = optJSONObject.optInt("physical");
                int optInt3 = optJSONObject.optInt("angle");
                int optInt4 = optJSONObject.optInt("score");
                String optString = optJSONObject.optString("keyword");
                LogUtils.m3297w("onWakeup", "angle: " + optInt3 + "physical:" + optInt2);
                CaeOperator.this.caeOperatorlistener.onWakeup(optJSONObject.optInt("angle"), optInt, optString, optInt4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public int initCAE(OnCaeOperatorlistener onCaeOperatorlistener, String str) {
        this.caeOperatorlistener = onCaeOperatorlistener;
        CAE.loadLib();
        int CAENew = CAE.CAENew(str, String.format("%s/%s", mWorkDir, "resources/config/vtn.ini"), this.mCAEListener);
        CAE.CAESetShowLog(0);
        wakeUpEnable(true);
        return CAENew;
    }

    public void writeAudioTest(byte[] bArr) {
        CAE.CAEAudioWrite(bArr, bArr.length);
    }

    public int setRealBeam(int i) {
        return CAE.CAESetRealBeam(i);
    }

    public static void portingFile(Context context) {
        copyAssetFolder(context, "resources", String.format("%s/resources", mWorkDir));
        mAlsaRawFileUtil = new FileUtil(mAlsaRawAudioDir);
        mAlsaRecFileUtil = new FileUtil(mAlsaRecAudioDir);
        mCaeOutPutFileUtil = new FileUtil(mCaeWriteAudioDir);
    }

    public void startSaveAudio() {
        isSaveAudio = true;
        mAlsaRawFileUtil.createPcmFile();
        mAlsaRecFileUtil.createPcmFile();
        mCaeOutPutFileUtil.createPcmFile();
    }

    public void stopSaveAudio() {
        isSaveAudio = false;
        mAlsaRawFileUtil.closeWriteFile();
        mAlsaRecFileUtil.closeWriteFile();
        mCaeOutPutFileUtil.closeWriteFile();
    }

    public void saveAduio(byte[] bArr, FileUtil fileUtil) {
        if (isSaveAudio) {
            fileUtil.write(bArr);
        }
    }

    public void stop() {
        this.mStop = false;
    }

    public static boolean copyAssetFolder(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list == null) {
                return false;
            }
            if (list.length == 0) {
                return copyAssetFile(context, str, str2);
            }
            boolean mkdirs = new File(str2).mkdirs();
            for (String str3 : list) {
                mkdirs &= copyAssetFolder(context, str + File.separator + str3, str2 + File.separator + str3);
            }
            return mkdirs;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyAssetFile(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str);
            File file = new File(str2);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAudioSaving() {
        return isSaveAudio;
    }

    public int wakeUpEnable(boolean z) {
        return CAE.CAESetParams(z ? 1 : 0, null);
    }
}

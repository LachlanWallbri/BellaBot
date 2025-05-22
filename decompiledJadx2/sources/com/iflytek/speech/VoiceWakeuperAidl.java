package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.aidl.IWakeuper;

/* loaded from: classes3.dex */
public class VoiceWakeuperAidl extends SpeechModuleAidl<IWakeuper> {
    public static final String ARG_RES_PROVIDER_AUTHORITY = "content_provider_name";
    public static final String ARG_RES_TYPE = "engine_res_type";
    public static final String IVP_RES_ID = "ivp_res_id";
    public static final String IVP_USER_NAME = "ivp_user_name";
    public static final String IVW_AND_IVP = "ivw_and_ivp";
    public static final String IVW_FILES = "ivw_files";
    public static final String IVW_THRESHOLD = "ivw_threshold";
    public static final String IVW_WORD_ID = "ivw_word_id";
    public static final String PARAMS_SEPARATE = ";";
    public static final String PROVIDER_NAME = "com.iflytek.speech.SharedProvider";
    public static final int RES_FROM_ASSETS = 257;
    public static final int RES_FROM_CLIENT = 259;
    public static final int RES_SPECIFIED = 258;
    public static final String WAKEUP_RESULT_ID = "wakeup_result_id";
    public static final String WAKEUP_RESULT_SCORE = "wakeup_result_Score";

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public /* bridge */ /* synthetic */ boolean destory() {
        return super.destory();
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public /* bridge */ /* synthetic */ Intent getIntent() {
        return super.getIntent();
    }

    @Override // com.iflytek.speech.SpeechModuleAidl
    public /* bridge */ /* synthetic */ boolean isActionInstalled(Context context, String str) {
        return super.isActionInstalled(context, str);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public VoiceWakeuperAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_SPEECH_WAKEUP);
    }

    public int startListening(WakeuperListener wakeuperListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (wakeuperListener == null) {
            return 20012;
        }
        try {
            ((IWakeuper) this.mService).startListening(getIntent(), wakeuperListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int cancel(WakeuperListener wakeuperListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (wakeuperListener == null) {
            return 20012;
        }
        try {
            ((IWakeuper) this.mService).cancel(wakeuperListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public boolean destroy() {
        if (this.mService == 0) {
            return false;
        }
        try {
            ((IWakeuper) this.mService).destroy();
        } catch (RemoteException e) {
            DebugLog.LogE(e);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return super.destory();
    }

    public boolean isListening() {
        try {
            return ((IWakeuper) this.mService).isListening();
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public String getParameter(String str) {
        return super.getParameter(str);
    }
}

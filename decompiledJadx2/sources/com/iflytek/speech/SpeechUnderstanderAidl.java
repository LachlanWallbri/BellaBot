package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.aidl.ISpeechUnderstander;

/* loaded from: classes3.dex */
public class SpeechUnderstanderAidl extends SpeechModuleAidl<ISpeechUnderstander> {
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

    public SpeechUnderstanderAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
    }

    public int startUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).startUnderstanding(getIntent(), speechUnderstanderListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).stopUnderstanding(speechUnderstanderListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public boolean isUnderstanding() {
        try {
            if (this.mService != 0) {
                return ((ISpeechUnderstander) this.mService).isUnderstanding();
            }
            return false;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int cancel(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).cancel(speechUnderstanderListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
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

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (SpeechUtility.getUtility().getServiceVersion() < 44) {
            return 20018;
        }
        if (this.mService == 0) {
            return 21003;
        }
        try {
            ((ISpeechUnderstander) this.mService).writeAudio(getIntent(), bArr, i, i2);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}

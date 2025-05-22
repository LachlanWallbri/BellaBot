package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.aidl.ISpeechSynthesizer;

/* loaded from: classes3.dex */
public class SpeechSynthesizerAidl extends SpeechModuleAidl<ISpeechSynthesizer> {
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

    public SpeechSynthesizerAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
    }

    public int synthesizeToUrl(String str, SynthesizerListener synthesizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("text", str);
            return ((ISpeechSynthesizer) this.mService).synthesizeToUrl(intent, synthesizerListener);
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int startSpeaking(String str, SynthesizerListener synthesizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("text", str);
            return ((ISpeechSynthesizer) this.mService).startSpeaking(intent, synthesizerListener);
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int pauseSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).pauseSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int resumeSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).resumeSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).stopSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public boolean isSpeaking() {
        try {
            if (this.mService != 0) {
                return ((ISpeechSynthesizer) this.mService).isSpeaking();
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

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public String getParameter(String str) {
        int serviceVersion;
        if (str.equals(SpeechConstant.LOCAL_SPEAKERS)) {
            try {
                if (SpeechUtility.getUtility() != null && (serviceVersion = SpeechUtility.getUtility().getServiceVersion()) >= 44 && (serviceVersion < 10000 || serviceVersion >= 10013)) {
                    return ((ISpeechSynthesizer) this.mService).getLocalSpeakerList();
                }
                return null;
            } catch (RemoteException e) {
                DebugLog.LogE(e);
                return "20999";
            }
        }
        return super.getParameter(str);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public boolean destory() {
        this.mService = null;
        return super.destory();
    }
}

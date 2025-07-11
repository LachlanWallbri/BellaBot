package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.aidl.ISpeechRecognizer;

/* loaded from: classes3.dex */
public class SpeechRecognizerAidl extends SpeechModuleAidl<ISpeechRecognizer> {
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

    public SpeechRecognizerAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_SPEECH_RECOGNIZER);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.speech.SpeechModuleAidl, com.iflytek.speech.ISpeechModule
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public int startListening(RecognizerListener recognizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).startListening(getIntent(), recognizerListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopListening(RecognizerListener recognizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).stopListening(recognizerListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int cancel(RecognizerListener recognizerListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).cancel(recognizerListener);
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
    public boolean destory() {
        this.mService = null;
        return super.destory();
    }

    public boolean isListening() {
        try {
            if (this.mService != 0) {
                return ((ISpeechRecognizer) this.mService).isListening();
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

    public int buildGrammar(String str, String str2, GrammarListener grammarListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (grammarListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra(SpeechConstant.GRAMMAR_TYPE, str);
            intent.putExtra("grammar_content", str2);
            ((ISpeechRecognizer) this.mService).buildGrammar(intent, grammarListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int updateLexicon(String str, String str2, LexiconListener lexiconListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (lexiconListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("lexicon_name", str);
            intent.putExtra("lexicon_content", str2);
            ((ISpeechRecognizer) this.mService).updateLexicon(intent, lexiconListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (SpeechUtility.getUtility().getServiceVersion() < 44) {
            return 20018;
        }
        if (this.mService == 0) {
            return 21003;
        }
        try {
            ((ISpeechRecognizer) this.mService).writeAudio(getIntent(), bArr, i, i2);
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

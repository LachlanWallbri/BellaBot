package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.aidl.ITextUnderstander;

/* loaded from: classes3.dex */
public class TextUnderstanderAidl extends SpeechModuleAidl<ITextUnderstander> {
    public static final String SCENE = "scene";
    private static final String TEXT = "text";

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

    public TextUnderstanderAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_TEXT_UNDERSTANDER);
    }

    public boolean isUnderstanding() {
        try {
            if (this.mService != 0) {
                return ((ITextUnderstander) this.mService).isUnderstanding();
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

    public int cancel(TextUnderstanderListener textUnderstanderListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (textUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ITextUnderstander) this.mService).cancel(textUnderstanderListener);
            return 0;
        } catch (RemoteException e) {
            DebugLog.LogE(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int understandText(String str, TextUnderstanderListener textUnderstanderListener) {
        if (this.mService == 0) {
            return 21003;
        }
        if (textUnderstanderListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("text", str);
            ((ITextUnderstander) this.mService).understandText(intent, textUnderstanderListener);
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
}

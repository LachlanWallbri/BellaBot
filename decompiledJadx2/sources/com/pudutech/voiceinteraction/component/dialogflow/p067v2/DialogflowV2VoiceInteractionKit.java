package com.pudutech.voiceinteraction.component.dialogflow.p067v2;

import android.content.Context;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.interf.IVoiceInteraction;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DialogflowV2VoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016JB\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016J(\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00110 j\b\u0012\u0004\u0012\u00020\u0011`!H\u0016J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u0011H\u0016J\b\u0010(\u001a\u00020\u0004H\u0016J3\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00010\t2\b\u0010,\u001a\u0004\u0018\u00010\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0016¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020\u0004H\u0016J\u0018\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020$2\u0006\u0010#\u001a\u00020$H\u0016¨\u00063"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/v2/DialogflowV2VoiceInteractionKit;", "Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "()V", "cancelCurrentRound", "", "cancelTTS", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "init", "", "context", "Landroid/content/Context;", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "pid", "", SpeechConstant.ISV_VID, "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "onlyWakeup", "b", "postAction", "actionString", "reConnectAIUI", "release", "resetCAE", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setRealBeam", "beam", "", "setSpeaker", "speaker", "languageCode", "startRecording", "startTts", "text", "fromTts", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;)V", "stopRecording", "wakeup", "angle", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DialogflowV2VoiceInteractionKit implements IVoiceInteraction {
    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelCurrentRound() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelTTS() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public boolean init(Context context, Language language, IVoiceInteractionListener onVoiceInteractionListener, String pid, String vid, MicArray micArray, boolean onlyWakeup) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Intrinsics.checkParameterIsNotNull(micArray, "micArray");
        return false;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void onlyWakeup(boolean b) {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void release() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void resetCAE() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setIntentObject(IntentObjectType type, ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setRealBeam(int beam) {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setSpeaker(String speaker, String languageCode) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(languageCode, "languageCode");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startRecording() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startTts(String text, Boolean fromTts, String filePath, OnTtsListener onTtsListener) {
        Intrinsics.checkParameterIsNotNull(text, "text");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void stopRecording() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void wakeup(int angle, int beam) {
    }

    private DialogflowV2VoiceInteractionKit() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void copyGoogleDialogflowConfigToSDCard(Context context, String folderPath, String fileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        IVoiceInteraction.DefaultImpls.copyGoogleDialogflowConfigToSDCard(this, context, folderPath, fileName);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void copyWakeupWordsToSDCard(Context context, String folderPath, String fileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        IVoiceInteraction.DefaultImpls.copyWakeupWordsToSDCard(this, context, folderPath, fileName);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void reConnectAIUI() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public NLPActionType getNLPActionType() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }
}

package com.pudutech.voiceinteraction.component.interf;

import android.content.Context;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.utils.FileUtil;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IVoiceInteraction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J \u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\u000eH&JB\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0010H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\tH&J\b\u0010\u001e\u001a\u00020\u0003H&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&J(\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#2\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\t0%j\b\u0012\u0004\u0012\u00020\t`&H&J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H&J\u0018\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH&J\b\u0010,\u001a\u00020\u0003H&J9\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\t2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u00100\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00101\u001a\u0004\u0018\u000102H&¢\u0006\u0002\u00103J\b\u00104\u001a\u00020\u0003H&J\u0018\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020)2\u0006\u0010(\u001a\u00020)H&¨\u00067"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "", "cancelCurrentRound", "", "cancelTTS", "copyGoogleDialogflowConfigToSDCard", "context", "Landroid/content/Context;", "folderPath", "", "fileName", "copyToSDCard", "copyWakeupWordsToSDCard", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "init", "", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "pid", SpeechConstant.ISV_VID, "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "onlyWake", "onlyWakeup", "b", "postAction", "actionString", "reConnectAIUI", "release", "resetCAE", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setRealBeam", "beam", "", "setSpeaker", "speaker", "startRecording", "startTts", "text", "fromTts", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;)V", "stopRecording", "wakeup", "angle", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface IVoiceInteraction {
    void cancelCurrentRound();

    void cancelTTS();

    void copyGoogleDialogflowConfigToSDCard(Context context, String folderPath, String fileName);

    void copyWakeupWordsToSDCard(Context context, String folderPath, String fileName);

    NLPActionType getNLPActionType();

    boolean init(Context context, Language language, IVoiceInteractionListener onVoiceInteractionListener, String pid, String vid, MicArray micArray, boolean onlyWake);

    void onlyWakeup(boolean b);

    void postAction(String actionString);

    void reConnectAIUI();

    void release();

    void resetCAE();

    void setIntentObject(IntentObjectType type, ArrayList<String> data);

    void setRealBeam(int beam);

    void setSpeaker(String speaker, String language);

    void startRecording();

    void startTts(String text, Boolean fromTts, String filePath, OnTtsListener onTtsListener);

    void stopRecording();

    void wakeup(int angle, int beam);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: IVoiceInteraction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void startTts$default(IVoiceInteraction iVoiceInteraction, String str, Boolean bool, String str2, OnTtsListener onTtsListener, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startTts");
            }
            if ((i & 2) != 0) {
                bool = false;
            }
            if ((i & 4) != 0) {
                str2 = (String) null;
            }
            if ((i & 8) != 0) {
                onTtsListener = (OnTtsListener) null;
            }
            iVoiceInteraction.startTts(str, bool, str2, onTtsListener);
        }

        private static void copyToSDCard(IVoiceInteraction iVoiceInteraction, Context context, String str, String str2) {
            if (FileUtil.INSTANCE.isAssetsFileExists(context, "", str2)) {
                File file = new File((str + File.separator) + str2);
                if (file.exists()) {
                    file.delete();
                }
                File file2 = new File(str);
                if (!file2.exists() || !file2.isDirectory()) {
                    file2.mkdirs();
                }
                FileUtil fileUtil = FileUtil.INSTANCE;
                String path = file.getPath();
                Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
                Pdlog.m3273d("IVoiceInteraction", "copyToSDCard() success = " + fileUtil.copyAssetsFileToSDCard(context, str2, path));
            }
        }

        public static void copyWakeupWordsToSDCard(IVoiceInteraction iVoiceInteraction, Context context, String folderPath, String fileName) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
            Intrinsics.checkParameterIsNotNull(fileName, "fileName");
            copyToSDCard(iVoiceInteraction, context, folderPath, fileName);
        }

        public static void copyGoogleDialogflowConfigToSDCard(IVoiceInteraction iVoiceInteraction, Context context, String folderPath, String fileName) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
            Intrinsics.checkParameterIsNotNull(fileName, "fileName");
            copyToSDCard(iVoiceInteraction, context, folderPath, fileName);
        }
    }
}

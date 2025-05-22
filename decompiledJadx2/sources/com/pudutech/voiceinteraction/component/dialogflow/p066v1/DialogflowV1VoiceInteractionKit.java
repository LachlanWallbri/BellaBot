package com.pudutech.voiceinteraction.component.dialogflow.p066v1;

import android.content.Context;
import android.util.Log;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.p049v2.AudioEncoding;
import com.google.cloud.dialogflow.p049v2.DetectIntentRequest;
import com.google.cloud.dialogflow.p049v2.DetectIntentResponse;
import com.google.cloud.dialogflow.p049v2.InputAudioConfig;
import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.cloud.dialogflow.p049v2.QueryInput;
import com.google.cloud.dialogflow.p049v2.QueryResult;
import com.google.cloud.dialogflow.p049v2.SessionName;
import com.google.cloud.dialogflow.p049v2.SessionsClient;
import com.google.cloud.dialogflow.p049v2.SessionsSettings;
import com.google.cloud.texttospeech.p050v1.AudioConfig;
import com.google.cloud.texttospeech.p050v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.p050v1.SynthesisInput;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.p050v1.TextToSpeechClient;
import com.google.cloud.texttospeech.p050v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.p050v1.VoiceSelectionParams;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mic_array.mic.IAudioListener;
import com.pudutech.mic_array.mic.IWakeupListener;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.voiceinteraction.component.C5767R;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer;
import com.pudutech.voiceinteraction.component.dialogflow.OnLowVolumeCheckCallback;
import com.pudutech.voiceinteraction.component.dialogflow.VolumeCheckTask;
import com.pudutech.voiceinteraction.component.interf.IVoiceInteraction;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.utils.AudioConvertTool;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import com.pudutech.voiceinteraction.component.utils.SystemTool;
import com.wzc.vad.VadUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DialogflowV1VoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t*\u0002\u0014\u0017\u0018\u0000 f2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020+H\u0016J\u0010\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020\fH\u0002J\b\u0010/\u001a\u00020+H\u0002J\b\u00100\u001a\u00020+H\u0016J\b\u00101\u001a\u000202H\u0016J\n\u00103\u001a\u0004\u0018\u00010\bH\u0002J&\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020\b2\b\u00106\u001a\u0004\u0018\u00010\b2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\bH\u0002JB\u00108\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u00109\u001a\u00020\u000eH\u0016J\b\u0010:\u001a\u00020\u000eH\u0002J\b\u0010;\u001a\u00020+H\u0016J\b\u0010<\u001a\u00020+H\u0016J\b\u0010=\u001a\u00020+H\u0016J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020\fH\u0016J\u0010\u00109\u001a\u00020+2\u0006\u0010@\u001a\u00020\u000eH\u0016J\u0010\u0010A\u001a\u00020+2\u0006\u00107\u001a\u00020\bH\u0016J\u0010\u0010B\u001a\u00020+2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020+H\u0002J\b\u0010F\u001a\u00020+H\u0016J\b\u0010G\u001a\u00020+H\u0016J\b\u0010H\u001a\u00020+H\u0016J\b\u0010I\u001a\u00020+H\u0002J\u0010\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020DH\u0002J(\u0010N\u001a\u00020+2\u0006\u0010O\u001a\u00020P2\u0016\u0010C\u001a\u0012\u0012\u0004\u0012\u00020\b0Qj\b\u0012\u0004\u0012\u00020\b`RH\u0016J\u0010\u0010S\u001a\u00020+2\u0006\u0010T\u001a\u00020\fH\u0016J\u0018\u0010U\u001a\u00020+2\u0006\u0010V\u001a\u00020\b2\u0006\u0010W\u001a\u00020\bH\u0016J\b\u0010X\u001a\u00020+H\u0016J3\u0010Y\u001a\u00020+2\u0006\u0010Z\u001a\u00020\b2\b\u0010[\u001a\u0004\u0018\u00010\u000e2\b\u0010\\\u001a\u0004\u0018\u00010\b2\b\u0010]\u001a\u0004\u0018\u00010^H\u0016¢\u0006\u0002\u0010_J\u0006\u0010`\u001a\u00020+J\b\u0010a\u001a\u00020+H\u0016J\u0012\u0010b\u001a\u00020+2\b\u0010Z\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010c\u001a\u00020+H\u0016J\u0018\u0010d\u001a\u00020+2\u0006\u0010e\u001a\u00020\f2\u0006\u0010T\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006g"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit;", "Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "Lcom/pudutech/voiceinteraction/component/dialogflow/OnLowVolumeCheckCallback;", "Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer$OnPlayStateChangedListener;", "()V", "context", "Landroid/content/Context;", "defaultSpeaker", "", "gson", "Lcom/google/gson/Gson;", "hasAvailVoiceCount", "", "isStopProcess", "", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "lastHasVoiceRecodeTime", "", "mAudioListener", "com/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit$mAudioListener$1", "Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit$mAudioListener$1;", "mWakeupListener", "com/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit$mWakeupListener$1", "Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit$mWakeupListener$1;", "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "mutex", "", "getMutex", "()Ljava/lang/Object;", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "pid", "recodingTime", "recordBuffer", "Ljava/nio/ByteBuffer;", "vadUtils", "Lcom/wzc/vad/VadUtils;", SpeechConstant.ISV_VID, "volumeCheckTask", "Lcom/pudutech/voiceinteraction/component/dialogflow/VolumeCheckTask;", "cancelCurrentRound", "", "cancelTTS", "checkBufferSize", Name.LENGTH, "clearWakeup", "finish", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "getWIFIMac", "httpText", "asw", SpeechUtility.TAG_RESOURCE_RESULT, "actionString", "init", "onlyWakeup", "initRecoding", "onPlayError", "onPlayFinished", "onPlaying", "onVolumeChange", "int", "b", "postAction", "processRecordData", "data", "", "queryByAudioFile", "reConnectAIUI", "release", "resetCAE", "resetVad", "saveToAmrFile", "pcmFile", "Ljava/io/File;", "saveToPcmFile", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setRealBeam", "beam", "setSpeaker", "speaker", "languageCode", "startRecording", "startTts", "text", "fromTts", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;)V", "stopProcess", "stopRecording", "textToSpeech", "timeout", "wakeup", "angle", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DialogflowV1VoiceInteractionKit implements IVoiceInteraction, OnLowVolumeCheckCallback, CMediaPlayer.OnPlayStateChangedListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String DEFAULT_RES_PATH = "res_path=";
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DialogflowV1VoiceInteractionKit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DialogflowV1VoiceInteractionKit invoke() {
            return new DialogflowV1VoiceInteractionKit(null);
        }
    });
    private static final String OUTPUT_AMR_FILE_NAME = "output_amr_wb.amr";
    private static final String OUTPUT_FILE_DIRECTORY = "/sdcard/pudu/files/dialogflow";
    private static final String OUTPUT_MP3_FILE_NAME = "output_mp3.mp3";
    private static final String OUTPUT_PCM_FILE_NAME = "output_pcm.pcm";
    private static final String TAG = "DialogflowV1VoiceInteractionKit";
    private static final String WAKEUP_WORDS_FOLDER_PATH = "/sdcard/pudu/wakeupwords";
    private static boolean copied = false;
    public static final String tinyMix = "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2";
    private Context context;
    private String defaultSpeaker;
    private final Gson gson;
    private volatile int hasAvailVoiceCount;
    private boolean isStopProcess;
    private Language language;
    private volatile long lastHasVoiceRecodeTime;
    private DialogflowV1VoiceInteractionKit$mAudioListener$1 mAudioListener;
    private DialogflowV1VoiceInteractionKit$mWakeupListener$1 mWakeupListener;
    private MicArray micArray;
    private final Object mutex;
    private IVoiceInteractionListener onVoiceInteractionListener;
    private String pid;
    private volatile long recodingTime;
    private ByteBuffer recordBuffer;
    private final VadUtils vadUtils;
    private String vid;
    private final VolumeCheckTask volumeCheckTask;

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void onlyWakeup(boolean b) {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setIntentObject(IntentObjectType type, ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$mWakeupListener$1] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$mAudioListener$1] */
    private DialogflowV1VoiceInteractionKit() {
        this.pid = "";
        this.vid = "";
        this.defaultSpeaker = "en-GB-Wavenet-F";
        ByteBuffer allocate = ByteBuffer.allocate(1048576);
        Intrinsics.checkExpressionValueIsNotNull(allocate, "ByteBuffer.allocate(1024 * 1024)");
        this.recordBuffer = allocate;
        this.volumeCheckTask = new VolumeCheckTask(this);
        this.gson = new Gson();
        this.mutex = new Object();
        this.vadUtils = new VadUtils();
        Pdlog.m3273d(TAG, "volumeCheckTask started.");
        this.volumeCheckTask.start();
        this.mWakeupListener = new IWakeupListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$mWakeupListener$1
            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeMessage(byte[] audioData) {
                VolumeCheckTask volumeCheckTask;
                ByteBuffer byteBuffer;
                if (audioData != null) {
                    volumeCheckTask = DialogflowV1VoiceInteractionKit.this.volumeCheckTask;
                    volumeCheckTask.updateData(audioData);
                    DialogflowV1VoiceInteractionKit.this.checkBufferSize(audioData.length);
                    synchronized (DialogflowV1VoiceInteractionKit.this.getMutex()) {
                        byteBuffer = DialogflowV1VoiceInteractionKit.this.recordBuffer;
                        byteBuffer.put(audioData);
                    }
                }
            }

            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeSuccess(int p0, int p1, String p2, int p3) {
                DialogflowV1VoiceInteractionKit.this.wakeup(p0, p1);
            }
        };
        this.mAudioListener = new IAudioListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$mAudioListener$1
            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onRawData(byte[] p0) {
            }

            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onSingleData(byte[] p0) {
            }
        };
        this.isStopProcess = true;
    }

    public /* synthetic */ DialogflowV1VoiceInteractionKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
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

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: DialogflowV1VoiceInteractionKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit$Companion;", "", "()V", "DEFAULT_RES_PATH", "", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/dialogflow/v1/DialogflowV1VoiceInteractionKit;", "INSTANCE$delegate", "Lkotlin/Lazy;", "OUTPUT_AMR_FILE_NAME", "OUTPUT_FILE_DIRECTORY", "OUTPUT_MP3_FILE_NAME", "OUTPUT_PCM_FILE_NAME", "TAG", "WAKEUP_WORDS_FOLDER_PATH", "copied", "", "tinyMix", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final DialogflowV1VoiceInteractionKit getINSTANCE() {
            Lazy lazy = DialogflowV1VoiceInteractionKit.INSTANCE$delegate;
            Companion companion = DialogflowV1VoiceInteractionKit.INSTANCE;
            return (DialogflowV1VoiceInteractionKit) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Object getMutex() {
        return this.mutex;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public boolean init(Context context, Language language, IVoiceInteractionListener onVoiceInteractionListener, String pid, String vid, MicArray micArray, boolean onlyWakeup) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Intrinsics.checkParameterIsNotNull(micArray, "micArray");
        Pdlog.m3273d(TAG, "init() context = " + context + ", onVoiceInteractionListener = " + onVoiceInteractionListener);
        this.context = context;
        this.language = language;
        this.pid = pid;
        this.vid = vid;
        this.onVoiceInteractionListener = onVoiceInteractionListener;
        this.vadUtils.create();
        int init = this.vadUtils.init();
        if (init == 0) {
            this.vadUtils.setMode(0);
        }
        Pdlog.m3273d(TAG, "vad init = " + init);
        this.micArray = micArray;
        return initRecoding();
    }

    private final boolean initRecoding() {
        Integer num;
        int recodeType = VoiceCommentConfig.INSTANCE.getRecodeType();
        if (recodeType != 1 && recodeType == 2) {
            try {
                if (!copied) {
                    Context context = this.context;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    copyWakeupWordsToSDCard(context, WAKEUP_WORDS_FOLDER_PATH, VoiceCommentConfig.INSTANCE.getEnAceWakeupWorkAssetName());
                    copied = true;
                }
                MicArray micArray = this.micArray;
                if (micArray != null) {
                    num = Integer.valueOf(micArray.replaceWakeup(((DEFAULT_RES_PATH + WAKEUP_WORDS_FOLDER_PATH) + File.separator) + VoiceCommentConfig.INSTANCE.getEnAceWakeupWorkAssetName()));
                } else {
                    num = null;
                }
                if (num != null && num.intValue() == 0) {
                    Pdlog.m3273d(TAG, "replaceWakeup success");
                    MicArray micArray2 = this.micArray;
                    Integer valueOf = micArray2 != null ? Integer.valueOf(micArray2.initSDK(this.pid, this.vid, "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2", getWIFIMac())) : null;
                    if (valueOf != null && valueOf.intValue() == 0) {
                        Pdlog.m3273d(TAG, "initSDK success");
                    }
                    Pdlog.m3274e(TAG, "initSDK failse  initCode = " + valueOf);
                    return false;
                }
                Pdlog.m3273d(TAG, "replaceWakeup failse replaceCode = " + num);
                return false;
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "initRecoding Exception: " + Log.getStackTraceString(e));
                return false;
            }
        }
        return true;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startRecording() {
        try {
            Pdlog.m3273d(TAG, "startRecording()");
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.startRecord(this.mWakeupListener, this.mAudioListener);
            }
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, "startRecording Exception: " + Log.getStackTraceString(th));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void stopRecording() {
        try {
            Pdlog.m3273d(TAG, "stopRecording()");
            this.volumeCheckTask.inactive();
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.stopRecord();
            }
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, "stopRecording Exception: " + Log.getStackTraceString(th));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void wakeup(int angle, int beam) {
        try {
            Pdlog.m3273d(TAG, "wakeup() angle = " + angle + ", beam = " + beam);
            CMediaPlayer.INSTANCE.getINSTANCE().release();
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.startWakeup(0);
            }
            this.isStopProcess = true;
            this.volumeCheckTask.active();
            resetVad();
            this.recodingTime = System.currentTimeMillis();
            this.hasAvailVoiceCount = 0;
            synchronized (this.mutex) {
                this.recordBuffer.clear();
            }
            IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener != null) {
                iVoiceInteractionListener.onWakeup(new WakeupInfo(angle, beam, null, null, 12, null));
            }
            IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener2 != null) {
                iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Recording);
            }
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, "wakeup Exception: " + Log.getStackTraceString(th));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setRealBeam(int beam) {
        MicArray micArray = this.micArray;
        if (micArray != null) {
            micArray.startWakeup(0);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void resetCAE() {
        Pdlog.m3273d(TAG, "resetCAE()");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void reConnectAIUI() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void release() {
        Pdlog.m3273d(TAG, "release()");
        this.volumeCheckTask.inactive();
        this.vadUtils.free();
        this.micArray = (MicArray) null;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startTts(String text, Boolean fromTts, String filePath, OnTtsListener onTtsListener) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        textToSpeech(text);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setSpeaker(String speaker, String languageCode) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(languageCode, "languageCode");
        this.defaultSpeaker = speaker;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelTTS() {
        CMediaPlayer.INSTANCE.getINSTANCE().stop();
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelCurrentRound() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public NLPActionType getNLPActionType() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.OnLowVolumeCheckCallback
    public void finish() {
        Pdlog.m3273d(TAG, "finish()");
        INSTANCE.getINSTANCE().clearWakeup();
        resetVad();
        synchronized (this.mutex) {
            byte[] bArr = new byte[this.recordBuffer.capacity() - this.recordBuffer.remaining()];
            this.recordBuffer.flip();
            this.recordBuffer.get(bArr, 0, bArr.length);
            this.recordBuffer.clear();
            this.isStopProcess = false;
            processRecordData(bArr);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.OnLowVolumeCheckCallback
    public void timeout() {
        Pdlog.m3273d(TAG, "timeout()");
        INSTANCE.getINSTANCE().clearWakeup();
        synchronized (this.mutex) {
            this.recordBuffer.clear();
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.BosTimeout);
        }
        resetVad();
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.OnLowVolumeCheckCallback
    public void onVolumeChange(int r2) {
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onVolumeChanged(r2);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer.OnPlayStateChangedListener
    public void onPlaying() {
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Speaking);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer.OnPlayStateChangedListener
    public void onPlayFinished() {
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.PlayCompleted);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer.OnPlayStateChangedListener
    public void onPlayError() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    private final void resetVad() {
        this.recodingTime = 0L;
        this.lastHasVoiceRecodeTime = 0L;
        this.hasAvailVoiceCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void checkBufferSize(int length) {
        synchronized (this.mutex) {
            int capacity = this.recordBuffer.capacity();
            int remaining = this.recordBuffer.remaining();
            if (remaining < length) {
                byte[] bArr = new byte[capacity - remaining];
                this.recordBuffer.flip();
                this.recordBuffer.get(bArr, 0, bArr.length);
                ByteBuffer allocate = ByteBuffer.allocate(this.recordBuffer.capacity() * 2);
                Intrinsics.checkExpressionValueIsNotNull(allocate, "ByteBuffer.allocate(recordBuffer.capacity() * 2)");
                this.recordBuffer = allocate;
                this.recordBuffer.put(bArr);
                Pdlog.m3273d(TAG, "checkBufferSize() 扩容成功 原缓冲区容量：" + capacity + ", 扩容后缓冲区容量：" + this.recordBuffer.capacity());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void processRecordData(byte[] data) {
        Pdlog.m3273d(TAG, "processRecordData() data.size " + data.length + ", isStopProcess " + this.isStopProcess);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DialogflowV1VoiceInteractionKit$processRecordData$1(this, data, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean saveToPcmFile(byte[] data) {
        try {
            File file = new File((OUTPUT_FILE_DIRECTORY + File.separator) + OUTPUT_PCM_FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileOutputStream fileOutputStream2 = fileOutputStream;
            Throwable th = (Throwable) null;
            try {
                FileOutputStream fileOutputStream3 = fileOutputStream2;
                fileOutputStream.write(data);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream2, th);
                Pdlog.m3273d(TAG, "saveToPcmFile() success, length " + file.length() + " path " + file.getPath());
                return true;
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean saveToAmrFile(File pcmFile) {
        File file = new File((OUTPUT_FILE_DIRECTORY + File.separator) + OUTPUT_AMR_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        AudioConvertTool.pcm2Amrwb(file, new FileInputStream(pcmFile));
        return file.length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x02ec, code lost:
    
        r0 = r14.onVoiceInteractionListener;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x02ee, code lost:
    
        if (r0 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x02f0, code lost:
    
        r0.onStatusChanged(com.pudutech.voiceinteraction.component.config.VoiceInteractionState.BosTimeout);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02dd A[Catch: all -> 0x030a, TryCatch #1 {all -> 0x030a, blocks: (B:27:0x00fa, B:29:0x0111, B:30:0x0116, B:33:0x0129, B:34:0x01e6, B:36:0x022f, B:37:0x0232, B:41:0x023a, B:43:0x0293, B:44:0x0296, B:46:0x02d1, B:51:0x02dd, B:53:0x02e2, B:58:0x02ec, B:60:0x02f0, B:63:0x02f8, B:65:0x0133, B:66:0x013d, B:67:0x0147, B:68:0x014f, B:69:0x0157, B:70:0x0161, B:71:0x016b, B:72:0x0173, B:73:0x017d, B:74:0x0186, B:75:0x018d, B:76:0x0196, B:77:0x019f, B:78:0x01a8, B:79:0x01b1, B:80:0x01ba, B:81:0x01c3, B:82:0x01cc, B:83:0x01d5, B:84:0x01de), top: B:26:0x00fa, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void queryByAudioFile() {
        boolean z;
        boolean z2 = true;
        Pdlog.m3273d(TAG, "queryByAudioFile() isStopProcess " + this.isStopProcess);
        SystemTool systemTool = SystemTool.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        if (!systemTool.isConnected(context)) {
            Pdlog.m3274e(TAG, "Network is unavailable");
            IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.ErrorNetWork);
                return;
            }
            return;
        }
        if (this.isStopProcess) {
            return;
        }
        File file = new File((OUTPUT_FILE_DIRECTORY + File.separator) + OUTPUT_AMR_FILE_NAME);
        if (!file.exists() || !file.isFile()) {
            return;
        }
        try {
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            InputStream openRawResource = context2.getResources().openRawResource(C5767R.raw.googlekey);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource, "context.resources.openRawResource(R.raw.googlekey)");
            GoogleCredentials fromStream = GoogleCredentials.fromStream(openRawResource);
            Intrinsics.checkExpressionValueIsNotNull(fromStream, "GoogleCredentials.fromStream(stream)");
            if (fromStream != null) {
                String projectId = ((ServiceAccountCredentials) fromStream).getProjectId();
                Intrinsics.checkExpressionValueIsNotNull(projectId, "(credentials as ServiceA…untCredentials).projectId");
                SessionsSettings.Builder newBuilder = SessionsSettings.newBuilder();
                Intrinsics.checkExpressionValueIsNotNull(newBuilder, "SessionsSettings.newBuilder()");
                SessionsSettings build = newBuilder.setCredentialsProvider(FixedCredentialsProvider.create(fromStream)).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "settingsBuilder.setCrede…                 .build()");
                SessionName m589of = SessionName.m589of(projectId, UUID.randomUUID().toString());
                Pdlog.m3273d(TAG, "Session Path:" + m589of);
                SessionsClient create = SessionsClient.create(build);
                Throwable th = (Throwable) null;
                try {
                    SessionsClient it = create;
                    InputAudioConfig.Builder inputAudioConfigBuilder = InputAudioConfig.newBuilder().setAudioEncoding(AudioEncoding.AUDIO_ENCODING_AMR_WB).setSampleRateHertz(16000);
                    Language language = this.language;
                    if (language == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("language");
                    }
                    switch (language) {
                        case Japanese:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("ja-JP");
                            break;
                        case ChineseHk:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("zh-HK");
                            break;
                        case Korean:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("ko-KR");
                            break;
                        case Dutch:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("nl-NL");
                            break;
                        case Spanish:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("es-ES");
                            break;
                        case French:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("fr-FR");
                            break;
                        case Italian:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("it-IT");
                            break;
                        case Russian:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("ru-RU");
                            break;
                        case German:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("de-DE");
                            break;
                        case EuropeanPortuguese:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("pt-PT");
                            break;
                        case Poland:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("pl-PL");
                            break;
                        case Czech:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("cs-CZ");
                            break;
                        case PortugueseBr:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("pt-BR");
                            break;
                        case Turkish:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("tr-TR");
                            break;
                        case Arabic:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("ar-SA");
                            break;
                        case Brazilian:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("pt-BR");
                            break;
                        case Portuguese:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("pt-PT");
                            break;
                        case Thai:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("th-TH");
                            break;
                        case Indonesia:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("id-ID");
                            break;
                        case Sweden:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("sv-SE");
                            break;
                        default:
                            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
                            inputAudioConfigBuilder.setLanguageCode("en-US");
                            break;
                    }
                    DetectIntentResponse response = it.detectIntent(DetectIntentRequest.newBuilder().setSession(m589of.toString()).setQueryInput(QueryInput.newBuilder().setAudioConfig(inputAudioConfigBuilder.build()).build()).setInputAudio(ByteString.copyFrom(Files.readAllBytes(Paths.get(file.getPath(), new String[0])))).build());
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (!it.isShutdown()) {
                        it.shutdownNow();
                    }
                    if (this.isStopProcess) {
                        AutoCloseableKt.closeFinally(create, th);
                        return;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(response, "response");
                    QueryResult queryResult = response.getQueryResult();
                    Intrinsics.checkExpressionValueIsNotNull(queryResult, "queryResult");
                    String requestText = queryResult.getQueryText();
                    String fulfillmentText = queryResult.getFulfillmentText();
                    Pdlog.m3273d(TAG, "=====================");
                    Pdlog.m3273d(TAG, "Query Text: '" + requestText + "'\n");
                    Pdlog.m3273d(TAG, "Fulfillment Text: '" + fulfillmentText + "'\n");
                    IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener2 != null) {
                        iVoiceInteractionListener2.onResultRequest(requestText, true);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Detected Intent: ");
                    Intent intent = queryResult.getIntent();
                    Intrinsics.checkExpressionValueIsNotNull(intent, "queryResult.intent");
                    sb.append(intent.getDisplayName());
                    sb.append(" (confidence: ");
                    sb.append(queryResult.getIntentDetectionConfidence());
                    sb.append(")\n");
                    Pdlog.m3273d(TAG, sb.toString());
                    String str = requestText;
                    if (str != null && str.length() != 0) {
                        z = false;
                        if (z) {
                            String str2 = fulfillmentText;
                            if (str2 != null && str2.length() != 0) {
                                z2 = false;
                            }
                        }
                        Intrinsics.checkExpressionValueIsNotNull(requestText, "requestText");
                        httpText$default(this, requestText, fulfillmentText, null, 4, null);
                        Unit unit = Unit.INSTANCE;
                        AutoCloseableKt.closeFinally(create, th);
                    }
                    z = true;
                    if (z) {
                    }
                    Intrinsics.checkExpressionValueIsNotNull(requestText, "requestText");
                    httpText$default(this, requestText, fulfillmentText, null, 4, null);
                    Unit unit2 = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(create, th);
                } finally {
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.google.auth.oauth2.ServiceAccountCredentials");
            }
        } catch (Exception e) {
            IVoiceInteractionListener iVoiceInteractionListener3 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener3 != null) {
                iVoiceInteractionListener3.onStatusChanged(VoiceInteractionState.ErrorNetWork);
            }
            e.printStackTrace();
        }
    }

    private final void textToSpeech(String text) {
        Pdlog.m3273d(TAG, "textToSpeech() isStopProcess " + this.isStopProcess);
        if (this.isStopProcess) {
            return;
        }
        String str = text;
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            InputStream openRawResource = context.getResources().openRawResource(C5767R.raw.googlekey);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource, "context.resources.openRawResource(R.raw.googlekey)");
            GoogleCredentials fromStream = GoogleCredentials.fromStream(openRawResource);
            Intrinsics.checkExpressionValueIsNotNull(fromStream, "GoogleCredentials.fromStream(stream)");
            if (fromStream != null) {
                String projectId = ((ServiceAccountCredentials) fromStream).getProjectId();
                Intrinsics.checkExpressionValueIsNotNull(projectId, "(credentials as ServiceA…untCredentials).projectId");
                TextToSpeechSettings.Builder newBuilder = TextToSpeechSettings.newBuilder();
                Intrinsics.checkExpressionValueIsNotNull(newBuilder, "TextToSpeechSettings.newBuilder()");
                TextToSpeechSettings build = newBuilder.setCredentialsProvider(FixedCredentialsProvider.create(fromStream)).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "settingBuilder.setCreden…                 .build()");
                Log.d(TAG, "Session Path:" + SessionName.m589of(projectId, UUID.randomUUID().toString()));
                TextToSpeechClient create = TextToSpeechClient.create(build);
                Throwable th = (Throwable) null;
                try {
                    TextToSpeechClient it = create;
                    SynthesisInput build2 = SynthesisInput.newBuilder().setText(text).build();
                    Intrinsics.checkExpressionValueIsNotNull(build2, "SynthesisInput.newBuilde…                 .build()");
                    VoiceSelectionParams.Builder voiceBuilder = VoiceSelectionParams.newBuilder().setSsmlGender(SsmlVoiceGender.FEMALE);
                    Language language = this.language;
                    if (language == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("language");
                    }
                    switch (language) {
                        case Japanese:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("ja-JP-Wavenet-A");
                            voiceBuilder.setLanguageCode("ja-JP");
                            break;
                        case ChineseHk:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("yue-HK-Wavenet-A");
                            voiceBuilder.setLanguageCode("yue-HK");
                            break;
                        case Dutch:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("nl-NL-Wavenet-A");
                            voiceBuilder.setLanguageCode("nl-NL");
                            break;
                        case Korean:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("ko-KR-Wavenet-A");
                            voiceBuilder.setLanguageCode("ko-KR");
                            break;
                        case Spanish:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("es-ES-Standard-A");
                            voiceBuilder.setLanguageCode("es-ES");
                            break;
                        case French:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("fr-FR-Wavenet-A");
                            voiceBuilder.setLanguageCode("fr-FR");
                            break;
                        case Italian:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("it-IT-Wavenet-A");
                            voiceBuilder.setLanguageCode("it-IT");
                            break;
                        case Russian:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("ru-RU-Wavenet-C");
                            voiceBuilder.setLanguageCode("ru-RU");
                            break;
                        case German:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("de-DE-Wavenet-F");
                            voiceBuilder.setLanguageCode("de-DE");
                            break;
                        case PortugueseBr:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("pt-BR-Wavenet-A");
                            voiceBuilder.setLanguageCode("pt-BR");
                            break;
                        case Portuguese:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("pt-PT-Wavenet-A");
                            voiceBuilder.setLanguageCode("pt-PT");
                            break;
                        case EuropeanPortuguese:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("pt-PT-Wavenet-A");
                            voiceBuilder.setLanguageCode("pt-PT");
                            break;
                        case Poland:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("pl-PL-Wavenet-D");
                            voiceBuilder.setLanguageCode("pl-PL");
                            break;
                        case Turkish:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("tr-TR-Wavenet-D");
                            voiceBuilder.setLanguageCode("tr-TR");
                            break;
                        case Arabic:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("ar-SA-Wavenet-A");
                            voiceBuilder.setLanguageCode("ar-SA");
                            break;
                        case Czech:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("cs-CZ-Wavenet-A");
                            voiceBuilder.setLanguageCode("cs-CZ");
                            break;
                        case Brazilian:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("pt-BR-Wavenet-A");
                            voiceBuilder.setLanguageCode("pt-BR");
                            break;
                        case Thai:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("th-TH-Standard-A");
                            voiceBuilder.setLanguageCode("th-TH");
                            break;
                        case Indonesia:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("id-ID-Wavenet-D");
                            voiceBuilder.setLanguageCode("id-ID");
                            break;
                        case Sweden:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName("sv-SE-Wavenet-A");
                            voiceBuilder.setLanguageCode("sv-SE");
                            break;
                        default:
                            Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                            voiceBuilder.setName(this.defaultSpeaker);
                            voiceBuilder.setLanguageCode("en-US");
                            break;
                    }
                    SynthesizeSpeechResponse response = it.synthesizeSpeech(build2, voiceBuilder.build(), AudioConfig.newBuilder().setAudioEncoding(com.google.cloud.texttospeech.p050v1.AudioEncoding.MP3).build());
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (!it.isShutdown()) {
                        it.shutdownNow();
                    }
                    if (this.isStopProcess) {
                        AutoCloseableKt.closeFinally(create, th);
                        return;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(response, "response");
                    ByteString audioContent = response.getAudioContent();
                    File file = new File(OUTPUT_FILE_DIRECTORY);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File((file.getPath() + File.separator) + OUTPUT_MP3_FILE_NAME);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2.getPath());
                    Throwable th2 = (Throwable) null;
                    try {
                        fileOutputStream.write(audioContent.toByteArray());
                        Pdlog.m3273d(TAG, "Audio content written to file \"output_mp3.mp3\"");
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, th2);
                        CMediaPlayer.INSTANCE.getINSTANCE().play(file2, this);
                        Unit unit2 = Unit.INSTANCE;
                        AutoCloseableKt.closeFinally(create, th);
                    } finally {
                    }
                } finally {
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.google.auth.oauth2.ServiceAccountCredentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void clearWakeup() {
        this.volumeCheckTask.inactive();
    }

    public final void stopProcess() {
        this.isStopProcess = true;
        CMediaPlayer.INSTANCE.getINSTANCE().release();
        clearWakeup();
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    Pdlog.m3273d(TAG, "onCmdChanged " + sb.toString());
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static /* synthetic */ void httpText$default(DialogflowV1VoiceInteractionKit dialogflowV1VoiceInteractionKit, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        dialogflowV1VoiceInteractionKit.httpText(str, str2, str3);
    }

    private final void httpText(String asw, final String result, String actionString) {
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            OkHttpUtils.INSTANCE.post(wIFIMac, asw, result != null ? result : "", (r13 & 8) != 0 ? "" : actionString, (r13 & 16) != 0 ? "" : null);
        }
        OkHttpUtils.INSTANCE.setTtsClick(new Function4<String, String, Integer, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$httpText$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, String str3) {
                invoke(str, str2, num.intValue(), str3);
                return Unit.INSTANCE;
            }

            public final void invoke(String str, String str2, int i, String str3) {
                IVoiceInteractionListener iVoiceInteractionListener;
                String str4;
                IVoiceInteractionListener iVoiceInteractionListener2;
                IVoiceInteractionListener iVoiceInteractionListener3;
                String str5 = str2;
                if (!(str5 == null || str5.length() == 0)) {
                    if (VoiceInteractionKit.INSTANCE.getType() == 0 && i != 2 && (str4 = result) != null) {
                        IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV1VoiceInteractionKit.this, str4, null, null, null, 14, null);
                    }
                    iVoiceInteractionListener = DialogflowV1VoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener != null) {
                        iVoiceInteractionListener.onResultResponse("", str2, i);
                        return;
                    }
                    return;
                }
                if (str != null) {
                    if (i == 0) {
                        IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV1VoiceInteractionKit.this, str, null, null, null, 14, null);
                    }
                    iVoiceInteractionListener3 = DialogflowV1VoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener3 != null) {
                        iVoiceInteractionListener3.onResultResponse(str, str2, i);
                        return;
                    }
                    return;
                }
                String str6 = result;
                if (str6 != null) {
                    IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV1VoiceInteractionKit.this, str6, null, null, null, 14, null);
                }
                iVoiceInteractionListener2 = DialogflowV1VoiceInteractionKit.this.onVoiceInteractionListener;
                if (iVoiceInteractionListener2 != null) {
                    iVoiceInteractionListener2.onResultResponse(result, str2, i);
                }
            }
        });
        OkHttpUtils.INSTANCE.setTtsError(new Function2<String, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$httpText$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String err, String str) {
                IVoiceInteractionListener iVoiceInteractionListener;
                Intrinsics.checkParameterIsNotNull(err, "err");
                String str2 = result;
                if (str2 != null) {
                    IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV1VoiceInteractionKit.this, str2, null, null, null, 14, null);
                }
                iVoiceInteractionListener = DialogflowV1VoiceInteractionKit.this.onVoiceInteractionListener;
                if (iVoiceInteractionListener != null) {
                    iVoiceInteractionListener.onResultResponse(result, null, 0);
                }
            }
        });
    }
}

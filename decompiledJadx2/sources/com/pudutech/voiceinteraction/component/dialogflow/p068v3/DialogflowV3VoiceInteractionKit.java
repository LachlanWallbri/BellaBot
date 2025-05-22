package com.pudutech.voiceinteraction.component.dialogflow.p068v3;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.BidiStream;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.p049v2.InputAudioConfig;
import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.cloud.dialogflow.p049v2.QueryInput;
import com.google.cloud.dialogflow.p049v2.QueryResult;
import com.google.cloud.dialogflow.p049v2.SessionName;
import com.google.cloud.dialogflow.p049v2.SessionsClient;
import com.google.cloud.dialogflow.p049v2.SessionsSettings;
import com.google.cloud.dialogflow.p049v2.StreamingDetectIntentRequest;
import com.google.cloud.dialogflow.p049v2.StreamingDetectIntentResponse;
import com.google.cloud.dialogflow.p049v2.StreamingRecognitionResult;
import com.google.cloud.texttospeech.p050v1.AudioConfig;
import com.google.cloud.texttospeech.p050v1.AudioEncoding;
import com.google.cloud.texttospeech.p050v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.p050v1.SynthesisInput;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.p050v1.TextToSpeechClient;
import com.google.cloud.texttospeech.p050v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.p050v1.VoiceSelectionParams;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.vad.sdk.Vad;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mic_array.mic.IAudioListener;
import com.pudutech.mic_array.mic.IWakeupListener;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.voiceinteraction.component.C5767R;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.cmd.AiCloudAdapter;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer;
import com.pudutech.voiceinteraction.component.dialogflow.p068v3.DialogflowV3VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.interf.IVoiceInteraction;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import com.pudutech.voiceinteraction.component.utils.AudioTrackUtils;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import com.pudutech.voiceinteraction.component.utils.SystemTool;
import com.pudutech.voiceinteraction.component.utils.UrlOkManager;
import com.pudutech.voiceinteraction.component.utils.VadTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: DialogflowV3VoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004*\u000227\u0018\u0000 £\u00012\u00020\u00012\u00020\u0002:\u0002£\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010V\u001a\u00020WJ\b\u0010X\u001a\u00020WH\u0016J\b\u0010Y\u001a\u00020WH\u0016J\b\u0010Z\u001a\u00020WH\u0002J\b\u0010[\u001a\u00020WH\u0002J\b\u0010\\\u001a\u00020WH\u0002J\b\u0010]\u001a\u00020WH\u0002J\b\u0010^\u001a\u00020LH\u0002J\b\u0010_\u001a\u00020LH\u0002J\b\u0010`\u001a\u00020aH\u0016J\b\u0010b\u001a\u00020\u0005H\u0002J\n\u0010c\u001a\u0004\u0018\u00010\u0005H\u0002J&\u0010d\u001a\u00020W2\u0006\u0010e\u001a\u00020\u00052\b\u0010f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u0005H\u0002J\u0012\u0010h\u001a\u00020W2\b\u0010i\u001a\u0004\u0018\u00010jH\u0002JB\u0010k\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-2\b\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010C\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00052\u0006\u0010:\u001a\u00020;2\u0006\u0010B\u001a\u00020%H\u0016J\b\u0010l\u001a\u00020WH\u0002J\b\u0010m\u001a\u00020WH\u0002J\b\u0010n\u001a\u00020WH\u0002J\b\u0010o\u001a\u00020WH\u0002J\b\u0010p\u001a\u00020WH\u0016J\b\u0010q\u001a\u00020WH\u0016J\b\u0010r\u001a\u00020WH\u0016J\u0018\u0010s\u001a\u00020W2\u0006\u0010t\u001a\u00020\u00052\u0006\u0010u\u001a\u00020vH\u0002J\u000e\u0010w\u001a\u00020W2\u0006\u0010x\u001a\u00020\u000bJ\u0010\u0010B\u001a\u00020W2\u0006\u0010y\u001a\u00020%H\u0016J\u0010\u0010z\u001a\u00020W2\u0006\u0010g\u001a\u00020\u0005H\u0016J\u0010\u0010{\u001a\u00020W2\u0006\u0010|\u001a\u00020vH\u0002J\b\u0010}\u001a\u00020WH\u0016J\b\u0010~\u001a\u00020WH\u0016J\b\u0010\u007f\u001a\u00020WH\u0016J\t\u0010\u0080\u0001\u001a\u00020WH\u0002J\u0011\u0010\u0081\u0001\u001a\u00020W2\u0006\u0010|\u001a\u00020vH\u0002J\u0011\u0010\u0082\u0001\u001a\u00020W2\u0006\u0010|\u001a\u00020vH\u0002J-\u0010\u0083\u0001\u001a\u00020W2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0018\u0010|\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0086\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0087\u0001H\u0016J\u0012\u0010\u0088\u0001\u001a\u00020W2\u0007\u0010\u0089\u0001\u001a\u00020\u000bH\u0016J\u001a\u0010\u008a\u0001\u001a\u00020W2\u0007\u0010\u008b\u0001\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\t\u0010\u008c\u0001\u001a\u00020WH\u0016J:\u0010\u008d\u0001\u001a\u00020W2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010%2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u00052\n\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0001J\u0007\u0010\u0094\u0001\u001a\u00020WJ\t\u0010\u0095\u0001\u001a\u00020WH\u0016J\u0014\u0010\u0096\u0001\u001a\u00020W2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0005H\u0002J\u0007\u0010\u0097\u0001\u001a\u00020WJ\u001c\u0010\u0098\u0001\u001a\u00020W2\u0011\u0010\u0099\u0001\u001a\f\u0012\u0005\u0012\u00030\u009b\u0001\u0018\u00010\u009a\u0001H\u0002J\u0013\u0010\u009c\u0001\u001a\u00020WH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009d\u0001J\u0013\u0010\u009e\u0001\u001a\u00020W2\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0002J\u001b\u0010¡\u0001\u001a\u00020W2\u0007\u0010¢\u0001\u001a\u00020\u000b2\u0007\u0010\u0089\u0001\u001a\u00020\u000bH\u0016R\u0010\u0010\u0004\u001a\u00020\u00058\u0002X\u0083D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000500X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0004\n\u0002\u00103R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u000207X\u0082\u000e¢\u0006\u0004\n\u0002\u00108R\u0010\u00109\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020LX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020LX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010T\u001a\n \u0014*\u0004\u0018\u00010U0UX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006¤\u0001"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit;", "Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer$OnPlayStateChangedListener;", "()V", "DIALOG_FLOW_CONFIG_DIRECTORY", "", "DIALOG_FLOW_SERVER_CONFIG_NAME", "LANGUAGE_CONFIG_NAME", "LOCK", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "WAKE_UP_CLOSE", "", "aiCloudAdapter", "Lcom/pudutech/voiceinteraction/component/cmd/AiCloudAdapter;", "getAiCloudAdapter", "()Lcom/pudutech/voiceinteraction/component/cmd/AiCloudAdapter;", "aiCloudAdapter$delegate", "Lkotlin/Lazy;", "audioConfig", "Lcom/google/cloud/texttospeech/v1/AudioConfig;", "kotlin.jvm.PlatformType", "bidiStream", "Lcom/google/api/gax/rpc/BidiStream;", "Lcom/google/cloud/dialogflow/v2/StreamingDetectIntentRequest;", "Lcom/google/cloud/dialogflow/v2/StreamingDetectIntentResponse;", "client", "Lcom/google/cloud/dialogflow/v2/SessionsClient;", "clientJob", "Lkotlinx/coroutines/Job;", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "credentials", "Lcom/google/auth/oauth2/GoogleCredentials;", "defaultSpeaker", "dialogflowActive", "", "gson", "Lcom/google/gson/Gson;", "isBella", "isBosTimeOut", "isStopProcess", "isSwift", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "languageCode", "languageMap", "", "mAudioListener", "com/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit$mAudioListener$1", "Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit$mAudioListener$1;", "mVadListener", "Lcom/iflytek/aiui/vad/sdk/Vad$VadListener;", "mWakeupListener", "com/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit$mWakeupListener$1", "Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit$mWakeupListener$1;", "macStr", "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "mutex", "", "getMutex", "()Ljava/lang/Object;", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "onlyWakeup", "pid", "projectId", "recordBuffer", "Ljava/nio/ByteBuffer;", "sessionsSettings", "Lcom/google/cloud/dialogflow/v2/SessionsSettings;", "singelThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "stream", "Ljava/io/InputStream;", "textToSpeechSettings", "Lcom/google/cloud/texttospeech/v1/TextToSpeechSettings;", "ttsClient", "Lcom/google/cloud/texttospeech/v1/TextToSpeechClient;", "ttsStream", "vadActive", SpeechConstant.ISV_VID, "voiceBuilder", "Lcom/google/cloud/texttospeech/v1/VoiceSelectionParams$Builder;", "bosTimeout", "", "cancelCurrentRound", "cancelTTS", "clearWakeup", "closeReceive", "closeSend", "createClient", "getGoogleServiceConfig", "getGoogleTTSConfig", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "getVioceConfigName", "getWIFIMac", "httpText", "asw", SpeechUtility.TAG_RESOURCE_RESULT, "actionString", "iflyWakeup", "wakeupInfo", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "init", "initClient", "initDialogflow", "initTTS", "onErrNetWork", "onPlayError", "onPlayFinished", "onPlaying", "onVadData", AIUIConstant.KEY_TAG, TypedValues.Attributes.S_FRAME, "", "onVolumeChange", "int", "b", "postAction", "queryByData", "data", "reConnectAIUI", "release", "resetCAE", "resetVad", "sendFinish", "sendStart", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setRealBeam", "beam", "setSpeaker", "speaker", "startRecording", "startTts", "text", "fromTts", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;)V", "stopProcess", "stopRecording", "textToSpeech", "timeout", "updateAiCloud", "cmd", "", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$RasaAnswer;", "updateLanguageConfig", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateState", "state", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "wakeup", "angle", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DialogflowV3VoiceInteractionKit implements IVoiceInteraction, CMediaPlayer.OnPlayStateChangedListener {
    private static final String DIALOG_FLOW_SERVER_CONFIG_FOLDER_PATH = "/sdcard/pudu/decoration/dialogflowdef";
    private static final String TAG = "DialogflowV3VoiceInteractionKit";
    private final String DIALOG_FLOW_CONFIG_DIRECTORY;
    private final String DIALOG_FLOW_SERVER_CONFIG_NAME;
    private final String LANGUAGE_CONFIG_NAME;
    private final ReentrantReadWriteLock LOCK;
    private final int WAKE_UP_CLOSE;

    /* renamed from: aiCloudAdapter$delegate, reason: from kotlin metadata */
    private final Lazy aiCloudAdapter;
    private final AudioConfig audioConfig;
    private volatile BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream;
    private SessionsClient client;
    private Job clientJob;
    private Context context;
    private CoroutineScope coroutineScope;
    private GoogleCredentials credentials;
    private String defaultSpeaker;
    private volatile boolean dialogflowActive;
    private final Gson gson;
    private boolean isBella;
    private volatile boolean isBosTimeOut;
    private boolean isStopProcess;
    private boolean isSwift;
    private Language language;
    private String languageCode;
    private final Map<String, String> languageMap;
    private DialogflowV3VoiceInteractionKit$mAudioListener$1 mAudioListener;
    private Vad.VadListener mVadListener;
    private DialogflowV3VoiceInteractionKit$mWakeupListener$1 mWakeupListener;
    private String macStr;
    private MicArray micArray;
    private final Object mutex;
    private IVoiceInteractionListener onVoiceInteractionListener;
    private volatile boolean onlyWakeup;
    private String pid;
    private String projectId;
    private ByteBuffer recordBuffer;
    private SessionsSettings sessionsSettings;
    private final ExecutorCoroutineDispatcher singelThreadContext;
    private InputStream stream;
    private TextToSpeechSettings textToSpeechSettings;
    private TextToSpeechClient ttsClient;
    private InputStream ttsStream;
    private volatile boolean vadActive;
    private String vid;
    private final VoiceSelectionParams.Builder voiceBuilder;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DialogflowV3VoiceInteractionKit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DialogflowV3VoiceInteractionKit invoke() {
            return new DialogflowV3VoiceInteractionKit(null);
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AudioTrackUtils.AudioPlayEvent.values().length];

        static {
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.STOP.ordinal()] = 1;
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.COMPLETE.ordinal()] = 2;
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.PLAYING.ordinal()] = 3;
        }
    }

    private final void closeReceive() {
    }

    private final AiCloudAdapter getAiCloudAdapter() {
        return (AiCloudAdapter) this.aiCloudAdapter.getValue();
    }

    private final void resetVad() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void reConnectAIUI() {
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setIntentObject(IntentObjectType type, ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
    }

    /* JADX WARN: Type inference failed for: r0v33, types: [com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$mWakeupListener$1] */
    /* JADX WARN: Type inference failed for: r0v36, types: [com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$mAudioListener$1] */
    private DialogflowV3VoiceInteractionKit() {
        this.pid = "";
        this.vid = "";
        this.defaultSpeaker = "en-GB-Wavenet-F";
        this.languageCode = "en-US";
        ByteBuffer allocate = ByteBuffer.allocate(1048576);
        Intrinsics.checkExpressionValueIsNotNull(allocate, "ByteBuffer.allocate(1024 * 1024)");
        this.recordBuffer = allocate;
        this.gson = new Gson();
        this.mutex = new Object();
        this.singelThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("Dialogflow");
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(this.singelThreadContext);
        this.WAKE_UP_CLOSE = 102;
        this.audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16).build();
        this.voiceBuilder = VoiceSelectionParams.newBuilder().setSsmlGender(SsmlVoiceGender.FEMALE);
        this.aiCloudAdapter = LazyKt.lazy(new Function0<AiCloudAdapter>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$aiCloudAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AiCloudAdapter invoke() {
                return new AiCloudAdapter();
            }
        });
        this.DIALOG_FLOW_CONFIG_DIRECTORY = "/sdcard/pudu/custom/dialogflowconfig";
        this.DIALOG_FLOW_SERVER_CONFIG_NAME = "service_config.json";
        this.LANGUAGE_CONFIG_NAME = "language_config.json";
        this.isBella = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot");
        this.isSwift = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "SwiftBot");
        this.languageMap = new LinkedHashMap();
        this.LOCK = new ReentrantReadWriteLock();
        this.mWakeupListener = new IWakeupListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$mWakeupListener$1
            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeMessage(byte[] audioData) {
                boolean z;
                if (audioData != null) {
                    z = DialogflowV3VoiceInteractionKit.this.vadActive;
                    if (z) {
                        VadTool.INSTANCE.detect(audioData);
                    }
                }
            }

            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeSuccess(int p0, int p1, String p2, int p3) {
                boolean z = true;
                Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "onWakeSuccess  angle = " + p0 + "  beam = " + p1 + "  keyword = " + p2 + "  score = " + p3);
                String str = p2;
                if (str != null && !StringsKt.isBlank(str)) {
                    z = false;
                }
                if (z) {
                    DialogflowV3VoiceInteractionKit.this.iflyWakeup(new WakeupInfo(p0, p1, p2, Integer.valueOf(p3)));
                    return;
                }
                Iterator<String> it = VoiceCommentConfig.INSTANCE.getEnWakeupList().iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(it.next(), p2)) {
                        DialogflowV3VoiceInteractionKit.this.iflyWakeup(new WakeupInfo(p0, p1, p2, Integer.valueOf(p3)));
                        return;
                    }
                }
            }
        };
        this.mVadListener = new Vad.VadListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$mVadListener$1
            @Override // com.iflytek.aiui.vad.sdk.Vad.VadListener
            public void onAuthSuccess() {
                Pdlog.m3273d(VadTool.INSTANCE.getTAG(), "VadTool Auth Success");
            }

            /* JADX WARN: Code restructure failed: missing block: B:24:0x009c, code lost:
            
                r3 = r2.this$0.onVoiceInteractionListener;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x00c8, code lost:
            
                r3 = r2.this$0.onVoiceInteractionListener;
             */
            @Override // com.iflytek.aiui.vad.sdk.Vad.VadListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onResult(int status, byte[] frame, int frameLen, int vol) {
                boolean z;
                IVoiceInteractionListener iVoiceInteractionListener;
                boolean z2;
                IVoiceInteractionListener iVoiceInteractionListener2;
                boolean z3;
                IVoiceInteractionListener iVoiceInteractionListener3;
                Intrinsics.checkParameterIsNotNull(frame, "frame");
                DialogflowV3VoiceInteractionKit.this.onVolumeChange(vol);
                if (status == 0) {
                    DialogflowV3VoiceInteractionKit.this.sendStart(frame);
                    return;
                }
                if (status == 1) {
                    DialogflowV3VoiceInteractionKit.this.onVadData("STATUS_CONTINUE:" + vol, frame);
                    return;
                }
                if (status == 2) {
                    DialogflowV3VoiceInteractionKit.this.onVadData("STATUS_FIND_EOS", frame);
                    z = DialogflowV3VoiceInteractionKit.this.isBella;
                    if (z && iVoiceInteractionListener != null) {
                        iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Eos);
                    }
                    DialogflowV3VoiceInteractionKit.this.onVolumeChange(0);
                    return;
                }
                if (status != 3) {
                    if (status != 4) {
                        return;
                    }
                    Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "STATUS_FINISH：" + new Date().toString());
                    DialogflowV3VoiceInteractionKit.this.sendFinish(frame);
                    DialogflowV3VoiceInteractionKit.this.onVolumeChange(0);
                    return;
                }
                Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "STATUS_BOS_TIMEOUT：" + new Date().toString());
                DialogflowV3VoiceInteractionKit.this.isBosTimeOut = true;
                if (!SystemTool.INSTANCE.isConnected(DialogflowV3VoiceInteractionKit.access$getContext$p(DialogflowV3VoiceInteractionKit.this))) {
                    z2 = DialogflowV3VoiceInteractionKit.this.isSwift;
                    if (!z2) {
                        iVoiceInteractionListener2 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener2 != null) {
                            iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.ErrorNetWork);
                        }
                        z3 = DialogflowV3VoiceInteractionKit.this.isBella;
                        if (z3 && iVoiceInteractionListener3 != null) {
                            iVoiceInteractionListener3.onStatusChanged(VoiceInteractionState.Idle);
                        }
                        Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "Network disconnect");
                        return;
                    }
                }
                DialogflowV3VoiceInteractionKit.this.bosTimeout();
            }

            @Override // com.iflytek.aiui.vad.sdk.Vad.VadListener
            public void onError(int error, String des) {
                Intrinsics.checkParameterIsNotNull(des, "des");
                Pdlog.m3273d(VadTool.INSTANCE.getTAG(), "VadListener error=" + error + ", des=" + des);
            }
        };
        this.mAudioListener = new IAudioListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$mAudioListener$1
            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onRawData(byte[] p0) {
            }

            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onSingleData(byte[] p0) {
            }
        };
        this.isStopProcess = true;
    }

    public /* synthetic */ DialogflowV3VoiceInteractionKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final /* synthetic */ Context access$getContext$p(DialogflowV3VoiceInteractionKit dialogflowV3VoiceInteractionKit) {
        Context context = dialogflowV3VoiceInteractionKit.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
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

    /* compiled from: DialogflowV3VoiceInteractionKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit$Companion;", "", "()V", "DIALOG_FLOW_SERVER_CONFIG_FOLDER_PATH", "", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/dialogflow/v3/DialogflowV3VoiceInteractionKit;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final DialogflowV3VoiceInteractionKit getINSTANCE() {
            Lazy lazy = DialogflowV3VoiceInteractionKit.INSTANCE$delegate;
            Companion companion = DialogflowV3VoiceInteractionKit.INSTANCE;
            return (DialogflowV3VoiceInteractionKit) lazy.getValue();
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVadData(String tag, byte[] frame) {
        Pdlog.m3273d(TAG, String.valueOf(tag));
        queryByData(frame);
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
        this.micArray = micArray;
        VadTool.INSTANCE.init(context, this.mVadListener);
        this.isBella = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot");
        this.isSwift = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "SwiftBot");
        this.onlyWakeup = onlyWakeup;
        if (!onlyWakeup) {
            initDialogflow();
            initTTS();
        }
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DialogflowV3VoiceInteractionKit$init$1(this, null), 3, null);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005e, code lost:
    
        if (r1 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0084, code lost:
    
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("ttsStream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0082, code lost:
    
        if (r1 != null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initTTS() {
        InputStream inputStream;
        TextToSpeechClient textToSpeechClient = this.ttsClient;
        if (textToSpeechClient != null && !textToSpeechClient.isShutdown()) {
            Pdlog.m3273d(TAG, "tts client continue to use");
            return;
        }
        try {
            try {
                this.ttsStream = getGoogleTTSConfig();
                TextToSpeechSettings.Builder newBuilder = TextToSpeechSettings.newBuilder();
                Intrinsics.checkExpressionValueIsNotNull(newBuilder, "TextToSpeechSettings.newBuilder()");
                InputStream inputStream2 = this.ttsStream;
                if (inputStream2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsStream");
                }
                TextToSpeechSettings build = newBuilder.setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(inputStream2))).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "settingBuilder.setCreden…                 .build()");
                this.textToSpeechSettings = build;
                TextToSpeechSettings textToSpeechSettings = this.textToSpeechSettings;
                if (textToSpeechSettings == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textToSpeechSettings");
                }
                this.ttsClient = TextToSpeechClient.create(textToSpeechSettings);
                inputStream = this.ttsStream;
            } catch (Exception e) {
                Pdlog.m3275i(TAG, "initTTS Exception: " + e.toString());
                inputStream = this.ttsStream;
            }
        } catch (Throwable th) {
            InputStream inputStream3 = this.ttsStream;
            if (inputStream3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsStream");
            }
            inputStream3.close();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r1 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b0, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b3, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ad, code lost:
    
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ab, code lost:
    
        if (r1 != null) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initDialogflow() {
        InputStream inputStream;
        SessionsClient sessionsClient = this.client;
        if (sessionsClient != null && !sessionsClient.isShutdown()) {
            Pdlog.m3273d(TAG, "client donot shutdown and continue to use");
            return;
        }
        try {
            try {
                this.stream = getGoogleServiceConfig();
                InputStream inputStream2 = this.stream;
                if (inputStream2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("stream");
                }
                GoogleCredentials fromStream = GoogleCredentials.fromStream(inputStream2);
                Intrinsics.checkExpressionValueIsNotNull(fromStream, "GoogleCredentials.fromStream(stream)");
                this.credentials = fromStream;
                GoogleCredentials googleCredentials = this.credentials;
                if (googleCredentials == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("credentials");
                }
                if (googleCredentials == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.google.auth.oauth2.ServiceAccountCredentials");
                }
                this.projectId = ((ServiceAccountCredentials) googleCredentials).getProjectId();
                SessionsSettings.Builder newBuilder = SessionsSettings.newBuilder();
                Intrinsics.checkExpressionValueIsNotNull(newBuilder, "SessionsSettings.newBuilder()");
                GoogleCredentials googleCredentials2 = this.credentials;
                if (googleCredentials2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("credentials");
                }
                SessionsSettings build = newBuilder.setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials2)).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "settingsBuilder.setCrede…                 .build()");
                this.sessionsSettings = build;
                SessionsSettings sessionsSettings = this.sessionsSettings;
                if (sessionsSettings == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionsSettings");
                }
                this.client = SessionsClient.create(sessionsSettings);
                inputStream = this.stream;
            } catch (Exception e) {
                Pdlog.m3275i(TAG, "initDialogflow Exception: " + e.toString());
                inputStream = this.stream;
            }
        } catch (Throwable th) {
            InputStream inputStream3 = this.stream;
            if (inputStream3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stream");
            }
            inputStream3.close();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
    
        if (r3.intValue() != (-4)) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        if (r3.intValue() != 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
    
        com.pudutech.base.Pdlog.m3273d(com.pudutech.voiceinteraction.component.dialogflow.p068v3.DialogflowV3VoiceInteractionKit.TAG, "开启收音成功");
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0039, code lost:
    
        if (r3.intValue() != 0) goto L12;
     */
    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void startRecording() {
        try {
            MicArray micArray = this.micArray;
            Integer valueOf = micArray != null ? Integer.valueOf(micArray.startRecord(this.mWakeupListener, this.mAudioListener)) : null;
            Pdlog.m3273d(TAG, "StartRecord == " + valueOf);
            if (valueOf != null) {
            }
            if (valueOf != null) {
            }
            MicArray micArray2 = this.micArray;
            if (micArray2 != null) {
                micArray2.reInitAlsa(VoiceCommentConfig.INSTANCE.getTinymixCmd());
            }
            Pdlog.m3274e(TAG, "开启收音失败,执行第一次重启设备成功,并重新开启录音");
            MicArray micArray3 = this.micArray;
            if (micArray3 != null && micArray3.startRecord(this.mWakeupListener, this.mAudioListener) == 0) {
                Pdlog.m3273d(TAG, "重启设备成功，开启收音成功");
            } else {
                Pdlog.m3274e(TAG, "开启收音失败，请重启机器");
            }
        } catch (Throwable th) {
            Pdlog.m3275i(TAG, "startRecording Exception: " + Log.getStackTraceString(th));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void stopRecording() {
        IVoiceInteractionListener iVoiceInteractionListener;
        try {
            Pdlog.m3273d(TAG, "stopRecording()");
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.stopRecord();
            }
            if (VoiceCommentConfig.INSTANCE.isContinuous()) {
                cancelTTS();
            }
            clearWakeup();
            if (this.isBella || (iVoiceInteractionListener = this.onVoiceInteractionListener) == null) {
                return;
            }
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Idle);
        } catch (Exception e) {
            Pdlog.m3275i(TAG, "stopRecording Exception: " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void wakeup(int angle, int beam) {
        try {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("wakeup() angle = ");
            sb.append(angle);
            sb.append(", beam = ");
            sb.append(beam);
            sb.append(" clientJob:");
            Job job = this.clientJob;
            sb.append(job != null ? Boolean.valueOf(job.isCompleted()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(TAG, objArr);
            iflyWakeup(new WakeupInfo(0, 0, null, null, 12, null));
        } catch (Throwable th) {
            Pdlog.m3275i(TAG, "wakeup Exception: " + Log.getStackTraceString(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void iflyWakeup(WakeupInfo wakeupInfo) {
        AudioTrackUtils.INSTANCE.stop();
        if (!this.onlyWakeup) {
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.startWakeup(0);
            }
            createClient();
            this.isBosTimeOut = false;
            this.isStopProcess = true;
            IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Recording);
            }
            this.vadActive = true;
            Pdlog.m3275i(TAG, "wakeup success");
            IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener2 != null) {
                iVoiceInteractionListener2.onWakeup(wakeupInfo);
            }
            MicArray micArray2 = this.micArray;
            if (micArray2 != null) {
                micArray2.wakeUpEnable(false);
                return;
            }
            return;
        }
        IVoiceInteractionListener iVoiceInteractionListener3 = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener3 != null) {
            iVoiceInteractionListener3.onWakeup(wakeupInfo);
        }
        Pdlog.m3273d(TAG, "onlyWakeup == " + this.onlyWakeup + "  不交互");
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
    public void release() {
        Pdlog.m3273d(TAG, "release()");
        VadTool.INSTANCE.destroy();
        this.vadActive = false;
        this.dialogflowActive = false;
        this.bidiStream = (BidiStream) null;
        SessionsClient sessionsClient = this.client;
        if (sessionsClient != null && !sessionsClient.isShutdown()) {
            SessionsClient sessionsClient2 = this.client;
            if (sessionsClient2 != null) {
                sessionsClient2.shutdownNow();
            }
            Pdlog.m3275i(TAG, "client shutdown");
        }
        MicArray micArray = this.micArray;
        if (micArray != null) {
            micArray.wakeUpEnable(true);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startTts(String text, Boolean fromTts, String filePath, OnTtsListener onTtsListener) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Pdlog.m3275i(TAG, "startTts");
        textToSpeech(text);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setSpeaker(String speaker, String languageCode) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(languageCode, "languageCode");
        Pdlog.m3273d(TAG, "setSpeaker speaker:" + speaker + " languageCode:" + languageCode);
        this.defaultSpeaker = speaker;
        this.languageCode = languageCode;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void onlyWakeup(boolean b) {
        this.onlyWakeup = b;
        Pdlog.m3273d(TAG, "onlyWakeup " + b);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelTTS() {
        this.isStopProcess = true;
        AudioTrackUtils.INSTANCE.stop();
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public NLPActionType getNLPActionType() {
        return NLPActionType.OTHER;
    }

    public final void timeout() {
        Pdlog.m3273d(TAG, "timeout()");
        closeSend();
        if (VoiceCommentConfig.INSTANCE.isContinuous() && !this.onlyWakeup) {
            SystemTool systemTool = SystemTool.INSTANCE;
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            if (systemTool.isConnected(context)) {
                createClient();
                return;
            }
        }
        MicArray micArray = this.micArray;
        if (micArray != null) {
            micArray.wakeUpEnable(true);
        }
        this.vadActive = false;
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.BosTimeout);
        }
        IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener2 != null) {
            iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
        }
    }

    public final void bosTimeout() {
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.BosTimeout);
        }
        IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener2 != null) {
            iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
        }
    }

    public final void onVolumeChange(int r2) {
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
        IVoiceInteractionListener iVoiceInteractionListener;
        IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener2 != null) {
            iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.PlayCompleted);
        }
        if (this.isSwift || (iVoiceInteractionListener = this.onVoiceInteractionListener) == null) {
            return;
        }
        iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Idle);
    }

    @Override // com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer.OnPlayStateChangedListener
    public void onPlayError() {
        if (this.isBella) {
            LogProxy.INSTANCE.m3305d(TAG, "onPlayError");
            IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Unknown);
            }
            IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener2 != null) {
                iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
            }
        }
    }

    private final void closeSend() {
        try {
            BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream = this.bidiStream;
            if (bidiStream != null) {
                Pdlog.m3273d(TAG, "bidiStream closeSend isSendReady::" + bidiStream.isSendReady());
                if (bidiStream.isSendReady()) {
                    bidiStream.closeSend();
                }
            }
        } catch (Exception e) {
            onErrNetWork();
            Pdlog.m3275i(TAG, "closeSend Exception:" + e.toString());
        }
    }

    private final void queryByData(byte[] data) {
        try {
            Pdlog.m3273d(TAG, "queryByData  :" + this.dialogflowActive + "  :" + this.bidiStream);
            if (this.dialogflowActive) {
                BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream = this.bidiStream;
                if (bidiStream != null) {
                    if (!bidiStream.isSendReady()) {
                        Pdlog.m3273d(TAG, "queryByData  :  isSendReady :" + bidiStream.isSendReady());
                    } else if (bidiStream != null) {
                        bidiStream.send(StreamingDetectIntentRequest.newBuilder().setInputAudio(ByteString.copyFrom(data)).build());
                    }
                }
            }
        } catch (Exception e) {
            onErrNetWork();
            Pdlog.m3275i(TAG, "queryByData Exception:" + e.toString());
        }
    }

    private final InputStream getGoogleServiceConfig() throws Resources.NotFoundException {
        File file = new File((this.DIALOG_FLOW_CONFIG_DIRECTORY + File.separator) + this.DIALOG_FLOW_SERVER_CONFIG_NAME);
        if (file.exists()) {
            Pdlog.m3273d(TAG, "getGoogleServiceConfig load custom google service config " + file.isDirectory() + "  " + file.isFile() + ' ');
            return new FileInputStream(file);
        }
        File file2 = new File((DIALOG_FLOW_SERVER_CONFIG_FOLDER_PATH + File.separator) + VoiceCommentConfig.INSTANCE.getGoogleDialogflowConfigKey());
        if (file2.exists() && file2.isFile()) {
            Pdlog.m3273d(TAG, "getGoogleServiceConfig load defDialogflowConfigFile");
            return new FileInputStream(file2);
        }
        if (!UrlOkManager.INSTANCE.isOkTestServer()) {
            Pdlog.m3273d(TAG, "GoogleService success key is product isSwift:" + this.isSwift + " ProductType:" + VoiceCommentConfig.INSTANCE.getProductType());
            if (this.isSwift) {
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                InputStream openRawResource = context.getResources().openRawResource(C5767R.raw.googlekey_swiftbot);
                Intrinsics.checkExpressionValueIsNotNull(openRawResource, "context.resources.openRa…R.raw.googlekey_swiftbot)");
                return openRawResource;
            }
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            InputStream openRawResource2 = context2.getResources().openRawResource(C5767R.raw.googlekey);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource2, "context.resources.openRawResource(R.raw.googlekey)");
            return openRawResource2;
        }
        Pdlog.m3275i(TAG, "GoogleService success key is test");
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        InputStream openRawResource3 = context3.getResources().openRawResource(C5767R.raw.googlekey_test);
        Intrinsics.checkExpressionValueIsNotNull(openRawResource3, "context.resources.openRa…rce(R.raw.googlekey_test)");
        return openRawResource3;
    }

    private final InputStream getGoogleTTSConfig() throws Resources.NotFoundException {
        File file = new File((this.DIALOG_FLOW_CONFIG_DIRECTORY + File.separator) + this.DIALOG_FLOW_SERVER_CONFIG_NAME);
        if (file.exists()) {
            Pdlog.m3273d(TAG, "getGoogleServiceConfig load custom google service config " + file.isDirectory() + "  " + file.isFile() + ' ');
            return new FileInputStream(file);
        }
        File file2 = new File((DIALOG_FLOW_SERVER_CONFIG_FOLDER_PATH + File.separator) + VoiceCommentConfig.INSTANCE.getGoogleDialogflowConfigKey());
        if (file2.exists() && file2.isFile()) {
            Pdlog.m3273d(TAG, "getGoogleServiceConfig load defDialogflowConfigFile");
            return new FileInputStream(file2);
        }
        Pdlog.m3275i(TAG, "getGoogleTTSConfig GoogleService success key is product");
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        InputStream openRawResource = context.getResources().openRawResource(C5767R.raw.googlekey);
        Intrinsics.checkExpressionValueIsNotNull(openRawResource, "context.resources.openRawResource(R.raw.googlekey)");
        return openRawResource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0174 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void initClient() {
        boolean z;
        IVoiceInteractionListener iVoiceInteractionListener;
        BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentCallable;
        IVoiceInteractionListener iVoiceInteractionListener2;
        Pdlog.m3273d(TAG, "initClient() isStopProcess " + this.isStopProcess + new Date().toString());
        if (this.onlyWakeup) {
            Pdlog.m3273d(TAG, "initClient() current onlyWakeup is  " + this.onlyWakeup);
            return;
        }
        BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream = this.bidiStream;
        if (bidiStream != null && bidiStream.isSendReady()) {
            Pdlog.m3273d(TAG, "current bidstream can be use");
            return;
        }
        SystemTool systemTool = SystemTool.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        if (!systemTool.isConnected(context)) {
            Pdlog.m3274e(TAG, "Network is unavailable");
            IVoiceInteractionListener iVoiceInteractionListener3 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener3 != null) {
                iVoiceInteractionListener3.onStatusChanged(VoiceInteractionState.ErrorNetWork);
            }
            if (!this.isBella || (iVoiceInteractionListener2 = this.onVoiceInteractionListener) == null) {
                return;
            }
            iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
            return;
        }
        try {
            Pdlog.m3273d(TAG, "SessionsClient : '" + this.client + "' " + this.bidiStream + '\n');
            initDialogflow();
            SessionsClient sessionsClient = this.client;
            this.bidiStream = (sessionsClient == null || (streamingDetectIntentCallable = sessionsClient.streamingDetectIntentCallable()) == null) ? null : streamingDetectIntentCallable.call();
            BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream2 = this.bidiStream;
            if (bidiStream2 != null) {
                for (StreamingDetectIntentResponse it : bidiStream2) {
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("queryResult Text: '");
                    sb.append(it);
                    sb.append("'\n i in the thread ");
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    sb.append(" clientJob: ");
                    Job job = this.clientJob;
                    sb.append(job != null ? Boolean.valueOf(job.isCompleted()) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(TAG, objArr);
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    StreamingRecognitionResult recognitionResult = it.getRecognitionResult();
                    Intrinsics.checkExpressionValueIsNotNull(recognitionResult, "recognitionResult");
                    if (!recognitionResult.getTranscript().equals("")) {
                        IVoiceInteractionListener iVoiceInteractionListener4 = this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener4 != null) {
                            iVoiceInteractionListener4.onResultRequest(recognitionResult.getTranscript(), recognitionResult.getIsFinal());
                        }
                    } else {
                        QueryResult queryResult = it.getQueryResult();
                        Intrinsics.checkExpressionValueIsNotNull(queryResult, "queryResult");
                        String queryText = queryResult.getQueryText();
                        String fulfillmentText = queryResult.getFulfillmentText();
                        String str = queryText;
                        if (str != null && str.length() != 0) {
                            z = false;
                            if (z) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("queryResult:");
                                sb2.append(queryResult);
                                sb2.append("; Query Text: '");
                                sb2.append(queryText);
                                sb2.append("'\n Fulfillment Text: '");
                                sb2.append(fulfillmentText);
                                sb2.append("'\n ;Detected Intent: ");
                                Intent intent = queryResult.getIntent();
                                Intrinsics.checkExpressionValueIsNotNull(intent, "queryResult.intent");
                                sb2.append(intent.getDisplayName());
                                sb2.append(" (confidence: ");
                                sb2.append(queryResult.getIntentDetectionConfidence());
                                sb2.append(") \n");
                                Pdlog.m3273d(TAG, sb2.toString());
                                IVoiceInteractionListener iVoiceInteractionListener5 = this.onVoiceInteractionListener;
                                if (iVoiceInteractionListener5 != null) {
                                    iVoiceInteractionListener5.onResultRequest(queryText, true);
                                }
                                this.isStopProcess = false;
                                httpText$default(this, queryText, fulfillmentText, null, 4, null);
                            } else {
                                if (this.isSwift && (iVoiceInteractionListener = this.onVoiceInteractionListener) != null) {
                                    iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.PlayCompleted);
                                }
                                Pdlog.m3275i(TAG, recognitionResult.getTranscript() + ':' + it.toString() + ";isBosTimeOut:" + this.isBosTimeOut);
                            }
                        }
                        z = true;
                        if (z) {
                        }
                    }
                }
            }
            Pdlog.m3273d(TAG, "SessionsClient dialogflowActive : '" + this.dialogflowActive + "'\n");
        } catch (Exception e) {
            if (StringsKt.contains$default((CharSequence) e.toString(), (CharSequence) "408", false, 2, (Object) null)) {
                Pdlog.m3273d(TAG, "initClient timeout : '" + e.toString() + "'\n");
                timeout();
                return;
            }
            if (!StringsKt.contains$default((CharSequence) e.toString(), (CharSequence) "shutdownNow invoked", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) e.toString(), (CharSequence) "User cancelled stream", false, 2, (Object) null)) {
                onErrNetWork();
            }
            Pdlog.m3273d(TAG, "initClient Exception : '" + e.toString() + "'\n");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendFinish(byte[] data) {
        try {
            Pdlog.m3273d(TAG, "sendFinish dialogflowActive: " + this.dialogflowActive);
            if (this.dialogflowActive) {
                queryByData(data);
                closeSend();
                this.dialogflowActive = false;
                if (VoiceCommentConfig.INSTANCE.isContinuous() && !this.onlyWakeup) {
                    Pdlog.m3275i(TAG, " vadActive change to true ,get a new request");
                    createClient();
                    return;
                }
                this.vadActive = false;
                MicArray micArray = this.micArray;
                if (micArray != null) {
                    micArray.wakeUpEnable(true);
                }
                Pdlog.m3275i(TAG, "finally,isContinuous false vadActive change to false");
            }
        } catch (Exception e) {
            onErrNetWork();
            Pdlog.m3273d(TAG, "sendFinish Exception:'" + e.toString() + '\'');
        }
    }

    private final void onErrNetWork() {
        SessionsClient sessionsClient;
        this.vadActive = false;
        closeSend();
        MicArray micArray = this.micArray;
        if (micArray != null) {
            micArray.wakeUpEnable(true);
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.ErrorNetWork);
        }
        SessionsClient sessionsClient2 = this.client;
        if (sessionsClient2 != null && !sessionsClient2.isShutdown() && (sessionsClient = this.client) != null) {
            sessionsClient.shutdownNow();
        }
        if (VoiceCommentConfig.INSTANCE.isContinuous() && !this.onlyWakeup) {
            wakeup(0, 0);
        } else {
            IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener2 != null) {
                iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
            }
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onErrNetWork ");
        sb.append(VoiceCommentConfig.INSTANCE.isContinuous());
        sb.append(' ');
        SystemTool systemTool = SystemTool.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        sb.append(systemTool.isConnected(context));
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendStart(byte[] data) {
        try {
            Pdlog.m3273d(TAG, "sendStart dialogflowActive: " + this.dialogflowActive + " ;" + this.bidiStream);
            com.google.cloud.dialogflow.p049v2.AudioEncoding audioEncoding = com.google.cloud.dialogflow.p049v2.AudioEncoding.AUDIO_ENCODING_LINEAR_16;
            SessionName m589of = SessionName.m589of(this.projectId, UUID.randomUUID().toString());
            InputAudioConfig.Builder inputAudioConfigBuilder = InputAudioConfig.newBuilder().setAudioEncoding(audioEncoding).setSampleRateHertz(16000);
            Intrinsics.checkExpressionValueIsNotNull(inputAudioConfigBuilder, "inputAudioConfigBuilder");
            inputAudioConfigBuilder.setLanguageCode(this.languageCode);
            QueryInput build = QueryInput.newBuilder().setAudioConfig(inputAudioConfigBuilder.build()).build();
            BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream = this.bidiStream;
            if (bidiStream != null) {
                if (bidiStream.isSendReady()) {
                    BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream2 = this.bidiStream;
                    if (bidiStream2 != null) {
                        bidiStream2.send(StreamingDetectIntentRequest.newBuilder().setSession(m589of.toString()).setQueryInput(build).build());
                    }
                    this.dialogflowActive = true;
                } else {
                    this.dialogflowActive = false;
                    Pdlog.m3275i(TAG, "dialogflow is not ready now");
                }
            }
            queryByData(data);
            if (this.bidiStream == null) {
                Pdlog.m3273d(TAG, "bidStream is null CoroutineScope reinit");
                CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
                this.coroutineScope = CoroutineScopeKt.CoroutineScope(this.singelThreadContext);
            }
        } catch (Exception e) {
            onErrNetWork();
            Pdlog.m3273d(TAG, "sendStart dialogflowActive:'" + e.toString() + '\'');
        }
    }

    private final void textToSpeech(final String text) {
        IVoiceInteractionListener iVoiceInteractionListener;
        Pdlog.m3273d(TAG, "textToSpeech() isStopProcess " + this.isStopProcess);
        if (this.isStopProcess) {
            IVoiceInteractionListener iVoiceInteractionListener2 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener2 != null) {
                iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.PlayCompleted);
                return;
            }
            return;
        }
        String str = text;
        if (str == null || str.length() == 0) {
            if (this.isBella && (iVoiceInteractionListener = this.onVoiceInteractionListener) != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Idle);
            }
            IVoiceInteractionListener iVoiceInteractionListener3 = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener3 != null) {
                iVoiceInteractionListener3.onStatusChanged(VoiceInteractionState.PlayCompleted);
                return;
            }
            return;
        }
        try {
            initTTS();
            TextToSpeechClient textToSpeechClient = this.ttsClient;
            if (textToSpeechClient == null) {
                return;
            }
            TextToSpeechClient textToSpeechClient2 = textToSpeechClient;
            Throwable th = (Throwable) null;
            try {
                TextToSpeechClient textToSpeechClient3 = textToSpeechClient2;
                SynthesisInput build = SynthesisInput.newBuilder().setText(text).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "SynthesisInput.newBuilde…                 .build()");
                VoiceSelectionParams.Builder voiceBuilder = this.voiceBuilder;
                Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                voiceBuilder.setName(getVioceConfigName());
                VoiceSelectionParams.Builder voiceBuilder2 = this.voiceBuilder;
                Intrinsics.checkExpressionValueIsNotNull(voiceBuilder2, "voiceBuilder");
                voiceBuilder2.setLanguageCode(this.languageCode);
                Pdlog.m3273d(TAG, "begin to get tts pcm ");
                SynthesizeSpeechResponse response = textToSpeechClient3.synthesizeSpeech(build, this.voiceBuilder.build(), this.audioConfig);
                if (!textToSpeechClient3.isShutdown()) {
                    textToSpeechClient3.shutdownNow();
                }
                if (this.isStopProcess) {
                    IVoiceInteractionListener iVoiceInteractionListener4 = this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener4 != null) {
                        iVoiceInteractionListener4.onStatusChanged(VoiceInteractionState.PlayCompleted);
                    }
                    AutoCloseableKt.closeFinally(textToSpeechClient2, th);
                    return;
                }
                Intrinsics.checkExpressionValueIsNotNull(response, "response");
                ByteString audioContent = response.getAudioContent();
                Pdlog.m3273d(TAG, "finish  get tts pcm ");
                if (this.onlyWakeup) {
                    IVoiceInteractionListener iVoiceInteractionListener5 = this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener5 != null) {
                        iVoiceInteractionListener5.onStatusChanged(VoiceInteractionState.PlayCompleted);
                    }
                    Pdlog.m3273d(TAG, "do not play tts pcm  in main page");
                    AutoCloseableKt.closeFinally(textToSpeechClient2, th);
                    return;
                }
                TextToSpeechSettings textToSpeechSettings = this.textToSpeechSettings;
                if (textToSpeechSettings == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textToSpeechSettings");
                }
                this.ttsClient = TextToSpeechClient.create(textToSpeechSettings);
                AudioTrackUtils.INSTANCE.stop();
                Boolean valueOf = audioContent != null ? Boolean.valueOf(audioContent.isEmpty()) : null;
                if (valueOf == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueOf.booleanValue()) {
                    AudioTrackUtils.INSTANCE.play(audioContent, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$textToSpeech$$inlined$use$lambda$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                            invoke2(audioPlayEvent);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
                        
                            r5 = r4.this$0.onVoiceInteractionListener;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:7:0x0032, code lost:
                        
                            r5 = r4.this$0.onVoiceInteractionListener;
                         */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                            IVoiceInteractionListener iVoiceInteractionListener6;
                            boolean z;
                            IVoiceInteractionListener iVoiceInteractionListener7;
                            IVoiceInteractionListener iVoiceInteractionListener8;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "AudioTrackUtils event " + it);
                            int i = DialogflowV3VoiceInteractionKit.WhenMappings.$EnumSwitchMapping$0[it.ordinal()];
                            if (i != 1) {
                                if (i != 2) {
                                    if (i == 3 && iVoiceInteractionListener8 != null) {
                                        iVoiceInteractionListener8.onStatusChanged(VoiceInteractionState.Speaking);
                                        return;
                                    }
                                    return;
                                }
                                iVoiceInteractionListener6 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                                if (iVoiceInteractionListener6 != null) {
                                    iVoiceInteractionListener6.onStatusChanged(VoiceInteractionState.PlayCompleted);
                                }
                                z = DialogflowV3VoiceInteractionKit.this.isSwift;
                                if (z || iVoiceInteractionListener7 == null) {
                                    return;
                                }
                                iVoiceInteractionListener7.onStatusChanged(VoiceInteractionState.Idle);
                            }
                        }
                    });
                }
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(textToSpeechClient2, th);
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.isBella) {
                IVoiceInteractionListener iVoiceInteractionListener6 = this.onVoiceInteractionListener;
                if (iVoiceInteractionListener6 != null) {
                    iVoiceInteractionListener6.onStatusChanged(VoiceInteractionState.ErrorNetWork);
                }
                IVoiceInteractionListener iVoiceInteractionListener7 = this.onVoiceInteractionListener;
                if (iVoiceInteractionListener7 != null) {
                    iVoiceInteractionListener7.onStatusChanged(VoiceInteractionState.Idle);
                }
            }
        }
    }

    private final void clearWakeup() {
        Job job = this.clientJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        MicArray micArray = this.micArray;
        if (micArray != null) {
            micArray.wakeUpEnable(true);
        }
        this.vadActive = false;
        BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream = this.bidiStream;
        if (bidiStream != null) {
            bidiStream.cancel();
        }
        this.bidiStream = (BidiStream) null;
        VadTool.INSTANCE.resetVad();
        Pdlog.m3275i(TAG, "clearWakeup");
    }

    public final void stopProcess() {
        this.isStopProcess = true;
        AudioTrackUtils.INSTANCE.stop();
        clearWakeup();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015 A[Catch: Exception -> 0x00ab, TryCatch #0 {Exception -> 0x00ab, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0018 A[Catch: Exception -> 0x00ab, TryCatch #0 {Exception -> 0x00ab, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getWIFIMac() {
        String str;
        boolean z;
        try {
            str = this.macStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str != null && str.length() != 0) {
            z = false;
            if (z) {
                return this.macStr;
            }
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
                    this.macStr = sb.toString();
                    return sb.toString();
                }
            }
            return null;
        }
        z = true;
        if (z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(VoiceInteractionState state) {
        LogProxy.INSTANCE.m3307i(TAG, "UPDATE STATE " + state);
        if (state == VoiceInteractionState.BosTimeout || state == VoiceInteractionState.Eos || state == VoiceInteractionState.Idle) {
            if (!VoiceCommentConfig.INSTANCE.isContinuous()) {
                this.vadActive = false;
                MicArray micArray = this.micArray;
                if (micArray != null) {
                    micArray.wakeUpEnable(true);
                }
            }
            LogProxy.INSTANCE.m3307i(TAG, "STOP RECOGNIZE");
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAiCloud(List<OkHttpUtils.RasaAnswer> cmd) {
        LogProxy.INSTANCE.m3305d(TAG, "updateAiCloud cmd:" + cmd);
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onCmdResponse(getAiCloudAdapter().transformationCmd2(cmd));
        }
        updateState(VoiceInteractionState.Idle);
    }

    static /* synthetic */ void httpText$default(DialogflowV3VoiceInteractionKit dialogflowV3VoiceInteractionKit, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        dialogflowV3VoiceInteractionKit.httpText(str, str2, str3);
    }

    private final void httpText(String asw, final String result, String actionString) {
        Pdlog.m3273d(TAG, "httpText" + new Date().toString());
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            OkHttpUtils.INSTANCE.post(wIFIMac, asw, result != null ? result : "", (r13 & 8) != 0 ? "" : actionString, (r13 & 16) != 0 ? "" : null);
        }
        OkHttpUtils.INSTANCE.setTtsClick(new Function4<String, String, Integer, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$httpText$2
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

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(String str, String str2, int i, String str3) {
                boolean z;
                IVoiceInteractionListener iVoiceInteractionListener;
                String str4;
                Gson gson;
                IVoiceInteractionListener iVoiceInteractionListener2;
                IVoiceInteractionListener iVoiceInteractionListener3;
                IVoiceInteractionListener iVoiceInteractionListener4;
                IVoiceInteractionListener iVoiceInteractionListener5;
                IVoiceInteractionListener iVoiceInteractionListener6;
                IVoiceInteractionListener iVoiceInteractionListener7;
                Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "OkHttpUtils tts:" + str + " json:" + str2 + " date:" + new Date().toString());
                String str5 = str2;
                if ((str5 == null || str5.length() == 0) == true) {
                    if (str != null) {
                        if (i == 0) {
                            IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV3VoiceInteractionKit.this, str, null, null, null, 14, null);
                        }
                        iVoiceInteractionListener6 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener6 != null) {
                            iVoiceInteractionListener6.onResultResponse(str, str2, i);
                        }
                        iVoiceInteractionListener7 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener7 != null) {
                            iVoiceInteractionListener7.onResultResponse(str, null, i);
                            return;
                        }
                        return;
                    }
                    if (!(true ^ Intrinsics.areEqual(result, ""))) {
                        iVoiceInteractionListener3 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener3 != null) {
                            iVoiceInteractionListener3.onStatusChanged(VoiceInteractionState.PlayCompleted);
                            return;
                        }
                        return;
                    }
                    String str6 = result;
                    if (str6 != null) {
                        IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV3VoiceInteractionKit.this, str6, null, null, null, 14, null);
                    }
                    iVoiceInteractionListener4 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener4 != null) {
                        iVoiceInteractionListener4.onResultResponse(result, str2, i);
                    }
                    iVoiceInteractionListener5 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener5 != null) {
                        iVoiceInteractionListener5.onResultResponse(result, null, i);
                        return;
                    }
                    return;
                }
                z = DialogflowV3VoiceInteractionKit.this.isBella;
                if (z) {
                    gson = DialogflowV3VoiceInteractionKit.this.gson;
                    OkHttpUtils.CloudSkillRespData cloudSkillRespData = (OkHttpUtils.CloudSkillRespData) gson.fromJson(str2, OkHttpUtils.CloudSkillRespData.class);
                    Pdlog.m3273d("DialogflowV3VoiceInteractionKit", "bella skill == " + cloudSkillRespData);
                    if (i == 1) {
                        iVoiceInteractionListener2 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener2 != null) {
                            iVoiceInteractionListener2.onResultResponse(str, GsonUtils.toJson(cloudSkillRespData != null ? cloudSkillRespData.getFaq_answer() : null), i);
                        }
                        DialogflowV3VoiceInteractionKit.this.updateState(VoiceInteractionState.Idle);
                        return;
                    }
                    if ((cloudSkillRespData != null ? cloudSkillRespData.getRasa_answer() : null) != null) {
                        DialogflowV3VoiceInteractionKit.this.updateAiCloud(cloudSkillRespData != null ? cloudSkillRespData.getRasa_answer() : null);
                        return;
                    }
                    String str7 = result;
                    if (str7 != null) {
                        IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV3VoiceInteractionKit.this, str7, null, null, null, 14, null);
                        return;
                    }
                    return;
                }
                if (VoiceInteractionKit.INSTANCE.getType() == 0 && i != 2 && (str4 = result) != null) {
                    IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV3VoiceInteractionKit.this, str4, null, null, null, 14, null);
                }
                iVoiceInteractionListener = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                if (iVoiceInteractionListener != null) {
                    iVoiceInteractionListener.onResultResponse("", str2, i);
                }
            }
        });
        OkHttpUtils.INSTANCE.setTtsError(new Function2<String, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.v3.DialogflowV3VoiceInteractionKit$httpText$3
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

            /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
            
                r8 = r7.this$0.onVoiceInteractionListener;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2(String err, String str) {
                IVoiceInteractionListener iVoiceInteractionListener;
                boolean z;
                IVoiceInteractionListener iVoiceInteractionListener2;
                IVoiceInteractionListener iVoiceInteractionListener3;
                Intrinsics.checkParameterIsNotNull(err, "err");
                if (!(!Intrinsics.areEqual(result, ""))) {
                    iVoiceInteractionListener = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener != null) {
                        iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.PlayCompleted);
                    }
                    z = DialogflowV3VoiceInteractionKit.this.isBella;
                    if (!z || iVoiceInteractionListener2 == null) {
                        return;
                    }
                    iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.Idle);
                    return;
                }
                String str2 = result;
                if (str2 != null) {
                    IVoiceInteraction.DefaultImpls.startTts$default(DialogflowV3VoiceInteractionKit.this, str2, null, null, null, 14, null);
                }
                iVoiceInteractionListener3 = DialogflowV3VoiceInteractionKit.this.onVoiceInteractionListener;
                if (iVoiceInteractionListener3 != null) {
                    iVoiceInteractionListener3.onResultResponse(result, null, 3);
                }
            }
        });
    }

    private final void createClient() {
        Job launch$default;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("createClient clientJob is ");
        Job job = this.clientJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        sb.append("  ");
        sb.append(this.coroutineScope);
        sb.append("?.isActive is");
        CoroutineScope coroutineScope = this.coroutineScope;
        sb.append((coroutineScope != null ? Boolean.valueOf(CoroutineScopeKt.isActive(coroutineScope)) : null).booleanValue());
        sb.append(' ');
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        Job job2 = this.clientJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new DialogflowV3VoiceInteractionKit$createClient$1(this, null), 3, null);
        this.clientJob = launch$default;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("createClient clientJob is ");
        Job job3 = this.clientJob;
        sb2.append(job3 != null ? Boolean.valueOf(job3.isActive()) : null);
        sb2.append("  ");
        sb2.append(this.coroutineScope);
        sb2.append("?.isActive is");
        CoroutineScope coroutineScope2 = this.coroutineScope;
        sb2.append((coroutineScope2 != null ? Boolean.valueOf(CoroutineScopeKt.isActive(coroutineScope2)) : null).booleanValue());
        sb2.append(' ');
        objArr2[0] = sb2.toString();
        Pdlog.m3273d(TAG, objArr2);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelCurrentRound() {
        clearWakeup();
        CMediaPlayer.INSTANCE.getINSTANCE().release();
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Idle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object updateLanguageConfig(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new DialogflowV3VoiceInteractionKit$updateLanguageConfig$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private final String getVioceConfigName() {
        try {
            this.LOCK.readLock().lock();
            String str = this.languageMap.get(this.languageCode);
            if (str == null) {
                str = this.defaultSpeaker;
            }
            return str;
        } finally {
            this.LOCK.readLock().unlock();
        }
    }
}

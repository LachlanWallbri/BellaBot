package com.pudutech.voiceinteraction.component.ifly;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.FileUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.mic_array.mic.IAudioListener;
import com.pudutech.mic_array.mic.IWakeupListener;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.mic_array.util.LogUtils;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.pd_network.utils.ExtKt;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.cmd.AiCloudAdapter;
import com.pudutech.voiceinteraction.component.cmd.IflyCmdAdatper;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.config.UserInfo;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.voiceinteraction.component.interf.IVoiceInteraction;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import com.pudutech.voiceinteraction.component.utils.LogUtil;
import com.pudutech.voiceinteraction.component.utils.NetworkStateReceive;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import com.pudutech.voiceinteraction.component.utils.PuduAudioReport;
import com.pudutech.voiceinteraction.component.utils.SystemTool;
import com.pudutech.voiceinteraction.component.utils.UrlOkManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IFlyVoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0095\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b*\u0003(;A\u0018\u0000 Â\u00012\u00020\u00012\u00020\u0002:\u0002Â\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010Z\u001a\u00020LH\u0016J\b\u0010[\u001a\u00020LH\u0016J\b\u0010\\\u001a\u00020LH\u0002J\u0006\u0010]\u001a\u00020LJ\b\u0010^\u001a\u00020LH\u0002J\u0010\u0010_\u001a\u00020L2\u0006\u0010`\u001a\u00020\rH\u0002J\b\u0010a\u001a\u00020LH\u0002J\n\u0010b\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010c\u001a\u00020dH\u0016J\n\u0010e\u001a\u0004\u0018\u00010\rH\u0002J+\u0010f\u001a\u00020\u000f2#\u0010g\u001a\u001f\u0012\u0013\u0012\u001101¢\u0006\f\bi\u0012\b\bj\u0012\u0004\b\b(k\u0012\u0004\u0012\u00020L\u0018\u00010hJ\u0012\u0010l\u001a\u0004\u0018\u00010\r2\u0006\u0010m\u001a\u00020\rH\u0002J\u0012\u0010n\u001a\u0004\u0018\u00010o2\u0006\u0010p\u001a\u00020qH\u0002J\n\u0010r\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010s\u001a\u00020L2\u0006\u0010t\u001a\u00020\u000fH\u0002J2\u0010u\u001a\u00020L2\u0006\u0010\f\u001a\u00020\r2\b\u0010v\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010w\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010x\u001a\u00020L2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010y\u001a\u00020L2\u0006\u0010\f\u001a\u00020\r2\b\u0010v\u001a\u0004\u0018\u00010\rH\u0002J\u0012\u0010z\u001a\u00020L2\b\u0010{\u001a\u0004\u0018\u00010|H\u0002JB\u0010}\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$2\b\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010I\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010>\u001a\u00020?2\u0006\u0010~\u001a\u00020\u000fH\u0016J\u0012\u0010\u007f\u001a\u00020L2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J4\u0010\u0080\u0001\u001a\u00020L2\u0006\u0010\u0010\u001a\u00020\u00112#\u0010g\u001a\u001f\u0012\u0013\u0012\u001101¢\u0006\f\bi\u0012\b\bj\u0012\u0004\b\b(k\u0012\u0004\u0012\u00020L\u0018\u00010hJ\u0012\u0010\u0081\u0001\u001a\u00020L2\u0007\u0010\u0082\u0001\u001a\u00020\rH\u0002J\u0012\u0010\u0083\u0001\u001a\u00020L2\u0007\u0010\u0084\u0001\u001a\u00020qH\u0016J\u0013\u0010\u0085\u0001\u001a\u00020L2\b\u0010v\u001a\u0004\u0018\u00010\rH\u0002J\u001c\u0010\u0086\u0001\u001a\u00020L2\b\u0010m\u001a\u0004\u0018\u00010\r2\u0007\u0010\u0087\u0001\u001a\u00020\u000fH\u0002J\u0011\u0010G\u001a\u00020L2\u0007\u0010\u0088\u0001\u001a\u00020\u000fH\u0016J\u0011\u0010\u0089\u0001\u001a\u00020L2\u0006\u0010w\u001a\u00020\rH\u0016J\t\u0010\u008a\u0001\u001a\u00020LH\u0002J\u0011\u0010\u008b\u0001\u001a\u00020L2\u0006\u0010p\u001a\u00020qH\u0002J\u0012\u0010\u008c\u0001\u001a\u00020L2\u0007\u0010\u0084\u0001\u001a\u00020qH\u0002J\u0013\u0010\u008d\u0001\u001a\u00020L2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0002J\u0011\u0010\u0090\u0001\u001a\u00020L2\u0006\u0010p\u001a\u00020qH\u0002J\u0012\u0010\u0091\u0001\u001a\u00020L2\u0007\u0010\u0084\u0001\u001a\u00020qH\u0002J\t\u0010\u0092\u0001\u001a\u00020LH\u0016J\u0011\u0010\u0093\u0001\u001a\u00020L2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\t\u0010\u0094\u0001\u001a\u00020LH\u0016J\t\u0010\u0095\u0001\u001a\u00020LH\u0016J\t\u0010\u0096\u0001\u001a\u00020LH\u0002J\t\u0010\u0097\u0001\u001a\u00020LH\u0002J\u0012\u0010\u0098\u0001\u001a\u00020L2\u0007\u0010\u0099\u0001\u001a\u00020\u000bH\u0002J\u0019\u0010\u009a\u0001\u001a\u00020L2\u000e\u0010\u009b\u0001\u001a\t\u0012\u0004\u0012\u00020\r0\u009c\u0001H\u0002J#\u0010\u009d\u0001\u001a\u00020L2\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\u000e\u0010 \u0001\u001a\t\u0012\u0004\u0012\u00020\r0\u009c\u0001H\u0016J\u0012\u0010¡\u0001\u001a\u00020L2\u0007\u0010¢\u0001\u001a\u00020\rH\u0002J\u0012\u0010£\u0001\u001a\u00020L2\u0007\u0010¤\u0001\u001a\u000201H\u0016J\t\u0010¥\u0001\u001a\u00020LH\u0002J\u001b\u0010¦\u0001\u001a\u00020L2\u0007\u0010§\u0001\u001a\u00020\r2\u0007\u0010¨\u0001\u001a\u00020\rH\u0016J\u0013\u0010©\u0001\u001a\u00020L2\b\u0010ª\u0001\u001a\u00030«\u0001H\u0002J\t\u0010¬\u0001\u001a\u00020LH\u0016J9\u0010\u00ad\u0001\u001a\u00020L2\u0006\u0010m\u001a\u00020\r2\t\u0010®\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010¯\u0001\u001a\u0004\u0018\u00010\r2\n\u0010°\u0001\u001a\u0005\u0018\u00010±\u0001H\u0016¢\u0006\u0003\u0010²\u0001J\t\u0010³\u0001\u001a\u00020LH\u0002J\t\u0010´\u0001\u001a\u00020LH\u0016J\u001c\u0010µ\u0001\u001a\u00020L2\u0011\u0010¶\u0001\u001a\f\u0012\u0005\u0012\u00030¸\u0001\u0018\u00010·\u0001H\u0002J\u0012\u0010¹\u0001\u001a\u00020L2\u0007\u0010¶\u0001\u001a\u00020\rH\u0002J\u0012\u0010º\u0001\u001a\u00020L2\u0007\u0010»\u0001\u001a\u00020-H\u0002J\u0012\u0010¼\u0001\u001a\u00020L2\u0007\u0010½\u0001\u001a\u000201H\u0002J\u001b\u0010¾\u0001\u001a\u00020L2\u0007\u0010¿\u0001\u001a\u0002012\u0007\u0010¤\u0001\u001a\u000201H\u0016J\u0014\u0010À\u0001\u001a\u00020L2\t\u0010Á\u0001\u001a\u0004\u0018\u00010oH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020;X\u0082\u000e¢\u0006\u0004\n\u0002\u0010<R\u0010\u0010=\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u00020AX\u0082\u000e¢\u0006\u0004\n\u0002\u0010BR\u0012\u0010C\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010DR\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010J\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010O\u001a\u0004\u0018\u00010PX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001c\u0010U\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y¨\u0006Ã\u0001"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit;", "Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "Lcom/iflytek/aiui/AIUIListener;", "()V", "aiCloudAdapter", "Lcom/pudutech/voiceinteraction/component/cmd/AiCloudAdapter;", "getAiCloudAdapter", "()Lcom/pudutech/voiceinteraction/component/cmd/AiCloudAdapter;", "aiCloudAdapter$delegate", "Lkotlin/Lazy;", "aiuiMsg", "Lcom/iflytek/aiui/AIUIMessage;", "asw", "", "cancelTts", "", "context", "Landroid/content/Context;", "currentReq", "finishAnswer", "gson", "Lcom/google/gson/Gson;", "iatText", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "iflyCmdAdatper", "Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper;", "getIflyCmdAdatper", "()Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper;", "iflyCmdAdatper$delegate", "isAIUIInitialed", "isBella", "isServerConnect", "isSwift", "isWakeUp", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "mAgent", "Lcom/iflytek/aiui/AIUIAgent;", "mAudioListener", "com/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$mAudioListener$1", "Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$mAudioListener$1;", "mConfig", "Lcom/pudutech/voiceinteraction/component/ifly/AIUIConfig;", "mCurrentState", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "mHandler", "Landroid/os/Handler;", "mLowVadCount", "", "mNetworkStateReceive", "Lcom/pudutech/voiceinteraction/component/utils/NetworkStateReceive;", "mScopCloudPlatformJob", "Lkotlinx/coroutines/Job;", "mScopCloudSkillJob", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "mSyncSid", "mWakeupListener", "com/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$mWakeupListener$1", "Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$mWakeupListener$1;", "macStr", "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "networkStateCallback", "com/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$networkStateCallback$1", "Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$networkStateCallback$1;", "notSkill", "Ljava/lang/Boolean;", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "onlyWakeup", "pcmName", "pid", "setIntentObjJob", "Lkotlin/Function0;", "", "ttsVcn", SpeechConstant.ISV_VID, "xttsFile", "Ljava/io/FileOutputStream;", "getXttsFile", "()Ljava/io/FileOutputStream;", "setXttsFile", "(Ljava/io/FileOutputStream;)V", "xttsPath", "getXttsPath", "()Ljava/lang/String;", "setXttsPath", "(Ljava/lang/String;)V", "cancelCurrentRound", "cancelTTS", "changeInteractMode", "checkState", "copyWakeupRes", "createFile", "path", "createScope", "getAIUIParams", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "getParamsFromTTS", "getServerConnectState", "initCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "code", "getTTSParams", "text", "getTtsAudioFrame", "", "event", "Lcom/iflytek/aiui/AIUIEvent;", "getWIFIMac", "handleMicArrayWake", "enable", "httpText", SpeechUtility.TAG_RESOURCE_RESULT, "actionString", "httpTextForBella", "httpTextForCloudPlatform", "iflyWakeup", "wakeupInfo", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "init", "ifOnlyWake", "initAIUIAgent", "initOnlyTTS", "offlineOperation", "keyWord", "onEvent", "aiuiEvent", "onResultChanged", "onTextChanged", "boolean", "b", "postAction", "processAnswerAndSkill", "processCmdReturnEvent", "processError", "processIATResult", "cntJson", "Lcom/alibaba/fastjson/JSONObject;", "processResult", "processVADEvent", "reConnectAIUI", "receiveNetWork", "release", "resetCAE", "resetSkillAndAnswer", "resetWakeup", "sendMessage", "message", "setDesIntentObject", "des", "Ljava/util/ArrayList;", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "data", "setParams", "mode", "setRealBeam", "beam", "setScene", "setSpeaker", "speaker", "languageCode", "setUserInfo", "userInfo", "Lcom/pudutech/voiceinteraction/component/config/UserInfo;", "startRecording", "startTts", "fromTts", "filePath", "ttsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;)V", "stopRecordAudio", "stopRecording", "updateAiCloud", "cmd", "", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$RasaAnswer;", "updateCmd", "updateState", "state", "updateVad", "vol", "wakeup", "angle", "writeAIUIMessage", "audioData", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IFlyVoiceInteractionKit implements IVoiceInteraction, AIUIListener {
    public static final String ASSETS_CONFIG_PATH = "cfg/aiui_phone.cfg";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String DEFAULT_RES_PATH = "res_path=";
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<IFlyVoiceInteractionKit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IFlyVoiceInteractionKit invoke() {
            return new IFlyVoiceInteractionKit(null);
        }
    });
    public static final int LOW_AUDIO_LEVEL = 6;
    private static final int RESETAIUI = 104;
    private static final String TAG = "IFlyVoiceInteractionKit";
    private static final String TAG_AIUI = "AIUI_Message";
    private static final int TIME_OUT = 101;
    private static final int TTS_TIME_OUT = 103;
    private static final int VAD_CUT = 102;
    public static final int VAD_TIMEOUT = 25;
    private static final String WAKEUP_WORDS_FOLDER_PATH = "/sdcard/pudu/wakeupwords";
    private static final String audioParam = "data_type=audio,sample_rate=16000";
    private static final String audiowpgsParam = "data_type=audio,sample_rate=16000,dwa=wpgs";
    private static boolean copied = false;
    private static String generatText = null;
    private static boolean isReady = false;
    private static boolean micCanWakeup = false;
    private static OnTtsListener onTtsListener = null;
    public static final String tinyMix = "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2";
    private static Function1<? super Integer, Unit> ttsCallBack;

    /* renamed from: aiCloudAdapter$delegate, reason: from kotlin metadata */
    private final Lazy aiCloudAdapter;
    private AIUIMessage aiuiMsg;
    private String asw;
    private volatile boolean cancelTts;
    private Context context;
    private String currentReq;
    private String finishAnswer;
    private final Gson gson;
    private StringBuilder iatText;

    /* renamed from: iflyCmdAdatper$delegate, reason: from kotlin metadata */
    private final Lazy iflyCmdAdatper;
    private volatile boolean isAIUIInitialed;
    private boolean isBella;
    private volatile boolean isServerConnect;
    private boolean isSwift;
    private volatile boolean isWakeUp;
    private Language language;
    private AIUIAgent mAgent;
    private IFlyVoiceInteractionKit$mAudioListener$1 mAudioListener;
    private final AIUIConfig mConfig;
    private VoiceInteractionState mCurrentState;
    private final Handler mHandler;
    private int mLowVadCount;
    private NetworkStateReceive mNetworkStateReceive;
    private Job mScopCloudPlatformJob;
    private Job mScopCloudSkillJob;
    private CoroutineScope mScope;
    private String mSyncSid;
    private IFlyVoiceInteractionKit$mWakeupListener$1 mWakeupListener;
    private String macStr;
    private MicArray micArray;
    private IFlyVoiceInteractionKit$networkStateCallback$1 networkStateCallback;
    private Boolean notSkill;
    private IVoiceInteractionListener onVoiceInteractionListener;
    private volatile boolean onlyWakeup;
    private String pcmName;
    private String pid;
    private Function0<Unit> setIntentObjJob;
    private String ttsVcn;
    private String vid;
    private FileOutputStream xttsFile;
    private String xttsPath;

    private final AiCloudAdapter getAiCloudAdapter() {
        return (AiCloudAdapter) this.aiCloudAdapter.getValue();
    }

    private final IflyCmdAdatper getIflyCmdAdatper() {
        return (IflyCmdAdatper) this.iflyCmdAdatper.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$networkStateCallback$1] */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$mWakeupListener$1] */
    /* JADX WARN: Type inference failed for: r1v20, types: [com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$mAudioListener$1] */
    private IFlyVoiceInteractionKit() {
        this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$mHandler$1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:12:0x00b9, code lost:
            
                return true;
             */
            @Override // android.os.Handler.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final boolean handleMessage(Message msg) {
                boolean z;
                AIUIMessage aIUIMessage;
                AIUIMessage aIUIMessage2;
                AIUIMessage aIUIMessage3;
                AIUIMessage aIUIMessage4;
                AIUIMessage aIUIMessage5;
                AIUIMessage aIUIMessage6;
                OnTtsListener onTtsListener2;
                OnTtsListener onTtsListener3;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                switch (msg.what) {
                    case 101:
                        Pdlog.m3273d("IFlyVoiceInteractionKit", "timeout 60s: ");
                        IFlyVoiceInteractionKit.this.updateState(VoiceInteractionState.ErrorAudioError);
                        z = IFlyVoiceInteractionKit.this.isBella;
                        if (z) {
                            IFlyVoiceInteractionKit.this.cancelCurrentRound();
                            break;
                        }
                        break;
                    case 102:
                        aIUIMessage = IFlyVoiceInteractionKit.this.aiuiMsg;
                        aIUIMessage.msgType = 3;
                        aIUIMessage2 = IFlyVoiceInteractionKit.this.aiuiMsg;
                        aIUIMessage2.arg1 = 0;
                        aIUIMessage3 = IFlyVoiceInteractionKit.this.aiuiMsg;
                        aIUIMessage3.arg2 = 0;
                        aIUIMessage4 = IFlyVoiceInteractionKit.this.aiuiMsg;
                        aIUIMessage4.params = "data_type=audio,sample_rate=16000";
                        aIUIMessage5 = IFlyVoiceInteractionKit.this.aiuiMsg;
                        aIUIMessage5.data = (byte[]) null;
                        IFlyVoiceInteractionKit iFlyVoiceInteractionKit = IFlyVoiceInteractionKit.this;
                        aIUIMessage6 = iFlyVoiceInteractionKit.aiuiMsg;
                        iFlyVoiceInteractionKit.sendMessage(aIUIMessage6);
                        break;
                    case 103:
                        onTtsListener2 = IFlyVoiceInteractionKit.onTtsListener;
                        if (onTtsListener2 != null) {
                            onTtsListener2.onError(-1, "GENERATE TTS TIME OUT");
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("onTtsListener cancel== ");
                        onTtsListener3 = IFlyVoiceInteractionKit.onTtsListener;
                        sb.append(onTtsListener3);
                        sb.append("  ");
                        sb.append(IFlyVoiceInteractionKit.this.getXttsPath());
                        Pdlog.m3273d("IFlyVoiceInteractionKit", sb.toString());
                        IFlyVoiceInteractionKit.onTtsListener = (OnTtsListener) null;
                        IFlyVoiceInteractionKit.this.setXttsPath((String) null);
                        break;
                    case 104:
                        Pdlog.m3273d("IFlyVoiceInteractionKit", "INIT RESET AIUI");
                        IFlyVoiceInteractionKit.this.reConnectAIUI();
                        break;
                }
            }
        });
        this.pid = "";
        this.vid = "";
        this.mConfig = new AIUIConfig();
        this.iflyCmdAdatper = LazyKt.lazy(new Function0<IflyCmdAdatper>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$iflyCmdAdatper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final IflyCmdAdatper invoke() {
                return new IflyCmdAdatper();
            }
        });
        this.aiCloudAdapter = LazyKt.lazy(new Function0<AiCloudAdapter>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$aiCloudAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AiCloudAdapter invoke() {
                return new AiCloudAdapter();
            }
        });
        this.gson = new Gson();
        this.ttsVcn = "x_chongchong";
        this.mCurrentState = VoiceInteractionState.Unknown;
        this.iatText = new StringBuilder();
        this.aiuiMsg = new AIUIMessage();
        this.isBella = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot");
        this.isSwift = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "SwiftBot");
        this.mWakeupListener = new IWakeupListener() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$mWakeupListener$1
            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeMessage(byte[] audioData) {
                boolean z;
                String wIFIMac;
                z = IFlyVoiceInteractionKit.this.isWakeUp;
                if (z) {
                    IFlyVoiceInteractionKit.this.writeAIUIMessage(audioData);
                    PuduAudioReport puduAudioReport = PuduAudioReport.INSTANCE;
                    if (audioData == null) {
                        Intrinsics.throwNpe();
                    }
                    wIFIMac = IFlyVoiceInteractionKit.this.getWIFIMac();
                    puduAudioReport.recodeDate(audioData, wIFIMac);
                }
            }

            @Override // com.pudutech.mic_array.mic.IWakeupListener
            public void onWakeSuccess(int p0, int p1, String p2, int p3) {
                boolean z = true;
                Pdlog.m3273d("AIUI_Message", "onWakeSuccess  angle = " + p0 + "  beam = " + p1 + "  keyword = " + p2 + "  score = " + p3);
                String str = p2;
                if (str != null && !StringsKt.isBlank(str)) {
                    z = false;
                }
                if (z) {
                    IFlyVoiceInteractionKit.this.iflyWakeup(new WakeupInfo(p0, p1, p2, Integer.valueOf(p3)));
                    return;
                }
                Iterator<String> it = VoiceCommentConfig.INSTANCE.getCnWakeupList().iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(it.next(), p2)) {
                        IFlyVoiceInteractionKit.this.iflyWakeup(new WakeupInfo(p0, p1, p2, Integer.valueOf(p3)));
                        return;
                    }
                }
            }
        };
        this.mAudioListener = new IAudioListener() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$mAudioListener$1
            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onRawData(byte[] p0) {
            }

            @Override // com.pudutech.mic_array.mic.IAudioListener
            public void onSingleData(byte[] p0) {
            }
        };
        this.asw = "";
        this.pcmName = "";
        this.currentReq = "";
        this.networkStateCallback = new NetworkStateReceive.NetworkStateCallback() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$networkStateCallback$1
            @Override // com.pudutech.voiceinteraction.component.utils.NetworkStateReceive.NetworkStateCallback
            public void onNetWorkStateChanged(boolean isConnet) {
                boolean z;
                NetworkStateReceive networkStateReceive;
                Pdlog.m3273d("IFlyVoiceInteractionKit", "onNetWorkStateChanged  " + isConnet + ' ');
                if (isConnet) {
                    z = IFlyVoiceInteractionKit.this.isServerConnect;
                    if (z) {
                        return;
                    }
                    IFlyVoiceInteractionKit.this.reConnectAIUI();
                    IFlyVoiceInteractionKit.this.changeInteractMode();
                    networkStateReceive = IFlyVoiceInteractionKit.this.mNetworkStateReceive;
                    if (networkStateReceive != null) {
                        IFlyVoiceInteractionKit.access$getContext$p(IFlyVoiceInteractionKit.this).unregisterReceiver(networkStateReceive);
                    }
                    IFlyVoiceInteractionKit.this.mNetworkStateReceive = (NetworkStateReceive) null;
                }
            }
        };
    }

    public /* synthetic */ IFlyVoiceInteractionKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final /* synthetic */ Context access$getContext$p(IFlyVoiceInteractionKit iFlyVoiceInteractionKit) {
        Context context = iFlyVoiceInteractionKit.context;
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

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: IFlyVoiceInteractionKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R+\u0010 \u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$Companion;", "", "()V", "ASSETS_CONFIG_PATH", "", "DEFAULT_RES_PATH", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit;", "INSTANCE$delegate", "Lkotlin/Lazy;", "LOW_AUDIO_LEVEL", "", "RESETAIUI", "TAG", "TAG_AIUI", "TIME_OUT", "TTS_TIME_OUT", "VAD_CUT", "VAD_TIMEOUT", "WAKEUP_WORDS_FOLDER_PATH", "audioParam", "audiowpgsParam", "copied", "", "generatText", "isReady", "micCanWakeup", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "tinyMix", "ttsCallBack", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "code", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final IFlyVoiceInteractionKit getINSTANCE() {
            Lazy lazy = IFlyVoiceInteractionKit.INSTANCE$delegate;
            Companion companion = IFlyVoiceInteractionKit.INSTANCE;
            return (IFlyVoiceInteractionKit) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void createScope() {
        if (this.mScope == null) {
            this.mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            Pdlog.m3273d(TAG, "createScope() mScope=" + this.mScope);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public boolean init(Context context, Language language, IVoiceInteractionListener onVoiceInteractionListener, String pid, String vid, MicArray micArray, boolean ifOnlyWake) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Intrinsics.checkParameterIsNotNull(micArray, "micArray");
        Pdlog.m3273d(TAG, "init() context = " + context + ", onVoiceInteractionListener = " + onVoiceInteractionListener);
        this.context = context;
        this.language = language;
        this.onVoiceInteractionListener = onVoiceInteractionListener;
        this.pid = pid;
        this.vid = vid;
        LogUtils.DEBUG = !UrlOkManager.INSTANCE.isOkTestServer();
        Pdlog.m3273d(TAG, "start init MicArray");
        this.isBella = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot");
        this.isSwift = Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "SwiftBot");
        this.micArray = micArray;
        this.onlyWakeup = ifOnlyWake;
        if (!ifOnlyWake) {
            initAIUIAgent(context);
        }
        Pdlog.m3273d(TAG, "end init MicArray");
        return true;
    }

    private final void copyWakeupRes() {
        Pdlog.m3273d(TAG, "copyWakeupRes VoiceCommentConfig.wakeResIsCopyFinisd:" + VoiceCommentConfig.INSTANCE.getWakeResIsCopyFinisd());
        if (VoiceCommentConfig.INSTANCE.getWakeResIsCopyFinisd()) {
            return;
        }
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        copyWakeupWordsToSDCard(context, WAKEUP_WORDS_FOLDER_PATH, VoiceCommentConfig.INSTANCE.getCnAceWakeupWorkAssetName());
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        copyWakeupWordsToSDCard(context2, WAKEUP_WORDS_FOLDER_PATH, "chinese_hlw.cfg");
        VoiceCommentConfig.INSTANCE.setWakeResIsCopyFinisd(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void iflyWakeup(WakeupInfo wakeupInfo) {
        Pdlog.m3273d(TAG_AIUI, "AIUI iflyWakeup");
        cancelTTS();
        if (this.isBella) {
            resetSkillAndAnswer();
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onWakeup(wakeupInfo);
        }
        if (!this.onlyWakeup) {
            Pdlog.m3273d(TAG_AIUI, "AIUI wakeUp");
            MicArray micArray = this.micArray;
            if (micArray != null) {
                micArray.startWakeup(0);
            }
            if (StringsKt.equals$default(wakeupInfo != null ? wakeupInfo.getWakeUpWord() : null, "", false, 2, null)) {
                AIUIMessage aIUIMessage = this.aiuiMsg;
                aIUIMessage.msgType = 7;
                aIUIMessage.arg1 = 0;
                aIUIMessage.arg2 = 0;
                aIUIMessage.params = "";
                aIUIMessage.data = (byte[]) null;
                sendMessage(aIUIMessage);
                Pdlog.m3273d(TAG_AIUI, "AIUI Wakeupword is null ");
                return;
            }
            AIUIMessage aIUIMessage2 = this.aiuiMsg;
            aIUIMessage2.msgType = 7;
            aIUIMessage2.arg1 = 0;
            aIUIMessage2.arg2 = 0;
            MicArray micArray2 = this.micArray;
            Boolean valueOf = micArray2 != null ? Boolean.valueOf(micArray2.isFreeWake()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.booleanValue()) {
                AIUIMessage aIUIMessage3 = this.aiuiMsg;
                StringBuilder sb = new StringBuilder();
                sb.append("keyword=");
                sb.append(wakeupInfo != null ? wakeupInfo.getWakeUpWord() : null);
                aIUIMessage3.params = sb.toString();
            } else {
                this.aiuiMsg.params = "";
            }
            AIUIMessage aIUIMessage4 = this.aiuiMsg;
            aIUIMessage4.data = (byte[]) null;
            sendMessage(aIUIMessage4);
            return;
        }
        Pdlog.m3273d(TAG_AIUI, "onlyWakeup == " + this.onlyWakeup + "  不交互");
    }

    private final void initAIUIAgent(Context context) {
        SystemTool systemTool = SystemTool.INSTANCE;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (!systemTool.isConnected(context) && this.isAIUIInitialed) {
            Pdlog.m3274e(TAG, " initAIUIAgent  Network is unavailable");
            IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
            if (iVoiceInteractionListener != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.ErrorNetWork);
            }
            this.isServerConnect = false;
            receiveNetWork(context);
            Function1<? super Integer, Unit> function1 = ttsCallBack;
            if (function1 != null) {
                function1.invoke(-1);
            }
            ttsCallBack = (Function1) null;
        }
        if (this.isServerConnect) {
            Pdlog.m3274e(TAG, "initAIUIAgent  isServerConnect is true");
            changeInteractMode();
            Function1<? super Integer, Unit> function12 = ttsCallBack;
            if (function12 != null) {
                function12.invoke(0);
            }
            ttsCallBack = (Function1) null;
            return;
        }
        if (this.isAIUIInitialed) {
            Pdlog.m3274e(TAG, "initAIUIAgent  isAIUIInitialed is true isReady=" + isReady);
            if (isReady) {
                this.mHandler.sendEmptyMessageDelayed(104, 5000L);
            }
            changeInteractMode();
            return;
        }
        this.isWakeUp = false;
        this.mCurrentState = VoiceInteractionState.Unknown;
        this.mLowVadCount = 0;
        AIUISetting.setSystemInfo("sn", VoiceInteractionKit.INSTANCE.getProduct_type() + getWIFIMac());
        this.mAgent = AIUIAgent.createAgent(context, getAIUIParams(), this);
        UserInfo userInfo = VoiceCommentConfig.INSTANCE.getUserInfo();
        if (userInfo != null) {
            setUserInfo(userInfo);
        }
        setScene();
        this.isAIUIInitialed = true;
    }

    private final String getAIUIParams() {
        String str;
        IOException e;
        JSONException e2;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        AssetManager assets = context.getResources().getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assets, "context.getResources().getAssets()");
        try {
            InputStream open = assets.open(ASSETS_CONFIG_PATH);
            Intrinsics.checkExpressionValueIsNotNull(open, "assetManager.open(ASSETS_CONFIG_PATH)");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            str = new String(bArr, Charsets.UTF_8);
            try {
                JSONObject parseObject = JSON.parseObject(str);
                JSONObject jSONObject = parseObject.getJSONObject("vad");
                if (jSONObject != null) {
                    jSONObject.put("vad_eos", (Object) Integer.valueOf(VoiceCommentConfig.INSTANCE.getVadEosTimeout()));
                }
                if (jSONObject != null) {
                    jSONObject.put("vad_bos", (Object) Integer.valueOf(VoiceCommentConfig.INSTANCE.getVadBosTimeout()));
                }
                JSONObject jSONObject2 = parseObject.getJSONObject(AIUIConstant.PARAM_SPEECH);
                if (VoiceCommentConfig.INSTANCE.isContinuous()) {
                    if (jSONObject2 != null) {
                        jSONObject2.put(AIUIConstant.KEY_INTERACT_MODE, (Object) AIUIConstant.INTERACT_MODE_CONTINUOUS);
                    }
                } else if (jSONObject2 != null) {
                    jSONObject2.put(AIUIConstant.KEY_INTERACT_MODE, (Object) AIUIConstant.INTERACT_MODE_ONESHOT);
                }
                if (VoiceCommentConfig.INSTANCE.getRecodeType() == 2 && jSONObject2 != null) {
                    jSONObject2.put(AIUIConstant.KEY_DATA_SOURCE, (Object) "user");
                }
                str = parseObject.toString();
            } catch (JSONException e3) {
                e2 = e3;
                e2.printStackTrace();
                Pdlog.m3273d(TAG, "getAIUIParams:" + str + ' ' + VoiceCommentConfig.INSTANCE.isContinuous());
                return str;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                Pdlog.m3273d(TAG, "getAIUIParams:" + str + ' ' + VoiceCommentConfig.INSTANCE.isContinuous());
                return str;
            }
        } catch (JSONException e5) {
            str = "";
            e2 = e5;
        } catch (IOException e6) {
            str = "";
            e = e6;
        }
        Pdlog.m3273d(TAG, "getAIUIParams:" + str + ' ' + VoiceCommentConfig.INSTANCE.isContinuous());
        return str;
    }

    private final void setScene() {
        String str = "{\"global\":{\"scene\":\"" + VoiceCommentConfig.INSTANCE.getScene() + "\"}}";
        Pdlog.m3273d(TAG, "setScene: " + str);
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 10;
        aIUIMessage.arg1 = 0;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = str;
        aIUIMessage.data = (byte[]) null;
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.sendMessage(aIUIMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeInteractMode() {
        Pdlog.m3273d(TAG, "changeInteractMode: " + VoiceCommentConfig.INSTANCE.isContinuous());
        if (VoiceCommentConfig.INSTANCE.isContinuous()) {
            AIUIMessage aIUIMessage = this.aiuiMsg;
            aIUIMessage.msgType = 10;
            aIUIMessage.arg1 = 0;
            aIUIMessage.arg2 = 0;
            aIUIMessage.params = "{\"speech\":{\"interact_mode\":\"continuous\"}}";
            aIUIMessage.data = (byte[]) null;
            AIUIAgent aIUIAgent = this.mAgent;
            if (aIUIAgent != null) {
                aIUIAgent.sendMessage(aIUIMessage);
            }
        }
        setScene();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
    
        if (r4.intValue() != (-4)) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a6, code lost:
    
        if (r4.intValue() != 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a8, code lost:
    
        com.pudutech.base.Pdlog.m3273d(com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit.TAG, "开启收音成功");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x005a, code lost:
    
        if (r4.intValue() != 0) goto L16;
     */
    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void startRecording() {
        Pdlog.m3273d(TAG, "startRecording()");
        try {
            int recodeType = VoiceCommentConfig.INSTANCE.getRecodeType();
            if (recodeType == 1) {
                iflyWakeup(new WakeupInfo(0, 0, null, null, 12, null));
                this.aiuiMsg.msgType = 22;
                this.aiuiMsg.arg1 = 0;
                this.aiuiMsg.arg2 = 0;
                this.aiuiMsg.params = "sample_rate=16000,data_type=audio,pers_param={\"uid\":\"\"},tag=audio-tag";
                this.aiuiMsg.data = (byte[]) null;
                AIUIAgent aIUIAgent = this.mAgent;
                if (aIUIAgent != null) {
                    aIUIAgent.sendMessage(this.aiuiMsg);
                    return;
                }
                return;
            }
            if (recodeType != 2) {
                return;
            }
            this.cancelTts = false;
            MicArray micArray = this.micArray;
            Integer valueOf = micArray != null ? Integer.valueOf(micArray.startRecord(this.mWakeupListener, this.mAudioListener)) : null;
            Pdlog.m3273d(TAG, "StartRecord == " + valueOf + ' ' + this.mWakeupListener);
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
                Pdlog.m3274e(TAG, "开启收音失败,请重启机器");
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "initRecoding Exception: " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void stopRecording() {
        try {
            handleMicArrayWake(true);
            Pdlog.m3273d(TAG, "stopRecording()");
            if (VoiceCommentConfig.INSTANCE.getRecodeType() == 2) {
                MicArray micArray = this.micArray;
                Pdlog.m3273d(TAG, "stopRecordStatus == " + (micArray != null ? Boolean.valueOf(micArray.stopRecord()) : null));
            } else {
                this.aiuiMsg.msgType = 23;
                this.aiuiMsg.arg1 = 0;
                this.aiuiMsg.arg2 = 0;
                this.aiuiMsg.params = audioParam;
                this.aiuiMsg.data = (byte[]) null;
                sendMessage(this.aiuiMsg);
            }
            if (VoiceCommentConfig.INSTANCE.isContinuous() || this.isBella) {
                stopRecordAudio();
                cancelTTS();
            }
            if (this.isBella) {
                return;
            }
            updateState(VoiceInteractionState.Idle);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "stopRecording Exception: " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void wakeup(int angle, int beam) {
        this.mHandler.removeMessages(101);
        this.mHandler.sendEmptyMessageDelayed(101, 20000L);
        Pdlog.m3273d(TAG, "wakeup() angle = " + angle + ", beam = " + beam);
        int recodeType = VoiceCommentConfig.INSTANCE.getRecodeType();
        if (recodeType == 1) {
            iflyWakeup(new WakeupInfo(0, 0, null, null, 12, null));
        } else {
            if (recodeType != 2) {
                return;
            }
            iflyWakeup(new WakeupInfo(angle, beam, null, null, 12, null));
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setRealBeam(int beam) {
        MicArray micArray;
        if (VoiceCommentConfig.INSTANCE.getRecodeType() != 2 || (micArray = this.micArray) == null) {
            return;
        }
        micArray.startWakeup(beam);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void resetCAE() {
        Pdlog.m3273d(TAG, "resetCAE()");
        PuduAudioReport.INSTANCE.release();
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.destroy();
        }
        this.isAIUIInitialed = false;
        this.isServerConnect = false;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        initAIUIAgent(context);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void release() {
        Pdlog.m3273d(TAG, "release()");
        PuduAudioReport.INSTANCE.release();
        this.isAIUIInitialed = false;
        this.isServerConnect = false;
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.destroy();
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void startTts(String text, Boolean fromTts, String filePath, OnTtsListener ttsListener) {
        String tTSParams;
        Intrinsics.checkParameterIsNotNull(text, "text");
        Pdlog.m3273d(TAG, "startTts text == " + text);
        if (this.cancelTts || StringsKt.isBlank(text)) {
            if (fromTts == null) {
                Intrinsics.throwNpe();
            }
            if (!fromTts.booleanValue()) {
                IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
                if (iVoiceInteractionListener != null) {
                    iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.PlayCompleted);
                }
                Pdlog.m3274e(TAG, "cancelTts == " + this.cancelTts + " 已取消，不播放，返回");
                return;
            }
        }
        String str = null;
        if (fromTts != null) {
            if (fromTts.booleanValue()) {
                this.mHandler.sendEmptyMessageDelayed(103, 20000L);
                generatText = text;
                onTtsListener = ttsListener;
                if (filePath == null) {
                    Intrinsics.throwNpe();
                }
                createFile(filePath);
                setParams("user");
                tTSParams = getParamsFromTTS();
            } else {
                generatText = "";
                onTtsListener = (OnTtsListener) null;
                this.xttsPath = (String) null;
                setParams("sdk");
                tTSParams = getTTSParams(text);
            }
            str = tTSParams;
        }
        Pdlog.m3275i(TAG, str);
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 27;
        aIUIMessage.arg1 = 1;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = str;
        byte[] bytes = text.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        aIUIMessage.data = bytes;
        sendMessage(this.aiuiMsg);
    }

    public final FileOutputStream getXttsFile() {
        return this.xttsFile;
    }

    public final void setXttsFile(FileOutputStream fileOutputStream) {
        this.xttsFile = fileOutputStream;
    }

    public final String getXttsPath() {
        return this.xttsPath;
    }

    public final void setXttsPath(String str) {
        this.xttsPath = str;
    }

    private final void createFile(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                file.delete();
            }
            FileUtil.createOrExistsFile(file);
            this.xttsFile = new FileOutputStream(file);
            this.xttsPath = path;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private final void setParams(String mode) {
        byte[] bytes = "".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        sendMessage(new AIUIMessage(10, 0, 0, "{\"tts\":{\"play_mode\":\"" + mode + "\",\"buffer_time\":\"0\",\"stream_type\":\"3\",\"audio_focus\":\"0\"}}", bytes));
    }

    private final String getParamsFromTTS() {
        String str = "@" + System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("vcn=" + TtsConfig.INSTANCE.getIFLY_SPEECH_VOICE_NAME());
        sb.append(",speed=" + String.valueOf(TtsConfig.INSTANCE.getIFLY_SPEECH_SPEED()));
        sb.append(",pitch=" + String.valueOf(TtsConfig.INSTANCE.getIFLY_SPEECH_PITCH()));
        sb.append(",volume=" + String.valueOf(TtsConfig.INSTANCE.getIFLY_SPEECH_VOLUME()));
        sb.append(",ent=x_tts");
        sb.append(",tag=");
        sb.append(str);
        return sb.toString();
    }

    private final String getTTSParams(String text) {
        String str = "@" + System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("vcn=" + this.ttsVcn);
        if (text.length() > 50) {
            sb.append(",speed=55");
            Pdlog.m3275i(TAG, "tts speed is 55,text.length is" + text.length());
        } else {
            sb.append(",speed=50");
        }
        sb.append(",pitch=50");
        sb.append(",volume=" + ((VoiceCommentConfig.INSTANCE.getTtsVolume() * 60) / 100));
        sb.append(",ent=x_tts");
        sb.append(",tag=");
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMessage(AIUIMessage message) {
        Pdlog.m3273d(TAG, "wakeup_state:" + this.isWakeUp + "   msgType: " + message.msgType + "  2=writeaiuimessage isReady=" + isReady);
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent == null || !isReady) {
            return;
        }
        if (aIUIAgent == null) {
            Intrinsics.throwNpe();
        }
        aIUIAgent.sendMessage(message);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setSpeaker(String speaker, String languageCode) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(languageCode, "languageCode");
        Pdlog.m3273d(TAG, "setSpeaker() " + speaker + ' ' + languageCode);
        this.ttsVcn = speaker;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void onlyWakeup(boolean b) {
        this.onlyWakeup = b;
        Pdlog.m3273d(TAG, "onlyWakeup " + b);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelTTS() {
        Pdlog.m3273d(TAG, "cancelTTS()");
        this.cancelTts = true;
        this.mHandler.removeMessages(101);
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 27;
        aIUIMessage.arg1 = 4;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = "";
        aIUIMessage.data = (byte[]) null;
        sendMessage(aIUIMessage);
    }

    private final void resetWakeup() {
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 8;
        aIUIMessage.arg1 = 0;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = audioParam;
        aIUIMessage.data = (byte[]) null;
        sendMessage(aIUIMessage);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void setIntentObject(IntentObjectType type, final ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (type == IntentObjectType.des) {
            if (!this.isServerConnect) {
                this.setIntentObjJob = new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$setIntentObject$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        IFlyVoiceInteractionKit.this.setDesIntentObject(data);
                    }
                };
            } else {
                setDesIntentObject(data);
            }
        }
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public NLPActionType getNLPActionType() {
        return NLPActionType.OTHER;
    }

    private final void onTextChanged(String text, boolean r8) {
        if (!CollectionsKt.contains(VoiceCommentConfig.INSTANCE.getUseWordList(), text)) {
            String str = text;
            if (str == null || str.length() == 0) {
                return;
            }
            if ((text != null ? Integer.valueOf(text.length()) : null).intValue() < 2 || VoiceCommentConfig.INSTANCE.getUseLessWordList().contains(text)) {
                return;
            }
        }
        if (r8) {
            String voiceFile = PuduAudioReport.INSTANCE.getVoiceFile();
            String str2 = voiceFile;
            if (!(str2 == null || str2.length() == 0)) {
                this.pcmName = String.valueOf(ExtKt.md5(new File(voiceFile))) + TtsVoiceHelper.FLIE_MARK;
            }
            this.cancelTts = false;
            this.currentReq = text;
            PuduAudioReport.INSTANCE.startReport(text != null ? text : "", voiceFile);
        }
        this.mHandler.removeMessages(101);
        this.mHandler.sendEmptyMessageDelayed(101, 20000L);
        Pdlog.m3273d("dev_voice", "onTextChanged " + text + " , b = " + r8 + " onVoiceInteractionListener=" + this.onVoiceInteractionListener);
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onResultRequest(text, r8);
        }
        this.asw = String.valueOf(text);
    }

    private final void onResultChanged(String result) {
        this.mHandler.removeMessages(101);
        boolean z = true;
        Pdlog.m3273d(TAG, "onResultChanged result:" + result + ",asw:" + this.asw);
        if (!VoiceCommentConfig.INSTANCE.isContinuous()) {
            if (this.isBella) {
                this.isWakeUp = false;
            } else {
                stopRecording();
            }
        }
        if (!Intrinsics.areEqual(this.asw, "")) {
            if (this.isBella) {
                httpTextForCloudPlatform(this.asw, result);
                return;
            } else {
                httpText(this.asw, result, "", this.pcmName);
                return;
            }
        }
        String str = result;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z && this.isBella) {
            updateState(VoiceInteractionState.Idle);
        } else if (result != null) {
            IVoiceInteraction.DefaultImpls.startTts$default(this, result, null, null, null, 14, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeAIUIMessage(byte[] audioData) {
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 2;
        aIUIMessage.arg1 = 0;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = audiowpgsParam;
        aIUIMessage.data = audioData;
        sendMessage(aIUIMessage);
        updateState(VoiceInteractionState.Recording);
    }

    static /* synthetic */ void httpText$default(IFlyVoiceInteractionKit iFlyVoiceInteractionKit, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        if ((i & 8) != 0) {
            str4 = "";
        }
        iFlyVoiceInteractionKit.httpText(str, str2, str3, str4);
    }

    private final void httpText(String asw, final String result, String actionString, String pcmName) {
        this.cancelTts = false;
        Pdlog.m3273d(TAG, "httpText asw: " + asw + " result:" + result + " actionString: " + actionString + " mac:" + getWIFIMac());
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            OkHttpUtils.INSTANCE.post(wIFIMac, asw, result != null ? result : "", actionString, pcmName);
        }
        OkHttpUtils.INSTANCE.setTtsClick(new Function4<String, String, Integer, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpText$2
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

            /* JADX WARN: Removed duplicated region for block: B:32:0x00ff  */
            /* JADX WARN: Removed duplicated region for block: B:40:0x011a  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke(String str, String str2, int i, String str3) {
                String str4;
                String str5;
                boolean z;
                IVoiceInteractionListener iVoiceInteractionListener;
                String str6;
                List<OkHttpUtils.RasaAnswer> rasa_answer;
                IVoiceInteractionListener iVoiceInteractionListener2;
                IVoiceInteractionListener iVoiceInteractionListener3;
                IVoiceInteractionListener iVoiceInteractionListener4;
                IVoiceInteractionListener iVoiceInteractionListener5;
                str4 = IFlyVoiceInteractionKit.this.currentReq;
                if (Intrinsics.areEqual(str3, str4)) {
                    IFlyVoiceInteractionKit.this.cancelTTS();
                    IFlyVoiceInteractionKit.this.cancelTts = false;
                    String str7 = str2;
                    if (str7 == null || str7.length() == 0) {
                        if (str != null) {
                            if (i == 0) {
                                IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str, null, null, null, 14, null);
                            }
                            iVoiceInteractionListener5 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                            if (iVoiceInteractionListener5 != null) {
                                iVoiceInteractionListener5.onResultResponse(str, str2, i);
                                return;
                            }
                            return;
                        }
                        String str8 = result;
                        if (str8 != null) {
                            IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str8, null, null, null, 14, null);
                        }
                        iVoiceInteractionListener4 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener4 != null) {
                            iVoiceInteractionListener4.onResultResponse(result, str2, i);
                            return;
                        }
                        return;
                    }
                    z = IFlyVoiceInteractionKit.this.isSwift;
                    if (z) {
                        NLPActionType nLPActionType = NLPActionType.OTHER;
                        OkHttpUtils.CloudSkillRespData cloudSkillRespData = (OkHttpUtils.CloudSkillRespData) null;
                        if (i == 2) {
                            try {
                                Gson gson = new Gson();
                                if (str2 != null) {
                                    OkHttpUtils.CloudSkillRespData cloudSkillRespData2 = (OkHttpUtils.CloudSkillRespData) gson.fromJson(str2, OkHttpUtils.CloudSkillRespData.class);
                                    if (cloudSkillRespData2 != null) {
                                        try {
                                            rasa_answer = cloudSkillRespData2.getRasa_answer();
                                        } catch (Exception e) {
                                            e = e;
                                            cloudSkillRespData = cloudSkillRespData2;
                                            Pdlog.m3273d("IFlyVoiceInteractionKit", "onResultResponse: e =" + e.getMessage());
                                            if (nLPActionType == NLPActionType.OTHER) {
                                            }
                                        }
                                    } else {
                                        rasa_answer = null;
                                    }
                                    if (rasa_answer != null) {
                                        for (OkHttpUtils.RasaAnswer rasaAnswer : rasa_answer) {
                                            Pdlog.m3273d("IFlyVoiceInteractionKit", "actionList: AnserBean.ActionBean =" + rasaAnswer.getAction().toString());
                                            NLPActionType.Companion companion = NLPActionType.INSTANCE;
                                            OkHttpUtils.Action action = rasaAnswer.getAction();
                                            nLPActionType = companion.match((action != null ? action.getAction() : null).toString());
                                        }
                                    }
                                    cloudSkillRespData = cloudSkillRespData2;
                                }
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                        if (nLPActionType == NLPActionType.OTHER) {
                            iVoiceInteractionListener3 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                            if (iVoiceInteractionListener3 != null) {
                                iVoiceInteractionListener3.onResultResponse(nLPActionType.getNlpActionType(), String.valueOf(cloudSkillRespData != null ? cloudSkillRespData.getRasa_answer() : null), i);
                                return;
                            }
                            return;
                        }
                        String str9 = result;
                        if (str9 != null) {
                            IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str9, null, null, null, 14, null);
                        }
                        iVoiceInteractionListener2 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener2 != null) {
                            iVoiceInteractionListener2.onResultResponse(result, null, i);
                            return;
                        }
                        return;
                    }
                    if (VoiceInteractionKit.INSTANCE.getType() == 0 && i != 2 && (str6 = result) != null) {
                        IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str6, null, null, null, 14, null);
                    }
                    iVoiceInteractionListener = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener != null) {
                        iVoiceInteractionListener.onResultResponse("", str2, i);
                        return;
                    }
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("current question is ");
                str5 = IFlyVoiceInteractionKit.this.currentReq;
                sb.append(str5);
                sb.append(" but question is ");
                sb.append(str3);
                sb.append("  result is ");
                sb.append(result);
                Pdlog.m3275i("IFlyVoiceInteractionKit", sb.toString());
            }
        });
        OkHttpUtils.INSTANCE.setTtsError(new Function2<String, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpText$3
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
                String str2;
                String str3;
                boolean z;
                IVoiceInteractionListener iVoiceInteractionListener;
                IVoiceInteractionListener iVoiceInteractionListener2;
                Intrinsics.checkParameterIsNotNull(err, "err");
                str2 = IFlyVoiceInteractionKit.this.currentReq;
                if (Intrinsics.areEqual(str, str2)) {
                    IFlyVoiceInteractionKit.this.cancelTTS();
                    IFlyVoiceInteractionKit.this.cancelTts = false;
                    String str4 = result;
                    if (str4 != null) {
                        IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str4, null, null, null, 14, null);
                    }
                    z = IFlyVoiceInteractionKit.this.isSwift;
                    if (!z) {
                        iVoiceInteractionListener2 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener2 != null) {
                            iVoiceInteractionListener2.onResultResponse(result, null, 3);
                            return;
                        }
                        return;
                    }
                    iVoiceInteractionListener = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener != null) {
                        iVoiceInteractionListener.onResultResponse(result, null, 0);
                        return;
                    }
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("current question is ");
                str3 = IFlyVoiceInteractionKit.this.currentReq;
                sb.append(str3);
                sb.append(" but question is ");
                sb.append(str);
                sb.append(" result is ");
                sb.append(result);
                Pdlog.m3275i("IFlyVoiceInteractionKit", sb.toString());
            }
        });
    }

    private final void httpTextForCloudPlatform(String asw, String result) {
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            createScope();
            Pdlog.m3273d(TAG, "httpTextForCloudPlatform() postCloudPlatform");
            CoroutineScope coroutineScope = this.mScope;
            this.mScopCloudPlatformJob = coroutineScope != null ? BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C5771x92126d42(wIFIMac, null, this, asw, result), 3, null) : null;
        }
    }

    private final void httpTextForBella(String asw) {
        this.cancelTts = false;
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            OkHttpUtils.post$default(OkHttpUtils.INSTANCE, wIFIMac, asw, "", "", null, 16, null);
        }
        OkHttpUtils.INSTANCE.setTtsClick(new Function4<String, String, Integer, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpTextForBella$2
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Gson gson;
                IVoiceInteractionListener iVoiceInteractionListener2;
                String str4 = str2;
                if (str4 == null || str4.length() == 0) {
                    if (str != null) {
                        if (i == 0) {
                            IVoiceInteraction.DefaultImpls.startTts$default(IFlyVoiceInteractionKit.this, str, null, null, null, 14, null);
                        }
                        iVoiceInteractionListener = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                        if (iVoiceInteractionListener != null) {
                            iVoiceInteractionListener.onResultResponse(str, str2, i);
                            return;
                        }
                        return;
                    }
                    IFlyVoiceInteractionKit.this.notSkill = true;
                    IFlyVoiceInteractionKit.this.processAnswerAndSkill();
                    return;
                }
                gson = IFlyVoiceInteractionKit.this.gson;
                OkHttpUtils.CloudSkillRespData cloudSkillRespData = (OkHttpUtils.CloudSkillRespData) gson.fromJson(str2, OkHttpUtils.CloudSkillRespData.class);
                Pdlog.m3273d("IFlyVoiceInteractionKit", "bella skill == " + cloudSkillRespData);
                List<OkHttpUtils.RasaAnswer> rasa_answer = cloudSkillRespData != null ? cloudSkillRespData.getRasa_answer() : null;
                if (i == 1) {
                    iVoiceInteractionListener2 = IFlyVoiceInteractionKit.this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener2 != null) {
                        iVoiceInteractionListener2.onResultResponse(str, GsonUtils.toJson(cloudSkillRespData != null ? cloudSkillRespData.getFaq_answer() : null), i);
                    }
                    IFlyVoiceInteractionKit.this.updateState(VoiceInteractionState.Idle);
                    return;
                }
                if (rasa_answer != null) {
                    IFlyVoiceInteractionKit.this.updateAiCloud(rasa_answer);
                } else {
                    IFlyVoiceInteractionKit.this.notSkill = true;
                    IFlyVoiceInteractionKit.this.processAnswerAndSkill();
                }
            }
        });
        OkHttpUtils.INSTANCE.setTtsError(new Function2<String, String, Unit>() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpTextForBella$3
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Intrinsics.checkParameterIsNotNull(err, "err");
                IFlyVoiceInteractionKit.this.notSkill = true;
                IFlyVoiceInteractionKit.this.processAnswerAndSkill();
                Pdlog.m3273d("IFlyVoiceInteractionKit", "bella ttsError skill == " + err);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processAnswerAndSkill() {
        boolean z = true;
        if (this.notSkill == null) {
            Pdlog.m3273d(TAG, "processAnswerAndSkill wait for AICloud");
            return;
        }
        String str = this.finishAnswer;
        if (str == null) {
            Pdlog.m3273d(TAG, "processAnswerAndSkill waiting for Cloud platform");
            return;
        }
        String str2 = str;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            updateState(VoiceInteractionState.Idle);
            return;
        }
        String str3 = this.finishAnswer;
        if (str3 != null) {
            IVoiceInteraction.DefaultImpls.startTts$default(this, str3, null, null, null, 14, null);
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onResultResponse(this.finishAnswer, null, 0);
        }
    }

    private final void resetSkillAndAnswer() {
        this.notSkill = (Boolean) null;
        this.finishAnswer = (String) null;
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
        if (this.isBella) {
            return;
        }
        httpText$default(this, this.asw, "", actionString, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0015 A[Catch: Exception -> 0x00ab, TryCatch #0 {Exception -> 0x00ab, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0018 A[Catch: Exception -> 0x00ab, TryCatch #0 {Exception -> 0x00ab, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getWIFIMac() {
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
                    LogUtil.INSTANCE.m3309d(TAG, "onCmdChanged " + sb.toString(), new Object[0]);
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

    /* JADX WARN: Removed duplicated region for block: B:68:0x019f A[Catch: Exception -> 0x020e, TryCatch #0 {Exception -> 0x020e, blocks: (B:61:0x018d, B:63:0x0193, B:68:0x019f, B:70:0x01a5, B:74:0x01df, B:76:0x01e6), top: B:60:0x018d }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a5 A[Catch: Exception -> 0x020e, TryCatch #0 {Exception -> 0x020e, blocks: (B:61:0x018d, B:63:0x0193, B:68:0x019f, B:70:0x01a5, B:74:0x01df, B:76:0x01e6), top: B:60:0x018d }] */
    @Override // com.iflytek.aiui.AIUIListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onEvent(AIUIEvent aiuiEvent) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(aiuiEvent, "aiuiEvent");
        Pdlog.m3273d(TAG_AIUI, "onEvent: aiuiEvent.eventType = " + aiuiEvent.eventType + "  aiuiEvent.info = " + aiuiEvent.info);
        switch (aiuiEvent.eventType) {
            case 1:
                processResult(aiuiEvent);
                return;
            case 2:
                this.mHandler.removeMessages(101);
                int i = aiuiEvent.arg1;
                processError(aiuiEvent);
                Pdlog.m3274e(TAG_AIUI, "EVENT_ERROR = " + i + "  info == " + aiuiEvent.info);
                return;
            case 3:
                if (1 == aiuiEvent.arg1) {
                    Pdlog.m3273d(TAG_AIUI, "STATE_IDLE");
                } else if (2 == aiuiEvent.arg1) {
                    isReady = true;
                    Pdlog.m3273d(TAG_AIUI, "STATE_READY");
                } else if (3 == aiuiEvent.arg1) {
                    Pdlog.m3273d(TAG_AIUI, "STATE_WORKING");
                    updateState(VoiceInteractionState.Recording);
                }
                Pdlog.m3273d(TAG_AIUI, "STATE: " + aiuiEvent.arg1);
                return;
            case 4:
                Pdlog.m3273d(TAG_AIUI, "进入识别状态 EVENT_WAKEUP");
                try {
                    String str = aiuiEvent.info;
                    if (str != null && str.length() != 0) {
                        z = false;
                        if (!z) {
                            this.isWakeUp = true;
                            handleMicArrayWake(false);
                            return;
                        }
                        JSONObject parseObject = JSON.parseObject(aiuiEvent.info);
                        Intrinsics.checkExpressionValueIsNotNull(parseObject, "JSON.parseObject(aiuiEvent.info)");
                        String keyWord = parseObject.getJSONObject("ivw_result").getString("keyword");
                        int intValue = parseObject.getIntValue("type");
                        Pdlog.m3273d(TAG_AIUI, "唤醒命令词：" + keyWord);
                        if (intValue != 1 && intValue != 2) {
                            handleMicArrayWake(false);
                            this.isWakeUp = true;
                            return;
                        }
                        this.aiuiMsg.msgType = 8;
                        this.aiuiMsg.arg1 = 0;
                        this.aiuiMsg.arg2 = 0;
                        this.aiuiMsg.params = audioParam;
                        this.aiuiMsg.data = (byte[]) null;
                        sendMessage(this.aiuiMsg);
                        Intrinsics.checkExpressionValueIsNotNull(keyWord, "keyWord");
                        offlineOperation(keyWord);
                        return;
                    }
                    z = true;
                    if (!z) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                break;
            case 5:
                this.mHandler.removeMessages(101);
                Pdlog.m3273d(TAG_AIUI, "SLEEP 设备进入休眠");
                this.isWakeUp = false;
                handleMicArrayWake(true);
                updateState(VoiceInteractionState.Sleep);
                return;
            case 6:
                int i2 = aiuiEvent.arg1;
                if (i2 == 0) {
                    Pdlog.m3273d(TAG, "找到vad_bos");
                } else if (i2 == 2) {
                    Pdlog.m3273d(TAG, "找到vad_eos");
                } else if (i2 == 3) {
                    Pdlog.m3273d(TAG, "前端点超时");
                } else {
                    Pdlog.m3273d(TAG, "aiuiEvent.arg1 == " + aiuiEvent.arg1 + "  aiuiEvent.arg2 == " + aiuiEvent.arg2);
                }
                processVADEvent(aiuiEvent);
                return;
            case 7:
            case 9:
            case 10:
            default:
                return;
            case 8:
                processCmdReturnEvent(aiuiEvent);
                return;
            case 11:
                Pdlog.m3273d(TAG_AIUI, "已开始录音");
                return;
            case 12:
                Pdlog.m3273d(TAG_AIUI, "已停止录音");
                return;
            case 13:
                Function1<? super Integer, Unit> function1 = ttsCallBack;
                if (function1 != null) {
                    function1.invoke(0);
                }
                isReady = true;
                ttsCallBack = (Function1) null;
                Pdlog.m3273d(TAG_AIUI, "aiuiEvent 已连接服务器");
                if (!this.isBella) {
                    updateState(VoiceInteractionState.Idle);
                }
                this.isServerConnect = true;
                Function0<Unit> function0 = this.setIntentObjJob;
                if (function0 != null) {
                    function0.invoke();
                }
                this.setIntentObjJob = (Function0) null;
                AIUIMessage aIUIMessage = this.aiuiMsg;
                aIUIMessage.msgType = 10;
                aIUIMessage.arg1 = 0;
                aIUIMessage.arg2 = 0;
                aIUIMessage.params = "{\"vad\": {\"threshold\": \"0.95\"}}";
                aIUIMessage.data = (byte[]) null;
                sendMessage(aIUIMessage);
                updateState(VoiceInteractionState.ConnectToServer);
                return;
            case 14:
                Pdlog.m3273d(TAG_AIUI, "aiuiEvent 与服务器断开连接");
                this.isServerConnect = false;
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                receiveNetWork(context);
                Function1<? super Integer, Unit> function12 = ttsCallBack;
                if (function12 != null) {
                    function12.invoke(-1);
                }
                ttsCallBack = (Function1) null;
                updateState(VoiceInteractionState.DisConnectToServer);
                return;
            case 15:
                int i3 = aiuiEvent.arg1;
                if (i3 == 1) {
                    Pdlog.m3273d(TAG, "tts事件：开始或者暂停");
                    return;
                }
                if (i3 == 4) {
                    updateState(VoiceInteractionState.Speaking);
                    return;
                }
                if (i3 != 5) {
                    return;
                }
                Pdlog.m3273d(TAG, "播放完成tts");
                this.mHandler.removeMessages(101);
                updateState(VoiceInteractionState.PlayCompleted);
                if (this.isBella || this.isSwift) {
                    updateState(VoiceInteractionState.Idle);
                    return;
                }
                return;
        }
    }

    private final void processCmdReturnEvent(AIUIEvent event) {
        int i = event.arg1;
        if (i == 13) {
            int i2 = event.data.getInt(InternalConstant.KEY_SYNC_DATA_TYPE);
            if (event.arg2 == 0) {
                if (3 == i2) {
                    this.mSyncSid = event.data.getString("sid");
                    Pdlog.m3273d(TAG, "schema数据同步成功，sid=" + this.mSyncSid);
                    return;
                }
                return;
            }
            if (3 == i2) {
                this.mSyncSid = event.data.getString("sid");
                LogProxy.INSTANCE.m3306e(TAG, "schema数据同步出错：" + event.arg2 + "，sid=" + this.mSyncSid);
                if (this.isBella) {
                    updateState(VoiceInteractionState.Unknown);
                    updateState(VoiceInteractionState.Idle);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 24 && 4 == event.data.getInt(InternalConstant.KEY_SYNC_DATA_TYPE)) {
            String string = event.data.getString(SpeechUtility.TAG_RESOURCE_RESULT);
            if (string == null) {
                string = "";
            }
            if (event.arg2 == 0) {
                Pdlog.m3273d(TAG, "查询结果：" + string);
                return;
            }
            Pdlog.m3273d(TAG, "schema数据状态查询出错：" + String.valueOf(event.arg2) + ", result:" + string);
        }
    }

    private final void processVADEvent(AIUIEvent aiuiEvent) {
        Pdlog.m3275i(TAG, "PROCESS VAD EVENT aiuiEvent.eventType = " + aiuiEvent.eventType + " aiuiEvent.arg1 = " + aiuiEvent.arg1);
        if (aiuiEvent.arg1 == 1) {
            updateVad(aiuiEvent.arg2);
            this.mLowVadCount++;
            if (aiuiEvent.arg2 <= 6) {
                Pdlog.m3273d(TAG, "stopcnt：" + this.mLowVadCount);
                this.mLowVadCount = this.mLowVadCount + 1;
            } else {
                Pdlog.m3275i(TAG, "vadcnt:" + this.mLowVadCount + "vol:" + aiuiEvent.arg2);
                this.mLowVadCount = 0;
            }
        }
        if (aiuiEvent.arg1 == 3) {
            Pdlog.m3275i(TAG, "VAD BOS TIMEOUT");
            this.mLowVadCount = 0;
            this.mHandler.removeMessages(101);
            updateState(VoiceInteractionState.BosTimeout);
            updateState(VoiceInteractionState.Idle);
        }
        if (aiuiEvent.arg1 == 0) {
            Pdlog.m3275i("processVADEvent", "cancelTTS");
        }
        if (aiuiEvent.arg1 == 2 && this.isBella) {
            updateState(VoiceInteractionState.Eos);
        }
        if (this.mLowVadCount > 25) {
            this.mLowVadCount = 0;
            AIUIMessage aIUIMessage = this.aiuiMsg;
            aIUIMessage.msgType = 3;
            aIUIMessage.arg1 = 0;
            aIUIMessage.arg2 = 0;
            aIUIMessage.params = audioParam;
            aIUIMessage.data = (byte[]) null;
            sendMessage(aIUIMessage);
        }
    }

    private final void updateVad(int vol) {
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onVolumeChanged(vol);
        }
        Pdlog.m3275i(TAG, "UPDATE VAD " + vol);
    }

    private final void processIATResult(JSONObject cntJson) throws JSONException {
        JSONObject jSONObject = cntJson.getJSONObject("text");
        Pdlog.m3273d(TAG, "processIATResult text == " + jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("ws");
        Boolean lastResult = jSONObject.getBoolean("ls");
        StringBuilder sb = new StringBuilder();
        int size = jSONArray.size();
        for (int i = 0; i < size; i++) {
            JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("cw");
            int size2 = jSONArray2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                sb.append(jSONArray2.getJSONObject(i2).getString("w"));
            }
        }
        if (!TextUtils.isEmpty(sb)) {
            this.iatText = sb;
        }
        if (TextUtils.isEmpty(this.iatText)) {
            return;
        }
        Pdlog.m3275i("dev_voice", "iatText:" + ((Object) this.iatText) + "lastResult:" + lastResult);
        String valueOf = String.valueOf(this.iatText);
        Intrinsics.checkExpressionValueIsNotNull(lastResult, "lastResult");
        onTextChanged(valueOf, lastResult.booleanValue());
        if (lastResult.booleanValue()) {
            this.iatText = (StringBuilder) null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00cb A[Catch: all -> 0x0378, TryCatch #0 {all -> 0x0378, blocks: (B:3:0x000f, B:6:0x002e, B:8:0x0036, B:9:0x0039, B:10:0x0065, B:12:0x007f, B:14:0x0087, B:15:0x008a, B:17:0x00bf, B:22:0x00cb, B:24:0x00d4, B:27:0x00e3, B:29:0x00ef, B:31:0x00f5, B:33:0x0101, B:35:0x011f, B:37:0x0157, B:39:0x015d, B:41:0x0186, B:43:0x018e, B:45:0x0194, B:47:0x01a2, B:49:0x01a6, B:50:0x01b2, B:54:0x01c1, B:56:0x01c5, B:58:0x01cc, B:60:0x01d2, B:62:0x01dd, B:64:0x01e1, B:68:0x01f4, B:70:0x0201, B:71:0x0206, B:73:0x020a, B:76:0x0138, B:78:0x0211, B:80:0x022a, B:82:0x0254, B:83:0x025d, B:85:0x0261, B:88:0x0279, B:90:0x0289, B:91:0x028c, B:93:0x029c, B:95:0x02a7, B:97:0x02ab, B:98:0x02b0, B:99:0x02e3, B:101:0x02e7, B:102:0x02ea, B:106:0x0351, B:108:0x02fa, B:110:0x0305, B:112:0x0309, B:113:0x030c, B:114:0x033f, B:116:0x0343, B:117:0x0346), top: B:2:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0201 A[Catch: all -> 0x0378, TryCatch #0 {all -> 0x0378, blocks: (B:3:0x000f, B:6:0x002e, B:8:0x0036, B:9:0x0039, B:10:0x0065, B:12:0x007f, B:14:0x0087, B:15:0x008a, B:17:0x00bf, B:22:0x00cb, B:24:0x00d4, B:27:0x00e3, B:29:0x00ef, B:31:0x00f5, B:33:0x0101, B:35:0x011f, B:37:0x0157, B:39:0x015d, B:41:0x0186, B:43:0x018e, B:45:0x0194, B:47:0x01a2, B:49:0x01a6, B:50:0x01b2, B:54:0x01c1, B:56:0x01c5, B:58:0x01cc, B:60:0x01d2, B:62:0x01dd, B:64:0x01e1, B:68:0x01f4, B:70:0x0201, B:71:0x0206, B:73:0x020a, B:76:0x0138, B:78:0x0211, B:80:0x022a, B:82:0x0254, B:83:0x025d, B:85:0x0261, B:88:0x0279, B:90:0x0289, B:91:0x028c, B:93:0x029c, B:95:0x02a7, B:97:0x02ab, B:98:0x02b0, B:99:0x02e3, B:101:0x02e7, B:102:0x02ea, B:106:0x0351, B:108:0x02fa, B:110:0x0305, B:112:0x0309, B:113:0x030c, B:114:0x033f, B:116:0x0343, B:117:0x0346), top: B:2:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020a A[Catch: all -> 0x0378, TryCatch #0 {all -> 0x0378, blocks: (B:3:0x000f, B:6:0x002e, B:8:0x0036, B:9:0x0039, B:10:0x0065, B:12:0x007f, B:14:0x0087, B:15:0x008a, B:17:0x00bf, B:22:0x00cb, B:24:0x00d4, B:27:0x00e3, B:29:0x00ef, B:31:0x00f5, B:33:0x0101, B:35:0x011f, B:37:0x0157, B:39:0x015d, B:41:0x0186, B:43:0x018e, B:45:0x0194, B:47:0x01a2, B:49:0x01a6, B:50:0x01b2, B:54:0x01c1, B:56:0x01c5, B:58:0x01cc, B:60:0x01d2, B:62:0x01dd, B:64:0x01e1, B:68:0x01f4, B:70:0x0201, B:71:0x0206, B:73:0x020a, B:76:0x0138, B:78:0x0211, B:80:0x022a, B:82:0x0254, B:83:0x025d, B:85:0x0261, B:88:0x0279, B:90:0x0289, B:91:0x028c, B:93:0x029c, B:95:0x02a7, B:97:0x02ab, B:98:0x02b0, B:99:0x02e3, B:101:0x02e7, B:102:0x02ea, B:106:0x0351, B:108:0x02fa, B:110:0x0305, B:112:0x0309, B:113:0x030c, B:114:0x033f, B:116:0x0343, B:117:0x0346), top: B:2:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0138 A[Catch: all -> 0x0378, TryCatch #0 {all -> 0x0378, blocks: (B:3:0x000f, B:6:0x002e, B:8:0x0036, B:9:0x0039, B:10:0x0065, B:12:0x007f, B:14:0x0087, B:15:0x008a, B:17:0x00bf, B:22:0x00cb, B:24:0x00d4, B:27:0x00e3, B:29:0x00ef, B:31:0x00f5, B:33:0x0101, B:35:0x011f, B:37:0x0157, B:39:0x015d, B:41:0x0186, B:43:0x018e, B:45:0x0194, B:47:0x01a2, B:49:0x01a6, B:50:0x01b2, B:54:0x01c1, B:56:0x01c5, B:58:0x01cc, B:60:0x01d2, B:62:0x01dd, B:64:0x01e1, B:68:0x01f4, B:70:0x0201, B:71:0x0206, B:73:0x020a, B:76:0x0138, B:78:0x0211, B:80:0x022a, B:82:0x0254, B:83:0x025d, B:85:0x0261, B:88:0x0279, B:90:0x0289, B:91:0x028c, B:93:0x029c, B:95:0x02a7, B:97:0x02ab, B:98:0x02b0, B:99:0x02e3, B:101:0x02e7, B:102:0x02ea, B:106:0x0351, B:108:0x02fa, B:110:0x0305, B:112:0x0309, B:113:0x030c, B:114:0x033f, B:116:0x0343, B:117:0x0346), top: B:2:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void processResult(AIUIEvent event) {
        IVoiceInteractionListener iVoiceInteractionListener;
        boolean z;
        IVoiceInteractionListener iVoiceInteractionListener2;
        JSONArray jSONArray;
        try {
            String str = event.info;
            Intrinsics.checkExpressionValueIsNotNull(str, "event.info");
            if (StringsKt.indexOf$default((CharSequence) str, "\"sub\":\"iat", 150, false, 4, (Object) null) != -1) {
                byte[] byteArray = event.data.getByteArray("0");
                if (byteArray == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(byteArray, "event.data.getByteArray(\"0\")!!");
                String str2 = new String(byteArray, Charsets.UTF_8);
                Pdlog.m3275i("dev_voice", "processResult " + str2);
                JSONObject cntJson = JSON.parseObject(str2);
                Intrinsics.checkExpressionValueIsNotNull(cntJson, "cntJson");
                processIATResult(cntJson);
            }
            String str3 = event.info;
            Intrinsics.checkExpressionValueIsNotNull(str3, "event.info");
            if (StringsKt.indexOf$default((CharSequence) str3, "\"sub\":\"nlp", 105, false, 4, (Object) null) != -1) {
                byte[] byteArray2 = event.data.getByteArray("0");
                if (byteArray2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(byteArray2, "event.data.getByteArray(\"0\")!!");
                String str4 = new String(byteArray2, Charsets.UTF_8);
                Pdlog.m3275i("dev_voice", "processResult " + str4);
                JSONObject jSONObject = JSON.parseObject(str4).getJSONObject("intent");
                String string = jSONObject.getString("text");
                String str5 = string;
                if (str5 != null && str5.length() != 0) {
                    z = false;
                    if (z) {
                        if (!Intrinsics.areEqual(string, this.currentReq)) {
                            Pdlog.m3273d(TAG, "cancel last voice");
                            PuduAudioReport.INSTANCE.release();
                            return;
                        }
                        if (!VoiceCommentConfig.INSTANCE.getUseWordList().contains(string) && (string.length() < 2 || VoiceCommentConfig.INSTANCE.getUseLessWordList().contains(string))) {
                            Pdlog.m3275i(TAG, "the length of " + string + " is lower than 2");
                            return;
                        }
                        Pdlog.m3273d(TAG, "semanticResult: " + string);
                    } else {
                        Pdlog.m3273d(TAG, "semanticResult " + string + " is null");
                    }
                    if (jSONObject == null && jSONObject.size() != 0) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("answer");
                        Pdlog.m3273d(TAG, "result: " + jSONObject);
                        if (jSONObject.containsKey("semantic") && (jSONArray = jSONObject.getJSONArray("semantic")) != null && jSONArray.size() > 0 && Intrinsics.areEqual(jSONObject.getString("service"), "OS7949860113.RobotControl") && !this.isBella) {
                            String jSONArray2 = jSONArray.toString();
                            Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "array.toString()");
                            updateCmd(jSONArray2);
                        }
                        if ((this.asw.length() > 0) && this.isBella) {
                            httpTextForBella(this.asw);
                        }
                        if (jSONObject2 != null && jSONObject2.size() != 0) {
                            onResultChanged(jSONObject2.getString("text"));
                            return;
                        } else {
                            if (this.isBella) {
                                LogProxy.INSTANCE.m3306e(TAG, "processResult answerResult is null");
                                updateState(VoiceInteractionState.PlayCompleted);
                                updateState(VoiceInteractionState.Idle);
                                return;
                            }
                            return;
                        }
                    }
                    Pdlog.m3274e(TAG, "answer is null");
                    iVoiceInteractionListener2 = this.onVoiceInteractionListener;
                    if (iVoiceInteractionListener2 != null) {
                        iVoiceInteractionListener2.onStatusChanged(VoiceInteractionState.PlayCompleted);
                    }
                    if (this.isBella) {
                        return;
                    }
                    updateState(VoiceInteractionState.Idle);
                    return;
                }
                z = true;
                if (z) {
                }
                if (jSONObject == null) {
                }
                Pdlog.m3274e(TAG, "answer is null");
                iVoiceInteractionListener2 = this.onVoiceInteractionListener;
                if (iVoiceInteractionListener2 != null) {
                }
                if (this.isBella) {
                }
            } else {
                String str6 = event.info;
                Intrinsics.checkExpressionValueIsNotNull(str6, "event.info");
                if (StringsKt.indexOf$default((CharSequence) str6, "\"sub\":\"tts", 105, false, 4, (Object) null) != -1) {
                    JSONObject jSONObject3 = JSON.parseObject(event.info).getJSONArray("data").getJSONObject(0).getJSONArray(AIUIConstant.KEY_CONTENT).getJSONObject(0);
                    String ttsText = jSONObject3.getString("text_seg");
                    Intrinsics.checkExpressionValueIsNotNull(ttsText, "ttsText");
                    if (StringsKt.startsWith$default(ttsText, "\ufeff", false, 2, (Object) null)) {
                        ttsText = ttsText.substring(1);
                        Intrinsics.checkExpressionValueIsNotNull(ttsText, "(this as java.lang.String).substring(startIndex)");
                    }
                    String str7 = generatText;
                    if (str7 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(ttsText, "ttsText");
                        if (StringsKt.indexOf$default((CharSequence) str7, ttsText, 0, false, 6, (Object) null) == -1) {
                            return;
                        }
                    }
                    int intValue = jSONObject3.getIntValue(InternalConstant.KEY_DTS);
                    byte[] byteArray3 = event.data.getByteArray("0");
                    FileOutputStream fileOutputStream = this.xttsFile;
                    if (fileOutputStream != null) {
                        fileOutputStream.write(byteArray3);
                    }
                    if (Intrinsics.areEqual(jSONObject3.getString("cancel"), "1")) {
                        this.mHandler.removeMessages(103);
                        String str8 = this.xttsPath;
                        if (str8 != null) {
                            OnTtsListener onTtsListener2 = onTtsListener;
                            if (onTtsListener2 != null) {
                                onTtsListener2.onError(-1, "tts cancel");
                            }
                            Pdlog.m3273d(TAG, "onTtsListener cancel== " + onTtsListener + ' ' + str8 + ' ' + this.xttsPath);
                            onTtsListener = (OnTtsListener) null;
                            this.xttsPath = (String) null;
                        }
                        FileOutputStream fileOutputStream2 = this.xttsFile;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        this.xttsFile = (FileOutputStream) null;
                        setParams("sdk");
                    }
                    if (intValue == 2 || intValue == 3) {
                        this.mHandler.removeMessages(103);
                        String str9 = this.xttsPath;
                        if (str9 != null) {
                            OnTtsListener onTtsListener3 = onTtsListener;
                            if (onTtsListener3 != null) {
                                onTtsListener3.onComplete(str9);
                            }
                            Pdlog.m3273d(TAG, "onTtsListener == " + onTtsListener + ' ' + str9 + ' ' + this.xttsPath);
                            onTtsListener = (OnTtsListener) null;
                            this.xttsPath = (String) null;
                        }
                        FileOutputStream fileOutputStream3 = this.xttsFile;
                        if (fileOutputStream3 != null) {
                            fileOutputStream3.close();
                        }
                        this.xttsFile = (FileOutputStream) null;
                        setParams("sdk");
                    }
                    Pdlog.m3273d(TAG_AIUI, "sub == " + event.info + ' ' + jSONObject3.getString("text_seg"));
                }
            }
        } catch (Throwable th) {
            this.mHandler.removeMessages(103);
            OnTtsListener onTtsListener4 = onTtsListener;
            if (onTtsListener4 != null) {
                onTtsListener4.onError(-1, String.valueOf(th.getMessage()));
            }
            onTtsListener = (OnTtsListener) null;
            this.xttsPath = (String) null;
            Pdlog.m3274e(TAG, "VoiceInteractionState.Unknown --" + Log.getStackTraceString(th));
            if (!this.isBella && (iVoiceInteractionListener = this.onVoiceInteractionListener) != null) {
                iVoiceInteractionListener.onStatusChanged(VoiceInteractionState.Unknown);
            }
            if (this.isBella) {
                updateState(VoiceInteractionState.Unknown);
                updateState(VoiceInteractionState.Idle);
            }
        }
    }

    private final byte[] getTtsAudioFrame(AIUIEvent event) {
        OnTtsListener onTtsListener2;
        try {
            JSONObject jSONObject = JSON.parseObject(event.info).getJSONArray("data").getJSONObject(0);
            JSONObject jSONObject2 = jSONObject.getJSONObject("params");
            JSONObject jSONObject3 = jSONObject.getJSONArray(AIUIConstant.KEY_CONTENT).getJSONObject(0);
            if (!Intrinsics.areEqual("tts", jSONObject2.getString("sub")) || !jSONObject3.containsKey(InternalConstant.KEY_CONTENT_ID)) {
                return null;
            }
            jSONObject3.getString(InternalConstant.KEY_CONTENT_ID);
            byte[] byteArray = event.data.getByteArray("0");
            jSONObject3.getIntValue("frame_id");
            int intValue = jSONObject3.getIntValue("text_percent");
            int intValue2 = jSONObject3.getIntValue("text_start");
            int intValue3 = jSONObject3.getIntValue("text_end");
            if (Intrinsics.areEqual("1", jSONObject3.getString("cancel")) && (onTtsListener2 = onTtsListener) != null) {
                onTtsListener2.onError(-1, "tts cancel");
            }
            jSONObject3.remove("text_percent");
            jSONObject3.remove("text_start");
            jSONObject3.remove("text_end");
            event.data.putInt("percent", intValue);
            event.data.putInt("begpos", intValue2 / 2);
            event.data.putInt("endpos", intValue3 / 2);
            Pdlog.m3273d(TAG, "getTtsAudioFrame dts audio:" + String.valueOf(byteArray));
            return byteArray;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private final void updateCmd(String cmd) {
        LogUtil.INSTANCE.m3309d(TAG, "onCmdChanged " + cmd, new Object[0]);
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onCmdResponse(getIflyCmdAdatper().transformationCmd(cmd));
        }
        LogProxy.INSTANCE.m3307i(TAG, "UPDATE CMD");
        updateState(VoiceInteractionState.Idle);
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

    /* JADX WARN: Removed duplicated region for block: B:12:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void processError(AIUIEvent aiuiEvent) {
        OnTtsListener onTtsListener2;
        int i = aiuiEvent.arg1;
        if (i != 10120) {
            if (i == 11217) {
                Pdlog.m3274e(TAG, "init fail reinit aiui code  " + i);
                AIUISetting.setSystemInfo("sn", VoiceInteractionKit.INSTANCE.getProduct_type() + getWIFIMac());
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                this.mAgent = AIUIAgent.createAgent(context, getAIUIParams(), this);
                UserInfo userInfo = VoiceCommentConfig.INSTANCE.getUserInfo();
                if (userInfo != null) {
                    setUserInfo(userInfo);
                }
                setScene();
            } else if (i != 20001) {
                if (i == 20006) {
                    Pdlog.m3274e(TAG, "Start Record fail " + i);
                    updateState(VoiceInteractionState.Unknown);
                } else {
                    updateState(VoiceInteractionState.Unknown);
                    Pdlog.m3274e(TAG, "Error code: " + i);
                }
            }
            this.mHandler.removeMessages(103);
            onTtsListener2 = onTtsListener;
            if (onTtsListener2 != null) {
                onTtsListener2.onError(-1, "errorCode is " + i);
            }
            onTtsListener = (OnTtsListener) null;
            this.xttsPath = (String) null;
            this.mHandler.removeMessages(101);
            if (this.isSwift) {
                updateState(VoiceInteractionState.Idle);
                return;
            }
            return;
        }
        updateState(VoiceInteractionState.ErrorNetWork);
        Pdlog.m3274e(TAG, "Network Error " + i);
        this.mHandler.removeMessages(103);
        onTtsListener2 = onTtsListener;
        if (onTtsListener2 != null) {
        }
        onTtsListener = (OnTtsListener) null;
        this.xttsPath = (String) null;
        this.mHandler.removeMessages(101);
        if (this.isSwift) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(VoiceInteractionState state) {
        this.mCurrentState = state;
        Pdlog.m3273d(TAG, "UPDATE STATE " + state);
        if (state == VoiceInteractionState.BosTimeout || state == VoiceInteractionState.Eos || state == VoiceInteractionState.Idle || state == VoiceInteractionState.ErrorNetWork) {
            if (!VoiceCommentConfig.INSTANCE.isContinuous()) {
                this.isWakeUp = false;
                handleMicArrayWake(true);
            }
            Pdlog.m3275i(TAG, "STOP RECOGNIZE");
            if (state == VoiceInteractionState.BosTimeout) {
                PuduAudioReport.INSTANCE.release();
            }
        }
        IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
        if (iVoiceInteractionListener != null) {
            iVoiceInteractionListener.onStatusChanged(state);
        }
    }

    private final void stopRecordAudio() {
        Pdlog.m3273d(TAG, "AIUI 停止写入");
        if (VoiceCommentConfig.INSTANCE.getRecodeType() != 2) {
            AIUIMessage aIUIMessage = this.aiuiMsg;
            aIUIMessage.msgType = 23;
            aIUIMessage.arg1 = 0;
            aIUIMessage.arg2 = 0;
            aIUIMessage.params = "sample_rate=16000,data_type=audio";
            aIUIMessage.data = (byte[]) null;
            sendMessage(aIUIMessage);
        }
        AIUIMessage aIUIMessage2 = this.aiuiMsg;
        aIUIMessage2.msgType = 3;
        aIUIMessage2.arg1 = 0;
        aIUIMessage2.arg2 = 0;
        aIUIMessage2.params = audioParam;
        aIUIMessage2.data = (byte[]) null;
        sendMessage(aIUIMessage2);
        if (this.isSwift) {
            return;
        }
        resetWakeup();
    }

    private final void offlineOperation(String keyWord) {
        IVoiceInteractionListener iVoiceInteractionListener;
        IVoiceInteractionListener iVoiceInteractionListener2;
        IVoiceInteractionListener iVoiceInteractionListener3;
        IVoiceInteractionListener iVoiceInteractionListener4;
        IVoiceInteractionListener iVoiceInteractionListener5;
        IVoiceInteractionListener iVoiceInteractionListener6;
        IVoiceInteractionListener iVoiceInteractionListener7;
        IVoiceInteractionListener iVoiceInteractionListener8;
        Pdlog.m3275i(TAG, "offlineOperation");
        switch (keyWord.hashCode()) {
            case -2076834589:
                if (!keyWord.equals("fan3-hui2") || (iVoiceInteractionListener = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener.offlineCmd("返回");
                return;
            case -2040811203:
                if (!keyWord.equals("jia1-da4-yin1-liang4") || (iVoiceInteractionListener2 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener2.offlineCmd("加大音量");
                return;
            case -1653314081:
                if (!keyWord.equals("ji4-xu4") || (iVoiceInteractionListener3 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener3.offlineCmd("继续");
                return;
            case -1596541349:
                if (!keyWord.equals("zeng1-da4-yin1-liang4") || (iVoiceInteractionListener4 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener4.offlineCmd("增大音量");
                return;
            case -1032981342:
                if (!keyWord.equals("wo3-yao4-qu4") || (iVoiceInteractionListener5 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener5.offlineCmd("我要去");
                return;
            case -580138698:
                if (!keyWord.equals("li2-kai1") || (iVoiceInteractionListener6 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener6.offlineCmd("离开");
                return;
            case 469817266:
                if (!keyWord.equals("jian3-xiao3-yin1-liang4") || (iVoiceInteractionListener7 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener7.offlineCmd("减小音量");
                return;
            case 975951352:
                if (!keyWord.equals("zou3-kai1") || (iVoiceInteractionListener8 = this.onVoiceInteractionListener) == null) {
                    return;
                }
                iVoiceInteractionListener8.offlineCmd("走开");
                return;
            default:
                return;
        }
    }

    private final void setUserInfo(UserInfo userInfo) {
        Pdlog.m3273d(TAG, "setUserInfo: userInfo=" + userInfo);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(InternalConstant.KEY_PERS_PARAM, (Object) "{\"appid\":\"\",\"uid\":\"\"}");
        jSONObject.put("audioparams", (Object) jSONObject2);
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 10;
        aIUIMessage.arg1 = 0;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = jSONObject.toString();
        AIUIMessage aIUIMessage2 = this.aiuiMsg;
        aIUIMessage2.data = (byte[]) null;
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.sendMessage(aIUIMessage2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDesIntentObject(ArrayList<String> des) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(InternalConstant.KEY_ID_NAME, (Object) AIUIConstant.KEY_UID);
        jSONObject2.put("res_name", (Object) "OS7949860113.user_des");
        jSONObject.put("param", (Object) jSONObject2);
        StringBuilder sb = new StringBuilder();
        for (String str : des) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", str);
            sb.append(this.gson.toJson(hashMap));
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        Pdlog.m3273d(TAG, "setDesIntentObject: des=" + sb.toString());
        Base64.Encoder encoder = Base64.getEncoder();
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "s.toString()");
        Charset forName = Charset.forName("utf-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
        if (sb2 != null) {
            byte[] bytes = sb2.getBytes(forName);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            jSONObject.put("data", (Object) encoder.encodeToString(bytes));
            String jSONObject3 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject3, "syncSchemaJson.toString()");
            Charset forName2 = Charset.forName("utf-8");
            Intrinsics.checkExpressionValueIsNotNull(forName2, "Charset.forName(charsetName)");
            if (jSONObject3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes2 = jSONObject3.getBytes(forName2);
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
            AIUIMessage aIUIMessage = this.aiuiMsg;
            aIUIMessage.msgType = 13;
            aIUIMessage.arg1 = 3;
            aIUIMessage.arg2 = 0;
            aIUIMessage.params = "";
            aIUIMessage.data = bytes2;
            sendMessage(aIUIMessage);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public final void checkState() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sid", (Object) this.mSyncSid);
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 24;
        aIUIMessage.arg1 = 3;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = jSONObject.toString();
        AIUIMessage aIUIMessage2 = this.aiuiMsg;
        aIUIMessage2.data = (byte[]) null;
        sendMessage(aIUIMessage2);
    }

    public final boolean getServerConnectState(Function1<? super Integer, Unit> initCallback) {
        ttsCallBack = initCallback;
        return this.isServerConnect;
    }

    public final void initOnlyTTS(Context context, Function1<? super Integer, Unit> initCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        Pdlog.m3273d(TAG, "initOnlyTTS");
        ttsCallBack = initCallback;
        copyWakeupRes();
        initAIUIAgent(context);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void reConnectAIUI() {
        Pdlog.m3273d(TAG, "reConnectAIUI");
        AIUIMessage aIUIMessage = this.aiuiMsg;
        aIUIMessage.msgType = 4;
        aIUIMessage.arg1 = 0;
        aIUIMessage.arg2 = 0;
        aIUIMessage.params = audioParam;
        aIUIMessage.data = (byte[]) null;
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.sendMessage(aIUIMessage);
        }
    }

    private final void receiveNetWork(Context context) {
        Pdlog.m3273d(TAG, "receiveNetWork");
        NetworkStateReceive networkStateReceive = this.mNetworkStateReceive;
        if (networkStateReceive != null) {
            context.unregisterReceiver(networkStateReceive);
        }
        this.mNetworkStateReceive = new NetworkStateReceive();
        NetworkStateReceive networkStateReceive2 = this.mNetworkStateReceive;
        if (networkStateReceive2 != null) {
            networkStateReceive2.setNetworkStateCallback(this.networkStateCallback);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this.mNetworkStateReceive, intentFilter);
    }

    @Override // com.pudutech.voiceinteraction.component.interf.IVoiceInteraction
    public void cancelCurrentRound() {
        stopRecordAudio();
        Job job = this.mScopCloudSkillJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.mScopCloudPlatformJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        Job job3 = (Job) null;
        this.mScopCloudSkillJob = job3;
        this.mScopCloudPlatformJob = job3;
        this.mScope = (CoroutineScope) null;
        cancelTTS();
        updateState(VoiceInteractionState.Idle);
    }

    private final void handleMicArrayWake(boolean enable) {
        Pdlog.m3273d(TAG, "handleMicArrayWake " + micCanWakeup + ' ' + enable);
        if (!micCanWakeup && !enable) {
            micCanWakeup = true;
            MicArray micArray = this.micArray;
            Pdlog.m3273d(TAG, "handleMicArrayWake " + (micArray != null ? Integer.valueOf(micArray.wakeUpEnable(enable)) : null));
        }
        if (micCanWakeup && enable) {
            micCanWakeup = false;
            MicArray micArray2 = this.micArray;
            Pdlog.m3273d(TAG, "handleMicArrayWake " + (micArray2 != null ? Integer.valueOf(micArray2.wakeUpEnable(enable)) : null));
        }
    }
}

package com.pudutech.voiceinteraction.component;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import com.pudutech.voiceinteraction.component.utils.PuduAudioReport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b*\u0001\f\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u0010J\u0006\u0010\u001e\u001a\u00020\u0010J\u0006\u0010\u001f\u001a\u00020 J_\u0010!\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0012\b\u0002\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f2\u0012\b\u0002\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u00102\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u000e\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\bJ\u000e\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020%J\u0006\u0010/\u001a\u00020\u0010J\u0006\u00100\u001a\u00020\u0010J\u000e\u00101\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u00102\u001a\u00020\u0010J.\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020%2\u0006\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020%2\u0006\u00108\u001a\u000209J&\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020<2\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020%0>j\b\u0012\u0004\u0012\u00020%`?J\u000e\u0010@\u001a\u00020\u00102\u0006\u0010A\u001a\u000209J\u000e\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u000209J\u0016\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020%J\u000e\u0010G\u001a\u00020\u00102\u0006\u0010H\u001a\u00020%J\u0016\u0010I\u001a\u00020\u00102\u0006\u0010J\u001a\u00020%2\u0006\u0010\"\u001a\u00020%J\u0016\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020%J\u000e\u0010N\u001a\u00020\u00102\u0006\u0010O\u001a\u000209J\u0006\u0010P\u001a\u00020\u0010J\u000e\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020%J\u0006\u0010S\u001a\u00020\u0010J6\u0010T\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0012\b\u0002\u0010U\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f2\u0012\b\u0002\u0010V\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fJ\u0016\u0010W\u001a\u00020\u00102\u0006\u0010X\u001a\u0002092\u0006\u0010C\u001a\u000209R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0018\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper;", "", "()V", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isResponse", "", "job", "Lkotlinx/coroutines/Job;", "mainListener", "com/pudutech/voiceinteraction/component/VoiceInteractionHelper$mainListener$1", "Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper$mainListener$1;", "onInitFailedListener", "Lkotlin/Function0;", "", "onInitSucceedListener", "singelThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "startRecord", "stopRecord", "voiceListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "wakeUpJob", "addVoiceListener", "listener", "cancelCurrentRound", "cancelTTS", "checkState", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "init", "language", "Lcom/pudutech/voiceinteraction/component/config/Language;", "pid", "", SpeechConstant.ISV_VID, "ifOnlyWake", "(Landroid/content/Context;Lcom/pudutech/voiceinteraction/component/config/Language;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "notifyInMain", "run", "onlyWakeup", "b", "postAction", "actionString", "reConnectAIUI", "release", "removeVoiceListener", "resetCAE", "setData", "map_name", "configId", "workMode", "shopId", "state", "", "setIntentObj", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setMode", "mode", "setRealBeam", "beam", "setRobotInfo", "productType", "editionStr", "setSessionId", "sessionId", "setSpeaker", "speaker", "setTinyMap", TransferTable.COLUMN_KEY, ES6Iterator.VALUE_PROPERTY, "setVolume", "i", "startRecording", "startTts", "text", "stopRecording", "switchLanguage", "onSwitchLanguageSucceedListener", "onSwitchLanguageFailedListener", "wakeup", "angle", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractionHelper {
    public static final String TAG = "VoiceInteractionHelper";
    private Context context;
    private volatile boolean isResponse;
    private Job job;
    private Function0<Unit> onInitFailedListener;
    private Function0<Unit> onInitSucceedListener;
    private Job startRecord;
    private Job stopRecord;
    private Job wakeUpJob;
    private CopyOnWriteArrayList<IVoiceInteractionListener> voiceListeners = new CopyOnWriteArrayList<>();
    private final ExecutorCoroutineDispatcher singelThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("Record-VoiceInteractionKit");
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(this.singelThreadContext);
    private VoiceInteractionHelper$mainListener$1 mainListener = new VoiceInteractionHelper$mainListener$1(this);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[VoiceInteractionState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[VoiceInteractionState.BosTimeout.ordinal()] = 1;
            $EnumSwitchMapping$0[VoiceInteractionState.Unconnected.ordinal()] = 2;
            $EnumSwitchMapping$0[VoiceInteractionState.Eos.ordinal()] = 3;
            $EnumSwitchMapping$0[VoiceInteractionState.PlayCompleted.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[VoiceInteractionState.values().length];
            $EnumSwitchMapping$1[VoiceInteractionState.Recording.ordinal()] = 1;
            $EnumSwitchMapping$1[VoiceInteractionState.Idle.ordinal()] = 2;
            $EnumSwitchMapping$1[VoiceInteractionState.Speaking.ordinal()] = 3;
            $EnumSwitchMapping$1[VoiceInteractionState.BosTimeout.ordinal()] = 4;
            $EnumSwitchMapping$1[VoiceInteractionState.Eos.ordinal()] = 5;
            $EnumSwitchMapping$1[VoiceInteractionState.Unconnected.ordinal()] = 6;
            $EnumSwitchMapping$1[VoiceInteractionState.PlayCompleted.ordinal()] = 7;
            $EnumSwitchMapping$1[VoiceInteractionState.Error10120.ordinal()] = 8;
            $EnumSwitchMapping$1[VoiceInteractionState.Error20001.ordinal()] = 9;
            $EnumSwitchMapping$1[VoiceInteractionState.ErrorAudioError.ordinal()] = 10;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyInMain(Function0<Unit> run) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoiceInteractionHelper$notifyInMain$1(run, null), 2, null);
    }

    public final void init(Context context, Language language, Function0<Unit> onInitSucceedListener, Function0<Unit> onInitFailedListener, String pid, String vid, Boolean ifOnlyWake) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Pdlog.m3273d(TAG, "init() context = " + context + ", language = " + language);
        this.context = context;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$init$1(this, context, language, pid, vid, ifOnlyWake, onInitSucceedListener, onInitFailedListener, null), 3, null);
        PuduAudioReport.INSTANCE.init();
    }

    public final void addVoiceListener(IVoiceInteractionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "listener:" + listener + " voiceListeners =" + this.voiceListeners.size());
        if (!this.voiceListeners.contains(listener)) {
            this.voiceListeners.add(listener);
        }
        Pdlog.m3273d(TAG, "end voiceListeners =" + this.voiceListeners.size());
    }

    public final void removeVoiceListener(IVoiceInteractionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, " removeVoiceListener =" + this.voiceListeners.size());
        this.voiceListeners.remove(listener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void switchLanguage$default(VoiceInteractionHelper voiceInteractionHelper, Language language, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = (Function0) null;
        }
        if ((i & 4) != 0) {
            function02 = (Function0) null;
        }
        voiceInteractionHelper.switchLanguage(language, function0, function02);
    }

    public final void switchLanguage(Language language, Function0<Unit> onSwitchLanguageSucceedListener, Function0<Unit> onSwitchLanguageFailedListener) {
        Intrinsics.checkParameterIsNotNull(language, "language");
        LogProxy.INSTANCE.m3305d(TAG, "switchLanguage() language = " + language);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$switchLanguage$1(language, onSwitchLanguageSucceedListener, onSwitchLanguageFailedListener, null), 3, null);
    }

    public final void startTts(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$startTts$1(text, null), 3, null);
    }

    public final void setSpeaker(String speaker, String language) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(language, "language");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setSpeaker$1(speaker, language, null), 3, null);
    }

    /* compiled from: VoiceInteractionHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper$CHandler;", "Landroid/os/Handler;", "helper", "Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper;", "(Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper;)V", "mHelper", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private static final class CHandler extends Handler {
        private final WeakReference<VoiceInteractionHelper> mHelper;

        public CHandler(VoiceInteractionHelper helper) {
            Intrinsics.checkParameterIsNotNull(helper, "helper");
            this.mHelper = new WeakReference<>(helper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            VoiceInteractionHelper voiceInteractionHelper = this.mHelper.get();
            if (voiceInteractionHelper != null) {
                Intrinsics.checkExpressionValueIsNotNull(voiceInteractionHelper, "mHelper.get() ?: return");
                switch (msg.what) {
                    case 101:
                        if (VoiceInteractionHelper.access$getInterruptDismissCountdown$p(voiceInteractionHelper)) {
                            VoiceInteractionHelper.access$setEosCountdown$p(voiceInteractionHelper, 0);
                            VoiceInteractionHelper.access$setInterruptEosCountdown$p(voiceInteractionHelper, false);
                            postDelayed(VoiceInteractionHelper.access$getEosCountdownRunnable$p(voiceInteractionHelper), 0L);
                            return;
                        }
                        return;
                    case 102:
                        VoiceInteractionHelper.access$setEosCountdown$p(voiceInteractionHelper, 0);
                        VoiceInteractionHelper.access$setInterruptEosCountdown$p(voiceInteractionHelper, true);
                        removeCallbacks(VoiceInteractionHelper.access$getEosCountdownRunnable$p(voiceInteractionHelper));
                        return;
                    case 103:
                        VoiceInteractionHelper.access$setDismissCountdown$p(voiceInteractionHelper, 0);
                        VoiceInteractionHelper.access$setInterruptDismissCountdown$p(voiceInteractionHelper, false);
                        postDelayed(VoiceInteractionHelper.access$getDismissCountdownRunnable$p(voiceInteractionHelper), 0L);
                        return;
                    case 104:
                        VoiceInteractionHelper.access$setDismissCountdown$p(voiceInteractionHelper, 0);
                        VoiceInteractionHelper.access$setInterruptDismissCountdown$p(voiceInteractionHelper, true);
                        VoiceInteractionHelper.access$setNetworkUnavailableCountingDown$p(voiceInteractionHelper, false);
                        removeCallbacks(VoiceInteractionHelper.access$getDismissCountdownRunnable$p(voiceInteractionHelper));
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public final void startRecording() {
        Job launch$default;
        Job job = this.startRecord;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new VoiceInteractionHelper$startRecording$1(null), 3, null);
        this.startRecord = launch$default;
    }

    public final NLPActionType getNLPActionType() {
        LogProxy.INSTANCE.m3305d(TAG, "startRecording()");
        return VoiceInteractionKit.INSTANCE.getINSTANCE().getNLPActionType();
    }

    public final void stopRecording() {
        Job launch$default;
        Job job = this.stopRecord;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new VoiceInteractionHelper$stopRecording$1(null), 3, null);
        this.stopRecord = launch$default;
    }

    public final void wakeup(int angle, int beam) {
        Job launch$default;
        Pdlog.m3273d(TAG, "wakeup() angle = " + angle + ", beam = " + beam + ' ' + this.wakeUpJob);
        Job job = this.wakeUpJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new VoiceInteractionHelper$wakeup$1(angle, beam, null), 3, null);
        this.wakeUpJob = launch$default;
    }

    public final void setSessionId(String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        LogProxy.INSTANCE.m3305d(TAG, "setSessionId  sessionId = " + sessionId);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setSessionId$1(sessionId, null), 3, null);
    }

    public final void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$postAction$1(actionString, null), 3, null);
    }

    public final void setRealBeam(int beam) {
        LogProxy.INSTANCE.m3305d(TAG, "setRealBeam() beam = " + beam);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setRealBeam$1(beam, null), 3, null);
    }

    public final void cancelTTS() {
        LogProxy.INSTANCE.m3305d(TAG, "cancelTTS()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$cancelTTS$1(null), 3, null);
    }

    public final void setData(String map_name, String configId, String workMode, String shopId, int state) {
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(configId, "configId");
        Intrinsics.checkParameterIsNotNull(workMode, "workMode");
        Intrinsics.checkParameterIsNotNull(shopId, "shopId");
        LogProxy.INSTANCE.m3305d(TAG, "cancelTTS()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setData$1(map_name, configId, workMode, shopId, state, null), 3, null);
    }

    public final void setRobotInfo(String productType, String editionStr) {
        Intrinsics.checkParameterIsNotNull(productType, "productType");
        Intrinsics.checkParameterIsNotNull(editionStr, "editionStr");
        LogProxy.INSTANCE.m3305d(TAG, "setRobotInfo() edition:" + editionStr);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setRobotInfo$1(productType, editionStr, null), 3, null);
    }

    public final void resetCAE() {
        LogProxy.INSTANCE.m3305d(TAG, "resetCAE()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$resetCAE$1(null), 3, null);
    }

    public final void release() {
        LogProxy.INSTANCE.m3305d(TAG, "release()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$release$1(null), 3, null);
    }

    public final void setIntentObj(IntentObjectType type, ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$setIntentObj$1(type, data, null), 3, null);
    }

    public final void onlyWakeup(boolean b) {
        LogProxy.INSTANCE.m3305d(TAG, "onlyWakeup " + b);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$onlyWakeup$1(b, null), 3, null);
    }

    public final void checkState() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$checkState$1(null), 3, null);
    }

    public final void cancelCurrentRound() {
        VoiceInteractionKit.INSTANCE.getINSTANCE().cancelCurrentRound();
    }

    public final void setVolume(int i) {
        LogProxy.INSTANCE.m3305d(TAG, "setVolume " + i);
        VoiceCommentConfig.INSTANCE.setTtsVolume(i);
    }

    public final void setMode(int mode) {
        VoiceInteractionKit.INSTANCE.getINSTANCE().setMode(mode);
    }

    public final void setTinyMap(int key, String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        VoiceInteractionKit.INSTANCE.getINSTANCE().setTinyMap(key, value);
    }

    public final void reConnectAIUI() {
        LogProxy.INSTANCE.m3305d(TAG, "reConnectAIUI()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoiceInteractionHelper$reConnectAIUI$1(null), 3, null);
    }
}

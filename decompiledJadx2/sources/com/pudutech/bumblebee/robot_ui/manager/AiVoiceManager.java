package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.RobotInfo;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.mirsdk.robotsdk.AccLoraConnection;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import com.pudutech.resources.language.SupportedLocale;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.VoiceInteractionHelper;
import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: AiVoiceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b*\u0001$\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010)\u001a\u00020\u000e2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rJ\u001c\u0010+\u001a\u00020\u000e2\u0014\u0010*\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u000e0\u0010J*\u0010,\u001a\u00020\u000e2\"\u0010*\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u001eJ\"\u0010-\u001a\u00020\u000e2\u001a\u0010*\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\rJ\u001a\u0010.\u001a\u00020\u000e2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000e0\u0010J\u001a\u0010/\u001a\u00020\u000e2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0010J\u001c\u00100\u001a\u00020\u000e2\u0014\u0010*\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010(\u0012\u0004\u0012\u00020\u000e0\u0010J\u001a\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u00172\b\u00103\u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u00104\u001a\u00020\u000eJ\u0006\u00105\u001a\u00020\u000eJ\u000e\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u000208J\u0010\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u00020=H\u0002J\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040?J\u000e\u0010@\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020;J\u0006\u0010A\u001a\u00020\u000eJ \u0010B\u001a\u00020\u000e2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rJ\u001c\u0010C\u001a\u00020\u000e2\u0014\u0010*\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u000e0\u0010J*\u0010D\u001a\u00020\u000e2\"\u0010*\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u001eJ\"\u0010E\u001a\u00020\u000e2\u001a\u0010*\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\rJ\u001a\u0010F\u001a\u00020\u000e2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000e0\u0010J\u001a\u0010G\u001a\u00020\u000e2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0010J\u001c\u0010H\u001a\u00020\u000e2\u0014\u0010*\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010(\u0012\u0004\u0012\u00020\u000e0\u0010J\u000e\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u0004J\u000e\u0010M\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020OJ\u0010\u0010P\u001a\u00020\u000e2\b\b\u0002\u0010Q\u001a\u00020\u0013J\u0016\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u00172\u0006\u0010T\u001a\u00020\u0017J\u0006\u0010U\u001a\u00020\u000eJ\u000e\u0010V\u001a\u00020=2\u0006\u00107\u001a\u000208R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u000e0\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u001d\u001a$\u0012 \u0012\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u001e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u001f\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000e0\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R \u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010'\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010(\u0012\u0004\u0012\u00020\u000e0\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "WAKEUP_VOICE", "getWAKEUP_VOICE", "()Ljava/lang/String;", "aiVoiceHelper", "Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper;", "businessResponseListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function2;", "", "cmdResponseListeners", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "isInitSuccess", "", "Ljava/lang/Boolean;", "isNeedRecording", "lastWakeAngle", "", "getLastWakeAngle", "()I", "setLastWakeAngle", "(I)V", "pid", "resultAnswerListeners", "Lkotlin/Function3;", "resultQuestionListeners", "statusChangeListeners", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", SpeechConstant.ISV_VID, "voiceInteractionListener", "com/pudutech/bumblebee/robot_ui/manager/AiVoiceManager$voiceInteractionListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceManager$voiceInteractionListener$1;", "volumeChangeListeners", "wakeupListeners", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "addBusinessResponseListener", "listener", "addCmdResponseListener", "addResultAnswerListener", "addResultQuestionListener", "addStatusListener", "addVolumeChangeListener", "addWakeupListener", "businessParse", "state", "json", "cancelCurrentRound", "cancelTTS", "changLanguage", "locale", "Ljava/util/Locale;", "getEchoDevice", "context", "Landroid/content/Context;", "getLanguage", "Lcom/pudutech/voiceinteraction/component/config/Language;", "getSpeaker", "Lkotlin/Pair;", "init", "release", "removeBusinessResponseListener", "removeCmdResponseListener", "removeResultAnswerListener", "removeResultQuestionListener", "removeStatusListener", "removeVolumeChangeListener", "removeWakeupListener", "setMapName", "mapName", "setShopId", "shopId", "setWorkMode", "mode", "Lcom/pudutech/voiceinteraction/component/cmd/WorkMode;", "startAiRecording", "isIgnoreVoiceSwitch", "startWakeUp", "angle", "beam", "stopAiRecording", "transLanguage", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AiVoiceManager {
    public static final AiVoiceManager INSTANCE;
    private static final String TAG;
    private static final String WAKEUP_VOICE;
    private static VoiceInteractionHelper aiVoiceHelper;
    private static final CopyOnWriteArrayList<Function2<String, String, Unit>> businessResponseListeners;
    private static final CopyOnWriteArrayList<Function1<CmdBean, Unit>> cmdResponseListeners;
    private static Boolean isInitSuccess;
    private static boolean isNeedRecording;
    private static int lastWakeAngle;
    private static String pid;
    private static final CopyOnWriteArrayList<Function3<String, String, Integer, Unit>> resultAnswerListeners;
    private static final CopyOnWriteArrayList<Function2<String, Boolean, Unit>> resultQuestionListeners;
    private static final CopyOnWriteArrayList<Function1<VoiceInteractionState, Unit>> statusChangeListeners;
    private static String vid;
    private static final AiVoiceManager$voiceInteractionListener$1 voiceInteractionListener;
    private static final CopyOnWriteArrayList<Function1<Integer, Unit>> volumeChangeListeners;
    private static final CopyOnWriteArrayList<Function1<WakeupInfo, Unit>> wakeupListeners;

    /* JADX WARN: Type inference failed for: r0v13, types: [com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$voiceInteractionListener$1] */
    static {
        AiVoiceManager aiVoiceManager = new AiVoiceManager();
        INSTANCE = aiVoiceManager;
        TAG = aiVoiceManager.getClass().getSimpleName();
        WAKEUP_VOICE = WAKEUP_VOICE;
        wakeupListeners = new CopyOnWriteArrayList<>();
        resultQuestionListeners = new CopyOnWriteArrayList<>();
        resultAnswerListeners = new CopyOnWriteArrayList<>();
        statusChangeListeners = new CopyOnWriteArrayList<>();
        volumeChangeListeners = new CopyOnWriteArrayList<>();
        cmdResponseListeners = new CopyOnWriteArrayList<>();
        businessResponseListeners = new CopyOnWriteArrayList<>();
        pid = "5758";
        vid = AccLoraConnection.LoRaConfig.ID_VENDOR;
        voiceInteractionListener = new IVoiceInteractionListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$voiceInteractionListener$1
            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void offlineCmd(String data) {
                String str;
                Intrinsics.checkParameterIsNotNull(data, "data");
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "offlineCmd: " + data);
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onCmdResponse(CmdBean data) {
                String str;
                CopyOnWriteArrayList copyOnWriteArrayList;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onCmdResponse data = " + data + ' ');
                AiVoiceTriggerHelper.INSTANCE.handlerTriggerMsg(data);
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.cmdResponseListeners;
                if (copyOnWriteArrayList != null) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        ((Function1) it.next()).invoke(data);
                    }
                }
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onResultRequest(String data, boolean r6) {
                String str;
                CopyOnWriteArrayList copyOnWriteArrayList;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onResultRequest data:" + data + ", boolean:" + r6);
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.resultQuestionListeners;
                if (copyOnWriteArrayList != null) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        ((Function2) it.next()).invoke(data, Boolean.valueOf(r6));
                    }
                }
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onResultResponse(String data, String json, int state) {
                String str;
                int businessParse;
                CopyOnWriteArrayList copyOnWriteArrayList;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onResultResponse data:" + data + ", json:" + json + ", state:" + state);
                businessParse = AiVoiceManager.INSTANCE.businessParse(state, json);
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.resultAnswerListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((Function3) it.next()).invoke(data, json, Integer.valueOf(businessParse));
                }
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onStatusChanged(VoiceInteractionState state) {
                String str;
                CopyOnWriteArrayList copyOnWriteArrayList;
                Intrinsics.checkParameterIsNotNull(state, "state");
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onStatusChanged state:" + state + ' ');
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.statusChangeListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((Function1) it.next()).invoke(state);
                }
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onVolumeChanged(int volume) {
                String str;
                CopyOnWriteArrayList copyOnWriteArrayList;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onVolumeChanged volume:" + volume + ' ');
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.volumeChangeListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((Function1) it.next()).invoke(Integer.valueOf(volume));
                }
            }

            @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
            public void onWakeup(WakeupInfo data) {
                String str;
                CopyOnWriteArrayList copyOnWriteArrayList;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onWakeup data:" + data + ' ');
                if (data != null) {
                    AiVoiceManager.INSTANCE.setLastWakeAngle(data.getAngle());
                }
                AiVoiceManager.INSTANCE.setShopId(String.valueOf(Constant.INSTANCE.getShopId()));
                AiVoiceManager.INSTANCE.setMapName(RobotMapManager.INSTANCE.getDefaultPdmap());
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                copyOnWriteArrayList = AiVoiceManager.wakeupListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((Function1) it.next()).invoke(data);
                }
            }
        };
    }

    private AiVoiceManager() {
    }

    public final int getLastWakeAngle() {
        return lastWakeAngle;
    }

    public final void setLastWakeAngle(int i) {
        lastWakeAngle = i;
    }

    public final String getWAKEUP_VOICE() {
        return WAKEUP_VOICE;
    }

    public final int businessParse(int state, String json) {
        final OkHttpUtils.FaqAnswer faqAnswer;
        String trigger_extend;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        if (state == 1 && !TextUtils.isEmpty(json) && (faqAnswer = (OkHttpUtils.FaqAnswer) GsonSingleton.INSTANCE.getINSTANCE().fromJson(json, OkHttpUtils.FaqAnswer.class)) != null && "3".equals(faqAnswer.getTrigger_type())) {
            int question_type = faqAnswer.getQuestion_type();
            if (question_type == 3) {
                String trigger_image_url = faqAnswer.getTrigger_image_url();
                if (trigger_image_url != null) {
                    intRef.element = 1;
                    Iterator<T> it = businessResponseListeners.iterator();
                    while (it.hasNext()) {
                        ((Function2) it.next()).invoke(trigger_image_url, faqAnswer.getAnswer());
                    }
                }
            } else if (question_type == 4 && VoiceCommentConfig.INSTANCE.getWokerMode() == WorkMode.Guide && (trigger_extend = faqAnswer.getTrigger_extend()) != null) {
                intRef.element = 1;
                String str = trigger_extend;
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "/", false, 2, (Object) null)) {
                    List split$default = StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null);
                    if (split$default.size() > 0) {
                        trigger_extend = (String) split$default.get(1);
                    }
                }
                AiVoiceTriggerHelper.INSTANCE.handlerBusiness(faqAnswer.getAnswer(), trigger_extend, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$businessParse$$inlined$apply$lambda$1
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
                        intRef.element = 0;
                    }
                });
                CopyOnWriteArrayList<Function1<CmdBean, Unit>> copyOnWriteArrayList = cmdResponseListeners;
                if (copyOnWriteArrayList != null) {
                    Iterator<T> it2 = copyOnWriteArrayList.iterator();
                    while (it2.hasNext()) {
                        ((Function1) it2.next()).invoke(null);
                    }
                }
                Pdlog.m3273d(TAG, "handlerBusiness() answer =" + faqAnswer.getAnswer() + " trigger_extend =" + faqAnswer.getTrigger_extend() + " destination =" + trigger_extend);
            }
        }
        return intRef.element;
    }

    public final void startWakeUp(int angle, int beam) {
        VoiceInteractionHelper voiceInteractionHelper;
        if (!Intrinsics.areEqual((Object) isInitSuccess, (Object) true) || (voiceInteractionHelper = aiVoiceHelper) == null) {
            return;
        }
        voiceInteractionHelper.wakeup(angle, beam);
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (!Constant.INSTANCE.getRobotActive() || Constant.INSTANCE.getPreActiveType() != ActivatePresenter.ActiveType.OFFICIAL_TYPE.ordinal()) {
            Pdlog.m3273d(TAG, "init robot is not active,don't initialize aiui: Constans.getRobotActive:" + Constant.INSTANCE.getRobotActive() + " , Constans.getPreActiveType:" + Constant.INSTANCE.getPreActiveType());
            return;
        }
        if (getEchoDevice(context) == 1) {
            pid = "a4a6";
            vid = "1d6b";
        } else {
            pid = "5758";
            vid = AccLoraConnection.LoRaConfig.ID_VENDOR;
        }
        Pdlog.m3273d(TAG, "init");
        aiVoiceHelper = new VoiceInteractionHelper();
        VoiceCommentConfig.INSTANCE.setScene("custom_box");
        VoiceCommentConfig.INSTANCE.setRecodeType(2);
        VoiceCommentConfig.INSTANCE.setAudioCard(RobotInfo.INSTANCE.isPDCore() ? 2 : 1);
        VoiceCommentConfig.INSTANCE.setVadBosTimeout(15000);
        VoiceCommentConfig.INSTANCE.setVadEosTimeout(600);
        VoiceCommentConfig.INSTANCE.setContinuous(false);
        VoiceCommentConfig.INSTANCE.setTtsVolume(60);
        VoiceCommentConfig.INSTANCE.setProductType("BellaBot");
        VoiceCommentConfig.INSTANCE.setCnAceWakeupWorkAssetName("chinese_wakeup_words.bin");
        VoiceCommentConfig.INSTANCE.setEnAceWakeupWorkAssetName("english_wakeup_words.bin");
        VoiceCommentConfig.INSTANCE.setGoogleDialogflowConfigKey("dialogflow_key.json");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper != null) {
            voiceInteractionHelper.setRobotInfo("BellaBot", "6.12.0.10");
        }
        VoiceCommentConfig.INSTANCE.getCnWakeupList().addAll(CollectionsKt.arrayListOf("xiao3-bei4-xiao3-bei4", "hey-pu3-du4"));
        VoiceCommentConfig.INSTANCE.getEnWakeupList().addAll(CollectionsKt.arrayListOf("bei4-la1-bei4-la1", "hey-pu3-du4"));
        LogProxy.INSTANCE.setDlog(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String t, String m) {
                String str;
                Intrinsics.checkParameterIsNotNull(t, "t");
                Intrinsics.checkParameterIsNotNull(m, "m");
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, t, m);
            }
        });
        LogProxy.INSTANCE.setElog(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String t, String m) {
                String str;
                Intrinsics.checkParameterIsNotNull(t, "t");
                Intrinsics.checkParameterIsNotNull(m, "m");
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3274e(str, t, m);
            }
        });
        LogProxy.INSTANCE.setIlog(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String t, String m) {
                String str;
                Intrinsics.checkParameterIsNotNull(t, "t");
                Intrinsics.checkParameterIsNotNull(m, "m");
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3275i(str, t, m);
            }
        });
        LogProxy.INSTANCE.setWlog(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String t, String m) {
                String str;
                Intrinsics.checkParameterIsNotNull(t, "t");
                Intrinsics.checkParameterIsNotNull(m, "m");
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3277w(str, t, m);
            }
        });
        try {
            Pdlog.m3273d(TAG, "init aiVoiceHelper start");
            VoiceInteractionHelper voiceInteractionHelper2 = aiVoiceHelper;
            if (voiceInteractionHelper2 != null) {
                voiceInteractionHelper2.init(context, getLanguage(), (r18 & 4) != 0 ? (Function0) null : new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$5
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        String str;
                        boolean z;
                        VoiceInteractionHelper voiceInteractionHelper3;
                        boolean z2;
                        String str2;
                        VoiceInteractionHelper voiceInteractionHelper4;
                        AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                        str = AiVoiceManager.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("onInitSuccessListener isNeedRecording = ");
                        AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                        z = AiVoiceManager.isNeedRecording;
                        sb.append(z);
                        Pdlog.m3273d(str, sb.toString());
                        AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                        AiVoiceManager.isInitSuccess = true;
                        Pair<String, String> speaker = AiVoiceManager.INSTANCE.getSpeaker();
                        AiVoiceManager aiVoiceManager4 = AiVoiceManager.INSTANCE;
                        voiceInteractionHelper3 = AiVoiceManager.aiVoiceHelper;
                        if (voiceInteractionHelper3 != null) {
                            voiceInteractionHelper3.setSpeaker(speaker.getFirst(), speaker.getSecond());
                        }
                        AiVoiceManager aiVoiceManager5 = AiVoiceManager.INSTANCE;
                        z2 = AiVoiceManager.isNeedRecording;
                        if (z2) {
                            try {
                                AiVoiceManager aiVoiceManager6 = AiVoiceManager.INSTANCE;
                                voiceInteractionHelper4 = AiVoiceManager.aiVoiceHelper;
                                if (voiceInteractionHelper4 != null) {
                                    voiceInteractionHelper4.startRecording();
                                }
                            } catch (Exception e) {
                                AiVoiceManager aiVoiceManager7 = AiVoiceManager.INSTANCE;
                                str2 = AiVoiceManager.TAG;
                                Pdlog.m3274e(str2, Log.getStackTraceString(e));
                            }
                        }
                    }
                }, (r18 & 8) != 0 ? (Function0) null : new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$init$6
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        String str;
                        AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                        AiVoiceManager.isInitSuccess = false;
                        AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                        str = AiVoiceManager.TAG;
                        Pdlog.m3274e(str, "onInitSuccessListener onInitFailure");
                    }
                }, pid, vid, (r18 & 64) != 0 ? false : null);
            }
            VoiceInteractionHelper voiceInteractionHelper3 = aiVoiceHelper;
            if (voiceInteractionHelper3 != null) {
                voiceInteractionHelper3.addVoiceListener(voiceInteractionListener);
            }
            Pdlog.m3273d(TAG, "init aiVoiceHelper end");
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void setMapName(String mapName) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        VoiceCommentConfig.INSTANCE.setMapName(mapName);
    }

    public final void setShopId(String shopId) {
        Intrinsics.checkParameterIsNotNull(shopId, "shopId");
        VoiceCommentConfig.INSTANCE.setShopId(shopId);
        Pdlog.m3273d(TAG, "setShopId() =" + VoiceCommentConfig.INSTANCE.getShopId());
    }

    public final void setWorkMode(WorkMode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        VoiceCommentConfig.INSTANCE.setWokerMode(mode);
    }

    public final Pair<String, String> getSpeaker() {
        Option current = new LanguageUtils(RobotContext.INSTANCE.getContext()).getCurrent();
        Pdlog.m3273d(TAG, "getSpeaker " + current);
        Locale locale = current.getLocale();
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getENGLISH().getLocale())) {
            return TuplesKt.m3968to("en-US-Wavenet-C", "en-US");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE().getLocale())) {
            return TuplesKt.m3968to("x3_yezi", "zh_cn");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE_HK().getLocale())) {
            return TuplesKt.m3968to("yue-HK-Standard-A", "zh-HK");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE_TW().getLocale())) {
            return TuplesKt.m3968to("x3_yezi", "zh_cn");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getJAPANESE().getLocale())) {
            return TuplesKt.m3968to("ja-JP-Wavenet-B", "ja-JP");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getDUTCH().getLocale())) {
            return TuplesKt.m3968to("nl-NL-Wavenet-A", "nl-NL");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getKOREAN().getLocale())) {
            return TuplesKt.m3968to("ko-KR-Wavenet-A", "ko-KR");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSPANISH().getLocale())) {
            return TuplesKt.m3968to("es-ES-Standard-A", "es-ES");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getFRENCH().getLocale())) {
            return TuplesKt.m3968to("fr-FR-Wavenet-A", "fr-FR");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getITALIAN().getLocale())) {
            return TuplesKt.m3968to("it-IT-Wavenet-A", "it-IT");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getRUSSIAN().getLocale())) {
            return TuplesKt.m3968to("ru-RU-Wavenet-C", "ru-RU");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getGERMAN().getLocale())) {
            return TuplesKt.m3968to("de-DE-Wavenet-F", "de-DE");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPORTUGUESE_Brazil().getLocale())) {
            return TuplesKt.m3968to("pt-BR-Wavenet-C", "pt-BR");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPORTUGUESE().getLocale())) {
            return TuplesKt.m3968to("pt-PT-Wavenet-A", "pt-PT");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPOLAND().getLocale())) {
            return TuplesKt.m3968to("pl-PL-Wavenet-D", "pl-PL");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getTURKEY().getLocale())) {
            return TuplesKt.m3968to("tr-TR-Wavenet-D", "tr-TR");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getARAB().getLocale())) {
            return TuplesKt.m3968to("ar-SA-Wavenet-A", "ar-SA");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCZECH().getLocale())) {
            return TuplesKt.m3968to("cs-CZ-Wavenet-A", "cs-CZ");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getTHAI_LAND().getLocale())) {
            return TuplesKt.m3968to("th-TH-Standard-A", "th-TH");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getINDONESIAN().getLocale())) {
            return TuplesKt.m3968to("id-ID-Wavenet-D", "id-ID");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSWEDISH().getLocale())) {
            return TuplesKt.m3968to("sv-SE-Wavenet-A", "sv-SE");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getHUNGARIAN().getLocale())) {
            return TuplesKt.m3968to("hu-HU-Wavenet-A", "hu-HU");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getBULGARIAN().getLocale())) {
            return TuplesKt.m3968to("bg-bg-Standard-A", "bg-BG");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getVIETNAMESE().getLocale())) {
            return TuplesKt.m3968to("vi-VN-Standard-A", "vi-VN");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSLOVAK().getLocale())) {
            return TuplesKt.m3968to("sk-SK-Wavenet-A", "sk-SK");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSERBIAN().getLocale())) {
            return TuplesKt.m3968to("sr-rs-Standard-A", "sr-RS");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getROMANIAN().getLocale())) {
            return TuplesKt.m3968to("ro-RO-Wavenet-A", "ro-RO");
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getMALAY().getLocale())) {
            return TuplesKt.m3968to("ms-MY-Standard-A", "ms-MY");
        }
        return TuplesKt.m3968to("en-US-Wavenet-C", "en-US");
    }

    private final Language getLanguage() {
        Option current = new LanguageUtils(RobotContext.INSTANCE.getContext()).getCurrent();
        Pdlog.m3273d(TAG, "getLanguage()---current" + current.getLocale());
        return transLanguage(current.getLocale());
    }

    public final Language transLanguage(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3273d(TAG, "transLanguage locale : " + locale + ",CHINESE_HK:" + SupportedLocale.INSTANCE.getCHINESE_HK().getLocale() + ",CHINESE:" + SupportedLocale.INSTANCE.getCHINESE().getLocale());
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getENGLISH().getLocale())) {
            return Language.English;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE_TW().getLocale()) || Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE().getLocale())) {
            return Language.Chinese;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCHINESE_HK().getLocale())) {
            return Language.ChineseHk;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getJAPANESE().getLocale())) {
            return Language.Japanese;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSPANISH().getLocale())) {
            return Language.Spanish;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getKOREAN().getLocale())) {
            return Language.Korean;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getFRENCH().getLocale())) {
            return Language.French;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getITALIAN().getLocale())) {
            return Language.Italian;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getRUSSIAN().getLocale())) {
            return Language.Russian;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getGERMAN().getLocale())) {
            return Language.German;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getDUTCH().getLocale())) {
            return Language.Dutch;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPOLAND().getLocale())) {
            return Language.Polish;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPORTUGUESE().getLocale())) {
            return Language.Portuguese;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getPORTUGUESE_Brazil().getLocale())) {
            return Language.PortugueseBr;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getTHAI_LAND().getLocale())) {
            return Language.Thai;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getTURKEY().getLocale())) {
            return Language.Turkish;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getSPANISH().getLocale())) {
            return Language.Spanish;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getINDONESIAN().getLocale())) {
            return Language.Indonesian;
        }
        if (Intrinsics.areEqual(locale, SupportedLocale.INSTANCE.getCZECH().getLocale())) {
            return Language.Czech;
        }
        return Language.English;
    }

    public final void changLanguage(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3273d(TAG, "changLanguage : locale = " + locale + "; ");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper != null) {
            voiceInteractionHelper.switchLanguage(transLanguage(locale), new Function0() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$changLanguage$1
                @Override // kotlin.jvm.functions.Function0
                public final Void invoke() {
                    String str;
                    VoiceInteractionHelper voiceInteractionHelper2;
                    AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                    str = AiVoiceManager.TAG;
                    Pdlog.m3273d(str, "changLanguage onSwitchLanguageSucceedListener");
                    Pair<String, String> speaker = AiVoiceManager.INSTANCE.getSpeaker();
                    AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                    voiceInteractionHelper2 = AiVoiceManager.aiVoiceHelper;
                    if (voiceInteractionHelper2 == null) {
                        return null;
                    }
                    voiceInteractionHelper2.setSpeaker(speaker.getFirst(), speaker.getSecond());
                    return null;
                }
            }, new Function0() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager$changLanguage$2
                @Override // kotlin.jvm.functions.Function0
                public final Void invoke() {
                    String str;
                    AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                    str = AiVoiceManager.TAG;
                    Pdlog.m3273d(str, "changLanguage onSwitchLanguageFailedListener");
                    return null;
                }
            });
        }
    }

    public final void cancelTTS() {
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper != null) {
            voiceInteractionHelper.cancelTTS();
        }
    }

    public final void cancelCurrentRound() {
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper != null) {
            voiceInteractionHelper.cancelCurrentRound();
        }
    }

    public static /* synthetic */ void startAiRecording$default(AiVoiceManager aiVoiceManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        aiVoiceManager.startAiRecording(z);
    }

    public final void startAiRecording(boolean isIgnoreVoiceSwitch) {
        Pdlog.m3273d(TAG, "startAiRecording");
        if (!Constans.INSTANCE.getVoiceSwitch() && !isIgnoreVoiceSwitch) {
            Pdlog.m3273d(TAG, "startAiRecording: voiceSwitch is close,no need to start record");
            return;
        }
        Boolean bool = isInitSuccess;
        if (bool == null) {
            Pdlog.m3273d(TAG, "startAiRecording failed, need wait init");
            isNeedRecording = true;
            return;
        }
        if (Intrinsics.areEqual((Object) bool, (Object) false)) {
            Pdlog.m3273d(TAG, "startAiRecording failed, init failed,need to retry init");
            isNeedRecording = true;
            init(RobotContext.INSTANCE.getContext());
            return;
        }
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper != null) {
                voiceInteractionHelper.startRecording();
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void stopAiRecording() {
        Pdlog.m3273d(TAG, "stopAiRecording " + isInitSuccess);
        Boolean bool = isInitSuccess;
        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) false)) {
            isNeedRecording = false;
            return;
        }
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper != null) {
                voiceInteractionHelper.stopRecording();
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void release() {
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper != null) {
                voiceInteractionHelper.release();
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void addWakeupListener(Function1<? super WakeupInfo, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (wakeupListeners.contains(listener)) {
            return;
        }
        wakeupListeners.add(listener);
    }

    public final void removeWakeupListener(Function1<? super WakeupInfo, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        wakeupListeners.remove(listener);
    }

    public final void addVolumeChangeListener(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (volumeChangeListeners.contains(listener)) {
            return;
        }
        volumeChangeListeners.add(listener);
    }

    public final void removeVolumeChangeListener(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        volumeChangeListeners.remove(listener);
    }

    public final void addStatusListener(Function1<? super VoiceInteractionState, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (statusChangeListeners.contains(listener)) {
            return;
        }
        statusChangeListeners.add(listener);
    }

    public final void removeStatusListener(Function1<? super VoiceInteractionState, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        statusChangeListeners.remove(listener);
    }

    public final void addResultAnswerListener(Function3<? super String, ? super String, ? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (resultAnswerListeners.contains(listener)) {
            return;
        }
        resultAnswerListeners.add(listener);
    }

    public final void removeResultAnswerListener(Function3<? super String, ? super String, ? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        resultAnswerListeners.remove(listener);
    }

    public final void addBusinessResponseListener(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (businessResponseListeners.contains(listener)) {
            return;
        }
        businessResponseListeners.add(listener);
    }

    public final void removeBusinessResponseListener(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        businessResponseListeners.remove(listener);
    }

    public final void addResultQuestionListener(Function2<? super String, ? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (resultQuestionListeners.contains(listener)) {
            return;
        }
        resultQuestionListeners.add(listener);
    }

    public final void removeResultQuestionListener(Function2<? super String, ? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        resultQuestionListeners.remove(listener);
    }

    public final void addCmdResponseListener(Function1<? super CmdBean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (cmdResponseListeners.contains(listener)) {
            return;
        }
        cmdResponseListeners.add(listener);
    }

    public final void removeCmdResponseListener(Function1<? super CmdBean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        cmdResponseListeners.remove(listener);
    }

    private final int getEchoDevice(Context context) {
        Object systemService = context.getSystemService("usb");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        UsbManager usbManager = (UsbManager) systemService;
        if (usbManager == null) {
            Intrinsics.throwNpe();
        }
        ArrayList arrayList = new ArrayList();
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            arrayList.add(usbDevice);
            Pdlog.m3273d(TAG, "usb device:" + usbDevice.getVendorId() + "  and  " + usbDevice.getProductId());
            if (7531 == usbDevice.getVendorId() && 42150 == usbDevice.getProductId()) {
                Pdlog.m3273d(TAG, "带有回声消除的");
                return 1;
            }
            if (1155 == usbDevice.getVendorId() && 22360 == usbDevice.getProductId()) {
                Pdlog.m3273d(TAG, "不带有回声消除的");
                return 2;
            }
        }
        Pdlog.m3273d(TAG, "未查到对应的设备");
        return 0;
    }
}

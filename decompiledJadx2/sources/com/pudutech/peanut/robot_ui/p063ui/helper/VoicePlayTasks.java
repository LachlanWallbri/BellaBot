package com.pudutech.peanut.robot_ui.p063ui.helper;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.SolicitBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.VoiceItem;
import com.pudutech.robot.module.voice.RobotVoicePlayer;
import com.pudutech.robot.module.voice.data.BaseVoice;
import com.pudutech.robot.module.voice.data.CombineVoices;
import com.pudutech.robot.module.voice.data.PlayEvent;
import com.pudutech.robot.module.voice.data.SingleVoice;
import com.pudutech.robot.module.voice.data.VoicePlayMode;
import com.pudutech.robot.module.voice.data.VoicePlayTask;
import com.pudutech.robot.module.voice.data.VoicePlayType;
import com.pudutech.robot.module.voice.data.VoiceSubItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: VoicePlayTasks.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J[\u0010\u0012\u001a\u00020\u00042S\b\u0002\u0010\u0013\u001aM\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014J\u0006\u0010\u001c\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u0010J\u0006\u0010\u001e\u001a\u00020\u0004J[\u0010\u001f\u001a\u00020\u00102S\b\u0002\u0010 \u001aM\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014J\u0006\u0010!\u001a\u00020\u0010J[\u0010\"\u001a\u00020\u00102S\b\u0002\u0010\u0013\u001aM\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014J\u0006\u0010#\u001a\u00020\u0010J\u0006\u0010$\u001a\u00020\u0010J\u0006\u0010%\u001a\u00020\u0010J.\u0010&\u001a\u00020\u00102\b\b\u0002\u0010'\u001a\u00020\u00042\u001c\b\u0002\u0010(\u001a\u0016\u0012\u0004\u0012\u00020)\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020)\u0018\u0001`\bJ\u0006\u0010*\u001a\u00020\u0010J[\u0010+\u001a\u00020\u00102S\b\u0002\u0010\u0013\u001aM\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014J\u0006\u0010,\u001a\u00020\u0010J[\u0010-\u001a\u00020\u00102S\b\u0002\u0010\u0013\u001aM\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014J\u0006\u0010.\u001a\u00020\u0010J\u0006\u0010/\u001a\u00020\u0010J\u0006\u00100\u001a\u00020\u0010J\u0006\u00101\u001a\u00020\u0010J\u0006\u00102\u001a\u00020\u0010J\u0006\u00103\u001a\u00020\u0010J\u0006\u00104\u001a\u00020\u0010J\u0006\u00105\u001a\u00020\u0010J\u0006\u00106\u001a\u00020\u0010J\u0006\u00107\u001a\u00020\u0010J\u0006\u00108\u001a\u00020\u0010J\u0006\u00109\u001a\u00020\u0010J\u0006\u0010:\u001a\u00020\u0010J\u0006\u0010;\u001a\u00020\u0010J\u0006\u0010<\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/VoicePlayTasks;", "", "()V", "TAG", "", "cruiseVoiceList", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "Lkotlin/collections/ArrayList;", "deliverVoiceList", "Lcom/pudutech/peanut/robot_ui/bean/SolicitBean;", "mSubItemList", "Lcom/pudutech/robot/module/voice/data/VoiceSubItem;", "solicitVoiceList", "usherVoiceList", "finishStop", "", "playAttractCustomer1", "playAttractCustomer2", "l", "Lkotlin/Function3;", "Lcom/pudutech/robot/module/voice/data/PlayEvent;", "Lkotlin/ParameterName;", "name", "event", AIUIConstant.KEY_CONTENT, "", "isCombine", "playAttractCustomer3", "playAttractCustomer4", "playAttractUsher", "playCruiseMoveVoiceTask", "listener", "playCruiseSolicit", "playCustomerArrive", "playCustomerEndFollow", "playCustomerStartFollow", "playDeliverMoving", "playDeliveryArrive", "desName", "trays", "", "playDeliveryArriveLooperTip", "playDeliveryFinish", "playDeliveryGoAlert", "playDeliveryInputOne", "playGreeter", "playGreeterArrive", "playGreeterStart", "playGreeterTurnBack", "playIntoDeliveryEdit", "playLowPower0_2", "playLowPower2_5", "playLowPower5_10", "playMarkerLostLocation", "playMotorError", "playMovingSchedule", "playMovingStruck", "playSayHi", "playTakeNo", "stop", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class VoicePlayTasks {
    private static ArrayList<BaseVoice> cruiseVoiceList;
    private static ArrayList<SolicitBean> deliverVoiceList;
    private static ArrayList<VoiceSubItem> mSubItemList;
    private static ArrayList<SolicitBean> solicitVoiceList;
    private static ArrayList<SolicitBean> usherVoiceList;
    public static final VoicePlayTasks INSTANCE = new VoicePlayTasks();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private VoicePlayTasks() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playCruiseMoveVoiceTask$default(VoicePlayTasks voicePlayTasks, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = (Function3) null;
        }
        voicePlayTasks.playCruiseMoveVoiceTask(function3);
    }

    public final void playCruiseMoveVoiceTask(Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> listener) {
        String voicePath;
        ArrayList<VoiceSubItem> arrayList;
        ArrayList<VoiceSubItem> arrayList2;
        String voicePath2;
        ArrayList<VoiceSubItem> arrayList3;
        Pdlog.m3273d(TAG, "playCruiseMoveVoiceTask ");
        ArrayList<VoiceSubItem> arrayList4 = null;
        List<TtsVoiceHelper.TtsConfigData> ttsConfigList$default = TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE, false, 2, null);
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_cruise_voice_interval", "20");
        cruiseVoiceList = new ArrayList<>();
        Pdlog.m3273d(TAG, "TtsVoiceHelper= " + ttsConfigList$default + ' ');
        if (ttsConfigList$default != null) {
            for (TtsVoiceHelper.TtsConfigData ttsConfigData : ttsConfigList$default) {
                if (ttsConfigData.isSelect()) {
                    mSubItemList = arrayList4;
                    mSubItemList = new ArrayList<>();
                    ArrayList<VoiceSubItem> arrayList5 = mSubItemList;
                    if (arrayList5 != null) {
                        arrayList5.add(new VoiceSubItem(ttsConfigData.getName(), ttsConfigData.getPath(), 0L));
                    }
                    if (Constans.INSTANCE.getCruiseSwitch() && (voicePath2 = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_5)) != null && (arrayList3 = mSubItemList) != null) {
                        String voiceName = TtsPkManager.INSTANCE.getVoiceName(VoiceItem.voice54_5);
                        if (voiceName == null) {
                            voiceName = "";
                        }
                        arrayList3.add(new VoiceSubItem(voiceName, voicePath2, 0L));
                    }
                    ArrayList<BaseVoice> arrayList6 = cruiseVoiceList;
                    if (arrayList6 != null) {
                        ArrayList<VoiceSubItem> arrayList7 = mSubItemList;
                        if (arrayList7 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList6.add(new CombineVoices(arrayList7, Integer.parseInt(str) * 1000, -1));
                    }
                }
                arrayList4 = null;
            }
        }
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("cruiseVoiceList= ");
        sb.append(cruiseVoiceList);
        sb.append(" getCruiseVoiceSwitch =");
        sb.append(!Constans.INSTANCE.getCruiseVoiceSwitch());
        Pdlog.m3273d(str2, sb.toString());
        if (Constans.INSTANCE.getCruiseVoiceSwitch() && ttsConfigList$default != null && ttsConfigList$default.size() != 0) {
            ArrayList<BaseVoice> arrayList8 = cruiseVoiceList;
            if (arrayList8 == null) {
                Intrinsics.throwNpe();
            }
            if (arrayList8.size() != 0) {
                RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                ArrayList<BaseVoice> arrayList9 = cruiseVoiceList;
                if (arrayList9 == null) {
                    Intrinsics.throwNpe();
                }
                robotVoicePlayer.play(new VoicePlayTask(arrayList9, VoicePlayMode.Mode, VoicePlayType.Random, listener));
                return;
            }
        }
        mSubItemList = (ArrayList) null;
        mSubItemList = new ArrayList<>();
        String voicePath3 = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice19_1);
        if (voicePath3 != null && (arrayList2 = mSubItemList) != null) {
            String voiceName2 = TtsPkManager.INSTANCE.getVoiceName(VoiceItem.voice19_1);
            if (voiceName2 == null) {
                voiceName2 = "";
            }
            arrayList2.add(new VoiceSubItem(voiceName2, voicePath3, 0L));
        }
        if (Constans.INSTANCE.getCruiseSwitch() && (voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_5)) != null && (arrayList = mSubItemList) != null) {
            String voiceName3 = TtsPkManager.INSTANCE.getVoiceName(VoiceItem.voice54_5);
            arrayList.add(new VoiceSubItem(voiceName3 != null ? voiceName3 : "", voicePath, 0L));
        }
        if (TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice19_1) != null) {
            RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
            BaseVoice[] baseVoiceArr = new BaseVoice[1];
            ArrayList<VoiceSubItem> arrayList10 = mSubItemList;
            if (arrayList10 == null) {
                Intrinsics.throwNpe();
            }
            baseVoiceArr[0] = new CombineVoices(arrayList10, Integer.parseInt(str) * 1000, -1);
            robotVoicePlayer2.play(new VoicePlayTask(CollectionsKt.arrayListOf(baseVoiceArr), VoicePlayMode.Mode, VoicePlayType.Order, listener));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playDeliveryArrive$default(VoicePlayTasks voicePlayTasks, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            arrayList = (ArrayList) null;
        }
        voicePlayTasks.playDeliveryArrive(str, arrayList);
    }

    public final void playDeliveryArrive(String desName, ArrayList<Integer> trays) {
        ArrayList<SolicitBean> arrayList;
        Intrinsics.checkParameterIsNotNull(desName, "desName");
        Pdlog.m3273d(TAG, "playDeliveryArrive ");
        stop();
        deliverVoiceList = new ArrayList<>();
        List<TtsVoiceHelper.TtsConfigData> ttsConfigList$default = TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE, false, 2, null);
        if (ttsConfigList$default != null) {
            for (TtsVoiceHelper.TtsConfigData ttsConfigData : ttsConfigList$default) {
                if (ttsConfigData.isSelect() && (arrayList = deliverVoiceList) != null) {
                    arrayList.add(new SolicitBean(ttsConfigData.getName(), ttsConfigData.getPath()));
                }
            }
        }
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("voice size ");
        ArrayList<SolicitBean> arrayList2 = deliverVoiceList;
        sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3274e(str, objArr);
        if (Constans.INSTANCE.getDeliverVoiceSwitch() && deliverVoiceList != null && ttsConfigList$default.size() != 0) {
            ArrayList<SolicitBean> arrayList3 = deliverVoiceList;
            if (arrayList3 == null) {
                Intrinsics.throwNpe();
            }
            if (arrayList3.size() != 0) {
                Random.Companion companion = Random.INSTANCE;
                ArrayList<SolicitBean> arrayList4 = deliverVoiceList;
                if (arrayList4 == null) {
                    Intrinsics.throwNpe();
                }
                int nextInt = companion.nextInt(arrayList4.size());
                RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                BaseVoice[] baseVoiceArr = new BaseVoice[1];
                ArrayList<SolicitBean> arrayList5 = deliverVoiceList;
                if (arrayList5 == null) {
                    Intrinsics.throwNpe();
                }
                baseVoiceArr[0] = new SingleVoice(null, arrayList5.get(nextInt).getPath(), 0L, 0, 13, null);
                robotVoicePlayer.play(new VoicePlayTask(CollectionsKt.arrayListOf(baseVoiceArr), VoicePlayMode.Temp, null, null, 12, null));
                return;
            }
        }
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice8_4);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    public final void playDeliveryArriveLooperTip() {
        Pdlog.m3273d(TAG, "playDeliveryArriveLooperTip ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice19_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, Constans.INSTANCE.getPlayLooperArriveVoiceTime(), -1, 1, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playDeliveryFinish$default(VoicePlayTasks voicePlayTasks, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = (Function3) null;
        }
        voicePlayTasks.playDeliveryFinish(function3);
    }

    public final void playDeliveryFinish(Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "playDeliveryFinish ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice10_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, l, 4, null));
        }
    }

    public final void playSayHi() {
        Pdlog.m3273d(TAG, "playDeliveryFinish ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice1_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playDeliveryInputOne$default(VoicePlayTasks voicePlayTasks, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = (Function3) null;
        }
        voicePlayTasks.playDeliveryInputOne(function3);
    }

    public final void playDeliveryInputOne(Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "playDeliveryInputOne : l = " + l + "; ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice5_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, l, 4, null));
        }
    }

    public final void playDeliveryGoAlert() {
        Pdlog.m3273d(TAG, "playIntoDeliveryEdit ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice6_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playIntoDeliveryEdit() {
        Pdlog.m3273d(TAG, "playIntoDeliveryEdit ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice5_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playDeliverMoving() {
        Pdlog.m3273d(TAG, "playDeliverMoving ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice7_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 10000L, -1, 1, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playMovingStruck() {
        Pdlog.m3273d(TAG, "playMovingStruck ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice13_1);
        if (voicePath != null) {
            RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
            BaseVoice[] baseVoiceArr = new BaseVoice[3];
            baseVoiceArr[0] = new SingleVoice(null, voicePath, 0L, 1, 1, null);
            baseVoiceArr[1] = new SingleVoice(null, voicePath, 10000L, 2, 1, null);
            String voicePath2 = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice13_2);
            if (voicePath2 == null) {
                voicePath2 = "";
            }
            baseVoiceArr[2] = new SingleVoice(null, voicePath2, 5000L, -1, 1, null);
            robotVoicePlayer.play(new VoicePlayTask(CollectionsKt.arrayListOf(baseVoiceArr), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playMovingSchedule() {
        Pdlog.m3273d(TAG, "playMovingSchedule ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice20_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 1, 1, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void stop() {
        Pdlog.m3273d(TAG, "stop ");
        RobotVoicePlayer.INSTANCE.stop();
    }

    public final void finishStop() {
        Pdlog.m3273d(TAG, "stop ");
        RobotVoicePlayer.INSTANCE.mActivityFinishStop();
    }

    public final void playMarkerLostLocation() {
        Pdlog.m3273d(TAG, "playMarkerLostLocation ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice17_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 1, 1, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playMotorError() {
        Pdlog.m3273d(TAG, "playCruiseMoving ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice22_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playAttractCustomer1() {
        Pdlog.m3273d(TAG, "playAttractCustomer1 ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice50_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String playAttractCustomer2$default(VoicePlayTasks voicePlayTasks, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = (Function3) null;
        }
        return voicePlayTasks.playAttractCustomer2(function3);
    }

    public final String playAttractCustomer2(Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> l) {
        ArrayList<SolicitBean> arrayList;
        Pdlog.m3273d(TAG, "playAttractCustomer2 ");
        solicitVoiceList = new ArrayList<>();
        List<TtsVoiceHelper.TtsConfigData> ttsConfigList$default = TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, TtsVoiceHelper.TtsVoiceType.SOLICIT_TYPE, false, 2, null);
        if (ttsConfigList$default != null) {
            for (TtsVoiceHelper.TtsConfigData ttsConfigData : ttsConfigList$default) {
                if (ttsConfigData.isSelect() && (arrayList = solicitVoiceList) != null) {
                    arrayList.add(new SolicitBean(ttsConfigData.getName(), ttsConfigData.getPath()));
                }
            }
        }
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("voice size ");
        ArrayList<SolicitBean> arrayList2 = solicitVoiceList;
        sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
        sb.append(" solicitVoiceList: ");
        sb.append(solicitVoiceList);
        objArr[0] = sb.toString();
        Pdlog.m3274e(str, objArr);
        if (Constans.INSTANCE.getSolicitVoiceSwitch() && ttsConfigList$default != null && ttsConfigList$default.size() != 0) {
            ArrayList<SolicitBean> arrayList3 = solicitVoiceList;
            if (arrayList3 == null) {
                Intrinsics.throwNpe();
            }
            if (arrayList3.size() != 0) {
                Random.Companion companion = Random.INSTANCE;
                ArrayList<SolicitBean> arrayList4 = solicitVoiceList;
                if (arrayList4 == null) {
                    Intrinsics.throwNpe();
                }
                int nextInt = companion.nextInt(arrayList4.size());
                RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                BaseVoice[] baseVoiceArr = new BaseVoice[1];
                ArrayList<SolicitBean> arrayList5 = solicitVoiceList;
                if (arrayList5 == null) {
                    Intrinsics.throwNpe();
                }
                baseVoiceArr[0] = new SingleVoice(null, arrayList5.get(nextInt).getPath(), 0L, 0, 13, null);
                robotVoicePlayer.play(new VoicePlayTask(CollectionsKt.arrayListOf(baseVoiceArr), VoicePlayMode.Mode, null, l, 4, null));
                ArrayList<SolicitBean> arrayList6 = solicitVoiceList;
                if (arrayList6 == null) {
                    Intrinsics.throwNpe();
                }
                return arrayList6.get(nextInt).getContent();
            }
        }
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice50_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, l, 4, null));
        }
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.voice50_2);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.voice50_2)");
        return string;
    }

    public final String playAttractUsher() {
        ArrayList<SolicitBean> arrayList;
        Pdlog.m3273d(TAG, "playAttractUsher ");
        usherVoiceList = new ArrayList<>();
        List<TtsVoiceHelper.TtsConfigData> ttsConfigList$default = TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, TtsVoiceHelper.TtsVoiceType.USHER_TYPE, false, 2, null);
        if (ttsConfigList$default != null) {
            for (TtsVoiceHelper.TtsConfigData ttsConfigData : ttsConfigList$default) {
                if (ttsConfigData.isSelect() && (arrayList = usherVoiceList) != null) {
                    arrayList.add(new SolicitBean(ttsConfigData.getName(), ttsConfigData.getPath()));
                }
            }
        }
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("voice size ");
        ArrayList<SolicitBean> arrayList2 = solicitVoiceList;
        sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3274e(str, objArr);
        if (Constans.INSTANCE.getGreeterSwitch() && ttsConfigList$default != null && ttsConfigList$default.size() != 0) {
            ArrayList<SolicitBean> arrayList3 = usherVoiceList;
            if (arrayList3 == null) {
                Intrinsics.throwNpe();
            }
            if (arrayList3.size() != 0) {
                Random.Companion companion = Random.INSTANCE;
                ArrayList<SolicitBean> arrayList4 = usherVoiceList;
                if (arrayList4 == null) {
                    Intrinsics.throwNpe();
                }
                int nextInt = companion.nextInt(arrayList4.size());
                RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                BaseVoice[] baseVoiceArr = new BaseVoice[1];
                ArrayList<SolicitBean> arrayList5 = usherVoiceList;
                if (arrayList5 == null) {
                    Intrinsics.throwNpe();
                }
                baseVoiceArr[0] = new SingleVoice(null, arrayList5.get(nextInt).getPath(), 0L, 0, 13, null);
                robotVoicePlayer.play(new VoicePlayTask(CollectionsKt.arrayListOf(baseVoiceArr), VoicePlayMode.Mode, null, null, 12, null));
                ArrayList<SolicitBean> arrayList6 = usherVoiceList;
                if (arrayList6 == null) {
                    Intrinsics.throwNpe();
                }
                return arrayList6.get(nextInt).getContent();
            }
        }
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice50_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.voice50_2);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.voice50_2)");
        return string;
    }

    public final void playAttractCustomer3() {
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice50_3);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    public final void playAttractCustomer4() {
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice50_4);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playCustomerArrive$default(VoicePlayTasks voicePlayTasks, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = (Function3) null;
        }
        voicePlayTasks.playCustomerArrive(function3);
    }

    public final void playCustomerArrive(Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "playCustomerArrive ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice51_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, l, 4, null));
        }
    }

    public final void playCustomerStartFollow() {
        Pdlog.m3273d(TAG, "playCustomerStartFollow ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice52_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playCustomerEndFollow() {
        Pdlog.m3273d(TAG, "playCustomerEndFollow ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice53_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playGreeter() {
        Pdlog.m3273d(TAG, "playGreeter ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playGreeterStart() {
        Pdlog.m3273d(TAG, "playGreeterStart ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playGreeterArrive() {
        Pdlog.m3273d(TAG, "playGreeterArrive ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_3);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playGreeterTurnBack() {
        Pdlog.m3273d(TAG, "playGreeterTurnBack ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_4);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playCruiseSolicit() {
        Pdlog.m3273d(TAG, "playCruiseSolicit ");
        stop();
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice54_5);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playTakeNo() {
        Pdlog.m3273d(TAG, "playTakeNo ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice55_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Mode, null, null, 12, null));
        }
    }

    public final void playLowPower5_10() {
        Pdlog.m3273d(TAG, "playLowPower5_10 ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice14_1);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    public final void playLowPower2_5() {
        Pdlog.m3273d(TAG, "playLowPower2_5 ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice14_2);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }

    public final void playLowPower0_2() {
        Pdlog.m3273d(TAG, "playLowPower0_2 ");
        String voicePath = TtsPkManager.INSTANCE.getVoicePath(VoiceItem.voice14_3);
        if (voicePath != null) {
            RobotVoicePlayer.INSTANCE.play(new VoicePlayTask(CollectionsKt.arrayListOf(new SingleVoice(null, voicePath, 0L, 0, 13, null)), VoicePlayMode.Temp, null, null, 12, null));
        }
    }
}

package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loc.C3898x;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.manager.ThreadPoolManager;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003hijB\u0007\b\u0002¢\u0006\u0002\u0010\u0002JA\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010.\u001a\u00020\u0012¢\u0006\u0002\u0010/JV\u00100\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010.\u001a\u00020\u00122#\u00101\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001602¢\u0006\u0002\u00107J\u0016\u00108\u001a\u00020\u00162\u0006\u00109\u001a\u00020\b2\u0006\u0010*\u001a\u00020+J\u000e\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\fJ\u0010\u0010<\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010=\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+J\u0016\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010*\u001a\u00020+J\u0016\u0010B\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\b2\u0006\u0010*\u001a\u00020+J;\u0010D\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042#\u00101\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001602J\u001e\u0010F\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010G\u001a\u00020HJR\u0010I\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2:\u00101\u001a6\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(E\u0012\u0004\u0012\u00020\u00160JJ\u0018\u0010K\u001a\u00020\u00042\u0006\u0010@\u001a\u00020A2\u0006\u00105\u001a\u00020\u0004H\u0002J\u000e\u0010L\u001a\u00020\u00042\u0006\u0010@\u001a\u00020AJ\u0018\u0010M\u001a\u00020\u00042\u0006\u0010@\u001a\u00020A2\u0006\u00105\u001a\u00020\u0004H\u0002J\u001e\u0010N\u001a\u00020\u00042\u0006\u0010@\u001a\u00020A2\u0006\u00105\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+J\u0010\u0010O\u001a\u00020\u00042\u0006\u0010@\u001a\u00020AH\u0002J\u0010\u0010P\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010Q\u001a\u00020\u00042\u0006\u0010@\u001a\u00020A2\u0006\u00105\u001a\u00020\u0004H\u0002J\u001e\u0010R\u001a\b\u0012\u0004\u0012\u00020\b0S2\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010T\u001a\u00020\u0012J\u0018\u0010U\u001a\u00020\u00042\u0006\u0010@\u001a\u00020A2\u0006\u00105\u001a\u00020\u0004H\u0002J\u000e\u0010V\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+J\u0006\u0010W\u001a\u00020\u0016J\b\u0010X\u001a\u00020\u0016H\u0002J\u0016\u0010Y\u001a\u00020\u00122\u0006\u0010@\u001a\u00020A2\u0006\u0010*\u001a\u00020+J\u0010\u0010Z\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0002J5\u0010[\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\b2%\b\u0002\u0010\\\u001a\u001f\u0012\u0013\u0012\u00110]¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u0016\u0018\u000102J\u0006\u0010_\u001a\u00020\u0016J5\u0010`\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+2%\b\u0002\u0010\\\u001a\u001f\u0012\u0013\u0012\u00110]¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u0016\u0018\u000102J\u0006\u0010a\u001a\u00020\u0016J\u000e\u0010b\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+J\u000e\u0010c\u001a\u00020\u00162\u0006\u0010d\u001a\u00020\u0004J\u001e\u0010e\u001a\u00020\u00162\u0006\u0010@\u001a\u00020A2\u0006\u0010f\u001a\u00020?2\u0006\u0010*\u001a\u00020+J\u0006\u0010g\u001a\u00020\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\"\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001e\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006k"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "cruiseAllLanguageList", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lkotlin/collections/ArrayList;", "cruiseConfigDateList", "currentLocale", "Ljava/util/Locale;", "deliverAllLanguageList", "deliverConfigDateList", "gson", "Lcom/google/gson/Gson;", "isInitSuccess", "", "isIniting", "onCruiseConfigChangeListener", "Lkotlin/Function0;", "", "getOnCruiseConfigChangeListener", "()Lkotlin/jvm/functions/Function0;", "setOnCruiseConfigChangeListener", "(Lkotlin/jvm/functions/Function0;)V", "onDeliverConfigChangeListener", "getOnDeliverConfigChangeListener", "setOnDeliverConfigChangeListener", "onSolicitConfigChangeListener", "getOnSolicitConfigChangeListener", "setOnSolicitConfigChangeListener", "onUsherConfigChangeListener", "getOnUsherConfigChangeListener", "setOnUsherConfigChangeListener", "usherAllLanguageList", "usherConfigDateList", "addConfigItem", "filePath", "it", "text", "ttsVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "index", "", "isSelect", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;Ljava/lang/Integer;Z)V", "addNewTtsVoice", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", C3898x.f4338g, "(Ljava/lang/String;Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;Ljava/lang/Integer;ZLkotlin/jvm/functions/Function1;)V", "changeChoose", "item", "changeLanguageType", "locale", "checkSelect", "checkTtsExist", "checkTtsOpenType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceOpenType;", "context", "Landroid/content/Context;", "deleteConfig", "configData", "genTts", "path", "genTtsVoice", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "generateVoiceTts", "Lkotlin/Function2;", "getCruiseFilePath", "getCruiseTempFilePath", "getDeliverFilePath", "getFilePath", "getPkPath", "getSaveSpKey", "getSolicitFilePath", "getTtsConfigList", "", "checkFile", "getUsherFilePath", "hasSelect", "init", "initLocalConfig", "isOpen", "notifyChange", "playPcm", "listener", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "event", "playTempCruiseTts", "playTtsVoice", "removeAllList", "saveConfig", "setOtherVoice", "ttsName", "setTtsType", "ttsVoiceOpenType", "stopCruiseTts", "TtsConfigData", "TtsVoiceOpenType", "TtsVoiceType", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsVoiceHelper {
    public static final TtsVoiceHelper INSTANCE;
    private static final String TAG;
    private static final ArrayList<TtsConfigData> cruiseAllLanguageList;
    private static final ArrayList<TtsConfigData> cruiseConfigDateList;
    private static Locale currentLocale;
    private static final ArrayList<TtsConfigData> deliverAllLanguageList;
    private static final ArrayList<TtsConfigData> deliverConfigDateList;
    private static final Gson gson;
    private static boolean isInitSuccess;
    private static boolean isIniting;
    private static Function0<Unit> onCruiseConfigChangeListener;
    private static Function0<Unit> onDeliverConfigChangeListener;
    private static Function0<Unit> onSolicitConfigChangeListener;
    private static Function0<Unit> onUsherConfigChangeListener;
    private static final ArrayList<TtsConfigData> usherAllLanguageList;
    private static final ArrayList<TtsConfigData> usherConfigDateList;

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "", "(Ljava/lang/String;I)V", "CRUISE_TYPE", "DELIVER_TYPE", "SOLICIT_TYPE", "USHER_TYPE", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum TtsVoiceType {
        CRUISE_TYPE,
        DELIVER_TYPE,
        SOLICIT_TYPE,
        USHER_TYPE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$10;
        public static final /* synthetic */ int[] $EnumSwitchMapping$11;
        public static final /* synthetic */ int[] $EnumSwitchMapping$12;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$1[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$1[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$1[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$2[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$2[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$2[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$2[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$3 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$3[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$3[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$3[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$3[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$4 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$4[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$4[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$4[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$4[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$5 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$5[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$5[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$5[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$5[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$6 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$6[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$6[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$6[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$6[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$7 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$7[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$7[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$7[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$7[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$8 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$8[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$8[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$8[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$8[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$9 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$9[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$9[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$9[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$9[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$10 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$10[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$10[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$10[TtsVoiceType.DELIVER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$10[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$11 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$11[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$11[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$11[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$11[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$12 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$12[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$12[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$12[TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$12[TtsVoiceType.USHER_TYPE.ordinal()] = 4;
        }
    }

    static {
        TtsVoiceHelper ttsVoiceHelper = new TtsVoiceHelper();
        INSTANCE = ttsVoiceHelper;
        TAG = ttsVoiceHelper.getClass().getSimpleName();
        cruiseConfigDateList = new ArrayList<>();
        deliverConfigDateList = new ArrayList<>();
        usherConfigDateList = new ArrayList<>();
        cruiseAllLanguageList = new ArrayList<>();
        deliverAllLanguageList = new ArrayList<>();
        usherAllLanguageList = new ArrayList<>();
        gson = new Gson();
        currentLocale = new LanguageUtils(RobotContext.INSTANCE.getContext()).getCurrent().getLocale();
    }

    private TtsVoiceHelper() {
    }

    public final Function0<Unit> getOnCruiseConfigChangeListener() {
        return onCruiseConfigChangeListener;
    }

    public final void setOnCruiseConfigChangeListener(Function0<Unit> function0) {
        onCruiseConfigChangeListener = function0;
    }

    public final Function0<Unit> getOnDeliverConfigChangeListener() {
        return onDeliverConfigChangeListener;
    }

    public final void setOnDeliverConfigChangeListener(Function0<Unit> function0) {
        onDeliverConfigChangeListener = function0;
    }

    public final Function0<Unit> getOnSolicitConfigChangeListener() {
        return onSolicitConfigChangeListener;
    }

    public final void setOnSolicitConfigChangeListener(Function0<Unit> function0) {
        onSolicitConfigChangeListener = function0;
    }

    public final Function0<Unit> getOnUsherConfigChangeListener() {
        return onUsherConfigChangeListener;
    }

    public final void setOnUsherConfigChangeListener(Function0<Unit> function0) {
        onUsherConfigChangeListener = function0;
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceOpenType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "CLOSE", "OPEN", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum TtsVoiceOpenType {
        CLOSE(0),
        OPEN(1);

        private final int type;

        TtsVoiceOpenType(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }
    }

    public final void init() {
        if (isIniting || isInitSuccess) {
            Pdlog.m3274e(TAG, "init is initing");
            return;
        }
        isIniting = true;
        TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(Constans.INSTANCE.getTtsVoiceType());
        TtsConfig.INSTANCE.setIFLY_SPEECH_VOLUME(100);
        TtsConfig.INSTANCE.setIFLY_SPEECH_SPEED(50);
        PdTtsSdk.INSTANCE.init(RobotContext.INSTANCE.getContext(), new Function1<Integer, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                String str;
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                str = TtsVoiceHelper.TAG;
                Pdlog.m3273d(str, "PdTtsSdk init , it = " + i);
                if (i == 0) {
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    TtsVoiceHelper.isInitSuccess = true;
                }
                TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                TtsVoiceHelper.isIniting = false;
            }
        });
        initLocalConfig();
        boolean isInitSuccess2 = TtsWordsTemplateHelper.INSTANCE.isInitSuccess();
        Pdlog.m3273d(TAG, "ttsWordsTemplate isInitSuccess: " + isInitSuccess2);
        if (isInitSuccess2) {
            return;
        }
        TtsWordsTemplateHelper.INSTANCE.init();
    }

    public final void setOtherVoice(String ttsName) {
        Intrinsics.checkParameterIsNotNull(ttsName, "ttsName");
        TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(ttsName);
    }

    public final void changeLanguageType(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        boolean z = true;
        Pdlog.m3273d(TAG, "locale=" + locale);
        Pdlog.m3273d(TAG, "cruiseAllLanguageList=" + cruiseAllLanguageList);
        Pdlog.m3273d(TAG, "deliverAllLanguageList=" + deliverAllLanguageList);
        Pdlog.m3273d(TAG, "usherAllLanguageList=" + usherAllLanguageList);
        Pdlog.m3273d(TAG, "cruiseConfigDateList=" + cruiseConfigDateList);
        Pdlog.m3273d(TAG, "deliverConfigDateList=" + deliverConfigDateList);
        Pdlog.m3273d(TAG, "usherConfigDateList=" + usherConfigDateList);
        currentLocale = locale;
        ArrayList<TtsConfigData> arrayList = cruiseConfigDateList;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        cruiseConfigDateList.clear();
        deliverConfigDateList.clear();
        usherConfigDateList.clear();
        ArrayList<TtsConfigData> arrayList2 = cruiseConfigDateList;
        ArrayList<TtsConfigData> arrayList3 = cruiseAllLanguageList;
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList3) {
            if (Intrinsics.areEqual(((TtsConfigData) obj).getLanguageType(), currentLocale)) {
                arrayList4.add(obj);
            }
        }
        arrayList2.addAll(arrayList4);
        ArrayList<TtsConfigData> arrayList5 = deliverConfigDateList;
        ArrayList<TtsConfigData> arrayList6 = deliverAllLanguageList;
        ArrayList arrayList7 = new ArrayList();
        for (Object obj2 : arrayList6) {
            if (Intrinsics.areEqual(((TtsConfigData) obj2).getLanguageType(), currentLocale)) {
                arrayList7.add(obj2);
            }
        }
        arrayList5.addAll(arrayList7);
        ArrayList<TtsConfigData> arrayList8 = usherConfigDateList;
        ArrayList<TtsConfigData> arrayList9 = usherAllLanguageList;
        ArrayList arrayList10 = new ArrayList();
        for (Object obj3 : arrayList9) {
            if (Intrinsics.areEqual(((TtsConfigData) obj3).getLanguageType(), currentLocale)) {
                arrayList10.add(obj3);
            }
        }
        arrayList8.addAll(arrayList10);
        Pdlog.m3273d(TAG, "cruiseAllLanguageList=" + cruiseAllLanguageList);
        Pdlog.m3273d(TAG, "deliverAllLanguageList=" + deliverAllLanguageList);
        Pdlog.m3273d(TAG, "usherAllLanguageList=" + usherAllLanguageList);
        Pdlog.m3273d(TAG, "cruiseConfigDateList=" + cruiseConfigDateList);
        Pdlog.m3273d(TAG, "deliverConfigDateList=" + deliverConfigDateList);
        Pdlog.m3273d(TAG, "usherConfigDateList=" + usherConfigDateList);
        ArrayList<TtsConfigData> arrayList11 = cruiseConfigDateList;
        if (!(arrayList11 == null || arrayList11.isEmpty())) {
            ArrayList<TtsConfigData> arrayList12 = deliverConfigDateList;
            if (!(arrayList12 == null || arrayList12.isEmpty())) {
                ArrayList<TtsConfigData> arrayList13 = usherConfigDateList;
                if (arrayList13 != null && !arrayList13.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    return;
                }
            }
        }
        TtsWordsTemplateHelper.INSTANCE.init();
    }

    private final void initLocalConfig() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_cruise_tts_config", "");
        Pdlog.m3273d(TAG, "initLocalConfig cruise: " + str, currentLocale);
        String str2 = str;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            try {
                ArrayList<TtsConfigData> arrayList = (ArrayList) gson.fromJson(str, new TypeToken<ArrayList<TtsConfigData>>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$initLocalConfig$arrayList$1
                }.getType());
                cruiseAllLanguageList.clear();
                cruiseAllLanguageList.addAll(arrayList);
                Intrinsics.checkExpressionValueIsNotNull(arrayList, "arrayList");
                for (TtsConfigData ttsConfigData : arrayList) {
                    if (Intrinsics.areEqual(ttsConfigData.getLanguageType(), currentLocale)) {
                        cruiseConfigDateList.add(ttsConfigData);
                        Function0<Unit> function0 = onCruiseConfigChangeListener;
                        if (function0 != null) {
                            function0.invoke();
                        }
                    }
                }
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "initLocalConfig failed cruise");
                Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            }
        }
        String str3 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_deliver_tts_config", "");
        Pdlog.m3273d(TAG, "initLocalConfig deliver: " + str3);
        String str4 = str3;
        if (!(str4 == null || StringsKt.isBlank(str4))) {
            try {
                ArrayList<TtsConfigData> arrayList2 = (ArrayList) gson.fromJson(str3, new TypeToken<ArrayList<TtsConfigData>>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$initLocalConfig$arrayList$2
                }.getType());
                deliverAllLanguageList.clear();
                deliverAllLanguageList.addAll(arrayList2);
                Intrinsics.checkExpressionValueIsNotNull(arrayList2, "arrayList");
                for (TtsConfigData ttsConfigData2 : arrayList2) {
                    if (Intrinsics.areEqual(ttsConfigData2.getLanguageType(), currentLocale)) {
                        deliverConfigDateList.add(ttsConfigData2);
                        Function0<Unit> function02 = onDeliverConfigChangeListener;
                        if (function02 != null) {
                            function02.invoke();
                        }
                    }
                }
            } catch (Exception e2) {
                Pdlog.m3274e(TAG, "initLocalConfig failed deliver");
                Pdlog.m3274e(TAG, Log.getStackTraceString(e2));
            }
        }
        String str5 = SpUtils.get(RobotContext.INSTANCE.getContext(), Constans.KEY_USHER_TTS_CONFIG, "");
        Pdlog.m3273d(TAG, "initLocalConfig usher: " + str5);
        String str6 = str5;
        if (!(str6 == null || StringsKt.isBlank(str6))) {
            try {
                ArrayList<TtsConfigData> arrayList3 = (ArrayList) gson.fromJson(str5, new TypeToken<ArrayList<TtsConfigData>>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$initLocalConfig$arrayList$3
                }.getType());
                usherAllLanguageList.clear();
                usherAllLanguageList.addAll(arrayList3);
                Intrinsics.checkExpressionValueIsNotNull(arrayList3, "arrayList");
                for (TtsConfigData ttsConfigData3 : arrayList3) {
                    if (Intrinsics.areEqual(ttsConfigData3.getLanguageType(), currentLocale)) {
                        usherConfigDateList.add(ttsConfigData3);
                        Function0<Unit> function03 = onUsherConfigChangeListener;
                        if (function03 != null) {
                            function03.invoke();
                        }
                    }
                }
            } catch (Exception e3) {
                Pdlog.m3274e(TAG, "initLocalConfig failed usher");
                Pdlog.m3274e(TAG, Log.getStackTraceString(e3));
            }
        }
        Pdlog.m3273d(TAG, "initLocalConfig");
        Pdlog.m3273d(TAG, "cruiseAllLanguageList=" + cruiseAllLanguageList);
        Pdlog.m3273d(TAG, "deliverAllLanguageList=" + deliverAllLanguageList);
        Pdlog.m3273d(TAG, "usherAllLanguageList=" + usherAllLanguageList);
        Pdlog.m3273d(TAG, "cruiseConfigDateList=" + cruiseConfigDateList);
        Pdlog.m3273d(TAG, "deliverConfigDateList=" + deliverConfigDateList);
        Pdlog.m3273d(TAG, "usherConfigDateList=" + usherConfigDateList);
    }

    public final void genTts(String text, String path, final Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "genTts : text = " + text + "; path = " + path + "; callback = " + callback + "; ");
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            callback.invoke(new Exception("tts not init"));
        } else {
            PdTtsSdk.startTts$default(PdTtsSdk.INSTANCE, text, path, new OnTtsListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$genTts$1
                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onProgress(int proses) {
                }

                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onComplete(String filePath) {
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    Function1.this.invoke(null);
                }

                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onError(int code, String msg) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str, "addNewTtsVoice onError , " + code + " , " + msg);
                    Function1.this.invoke(new Exception(msg));
                }
            }, null, 8, null);
        }
    }

    public static /* synthetic */ void addNewTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, String str, TtsVoiceType ttsVoiceType, Integer num, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        Integer num2 = num;
        if ((i & 8) != 0) {
            z = false;
        }
        ttsVoiceHelper.addNewTtsVoice(str, ttsVoiceType, num2, z, function1);
    }

    public final void addNewTtsVoice(final String text, final TtsVoiceType ttsVoiceType, final Integer index, final boolean isSelect, final Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "addNewTtsVoice " + text);
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            callback.invoke(new Exception("tts not init"));
        } else {
            Observable.just(text).map(new Function<T, R>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$1
                @Override // io.reactivex.functions.Function
                public final String apply(String it) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    String md5 = MD5Util.toMD5(it);
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3273d(str, "gen md5 name = " + md5);
                    return md5;
                }
            }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(final String it) {
                    PdTtsSdk pdTtsSdk = PdTtsSdk.INSTANCE;
                    String str = text;
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    Context context = RobotContext.INSTANCE.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    PdTtsSdk.startTts$default(pdTtsSdk, str, ttsVoiceHelper.getFilePath(context, it, ttsVoiceType), new OnTtsListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$2.1
                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onProgress(int proses) {
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onComplete(String filePath) {
                            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                            TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                            String it2 = it;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            ttsVoiceHelper2.addConfigItem(filePath, it2, text, ttsVoiceType, index, isSelect);
                            callback.invoke(null);
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onError(int code, String msg) {
                            String str2;
                            Intrinsics.checkParameterIsNotNull(msg, "msg");
                            TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                            str2 = TtsVoiceHelper.TAG;
                            Pdlog.m3274e(str2, "addNewTtsVoice onError , " + code + " , " + msg);
                            callback.invoke(new Exception(msg));
                        }
                    }, null, 8, null);
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str;
                    String str2;
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str, "md5 error ??");
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    str2 = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str2, Log.getStackTraceString(th));
                    Function1.this.invoke(th);
                }
            });
        }
    }

    public final void generateVoiceTts(final String text, final TtsVoiceType ttsVoiceType, final Function2<? super Throwable, ? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "generateVoiceTts " + text);
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            callback.invoke(new Exception("tts not init"), "");
        } else {
            Observable.just(text).map(new Function<T, R>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$generateVoiceTts$p$1
                @Override // io.reactivex.functions.Function
                public final String apply(String it) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    String md5 = MD5Util.toMD5(it);
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3273d(str, "gen md5 name = " + md5);
                    return md5;
                }
            }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$generateVoiceTts$p$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(String it) {
                    PdTtsSdk pdTtsSdk = PdTtsSdk.INSTANCE;
                    String str = text;
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    Context context = RobotContext.INSTANCE.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    PdTtsSdk.startTts$default(pdTtsSdk, str, ttsVoiceHelper.getFilePath(context, it, ttsVoiceType), new OnTtsListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$generateVoiceTts$p$2.1
                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onProgress(int proses) {
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onComplete(String filePath) {
                            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                            callback.invoke(null, filePath);
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onError(int code, String msg) {
                            String str2;
                            Intrinsics.checkParameterIsNotNull(msg, "msg");
                            TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                            str2 = TtsVoiceHelper.TAG;
                            Pdlog.m3274e(str2, "addNewTtsVoice onError , " + code + " , " + msg);
                            callback.invoke(new Exception(msg), "");
                        }
                    }, null, 8, null);
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$generateVoiceTts$p$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str;
                    String str2;
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str, "md5 error ??");
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    str2 = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str2, Log.getStackTraceString(th));
                    Function2.this.invoke(th, "");
                }
            });
        }
    }

    public static /* synthetic */ void addConfigItem$default(TtsVoiceHelper ttsVoiceHelper, String str, String str2, String str3, TtsVoiceType ttsVoiceType, Integer num, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            num = (Integer) null;
        }
        Integer num2 = num;
        if ((i & 32) != 0) {
            z = false;
        }
        ttsVoiceHelper.addConfigItem(str, str2, str3, ttsVoiceType, num2, z);
    }

    public final void addConfigItem(String filePath, String it, String text, TtsVoiceType ttsVoiceType, Integer index, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(it, "it");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        TtsConfigData ttsConfigData = new TtsConfigData(filePath, it, text, isSelect, false, currentLocale, 16, null);
        Pdlog.m3274e(TAG, ttsConfigData);
        int i = WhenMappings.$EnumSwitchMapping$0[ttsVoiceType.ordinal()];
        if (i == 1) {
            ArrayList<TtsConfigData> arrayList = cruiseConfigDateList;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (Intrinsics.areEqual(ttsConfigData.getName(), ((TtsConfigData) obj).getName())) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty()) {
                return;
            }
            cruiseAllLanguageList.add(ttsConfigData);
            if (index == null) {
                cruiseConfigDateList.add(ttsConfigData);
            } else if (Intrinsics.compare(cruiseConfigDateList.size(), index.intValue()) <= 0) {
                cruiseConfigDateList.add(ttsConfigData);
            } else {
                cruiseConfigDateList.add(index.intValue(), ttsConfigData);
            }
        } else if (i == 2) {
            ArrayList<TtsConfigData> arrayList3 = deliverConfigDateList;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : arrayList3) {
                if (Intrinsics.areEqual(ttsConfigData.getName(), ((TtsConfigData) obj2).getName())) {
                    arrayList4.add(obj2);
                }
            }
            if (!arrayList4.isEmpty()) {
                return;
            }
            deliverAllLanguageList.add(ttsConfigData);
            if (index == null) {
                deliverConfigDateList.add(ttsConfigData);
            } else if (Intrinsics.compare(deliverConfigDateList.size(), index.intValue()) <= 0) {
                deliverConfigDateList.add(ttsConfigData);
            } else {
                deliverConfigDateList.add(index.intValue(), ttsConfigData);
            }
        } else if (i == 3) {
            ArrayList<TtsConfigData> arrayList5 = cruiseConfigDateList;
            ArrayList arrayList6 = new ArrayList();
            for (Object obj3 : arrayList5) {
                if (Intrinsics.areEqual(ttsConfigData.getName(), ((TtsConfigData) obj3).getName())) {
                    arrayList6.add(obj3);
                }
            }
            if (!arrayList6.isEmpty()) {
                return;
            }
            cruiseAllLanguageList.add(ttsConfigData);
            if (index == null) {
                cruiseConfigDateList.add(ttsConfigData);
            } else if (Intrinsics.compare(cruiseConfigDateList.size(), index.intValue()) <= 0) {
                cruiseConfigDateList.add(ttsConfigData);
            } else {
                cruiseConfigDateList.add(index.intValue(), ttsConfigData);
            }
        } else if (i == 4) {
            ArrayList<TtsConfigData> arrayList7 = usherConfigDateList;
            ArrayList arrayList8 = new ArrayList();
            for (Object obj4 : arrayList7) {
                if (Intrinsics.areEqual(ttsConfigData.getName(), ((TtsConfigData) obj4).getName())) {
                    arrayList8.add(obj4);
                }
            }
            if (!arrayList8.isEmpty()) {
                return;
            }
            usherAllLanguageList.add(ttsConfigData);
            if (index == null) {
                usherConfigDateList.add(ttsConfigData);
            } else if (Intrinsics.compare(usherConfigDateList.size(), index.intValue()) <= 0) {
                usherConfigDateList.add(ttsConfigData);
            } else {
                usherConfigDateList.add(index.intValue(), ttsConfigData);
            }
        }
        checkSelect(ttsVoiceType);
        saveConfig(ttsVoiceType);
    }

    private final void checkSelect(TtsVoiceType ttsVoiceType) {
        int i = WhenMappings.$EnumSwitchMapping$1[ttsVoiceType.ordinal()];
        if (i == 1 || i == 2) {
            if (!cruiseConfigDateList.isEmpty()) {
                ArrayList<TtsConfigData> arrayList = cruiseConfigDateList;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (((TtsConfigData) obj).isSelect()) {
                        arrayList2.add(obj);
                    }
                }
                if (arrayList2.isEmpty()) {
                    cruiseConfigDateList.get(0).setSelect(true);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 3) {
            if (!deliverConfigDateList.isEmpty()) {
                ArrayList<TtsConfigData> arrayList3 = deliverConfigDateList;
                ArrayList arrayList4 = new ArrayList();
                for (Object obj2 : arrayList3) {
                    if (((TtsConfigData) obj2).isSelect()) {
                        arrayList4.add(obj2);
                    }
                }
                if (arrayList4.isEmpty()) {
                    deliverConfigDateList.get(0).setSelect(true);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 4 && (!usherConfigDateList.isEmpty())) {
            ArrayList<TtsConfigData> arrayList5 = usherConfigDateList;
            ArrayList arrayList6 = new ArrayList();
            for (Object obj3 : arrayList5) {
                if (((TtsConfigData) obj3).isSelect()) {
                    arrayList6.add(obj3);
                }
            }
            if (arrayList6.isEmpty()) {
                usherConfigDateList.get(0).setSelect(true);
            }
        }
    }

    public final boolean hasSelect(TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$2[ttsVoiceType.ordinal()];
        if (i == 1 || i == 2) {
            if (!(!cruiseConfigDateList.isEmpty())) {
                return false;
            }
            ArrayList<TtsConfigData> arrayList = cruiseConfigDateList;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((TtsConfigData) obj).isSelect()) {
                    arrayList2.add(obj);
                }
            }
            if (arrayList2.isEmpty()) {
                return false;
            }
        } else if (i == 3) {
            if (!(!deliverConfigDateList.isEmpty())) {
                return false;
            }
            ArrayList<TtsConfigData> arrayList3 = deliverConfigDateList;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : arrayList3) {
                if (((TtsConfigData) obj2).isSelect()) {
                    arrayList4.add(obj2);
                }
            }
            if (arrayList4.isEmpty()) {
                return false;
            }
        } else if (i == 4) {
            if (!(!usherConfigDateList.isEmpty())) {
                return false;
            }
            ArrayList<TtsConfigData> arrayList5 = usherConfigDateList;
            ArrayList arrayList6 = new ArrayList();
            for (Object obj3 : arrayList5) {
                if (((TtsConfigData) obj3).isSelect()) {
                    arrayList6.add(obj3);
                }
            }
            if (arrayList6.isEmpty()) {
                return false;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return true;
    }

    public final void deleteConfig(final TtsConfigData configData, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Pdlog.m3273d(TAG, "deleteConfig " + configData);
        int i = WhenMappings.$EnumSwitchMapping$3[ttsVoiceType.ordinal()];
        if (i == 1 || i == 2) {
            cruiseConfigDateList.remove(configData);
            cruiseAllLanguageList.remove(configData);
        } else if (i == 3) {
            deliverConfigDateList.remove(configData);
            deliverAllLanguageList.remove(configData);
        } else if (i == 4) {
            usherConfigDateList.remove(configData);
            usherAllLanguageList.remove(configData);
        }
        checkSelect(ttsVoiceType);
        ThreadPoolManager.getInstance().execSimpleTask(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper$deleteConfig$1
            @Override // java.lang.Runnable
            public final void run() {
                FileUtils.delete(TtsVoiceHelper.TtsConfigData.this.getPath());
            }
        });
        notifyChange(ttsVoiceType);
        saveConfig(ttsVoiceType);
    }

    private final void notifyChange(TtsVoiceType ttsVoiceType) {
        Function0<Unit> function0;
        int i = WhenMappings.$EnumSwitchMapping$4[ttsVoiceType.ordinal()];
        if (i == 1) {
            Function0<Unit> function02 = onCruiseConfigChangeListener;
            if (function02 != null) {
                function02.invoke();
                return;
            }
            return;
        }
        if (i == 2) {
            Function0<Unit> function03 = onDeliverConfigChangeListener;
            if (function03 != null) {
                function03.invoke();
                return;
            }
            return;
        }
        if (i != 3) {
            if (i == 4 && (function0 = onUsherConfigChangeListener) != null) {
                function0.invoke();
                return;
            }
            return;
        }
        Function0<Unit> function04 = onSolicitConfigChangeListener;
        if (function04 != null) {
            function04.invoke();
        }
    }

    public final void saveConfig(TtsVoiceType ttsVoiceType) {
        String json;
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        try {
            int i = WhenMappings.$EnumSwitchMapping$5[ttsVoiceType.ordinal()];
            if (i == 1 || i == 2) {
                json = gson.toJson(cruiseAllLanguageList);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(cruiseAllLanguageList)");
            } else if (i == 3) {
                json = gson.toJson(deliverAllLanguageList);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(deliverAllLanguageList)");
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                json = gson.toJson(usherAllLanguageList);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(usherAllLanguageList)");
            }
            Pdlog.m3273d(TAG, "saveConfig type = " + ttsVoiceType + ' ' + json);
            SpUtils.set(RobotContext.INSTANCE.getContext(), getSaveSpKey(ttsVoiceType), json);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "saveConfig error");
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    private final String getSaveSpKey(TtsVoiceType ttsVoiceType) {
        int i = WhenMappings.$EnumSwitchMapping$6[ttsVoiceType.ordinal()];
        if (i == 1) {
            return "key_cruise_tts_config";
        }
        if (i == 2) {
            return "key_deliver_tts_config";
        }
        if (i == 3) {
            return "key_cruise_tts_config";
        }
        if (i == 4) {
            return Constans.KEY_USHER_TTS_CONFIG;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void genTtsVoice(String text, String path, OnTtsListener onTtsListener) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            onTtsListener.onError(-1, "sdk not init");
        } else {
            PdTtsSdk.startTts$default(PdTtsSdk.INSTANCE, text, path, onTtsListener, null, 8, null);
        }
    }

    public final boolean checkTtsExist(String text, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$7[ttsVoiceType.ordinal()];
        boolean z = false;
        if (i == 1 || i == 2) {
            Iterator<T> it = cruiseConfigDateList.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((TtsConfigData) it.next()).getName(), text)) {
                    z = true;
                }
            }
        } else if (i == 3) {
            Iterator<T> it2 = deliverConfigDateList.iterator();
            while (it2.hasNext()) {
                if (Intrinsics.areEqual(((TtsConfigData) it2.next()).getName(), text)) {
                    z = true;
                }
            }
        } else if (i == 4) {
            Iterator<T> it3 = usherConfigDateList.iterator();
            while (it3.hasNext()) {
                if (Intrinsics.areEqual(((TtsConfigData) it3.next()).getName(), text)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final TtsVoiceOpenType checkTtsOpenType(Context context, TtsVoiceType ttsVoiceType) {
        int i;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i2 = WhenMappings.$EnumSwitchMapping$8[ttsVoiceType.ordinal()];
        if (i2 == 1) {
            i = SpUtils.get(context, "key_cruise_tts_type", 0);
        } else if (i2 == 2) {
            i = SpUtils.get(context, "key_deliver_tts_type", 0);
        } else if (i2 == 3) {
            i = SpUtils.get(context, Constans.KEY_SOLICIT_TTS_TYPE, 0);
        } else {
            if (i2 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            i = SpUtils.get(context, Constans.KEY_USHER_TTS_TYPE, 0);
        }
        if (i == TtsVoiceOpenType.CLOSE.getType()) {
            return TtsVoiceOpenType.CLOSE;
        }
        boolean isZh = com.pudutech.peanut.robot_ui.util.LanguageUtils.INSTANCE.isZh(context);
        boolean isEnglish = com.pudutech.peanut.robot_ui.util.LanguageUtils.INSTANCE.isEnglish(context);
        if (!isZh && !isEnglish) {
            Pdlog.m3273d(TAG, "checkCruiseTtsOpenType not is zh language");
            return TtsVoiceOpenType.CLOSE;
        }
        if (!getTtsConfigList$default(this, ttsVoiceType, false, 2, null).isEmpty()) {
            return i == TtsVoiceOpenType.OPEN.getType() ? TtsVoiceOpenType.OPEN : TtsVoiceOpenType.CLOSE;
        }
        Pdlog.m3274e(TAG, "cruiseConfigDateList is empty ????");
        setTtsType(context, TtsVoiceOpenType.CLOSE, ttsVoiceType);
        return TtsVoiceOpenType.CLOSE;
    }

    public final boolean isOpen(Context context, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        return checkTtsOpenType(context, ttsVoiceType) == TtsVoiceOpenType.OPEN;
    }

    private final String getCruiseFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getCruiseFilePath " + name);
        return getPkPath(context) + "/tts/" + name + com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper.FLIE_MARK;
    }

    public final String getFilePath(Context context, String name, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$9[ttsVoiceType.ordinal()];
        if (i == 1) {
            return getCruiseFilePath(context, name);
        }
        if (i == 2) {
            return getDeliverFilePath(context, name);
        }
        if (i == 3) {
            return getSolicitFilePath(context, name);
        }
        if (i == 4) {
            return getUsherFilePath(context, name);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String getDeliverFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        return getPkPath(context) + "/tts_deliver/" + name + com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper.FLIE_MARK;
    }

    private final String getSolicitFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getSolicitFilePath " + name);
        return getPkPath(context) + "/tts_solicit/" + name + com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper.FLIE_MARK;
    }

    private final String getUsherFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getUsherFilePath " + name);
        return getPkPath(context) + "/tts_usher/" + name + com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper.FLIE_MARK;
    }

    public final String getCruiseTempFilePath(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append("/tts/temp_cruise.pcm");
        return sb.toString();
    }

    private final String getPkPath(Context context) {
        String language = new LanguageUtils(context).getCurrent().getLocale().getLanguage();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        return (((filesDir.getAbsolutePath() + File.separator) + language) + File.separator) + TtsConfig.INSTANCE.getIFLY_SPEECH_VOICE_NAME();
    }

    public static /* synthetic */ List getTtsConfigList$default(TtsVoiceHelper ttsVoiceHelper, TtsVoiceType ttsVoiceType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return ttsVoiceHelper.getTtsConfigList(ttsVoiceType, z);
    }

    public final List<TtsConfigData> getTtsConfigList(TtsVoiceType ttsVoiceType, boolean checkFile) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$10[ttsVoiceType.ordinal()];
        if (i == 1 || i == 2) {
            if (checkFile) {
                ArrayList<TtsConfigData> arrayList = cruiseConfigDateList;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (new File(((TtsConfigData) obj).getPath()).exists()) {
                        arrayList2.add(obj);
                    }
                }
                return arrayList2;
            }
            return cruiseConfigDateList;
        }
        if (i == 3) {
            if (checkFile) {
                ArrayList<TtsConfigData> arrayList3 = deliverConfigDateList;
                ArrayList arrayList4 = new ArrayList();
                for (Object obj2 : arrayList3) {
                    if (new File(((TtsConfigData) obj2).getPath()).exists()) {
                        arrayList4.add(obj2);
                    }
                }
                return arrayList4;
            }
            return deliverConfigDateList;
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        if (checkFile) {
            ArrayList<TtsConfigData> arrayList5 = usherConfigDateList;
            ArrayList arrayList6 = new ArrayList();
            for (Object obj3 : arrayList5) {
                if (new File(((TtsConfigData) obj3).getPath()).exists()) {
                    arrayList6.add(obj3);
                }
            }
            return arrayList6;
        }
        return usherConfigDateList;
    }

    public final void removeAllList() {
        cruiseConfigDateList.clear();
    }

    public final void setTtsType(Context context, TtsVoiceOpenType ttsVoiceOpenType, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceOpenType, "ttsVoiceOpenType");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$11[ttsVoiceType.ordinal()];
        if (i == 1) {
            SpUtils.set(context, "key_cruise_tts_type", ttsVoiceOpenType.getType());
            return;
        }
        if (i == 2) {
            SpUtils.set(context, "key_deliver_tts_type", ttsVoiceOpenType.getType());
        } else if (i == 3) {
            SpUtils.set(context, Constans.KEY_SOLICIT_TTS_TYPE, ttsVoiceOpenType.getType());
        } else {
            if (i != 4) {
                return;
            }
            SpUtils.set(context, Constans.KEY_USHER_TTS_TYPE, ttsVoiceOpenType.getType());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, TtsVoiceType ttsVoiceType, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.playTtsVoice(ttsVoiceType, function1);
    }

    public final void playTtsVoice(TtsVoiceType ttsVoiceType, Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        List ttsConfigList$default = getTtsConfigList$default(this, ttsVoiceType, false, 2, null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : ttsConfigList$default) {
            if (((TtsConfigData) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        Pdlog.m3273d(TAG, "playTtsVoice " + arrayList2);
        if (!arrayList2.isEmpty()) {
            TtsConfigData ttsConfigData = (TtsConfigData) arrayList2.get(Random.INSTANCE.nextInt(arrayList2.size()));
            Pdlog.m3273d(TAG, "playTtsVoice " + ttsConfigData);
            PdTtsSdk.INSTANCE.playTtsPcmFile(ttsConfigData.getPath(), listener);
            return;
        }
        Pdlog.m3274e(TAG, "playTtsVoice , select is null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playPcm$default(TtsVoiceHelper ttsVoiceHelper, TtsConfigData ttsConfigData, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.playPcm(ttsConfigData, function1);
    }

    public final void playPcm(TtsConfigData configData, Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Pdlog.m3273d(TAG, "playPcm " + configData);
        PdTtsSdk.INSTANCE.playTtsPcmFile(configData.getPath(), listener);
    }

    public final void playTempCruiseTts() {
        Pdlog.m3273d(TAG, "playTempCruiseTts");
        PdTtsSdk.playTtsPcmFile$default(PdTtsSdk.INSTANCE, getCruiseTempFilePath(RobotContext.INSTANCE.getContext()), null, 2, null);
    }

    public final void stopCruiseTts() {
        Pdlog.m3273d(TAG, "stopCruiseTts");
        PdTtsSdk.INSTANCE.stopTtsPcmFile();
    }

    public final void changeChoose(TtsConfigData item, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        int i = WhenMappings.$EnumSwitchMapping$12[ttsVoiceType.ordinal()];
        if (i == 1) {
            item.setSelect(!item.isSelect());
        } else if (i == 2) {
            for (TtsConfigData ttsConfigData : getTtsConfigList$default(this, ttsVoiceType, false, 2, null)) {
                ttsConfigData.setSelect(Intrinsics.areEqual(ttsConfigData, item));
            }
        } else if (i == 3) {
            item.setSelect(!item.isSelect());
        } else if (i == 4) {
            for (TtsConfigData ttsConfigData2 : getTtsConfigList$default(this, ttsVoiceType, false, 2, null)) {
                ttsConfigData2.setSelect(Intrinsics.areEqual(ttsConfigData2, item));
            }
        }
        if (!item.isSelect()) {
            List ttsConfigList$default = getTtsConfigList$default(this, ttsVoiceType, false, 2, null);
            ArrayList arrayList = new ArrayList();
            for (Object obj : ttsConfigList$default) {
                if (((TtsConfigData) obj).isSelect()) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                item.setSelect(true);
            }
        }
        saveConfig(ttsVoiceType);
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\f\"\u0004\b\u000f\u0010\u000eR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017¨\u0006("}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "", "path", "", "md5", "name", "isSelect", "", "isPlay", "languageType", "Ljava/util/Locale;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Locale;)V", "()Z", "setPlay", "(Z)V", "setSelect", "getLanguageType", "()Ljava/util/Locale;", "setLanguageType", "(Ljava/util/Locale;)V", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getName", "setName", "getPath", "setPath", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class TtsConfigData {
        private boolean isPlay;
        private boolean isSelect;
        private Locale languageType;
        private String md5;
        private String name;
        private String path;

        public static /* synthetic */ TtsConfigData copy$default(TtsConfigData ttsConfigData, String str, String str2, String str3, boolean z, boolean z2, Locale locale, int i, Object obj) {
            if ((i & 1) != 0) {
                str = ttsConfigData.path;
            }
            if ((i & 2) != 0) {
                str2 = ttsConfigData.md5;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = ttsConfigData.name;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                z = ttsConfigData.isSelect;
            }
            boolean z3 = z;
            if ((i & 16) != 0) {
                z2 = ttsConfigData.isPlay;
            }
            boolean z4 = z2;
            if ((i & 32) != 0) {
                locale = ttsConfigData.languageType;
            }
            return ttsConfigData.copy(str, str4, str5, z3, z4, locale);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMd5() {
            return this.md5;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsSelect() {
            return this.isSelect;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsPlay() {
            return this.isPlay;
        }

        /* renamed from: component6, reason: from getter */
        public final Locale getLanguageType() {
            return this.languageType;
        }

        public final TtsConfigData copy(String path, String md5, String name, boolean isSelect, boolean isPlay, Locale languageType) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(languageType, "languageType");
            return new TtsConfigData(path, md5, name, isSelect, isPlay, languageType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TtsConfigData)) {
                return false;
            }
            TtsConfigData ttsConfigData = (TtsConfigData) other;
            return Intrinsics.areEqual(this.path, ttsConfigData.path) && Intrinsics.areEqual(this.md5, ttsConfigData.md5) && Intrinsics.areEqual(this.name, ttsConfigData.name) && this.isSelect == ttsConfigData.isSelect && this.isPlay == ttsConfigData.isPlay && Intrinsics.areEqual(this.languageType, ttsConfigData.languageType);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.path;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.md5;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.name;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            boolean z = this.isSelect;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode3 + i) * 31;
            boolean z2 = this.isPlay;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            Locale locale = this.languageType;
            return i4 + (locale != null ? locale.hashCode() : 0);
        }

        public String toString() {
            return "TtsConfigData(path=" + this.path + ", md5=" + this.md5 + ", name=" + this.name + ", isSelect=" + this.isSelect + ", isPlay=" + this.isPlay + ", languageType=" + this.languageType + ")";
        }

        public TtsConfigData(String path, String md5, String name, boolean z, boolean z2, Locale languageType) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(languageType, "languageType");
            this.path = path;
            this.md5 = md5;
            this.name = name;
            this.isSelect = z;
            this.isPlay = z2;
            this.languageType = languageType;
        }

        public final String getPath() {
            return this.path;
        }

        public final void setPath(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.path = str;
        }

        public final String getMd5() {
            return this.md5;
        }

        public final void setMd5(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.md5 = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final boolean isSelect() {
            return this.isSelect;
        }

        public final void setSelect(boolean z) {
            this.isSelect = z;
        }

        public /* synthetic */ TtsConfigData(String str, String str2, String str3, boolean z, boolean z2, Locale locale, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, z, (i & 16) != 0 ? false : z2, locale);
        }

        public final boolean isPlay() {
            return this.isPlay;
        }

        public final void setPlay(boolean z) {
            this.isPlay = z;
        }

        public final Locale getLanguageType() {
            return this.languageType;
        }

        public final void setLanguageType(Locale locale) {
            Intrinsics.checkParameterIsNotNull(locale, "<set-?>");
            this.languageType = locale;
        }
    }
}

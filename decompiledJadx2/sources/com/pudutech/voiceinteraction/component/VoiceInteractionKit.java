package com.pudutech.voiceinteraction.component;

import android.content.Context;
import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.mic_array.mic.MicArray;
import com.pudutech.voiceinteraction.component.cmd.IntentObjectType;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.NLPActionType;
import com.pudutech.voiceinteraction.component.config.UserInfo;
import com.pudutech.voiceinteraction.component.dialogflow.p068v3.DialogflowV3VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit;
import com.pudutech.voiceinteraction.component.interf.IVoiceInteraction;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.utils.FileUtil;
import java.io.File;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: VoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 L2\u00020\u0001:\u0001LB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u001e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bJ\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\u001a\u001a\u00020\u001bJ\n\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u0002J8\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\bJ\b\u0010\u001f\u001a\u00020\bH\u0002J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\bJ\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u000bJ\u0006\u0010$\u001a\u00020\u0013J\u0006\u0010%\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020\u0013J.\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020-J&\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u0002002\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u000b02j\b\u0012\u0004\u0012\u00020\u000b`3J\u000e\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020-J\u000e\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020-J\u0016\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bJ\u000e\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u000bJ\u0016\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u000bJ\u0016\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020-2\u0006\u0010A\u001a\u00020\u000bJ\u0006\u0010B\u001a\u00020\u0013J\u000e\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020\u000bJ\u0006\u0010E\u001a\u00020\u0013J\u000e\u0010F\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020IJ\u0016\u0010J\u001a\u00020\u00132\u0006\u0010K\u001a\u00020-2\u0006\u00107\u001a\u00020-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/VoiceInteractionKit;", "", "()V", "context", "Landroid/content/Context;", "currentLanguage", "Lcom/pudutech/voiceinteraction/component/config/Language;", "ifOnlyWake", "", "language", "macStr", "", "onVoiceInteractionListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "pid", SpeechConstant.ISV_VID, "voiceInteractionKit", "Lcom/pudutech/voiceinteraction/component/interf/IVoiceInteraction;", "cancelCurrentRound", "", "cancelTTS", "checkState", "copyWakeupWordsToSDCard", "folderPath", "fileName", "getLanguage", "getNLPActionType", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "getWIFIMac", "init", "onlyWake", "initRecoding", "onlyWakeup", "b", "postAction", "actionString", "reConnectAIUI", "release", "resetCAE", "setData", "map_name", "config_id", "worker_mode", "shop_id", "state", "", "setIntentObject", "type", "Lcom/pudutech/voiceinteraction/component/cmd/IntentObjectType;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setMode", "mode", "setRealBeam", "beam", "setRobotInfo", "productType", "editionStr", "setSessionId", "sessionId", "setSpeaker", "speaker", "setTinyMap", TransferTable.COLUMN_KEY, ES6Iterator.VALUE_PROPERTY, "startRecording", "startTts", "text", "stopRecording", "switchLanguage", "updateUsetInfo", "userInfo", "Lcom/pudutech/voiceinteraction/component/config/UserInfo;", "wakeup", "angle", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractionKit {
    private static final String TAG = "VoiceInteractionKit";
    private static final String WAKEUP_WORDS_FOLDER_PATH = "/sdcard/pudu/wakeupwords";
    private static int currentMode;
    private static MicArray micArray;
    private static int type;
    private Context context;
    private Language currentLanguage;
    private boolean ifOnlyWake;
    private Language language;
    private String macStr;
    private IVoiceInteractionListener onVoiceInteractionListener;
    private String pid;
    private String vid;
    private IVoiceInteraction voiceInteractionKit;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(new Function0<VoiceInteractionKit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionKit$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VoiceInteractionKit invoke() {
            return new VoiceInteractionKit(null);
        }
    });
    private static volatile String product_type = "KettyBot";
    private static String edition = "9.12.0.0";
    private static String wokerMode = "tout";
    private static String configId = "3,4,5,10";
    private static String shopId = "14950";
    private static String mapName = "";
    private static String session_id = "";
    private static String languageStr = "zh-CN";
    private static Integer initCode = -1;
    private static String DEFAULT_RES_PATH = "res_path=";
    private static HashMap<Integer, String> tinyMap = MapsKt.hashMapOf(TuplesKt.m3968to(0, "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2"), TuplesKt.m3968to(1, "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2"), TuplesKt.m3968to(2, "tinymix -D 2 3 -2 -2 -2 -2 -2 -2 -2 -2"));

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Language.values().length];

        static {
            $EnumSwitchMapping$0[Language.Chinese.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[Language.values().length];
            $EnumSwitchMapping$1[Language.Chinese.ordinal()] = 1;
            $EnumSwitchMapping$1[Language.English.ordinal()] = 2;
            $EnumSwitchMapping$1[Language.Japanese.ordinal()] = 3;
            $EnumSwitchMapping$1[Language.ChineseHk.ordinal()] = 4;
            $EnumSwitchMapping$1[Language.Korean.ordinal()] = 5;
            $EnumSwitchMapping$1[Language.Dutch.ordinal()] = 6;
            $EnumSwitchMapping$1[Language.Spanish.ordinal()] = 7;
            $EnumSwitchMapping$1[Language.French.ordinal()] = 8;
            $EnumSwitchMapping$1[Language.Italian.ordinal()] = 9;
            $EnumSwitchMapping$1[Language.Russian.ordinal()] = 10;
            $EnumSwitchMapping$1[Language.German.ordinal()] = 11;
            $EnumSwitchMapping$1[Language.EuropeanPortuguese.ordinal()] = 12;
            $EnumSwitchMapping$1[Language.PortugueseBr.ordinal()] = 13;
            $EnumSwitchMapping$1[Language.Brazilian.ordinal()] = 14;
            $EnumSwitchMapping$1[Language.Portuguese.ordinal()] = 15;
            $EnumSwitchMapping$1[Language.Poland.ordinal()] = 16;
            $EnumSwitchMapping$1[Language.Czech.ordinal()] = 17;
            $EnumSwitchMapping$1[Language.Turkish.ordinal()] = 18;
            $EnumSwitchMapping$1[Language.Arabic.ordinal()] = 19;
            $EnumSwitchMapping$1[Language.Thai.ordinal()] = 20;
            $EnumSwitchMapping$1[Language.Indonesia.ordinal()] = 21;
            $EnumSwitchMapping$1[Language.Sweden.ordinal()] = 22;
        }
    }

    private VoiceInteractionKit() {
        this.pid = "";
        this.vid = "";
    }

    public /* synthetic */ VoiceInteractionKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: VoiceInteractionKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000f\"\u0004\b/\u0010\u0011R\u001a\u00100\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011R\u001a\u00103\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000f\"\u0004\b5\u0010\u0011R6\u00106\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000407j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0004`8X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010=\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0015\"\u0004\b?\u0010\u0017R\u001a\u0010@\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u000f\"\u0004\bB\u0010\u0011¨\u0006C"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/VoiceInteractionKit$Companion;", "", "()V", "DEFAULT_RES_PATH", "", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/VoiceInteractionKit;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/VoiceInteractionKit;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "WAKEUP_WORDS_FOLDER_PATH", "configId", "getConfigId", "()Ljava/lang/String;", "setConfigId", "(Ljava/lang/String;)V", "currentMode", "", "getCurrentMode", "()I", "setCurrentMode", "(I)V", "edition", "getEdition", "setEdition", "initCode", "getInitCode", "()Ljava/lang/Integer;", "setInitCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "languageStr", "getLanguageStr", "setLanguageStr", "mapName", "getMapName", "setMapName", "micArray", "Lcom/pudutech/mic_array/mic/MicArray;", "getMicArray", "()Lcom/pudutech/mic_array/mic/MicArray;", "setMicArray", "(Lcom/pudutech/mic_array/mic/MicArray;)V", "product_type", "getProduct_type", "setProduct_type", SpeechEvent.KEY_EVENT_SESSION_ID, "getSession_id", "setSession_id", "shopId", "getShopId", "setShopId", "tinyMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getTinyMap", "()Ljava/util/HashMap;", "setTinyMap", "(Ljava/util/HashMap;)V", "type", "getType", "setType", "wokerMode", "getWokerMode", "setWokerMode", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final VoiceInteractionKit getINSTANCE() {
            Lazy lazy = VoiceInteractionKit.INSTANCE$delegate;
            Companion companion = VoiceInteractionKit.INSTANCE;
            return (VoiceInteractionKit) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getProduct_type() {
            return VoiceInteractionKit.product_type;
        }

        public final void setProduct_type(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.product_type = str;
        }

        public final String getEdition() {
            return VoiceInteractionKit.edition;
        }

        public final void setEdition(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.edition = str;
        }

        public final String getWokerMode() {
            return VoiceInteractionKit.wokerMode;
        }

        public final void setWokerMode(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.wokerMode = str;
        }

        public final String getConfigId() {
            return VoiceInteractionKit.configId;
        }

        public final void setConfigId(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.configId = str;
        }

        public final String getShopId() {
            return VoiceInteractionKit.shopId;
        }

        public final void setShopId(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.shopId = str;
        }

        public final String getMapName() {
            return VoiceInteractionKit.mapName;
        }

        public final void setMapName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.mapName = str;
        }

        public final int getType() {
            return VoiceInteractionKit.type;
        }

        public final void setType(int i) {
            VoiceInteractionKit.type = i;
        }

        public final String getSession_id() {
            return VoiceInteractionKit.session_id;
        }

        public final void setSession_id(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.session_id = str;
        }

        public final String getLanguageStr() {
            return VoiceInteractionKit.languageStr;
        }

        public final void setLanguageStr(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            VoiceInteractionKit.languageStr = str;
        }

        public final MicArray getMicArray() {
            return VoiceInteractionKit.micArray;
        }

        public final void setMicArray(MicArray micArray) {
            VoiceInteractionKit.micArray = micArray;
        }

        public final Integer getInitCode() {
            return VoiceInteractionKit.initCode;
        }

        public final void setInitCode(Integer num) {
            VoiceInteractionKit.initCode = num;
        }

        public final int getCurrentMode() {
            return VoiceInteractionKit.currentMode;
        }

        public final void setCurrentMode(int i) {
            VoiceInteractionKit.currentMode = i;
        }

        public final HashMap<Integer, String> getTinyMap() {
            return VoiceInteractionKit.tinyMap;
        }

        public final void setTinyMap(HashMap<Integer, String> hashMap) {
            Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
            VoiceInteractionKit.tinyMap = hashMap;
        }
    }

    public final boolean init(Context context, Language language, IVoiceInteractionListener onVoiceInteractionListener, String pid, String vid, boolean onlyWake) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Pdlog.m3273d(TAG, "init() context = " + context + ", language = " + language + ", onVoiceInteractionListener = " + onVoiceInteractionListener + " onlyWake=" + onlyWake);
        this.context = context;
        this.onVoiceInteractionListener = onVoiceInteractionListener;
        this.pid = pid;
        this.vid = vid;
        this.ifOnlyWake = onlyWake;
        if (micArray == null) {
            micArray = new MicArray(context);
        }
        this.language = language;
        return initRecoding();
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e4, code lost:
    
        if (r1.intValue() != 0) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d1 A[Catch: Exception -> 0x018b, TryCatch #0 {Exception -> 0x018b, blocks: (B:8:0x0019, B:11:0x0041, B:14:0x0047, B:15:0x004a, B:17:0x0057, B:18:0x005a, B:19:0x0064, B:21:0x0069, B:24:0x0172, B:26:0x00ae, B:28:0x00b4, B:30:0x00c5, B:35:0x00d1, B:37:0x00db, B:40:0x00e6, B:42:0x00f6, B:43:0x00f9, B:45:0x0102, B:46:0x0135, B:47:0x0137, B:50:0x0157, B:52:0x013c, B:54:0x0142, B:56:0x014f, B:57:0x0152, B:59:0x00e0), top: B:7:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00db A[Catch: Exception -> 0x018b, TryCatch #0 {Exception -> 0x018b, blocks: (B:8:0x0019, B:11:0x0041, B:14:0x0047, B:15:0x004a, B:17:0x0057, B:18:0x005a, B:19:0x0064, B:21:0x0069, B:24:0x0172, B:26:0x00ae, B:28:0x00b4, B:30:0x00c5, B:35:0x00d1, B:37:0x00db, B:40:0x00e6, B:42:0x00f6, B:43:0x00f9, B:45:0x0102, B:46:0x0135, B:47:0x0137, B:50:0x0157, B:52:0x013c, B:54:0x0142, B:56:0x014f, B:57:0x0152, B:59:0x00e0), top: B:7:0x0019 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean initRecoding() {
        Integer num;
        boolean z;
        Pdlog.m3273d(TAG, "begin initRecoding");
        int recodeType = VoiceCommentConfig.INSTANCE.getRecodeType();
        if (recodeType == 1) {
            Language language = this.language;
            if (language == null) {
                Intrinsics.throwNpe();
            }
            return switchLanguage(language);
        }
        if (recodeType != 2) {
            return true;
        }
        try {
            Pdlog.m3273d(TAG, "initRecoding VoiceCommentConfig.wakeResIsCopyFinisd:" + VoiceCommentConfig.INSTANCE.getWakeResIsCopyFinisd());
            if (!VoiceCommentConfig.INSTANCE.getWakeResIsCopyFinisd()) {
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
            MicArray micArray2 = micArray;
            Integer num2 = null;
            if (micArray2 != null) {
                num = Integer.valueOf(micArray2.replaceWakeup(((DEFAULT_RES_PATH + WAKEUP_WORDS_FOLDER_PATH) + File.separator) + VoiceCommentConfig.INSTANCE.getCnAceWakeupWorkAssetName()));
            } else {
                num = null;
            }
            if (num != null && num.intValue() == 0) {
                Pdlog.m3273d(TAG, "replaceWakeup success");
                String wIFIMac = getWIFIMac();
                if (wIFIMac != null && !StringsKt.isBlank(wIFIMac)) {
                    z = false;
                    if (!z) {
                        Pdlog.m3273d(TAG, "fail to get mac from the robot ,please connect network and try later");
                        return false;
                    }
                    Integer num3 = initCode;
                    if (num3 != null) {
                    }
                    VoiceCommentConfig voiceCommentConfig = VoiceCommentConfig.INSTANCE;
                    String str = tinyMap.get(Integer.valueOf(currentMode));
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    voiceCommentConfig.setTinymixCmd(str);
                    MicArray micArray3 = micArray;
                    if (micArray3 != null) {
                        num2 = Integer.valueOf(micArray3.initSDK(this.pid, this.vid, VoiceCommentConfig.INSTANCE.getTinymixCmd(), product_type + getWIFIMac(), Boolean.valueOf(Intrinsics.areEqual(product_type, "HolaBot"))));
                    }
                    initCode = num2;
                    Integer num4 = initCode;
                    if (num4 != null && num4.intValue() == 0) {
                        Pdlog.m3273d(TAG, "initSDK success");
                        Language language2 = this.language;
                        if (language2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return switchLanguage(language2);
                    }
                    Pdlog.m3274e(TAG, "initSDK failse  initCode = " + initCode);
                    return false;
                }
                z = true;
                if (!z) {
                }
            }
            Pdlog.m3273d(TAG, "replaceWakeup failse replaceCode = " + num);
            return false;
        } catch (Exception e) {
            Pdlog.m3275i(TAG, "initRecoding Exception: " + Log.getStackTraceString(e));
            return false;
        }
    }

    public final boolean switchLanguage(Language language) {
        IFlyVoiceInteractionKit instance;
        Intrinsics.checkParameterIsNotNull(language, "language");
        Pdlog.m3273d(TAG, "switchLanguage() language = " + language + ", lastLanguage = " + this.currentLanguage);
        if (language == this.currentLanguage) {
            return true;
        }
        try {
            IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
            if (iVoiceInteraction != null) {
                iVoiceInteraction.release();
            }
            if (WhenMappings.$EnumSwitchMapping$0[language.ordinal()] == 1) {
                instance = IFlyVoiceInteractionKit.INSTANCE.getINSTANCE();
            } else {
                instance = DialogflowV3VoiceInteractionKit.INSTANCE.getINSTANCE();
            }
            this.voiceInteractionKit = instance;
            switch (language) {
                case Chinese:
                    languageStr = "zh-CN";
                    break;
                case English:
                    languageStr = "en";
                    break;
                case Japanese:
                    languageStr = "ja";
                    break;
                case ChineseHk:
                    languageStr = "zh-TW";
                    break;
                case Korean:
                    languageStr = "ko";
                    break;
                case Dutch:
                    languageStr = "nl";
                    break;
                case Spanish:
                    languageStr = "es";
                    break;
                case French:
                    languageStr = "fr";
                    break;
                case Italian:
                    languageStr = "it";
                    break;
                case Russian:
                    languageStr = "ru";
                    break;
                case German:
                    languageStr = "de";
                    break;
                case EuropeanPortuguese:
                case PortugueseBr:
                case Brazilian:
                case Portuguese:
                    languageStr = "pt";
                    break;
                case Poland:
                    languageStr = "pl";
                    break;
                case Czech:
                    languageStr = "cs";
                    break;
                case Turkish:
                    languageStr = "tr";
                    break;
                case Arabic:
                    languageStr = ArchiveStreamFactory.f8911AR;
                    break;
                case Thai:
                    languageStr = "th";
                    break;
                case Indonesia:
                    languageStr = "id";
                    break;
                case Sweden:
                    languageStr = "sv";
                    break;
                default:
                    languageStr = "en";
                    break;
            }
            IVoiceInteraction iVoiceInteraction2 = this.voiceInteractionKit;
            if (iVoiceInteraction2 != null) {
                Pdlog.m3273d(TAG, "init() voiceInteractionKit = " + iVoiceInteraction2.getClass().getSimpleName());
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                IVoiceInteractionListener iVoiceInteractionListener = this.onVoiceInteractionListener;
                String str = this.pid;
                String str2 = this.vid;
                MicArray micArray2 = micArray;
                if (micArray2 == null) {
                    Intrinsics.throwNpe();
                }
                boolean init = iVoiceInteraction2.init(context, language, iVoiceInteractionListener, str, str2, micArray2, this.ifOnlyWake);
                Pdlog.m3273d(TAG, "initStatus == " + init);
                if (init) {
                    this.currentLanguage = language;
                }
                return init;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public final void setData(String map_name, String config_id, String worker_mode, String shop_id, int state) {
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(config_id, "config_id");
        Intrinsics.checkParameterIsNotNull(worker_mode, "worker_mode");
        Intrinsics.checkParameterIsNotNull(shop_id, "shop_id");
        configId = config_id;
        wokerMode = worker_mode;
        mapName = map_name;
        shopId = shop_id;
        type = state;
        VoiceCommentConfig.INSTANCE.setMapName(map_name);
        if (Intrinsics.areEqual(worker_mode, WorkMode.Guide.getMode())) {
            VoiceCommentConfig.INSTANCE.setWokerMode(WorkMode.Guide);
        } else if (Intrinsics.areEqual(worker_mode, WorkMode.Delivery.getMode())) {
            VoiceCommentConfig.INSTANCE.setWokerMode(WorkMode.Delivery);
        }
    }

    public final void setRobotInfo(String productType, String editionStr) {
        Intrinsics.checkParameterIsNotNull(productType, "productType");
        Intrinsics.checkParameterIsNotNull(editionStr, "editionStr");
        product_type = productType;
        edition = editionStr;
    }

    public final void setSessionId(String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        session_id = sessionId;
        Pdlog.m3273d(TAG, "setSessionId == " + session_id);
    }

    public final void postAction(String actionString) {
        Intrinsics.checkParameterIsNotNull(actionString, "actionString");
        Pdlog.m3273d(TAG, "postAction() actionString = " + actionString);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.postAction(actionString);
        }
    }

    /* renamed from: getLanguage, reason: from getter */
    public final Language getCurrentLanguage() {
        return this.currentLanguage;
    }

    public final void startRecording() {
        Pdlog.m3273d(TAG, "startRecording() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.startRecording();
        }
    }

    public final void stopRecording() {
        Pdlog.m3273d(TAG, "stopRecording() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.stopRecording();
        }
    }

    public final void reConnectAIUI() {
        Pdlog.m3273d(TAG, "reConnectAIUI() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.reConnectAIUI();
        }
    }

    public final NLPActionType getNLPActionType() {
        Pdlog.m3273d(TAG, "getNLPActionType()");
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            return iVoiceInteraction.getNLPActionType();
        }
        return NLPActionType.OTHER;
    }

    public final void wakeup(int angle, int beam) {
        Pdlog.m3273d(TAG, "wakeup() angle = " + angle + ", beam = " + beam);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.wakeup(angle, beam);
        }
    }

    public final void setRealBeam(int beam) {
        Pdlog.m3273d(TAG, "setRealBeam() beam = " + beam);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.setRealBeam(beam);
        }
    }

    public final void resetCAE() {
        Pdlog.m3273d(TAG, "resetCAE() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.resetCAE();
        }
    }

    public final void release() {
        Pdlog.m3273d(TAG, "release() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.release();
        }
    }

    public final void startTts(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Pdlog.m3273d(TAG, "startTts() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            IVoiceInteraction.DefaultImpls.startTts$default(iVoiceInteraction, text, null, null, null, 14, null);
        }
    }

    public final void setSpeaker(String speaker, String language) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(language, "language");
        Pdlog.m3273d(TAG, "setSpeaker() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.setSpeaker(speaker, language);
        }
    }

    public final void cancelTTS() {
        Pdlog.m3273d(TAG, "cancelTTS() voiceInteractionKit = " + this.voiceInteractionKit);
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.cancelTTS();
        }
    }

    public final void setIntentObject(IntentObjectType type2, ArrayList<String> data) {
        Intrinsics.checkParameterIsNotNull(type2, "type");
        Intrinsics.checkParameterIsNotNull(data, "data");
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.setIntentObject(type2, data);
        }
    }

    public final void updateUsetInfo(UserInfo userInfo) {
        Intrinsics.checkParameterIsNotNull(userInfo, "userInfo");
        VoiceCommentConfig.INSTANCE.setUserInfo(userInfo);
    }

    public final void onlyWakeup(boolean b) {
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.onlyWakeup(b);
        }
    }

    public final void checkState() {
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction instanceof IFlyVoiceInteractionKit) {
            if (iVoiceInteraction == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit");
            }
            ((IFlyVoiceInteractionKit) iVoiceInteraction).checkState();
        }
    }

    public final void cancelCurrentRound() {
        IVoiceInteraction iVoiceInteraction = this.voiceInteractionKit;
        if (iVoiceInteraction != null) {
            iVoiceInteraction.cancelCurrentRound();
        }
    }

    public final void copyWakeupWordsToSDCard(Context context, String folderPath, String fileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        if (FileUtil.INSTANCE.isAssetsFileExists(context, "", fileName)) {
            File file = new File((folderPath + File.separator) + fileName);
            if (file.exists()) {
                file.delete();
            }
            File file2 = new File(folderPath);
            if (!file2.exists() || !file2.isDirectory()) {
                file2.mkdirs();
            }
            FileUtil fileUtil = FileUtil.INSTANCE;
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
            Pdlog.m3273d("IVoiceInteraction", "copyWakeupWordsToSDCard() success = " + fileUtil.copyAssetsFileToSDCard(context, fileName, path));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015 A[Catch: Exception -> 0x00a9, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0018 A[Catch: Exception -> 0x00a9, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0015, B:13:0x0018, B:14:0x0024, B:16:0x002a, B:20:0x0042, B:23:0x0049, B:25:0x0052, B:27:0x0074, B:29:0x007a, B:30:0x0082), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getWIFIMac() {
        boolean z;
        try {
            String str = this.macStr;
            if (str != null && !StringsKt.isBlank(str)) {
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
                        return this.macStr;
                    }
                }
                return null;
            }
            z = true;
            if (z) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void setMode(int mode) {
        currentMode = mode;
        VoiceCommentConfig voiceCommentConfig = VoiceCommentConfig.INSTANCE;
        String str = tinyMap.get(Integer.valueOf(currentMode));
        if (str == null) {
            Intrinsics.throwNpe();
        }
        voiceCommentConfig.setTinymixCmd(str);
        MicArray micArray2 = micArray;
        if (micArray2 != null) {
            micArray2.reInitAlsa(VoiceCommentConfig.INSTANCE.getTinymixCmd());
        }
    }

    public final void setTinyMap(int key, String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        tinyMap.put(Integer.valueOf(key), value);
    }
}

package com.pudutech.voiceinteraction.component.utils;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.gson.JsonObject;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.StringUtils;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdHost;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.ResponseBody;
import org.json.JSONException;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\r-./0123456789B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020#J6\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0004J)\u0010)\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010,R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R|\u0010\u000b\u001ad\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018RN\u0010\u0019\u001a6\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "ttsClick", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "anser", "json", "", "state", "question", "", "getTtsClick", "()Lkotlin/jvm/functions/Function4;", "setTtsClick", "(Lkotlin/jvm/functions/Function4;)V", "ttsError", "Lkotlin/Function2;", SpeechUtility.TAG_RESOURCE_RESULT, "getTtsError", "()Lkotlin/jvm/functions/Function2;", "setTtsError", "(Lkotlin/jvm/functions/Function2;)V", "logPost", "log", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogData;", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogDataD;", "post", "mac", "text", "actionIntent", "pcmName", "postCloudPlatform", "callback", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$HttpCallbackInterface;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$HttpCallbackInterface;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", JsonDocumentFields.ACTION, "AiCloudPlatformReq", "AiCloudSkillReq", "AiVoiceService", "CloudSkillResp", "CloudSkillRespData", "Data", "FaqAnswer", "HttpCallbackInterface", "LogData", "LogDataD", "RasaAnswer", "TestData", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class OkHttpUtils {
    public static final OkHttpUtils INSTANCE = new OkHttpUtils();
    private static String TAG = "OkHttpUtils";
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static Function4<? super String, ? super String, ? super Integer, ? super String, Unit> ttsClick;
    private static Function2<? super String, ? super String, Unit> ttsError;

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0002\u001a\u00020\u00032\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0007H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ.\u0010\t\u001a\u0004\u0018\u00010\n2\u0019\b\u0001\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\n2\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0011H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u00142\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiVoiceService;", "", "logPost", "Lokhttp3/ResponseBody;", "reqData", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogData;", "(Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogDataD;", "(Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogDataD;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "post", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillResp;", "json", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postAiCloudSkill", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudSkillReq;", "(Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudSkillReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postCloudPlatform", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$TestData;", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudPlatformReq;", "(Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudPlatformReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface AiVoiceService {
        @Headers({PdHost.ROBOT_AI})
        @POST(UrlOkManager.NPL_LOG)
        Object logPost(@Body LogData logData, Continuation<? super ResponseBody> continuation);

        @Headers({PdHost.ROBOT_AI})
        @POST(UrlOkManager.NPL_LOG)
        Object logPost(@Body LogDataD logDataD, Continuation<? super ResponseBody> continuation);

        @Headers({PdHost.ROBOT_AI})
        @POST(UrlOkManager.NPL_ANSWER)
        Object post(@Body Map<String, Object> map, Continuation<? super CloudSkillResp> continuation);

        @Headers({PdHost.ROBOT_AI})
        @POST(UrlOkManager.NPL_ANSWER)
        Object postAiCloudSkill(@Body AiCloudSkillReq aiCloudSkillReq, Continuation<? super CloudSkillResp> continuation);

        @Headers({PdHost.ROBOT_AI})
        @POST(UrlOkManager.NPL_TTS_ANSWER)
        Object postCloudPlatform(@Body AiCloudPlatformReq aiCloudPlatformReq, Continuation<? super TestData> continuation);
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$HttpCallbackInterface;", "", "onErrorCallback", "", "error", "", "onSuccessCallback", "answer", "json", "state", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface HttpCallbackInterface {
        void onErrorCallback(String error);

        void onSuccessCallback(String answer, String json, int state);
    }

    private OkHttpUtils() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        TAG = str;
    }

    public final Function4<String, String, Integer, String, Unit> getTtsClick() {
        return ttsClick;
    }

    public final void setTtsClick(Function4<? super String, ? super String, ? super Integer, ? super String, Unit> function4) {
        ttsClick = function4;
    }

    public final Function2<String, String, Unit> getTtsError() {
        return ttsError;
    }

    public final void setTtsError(Function2<? super String, ? super String, Unit> function2) {
        ttsError = function2;
    }

    public static /* synthetic */ void post$default(OkHttpUtils okHttpUtils, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        okHttpUtils.post(str, str2, str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5);
    }

    public final void post(String mac, String text, String r33, String actionIntent, String pcmName) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(r33, "result");
        Pdlog.m3273d(TAG, "map_name == " + VoiceInteractionKit.INSTANCE.getMapName() + " mac == " + mac + " text == " + text + " shop_id == " + VoiceInteractionKit.INSTANCE.getShopId() + " config_id == " + VoiceInteractionKit.INSTANCE.getConfigId() + " worker_mode == " + VoiceInteractionKit.INSTANCE.getWokerMode() + ' ' + scope);
        JsonObject jsonObject = new JsonObject();
        try {
            if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot")) {
                jsonObject.addProperty("worker_mode", VoiceCommentConfig.INSTANCE.getWokerMode().getMode());
                jsonObject.addProperty("map_name", VoiceCommentConfig.INSTANCE.getMapName());
                jsonObject.addProperty("shop_id", VoiceCommentConfig.INSTANCE.getShopId());
            } else {
                jsonObject.addProperty("worker_mode", VoiceInteractionKit.INSTANCE.getWokerMode());
                jsonObject.addProperty("map_name", VoiceInteractionKit.INSTANCE.getMapName());
                jsonObject.addProperty("shop_id", VoiceInteractionKit.INSTANCE.getShopId());
                jsonObject.addProperty("config_id", VoiceInteractionKit.INSTANCE.getConfigId());
            }
            jsonObject.addProperty("device_id", mac);
            jsonObject.addProperty("text", text);
            jsonObject.addProperty("product_type", VoiceInteractionKit.INSTANCE.getProduct_type());
            jsonObject.addProperty("language", VoiceInteractionKit.INSTANCE.getLanguageStr());
            jsonObject.addProperty("edition", VoiceInteractionKit.INSTANCE.getEdition());
            jsonObject.addProperty(SpeechEvent.KEY_EVENT_SESSION_ID, mac + VoiceInteractionKit.INSTANCE.getSession_id());
            jsonObject.addProperty("third_party_answer", r33);
            jsonObject.addProperty("voice_file_name", pcmName);
            if (!StringUtils.isEmpty(actionIntent)) {
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("intent", actionIntent);
                jsonObject.add("active_order", jsonObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AiVoiceService aiVoiceService = (AiVoiceService) PdNetworkManager.f10310INSTANCE.createService(AiVoiceService.class);
        LogDataD logDataD = new LogDataD(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 131071, null);
        logDataD.setQuestion(text);
        logDataD.setAnswer(r33);
        logDataD.setQuestion_type(0);
        logDataD.setAnswer_type(0);
        logDataD.setAnswer_source("answer_source_xunfei");
        logDataD.setSession_id(mac + System.currentTimeMillis());
        logDataD.setWork_mode(VoiceInteractionKit.INSTANCE.getWokerMode());
        logDataD.setSkill("");
        logDataD.setIntention("");
        logDataD.setUser_id("");
        logDataD.setShop_id(VoiceInteractionKit.INSTANCE.getShopId());
        logDataD.setDevice_id(mac);
        logDataD.setCreate_at(String.valueOf(System.currentTimeMillis()));
        logDataD.setProduct_type(VoiceInteractionKit.INSTANCE.getProduct_type());
        logDataD.setLanguage(VoiceInteractionKit.INSTANCE.getLanguageStr());
        logDataD.setThird_party_answer(r33);
        logDataD.setVoice_file_name(pcmName);
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new OkHttpUtils$post$2(aiVoiceService, jsonObject, logDataD, text, mac, null), 3, null);
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$DataX;", "", "answer", "", "text", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "getText", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class DataX {
        private final String answer;
        private final String text;
        private final String type;

        public static /* synthetic */ DataX copy$default(DataX dataX, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = dataX.answer;
            }
            if ((i & 2) != 0) {
                str2 = dataX.text;
            }
            if ((i & 4) != 0) {
                str3 = dataX.type;
            }
            return dataX.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAnswer() {
            return this.answer;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final DataX copy(String answer, String text, String type) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new DataX(answer, text, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DataX)) {
                return false;
            }
            DataX dataX = (DataX) other;
            return Intrinsics.areEqual(this.answer, dataX.answer) && Intrinsics.areEqual(this.text, dataX.text) && Intrinsics.areEqual(this.type, dataX.type);
        }

        public int hashCode() {
            String str = this.answer;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.text;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.type;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "DataX(answer=" + this.answer + ", text=" + this.text + ", type=" + this.type + ")";
        }

        public DataX(String answer, String text, String type) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.answer = answer;
            this.text = text;
            this.type = type;
        }

        public final String getAnswer() {
            return this.answer;
        }

        public final String getText() {
            return this.text;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|(1:(1:(1:(1:(3:12|13|14)(2:16|17))(7:18|19|20|21|(2:23|(1:25))|13|14))(10:26|27|28|29|30|20|21|(0)|13|14))(4:37|38|39|40))(7:75|76|77|78|79|80|(1:82)(1:83))|41|42|43|44|(10:46|47|48|49|50|51|52|53|54|(1:56)(7:57|30|20|21|(0)|13|14))(5:67|21|(0)|13|14)))|90|6|(0)(0)|41|42|43|44|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01a6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01a7, code lost:
    
        r10 = r5;
        r5 = r6;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x025e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, com.pudutech.voiceinteraction.component.utils.OkHttpUtils$AiVoiceService] */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, com.pudutech.voiceinteraction.component.utils.OkHttpUtils$AiCloudPlatformReq] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object postCloudPlatform(String str, String str2, HttpCallbackInterface httpCallbackInterface, Continuation<? super Unit> continuation) {
        OkHttpUtils$postCloudPlatform$1 okHttpUtils$postCloudPlatform$1;
        int i;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        HttpCallbackInterface httpCallbackInterface2;
        OkHttpUtils okHttpUtils;
        OkHttpUtils okHttpUtils2;
        AiVoiceService aiVoiceService;
        AiCloudPlatformReq aiCloudPlatformReq;
        Ref.ObjectRef objectRef3;
        HttpCallbackInterface httpCallbackInterface3;
        String str3;
        String str4;
        Ref.ObjectRef objectRef4;
        OkHttpUtils okHttpUtils3;
        HttpCallbackInterface httpCallbackInterface4;
        Object m4510constructorimpl;
        Ref.ObjectRef objectRef5;
        Ref.ObjectRef objectRef6;
        Ref.ObjectRef objectRef7;
        Object obj;
        TestData testData;
        Ref.ObjectRef objectRef8;
        Ref.ObjectRef objectRef9;
        Object obj2;
        Ref.ObjectRef objectRef10;
        MainCoroutineDispatcher main;
        OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$1 okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$1;
        Ref.ObjectRef objectRef11;
        Object obj3;
        Object withContext;
        Ref.ObjectRef objectRef12;
        Ref.ObjectRef objectRef13;
        Throwable m4513exceptionOrNullimpl;
        String str5 = str;
        String str6 = str2;
        if (continuation instanceof OkHttpUtils$postCloudPlatform$1) {
            okHttpUtils$postCloudPlatform$1 = (OkHttpUtils$postCloudPlatform$1) continuation;
            if ((okHttpUtils$postCloudPlatform$1.label & Integer.MIN_VALUE) != 0) {
                okHttpUtils$postCloudPlatform$1.label -= Integer.MIN_VALUE;
                Object obj4 = okHttpUtils$postCloudPlatform$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = okHttpUtils$postCloudPlatform$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj4);
                    objectRef = new Ref.ObjectRef();
                    objectRef.element = (AiVoiceService) PdNetworkManager.f10310INSTANCE.createService(AiVoiceService.class);
                    objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = new AiCloudPlatformReq(str5, str6);
                    Pdlog.m3273d(TAG, "postCloudPlatform() postCloudPlatform  aiCloudPlatformReq = " + ((AiCloudPlatformReq) objectRef2.element));
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        okHttpUtils2 = this;
                        String str7 = TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("postCloudPlatform runCatching aiCloudPlatformReq:");
                        sb.append((AiCloudPlatformReq) objectRef2.element);
                        sb.append(" ThreadName=");
                        Thread currentThread = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                        sb.append(currentThread.getName());
                        Pdlog.m3273d(str7, sb.toString());
                        aiVoiceService = (AiVoiceService) objectRef.element;
                        aiCloudPlatformReq = (AiCloudPlatformReq) objectRef2.element;
                        okHttpUtils$postCloudPlatform$1.L$0 = this;
                        okHttpUtils$postCloudPlatform$1.L$1 = str5;
                        okHttpUtils$postCloudPlatform$1.L$2 = str6;
                        httpCallbackInterface2 = httpCallbackInterface;
                    } catch (Throwable th) {
                        th = th;
                        httpCallbackInterface2 = httpCallbackInterface;
                    }
                    try {
                        okHttpUtils$postCloudPlatform$1.L$3 = httpCallbackInterface2;
                        okHttpUtils$postCloudPlatform$1.L$4 = objectRef;
                        okHttpUtils$postCloudPlatform$1.L$5 = objectRef2;
                        okHttpUtils$postCloudPlatform$1.L$6 = okHttpUtils2;
                        okHttpUtils$postCloudPlatform$1.label = 1;
                        obj4 = aiVoiceService.postCloudPlatform(aiCloudPlatformReq, okHttpUtils$postCloudPlatform$1);
                        if (obj4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        okHttpUtils = this;
                        objectRef3 = objectRef;
                        httpCallbackInterface3 = httpCallbackInterface2;
                    } catch (Throwable th2) {
                        th = th2;
                        okHttpUtils = this;
                        Result.Companion companion2 = Result.INSTANCE;
                        str3 = str5;
                        str4 = str6;
                        objectRef4 = objectRef2;
                        okHttpUtils3 = okHttpUtils;
                        httpCallbackInterface4 = httpCallbackInterface2;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        objectRef5 = objectRef;
                        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i == 4) {
                                    Object obj5 = okHttpUtils$postCloudPlatform$1.L$6;
                                    ResultKt.throwOnFailure(obj4);
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            Object obj6 = okHttpUtils$postCloudPlatform$1.L$6;
                            objectRef10 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$5;
                            Ref.ObjectRef objectRef14 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$4;
                            HttpCallbackInterface httpCallbackInterface5 = (HttpCallbackInterface) okHttpUtils$postCloudPlatform$1.L$3;
                            String str8 = (String) okHttpUtils$postCloudPlatform$1.L$2;
                            String str9 = (String) okHttpUtils$postCloudPlatform$1.L$1;
                            OkHttpUtils okHttpUtils4 = (OkHttpUtils) okHttpUtils$postCloudPlatform$1.L$0;
                            ResultKt.throwOnFailure(obj4);
                            objectRef9 = objectRef14;
                            httpCallbackInterface4 = httpCallbackInterface5;
                            str4 = str8;
                            str3 = str9;
                            obj = coroutine_suspended;
                            obj3 = obj6;
                            okHttpUtils3 = okHttpUtils4;
                            objectRef12 = objectRef9;
                            objectRef13 = objectRef10;
                            m4510constructorimpl = obj3;
                            objectRef6 = objectRef13;
                            objectRef7 = objectRef12;
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                            if (m4513exceptionOrNullimpl != null) {
                                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                                OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1 okHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1 = new OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1(m4513exceptionOrNullimpl, null, okHttpUtils$postCloudPlatform$1, httpCallbackInterface4);
                                okHttpUtils$postCloudPlatform$1.L$0 = okHttpUtils3;
                                okHttpUtils$postCloudPlatform$1.L$1 = str3;
                                okHttpUtils$postCloudPlatform$1.L$2 = str4;
                                okHttpUtils$postCloudPlatform$1.L$3 = httpCallbackInterface4;
                                okHttpUtils$postCloudPlatform$1.L$4 = objectRef7;
                                okHttpUtils$postCloudPlatform$1.L$5 = objectRef6;
                                okHttpUtils$postCloudPlatform$1.L$6 = m4510constructorimpl;
                                okHttpUtils$postCloudPlatform$1.L$7 = m4513exceptionOrNullimpl;
                                okHttpUtils$postCloudPlatform$1.label = 4;
                                if (BuildersKt.withContext(main2, okHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1, okHttpUtils$postCloudPlatform$1) == obj) {
                                    return obj;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        TestData testData2 = (TestData) okHttpUtils$postCloudPlatform$1.L$7;
                        m4510constructorimpl = okHttpUtils$postCloudPlatform$1.L$6;
                        objectRef11 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$5;
                        Ref.ObjectRef objectRef15 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$4;
                        HttpCallbackInterface httpCallbackInterface6 = (HttpCallbackInterface) okHttpUtils$postCloudPlatform$1.L$3;
                        String str10 = (String) okHttpUtils$postCloudPlatform$1.L$2;
                        String str11 = (String) okHttpUtils$postCloudPlatform$1.L$1;
                        OkHttpUtils okHttpUtils5 = (OkHttpUtils) okHttpUtils$postCloudPlatform$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj4);
                            objectRef9 = objectRef15;
                            httpCallbackInterface4 = httpCallbackInterface6;
                            str4 = str10;
                            str3 = str11;
                            okHttpUtils3 = okHttpUtils5;
                            objectRef12 = objectRef9;
                            objectRef13 = objectRef11;
                            obj = coroutine_suspended;
                        } catch (Exception e) {
                            e = e;
                            testData = testData2;
                            objectRef9 = objectRef15;
                            httpCallbackInterface4 = httpCallbackInterface6;
                            str4 = str10;
                            str3 = str11;
                            okHttpUtils3 = okHttpUtils5;
                            obj2 = m4510constructorimpl;
                            objectRef10 = objectRef11;
                            MainCoroutineDispatcher main3 = Dispatchers.getMain();
                            obj3 = obj2;
                            OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2 okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2 = new OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2(e, testData, null, okHttpUtils$postCloudPlatform$1, httpCallbackInterface4);
                            okHttpUtils$postCloudPlatform$1.L$0 = okHttpUtils3;
                            okHttpUtils$postCloudPlatform$1.L$1 = str3;
                            okHttpUtils$postCloudPlatform$1.L$2 = str4;
                            okHttpUtils$postCloudPlatform$1.L$3 = httpCallbackInterface4;
                            okHttpUtils$postCloudPlatform$1.L$4 = objectRef9;
                            okHttpUtils$postCloudPlatform$1.L$5 = objectRef10;
                            okHttpUtils$postCloudPlatform$1.L$6 = obj3;
                            okHttpUtils$postCloudPlatform$1.L$7 = testData;
                            okHttpUtils$postCloudPlatform$1.L$8 = e;
                            okHttpUtils$postCloudPlatform$1.label = 3;
                            withContext = BuildersKt.withContext(main3, okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2, okHttpUtils$postCloudPlatform$1);
                            obj = coroutine_suspended;
                            if (withContext == obj) {
                                return obj;
                            }
                            objectRef12 = objectRef9;
                            objectRef13 = objectRef10;
                            m4510constructorimpl = obj3;
                            objectRef6 = objectRef13;
                            objectRef7 = objectRef12;
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                            if (m4513exceptionOrNullimpl != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        objectRef6 = objectRef13;
                        objectRef7 = objectRef12;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    Ref.ObjectRef objectRef16 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$5;
                    Ref.ObjectRef objectRef17 = (Ref.ObjectRef) okHttpUtils$postCloudPlatform$1.L$4;
                    httpCallbackInterface3 = (HttpCallbackInterface) okHttpUtils$postCloudPlatform$1.L$3;
                    String str12 = (String) okHttpUtils$postCloudPlatform$1.L$2;
                    String str13 = (String) okHttpUtils$postCloudPlatform$1.L$1;
                    okHttpUtils = (OkHttpUtils) okHttpUtils$postCloudPlatform$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        objectRef2 = objectRef16;
                        str5 = str13;
                        objectRef3 = objectRef17;
                        str6 = str12;
                    } catch (Throwable th3) {
                        th = th3;
                        httpCallbackInterface2 = httpCallbackInterface3;
                        objectRef = objectRef17;
                        str6 = str12;
                        objectRef2 = objectRef16;
                        str5 = str13;
                        Result.Companion companion22 = Result.INSTANCE;
                        str3 = str5;
                        str4 = str6;
                        objectRef4 = objectRef2;
                        okHttpUtils3 = okHttpUtils;
                        httpCallbackInterface4 = httpCallbackInterface2;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        objectRef5 = objectRef;
                        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                }
                str3 = str5;
                str4 = str6;
                httpCallbackInterface4 = httpCallbackInterface3;
                objectRef5 = objectRef3;
                objectRef4 = objectRef2;
                okHttpUtils3 = okHttpUtils;
                m4510constructorimpl = Result.m4510constructorimpl((TestData) obj4);
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    TestData testData3 = (TestData) m4510constructorimpl;
                    try {
                        String answer = testData3.getData().getAnswer();
                        main = Dispatchers.getMain();
                        testData = testData3;
                        Ref.ObjectRef objectRef18 = objectRef4;
                        objectRef9 = objectRef5;
                        try {
                            okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$1 = new OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$1(answer, testData3, null, okHttpUtils$postCloudPlatform$1, httpCallbackInterface4);
                            okHttpUtils$postCloudPlatform$1.L$0 = okHttpUtils3;
                            okHttpUtils$postCloudPlatform$1.L$1 = str3;
                            okHttpUtils$postCloudPlatform$1.L$2 = str4;
                            okHttpUtils$postCloudPlatform$1.L$3 = httpCallbackInterface4;
                            okHttpUtils$postCloudPlatform$1.L$4 = objectRef9;
                            objectRef8 = objectRef18;
                            try {
                                okHttpUtils$postCloudPlatform$1.L$5 = objectRef8;
                                okHttpUtils$postCloudPlatform$1.L$6 = m4510constructorimpl;
                                okHttpUtils$postCloudPlatform$1.L$7 = testData;
                                okHttpUtils$postCloudPlatform$1.L$8 = answer;
                                okHttpUtils$postCloudPlatform$1.label = 2;
                            } catch (Exception e2) {
                                e = e2;
                                obj2 = m4510constructorimpl;
                                objectRef10 = objectRef8;
                                MainCoroutineDispatcher main32 = Dispatchers.getMain();
                                obj3 = obj2;
                                OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2 okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$22 = new OkHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$2(e, testData, null, okHttpUtils$postCloudPlatform$1, httpCallbackInterface4);
                                okHttpUtils$postCloudPlatform$1.L$0 = okHttpUtils3;
                                okHttpUtils$postCloudPlatform$1.L$1 = str3;
                                okHttpUtils$postCloudPlatform$1.L$2 = str4;
                                okHttpUtils$postCloudPlatform$1.L$3 = httpCallbackInterface4;
                                okHttpUtils$postCloudPlatform$1.L$4 = objectRef9;
                                okHttpUtils$postCloudPlatform$1.L$5 = objectRef10;
                                okHttpUtils$postCloudPlatform$1.L$6 = obj3;
                                okHttpUtils$postCloudPlatform$1.L$7 = testData;
                                okHttpUtils$postCloudPlatform$1.L$8 = e;
                                okHttpUtils$postCloudPlatform$1.label = 3;
                                withContext = BuildersKt.withContext(main32, okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$22, okHttpUtils$postCloudPlatform$1);
                                obj = coroutine_suspended;
                                if (withContext == obj) {
                                }
                                objectRef12 = objectRef9;
                                objectRef13 = objectRef10;
                                m4510constructorimpl = obj3;
                                objectRef6 = objectRef13;
                                objectRef7 = objectRef12;
                                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                                if (m4513exceptionOrNullimpl != null) {
                                }
                                return Unit.INSTANCE;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            objectRef8 = objectRef18;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        testData = testData3;
                        objectRef8 = objectRef4;
                        objectRef9 = objectRef5;
                    }
                    if (BuildersKt.withContext(main, okHttpUtils$postCloudPlatform$$inlined$onSuccess$lambda$1, okHttpUtils$postCloudPlatform$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef11 = objectRef8;
                    objectRef12 = objectRef9;
                    objectRef13 = objectRef11;
                    obj = coroutine_suspended;
                    objectRef6 = objectRef13;
                    objectRef7 = objectRef12;
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                objectRef6 = objectRef4;
                objectRef7 = objectRef5;
                obj = coroutine_suspended;
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        okHttpUtils$postCloudPlatform$1 = new OkHttpUtils$postCloudPlatform$1(this, continuation);
        Object obj42 = okHttpUtils$postCloudPlatform$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = okHttpUtils$postCloudPlatform$1.label;
        if (i != 0) {
        }
        str3 = str5;
        str4 = str6;
        httpCallbackInterface4 = httpCallbackInterface3;
        objectRef5 = objectRef3;
        objectRef4 = objectRef2;
        okHttpUtils3 = okHttpUtils;
        m4510constructorimpl = Result.m4510constructorimpl((TestData) obj42);
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.pudutech.voiceinteraction.component.utils.OkHttpUtils$AiVoiceService] */
    public final void logPost(LogData log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (AiVoiceService) PdNetworkManager.f10310INSTANCE.createService(AiVoiceService.class);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new OkHttpUtils$logPost$1(log, objectRef, null), 2, null);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.pudutech.voiceinteraction.component.utils.OkHttpUtils$AiVoiceService] */
    public final void logPost(LogDataD log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (AiVoiceService) PdNetworkManager.f10310INSTANCE.createService(AiVoiceService.class);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new OkHttpUtils$logPost$2(log, objectRef, null), 2, null);
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudPlatformReq;", "", "mac", "", "text", "(Ljava/lang/String;Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "getText", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class AiCloudPlatformReq {
        private final String mac;
        private final String text;

        public static /* synthetic */ AiCloudPlatformReq copy$default(AiCloudPlatformReq aiCloudPlatformReq, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aiCloudPlatformReq.mac;
            }
            if ((i & 2) != 0) {
                str2 = aiCloudPlatformReq.text;
            }
            return aiCloudPlatformReq.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMac() {
            return this.mac;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final AiCloudPlatformReq copy(String mac, String text) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(text, "text");
            return new AiCloudPlatformReq(mac, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AiCloudPlatformReq)) {
                return false;
            }
            AiCloudPlatformReq aiCloudPlatformReq = (AiCloudPlatformReq) other;
            return Intrinsics.areEqual(this.mac, aiCloudPlatformReq.mac) && Intrinsics.areEqual(this.text, aiCloudPlatformReq.text);
        }

        public int hashCode() {
            String str = this.mac;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.text;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "AiCloudPlatformReq(mac=" + this.mac + ", text=" + this.text + ")";
        }

        public AiCloudPlatformReq(String mac, String text) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(text, "text");
            this.mac = mac;
            this.text = text;
        }

        public final String getMac() {
            return this.mac;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$AiCloudSkillReq;", "", "device_id", "", "text", "language", "worker_mode", "map_name", "product_type", SpeechEvent.KEY_EVENT_SESSION_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDevice_id", "()Ljava/lang/String;", "getLanguage", "getMap_name", "getProduct_type", "getSession_id", "getText", "getWorker_mode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class AiCloudSkillReq {
        private final String device_id;
        private final String language;
        private final String map_name;
        private final String product_type;
        private final String session_id;
        private final String text;
        private final String worker_mode;

        public static /* synthetic */ AiCloudSkillReq copy$default(AiCloudSkillReq aiCloudSkillReq, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aiCloudSkillReq.device_id;
            }
            if ((i & 2) != 0) {
                str2 = aiCloudSkillReq.text;
            }
            String str8 = str2;
            if ((i & 4) != 0) {
                str3 = aiCloudSkillReq.language;
            }
            String str9 = str3;
            if ((i & 8) != 0) {
                str4 = aiCloudSkillReq.worker_mode;
            }
            String str10 = str4;
            if ((i & 16) != 0) {
                str5 = aiCloudSkillReq.map_name;
            }
            String str11 = str5;
            if ((i & 32) != 0) {
                str6 = aiCloudSkillReq.product_type;
            }
            String str12 = str6;
            if ((i & 64) != 0) {
                str7 = aiCloudSkillReq.session_id;
            }
            return aiCloudSkillReq.copy(str, str8, str9, str10, str11, str12, str7);
        }

        /* renamed from: component1, reason: from getter */
        public final String getDevice_id() {
            return this.device_id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* renamed from: component4, reason: from getter */
        public final String getWorker_mode() {
            return this.worker_mode;
        }

        /* renamed from: component5, reason: from getter */
        public final String getMap_name() {
            return this.map_name;
        }

        /* renamed from: component6, reason: from getter */
        public final String getProduct_type() {
            return this.product_type;
        }

        /* renamed from: component7, reason: from getter */
        public final String getSession_id() {
            return this.session_id;
        }

        public final AiCloudSkillReq copy(String device_id, String text, String language, String worker_mode, String map_name, String product_type, String r16) {
            Intrinsics.checkParameterIsNotNull(device_id, "device_id");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(language, "language");
            Intrinsics.checkParameterIsNotNull(worker_mode, "worker_mode");
            Intrinsics.checkParameterIsNotNull(map_name, "map_name");
            Intrinsics.checkParameterIsNotNull(product_type, "product_type");
            Intrinsics.checkParameterIsNotNull(r16, "session_id");
            return new AiCloudSkillReq(device_id, text, language, worker_mode, map_name, product_type, r16);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AiCloudSkillReq)) {
                return false;
            }
            AiCloudSkillReq aiCloudSkillReq = (AiCloudSkillReq) other;
            return Intrinsics.areEqual(this.device_id, aiCloudSkillReq.device_id) && Intrinsics.areEqual(this.text, aiCloudSkillReq.text) && Intrinsics.areEqual(this.language, aiCloudSkillReq.language) && Intrinsics.areEqual(this.worker_mode, aiCloudSkillReq.worker_mode) && Intrinsics.areEqual(this.map_name, aiCloudSkillReq.map_name) && Intrinsics.areEqual(this.product_type, aiCloudSkillReq.product_type) && Intrinsics.areEqual(this.session_id, aiCloudSkillReq.session_id);
        }

        public int hashCode() {
            String str = this.device_id;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.text;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.language;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.worker_mode;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.map_name;
            int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.product_type;
            int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
            String str7 = this.session_id;
            return hashCode6 + (str7 != null ? str7.hashCode() : 0);
        }

        public String toString() {
            return "AiCloudSkillReq(device_id=" + this.device_id + ", text=" + this.text + ", language=" + this.language + ", worker_mode=" + this.worker_mode + ", map_name=" + this.map_name + ", product_type=" + this.product_type + ", session_id=" + this.session_id + ")";
        }

        public AiCloudSkillReq(String device_id, String text, String language, String worker_mode, String map_name, String product_type, String session_id) {
            Intrinsics.checkParameterIsNotNull(device_id, "device_id");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(language, "language");
            Intrinsics.checkParameterIsNotNull(worker_mode, "worker_mode");
            Intrinsics.checkParameterIsNotNull(map_name, "map_name");
            Intrinsics.checkParameterIsNotNull(product_type, "product_type");
            Intrinsics.checkParameterIsNotNull(session_id, "session_id");
            this.device_id = device_id;
            this.text = text;
            this.language = language;
            this.worker_mode = worker_mode;
            this.map_name = map_name;
            this.product_type = product_type;
            this.session_id = session_id;
        }

        public final String getDevice_id() {
            return this.device_id;
        }

        public final String getText() {
            return this.text;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getWorker_mode() {
            return this.worker_mode;
        }

        public final String getMap_name() {
            return this.map_name;
        }

        public final String getProduct_type() {
            return this.product_type;
        }

        public final String getSession_id() {
            return this.session_id;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillResp;", "", "code", "", "data", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillRespData;", "message", "", "(JLcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillRespData;Ljava/lang/String;)V", "getCode", "()J", "getData", "()Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillRespData;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class CloudSkillResp {
        private final long code;
        private final CloudSkillRespData data;
        private final String message;

        public static /* synthetic */ CloudSkillResp copy$default(CloudSkillResp cloudSkillResp, long j, CloudSkillRespData cloudSkillRespData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = cloudSkillResp.code;
            }
            if ((i & 2) != 0) {
                cloudSkillRespData = cloudSkillResp.data;
            }
            if ((i & 4) != 0) {
                str = cloudSkillResp.message;
            }
            return cloudSkillResp.copy(j, cloudSkillRespData, str);
        }

        /* renamed from: component1, reason: from getter */
        public final long getCode() {
            return this.code;
        }

        /* renamed from: component2, reason: from getter */
        public final CloudSkillRespData getData() {
            return this.data;
        }

        /* renamed from: component3, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final CloudSkillResp copy(long code, CloudSkillRespData data, String message) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return new CloudSkillResp(code, data, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloudSkillResp)) {
                return false;
            }
            CloudSkillResp cloudSkillResp = (CloudSkillResp) other;
            return this.code == cloudSkillResp.code && Intrinsics.areEqual(this.data, cloudSkillResp.data) && Intrinsics.areEqual(this.message, cloudSkillResp.message);
        }

        public int hashCode() {
            long j = this.code;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            CloudSkillRespData cloudSkillRespData = this.data;
            int hashCode = (i + (cloudSkillRespData != null ? cloudSkillRespData.hashCode() : 0)) * 31;
            String str = this.message;
            return hashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "CloudSkillResp(code=" + this.code + ", data=" + this.data + ", message=" + this.message + ")";
        }

        public CloudSkillResp(long j, CloudSkillRespData data, String message) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            Intrinsics.checkParameterIsNotNull(message, "message");
            this.code = j;
            this.data = data;
            this.message = message;
        }

        public final long getCode() {
            return this.code;
        }

        public final CloudSkillRespData getData() {
            return this.data;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Ja\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006)"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillRespData;", "", "answer_source", "", "voiceId", "device_id", "faq_answer", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$FaqAnswer;", "rasa_answer", "", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$RasaAnswer;", "shop_id", "use_time", "user_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$FaqAnswer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer_source", "()Ljava/lang/String;", "getDevice_id", "getFaq_answer", "()Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$FaqAnswer;", "getRasa_answer", "()Ljava/util/List;", "getShop_id", "getUse_time", "getUser_id", "getVoiceId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class CloudSkillRespData {
        private final String answer_source;
        private final String device_id;
        private final FaqAnswer faq_answer;
        private final List<RasaAnswer> rasa_answer;
        private final String shop_id;
        private final String use_time;
        private final String user_id;
        private final String voiceId;

        /* renamed from: component1, reason: from getter */
        public final String getAnswer_source() {
            return this.answer_source;
        }

        /* renamed from: component2, reason: from getter */
        public final String getVoiceId() {
            return this.voiceId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDevice_id() {
            return this.device_id;
        }

        /* renamed from: component4, reason: from getter */
        public final FaqAnswer getFaq_answer() {
            return this.faq_answer;
        }

        public final List<RasaAnswer> component5() {
            return this.rasa_answer;
        }

        /* renamed from: component6, reason: from getter */
        public final String getShop_id() {
            return this.shop_id;
        }

        /* renamed from: component7, reason: from getter */
        public final String getUse_time() {
            return this.use_time;
        }

        /* renamed from: component8, reason: from getter */
        public final String getUser_id() {
            return this.user_id;
        }

        public final CloudSkillRespData copy(String answer_source, String voiceId, String device_id, FaqAnswer faq_answer, List<RasaAnswer> rasa_answer, String shop_id, String use_time, String user_id) {
            Intrinsics.checkParameterIsNotNull(answer_source, "answer_source");
            Intrinsics.checkParameterIsNotNull(device_id, "device_id");
            Intrinsics.checkParameterIsNotNull(faq_answer, "faq_answer");
            Intrinsics.checkParameterIsNotNull(rasa_answer, "rasa_answer");
            Intrinsics.checkParameterIsNotNull(shop_id, "shop_id");
            Intrinsics.checkParameterIsNotNull(use_time, "use_time");
            Intrinsics.checkParameterIsNotNull(user_id, "user_id");
            return new CloudSkillRespData(answer_source, voiceId, device_id, faq_answer, rasa_answer, shop_id, use_time, user_id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloudSkillRespData)) {
                return false;
            }
            CloudSkillRespData cloudSkillRespData = (CloudSkillRespData) other;
            return Intrinsics.areEqual(this.answer_source, cloudSkillRespData.answer_source) && Intrinsics.areEqual(this.voiceId, cloudSkillRespData.voiceId) && Intrinsics.areEqual(this.device_id, cloudSkillRespData.device_id) && Intrinsics.areEqual(this.faq_answer, cloudSkillRespData.faq_answer) && Intrinsics.areEqual(this.rasa_answer, cloudSkillRespData.rasa_answer) && Intrinsics.areEqual(this.shop_id, cloudSkillRespData.shop_id) && Intrinsics.areEqual(this.use_time, cloudSkillRespData.use_time) && Intrinsics.areEqual(this.user_id, cloudSkillRespData.user_id);
        }

        public int hashCode() {
            String str = this.answer_source;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.voiceId;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.device_id;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            FaqAnswer faqAnswer = this.faq_answer;
            int hashCode4 = (hashCode3 + (faqAnswer != null ? faqAnswer.hashCode() : 0)) * 31;
            List<RasaAnswer> list = this.rasa_answer;
            int hashCode5 = (hashCode4 + (list != null ? list.hashCode() : 0)) * 31;
            String str4 = this.shop_id;
            int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.use_time;
            int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.user_id;
            return hashCode7 + (str6 != null ? str6.hashCode() : 0);
        }

        public String toString() {
            return "CloudSkillRespData(answer_source=" + this.answer_source + ", voiceId=" + this.voiceId + ", device_id=" + this.device_id + ", faq_answer=" + this.faq_answer + ", rasa_answer=" + this.rasa_answer + ", shop_id=" + this.shop_id + ", use_time=" + this.use_time + ", user_id=" + this.user_id + ")";
        }

        public CloudSkillRespData(String answer_source, String str, String device_id, FaqAnswer faq_answer, List<RasaAnswer> rasa_answer, String shop_id, String use_time, String user_id) {
            Intrinsics.checkParameterIsNotNull(answer_source, "answer_source");
            Intrinsics.checkParameterIsNotNull(device_id, "device_id");
            Intrinsics.checkParameterIsNotNull(faq_answer, "faq_answer");
            Intrinsics.checkParameterIsNotNull(rasa_answer, "rasa_answer");
            Intrinsics.checkParameterIsNotNull(shop_id, "shop_id");
            Intrinsics.checkParameterIsNotNull(use_time, "use_time");
            Intrinsics.checkParameterIsNotNull(user_id, "user_id");
            this.answer_source = answer_source;
            this.voiceId = str;
            this.device_id = device_id;
            this.faq_answer = faq_answer;
            this.rasa_answer = rasa_answer;
            this.shop_id = shop_id;
            this.use_time = use_time;
            this.user_id = user_id;
        }

        public final String getAnswer_source() {
            return this.answer_source;
        }

        public final String getVoiceId() {
            return this.voiceId;
        }

        public final String getDevice_id() {
            return this.device_id;
        }

        public final FaqAnswer getFaq_answer() {
            return this.faq_answer;
        }

        public final List<RasaAnswer> getRasa_answer() {
            return this.rasa_answer;
        }

        public final String getShop_id() {
            return this.shop_id;
        }

        public final String getUse_time() {
            return this.use_time;
        }

        public final String getUser_id() {
            return this.user_id;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003JU\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0006HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006$"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$FaqAnswer;", "", "answer", "", "question", "question_type", "", "sim", "", "trigger_image_url", "trigger_type", "trigger_extend", "(Ljava/lang/String;Ljava/lang/String;IFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "getQuestion", "getQuestion_type", "()I", "getSim", "()F", "getTrigger_extend", "getTrigger_image_url", "getTrigger_type", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class FaqAnswer {
        private final String answer;
        private final String question;
        private final int question_type;
        private final float sim;
        private final String trigger_extend;
        private final String trigger_image_url;
        private final String trigger_type;

        public static /* synthetic */ FaqAnswer copy$default(FaqAnswer faqAnswer, String str, String str2, int i, float f, String str3, String str4, String str5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = faqAnswer.answer;
            }
            if ((i2 & 2) != 0) {
                str2 = faqAnswer.question;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                i = faqAnswer.question_type;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                f = faqAnswer.sim;
            }
            float f2 = f;
            if ((i2 & 16) != 0) {
                str3 = faqAnswer.trigger_image_url;
            }
            String str7 = str3;
            if ((i2 & 32) != 0) {
                str4 = faqAnswer.trigger_type;
            }
            String str8 = str4;
            if ((i2 & 64) != 0) {
                str5 = faqAnswer.trigger_extend;
            }
            return faqAnswer.copy(str, str6, i3, f2, str7, str8, str5);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAnswer() {
            return this.answer;
        }

        /* renamed from: component2, reason: from getter */
        public final String getQuestion() {
            return this.question;
        }

        /* renamed from: component3, reason: from getter */
        public final int getQuestion_type() {
            return this.question_type;
        }

        /* renamed from: component4, reason: from getter */
        public final float getSim() {
            return this.sim;
        }

        /* renamed from: component5, reason: from getter */
        public final String getTrigger_image_url() {
            return this.trigger_image_url;
        }

        /* renamed from: component6, reason: from getter */
        public final String getTrigger_type() {
            return this.trigger_type;
        }

        /* renamed from: component7, reason: from getter */
        public final String getTrigger_extend() {
            return this.trigger_extend;
        }

        public final FaqAnswer copy(String answer, String question, int question_type, float sim, String trigger_image_url, String trigger_type, String trigger_extend) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(question, "question");
            return new FaqAnswer(answer, question, question_type, sim, trigger_image_url, trigger_type, trigger_extend);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaqAnswer)) {
                return false;
            }
            FaqAnswer faqAnswer = (FaqAnswer) other;
            return Intrinsics.areEqual(this.answer, faqAnswer.answer) && Intrinsics.areEqual(this.question, faqAnswer.question) && this.question_type == faqAnswer.question_type && Float.compare(this.sim, faqAnswer.sim) == 0 && Intrinsics.areEqual(this.trigger_image_url, faqAnswer.trigger_image_url) && Intrinsics.areEqual(this.trigger_type, faqAnswer.trigger_type) && Intrinsics.areEqual(this.trigger_extend, faqAnswer.trigger_extend);
        }

        public int hashCode() {
            String str = this.answer;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.question;
            int hashCode2 = (((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.question_type) * 31) + Float.floatToIntBits(this.sim)) * 31;
            String str3 = this.trigger_image_url;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.trigger_type;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.trigger_extend;
            return hashCode4 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "FaqAnswer(answer=" + this.answer + ", question=" + this.question + ", question_type=" + this.question_type + ", sim=" + this.sim + ", trigger_image_url=" + this.trigger_image_url + ", trigger_type=" + this.trigger_type + ", trigger_extend=" + this.trigger_extend + ")";
        }

        public FaqAnswer(String answer, String question, int i, float f, String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(question, "question");
            this.answer = answer;
            this.question = question;
            this.question_type = i;
            this.sim = f;
            this.trigger_image_url = str;
            this.trigger_type = str2;
            this.trigger_extend = str3;
        }

        public final String getAnswer() {
            return this.answer;
        }

        public final String getQuestion() {
            return this.question;
        }

        public final int getQuestion_type() {
            return this.question_type;
        }

        public final float getSim() {
            return this.sim;
        }

        public final String getTrigger_image_url() {
            return this.trigger_image_url;
        }

        public final String getTrigger_type() {
            return this.trigger_type;
        }

        public final String getTrigger_extend() {
            return this.trigger_extend;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$RasaAnswer;", "", "action", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Action;", "action_type", "", "utter", "(Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Action;Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Action;", "getAction_type", "()Ljava/lang/String;", "getUtter", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class RasaAnswer {
        private final Action action;
        private final String action_type;
        private final String utter;

        public static /* synthetic */ RasaAnswer copy$default(RasaAnswer rasaAnswer, Action action, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                action = rasaAnswer.action;
            }
            if ((i & 2) != 0) {
                str = rasaAnswer.action_type;
            }
            if ((i & 4) != 0) {
                str2 = rasaAnswer.utter;
            }
            return rasaAnswer.copy(action, str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final Action getAction() {
            return this.action;
        }

        /* renamed from: component2, reason: from getter */
        public final String getAction_type() {
            return this.action_type;
        }

        /* renamed from: component3, reason: from getter */
        public final String getUtter() {
            return this.utter;
        }

        public final RasaAnswer copy(Action action, String action_type, String utter) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            Intrinsics.checkParameterIsNotNull(action_type, "action_type");
            Intrinsics.checkParameterIsNotNull(utter, "utter");
            return new RasaAnswer(action, action_type, utter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RasaAnswer)) {
                return false;
            }
            RasaAnswer rasaAnswer = (RasaAnswer) other;
            return Intrinsics.areEqual(this.action, rasaAnswer.action) && Intrinsics.areEqual(this.action_type, rasaAnswer.action_type) && Intrinsics.areEqual(this.utter, rasaAnswer.utter);
        }

        public int hashCode() {
            Action action = this.action;
            int hashCode = (action != null ? action.hashCode() : 0) * 31;
            String str = this.action_type;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.utter;
            return hashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "RasaAnswer(action=" + this.action + ", action_type=" + this.action_type + ", utter=" + this.utter + ")";
        }

        public RasaAnswer(Action action, String action_type, String utter) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            Intrinsics.checkParameterIsNotNull(action_type, "action_type");
            Intrinsics.checkParameterIsNotNull(utter, "utter");
            this.action = action;
            this.action_type = action_type;
            this.utter = utter;
        }

        public final Action getAction() {
            return this.action;
        }

        public final String getAction_type() {
            return this.action_type;
        }

        public final String getUtter() {
            return this.utter;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Action;", "", "action", "", "pallet", "place", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "getPallet", "getPlace", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class Action {
        private final String action;
        private final String pallet;
        private final String place;

        public static /* synthetic */ Action copy$default(Action action, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = action.action;
            }
            if ((i & 2) != 0) {
                str2 = action.pallet;
            }
            if ((i & 4) != 0) {
                str3 = action.place;
            }
            return action.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        /* renamed from: component2, reason: from getter */
        public final String getPallet() {
            return this.pallet;
        }

        /* renamed from: component3, reason: from getter */
        public final String getPlace() {
            return this.place;
        }

        public final Action copy(String action, String pallet, String place) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            Intrinsics.checkParameterIsNotNull(pallet, "pallet");
            Intrinsics.checkParameterIsNotNull(place, "place");
            return new Action(action, pallet, place);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Action)) {
                return false;
            }
            Action action = (Action) other;
            return Intrinsics.areEqual(this.action, action.action) && Intrinsics.areEqual(this.pallet, action.pallet) && Intrinsics.areEqual(this.place, action.place);
        }

        public int hashCode() {
            String str = this.action;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.pallet;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.place;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Action(action=" + this.action + ", pallet=" + this.pallet + ", place=" + this.place + ")";
        }

        public Action(String action, String pallet, String place) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            Intrinsics.checkParameterIsNotNull(pallet, "pallet");
            Intrinsics.checkParameterIsNotNull(place, "place");
            this.action = action;
            this.pallet = pallet;
            this.place = place;
        }

        public final String getAction() {
            return this.action;
        }

        public final String getPallet() {
            return this.pallet;
        }

        public final String getPlace() {
            return this.place;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bJ\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010G\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010H\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010I\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÚ\u0001\u0010N\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010OJ\u0013\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010S\u001a\u00020\u0006HÖ\u0001J\t\u0010T\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010\u0019R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0017\"\u0004\b*\u0010\u0019R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010\u001dR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u0019R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0017\"\u0004\b8\u0010\u0019R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0017\"\u0004\b:\u0010\u0019R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019¨\u0006U"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogData;", "", "question", "", "answer", "question_type", "", "answer_type", "answer_source", SpeechEvent.KEY_EVENT_SESSION_ID, AIUIConstant.KEY_WORK_MODE, "skill", "intention", "user_id", "shop_id", "device_id", "create_at", "product_type", "language", "third_party_answer", "voice_file_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "setAnswer", "(Ljava/lang/String;)V", "getAnswer_source", "()Ljava/lang/Integer;", "setAnswer_source", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getAnswer_type", "setAnswer_type", "getCreate_at", "setCreate_at", "getDevice_id", "setDevice_id", "getIntention", "setIntention", "getLanguage", "setLanguage", "getProduct_type", "setProduct_type", "getQuestion", "setQuestion", "getQuestion_type", "setQuestion_type", "getSession_id", "setSession_id", "getShop_id", "setShop_id", "getSkill", "setSkill", "getThird_party_answer", "setThird_party_answer", "getUser_id", "setUser_id", "getVoice_file_name", "setVoice_file_name", "getWork_mode", "setWork_mode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogData;", "equals", "", "other", "hashCode", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class LogData {
        private String answer;
        private Integer answer_source;
        private Integer answer_type;
        private String create_at;
        private String device_id;
        private String intention;
        private String language;
        private String product_type;
        private String question;
        private Integer question_type;
        private String session_id;
        private String shop_id;
        private String skill;
        private String third_party_answer;
        private String user_id;
        private String voice_file_name;
        private String work_mode;

        public LogData() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 131071, null);
        }

        /* renamed from: component1, reason: from getter */
        public final String getQuestion() {
            return this.question;
        }

        /* renamed from: component10, reason: from getter */
        public final String getUser_id() {
            return this.user_id;
        }

        /* renamed from: component11, reason: from getter */
        public final String getShop_id() {
            return this.shop_id;
        }

        /* renamed from: component12, reason: from getter */
        public final String getDevice_id() {
            return this.device_id;
        }

        /* renamed from: component13, reason: from getter */
        public final String getCreate_at() {
            return this.create_at;
        }

        /* renamed from: component14, reason: from getter */
        public final String getProduct_type() {
            return this.product_type;
        }

        /* renamed from: component15, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* renamed from: component16, reason: from getter */
        public final String getThird_party_answer() {
            return this.third_party_answer;
        }

        /* renamed from: component17, reason: from getter */
        public final String getVoice_file_name() {
            return this.voice_file_name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getAnswer() {
            return this.answer;
        }

        /* renamed from: component3, reason: from getter */
        public final Integer getQuestion_type() {
            return this.question_type;
        }

        /* renamed from: component4, reason: from getter */
        public final Integer getAnswer_type() {
            return this.answer_type;
        }

        /* renamed from: component5, reason: from getter */
        public final Integer getAnswer_source() {
            return this.answer_source;
        }

        /* renamed from: component6, reason: from getter */
        public final String getSession_id() {
            return this.session_id;
        }

        /* renamed from: component7, reason: from getter */
        public final String getWork_mode() {
            return this.work_mode;
        }

        /* renamed from: component8, reason: from getter */
        public final String getSkill() {
            return this.skill;
        }

        /* renamed from: component9, reason: from getter */
        public final String getIntention() {
            return this.intention;
        }

        public final LogData copy(String question, String answer, Integer question_type, Integer answer_type, Integer answer_source, String r25, String r26, String skill, String intention, String user_id, String shop_id, String device_id, String create_at, String product_type, String language, String third_party_answer, String voice_file_name) {
            return new LogData(question, answer, question_type, answer_type, answer_source, r25, r26, skill, intention, user_id, shop_id, device_id, create_at, product_type, language, third_party_answer, voice_file_name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LogData)) {
                return false;
            }
            LogData logData = (LogData) other;
            return Intrinsics.areEqual(this.question, logData.question) && Intrinsics.areEqual(this.answer, logData.answer) && Intrinsics.areEqual(this.question_type, logData.question_type) && Intrinsics.areEqual(this.answer_type, logData.answer_type) && Intrinsics.areEqual(this.answer_source, logData.answer_source) && Intrinsics.areEqual(this.session_id, logData.session_id) && Intrinsics.areEqual(this.work_mode, logData.work_mode) && Intrinsics.areEqual(this.skill, logData.skill) && Intrinsics.areEqual(this.intention, logData.intention) && Intrinsics.areEqual(this.user_id, logData.user_id) && Intrinsics.areEqual(this.shop_id, logData.shop_id) && Intrinsics.areEqual(this.device_id, logData.device_id) && Intrinsics.areEqual(this.create_at, logData.create_at) && Intrinsics.areEqual(this.product_type, logData.product_type) && Intrinsics.areEqual(this.language, logData.language) && Intrinsics.areEqual(this.third_party_answer, logData.third_party_answer) && Intrinsics.areEqual(this.voice_file_name, logData.voice_file_name);
        }

        public int hashCode() {
            String str = this.question;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.answer;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            Integer num = this.question_type;
            int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
            Integer num2 = this.answer_type;
            int hashCode4 = (hashCode3 + (num2 != null ? num2.hashCode() : 0)) * 31;
            Integer num3 = this.answer_source;
            int hashCode5 = (hashCode4 + (num3 != null ? num3.hashCode() : 0)) * 31;
            String str3 = this.session_id;
            int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.work_mode;
            int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.skill;
            int hashCode8 = (hashCode7 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.intention;
            int hashCode9 = (hashCode8 + (str6 != null ? str6.hashCode() : 0)) * 31;
            String str7 = this.user_id;
            int hashCode10 = (hashCode9 + (str7 != null ? str7.hashCode() : 0)) * 31;
            String str8 = this.shop_id;
            int hashCode11 = (hashCode10 + (str8 != null ? str8.hashCode() : 0)) * 31;
            String str9 = this.device_id;
            int hashCode12 = (hashCode11 + (str9 != null ? str9.hashCode() : 0)) * 31;
            String str10 = this.create_at;
            int hashCode13 = (hashCode12 + (str10 != null ? str10.hashCode() : 0)) * 31;
            String str11 = this.product_type;
            int hashCode14 = (hashCode13 + (str11 != null ? str11.hashCode() : 0)) * 31;
            String str12 = this.language;
            int hashCode15 = (hashCode14 + (str12 != null ? str12.hashCode() : 0)) * 31;
            String str13 = this.third_party_answer;
            int hashCode16 = (hashCode15 + (str13 != null ? str13.hashCode() : 0)) * 31;
            String str14 = this.voice_file_name;
            return hashCode16 + (str14 != null ? str14.hashCode() : 0);
        }

        public String toString() {
            return "LogData(question=" + this.question + ", answer=" + this.answer + ", question_type=" + this.question_type + ", answer_type=" + this.answer_type + ", answer_source=" + this.answer_source + ", session_id=" + this.session_id + ", work_mode=" + this.work_mode + ", skill=" + this.skill + ", intention=" + this.intention + ", user_id=" + this.user_id + ", shop_id=" + this.shop_id + ", device_id=" + this.device_id + ", create_at=" + this.create_at + ", product_type=" + this.product_type + ", language=" + this.language + ", third_party_answer=" + this.third_party_answer + ", voice_file_name=" + this.voice_file_name + ")";
        }

        public LogData(String str, String str2, Integer num, Integer num2, Integer num3, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
            this.question = str;
            this.answer = str2;
            this.question_type = num;
            this.answer_type = num2;
            this.answer_source = num3;
            this.session_id = str3;
            this.work_mode = str4;
            this.skill = str5;
            this.intention = str6;
            this.user_id = str7;
            this.shop_id = str8;
            this.device_id = str9;
            this.create_at = str10;
            this.product_type = str11;
            this.language = str12;
            this.third_party_answer = str13;
            this.voice_file_name = str14;
        }

        public final String getQuestion() {
            return this.question;
        }

        public final void setQuestion(String str) {
            this.question = str;
        }

        public final String getAnswer() {
            return this.answer;
        }

        public final void setAnswer(String str) {
            this.answer = str;
        }

        public /* synthetic */ LogData(String str, String str2, Integer num, Integer num2, Integer num3, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? 0 : num2, (i & 16) != 0 ? 0 : num3, (i & 32) != 0 ? "" : str3, (i & 64) != 0 ? "" : str4, (i & 128) != 0 ? "" : str5, (i & 256) != 0 ? "" : str6, (i & 512) != 0 ? "" : str7, (i & 1024) != 0 ? "" : str8, (i & 2048) != 0 ? "" : str9, (i & 4096) != 0 ? "" : str10, (i & 8192) != 0 ? "" : str11, (i & 16384) != 0 ? "" : str12, (i & 32768) != 0 ? "" : str13, (i & 65536) != 0 ? "" : str14);
        }

        public final Integer getQuestion_type() {
            return this.question_type;
        }

        public final void setQuestion_type(Integer num) {
            this.question_type = num;
        }

        public final Integer getAnswer_type() {
            return this.answer_type;
        }

        public final void setAnswer_type(Integer num) {
            this.answer_type = num;
        }

        public final Integer getAnswer_source() {
            return this.answer_source;
        }

        public final void setAnswer_source(Integer num) {
            this.answer_source = num;
        }

        public final String getSession_id() {
            return this.session_id;
        }

        public final void setSession_id(String str) {
            this.session_id = str;
        }

        public final String getWork_mode() {
            return this.work_mode;
        }

        public final void setWork_mode(String str) {
            this.work_mode = str;
        }

        public final String getSkill() {
            return this.skill;
        }

        public final void setSkill(String str) {
            this.skill = str;
        }

        public final String getIntention() {
            return this.intention;
        }

        public final void setIntention(String str) {
            this.intention = str;
        }

        public final String getUser_id() {
            return this.user_id;
        }

        public final void setUser_id(String str) {
            this.user_id = str;
        }

        public final String getShop_id() {
            return this.shop_id;
        }

        public final void setShop_id(String str) {
            this.shop_id = str;
        }

        public final String getDevice_id() {
            return this.device_id;
        }

        public final void setDevice_id(String str) {
            this.device_id = str;
        }

        public final String getCreate_at() {
            return this.create_at;
        }

        public final void setCreate_at(String str) {
            this.create_at = str;
        }

        public final String getProduct_type() {
            return this.product_type;
        }

        public final void setProduct_type(String str) {
            this.product_type = str;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            this.language = str;
        }

        public final String getThird_party_answer() {
            return this.third_party_answer;
        }

        public final void setThird_party_answer(String str) {
            this.third_party_answer = str;
        }

        public final String getVoice_file_name() {
            return this.voice_file_name;
        }

        public final void setVoice_file_name(String str) {
            this.voice_file_name = str;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bJ\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010G\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010H\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÚ\u0001\u0010N\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010OJ\u0013\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010S\u001a\u00020\u0006HÖ\u0001J\t\u0010T\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010\u0019R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0017\"\u0004\b*\u0010\u0019R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u0019R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0017\"\u0004\b8\u0010\u0019R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0017\"\u0004\b:\u0010\u0019R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019¨\u0006U"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogDataD;", "", "question", "", "answer", "question_type", "", "answer_type", "answer_source", SpeechEvent.KEY_EVENT_SESSION_ID, AIUIConstant.KEY_WORK_MODE, "skill", "intention", "user_id", "shop_id", "device_id", "create_at", "product_type", "language", "third_party_answer", "voice_file_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "setAnswer", "(Ljava/lang/String;)V", "getAnswer_source", "setAnswer_source", "getAnswer_type", "()Ljava/lang/Integer;", "setAnswer_type", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCreate_at", "setCreate_at", "getDevice_id", "setDevice_id", "getIntention", "setIntention", "getLanguage", "setLanguage", "getProduct_type", "setProduct_type", "getQuestion", "setQuestion", "getQuestion_type", "setQuestion_type", "getSession_id", "setSession_id", "getShop_id", "setShop_id", "getSkill", "setSkill", "getThird_party_answer", "setThird_party_answer", "getUser_id", "setUser_id", "getVoice_file_name", "setVoice_file_name", "getWork_mode", "setWork_mode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$LogDataD;", "equals", "", "other", "hashCode", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class LogDataD {
        private String answer;
        private String answer_source;
        private Integer answer_type;
        private String create_at;
        private String device_id;
        private String intention;
        private String language;
        private String product_type;
        private String question;
        private Integer question_type;
        private String session_id;
        private String shop_id;
        private String skill;
        private String third_party_answer;
        private String user_id;
        private String voice_file_name;
        private String work_mode;

        public LogDataD() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 131071, null);
        }

        /* renamed from: component1, reason: from getter */
        public final String getQuestion() {
            return this.question;
        }

        /* renamed from: component10, reason: from getter */
        public final String getUser_id() {
            return this.user_id;
        }

        /* renamed from: component11, reason: from getter */
        public final String getShop_id() {
            return this.shop_id;
        }

        /* renamed from: component12, reason: from getter */
        public final String getDevice_id() {
            return this.device_id;
        }

        /* renamed from: component13, reason: from getter */
        public final String getCreate_at() {
            return this.create_at;
        }

        /* renamed from: component14, reason: from getter */
        public final String getProduct_type() {
            return this.product_type;
        }

        /* renamed from: component15, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* renamed from: component16, reason: from getter */
        public final String getThird_party_answer() {
            return this.third_party_answer;
        }

        /* renamed from: component17, reason: from getter */
        public final String getVoice_file_name() {
            return this.voice_file_name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getAnswer() {
            return this.answer;
        }

        /* renamed from: component3, reason: from getter */
        public final Integer getQuestion_type() {
            return this.question_type;
        }

        /* renamed from: component4, reason: from getter */
        public final Integer getAnswer_type() {
            return this.answer_type;
        }

        /* renamed from: component5, reason: from getter */
        public final String getAnswer_source() {
            return this.answer_source;
        }

        /* renamed from: component6, reason: from getter */
        public final String getSession_id() {
            return this.session_id;
        }

        /* renamed from: component7, reason: from getter */
        public final String getWork_mode() {
            return this.work_mode;
        }

        /* renamed from: component8, reason: from getter */
        public final String getSkill() {
            return this.skill;
        }

        /* renamed from: component9, reason: from getter */
        public final String getIntention() {
            return this.intention;
        }

        public final LogDataD copy(String question, String answer, Integer question_type, Integer answer_type, String answer_source, String r25, String r26, String skill, String intention, String user_id, String shop_id, String device_id, String create_at, String product_type, String language, String third_party_answer, String voice_file_name) {
            return new LogDataD(question, answer, question_type, answer_type, answer_source, r25, r26, skill, intention, user_id, shop_id, device_id, create_at, product_type, language, third_party_answer, voice_file_name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LogDataD)) {
                return false;
            }
            LogDataD logDataD = (LogDataD) other;
            return Intrinsics.areEqual(this.question, logDataD.question) && Intrinsics.areEqual(this.answer, logDataD.answer) && Intrinsics.areEqual(this.question_type, logDataD.question_type) && Intrinsics.areEqual(this.answer_type, logDataD.answer_type) && Intrinsics.areEqual(this.answer_source, logDataD.answer_source) && Intrinsics.areEqual(this.session_id, logDataD.session_id) && Intrinsics.areEqual(this.work_mode, logDataD.work_mode) && Intrinsics.areEqual(this.skill, logDataD.skill) && Intrinsics.areEqual(this.intention, logDataD.intention) && Intrinsics.areEqual(this.user_id, logDataD.user_id) && Intrinsics.areEqual(this.shop_id, logDataD.shop_id) && Intrinsics.areEqual(this.device_id, logDataD.device_id) && Intrinsics.areEqual(this.create_at, logDataD.create_at) && Intrinsics.areEqual(this.product_type, logDataD.product_type) && Intrinsics.areEqual(this.language, logDataD.language) && Intrinsics.areEqual(this.third_party_answer, logDataD.third_party_answer) && Intrinsics.areEqual(this.voice_file_name, logDataD.voice_file_name);
        }

        public int hashCode() {
            String str = this.question;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.answer;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            Integer num = this.question_type;
            int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
            Integer num2 = this.answer_type;
            int hashCode4 = (hashCode3 + (num2 != null ? num2.hashCode() : 0)) * 31;
            String str3 = this.answer_source;
            int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.session_id;
            int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.work_mode;
            int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.skill;
            int hashCode8 = (hashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
            String str7 = this.intention;
            int hashCode9 = (hashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31;
            String str8 = this.user_id;
            int hashCode10 = (hashCode9 + (str8 != null ? str8.hashCode() : 0)) * 31;
            String str9 = this.shop_id;
            int hashCode11 = (hashCode10 + (str9 != null ? str9.hashCode() : 0)) * 31;
            String str10 = this.device_id;
            int hashCode12 = (hashCode11 + (str10 != null ? str10.hashCode() : 0)) * 31;
            String str11 = this.create_at;
            int hashCode13 = (hashCode12 + (str11 != null ? str11.hashCode() : 0)) * 31;
            String str12 = this.product_type;
            int hashCode14 = (hashCode13 + (str12 != null ? str12.hashCode() : 0)) * 31;
            String str13 = this.language;
            int hashCode15 = (hashCode14 + (str13 != null ? str13.hashCode() : 0)) * 31;
            String str14 = this.third_party_answer;
            int hashCode16 = (hashCode15 + (str14 != null ? str14.hashCode() : 0)) * 31;
            String str15 = this.voice_file_name;
            return hashCode16 + (str15 != null ? str15.hashCode() : 0);
        }

        public String toString() {
            return "LogDataD(question=" + this.question + ", answer=" + this.answer + ", question_type=" + this.question_type + ", answer_type=" + this.answer_type + ", answer_source=" + this.answer_source + ", session_id=" + this.session_id + ", work_mode=" + this.work_mode + ", skill=" + this.skill + ", intention=" + this.intention + ", user_id=" + this.user_id + ", shop_id=" + this.shop_id + ", device_id=" + this.device_id + ", create_at=" + this.create_at + ", product_type=" + this.product_type + ", language=" + this.language + ", third_party_answer=" + this.third_party_answer + ", voice_file_name=" + this.voice_file_name + ")";
        }

        public LogDataD(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
            this.question = str;
            this.answer = str2;
            this.question_type = num;
            this.answer_type = num2;
            this.answer_source = str3;
            this.session_id = str4;
            this.work_mode = str5;
            this.skill = str6;
            this.intention = str7;
            this.user_id = str8;
            this.shop_id = str9;
            this.device_id = str10;
            this.create_at = str11;
            this.product_type = str12;
            this.language = str13;
            this.third_party_answer = str14;
            this.voice_file_name = str15;
        }

        public final String getQuestion() {
            return this.question;
        }

        public final void setQuestion(String str) {
            this.question = str;
        }

        public final String getAnswer() {
            return this.answer;
        }

        public final void setAnswer(String str) {
            this.answer = str;
        }

        public /* synthetic */ LogDataD(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? 0 : num2, (i & 16) != 0 ? "answer_source_unspecified" : str3, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? "" : str5, (i & 128) != 0 ? "" : str6, (i & 256) != 0 ? "" : str7, (i & 512) != 0 ? "" : str8, (i & 1024) != 0 ? "" : str9, (i & 2048) != 0 ? "" : str10, (i & 4096) != 0 ? "" : str11, (i & 8192) != 0 ? "" : str12, (i & 16384) != 0 ? "" : str13, (i & 32768) != 0 ? "" : str14, (i & 65536) != 0 ? "" : str15);
        }

        public final Integer getQuestion_type() {
            return this.question_type;
        }

        public final void setQuestion_type(Integer num) {
            this.question_type = num;
        }

        public final Integer getAnswer_type() {
            return this.answer_type;
        }

        public final void setAnswer_type(Integer num) {
            this.answer_type = num;
        }

        public final String getAnswer_source() {
            return this.answer_source;
        }

        public final void setAnswer_source(String str) {
            this.answer_source = str;
        }

        public final String getSession_id() {
            return this.session_id;
        }

        public final void setSession_id(String str) {
            this.session_id = str;
        }

        public final String getWork_mode() {
            return this.work_mode;
        }

        public final void setWork_mode(String str) {
            this.work_mode = str;
        }

        public final String getSkill() {
            return this.skill;
        }

        public final void setSkill(String str) {
            this.skill = str;
        }

        public final String getIntention() {
            return this.intention;
        }

        public final void setIntention(String str) {
            this.intention = str;
        }

        public final String getUser_id() {
            return this.user_id;
        }

        public final void setUser_id(String str) {
            this.user_id = str;
        }

        public final String getShop_id() {
            return this.shop_id;
        }

        public final void setShop_id(String str) {
            this.shop_id = str;
        }

        public final String getDevice_id() {
            return this.device_id;
        }

        public final void setDevice_id(String str) {
            this.device_id = str;
        }

        public final String getCreate_at() {
            return this.create_at;
        }

        public final void setCreate_at(String str) {
            this.create_at = str;
        }

        public final String getProduct_type() {
            return this.product_type;
        }

        public final void setProduct_type(String str) {
            this.product_type = str;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            this.language = str;
        }

        public final String getThird_party_answer() {
            return this.third_party_answer;
        }

        public final void setThird_party_answer(String str) {
            this.third_party_answer = str;
        }

        public final String getVoice_file_name() {
            return this.voice_file_name;
        }

        public final void setVoice_file_name(String str) {
            this.voice_file_name = str;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$TestData;", "", "code", "", "data", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Data;", "(JLcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Data;)V", "getCode", "()J", "getData", "()Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Data;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class TestData {
        private final long code;
        private final Data data;

        public static /* synthetic */ TestData copy$default(TestData testData, long j, Data data, int i, Object obj) {
            if ((i & 1) != 0) {
                j = testData.code;
            }
            if ((i & 2) != 0) {
                data = testData.data;
            }
            return testData.copy(j, data);
        }

        /* renamed from: component1, reason: from getter */
        public final long getCode() {
            return this.code;
        }

        /* renamed from: component2, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        public final TestData copy(long code, Data data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            return new TestData(code, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TestData)) {
                return false;
            }
            TestData testData = (TestData) other;
            return this.code == testData.code && Intrinsics.areEqual(this.data, testData.data);
        }

        public int hashCode() {
            long j = this.code;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            Data data = this.data;
            return i + (data != null ? data.hashCode() : 0);
        }

        public String toString() {
            return "TestData(code=" + this.code + ", data=" + this.data + ")";
        }

        public TestData(long j, Data data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.code = j;
            this.data = data;
        }

        public final long getCode() {
            return this.code;
        }

        public final Data getData() {
            return this.data;
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$Data;", "", "answer", "", "text", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "getText", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class Data {
        private final String answer;
        private final String text;
        private final String type;

        public static /* synthetic */ Data copy$default(Data data, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = data.answer;
            }
            if ((i & 2) != 0) {
                str2 = data.text;
            }
            if ((i & 4) != 0) {
                str3 = data.type;
            }
            return data.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAnswer() {
            return this.answer;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final Data copy(String answer, String text, String type) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new Data(answer, text, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.answer, data.answer) && Intrinsics.areEqual(this.text, data.text) && Intrinsics.areEqual(this.type, data.type);
        }

        public int hashCode() {
            String str = this.answer;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.text;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.type;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Data(answer=" + this.answer + ", text=" + this.text + ", type=" + this.type + ")";
        }

        public Data(String answer, String text, String type) {
            Intrinsics.checkParameterIsNotNull(answer, "answer");
            Intrinsics.checkParameterIsNotNull(text, "text");
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.answer = answer;
            this.text = text;
            this.type = type;
        }

        public final String getAnswer() {
            return this.answer;
        }

        public final String getText() {
            return this.text;
        }

        public final String getType() {
            return this.type;
        }
    }
}

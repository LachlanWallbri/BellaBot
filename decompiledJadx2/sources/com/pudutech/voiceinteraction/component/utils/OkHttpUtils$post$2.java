package com.pudutech.voiceinteraction.component.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.utils.OkHttpUtils$post$2", m3970f = "OkHttpUtils.kt", m3971i = {0}, m3972l = {133}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class OkHttpUtils$post$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OkHttpUtils.AiVoiceService $avoiceService;
    final /* synthetic */ JsonObject $jsonBuild;
    final /* synthetic */ OkHttpUtils.LogDataD $log;
    final /* synthetic */ String $mac;
    final /* synthetic */ String $text;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7587p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpUtils$post$2(OkHttpUtils.AiVoiceService aiVoiceService, JsonObject jsonObject, OkHttpUtils.LogDataD logDataD, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$avoiceService = aiVoiceService;
        this.$jsonBuild = jsonObject;
        this.$log = logDataD;
        this.$text = str;
        this.$mac = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OkHttpUtils$post$2 okHttpUtils$post$2 = new OkHttpUtils$post$2(this.$avoiceService, this.$jsonBuild, this.$log, this.$text, this.$mac, completion);
        okHttpUtils$post$2.f7587p$ = (CoroutineScope) obj;
        return okHttpUtils$post$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OkHttpUtils$post$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:9|10|(5:15|(8:(2:61|62)(1:18)|19|(5:24|(3:(1:27)(1:55)|28|(4:33|(1:35)(1:54)|36|(2:38|(3:40|(1:42)|43))(2:48|(3:50|(1:52)|53))))|56|(1:58)|59)|60|(0)|56|(0)|59)(3:69|(1:71)|72)|44|45|46)|73|74|(1:76)(1:138)|77|(1:137)(1:81)|82|(2:84|(1:(2:87|(3:(1:124)(3:94|(1:96)(1:123)|97)|98|(2:100|(4:102|(1:104)|(1:108)|109)(4:110|(1:112)|(1:118)(1:116)|117))(3:119|(1:121)|122))(3:125|(1:127)|128))(3:129|(1:131)|132))(3:133|(1:135)|136))|44|45|46) */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x034a, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x034b, code lost:
    
        com.pudutech.base.Pdlog.m3273d(com.pudutech.voiceinteraction.component.utils.OkHttpUtils.INSTANCE.getTAG(), "mac:" + r12.$mac + " JsonSyntaxException:" + r13);
        r0 = com.pudutech.voiceinteraction.component.utils.OkHttpUtils.INSTANCE.getTtsError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0375, code lost:
    
        if (r0 == null) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0377, code lost:
    
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x037a, code lost:
    
        r0.invoke(r13.toString(), r12.$text);
        r12.$log.setAnswer_source("answer_source_xunfei");
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0161 A[Catch: JsonSyntaxException -> 0x0085, Exception -> 0x039a, TryCatch #0 {JsonSyntaxException -> 0x0085, blocks: (B:62:0x0080, B:19:0x0089, B:21:0x00a5, B:27:0x00b3, B:28:0x00bd, B:30:0x00c3, B:33:0x00d1, B:35:0x00d9, B:36:0x00df, B:38:0x00e7, B:40:0x00fe, B:42:0x0106, B:43:0x0109, B:48:0x011e, B:50:0x0135, B:52:0x013d, B:53:0x0140, B:56:0x0159, B:58:0x0161, B:59:0x0164), top: B:61:0x0080, outer: #1 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        OkHttpUtils.CloudSkillRespData cloudSkillRespData;
        Boolean bool;
        OkHttpUtils.FaqAnswer faq_answer;
        OkHttpUtils.FaqAnswer faq_answer2;
        OkHttpUtils.FaqAnswer faq_answer3;
        String question;
        OkHttpUtils.FaqAnswer faq_answer4;
        List<OkHttpUtils.RasaAnswer> rasa_answer;
        boolean z;
        Function2<String, String, Unit> ttsError;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        String str = null;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7587p$;
                OkHttpUtils$post$2$test$1 okHttpUtils$post$2$test$1 = new OkHttpUtils$post$2$test$1(this, null);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = TimeoutKt.withTimeoutOrNull(2000L, okHttpUtils$post$2$test$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            cloudSkillRespData = (OkHttpUtils.CloudSkillRespData) obj;
        } catch (Exception e) {
            Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), "response onFailure " + e.toString());
            Function2<String, String, Unit> ttsError2 = OkHttpUtils.INSTANCE.getTtsError();
            if (ttsError2 == null) {
                Intrinsics.throwNpe();
            }
            ttsError2.invoke(e.toString(), this.$text);
            this.$log.setAnswer_source("answer_source_xunfei");
            OkHttpUtils.INSTANCE.logPost(this.$log);
        }
        if (cloudSkillRespData == null) {
            throw new Exception("res==null");
        }
        Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), "response == " + cloudSkillRespData);
        String answer_source = cloudSkillRespData.getAnswer_source();
        if (!answer_source.equals("answer_source_faq") && !answer_source.equals("answer_source_keyword")) {
            if (answer_source.equals("answer_source_rasa")) {
                if (cloudSkillRespData != null) {
                    try {
                        rasa_answer = cloudSkillRespData.getRasa_answer();
                    } catch (JsonSyntaxException e2) {
                        Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), "mac:" + this.$mac + " JsonSyntaxException:" + e2);
                        Function2<String, String, Unit> ttsError3 = OkHttpUtils.INSTANCE.getTtsError();
                        if (ttsError3 == null) {
                            Intrinsics.throwNpe();
                        }
                        ttsError3.invoke(e2.toString(), this.$text);
                        this.$log.setAnswer_source("answer_source_xunfei");
                    }
                } else {
                    rasa_answer = null;
                }
                this.$log.setAnswer_source("answer_source_rasa");
                this.$log.setShop_id(cloudSkillRespData.getShop_id());
                this.$log.setUser_id(cloudSkillRespData.getUser_id());
                List<OkHttpUtils.RasaAnswer> list = rasa_answer;
                if (list != null && !list.isEmpty()) {
                    z = false;
                    if (!z) {
                        if ((rasa_answer != null ? Boxing.boxInt(rasa_answer.size()) : null).intValue() != 0 && rasa_answer.get(0).getAction_type() != null) {
                            OkHttpUtils.RasaAnswer rasaAnswer = rasa_answer.get(0);
                            if (Intrinsics.areEqual(rasaAnswer != null ? rasaAnswer.getAction_type() : null, "utter")) {
                                this.$log.setAnswer(rasa_answer.get(0).getUtter());
                                if (OkHttpUtils.INSTANCE.getTtsClick() != null) {
                                    Function4<String, String, Integer, String, Unit> ttsClick = OkHttpUtils.INSTANCE.getTtsClick();
                                    if (ttsClick == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    ttsClick.invoke(rasa_answer.get(0).getUtter(), null, Boxing.boxInt(0), this.$text);
                                }
                            } else {
                                this.$log.setAnswer_type(Boxing.boxInt(2));
                                this.$log.setAnswer("");
                                if (OkHttpUtils.INSTANCE.getTtsClick() != null) {
                                    Function4<String, String, Integer, String, Unit> ttsClick2 = OkHttpUtils.INSTANCE.getTtsClick();
                                    if (ttsClick2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    ttsClick2.invoke(rasa_answer.get(0).getUtter(), GsonUtils.toJson(cloudSkillRespData), Boxing.boxInt(2), this.$text);
                                }
                            }
                        }
                    }
                    ttsError = OkHttpUtils.INSTANCE.getTtsError();
                    if (ttsError == null) {
                        Intrinsics.throwNpe();
                    }
                    ttsError.invoke("noanser", this.$text);
                    this.$log.setAnswer_source("answer_source_xunfei");
                }
                z = true;
                if (!z) {
                }
                ttsError = OkHttpUtils.INSTANCE.getTtsError();
                if (ttsError == null) {
                }
                ttsError.invoke("noanser", this.$text);
                this.$log.setAnswer_source("answer_source_xunfei");
            } else {
                Function2<String, String, Unit> ttsError4 = OkHttpUtils.INSTANCE.getTtsError();
                if (ttsError4 == null) {
                    Intrinsics.throwNpe();
                }
                ttsError4.invoke("ansourceType Error", this.$text);
                this.$log.setAnswer_source("answer_source_xunfei");
            }
            OkHttpUtils.INSTANCE.logPost(this.$log);
            return Unit.INSTANCE;
        }
        OkHttpUtils.FaqAnswer faq_answer5 = cloudSkillRespData.getFaq_answer();
        String answer = faq_answer5 != null ? faq_answer5.getAnswer() : null;
        this.$log.setShop_id(cloudSkillRespData.getShop_id());
        this.$log.setUser_id(cloudSkillRespData.getUser_id());
        int intValue = ((cloudSkillRespData == null || (faq_answer4 = cloudSkillRespData.getFaq_answer()) == null) ? null : Boxing.boxInt(faq_answer4.getQuestion_type())).intValue();
        if (OkHttpUtils.INSTANCE.getTtsClick() != null) {
            if (intValue == -1) {
                this.$log.setAnswer(answer);
                this.$log.setAnswer_type(Boxing.boxInt(1));
                this.$log.setAnswer_source("answer_source_faq");
                Function4<String, String, Integer, String, Unit> ttsClick3 = OkHttpUtils.INSTANCE.getTtsClick();
                if (ttsClick3 == null) {
                    Intrinsics.throwNpe();
                }
                ttsClick3.invoke(answer, null, Boxing.boxInt(0), this.$text);
            } else if (intValue == 0) {
                this.$log.setAnswer(answer);
                this.$log.setAnswer_type(Boxing.boxInt(1));
                this.$log.setAnswer_source("answer_source_faq");
                Function4<String, String, Integer, String, Unit> ttsClick4 = OkHttpUtils.INSTANCE.getTtsClick();
                if (ttsClick4 == null) {
                    Intrinsics.throwNpe();
                }
                ttsClick4.invoke(answer, null, Boxing.boxInt(0), this.$text);
            } else if (intValue != 10) {
                if (cloudSkillRespData == null || (faq_answer3 = cloudSkillRespData.getFaq_answer()) == null || (question = faq_answer3.getQuestion()) == null) {
                    bool = null;
                } else {
                    bool = Boxing.boxBoolean(question.length() > 0);
                }
                if (bool.booleanValue()) {
                    this.$log.setAnswer_type(Boxing.boxInt(0));
                    this.$log.setAnswer_source("answer_source_keyword");
                    this.$log.setQuestion_type(Boxing.boxInt(1));
                    this.$log.setAnswer("");
                    if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot")) {
                        Function4<String, String, Integer, String, Unit> ttsClick5 = OkHttpUtils.INSTANCE.getTtsClick();
                        if (ttsClick5 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (cloudSkillRespData != null && (faq_answer2 = cloudSkillRespData.getFaq_answer()) != null) {
                            str = faq_answer2.getQuestion();
                        }
                        ttsClick5.invoke(str, GsonUtils.toJson(cloudSkillRespData), Boxing.boxInt(1), this.$text);
                    } else {
                        Function4<String, String, Integer, String, Unit> ttsClick6 = OkHttpUtils.INSTANCE.getTtsClick();
                        if (ttsClick6 == null) {
                            Intrinsics.throwNpe();
                        }
                        ttsClick6.invoke((cloudSkillRespData == null || (faq_answer = cloudSkillRespData.getFaq_answer()) == null) ? null : faq_answer.getQuestion(), null, Boxing.boxInt(1), this.$text);
                    }
                } else {
                    this.$log.setAnswer(answer);
                    this.$log.setAnswer_type(Boxing.boxInt(1));
                    this.$log.setAnswer_source("answer_source_faq");
                    Function4<String, String, Integer, String, Unit> ttsClick7 = OkHttpUtils.INSTANCE.getTtsClick();
                    if (ttsClick7 == null) {
                        Intrinsics.throwNpe();
                    }
                    ttsClick7.invoke(answer, null, Boxing.boxInt(0), this.$text);
                }
            } else {
                this.$log.setAnswer(answer);
                this.$log.setAnswer_type(Boxing.boxInt(1));
                this.$log.setAnswer_source("answer_source_faq");
                Function4<String, String, Integer, String, Unit> ttsClick8 = OkHttpUtils.INSTANCE.getTtsClick();
                if (ttsClick8 == null) {
                    Intrinsics.throwNpe();
                }
                ttsClick8.invoke(answer, null, Boxing.boxInt(0), this.$text);
            }
        }
        OkHttpUtils.INSTANCE.logPost(this.$log);
        return Unit.INSTANCE;
    }
}

package com.pudutech.bumblebee.robot_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.TtsInfoReq;
import com.pudutech.disinfect.baselib.network.response.TtsInfoResp;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BusinessVoiceVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, m3961d2 = {"<anonymous>", "", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/ViewModelLaunchBuild;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BusinessVoiceVm$loadTtsVoice$1 extends Lambda implements Function1<ViewModelLaunchBuild, Unit> {
    final /* synthetic */ BusinessVoiceVm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BusinessVoiceVm$loadTtsVoice$1(BusinessVoiceVm businessVoiceVm) {
        super(1);
        this.this$0 = businessVoiceVm;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ViewModelLaunchBuild viewModelLaunchBuild) {
        invoke2(viewModelLaunchBuild);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BusinessVoiceVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$loadTtsVoice$1$1", m3970f = "BusinessVoiceVm.kt", m3971i = {0, 0, 0, 0}, m3972l = {57}, m3973m = "invokeSuspend", m3974n = {"$this$job", "mac", "lang", "ttsInfoReq"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$loadTtsVoice$1$1 */
    /* loaded from: classes4.dex */
    public static final class C44071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4973p$;

        C44071(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44071 c44071 = new C44071(completion);
            c44071.f4973p$ = (CoroutineScope) obj;
            return c44071;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String languageConvert;
            List list;
            List list2;
            String str;
            List list3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4973p$;
                String mac = WifiUtil.INSTANCE.getMac();
                if (mac == null) {
                    mac = "";
                }
                languageConvert = BusinessVoiceVm$loadTtsVoice$1.this.this$0.getLanguageConvert();
                TtsInfoReq ttsInfoReq = new TtsInfoReq(Boxing.boxInt(0), Constant.INSTANCE.getShopId(), languageConvert, mac, 0, 16, null);
                NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                this.L$0 = coroutineScope;
                this.L$1 = mac;
                this.L$2 = languageConvert;
                this.L$3 = ttsInfoReq;
                this.label = 1;
                obj = cloudApi.getTtsInfo(ttsInfoReq, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            TtsInfoResp ttsInfoResp = (TtsInfoResp) obj;
            if (ttsInfoResp.getCode() == 0) {
                Map<String, Object> data = ttsInfoResp.getData();
                if (!(data == null || data.isEmpty())) {
                    Map<String, Object> data2 = ttsInfoResp.getData();
                    if (data2 != null) {
                        list3 = BusinessVoiceVm$loadTtsVoice$1.this.this$0._ttsVoiceInfoList;
                        list3.clear();
                        data2.forEach(new BiConsumer<String, Object>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$loadTtsVoice$1$1$invokeSuspend$$inlined$apply$lambda$1
                            @Override // java.util.function.BiConsumer
                            public final void accept(String key, Object u) {
                                String str2;
                                boolean isNumeric;
                                String str3;
                                String str4;
                                List list4;
                                Intrinsics.checkParameterIsNotNull(key, "key");
                                Intrinsics.checkParameterIsNotNull(u, "u");
                                str2 = BusinessVoiceVm$loadTtsVoice$1.this.this$0.TAG;
                                Pdlog.m3273d(str2, "t =" + key + "  u =" + u + ' ');
                                isNumeric = BusinessVoiceVm$loadTtsVoice$1.this.this$0.isNumeric(key);
                                if (isNumeric) {
                                    List<T> fromJsonArray = GsonSingleton.INSTANCE.getINSTANCE().fromJsonArray(GsonSingleton.INSTANCE.getINSTANCE().toJson(u), TtsVoiceInfo.class);
                                    if (fromJsonArray != null) {
                                        list4 = BusinessVoiceVm$loadTtsVoice$1.this.this$0._ttsVoiceInfoList;
                                        list4.addAll(fromJsonArray);
                                        return;
                                    }
                                    return;
                                }
                                if (key.equals("version_code")) {
                                    BusinessVoiceVm$loadTtsVoice$1.this.this$0._curTtsVersion = String.valueOf(u);
                                    str3 = BusinessVoiceVm$loadTtsVoice$1.this.this$0.TAG;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("_curTtsVersion =");
                                    str4 = BusinessVoiceVm$loadTtsVoice$1.this.this$0._curTtsVersion;
                                    sb.append(str4);
                                    Pdlog.m3273d(str3, sb.toString());
                                }
                            }
                        });
                    }
                    String merchantTtsData = VoicePackageHelper.INSTANCE.getMerchantTtsData();
                    if (merchantTtsData == null || merchantTtsData.length() == 0) {
                        list = BusinessVoiceVm$loadTtsVoice$1.this.this$0._ttsVoiceInfoList;
                        if (list.size() <= 0) {
                            BusinessVoiceVm$loadTtsVoice$1.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.loadTtsVoice.1.1.2
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
                                    Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$loadTtsVoice$1.this.this$0.getOnResult();
                                    if (onResult != null) {
                                        onResult.invoke(BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE);
                                    }
                                }
                            });
                            str = BusinessVoiceVm$loadTtsVoice$1.this.this$0.TAG;
                            Pdlog.m3273d(str, "loadTtsVoice() _ttsVoiceInfoList.size is empty");
                        } else {
                            BusinessVoiceVm businessVoiceVm = BusinessVoiceVm$loadTtsVoice$1.this.this$0;
                            list2 = BusinessVoiceVm$loadTtsVoice$1.this.this$0._ttsVoiceInfoList;
                            businessVoiceVm.generateTts(list2);
                        }
                    } else {
                        BusinessVoiceVm$loadTtsVoice$1.this.this$0.localMd5CompareServerMd5();
                        BusinessVoiceVm$loadTtsVoice$1.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.loadTtsVoice.1.1.3
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
                                Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$loadTtsVoice$1.this.this$0.getOnResult();
                                if (onResult != null) {
                                    onResult.invoke(BusinessVoiceVm.TtsResult.ResultSelected.INSTANCE);
                                }
                            }
                        });
                    }
                    BusinessVoiceVm$loadTtsVoice$1.this.this$0._Job = (Job) null;
                    return Unit.INSTANCE;
                }
            }
            BusinessVoiceVm$loadTtsVoice$1.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.loadTtsVoice.1.1.4
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
                    Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$loadTtsVoice$1.this.this$0.getOnResult();
                    if (onResult != null) {
                        onResult.invoke(BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE);
                    }
                }
            });
            BusinessVoiceVm$loadTtsVoice$1.this.this$0._Job = (Job) null;
            return Unit.INSTANCE;
        }
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ViewModelLaunchBuild receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.job(new C44071(null));
        receiver.onError(new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$loadTtsVoice$1.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BusinessVoiceVm$loadTtsVoice$1.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.loadTtsVoice.1.2.1
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
                        Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$loadTtsVoice$1.this.this$0.getOnResult();
                        if (onResult != null) {
                            onResult.invoke(BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE);
                        }
                    }
                });
                BusinessVoiceVm$loadTtsVoice$1.this.this$0._Job = (Job) null;
            }
        });
    }
}

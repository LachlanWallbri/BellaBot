package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.LoRaInfo;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.resources.language.LanguageUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$checkLoRaUpdate$1", m3970f = "CallSettingVM.kt", m3971i = {0, 0, 0}, m3972l = {326}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "languageUtils", "language"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class CallSettingVM$checkLoRaUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4975p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$checkLoRaUpdate$1(CallSettingVM callSettingVM, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$checkLoRaUpdate$1 callSettingVM$checkLoRaUpdate$1 = new CallSettingVM$checkLoRaUpdate$1(this.this$0, completion);
        callSettingVM$checkLoRaUpdate$1.f4975p$ = (CoroutineScope) obj;
        return callSettingVM$checkLoRaUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$checkLoRaUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableLiveData mutableLiveData;
        MutableLiveData mutableLiveData2;
        MutableLiveData mutableLiveData3;
        String firmwareVersion;
        MutableLiveData mutableLiveData4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4975p$;
                LanguageUtils languageUtils = new LanguageUtils(RobotContext.INSTANCE.getContext());
                String str = (languageUtils.getCurrent().getLocale().getLanguage() + "-") + languageUtils.getCurrent().getLocale().getCountry();
                CallSettingRepo callSettingRepo = this.this$0.callRepo;
                String mac = this.this$0.callRepo.mac();
                this.L$0 = coroutineScope;
                this.L$1 = languageUtils;
                this.L$2 = str;
                this.label = 1;
                obj = callSettingRepo.fetchLoRaNewestVersion(mac, str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Version version = (Version) obj;
            if (version != null) {
                LoRaInfo loRaInfo = (LoRaInfo) this.this$0._loraInfo.getValue();
                if (loRaInfo == null || (firmwareVersion = loRaInfo.getFirmwareVersion()) == null || firmwareVersion.equals(version.getVersion_name())) {
                    mutableLiveData3 = this.this$0._toastLD;
                    mutableLiveData3.setValue(Boxing.boxInt(C4188R.string.call_lora_refresh_1));
                } else {
                    mutableLiveData4 = this.this$0._loraServerVersionLD;
                    mutableLiveData4.setValue(version);
                }
            } else {
                mutableLiveData2 = this.this$0._toastLD;
                mutableLiveData2.setValue(Boxing.boxInt(C4188R.string.call_lora_refresh_2));
            }
        } catch (Exception unused) {
            mutableLiveData = this.this$0._toastLD;
            mutableLiveData.setValue(Boxing.boxInt(C4188R.string.call_lora_refresh_2));
        }
        return Unit.INSTANCE;
    }
}

package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.bumblebee.robot_ui.manager.LoRaUpdateState;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.lib_update.module.model.Version;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$startLoRaUpdate$1", m3970f = "CallSettingVM.kt", m3971i = {0, 1, 1, 2, 2, 2, 2}, m3972l = {372, 375, 481}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "file", "$this$launch", "file", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "$this$collect$iv"}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1", "Z$0", "L$2"})
/* loaded from: classes4.dex */
public final class CallSettingVM$startLoRaUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Version $version;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4983p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$startLoRaUpdate$1(CallSettingVM callSettingVM, Version version, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
        this.$version = version;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$startLoRaUpdate$1 callSettingVM$startLoRaUpdate$1 = new CallSettingVM$startLoRaUpdate$1(this.this$0, this.$version, completion);
        callSettingVM$startLoRaUpdate$1.f4983p$ = (CoroutineScope) obj;
        return callSettingVM$startLoRaUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$startLoRaUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c6  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MutableLiveData mutableLiveData;
        CoroutineScope coroutineScope;
        String str;
        boolean booleanValue;
        MutableLiveData mutableLiveData2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f4983p$;
            mutableLiveData = this.this$0._loraUpdateState;
            mutableLiveData.setValue(TuplesKt.m3968to(Boxing.boxInt(0), Boxing.boxInt(0)));
            CallSettingRepo callSettingRepo = this.this$0.callRepo;
            String url = this.$version.getUrl();
            Function1<Integer, Unit> function1 = new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$startLoRaUpdate$1$file$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i2) {
                    MutableLiveData mutableLiveData3;
                    mutableLiveData3 = CallSettingVM$startLoRaUpdate$1.this.this$0._loraUpdateState;
                    mutableLiveData3.setValue(TuplesKt.m3968to(0, Integer.valueOf((int) (i2 * 0.25d))));
                }
            };
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object startDownload = callSettingRepo.startDownload(url, function1, this);
            if (startDownload == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = startDownload;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        boolean z = this.Z$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str2 = (String) this.L$1;
                CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                str = str2;
                coroutineScope = coroutineScope3;
                booleanValue = ((Boolean) obj).booleanValue();
                if (!booleanValue) {
                    Flow<Pair<LoRaUpdateState, Integer>> loraUpdateState = this.this$0.callRepo.loraUpdateState();
                    CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 = new CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1(this);
                    this.L$0 = coroutineScope;
                    this.L$1 = str;
                    this.Z$0 = booleanValue;
                    this.L$2 = loraUpdateState;
                    this.label = 3;
                    if (loraUpdateState.collect(callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    mutableLiveData2 = this.this$0._loraUpdateState;
                    mutableLiveData2.setValue(TuplesKt.m3968to(Boxing.boxInt(6), Boxing.boxInt(0)));
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        String str3 = (String) obj;
        CallSettingRepo callSettingRepo2 = this.this$0.callRepo;
        File file = new File(str3);
        this.L$0 = coroutineScope;
        this.L$1 = str3;
        this.label = 2;
        Object startUpdate = callSettingRepo2.startUpdate(file, this);
        if (startUpdate == coroutine_suspended) {
            return coroutine_suspended;
        }
        str = str3;
        obj = startUpdate;
        booleanValue = ((Boolean) obj).booleanValue();
        if (!booleanValue) {
        }
        return Unit.INSTANCE;
    }
}

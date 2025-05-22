package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import java.util.concurrent.CancellationException;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$trueFetchLinkCode$1", m3970f = "CallSettingVM.kt", m3971i = {0, 1}, m3972l = {265, 266}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
public final class CallSettingVM$trueFetchLinkCode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4984p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$trueFetchLinkCode$1(CallSettingVM callSettingVM, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$trueFetchLinkCode$1 callSettingVM$trueFetchLinkCode$1 = new CallSettingVM$trueFetchLinkCode$1(this.this$0, completion);
        callSettingVM$trueFetchLinkCode$1.f4984p$ = (CoroutineScope) obj;
        return callSettingVM$trueFetchLinkCode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$trueFetchLinkCode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x006c -> B:5:0x0037). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CallSettingVM$trueFetchLinkCode$1 callSettingVM$trueFetchLinkCode$1;
        CoroutineScope coroutineScope;
        MutableLiveData mutableLiveData;
        MutableLiveData mutableLiveData2;
        Object obj2;
        MutableLiveData mutableLiveData3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            try {
            } catch (Exception e) {
                e = e;
                callSettingVM$trueFetchLinkCode$1 = this;
                if (!(e instanceof CancellationException)) {
                }
                return Unit.INSTANCE;
            }
            if (i == 1) {
                MutableLiveData mutableLiveData4 = (MutableLiveData) this.L$1;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object obj3 = coroutine_suspended;
                callSettingVM$trueFetchLinkCode$1 = this;
                mutableLiveData4.setValue(obj);
                callSettingVM$trueFetchLinkCode$1.L$0 = coroutineScope2;
                callSettingVM$trueFetchLinkCode$1.label = 2;
                if (DelayKt.delay(300000L, callSettingVM$trueFetchLinkCode$1) != obj3) {
                    return obj3;
                }
                coroutineScope = coroutineScope2;
                obj2 = obj3;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    try {
                        mutableLiveData3 = callSettingVM$trueFetchLinkCode$1.this$0._openApiCodeLD;
                        CallSettingRepo callSettingRepo = callSettingVM$trueFetchLinkCode$1.this$0.callRepo;
                        callSettingVM$trueFetchLinkCode$1.L$0 = coroutineScope;
                        callSettingVM$trueFetchLinkCode$1.L$1 = mutableLiveData3;
                        callSettingVM$trueFetchLinkCode$1.label = 1;
                        Object fetchLinkCode = callSettingRepo.fetchLinkCode(callSettingVM$trueFetchLinkCode$1);
                        if (fetchLinkCode == obj2) {
                            return obj2;
                        }
                        coroutineScope2 = coroutineScope;
                        obj = fetchLinkCode;
                        obj3 = obj2;
                        mutableLiveData4 = mutableLiveData3;
                        mutableLiveData4.setValue(obj);
                        callSettingVM$trueFetchLinkCode$1.L$0 = coroutineScope2;
                        callSettingVM$trueFetchLinkCode$1.label = 2;
                        if (DelayKt.delay(300000L, callSettingVM$trueFetchLinkCode$1) != obj3) {
                        }
                    } catch (Exception e2) {
                        e = e2;
                        if (!(e instanceof CancellationException)) {
                            mutableLiveData = callSettingVM$trueFetchLinkCode$1.this$0._openApiCodeLD;
                            mutableLiveData.setValue(null);
                            mutableLiveData2 = callSettingVM$trueFetchLinkCode$1.this$0._toastLD;
                            mutableLiveData2.postValue(Boxing.boxInt(C4188R.string.pdStr7_136));
                        }
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4984p$;
        }
        obj2 = coroutine_suspended;
        callSettingVM$trueFetchLinkCode$1 = this;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        return Unit.INSTANCE;
    }
}

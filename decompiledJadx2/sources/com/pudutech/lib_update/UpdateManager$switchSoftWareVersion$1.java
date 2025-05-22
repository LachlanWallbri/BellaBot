package com.pudutech.lib_update;

import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.service.UpdateRequestManager;
import com.pudutech.pd_network.bean.BaseResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$switchSoftWareVersion$1", m3970f = "UpdateManager.kt", m3971i = {0}, m3972l = {106}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class UpdateManager$switchSoftWareVersion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callBack;
    final /* synthetic */ Function1 $onError;
    final /* synthetic */ CheckUpdateRequestParams $req;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5433p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$switchSoftWareVersion$1(CheckUpdateRequestParams checkUpdateRequestParams, Function1 function1, Function1 function12, Continuation continuation) {
        super(2, continuation);
        this.$req = checkUpdateRequestParams;
        this.$callBack = function1;
        this.$onError = function12;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$switchSoftWareVersion$1 updateManager$switchSoftWareVersion$1 = new UpdateManager$switchSoftWareVersion$1(this.$req, this.$callBack, this.$onError, completion);
        updateManager$switchSoftWareVersion$1.f5433p$ = (CoroutineScope) obj;
        return updateManager$switchSoftWareVersion$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$switchSoftWareVersion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5433p$;
                UpdateRequestManager updateRequestManager = UpdateRequestManager.INSTANCE;
                CheckUpdateRequestParams checkUpdateRequestParams = this.$req;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = updateRequestManager.switchSoftWareVersion(checkUpdateRequestParams, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            BaseResponse baseResponse = (BaseResponse) obj;
            if (baseResponse.getData() != null) {
                Function1 function1 = this.$callBack;
                Object data = baseResponse.getData();
                if (data == null) {
                    Intrinsics.throwNpe();
                }
                function1.invoke(data);
            }
        } catch (Exception e) {
            Function1 function12 = this.$onError;
            if (function12 != null) {
            }
        }
        return Unit.INSTANCE;
    }
}

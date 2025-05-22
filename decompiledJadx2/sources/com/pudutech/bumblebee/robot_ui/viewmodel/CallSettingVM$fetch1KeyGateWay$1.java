package com.pudutech.bumblebee.robot_ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.bumblebee.robot_ui.repo.Gateway2;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$fetch1KeyGateWay$1", m3970f = "CallSettingVM.kt", m3971i = {0}, m3972l = {289}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class CallSettingVM$fetch1KeyGateWay$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4977p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$fetch1KeyGateWay$1(CallSettingVM callSettingVM, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$fetch1KeyGateWay$1 callSettingVM$fetch1KeyGateWay$1 = new CallSettingVM$fetch1KeyGateWay$1(this.this$0, completion);
        callSettingVM$fetch1KeyGateWay$1.f4977p$ = (CoroutineScope) obj;
        return callSettingVM$fetch1KeyGateWay$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$fetch1KeyGateWay$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ArrayList emptyList;
        MutableLiveData mutableLiveData;
        MutableLiveData mutableLiveData2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4977p$;
                CallSettingRepo callSettingRepo = this.this$0.callRepo;
                String mac = this.this$0.callRepo.mac();
                String valueOf = String.valueOf(this.this$0.callRepo.shopID());
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = callSettingRepo.fetch1KeyGatewayList(mac, valueOf, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List list = (List) obj;
            if (list == null) {
                emptyList = CollectionsKt.emptyList();
            } else {
                List<Gateway> list2 = list;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (Gateway gateway : list2) {
                    Gateway2 gateway2 = new Gateway2(gateway);
                    String name = gateway.getName();
                    mutableLiveData2 = this.this$0._crtGatewayLD;
                    Gateway gateway3 = (Gateway) mutableLiveData2.getValue();
                    gateway2.setCheck(Intrinsics.areEqual(name, gateway3 != null ? gateway3.getName() : null));
                    arrayList.add(gateway2);
                }
                emptyList = arrayList;
            }
            if (emptyList.isEmpty()) {
                this.this$0.callRepo.setCrtGateway((Gateway) null);
            }
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "fetch1KeyGateWay.error " + Log.getStackTraceString(e));
            emptyList = CollectionsKt.emptyList();
        }
        mutableLiveData = this.this$0._loraGatewayListLD;
        mutableLiveData.setValue(emptyList);
        return Unit.INSTANCE;
    }
}

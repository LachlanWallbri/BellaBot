package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$refreshKeyBtnList$1", m3970f = "CallSettingVM.kt", m3971i = {0}, m3972l = {HttpStatus.SC_REQUEST_TOO_LONG}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class CallSettingVM$refreshKeyBtnList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $withUI;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4979p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$refreshKeyBtnList$1(CallSettingVM callSettingVM, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
        this.$withUI = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$refreshKeyBtnList$1 callSettingVM$refreshKeyBtnList$1 = new CallSettingVM$refreshKeyBtnList$1(this.this$0, this.$withUI, completion);
        callSettingVM$refreshKeyBtnList$1.f4979p$ = (CoroutineScope) obj;
        return callSettingVM$refreshKeyBtnList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$refreshKeyBtnList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableLiveData mutableLiveData;
        MutableLiveData mutableLiveData2;
        String str;
        MutableLiveData mutableLiveData3;
        MutableLiveData mutableLiveData4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4979p$;
                CallSettingRepo callSettingRepo = this.this$0.callRepo;
                String mac = this.this$0.callRepo.mac();
                String valueOf = String.valueOf(this.this$0.callRepo.shopID());
                mutableLiveData2 = this.this$0._crtGatewayLD;
                T value = mutableLiveData2.getValue();
                if (value == 0) {
                    Intrinsics.throwNpe();
                }
                String pid = ((Gateway) value).getPid();
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = callSettingRepo.fetch1KeyGatewayKeyBtnList(mac, valueOf, pid, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<KeyBtnWithDestination> list = (List) obj;
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "refreshKeyBtnList.list > " + list);
            if (this.$withUI) {
                mutableLiveData3 = this.this$0._toastLD;
                mutableLiveData3.setValue(Boxing.boxInt(C4188R.string.loading_tip));
                mutableLiveData4 = this.this$0._crtKeyBtnListLD;
                mutableLiveData4.setValue(list != null ? list : CollectionsKt.emptyList());
            }
            this.this$0.callRepo.setCrtKeyBtnList(list);
            return Unit.INSTANCE;
        } catch (Exception unused) {
            mutableLiveData = this.this$0._toastLD;
            mutableLiveData.setValue(Boxing.boxInt(C4188R.string.pdStr16_104));
            return Unit.INSTANCE;
        }
    }
}

package com.pudutech.peanut.robot_ui.p063ui;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.IotHostReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.ShopBean;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.peanut.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WelComeDialogueActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$getTableNumber$1", m3970f = "WelComeDialogueActivity.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {223, 225}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "mac", "$this$launch", "mac", "resp"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class WelComeDialogueActivity$getTableNumber$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7032p$;
    final /* synthetic */ WelComeDialogueActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WelComeDialogueActivity$getTableNumber$1(WelComeDialogueActivity welComeDialogueActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = welComeDialogueActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WelComeDialogueActivity$getTableNumber$1 welComeDialogueActivity$getTableNumber$1 = new WelComeDialogueActivity$getTableNumber$1(this.this$0, completion);
        welComeDialogueActivity$getTableNumber$1.f7032p$ = (CoroutineScope) obj;
        return welComeDialogueActivity$getTableNumber$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WelComeDialogueActivity$getTableNumber$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007c A[Catch: Exception -> 0x00e5, TryCatch #0 {Exception -> 0x00e5, blocks: (B:7:0x001e, B:14:0x003b, B:16:0x00a4, B:20:0x0047, B:22:0x0070, B:27:0x007c), top: B:2:0x000c }] */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        boolean z;
        Ref.ObjectRef objectRef;
        Object shopId$default;
        CoroutineScope coroutineScope;
        String str3;
        Ref.ObjectRef objectRef2;
        String str4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "getTableGroup " + Log.getStackTraceString(e));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7032p$;
            String mac = WifiUtil.INSTANCE.getMac();
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "initByServer : mac = " + mac);
            String str5 = mac;
            if (str5 != null && str5.length() != 0) {
                z = false;
                if (!z) {
                    objectRef = new Ref.ObjectRef();
                    NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                    IotHostReq iotHostReq = new IotHostReq(mac);
                    this.L$0 = coroutineScope2;
                    this.L$1 = mac;
                    this.L$2 = objectRef;
                    this.L$3 = objectRef;
                    this.label = 1;
                    shopId$default = NetWorkApiManager.CloudApiService.DefaultImpls.getShopId$default(cloudApi, iotHostReq, null, this, 2, null);
                    if (shopId$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope = coroutineScope2;
                    str3 = mac;
                    objectRef2 = objectRef;
                }
                return Unit.INSTANCE;
            }
            z = true;
            if (!z) {
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$3;
        objectRef2 = (Ref.ObjectRef) this.L$2;
        str3 = (String) this.L$1;
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        objectRef = objectRef3;
        shopId$default = obj;
        objectRef.element = (ApiResponse) shopId$default;
        str4 = this.this$0.TAG;
        Pdlog.m3273d(str4, "getTableGroup " + ((ApiResponse) objectRef2.element));
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C55581 c55581 = new C55581(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = str3;
        this.L$2 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c55581, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WelComeDialogueActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$getTableNumber$1$1", m3970f = "WelComeDialogueActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$getTableNumber$1$1 */
    /* loaded from: classes5.dex */
    public static final class C55581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $resp;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7033p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C55581(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$resp = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55581 c55581 = new C55581(this.$resp, completion);
            c55581.f7033p$ = (CoroutineScope) obj;
            return c55581;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C55581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7033p$;
            ShopBean shopBean = (ShopBean) ((ApiResponse) this.$resp.element).getData();
            if (shopBean != null) {
                Constans.INSTANCE.setShopInfo(shopBean);
            }
            return Unit.INSTANCE;
        }
    }
}

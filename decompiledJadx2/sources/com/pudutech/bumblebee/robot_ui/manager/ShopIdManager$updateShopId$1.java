package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot_ui.track.TrackInit;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.IotHostReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.ShopBean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ShopIdManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.manager.ShopIdManager$updateShopId$1", m3970f = "ShopIdManager.kt", m3971i = {0, 0, 1, 1}, m3972l = {41, 52}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "mac", "$this$launch", "mac"}, m3975s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class ShopIdManager$updateShopId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4853p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShopIdManager$updateShopId$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ShopIdManager$updateShopId$1 shopIdManager$updateShopId$1 = new ShopIdManager$updateShopId$1(this.$context, completion);
        shopIdManager$updateShopId$1.f4853p$ = (CoroutineScope) obj;
        return shopIdManager$updateShopId$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShopIdManager$updateShopId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00a8 A[Catch: Exception -> 0x00cb, TRY_LEAVE, TryCatch #1 {Exception -> 0x00cb, blocks: (B:23:0x0082, B:25:0x00a8), top: B:22:0x0082 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0100 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0101 -> B:6:0x003f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ShopIdManager$updateShopId$1 shopIdManager$updateShopId$1;
        Exception e;
        Object obj2;
        String str;
        ShopIdManager$updateShopId$1 shopIdManager$updateShopId$12;
        String str2;
        ApiResponse apiResponse;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4853p$;
            shopIdManager$updateShopId$1 = this;
        } else if (i == 1) {
            str = (String) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                shopIdManager$updateShopId$12 = this;
            } catch (Exception e2) {
                e = e2;
                obj2 = coroutine_suspended;
                shopIdManager$updateShopId$12 = this;
                ShopIdManager shopIdManager = ShopIdManager.INSTANCE;
                str2 = ShopIdManager.TAG;
                Pdlog.m3273d(str2, "updateShopId: " + e);
                String str4 = str;
                CoroutineScope coroutineScope2 = coroutineScope;
                shopIdManager$updateShopId$12.L$0 = coroutineScope2;
                shopIdManager$updateShopId$12.L$1 = str4;
                shopIdManager$updateShopId$12.label = 2;
                if (DelayKt.delay(5000L, shopIdManager$updateShopId$12) == obj2) {
                }
            }
            try {
            } catch (Exception e3) {
                e = e3;
                ShopIdManager shopIdManager2 = ShopIdManager.INSTANCE;
                str2 = ShopIdManager.TAG;
                Pdlog.m3273d(str2, "updateShopId: " + e);
                String str42 = str;
                CoroutineScope coroutineScope22 = coroutineScope;
                shopIdManager$updateShopId$12.L$0 = coroutineScope22;
                shopIdManager$updateShopId$12.L$1 = str42;
                shopIdManager$updateShopId$12.label = 2;
                if (DelayKt.delay(5000L, shopIdManager$updateShopId$12) == obj2) {
                }
            }
            apiResponse = (ApiResponse) obj;
            ShopIdManager shopIdManager3 = ShopIdManager.INSTANCE;
            str3 = ShopIdManager.TAG;
            Pdlog.m3273d(str3, "updateShopId shopBean: " + apiResponse);
            if (apiResponse.isSuccess()) {
                Constant.INSTANCE.setShopBean((ShopBean) apiResponse.getData());
                TrackInit.INSTANCE.updateShopId();
                RobotNewOpenManager.INSTANCE.setShopId(((ShopBean) apiResponse.getData()).getShop_id());
                return Unit.INSTANCE;
            }
            String str422 = str;
            CoroutineScope coroutineScope222 = coroutineScope;
            shopIdManager$updateShopId$12.L$0 = coroutineScope222;
            shopIdManager$updateShopId$12.L$1 = str422;
            shopIdManager$updateShopId$12.label = 2;
            if (DelayKt.delay(5000L, shopIdManager$updateShopId$12) == obj2) {
                return obj2;
            }
            shopIdManager$updateShopId$1 = shopIdManager$updateShopId$12;
            coroutineScope = coroutineScope222;
            coroutine_suspended = obj2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            shopIdManager$updateShopId$1 = this;
            coroutineScope = coroutineScope3;
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            str = WifiUtil.INSTANCE.getMac();
            String str5 = str;
            if (str5 == null || str5.length() == 0) {
                ShopIdManager.INSTANCE.registerNetworkCallback(shopIdManager$updateShopId$1.$context);
                return Unit.INSTANCE;
            }
            try {
            } catch (Exception e4) {
                Object obj3 = coroutine_suspended;
                shopIdManager$updateShopId$12 = shopIdManager$updateShopId$1;
                e = e4;
                obj2 = obj3;
                ShopIdManager shopIdManager22 = ShopIdManager.INSTANCE;
                str2 = ShopIdManager.TAG;
                Pdlog.m3273d(str2, "updateShopId: " + e);
                String str4222 = str;
                CoroutineScope coroutineScope2222 = coroutineScope;
                shopIdManager$updateShopId$12.L$0 = coroutineScope2222;
                shopIdManager$updateShopId$12.L$1 = str4222;
                shopIdManager$updateShopId$12.label = 2;
                if (DelayKt.delay(5000L, shopIdManager$updateShopId$12) == obj2) {
                }
            }
            NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
            IotHostReq iotHostReq = new IotHostReq(str);
            shopIdManager$updateShopId$1.L$0 = coroutineScope;
            shopIdManager$updateShopId$1.L$1 = str;
            shopIdManager$updateShopId$1.label = 1;
            Object shopId = cloudApi.getShopId(iotHostReq, shopIdManager$updateShopId$1);
            if (shopId == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj4 = coroutine_suspended;
            shopIdManager$updateShopId$12 = shopIdManager$updateShopId$1;
            obj = shopId;
            obj2 = obj4;
            apiResponse = (ApiResponse) obj;
            ShopIdManager shopIdManager32 = ShopIdManager.INSTANCE;
            str3 = ShopIdManager.TAG;
            Pdlog.m3273d(str3, "updateShopId shopBean: " + apiResponse);
            if (apiResponse.isSuccess()) {
            }
            String str42222 = str;
            CoroutineScope coroutineScope22222 = coroutineScope;
            shopIdManager$updateShopId$12.L$0 = coroutineScope22222;
            shopIdManager$updateShopId$12.L$1 = str42222;
            shopIdManager$updateShopId$12.label = 2;
            if (DelayKt.delay(5000L, shopIdManager$updateShopId$12) == obj2) {
            }
        } else {
            return Unit.INSTANCE;
        }
    }
}

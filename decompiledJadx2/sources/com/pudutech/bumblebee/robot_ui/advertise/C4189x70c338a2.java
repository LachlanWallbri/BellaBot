package com.pudutech.bumblebee.robot_ui.advertise;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: AdBannerFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/advertise/AdBannerFragment$initData$3$onPageSelected$1$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot_ui.advertise.AdBannerFragment$initData$3$onPageSelected$$inlined$apply$lambda$1 */
/* loaded from: classes2.dex */
final class C4189x70c338a2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4834p$;
    final /* synthetic */ AdBannerFragment$initData$3 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4189x70c338a2(Continuation continuation, AdBannerFragment$initData$3 adBannerFragment$initData$3, int i) {
        super(2, continuation);
        this.this$0 = adBannerFragment$initData$3;
        this.$position$inlined = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4189x70c338a2 c4189x70c338a2 = new C4189x70c338a2(completion, this.this$0, this.$position$inlined);
        c4189x70c338a2.f4834p$ = (CoroutineScope) obj;
        return c4189x70c338a2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4189x70c338a2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BannerAdapter adapter;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f4834p$;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Banner<AdverImageBean, AdverBannerAdapter> mAdverBannerView = this.this$0.this$0.getMAdverBannerView();
        if (mAdverBannerView != null && (adapter = mAdverBannerView.getAdapter()) != null) {
            adapter.notifyItemChanged(this.$position$inlined);
        }
        return Unit.INSTANCE;
    }
}

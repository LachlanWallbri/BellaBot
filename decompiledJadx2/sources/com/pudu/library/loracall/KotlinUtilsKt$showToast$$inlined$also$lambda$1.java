package com.pudu.library.loracall;

import android.content.Context;
import defpackage.ToastUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KotlinUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudu/library/loracall/KotlinUtilsKt$showToast$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KotlinUtilsKt$showToast$$inlined$also$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $content;
    final /* synthetic */ String $this_showToast$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4369p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinUtilsKt$showToast$$inlined$also$lambda$1(Context context, Continuation continuation, String str) {
        super(2, continuation);
        this.$content = context;
        this.$this_showToast$inlined = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        KotlinUtilsKt$showToast$$inlined$also$lambda$1 kotlinUtilsKt$showToast$$inlined$also$lambda$1 = new KotlinUtilsKt$showToast$$inlined$also$lambda$1(this.$content, completion, this.$this_showToast$inlined);
        kotlinUtilsKt$showToast$$inlined$also$lambda$1.f4369p$ = (CoroutineScope) obj;
        return kotlinUtilsKt$showToast$$inlined$also$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((KotlinUtilsKt$showToast$$inlined$also$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4369p$;
        ToastUtils.INSTANCE.showMsg(this.$content, this.$this_showToast$inlined, 1);
        return Unit.INSTANCE;
    }
}

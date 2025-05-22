package com.pudutech.mirsdk.mircore;

import com.pudutech.base.Pdlog;
import java.util.List;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreBinder$reloadPdmap$1", m3970f = "MirCoreBinder.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class MirCoreBinder$reloadPdmap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $defFloorIndex;
    final /* synthetic */ List $floors_map;
    final /* synthetic */ ReloadMapResultListener $listener;
    final /* synthetic */ String $pdmap;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6162p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirCoreBinder$reloadPdmap$1(int i, String str, List list, ReloadMapResultListener reloadMapResultListener, Continuation continuation) {
        super(2, continuation);
        this.$defFloorIndex = i;
        this.$pdmap = str;
        this.$floors_map = list;
        this.$listener = reloadMapResultListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreBinder$reloadPdmap$1 mirCoreBinder$reloadPdmap$1 = new MirCoreBinder$reloadPdmap$1(this.$defFloorIndex, this.$pdmap, this.$floors_map, this.$listener, completion);
        mirCoreBinder$reloadPdmap$1.f6162p$ = (CoroutineScope) obj;
        return mirCoreBinder$reloadPdmap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreBinder$reloadPdmap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6162p$;
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        int i = this.$defFloorIndex;
        String str3 = this.$pdmap;
        if (str3 == null) {
            str3 = "";
        }
        if (mirCoreImpl.reloadMap(i, str3, this.$floors_map)) {
            MirCoreBinder mirCoreBinder = MirCoreBinder.INSTANCE;
            str2 = MirCoreBinder.TAG;
            Pdlog.m3275i(str2, "reload map success");
            this.$listener.reloadMapSuccess();
        } else {
            MirCoreBinder mirCoreBinder2 = MirCoreBinder.INSTANCE;
            str = MirCoreBinder.TAG;
            Pdlog.m3277w(str, "reload map fail");
            this.$listener.reloadMapFail();
        }
        return Unit.INSTANCE;
    }
}

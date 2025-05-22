package com.pudutech.antichannelconflict.location;

import com.pudutech.antichannelconflict.location.PDLocationManager;
import com.pudutech.antichannelconflict.location.util.LocationType;
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

/* compiled from: PDLocationManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.location.PDLocationManager$init$1", m3970f = "PDLocationManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class PDLocationManager$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LocationType $type;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4468p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PDLocationManager$init$1(LocationType locationType, Continuation continuation) {
        super(2, continuation);
        this.$type = locationType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PDLocationManager$init$1 pDLocationManager$init$1 = new PDLocationManager$init$1(this.$type, completion);
        pDLocationManager$init$1.f4468p$ = (CoroutineScope) obj;
        return pDLocationManager$init$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PDLocationManager$init$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4468p$;
        int i = PDLocationManager.WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
        if (i == 1) {
            PDLocationManager.INSTANCE.startLocationFromSDK();
        } else if (i == 2 || i == 3) {
            PDLocationManager.INSTANCE.startLocateFromWifi();
        } else if (i == 4) {
            PDLocationManager.INSTANCE.startLocationFromSDK();
            PDLocationManager.INSTANCE.startLocateFromWifi();
        }
        return Unit.INSTANCE;
    }
}

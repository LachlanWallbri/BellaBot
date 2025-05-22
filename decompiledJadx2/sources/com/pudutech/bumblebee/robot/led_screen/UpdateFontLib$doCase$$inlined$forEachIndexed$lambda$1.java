package com.pudutech.bumblebee.robot.led_screen;

import com.pudutech.bumblebee.robot.protocol_utils.ProtocolUtils;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UpdateFontLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "Lkotlin/UByteArray;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot/led_screen/UpdateFontLib$doCase$2$response$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UByteArray>, Object> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ int $crc;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4784p$;
    final /* synthetic */ UpdateFontLib this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1(int i, Continuation continuation, UpdateFontLib updateFontLib, Continuation continuation2) {
        super(2, continuation);
        this.$crc = i;
        this.this$0 = updateFontLib;
        this.$continuation$inlined = continuation2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1 updateFontLib$doCase$$inlined$forEachIndexed$lambda$1 = new UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1(this.$crc, completion, this.this$0, this.$continuation$inlined);
        updateFontLib$doCase$$inlined$forEachIndexed$lambda$1.f4784p$ = (CoroutineScope) obj;
        return updateFontLib$doCase$$inlined$forEachIndexed$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UByteArray> continuation) {
        return ((UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProtocolUtils protocolUtils;
        byte[] bArr;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4784p$;
            protocolUtils = this.this$0.getProtocolUtils();
            byte[] uByteArray = UByteArrayUtilsKt.toUByteArray(this.$crc);
            bArr = this.this$0.crcCheckout;
            ArraysKt.copyInto(uByteArray, bArr, 2, 0, 4);
            byte protocolHead = this.this$0.getProtocolHead();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = protocolUtils.m4331sendCANAndWaitFaKdhUs(bArr, protocolHead, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}

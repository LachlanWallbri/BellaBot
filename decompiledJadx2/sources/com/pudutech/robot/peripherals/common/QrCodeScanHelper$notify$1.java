package com.pudutech.robot.peripherals.common;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: QrCodeScanHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.QrCodeScanHelper$notify$1", m3970f = "QrCodeScanHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class QrCodeScanHelper$notify$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    final /* synthetic */ QrScanEvent $event;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7306p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QrCodeScanHelper$notify$1(QrScanEvent qrScanEvent, String str, Continuation continuation) {
        super(2, continuation);
        this.$event = qrScanEvent;
        this.$data = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        QrCodeScanHelper$notify$1 qrCodeScanHelper$notify$1 = new QrCodeScanHelper$notify$1(this.$event, this.$data, completion);
        qrCodeScanHelper$notify$1.f7306p$ = (CoroutineScope) obj;
        return qrCodeScanHelper$notify$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((QrCodeScanHelper$notify$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7306p$;
        QrCodeScanHelper qrCodeScanHelper = QrCodeScanHelper.INSTANCE;
        copyOnWriteArrayList = QrCodeScanHelper.listeners;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            ((QrScanTaskListener) it.next()).onQrScanEvent(this.$event, this.$data);
        }
        return Unit.INSTANCE;
    }
}

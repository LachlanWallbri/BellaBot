package com.pudutech.mirsdk.hardware;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, m3961d2 = {"updateMCU", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$Result;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 0, 0, 0, 0}, m3972l = {836}, m3973m = "updateMCU", m3974n = {"this", "hardwareVersions", "vstr", "updateMCUResult", SpeechUtility.TAG_RESOURCE_RESULT, NotificationCompat.CATEGORY_MESSAGE}, m3975s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$4"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$updateMCU$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HardwareInterfaceStub this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$updateMCU$1(HardwareInterfaceStub hardwareInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = hardwareInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateMCU(this);
    }
}

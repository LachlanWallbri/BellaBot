package com.pudutech.mirsdkwrap.lib.robot;

import com.iflytek.speech.UtilityConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DevicesControlHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"restartDevice", "", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper", m3970f = "DevicesControlHelper.kt", m3971i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, m3972l = {153, 154, 155, 156, 157}, m3973m = "restartDevice", m3974n = {"this", UtilityConfig.KEY_DEVICE_INFO, "this", UtilityConfig.KEY_DEVICE_INFO, "this", UtilityConfig.KEY_DEVICE_INFO, "this", UtilityConfig.KEY_DEVICE_INFO, "this", UtilityConfig.KEY_DEVICE_INFO}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class DevicesControlHelper$restartDevice$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DevicesControlHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DevicesControlHelper$restartDevice$1(DevicesControlHelper devicesControlHelper, Continuation continuation) {
        super(continuation);
        this.this$0 = devicesControlHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.restartDevice(null, this);
    }
}

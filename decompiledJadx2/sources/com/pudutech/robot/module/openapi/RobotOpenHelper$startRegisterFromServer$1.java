package com.pudutech.robot.module.openapi;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082@"}, m3961d2 = {"startRegisterFromServer", "", "deviceName", "", "url", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.openapi.RobotOpenHelper", m3970f = "RobotOpenHelper.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, m3972l = {236, DimensionsKt.HDPI}, m3973m = "startRegisterFromServer", m3974n = {"this", "deviceName", "url", OSSConfig.PARAM_REGION, "this", "deviceName", "url", OSSConfig.PARAM_REGION, "tokenData"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes6.dex */
public final class RobotOpenHelper$startRegisterFromServer$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RobotOpenHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenHelper$startRegisterFromServer$1(RobotOpenHelper robotOpenHelper, Continuation continuation) {
        super(continuation);
        this.this$0 = robotOpenHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.startRegisterFromServer(null, null, this);
    }
}

package com.pudutech.mirsdk.sdksafe;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.pudutech.remotemaintenance.config.IoTConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafe.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0082@"}, m3961d2 = {"chkNode", "", "context", "Landroid/content/Context;", "node", "", IoTConfig.PARAM_ERROR_MSG, "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "code", "error", "", "continuation", "Lkotlin/coroutines/Continuation;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.SDKSafe", m3970f = "SDKSafe.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0}, m3972l = {VoiceWakeuperAidl.RES_FROM_CLIENT}, m3973m = "chkNode", m3974n = {"this", "context", "node", IoTConfig.PARAM_ERROR_MSG, "chkApi", "baseUrl", "retrofit", "chkRequest"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* loaded from: classes2.dex */
public final class SDKSafe$chkNode$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SDKSafe this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKSafe$chkNode$1(SDKSafe sDKSafe, Continuation continuation) {
        super(continuation);
        this.this$0 = sDKSafe;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.chkNode(null, null, null, this);
    }
}

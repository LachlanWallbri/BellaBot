package com.pudutech.pd_network.ability;

import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", MqttServiceConstants.TRACE_EXCEPTION, "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.pd_network.ability.SupportAbilityComponent$$special$$inlined$CoroutineExceptionHandler$1 */
/* loaded from: classes6.dex */
public final class C5471xd3fc7690 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public C5471xd3fc7690(CoroutineContext.Key key) {
        super(key);
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext context, Throwable exception) {
        String str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        SupportAbilityComponent supportAbilityComponent = SupportAbilityComponent.INSTANCE;
        str = SupportAbilityComponent.TAG;
        netWorkLog.mo3280i(str, "CoroutineExceptionHandler > ex:" + exception + ' ');
    }
}

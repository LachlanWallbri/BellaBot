package com.pudu.library.loracall;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", MqttServiceConstants.TRACE_EXCEPTION, "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KotlinUtilsKt$createJob$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public KotlinUtilsKt$createJob$$inlined$CoroutineExceptionHandler$1(CoroutineContext.Key key) {
        super(key);
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext context, final Throwable exception) {
        KotlinUtilsKt.log$default(exception, null, new Function0<String>() { // from class: com.pudu.library.loracall.KotlinUtilsKt$createJob$handler$1$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "------------全局协程myScope错误--------------";
            }
        }, 1, null);
        KotlinUtilsKt.log$default(exception, null, new Function0<String>() { // from class: com.pudu.library.loracall.KotlinUtilsKt$createJob$$inlined$CoroutineExceptionHandler$1$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return KotlinUtilsKt.stackTraceToString(exception);
            }
        }, 1, null);
    }
}

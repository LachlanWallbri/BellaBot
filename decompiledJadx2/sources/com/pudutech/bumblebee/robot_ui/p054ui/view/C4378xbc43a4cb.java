package com.pudutech.bumblebee.robot_ui.p054ui.view;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", MqttServiceConstants.TRACE_EXCEPTION, "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot_ui.ui.view.LongDoublePressConstraintLayout$$special$$inlined$CoroutineExceptionHandler$1 */
/* loaded from: classes4.dex */
public final class C4378xbc43a4cb extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ LongDoublePressConstraintLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4378xbc43a4cb(CoroutineContext.Key key, LongDoublePressConstraintLayout longDoublePressConstraintLayout) {
        super(key);
        this.this$0 = longDoublePressConstraintLayout;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext context, Throwable exception) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "coroutineExceptionHandler coroutineContext:" + context + ",throwable:" + exception.getMessage());
    }
}

package com.pudutech.event_tracking.component;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import com.pudutech.event_tracking.IEventTrackingParams;
import com.pudutech.event_tracking.bean.Event;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LifeCycleComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.LifeCycleComponent$crtResumeActivity$1", m3970f = "LifeCycleComponent.kt", m3971i = {0}, m3972l = {43}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class LifeCycleComponent$crtResumeActivity$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5120p$;
    final /* synthetic */ LifeCycleComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LifeCycleComponent$crtResumeActivity$1(LifeCycleComponent lifeCycleComponent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lifeCycleComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LifeCycleComponent$crtResumeActivity$1 lifeCycleComponent$crtResumeActivity$1 = new LifeCycleComponent$crtResumeActivity$1(this.this$0, completion);
        lifeCycleComponent$crtResumeActivity$1.f5120p$ = (CoroutineScope) obj;
        return lifeCycleComponent$crtResumeActivity$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LifeCycleComponent$crtResumeActivity$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ComponentCallbacks2 componentCallbacks2;
        Map<String, Object> emptyMap;
        Activity activity;
        String str;
        Class<?> cls;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f5120p$;
            this.label = 1;
            if (DelayKt.delay(30000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        componentCallbacks2 = this.this$0.crtResumeActivity;
        if (!(componentCallbacks2 instanceof IEventTrackingParams)) {
            componentCallbacks2 = null;
        }
        IEventTrackingParams iEventTrackingParams = (IEventTrackingParams) componentCallbacks2;
        if (iEventTrackingParams == null || (emptyMap = iEventTrackingParams.eventParams(Event.OnStop.INSTANCE)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        LifeCycleComponent lifeCycleComponent = this.this$0;
        Event.OnStop onStop = Event.OnStop.INSTANCE;
        activity = this.this$0.crtResumeActivity;
        if (activity == null || (cls = activity.getClass()) == null || (str = cls.getName()) == null) {
            str = "";
        }
        lifeCycleComponent.onPageChange(onStop, str, emptyMap);
        return Unit.INSTANCE;
    }
}

package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FaceVideoView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$onTouchEvent$1", m3970f = "FaceVideoView.kt", m3971i = {0, 0}, m3972l = {541}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "idx"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes4.dex */
public final class FaceVideoView$onTouchEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4962p$;
    final /* synthetic */ FaceVideoView this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceVideoView$onTouchEvent$1(FaceVideoView faceVideoView, Continuation continuation) {
        super(2, continuation);
        this.this$0 = faceVideoView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FaceVideoView$onTouchEvent$1 faceVideoView$onTouchEvent$1 = new FaceVideoView$onTouchEvent$1(this.this$0, completion);
        faceVideoView$onTouchEvent$1.f4962p$ = (CoroutineScope) obj;
        return faceVideoView$onTouchEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FaceVideoView$onTouchEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003a -> B:5:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        int i;
        FaceVideoView$onTouchEvent$1 faceVideoView$onTouchEvent$1;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4962p$;
            i = 0;
            faceVideoView$onTouchEvent$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            faceVideoView$onTouchEvent$1.this$0.locationLostStopTouchJob = (Job) null;
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            faceVideoView$onTouchEvent$1 = this;
            i++;
            str = faceVideoView$onTouchEvent$1.this$0.TAG;
            Pdlog.m3273d(str, "onTouchEvent : start time " + i);
            if (i >= 5) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C43911(null), 2, null);
                faceVideoView$onTouchEvent$1.this$0.locationLostStopTouchJob = (Job) null;
                return Unit.INSTANCE;
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                faceVideoView$onTouchEvent$1.L$0 = coroutineScope;
                faceVideoView$onTouchEvent$1.I$0 = i;
                faceVideoView$onTouchEvent$1.label = 1;
                if (DelayKt.delay(1000L, faceVideoView$onTouchEvent$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i++;
                str = faceVideoView$onTouchEvent$1.this$0.TAG;
                Pdlog.m3273d(str, "onTouchEvent : start time " + i);
                if (i >= 5) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            faceVideoView$onTouchEvent$1.this$0.locationLostStopTouchJob = (Job) null;
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FaceVideoView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$onTouchEvent$1$1", m3970f = "FaceVideoView.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$onTouchEvent$1$1 */
    /* loaded from: classes4.dex */
    public static final class C43911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4963p$;

        C43911(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43911 c43911 = new C43911(completion);
            c43911.f4963p$ = (CoroutineScope) obj;
            return c43911;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4963p$;
            Function0<Unit> touchLostLocationCancelCallback = FaceVideoView$onTouchEvent$1.this.this$0.getTouchLostLocationCancelCallback();
            if (touchLostLocationCancelCallback != null) {
                touchLostLocationCancelCallback.invoke();
            }
            return Unit.INSTANCE;
        }
    }
}

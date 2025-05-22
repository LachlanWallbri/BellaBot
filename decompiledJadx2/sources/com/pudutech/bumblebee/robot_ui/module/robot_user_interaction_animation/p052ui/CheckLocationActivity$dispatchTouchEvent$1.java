package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CheckLocationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$dispatchTouchEvent$1", m3970f = "CheckLocationActivity.kt", m3971i = {0, 0}, m3972l = {143}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "idx"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes3.dex */
public final class CheckLocationActivity$dispatchTouchEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4856p$;
    final /* synthetic */ CheckLocationActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckLocationActivity$dispatchTouchEvent$1(CheckLocationActivity checkLocationActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = checkLocationActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CheckLocationActivity$dispatchTouchEvent$1 checkLocationActivity$dispatchTouchEvent$1 = new CheckLocationActivity$dispatchTouchEvent$1(this.this$0, completion);
        checkLocationActivity$dispatchTouchEvent$1.f4856p$ = (CoroutineScope) obj;
        return checkLocationActivity$dispatchTouchEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CheckLocationActivity$dispatchTouchEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0038 -> B:5:0x003b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        CheckLocationActivity$dispatchTouchEvent$1 checkLocationActivity$dispatchTouchEvent$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 0;
            coroutineScope = this.f4856p$;
            checkLocationActivity$dispatchTouchEvent$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            checkLocationActivity$dispatchTouchEvent$1 = this;
            i++;
            if (i >= 5) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C42071(null), 2, null);
                return Unit.INSTANCE;
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                checkLocationActivity$dispatchTouchEvent$1.L$0 = coroutineScope;
                checkLocationActivity$dispatchTouchEvent$1.I$0 = i;
                checkLocationActivity$dispatchTouchEvent$1.label = 1;
                if (DelayKt.delay(1000L, checkLocationActivity$dispatchTouchEvent$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i++;
                if (i >= 5) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CheckLocationActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$dispatchTouchEvent$1$1", m3970f = "CheckLocationActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$dispatchTouchEvent$1$1 */
    /* loaded from: classes3.dex */
    public static final class C42071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4857p$;

        C42071(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42071 c42071 = new C42071(completion);
            c42071.f4857p$ = (CoroutineScope) obj;
            return c42071;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4857p$;
            CheckLocationActivity$dispatchTouchEvent$1.this.this$0.jumpNext();
            return Unit.INSTANCE;
        }
    }
}

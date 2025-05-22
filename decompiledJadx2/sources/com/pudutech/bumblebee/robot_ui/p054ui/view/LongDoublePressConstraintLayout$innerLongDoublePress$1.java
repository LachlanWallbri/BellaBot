package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LongDoublePressConstraintLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.LongDoublePressConstraintLayout$innerLongDoublePress$1", m3970f = "LongDoublePressConstraintLayout.kt", m3971i = {0, 0, 1, 1}, m3972l = {95, 98}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "idx", "$this$launch", "idx"}, m3975s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes4.dex */
public final class LongDoublePressConstraintLayout$innerLongDoublePress$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4952p$;
    final /* synthetic */ LongDoublePressConstraintLayout this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LongDoublePressConstraintLayout$innerLongDoublePress$1(LongDoublePressConstraintLayout longDoublePressConstraintLayout, Continuation continuation) {
        super(2, continuation);
        this.this$0 = longDoublePressConstraintLayout;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LongDoublePressConstraintLayout$innerLongDoublePress$1 longDoublePressConstraintLayout$innerLongDoublePress$1 = new LongDoublePressConstraintLayout$innerLongDoublePress$1(this.this$0, completion);
        longDoublePressConstraintLayout$innerLongDoublePress$1.f4952p$ = (CoroutineScope) obj;
        return longDoublePressConstraintLayout$innerLongDoublePress$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LongDoublePressConstraintLayout$innerLongDoublePress$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0045 -> B:12:0x0048). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        LongDoublePressConstraintLayout$innerLongDoublePress$1 longDoublePressConstraintLayout$innerLongDoublePress$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 0;
            coroutineScope = this.f4952p$;
            longDoublePressConstraintLayout$innerLongDoublePress$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                int i3 = this.I$0;
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        i = this.I$0;
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        longDoublePressConstraintLayout$innerLongDoublePress$1 = this;
        i++;
        if (i >= 3) {
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C43811 c43811 = new C43811(null);
            longDoublePressConstraintLayout$innerLongDoublePress$1.L$0 = coroutineScope;
            longDoublePressConstraintLayout$innerLongDoublePress$1.I$0 = i;
            longDoublePressConstraintLayout$innerLongDoublePress$1.label = 2;
            if (BuildersKt.withContext(main, c43811, longDoublePressConstraintLayout$innerLongDoublePress$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            longDoublePressConstraintLayout$innerLongDoublePress$1.L$0 = coroutineScope;
            longDoublePressConstraintLayout$innerLongDoublePress$1.I$0 = i;
            longDoublePressConstraintLayout$innerLongDoublePress$1.label = 1;
            if (DelayKt.delay(1000L, longDoublePressConstraintLayout$innerLongDoublePress$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i++;
            if (i >= 3) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LongDoublePressConstraintLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.LongDoublePressConstraintLayout$innerLongDoublePress$1$1", m3970f = "LongDoublePressConstraintLayout.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.view.LongDoublePressConstraintLayout$innerLongDoublePress$1$1 */
    /* loaded from: classes4.dex */
    public static final class C43811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4953p$;

        C43811(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43811 c43811 = new C43811(completion);
            c43811.f4953p$ = (CoroutineScope) obj;
            return c43811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4953p$;
            LongDoublePressConstraintLayout$innerLongDoublePress$1.this.this$0.isLongDoublePress = true;
            Function1<View, Unit> onLongDoublePressListener = LongDoublePressConstraintLayout$innerLongDoublePress$1.this.this$0.getOnLongDoublePressListener();
            if (onLongDoublePressListener != null) {
                return onLongDoublePressListener.invoke(LongDoublePressConstraintLayout$innerLongDoublePress$1.this.this$0);
            }
            return null;
        }
    }
}

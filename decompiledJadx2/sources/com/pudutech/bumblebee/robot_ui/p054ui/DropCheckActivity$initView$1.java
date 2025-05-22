package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.FallDropHelper;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DropCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DropCheckActivity$initView$1 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ DropCheckActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DropCheckActivity$initView$1(DropCheckActivity dropCheckActivity) {
        super(1);
        this.this$0 = dropCheckActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DropCheckActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$initView$1$1", m3970f = "DropCheckActivity.kt", m3971i = {0, 1}, m3972l = {122, 123}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$initView$1$1 */
    /* loaded from: classes3.dex */
    public static final class C42981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4901p$;

        C42981(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42981 c42981 = new C42981(completion);
            c42981.f4901p$ = (CoroutineScope) obj;
            return c42981;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f4901p$;
                FallDropHelper.INSTANCE.clearDropEvent();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(400L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        Pdlog.m3273d(DropCheckActivity$initView$1.this.this$0.getTAG(), "clearDropEvent 解除防跌落--lockCount：");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.L$0 = coroutineScope;
            this.label = 2;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            Pdlog.m3273d(DropCheckActivity$initView$1.this.this$0.getTAG(), "clearDropEvent 解除防跌落--lockCount：");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DropCheckActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$initView$1$1$1", m3970f = "DropCheckActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$initView$1$1$1, reason: invalid class name */
        /* loaded from: classes3.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f4902p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f4902p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4902p$;
                PeripheralsSceneUtil.INSTANCE.resetLedScreen();
                DropCheckActivity$initView$1.this.this$0.setMIsLoseWay(!CheckLocationHelper.INSTANCE.isLocated());
                if (DropCheckActivity$initView$1.this.this$0.getMIsLoseWay()) {
                    DropCheckActivity$initView$1.this.this$0.openLoseWay();
                } else {
                    DropCheckActivity$initView$1.this.this$0.openDeliverEdit();
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void invoke(boolean z) {
        if (z) {
            this.this$0.isCheckGoto = true;
            BuildersKt__Builders_commonKt.launch$default(this.this$0.getMScopThread(), null, null, new C42981(null), 3, null);
        } else {
            this.this$0.isCheckGoto = false;
            Pdlog.m3274e(this.this$0.getTAG(), "initView-PermissionCheck-Failure");
        }
    }
}

package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.ResAuth;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapListViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1", m3970f = "MapListViewModel.kt", m3971i = {0}, m3972l = {87}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class MapListViewModel$checkEditAuth$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $result;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5282p$;
    final /* synthetic */ MapListViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapListViewModel$checkEditAuth$1(MapListViewModel mapListViewModel, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapListViewModel;
        this.$result = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapListViewModel$checkEditAuth$1 mapListViewModel$checkEditAuth$1 = new MapListViewModel$checkEditAuth$1(this.this$0, this.$result, completion);
        mapListViewModel$checkEditAuth$1.f5282p$ = (CoroutineScope) obj;
        return mapListViewModel$checkEditAuth$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapListViewModel$checkEditAuth$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5282p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            Function1<ResAuth, Unit> function1 = new Function1<ResAuth, Unit>() { // from class: com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResAuth resAuth) {
                    invoke2(resAuth);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResAuth resAuth) {
                    String str;
                    str = MapListViewModel$checkEditAuth$1.this.this$0.TAG;
                    Pdlog.m3274e(str, "checkEditAuth " + resAuth);
                    if (resAuth == null) {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
                    } else if (resAuth.getCode() != 0) {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(null), 3, null);
                    } else {
                        MapListViewModel$checkEditAuth$1.this.$result.invoke(true);
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MapListViewModel.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1$1$1", m3970f = "MapListViewModel.kt", m3971i = {0}, m3972l = {91}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1$1$1, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5283p$;

                    AnonymousClass1(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                        anonymousClass1.f5283p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.L$0 = this.f5283p$;
                            this.label = 1;
                            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        MapListViewModel$checkEditAuth$1.this.this$0.checkEditAuth(MapListViewModel$checkEditAuth$1.this.$result);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MapListViewModel.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1$1$2", m3970f = "MapListViewModel.kt", m3971i = {0}, m3972l = {100}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$checkEditAuth$1$1$2, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5284p$;

                    AnonymousClass2(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
                        anonymousClass2.f5284p$ = (CoroutineScope) obj;
                        return anonymousClass2;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.L$0 = this.f5284p$;
                            this.label = 1;
                            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        MapListViewModel$checkEditAuth$1.this.this$0.checkEditAuth(MapListViewModel$checkEditAuth$1.this.$result);
                        return Unit.INSTANCE;
                    }
                }
            };
            this.L$0 = coroutineScope;
            this.label = 1;
            if (mapUpdateManager.checkEditAuth(function1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

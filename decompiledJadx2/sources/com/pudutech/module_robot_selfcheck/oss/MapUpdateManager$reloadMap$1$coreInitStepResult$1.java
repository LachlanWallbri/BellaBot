package com.pudutech.module_robot_selfcheck.oss;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$reloadMap$1$coreInitStepResult$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class MapUpdateManager$reloadMap$1$coreInitStepResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoreStepType $p0;
    final /* synthetic */ CoreInitState $p1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6760p$;
    final /* synthetic */ MapUpdateManager$reloadMap$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$reloadMap$1$coreInitStepResult$1(MapUpdateManager$reloadMap$1 mapUpdateManager$reloadMap$1, CoreStepType coreStepType, CoreInitState coreInitState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapUpdateManager$reloadMap$1;
        this.$p0 = coreStepType;
        this.$p1 = coreInitState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$reloadMap$1$coreInitStepResult$1 mapUpdateManager$reloadMap$1$coreInitStepResult$1 = new MapUpdateManager$reloadMap$1$coreInitStepResult$1(this.this$0, this.$p0, this.$p1, completion);
        mapUpdateManager$reloadMap$1$coreInitStepResult$1.f6760p$ = (CoroutineScope) obj;
        return mapUpdateManager$reloadMap$1$coreInitStepResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$reloadMap$1$coreInitStepResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6760p$;
        if (this.$p0 == CoreStepType.LoadTopoMap && this.$p1 == CoreInitState.Success) {
            boolean updateMapConfig = RobotMoveManager.INSTANCE.updateMapConfig();
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            str2 = MapUpdateManager.TAG;
            Pdlog.m3274e(str2, Boxing.boxBoolean(updateMapConfig));
            if (updateMapConfig) {
                Function1 function1 = this.this$0.$result;
                if (function1 != null) {
                }
            } else {
                Function1 function12 = this.this$0.$result;
                if (function12 != null) {
                }
            }
            MirSdkManager.INSTANCE.removeLocateListener(this.this$0.$name);
        } else if (this.$p0 == CoreStepType.LoadLocateMap && this.$p1 == CoreInitState.Success) {
            MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
            str = MapUpdateManager.TAG;
            Pdlog.m3274e(str, this.$p0, this.$p1, Boxing.boxBoolean(this.this$0.$isLoadLocateMap.element));
            if (!this.this$0.$isLoadLocateMap.element) {
                this.this$0.$isLoadLocateMap.element = true;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C53841(null), 3, null);
            }
        } else if (this.$p1 == CoreInitState.Fail) {
            Function1 function13 = this.this$0.$result;
            if (function13 != null) {
            }
            MirSdkManager.INSTANCE.removeLocateListener(this.this$0.$name);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$reloadMap$1$coreInitStepResult$1$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$reloadMap$1$coreInitStepResult$1$1 */
    /* loaded from: classes5.dex */
    public static final class C53841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6761p$;

        C53841(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53841 c53841 = new C53841(completion);
            c53841.f6761p$ = (CoroutineScope) obj;
            return c53841;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6761p$;
            RobotMapManager.INSTANCE.loadTopoMapCore(MapUpdateManager$reloadMap$1$coreInitStepResult$1.this.this$0.$name);
            return Unit.INSTANCE;
        }
    }
}

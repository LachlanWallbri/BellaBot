package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl$loadMapAndConfig$scheduleInitJob$1", m3970f = "MirCoreImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MirCoreImpl$loadMapAndConfig$scheduleInitJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $defFloorIndex;
    final /* synthetic */ List $floors_map;
    final /* synthetic */ InitServiceListener $initListener;
    final /* synthetic */ List $initModuleList;
    final /* synthetic */ Ref.ObjectRef $scheduleJsonBytes;
    final /* synthetic */ Ref.ObjectRef $scheduleYamlBytes;
    final /* synthetic */ Ref.ObjectRef $topoMapBytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6171p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirCoreImpl$loadMapAndConfig$scheduleInitJob$1(Ref.ObjectRef objectRef, InitServiceListener initServiceListener, int i, List list, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, List list2, Continuation continuation) {
        super(2, continuation);
        this.$topoMapBytes = objectRef;
        this.$initListener = initServiceListener;
        this.$defFloorIndex = i;
        this.$floors_map = list;
        this.$scheduleJsonBytes = objectRef2;
        this.$scheduleYamlBytes = objectRef3;
        this.$initModuleList = list2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreImpl$loadMapAndConfig$scheduleInitJob$1 mirCoreImpl$loadMapAndConfig$scheduleInitJob$1 = new MirCoreImpl$loadMapAndConfig$scheduleInitJob$1(this.$topoMapBytes, this.$initListener, this.$defFloorIndex, this.$floors_map, this.$scheduleJsonBytes, this.$scheduleYamlBytes, this.$initModuleList, completion);
        mirCoreImpl$loadMapAndConfig$scheduleInitJob$1.f6171p$ = (CoroutineScope) obj;
        return mirCoreImpl$loadMapAndConfig$scheduleInitJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreImpl$loadMapAndConfig$scheduleInitJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean loadTopoMap;
        boolean loadMultiTopoMap;
        boolean loadScheduleConfig;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6171p$;
        if (((byte[]) this.$topoMapBytes.element) != null) {
            loadTopoMap = MirCoreImpl.INSTANCE.loadTopoMap((byte[]) this.$topoMapBytes.element, this.$initListener);
            if (loadTopoMap) {
                loadMultiTopoMap = MirCoreImpl.INSTANCE.loadMultiTopoMap(this.$defFloorIndex, (byte[]) this.$topoMapBytes.element, this.$floors_map, this.$initListener);
                if (loadMultiTopoMap) {
                    loadScheduleConfig = MirCoreImpl.INSTANCE.loadScheduleConfig((byte[]) this.$scheduleJsonBytes.element, (byte[]) this.$scheduleYamlBytes.element, this.$initListener);
                    if (!loadScheduleConfig) {
                        return Unit.INSTANCE;
                    }
                    this.$initModuleList.remove(MirCoreImpl.CoreInitModules.Scheudle);
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        InitServiceListener initServiceListener = this.$initListener;
        if (initServiceListener != null) {
            initServiceListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Fail, "not find topomap in map package");
        }
        return Unit.INSTANCE;
    }
}

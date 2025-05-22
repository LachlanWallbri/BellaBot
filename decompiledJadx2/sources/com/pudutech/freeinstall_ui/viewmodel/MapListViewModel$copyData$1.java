package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
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
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapListViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$copyData$1", m3970f = "MapListViewModel.kt", m3971i = {0}, m3972l = {43}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class MapListViewModel$copyData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MapInfo $item;
    final /* synthetic */ String $name;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5285p$;
    final /* synthetic */ MapListViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapListViewModel$copyData$1(MapListViewModel mapListViewModel, MapInfo mapInfo, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapListViewModel;
        this.$item = mapInfo;
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapListViewModel$copyData$1 mapListViewModel$copyData$1 = new MapListViewModel$copyData$1(this.this$0, this.$item, this.$name, completion);
        mapListViewModel$copyData$1.f5285p$ = (CoroutineScope) obj;
        return mapListViewModel$copyData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapListViewModel$copyData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5285p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            MapInfo mapInfo = this.$item;
            String str = this.$name;
            Function1<MapInfo, Unit> function1 = new Function1<MapInfo, Unit>() { // from class: com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$copyData$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MapInfo mapInfo2) {
                    invoke2(mapInfo2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MapListViewModel.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$copyData$1$1$1", m3970f = "MapListViewModel.kt", m3971i = {0}, m3972l = {48}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$copyData$1$1$1, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ MapInfo $it;
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5286p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(MapInfo mapInfo, Continuation continuation) {
                        super(2, continuation);
                        this.$it = mapInfo;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$it, completion);
                        anonymousClass1.f5286p$ = (CoroutineScope) obj;
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
                            CoroutineScope coroutineScope = this.f5286p$;
                            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                            String mapName = this.$it.getMapName();
                            this.L$0 = coroutineScope;
                            this.label = 1;
                            if (MapUpdateManager.upload$default(mapUpdateManager, mapName, 0, this, 2, null) == coroutine_suspended) {
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

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MapInfo mapInfo2) {
                    if (mapInfo2 != null) {
                        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(MapListViewModel$copyData$1.this.this$0), Dispatchers.getIO(), null, new AnonymousClass1(mapInfo2, null), 2, null);
                    } else {
                        MapListViewModel$copyData$1.this.this$0.isCopySuccess().postValue(false);
                    }
                    MapListViewModel$copyData$1.this.this$0.setCopyValue(mapInfo2);
                }
            };
            this.L$0 = coroutineScope;
            this.label = 1;
            if (mapUpdateManager.mapCopy(mapInfo, function1, str, this) == coroutine_suspended) {
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

package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MapSelectViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapSelectViewModel$jumpMap$1", m3970f = "MapSelectViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class MapSelectViewModel$jumpMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MapInfo $item;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5289p$;
    final /* synthetic */ MapSelectViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapSelectViewModel$jumpMap$1(MapSelectViewModel mapSelectViewModel, MapInfo mapInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapSelectViewModel;
        this.$item = mapInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapSelectViewModel$jumpMap$1 mapSelectViewModel$jumpMap$1 = new MapSelectViewModel$jumpMap$1(this.this$0, this.$item, completion);
        mapSelectViewModel$jumpMap$1.f5289p$ = (CoroutineScope) obj;
        return mapSelectViewModel$jumpMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapSelectViewModel$jumpMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5289p$;
        List<Destination> analyMap = MapingFuntionManager.INSTANCE.analyMap(MapUpdateManager.INSTANCE.getMapFile(this.$item.getMapName()));
        Pdlog.m3274e("JUMP", this.$item);
        List<Destination> list = analyMap;
        if (list == null || list.isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getMain(), null, new C45891(null), 2, null);
        } else {
            Pdlog.m3274e("localClassName", analyMap);
            this.this$0.setMapData(analyMap, this.$item);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapSelectViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapSelectViewModel$jumpMap$1$1", m3970f = "MapSelectViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.freeinstall_ui.viewmodel.MapSelectViewModel$jumpMap$1$1 */
    /* loaded from: classes2.dex */
    public static final class C45891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5290p$;

        C45891(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45891 c45891 = new C45891(completion);
            c45891.f5290p$ = (CoroutineScope) obj;
            return c45891;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5290p$;
            ToastUtils.INSTANCE.showToast("地图未设置业务点", 0);
            return Unit.INSTANCE;
        }
    }
}

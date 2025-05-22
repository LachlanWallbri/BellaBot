package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.oss.UpdateErrorSealed;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadCurrentMap$2", m3970f = "MapUpdatePresenter.kt", m3971i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, m3972l = {192, 199, HttpStatus.SC_PARTIAL_CONTENT, 209}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "$this$withContext", "serverMapList", "localMapList", "currentMap", "$this$withContext", "serverMapList", "localMapList", "currentMap", "$this$withContext", "serverMapList", "localMapList", "currentMap"}, m3975s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes4.dex */
public final class MapUpdatePresenter$uploadCurrentMap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mapName;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4692p$;
    final /* synthetic */ MapUpdatePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdatePresenter$uploadCurrentMap$2(MapUpdatePresenter mapUpdatePresenter, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapUpdatePresenter;
        this.$mapName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdatePresenter$uploadCurrentMap$2 mapUpdatePresenter$uploadCurrentMap$2 = new MapUpdatePresenter$uploadCurrentMap$2(this.this$0, this.$mapName, completion);
        mapUpdatePresenter$uploadCurrentMap$2.f4692p$ = (CoroutineScope) obj;
        return mapUpdatePresenter$uploadCurrentMap$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdatePresenter$uploadCurrentMap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ArrayList arrayList;
        MapInfo mapInfo;
        MapUpdateContract.UpdateState checkCurrentMapUpdateState;
        MapInfo mapInfo2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4692p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = mapUpdateManager.getMapList(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i == 3 || i == 4) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        List list = (List) obj;
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                if (Boxing.boxBoolean(((RobotMapResp) obj2).getStatus() == 1).booleanValue()) {
                    arrayList2.add(obj2);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Pdlog.m3273d(this.this$0.getTAG(), "uploadCurrentMap serverMapList:" + arrayList);
        List<MapInfo> mapList = RobotMapManager.INSTANCE.getMapList();
        Pdlog.m3273d(this.this$0.getTAG(), "uploadCurrentMap localMapList:" + mapList);
        if (mapList != null) {
            Iterator it = mapList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    mapInfo2 = 0;
                    break;
                }
                mapInfo2 = it.next();
                if (Boxing.boxBoolean(Intrinsics.areEqual(((MapInfo) mapInfo2).getMapName(), this.$mapName)).booleanValue()) {
                    break;
                }
            }
            mapInfo = mapInfo2;
        } else {
            mapInfo = null;
        }
        Pdlog.m3273d(this.this$0.getTAG(), "uploadCurrentMap currentMap:" + mapInfo);
        if (mapInfo != null) {
            checkCurrentMapUpdateState = this.this$0.checkCurrentMapUpdateState(mapInfo, arrayList);
            if (checkCurrentMapUpdateState == MapUpdateContract.UpdateState.UPLOAD) {
                Pdlog.m3273d(this.this$0.getTAG(), "uploadCurrentMap upload current map :" + mapInfo + ' ');
                MapUpdatePresenter mapUpdatePresenter = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = arrayList;
                this.L$2 = mapList;
                this.L$3 = mapInfo;
                this.label = 3;
                if (mapUpdatePresenter.uploadMap(mapInfo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Pdlog.m3273d(this.this$0.getTAG(), "uploadCurrentMap no need to upload the current map ");
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C40982 c40982 = new C40982(null);
                this.L$0 = coroutineScope;
                this.L$1 = arrayList;
                this.L$2 = mapList;
                this.L$3 = mapInfo;
                this.label = 4;
                if (BuildersKt.withContext(main, c40982, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        MainCoroutineDispatcher main2 = Dispatchers.getMain();
        C40971 c40971 = new C40971(null);
        this.L$0 = coroutineScope;
        this.L$1 = arrayList;
        this.L$2 = mapList;
        this.L$3 = mapInfo;
        this.label = 2;
        if (BuildersKt.withContext(main2, c40971, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdatePresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadCurrentMap$2$1", m3970f = "MapUpdatePresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadCurrentMap$2$1 */
    /* loaded from: classes4.dex */
    public static final class C40971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4693p$;

        C40971(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C40971 c40971 = new C40971(completion);
            c40971.f4693p$ = (CoroutineScope) obj;
            return c40971;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MapUpdateContract.ViewInterface theView;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4693p$;
            theView = MapUpdatePresenter$uploadCurrentMap$2.this.this$0.getTheView();
            if (theView == null) {
                return null;
            }
            theView.updateFinish(MapUpdateContract.UpdateState.NONE, UpdateErrorSealed.MAP_NOT_EXIT);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdatePresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadCurrentMap$2$2", m3970f = "MapUpdatePresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadCurrentMap$2$2 */
    /* loaded from: classes4.dex */
    public static final class C40982 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4694p$;

        C40982(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C40982 c40982 = new C40982(completion);
            c40982.f4694p$ = (CoroutineScope) obj;
            return c40982;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40982) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MapUpdateContract.ViewInterface theView;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4694p$;
            theView = MapUpdatePresenter$uploadCurrentMap$2.this.this$0.getTheView();
            if (theView == null) {
                return null;
            }
            theView.updateFinish(MapUpdateContract.UpdateState.NONE, UpdateErrorSealed.NOT_ERROR);
            return Unit.INSTANCE;
        }
    }
}

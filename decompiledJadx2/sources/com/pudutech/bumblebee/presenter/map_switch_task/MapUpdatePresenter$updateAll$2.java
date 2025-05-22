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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$updateAll$2", m3970f = "MapUpdatePresenter.kt", m3971i = {0, 1, 2, 2, 2}, m3972l = {146, 153, 170}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "$this$withContext", "$this$withContext", "serverMapList", "localMapList"}, m3975s = {"L$0", "L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class MapUpdatePresenter$updateAll$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineScope $scope;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4690p$;
    final /* synthetic */ MapUpdatePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdatePresenter$updateAll$2(MapUpdatePresenter mapUpdatePresenter, CoroutineScope coroutineScope, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapUpdatePresenter;
        this.$scope = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdatePresenter$updateAll$2 mapUpdatePresenter$updateAll$2 = new MapUpdatePresenter$updateAll$2(this.this$0, this.$scope, completion);
        mapUpdatePresenter$updateAll$2.f4690p$ = (CoroutineScope) obj;
        return mapUpdatePresenter$updateAll$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdatePresenter$updateAll$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01cd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00da  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        boolean z2;
        List list;
        ArrayList arrayList;
        List<MapInfo> mapList;
        MapUpdatePresenter mapUpdatePresenter;
        MapUpdateContract.UpdateState checkCurrentMapUpdateState;
        int i;
        List list2;
        Object obj2;
        int i2;
        List list3;
        int i3;
        int i4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = this.label;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4690p$;
            z = this.this$0.isAllUpdating;
            if (!z) {
                this.this$0.coroutineScope = this.$scope;
                this.this$0.isAllUpdating = true;
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                this.L$0 = coroutineScope;
                this.label = 2;
                obj = mapUpdateManager.getMapList(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                list = (List) obj;
                if (list == null) {
                }
                Pdlog.m3273d(this.this$0.getTAG(), "updateAll serverMapList:" + arrayList);
                mapList = RobotMapManager.INSTANCE.getMapList();
                Pdlog.m3273d(this.this$0.getTAG(), "updateAll localMapList:" + mapList);
                if (mapList != null) {
                }
                mapUpdatePresenter = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = arrayList;
                this.L$2 = mapList;
                this.label = 3;
                if (mapUpdatePresenter.uploadOrDownload(this) == coroutine_suspended) {
                }
            } else {
                String tag = this.this$0.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("updateAll isAllUpdating: ");
                z2 = this.this$0.isAllUpdating;
                sb.append(z2);
                Pdlog.m3273d(tag, sb.toString());
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C40951 c40951 = new C40951(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, c40951, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else {
            if (i5 == 1) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i5 == 2) {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                list = (List) obj;
                if (list == null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj3 : list) {
                        if (Boxing.boxBoolean(((RobotMapResp) obj3).getStatus() == 1).booleanValue()) {
                            arrayList2.add(obj3);
                        }
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = null;
                }
                Pdlog.m3273d(this.this$0.getTAG(), "updateAll serverMapList:" + arrayList);
                mapList = RobotMapManager.INSTANCE.getMapList();
                Pdlog.m3273d(this.this$0.getTAG(), "updateAll localMapList:" + mapList);
                if (mapList != null) {
                    for (MapInfo mapInfo : mapList) {
                        checkCurrentMapUpdateState = this.this$0.checkCurrentMapUpdateState(mapInfo, arrayList);
                        Pdlog.m3273d(this.this$0.getTAG(), "updateAll state: " + checkCurrentMapUpdateState);
                        if (checkCurrentMapUpdateState == MapUpdateContract.UpdateState.UPLOAD) {
                            MapUpdatePresenter mapUpdatePresenter2 = this.this$0;
                            i = mapUpdatePresenter2.needUpdateMapSize;
                            mapUpdatePresenter2.needUpdateMapSize = i + 1;
                            list2 = this.this$0.uploadMapList;
                            list2.add(mapInfo);
                        } else if (checkCurrentMapUpdateState == MapUpdateContract.UpdateState.DOWNLOAD && arrayList != null) {
                            Iterator it = arrayList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    obj2 = null;
                                    break;
                                }
                                obj2 = it.next();
                                if (Boxing.boxBoolean(Intrinsics.areEqual(((RobotMapResp) obj2).getName(), mapInfo.getMapName())).booleanValue()) {
                                    break;
                                }
                            }
                            RobotMapResp robotMapResp = (RobotMapResp) obj2;
                            if (robotMapResp != null) {
                                MapUpdatePresenter mapUpdatePresenter3 = this.this$0;
                                i2 = mapUpdatePresenter3.needUpdateMapSize;
                                mapUpdatePresenter3.needUpdateMapSize = i2 + 1;
                                list3 = this.this$0.downloadMapList;
                                Boxing.boxBoolean(list3.add(robotMapResp));
                            }
                        }
                    }
                }
                mapUpdatePresenter = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = arrayList;
                this.L$2 = mapList;
                this.label = 3;
                if (mapUpdatePresenter.uploadOrDownload(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i5 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        }
        String tag2 = this.this$0.getTAG();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updateAll needUpdateMapSize: ");
        i3 = this.this$0.needUpdateMapSize;
        sb2.append(i3);
        Pdlog.m3273d(tag2, sb2.toString());
        i4 = this.this$0.needUpdateMapSize;
        if (i4 == 0) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$updateAll$2.3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MapUpdateContract.ViewInterface theView;
                    theView = MapUpdatePresenter$updateAll$2.this.this$0.getTheView();
                    if (theView != null) {
                        theView.updateFinish(MapUpdateContract.UpdateState.NONE, UpdateErrorSealed.NOT_ERROR);
                    }
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdatePresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$updateAll$2$1", m3970f = "MapUpdatePresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$updateAll$2$1 */
    /* loaded from: classes4.dex */
    public static final class C40951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4691p$;

        C40951(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C40951 c40951 = new C40951(completion);
            c40951.f4691p$ = (CoroutineScope) obj;
            return c40951;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MapUpdateContract.ViewInterface theView;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4691p$;
            theView = MapUpdatePresenter$updateAll$2.this.this$0.getTheView();
            if (theView == null) {
                return null;
            }
            theView.updateFinish(MapUpdateContract.UpdateState.NONE, UpdateErrorSealed.NOT_ERROR);
            return Unit.INSTANCE;
        }
    }
}

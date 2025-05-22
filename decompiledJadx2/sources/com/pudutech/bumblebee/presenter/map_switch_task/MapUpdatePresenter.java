package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.oss.UpdateErrorSealed;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MapUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J \u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010-H\u0002J\u0011\u0010.\u001a\u00020\u0012H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010/J\u0019\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u00102J\b\u00103\u001a\u00020\u001dH\u0002J\b\u00104\u001a\u00020\u001dH\u0014J\b\u00105\u001a\u00020\u001dH\u0014J\u001b\u00106\u001a\u00020\u001d2\b\u00107\u001a\u0004\u0018\u00010\nH\u0096@ø\u0001\u0000¢\u0006\u0002\u00108J\u0019\u00109\u001a\u00020\u001d2\u0006\u0010:\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010;J\u0019\u0010<\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020(H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0011\u0010>\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010/R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000RS\u0010\u0015\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u001e\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000Ra\u0010 \u001aI\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "currentDownloadMap", "currentUpdateFinishSize", "", "downloadMapList", "", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "haveDownload", "", "isAllUpdating", "needUpdateMapSize", "onErrorListener", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "url", "Lcom/pudutech/bumblebee/business/oss/UpdateErrorSealed;", "errorType", "operateType", "", "onSuccessListener", "Lkotlin/Function2;", "onUpdateProgressListener", "progress", "getOnUpdateProgressListener", "()Lkotlin/jvm/functions/Function3;", "setOnUpdateProgressListener", "(Lkotlin/jvm/functions/Function3;)V", "updateErrorSealed", "uploadMapList", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "checkCurrentMapUpdateState", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateContract$UpdateState;", "mapInfo", "serverMapList", "", "checkMapUpdate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadMap", "robotMapResp", "(Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishAllUpdate", "onViewAttach", "onViewRemoved", "updateAll", "scope", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadCurrentMap", "mapName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadMap", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadOrDownload", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapUpdatePresenter extends BaseOneViewPresenter<MapUpdateContract.ViewInterface> implements MapUpdateContract.PresenterInterface {
    private CoroutineScope coroutineScope;
    private String currentDownloadMap;
    private int currentUpdateFinishSize;
    private boolean haveDownload;
    private volatile boolean isAllUpdating;
    private volatile int needUpdateMapSize;
    private final String TAG = "MapUpdatePresenter";
    private UpdateErrorSealed updateErrorSealed = UpdateErrorSealed.NOT_ERROR;
    private final List<RobotMapResp> downloadMapList = new ArrayList();
    private final List<MapInfo> uploadMapList = new ArrayList();
    private final Function2<String, String, Unit> onSuccessListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onSuccessListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
            invoke2(str, str2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String url, String operateType) {
            boolean z;
            int i;
            int i2;
            boolean z2;
            boolean z3;
            int i3;
            CoroutineScope coroutineScope;
            String str;
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(operateType, "operateType");
            String tag = MapUpdatePresenter.this.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("onSuccessListener url: ");
            sb.append(url);
            sb.append(",operateType: ");
            sb.append(operateType);
            sb.append(",isAllUpdating:");
            z = MapUpdatePresenter.this.isAllUpdating;
            sb.append(z);
            sb.append(",needUpdateMapSize:");
            i = MapUpdatePresenter.this.needUpdateMapSize;
            sb.append(i);
            sb.append(",currentUpdateFinishSize:");
            i2 = MapUpdatePresenter.this.currentUpdateFinishSize;
            sb.append(i2);
            sb.append(",haveDownload:");
            z2 = MapUpdatePresenter.this.haveDownload;
            sb.append(z2);
            Pdlog.m3273d(tag, sb.toString());
            z3 = MapUpdatePresenter.this.isAllUpdating;
            if (z3) {
                MapUpdatePresenter mapUpdatePresenter = MapUpdatePresenter.this;
                i3 = mapUpdatePresenter.currentUpdateFinishSize;
                mapUpdatePresenter.currentUpdateFinishSize = i3 + 1;
                if (Intrinsics.areEqual(operateType, "TYPE_DOWNLOAD")) {
                    MapUpdatePresenter.this.haveDownload = true;
                    str = MapUpdatePresenter.this.currentDownloadMap;
                    if (str != null) {
                        SDK.INSTANCE.reloadMap(str);
                    }
                    MapUpdatePresenter.this.currentDownloadMap = (String) null;
                }
                coroutineScope = MapUpdatePresenter.this.coroutineScope;
                if (coroutineScope != null) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40912(null), 3, null);
                }
                MapUpdatePresenter.this.finishAllUpdate();
                return;
            }
            if (Intrinsics.areEqual(operateType, "TYPE_DOWNLOAD")) {
                MapUpdatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onSuccessListener$1.3
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
                        theView = MapUpdatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.updateFinish(MapUpdateContract.UpdateState.DOWNLOAD, UpdateErrorSealed.NOT_ERROR);
                        }
                    }
                });
            } else {
                MapUpdatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onSuccessListener$1.4
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
                        theView = MapUpdatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.updateFinish(MapUpdateContract.UpdateState.UPLOAD, UpdateErrorSealed.NOT_ERROR);
                        }
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MapUpdatePresenter.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onSuccessListener$1$2", m3970f = "MapUpdatePresenter.kt", m3971i = {0}, m3972l = {56}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
        /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onSuccessListener$1$2 */
        /* loaded from: classes4.dex */
        public static final class C40912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f4688p$;

            C40912(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C40912 c40912 = new C40912(completion);
                c40912.f4688p$ = (CoroutineScope) obj;
                return c40912;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C40912) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4688p$;
                    MapUpdatePresenter mapUpdatePresenter = MapUpdatePresenter.this;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (mapUpdatePresenter.uploadOrDownload(this) == coroutine_suspended) {
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
    };
    private final Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = new Function3<String, UpdateErrorSealed, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onErrorListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(String str, UpdateErrorSealed updateErrorSealed, String str2) {
            invoke2(str, updateErrorSealed, str2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String url, final UpdateErrorSealed errorType, String operateType) {
            boolean z;
            int i;
            boolean z2;
            boolean z3;
            int i2;
            CoroutineScope coroutineScope;
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(errorType, "errorType");
            Intrinsics.checkParameterIsNotNull(operateType, "operateType");
            String tag = MapUpdatePresenter.this.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("onErrorListener url: ");
            sb.append(url);
            sb.append(",errorType: ");
            sb.append(errorType);
            sb.append(",operateType: ");
            sb.append(operateType);
            sb.append("，isAllUpdating:");
            z = MapUpdatePresenter.this.isAllUpdating;
            sb.append(z);
            sb.append(",currentUpdateFinishSize:");
            i = MapUpdatePresenter.this.currentUpdateFinishSize;
            sb.append(i);
            sb.append(",haveDownload:");
            z2 = MapUpdatePresenter.this.haveDownload;
            sb.append(z2);
            Pdlog.m3273d(tag, sb.toString());
            z3 = MapUpdatePresenter.this.isAllUpdating;
            if (z3) {
                MapUpdatePresenter mapUpdatePresenter = MapUpdatePresenter.this;
                i2 = mapUpdatePresenter.currentUpdateFinishSize;
                mapUpdatePresenter.currentUpdateFinishSize = i2 + 1;
                MapUpdatePresenter.this.updateErrorSealed = errorType;
                coroutineScope = MapUpdatePresenter.this.coroutineScope;
                if (coroutineScope != null) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40891(null), 3, null);
                }
                MapUpdatePresenter.this.finishAllUpdate();
                return;
            }
            MapUpdatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onErrorListener$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    theView = MapUpdatePresenter.this.getTheView();
                    if (theView != null) {
                        theView.updateFinish(MapUpdateContract.UpdateState.NONE, errorType);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MapUpdatePresenter.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onErrorListener$1$1", m3970f = "MapUpdatePresenter.kt", m3971i = {0}, m3972l = {80}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
        /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onErrorListener$1$1 */
        /* loaded from: classes4.dex */
        public static final class C40891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f4687p$;

            C40891(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C40891 c40891 = new C40891(completion);
                c40891.f4687p$ = (CoroutineScope) obj;
                return c40891;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C40891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4687p$;
                    MapUpdatePresenter mapUpdatePresenter = MapUpdatePresenter.this;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (mapUpdatePresenter.uploadOrDownload(this) == coroutine_suspended) {
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
    };
    private Function3<? super String, ? super String, ? super String, Unit> onUpdateProgressListener = new Function3<String, String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onUpdateProgressListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, String str3) {
            invoke2(str, str2, str3);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String url, String progress, String operateType) {
            int i;
            int i2;
            int i3;
            int i4;
            boolean z;
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(progress, "progress");
            Intrinsics.checkParameterIsNotNull(operateType, "operateType");
            try {
                double parseDouble = Double.parseDouble(progress);
                i = MapUpdatePresenter.this.currentUpdateFinishSize;
                double d = parseDouble + (i * 100.0d);
                i2 = MapUpdatePresenter.this.needUpdateMapSize;
                final double d2 = d / i2;
                String tag = MapUpdatePresenter.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("onUpdateProgressListener p:");
                sb.append(d2);
                sb.append(",progress:");
                sb.append(progress);
                sb.append(",needUpdateMapSize:");
                i3 = MapUpdatePresenter.this.needUpdateMapSize;
                sb.append(i3);
                sb.append(",currentUpdateFinishSize:");
                i4 = MapUpdatePresenter.this.currentUpdateFinishSize;
                sb.append(i4);
                sb.append(",url:");
                sb.append(url);
                sb.append(' ');
                Pdlog.m3273d(tag, sb.toString());
                z = MapUpdatePresenter.this.isAllUpdating;
                if (z) {
                    MapUpdatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$onUpdateProgressListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            theView = MapUpdatePresenter.this.getTheView();
                            if (theView != null) {
                                theView.updateProgress((int) d2);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Pdlog.m3274e(MapUpdatePresenter.this.getTAG(), "onUpdateProgressListener : " + e.getMessage());
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    public final Function3<String, String, String, Unit> getOnUpdateProgressListener() {
        return this.onUpdateProgressListener;
    }

    public final void setOnUpdateProgressListener(Function3<? super String, ? super String, ? super String, Unit> function3) {
        this.onUpdateProgressListener = function3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAllUpdate() {
        if (this.currentUpdateFinishSize == this.needUpdateMapSize) {
            final UpdateErrorSealed updateErrorSealed = this.updateErrorSealed;
            this.currentUpdateFinishSize = 0;
            this.needUpdateMapSize = 0;
            this.updateErrorSealed = UpdateErrorSealed.NOT_ERROR;
            Pdlog.m3273d(getTAG(), "finishAllUpdate getLocateCase: " + SDK.INSTANCE.getLocateCase());
            this.isAllUpdating = false;
            if (this.haveDownload) {
                this.haveDownload = false;
                runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$finishAllUpdate$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        theView = MapUpdatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.updateFinish(MapUpdateContract.UpdateState.DOWNLOAD, updateErrorSealed);
                        }
                    }
                });
            } else {
                runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$finishAllUpdate$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        theView = MapUpdatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.updateFinish(MapUpdateContract.UpdateState.UPLOAD, updateErrorSealed);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        MapUpdateManager.INSTANCE.setOnSuccessListener(this.onSuccessListener);
        MapUpdateManager.INSTANCE.setOnErrorListener(this.onErrorListener);
        MapUpdateManager.INSTANCE.setUpdateProgressListener(this.onUpdateProgressListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        MapUpdateManager.INSTANCE.setOnSuccessListener((Function2) null);
        Function3<? super String, ? super UpdateErrorSealed, ? super String, Unit> function3 = (Function3) null;
        MapUpdateManager.INSTANCE.setOnErrorListener(function3);
        MapUpdateManager.INSTANCE.setUpdateProgressListener(function3);
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.coroutineScope = (CoroutineScope) null;
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract.PresenterInterface
    public Object updateAll(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MapUpdatePresenter$updateAll$2(this, coroutineScope, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object uploadOrDownload(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MapUpdatePresenter$uploadOrDownload$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract.PresenterInterface
    public Object uploadCurrentMap(String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MapUpdatePresenter$uploadCurrentMap$2(this, str, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract.PresenterInterface
    public Object checkMapUpdate(Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MapUpdatePresenter$checkMapUpdate$2(this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final MapUpdateContract.UpdateState checkCurrentMapUpdateState(MapInfo mapInfo, List<RobotMapResp> serverMapList) {
        RobotMapResp robotMapResp = null;
        if (serverMapList != null) {
            Iterator<T> it = serverMapList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((RobotMapResp) next).getName(), mapInfo.getMapName())) {
                    robotMapResp = next;
                    break;
                }
            }
            robotMapResp = robotMapResp;
        }
        Pdlog.m3273d(getTAG(), "checkCurrentMapUpdateState serverMap:" + robotMapResp + ' ');
        if (robotMapResp == null) {
            return MapUpdateContract.UpdateState.UPLOAD;
        }
        String encryptMD5File2String = EncryptUtils.encryptMD5File2String(MapUpdateManager.INSTANCE.getMapFile(mapInfo.getMapName()));
        Pdlog.m3273d(getTAG(), "checkCurrentMapUpdateState serverMap v:" + robotMapResp.getLv() + ",mapInfo v:" + mapInfo.getMapVersion() + ",serverMap md5:" + robotMapResp.getMd5() + "，local MD5:" + encryptMD5File2String);
        if (robotMapResp.getLv() > mapInfo.getMapVersion() || (robotMapResp.getLv() == mapInfo.getMapVersion() && (!Intrinsics.areEqual(robotMapResp.getMd5(), encryptMD5File2String)))) {
            Pdlog.m3273d(getTAG(), "checkCurrentMapUpdateState: need to download");
            return MapUpdateContract.UpdateState.DOWNLOAD;
        }
        if (robotMapResp.getLv() == mapInfo.getMapVersion() && Intrinsics.areEqual(robotMapResp.getMd5(), encryptMD5File2String)) {
            Pdlog.m3273d(getTAG(), "checkCurrentMapUpdateState: no need to download and upload");
            return MapUpdateContract.UpdateState.NONE;
        }
        Pdlog.m3273d(getTAG(), "checkCurrentMapUpdateState: need to upload");
        return MapUpdateContract.UpdateState.UPLOAD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object downloadMap(RobotMapResp robotMapResp, Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "downloadMap robotMapResp: " + robotMapResp);
        Object downloadOne = MapUpdateManager.INSTANCE.downloadOne(robotMapResp, continuation);
        return downloadOne == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? downloadOne : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object uploadMap(MapInfo mapInfo, Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "uploadMap mapInfo: " + mapInfo);
        Object upload = MapUpdateManager.INSTANCE.upload(mapInfo, continuation);
        return upload == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? upload : Unit.INSTANCE;
    }
}

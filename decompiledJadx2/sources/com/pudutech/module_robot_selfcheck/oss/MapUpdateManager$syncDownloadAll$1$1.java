package com.pudutech.module_robot_selfcheck.oss;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.module_robot_selfcheck.ExtandsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$syncDownloadAll$1$1", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0}, m3972l = {113}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "downloadPath", "realObjectKey"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
final class MapUpdateManager$syncDownloadAll$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RobotMapResp $it;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6762p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$syncDownloadAll$1$1(RobotMapResp robotMapResp, Continuation continuation) {
        super(2, continuation);
        this.$it = robotMapResp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$syncDownloadAll$1$1 mapUpdateManager$syncDownloadAll$1$1 = new MapUpdateManager$syncDownloadAll$1$1(this.$it, completion);
        mapUpdateManager$syncDownloadAll$1$1.f6762p$ = (CoroutineScope) obj;
        return mapUpdateManager$syncDownloadAll$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$syncDownloadAll$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OssService ossService;
        OssService ossService2;
        OssService ossService3;
        OssService ossService4;
        OssService ossService5;
        OssService ossService6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6762p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            ossService = MapUpdateManager.ossService;
            String name = this.$it.getName();
            String downloadPath = ossService.getDownloadPath(name != null ? ExtandsKt.encodeMapName(name) : null);
            MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
            ossService2 = MapUpdateManager.ossService;
            String realObjectKey = ossService2.getRealObjectKey(this.$it.getUrl());
            MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
            ossService3 = MapUpdateManager.ossService;
            ossService3.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$syncDownloadAll$1$1.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                    invoke2(str, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String url, String progress) {
                    Intrinsics.checkParameterIsNotNull(url, "url");
                    Intrinsics.checkParameterIsNotNull(progress, "progress");
                    Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        updateProgressListener.invoke(url, progress, "TYPE_DOWNLOAD");
                    }
                }
            });
            MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
            ossService4 = MapUpdateManager.ossService;
            ossService4.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$syncDownloadAll$1$1.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    MapUpdateManager mapUpdateManager5 = MapUpdateManager.INSTANCE;
                    str = MapUpdateManager.TAG;
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    Pdlog.m3274e(str, "syncDownloadAll = onSuccessListener", currentThread.getName(), MapUpdateManager.INSTANCE.getOnSuccessListener());
                    Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.invoke(it, "TYPE_DOWNLOAD");
                    }
                }
            });
            MapUpdateManager mapUpdateManager5 = MapUpdateManager.INSTANCE;
            ossService5 = MapUpdateManager.ossService;
            ossService5.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$syncDownloadAll$1$1.3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                    invoke2(str, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String url, String str) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(url, "url");
                    MapUpdateManager mapUpdateManager6 = MapUpdateManager.INSTANCE;
                    str2 = MapUpdateManager.TAG;
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    Pdlog.m3274e(str2, "syncDownloadAll = onErrorListener", currentThread.getName(), MapUpdateManager.INSTANCE.getOnErrorListener());
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(url, UpdateErrorSealed.UNKNOW_ERROR, "TYPE_DOWNLOAD");
                    }
                }
            });
            MapUpdateManager mapUpdateManager6 = MapUpdateManager.INSTANCE;
            ossService6 = MapUpdateManager.ossService;
            String url = this.$it.getUrl();
            this.L$0 = coroutineScope;
            this.L$1 = downloadPath;
            this.L$2 = realObjectKey;
            this.label = 1;
            if (ossService6.downloadFile(realObjectKey, downloadPath, url, this) == coroutine_suspended) {
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

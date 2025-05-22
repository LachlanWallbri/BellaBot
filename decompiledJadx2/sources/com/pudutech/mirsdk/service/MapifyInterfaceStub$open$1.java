package com.pudutech.mirsdk.service;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.mircore.InitMappingServiceListener;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mirhardware.MappingHardware;
import com.pudutech.mirsdk.service.MapifyInterfaceStub;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MapifyService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.service.MapifyInterfaceStub$open$1", m3970f = "MapifyService.kt", m3971i = {0, 1}, m3972l = {94, 97}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class MapifyInterfaceStub$open$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $result;
    final /* synthetic */ Ref.ObjectRef $state;
    final /* synthetic */ Ref.ObjectRef $step;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6463p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapifyInterfaceStub$open$1(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Continuation continuation) {
        super(2, continuation);
        this.$state = objectRef;
        this.$result = objectRef2;
        this.$step = objectRef3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapifyInterfaceStub$open$1 mapifyInterfaceStub$open$1 = new MapifyInterfaceStub$open$1(this.$state, this.$result, this.$step, completion);
        mapifyInterfaceStub$open$1.f6463p$ = (CoroutineScope) obj;
        return mapifyInterfaceStub$open$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapifyInterfaceStub$open$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x008b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        MappingHardware mappingHardware;
        Context context;
        String str;
        String str2;
        AIDLConnection aIDLConnection;
        Context context2;
        String str3;
        AIDLConnection aIDLConnection2;
        MirMappingCoreInterface mirMappingCoreInterface;
        File file;
        String str4;
        String str5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6463p$;
            MapifyInterfaceStub mapifyInterfaceStub = MapifyInterfaceStub.INSTANCE;
            mappingHardware = MapifyInterfaceStub.mappingHardware;
            MapifyInterfaceStub mapifyInterfaceStub2 = MapifyInterfaceStub.INSTANCE;
            context = MapifyInterfaceStub.context;
            if (context == null) {
                Intrinsics.throwNpe();
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = AIDLConnection.connect$default(mappingHardware, context, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    if (!((Boolean) obj).booleanValue()) {
                        MapifyInterfaceStub mapifyInterfaceStub3 = MapifyInterfaceStub.INSTANCE;
                        str5 = MapifyInterfaceStub.TAG;
                        Pdlog.m3273d(str5, "MapifyService MapServiceConnection  connect success ");
                    } else {
                        MapifyInterfaceStub mapifyInterfaceStub4 = MapifyInterfaceStub.INSTANCE;
                        str3 = MapifyInterfaceStub.TAG;
                        Pdlog.m3273d(str3, "MapifyService MapServiceConnection  connect faile ");
                    }
                    MapifyInterfaceStub mapifyInterfaceStub5 = MapifyInterfaceStub.INSTANCE;
                    aIDLConnection2 = MapifyInterfaceStub.coreService;
                    mirMappingCoreInterface = (MirMappingCoreInterface) aIDLConnection2.getInterface();
                    if (mirMappingCoreInterface != null) {
                        mirMappingCoreInterface.initModules(new InitMappingServiceListener.Stub() { // from class: com.pudutech.mirsdk.service.MapifyInterfaceStub$open$1.1
                            /* JADX WARN: Multi-variable type inference failed */
                            /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
                            @Override // com.pudutech.mirsdk.mircore.InitMappingServiceListener
                            public void initMappingCoreServiceState(MappingCoreInitStep p0, MappingCoreInitState p1, String p2) {
                                String str6;
                                if (p0 != 0 && MapifyInterfaceStub.WhenMappings.$EnumSwitchMapping$0[p0.ordinal()] == 1) {
                                    if (p1 == MappingCoreInitState.Success) {
                                        MapifyInterfaceStub$open$1.this.$state.element = p1;
                                    }
                                    MapifyInterfaceStub mapifyInterfaceStub6 = MapifyInterfaceStub.INSTANCE;
                                    str6 = MapifyInterfaceStub.TAG;
                                    Pdlog.m3273d(str6, "MapifyService  MapServiceConnection init Finish");
                                } else if (p1 == MappingCoreInitState.Fail) {
                                    MapifyInterfaceStub$open$1.this.$state.element = p1;
                                    Ref.ObjectRef objectRef = MapifyInterfaceStub$open$1.this.$result;
                                    objectRef.element = ((String) objectRef.element) + p2;
                                }
                                MapifyInterfaceStub$open$1.this.$step.element = p0;
                            }
                        });
                    }
                    file = new File(MapFilePathConfig.STATIC_MAP_DEPTH);
                    if (file.exists()) {
                        MapifyInterfaceStub mapifyInterfaceStub6 = MapifyInterfaceStub.INSTANCE;
                        str4 = MapifyInterfaceStub.TAG;
                        Pdlog.m3273d(str4, "staticMapsdepth deleteRecursively");
                        FilesKt.deleteRecursively(file);
                        file.mkdirs();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            MapifyInterfaceStub mapifyInterfaceStub7 = MapifyInterfaceStub.INSTANCE;
            str2 = MapifyInterfaceStub.TAG;
            Pdlog.m3273d(str2, "mappingHardware  MapServiceConnection connect success");
            MapifyInterfaceStub mapifyInterfaceStub8 = MapifyInterfaceStub.INSTANCE;
            aIDLConnection = MapifyInterfaceStub.coreService;
            MapifyInterfaceStub mapifyInterfaceStub9 = MapifyInterfaceStub.INSTANCE;
            context2 = MapifyInterfaceStub.context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            this.L$0 = coroutineScope;
            this.label = 2;
            obj = AIDLConnection.connect$default(aIDLConnection, context2, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!((Boolean) obj).booleanValue()) {
            }
            MapifyInterfaceStub mapifyInterfaceStub52 = MapifyInterfaceStub.INSTANCE;
            aIDLConnection2 = MapifyInterfaceStub.coreService;
            mirMappingCoreInterface = (MirMappingCoreInterface) aIDLConnection2.getInterface();
            if (mirMappingCoreInterface != null) {
            }
            file = new File(MapFilePathConfig.STATIC_MAP_DEPTH);
            if (file.exists()) {
            }
            return Unit.INSTANCE;
        }
        MapifyInterfaceStub mapifyInterfaceStub10 = MapifyInterfaceStub.INSTANCE;
        str = MapifyInterfaceStub.TAG;
        Pdlog.m3273d(str, "mappingHardware  MapServiceConnection connect fail ");
        return Unit.INSTANCE;
    }
}

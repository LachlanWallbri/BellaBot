package com.pudutech.mirsdkwrap.lib.map;

import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMapDrawPath$1", m3970f = "RobotMapManager.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2}, m3972l = {566, 567, 573}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "mc", "mapP", "loader", "$this$launch", "mc", "mapP", "loader", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class RobotMapManager$getMapDrawPath$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    final /* synthetic */ String $mapName;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6512p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotMapManager$getMapDrawPath$1(String str, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$mapName = str;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMapManager$getMapDrawPath$1 robotMapManager$getMapDrawPath$1 = new RobotMapManager$getMapDrawPath$1(this.$mapName, this.$cb, completion);
        robotMapManager$getMapDrawPath$1.f6512p$ = (CoroutineScope) obj;
        return robotMapManager$getMapDrawPath$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMapManager$getMapDrawPath$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.pudutech.mirsdkwrap.lib.map.MapDrawPathLoader, T] */
    /* JADX WARN: Type inference failed for: r9v5, types: [T, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        SDKInterface sDKInterface;
        MapPackageConfig pdmapNameList;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        List<MapListConfig> list;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object obj2 = this.label;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (obj2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6512p$;
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            sDKInterface = RobotMapManager.mirSdk;
            pdmapNameList = sDKInterface != null ? sDKInterface.getPdmapNameList() : null;
            objectRef = new Ref.ObjectRef();
            objectRef.element = "";
            if (pdmapNameList != null && (list = pdmapNameList.getList()) != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    Map<String, String> list2 = ((MapListConfig) it.next()).getList();
                    if (list2 != null) {
                        for (Map.Entry<String, String> entry : list2.entrySet()) {
                            if (Intrinsics.areEqual(entry.getKey(), this.$mapName)) {
                                objectRef.element = entry.getValue();
                            }
                        }
                    }
                }
            }
            objectRef2 = new Ref.ObjectRef();
            objectRef2.element = new MapDrawPathLoader(this.$mapName, (String) objectRef.element);
            MapDrawPathLoader mapDrawPathLoader = (MapDrawPathLoader) objectRef2.element;
            this.L$0 = coroutineScope;
            this.L$1 = pdmapNameList;
            this.L$2 = objectRef;
            this.L$3 = objectRef2;
            this.label = 1;
            if (mapDrawPathLoader.loadMap$module_robot_mirsdk_wrapper_release(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (obj2 == 1) {
            Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            pdmapNameList = (MapPackageConfig) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                objectRef2 = objectRef3;
                coroutineScope = coroutineScope2;
            } catch (Exception e2) {
                e = e2;
                obj2 = coroutineScope2;
                RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
                str = RobotMapManager.TAG;
                Pdlog.m3274e(str, "getMapDrawPath : " + Log.getStackTraceString(e));
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C53263 c53263 = new C53263(null);
                this.L$0 = obj2;
                this.L$1 = e;
                this.label = 3;
                if (BuildersKt.withContext(main, c53263, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else {
            if (obj2 == 2) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (obj2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        MainCoroutineDispatcher main2 = Dispatchers.getMain();
        C53252 c53252 = new C53252(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = pdmapNameList;
        this.L$2 = objectRef;
        this.L$3 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main2, c53252, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMapDrawPath$1$2", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMapDrawPath$1$2 */
    /* loaded from: classes6.dex */
    public static final class C53252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $loader;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6513p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53252(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$loader = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53252 c53252 = new C53252(this.$loader, completion);
            c53252.f6513p$ = (CoroutineScope) obj;
            return c53252;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            HashMap hashMap;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6513p$;
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            hashMap = RobotMapManager.mapDrawPathM;
            hashMap.put(RobotMapManager$getMapDrawPath$1.this.$mapName, (MapDrawPathLoader) this.$loader.element);
            RobotMapManager$getMapDrawPath$1.this.$cb.invoke((MapDrawPathLoader) this.$loader.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMapDrawPath$1$3", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMapDrawPath$1$3 */
    /* loaded from: classes6.dex */
    public static final class C53263 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6514p$;

        C53263(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53263 c53263 = new C53263(completion);
            c53263.f6514p$ = (CoroutineScope) obj;
            return c53263;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53263) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6514p$;
            RobotMapManager$getMapDrawPath$1.this.$cb.invoke(null);
            return Unit.INSTANCE;
        }
    }
}

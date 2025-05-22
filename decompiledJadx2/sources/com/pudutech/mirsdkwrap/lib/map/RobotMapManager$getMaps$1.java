package com.pudutech.mirsdkwrap.lib.map;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMaps$1", m3970f = "RobotMapManager.kt", m3971i = {0, 0, 0, 0}, m3972l = {729}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "l", "defFloor", "filter"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes6.dex */
public final class RobotMapManager$getMaps$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6515p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotMapManager$getMaps$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMapManager$getMaps$1 robotMapManager$getMaps$1 = new RobotMapManager$getMaps$1(this.$cb, completion);
        robotMapManager$getMaps$1.f6515p$ = (CoroutineScope) obj;
        return robotMapManager$getMaps$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMapManager$getMaps$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.ArrayList] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SDKInterface sDKInterface;
        SDKInterface sDKInterface2;
        MapPackageConfig pdmapNameList;
        MapPackageConfig pdmapNameList2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6515p$;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new ArrayList();
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            sDKInterface = RobotMapManager.mirSdk;
            String def_floor = (sDKInterface == null || (pdmapNameList2 = sDKInterface.getPdmapNameList()) == null) ? null : pdmapNameList2.getDef_floor();
            RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
            sDKInterface2 = RobotMapManager.mirSdk;
            List<MapListConfig> list = (sDKInterface2 == null || (pdmapNameList = sDKInterface2.getPdmapNameList()) == null) ? null : pdmapNameList.getList();
            if (list == null) {
                Intrinsics.throwNpe();
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list) {
                if (Boxing.boxBoolean(StringsKt.equals$default(((MapListConfig) obj2).getFloor(), def_floor, false, 2, null)).booleanValue()) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = arrayList;
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Map<String, String> list2 = ((MapListConfig) it.next()).getList();
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                for (Map.Entry<String, String> entry : list2.entrySet()) {
                    ArrayList arrayList3 = (ArrayList) objectRef.element;
                    String key = entry.getKey();
                    if (key == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    arrayList3.add(key);
                }
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C53272 c53272 = new C53272(objectRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.L$2 = def_floor;
            this.L$3 = arrayList2;
            this.label = 1;
            if (BuildersKt.withContext(main, c53272, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMaps$1$2", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getMaps$1$2 */
    /* loaded from: classes6.dex */
    public static final class C53272 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $l */
        final /* synthetic */ Ref.ObjectRef f6516$l;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6517p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53272(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.f6516$l = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53272 c53272 = new C53272(this.f6516$l, completion);
            c53272.f6517p$ = (CoroutineScope) obj;
            return c53272;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53272) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6517p$;
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            str = RobotMapManager.TAG;
            Pdlog.m3273d(str, "getMaps " + ((ArrayList) this.f6516$l.element));
            RobotMapManager$getMaps$1.this.$cb.invoke((ArrayList) this.f6516$l.element);
            return Unit.INSTANCE;
        }
    }
}

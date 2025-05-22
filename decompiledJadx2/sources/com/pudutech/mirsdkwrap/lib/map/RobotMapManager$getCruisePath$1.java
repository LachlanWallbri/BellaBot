package com.pudutech.mirsdkwrap.lib.map;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getCruisePath$1", m3970f = "RobotMapManager.kt", m3971i = {0, 0, 0, 0, 1, 1}, m3972l = {TypedValues.Motion.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 618}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "mc", "mapP", "loader", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class RobotMapManager$getCruisePath$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    final /* synthetic */ String $defaultPdmap;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6506p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotMapManager$getCruisePath$1(String str, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$defaultPdmap = str;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMapManager$getCruisePath$1 robotMapManager$getCruisePath$1 = new RobotMapManager$getCruisePath$1(this.$defaultPdmap, this.$cb, completion);
        robotMapManager$getCruisePath$1.f6506p$ = (CoroutineScope) obj;
        return robotMapManager$getCruisePath$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMapManager$getCruisePath$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.pudutech.mirsdkwrap.lib.map.MapCruisePathLoader, T] */
    /* JADX WARN: Type inference failed for: r8v11, types: [T, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        SDKInterface sDKInterface;
        List<MapListConfig> list;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6506p$;
                RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
                sDKInterface = RobotMapManager.mirSdk;
                MapPackageConfig pdmapNameList = sDKInterface != null ? sDKInterface.getPdmapNameList() : null;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = "";
                if (pdmapNameList != null && (list = pdmapNameList.getList()) != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        Map<String, String> list2 = ((MapListConfig) it.next()).getList();
                        if (list2 != null) {
                            for (Map.Entry<String, String> entry : list2.entrySet()) {
                                if (Intrinsics.areEqual(entry.getKey(), this.$defaultPdmap)) {
                                    objectRef.element = entry.getValue();
                                }
                            }
                        }
                    }
                }
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = new MapCruisePathLoader(this.$defaultPdmap, (String) objectRef.element);
                ((MapCruisePathLoader) objectRef2.element).load$module_robot_mirsdk_wrapper_release();
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C53212 c53212 = new C53212(objectRef2, null);
                this.L$0 = coroutineScope;
                this.L$1 = pdmapNameList;
                this.L$2 = objectRef;
                this.L$3 = objectRef2;
                this.label = 1;
                if (BuildersKt.withContext(main, c53212, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (r1 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (r1 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e) {
            RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
            str = RobotMapManager.TAG;
            Pdlog.m3274e(str, "getCruisePath : " + Log.getStackTraceString(e));
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C53223 c53223 = new C53223(null);
            this.L$0 = r1;
            this.L$1 = e;
            this.label = 2;
            if (BuildersKt.withContext(main2, c53223, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getCruisePath$1$2", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getCruisePath$1$2 */
    /* loaded from: classes6.dex */
    public static final class C53212 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $loader;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6507p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53212(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$loader = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53212 c53212 = new C53212(this.$loader, completion);
            c53212.f6507p$ = (CoroutineScope) obj;
            return c53212;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53212) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            HashMap hashMap;
            String str;
            HashMap hashMap2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6507p$;
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            hashMap = RobotMapManager.mapCruiseLineM;
            hashMap.put(RobotMapManager$getCruisePath$1.this.$defaultPdmap, (MapCruisePathLoader) this.$loader.element);
            RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
            str = RobotMapManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("getCruisePath launch mapCruiseLineM = ");
            RobotMapManager robotMapManager3 = RobotMapManager.INSTANCE;
            hashMap2 = RobotMapManager.mapCruiseLineM;
            sb.append(hashMap2);
            Pdlog.m3273d(str, sb.toString());
            RobotMapManager$getCruisePath$1.this.$cb.invoke((MapCruisePathLoader) this.$loader.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getCruisePath$1$3", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$getCruisePath$1$3 */
    /* loaded from: classes6.dex */
    public static final class C53223 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6508p$;

        C53223(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53223 c53223 = new C53223(completion);
            c53223.f6508p$ = (CoroutineScope) obj;
            return c53223;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53223) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6508p$;
            RobotMapManager$getCruisePath$1.this.$cb.invoke(null);
            return Unit.INSTANCE;
        }
    }
}

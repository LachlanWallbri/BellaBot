package com.pudutech.event_tracking.component;

import android.os.Environment;
import android.os.StatFs;
import com.pudutech.event_tracking.custom.StorageEvent;
import java.io.File;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: StorageEventComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\r\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0007\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/StorageEventComponent;", "", "()V", "externalPath", "", "kotlin.jvm.PlatformType", "internalPath", "onStorageEventLis", "Lkotlin/Function1;", "Lcom/pudutech/event_tracking/custom/StorageEvent;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onEvent", "()Lkotlin/Unit;", "lis", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class StorageEventComponent {
    private final String externalPath;
    private final String internalPath;
    private Function1<? super StorageEvent, Unit> onStorageEventLis;
    private final CoroutineScope scope;

    public StorageEventComponent() {
        File dataDirectory = Environment.getDataDirectory();
        Intrinsics.checkExpressionValueIsNotNull(dataDirectory, "Environment.getDataDirectory()");
        this.internalPath = dataDirectory.getPath();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        this.externalPath = externalStorageDirectory.getPath();
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C44561(null), 3, null);
    }

    /* compiled from: StorageEventComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.StorageEventComponent$1", m3970f = "StorageEventComponent.kt", m3971i = {0, 1}, m3972l = {16, 18}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.event_tracking.component.StorageEventComponent$1 */
    /* loaded from: classes5.dex */
    static final class C44561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5122p$;

        C44561(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44561 c44561 = new C44561(completion);
            c44561.f5122p$ = (CoroutineScope) obj;
            return c44561;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0059 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005a -> B:7:0x0030). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C44561 c44561;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5122p$;
            } else if (i == 1) {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object obj3 = coroutine_suspended;
                c44561 = this;
                StorageEventComponent.this.onEvent();
                c44561.L$0 = coroutineScope2;
                c44561.label = 2;
                if (DelayKt.delay(3300000L, c44561) != obj3) {
                    return obj3;
                }
                CoroutineScope coroutineScope3 = coroutineScope2;
                obj2 = obj3;
                coroutineScope = coroutineScope3;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    c44561.L$0 = coroutineScope;
                    c44561.label = 1;
                    if (DelayKt.delay(300000L, c44561) == obj2) {
                        return obj2;
                    }
                    Object obj4 = obj2;
                    coroutineScope2 = coroutineScope;
                    obj3 = obj4;
                    StorageEventComponent.this.onEvent();
                    c44561.L$0 = coroutineScope2;
                    c44561.label = 2;
                    if (DelayKt.delay(3300000L, c44561) != obj3) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope4;
            }
            obj2 = coroutine_suspended;
            c44561 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Unit onEvent() {
        long j;
        long j2;
        Function1<? super StorageEvent, Unit> function1 = this.onStorageEventLis;
        if (function1 == null) {
            return null;
        }
        StatFs statFs = new StatFs(this.internalPath);
        long blockSizeLong = statFs.getBlockSizeLong() * statFs.getBlockCountLong();
        long availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        if (Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted")) {
            StatFs statFs2 = new StatFs(this.externalPath);
            j = statFs2.getBlockSizeLong() * statFs2.getBlockCountLong();
            j2 = statFs2.getAvailableBlocksLong() * statFs2.getBlockSizeLong();
        } else {
            j = 0;
            j2 = 0;
        }
        function1.invoke(new StorageEvent(blockSizeLong, availableBlocksLong, j, j2));
        return Unit.INSTANCE;
    }

    public final void onStorageEventLis(Function1<? super StorageEvent, Unit> lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.onStorageEventLis = lis;
    }
}

package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.internal.CombineKt;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__MigrationKt$combineLatest$$inlined$combine$2<R> implements Flow<R> {
    final /* synthetic */ Flow[] $flows$inlined;
    final /* synthetic */ Function5 $transform$inlined$1;

    public FlowKt__MigrationKt$combineLatest$$inlined$combine$2(Flow[] flowArr, Function5 function5) {
        this.$flows$inlined = flowArr;
        this.$transform$inlined$1 = function5;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object combineInternal = CombineKt.combineInternal(flowCollector, this.$flows$inlined, new C79062(), new C79073(null, this), continuation);
        return combineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? combineInternal : Unit.INSTANCE;
    }

    public Object collect$$forInline(FlowCollector flowCollector, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__MigrationKt$combineLatest$$inlined$combine$2.this.collect(null, this);
            }
        };
        InlineMarker.mark(5);
        Flow[] flowArr = this.$flows$inlined;
        C79062 c79062 = new C79062();
        C79073 c79073 = new C79073(null, this);
        InlineMarker.mark(0);
        CombineKt.combineInternal(flowCollector, flowArr, c79062, c79073, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: Zip.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000®\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\b"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "invoke", "()[Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$1", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2$lambda$1", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2$2"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$2 */
    /* loaded from: classes2.dex */
    public static final class C79062 extends Lambda implements Function0<Object[]> {
        public C79062() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object[] invoke() {
            return new Object[FlowKt__MigrationKt$combineLatest$$inlined$combine$2.this.$flows$inlined.length];
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: Zip.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2$lambda$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2$3"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$3", m3970f = "Migration.kt", m3971i = {0, 0, 0, 0, 1, 1}, m3972l = {323, 324}, m3973m = "invokeSuspend", m3974n = {"$receiver", "it", "continuation", "args", "$receiver", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$3 */
    /* loaded from: classes2.dex */
    public static final class C79073 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* renamed from: p$ */
        private FlowCollector f8842p$;
        private Object[] p$0;
        final /* synthetic */ FlowKt__MigrationKt$combineLatest$$inlined$combine$2 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C79073(Continuation continuation, FlowKt__MigrationKt$combineLatest$$inlined$combine$2 flowKt__MigrationKt$combineLatest$$inlined$combine$2) {
            super(3, continuation);
            this.this$0 = flowKt__MigrationKt$combineLatest$$inlined$combine$2;
        }

        public final Continuation<Unit> create(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
            C79073 c79073 = new C79073(continuation, this.this$0);
            c79073.f8842p$ = flowCollector;
            c79073.p$0 = objArr;
            return c79073;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object[] objArr, Continuation<? super Unit> continuation) {
            return ((C79073) create((FlowCollector) obj, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector flowCollector = this.f8842p$;
            Object[] objArr = this.p$0;
            Function5 function5 = this.this$0.$transform$inlined$1;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Object obj5 = objArr[3];
            InlineMarker.mark(0);
            Object invoke = function5.invoke(obj2, obj3, obj4, obj5, this);
            InlineMarker.mark(1);
            InlineMarker.mark(0);
            flowCollector.emit(invoke, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            FlowCollector flowCollector2;
            Object[] objArr;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = this.f8842p$;
                Object[] objArr2 = this.p$0;
                Function5 function5 = this.this$0.$transform$inlined$1;
                Object obj2 = objArr2[0];
                Object obj3 = objArr2[1];
                Object obj4 = objArr2[2];
                Object obj5 = objArr2[3];
                this.L$0 = flowCollector;
                this.L$1 = objArr2;
                this.L$2 = this;
                this.L$3 = objArr2;
                this.L$4 = flowCollector;
                this.label = 1;
                Object invoke = function5.invoke(obj2, obj3, obj4, obj5, this);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector2 = flowCollector;
                objArr = objArr2;
                obj = invoke;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$4;
                objArr = (Object[]) this.L$1;
                flowCollector2 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector2;
            this.L$1 = objArr;
            this.label = 2;
            if (flowCollector.emit(obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}

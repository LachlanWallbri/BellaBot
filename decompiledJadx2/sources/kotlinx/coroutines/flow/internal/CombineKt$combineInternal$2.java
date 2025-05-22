package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.selects.SelectBuilderImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Combine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", m3970f = "Combine.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {143}, m3973m = "invokeSuspend", m3974n = {"$this$coroutineScope", "size", "channels", "latestValues", "isClosed", "nonClosed", "remainingNulls"}, m3975s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes2.dex */
public final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $arrayFactory;
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ FlowCollector $this_combineInternal;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f8880p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_combineInternal = flowCollector;
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, continuation);
        combineKt$combineInternal$2.f8880p$ = (CoroutineScope) obj;
        return combineKt$combineInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x016c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x009d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x016d -> B:5:0x017d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ReceiveChannel[] receiveChannelArr;
        Object[] objArr;
        Boolean[] boolArr;
        Ref.IntRef intRef;
        Ref.IntRef intRef2;
        CombineKt$combineInternal$2 combineKt$combineInternal$2;
        CoroutineScope coroutineScope;
        int i;
        Object obj2;
        ReceiveChannel asFairChannel;
        SelectBuilderImpl selectBuilderImpl;
        CombineKt$combineInternal$2 combineKt$combineInternal$22;
        CombineKt$combineInternal$2 combineKt$combineInternal$23;
        CoroutineScope coroutineScope2;
        int i2;
        ReceiveChannel[] receiveChannelArr2;
        Object[] objArr2;
        Boolean[] boolArr2;
        Ref.IntRef intRef3;
        Ref.IntRef intRef4;
        Object result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        int i4 = 0;
        int i5 = 1;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f8880p$;
            int length = this.$flows.length;
            ReceiveChannel[] receiveChannelArr3 = new ReceiveChannel[length];
            for (int i6 = 0; i6 < length; i6++) {
                asFairChannel = CombineKt.asFairChannel(coroutineScope3, this.$flows[Boxing.boxInt(i6).intValue()]);
                receiveChannelArr3[i6] = asFairChannel;
            }
            Object[] objArr3 = new Object[length];
            Boolean[] boolArr3 = new Boolean[length];
            for (int i7 = 0; i7 < length; i7++) {
                Boxing.boxInt(i7).intValue();
                boolArr3[i7] = Boxing.boxBoolean(false);
            }
            Ref.IntRef intRef5 = new Ref.IntRef();
            intRef5.element = length;
            Ref.IntRef intRef6 = new Ref.IntRef();
            intRef6.element = length;
            receiveChannelArr = receiveChannelArr3;
            objArr = objArr3;
            boolArr = boolArr3;
            intRef = intRef5;
            intRef2 = intRef6;
            combineKt$combineInternal$2 = this;
            coroutineScope = coroutineScope3;
            i = length;
            obj2 = coroutine_suspended;
            if (intRef.element != 0) {
            }
        } else if (i3 == 1) {
            Ref.IntRef intRef7 = (Ref.IntRef) this.L$5;
            Ref.IntRef intRef8 = (Ref.IntRef) this.L$4;
            Boolean[] boolArr4 = (Boolean[]) this.L$3;
            Object[] objArr4 = (Object[]) this.L$2;
            ReceiveChannel[] receiveChannelArr4 = (ReceiveChannel[]) this.L$1;
            int i8 = this.I$0;
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            intRef2 = intRef7;
            intRef = intRef8;
            boolArr = boolArr4;
            objArr = objArr4;
            receiveChannelArr = receiveChannelArr4;
            obj2 = coroutine_suspended;
            combineKt$combineInternal$2 = this;
            i = i8;
            coroutineScope = coroutineScope4;
            i4 = 0;
            i5 = 1;
            if (intRef.element != 0) {
                combineKt$combineInternal$2.L$0 = coroutineScope;
                combineKt$combineInternal$2.I$0 = i;
                combineKt$combineInternal$2.L$1 = receiveChannelArr;
                combineKt$combineInternal$2.L$2 = objArr;
                combineKt$combineInternal$2.L$3 = boolArr;
                combineKt$combineInternal$2.L$4 = intRef;
                combineKt$combineInternal$2.L$5 = intRef2;
                combineKt$combineInternal$2.L$6 = combineKt$combineInternal$2;
                combineKt$combineInternal$2.label = i5;
                CombineKt$combineInternal$2 combineKt$combineInternal$24 = combineKt$combineInternal$2;
                SelectBuilderImpl selectBuilderImpl2 = new SelectBuilderImpl(combineKt$combineInternal$24);
                try {
                } catch (Throwable th) {
                    th = th;
                    selectBuilderImpl = selectBuilderImpl2;
                    combineKt$combineInternal$22 = combineKt$combineInternal$24;
                    combineKt$combineInternal$23 = combineKt$combineInternal$2;
                    coroutineScope2 = coroutineScope;
                    i2 = i;
                    receiveChannelArr2 = receiveChannelArr;
                    objArr2 = objArr;
                    boolArr2 = boolArr;
                    intRef3 = intRef;
                }
                SelectBuilderImpl selectBuilderImpl3 = selectBuilderImpl2;
                int i9 = i4;
                while (i9 < i) {
                    boolean booleanValue = boolArr[i9].booleanValue();
                    ReceiveChannel receiveChannel = receiveChannelArr[i9];
                    int i10 = i9;
                    selectBuilderImpl = selectBuilderImpl2;
                    combineKt$combineInternal$22 = combineKt$combineInternal$24;
                    combineKt$combineInternal$23 = combineKt$combineInternal$2;
                    coroutineScope2 = coroutineScope;
                    i2 = i;
                    receiveChannelArr2 = receiveChannelArr;
                    objArr2 = objArr;
                    boolArr2 = boolArr;
                    intRef3 = intRef;
                    try {
                    } catch (Throwable th2) {
                        th = th2;
                        intRef4 = intRef2;
                        selectBuilderImpl.handleBuilderException(th);
                        result = selectBuilderImpl.getResult();
                        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        }
                        if (result == obj2) {
                        }
                    }
                    C7978x2d5a9c55 c7978x2d5a9c55 = new C7978x2d5a9c55(i10, null, combineKt$combineInternal$2, i, boolArr, receiveChannelArr, objArr, intRef2, intRef);
                    if (booleanValue) {
                        intRef4 = intRef2;
                    } else {
                        intRef4 = intRef2;
                        try {
                        } catch (Throwable th3) {
                            th = th3;
                            selectBuilderImpl.handleBuilderException(th);
                            result = selectBuilderImpl.getResult();
                            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            }
                            if (result == obj2) {
                            }
                        }
                        selectBuilderImpl3.invoke(receiveChannel.getOnReceiveOrNull(), new C7979x2d5a9c56(c7978x2d5a9c55, null, i10, combineKt$combineInternal$23, i2, boolArr2, receiveChannelArr2, objArr2, intRef2, intRef3));
                    }
                    i9 = i10 + 1;
                    selectBuilderImpl2 = selectBuilderImpl;
                    intRef2 = intRef4;
                    combineKt$combineInternal$2 = combineKt$combineInternal$23;
                    combineKt$combineInternal$24 = combineKt$combineInternal$22;
                    coroutineScope = coroutineScope2;
                    i = i2;
                    receiveChannelArr = receiveChannelArr2;
                    objArr = objArr2;
                    boolArr = boolArr2;
                    intRef = intRef3;
                }
                selectBuilderImpl = selectBuilderImpl2;
                combineKt$combineInternal$22 = combineKt$combineInternal$24;
                combineKt$combineInternal$23 = combineKt$combineInternal$2;
                coroutineScope2 = coroutineScope;
                i2 = i;
                receiveChannelArr2 = receiveChannelArr;
                objArr2 = objArr;
                boolArr2 = boolArr;
                intRef3 = intRef;
                intRef4 = intRef2;
                result = selectBuilderImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(combineKt$combineInternal$22);
                }
                if (result == obj2) {
                    return obj2;
                }
                intRef2 = intRef4;
                combineKt$combineInternal$2 = combineKt$combineInternal$23;
                coroutineScope = coroutineScope2;
                i = i2;
                receiveChannelArr = receiveChannelArr2;
                objArr = objArr2;
                boolArr = boolArr2;
                intRef = intRef3;
                i4 = 0;
                i5 = 1;
                if (intRef.element != 0) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

package com.pudutech.freeinstall_ui.viewmodel;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.ConfigDataHelper;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NewMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.NewMapViewModel$getRealMap$1", m3970f = "NewMapViewModel.kt", m3971i = {0, 0, 0, 0}, m3972l = {135}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "checkMemoryLimit", "parcelFileDescriptor", "realMapData"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes2.dex */
public final class NewMapViewModel$getRealMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5295p$;
    final /* synthetic */ NewMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewMapViewModel$getRealMap$1(NewMapViewModel newMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = newMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NewMapViewModel$getRealMap$1 newMapViewModel$getRealMap$1 = new NewMapViewModel$getRealMap$1(this.this$0, completion);
        newMapViewModel$getRealMap$1.f5295p$ = (CoroutineScope) obj;
        return newMapViewModel$getRealMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NewMapViewModel$getRealMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0034 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5295p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!z) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "getRealMap");
            Boolean checkMemoryLimit = LocateMappingManager.INSTANCE.checkMemoryLimit(ConfigDataHelper.INSTANCE.getDeviceSize());
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "getRealMap---checkMemoryLimit " + checkMemoryLimit);
            if (Intrinsics.areEqual(checkMemoryLimit, Boxing.boxBoolean(true))) {
                this.this$0.getMemoryLimitLiveData().postValue(Boxing.boxBoolean(true));
                this.this$0.stop = true;
                return Unit.INSTANCE;
            }
            ParcelFileDescriptor realMapData = LocateMappingManager.INSTANCE.getRealMapData();
            MapData readParcelFileDescriptor = Utils.INSTANCE.readParcelFileDescriptor(realMapData);
            this.this$0.getGetRealMapLiveData().postValue(readParcelFileDescriptor);
            this.L$0 = coroutineScope;
            this.L$1 = checkMemoryLimit;
            this.L$2 = realMapData;
            this.L$3 = readParcelFileDescriptor;
            this.label = 1;
            if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}

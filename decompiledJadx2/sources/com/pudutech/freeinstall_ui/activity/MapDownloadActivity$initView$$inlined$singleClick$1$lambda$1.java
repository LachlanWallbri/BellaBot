package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapDownloadActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/freeinstall_ui/activity/MapDownloadActivity$initView$3$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5225p$;
    final /* synthetic */ MapDownloadActivity$initView$$inlined$singleClick$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1(Continuation continuation, MapDownloadActivity$initView$$inlined$singleClick$1 mapDownloadActivity$initView$$inlined$singleClick$1) {
        super(2, continuation);
        this.this$0 = mapDownloadActivity$initView$$inlined$singleClick$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1 mapDownloadActivity$initView$$inlined$singleClick$1$lambda$1 = new MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1(completion, this.this$0);
        mapDownloadActivity$initView$$inlined$singleClick$1$lambda$1.f5225p$ = (CoroutineScope) obj;
        return mapDownloadActivity$initView$$inlined$singleClick$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5225p$;
        MapingFuntionManager.INSTANCE.open();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45601(null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapDownloadActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/freeinstall_ui/activity/MapDownloadActivity$initView$3$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1$1 */
    /* loaded from: classes3.dex */
    public static final class C45601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5226p$;

        C45601(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45601 c45601 = new C45601(completion);
            c45601.f5226p$ = (CoroutineScope) obj;
            return c45601;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5226p$;
            MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1.this.this$0.this$0.dismissLoadingDialog();
            AbnormalManager.INSTANCE.addHardWareListener();
            MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1.this.this$0.this$0.startActivity(new Intent(MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1.this.this$0.this$0, (Class<?>) CreateMapNoticeActivity.class).putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1).putExtra("from_edit_map_arguments", true));
            return Unit.INSTANCE;
        }
    }
}

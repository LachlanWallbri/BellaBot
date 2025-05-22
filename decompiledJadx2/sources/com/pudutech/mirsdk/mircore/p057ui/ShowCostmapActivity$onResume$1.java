package com.pudutech.mirsdk.mircore.p057ui;

import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import com.pudutech.mirsdk.mircore.mirperception.Costmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ShowCostmapActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.ShowCostmapActivity$onResume$1", m3970f = "ShowCostmapActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class ShowCostmapActivity$onResume$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6274p$;
    final /* synthetic */ ShowCostmapActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowCostmapActivity$onResume$1(ShowCostmapActivity showCostmapActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = showCostmapActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ShowCostmapActivity$onResume$1 showCostmapActivity$onResume$1 = new ShowCostmapActivity$onResume$1(this.this$0, completion);
        showCostmapActivity$onResume$1.f6274p$ = (CoroutineScope) obj;
        return showCostmapActivity$onResume$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShowCostmapActivity$onResume$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6274p$;
        Costmap.INSTANCE.getCostmapListener().add("show", new Function2<MapData, MapData, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.ShowCostmapActivity$onResume$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MapData mapData, MapData mapData2) {
                invoke2(mapData, mapData2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MapData mapData, MapData high) {
                DrawCostmapView drawCostmapView;
                Intrinsics.checkParameterIsNotNull(mapData, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(high, "high");
                drawCostmapView = ShowCostmapActivity$onResume$1.this.this$0.costmapView;
                if (drawCostmapView != null) {
                    drawCostmapView.showData(high);
                }
            }
        });
        return Unit.INSTANCE;
    }
}

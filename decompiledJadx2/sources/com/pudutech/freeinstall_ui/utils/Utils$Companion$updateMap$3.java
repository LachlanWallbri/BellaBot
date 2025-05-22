package com.pudutech.freeinstall_ui.utils;

import android.graphics.Bitmap;
import com.pudutech.base.Pdlog;
import com.pudutech.opengl_draw.bean.OccupancyGrid;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import java.util.List;
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
/* compiled from: Utils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.utils.Utils$Companion$updateMap$3", m3970f = "Utils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class Utils$Companion$updateMap$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OccupancyGridLayer $mapLayer;
    final /* synthetic */ Bitmap $mapPic;
    final /* synthetic */ OccupancyGridLayer.OccupancyOneListener $occupancyOneListener;
    final /* synthetic */ List $origin;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5247p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Utils$Companion$updateMap$3(List list, Bitmap bitmap, OccupancyGridLayer occupancyGridLayer, OccupancyGridLayer.OccupancyOneListener occupancyOneListener, Continuation continuation) {
        super(2, continuation);
        this.$origin = list;
        this.$mapPic = bitmap;
        this.$mapLayer = occupancyGridLayer;
        this.$occupancyOneListener = occupancyOneListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Utils$Companion$updateMap$3 utils$Companion$updateMap$3 = new Utils$Companion$updateMap$3(this.$origin, this.$mapPic, this.$mapLayer, this.$occupancyOneListener, completion);
        utils$Companion$updateMap$3.f5247p$ = (CoroutineScope) obj;
        return utils$Companion$updateMap$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Utils$Companion$updateMap$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5247p$;
        OccupancyGrid occupancyGrid = new OccupancyGrid();
        Pdlog.m3273d("updateMap", "origin_x " + ((Number) this.$origin.get(0)).doubleValue() + " origin_y " + ((Number) this.$origin.get(1)).doubleValue() + " scale " + ((Number) this.$origin.get(2)).doubleValue() + " mapPic " + this.$mapPic);
        double d = (double) 2;
        occupancyGrid.setVector3(new Vector3((((double) (-Utils.INSTANCE.getSize_x())) * Utils.INSTANCE.getResolution()) / d, (((double) (-Utils.INSTANCE.getSize_y())) * Utils.INSTANCE.getResolution()) / d, 0.0d));
        occupancyGrid.setQuaternion(Quaternion.identity());
        OccupancyGridLayer occupancyGridLayer = this.$mapLayer;
        if (occupancyGridLayer != null) {
            occupancyGridLayer.update(this.$mapPic, occupancyGrid, this.$occupancyOneListener);
        }
        return Unit.INSTANCE;
    }
}

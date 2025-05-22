package com.pudutech.freeinstall_ui.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import com.pudutech.opengl_draw.bean.OccupancyGrid;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
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
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.utils.Utils$Companion$updateMap$1", m3970f = "Utils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class Utils$Companion$updateMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OccupancyGridLayer $mapLayer;
    final /* synthetic */ OccupancyGridLayer.OccupancyOneListener $occupancyOneListener;
    final /* synthetic */ MapData $p0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5246p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Utils$Companion$updateMap$1(MapData mapData, OccupancyGridLayer occupancyGridLayer, OccupancyGridLayer.OccupancyOneListener occupancyOneListener, Continuation continuation) {
        super(2, continuation);
        this.$p0 = mapData;
        this.$mapLayer = occupancyGridLayer;
        this.$occupancyOneListener = occupancyOneListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Utils$Companion$updateMap$1 utils$Companion$updateMap$1 = new Utils$Companion$updateMap$1(this.$p0, this.$mapLayer, this.$occupancyOneListener, completion);
        utils$Companion$updateMap$1.f5246p$ = (CoroutineScope) obj;
        return utils$Companion$updateMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Utils$Companion$updateMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5246p$;
        OccupancyGrid occupancyGrid = new OccupancyGrid();
        Pdlog.m3273d("updateMap", "origin_x " + this.$p0.getOrigin_x() + " origin_y " + this.$p0.getOrigin_y() + " scale " + this.$p0.getScale() + " size_x " + this.$p0.getSize_x() + " size_y " + this.$p0.getSize_y());
        double d = (double) 2;
        occupancyGrid.setVector3(new Vector3((((double) (-Utils.INSTANCE.getSize_x())) * Utils.INSTANCE.getResolution()) / d, (((double) (-Utils.INSTANCE.getSize_y())) * Utils.INSTANCE.getResolution()) / d, 0.0d));
        StringBuilder sb = new StringBuilder();
        sb.append("occupancyGrid.vector3.x ");
        Vector3 vector3 = occupancyGrid.getVector3();
        Intrinsics.checkExpressionValueIsNotNull(vector3, "occupancyGrid.vector3");
        sb.append(vector3.getX());
        sb.append(" occupancyGrid.vector3.y ");
        Vector3 vector32 = occupancyGrid.getVector3();
        Intrinsics.checkExpressionValueIsNotNull(vector32, "occupancyGrid.vector3");
        sb.append(vector32.getY());
        Pdlog.m3273d("updateMap", sb.toString());
        occupancyGrid.setQuaternion(Quaternion.identity());
        OccupancyGridLayer occupancyGridLayer = this.$mapLayer;
        if (occupancyGridLayer != null) {
            occupancyGridLayer.update(this.$p0.getData(), this.$p0.getSize_x(), this.$p0.getSize_y(), occupancyGrid, this.$occupancyOneListener);
        }
        return Unit.INSTANCE;
    }
}

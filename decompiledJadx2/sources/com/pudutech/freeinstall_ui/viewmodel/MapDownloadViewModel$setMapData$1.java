package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.freeinstall_ui.utils.Constants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapDownloadViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@"}, m3961d2 = {"setMapData", "", "analyMap", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "item", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel", m3970f = "MapDownloadViewModel.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, m3972l = {139}, m3973m = "setMapData", m3974n = {"this", "analyMap", "item", "dockerChargeList", "cruiseTracksList", "doors", "parking", Constants.POINT_TYPE_TABLE, "takeMeal", "mapInitPoint", "virtualWallList"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"})
/* loaded from: classes2.dex */
public final class MapDownloadViewModel$setMapData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MapDownloadViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapDownloadViewModel$setMapData$1(MapDownloadViewModel mapDownloadViewModel, Continuation continuation) {
        super(continuation);
        this.this$0 = mapDownloadViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.setMapData(null, null, this);
    }
}

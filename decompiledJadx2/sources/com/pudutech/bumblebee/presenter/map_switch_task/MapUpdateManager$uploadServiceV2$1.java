package com.pudutech.bumblebee.presenter.map_switch_task;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, m3961d2 = {"uploadServiceV2", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {292, 294}, m3973m = "uploadServiceV2", m3974n = {"this", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "this", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "uploadMap"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class MapUpdateManager$uploadServiceV2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MapUpdateManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$uploadServiceV2$1(MapUpdateManager mapUpdateManager, Continuation continuation) {
        super(continuation);
        this.this$0 = mapUpdateManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.uploadServiceV2(null, this);
    }
}

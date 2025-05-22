package com.pudutech.bumblebee.presenter.map_switch_task;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0082@"}, m3961d2 = {"download", "", "currentIndex", "", "it", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "data", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {106}, m3973m = "download", m3974n = {"this", "currentIndex", "it", "data", "scope", "downloadPath", "realObjectKey"}, m3975s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes4.dex */
public final class MapUpdateManager$download$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MapUpdateManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$download$1(MapUpdateManager mapUpdateManager, Continuation continuation) {
        super(continuation);
        this.this$0 = mapUpdateManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.download(0, null, null, null, this);
    }
}

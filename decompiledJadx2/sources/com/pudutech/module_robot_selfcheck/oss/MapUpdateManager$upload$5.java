package com.pudutech.module_robot_selfcheck.oss;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@"}, m3961d2 = {"upload", "", "filePath", "", "name", "lv", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, m3972l = {219, 221, 231, 233}, m3973m = "upload", m3974n = {"this", "filePath", "name", "lv", "md5", "this", "filePath", "name", "lv", "md5", "level", "this", "filePath", "name", "lv", "md5", "level", "uploadFile", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "this", "filePath", "name", "lv", "md5", "level", "uploadFile"}, m3975s = {"L$0", "L$1", "L$2", "I$0", "L$3", "L$0", "L$1", "L$2", "I$0", "L$3", "I$1", "L$0", "L$1", "L$2", "I$0", "L$3", "I$1", "L$4", "L$5", "L$0", "L$1", "L$2", "I$0", "L$3", "I$1", "L$4"})
/* loaded from: classes5.dex */
public final class MapUpdateManager$upload$5 extends ContinuationImpl {
    int I$0;
    int I$1;
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
    public MapUpdateManager$upload$5(MapUpdateManager mapUpdateManager, Continuation continuation) {
        super(continuation);
        this.this$0 = mapUpdateManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.upload(null, null, 0, this);
    }
}

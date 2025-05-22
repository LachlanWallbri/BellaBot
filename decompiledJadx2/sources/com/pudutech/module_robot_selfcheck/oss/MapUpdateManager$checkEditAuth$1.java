package com.pudutech.module_robot_selfcheck.oss;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012#\u0010\u0002\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u00070\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0086@"}, m3961d2 = {"checkEditAuth", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Function1;", "Lcom/pudutech/disinfect/baselib/network/response/ResAuth;", "Lkotlin/ParameterName;", "name", "", "continuation", "Lkotlin/coroutines/Continuation;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {DimensionsKt.XHDPI, 321}, m3973m = "checkEditAuth", m3974n = {"this", SpeechUtility.TAG_RESOURCE_RESULT, "this", SpeechUtility.TAG_RESOURCE_RESULT, "auth"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class MapUpdateManager$checkEditAuth$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MapUpdateManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$checkEditAuth$1(MapUpdateManager mapUpdateManager, Continuation continuation) {
        super(continuation);
        this.this$0 = mapUpdateManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkEditAuth(null, this);
    }
}

package com.pudutech.antichannelconflict.escape.network;

import androidx.core.app.NotificationCompat;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032]\u0010\u000e\u001aY\b\u0001\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@"}, m3961d2 = {"checkEscape", "", "mac", "", "longitude", "latitude", "autoLock", "", "changeType", "Lcom/pudutech/antichannelconflict/escape/util/MapStatus;", "mbts", "product", "", "softver", "callback", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "code", "lockStatus", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/coroutines/Continuation;", "", "continuation"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.escape.network.NetworkUtil", m3970f = "NetworkUtil.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, m3972l = {56, 60, 63, 67, 73}, m3973m = "checkEscape", m3974n = {"this", "mac", "longitude", "latitude", "autoLock", "changeType", "mbts", "product", "softver", "callback", "service", "$this$runCatching", "reqData", "this", "mac", "longitude", "latitude", "autoLock", "changeType", "mbts", "product", "softver", "callback", "service", "it", "this", "mac", "longitude", "latitude", "autoLock", "changeType", "mbts", "product", "softver", "callback", "service", "it", "this", "mac", "longitude", "latitude", "autoLock", "changeType", "mbts", "product", "softver", "callback", "service", "it", C3898x.f4338g, "this", "mac", "longitude", "latitude", "autoLock", "changeType", "mbts", "product", "softver", "callback", "service", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$4", "L$5", "I$0", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$4", "L$5", "I$0", "L$6", "L$7", "L$8", "L$10", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$4", "L$5", "I$0", "L$6", "L$7", "L$8", "L$10", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$4", "L$5", "I$0", "L$6", "L$7", "L$8", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$4", "L$5", "I$0", "L$6", "L$7", "L$8", "L$10"})
/* loaded from: classes4.dex */
public final class NetworkUtil$checkEscape$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkUtil this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkUtil$checkEscape$1(NetworkUtil networkUtil, Continuation continuation) {
        super(continuation);
        this.this$0 = networkUtil;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkEscape(null, null, null, false, null, null, 0, null, null, this);
    }
}

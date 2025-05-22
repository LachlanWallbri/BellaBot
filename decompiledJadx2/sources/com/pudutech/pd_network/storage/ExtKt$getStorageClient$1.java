package com.pudutech.pd_network.storage;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0080@"}, m3961d2 = {"getStorageClient", "", "context", "Landroid/content/Context;", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/pd_network/IOSSClient;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ExtKt", m3970f = "ext.kt", m3971i = {0, 0, 0}, m3972l = {131}, m3973m = "getStorageClient", m3974n = {"context", "bucketType", "config"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes6.dex */
public final class ExtKt$getStorageClient$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtKt$getStorageClient$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ExtKt.getStorageClient(null, null, null, this);
    }
}

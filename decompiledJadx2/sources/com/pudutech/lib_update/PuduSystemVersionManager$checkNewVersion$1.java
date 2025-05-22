package com.pudutech.lib_update;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0086@"}, m3961d2 = {"checkNewVersion", "", "mac", "", "sys_ver_name", "sys_ver_code", "product_name", "request_ver_name", "request_ver_code", "app_version_name", "sys_build_id", "channel_name", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/lib_update/module/model/Version;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager", m3970f = "PuduSystemVersionManager.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, m3972l = {54}, m3973m = "checkNewVersion", m3974n = {"this", "mac", "sys_ver_name", "sys_ver_code", "product_name", "request_ver_name", "request_ver_code", "app_version_name", "sys_build_id", "channel_name"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$checkNewVersion$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
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
    final /* synthetic */ PuduSystemVersionManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PuduSystemVersionManager$checkNewVersion$1(PuduSystemVersionManager puduSystemVersionManager, Continuation continuation) {
        super(continuation);
        this.this$0 = puduSystemVersionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkNewVersion(null, null, null, null, null, null, null, null, null, this);
    }
}

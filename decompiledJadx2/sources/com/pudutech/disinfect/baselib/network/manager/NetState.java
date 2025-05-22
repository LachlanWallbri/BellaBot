package com.pudutech.disinfect.baselib.network.manager;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NetState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "", "isSuccess", "", "(Z)V", "()Z", "setSuccess", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetState {
    private boolean isSuccess;

    public NetState() {
        this(false, 1, null);
    }

    public NetState(boolean z) {
        this.isSuccess = z;
    }

    public /* synthetic */ NetState(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    /* renamed from: isSuccess, reason: from getter */
    public final boolean getIsSuccess() {
        return this.isSuccess;
    }

    public final void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}

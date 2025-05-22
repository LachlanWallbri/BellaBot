package com.pudutech.bumblebee.business.core_devices_task.monitor_task;

import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MonitorEvent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;", "", "boolean", "", "errors", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "(ZLcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;)V", "error", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "(ZLcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;)V", "getErrors", "()Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "setErrors", "(Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;)V", "isError", "()Z", "setError", "(Z)V", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MonitorEvent {
    private Errors errors;
    private boolean isError;

    /* renamed from: isError, reason: from getter */
    public final boolean getIsError() {
        return this.isError;
    }

    public final void setError(boolean z) {
        this.isError = z;
    }

    public final Errors getErrors() {
        return this.errors;
    }

    public final void setErrors(Errors errors) {
        this.errors = errors;
    }

    public MonitorEvent(boolean z, Errors errors) {
        this.isError = z;
        this.errors = errors;
    }

    public MonitorEvent(boolean z, Error error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.isError = z;
        this.errors = new Errors();
        Errors errors = this.errors;
        if (errors == null) {
            Intrinsics.throwNpe();
        }
        errors.list.add(error);
    }

    public String toString() {
        return "MonitorEvent(isError=" + this.isError + ", errors=" + this.errors + ')';
    }
}

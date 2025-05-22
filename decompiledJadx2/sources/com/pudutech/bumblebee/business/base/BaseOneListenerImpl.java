package com.pudutech.bumblebee.business.base;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.bumblebee.business.base.BaseListener;
import kotlin.Metadata;

/* compiled from: BaseOneListenerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\tR\u001e\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0084\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/base/BaseOneListenerImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/business/base/BaseListener;", "", "()V", "listener", "getListener", "()Lcom/pudutech/bumblebee/business/base/BaseListener;", "setListener", "(Lcom/pudutech/bumblebee/business/base/BaseListener;)V", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "bindListener", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseOneListenerImpl<T extends BaseListener> {
    private T listener;

    protected final T getListener() {
        return this.listener;
    }

    protected final void setListener(T t) {
        this.listener = t;
    }

    public final void bindListener(T listener) {
        this.listener = listener;
    }
}

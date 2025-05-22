package com.pudutech.disinfect.baselib.callback.livedata;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ShortLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/livedata/ShortLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "getValue", "()Ljava/lang/Short;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ShortLiveData extends MutableLiveData<Short> {
    @Override // androidx.lifecycle.LiveData
    public Short getValue() {
        Short sh = (Short) super.getValue();
        return Short.valueOf(sh != null ? sh.shortValue() : (short) 0);
    }
}

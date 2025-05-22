package com.pudutech.disinfect.baselib.callback.livedata;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: IntLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000f\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/livedata/IntLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "getValue", "()Ljava/lang/Integer;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class IntLiveData extends MutableLiveData<Integer> {
    @Override // androidx.lifecycle.LiveData
    public Integer getValue() {
        Integer num = (Integer) super.getValue();
        return Integer.valueOf(num != null ? num.intValue() : 0);
    }
}

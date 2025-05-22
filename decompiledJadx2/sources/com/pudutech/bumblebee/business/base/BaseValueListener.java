package com.pudutech.bumblebee.business.base;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: BaseValueListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00018\u00002\b\u0010\b\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/base/BaseValueListener;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onValueSet", "", "describe", "", "oldValue", "newValue", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BaseValueListener<T> extends BaseListener {
    void onValueSet(String describe, T oldValue, T newValue);
}

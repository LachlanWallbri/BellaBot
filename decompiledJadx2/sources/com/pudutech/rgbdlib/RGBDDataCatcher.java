package com.pudutech.rgbdlib;

import android.os.ParcelFileDescriptor;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: RGBDDataCatcher.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RGBDDataCatcher;", "", "onFrameDescriptor", "", "parcelFileDescriptor", "Landroid/os/ParcelFileDescriptor;", "rows", "", "cols", "memorySize", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface RGBDDataCatcher {
    void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize);
}

package com.pudutech.serialport.library;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: ISerialPortOpenStatusCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/serialport/library/ISerialPortOpenStatusCallback;", "", "callbackOpenStatus", "", "opened", "", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public interface ISerialPortOpenStatusCallback {
    void callbackOpenStatus(boolean opened);
}

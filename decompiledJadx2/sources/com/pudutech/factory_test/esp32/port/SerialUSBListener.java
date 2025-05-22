package com.pudutech.factory_test.esp32.port;

import kotlin.Metadata;

/* compiled from: SerialUSBListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/port/SerialUSBListener;", "", "onDeviceDisConnect", "", "onDeviceOpen", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface SerialUSBListener {
    void onDeviceDisConnect();

    void onDeviceOpen();
}

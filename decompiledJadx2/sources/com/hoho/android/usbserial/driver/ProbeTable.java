package com.hoho.android.usbserial.driver;

import android.util.Pair;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ProbeTable {
    private final Map<Pair<Integer, Integer>, Class<? extends UsbSerialDriver>> mProbeTable = new LinkedHashMap();

    public ProbeTable addProduct(int vendorId, int productId, Class<? extends UsbSerialDriver> driverClass) {
        this.mProbeTable.put(Pair.create(Integer.valueOf(vendorId), Integer.valueOf(productId)), driverClass);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProbeTable addDriver(Class<? extends UsbSerialDriver> driverClass) {
        try {
            try {
                for (Map.Entry entry : ((Map) driverClass.getMethod("getSupportedDevices", new Class[0]).invoke(null, new Object[0])).entrySet()) {
                    int intValue = ((Integer) entry.getKey()).intValue();
                    for (int i : (int[]) entry.getValue()) {
                        addProduct(intValue, i, driverClass);
                    }
                }
                return this;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException | SecurityException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Class<? extends UsbSerialDriver> findDriver(int vendorId, int productId) {
        return this.mProbeTable.get(Pair.create(Integer.valueOf(vendorId), Integer.valueOf(productId)));
    }
}

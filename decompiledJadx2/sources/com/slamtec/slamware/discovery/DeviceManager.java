package com.slamtec.slamware.discovery;

import android.content.Context;
import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.SlamwareCorePlatform;
import com.slamtec.slamware.discovery.AbstractDiscover;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class DeviceManager extends AbstractDiscover {
    private BleDiscover bleDiscover;
    private MdnsDiscover mdnsDiscover;

    public AbstractSlamwarePlatform connect(Device device) {
        return null;
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public DiscoveryMode getMode() {
        return null;
    }

    public DeviceManager(Context context) {
        this.bleDiscover = new BleDiscover(new WeakReference(context));
        this.mdnsDiscover = new MdnsDiscover(new WeakReference(context));
    }

    public static AbstractSlamwarePlatform connect(String str, int i) {
        return SlamwareCorePlatform.connect(str, i);
    }

    public void pair(Device device, String str, String str2, AbstractDiscover.BleConfigureListener bleConfigureListener) {
        this.bleDiscover.configDevice(device, str, str2, bleConfigureListener);
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void setListener(AbstractDiscover.DiscoveryListener discoveryListener) {
        this.bleDiscover.setListener(discoveryListener);
        this.mdnsDiscover.setListener(discoveryListener);
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public AbstractDiscover.DiscoverStatus getStatus(DiscoveryMode discoveryMode) {
        return discoveryMode == DiscoveryMode.BLE ? this.bleDiscover.getStatus(discoveryMode) : this.mdnsDiscover.getStatus(discoveryMode);
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void start(DiscoveryMode discoveryMode) {
        if (discoveryMode == DiscoveryMode.MDNS) {
            this.mdnsDiscover.start(discoveryMode);
        } else if (discoveryMode == DiscoveryMode.BLE) {
            this.bleDiscover.start(discoveryMode);
        }
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void stop(DiscoveryMode discoveryMode) {
        if (discoveryMode == DiscoveryMode.MDNS) {
            this.mdnsDiscover.stop(discoveryMode);
        } else if (discoveryMode == DiscoveryMode.BLE) {
            this.bleDiscover.stop(discoveryMode);
        }
    }
}

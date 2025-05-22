package com.slamtec.slamware.discovery;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import com.slamtec.slamware.SlamwareCorePlatform;
import com.slamtec.slamware.discovery.AbstractDiscover;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class MdnsDiscover extends AbstractDiscover {
    private static final String SERVICE_TYPE = "_slamware._tcp.";
    private WeakReference<Context> context;
    private AbstractDiscover.DiscoveryListener listener;
    private NsdManager nsdManager;
    private AbstractDiscover.DiscoverStatus status = AbstractDiscover.DiscoverStatus.STOPPED;
    private NsdManager.DiscoveryListener discoveryListener = new NsdManager.DiscoveryListener() { // from class: com.slamtec.slamware.discovery.MdnsDiscover.1
        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStartDiscoveryFailed(String str, int i) {
            if (str.equals(MdnsDiscover.SERVICE_TYPE)) {
                MdnsDiscover.this.listener.onDiscoveryError(MdnsDiscover.this, String.format("MdnDiscover: Failed to start discover. Code: %d", Integer.valueOf(i)));
            }
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStopDiscoveryFailed(String str, int i) {
            if (str.equals(MdnsDiscover.SERVICE_TYPE)) {
                MdnsDiscover.this.listener.onDiscoveryError(MdnsDiscover.this, String.format("MdnsDiscover: Failed to stop discover. Code: %d", Integer.valueOf(i)));
            }
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStarted(String str) {
            MdnsDiscover.this.listener.onStartDiscovery(MdnsDiscover.this);
            MdnsDiscover.this.status = AbstractDiscover.DiscoverStatus.WORKING;
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStopped(String str) {
            MdnsDiscover.this.listener.onStopDiscovery(MdnsDiscover.this);
            MdnsDiscover.this.status = AbstractDiscover.DiscoverStatus.STOPPED;
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
            if (MdnsDiscover.this.status == AbstractDiscover.DiscoverStatus.WORKING && nsdServiceInfo.getServiceType().equals(MdnsDiscover.SERVICE_TYPE)) {
                MdnsDiscover.this.resolveService(nsdServiceInfo);
            }
        }
    };
    private DeviceInfoHandler deviceInfoHandler = new DeviceInfoHandler();

    public MdnsDiscover(WeakReference<Context> weakReference) {
        this.context = weakReference;
        this.nsdManager = (NsdManager) weakReference.get().getSystemService("servicediscovery");
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void setListener(AbstractDiscover.DiscoveryListener discoveryListener) {
        this.listener = discoveryListener;
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public AbstractDiscover.DiscoverStatus getStatus(DiscoveryMode discoveryMode) {
        return this.status;
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void start(DiscoveryMode discoveryMode) {
        if (this.status == AbstractDiscover.DiscoverStatus.WORKING) {
            return;
        }
        this.nsdManager.discoverServices(SERVICE_TYPE, 1, this.discoveryListener);
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void stop(DiscoveryMode discoveryMode) {
        if (this.status == AbstractDiscover.DiscoverStatus.WORKING) {
            this.nsdManager.stopServiceDiscovery(this.discoveryListener);
        }
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public DiscoveryMode getMode() {
        return DiscoveryMode.MDNS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveService(NsdServiceInfo nsdServiceInfo) {
        this.nsdManager.resolveService(nsdServiceInfo, new NsdManager.ResolveListener() { // from class: com.slamtec.slamware.discovery.MdnsDiscover.2
            @Override // android.net.nsd.NsdManager.ResolveListener
            public void onResolveFailed(NsdServiceInfo nsdServiceInfo2, int i) {
            }

            @Override // android.net.nsd.NsdManager.ResolveListener
            public void onServiceResolved(NsdServiceInfo nsdServiceInfo2) {
                MdnsDevice mdnsDevice = new MdnsDevice(nsdServiceInfo2.getHost().getHostAddress(), nsdServiceInfo2.getPort());
                mdnsDevice.setDeviceName(nsdServiceInfo2.getServiceName());
                MdnsDiscover.this.deviceInfoHandler.appendDevice(mdnsDevice);
            }
        });
    }

    private UUID getUUID(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    private class DeviceInfoHandler {
        private ExecutorService worker = Executors.newSingleThreadExecutor();

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public final class Work implements Runnable {
            private MdnsDevice device;

            public Work(MdnsDevice mdnsDevice) {
                this.device = mdnsDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                SlamwareCorePlatform slamwareCorePlatform = null;
                try {
                    slamwareCorePlatform = SlamwareCorePlatform.connect(this.device.getAddr(), this.device.getPort());
                    this.device.setManufactureId(slamwareCorePlatform.getManufacturerId());
                    this.device.setManufactureName(slamwareCorePlatform.getManuFacturerName());
                    this.device.setModelId(slamwareCorePlatform.getModelId());
                    this.device.setModelName(slamwareCorePlatform.getModelName());
                    this.device.setHardwareVersion(slamwareCorePlatform.getHardwareVersion());
                    this.device.setSoftwareVersion(slamwareCorePlatform.getSoftwareVersion());
                    this.device.setDeviceId(UUID.fromString(getFormattedUUIDString(slamwareCorePlatform.getDeviceId())));
                    slamwareCorePlatform.disconnect();
                    if (MdnsDiscover.this.listener != null) {
                        MdnsDiscover.this.listener.onDeviceFound(MdnsDiscover.this, this.device);
                    }
                } catch (Exception unused) {
                    if (slamwareCorePlatform != null) {
                        slamwareCorePlatform.disconnect();
                    }
                }
            }

            private String getFormattedUUIDString(String str) {
                return String.format("%s-%s-%s-%s-%s", str.substring(0, 8), str.substring(8, 12), str.substring(12, 16), str.substring(16, 20), str.substring(20, 32));
            }
        }

        public DeviceInfoHandler() {
        }

        public void appendDevice(MdnsDevice mdnsDevice) {
            this.worker.submit(new Work(mdnsDevice));
        }
    }
}

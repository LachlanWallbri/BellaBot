package com.slamtec.slamware.discovery;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public abstract class AbstractDiscover {
    public static final String BLE_CONFIG_ERROR_BLE_DISCONNECTED = "Discover: bluetooth connection lost";
    public static final String BLE_CONFIG_ERROR_INVALID_PWD = "Discover: invalid password";
    public static final String BLE_CONFIG_ERROR_SSID_REQUIRED = "Discover: bluetoothRequire ssid";
    public static final String BLE_CONFIG_ERROR_UNABLE_CONNECT_WIFI = "Discover: unable to connect wifi";
    public static final String BLE_CONFIG_ERROR_WRITING_FAIL = "Discover: bluetooth write characteristic failed";
    protected DiscoveryMode mode;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface BleConfigureListener {
        void onConfigureFailure(String str);

        void onConfigureSuccess();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public enum DiscoverStatus {
        STOPPED,
        WORKING,
        ERROR
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class DiscoveryListener {
        public abstract void onDeviceFound(AbstractDiscover abstractDiscover, Device device);

        public abstract void onDiscoveryError(AbstractDiscover abstractDiscover, String str);

        public abstract void onStartDiscovery(AbstractDiscover abstractDiscover);

        public abstract void onStopDiscovery(AbstractDiscover abstractDiscover);
    }

    public abstract DiscoveryMode getMode();

    public abstract DiscoverStatus getStatus(DiscoveryMode discoveryMode);

    public abstract void setListener(DiscoveryListener discoveryListener);

    public abstract void start(DiscoveryMode discoveryMode);

    public abstract void stop(DiscoveryMode discoveryMode);
}

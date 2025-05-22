package com.slamtec.slamware.discovery;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class MdnsDevice extends Device {
    private String addr;
    private int port;

    public MdnsDevice(String str, int i) {
        this.addr = str;
        this.port = i;
    }

    public String getAddr() {
        return this.addr;
    }

    public int getPort() {
        return this.port;
    }

    @Override // com.slamtec.slamware.discovery.Device
    public boolean canBeFoundWith(DiscoveryMode discoveryMode) {
        return discoveryMode == DiscoveryMode.MDNS;
    }
}

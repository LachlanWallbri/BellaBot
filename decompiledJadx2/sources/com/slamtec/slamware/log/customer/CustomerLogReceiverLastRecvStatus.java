package com.slamtec.slamware.log.customer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class CustomerLogReceiverLastRecvStatus {
    private String serviceUuid = "";
    private long lastReceivedSeqNum = -1;

    public String getServiceUuid() {
        return this.serviceUuid;
    }

    public void setServiceUuid(String str) {
        this.serviceUuid = str;
    }

    public long getLastReceivedSeqNum() {
        return this.lastReceivedSeqNum;
    }

    public void setLastReceivedSeqNum(long j) {
        this.lastReceivedSeqNum = j;
    }
}

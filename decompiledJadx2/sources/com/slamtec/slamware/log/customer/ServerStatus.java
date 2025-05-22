package com.slamtec.slamware.log.customer;

import com.slamtec.slamware.message.SeqNumRange;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ServerStatus {
    private String serviceUuid = "";
    private long latestSeqNum = -1;
    private SeqNumRange seqNumRange = new SeqNumRange();

    public String getServiceUuid() {
        return this.serviceUuid;
    }

    public void setServiceUuid(String str) {
        this.serviceUuid = str;
    }

    public long getLatestSeqNum() {
        return this.latestSeqNum;
    }

    public void setLatestSeqNum(long j) {
        this.latestSeqNum = j;
    }

    public SeqNumRange getSeqNumRange() {
        return this.seqNumRange;
    }

    public void setSeqNumRange(SeqNumRange seqNumRange) {
        this.seqNumRange = seqNumRange;
    }
}

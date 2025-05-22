package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SendAndRecvUserDefinedCBusMsgResult {
    private int retCode = -1;
    private byte[] recvData = null;

    public int getRetCode() {
        return this.retCode;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    public byte[] getRecvData() {
        return this.recvData;
    }

    public void setRecvData(byte[] bArr) {
        this.recvData = bArr;
    }
}

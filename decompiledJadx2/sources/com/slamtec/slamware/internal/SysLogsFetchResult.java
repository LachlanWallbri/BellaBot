package com.slamtec.slamware.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SysLogsFetchResult {
    private long resCode = -1;
    private byte[] resBody = null;
    private String errMsg = null;

    public long getResCode() {
        return this.resCode;
    }

    public void setResCode(long j) {
        this.resCode = j;
    }

    public byte[] getResBody() {
        return this.resBody;
    }

    public void setResBody(byte[] bArr) {
        this.resBody = bArr;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }
}

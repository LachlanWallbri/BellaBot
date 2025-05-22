package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpResponse extends TmpCommonResponse<AResponse> {
    protected String iotId;
    protected String mAddr;
    protected int mPort;

    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public boolean isSuccess() {
        return true;
    }

    public CpResponse(AResponse aResponse) {
        super(aResponse);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public String getAddr() {
        return this.mAddr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public String getResponseText() {
        if (this.mResponse == 0 || ((AResponse) this.mResponse).data == null) {
            return null;
        }
        if (((AResponse) this.mResponse).data instanceof byte[]) {
            try {
                return new String((byte[]) ((AResponse) this.mResponse).data, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return ((AResponse) this.mResponse).data.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public byte[] getResponseByte() {
        if (this.mResponse == 0 || ((AResponse) this.mResponse).data == null || !(((AResponse) this.mResponse).data instanceof byte[])) {
            return null;
        }
        return (byte[]) ((AResponse) this.mResponse).data;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public void setResponseText(String str) {
        if (this.mResponse == 0) {
            return;
        }
        ((AResponse) this.mResponse).data = str;
    }

    public void setIp(String str) {
        this.mAddr = str;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public int getPort() {
        return this.mPort;
    }

    public void setPort(int i) {
        this.mPort = i;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public void setIotId(String str) {
        this.iotId = str;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse
    public String getIotId() {
        return this.iotId;
    }
}

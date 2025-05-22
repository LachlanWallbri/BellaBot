package com.aliyun.alink.linksdk.tmp.device.payload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EncryptRequestPayload<T> {
    protected CommonRequestPayload<T> data;
    protected Extra extra;

    public Extra getExtra() {
        return this.extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public CommonRequestPayload<T> getData() {
        return this.data;
    }

    public void setData(CommonRequestPayload<T> commonRequestPayload) {
        this.data = commonRequestPayload;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Extra {
        String sessionKey;
        String uid;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getSessionKey() {
            return this.sessionKey;
        }

        public void setSessionKey(String str) {
            this.sessionKey = str;
        }
    }
}

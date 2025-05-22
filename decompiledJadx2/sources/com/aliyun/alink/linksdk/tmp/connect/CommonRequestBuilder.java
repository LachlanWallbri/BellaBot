package com.aliyun.alink.linksdk.tmp.connect;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.devicemodel.Profile;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class CommonRequestBuilder<Builder, Payload> {
    protected static final String TAG = "CommonRequestBuilder";
    protected String mAddr;
    protected String mPath;
    protected String mPayload;
    protected Payload mPayloadData;
    protected int mPort;
    protected Object mTag;
    protected boolean mIsSecure = false;
    protected long mTimeOut = 5000;
    protected RequestType mRequestTye = RequestType.NORMAL;
    protected Method mMethod = Method.GET;
    protected Builder mBuilder = this;

    public abstract TmpCommonRequest createRequest();

    public Builder setIsSecure(boolean z) {
        this.mIsSecure = z;
        return this.mBuilder;
    }

    public Builder setTag(Object obj) {
        this.mTag = obj;
        return this.mBuilder;
    }

    public Builder setAddr(String str) {
        this.mAddr = str;
        return this.mBuilder;
    }

    public Builder setPayload(String str) {
        this.mPayload = str;
        return this.mBuilder;
    }

    public Builder setPayloadData(Payload payload) {
        this.mPayloadData = payload;
        return this.mBuilder;
    }

    public Builder setPath(String str) {
        this.mPath = str;
        return this.mBuilder;
    }

    public Builder setTimeOut(long j) {
        this.mTimeOut = j;
        return this.mBuilder;
    }

    public Builder setRequestType(RequestType requestType) {
        this.mRequestTye = requestType;
        return this.mBuilder;
    }

    public int getPort() {
        return this.mPort;
    }

    public Builder setPort(int i) {
        this.mPort = i;
        return this.mBuilder;
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public void setMethod(Method method) {
        this.mMethod = method;
    }

    public static String formatPath(Profile profile, String str) {
        if (profile == null) {
            LogCat.m471e(TAG, "formatPath error param null profile:" + profile + " method" + str);
            return str;
        }
        return formatPath(profile.getProdKey(), profile.getName(), str, "sys");
    }

    public static String formatPath(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogCat.m471e(TAG, "formatPath error param null productKey:" + str + " deviceName:" + str2 + " method" + str3);
            return str3;
        }
        if (TextUtils.isEmpty(str3)) {
            LogCat.m471e(TAG, "formatPath error param  method" + str3);
            return "/" + str4 + "/" + str + "/" + str2;
        }
        return "/" + str4 + "/" + str + "/" + str2 + "/" + str3.replace(".", "/");
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum Method {
        UNKNOW(0),
        POST(1),
        GET(2),
        DELETE(3),
        PUT(4);

        protected int mValue;

        Method(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public CommonRequest.METHOD toCRMethod() {
            CommonRequest.METHOD method = CommonRequest.METHOD.GET;
            int i = this.mValue;
            if (i == 1) {
                return CommonRequest.METHOD.POST;
            }
            if (i == 2) {
                return CommonRequest.METHOD.GET;
            }
            if (i != 3) {
                return i != 4 ? method : CommonRequest.METHOD.PUT;
            }
            return CommonRequest.METHOD.DELETE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum RequestType {
        NORMAL(1, "normal"),
        MULTIPLE_RESPONSE(2, "multiple_response"),
        RELEATE(3, "releate");

        protected String mDesc;
        protected int mType;

        RequestType(int i, String str) {
            this.mType = i;
            this.mDesc = str;
        }

        public int getType() {
            return this.mType;
        }

        public String getDesc() {
            return this.mDesc;
        }
    }
}

package com.aliyun.alink.linksdk.cmp.api;

import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPConstant;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.iot.aep.sdk.apiclient.emuns.Method;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CommonRequest extends ARequest {
    public String alinkIdForTracker;
    public Object context;

    /* renamed from: ip */
    public String f1011ip;
    public boolean isSecurity;
    public METHOD mothod = null;
    public Map<String, Object> params;
    public Object payload;
    public int port;
    public String topic;
    public String traceId;
    public Object type;

    public String getSpecificTopic(boolean z) {
        return this.topic;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.cmp.api.CommonRequest$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C09901 {
        static final /* synthetic */ int[] $SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD;

        static {
            int[] iArr = new int[METHOD.values().length];
            $SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD = iArr;
            try {
                iArr[METHOD.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD[METHOD.GET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD[METHOD.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD[METHOD.PUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum METHOD {
        POST,
        GET,
        DELETE,
        PUT;

        public Method toApiGwMethod() {
            int i = C09901.$SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD[ordinal()];
            if (i == 1) {
                return Method.POST;
            }
            if (i == 2) {
                return Method.GET;
            }
            if (i == 3) {
                return Method.DELETE;
            }
            if (i != 4) {
                return null;
            }
            return Method.PUT;
        }

        public AlcsCoAPConstant.Code toCoapCode() {
            int i = C09901.$SwitchMap$com$aliyun$alink$linksdk$cmp$api$CommonRequest$METHOD[ordinal()];
            if (i == 1) {
                return AlcsCoAPConstant.Code.POST;
            }
            if (i == 2) {
                return AlcsCoAPConstant.Code.GET;
            }
            if (i == 3) {
                return AlcsCoAPConstant.Code.DELETE;
            }
            if (i != 4) {
                return null;
            }
            return AlcsCoAPConstant.Code.PUT;
        }
    }
}

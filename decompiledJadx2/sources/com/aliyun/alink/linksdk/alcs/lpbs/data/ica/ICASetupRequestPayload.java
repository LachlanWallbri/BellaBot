package com.aliyun.alink.linksdk.alcs.lpbs.data.ica;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICASetupRequestPayload {

    /* renamed from: id */
    public String f865id;
    public String method;
    public ICASetupData params;
    public String version;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ICAProvisionAuthData {
        public String accessKey;
        public String accessToken;
        public String authCode;
        public String authSecret;
        public String deviceName;
        public String productKey;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ICASetupData {
        public String configType;
        public List<ICAProvisionAuthData> configValue = new ArrayList();
    }
}

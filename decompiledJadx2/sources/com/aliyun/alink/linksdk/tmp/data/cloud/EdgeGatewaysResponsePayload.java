package com.aliyun.alink.linksdk.tmp.data.cloud;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EdgeGatewaysResponsePayload {
    public int code;
    public EdgeGatewaysData data;
    public String message;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class EdgeGatewaysData {
        public List<EdgeGateway> edgeGateways;
        public int pageNo;
        public int pageSize;
        public int total;

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes.dex */
        public static class EdgeGateway {
            public String iotId;
            public List<Model> models;
            public String name;

            /* JADX WARN: Classes with same name are omitted:
              
             */
            /* loaded from: classes.dex */
            public static class Model {
                public String deviceName;
                public String modelName;
                public List<String> productKeys;
            }
        }
    }
}

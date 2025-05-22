package com.aliyun.alink.linksdk.tmp.data.config;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LocalConfigData {
    public ClientConfig client;
    public DynamicConfig configReceiver;
    public ProvisionConfig provision;
    public ServerConfig server;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ClientAuthInfo {
        public String accessKey;
        public String accessToken;
        public String deviceName;
        public String productKey;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ClientConfig {
        public List<ClientAuthInfo> AuthInfo;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DynamicConfig {
        public String authcode;
        public boolean autoRun;
        public String deviceName;
        public String productKey;
        public String secret;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ProvisionConfig {
        public List<ClientAuthInfo> AuthInfo;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ServerAuthInfo {
        public String authcode;
        public String deviceName;
        public String productKey;
        public String secret;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ServerConfig {
        public List<ServerAuthInfo> AuthInfo;
    }
}

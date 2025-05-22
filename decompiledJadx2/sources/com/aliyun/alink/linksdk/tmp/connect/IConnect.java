package com.aliyun.alink.linksdk.tmp.connect;

import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IConnect {
    public static final int OPTION_ICONNECT_BLACKLIST = 2;
    public static final int OPTION_ICONNECT_PREFIXSECRET = 1;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum ConnectType {
        CONNECT_TYPE_COAP,
        CONNECT_TYPE_MQTT,
        CONNECT_TYPE_APIGATE
    }

    boolean addConnectChangeListener(IDevStateChangeListener iDevStateChangeListener);

    String getConnectId(ConnectType connectType);

    TmpEnum.DeviceState getConnectState();

    ConnectType getConnectType();

    boolean rawResourcePublish(String str, String str2, byte[] bArr, IPublishResourceListener iPublishResourceListener);

    boolean reDiscoveryDevice(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler);

    boolean regTopic(String str, String str2, String str3, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler);

    boolean removeConnectChangeListener(IDevStateChangeListener iDevStateChangeListener);

    boolean resourcePublish(String str, String str2, String str3, OutputParams outputParams, IPublishResourceListener iPublishResourceListener);

    boolean send(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler);

    boolean setOption(String str, int i, Object obj);

    boolean startDiscovery(int i, INotifyHandler iNotifyHandler);

    boolean startNotifyMonitor(INotifyHandler iNotifyHandler);

    boolean stopConnect();

    boolean stopDiscovery();

    boolean stopNotifyMonitor();

    boolean subscribe(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler, INotifyHandler iNotifyHandler);

    boolean unInit();

    boolean unRegTopic(String str, String str2);

    boolean unsubscribe(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum TConnectState {
        UNKNOW(0),
        CONNECT(1),
        DISCONNECT(2);

        private int value;

        TConnectState(int i) {
            this.value = i;
        }

        public static TConnectState createConnectState(ConnectState connectState) {
            if (connectState == ConnectState.CONNECTED) {
                return CONNECT;
            }
            if (connectState == ConnectState.DISCONNECTED) {
                return DISCONNECT;
            }
            return UNKNOW;
        }
    }
}

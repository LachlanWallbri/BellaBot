package com.aliyun.alink.linksdk.tmp.connect;

import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnectConfig;
import com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsServerConnectConfig;
import com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsServerConnectOption;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DefaultClientConfig;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CmpConnect;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpConnectHandler;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConnectFactory {
    protected static final String TAG = "[Tmp]ConnectFactory";
    public static final String mAlcsConnecteId = "Alcs_Connect_ID";

    public static ConnectWrapper createConnect(String str) {
        return new CmpConnect(str);
    }

    public static ConnectWrapper createConnect(String str, String str2) {
        return new CmpConnect(str, str2);
    }

    public static ConnectWrapper createConnect() {
        return new CmpConnect(ConnectSDK.getInstance().getAlcsDiscoveryConnectId());
    }

    public static String createConnectId(DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        if (deviceBasicData.isLocal()) {
            if (deviceConfig.getDeviceType() == DeviceConfig.DeviceType.CLIENT || deviceConfig.getDeviceType() == DeviceConfig.DeviceType.PROVISION) {
                return createClientConnectId(deviceBasicData, deviceConfig, iDevListener);
            }
            if (deviceConfig.getDeviceType() == DeviceConfig.DeviceType.SERVER || deviceConfig.getDeviceType() == DeviceConfig.DeviceType.PROVISION_RECEIVER) {
                return createServerConnectId(deviceBasicData, deviceConfig, iDevListener);
            }
            ALog.m480e(TAG, "createConnectId local error");
            return mAlcsConnecteId;
        }
        ALog.m479d(TAG, "createConnectId api");
        iDevListener.onSuccess(null, new OutputParams(mAlcsConnecteId, new ValueWrapper.StringValueWrapper(ConnectSDK.getInstance().getApiGatewayConnectId())));
        return ConnectSDK.getInstance().getApiGatewayConnectId();
    }

    public static String createClientConnectId(DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        ALog.m479d(TAG, "createConnectId local client");
        DefaultClientConfig defaultClientConfig = (DefaultClientConfig) deviceConfig;
        String str = mAlcsConnecteId + deviceConfig.getBasicData().getProductKey() + deviceConfig.getBasicData().getDeviceName() + defaultClientConfig.getAccessKey() + defaultClientConfig.getAccessToken();
        CpConnectHandler cpConnectHandler = new CpConnectHandler(str, iDevListener);
        if (ConnectSDK.getInstance().isConnectRegisted(str) && (ConnectState.DISCONNECTED == ConnectSDK.getInstance().getConnectState(str) || ConnectState.CONNECTFAIL == ConnectSDK.getInstance().getConnectState(str))) {
            ALog.m484w(TAG, "createConnectId isConnectRegisted true but DISCONNECTED local connectId:" + str);
            ConnectSDK.getInstance().unregisterConnect(str);
        }
        if (ConnectSDK.getInstance().isConnectRegisted(str) && ConnectState.CONNECTED == ConnectSDK.getInstance().getConnectState(str)) {
            ALog.m484w(TAG, "CONNECTED createConnectId isConnectRegisted true local connectId:" + str);
            cpConnectHandler.onSuccess();
        } else if (ConnectSDK.getInstance().isConnectRegisted(str) && ConnectState.CONNECTING == ConnectSDK.getInstance().getConnectState(str)) {
            ALog.m484w(TAG, "CONNECTING createConnectId isConnectRegisted true local connectId:" + str);
            cpConnectHandler.onSuccess();
        } else {
            AlcsConnectConfig alcsConnectConfig = new AlcsConnectConfig();
            alcsConnectConfig.setDstAddr(deviceBasicData.getAddr());
            alcsConnectConfig.setDstPort(deviceBasicData.getPort());
            alcsConnectConfig.setProductKey(defaultClientConfig.getBasicData().getProductKey());
            alcsConnectConfig.setDeviceName(defaultClientConfig.getBasicData().getDeviceName());
            alcsConnectConfig.setAccessKey(defaultClientConfig.getAccessKey());
            alcsConnectConfig.setAccessToken(defaultClientConfig.getAccessToken());
            alcsConnectConfig.setIotId(defaultClientConfig.getBasicData().getIotId());
            alcsConnectConfig.setSecurity(true);
            alcsConnectConfig.mDataFormat = defaultClientConfig.mDateFormat;
            ALog.m479d(TAG, "createConnectId local client connectId:" + str + " ip:" + deviceBasicData.getAddr() + " port:" + deviceBasicData.getPort() + " pk:" + defaultClientConfig.getBasicData().getProductKey() + " dn:" + defaultClientConfig.getBasicData().getDeviceName() + " iotid:" + defaultClientConfig.getBasicData().getIotId());
            ConnectSDK.getInstance().registerAlcsConnect(TmpSdk.getContext(), str, alcsConnectConfig, cpConnectHandler);
        }
        return str;
    }

    public static String createServerConnectId(DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        ALog.m479d(TAG, "createConnectId local server");
        DefaultServerConfig defaultServerConfig = (DefaultServerConfig) deviceConfig;
        AlcsServerConnectConfig alcsServerConnectConfig = new AlcsServerConnectConfig();
        alcsServerConnectConfig.setPrefix(defaultServerConfig.getPrefix());
        alcsServerConnectConfig.setSecret(defaultServerConfig.getSecret());
        alcsServerConnectConfig.setProductKey(deviceBasicData.getProductKey());
        alcsServerConnectConfig.setDeviceName(deviceBasicData.getDeviceName());
        alcsServerConnectConfig.setBlackClients(TmpStorage.getInstance().getBlackList(deviceBasicData.getDevId()));
        if (ConnectSDK.getInstance().isConnectRegisted(ConnectSDK.getInstance().getAlcsServerConnectId())) {
            if (!"Xtau@iot".equalsIgnoreCase(alcsServerConnectConfig.getPrefix()) && !"Yx3DdsyetbSezlvc".equalsIgnoreCase(alcsServerConnectConfig.getSecret())) {
                updateAlcsServerConnectOption(defaultServerConfig.getPrefix(), defaultServerConfig.getSecret());
            }
            new CpConnectHandler(ConnectSDK.getInstance().getAlcsServerConnectId(), iDevListener).onSuccess();
        } else {
            ConnectSDK.getInstance().registerAlcsServerConnect(TmpSdk.getContext(), alcsServerConnectConfig, new CpConnectHandler(ConnectSDK.getInstance().getAlcsServerConnectId(), iDevListener));
        }
        return ConnectSDK.getInstance().getAlcsServerConnectId();
    }

    public static void updateAlcsServerConnectOption(String str, String str2) {
        ALog.m479d(TAG, "updateAlcsServerConnectOption authcode:" + str + " ConnectId:" + ConnectSDK.getInstance().getAlcsServerConnectId());
        if (ConnectSDK.getInstance().isConnectRegisted(ConnectSDK.getInstance().getAlcsServerConnectId())) {
            AlcsServerConnectOption alcsServerConnectOption = new AlcsServerConnectOption();
            alcsServerConnectOption.setOptionFlag(AlcsServerConnectOption.OptionFlag.ADD_PREFIX_SECRET);
            alcsServerConnectOption.setPrefix(str);
            alcsServerConnectOption.setSecrect(str2);
            ConnectSDK.getInstance().updateConnectOption(ConnectSDK.getInstance().getAlcsServerConnectId(), alcsServerConnectOption);
        }
    }

    public static void removeAlcsServerAuthInfo(String str) {
        ALog.m479d(TAG, "removeAlcsServerAuthInfo authcode:" + str + " ConnectId:" + ConnectSDK.getInstance().getAlcsServerConnectId());
        if (ConnectSDK.getInstance().isConnectRegisted(ConnectSDK.getInstance().getAlcsServerConnectId())) {
            AlcsServerConnectOption alcsServerConnectOption = new AlcsServerConnectOption();
            alcsServerConnectOption.setOptionFlag(AlcsServerConnectOption.OptionFlag.DELETE_PREFIX);
            alcsServerConnectOption.setPrefix(str);
            ConnectSDK.getInstance().updateConnectOption(ConnectSDK.getInstance().getAlcsServerConnectId(), alcsServerConnectOption);
        }
    }
}

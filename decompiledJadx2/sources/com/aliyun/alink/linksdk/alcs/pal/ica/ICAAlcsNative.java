package com.aliyun.alink.linksdk.alcs.pal.ica;

import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;
import com.aliyun.alink.linksdk.alcs.api.ICADisconnectListener;
import com.aliyun.alink.linksdk.alcs.api.ICAMsgListener;
import com.aliyun.alink.linksdk.alcs.api.ICAProbeListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAConnectParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAGroupReqMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAInitData;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAReqMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICARspMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICASubMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAUTConnectListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAUTPointEx;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAAlcsNative {
    public static final int ALCS_DISCOVERY_TYPE_FINISH = 0;
    public static final int ALCS_DISCOVERY_TYPE_FOUND = 1;
    public static final int ALCS_MSG_CODE_DELETE = 4;
    public static final int ALCS_MSG_CODE_GET = 1;
    public static final int ALCS_MSG_CODE_POST = 2;
    public static final int ALCS_MSG_CODE_PUT = 3;
    public static final int ALCS_MSG_TYPE_ACK = 2;
    public static final int ALCS_MSG_TYPE_CON = 0;
    public static final int ALCS_MSG_TYPE_NON = 1;
    public static final int ALCS_MSG_TYPE_RST = 3;
    public static final int ALCS_RESULT_DUPLICATE = 5;
    public static final int ALCS_RESULT_FAIL = 1;
    public static final int ALCS_RESULT_INSUFFICIENT_MEM = 2;
    public static final int ALCS_RESULT_INVALIDPARAM = 4;
    public static final int ALCS_RESULT_NOTFOUND = 3;
    public static final int ALCS_RESULT_OK = 0;
    public static final int ALCS_ROLE_BOTH = 2;
    public static final int ALCS_ROLE_CLIENT = 0;
    public static final int ALCS_ROLE_SERVER = 1;
    private static final String TAG = "[alcs_coap_sdk]ICAAlcsNative";

    protected static native int connectDeviceNative(String str, int i, ICADeviceInfo iCADeviceInfo, ICAAuthParams iCAAuthParams, ICAConnectListener iCAConnectListener);

    protected static native void deInitPalNative();

    protected static native int disConnectDeviceNative(ICADeviceInfo iCADeviceInfo);

    protected static native int discoveryDeviceNative(int i, ICADiscoveryListener iCADiscoveryListener);

    protected static native int initPalNative(ICADeviceInfo iCADeviceInfo, int i);

    protected static native boolean isDeviceOnlineNative(ICADeviceInfo iCADeviceInfo);

    protected static native int probeDeviceNative(String str, int i, ICADeviceInfo iCADeviceInfo, ICAProbeListener iCAProbeListener);

    protected static native int regDeviceNotifyListenerNative(ICADiscoveryListener iCADiscoveryListener);

    protected static native boolean removeDeviceDisconnectListenerNative(ICADeviceInfo iCADeviceInfo);

    protected static native int sendGroupRequestNative(ICAGroupReqMessage iCAGroupReqMessage, ICAAuthParams iCAAuthParams, ICAMsgListener iCAMsgListener);

    protected static native int sendRequestNative(ICAReqMessage iCAReqMessage, ICAMsgListener iCAMsgListener);

    protected static native int sendResponseNative(ICARspMessage iCARspMessage, int i, ICAMsgListener iCAMsgListener);

    protected static native boolean setDeviceDisconnectListenerNative(ICADeviceInfo iCADeviceInfo, ICADisconnectListener iCADisconnectListener);

    protected static native int stopDiscoveryDeviceNative();

    protected static native int subcribeNative(ICASubMessage iCASubMessage, ICAMsgListener iCAMsgListener, ICAMsgListener iCAMsgListener2);

    protected static native int unsubcribeNative(ICASubMessage iCASubMessage, ICAMsgListener iCAMsgListener);

    static {
        System.loadLibrary("coap");
    }

    public static void initPal(ICAInitData iCAInitData) {
        initPalNative(iCAInitData.deviceInfo, iCAInitData.role);
    }

    public static void deInitPal() {
        deInitPalNative();
    }

    public static void connectDevice(String str, int i, ICAConnectParams iCAConnectParams, ICAConnectListener iCAConnectListener) {
        ICAUTConnectListener iCAUTConnectListener = new ICAUTConnectListener(iCAConnectListener, new ICAUTPointEx(iCAConnectParams.deviceInfo.productKey, iCAConnectParams.deviceInfo.deviceName));
        int connectDeviceNative = connectDeviceNative(str, i, iCAConnectParams.deviceInfo, iCAConnectParams.authInfo, iCAUTConnectListener);
        ALog.m479d(TAG, "connectDevice ret:" + connectDeviceNative + " listener:" + iCAConnectListener);
        if (connectDeviceNative >= 0 || iCAConnectListener == null) {
            return;
        }
        iCAUTConnectListener.onLoad(connectDeviceNative, "connect fail", iCAConnectParams == null ? null : iCAConnectParams.deviceInfo);
    }

    public static void disConnectDevice(ICADeviceInfo iCADeviceInfo) {
        disConnectDeviceNative(iCADeviceInfo);
    }

    public static boolean isDeviceOnline(ICADeviceInfo iCADeviceInfo) {
        return isDeviceOnlineNative(iCADeviceInfo);
    }

    public static boolean setDeviceDisconnectListener(ICADeviceInfo iCADeviceInfo, ICADisconnectListener iCADisconnectListener) {
        return setDeviceDisconnectListenerNative(iCADeviceInfo, iCADisconnectListener);
    }

    public static boolean removeDeviceDisconnectListener(ICADeviceInfo iCADeviceInfo) {
        return removeDeviceDisconnectListenerNative(iCADeviceInfo);
    }

    public static void probeDevice(String str, int i, ICADeviceInfo iCADeviceInfo, ICAProbeListener iCAProbeListener) {
        int probeDeviceNative = probeDeviceNative(str, i, iCADeviceInfo, iCAProbeListener);
        ALog.m479d(TAG, "probeDevice ret:" + probeDeviceNative + " listener:" + iCAProbeListener);
        if (probeDeviceNative >= 0 || iCAProbeListener == null) {
            return;
        }
        iCAProbeListener.onComplete(iCADeviceInfo, probeDeviceNative);
    }

    public static boolean discoveryDevice(int i, ICADiscoveryListener iCADiscoveryListener) {
        return discoveryDeviceNative(i, iCADiscoveryListener) == 0;
    }

    public static boolean stopDiscoveryDevice() {
        return stopDiscoveryDeviceNative() == 0;
    }

    public static boolean sendRequest(ICAReqMessage iCAReqMessage, ICAMsgListener iCAMsgListener) {
        int sendRequestNative = sendRequestNative(iCAReqMessage, iCAMsgListener);
        if (sendRequestNative < 0 && iCAMsgListener != null) {
            iCAMsgListener.onLoad(new ICARspMessage(sendRequestNative));
        }
        return sendRequestNative == 0;
    }

    public static boolean sendGroupRequest(ICAGroupReqMessage iCAGroupReqMessage, ICAAuthParams iCAAuthParams, ICAMsgListener iCAMsgListener) {
        int sendGroupRequestNative = sendGroupRequestNative(iCAGroupReqMessage, iCAAuthParams, iCAMsgListener);
        if (sendGroupRequestNative < 0 && iCAMsgListener != null) {
            iCAMsgListener.onLoad(new ICARspMessage(sendGroupRequestNative));
        }
        return sendGroupRequestNative == 0;
    }

    public static boolean sendResponse(ICARspMessage iCARspMessage, int i, ICAMsgListener iCAMsgListener) {
        int sendResponseNative = sendResponseNative(iCARspMessage, i, iCAMsgListener);
        if (sendResponseNative < 0 && iCAMsgListener != null) {
            iCAMsgListener.onLoad(new ICARspMessage(sendResponseNative));
        }
        return sendResponseNative == 0;
    }

    public static boolean subcribe(ICASubMessage iCASubMessage, ICAMsgListener iCAMsgListener, ICAMsgListener iCAMsgListener2) {
        int subcribeNative = subcribeNative(iCASubMessage, iCAMsgListener, iCAMsgListener2);
        if (subcribeNative < 0 && iCAMsgListener != null) {
            iCAMsgListener.onLoad(new ICARspMessage(subcribeNative));
        }
        return subcribeNative == 0;
    }

    public static boolean unsubcribe(ICASubMessage iCASubMessage, ICAMsgListener iCAMsgListener) {
        int unsubcribeNative = unsubcribeNative(iCASubMessage, iCAMsgListener);
        if (unsubcribeNative < 0 && iCAMsgListener != null) {
            iCAMsgListener.onLoad(new ICARspMessage(unsubcribeNative));
        }
        return unsubcribeNative == 0;
    }

    public static boolean regDeviceNotifyListener(ICADiscoveryListener iCADiscoveryListener) {
        return regDeviceNotifyListenerNative(iCADiscoveryListener) == 0;
    }

    public static boolean unregDeviceNotifyListener() {
        return regDeviceNotifyListenerNative(null) == 0;
    }
}

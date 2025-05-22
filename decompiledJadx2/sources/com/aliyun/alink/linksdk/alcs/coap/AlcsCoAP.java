package com.aliyun.alink.linksdk.alcs.coap;

import android.text.TextUtils;
import android.util.SparseArray;
import com.aliyun.alink.linksdk.alcs.coap.option.Option;
import com.aliyun.alink.linksdk.alcs.coap.option.OptionSet;
import com.aliyun.alink.linksdk.alcs.coap.resources.AlcsCoAPResource;
import com.aliyun.alink.linksdk.alcs.coap.resources.Resource;
import com.aliyun.alink.linksdk.tools.ALog;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsCoAP {
    protected static final String TAG = "[alcs_coap_sdk]AlcsCoAP";
    protected static SparseArray<IAuthHandler> mAuthHandlerList;
    protected static Map<Long, AlcsCoAPContext> mContextList;
    protected static Map<Long, Map<Long, IAlcsCoAPReqHandler>> mReqHandlerList;
    protected static Map<String, AlcsCoAPResource> mResourceList;
    protected static AtomicInteger mUserData;

    protected static native void setLogLevelNative(int i);

    protected native void addIntOption(long j, long j2, int i, int i2);

    protected native boolean addKey(String str, int i, int i2, String str2);

    protected native void addStringOption(long j, long j2, int i, String str);

    protected native boolean addSvrAccessKey(long j, String str, String str2);

    public native void alcsStart(long j);

    public native void alcsStop(long j);

    protected native boolean authHasKey(long j, String str, int i, String str2, String str3, String str4, String str5, int i2);

    public native boolean cancelMessage(long j, long j2);

    protected native long createCoAPContext(AlcsCoAPContext alcsCoAPContext);

    protected native void freeContext(long j);

    public native boolean initAuth(long j, String str, String str2, int i);

    protected native long initRequest(long j, AlcsCoAPRequest alcsCoAPRequest);

    protected native long initResponse(long j, AlcsCoAPResponse alcsCoAPResponse);

    protected native boolean isServerOnLine(long j, String str, int i, String str2, String str3);

    public native boolean notifyObserve(long j, String str, byte[] bArr);

    protected native long registerResource(long j, AlcsCoAPResource alcsCoAPResource, String str, String str2);

    protected native boolean removeKey(String str, int i, int i2);

    protected native boolean removeSvrKey(long j, String str);

    protected native boolean sendAlcsRequest(long j, long j2, String str, int i);

    protected native boolean sendAlcsRequestSecure(long j, long j2, String str, int i, String str2, String str3);

    protected native boolean sendAlcsResponse(long j, long j2, String str, int i);

    protected native boolean sendAlcsResponseSecure(long j, long j2, String str, int i, String str2, String str3);

    protected native void unInitMessage(long j, long j2);

    protected native long unRegisterResource(long j, String str);

    protected native boolean updateSvrBlackList(long j, String str);

    static {
        System.loadLibrary("coap");
        mUserData = new AtomicInteger(0);
    }

    public AlcsCoAP() {
        if (mContextList == null) {
            mContextList = new HashMap();
        }
        if (mResourceList == null) {
            mResourceList = new HashMap();
        }
        if (mReqHandlerList == null) {
            mReqHandlerList = new HashMap();
        }
        if (mAuthHandlerList == null) {
            mAuthHandlerList = new SparseArray<>();
        }
    }

    public long createNewCoAPContext(AlcsCoAPContext alcsCoAPContext) {
        if (alcsCoAPContext == null) {
            ALog.m480e(TAG, "createNewCoAPContext error context null");
            return -1L;
        }
        long contextByPort = getContextByPort(alcsCoAPContext.getPort());
        if (contextByPort != 0) {
            alcsCoAPContext.mContextId = contextByPort;
            return contextByPort;
        }
        long createCoAPContext = createCoAPContext(alcsCoAPContext);
        alcsCoAPContext.setContextId(createCoAPContext);
        mContextList.put(Long.valueOf(createCoAPContext), alcsCoAPContext);
        return createCoAPContext;
    }

    protected long getContextByPort(int i) {
        Map<Long, AlcsCoAPContext> map = mContextList;
        long j = 0;
        if (map == null) {
            ALog.m480e(TAG, "getContextByPort mContextList empty");
            return 0L;
        }
        Iterator<Map.Entry<Long, AlcsCoAPContext>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Long, AlcsCoAPContext> next = it.next();
            if (next.getValue().getPort() == i) {
                j = next.getKey().longValue();
                break;
            }
        }
        ALog.m479d(TAG, "getContextByPort port:" + i + " contextId:" + j);
        return j;
    }

    public long freeCoAPContext(long j) {
        mContextList.remove(Long.valueOf(j));
        freeContext(j);
        return j;
    }

    public long createCoAPContext(AlcsCoAPContext alcsCoAPContext, AlcsCoAPResource alcsCoAPResource) {
        long createNewCoAPContext = createNewCoAPContext(alcsCoAPContext);
        registerAllResource(createNewCoAPContext, alcsCoAPResource, null, null);
        return createNewCoAPContext;
    }

    public void registerAllResource(long j, AlcsCoAPResource alcsCoAPResource, String str, String str2) {
        if (alcsCoAPResource == null || TextUtils.isEmpty(alcsCoAPResource.getPath())) {
            ALog.m480e(TAG, "registerAllResource resource null");
            return;
        }
        registerResource(j, alcsCoAPResource, str, str2);
        mResourceList.put(alcsCoAPResource.getPath(), alcsCoAPResource);
        Collection<Resource> children = alcsCoAPResource.getChildren();
        if (children == null || children.isEmpty()) {
            return;
        }
        Iterator<Resource> it = children.iterator();
        while (it.hasNext()) {
            registerResource(j, (AlcsCoAPResource) it.next(), str, str2);
        }
    }

    public void registerAllResource(long j, AlcsCoAPResource alcsCoAPResource) {
        registerAllResource(j, alcsCoAPResource, null, null);
    }

    public void unRegisterResource(long j, AlcsCoAPResource alcsCoAPResource) {
        unRegisterResourceByPath(j, alcsCoAPResource.getPath());
    }

    public long unRegisterResourceByPath(long j, String str) {
        ALog.m479d(TAG, "unRegisterResourceByPath contextId:" + j + " path:" + str);
        return unRegisterResource(j, str);
    }

    public long sendRequest(long j, AlcsCoAPRequest alcsCoAPRequest, IAlcsCoAPReqHandler iAlcsCoAPReqHandler) {
        ALog.m479d(TAG, "sendRequest coapContextId:" + j + " requestCallback:" + iAlcsCoAPReqHandler);
        long initRequest = initRequest(j, alcsCoAPRequest);
        putRequestCallback(j, initRequest, iAlcsCoAPReqHandler);
        initOptionSet(j, initRequest, alcsCoAPRequest.getOptions());
        sendAlcsRequest(j, initRequest, alcsCoAPRequest.getDestination().getHostAddress(), alcsCoAPRequest.getDestinationPort());
        unInitMessage(j, initRequest);
        return initRequest;
    }

    public long sendRequestS(long j, AlcsCoAPRequest alcsCoAPRequest, String str, String str2, IAlcsCoAPReqHandler iAlcsCoAPReqHandler) {
        ALog.m479d(TAG, "sendRequests coapContextId:" + j + " requestCallback:" + iAlcsCoAPReqHandler);
        long initRequest = initRequest(j, alcsCoAPRequest);
        putRequestCallback(j, initRequest, iAlcsCoAPReqHandler);
        initOptionSet(j, initRequest, alcsCoAPRequest.getOptions());
        sendAlcsRequestSecure(j, initRequest, alcsCoAPRequest.getDestination().getHostAddress(), alcsCoAPRequest.getDestinationPort(), str, str2);
        unInitMessage(j, initRequest);
        return initRequest;
    }

    public boolean sendResponse(long j, AlcsCoAPResponse alcsCoAPResponse) {
        long initResponse = initResponse(j, alcsCoAPResponse);
        ALog.m479d(TAG, "sendResponse coapContextId:" + j + " msgId:" + initResponse);
        initOptionSet(j, initResponse, alcsCoAPResponse.getOptions());
        sendAlcsResponse(j, initResponse, alcsCoAPResponse.getDestination().getHostAddress(), alcsCoAPResponse.getDestinationPort());
        unInitMessage(j, initResponse);
        return true;
    }

    public boolean sendResponseS(long j, AlcsCoAPResponse alcsCoAPResponse, String str, String str2) {
        ALog.m479d(TAG, "sendResponse coapContextId:" + j);
        long initResponse = initResponse(j, alcsCoAPResponse);
        initOptionSet(j, initResponse, alcsCoAPResponse.getOptions());
        sendAlcsResponseSecure(j, initResponse, alcsCoAPResponse.getDestination().getHostAddress(), alcsCoAPResponse.getDestinationPort(), str, str2);
        unInitMessage(j, initResponse);
        return true;
    }

    public boolean sendObserveResponse(long j, AlcsCoAPRequest alcsCoAPRequest, AlcsCoAPResponse alcsCoAPResponse) {
        ALog.m479d(TAG, "sendObserveResponse coapContextId:" + j);
        return notifyObserve(j, alcsCoAPRequest.getURI(), alcsCoAPResponse.getPayload());
    }

    public boolean authHasKey(long j, String str, int i, String str2, String str3, String str4, String str5, IAuthHandler iAuthHandler) {
        int incrementAndGet = mUserData.incrementAndGet();
        ALog.m479d(TAG, "authHasKey coapContextId:" + j + " ip:" + str + " port:" + i + " userData:" + incrementAndGet + " productKey:" + str2 + " deviceName:" + str3 + " accessKey:" + str4 + " accessToken:" + str5);
        mAuthHandlerList.put(incrementAndGet, iAuthHandler);
        return authHasKey(j, str, i, str2, str3, str4, str5, incrementAndGet);
    }

    public boolean isServerDevOnline(long j, String str, int i, String str2, String str3) {
        boolean isServerOnLine = isServerOnLine(j, str, i, str2, str3);
        ALog.m479d(TAG, "isServerDevOnline coapContextId:" + j + " ip:" + str + " port:" + i + " pk:" + str2 + " dn:" + str3 + " ret:" + isServerOnLine);
        return isServerOnLine;
    }

    public boolean addAlcsSvrAccessKey(long j, String str, String str2) {
        boolean addSvrAccessKey = addSvrAccessKey(j, str, str2);
        ALog.m479d(TAG, "addAlcsSvrAccessKey coapContextId:" + j + " prefix:" + str + " ret:" + addSvrAccessKey);
        return addSvrAccessKey;
    }

    public boolean removeAlcsSvrKey(long j, String str) {
        boolean removeSvrKey = removeSvrKey(j, str);
        ALog.m479d(TAG, "removeAlcsSvrKey coapContextId:" + j + " prefix:" + str + " ret:" + removeSvrKey);
        return removeSvrKey;
    }

    public boolean updateAlcsSvrBlackList(long j, String str) {
        boolean updateSvrBlackList = updateSvrBlackList(j, str);
        ALog.m479d(TAG, "updateAlcsSvrBlackList coapContextId:" + j + " blackList:" + str);
        return updateSvrBlackList;
    }

    public static void onRecvRequestHandler(long j, String str, String str2, int i, AlcsCoAPRequest alcsCoAPRequest) {
        InetAddress inetAddress;
        ALog.m479d(TAG, "onRecvRequestHandler contextId:" + j + " resourceId:" + str + " Ip:" + str2 + " port:" + i + " request:" + alcsCoAPRequest);
        alcsCoAPRequest.setSourcePort(i);
        try {
            inetAddress = InetAddress.getByName(str2);
        } catch (Exception e) {
            e.printStackTrace();
            inetAddress = null;
        }
        alcsCoAPRequest.setSource(inetAddress);
        AlcsCoAPResource alcsCoAPResource = mResourceList.get(str);
        AlcsCoAPContext alcsCoAPContext = mContextList.get(Long.valueOf(j));
        if (alcsCoAPResource != null && alcsCoAPResource.getHandler() != null) {
            alcsCoAPResource.getHandler().onRecRequest(alcsCoAPContext, alcsCoAPRequest);
        } else {
            ALog.m480e(TAG, "onRecvRequestHandler callback error null");
        }
    }

    public static void onSendRequestComplete(long j, long j2, String str, int i, int i2, AlcsCoAPResponse alcsCoAPResponse) {
        InetAddress inetAddress;
        ALog.m479d(TAG, "onSendRequestComplete contextId:" + j + " msgId:" + j2 + " Ip:" + str + " port:" + i + " result:" + i2 + " response:" + alcsCoAPResponse);
        try {
            inetAddress = InetAddress.getByName(str);
        } catch (Exception e) {
            e.printStackTrace();
            inetAddress = null;
        }
        if (alcsCoAPResponse != null) {
            alcsCoAPResponse.setSource(inetAddress);
            alcsCoAPResponse.setSourcePort(i);
        }
        AlcsCoAPContext alcsCoAPContext = mContextList.get(Long.valueOf(j));
        IAlcsCoAPReqHandler requestCallback = getRequestCallback(j, j2);
        if (requestCallback != null) {
            requestCallback.onReqComplete(alcsCoAPContext, i2, alcsCoAPResponse);
        } else {
            ALog.m480e(TAG, "onSendRequestComplete callback error null");
        }
    }

    public static void onClientAuthComplete(long j, String str, int i, int i2, int i3) {
        ALog.m479d(TAG, "onClientAuthComplete contextId:" + j + " Ip:" + str + " port:" + i + " result:" + i3 + " userdata:" + i2);
        IAuthHandler iAuthHandler = mAuthHandlerList.get(i2);
        if (iAuthHandler != null) {
            iAuthHandler.onAuthResult(str, i, i3);
        } else {
            ALog.m480e(TAG, "onClientAuthComplete error handler not found");
        }
        mAuthHandlerList.remove(i2);
    }

    public void setLogLevel(int i) {
        ALog.m479d(TAG, "setNativeLogLevel logLevel:" + i);
        setLogLevelNative(i);
    }

    public static void setLogLevelEx(int i) {
        ALog.m479d(TAG, "setNativeLogLevel logLevel:" + i);
        setLogLevelNative(i);
    }

    protected void initOptionSet(long j, long j2, OptionSet optionSet) {
        List<Option> asSortedList;
        if (optionSet == null || (asSortedList = optionSet.asSortedList()) == null || asSortedList.isEmpty()) {
            return;
        }
        for (Option option : asSortedList) {
            if (TextUtils.isEmpty(option.getStringValue())) {
                ALog.m480e(TAG, "initOptionSet stringvalue empty");
            } else {
                addStringOption(j, j2, option.getNumber(), option.getStringValue());
            }
        }
    }

    protected void putRequestCallback(long j, long j2, IAlcsCoAPReqHandler iAlcsCoAPReqHandler) {
        ALog.m479d(TAG, "putRequestCallback coapContextId: " + j + " msgId:" + j2);
        Map<Long, IAlcsCoAPReqHandler> map = mReqHandlerList.get(Long.valueOf(j));
        if (map == null) {
            map = new HashMap<>();
            mReqHandlerList.put(Long.valueOf(j), map);
        }
        map.put(Long.valueOf(j2), iAlcsCoAPReqHandler);
    }

    protected static IAlcsCoAPReqHandler getRequestCallback(long j, long j2) {
        ALog.m479d(TAG, "getRequestCallback coapContextId: " + j + " msgId:" + j2);
        Map<Long, IAlcsCoAPReqHandler> map = mReqHandlerList.get(Long.valueOf(j));
        if (map == null) {
            ALog.m480e(TAG, "getRequestCallback not find context");
            return null;
        }
        return map.get(Long.valueOf(j2));
    }
}

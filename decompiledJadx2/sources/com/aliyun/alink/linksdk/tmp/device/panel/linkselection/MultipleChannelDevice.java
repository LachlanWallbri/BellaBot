package com.aliyun.alink.linksdk.tmp.device.panel.linkselection;

import com.aliyun.alink.linksdk.tmp.device.panel.data.PanelMethodExtraData;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelDeviceLocalInitListener;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelEventCallback;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MultipleChannelDevice {
    private static final String TAG = "[Tmp]MultipleChannelDevice";
    private static Object mPropertyCacheData;
    private CloudChannelDevice mCloudChannelDevice;
    private String mIotId;
    private LocalChannelDevice mLocalChannelDevice;
    private WeakReference<IPanelEventCallback> mPanelEventCallback;
    private PanelMethodExtraData mPanelMethodExtraData;

    public MultipleChannelDevice(String str, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("MultipleChannelDevice iotId:");
        sb.append(str);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "null" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        this.mIotId = str;
        if (panelMethodExtraData == null) {
            this.mPanelMethodExtraData = new PanelMethodExtraData(TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST);
        } else {
            this.mPanelMethodExtraData = panelMethodExtraData;
        }
        this.mPanelEventCallback = new WeakReference<>(null);
        this.mCloudChannelDevice = new CloudChannelDevice(str, this.mPanelMethodExtraData.channelStrategy);
        this.mLocalChannelDevice = new LocalChannelDevice(str, this.mPanelMethodExtraData.channelStrategy);
    }

    private boolean isIniting() {
        boolean z = this.mCloudChannelDevice.isIniting() && this.mLocalChannelDevice.isLocalIniting();
        ALog.m479d(TAG, "isIniting :" + z);
        return z;
    }

    private boolean isLocalIniting() {
        boolean isLocalIniting = this.mLocalChannelDevice.isLocalIniting();
        ALog.m479d(TAG, "isLocalIniting :" + isLocalIniting);
        return isLocalIniting;
    }

    public void uninit() {
        ALog.m479d(TAG, "uninit iotid:" + this.mIotId);
        this.mCloudChannelDevice.uninit();
        this.mLocalChannelDevice.uninit();
    }

    public void init(IPanelCallback iPanelCallback) {
        init(null);
    }

    public String getIotId() {
        return this.mIotId;
    }

    public String getProductKey() {
        return this.mLocalChannelDevice.getProductKey();
    }

    public String getDeviceName() {
        return this.mLocalChannelDevice.getDeviceName();
    }

    public IPanelEventCallback getPanelEventCallback() {
        return this.mPanelEventCallback.get();
    }

    public void init(final IPanelCallback iPanelCallback, IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener) {
        ALog.m479d(TAG, "init  mIotId:" + this.mIotId + " callback:" + iPanelCallback + " mIsIniting:" + isIniting() + " localInitListener:" + iPanelDeviceLocalInitListener);
        if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == this.mPanelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.init(iPanelCallback, iPanelDeviceLocalInitListener);
        } else if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == this.mPanelMethodExtraData.channelStrategy) {
            this.mCloudChannelDevice.init(iPanelCallback);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST == this.mPanelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.init(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.1
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z, Object obj) {
                    MultipleChannelDevice.this.mCloudChannelDevice.init(iPanelCallback);
                }
            }, iPanelDeviceLocalInitListener);
        }
    }

    public boolean isInit() {
        boolean z;
        if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == this.mPanelMethodExtraData.channelStrategy) {
            z = this.mCloudChannelDevice.isInit();
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == this.mPanelMethodExtraData.channelStrategy) {
            z = this.mLocalChannelDevice.isInit();
        } else {
            z = this.mCloudChannelDevice.isInit() && this.mLocalChannelDevice.isInit();
        }
        ALog.m479d(TAG, "isInit :" + z);
        return z;
    }

    public void getProperties(final IPanelCallback iPanelCallback, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("getProperties callback:");
        sb.append(iPanelCallback);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        Object propertyCacheDataAndClear = getPropertyCacheDataAndClear();
        if (propertyCacheDataAndClear != null) {
            ALog.m479d(TAG, "getProperties cacheData not null");
            iPanelCallback.onComplete(true, propertyCacheDataAndClear);
            return;
        }
        if (panelMethodExtraData == null) {
            ALog.m484w(TAG, "getProperties extraData null");
            panelMethodExtraData = new PanelMethodExtraData(TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST);
        }
        if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mCloudChannelDevice.getProperties(iPanelCallback);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.getProperties(iPanelCallback, true);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.getProperties(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.2
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z, Object obj) {
                    if (!z) {
                        MultipleChannelDevice.this.mCloudChannelDevice.getProperties(iPanelCallback);
                    } else {
                        iPanelCallback.onComplete(z, obj);
                    }
                }
            }, true);
        }
    }

    @Deprecated
    public void cacheProperties(final IPanelCallback iPanelCallback, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("cacheProperties callback:");
        sb.append(iPanelCallback);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        setPropertyCacheData(null);
        getProperties(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.3
            @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
            public void onComplete(boolean z, Object obj) {
                ALog.m479d(MultipleChannelDevice.TAG, "cacheProperties isSuccess:" + z + " data:" + obj);
                if (z && obj != null) {
                    MultipleChannelDevice.this.setPropertyCacheData(obj);
                }
                IPanelCallback iPanelCallback2 = iPanelCallback;
                if (iPanelCallback2 == null) {
                    ALog.m484w(MultipleChannelDevice.TAG, "getProperties callback null");
                } else {
                    iPanelCallback2.onComplete(z, obj);
                }
            }
        }, panelMethodExtraData);
    }

    public void setProperties(final String str, final IPanelCallback iPanelCallback, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("setProperties params:");
        sb.append(str);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "setProperties callback null");
            return;
        }
        if (panelMethodExtraData == null) {
            ALog.m484w(TAG, "setProperties extraData null");
            panelMethodExtraData = new PanelMethodExtraData(TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST);
        }
        if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mCloudChannelDevice.setProperties(str, iPanelCallback);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.setProperties(str, iPanelCallback, true);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.setProperties(str, new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.4
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z, Object obj) {
                    if (!z) {
                        MultipleChannelDevice.this.mCloudChannelDevice.setProperties(str, iPanelCallback);
                    } else {
                        iPanelCallback.onComplete(z, obj);
                    }
                }
            }, true);
        }
    }

    @Deprecated
    public void getEvents(IPanelCallback iPanelCallback) {
        getLastEvent(iPanelCallback);
    }

    public void getLastEvent(IPanelCallback iPanelCallback) {
        this.mCloudChannelDevice.getLastEvent(iPanelCallback);
    }

    public void invokeService(final String str, final IPanelCallback iPanelCallback, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("invokeService params:");
        sb.append(str);
        sb.append("callback:");
        sb.append(iPanelCallback);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "invokeService callback null");
            return;
        }
        if (panelMethodExtraData == null) {
            ALog.m484w(TAG, "invokeService extraData null");
            panelMethodExtraData = new PanelMethodExtraData(TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST);
        }
        if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mCloudChannelDevice.invokeService(str, iPanelCallback);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.invokeService(str, iPanelCallback, true);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.invokeService(str, new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.5
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z, Object obj) {
                    if (!z) {
                        MultipleChannelDevice.this.mCloudChannelDevice.invokeService(str, iPanelCallback);
                    } else {
                        iPanelCallback.onComplete(z, obj);
                    }
                }
            }, true);
        }
    }

    public void getStatus(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getStatus callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getStatus callback  null");
        } else if (this.mLocalChannelDevice.isInit()) {
            this.mLocalChannelDevice.getStatus(iPanelCallback);
        } else {
            this.mCloudChannelDevice.getStatus(iPanelCallback);
        }
    }

    public void subAllEvents(IPanelEventCallback iPanelEventCallback, IPanelCallback iPanelCallback) {
        subAllEvents(iPanelEventCallback, iPanelCallback, this.mPanelMethodExtraData);
    }

    public void subAllEvents(final IPanelEventCallback iPanelEventCallback, final IPanelCallback iPanelCallback, PanelMethodExtraData panelMethodExtraData) {
        StringBuilder sb = new StringBuilder();
        sb.append("subAllEvents listener:");
        sb.append(iPanelEventCallback);
        sb.append(" callback:");
        sb.append(iPanelCallback);
        sb.append(" extraData:");
        sb.append(panelMethodExtraData == null ? "" : panelMethodExtraData.toString());
        ALog.m479d(TAG, sb.toString());
        if (iPanelEventCallback == null) {
            ALog.m480e(TAG, "subAllEvent callback null");
            return;
        }
        this.mPanelEventCallback = new WeakReference<>(iPanelEventCallback);
        if (panelMethodExtraData == null) {
            ALog.m484w(TAG, "subAllEvents extraData null");
            panelMethodExtraData = new PanelMethodExtraData(TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST);
        }
        if (TmpEnum.ChannelStrategy.CLOUD_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mCloudChannelDevice.subAllEvents(iPanelEventCallback, iPanelCallback);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_ONLY == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.subAllEvents(iPanelEventCallback, iPanelCallback, true);
        } else if (TmpEnum.ChannelStrategy.LOCAL_CHANNEL_FIRST == panelMethodExtraData.channelStrategy) {
            this.mLocalChannelDevice.subAllEvents(iPanelEventCallback, new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.6
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(final boolean z, Object obj) {
                    MultipleChannelDevice.this.mCloudChannelDevice.subAllEvents(iPanelEventCallback, new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.MultipleChannelDevice.6.1
                        @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                        public void onComplete(boolean z2, Object obj2) {
                            boolean z3 = z || z2;
                            ALog.m479d(MultipleChannelDevice.TAG, "subAllEvents onComplete ret:" + z3);
                            if (iPanelCallback != null) {
                                iPanelCallback.onComplete(z3, null);
                            }
                        }
                    });
                }
            }, true);
        }
    }

    public void startLocalConnect(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "startLocalConnect callback:" + iPanelCallback + " mLocalChannelDevice:" + this.mLocalChannelDevice);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "startLocalConnect callback:" + iPanelCallback);
            return;
        }
        this.mLocalChannelDevice.startLocalConnect(iPanelCallback);
    }

    public void stopLocalConnect(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "stopLocalConnect callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "stopLocalConnect callback:" + iPanelCallback);
            return;
        }
        this.mLocalChannelDevice.stopLocalConnect(iPanelCallback);
    }

    public void getLocalConnectionState(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getLocalConnectionState islocaliniting:" + isLocalIniting() + " isIniting:" + isIniting());
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "stopLocalConnect callback:" + iPanelCallback);
            return;
        }
        this.mLocalChannelDevice.getLocalConnectionState(iPanelCallback);
    }

    @Deprecated
    public Object getPropertyCacheData() {
        return mPropertyCacheData;
    }

    @Deprecated
    public void setPropertyCacheData(Object obj) {
        mPropertyCacheData = obj;
    }

    @Deprecated
    public Object getPropertyCacheDataAndClear() {
        Object propertyCacheData = getPropertyCacheData();
        setPropertyCacheData(null);
        return propertyCacheData;
    }
}

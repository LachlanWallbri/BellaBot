package com.aliyun.alink.linksdk.tmp.config;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.configuration.ProvisionResponser;
import com.aliyun.alink.linksdk.tmp.device.wrapper.ServerWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IProvisionListener;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler;
import com.aliyun.alink.linksdk.tmp.resource.TResManager;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ProvisionReceiver extends ServerWrapper implements ITResRequestInnerHandler {
    private static final String TAG = "[Tmp]ProvisionReceiver";
    private String mDeviceName;
    private boolean mIsStarted;
    private Map<Integer, IProvisionListener> mListenerList;
    private String mProductKey;

    private ProvisionReceiver() {
        this.mIsStarted = false;
        this.mListenerList = new ConcurrentHashMap();
    }

    public static ProvisionReceiver getInstance() {
        return InstanceHolder.mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {
        private static ProvisionReceiver mInstance = new ProvisionReceiver();

        private InstanceHolder() {
        }
    }

    public void init(DeviceConfig deviceConfig, Object obj, IDevListener iDevListener) {
        this.mConfig = deviceConfig;
        super.init(obj, iDevListener);
    }

    public void start(String str, String str2) {
        ALog.m479d(TAG, "start pk:" + str + " dn:" + str + " mIsStarted:" + this.mIsStarted);
        if (this.mIsStarted) {
            return;
        }
        this.mProductKey = str;
        this.mDeviceName = str2;
        TResManager.getinstance().regTopic(this.mDeviceImpl.getConnect(), TmpConstant.IDENTIFIER_SETUP, TextHelper.formatTopic(str, str2, TmpConstant.PATH_SETUP), true, this);
        this.mIsStarted = true;
    }

    public void stop() {
        ALog.m479d(TAG, "stop pk:" + this.mProductKey + " dn:" + this.mDeviceName + " mIsStarted:" + this.mIsStarted);
        if (this.mIsStarted) {
            this.mIsStarted = false;
            TResManager.getinstance().unRegTopic(this.mDeviceImpl.getConnect(), TmpConstant.IDENTIFIER_SETUP, TextHelper.formatTopic(this.mProductKey, this.mDeviceName, TmpConstant.PATH_SETUP));
        }
    }

    public void addListener(IProvisionListener iProvisionListener) {
        if (iProvisionListener != null) {
            this.mListenerList.put(Integer.valueOf(iProvisionListener.hashCode()), iProvisionListener);
        }
    }

    public void removeListener(IProvisionListener iProvisionListener) {
        if (iProvisionListener != null) {
            this.mListenerList.remove(Integer.valueOf(iProvisionListener.hashCode()));
        }
    }

    private void onNotify(String str, String str2, Object obj, ITResResponseCallback iTResResponseCallback) {
        HashMap hashMap = new HashMap(this.mListenerList);
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            ((IProvisionListener) ((Map.Entry) it.next()).getValue()).onMsg(str, str2, obj, new ProvisionResponser(iTResResponseCallback, hashMap.size()));
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler
    public void onProcess(String str, String str2, Object obj, ITResResponseCallback iTResResponseCallback) {
        onNotify(str, str2, obj, iTResResponseCallback);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        ALog.m479d(TAG, "onSuccess returnValue:" + outputParams);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onFail errorInfo:" + errorInfo);
    }
}

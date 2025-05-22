package com.aliyun.alink.linksdk.tmp.device.asynctask.discovery;

import android.os.Handler;
import android.os.Looper;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.manager.discovery.DiscoveryMessage;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IDiscoveryFilter;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpDiscoverRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpResponse;
import com.aliyun.alink.linksdk.tmp.data.cloud.CloudLcaRequestParams;
import com.aliyun.alink.linksdk.tmp.data.cloud.EdgeGatewaysResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.notify.DiscoveryDeviceStateMgr;
import com.aliyun.alink.linksdk.tmp.device.payload.discovery.DiscoveryRequestPayload;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tmp.utils.WifiManagerUtil;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.linksdk.alcs.AlcsCmpSDK;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiscoveryTask extends DeviceAsyncTask<DiscoveryTask> implements INotifyHandler {
    private static final String TAG = "[Tmp]DiscoveryTask";
    protected TmpCommonRequest mDiscoveryReqeust;
    protected Object mExtraParams;
    protected IDiscoveryFilter mFilter;
    protected Map<String, DeviceBasicData> mFoundDevList;
    protected Handler mHandler;
    protected ProbeDevicesRunnable mProbeDeviceRunnable;
    protected List<ProbeTask> mProbeTaskFlowList;
    protected long mTimeOut;
    protected TimeOutRunnable mTimeoutRunnable;

    public DiscoveryTask(ConnectWrapper connectWrapper, IDevListener iDevListener) {
        super(null, iDevListener);
        this.mConnect = connectWrapper;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTimeoutRunnable = new TimeOutRunnable(this);
        this.mProbeDeviceRunnable = new ProbeDevicesRunnable(this);
        this.mFoundDevList = new ConcurrentHashMap();
        this.mProbeTaskFlowList = new CopyOnWriteArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DiscoveryTask setFilter(IDiscoveryFilter iDiscoveryFilter) {
        this.mFilter = iDiscoveryFilter;
        return (DiscoveryTask) this.mTask;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DiscoveryTask setExtraParams(Object obj) {
        this.mExtraParams = obj;
        return (DiscoveryTask) this.mTask;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0080, code lost:
    
        if (r4.mFilter.doFilter(r1) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onDeviceFound(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ALog.m479d(TAG, "onDeviceFound response:" + tmpCommonResponse + " mDeviceHandler:" + this.mDeviceHandler + " mFilter:" + this.mFilter);
        if (tmpCommonResponse == null || tmpCommonResponse.getResponse() == null) {
            LogCat.m471e(TAG, "addDevice error response null or unsuccess");
            return;
        }
        boolean z = false;
        DiscoveryMessage discoveryMessage = (DiscoveryMessage) ((CpResponse) tmpCommonResponse).getResponse().data;
        if (discoveryMessage == null) {
            ALog.m480e(TAG, "onDeviceFound discoveryMessage or deviceInfo null");
            return;
        }
        DeviceBasicData deviceBasicData = new DeviceBasicData(true);
        deviceBasicData.setProductKey(discoveryMessage.productKey);
        deviceBasicData.setDeviceName(discoveryMessage.deviceName);
        deviceBasicData.setModelType(discoveryMessage.modelType);
        if (this.mFilter != null) {
            ALog.m484w(TAG, "mFilter true basicData:" + deviceBasicData.getDevId());
        }
        z = true;
        this.mFoundDevList.put(deviceBasicData.getDevId(), deviceBasicData);
        if (z) {
            DeviceManager.getInstance().addDeviceBasicData(deviceBasicData);
        }
        IDevListener iDevListener = this.mDeviceHandler;
        ALog.m479d(TAG, "onDeviceFound tmpHander:" + iDevListener + " isNeedNotify:" + z);
        if (iDevListener == null || !z) {
            return;
        }
        DevFoundOutputParams devFoundOutputParams = new DevFoundOutputParams();
        devFoundOutputParams.setProductKey(deviceBasicData.getProductKey());
        devFoundOutputParams.setDeviceName(deviceBasicData.getDeviceName());
        devFoundOutputParams.setModelType(deviceBasicData.getModelType());
        iDevListener.onSuccess(this.mTag, devFoundOutputParams);
    }

    public void setTimeout(long j) {
        this.mTimeOut = j;
    }

    public void onTimeOut() {
        LogCat.m469d(TAG, "onTimeOut");
        stop(true);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask
    protected void onNormalFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mConnect.stopDiscovery();
        if (this.mDeviceHandler == null) {
            LogCat.m471e(TAG, "onFlowComplete handler empty error");
            return;
        }
        IDevListener iDevListener = this.mDeviceHandler;
        this.mDeviceHandler = null;
        iDevListener.onSuccess(this.mTag, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask
    protected void onNormalFlowError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mConnect.stopDiscovery();
        if (this.mDeviceHandler == null) {
            LogCat.m477w(TAG, "onFlowError empty error");
            return;
        }
        IDevListener iDevListener = this.mDeviceHandler;
        this.mDeviceHandler = null;
        iDevListener.onSuccess(this.mTag, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void taskSuccess(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        super.taskSuccess((DiscoveryTask) tmpCommonRequest, (TmpCommonRequest) tmpCommonResponse);
        startProbeDifferentDeivces();
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void taskError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        super.taskError((DiscoveryTask) tmpCommonRequest, errorInfo);
    }

    protected void startProbeDifferentDeivces() {
        ALog.m479d(TAG, "startProbeDifferentDeivces");
        List<DeviceBasicData> allDeviceDataList = DeviceManager.getInstance().getAllDeviceDataList();
        if (allDeviceDataList == null || allDeviceDataList.isEmpty()) {
            ALog.m479d(TAG, "startProbeDifferentDeivces allFoundDeviceList empty");
            return;
        }
        for (final DeviceBasicData deviceBasicData : allDeviceDataList) {
            if (!this.mFoundDevList.containsKey(deviceBasicData.getDevId())) {
                ProbeTask probeTask = new ProbeTask(this.mDeviceImplRef.get(), this.mConnect, deviceBasicData, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.discovery.DiscoveryTask.1
                    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                    public void onSuccess(Object obj, OutputParams outputParams) {
                        DiscoveryDeviceStateMgr.getInstance().onDiscoveryDeviceStateChange(deviceBasicData, TmpEnum.DiscoveryDeviceState.DISCOVERY_STATE_ONLINE);
                    }

                    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                    public void onFail(Object obj, ErrorInfo errorInfo) {
                        DeviceManager.getInstance().removeDeviceBasicData(deviceBasicData.getDevId());
                        DiscoveryDeviceStateMgr.getInstance().onDiscoveryDeviceStateChange(deviceBasicData, TmpEnum.DiscoveryDeviceState.DISCOVERY_STATE_OFFLINE);
                    }
                });
                new AsyncTaskFlow().appendTask(probeTask).action();
                this.mProbeTaskFlowList.add(probeTask);
            }
        }
    }

    protected void stopProbeDifferentDeivces() {
        ALog.m479d(TAG, "stopProbeDifferentDeivces");
        this.mHandler.removeCallbacks(this.mProbeDeviceRunnable);
        List<ProbeTask> list = this.mProbeTaskFlowList;
        if (list == null || list.isEmpty()) {
            ALog.m479d(TAG, "stopProbeDifferentDeivces mProbeTaskFlowList empty");
            return;
        }
        Iterator<ProbeTask> it = this.mProbeTaskFlowList.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
        this.mProbeTaskFlowList.clear();
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        queryCloudLcaDeviceList();
        InetAddress broadcast = WifiManagerUtil.getBroadcast(WifiManagerUtil.getIpAddress(WifiManagerUtil.NetworkType.ETHERNET));
        if (broadcast != null) {
            AlcsCmpSDK.DISCOVERY_ADDR = broadcast.getHostAddress();
        }
        DiscoveryRequestPayload discoveryRequestPayload = new DiscoveryRequestPayload();
        discoveryRequestPayload.setMethod("core.service.dev");
        this.mDiscoveryReqeust = TmpDiscoverRequestBuilder.createBuilder().setTag(this.mTag).setPayloadData(discoveryRequestPayload).setAddr(broadcast != null ? broadcast.getHostAddress() : AlcsCmpSDK.DISCOVERY_ADDR).createRequest();
        this.mConnect.startDiscovery((int) this.mTimeOut, this);
        this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeOut + 1000);
        this.mHandler.postDelayed(this.mProbeDeviceRunnable, this.mTimeOut);
        return true;
    }

    protected void queryCloudLcaDeviceList() {
        Object obj = this.mExtraParams;
        if (obj == null || !(obj instanceof CloudLcaRequestParams)) {
            return;
        }
        CloudLcaRequestParams cloudLcaRequestParams = (CloudLcaRequestParams) obj;
        CloudUtils.getLcaDeviceList(cloudLcaRequestParams.groupId, cloudLcaRequestParams.gatewayIotId, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.discovery.DiscoveryTask.2
            /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
            
                if (r10.this$0.mFilter.doFilter(r7) != false) goto L39;
             */
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ALog.m479d(DiscoveryTask.TAG, "queryCloudLcaDeviceList onResponse aRequest:" + aRequest + " aResponse:" + aResponse);
                if (aResponse == null || aResponse.data == null) {
                    ALog.m480e(DiscoveryTask.TAG, "getLcaDeviceList onResponse null");
                    return;
                }
                EdgeGatewaysResponsePayload edgeGatewaysResponsePayload = (EdgeGatewaysResponsePayload) GsonUtils.fromJson(String.valueOf(aResponse.data), new TypeToken<EdgeGatewaysResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.discovery.DiscoveryTask.2.1
                }.getType());
                if (edgeGatewaysResponsePayload == null || edgeGatewaysResponsePayload.data == null || edgeGatewaysResponsePayload.data.edgeGateways == null || edgeGatewaysResponsePayload.data.edgeGateways.isEmpty()) {
                    ALog.m480e(DiscoveryTask.TAG, "getLcaDeviceList payload null");
                    return;
                }
                boolean z = false;
                for (EdgeGatewaysResponsePayload.EdgeGatewaysData.EdgeGateway edgeGateway : edgeGatewaysResponsePayload.data.edgeGateways) {
                    if (edgeGateway.models != null && !edgeGateway.models.isEmpty()) {
                        for (EdgeGatewaysResponsePayload.EdgeGatewaysData.EdgeGateway.Model model : edgeGateway.models) {
                            if (model.productKeys != null && !model.productKeys.isEmpty()) {
                                for (String str : model.productKeys) {
                                    DeviceBasicData deviceBasicData = new DeviceBasicData(false);
                                    deviceBasicData.setProductKey(str);
                                    deviceBasicData.setDeviceName(model.deviceName);
                                    deviceBasicData.setModelType(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
                                    if (DiscoveryTask.this.mFilter != null) {
                                        ALog.m484w(DiscoveryTask.TAG, "mFilter true basicData:" + deviceBasicData.getDevId());
                                    }
                                    z = true;
                                    IDevListener iDevListener = DiscoveryTask.this.mDeviceHandler;
                                    ALog.m479d(DiscoveryTask.TAG, "queryCloudLcaDeviceList outputParams callback:" + iDevListener + " isNeedNotify:" + z + " basicData：" + deviceBasicData);
                                    if (iDevListener != null && z) {
                                        DevFoundOutputParams devFoundOutputParams = new DevFoundOutputParams();
                                        devFoundOutputParams.setProductKey(deviceBasicData.getProductKey());
                                        devFoundOutputParams.setDeviceName(deviceBasicData.getDeviceName());
                                        devFoundOutputParams.setModelType(deviceBasicData.getModelType());
                                        devFoundOutputParams.setStringValue(DevFoundOutputParams.PARAMS_GATEWAY_IOTID, edgeGateway.iotId);
                                        devFoundOutputParams.setStringValue(DevFoundOutputParams.PARAMS_GATEWAY_NAME, edgeGateway.name);
                                        devFoundOutputParams.setStringValue(DevFoundOutputParams.PARAMS_MODEL_NAME, model.modelName);
                                        iDevListener.onSuccess(DiscoveryTask.this.mTag, devFoundOutputParams);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m480e(DiscoveryTask.TAG, "queryCloudLcaDeviceList onFailure aRequest:" + aRequest + " aError:" + aError);
            }
        });
    }

    public boolean stop(boolean z) {
        ALog.m479d(TAG, "stop isTimeout:" + z);
        taskError(this.mDiscoveryReqeust, (ErrorInfo) null);
        if (z) {
            return true;
        }
        stopProbeDifferentDeivces();
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        onDeviceFound(tmpCommonRequest, tmpCommonResponse);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class TimeOutRunnable implements Runnable {
        protected WeakReference<DiscoveryTask> mTaskRef;

        public TimeOutRunnable(DiscoveryTask discoveryTask) {
            this.mTaskRef = new WeakReference<>(discoveryTask);
        }

        @Override // java.lang.Runnable
        public void run() {
            DiscoveryTask discoveryTask = this.mTaskRef.get();
            if (discoveryTask != null) {
                discoveryTask.onTimeOut();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ProbeDevicesRunnable implements Runnable {
        protected WeakReference<DiscoveryTask> mTaskRef;

        public ProbeDevicesRunnable(DiscoveryTask discoveryTask) {
            this.mTaskRef = new WeakReference<>(discoveryTask);
        }

        @Override // java.lang.Runnable
        public void run() {
            DiscoveryTask discoveryTask = this.mTaskRef.get();
            if (discoveryTask != null) {
                discoveryTask.startProbeDifferentDeivces();
            }
        }
    }
}

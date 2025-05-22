package com.aliyun.alink.linksdk.tmp.component.pkdnconvert;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener;
import com.aliyun.alink.linksdk.tmp.data.auth.ProductKeyResult;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DefaultDevInfoTrans implements IDevInfoTrans {
    private static final String TAG = "[Tmp]DefaultDevInfoTrans";
    private Map<String, String> mProductIdToKeyList;
    private Map<String, PalDeviceInfo> mToAliIoTPkDnList;
    private Map<String, PalDeviceInfo> mToPrivatePkDnList;

    private DefaultDevInfoTrans() {
        this.mToPrivatePkDnList = new ConcurrentHashMap();
        this.mToAliIoTPkDnList = new ConcurrentHashMap();
        this.mProductIdToKeyList = new ConcurrentHashMap();
    }

    public static DefaultDevInfoTrans getInstance() {
        return SingleInstanceHolder.mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class SingleInstanceHolder {
        private static DefaultDevInfoTrans mInstance = new DefaultDevInfoTrans();

        private SingleInstanceHolder() {
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans
    public void init(final PalDiscoveryDeviceInfo palDiscoveryDeviceInfo, final IDevInfoTrans.IDevInfoTransListener iDevInfoTransListener) {
        if (palDiscoveryDeviceInfo == null || iDevInfoTransListener == null || palDiscoveryDeviceInfo.deviceInfo == null) {
            ALog.m480e(TAG, "init DefaultDevInfoTrans error privateDeviceInfo or listener null");
            return;
        }
        if ("3".equalsIgnoreCase(palDiscoveryDeviceInfo.modelType) && palDiscoveryDeviceInfo.deviceInfo.productModel.endsWith("-model")) {
            insertConvertData(palDiscoveryDeviceInfo.deviceInfo, new PalDeviceInfo(palDiscoveryDeviceInfo.deviceInfo.productModel.replace("-model", ""), palDiscoveryDeviceInfo.deviceInfo.deviceId));
            iDevInfoTransListener.onComplete(true, null);
            ALog.m479d(TAG, "MODEL_TYPE_ALI_THIRD_PART local found");
            return;
        }
        if ("2".equalsIgnoreCase(palDiscoveryDeviceInfo.modelType)) {
            String str = this.mProductIdToKeyList.get(palDiscoveryDeviceInfo.deviceInfo.productModel);
            ALog.m479d(TAG, "MODEL_TYPE_ALI_BREEZE pubProductKey:" + str + " productModel:" + palDiscoveryDeviceInfo.deviceInfo.productModel);
            if (!TextUtils.isEmpty(str)) {
                breezeTrans(palDiscoveryDeviceInfo, str, iDevInfoTransListener);
                return;
            } else {
                TmpSdk.getCloudProxy().queryProdtKeyFromProdtId(palDiscoveryDeviceInfo.deviceInfo.productModel, new ICloudProxyListener() { // from class: com.aliyun.alink.linksdk.tmp.component.pkdnconvert.DefaultDevInfoTrans.1
                    @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                    public void onResponse(String str2, Object obj) {
                        ALog.m479d(DefaultDevInfoTrans.TAG, "queryProdtKeyFromProdtId onResponse id:" + str2);
                        if (obj == null) {
                            ALog.m480e(DefaultDevInfoTrans.TAG, "queryProdtKeyFromProdtId onResponse rawPayload empty");
                            iDevInfoTransListener.onComplete(false, null);
                            return;
                        }
                        ProductKeyResult productKeyResult = (ProductKeyResult) JSON.parseObject(String.valueOf(obj), ProductKeyResult.class);
                        if (productKeyResult != null && !TextUtils.isEmpty(productKeyResult.productKey)) {
                            DefaultDevInfoTrans.this.mProductIdToKeyList.put(palDiscoveryDeviceInfo.deviceInfo.productModel, productKeyResult.productKey);
                            DefaultDevInfoTrans.this.breezeTrans(palDiscoveryDeviceInfo, productKeyResult.productKey, iDevInfoTransListener);
                        } else {
                            ALog.m480e(DefaultDevInfoTrans.TAG, "queryProdtKeyFromProdtId onResponse productKeyResult or productKey empty");
                            iDevInfoTransListener.onComplete(false, null);
                        }
                    }

                    @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                    public void onFailure(String str2, AError aError) {
                        ALog.m480e(DefaultDevInfoTrans.TAG, "queryProdtKeyFromProdtId onFailure id:" + str2);
                        iDevInfoTransListener.onComplete(false, null);
                    }
                });
                return;
            }
        }
        ALog.m479d(TAG, "init don't need translate modelType:" + palDiscoveryDeviceInfo.modelType + " pk:" + palDiscoveryDeviceInfo.deviceInfo.productModel + " dn:" + palDiscoveryDeviceInfo.deviceInfo.deviceId);
        iDevInfoTransListener.onComplete(true, null);
    }

    protected void breezeTrans(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo, String str, IDevInfoTrans.IDevInfoTransListener iDevInfoTransListener) {
        insertConvertData(palDiscoveryDeviceInfo.deviceInfo, new PalDeviceInfo(str, palDiscoveryDeviceInfo.deviceInfo.deviceId));
        iDevInfoTransListener.onComplete(true, null);
    }

    public void insertConvertData(PalDeviceInfo palDeviceInfo, PalDeviceInfo palDeviceInfo2) {
        if (palDeviceInfo == null || palDeviceInfo2 == null) {
            ALog.m480e(TAG, "insertConvertData privateDeviceInfo or aliIoTDeviceInfo null");
            return;
        }
        ALog.m479d(TAG, "insertConvertData privateDeviceInfo:" + palDeviceInfo.toString() + " aliIoTDeviceInfo:" + palDeviceInfo2);
        this.mToPrivatePkDnList.put(palDeviceInfo2.getDevId(), palDeviceInfo);
        this.mToAliIoTPkDnList.put(palDeviceInfo.getDevId(), palDeviceInfo2);
    }

    public void updatePubDevInfo(String str, String str2, String str3) {
        ALog.m479d(TAG, "updatePubDevInfo oldId:" + str + " productKey:" + str2 + " deviceName:" + str3);
        PalDeviceInfo palDeviceInfo = new PalDeviceInfo(str2, str3);
        PalDeviceInfo palDeviceInfo2 = this.mToPrivatePkDnList.get(str);
        if (palDeviceInfo2 != null) {
            this.mToPrivatePkDnList.remove(str);
            this.mToPrivatePkDnList.put(palDeviceInfo.getDevId(), palDeviceInfo2);
            this.mToAliIoTPkDnList.put(palDeviceInfo2.getDevId(), palDeviceInfo);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans
    public PalDeviceInfo toAliIoTDeviceInfo(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(TAG, "toAliIoTDeviceInfo deviceInfo null");
            return palDeviceInfo;
        }
        PalDeviceInfo palDeviceInfo2 = this.mToAliIoTPkDnList.get(palDeviceInfo.getDevId());
        if (palDeviceInfo2 == null) {
            palDeviceInfo2 = palDeviceInfo;
        }
        ALog.m479d(TAG, "toAliIoTDeviceInfo deviceInfo :" + palDeviceInfo.toString() + " retDeviceInfo:" + palDeviceInfo2.toString());
        return palDeviceInfo2;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans
    public PalDeviceInfo toPrivateDeviceInfo(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(TAG, "toPrivateDeviceInfo deviceInfo null");
            return palDeviceInfo;
        }
        PalDeviceInfo palDeviceInfo2 = this.mToPrivatePkDnList.get(palDeviceInfo.getDevId());
        if (palDeviceInfo2 == null) {
            palDeviceInfo2 = palDeviceInfo;
        }
        ALog.m479d(TAG, "toPrivateDeviceInfo deviceInfo :" + palDeviceInfo.toString() + " retDeviceInfo:" + palDeviceInfo2.toString());
        return palDeviceInfo2;
    }
}

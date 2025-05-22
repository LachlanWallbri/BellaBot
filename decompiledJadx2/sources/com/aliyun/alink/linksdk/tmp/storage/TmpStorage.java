package com.aliyun.alink.linksdk.tmp.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linksdk.tmp.data.auth.AccessInfo;
import com.aliyun.alink.linksdk.tmp.data.auth.ServerEncryptInfo;
import com.aliyun.alink.linksdk.tmp.device.panel.data.ProductInfoPayload;
import com.aliyun.alink.linksdk.tmp.utils.SecurityGuardProxy;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpStorage {
    public static final String FLAG_CLOUD = "cloud";
    public static final String FLAG_LOCAL = "local";
    public static final String FLAG_PROVISIONER = "provisioner";
    protected static final String TAG = "[Tmp]TmpStorage";
    protected static final String TMP_ACCESS_TOKEN_IDLIST = "tmp_id_token_list";
    protected static final String TMP_ACCESS_TOKEN_ID_SPLITE = "===";
    protected static final String TMP_STORAGE_ASKEY_PRE = "asKey_pre_";
    protected static final String TMP_STORAGE_ASTOKEN_PRE = "asToken_pre_";
    protected static final String TMP_STORAGE_BKLIST_PRE = "bklist_pre_";
    protected static final String TMP_STORAGE_DATAFORMAT_PRE = "dataformat_pre_";
    protected static final String TMP_STORAGE_DEVICE_PRE = "devName_pre_";
    protected static final String TMP_STORAGE_DEV_DETAIL_PRE = "dev_detail_pre";
    protected static final String TMP_STORAGE_DNTOMAC_PRE = "translate_dn_to_mac_pre_";
    protected static final String TMP_STORAGE_FILENAME = "tmp_pref";
    protected static final String TMP_STORAGE_PREFIX_PRE = "prefix_pre_";
    protected static final String TMP_STORAGE_PRODKEY_PRE = "prodKey_pre_";
    protected static final String TMP_STORAGE_PRODUCTINFO_PRE = "productinfo_pre_";
    protected static final String TMP_STORAGE_PROVISION_ASTOKEN_PRE = "provision_asToken_pre_";
    protected static final String TMP_STORAGE_PROVISION_PREFIX_PRE = "prefix_pre_";
    protected static final String TMP_STORAGE_PROVISION_SECRET_PRE = "secret_pre_";
    public static final String TMP_STORAGE_SCRIPT_DIGESTMETHOD_PRE = "script_digestmethod_pre";
    public static final String TMP_STORAGE_SCRIPT_DIGEST_PRE = "script_digest_pre";
    protected static final String TMP_STORAGE_SCRIPT_PRE = "script_pre";
    protected static final String TMP_STORAGE_SECRET_PRE = "secret_pre_";
    protected static final String TMP_STORAGE_TOALIIOTDEVINFO_DEVICENAME_PRE = "translate_to_ali_dev_info_devicenname_pre_";
    protected static final String TMP_STORAGE_TOALIIOTDEVINFO_PRODUCTKEY_PRE = "translate_to_ali_dev_info_productkey_pre_";
    protected static final String TMP_STORAGE_TSL_PRE = "tsl_pre_";
    protected static final String TMP_STORAGE__PROVISION_ASKEY_PRE = "provision_asKey_pre_";
    protected Map<String, String> mAccessTokenList = new ConcurrentHashMap();
    protected Context mContext;
    protected SharedPreferences.Editor mEditor;
    protected SecurityGuardProxy mSecurityProxy;
    protected SharedPreferences mSharedPerfences;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {
        protected static TmpStorage mInstance = new TmpStorage();
    }

    public static TmpStorage getInstance() {
        return InstanceHolder.mInstance;
    }

    public void init(Context context) {
        ALog.m479d(TAG, "init");
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(TMP_STORAGE_FILENAME, 0);
        this.mSharedPerfences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
        this.mSecurityProxy = new SecurityGuardProxy(this.mContext);
        readAccessTokenIds();
    }

    public void clearAccessTokenCache() {
        ALog.m479d(TAG, "clearAccessTokenCache");
        Map<String, String> map = this.mAccessTokenList;
        if (map == null || map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, String>> it = this.mAccessTokenList.entrySet().iterator();
        while (it.hasNext()) {
            saveAccessInfo(it.next().getKey(), null, null, false, "cloud");
        }
        this.mAccessTokenList.clear();
        writeAccessTokenIds();
    }

    public void readAccessTokenIds() {
        String[] split;
        String string = this.mSharedPerfences.getString(TMP_ACCESS_TOKEN_IDLIST, "");
        ALog.m479d(TAG, "readAccessTokenIds accessTokenIds:" + string);
        if (TextUtils.isEmpty(string) || (split = string.split(TMP_ACCESS_TOKEN_ID_SPLITE)) == null) {
            return;
        }
        for (int i = 0; i < split.length; i++) {
            this.mAccessTokenList.put(split[i], split[i]);
        }
    }

    protected void addAccessTokenId(String str) {
        ALog.m479d(TAG, "addAccessTokenId id:" + str);
        this.mAccessTokenList.put(str, str);
        writeAccessTokenIds();
    }

    protected void removeAccessTokenId(String str) {
        ALog.m479d(TAG, "removeAccessTokenId id:" + str);
        this.mAccessTokenList.remove(str);
        writeAccessTokenIds();
    }

    protected void writeAccessTokenIds() {
        Map<String, String> map = this.mAccessTokenList;
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.mAccessTokenList.entrySet()) {
                ALog.m479d(TAG, "writeAccessTokenIds id:" + entry.getValue());
                sb.append(TMP_ACCESS_TOKEN_ID_SPLITE);
                sb.append(entry.getKey());
            }
            this.mEditor.putString(TMP_ACCESS_TOKEN_IDLIST, sb.toString());
            this.mEditor.apply();
            return;
        }
        this.mEditor.remove(TMP_ACCESS_TOKEN_IDLIST);
    }

    public DeviceInfo getDeviceInfo(String str) {
        ALog.m479d(TAG, "getDeviceInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String string = this.mSharedPerfences.getString(TMP_STORAGE_PRODKEY_PRE + str, "");
        String string2 = this.mSharedPerfences.getString(TMP_STORAGE_DEVICE_PRE + str, "");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            ALog.m480e(TAG, "getDeviceInfo error empty");
            return null;
        }
        return new DeviceInfo(string, string2);
    }

    public void saveDeviceInfo(String str, String str2, String str3) {
        ALog.m479d(TAG, "saveDeviceInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveDeviceInfo id error empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mEditor.remove(TMP_STORAGE_PRODKEY_PRE + str);
        } else {
            this.mEditor.putString(TMP_STORAGE_PRODKEY_PRE + str, str2);
        }
        if (TextUtils.isEmpty(str3)) {
            this.mEditor.remove(TMP_STORAGE_DEVICE_PRE + str);
        } else {
            this.mEditor.putString(TMP_STORAGE_DEVICE_PRE + str, str3);
        }
        this.mEditor.apply();
    }

    public void saveProductInfo(String str, ProductInfoPayload.ProductInfo productInfo) {
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveProductInfo id error empty");
            return;
        }
        String str2 = null;
        try {
            str2 = JSON.toJSONString(productInfo);
        } catch (Exception e) {
            ALog.m480e(TAG, "saveProductInfo toJSONString error:" + e.toString());
        }
        ALog.m479d(TAG, "saveProductInfo id:" + str + " productInfo:" + str2);
        if (TextUtils.isEmpty(str2)) {
            this.mEditor.remove(TMP_STORAGE_PRODUCTINFO_PRE + str);
        } else {
            this.mEditor.putString(TMP_STORAGE_PRODUCTINFO_PRE + str, str2);
        }
        this.mEditor.apply();
    }

    public ProductInfoPayload.ProductInfo getProductInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String string = this.mSharedPerfences.getString(TMP_STORAGE_PRODUCTINFO_PRE + str, "");
        ALog.m479d(TAG, "getProductInfo id:" + str + " ProductInfo:" + string);
        try {
            return (ProductInfoPayload.ProductInfo) JSON.parseObject(string, ProductInfoPayload.ProductInfo.class);
        } catch (Exception e) {
            ALog.m484w(TAG, "getProductInfo error :" + e.toString());
            return null;
        }
    }

    public String getTsl(String str) {
        ALog.m479d(TAG, "getTsl id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "getTsl id error empty");
            return "";
        }
        return this.mSharedPerfences.getString(TMP_STORAGE_TSL_PRE + str, "");
    }

    public void saveTsl(String str, String str2) {
        ALog.m479d(TAG, "saveTsl id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveTsl id error empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mEditor.remove(TMP_STORAGE_TSL_PRE + str);
        } else {
            this.mEditor.putString(TMP_STORAGE_TSL_PRE + str, str2);
        }
        this.mEditor.apply();
    }

    public void saveBlackList(String str, String str2) {
        ALog.m479d(TAG, "saveBlackList id:" + str + " blackList:" + str2);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveBlackList error id null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mEditor.remove(TMP_STORAGE_BKLIST_PRE + str);
        } else {
            this.mEditor.putString(TMP_STORAGE_BKLIST_PRE + str, str2);
        }
        this.mEditor.apply();
    }

    public String getBlackList(String str) {
        ALog.m479d(TAG, "getBlackList id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "getBlackList id error empty");
            return "";
        }
        return this.mSharedPerfences.getString(TMP_STORAGE_BKLIST_PRE + str, "");
    }

    public AccessInfo getAccessInfo(String str, String str2) {
        return getAccessInfoInner(getRealId(str, str2));
    }

    public AccessInfo getAccessInfo(String str) {
        return getAccessInfo(str, "cloud");
    }

    protected AccessInfo getAccessInfoInner(String str) {
        ALog.m479d(TAG, "getAccessInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "getAccessInfo id error empty");
            return null;
        }
        String stringDDpEx = this.mSecurityProxy.getStringDDpEx(TMP_STORAGE_ASKEY_PRE + str);
        String stringDDpEx2 = this.mSecurityProxy.getStringDDpEx(TMP_STORAGE_ASTOKEN_PRE + str);
        if (TextUtils.isEmpty(stringDDpEx) || TextUtils.isEmpty(stringDDpEx2)) {
            ALog.m480e(TAG, "getAccessInfo accessKey or asToken empty");
            return null;
        }
        return new AccessInfo(stringDDpEx, stringDDpEx2);
    }

    public void saveAccessInfo(String str, String str2, String str3) {
        saveAccessInfo(str, str2, str3, true, "cloud");
    }

    public void saveAccessInfo(String str, String str2, String str3, boolean z, String str4) {
        saveAccessInfoInner(getRealId(str, str4), str2, str3, z);
    }

    protected void saveAccessInfoInner(String str, String str2, String str3, boolean z) {
        ALog.m479d(TAG, "saveAccessInfoInner id:" + str + " asKey:" + str2 + " updateIds:" + z);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveAccessInfoInner id error empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            if (z) {
                removeAccessTokenId(str);
            }
            this.mSecurityProxy.removeStringDDpEx(TMP_STORAGE_ASKEY_PRE + str);
        } else {
            if (z) {
                addAccessTokenId(str);
            }
            this.mSecurityProxy.addStringDDpEx(TMP_STORAGE_ASKEY_PRE + str, str2);
        }
        if (TextUtils.isEmpty(str3)) {
            this.mSecurityProxy.removeStringDDpEx(TMP_STORAGE_ASTOKEN_PRE + str);
            return;
        }
        this.mSecurityProxy.addStringDDpEx(TMP_STORAGE_ASTOKEN_PRE + str, str3);
    }

    protected String getRealId(String str, String str2) {
        if ("cloud".equals(str2)) {
            return str;
        }
        return str + str2;
    }

    public AccessInfo getProvisionAccessInfo(String str) {
        ALog.m479d(TAG, "getProvisionAccessInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "getProvisionAccessInfo id error empty");
            return null;
        }
        String stringDDpEx = this.mSecurityProxy.getStringDDpEx(TMP_STORAGE__PROVISION_ASKEY_PRE + str);
        String stringDDpEx2 = this.mSecurityProxy.getStringDDpEx(TMP_STORAGE_PROVISION_ASTOKEN_PRE + str);
        if (TextUtils.isEmpty(stringDDpEx) || TextUtils.isEmpty(stringDDpEx2)) {
            ALog.m480e(TAG, "getAccessInfo accessKey or asToken empty");
            return null;
        }
        return new AccessInfo(stringDDpEx, stringDDpEx2);
    }

    public void saveProvisionAccessInfo(String str, String str2, String str3) {
        ALog.m479d(TAG, "saveProvisionAccessInfo id:" + str + " asKey:" + str2);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveProvisionAccessInfo id error empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mSecurityProxy.removeStringDDpEx(TMP_STORAGE__PROVISION_ASKEY_PRE + str);
        } else {
            this.mSecurityProxy.addStringDDpEx(TMP_STORAGE__PROVISION_ASKEY_PRE + str, str2);
        }
        if (TextUtils.isEmpty(str3)) {
            this.mSecurityProxy.removeStringDDpEx(TMP_STORAGE_PROVISION_ASTOKEN_PRE + str);
            return;
        }
        this.mSecurityProxy.addStringDDpEx(TMP_STORAGE_PROVISION_ASTOKEN_PRE + str, str3);
    }

    public ServerEncryptInfo getServerEnptInfo(String str, String str2) {
        return getServerEnptInfo(getRealId(str, str2));
    }

    public ServerEncryptInfo getServerEnptInfo(String str) {
        ALog.m479d(TAG, "getServerEnptInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "getServerEnptInfo error id empty");
            return null;
        }
        String stringDDpEx = this.mSecurityProxy.getStringDDpEx("prefix_pre_" + str);
        String stringDDpEx2 = this.mSecurityProxy.getStringDDpEx("secret_pre_" + str);
        if (TextUtils.isEmpty(stringDDpEx) || TextUtils.isEmpty(stringDDpEx2)) {
            ALog.m480e(TAG, "getServerEnptInfo prefix or secret null");
            return null;
        }
        return new ServerEncryptInfo(stringDDpEx, stringDDpEx2);
    }

    public void saveServerEnptInfo(String str, String str2, String str3, String str4) {
        saveServerEnptInfo(getRealId(str, str4), str2, str3);
    }

    public void saveServerEnptInfo(String str, String str2, String str3) {
        ALog.m479d(TAG, "saveServerEnptInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "saveServerEnptInfo error id null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mSecurityProxy.removeStringDDpEx("prefix_pre_" + str);
        } else {
            this.mSecurityProxy.addStringDDpEx("prefix_pre_" + str, str2);
        }
        if (TextUtils.isEmpty(str3)) {
            this.mSecurityProxy.removeStringDDpEx("secret_pre_" + str);
            return;
        }
        this.mSecurityProxy.addStringDDpEx("secret_pre_" + str, str3);
    }

    public String getDevDetailInfo(String str) {
        ALog.m479d(TAG, "getDevDetailInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mSharedPerfences.getString(TMP_STORAGE_DEV_DETAIL_PRE + str, null);
    }

    public boolean saveDevDetailInfo(String str, String str2) {
        ALog.m479d(TAG, "saveDevDetailInfo id:" + str + " data:" + str2);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.mEditor.putString(TMP_STORAGE_DEV_DETAIL_PRE + str, str2);
        this.mEditor.apply();
        return true;
    }

    public boolean removeDevDetailInfo(String str) {
        ALog.m479d(TAG, "removeDevDetailInfo id:" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.mEditor.remove(TMP_STORAGE_DEV_DETAIL_PRE + str);
        this.mEditor.apply();
        return true;
    }

    public boolean saveDigest(String str, String str2) {
        return saveString(str, TMP_STORAGE_SCRIPT_DIGEST_PRE, str2);
    }

    public String getDigest(String str) {
        return getString(str, TMP_STORAGE_SCRIPT_DIGEST_PRE);
    }

    public boolean saveDigestMethod(String str, String str2) {
        return saveString(str, TMP_STORAGE_SCRIPT_DIGESTMETHOD_PRE, str2);
    }

    public String getDigestMethod(String str) {
        return getString(str, TMP_STORAGE_SCRIPT_DIGESTMETHOD_PRE);
    }

    public boolean saveScript(String str, String str2) {
        return saveString(str, TMP_STORAGE_SCRIPT_PRE, str2);
    }

    public String getScript(String str) {
        return getString(str, TMP_STORAGE_SCRIPT_PRE);
    }

    public boolean saveDnToMac(String str, String str2) {
        ALog.m479d(TAG, "saveDnToMac dn:" + str + " mac:" + str2);
        return saveString(str, TMP_STORAGE_DNTOMAC_PRE, str2);
    }

    public String getDnMac(String str) {
        String string = getString(str, TMP_STORAGE_DNTOMAC_PRE);
        ALog.m479d(TAG, "getDnMac dn:" + str + " mac:" + string);
        return string;
    }

    public String getString(String str, String str2) {
        ALog.m479d(TAG, "getString id:" + str + " prefix:" + str2);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mSharedPerfences.getString(str2 + str, null);
    }

    public boolean saveString(String str, String str2, String str3) {
        ALog.m479d(TAG, "saveString id:" + str + " prefix:" + str2 + " value isempty:" + TextUtils.isEmpty(str3));
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.mEditor.putString(str2 + str, str3);
        this.mEditor.apply();
        return true;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DeviceInfo {
        public String mDeviceName;
        public String mProductKey;

        public DeviceInfo(String str, String str2) {
            this.mProductKey = str;
            this.mDeviceName = str2;
        }

        public String getId() {
            return TextHelper.combineStr(this.mProductKey, this.mDeviceName);
        }
    }
}

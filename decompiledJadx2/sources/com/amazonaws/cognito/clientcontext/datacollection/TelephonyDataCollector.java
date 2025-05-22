package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TelephonyDataCollector extends DataCollector {
    @Override // com.amazonaws.cognito.clientcontext.datacollection.DataCollector
    public Map<String, String> collect(Context context) {
        HashMap hashMap = new HashMap();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            hashMap.put(DataRecordKey.HAS_ICC_CARD, String.valueOf(telephonyManager.hasIccCard()));
            hashMap.put(DataRecordKey.IS_NETWORK_ROAMING, String.valueOf(telephonyManager.isNetworkRoaming()));
            hashMap.put(DataRecordKey.NETWORK_OPERATOR, telephonyManager.getNetworkOperatorName());
            hashMap.put(DataRecordKey.NETWORK_TYPE, String.valueOf(telephonyManager.getNetworkType()));
            hashMap.put(DataRecordKey.PHONE_TYPE, String.valueOf(telephonyManager.getPhoneType()));
            if (telephonyManager.getSimState() == 5) {
                hashMap.put(DataRecordKey.SIM_COUNTRY_ISO, String.valueOf(telephonyManager.getSimCountryIso()));
                hashMap.put(DataRecordKey.SIM_OPERATOR, String.valueOf(telephonyManager.getSimOperatorName()));
            }
        }
        return hashMap;
    }
}

package com.amazonaws.mobileconnectors.cognitoidentityprovider;

import com.amazonaws.services.cognitoidentityprovider.model.MFAOptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CognitoUserSettings {
    private Map<String, String> userSettings;

    public CognitoUserSettings() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CognitoUserSettings(List<MFAOptionType> list) {
        this.userSettings = new HashMap();
        if (list != null) {
            for (MFAOptionType mFAOptionType : list) {
                this.userSettings.put(mFAOptionType.getAttributeName().toString(), mFAOptionType.getDeliveryMedium().toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<MFAOptionType> getSettingsList() {
        ArrayList arrayList = new ArrayList();
        Map<String, String> map = this.userSettings;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                MFAOptionType mFAOptionType = new MFAOptionType();
                mFAOptionType.setAttributeName(entry.getKey());
                mFAOptionType.setDeliveryMedium(entry.getValue());
                arrayList.add(mFAOptionType);
            }
        }
        return arrayList;
    }

    public Map<String, String> getSettings() {
        return this.userSettings;
    }

    public void setSettings(String str, String str2) {
        this.userSettings.put(str, str2);
    }
}

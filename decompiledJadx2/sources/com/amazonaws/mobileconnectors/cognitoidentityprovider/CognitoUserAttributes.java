package com.amazonaws.mobileconnectors.cognitoidentityprovider;

import com.amazonaws.services.cognitoidentityprovider.model.AttributeType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CognitoUserAttributes {
    private Map<String, String> userAttributes;

    public CognitoUserAttributes() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CognitoUserAttributes(List<AttributeType> list) {
        this.userAttributes = new HashMap();
        if (list != null) {
            for (AttributeType attributeType : list) {
                this.userAttributes.put(attributeType.getName(), attributeType.getValue());
            }
        }
    }

    public void addAttribute(String str, String str2) {
        this.userAttributes.put(str, str2);
    }

    public Map<String, String> getAttributes() {
        return this.userAttributes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<AttributeType> getAttributesList() {
        ArrayList arrayList = new ArrayList();
        Map<String, String> map = this.userAttributes;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                AttributeType attributeType = new AttributeType();
                attributeType.setName(entry.getKey());
                attributeType.setValue(entry.getValue());
                arrayList.add(attributeType);
            }
        }
        return arrayList;
    }
}

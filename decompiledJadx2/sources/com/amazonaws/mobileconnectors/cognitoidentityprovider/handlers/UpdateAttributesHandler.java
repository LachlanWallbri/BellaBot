package com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import java.util.List;

/* loaded from: classes.dex */
public interface UpdateAttributesHandler {
    void onFailure(Exception exc);

    void onSuccess(List<CognitoUserCodeDeliveryDetails> list);
}

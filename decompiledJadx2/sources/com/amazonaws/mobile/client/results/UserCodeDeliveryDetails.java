package com.amazonaws.mobile.client.results;

/* loaded from: classes.dex */
public class UserCodeDeliveryDetails {
    private final String attributeName;
    private final String deliveryMedium;
    private final String destination;

    public UserCodeDeliveryDetails(String str, String str2, String str3) {
        this.destination = str;
        this.deliveryMedium = str2;
        this.attributeName = str3;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDeliveryMedium() {
        return this.deliveryMedium;
    }

    public String getAttributeName() {
        return this.attributeName;
    }
}

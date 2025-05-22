package com.google.api;

import com.google.api.Billing;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BillingOrBuilder extends MessageOrBuilder {
    Billing.BillingDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<Billing.BillingDestination> getConsumerDestinationsList();

    Billing.BillingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i);

    List<? extends Billing.BillingDestinationOrBuilder> getConsumerDestinationsOrBuilderList();
}

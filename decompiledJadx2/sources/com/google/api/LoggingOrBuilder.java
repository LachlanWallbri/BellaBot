package com.google.api;

import com.google.api.Logging;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface LoggingOrBuilder extends MessageOrBuilder {
    Logging.LoggingDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<Logging.LoggingDestination> getConsumerDestinationsList();

    Logging.LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i);

    List<? extends Logging.LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList();

    Logging.LoggingDestination getProducerDestinations(int i);

    int getProducerDestinationsCount();

    List<Logging.LoggingDestination> getProducerDestinationsList();

    Logging.LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i);

    List<? extends Logging.LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList();
}

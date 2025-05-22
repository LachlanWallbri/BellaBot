package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ContextRuleOrBuilder extends MessageOrBuilder {
    String getAllowedRequestExtensions(int i);

    ByteString getAllowedRequestExtensionsBytes(int i);

    int getAllowedRequestExtensionsCount();

    List<String> getAllowedRequestExtensionsList();

    String getAllowedResponseExtensions(int i);

    ByteString getAllowedResponseExtensionsBytes(int i);

    int getAllowedResponseExtensionsCount();

    List<String> getAllowedResponseExtensionsList();

    String getProvided(int i);

    ByteString getProvidedBytes(int i);

    int getProvidedCount();

    List<String> getProvidedList();

    String getRequested(int i);

    ByteString getRequestedBytes(int i);

    int getRequestedCount();

    List<String> getRequestedList();

    String getSelector();

    ByteString getSelectorBytes();
}

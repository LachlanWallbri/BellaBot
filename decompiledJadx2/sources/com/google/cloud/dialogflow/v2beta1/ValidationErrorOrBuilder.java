package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.ValidationError;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ValidationErrorOrBuilder extends MessageOrBuilder {
    String getEntries(int i);

    ByteString getEntriesBytes(int i);

    int getEntriesCount();

    List<String> getEntriesList();

    String getErrorMessage();

    ByteString getErrorMessageBytes();

    ValidationError.Severity getSeverity();

    int getSeverityValue();
}

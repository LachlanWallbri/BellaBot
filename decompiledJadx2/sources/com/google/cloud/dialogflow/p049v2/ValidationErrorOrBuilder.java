package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.ValidationError;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
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

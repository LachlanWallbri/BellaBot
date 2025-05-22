package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ValidationResultOrBuilder extends MessageOrBuilder {
    ValidationError getValidationErrors(int i);

    int getValidationErrorsCount();

    List<ValidationError> getValidationErrorsList();

    ValidationErrorOrBuilder getValidationErrorsOrBuilder(int i);

    List<? extends ValidationErrorOrBuilder> getValidationErrorsOrBuilderList();
}

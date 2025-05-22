package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.cognitoidentityprovider.model.SoftwareTokenMFANotFoundException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class SoftwareTokenMFANotFoundExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public SoftwareTokenMFANotFoundExceptionUnmarshaller() {
        super(SoftwareTokenMFANotFoundException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("SoftwareTokenMFANotFoundException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        SoftwareTokenMFANotFoundException softwareTokenMFANotFoundException = (SoftwareTokenMFANotFoundException) super.unmarshall(jsonErrorResponse);
        softwareTokenMFANotFoundException.setErrorCode("SoftwareTokenMFANotFoundException");
        return softwareTokenMFANotFoundException;
    }
}

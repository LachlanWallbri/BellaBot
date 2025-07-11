package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.cognitoidentityprovider.model.UsernameExistsException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class UsernameExistsExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public UsernameExistsExceptionUnmarshaller() {
        super(UsernameExistsException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("UsernameExistsException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        UsernameExistsException usernameExistsException = (UsernameExistsException) super.unmarshall(jsonErrorResponse);
        usernameExistsException.setErrorCode("UsernameExistsException");
        return usernameExistsException;
    }
}

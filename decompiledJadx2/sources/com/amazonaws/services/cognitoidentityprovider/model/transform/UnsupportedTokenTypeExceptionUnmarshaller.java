package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.cognitoidentityprovider.model.UnsupportedTokenTypeException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class UnsupportedTokenTypeExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public UnsupportedTokenTypeExceptionUnmarshaller() {
        super(UnsupportedTokenTypeException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("UnsupportedTokenTypeException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        UnsupportedTokenTypeException unsupportedTokenTypeException = (UnsupportedTokenTypeException) super.unmarshall(jsonErrorResponse);
        unsupportedTokenTypeException.setErrorCode("UnsupportedTokenTypeException");
        return unsupportedTokenTypeException;
    }
}

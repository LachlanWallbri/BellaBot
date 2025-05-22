package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.securitytoken.model.ExpiredTokenException;
import com.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class ExpiredTokenExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public ExpiredTokenExceptionUnmarshaller() {
        super(ExpiredTokenException.class);
    }

    @Override // com.amazonaws.transform.StandardErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("ExpiredTokenException")) {
            return null;
        }
        return (ExpiredTokenException) super.unmarshall(node);
    }
}

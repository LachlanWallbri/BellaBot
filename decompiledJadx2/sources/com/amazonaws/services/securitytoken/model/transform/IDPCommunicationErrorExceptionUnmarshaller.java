package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.securitytoken.model.IDPCommunicationErrorException;
import com.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class IDPCommunicationErrorExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public IDPCommunicationErrorExceptionUnmarshaller() {
        super(IDPCommunicationErrorException.class);
    }

    @Override // com.amazonaws.transform.StandardErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("IDPCommunicationError")) {
            return null;
        }
        return (IDPCommunicationErrorException) super.unmarshall(node);
    }
}

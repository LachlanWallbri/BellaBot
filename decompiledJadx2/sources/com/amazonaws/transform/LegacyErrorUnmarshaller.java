package com.amazonaws.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.util.XpathUtils;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class LegacyErrorUnmarshaller implements Unmarshaller<AmazonServiceException, Node> {
    private final Class<? extends AmazonServiceException> exceptionClass;

    public LegacyErrorUnmarshaller() {
        this(AmazonServiceException.class);
    }

    protected LegacyErrorUnmarshaller(Class<? extends AmazonServiceException> cls) {
        this.exceptionClass = cls;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        String asString = XpathUtils.asString("Response/Errors/Error/Message", node);
        String asString2 = XpathUtils.asString("Response/RequestID", node);
        String asString3 = XpathUtils.asString("Response/Errors/Error/Type", node);
        AmazonServiceException newInstance = this.exceptionClass.getConstructor(String.class).newInstance(asString);
        newInstance.setErrorCode(parseErrorCode);
        newInstance.setRequestId(asString2);
        if (asString3 == null) {
            newInstance.setErrorType(AmazonServiceException.ErrorType.Unknown);
        } else if ("server".equalsIgnoreCase(asString3)) {
            newInstance.setErrorType(AmazonServiceException.ErrorType.Service);
        } else if ("client".equalsIgnoreCase(asString3)) {
            newInstance.setErrorType(AmazonServiceException.ErrorType.Client);
        }
        return newInstance;
    }

    public String parseErrorCode(Node node) throws Exception {
        return XpathUtils.asString("Response/Errors/Error/Code", node);
    }

    public String getErrorPropertyPath(String str) {
        return "Response/Errors/Error/" + str;
    }
}

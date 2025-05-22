package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.DecodeAuthorizationMessageResult;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class DecodeAuthorizationMessageResultStaxUnmarshaller implements Unmarshaller<DecodeAuthorizationMessageResult, StaxUnmarshallerContext> {
    private static DecodeAuthorizationMessageResultStaxUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DecodeAuthorizationMessageResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        DecodeAuthorizationMessageResult decodeAuthorizationMessageResult = new DecodeAuthorizationMessageResult();
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i += 2;
        }
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                break;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression("DecodedMessage", i)) {
                    decodeAuthorizationMessageResult.setDecodedMessage(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return decodeAuthorizationMessageResult;
    }

    public static DecodeAuthorizationMessageResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DecodeAuthorizationMessageResultStaxUnmarshaller();
        }
        return instance;
    }
}

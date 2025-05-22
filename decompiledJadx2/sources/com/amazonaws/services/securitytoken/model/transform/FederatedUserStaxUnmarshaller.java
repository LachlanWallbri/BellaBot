package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.FederatedUser;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class FederatedUserStaxUnmarshaller implements Unmarshaller<FederatedUser, StaxUnmarshallerContext> {
    private static FederatedUserStaxUnmarshaller instance;

    FederatedUserStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FederatedUser unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        FederatedUser federatedUser = new FederatedUser();
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
                if (staxUnmarshallerContext.testExpression("FederatedUserId", i)) {
                    federatedUser.setFederatedUserId(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("Arn", i)) {
                    federatedUser.setArn(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return federatedUser;
    }

    public static FederatedUserStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new FederatedUserStaxUnmarshaller();
        }
        return instance;
    }
}

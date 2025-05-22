package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.GetFederationTokenResult;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class GetFederationTokenResultStaxUnmarshaller implements Unmarshaller<GetFederationTokenResult, StaxUnmarshallerContext> {
    private static GetFederationTokenResultStaxUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetFederationTokenResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        GetFederationTokenResult getFederationTokenResult = new GetFederationTokenResult();
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
                if (staxUnmarshallerContext.testExpression("Credentials", i)) {
                    getFederationTokenResult.setCredentials(CredentialsStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("FederatedUser", i)) {
                    getFederationTokenResult.setFederatedUser(FederatedUserStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("PackedPolicySize", i)) {
                    getFederationTokenResult.setPackedPolicySize(SimpleTypeStaxUnmarshallers.IntegerStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return getFederationTokenResult;
    }

    public static GetFederationTokenResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetFederationTokenResultStaxUnmarshaller();
        }
        return instance;
    }
}

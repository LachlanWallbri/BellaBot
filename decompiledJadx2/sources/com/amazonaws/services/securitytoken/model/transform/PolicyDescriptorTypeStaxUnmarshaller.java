package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class PolicyDescriptorTypeStaxUnmarshaller implements Unmarshaller<PolicyDescriptorType, StaxUnmarshallerContext> {
    private static PolicyDescriptorTypeStaxUnmarshaller instance;

    PolicyDescriptorTypeStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PolicyDescriptorType unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        PolicyDescriptorType policyDescriptorType = new PolicyDescriptorType();
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
                if (staxUnmarshallerContext.testExpression("arn", i)) {
                    policyDescriptorType.setArn(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return policyDescriptorType;
    }

    public static PolicyDescriptorTypeStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyDescriptorTypeStaxUnmarshaller();
        }
        return instance;
    }
}

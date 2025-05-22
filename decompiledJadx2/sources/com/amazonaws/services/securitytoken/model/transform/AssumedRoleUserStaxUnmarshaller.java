package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.AssumedRoleUser;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class AssumedRoleUserStaxUnmarshaller implements Unmarshaller<AssumedRoleUser, StaxUnmarshallerContext> {
    private static AssumedRoleUserStaxUnmarshaller instance;

    AssumedRoleUserStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AssumedRoleUser unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        AssumedRoleUser assumedRoleUser = new AssumedRoleUser();
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
                if (staxUnmarshallerContext.testExpression("AssumedRoleId", i)) {
                    assumedRoleUser.setAssumedRoleId(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("Arn", i)) {
                    assumedRoleUser.setArn(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return assumedRoleUser;
    }

    public static AssumedRoleUserStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssumedRoleUserStaxUnmarshaller();
        }
        return instance;
    }
}

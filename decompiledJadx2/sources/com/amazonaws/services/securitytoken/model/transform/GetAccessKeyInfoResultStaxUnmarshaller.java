package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.services.securitytoken.model.GetAccessKeyInfoResult;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class GetAccessKeyInfoResultStaxUnmarshaller implements Unmarshaller<GetAccessKeyInfoResult, StaxUnmarshallerContext> {
    private static GetAccessKeyInfoResultStaxUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetAccessKeyInfoResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        GetAccessKeyInfoResult getAccessKeyInfoResult = new GetAccessKeyInfoResult();
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
                if (staxUnmarshallerContext.testExpression("Account", i)) {
                    getAccessKeyInfoResult.setAccount(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return getAccessKeyInfoResult;
    }

    public static GetAccessKeyInfoResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetAccessKeyInfoResultStaxUnmarshaller();
        }
        return instance;
    }
}

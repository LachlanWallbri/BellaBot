package com.amazonaws.services.p048s3.model.transform;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.p048s3.model.CloudFunctionConfiguration;
import com.amazonaws.services.p048s3.model.Filter;
import com.amazonaws.services.p048s3.model.LambdaConfiguration;
import com.amazonaws.services.p048s3.model.NotificationConfiguration;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class LambdaConfigurationStaxUnmarshaller implements Unmarshaller<Map.Entry<String, NotificationConfiguration>, StaxUnmarshallerContext> {
    private static LambdaConfigurationStaxUnmarshaller instance = new LambdaConfigurationStaxUnmarshaller();

    public static LambdaConfigurationStaxUnmarshaller getInstance() {
        return instance;
    }

    private LambdaConfigurationStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Map.Entry<String, NotificationConfiguration> unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i++;
        }
        ArrayList arrayList = new ArrayList();
        String str = null;
        String str2 = null;
        String str3 = null;
        Filter filter = null;
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                return null;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression(JsonDocumentFields.POLICY_ID, i)) {
                    str = SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                } else if (staxUnmarshallerContext.testExpression(MoveError.LEVEL_EVENT, i)) {
                    arrayList.add(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("Filter", i)) {
                    filter = FilterStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                } else if (staxUnmarshallerContext.testExpression("CloudFunction", i)) {
                    str2 = SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                } else if (staxUnmarshallerContext.testExpression("InvocationRole", i)) {
                    str3 = SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return createLambdaConfig(str, arrayList, str2, str3, filter);
            }
        }
    }

    private Map.Entry<String, NotificationConfiguration> createLambdaConfig(String str, List<String> list, String str2, String str3, Filter filter) {
        NotificationConfiguration cloudFunctionConfiguration;
        if (str3 == null) {
            cloudFunctionConfiguration = new LambdaConfiguration(str2, (String[]) list.toArray(new String[0]));
        } else {
            cloudFunctionConfiguration = new CloudFunctionConfiguration(str3, str2, (String[]) list.toArray(new String[0]));
        }
        return new AbstractMap.SimpleEntry(str, cloudFunctionConfiguration.withFilter(filter));
    }
}

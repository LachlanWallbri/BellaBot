package com.amazonaws.services.p048s3.model.transform;

import com.amazonaws.services.p048s3.model.S3KeyFilter;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class S3KeyFilterStaxUnmarshaller implements Unmarshaller<S3KeyFilter, StaxUnmarshallerContext> {
    private static S3KeyFilterStaxUnmarshaller instance = new S3KeyFilterStaxUnmarshaller();

    public static S3KeyFilterStaxUnmarshaller getInstance() {
        return instance;
    }

    private S3KeyFilterStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public S3KeyFilter unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i++;
        }
        S3KeyFilter s3KeyFilter = new S3KeyFilter();
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                return s3KeyFilter;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression("FilterRule", i)) {
                    s3KeyFilter.addFilterRule(FilterRuleStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return s3KeyFilter;
            }
        }
    }
}

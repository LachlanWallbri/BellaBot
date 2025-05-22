package com.amazonaws.services.p048s3.model.transform;

import com.amazonaws.services.p048s3.model.Filter;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class FilterStaxUnmarshaller implements Unmarshaller<Filter, StaxUnmarshallerContext> {
    private static FilterStaxUnmarshaller instance = new FilterStaxUnmarshaller();

    public static FilterStaxUnmarshaller getInstance() {
        return instance;
    }

    private FilterStaxUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Filter unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i++;
        }
        Filter filter = new Filter();
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                return filter;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression("S3Key", i)) {
                    filter.withS3KeyFilter(S3KeyFilterStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return filter;
            }
        }
    }
}

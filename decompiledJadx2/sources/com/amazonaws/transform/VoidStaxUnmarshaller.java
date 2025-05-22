package com.amazonaws.transform;

/* loaded from: classes.dex */
public class VoidStaxUnmarshaller<T> implements Unmarshaller<T, StaxUnmarshallerContext> {
    @Override // com.amazonaws.transform.Unmarshaller
    public T unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        do {
        } while (staxUnmarshallerContext.nextEvent() != 1);
        return null;
    }
}

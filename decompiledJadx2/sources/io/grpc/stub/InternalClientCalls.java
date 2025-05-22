package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.stub.ClientCalls;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalClientCalls {
    public static CallOptions.Key<ClientCalls.StubType> getStubTypeOption() {
        return ClientCalls.STUB_TYPE_OPTION;
    }

    public static StubType getStubType(CallOptions callOptions) {
        return StubType.m3923of((ClientCalls.StubType) callOptions.getOption(ClientCalls.STUB_TYPE_OPTION));
    }

    public static CallOptions setStubType(CallOptions callOptions, StubType stubType) {
        return callOptions.withOption(ClientCalls.STUB_TYPE_OPTION, stubType.internalType);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum StubType {
        BLOCKING(ClientCalls.StubType.BLOCKING),
        ASYNC(ClientCalls.StubType.ASYNC),
        FUTURE(ClientCalls.StubType.FUTURE);

        private final ClientCalls.StubType internalType;

        StubType(ClientCalls.StubType stubType) {
            this.internalType = stubType;
        }

        /* renamed from: of */
        public static StubType m3923of(ClientCalls.StubType stubType) {
            for (StubType stubType2 : values()) {
                if (stubType2.internalType == stubType) {
                    return stubType2;
                }
            }
            throw new AssertionError("Unknown StubType: " + stubType.name());
        }
    }
}

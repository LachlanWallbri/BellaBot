package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ClientStreamTracer extends StreamTracer {
    public void inboundHeaders() {
    }

    public void inboundTrailers(Metadata metadata) {
    }

    public void outboundHeaders() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Factory {
        @Deprecated
        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata metadata) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public ClientStreamTracer newClientStreamTracer(StreamInfo streamInfo, Metadata metadata) {
            return newClientStreamTracer(streamInfo.getCallOptions(), metadata);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class StreamInfo {
        private final CallOptions callOptions;
        private final Attributes transportAttrs;

        StreamInfo(Attributes attributes, CallOptions callOptions) {
            this.transportAttrs = (Attributes) Preconditions.checkNotNull(attributes, "transportAttrs");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
        }

        public Attributes getTransportAttrs() {
            return this.transportAttrs;
        }

        public CallOptions getCallOptions() {
            return this.callOptions;
        }

        public Builder toBuilder() {
            Builder builder = new Builder();
            builder.setTransportAttrs(this.transportAttrs);
            builder.setCallOptions(this.callOptions);
            return builder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("transportAttrs", this.transportAttrs).add("callOptions", this.callOptions).toString();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder {
            private Attributes transportAttrs = Attributes.EMPTY;
            private CallOptions callOptions = CallOptions.DEFAULT;

            Builder() {
            }

            public Builder setTransportAttrs(Attributes attributes) {
                this.transportAttrs = (Attributes) Preconditions.checkNotNull(attributes, "transportAttrs cannot be null");
                return this;
            }

            public Builder setCallOptions(CallOptions callOptions) {
                this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions cannot be null");
                return this;
            }

            public StreamInfo build() {
                return new StreamInfo(this.transportAttrs, this.callOptions);
            }
        }
    }
}

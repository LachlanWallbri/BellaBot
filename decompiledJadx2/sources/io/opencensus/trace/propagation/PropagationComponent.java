package io.opencensus.trace.propagation;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class PropagationComponent {
    private static final PropagationComponent NOOP_PROPAGATION_COMPONENT = new NoopPropagationComponent();

    public abstract TextFormat getB3Format();

    public abstract BinaryFormat getBinaryFormat();

    public abstract TextFormat getTraceContextFormat();

    public static PropagationComponent getNoopPropagationComponent() {
        return NOOP_PROPAGATION_COMPONENT;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopPropagationComponent extends PropagationComponent {
        private NoopPropagationComponent() {
        }

        @Override // io.opencensus.trace.propagation.PropagationComponent
        public BinaryFormat getBinaryFormat() {
            return BinaryFormat.getNoopBinaryFormat();
        }

        @Override // io.opencensus.trace.propagation.PropagationComponent
        public TextFormat getB3Format() {
            return TextFormat.getNoopTextFormat();
        }

        @Override // io.opencensus.trace.propagation.PropagationComponent
        public TextFormat getTraceContextFormat() {
            return TextFormat.getNoopTextFormat();
        }
    }
}

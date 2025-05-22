package io.minio;

import io.minio.ObjectReadArgs;
import io.minio.messages.InputSerialization;
import io.minio.messages.OutputSerialization;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SelectObjectContentArgs extends ObjectReadArgs {
    private InputSerialization inputSerialization;
    private OutputSerialization outputSerialization;
    private Boolean requestProgress;
    private Long scanEndRange;
    private Long scanStartRange;
    private String sqlExpression;

    public Long scanEndRange() {
        return this.scanEndRange;
    }

    public Long scanStartRange() {
        return this.scanStartRange;
    }

    public Boolean requestProgress() {
        return this.requestProgress;
    }

    public OutputSerialization outputSerialization() {
        return this.outputSerialization;
    }

    public InputSerialization inputSerialization() {
        return this.inputSerialization;
    }

    public String sqlExpression() {
        return this.sqlExpression;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectReadArgs.Builder<Builder, SelectObjectContentArgs> {
        private void validateSqlExpression(String str) {
            validateNotEmptyString(str, "sqlExpression");
        }

        public Builder sqlExpression(final String str) {
            validateSqlExpression(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$Hev16km-XauLeiRBZzs6G7vkor0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).sqlExpression = str;
                }
            });
            return this;
        }

        private void validateInputSerialization(InputSerialization inputSerialization) {
            validateNotNull(inputSerialization, "inputSerialization");
        }

        public Builder inputSerialization(final InputSerialization inputSerialization) {
            validateInputSerialization(inputSerialization);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$JNOXRXZroEgKMqGEcsiBAwoLOw0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).inputSerialization = InputSerialization.this;
                }
            });
            return this;
        }

        private void validateOutputSerialization(OutputSerialization outputSerialization) {
            validateNotNull(outputSerialization, "outputSerialization");
        }

        public Builder outputSerialization(final OutputSerialization outputSerialization) {
            validateOutputSerialization(outputSerialization);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$ZRm42PA4UjyUHypu5m4oAgCVO6A
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).outputSerialization = OutputSerialization.this;
                }
            });
            return this;
        }

        public Builder requestProgress(final Boolean bool) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$6WC1VcVOZ9er86yw4kMKzH4W4Ck
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).requestProgress = bool;
                }
            });
            return this;
        }

        public Builder scanStartRange(final Long l) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$NLBufilZuJ3lF8euVDhLmp4M9w0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).scanStartRange = l;
                }
            });
            return this;
        }

        public Builder scanEndRange(final Long l) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SelectObjectContentArgs$Builder$l5rAlRBEKnTGYcyJxUC49-PaKT4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SelectObjectContentArgs) obj).scanEndRange = l;
                }
            });
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(SelectObjectContentArgs selectObjectContentArgs) {
            super.validate((Builder) selectObjectContentArgs);
            validateSqlExpression(selectObjectContentArgs.sqlExpression());
            validateInputSerialization(selectObjectContentArgs.inputSerialization());
            validateOutputSerialization(selectObjectContentArgs.outputSerialization());
        }
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectObjectContentArgs) || !super.equals(obj)) {
            return false;
        }
        SelectObjectContentArgs selectObjectContentArgs = (SelectObjectContentArgs) obj;
        return Objects.equals(this.sqlExpression, selectObjectContentArgs.sqlExpression) && Objects.equals(this.inputSerialization, selectObjectContentArgs.inputSerialization) && Objects.equals(this.outputSerialization, selectObjectContentArgs.outputSerialization) && Objects.equals(this.requestProgress, selectObjectContentArgs.requestProgress) && Objects.equals(this.scanStartRange, selectObjectContentArgs.scanStartRange) && Objects.equals(this.scanEndRange, selectObjectContentArgs.scanEndRange);
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.sqlExpression, this.inputSerialization, this.outputSerialization, this.requestProgress, this.scanStartRange, this.scanEndRange);
    }
}

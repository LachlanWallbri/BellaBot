package io.opencensus.metrics;

import io.opencensus.common.ToLongFunction;
import io.opencensus.internal.Utils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class DerivedLongCumulative {
    public abstract void clear();

    public abstract <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction);

    public abstract void removeTimeSeries(List<LabelValue> list);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DerivedLongCumulative newNoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDerivedLongCumulative.create(str, str2, str3, list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopDerivedLongCumulative extends DerivedLongCumulative {
        private final int labelKeysSize;

        @Override // io.opencensus.metrics.DerivedLongCumulative
        public void clear() {
        }

        static NoopDerivedLongCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDerivedLongCumulative(str, str2, str3, list);
        }

        NoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, "name");
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        @Override // io.opencensus.metrics.DerivedLongCumulative
        public <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            Utils.checkNotNull(toLongFunction, "function");
        }

        @Override // io.opencensus.metrics.DerivedLongCumulative
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }
}

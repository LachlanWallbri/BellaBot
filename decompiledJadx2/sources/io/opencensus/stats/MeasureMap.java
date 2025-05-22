package io.opencensus.stats;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.AttachmentValue;
import io.opencensus.stats.Measure;
import io.opencensus.tags.TagContext;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class MeasureMap {
    public abstract MeasureMap put(Measure.MeasureDouble measureDouble, double d);

    public abstract MeasureMap put(Measure.MeasureLong measureLong, long j);

    public abstract void record();

    public abstract void record(TagContext tagContext);

    @Deprecated
    public MeasureMap putAttachment(String str, String str2) {
        return putAttachment(str, AttachmentValue.AttachmentValueString.create(str2));
    }

    public MeasureMap putAttachment(String str, AttachmentValue attachmentValue) {
        Utils.checkNotNull(str, TransferTable.COLUMN_KEY);
        Utils.checkNotNull(attachmentValue, ES6Iterator.VALUE_PROPERTY);
        return this;
    }
}

package io.opencensus.stats;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class BucketBoundaries {
    private static final Logger logger = Logger.getLogger(BucketBoundaries.class.getName());

    public abstract List<Double> getBoundaries();

    public static final BucketBoundaries create(List<Double> list) {
        Utils.checkNotNull(list, "bucketBoundaries");
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() > 1) {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            int i = 1;
            while (i < arrayList.size()) {
                double doubleValue2 = ((Double) arrayList.get(i)).doubleValue();
                Utils.checkArgument(doubleValue < doubleValue2, "Bucket boundaries not sorted.");
                i++;
                doubleValue = doubleValue2;
            }
        }
        return new AutoValue_BucketBoundaries(Collections.unmodifiableList(dropNegativeBucketBounds(arrayList)));
    }

    private static List<Double> dropNegativeBucketBounds(List<Double> list) {
        int i = 0;
        int i2 = 0;
        for (Double d : list) {
            if (d.doubleValue() > 0.0d) {
                break;
            }
            if (d.doubleValue() == 0.0d) {
                i2++;
            } else {
                i++;
            }
        }
        if (i > 0) {
            logger.log(Level.WARNING, "Dropping " + i + " negative bucket boundaries, the values must be strictly > 0.");
        }
        return list.subList(i + i2, list.size());
    }
}

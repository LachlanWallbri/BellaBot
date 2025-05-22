package io.opencensus.stats;

import io.opencensus.stats.View;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class ViewManager {
    public abstract Set<View> getAllExportedViews();

    @Nullable
    public abstract ViewData getView(View.Name name);

    public abstract void registerView(View view);
}

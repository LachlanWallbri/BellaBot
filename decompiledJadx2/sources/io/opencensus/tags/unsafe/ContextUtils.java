package io.opencensus.tags.unsafe;

import io.grpc.Context;
import io.opencensus.internal.Utils;
import io.opencensus.tags.Tag;
import io.opencensus.tags.TagContext;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class ContextUtils {
    private static final TagContext EMPTY_TAG_CONTEXT = new EmptyTagContext();
    private static final Context.Key<TagContext> TAG_CONTEXT_KEY = Context.keyWithDefault("opencensus-tag-context-key", EMPTY_TAG_CONTEXT);

    private ContextUtils() {
    }

    public static Context withValue(Context context, @Nullable TagContext tagContext) {
        return ((Context) Utils.checkNotNull(context, "context")).withValue(TAG_CONTEXT_KEY, tagContext);
    }

    public static TagContext getValue(Context context) {
        TagContext tagContext = TAG_CONTEXT_KEY.get(context);
        return tagContext == null ? EMPTY_TAG_CONTEXT : tagContext;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class EmptyTagContext extends TagContext {
        private EmptyTagContext() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.opencensus.tags.TagContext
        public Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }
}

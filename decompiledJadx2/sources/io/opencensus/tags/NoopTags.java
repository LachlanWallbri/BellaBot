package io.opencensus.tags;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import io.opencensus.common.Scope;
import io.opencensus.internal.NoopScope;
import io.opencensus.internal.Utils;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextDeserializationException;
import io.opencensus.tags.propagation.TagContextSerializationException;
import io.opencensus.tags.propagation.TagContextTextFormat;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class NoopTags {
    private NoopTags() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TagsComponent newNoopTagsComponent() {
        return new NoopTagsComponent();
    }

    static Tagger getNoopTagger() {
        return NoopTagger.INSTANCE;
    }

    static TagContextBuilder getNoopTagContextBuilder() {
        return NoopTagContextBuilder.INSTANCE;
    }

    static TagContext getNoopTagContext() {
        return NoopTagContext.INSTANCE;
    }

    static TagPropagationComponent getNoopTagPropagationComponent() {
        return NoopTagPropagationComponent.INSTANCE;
    }

    static TagContextBinarySerializer getNoopTagContextBinarySerializer() {
        return NoopTagContextBinarySerializer.INSTANCE;
    }

    static TagContextTextFormat getNoopTagContextTextSerializer() {
        return NoopTagContextTextFormat.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopTagsComponent extends TagsComponent {
        private volatile boolean isRead;

        private NoopTagsComponent() {
        }

        @Override // io.opencensus.tags.TagsComponent
        public Tagger getTagger() {
            return NoopTags.getNoopTagger();
        }

        @Override // io.opencensus.tags.TagsComponent
        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.getNoopTagPropagationComponent();
        }

        @Override // io.opencensus.tags.TagsComponent
        public TaggingState getState() {
            this.isRead = true;
            return TaggingState.DISABLED;
        }

        @Override // io.opencensus.tags.TagsComponent
        @Deprecated
        public void setState(TaggingState taggingState) {
            Utils.checkNotNull(taggingState, "state");
            Utils.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagger extends Tagger {
        static final Tagger INSTANCE = new NoopTagger();

        private NoopTagger() {
        }

        @Override // io.opencensus.tags.Tagger
        public TagContext empty() {
            return NoopTags.getNoopTagContext();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContext getCurrentTagContext() {
            return NoopTags.getNoopTagContext();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder emptyBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder toBuilder(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder currentBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override // io.opencensus.tags.Tagger
        public Scope withTagContext(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopScope.getInstance();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagContextBuilder extends TagContextBuilder {
        static final TagContextBuilder INSTANCE = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            Utils.checkNotNull(tagKey, TransferTable.COLUMN_KEY);
            Utils.checkNotNull(tagValue, ES6Iterator.VALUE_PROPERTY);
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder put(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
            Utils.checkNotNull(tagKey, TransferTable.COLUMN_KEY);
            Utils.checkNotNull(tagValue, ES6Iterator.VALUE_PROPERTY);
            Utils.checkNotNull(tagMetadata, "tagMetadata");
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder remove(TagKey tagKey) {
            Utils.checkNotNull(tagKey, TransferTable.COLUMN_KEY);
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContext build() {
            return NoopTags.getNoopTagContext();
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public Scope buildScoped() {
            return NoopScope.getInstance();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagContext extends TagContext {
        static final TagContext INSTANCE = new NoopTagContext();

        private NoopTagContext() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.opencensus.tags.TagContext
        public Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagPropagationComponent extends TagPropagationComponent {
        static final TagPropagationComponent INSTANCE = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        @Override // io.opencensus.tags.propagation.TagPropagationComponent
        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.getNoopTagContextBinarySerializer();
        }

        @Override // io.opencensus.tags.propagation.TagPropagationComponent
        public TagContextTextFormat getCorrelationContextFormat() {
            return NoopTags.getNoopTagContextTextSerializer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagContextBinarySerializer extends TagContextBinarySerializer {
        static final TagContextBinarySerializer INSTANCE = new NoopTagContextBinarySerializer();
        static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

        private NoopTagContextBinarySerializer() {
        }

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public byte[] toByteArray(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return EMPTY_BYTE_ARRAY;
        }

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public TagContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return NoopTags.getNoopTagContext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopTagContextTextFormat extends TagContextTextFormat {
        static final NoopTagContextTextFormat INSTANCE = new NoopTagContextTextFormat();

        private NoopTagContextTextFormat() {
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public List<String> fields() {
            return Collections.emptyList();
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public <C> void inject(TagContext tagContext, C c, TagContextTextFormat.Setter<C> setter) throws TagContextSerializationException {
            Utils.checkNotNull(tagContext, "tagContext");
            Utils.checkNotNull(c, AppInfoUtil.CARRIER);
            Utils.checkNotNull(setter, "setter");
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public <C> TagContext extract(C c, TagContextTextFormat.Getter<C> getter) throws TagContextDeserializationException {
            Utils.checkNotNull(c, AppInfoUtil.CARRIER);
            Utils.checkNotNull(getter, "getter");
            return NoopTags.getNoopTagContext();
        }
    }
}

package com.google.api.gax.core;

import com.google.api.gax.core.InstantiatingExecutorProvider;
import java.util.concurrent.ThreadFactory;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_InstantiatingExecutorProvider extends InstantiatingExecutorProvider {
    private final int executorThreadCount;
    private final ThreadFactory threadFactory;

    private AutoValue_InstantiatingExecutorProvider(int i, ThreadFactory threadFactory) {
        this.executorThreadCount = i;
        this.threadFactory = threadFactory;
    }

    @Override // com.google.api.gax.core.InstantiatingExecutorProvider
    public int getExecutorThreadCount() {
        return this.executorThreadCount;
    }

    @Override // com.google.api.gax.core.InstantiatingExecutorProvider
    public ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    public String toString() {
        return "InstantiatingExecutorProvider{executorThreadCount=" + this.executorThreadCount + ", threadFactory=" + this.threadFactory + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstantiatingExecutorProvider)) {
            return false;
        }
        InstantiatingExecutorProvider instantiatingExecutorProvider = (InstantiatingExecutorProvider) obj;
        return this.executorThreadCount == instantiatingExecutorProvider.getExecutorThreadCount() && this.threadFactory.equals(instantiatingExecutorProvider.getThreadFactory());
    }

    public int hashCode() {
        return ((this.executorThreadCount ^ 1000003) * 1000003) ^ this.threadFactory.hashCode();
    }

    @Override // com.google.api.gax.core.InstantiatingExecutorProvider
    public InstantiatingExecutorProvider.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class Builder extends InstantiatingExecutorProvider.Builder {
        private Integer executorThreadCount;
        private ThreadFactory threadFactory;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(InstantiatingExecutorProvider instantiatingExecutorProvider) {
            this.executorThreadCount = Integer.valueOf(instantiatingExecutorProvider.getExecutorThreadCount());
            this.threadFactory = instantiatingExecutorProvider.getThreadFactory();
        }

        @Override // com.google.api.gax.core.InstantiatingExecutorProvider.Builder
        public InstantiatingExecutorProvider.Builder setExecutorThreadCount(int i) {
            this.executorThreadCount = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.api.gax.core.InstantiatingExecutorProvider.Builder
        public int getExecutorThreadCount() {
            Integer num = this.executorThreadCount;
            if (num == null) {
                throw new IllegalStateException("Property \"executorThreadCount\" has not been set");
            }
            return num.intValue();
        }

        @Override // com.google.api.gax.core.InstantiatingExecutorProvider.Builder
        public InstantiatingExecutorProvider.Builder setThreadFactory(ThreadFactory threadFactory) {
            if (threadFactory == null) {
                throw new NullPointerException("Null threadFactory");
            }
            this.threadFactory = threadFactory;
            return this;
        }

        @Override // com.google.api.gax.core.InstantiatingExecutorProvider.Builder
        public ThreadFactory getThreadFactory() {
            ThreadFactory threadFactory = this.threadFactory;
            if (threadFactory != null) {
                return threadFactory;
            }
            throw new IllegalStateException("Property \"threadFactory\" has not been set");
        }

        @Override // com.google.api.gax.core.InstantiatingExecutorProvider.Builder
        public InstantiatingExecutorProvider build() {
            String str = "";
            if (this.executorThreadCount == null) {
                str = " executorThreadCount";
            }
            if (this.threadFactory == null) {
                str = str + " threadFactory";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_InstantiatingExecutorProvider(this.executorThreadCount.intValue(), this.threadFactory);
        }
    }
}

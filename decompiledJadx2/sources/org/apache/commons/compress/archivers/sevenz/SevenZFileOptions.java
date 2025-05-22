package org.apache.commons.compress.archivers.sevenz;

/* loaded from: classes8.dex */
public class SevenZFileOptions {
    public static final SevenZFileOptions DEFAULT = new SevenZFileOptions(Integer.MAX_VALUE, false, false);
    private static final boolean DEFAULT_TRY_TO_RECOVER_BROKEN_ARCHIVES = false;
    private static final boolean DEFAULT_USE_DEFAULTNAME_FOR_UNNAMED_ENTRIES = false;
    private static final int DEFAUL_MEMORY_LIMIT_IN_KB = Integer.MAX_VALUE;
    private final int maxMemoryLimitInKb;
    private final boolean tryToRecoverBrokenArchives;
    private final boolean useDefaultNameForUnnamedEntries;

    private SevenZFileOptions(int i, boolean z, boolean z2) {
        this.maxMemoryLimitInKb = i;
        this.useDefaultNameForUnnamedEntries = z;
        this.tryToRecoverBrokenArchives = z2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getMaxMemoryLimitInKb() {
        return this.maxMemoryLimitInKb;
    }

    public boolean getUseDefaultNameForUnnamedEntries() {
        return this.useDefaultNameForUnnamedEntries;
    }

    public boolean getTryToRecoverBrokenArchives() {
        return this.tryToRecoverBrokenArchives;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private int maxMemoryLimitInKb = Integer.MAX_VALUE;
        private boolean useDefaultNameForUnnamedEntries = false;
        private boolean tryToRecoverBrokenArchives = false;

        public Builder withMaxMemoryLimitInKb(int i) {
            this.maxMemoryLimitInKb = i;
            return this;
        }

        public Builder withUseDefaultNameForUnnamedEntries(boolean z) {
            this.useDefaultNameForUnnamedEntries = z;
            return this;
        }

        public Builder withTryToRecoverBrokenArchives(boolean z) {
            this.tryToRecoverBrokenArchives = z;
            return this;
        }

        public SevenZFileOptions build() {
            return new SevenZFileOptions(this.maxMemoryLimitInKb, this.useDefaultNameForUnnamedEntries, this.tryToRecoverBrokenArchives);
        }
    }
}

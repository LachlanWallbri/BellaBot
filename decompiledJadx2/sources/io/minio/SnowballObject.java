package io.minio;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes7.dex */
public class SnowballObject {
    private String filename;
    private ZonedDateTime modificationTime;
    private String name;
    private long size;
    private InputStream stream;

    public SnowballObject(@Nonnull String str, @Nonnull InputStream inputStream, long j, @Nullable ZonedDateTime zonedDateTime) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("name must be provided");
        }
        this.name = str.startsWith("/") ? str.substring(1) : str;
        this.stream = (InputStream) Objects.requireNonNull(inputStream, "stream must not be null");
        if (j < 0) {
            throw new IllegalArgumentException("size cannot be negative value");
        }
        this.size = j;
        this.modificationTime = zonedDateTime;
    }

    public SnowballObject(@Nonnull String str, @Nonnull String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("name must be provided");
        }
        this.name = str.startsWith("/") ? str.substring(1) : str;
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("filename must be provided");
        }
        this.filename = str2;
    }

    public String name() {
        return this.name;
    }

    public InputStream stream() {
        return this.stream;
    }

    public long size() {
        return this.size;
    }

    public String filename() {
        return this.filename;
    }

    public ZonedDateTime modificationTime() {
        return this.modificationTime;
    }
}

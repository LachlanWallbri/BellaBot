package com.pudu.library.loracall.bean;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoraUpdateFile.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoraUpdateFile;", "", "file", "Ljava/io/File;", "type", "", "version", "", "(Ljava/io/File;IF)V", "getFile", "()Ljava/io/File;", "getType", "()I", "getVersion", "()F", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraUpdateFile {
    private final File file;
    private final int type;
    private final float version;

    public LoraUpdateFile() {
        this(null, 0, 0.0f, 7, null);
    }

    public static /* synthetic */ LoraUpdateFile copy$default(LoraUpdateFile loraUpdateFile, File file, int i, float f, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            file = loraUpdateFile.file;
        }
        if ((i2 & 2) != 0) {
            i = loraUpdateFile.type;
        }
        if ((i2 & 4) != 0) {
            f = loraUpdateFile.version;
        }
        return loraUpdateFile.copy(file, i, f);
    }

    /* renamed from: component1, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final float getVersion() {
        return this.version;
    }

    public final LoraUpdateFile copy(File file, int type, float version) {
        return new LoraUpdateFile(file, type, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoraUpdateFile)) {
            return false;
        }
        LoraUpdateFile loraUpdateFile = (LoraUpdateFile) other;
        return Intrinsics.areEqual(this.file, loraUpdateFile.file) && this.type == loraUpdateFile.type && Float.compare(this.version, loraUpdateFile.version) == 0;
    }

    public int hashCode() {
        File file = this.file;
        return ((((file != null ? file.hashCode() : 0) * 31) + Integer.hashCode(this.type)) * 31) + Float.hashCode(this.version);
    }

    public String toString() {
        return "LoraUpdateFile(file=" + this.file + ", type=" + this.type + ", version=" + this.version + ")";
    }

    public LoraUpdateFile(File file, int i, float f) {
        this.file = file;
        this.type = i;
        this.version = f;
    }

    public /* synthetic */ LoraUpdateFile(File file, int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? (File) null : file, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? -1.0f : f);
    }

    public final File getFile() {
        return this.file;
    }

    public final int getType() {
        return this.type;
    }

    public final float getVersion() {
        return this.version;
    }
}

package okhttp3.internal.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: FileSystem.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0014"}, m3961d2 = {"okhttp3/internal/io/FileSystem$Companion$SYSTEM$1", "Lokhttp3/internal/io/FileSystem;", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", RequestParameters.SUBRESOURCE_DELETE, "", "deleteContents", "directory", "exists", "", "rename", "from", TypedValues.Transition.S_TO, "sink", "size", "", MapElement.Source.SOURCE, "Lokio/Source;", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class FileSystem$Companion$SYSTEM$1 implements FileSystem {
    FileSystem$Companion$SYSTEM$1() {
    }

    @Override // okhttp3.internal.io.FileSystem
    public Source source(File file) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return Okio.source(file);
    }

    @Override // okhttp3.internal.io.FileSystem
    public Sink sink(File file) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "file");
        try {
            return Okio.sink$default(file, false, 1, null);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return Okio.sink$default(file, false, 1, null);
        }
    }

    @Override // okhttp3.internal.io.FileSystem
    public Sink appendingSink(File file) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "file");
        try {
            return Okio.appendingSink(file);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return Okio.appendingSink(file);
        }
    }

    @Override // okhttp3.internal.io.FileSystem
    public void delete(File file) throws IOException {
        Intrinsics.checkParameterIsNotNull(file, "file");
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }

    @Override // okhttp3.internal.io.FileSystem
    public boolean exists(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.exists();
    }

    @Override // okhttp3.internal.io.FileSystem
    public long size(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.length();
    }

    @Override // okhttp3.internal.io.FileSystem
    public void rename(File from, File to) throws IOException {
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(to, "to");
        delete(to);
        if (from.renameTo(to)) {
            return;
        }
        throw new IOException("failed to rename " + from + " to " + to);
    }

    @Override // okhttp3.internal.io.FileSystem
    public void deleteContents(File directory) throws IOException {
        Intrinsics.checkParameterIsNotNull(directory, "directory");
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + directory);
        }
        for (File file : listFiles) {
            Intrinsics.checkExpressionValueIsNotNull(file, "file");
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (!file.delete()) {
                throw new IOException("failed to delete " + file);
            }
        }
    }
}

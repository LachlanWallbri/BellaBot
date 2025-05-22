package androidx.core.net;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Uri.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"}, m3961d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class UriKt {
    public static final Uri toUri(String toUri) {
        Intrinsics.checkParameterIsNotNull(toUri, "$this$toUri");
        Uri parse = Uri.parse(toUri);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
        return parse;
    }

    public static final Uri toUri(File toUri) {
        Intrinsics.checkParameterIsNotNull(toUri, "$this$toUri");
        Uri fromFile = Uri.fromFile(toUri);
        Intrinsics.checkExpressionValueIsNotNull(fromFile, "Uri.fromFile(this)");
        return fromFile;
    }

    public static final File toFile(Uri toFile) {
        Intrinsics.checkParameterIsNotNull(toFile, "$this$toFile");
        if (!Intrinsics.areEqual(toFile.getScheme(), "file")) {
            throw new IllegalArgumentException(("Uri lacks 'file' scheme: " + toFile).toString());
        }
        String path = toFile.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(("Uri path is null: " + toFile).toString());
    }
}

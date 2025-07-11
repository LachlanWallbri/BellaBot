package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: FilePathComponents.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/io/FilesKt")
/* loaded from: classes2.dex */
public class FilesKt__FilePathComponentsKt {
    private static final int getRootLength$FilesKt__FilePathComponentsKt(String str) {
        int indexOf$default;
        String str2 = str;
        int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str2, File.separatorChar, 0, false, 4, (Object) null);
        if (indexOf$default2 == 0) {
            if (str.length() <= 1 || str.charAt(1) != File.separatorChar || (indexOf$default = StringsKt.indexOf$default((CharSequence) str2, File.separatorChar, 2, false, 4, (Object) null)) < 0) {
                return 1;
            }
            int indexOf$default3 = StringsKt.indexOf$default((CharSequence) str2, File.separatorChar, indexOf$default + 1, false, 4, (Object) null);
            return indexOf$default3 >= 0 ? indexOf$default3 + 1 : str.length();
        }
        if (indexOf$default2 > 0 && str.charAt(indexOf$default2 - 1) == ':') {
            return indexOf$default2 + 1;
        }
        if (indexOf$default2 == -1 && StringsKt.endsWith$default((CharSequence) str2, ':', false, 2, (Object) null)) {
            return str.length();
        }
        return 0;
    }

    public static final String getRootName(File rootName) {
        Intrinsics.checkParameterIsNotNull(rootName, "$this$rootName");
        String path = rootName.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        String path2 = rootName.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path2);
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static final File getRoot(File root) {
        Intrinsics.checkParameterIsNotNull(root, "$this$root");
        return new File(FilesKt.getRootName(root));
    }

    public static final boolean isRooted(File isRooted) {
        Intrinsics.checkParameterIsNotNull(isRooted, "$this$isRooted");
        String path = isRooted.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return getRootLength$FilesKt__FilePathComponentsKt(path) > 0;
    }

    public static final FilePathComponents toComponents(File toComponents) {
        ArrayList arrayList;
        Intrinsics.checkParameterIsNotNull(toComponents, "$this$toComponents");
        String path = toComponents.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path);
        String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String substring2 = path.substring(rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        String str = substring2;
        if (str.length() == 0) {
            arrayList = CollectionsKt.emptyList();
        } else {
            List split$default = StringsKt.split$default((CharSequence) str, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
            Iterator it = split$default.iterator();
            while (it.hasNext()) {
                arrayList2.add(new File((String) it.next()));
            }
            arrayList = arrayList2;
        }
        return new FilePathComponents(new File(substring), arrayList);
    }

    public static final File subPath(File subPath, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(subPath, "$this$subPath");
        return FilesKt.toComponents(subPath).subPath(i, i2);
    }
}

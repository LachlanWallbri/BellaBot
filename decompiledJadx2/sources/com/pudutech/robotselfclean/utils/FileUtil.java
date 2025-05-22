package com.pudutech.robotselfclean.utils;

import com.pudutech.base.Pdlog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\u0007\u001a\u00020\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robotselfclean/utils/FileUtil;", "", "()V", "TAG", "", "deleteFile", "", "filePath", "deleteFolder", "", "filterList", "", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private FileUtil() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
    
        if ((r4.length == 0) != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void deleteFile(String filePath) {
        String str = filePath;
        boolean z = false;
        if (str == null || str.length() == 0) {
            return;
        }
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                String[] list = file.list();
                if (list != null) {
                }
                z = true;
                if (z) {
                    file.delete();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List deleteFolder$default(FileUtil fileUtil, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = new ArrayList();
        }
        return fileUtil.deleteFolder(str, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<String> deleteFolder(String filePath, List<String> filterList) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(filterList, "filterList");
        ArrayList arrayList = new ArrayList();
        int i = 1;
        char c = 0;
        if (filePath.length() == 0) {
            return new ArrayList();
        }
        if (filterList.contains(filePath)) {
            filterList.remove(filePath);
            Pdlog.m3273d(TAG, "deleteFolder remove file = " + filePath);
            return new ArrayList();
        }
        File file = new File(filePath);
        if (file.isFile()) {
            boolean delete = file.delete();
            Pdlog.m3273d(TAG, "deleteFolder delete file = " + file.getPath());
            if (!delete) {
                String path = file.getPath();
                Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
                arrayList.add(path);
            }
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "file.listFiles()");
            int length = listFiles.length;
            int i2 = 0;
            while (i2 < length) {
                File it = listFiles[i2];
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (filterList.contains(it.getPath())) {
                    filterList.remove(it.getPath());
                    String str = TAG;
                    Object[] objArr = new Object[i];
                    objArr[c] = "deleteFolder remove file = " + it.getPath();
                    Pdlog.m3273d(str, objArr);
                } else if (it.isFile()) {
                    boolean delete2 = it.delete();
                    String str2 = TAG;
                    Object[] objArr2 = new Object[i];
                    objArr2[0] = "deleteFolder delete file = " + it.getPath();
                    Pdlog.m3273d(str2, objArr2);
                    if (!delete2) {
                        String path2 = it.getPath();
                        Intrinsics.checkExpressionValueIsNotNull(path2, "it.path");
                        arrayList.add(path2);
                    }
                } else {
                    FileUtil fileUtil = INSTANCE;
                    String path3 = it.getPath();
                    Intrinsics.checkExpressionValueIsNotNull(path3, "it.path");
                    arrayList.addAll(fileUtil.deleteFolder(path3, filterList));
                }
                i2++;
                i = 1;
                c = 0;
            }
            String[] list = file.list();
            if (list != null) {
                if (!(list.length == 0)) {
                    z = false;
                    if (z) {
                        boolean delete3 = file.delete();
                        Pdlog.m3273d(TAG, "deleteFolder delete folder = " + file.getPath());
                        if (!delete3) {
                            String path4 = file.getPath();
                            Intrinsics.checkExpressionValueIsNotNull(path4, "file.path");
                            arrayList.add(path4);
                        }
                    }
                }
            }
            z = true;
            if (z) {
            }
        }
        return arrayList;
    }
}

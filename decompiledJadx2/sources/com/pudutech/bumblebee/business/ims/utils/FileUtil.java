package com.pudutech.bumblebee.business.ims.utils;

import com.iflytek.aiui.AIUIConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;

/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J$\u0010\u0006\u001a\u00020\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/utils/FileUtil;", "", "()V", "readContentFromFile", "", "path", "saveContentToFile", "", AIUIConstant.KEY_CONTENT, "isAppend", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public static /* synthetic */ void saveContentToFile$default(FileUtil fileUtil, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        fileUtil.saveContentToFile(str, str2, z);
    }

    public final void saveContentToFile(String path, String content, boolean isAppend) {
        String str = path;
        if (str == null || str.length() == 0) {
            return;
        }
        FileWriter fileWriter = (FileWriter) null;
        try {
            try {
                try {
                    File file = new File(path);
                    if (isAppend) {
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                    } else if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter2 = new FileWriter(file, isAppend);
                    try {
                        fileWriter2.write(content);
                        fileWriter2.close();
                    } catch (Exception e) {
                        fileWriter = fileWriter2;
                        e = e;
                        e.printStackTrace();
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (Throwable th) {
                        fileWriter = fileWriter2;
                        th = th;
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String readContentFromFile(String path) {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        String str = path;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        InputStream inputStream = (InputStream) null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            fileInputStream = inputStream;
            e = e2;
        } catch (Throwable th2) {
            fileInputStream = inputStream;
            th = th2;
            if (fileInputStream != null) {
            }
            throw th;
        }
        try {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str2 = (String) null;
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str2 = readLine;
                    } else {
                        readLine = null;
                    }
                    if (readLine == null) {
                        break;
                    }
                    sb.append(str2);
                    sb.append("\n");
                }
                String sb2 = sb.toString();
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return sb2;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            return null;
        }
    }
}

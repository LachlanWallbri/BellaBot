package com.pudutech.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class FileUtil {
    public static final String DEFAULT_ROOT_PATH = "/mnt/download/pudu_kuaichuan/";
    public static final String DEFAULT_SCREENSHOT_PATH = "/mnt/kc_screenshot/";
    public static final int TYPE_APK = 1;
    public static final int TYPE_JPEG = 2;
    public static final int TYPE_MP3 = 3;
    public static final int TYPE_MP4 = 4;
    private static final String TAG = FileUtil.class.getSimpleName();
    public static final DecimalFormat FORMAT = new DecimalFormat("####.##");
    public static final DecimalFormat FORMAT_ONE = new DecimalFormat("####.#");
    private static final String SEPARATOR = File.separator;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum PathStatus {
        SUCCESS,
        EXITS,
        ERROR
    }

    public static void write(Context context, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String read(Context context, String str) {
        try {
            return readInStream(context.openFileInput(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readInStream(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[512];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e) {
            Pdlog.m3275i("FileTest", e.getMessage());
            return null;
        }
    }

    public static boolean writeFile(byte[] bArr, String str, String str2) {
        String str3;
        if (Environment.getExternalStorageState().equals("mounted")) {
            str3 = Environment.getExternalStorageDirectory() + File.separator + str + File.separator;
        } else {
            str3 = "";
        }
        boolean z = false;
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(str3 + str2));
                    try {
                        fileOutputStream2.write(bArr);
                        z = true;
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        fileOutputStream.close();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return z;
    }

    public static String getFileName(String str) {
        return StringUtils.isEmpty(str) ? "" : str.substring(str.lastIndexOf(File.separator) + 1);
    }

    public static String getFileNameNoFormat(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(File.separator) + 1, str.lastIndexOf(46));
    }

    public static String getFileFormat(String str) {
        return StringUtils.isEmpty(str) ? "" : str.substring(str.lastIndexOf(46) + 1);
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    public static String getFileSize(long j) {
        if (j <= 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        float f = ((float) j) / 1024.0f;
        if (f >= 1024.0f) {
            return decimalFormat.format(f / 1024.0f) + "M";
        }
        return decimalFormat.format(f) + "K";
    }

    public static String formatFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j < 1024) {
            return decimalFormat.format(j) + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "G";
    }

    public static long getDirSize(File file) {
        long dirSize;
        long j = 0;
        if (file == null || !file.isDirectory()) {
            return 0L;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    dirSize = file2.length();
                } else if (file2.isDirectory()) {
                    j += file2.length();
                    dirSize = getDirSize(file2);
                }
                j += dirSize;
            }
        }
        return j;
    }

    public long getFileList(File file) {
        File[] listFiles = file.listFiles();
        long length = listFiles.length;
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                length = (length + getFileList(file2)) - 1;
            }
        }
        return length;
    }

    public static byte[] toBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                byteArrayOutputStream.write(read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static byte[] toBytes(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read();
            if (read != -1) {
                byteArrayOutputStream.write(read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static boolean checkFileExists(String str) {
        if (str.equals("")) {
            return false;
        }
        return new File(Environment.getExternalStorageDirectory().toString() + str).exists();
    }

    public static boolean checkFilePathExists(String str) {
        return new File(str).exists();
    }

    public static boolean checkSaveLocationExists() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean checkExternalSDExists() {
        return System.getenv().containsKey("SECONDARY_STORAGE");
    }

    public static boolean deleteDirectory(String str) {
        SecurityManager securityManager = new SecurityManager();
        boolean z = true;
        Pdlog.m3275i("App", str);
        if (str.equals("")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + str);
        securityManager.checkDelete(file.toString());
        Pdlog.m3275i("App", "check" + str + " :directory:" + file.isDirectory());
        if (!file.isDirectory()) {
            return false;
        }
        Pdlog.m3275i("App", "dire");
        for (String str2 : file.list()) {
            try {
                new File(file.toString() + "/" + str2.toString()).delete();
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        file.delete();
        Pdlog.m3275i("App", str);
        return z;
    }

    public static boolean deleteChildRenDirectory(String str) {
        SecurityManager securityManager = new SecurityManager();
        boolean z = true;
        Pdlog.m3275i("App", str);
        if (str.equals("")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + str);
        securityManager.checkDelete(file.toString());
        Pdlog.m3275i("App", "check" + str + " :directory:" + file.isDirectory());
        if (!file.isDirectory()) {
            return false;
        }
        Pdlog.m3275i("App", "dire");
        for (String str2 : file.list()) {
            try {
                new File(file.toString() + "/" + str2.toString()).delete();
            } catch (Exception e) {
                e.printStackTrace();
                Pdlog.m3275i("App", "dire" + e);
                z = false;
            }
        }
        Pdlog.m3275i("App", str);
        return z;
    }

    public static boolean deleteBakDirectory(String str) {
        SecurityManager securityManager = new SecurityManager();
        boolean z = true;
        Pdlog.m3275i("App", str);
        if (str.equals("")) {
            return false;
        }
        File file = new File(str);
        securityManager.checkDelete(file.toString());
        Pdlog.m3275i("App", "check" + str + " :directory:" + file.isDirectory());
        if (!file.isDirectory()) {
            return false;
        }
        Pdlog.m3275i("App", "dire");
        for (String str2 : file.list()) {
            try {
                new File(file.toString() + "/" + str2.toString()).delete();
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        file.delete();
        Pdlog.m3275i("App", str);
        return z;
    }

    public static boolean deleteFile(String str) {
        SecurityManager securityManager = new SecurityManager();
        if (str.equals("")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + str);
        securityManager.checkDelete(file.toString());
        if (!file.isFile()) {
            return false;
        }
        try {
            Pdlog.m3275i("DirectoryManager deleteFile", str);
            file.delete();
            return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int deleteBlankPath(String str) {
        File file = new File(str);
        if (!file.canWrite()) {
            return 1;
        }
        if (file.list() == null || file.list().length <= 0) {
            return file.delete() ? 0 : 3;
        }
        return 2;
    }

    public static boolean reNamePath(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    public static boolean deleteFileWithPath(String str) {
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        try {
            securityManager.checkDelete(str);
        } catch (Exception unused) {
            Pdlog.m3275i("FileUpload", "checker execption");
        }
        if (!file.isFile()) {
            return false;
        }
        file.delete();
        Pdlog.m3275i("FileUpload", "delete file:" + str);
        return true;
    }

    public static void clearFileWithPath(String str) {
        List<File> listPathFiles = listPathFiles(str);
        if (listPathFiles.isEmpty()) {
            Pdlog.m3275i("App", "files empty");
            return;
        }
        for (File file : listPathFiles) {
            if (file.isDirectory()) {
                clearFileWithPath(file.getAbsolutePath());
                Pdlog.m3275i("App", "dir path");
            } else {
                file.delete();
            }
        }
    }

    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getExternalSDRoot() {
        return System.getenv().get("SECONDARY_STORAGE");
    }

    public static List<String> listPath(String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory() && !file2.getName().startsWith(".")) {
                    arrayList.add(file2.getAbsolutePath());
                }
            }
        }
        return arrayList;
    }

    public static List<File> listPathFiles(String str) {
        ArrayList arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                arrayList.add(file2);
            } else {
                listPath(file2.getAbsolutePath());
            }
        }
        return arrayList;
    }

    public static List<String> listPathFileNames(String str) {
        ArrayList arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                arrayList.add(file2.getPath());
            } else {
                listPath(file2.getAbsolutePath());
            }
        }
        return arrayList;
    }

    public static String getPathName(String str) {
        return str.substring(str.lastIndexOf(File.separator) + 1, str.length());
    }

    public static String getAppCache(Context context, String str) {
        String str2 = context.getCacheDir().getAbsolutePath() + "/" + str + "/";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String getLocalFilePath(String str) {
        if (isApkFile(str)) {
            return getSpecifyDirPath(1) + getFileName(str);
        }
        if (isJpgFile(str)) {
            return getSpecifyDirPath(2) + getFileName(str);
        }
        if (isMp3File(str)) {
            return getSpecifyDirPath(3) + getFileName(str);
        }
        if (!isMp4File(str)) {
            return "";
        }
        return getSpecifyDirPath(4) + getFileName(str);
    }

    public static String getSpecifyDirPath(int i) {
        String rootDirPath = getRootDirPath();
        if (i == 1) {
            return rootDirPath + "apk/";
        }
        if (i == 2) {
            return rootDirPath + "jpg/";
        }
        if (i == 3) {
            return rootDirPath + "mp3/";
        }
        if (i == 4) {
            return rootDirPath + "mp4/";
        }
        return rootDirPath + "other/";
    }

    public static String getRootDirPath() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return DEFAULT_ROOT_PATH;
        }
        return Environment.getExternalStorageDirectory() + "/pudu_kuaichuan/";
    }

    public static boolean isApkFile(String str) {
        return (str == null || str.equals("") || str.lastIndexOf(FileInfo.EXTEND_APK) <= 0) ? false : true;
    }

    public static boolean isJpgFile(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.lastIndexOf(FileInfo.EXTEND_JPG) > 0 || str.lastIndexOf(FileInfo.EXTEND_JPEG) > 0;
    }

    public static boolean isPngFile(String str) {
        return (str == null || str.equals("") || str.lastIndexOf(FileInfo.EXTEND_PNG) <= 0) ? false : true;
    }

    public static boolean isMp3File(String str) {
        return (str == null || str.equals("") || str.lastIndexOf(".mp3") <= 0) ? false : true;
    }

    public static boolean isMp4File(String str) {
        return (str == null || str.equals("") || str.lastIndexOf(FileInfo.EXTEND_MP4) <= 0) ? false : true;
    }

    public static File gerateLocalFile(String str) {
        String specifyDirPath;
        String fileName = getFileName(str);
        getRootDirPath();
        if (fileName.lastIndexOf(FileInfo.EXTEND_APK) > 0) {
            specifyDirPath = getSpecifyDirPath(1);
        } else if (fileName.lastIndexOf(FileInfo.EXTEND_JPG) > 0) {
            specifyDirPath = getSpecifyDirPath(2);
        } else if (fileName.lastIndexOf(".mp3") > 0) {
            specifyDirPath = getSpecifyDirPath(3);
        } else if (fileName.lastIndexOf(FileInfo.EXTEND_MP4) > 0) {
            specifyDirPath = getSpecifyDirPath(4);
        } else {
            specifyDirPath = getSpecifyDirPath(-1);
        }
        File file = new File(specifyDirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, fileName);
    }

    public static Drawable getApkThumbnail(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getPackageArchiveInfo(str, 1).applicationInfo;
            applicationInfo.sourceDir = str;
            applicationInfo.publicSourceDir = str;
            if (applicationInfo != null) {
                return applicationInfo.loadIcon(packageManager);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void copyFilesFromAssets(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.endsWith(SEPARATOR)) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        String str3 = "";
        if (TextUtils.isEmpty(str) || str.equals(SEPARATOR)) {
            str = "";
        } else if (str.endsWith(SEPARATOR)) {
            str = str.substring(0, str.length() - 1);
        }
        AssetManager assets = context.getAssets();
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            String[] list = assets.list(str);
            if (list.length > 0) {
                for (String str4 : list) {
                    if (!TextUtils.isEmpty(str)) {
                        str3 = str + SEPARATOR + str4;
                    }
                    String[] list2 = assets.list(str3);
                    if (!TextUtils.isEmpty(str3) && list2.length > 0) {
                        copyFilesFromAssets(context, str3, str2 + SEPARATOR + str4);
                    } else {
                        readInputStream(context.getApplicationContext(), str2 + SEPARATOR + str4, assets.open(str3));
                    }
                }
                return;
            }
            InputStream open = assets.open(str);
            if (str.contains(SEPARATOR)) {
                str = str.substring(str.lastIndexOf(SEPARATOR), str.length());
            }
            readInputStream(context.getApplicationContext(), str2 + SEPARATOR + str, open);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readInputStream(Context context, String str, InputStream inputStream) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            byte[] bArr = new byte[inputStream.available()];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    SpUtils.set(context, "write_default_music", true);
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static long getSDTotalSize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    public static long getSDAvailableSize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }
}

package com.pudutech.mpmodule.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.media.MediaConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class TransferFilesUtil {
    private static final String TAG = "TransferFilesUtil";
    private static Context sContext;

    public static void doUpgrade(Activity activity) {
        String[] list;
        sContext = activity.getApplicationContext();
        FileUtil.verifyStoragePermissions(activity);
        Pdlog.m3273d(TAG, "do music file transfer if necessary..");
        SharedPreferences sharedPreferences = activity.getSharedPreferences("music_version_control", 0);
        int i = sharedPreferences.getInt("music_module_version", 1);
        File file = new File(MusicFolderHelper.FOLDER_PATH_PUDU);
        boolean z = !file.exists() || !file.isDirectory() || (list = file.list()) == null || list.length <= 0;
        Pdlog.m3273d(TAG, "sp music module version: " + i + " is target folder empty: " + z);
        if (i > 1 || !z) {
            return;
        }
        Pdlog.m3277w(TAG, "old music files transfertation start in sub thread.");
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(MusicFolderHelper.FOLDER_PATH_QQ_MUSIC);
        arrayList.add(MusicFolderHelper.FOLDER_PATH_KUWO_MUSIC);
        arrayList.add(MusicFolderHelper.FOLDER_PATH_WANGYI_MUSIC);
        arrayList.add(MusicFolderHelper.FOLDER_PATH_ORIGIN);
        for (final String str : arrayList) {
            CThreadPoolExecutor.runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.utils.-$$Lambda$TransferFilesUtil$861WG8MFMgtD0AVOvUTgb0RniM8
                @Override // java.lang.Runnable
                public final void run() {
                    TransferFilesUtil.copyFolder(str, MusicFolderHelper.FOLDER_PATH_PUDU);
                }
            });
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("music_module_version", 2);
        edit.apply();
    }

    public static boolean copyFolder(String str, String str2) {
        File file;
        try {
            File file2 = new File(str2);
            if (!file2.exists() && !file2.mkdirs()) {
                Pdlog.m3274e(TAG, "copyFolder: cannot create directory.");
                return false;
            }
            File file3 = new File(str);
            if (!file3.exists()) {
                Pdlog.m3277w(TAG, "copyFolder:oldpath directory is not exist.");
                return false;
            }
            String[] list = file3.list();
            if (list != null && list.length != 0) {
                for (String str3 : list) {
                    if (str.endsWith(File.separator)) {
                        file = new File(str + str3);
                    } else {
                        file = new File(str + File.separator + str3);
                    }
                    if (file.isDirectory()) {
                        Pdlog.m3277w(TAG, "copyFolder:  oldFile is a directory. copy recrusivly");
                        copyFolder(file.getPath(), str2);
                    } else if (!file.exists()) {
                        Pdlog.m3277w(TAG, "copyFolder:  oldFile not exist.");
                    } else if (!file.isFile()) {
                        Pdlog.m3277w(TAG, "copyFolder:  oldFile not file.");
                    } else if (!file.canRead()) {
                        Pdlog.m3277w(TAG, "copyFolder:  oldFile cannot read.");
                    } else if (!isLegalFormat(str3)) {
                        Pdlog.m3277w(TAG, "copyFolder:  oldFile suffix is illegal.");
                    } else {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        FileOutputStream fileOutputStream = new FileOutputStream(str2 + file.getName());
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileInputStream.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        updateMediaStore(file, str2 + file.getName(), file.getName());
                    }
                }
                return true;
            }
            Pdlog.m3277w(TAG, "copyFolder: oldpath directory is empty.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isLegalFormat(String str) {
        for (String str2 : MediaConfig.SUPPORT_SUFFIX) {
            if (str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private static void updateMediaStore(File file, String str, String str2) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
        ContentValues contentValues = new ContentValues(7);
        contentValues.put("title", mediaMetadataRetriever.extractMetadata(7));
        contentValues.put("_display_name", str2);
        contentValues.put("_data", str);
        contentValues.put("album", mediaMetadataRetriever.extractMetadata(1));
        contentValues.put("artist", mediaMetadataRetriever.extractMetadata(2));
        contentValues.put(TypedValues.Transition.S_DURATION, mediaMetadataRetriever.extractMetadata(9));
        contentValues.put("_size", Long.valueOf(file.length()));
        sContext.getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues);
    }
}

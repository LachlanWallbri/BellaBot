package com.pudutech.mpmodule.media;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.utils.CThreadPoolExecutor;
import com.pudutech.mpmodule.utils.FileUtil;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.MusicFolderHelper;
import com.pudutech.mpmodule.utils.StringUtil;
import java.io.File;
import java.util.List;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MediaLoader {
    private static final String TAG = "MediaLoader";
    private final String[] MUSIC_PROJECTION = {TransferTable.COLUMN_ID, "title", "_display_name", "_data", "album", "artist", TypedValues.Transition.S_DURATION, "_size"};
    private Context mContext;
    private volatile OnMediaLoadListener mListener;
    private List<MediaFolder> mMediaFolders;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnMediaLoadListener {
        void onLoadFinished();

        void onLoadStart();

        void onLoadSuccessful(List<Media> list);
    }

    public MediaLoader(Context context) {
        this.mContext = context;
    }

    public void loadMedias(OnMediaLoadListener onMediaLoadListener) {
        Pdlog.m3273d(TAG, "loadMedias");
        this.mListener = onMediaLoadListener;
        synchronized (this) {
            if (this.mListener != null) {
                this.mListener.onLoadStart();
            }
        }
        CThreadPoolExecutor.runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.media.-$$Lambda$MediaLoader$apuOk9XXCuFGxqGJl4awLVIZw_k
            @Override // java.lang.Runnable
            public final void run() {
                MediaLoader.this.lambda$loadMedias$2$MediaLoader();
            }
        });
    }

    public /* synthetic */ void lambda$loadMedias$2$MediaLoader() {
        MediaFolder mediaFolder;
        int i;
        int i2 = 1;
        Pdlog.m3273d(TAG, "start query medias");
        Cursor query = this.mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, this.MUSIC_PROJECTION, null, null, "_data");
        if (query != null) {
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("_data"));
                if (!StringUtil.isEmpty(string)) {
                    File file = new File(string);
                    if (FileUtil.isExists(file) && (mediaFolder = getMediaFolder(file)) != null) {
                        String[] strArr = MediaConfig.SUPPORT_SUFFIX;
                        int length = strArr.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                i = 0;
                                break;
                            } else {
                                if (string.endsWith(strArr[i3])) {
                                    i = i2;
                                    break;
                                }
                                i3++;
                            }
                        }
                        if (i == 0) {
                            Object[] objArr = new Object[i2];
                            objArr[0] = "music file suffix is illegal, path: " + string;
                            Pdlog.m3273d(TAG, objArr);
                        } else {
                            String valueOf = String.valueOf(query.getInt(query.getColumnIndex(TransferTable.COLUMN_ID)));
                            String string2 = query.getString(query.getColumnIndex("title"));
                            String string3 = query.getString(query.getColumnIndex("_display_name"));
                            String string4 = query.getString(query.getColumnIndex("album"));
                            String string5 = query.getString(query.getColumnIndex("artist"));
                            int i4 = query.getInt(query.getColumnIndex(TypedValues.Transition.S_DURATION));
                            long j = query.getLong(query.getColumnIndex("_size"));
                            Object[] objArr2 = new Object[i2];
                            objArr2[0] = String.format("id=%s, title=%s, filename=%s, data=%s, album=%s, artist=%s, duration=%s, size=%s", valueOf, string2, string3, string, string4, string5, Integer.valueOf(i4), Long.valueOf(j));
                            Pdlog.m3273d(TAG, objArr2);
                            mediaFolder.getMedias().add(new Media(valueOf, string2, string3, string, string5, i4, j));
                            i2 = 1;
                        }
                    }
                }
            }
            query.close();
            if (this.mListener != null) {
                CThreadPoolExecutor.runOnMainThread(new Runnable() { // from class: com.pudutech.mpmodule.media.-$$Lambda$MediaLoader$R47BOmiakD3QEW3oqRZtkQdMmkg
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaLoader.this.lambda$null$0$MediaLoader();
                    }
                });
            }
        }
        if (this.mListener != null) {
            CThreadPoolExecutor.runOnMainThread(new Runnable() { // from class: com.pudutech.mpmodule.media.-$$Lambda$MediaLoader$mxbG0IjIoXqf18lqsibOHPafUx0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaLoader.this.lambda$null$1$MediaLoader();
                }
            });
        }
    }

    public /* synthetic */ void lambda$null$0$MediaLoader() {
        synchronized (this) {
            if (this.mListener != null) {
                this.mListener.onLoadSuccessful(getMediasFromFolder("pudumusic"));
            }
        }
    }

    public /* synthetic */ void lambda$null$1$MediaLoader() {
        synchronized (this) {
            if (this.mListener != null) {
                this.mListener.onLoadFinished();
            }
        }
    }

    private List<Media> getMediasFromFolder(String str) {
        List<MediaFolder> list = this.mMediaFolders;
        if (list != null && list.size() != 0) {
            for (MediaFolder mediaFolder : this.mMediaFolders) {
                if (StringUtil.equals(str, mediaFolder.getName())) {
                    return mediaFolder.getMedias();
                }
            }
        }
        return null;
    }

    private MediaFolder getMediaFolder(File file) {
        String str = file.getParentFile().getAbsolutePath() + File.separator;
        String str2 = StringUtil.equals(str, MusicFolderHelper.FOLDER_PATH_PUDU) ? "pudumusic" : null;
        if (StringUtil.isEmpty(str2)) {
            return null;
        }
        if (Lists.isEmpty(this.mMediaFolders)) {
            this.mMediaFolders = Lists.newArrayList();
        }
        for (MediaFolder mediaFolder : this.mMediaFolders) {
            if (StringUtil.equals(str, mediaFolder.getPath())) {
                return mediaFolder;
            }
        }
        MediaFolder mediaFolder2 = new MediaFolder();
        mediaFolder2.setId(UUID.randomUUID().toString());
        mediaFolder2.setName(str2);
        mediaFolder2.setPath(str);
        mediaFolder2.setMedias(Lists.newArrayList());
        this.mMediaFolders.add(mediaFolder2);
        return mediaFolder2;
    }

    public void release() {
        synchronized (this) {
            this.mListener = null;
        }
    }
}

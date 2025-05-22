package com.pudutech.bumblebee.presenter.utils;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import com.iflytek.aiui.AIUIConstant;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class SoundPoolHelper {
    public static final int RING_TYPE_ALARM = 2;
    public static final int RING_TYPE_MUSIC = 4;
    public static final int RING_TYPE_RING = 1;
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_MUSIC = 3;
    public static final int TYPE_NOTIFICATION = 5;
    public static final int TYPE_RING = 2;
    private int NOW_RINGTONE_TYPE;
    private int maxStream;
    private Map<String, Integer> ringtoneIds;
    private SoundPool soundPool;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface RING_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface TYPE {
    }

    public SoundPoolHelper() {
        this(1, 5);
    }

    public SoundPoolHelper(int i) {
        this(i, 4);
    }

    public SoundPoolHelper(int i, int i2) {
        this.NOW_RINGTONE_TYPE = 2;
        this.soundPool = new SoundPool(i, i2, 1);
        this.maxStream = i;
        this.ringtoneIds = new HashMap();
    }

    public SoundPoolHelper setRingtoneType(int i) {
        this.NOW_RINGTONE_TYPE = i;
        return this;
    }

    public SoundPoolHelper load(Context context, String str, int i) {
        int i2 = this.maxStream;
        if (i2 == 0) {
            return this;
        }
        this.maxStream = i2 - 1;
        this.ringtoneIds.put(str, Integer.valueOf(this.soundPool.load(context, i, 1)));
        return this;
    }

    public static String uri2Path(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!AIUIConstant.KEY_CONTENT.equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }

    public SoundPoolHelper load(Context context, String str, String str2) {
        int i = this.maxStream;
        if (i == 0) {
            return this;
        }
        this.maxStream = i - 1;
        this.ringtoneIds.put(str, Integer.valueOf(this.soundPool.load(str2, 1)));
        return this;
    }

    public void play(String str, boolean z) {
        if (this.ringtoneIds.containsKey(str)) {
            this.soundPool.play(this.ringtoneIds.get(str).intValue(), 1.0f, 1.0f, 1, z ? -1 : 0, 1.0f);
        }
    }

    public void play(String str, boolean z, float f) {
        if (this.ringtoneIds.containsKey(str)) {
            this.soundPool.play(this.ringtoneIds.get(str).intValue(), f, f, 1, z ? -1 : 0, 1.0f);
        }
    }

    public void playDefault() {
        play("default", false);
    }

    public void release() {
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.release();
        }
    }

    private Uri getSystemDefaultRingtoneUri(Context context) {
        try {
            return RingtoneManager.getActualDefaultRingtoneUri(context, this.NOW_RINGTONE_TYPE);
        } catch (Exception unused) {
            return null;
        }
    }
}

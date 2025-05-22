package com.pudutech.base;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class FileInfo implements Serializable {
    public static final String EXTEND_APK = ".apk";
    public static final String EXTEND_JPEG = ".jpeg";
    public static final String EXTEND_JPG = ".jpg";
    public static final String EXTEND_MP3 = ".mp3";
    public static final String EXTEND_MP4 = ".mp4";
    public static final String EXTEND_PNG = ".png";
    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_FAILURE = -1;
    public static final int FLAG_SUCCESS = 1;
    public static final int TYPE_APK = 1;
    public static final int TYPE_JPG = 2;
    public static final int TYPE_MP3 = 3;
    public static final int TYPE_MP4 = 4;
    private Bitmap bitmap;
    private String extra;
    private String filePath;
    private int fileType;
    private String name;
    private long procceed;
    private int result;
    private long size;
    private String sizeDesc;

    public FileInfo() {
    }

    public FileInfo(String str, long j) {
        this.filePath = str;
        this.size = j;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSizeDesc() {
        return this.sizeDesc;
    }

    public void setSizeDesc(String str) {
        this.sizeDesc = str;
    }

    public long getProcceed() {
        return this.procceed;
    }

    public void setProcceed(long j) {
        this.procceed = j;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public static String toJsonStr(FileInfo fileInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("filePath", fileInfo.getFilePath());
            jSONObject.put("fileType", fileInfo.getFileType());
            jSONObject.put("size", fileInfo.getSize());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static FileInfo toObject(String str) {
        FileInfo fileInfo = new FileInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = (String) jSONObject.get("filePath");
            long j = jSONObject.getLong("size");
            int i = jSONObject.getInt("fileType");
            fileInfo.setFilePath(str2);
            fileInfo.setSize(j);
            fileInfo.setFileType(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    public static String toJsonArrayStr(List<FileInfo> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (FileInfo fileInfo : list) {
                if (fileInfo != null) {
                    try {
                        jSONArray.put(new JSONObject(toJsonStr(fileInfo)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jSONArray.toString();
    }

    public String toString() {
        return "FileInfo{filePath='" + this.filePath + "', fileType=" + this.fileType + ", size=" + this.size + '}';
    }

    public static void main(String[] strArr) {
        System.out.println("Hello world");
        ArrayList arrayList = new ArrayList();
        new FileInfo();
        for (int i = 0; i < 3; i++) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath("/sdcard/test" + i + EXTEND_APK);
            fileInfo.setFileType(1);
            fileInfo.setSize((long) (i + 1000));
            arrayList.add(fileInfo);
        }
        System.out.println("List<FileInfo> to JsonStr: \n" + toJsonArrayStr(arrayList));
    }
}

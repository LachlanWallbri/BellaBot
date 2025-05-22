package com.pudutech.mpmodule.media;

import com.pudutech.mpmodule.utils.StringUtil;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class Media implements Serializable {
    private static final long serialVersionUID = 5240719059162235680L;
    private String artist;
    private int duration;
    private String fileName;

    /* renamed from: id */
    private String f6792id;
    private String path;
    private long size;
    private String title;

    public Media() {
    }

    public Media(String str, String str2, String str3, String str4, String str5, int i, long j) {
        this.f6792id = str;
        this.title = str2;
        this.fileName = str3;
        this.path = str4;
        this.artist = str5;
        this.duration = i;
        this.size = j;
    }

    public String getId() {
        return this.f6792id;
    }

    public void setId(String str) {
        this.f6792id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int hashCode() {
        try {
            return this.f6792id.hashCode();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.hashCode();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Media)) {
            return false;
        }
        try {
            if (StringUtil.equals(this.f6792id, ((Media) obj).getId())) {
                return StringUtil.equals(this.path, ((Media) obj).getPath());
            }
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }

    public String toString() {
        return "Media{id='" + this.f6792id + "', title='" + this.title + "', fileName='" + this.fileName + "', path='" + this.path + "', artist='" + this.artist + "', duration=" + this.duration + ", size=" + this.size + '}';
    }
}

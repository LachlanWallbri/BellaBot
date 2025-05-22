package com.pudutech.mpcomponent;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class Music implements Serializable {
    private static final long serialVersionUID = 2654352606890565632L;

    /* renamed from: id */
    private String f6770id;
    private String modeId;
    private String path;
    private long seekTime;

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public long getSeekTime() {
        return this.seekTime;
    }

    public void setSeekTime(long j) {
        this.seekTime = j;
    }

    public String getId() {
        String str = this.f6770id;
        return str == null ? "" : str;
    }

    public void setId(String str) {
        this.f6770id = str;
    }

    public String getModeId() {
        String str = this.modeId;
        return str == null ? "" : str;
    }

    public void setModeId(String str) {
        this.modeId = str;
    }

    public String toString() {
        return "Music{path='" + this.path + "', seekTime=" + this.seekTime + ", id='" + this.f6770id + "'}";
    }
}

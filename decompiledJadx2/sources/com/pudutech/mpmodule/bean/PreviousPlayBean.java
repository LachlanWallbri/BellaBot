package com.pudutech.mpmodule.bean;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class PreviousPlayBean implements Serializable {
    private static final long serialVersionUID = -7687451446204457504L;

    /* renamed from: id */
    private Long f6788id;
    private String modeId;
    private String playedId;
    private long seekTime;

    public PreviousPlayBean(Long l, String str, String str2, long j) {
        this.f6788id = l;
        this.modeId = str;
        this.playedId = str2;
        this.seekTime = j;
    }

    public PreviousPlayBean(String str, String str2, long j) {
        this.modeId = str;
        this.playedId = str2;
        this.seekTime = j;
    }

    public PreviousPlayBean() {
    }

    public Long getId() {
        return this.f6788id;
    }

    public void setId(Long l) {
        this.f6788id = l;
    }

    public String getModeId() {
        return this.modeId;
    }

    public void setModeId(String str) {
        this.modeId = str;
    }

    public String getPlayedId() {
        return this.playedId;
    }

    public void setPlayedId(String str) {
        this.playedId = str;
    }

    public long getSeekTime() {
        return this.seekTime;
    }

    public void setSeekTime(long j) {
        this.seekTime = j;
    }
}

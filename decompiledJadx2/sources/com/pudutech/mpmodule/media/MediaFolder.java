package com.pudutech.mpmodule.media;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MediaFolder {

    /* renamed from: id */
    private String f6793id;
    private List<Media> medias;
    private String name;
    private String path;

    public String getId() {
        return this.f6793id;
    }

    public void setId(String str) {
        this.f6793id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public List<Media> getMedias() {
        return this.medias;
    }

    public void setMedias(List<Media> list) {
        this.medias = list;
    }

    public String toString() {
        return "MediaFolder{id='" + this.f6793id + "', name='" + this.name + "', path='" + this.path + "', medias=" + this.medias + '}';
    }
}

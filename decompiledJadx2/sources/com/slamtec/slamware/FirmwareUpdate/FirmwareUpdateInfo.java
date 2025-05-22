package com.slamtec.slamware.FirmwareUpdate;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class FirmwareUpdateInfo {
    private String brief;
    private String current;
    private String latest;
    private String releaseDate;

    public FirmwareUpdateInfo(String str, String str2, String str3, String str4) {
        this.current = str;
        this.latest = str2;
        this.releaseDate = str3;
        this.brief = str4;
    }

    public String getCurrent() {
        return this.current;
    }

    public String getLatest() {
        return this.latest;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getBrief() {
        return this.brief;
    }
}

package com.slamtec.slamware.robot;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class CompositePose {
    private static final char FLAG_HEADING_SENSITIVE = 1;
    private char flags;
    private MapMetaData metaData;
    private String name;
    private Pose pose;
    private ArrayList<String> tags;

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public Pose getPose() {
        return this.pose;
    }

    public char getFlags() {
        return this.flags;
    }

    public MapMetaData getMetaData() {
        return this.metaData;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTags(ArrayList<String> arrayList) {
        this.tags = arrayList;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }

    public void setFlags(char c) {
        this.flags = c;
    }

    public void setMetaData(MapMetaData mapMetaData) {
        this.metaData = mapMetaData;
    }

    public boolean isHeadingSensitive() {
        return (this.flags & 1) != 0;
    }
}

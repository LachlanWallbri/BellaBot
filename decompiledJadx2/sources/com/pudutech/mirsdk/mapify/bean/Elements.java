package com.pudutech.mirsdk.mapify.bean;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.List;
import kotlin.Metadata;

/* compiled from: Elements.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b \n\u0002\u0010\u0013\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R\"\u0010*\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R\u001a\u00103\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\f\"\u0004\b5\u0010\u000eR\u001a\u00106\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\u001c\u00109\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001e\"\u0004\b;\u0010 R\u001c\u0010<\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001e\"\u0004\b>\u0010 R(\u0010?\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040+\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010-\"\u0004\bA\u0010/R\u001e\u0010B\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001c\u0010H\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u001e\"\u0004\bJ\u0010 R\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\f\"\u0004\bS\u0010\u000e¨\u0006T"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/bean/Elements;", "", "()V", "align_dist", "", "getAlign_dist", "()Ljava/lang/Double;", "setAlign_dist", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", MapElement.Key.DIR, "getDir", "()D", "setDir", "(D)V", MapElement.Key.DIR_MODE, "", "getDirMode", "()I", "setDirMode", "(I)V", "doubleDir", "getDoubleDir", "setDoubleDir", "dualWidth", "getDualWidth", "setDualWidth", MapElement.Key.GROUP, "", "getGroup", "()Ljava/lang/String;", "setGroup", "(Ljava/lang/String;)V", "high_precision", "", "getHigh_precision", "()Z", "setHigh_precision", "(Z)V", "id", "getId", "setId", "idList", "", "getIdList", "()Ljava/util/List;", "setIdList", "(Ljava/util/List;)V", "leftMode", "getLeftMode", "setLeftMode", "maxSpeed", "getMaxSpeed", "setMaxSpeed", "middleMode", "getMiddleMode", "setMiddleMode", "mode", "getMode", "setMode", "name", "getName", "setName", "poseList", "getPoseList", "setPoseList", "sort_weight", "getSort_weight", "()Ljava/lang/Integer;", "setSort_weight", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "type", "getType", "setType", MapElement.Key.VECTOR, "", "getVector", "()[D", "setVector", "([D)V", "width", "getWidth", "setWidth", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Elements {
    private Double align_dist;
    private double dir;
    private int doubleDir;
    private double dualWidth;
    private String group;
    private boolean high_precision;
    private String id;
    private List<String> idList;
    private boolean leftMode;
    private double maxSpeed;
    private String mode;
    private String name;
    private List<List<Double>> poseList;
    private Integer sort_weight;
    private String type;
    private double[] vector;
    private double width;
    private boolean middleMode = true;
    private int dirMode = 2;

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final double[] getVector() {
        return this.vector;
    }

    public final void setVector(double[] dArr) {
        this.vector = dArr;
    }

    public final double getWidth() {
        return this.width;
    }

    public final void setWidth(double d) {
        this.width = d;
    }

    public final double getMaxSpeed() {
        return this.maxSpeed;
    }

    public final void setMaxSpeed(double d) {
        this.maxSpeed = d;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        this.mode = str;
    }

    public final double getDualWidth() {
        return this.dualWidth;
    }

    public final void setDualWidth(double d) {
        this.dualWidth = d;
    }

    public final boolean getLeftMode() {
        return this.leftMode;
    }

    public final void setLeftMode(boolean z) {
        this.leftMode = z;
    }

    public final boolean getMiddleMode() {
        return this.middleMode;
    }

    public final void setMiddleMode(boolean z) {
        this.middleMode = z;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final int getDoubleDir() {
        return this.doubleDir;
    }

    public final void setDoubleDir(int i) {
        this.doubleDir = i;
    }

    public final double getDir() {
        return this.dir;
    }

    public final void setDir(double d) {
        this.dir = d;
    }

    public final int getDirMode() {
        return this.dirMode;
    }

    public final void setDirMode(int i) {
        this.dirMode = i;
    }

    public final String getGroup() {
        return this.group;
    }

    public final void setGroup(String str) {
        this.group = str;
    }

    public final boolean getHigh_precision() {
        return this.high_precision;
    }

    public final void setHigh_precision(boolean z) {
        this.high_precision = z;
    }

    public final Integer getSort_weight() {
        return this.sort_weight;
    }

    public final void setSort_weight(Integer num) {
        this.sort_weight = num;
    }

    public final Double getAlign_dist() {
        return this.align_dist;
    }

    public final void setAlign_dist(Double d) {
        this.align_dist = d;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final List<String> getIdList() {
        return this.idList;
    }

    public final void setIdList(List<String> list) {
        this.idList = list;
    }

    public final List<List<Double>> getPoseList() {
        return this.poseList;
    }

    public final void setPoseList(List<List<Double>> list) {
        this.poseList = list;
    }
}

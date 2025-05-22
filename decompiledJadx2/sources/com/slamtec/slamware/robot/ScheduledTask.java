package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class ScheduledTask {
    private int day;
    private boolean enabled;
    private int hour;
    private int maxDuration;
    private int minute;
    private int month;
    private String task;
    private int taskId;
    private int weekRepeat;
    private int year;

    public ScheduledTask(int i, String str, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.taskId = i;
        this.task = str;
        this.weekRepeat = i2;
        this.enabled = z;
        this.maxDuration = i3;
        this.year = i4;
        this.month = i5;
        this.day = i6;
        this.hour = i7;
        this.minute = i8;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int i) {
        this.taskId = i;
    }

    public String getTask() {
        return this.task;
    }

    public void setTask(String str) {
        this.task = str;
    }

    public int getWeekRepeat() {
        return this.weekRepeat;
    }

    public void setWeekRepeat(int i) {
        this.weekRepeat = i;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public int getMaxDuration() {
        return this.maxDuration;
    }

    public void setMaxDuration(int i) {
        this.maxDuration = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int i) {
        this.minute = i;
    }
}

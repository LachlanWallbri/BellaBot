package com.iflytek.aiui;

/* loaded from: classes4.dex */
public enum AIUIFingerType {
    UNKNOWN("", ""),
    CLAP("Clap", "拍手"),
    OK("OK", "OK"),
    STOP_SIGN("Stop sign", "停止"),
    SHAKING_HANDS("Shaking hands", "招手"),
    PALM_TO_FIRST("Palm to fist", "握拳"),
    SWIPING_UP("Swiping up", "上划"),
    SWIPING_DOWN("Swiping down", "下划"),
    SWIPING_RIGHT("Swiping right", "右划"),
    SWIPING_LEFT("Swiping left", "左划"),
    SWEEP_CROSS("Sweep cross", "划错号"),
    SWEEP_CHECKMARK("Sweep checkmark", "划对号"),
    THUMB_UP("Thumb up", "拇指向上"),
    THUMB_DOWN("Thumb down", "拇指向下"),
    ROTATE_FINGER_CLOCKWISE("Rotate finger clockwise", "顺时针划圈"),
    ROTATE_FINGER_COUNTERCLOCKWISE("Rotate finger counterclockwise", "逆时针划圈");

    private String name;
    private String tag;

    AIUIFingerType(String str, String str2) {
        this.tag = str;
        this.name = str2;
    }

    public static AIUIFingerType getValueByTag(String str) {
        for (AIUIFingerType aIUIFingerType : values()) {
            if (aIUIFingerType.tag.equals(str)) {
                return aIUIFingerType;
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return this.name;
    }

    public String getTag() {
        return this.tag;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }
}
